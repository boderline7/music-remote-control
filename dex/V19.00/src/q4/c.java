package q4;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8456a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f8457b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f8458c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ File f8459d;

    public /* synthetic */ c(h hVar, String str, File file, int i7) {
        this.f8456a = i7;
        this.f8457b = hVar;
        this.f8458c = str;
        this.f8459d = file;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f8456a;
        File file = this.f8459d;
        String str = this.f8458c;
        h hVar = this.f8457b;
        switch (i7) {
            case 0:
                hVar.getClass();
                try {
                    Socket d7 = h.d(str);
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(d7.getOutputStream()));
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            try {
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("cmd", "send_file");
                                jsonObject.addProperty("service", "general");
                                jsonObject.addProperty("fileName", file.getName());
                                jsonObject.addProperty("fileSize", Long.valueOf(file.length()));
                                dataOutputStream.writeUTF(hVar.f8468d.toJson((JsonElement) jsonObject));
                                dataOutputStream.flush();
                                byte[] bArr = new byte[8192];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        dataOutputStream.flush();
                                        fileInputStream.close();
                                        dataOutputStream.close();
                                        d7.close();
                                        return;
                                    }
                                    dataOutputStream.write(bArr, 0, read);
                                }
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } catch (Exception e7) {
                    hVar.i(str, "general");
                    hVar.j("Send file error: " + e7.getMessage());
                    return;
                }
            default:
                g gVar = hVar.f8481q;
                if (gVar != null) {
                    gVar.onFileReceived(str, file);
                    return;
                }
                return;
        }
    }
}
