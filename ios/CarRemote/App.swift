import SwiftUI
import UIKit

@main
struct CarRemoteApp: App {
    @StateObject private var link = CarLink()

    var body: some Scene {
        WindowGroup {
            RootView()
                .environmentObject(link)
                .preferredColorScheme(.dark)
        }
    }
}

enum LinkPhase {
    case ready
    case offline(String)

    var isReady: Bool {
        switch self {
        case .ready: return true
        case .offline: return false
        }
    }
}

/// 只负责一件事：把车机的固定 IP 记牢，确认在线后让 WebView 直连。
/// 刻意不做 mDNS / Bonjour / carremote.local 域名解析 —— 网页端的不稳定就出在那条链路上。
@MainActor
final class CarLink: ObservableObject {
    @Published var host: String
    @Published var port: Int
    @Published var phase: LinkPhase = .offline("正在连接车机…")
    @Published var reloadID = 0

    private let kHost = "carRemoteHost"
    private let kPort = "carRemotePort"
    private var retryTask: Task<Void, Never>?
    private var started = false

    init() {
        let d = UserDefaults.standard
        let fallback = Bundle.main.object(forInfoDictionaryKey: "CarRemoteDefaultHost") as? String
        let saved = d.string(forKey: "carRemoteHost")
        if let saved, !saved.isEmpty {
            host = saved
        } else if let fallback, !fallback.isEmpty, !fallback.hasPrefix("$(") {
            host = fallback
        } else {
            host = "192.168.43.1"
        }
        let p = d.integer(forKey: "carRemotePort")
        port = (p > 0 && p < 65536) ? p : 18765
    }

    var address: String { "\(host):\(port)" }
    var url: URL? { URL(string: "http://\(host):\(port)/") }

    func start() {
        guard !started else { return }
        started = true
        refresh()
    }

    /// 先用一次短超时的 HTTP 探测确认车机在线，再让 WebView 加载，
    /// 避免车机没开机时 WKWebView 卡在一片白屏上。
    func refresh() {
        retryTask?.cancel()
        retryTask = nil
        Task { await probe() }
    }

    /// 保存地址。允许直接粘 "http://192.168.1.8:18765/" 这种整串。
    func apply(address raw: String, port rawPort: String) {
        var s = raw.trimmingCharacters(in: .whitespacesAndNewlines)
        if let r = s.range(of: "://") { s = String(s[r.upperBound...]) }
        s = String(s.split(separator: "/").first ?? Substring(s))

        var p = Int(rawPort.trimmingCharacters(in: .whitespacesAndNewlines)) ?? port
        if let colon = s.lastIndex(of: ":"),
           let n = Int(String(s[s.index(after: colon)...])),
           n > 0, n < 65536 {
            p = n
            s = String(s[..<colon])
        }
        if s.isEmpty { s = host }

        host = s
        port = p
        UserDefaults.standard.set(s, forKey: kHost)
        UserDefaults.standard.set(p, forKey: kPort)
        reloadID += 1
        phase = .offline("正在连接 \(address)…")
        refresh()
    }

    func fail(_ message: String) { setOffline(message) }

    private func probe() async {
        guard let url else {
            setOffline("地址无效：\(address)")
            return
        }
        var req = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData,
                             timeoutInterval: 1.5)
        req.httpMethod = "GET"
        do {
            let (_, response) = try await URLSession.shared.data(for: req)
            let code = (response as? HTTPURLResponse)?.statusCode ?? -1
            if (200..<400).contains(code) {
                let wasReady = phase.isReady
                phase = .ready
                retryTask?.cancel()
                retryTask = nil
                // 从离线恢复 / 换地址 / 页面被回收时才重载；正常回前台不打断正在播放的页面。
                if !wasReady { reloadID += 1 }
            } else {
                setOffline("车机返回 HTTP \(code)")
            }
        } catch {
            setOffline("连不上 \(address) · \((error as NSError).localizedDescription)")
        }
    }

    private func setOffline(_ message: String) {
        phase = .offline(message)
        guard retryTask == nil else { return }
        retryTask = Task { [weak self] in
            while !Task.isCancelled {
                try? await Task.sleep(nanoseconds: 2_000_000_000)
                guard let self else { return }
                await self.probe()
            }
        }
    }
}

struct RootView: View {
    @EnvironmentObject private var link: CarLink
    @State private var showSetup = false

    var body: some View {
        ZStack(alignment: .topTrailing) {
            Color(red: 0.043, green: 0.043, blue: 0.051).ignoresSafeArea()

            switch link.phase {
            case .ready:
                if let url = link.url {
                    RemoteWebView(url: url, reloadID: link.reloadID) { message in
                        link.fail(message)
                    }
                    .ignoresSafeArea()
                }
            case .offline(let message):
                OfflineView(message: message,
                            retry: { link.refresh() },
                            changeAddress: { showSetup = true })
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            }

            Button {
                showSetup = true
            } label: {
                Image(systemName: "gearshape.fill")
                    .font(.system(size: 15, weight: .semibold))
                    .foregroundStyle(.white.opacity(0.8))
                    .padding(10)
                    .background(.black.opacity(0.35), in: Circle())
            }
            .padding(.top, 12)
            .padding(.trailing, 14)
            .opacity(link.phase.isReady ? 0.5 : 1)
        }
        .sheet(isPresented: $showSetup) {
            SetupView().environmentObject(link)
        }
        .onAppear { link.start() }
        .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
            link.refresh()
        }
    }
}

struct OfflineView: View {
    let message: String
    let retry: () -> Void
    let changeAddress: () -> Void

    var body: some View {
        VStack(spacing: 16) {
            Image(systemName: "wifi.exclamationmark")
                .font(.system(size: 44))
                .foregroundStyle(.orange)
            Text("连不上车机").font(.title2.bold())
            Text(message)
                .font(.footnote)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
            Text("每 2 秒自动重试一次，车机开机或打开遥控服务后会自动连上。")
                .font(.caption)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
            HStack(spacing: 12) {
                Button("立即重试") { retry() }.buttonStyle(.borderedProminent)
                Button("修改地址") { changeAddress() }.buttonStyle(.bordered)
            }
            .padding(.top, 6)
        }
        .padding(32)
    }
}
