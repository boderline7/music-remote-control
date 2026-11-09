import SwiftUI
import UIKit
import WebKit

/// 原生层 → 网页的单向桥。iOS 不给硬件音量键事件，WKWebView 也收不到，
/// 所以由 VolumeKeyWatcher 拦到按键后调这里，再交给页面的 stepVolume。
enum WebViewBridge {
    static weak var webView: WKWebView?

    static func stepVolume(_ dir: Int) {
        guard let webView else { return }
        let js = "window.carRemoteStepVolume(\(dir))"
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
        let key = "\(url.absoluteString)#\(reloadID)"
        guard context.coordinator.loadedKey != key else { return }
        context.coordinator.loadedKey = key
        let req = URLRequest(url: url,
                             cachePolicy: .reloadIgnoringLocalAndRemoteCacheData,
                             timeoutInterval: 10)
        webView.load(req)
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
