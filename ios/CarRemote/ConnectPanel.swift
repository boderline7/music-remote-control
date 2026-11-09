import SwiftUI

/// 与安卓端 ConnectPanel 对齐：状态行 + 发现列表 + 扫描结果 + 重新搜索 + 手动连接。
/// embedded=true 时作为主界面（未连接），false 时作为已连接后齿轮打开的设置面板。
struct ConnectPanel: View {
    @EnvironmentObject private var link: CarLink
    @Environment(\.dismiss) private var dismiss

    let embedded: Bool

    @State private var address = ""
    @State private var portText = "18765"

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
                ForEach(link.scanned.filter { ip in
                    !link.found.contains(where: { $0.host == ip })
                }, id: \.self) { ip in
                    candidateRow(title: "扫描到车机", subtitle: "\(ip):18765") {
                        link.connect(ip, 18765)
                        closeIfSheet()
                    }
                }
                Button {
                    link.rescan()
                } label: {
                    Label(link.scanning ? "扫描中…" : "重新搜索 / 扫网段",
                          systemImage: "arrow.clockwise")
                }
                Text("车机 IP 变了也没关系：会自动扫当前网段寻找开着的 18765 端口。")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }

            Section("手动连接") {
                TextField("车机 IP", text: $address)
                    .keyboardType(.URL)
                    .textInputAutocapitalization(.never)
                    .disableAutocorrection(true)
                TextField("端口", text: $portText)
                    .keyboardType(.numberPad)
                Button("连接") {
                    link.apply(address: address, port: portText)
                    closeIfSheet()
                }
                .disabled(address.trimmingCharacters(in: .whitespaces).isEmpty)
            }

            Section("说明") {
                Text("""
                发现逻辑和安卓遥控 App 一致：搜 _cloudmusic-car._tcp 服务 + 扫本机 /24 网段的 18765 端口，拿到的是纯 IPv4，不走 carremote.local 域名解析。

                连过一次会记住地址，下次先直连；连不上就自动重新发现 / 扫网段。首次使用 iOS 会问「允许访问本地网络」，请点允许。

                连接是车内局域网的明文 HTTP，不要把 18765 映射到公网。
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
            portText = String(link.port)
        }
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
