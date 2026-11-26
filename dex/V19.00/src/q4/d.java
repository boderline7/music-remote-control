package q4;

import android.os.Build;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8460a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ h f8461b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f8462c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f8463d;

    public /* synthetic */ d(h hVar, String str, String str2, int i7) {
        this.f8460a = i7;
        this.f8461b = hVar;
        this.f8462c = str;
        this.f8463d = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        g gVar;
        int i7 = this.f8460a;
        String str = this.f8463d;
        String str2 = this.f8462c;
        h hVar = this.f8461b;
        switch (i7) {
            case 0:
                g gVar2 = hVar.f8481q;
                if (gVar2 != null) {
                    gVar2.onDeviceFound(str2, str);
                    return;
                }
                return;
            case 1:
                g gVar3 = hVar.f8481q;
                if (gVar3 != null) {
                    gVar3.onDeviceAlive(str2, str);
                    return;
                }
                return;
            case 2:
                hVar.getClass();
                Iterator it = ("hud".equals(str2) ? hVar.f8470f : hVar.f8469e).iterator();
                while (it.hasNext()) {
                    ((f) it.next()).onDisconnected(str);
                }
                return;
            case 3:
                hVar.getClass();
                try {
                    Socket d7 = h.d(str2);
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(d7.getOutputStream()));
                        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(d7.getInputStream()));
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("cmd", "hud".equals(str) ? "hud_connect" : "connect");
                        if ("hud".equals(str)) {
                            jsonObject.addProperty("service", "hud");
                        }
                        jsonObject.addProperty("name", Build.MODEL);
                        dataOutputStream.writeUTF(hVar.f8468d.toJson((JsonElement) jsonObject));
                        dataOutputStream.flush();
                        JsonObject asJsonObject = JsonParser.parseString(dataInputStream.readUTF()).getAsJsonObject();
                        if (!("hud".equals(str) ? "hud_accept" : "accept").equals(asJsonObject.get("cmd").getAsString())) {
                            throw new IOException("Unexpected handshake response");
                        }
                        String asString = asJsonObject.get("name").getAsString();
                        if ("hud".equals(str)) {
                            hVar.f8485u = str2;
                        } else {
                            hVar.f8482r = str2;
                            hVar.f8466b.post(new d(hVar, str2, asString, 4));
                        }
                        d7.close();
                        return;
                    } finally {
                    }
                } catch (Exception unused) {
                    hVar.i(str2, str);
                    return;
                }
            case 4:
                g gVar4 = hVar.f8481q;
                if (gVar4 != null) {
                    gVar4.onConnected(str2, str);
                    return;
                }
                return;
            default:
                hVar.getClass();
                CopyOnWriteArraySet copyOnWriteArraySet = "hud".equals(str2) ? hVar.f8470f : hVar.f8469e;
                if ("general".equals(str2) && (gVar = hVar.f8481q) != null) {
                    gVar.onMessageReceived(str);
                }
                Iterator it2 = copyOnWriteArraySet.iterator();
                while (it2.hasNext()) {
                    ((f) it2.next()).onMessageReceived(str);
                }
                return;
        }
    }
}
