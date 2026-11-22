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

/// 直接加载车机端下发的那一页，WebSocket 由页面自己按 location.host 连当前端口。
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

        // 网页的「复制 / 导出 / 下载」都从这里进原生：
        // WKWebView 不支持 a[download]，navigator.clipboard 在 http 页面也不存在，
        // 只靠网页自己点不动（iOS 上直接跳 txt 还会弹一个预览大框）。
        config.userContentController.add(context.coordinator, name: "carRemote")

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
        uiView.configuration.userContentController.removeScriptMessageHandler(forName: "carRemote")
        if WebViewBridge.webView === uiView { WebViewBridge.webView = nil }
    }

    final class Coordinator: NSObject, WKNavigationDelegate, WKScriptMessageHandler, WKDownloadDelegate {
        let onFailure: (String) -> Void
        var loadedKey: String?
        weak var webView: WKWebView?
        /// WKDownload 没有回调带回目标路径，按对象记住，finish 时再弹分享
        private var downloadDestinations: [ObjectIdentifier: URL] = [:]

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

        // MARK: - 网页 → 原生（复制 / 导出 / 下载）

        func userContentController(_ userContentController: WKUserContentController,
                                   didReceive message: WKScriptMessage) {
            guard message.name == "carRemote",
                  let body = message.body as? [String: Any] else { return }
            let action = body["action"] as? String ?? ""
            switch action {
            case "copy":
                if let text = body["text"] as? String {
                    UIPasteboard.general.string = text
                }
            case "save":
                guard let text = body["text"] as? String else { return }
                let name = Coordinator.safeName(body["name"] as? String ?? "car-log.txt")
                let dst = FileManager.default.temporaryDirectory.appendingPathComponent(name)
                do {
                    try text.data(using: .utf8)?.write(to: dst, options: .atomic)
                    presentShare([dst])
                } catch {
                    print("save failed: \(error)")
                }
            case "download":
                if let s = body["url"] as? String, let u = URL(string: s) {
                    fetchAndShare(u, name: body["name"] as? String)
                }
            default:
                break
            }
        }

        // MARK: - 下载

        /// 原生下载（不经过 WKWebView）：手机能直连记录仪时就是它自己的 80 端口，最快。
        private func fetchAndShare(_ url: URL, name: String?) {
            let task = URLSession.shared.downloadTask(with: url) { [weak self] tmp, resp, err in
                guard let self else { return }
                guard let tmp, err == nil else {
                    DispatchQueue.main.async {
                        guard let vc = Coordinator.topViewController() else { return }
                        let alert = UIAlertController(
                            title: "下载失败",
                            message: err?.localizedDescription ?? "连接中断",
                            preferredStyle: .alert)
                        alert.addAction(UIAlertAction(title: "好", style: .default))
                        vc.present(alert, animated: true)
                    }
                    return
                }
                let suggested = name ?? resp?.suggestedFilename ?? "car-file"
                let dst = FileManager.default.temporaryDirectory
                    .appendingPathComponent(Coordinator.safeName(suggested))
                try? FileManager.default.removeItem(at: dst)
                do {
                    try FileManager.default.moveItem(at: tmp, to: dst)
                    self.presentShare([dst])
                } catch {
                    self.presentShare([tmp])
                }
            }
            task.resume()
        }

        /// 系统分享面板：可以直接「存储到文件」，LiveContainer 侧载也照常工作
        private func presentShare(_ items: [Any]) {
            DispatchQueue.main.async {
                guard let vc = Coordinator.topViewController() else { return }
                let av = UIActivityViewController(activityItems: items, applicationActivities: nil)
                if let pop = av.popoverPresentationController {
                    pop.sourceView = vc.view
                    pop.sourceRect = CGRect(x: vc.view.bounds.midX,
                                            y: vc.view.bounds.maxY,
                                            width: 1, height: 1)
                    pop.permittedArrowDirections = []
                }
                vc.present(av, animated: true)
            }
        }

        private static func topViewController() -> UIViewController? {
            let scenes = UIApplication.shared.connectedScenes.compactMap { $0 as? UIWindowScene }
            let window = scenes.flatMap { $0.windows }.first(where: { $0.isKeyWindow })
                ?? scenes.first?.windows.first
            var vc = window?.rootViewController
            while let p = vc?.presentedViewController { vc = p }
            if let nav = vc as? UINavigationController { return nav.visibleViewController ?? nav }
            if let tab = vc as? UITabBarController { return tab.selectedViewController ?? tab }
            return vc
        }

        private static func safeName(_ raw: String) -> String {
            let bad = CharacterSet(charactersIn: "\\/:*?\"<>|")
            let cleaned = raw.components(separatedBy: bad).joined(separator: "_")
            let trimmed = cleaned.trimmingCharacters(in: .whitespacesAndNewlines)
            return trimmed.isEmpty ? "car-file" : String(trimmed.prefix(120))
        }

        // MARK: - WKDownloadDelegate（页面里直接点 <a download> 时兜底）

        func webView(_ webView: WKWebView,
                     decidePolicyFor navigationAction: WKNavigationAction,
                     decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
            if navigationAction.shouldPerformDownload {
                decisionHandler(.download)
                return
            }
            decisionHandler(.allow)
        }

        func webView(_ webView: WKWebView,
                     decidePolicyFor navigationResponse: WKNavigationResponse,
                     decisionHandler: @escaping (WKNavigationResponsePolicy) -> Void) {
            if !navigationResponse.canShowMIMEType {
                decisionHandler(.download)
                return
            }
            decisionHandler(.allow)
        }

        func webView(_ webView: WKWebView,
                     navigationAction: WKNavigationAction,
                     didBecome download: WKDownload) {
            download.delegate = self
        }

        func webView(_ webView: WKWebView,
                     navigationResponse: WKNavigationResponse,
                     didBecome download: WKDownload) {
            download.delegate = self
        }

        func download(_ download: WKDownload,
                      decideDestinationUsing response: URLResponse,
                      suggestedFilename: String,
                      completionHandler: @escaping (URL?) -> Void) {
            let name = Coordinator.safeName(suggestedFilename.isEmpty ? "car-file" : suggestedFilename)
            let dst = FileManager.default.temporaryDirectory.appendingPathComponent(name)
            try? FileManager.default.removeItem(at: dst)
            downloadDestinations[ObjectIdentifier(download)] = dst
            completionHandler(dst)
        }

        func downloadDidFinish(_ download: WKDownload) {
            if let dst = downloadDestinations.removeValue(forKey: ObjectIdentifier(download)) {
                presentShare([dst])
            }
        }

        func download(_ download: WKDownload, didFailWithError error: Error, resumeData: Data?) {
            downloadDestinations.removeValue(forKey: ObjectIdentifier(download))
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
