import SwiftUI

struct SetupView: View {
    @EnvironmentObject private var link: CarLink
    @Environment(\.dismiss) private var dismiss
    @State private var address = ""
    @State private var portText = ""

    private var defaultHost: String {
        let v = Bundle.main.object(forInfoDictionaryKey: "CarRemoteDefaultHost") as? String
        return (v?.isEmpty == false && v?.hasPrefix("$(") == false) ? v! : "192.168.43.1"
    }

    var body: some View {
        NavigationView {
            Form {
                Section("车机地址") {
                    TextField("例如 192.168.43.1", text: $address)
                        .keyboardType(.URL)
                        .textInputAutocapitalization(.never)
                        .disableAutocorrection(true)
                    TextField("端口", text: $portText)
                        .keyboardType(.numberPad)
                    Text("当前：http://\(link.address)/")
                        .font(.caption)
                        .foregroundStyle(.secondary)
                }

                Section {
                    Button("保存并连接") {
                        link.apply(address: address, port: portText)
                        dismiss()
                    }
                    .disabled(address.trimmingCharacters(in: .whitespaces).isEmpty)

                    Button("恢复出厂默认地址") {
                        address = defaultHost
                        portText = "18765"
                    }
                    .font(.footnote)
                }

                Section("说明") {
                    Text("""
                    直接填车机的固定 IP。本 App 不走 carremote.local 域名解析，也不做 mDNS / Bonjour 发现 —— 网页端时好时坏的就是那条链路，IP 直连才是稳的。

                    车机端「设置 → 手机遥控」页能看到当前 IP。首次使用 iOS 会问「允许访问本地网络」，请点允许。

                    连接是车内局域网的明文 HTTP，不要把 18765 映射到公网。
                    """)
                    .font(.footnote)
                    .foregroundStyle(.secondary)
                }
            }
            .navigationTitle("连接设置")
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("关闭") { dismiss() }
                }
            }
            .onAppear {
                address = link.host
                portText = String(link.port)
            }
        }
    }
}
