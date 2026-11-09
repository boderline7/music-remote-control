import SwiftUI
import UIKit
import AVFoundation
import MediaPlayer
import Combine

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
        // 带上客户端标识，车机侧能看到是 iOS / 哪个版本连进来的；车机端 HTTP 会忽略 query。
        let cv = (Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as? String) ?? "1.2.1"
        return URL(string: "http://\(host):\(port)/?client=ios&cv=\(cv)")
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
    @StateObject private var volumeKeys = VolumeKeyWatcher()

    var body: some View {
        ZStack {
            Color(red: 0.043, green: 0.043, blue: 0.051).ignoresSafeArea()

            if link.phase.isReady {
                if let url = link.url {
                    RemoteWebView(url: url, reloadID: link.reloadID) { message in
                        link.pageFailed(message)
                    }
                    .ignoresSafeArea()
                }
            } else {
                NavigationView {
                    ConnectPanel(embedded: true)
                }
            }
        }
        .onAppear {
            link.start()
            volumeKeys.start()
        }
        .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
            if link.phase.isReady { link.checkConnection() } else { link.rescan() }
        }
    }
}

/// iOS 不开放硬件音量键事件，WKWebView 更收不到。
/// 双通道检测，任一条生效都能用：
///   1) AVAudioSession.outputVolume 的 KVO（主通道）；
///   2) MPVolumeView 内部 UISlider 的 valueChanged（部分 iOS 版本 KVO 不派发，滑杆仍会动）。
/// 检测到按键后把系统音量拉回 0.5 锚点：既能持续识别下一次按键，又能吃掉系统音量 HUD。
/// 两条通道共用 120ms 去重；回锚点产生的「回声」直接按数值忽略，不占用冷却。
final class VolumeKeyWatcher: NSObject, ObservableObject {
    private let session = AVAudioSession.sharedInstance()
    private var observation: NSKeyValueObservation?
    private let volumeView = MPVolumeView(frame: CGRect(x: 1, y: 1, width: 1, height: 1))
    private let anchor: Float = 0.5
    private var lastHandled = Date.distantPast
    private var started = false
    private var sliderHooked = false
    private var baseline: Float = 0.5

    func start() {
        guard !started else { return }
        started = true
        activateSession()
        attachVolumeView()

        observation = session.observe(\.outputVolume, options: [.old, .new]) { [weak self] session, change in
            let new = session.outputVolume
            let old = change.oldValue
            // KVO 回调线程不固定，统一切回主线程再做去重与发命令
            DispatchQueue.main.async { [weak self] in
                guard let self, abs(new - self.anchor) > 0.02 else { return }
                self.handle(newValue: new, oldValue: old)
            }
        }
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(appDidBecomeActive),
            name: UIApplication.didBecomeActiveNotification,
            object: nil)
    }

    @objc private func appDidBecomeActive() {
        // 后台切回时系统可能已经把音频会话挂起，重新激活并把音量拉回锚点
        activateSession()
        attachVolumeView()
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) { [weak self] in
            self?.resetSystemVolume()
        }
    }

    private func activateSession() {
        do {
            try session.setCategory(.playback, mode: .default, options: [.mixWithOthers])
            try session.setActive(true, options: [])
        } catch {
            print("volume key watcher: audio session unavailable: \(error)")
        }
    }

    /// MPVolumeView 必须挂在 window 上才会创建 UISlider。冷启动时 keyWindow 可能还没就绪，
    /// 所以失败就退避重试（约 2.4 秒）。alpha 不能设成 0，否则系统不渲染、内部滑杆可能不创建。
    private func attachVolumeView(attempt: Int = 0) {
        DispatchQueue.main.async { [weak self] in
            guard let self else { return }
            guard let window = UIApplication.shared.connectedScenes
                .compactMap({ $0 as? UIWindowScene })
                .flatMap({ $0.windows })
                .first(where: { $0.isKeyWindow }) else {
                if attempt < 8 {
                    DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) { [weak self] in
                        self?.attachVolumeView(attempt: attempt + 1)
                    }
                }
                return
            }
            self.volumeView.alpha = 0.01
            self.volumeView.showsRouteButton = false
            if self.volumeView.superview !== window {
                self.volumeView.removeFromSuperview()
                window.addSubview(self.volumeView)
            }
            self.volumeView.frame = CGRect(x: 1, y: 1, width: 1, height: 1)
            self.volumeView.layoutIfNeeded()
            self.hookSlider()
            self.resetSystemVolume()
        }
    }

    /// 不同 iOS 版本 MPVolumeView 的层级深浅不一样，递归找 UISlider 而不是只看一层 subviews。
    private func findSlider(in view: UIView) -> UISlider? {
        for sub in view.subviews {
            if let slider = sub as? UISlider { return slider }
            if let slider = findSlider(in: sub) { return slider }
        }
        return nil
    }

    private func hookSlider() {
        guard !sliderHooked, let slider = findSlider(in: volumeView) else { return }
        sliderHooked = true
        slider.addTarget(self, action: #selector(sliderChanged(_:)), for: .valueChanged)
    }

    @objc private func sliderChanged(_ slider: UISlider) {
        let value = slider.value
        guard abs(value - anchor) > 0.02 else { return }
        handle(newValue: value, oldValue: baseline)
    }

    /// 两条通道汇到这里：判断加/减 → 立刻回锚点 → 把命令交给网页。
    private func handle(newValue new: Float, oldValue old: Float?) {
        let now = Date()
        guard now.timeIntervalSince(lastHandled) > 0.12 else { return }
        let base = old ?? anchor
        guard abs(new - base) > 0.004 else { return }
        let dir = new > base ? 1 : -1
        lastHandled = now
        // 记下这次的实际音量：万一回锚点没生效，下一条通道仍能用它判断方向
        baseline = new
        resetSystemVolume()
        WebViewBridge.stepVolume(dir)
    }

    /// 把系统音量拉回锚点。slider 是异步创建的，找不到就短退避重试。
    /// 即使这个回拉在某些 iOS 版本上失效，滑杆的 valueChanged 通道仍能继续识别按键。
    private func resetSystemVolume(attempt: Int = 0) {
        DispatchQueue.main.async { [weak self] in
            guard let self else { return }
            self.hookSlider()
            guard let slider = self.findSlider(in: self.volumeView) else {
                if attempt < 8 {
                    DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) { [weak self] in
                        self?.resetSystemVolume(attempt: attempt + 1)
                    }
                }
                return
            }
            slider.setValue(self.anchor, animated: false)
            self.baseline = self.anchor
        }
    }

    deinit {
        observation?.invalidate()
        NotificationCenter.default.removeObserver(self)
    }
}
