import SwiftUI
import UIKit
import WebKit

/// 原生层 → 网页的单向桥。iOS 不给硬件音量键事件，WKWebView 也收不到，
/// 所以由 VolumeKeyWatcher 拦到按键后调这里，再交给页面的 stepVolume。
enum WebViewBridge {
    // 之前用 weak：SwiftUI 在连接状态切换时若重建 UIViewRepresentable，这个引用会先变 nil，
    // 音量键就静默失效。改成强引用，并在 dismantleUIView 里显式清掉。
    static var webView: WKWebView?

    static func stepVolume(_ dir: Int) {
        guard let webView else {
            print("volume key: webView not ready, dropped")
            return
        }
        // documentStart 已注入同名函数，这里用 && 兜底，页面未开始加载也不会抛异常
        let js = "window.carRemoteStepVolume && window.carRemoteStepVolume(\(dir))"
        if Thread.isMainThread {
            webView.evaluateJavaScript(js, completionHandler: nil)
        } else {
            DispatchQueue.main.async { webView.evaluateJavaScript(js, completionHandler: nil) }
        }
    }
}

/// 直接加载车机端的 remote.html，WebSocket 由页面自己按 location.host 连到同一个 IP:18765。
/// 这里不做协议翻译、不注入 JS：网页端有什么功能，App 里就有什么功能。
struct RemoteWebView: UIViewRepresentable {
    let url: URL
    let reloadID: Int
    let onFailure: (String) -> Void

    func makeCoordinator() -> Coordinator {
        Coordinator(onFailure: onFailure)
    }

    func makeUIView(context: Context) -> WKWebView {
        let config = WKWebViewConfiguration()
        config.allowsInlineMediaPlayback = true
        config.mediaTypesRequiringUserActionForPlayback = []
        config.dataDetectorTypes = []
        // remote.html 用 localStorage 记住 deviceId / token，必须用持久化存储
        config.websiteDataStore = .default()
        // 页面脚本执行前先把 carRemoteStepVolume 装成「排队」版本：
        // 硬件音量键在网页刚打开、remote.html 还没执行完时按下也不会丢，
        // 页面加载完会把队列补放一遍（见 remote.html 末尾）。
        let earlyVolumeJS = """
        window.__carRemoteVolumeQueue = [];
        window.carRemoteStepVolume = function (dir) { window.__carRemoteVolumeQueue.push(dir); };
        """
        config.userContentController.addUserScript(
            WKUserScript(source: earlyVolumeJS,
                         injectionTime: .atDocumentStart,
                         forMainFrameOnly: true))

        let webView = WKWebView(frame: .zero, configuration: config)
        webView.navigationDelegate = context.coordinator
        webView.isOpaque = false
        webView.backgroundColor = UIColor(red: 0.043, green: 0.043, blue: 0.051, alpha: 1)
        webView.scrollView.backgroundColor = webView.backgroundColor
        webView.scrollView.contentInsetAdjustmentBehavior = .never
        // 下拉刷新：网页里也有手势实现，但 iOS 壳改用原生 UIRefreshControl 才有 spinner 反馈，
        // 所以 remote.html 里 client=ios 时会跳过自己的下拉逻辑，避免一次下拉发两条 refresh。
        let refresh = UIRefreshControl()
        refresh.tintColor = UIColor(red: 0.98, green: 0.18, blue: 0.28, alpha: 1)
        refresh.addTarget(context.coordinator,
                          action: #selector(Coordinator.didPullToRefresh(_:)),
                          for: .valueChanged)
        webView.scrollView.refreshControl = refresh
        webView.scrollView.bounces = true
        context.coordinator.webView = webView
        WebViewBridge.webView = webView
        return webView
    }

    func updateUIView(_ webView: WKWebView, context: Context) {
        // 每次 SwiftUI 更新都把桥重新指向当前 WKWebView，避免重建后桥落在旧实例上
        WebViewBridge.webView = webView
        let key = "\(url.absoluteString)#\(reloadID)"
        guard context.coordinator.loadedKey != key else { return }
        context.coordinator.loadedKey = key
        let req = URLRequest(url: url,
                             cachePolicy: .reloadIgnoringLocalAndRemoteCacheData,
                             timeoutInterval: 10)
        webView.load(req)
    }

    static func dismantleUIView(_ uiView: WKWebView, coordinator: Coordinator) {
        if WebViewBridge.webView === uiView { WebViewBridge.webView = nil }
    }

    final class Coordinator: NSObject, WKNavigationDelegate {
        let onFailure: (String) -> Void
        var loadedKey: String?
        weak var webView: WKWebView?

        init(onFailure: @escaping (String) -> Void) {
            self.onFailure = onFailure
        }

        @objc func didPullToRefresh(_ control: UIRefreshControl) {
            guard let webView else {
                control.endRefreshing()
                return
            }
            webView.evaluateJavaScript("window.carRemoteRefresh && window.carRemoteRefresh()") { _, _ in
                DispatchQueue.main.async { control.endRefreshing() }
            }
        }

        func webView(_ webView: WKWebView,
                     didFailProvisionalNavigation navigation: WKNavigation!,
                     withError error: Error) {
            report(error)
        }

        func webView(_ webView: WKWebView,
                     didFail navigation: WKNavigation!,
                     withError error: Error) {
            report(error)
        }

        func webViewWebContentProcessDidTerminate(_ webView: WKWebView) {
            loadedKey = nil
            Task { @MainActor in
                self.onFailure("页面进程被系统回收，正在重新连接")
            }
        }

        private func report(_ error: Error) {
            let e = error as NSError
            if e.domain == NSURLErrorDomain && e.code == NSURLErrorCancelled { return }
            loadedKey = nil
            Task { @MainActor in
                self.onFailure(e.localizedDescription)
            }
        }
    }
}
