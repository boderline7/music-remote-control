import SwiftUI
import WebKit

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
        webView.scrollView.bounces = false
        webView.scrollView.contentInsetAdjustmentBehavior = .never
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

        init(onFailure: @escaping (String) -> Void) {
            self.onFailure = onFailure
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
