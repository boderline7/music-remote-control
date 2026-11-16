import SwiftUI

/// 与安卓端 ConnectPanel 对齐：状态行 + 已保存地址 + 发现列表 + 扫描结果 + 手动连接。
/// embedded=true 时作为主界面（未连接），false 时作为已连接后齿轮打开的设置面板。
struct ConnectPanel: View {
    @EnvironmentObject private var link: CarLink
    @Environment(\.dismiss) private var dismiss

    let embedded: Bool

    @State private var address = ""
    @State private var portText = ""

    var body: some View {
        Form {
            Section("连接状态") {
                HStack(spacing: 8) {
                    Circle().fill(statusColor).frame(width: 8, height: 8)
                    Text(link.phase.label).font(.footnote)
                }
                if link.phase.isReady {
                    Text("当前：http://\(link.host):\(link.port)/")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                    Button("断开并重新搜索") { link.disconnect(); closeIfSheet() }
                }
            }

            Section("已保存") {
                if link.saved.isEmpty {
                    Text("还没有。手动连过一次就会留在这里，换网络也不用重输。")
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                }
                ForEach(link.saved, id: \.self) { key in
                    let a = split(key)
                    candidateRow(title: a.0, subtitle: "\(a.1) · \(role(a.1))") {
                        link.connect(a.0, a.1)
                        closeIfSheet()
                    }
                }
                .onDelete { idx in
                    // 先取出 key 再删，边删边按索引取会错位
                    let keys = idx.compactMap { i -> String? in
                        i < link.saved.count ? link.saved[i] : nil
                    }
                    keys.forEach { link.removeSaved($0) }
                }
            }

            Section("发现的车机") {
                if link.found.isEmpty && link.scanned.isEmpty {
                    Text(link.scanning ? "正在扫描本网段…" : "正在搜索同一 Wi-Fi 下的车机…")
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                }
                ForEach(link.found) { endpoint in
                    candidateRow(title: endpoint.name.isEmpty ? "车机" : endpoint.name,
                                 subtitle: "\(endpoint.host):\(endpoint.port)") {
                        link.connect(endpoint.host, endpoint.port)
                        closeIfSheet()
                    }
                }
                ForEach(link.scanned.filter { hit in
                    !link.found.contains(where: { $0.host == hit.host && $0.port == hit.port })
                }) { hit in
                    candidateRow(title: "扫描到", subtitle: "\(hit.host):\(hit.port) · \(role(hit.port))") {
                        link.connect(hit.host, hit.port)
                        closeIfSheet()
                    }
                }
                Button {
                    link.rescan()
                } label: {
                    Label(link.scanning ? "扫描中…" : "重新搜索 / 扫网段",
                          systemImage: "arrow.clockwise")
                }
                Text("车机 IP 变了也没关系：会自动扫当前网段，18766（车控）和 18765（音乐）一起探。")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }

            Section("手动连接") {
                TextField("车机 IP，可带端口", text: $address)
                    .keyboardType(.URL)
                    .textInputAutocapitalization(.never)
                    .disableAutocorrection(true)
                TextField("端口（留空则两个都试）", text: $portText)
                    .keyboardType(.numberPad)
                Button("连接") {
                    link.apply(address: address, port: portText)
                    closeIfSheet()
                }
                .disabled(address.trimmingCharacters(in: .whitespaces).isEmpty)
            }

            Section("说明") {
                Text("""
                自动探测两个端口：18766 打开车控页，18765 打开音乐遥控。哪个开着就进哪个，扫本机 /24 网段拿到的是纯 IPv4，不走域名解析。

                连过一次会记住地址，下次先直连；连不上就自动重新发现 / 扫网段。手动加的地址一直留着（左滑删除）。首次使用 iOS 会问「允许访问本地网络」，请点允许。

                连接是车内局域网的明文 HTTP，不要把这些端口映射到公网。
                """)
                .font(.footnote)
                .foregroundStyle(.secondary)
            }
        }
        .navigationTitle("车机遥控")
        .toolbar {
            ToolbarItem(placement: .cancellationAction) {
                if !embedded {
                    Button("关闭") { dismiss() }
                }
            }
        }
        .onAppear {
            address = link.host
            portText = ""
        }
    }

    // MARK: - 小工具

    private func split(_ key: String) -> (String, Int) {
        guard let i = key.lastIndex(of: ":") else { return (key, 18765) }
        let host = String(key[..<i])
        let port = Int(key[key.index(after: i)...]) ?? 18765
        return (host, port)
    }

    private func role(_ port: Int) -> String {
        if port == 18766 { return "车控" }
        if port == 18765 { return "音乐" }
        return "端口 \(port)"
    }

    private var statusColor: Color {
        switch link.phase {
        case .ready: return .green
        case .connecting: return .orange
        case .failed: return .red
        case .idle: return .gray
        }
    }

    private func closeIfSheet() {
        if !embedded { dismiss() }
    }

    private func candidateRow(title: String, subtitle: String,
                              action: @escaping () -> Void) -> some View {
        HStack {
            VStack(alignment: .leading, spacing: 3) {
                Text(title)
                Text(subtitle).font(.caption).foregroundStyle(.secondary)
            }
            Spacer()
            Button("连接") { action() }.buttonStyle(.borderedProminent)
        }
    }
}
