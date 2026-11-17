import SwiftUI

/// 连接页只留「上次连接的 IP」一条 + 手动输入，文字精简。
/// 发现/扫描仍在后台跑，只用于自动直连，不再铺成一长串候选。
/// embedded=true 时作为主界面（未连接），false 时作为已连接后齿轮打开的设置面板。
struct ConnectPanel: View {
    @EnvironmentObject private var link: CarLink
    @Environment(\.dismiss) private var dismiss

    let embedded: Bool

    @State private var address = ""
    @State private var portText = ""

    var body: some View {
        Form {
            Section {
                HStack(spacing: 8) {
                    Circle().fill(statusColor).frame(width: 8, height: 8)
                    Text(link.phase.label).font(.footnote)
                }
                if !link.host.isEmpty {
                    Button {
                        link.connect(link.host, link.port)
                        closeIfSheet()
                    } label: {
                        Label("连接上次车机 \(link.host)", systemImage: "bolt.fill")
                    }
                }
            } header: {
                Text("车机遥控")
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
}
