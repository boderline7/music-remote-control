import Foundation
import Darwin

/// 与安卓端 Discovery.kt 对齐：搜索 `_cloudmusic-car._tcp` 的 mDNS 服务。
///
/// 关键一点：NetService.hostName 往往返回 `xxx.local`，那就又回到域名解析了。
/// 这里从 addresses 里直接取纯 IPv4，拿到手就是 IP，和安卓 NsdServiceInfo.host 一致。
final class BonjourFinder: NSObject, NetServiceBrowserDelegate, NetServiceDelegate {

    struct Endpoint: Identifiable, Hashable {
        var id: String { "\(host):\(port)" }
        let name: String
        let host: String
        let port: Int
    }

    var onFound: ((Endpoint) -> Void)?
    var onLost: ((String) -> Void)?

    private let browser = NetServiceBrowser()
    private var resolving: [NetService] = []
    private var running = false

    func start() {
        guard !running else { return }
        running = true
        browser.delegate = self
        browser.searchForServices(ofType: "_cloudmusic-car._tcp", inDomain: "local.")
    }

    func stop() {
        guard running else { return }
        running = false
        browser.stop()
        resolving.removeAll()
    }

    func netServiceBrowser(_ browser: NetServiceBrowser,
                           didFind service: NetService,
                           moreComing: Bool) {
        service.delegate = self
        resolving.append(service)
        service.resolve(withTimeout: 5)
    }

    func netServiceBrowser(_ browser: NetServiceBrowser,
                           didRemove service: NetService,
                           moreComing: Bool) {
        let name = service.name
        DispatchQueue.main.async { [weak self] in self?.onLost?(name) }
    }

    func netServiceDidResolveAddress(_ sender: NetService) {
        guard let host = Self.ipv4(from: sender.addresses), !host.isEmpty else { return }
        let endpoint = Endpoint(name: sender.name, host: host, port: sender.port)
        resolving.removeAll { $0 === sender }
        DispatchQueue.main.async { [weak self] in self?.onFound?(endpoint) }
    }

    func netService(_ sender: NetService, didNotResolve errorDict: [String : NSNumber]) {
        resolving.removeAll { $0 === sender }
    }

    /// Darwin 的 sockaddr_in 布局：0=sa_len，1=sa_family(AF_INET=2)，2..3=port，4..7=IPv4。
    /// 按字节读，避开 UnsafePointer 绑定和对齐问题。
    private static func ipv4(from addresses: [Data]?) -> String? {
        for data in addresses ?? [] {
            let b = [UInt8](data)
            guard b.count >= 8, b[1] == UInt8(AF_INET) else { continue }
            return "\(b[4]).\(b[5]).\(b[6]).\(b[7])"
        }
        return nil
    }
}

/// 与安卓端 Scanner.kt 对齐：枚举本机所有 site-local IPv4 的 /24，逐段探 18765。
/// 车机开热点时 IP 每次都可能变，网段扫描是「IP 变了也能自己找回来」的兜底。
enum LanScanner {
    static let defaultPort = 18765

    static func subnets() -> [String] {
        var head: UnsafeMutablePointer<ifaddrs>?
        guard getifaddrs(&head) == 0, let first = head else { return [] }
        defer { freeifaddrs(head) }

        var result: [String] = []
        var cur: UnsafeMutablePointer<ifaddrs>? = first
        while let p = cur {
            defer { cur = p.pointee.ifa_next }
            guard let sa = p.pointee.ifa_addr else { continue }
            let bytes = UnsafeRawBufferPointer(start: UnsafeRawPointer(sa),
                                               count: MemoryLayout<sockaddr_in>.size)
            guard bytes.count >= 8, bytes[1] == UInt8(AF_INET) else { continue }
            let a = Int(bytes[4]), b = Int(bytes[5]), c = Int(bytes[6])
            // 只要私有网段，跳过 127 / 169.254 / 公网地址
            let privateRange = a == 10
                || (a == 172 && (16...31).contains(b))
                || (a == 192 && b == 168)
            guard privateRange else { continue }
            result.append("\(a).\(b).\(c)")
        }
        return Array(Set(result)).sorted()
    }

    static func scan(port: Int = defaultPort) async -> [String] {
        let prefixes = subnets()
        guard !prefixes.isEmpty else { return [] }
        var hits: [String] = []
        for prefix in prefixes {
            for start in stride(from: 1, through: 254, by: 64) {
                let end = min(start + 63, 254)
                let batch: [String] = await withTaskGroup(of: String?.self) { group in
                    for n in start...end {
                        let ip = "\(prefix).\(n)"
                        group.addTask {
                            let ok = await reachable(ip, port: port)
                            return ok ? ip : nil
                        }
                    }
                    var acc: [String] = []
                    for await value in group {
                        if let value { acc.append(value) }
                    }
                    return acc
                }
                hits.append(contentsOf: batch)
                // 和安卓一样：拿到第一批命中就够了，不必把整段扫完
                if !hits.isEmpty { return hits.sorted() }
            }
        }
        return []
    }

    /// 用一次 HTTP 探测代替纯 TCP connect：能确认对面确实是遥控服务，不是别的设备占着端口。
    private static func reachable(_ ip: String, port: Int) async -> Bool {
        guard let url = URL(string: "http://\(ip):\(port)/") else { return false }
        var req = URLRequest(url: url,
                             cachePolicy: .reloadIgnoringLocalAndRemoteCacheData,
                             timeoutInterval: 0.5)
        req.httpMethod = "GET"
        do {
            let (_, response) = try await URLSession.shared.data(for: req)
            return (response as? HTTPURLResponse)?.statusCode == 200
        } catch {
            return false
        }
    }
}
