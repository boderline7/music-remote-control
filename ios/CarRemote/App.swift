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
    case idle
    case connecting(String)
    case ready
    case failed(String)

    var isReady: Bool {
        if case .ready = self { return true }
        return false
    }

    var label: String {
        switch self {
        case .idle: return "未连接 · 正在搜索局域网"
        case .connecting(let s): return s
        case .ready: return "已连接"
        case .failed(let s): return s
        }
    }
}

/// 连接决策与安卓端 MainActivity / RemoteClient 对齐：
/// 1. 启动即开始 `_cloudmusic-car._tcp` 服务发现；
/// 2. 有上次成功地址就先直连（快），2.5 秒还没连上再扫当前 /24 网段；
/// 3. 连上停发现、保存地址；断开/失败重新发现并自动重试；
/// 4. 全程只拿 IPv4，不解析 `carremote.local` 之类的域名。
@MainActor
final class CarLink: ObservableObject {
    @Published var phase: LinkPhase = .idle
    @Published var found: [BonjourFinder.Endpoint] = []
    @Published var scanned: [String] = []
    @Published var scanning = false
    @Published var host: String = ""
    @Published var port: Int = 18765
    @Published var reloadID = 0

    private let kHost = "carRemoteHost"
    private let kPort = "carRemotePort"
    private let finder = BonjourFinder()
    private var started = false
    private var maintenance: Task<Void, Never>?
    private var tick = 0

    init() {
        let d = UserDefaults.standard
        host = d.string(forKey: kHost) ?? ""
        let p = d.integer(forKey: kPort)
        port = (p > 0 && p < 65536) ? p : 18765

        finder.onFound = { [weak self] endpoint in
            Task { @MainActor in
                guard let self else { return }
                self.found.removeAll { $0.host == endpoint.host }
                self.found.append(endpoint)
                // 安卓把这个列表留给用户手点；iOS 在没连上时直接采用，少一步操作。
                // 已经是 connecting / ready 就不打断正在进行的连接。
                switch self.phase {
                case .ready, .connecting: break
                default: self.connect(endpoint.host, endpoint.port)
                }
            }
        }
        finder.onLost = { [weak self] name in
            Task { @MainActor in self?.found.removeAll { $0.name == name } }
        }
    }

    var url: URL? {
        guard !host.isEmpty else { return nil }
        return URL(string: "http://\(host):\(port)/")
    }

    func start() {
        guard !started else { return }
        started = true
        finder.start()

        if !host.isEmpty { connect(host, port) }

        // 直连旧地址失败后再扫网段，避免两条连接同时开跑（安卓注释里的那个坑）
        Task { [weak self] in
            try? await Task.sleep(nanoseconds: 2_500_000_000)
            guard let self, !self.phase.isReady else { return }
            await self.scan()
        }

        // 掉线自愈：每 5 秒试一次上次地址，每 15 秒全量扫一次网段
        maintenance = Task { [weak self] in
            while !Task.isCancelled {
                try? await Task.sleep(nanoseconds: 5_000_000_000)
                guard let self else { return }
                await self.maintain()
            }
        }
    }

    func connect(_ h: String, _ p: Int = 18765) {
        guard !h.isEmpty else { return }
        phase = .connecting("正在连接 \(h):\(p)…")
        Task { await probe(h, p) }
    }

    /// 手动连接，允许直接粘 `http://192.168.1.23:18765/` 这种整串。
    func apply(address raw: String, port rawPort: String) {
        var s = raw.trimmingCharacters(in: .whitespacesAndNewlines)
        if let r = s.range(of: "://") { s = String(s[r.upperBound...]) }
        s = String(s.split(separator: "/").first ?? Substring(s))
        var p = Int(rawPort.trimmingCharacters(in: .whitespacesAndNewlines)) ?? 18765
        if let colon = s.lastIndex(of: ":"),
           let n = Int(String(s[s.index(after: colon)...])),
           n > 0, n < 65536 {
            p = n
            s = String(s[..<colon])
        }
        guard !s.isEmpty else { return }
        connect(s, p)
    }

    func rescan() {
        phase = .idle
        found = []
        scanned = []
        finder.stop()
        finder.start()
        Task { await scan() }
    }

    func scan() async {
        guard !scanning else { return }
        scanning = true
        let hits = await LanScanner.scan(port: 18765)
        scanning = false
        scanned = hits
        if !phase.isReady, let first = hits.first {
            connect(first, 18765)
        }
    }

    func disconnect() {
        phase = .idle
        reloadID += 1
        found = []
        finder.stop()
        finder.start()
        Task { await scan() }
    }

    func checkConnection() {
        guard phase.isReady, !host.isEmpty else { return }
        Task { await probe(host, port) }
    }

    func pageFailed(_ message: String) {
        phase = .failed("连接中断 · \(message)")
        reloadID += 1
        finder.start()
    }

    private func probe(_ h: String, _ p: Int) async {
        guard let u = URL(string: "http://\(h):\(p)/") else {
            phase = .failed("地址无效：\(h):\(p)")
            return
        }
        var req = URLRequest(url: u,
                             cachePolicy: .reloadIgnoringLocalAndRemoteCacheData,
                             timeoutInterval: 1.5)
        req.httpMethod = "GET"
        do {
            let (_, response) = try await URLSession.shared.data(for: req)
            let code = (response as? HTTPURLResponse)?.statusCode ?? -1
            if (200..<400).contains(code) {
                let wasReady = phase.isReady
                host = h
                port = p
                UserDefaults.standard.set(h, forKey: kHost)
                UserDefaults.standard.set(p, forKey: kPort)
                phase = .ready
                finder.stop()
                if !wasReady { reloadID += 1 }
            } else {
                phase = .failed("车机返回 HTTP \(code)")
            }
        } catch {
            phase = .failed("连不上 \(h):\(p) · \((error as NSError).localizedDescription)")
        }
    }

    private func maintain() async {
        guard !phase.isReady else { return }
        tick += 1
        if !host.isEmpty {
            await probe(host, port)
            if phase.isReady { return }
        }
        if tick % 3 == 0 { await scan() }
    }
}

struct RootView: View {
    @EnvironmentObject private var link: CarLink
    @State private var showPanel = false

    var body: some View {
        ZStack(alignment: .topTrailing) {
            Color(red: 0.043, green: 0.043, blue: 0.051).ignoresSafeArea()

            if link.phase.isReady {
                if let url = link.url {
                    RemoteWebView(url: url, reloadID: link.reloadID) { message in
                        link.pageFailed(message)
                    }
                    .ignoresSafeArea()
                }
                gearButton.opacity(0.5)
            } else {
                NavigationView {
                    ConnectPanel(embedded: true)
                }
            }
        }
        .sheet(isPresented: $showPanel) {
            NavigationView {
                ConnectPanel(embedded: false)
            }
        }
        .onAppear { link.start() }
        .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
            if link.phase.isReady { link.checkConnection() } else { link.rescan() }
        }
    }

    private var gearButton: some View {
        Button {
            showPanel = true
        } label: {
            Image(systemName: "gearshape.fill")
                .font(.system(size: 15, weight: .semibold))
                .foregroundStyle(.white.opacity(0.8))
                .padding(10)
                .background(.black.opacity(0.35), in: Circle())
        }
        .padding(.top, 12)
        .padding(.trailing, 14)
    }
}
