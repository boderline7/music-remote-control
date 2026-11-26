package o4;

import android.content.Context;
import android.util.Base64;
import androidx.appcompat.app.p0;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.service.ModuleCrypto;
import com.navi.tool.tire.api.TireBrand;
import com.navi.tool.tire.api.TireEngine;
import com.navi.tool.utils.NativeBridge;
import dalvik.system.InMemoryDexClassLoader;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u {

    /* renamed from: e, reason: collision with root package name */
    public static final u f8031e = new u();

    /* renamed from: a, reason: collision with root package name */
    public final ExecutorService f8032a = Executors.newSingleThreadExecutor();

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f8033b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public volatile TireEngine f8034c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f8035d;

    public static byte[] a(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        byte[] decryptDex = ModuleCrypto.decryptDex(Base64.decode(str, 0), Base64.decode(str2, 0), Base64.decode(str3, 0), Base64.decode(str4, 0));
        if (jSONObject.optInt("size", -1) != decryptDex.length) {
            throw new Exception("模块大小不匹配");
        }
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(decryptDex);
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b7 : digest) {
            sb.append(String.format(Locale.US, "%02x", Integer.valueOf(b7 & 255)));
        }
        if (sb.toString().equalsIgnoreCase(jSONObject.optString("sha256"))) {
            return decryptDex;
        }
        throw new Exception("模块 hash 不匹配");
    }

    public static TireEngine b(Context context) {
        String systemVin = NativeBridge.getSystemVin();
        String trim = systemVin == null ? h0.FRAGMENT_ENCODE_SET : systemVin.trim();
        if (trim.isEmpty()) {
            throw new Exception("缺少 VIN");
        }
        byte[] fetchLicensedModule = NativeBridge.fetchLicensedModule(context, 4, ModuleCrypto.getPublicKeyBase64());
        if (fetchLicensedModule == null || fetchLicensedModule.length <= 32) {
            throw new Exception("模块下载失败");
        }
        String str = new String(fetchLicensedModule, 0, 32, "UTF-8");
        try {
            JSONObject jSONObject = new JSONObject(new String(fetchLicensedModule, 32, fetchLicensedModule.length - 32, "UTF-8"));
            if (!jSONObject.optBoolean("ok", false)) {
                throw new Exception(jSONObject.optString(ConfigBackupService.STAGE_ERROR, "模块下载被拒绝"));
            }
            String string = jSONObject.getString("body");
            JSONObject h7 = h(string, trim, str);
            TireEngine f7 = f(a(h7, jSONObject.getString("key"), jSONObject.getString("iv"), jSONObject.getString("tag"), jSONObject.getString("data")));
            if (!e(f7)) {
                throw new Exception("模块版本过旧，请更新服务端模块");
            }
            context.getSharedPreferences("tire_module", 0).edit().putString("body", string).putString("nonce", str).putString("uid", trim).putString("wrapped_key", jSONObject.getString("key")).putString("iv", jSONObject.getString("iv")).putString("tag", jSONObject.getString("tag")).putString("data", jSONObject.getString("data")).putLong("fetched_at", System.currentTimeMillis()).apply();
            h7.optString("moduleVersion");
            return f7;
        } catch (Exception unused) {
            throw new Exception("模块下载响应异常");
        }
    }

    public static boolean e(TireEngine tireEngine) {
        try {
            return !Modifier.isAbstract(tireEngine.getClass().getMethod("fetchCaptcha", TireBrand.class, String.class).getModifiers());
        } catch (Throwable unused) {
            return false;
        }
    }

    public static TireEngine f(byte[] bArr) {
        return (TireEngine) new InMemoryDexClassLoader(ByteBuffer.wrap(bArr), TireEngine.class.getClassLoader()).loadClass("com.navi.tool.tire.engine.TireEngineImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a7, code lost:
    
        if (r3.f2487b <= 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b5, code lost:
    
        if ((java.lang.System.currentTimeMillis() - r3.f2487b) < 21600000) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c0, code lost:
    
        if (e((com.navi.tool.tire.api.TireEngine) r3.f2488c) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c6, code lost:
    
        return (com.navi.tool.tire.api.TireEngine) r3.f2488c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.navi.tool.tire.api.TireEngine g(android.content.Context r14) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            r3 = 0
            java.lang.String r4 = "tire_module"
            r5 = 0
            android.content.SharedPreferences r4 = r14.getSharedPreferences(r4, r5)     // Catch: java.lang.Exception -> L9d
            java.lang.String r5 = "body"
            java.lang.String r5 = r4.getString(r5, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r6 = "nonce"
            java.lang.String r6 = r4.getString(r6, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r7 = "uid"
            java.lang.String r7 = r4.getString(r7, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r8 = "wrapped_key"
            java.lang.String r8 = r4.getString(r8, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r9 = "iv"
            java.lang.String r9 = r4.getString(r9, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r10 = "tag"
            java.lang.String r10 = r4.getString(r10, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r11 = "data"
            java.lang.String r11 = r4.getString(r11, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r12 = com.navi.tool.utils.NativeBridge.getSystemVin()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto L3d
            goto L41
        L3d:
            java.lang.String r0 = r12.trim()     // Catch: java.lang.Exception -> L9d
        L41:
            boolean r12 = r5.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r6.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r7.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r8.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r9.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r10.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r12 = r11.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r12 != 0) goto La1
            boolean r0 = r7.equals(r0)     // Catch: java.lang.Exception -> L9d
            if (r0 != 0) goto L72
            goto La1
        L72:
            org.json.JSONObject r0 = h(r5, r7, r6)     // Catch: java.lang.Exception -> L9d
            java.lang.String r5 = "expiresAt"
            long r5 = r0.optLong(r5, r1)     // Catch: java.lang.Exception -> L9d
            r12 = 1000(0x3e8, double:4.94E-321)
            long r5 = r5 * r12
            long r12 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L9d
            int r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r5 > 0) goto L88
            goto La1
        L88:
            byte[] r0 = a(r0, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L9d
            androidx.recyclerview.widget.i r5 = new androidx.recyclerview.widget.i     // Catch: java.lang.Exception -> L9d
            com.navi.tool.tire.api.TireEngine r0 = f(r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r6 = "fetched_at"
            long r6 = r4.getLong(r6, r1)     // Catch: java.lang.Exception -> L9d
            r5.<init>(r0, r6)     // Catch: java.lang.Exception -> L9d
            r3 = r5
            goto La1
        L9d:
            r0 = move-exception
            r0.getMessage()
        La1:
            if (r3 == 0) goto Lc7
            long r4 = r3.f2487b
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 <= 0) goto Lc7
            long r0 = java.lang.System.currentTimeMillis()
            long r4 = r3.f2487b
            long r0 = r0 - r4
            r4 = 21600000(0x1499700, double:1.0671818E-316)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 < 0) goto Lb8
            goto Lc7
        Lb8:
            java.lang.Object r0 = r3.f2488c
            com.navi.tool.tire.api.TireEngine r0 = (com.navi.tool.tire.api.TireEngine) r0
            boolean r0 = e(r0)
            if (r0 == 0) goto Lc7
            java.lang.Object r14 = r3.f2488c
            com.navi.tool.tire.api.TireEngine r14 = (com.navi.tool.tire.api.TireEngine) r14
            return r14
        Lc7:
            com.navi.tool.tire.api.TireEngine r14 = b(r14)     // Catch: java.lang.Exception -> Lcc
            return r14
        Lcc:
            r14 = move-exception
            if (r3 == 0) goto Le1
            java.lang.Object r0 = r3.f2488c
            com.navi.tool.tire.api.TireEngine r0 = (com.navi.tool.tire.api.TireEngine) r0
            boolean r0 = e(r0)
            if (r0 == 0) goto Le1
            r14.getMessage()
            java.lang.Object r14 = r3.f2488c
            com.navi.tool.tire.api.TireEngine r14 = (com.navi.tool.tire.api.TireEngine) r14
            return r14
        Le1:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: o4.u.g(android.content.Context):com.navi.tool.tire.api.TireEngine");
    }

    public static JSONObject h(String str, String str2, String str3) {
        String verifySignedLicense = NativeBridge.verifySignedLicense(str, str2, str3);
        if (verifySignedLicense == null || !verifySignedLicense.startsWith("OK|1|0|")) {
            throw new Exception(a.a.B("模块签名或授权校验失败: ", verifySignedLicense));
        }
        int lastIndexOf = str.lastIndexOf(124);
        if (lastIndexOf <= 0) {
            throw new Exception("模块报文格式错误");
        }
        JSONObject jSONObject = new JSONObject(str.substring(0, lastIndexOf));
        if (!"tire-engine".equals(jSONObject.optString("module"))) {
            throw new Exception("模块类型不匹配");
        }
        if (jSONObject.optInt("hostApi", 0) > 1) {
            throw new Exception("宿主版本过旧，请先升级车机助手");
        }
        if ("aes-256-gcm".equals(jSONObject.optString("enc"))) {
            return jSONObject;
        }
        throw new Exception("模块加密格式不支持");
    }

    public final void c(Context context, t tVar) {
        TireEngine tireEngine = this.f8034c;
        if (tireEngine != null && e(tireEngine)) {
            tVar.onReady(tireEngine);
            return;
        }
        if (tireEngine != null) {
            this.f8034c = null;
        }
        synchronized (this.f8033b) {
            try {
                this.f8033b.add(tVar);
                if (this.f8035d) {
                    return;
                }
                this.f8035d = true;
                this.f8032a.execute(new p0(16, this, context.getApplicationContext()));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d(String str, TireEngine tireEngine) {
        ArrayList arrayList;
        synchronized (this.f8033b) {
            arrayList = new ArrayList(this.f8033b);
            this.f8033b.clear();
            this.f8035d = false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            t tVar = (t) it.next();
            if (tireEngine != null) {
                tVar.onReady(tireEngine);
            } else {
                tVar.onError(str == null ? "未知错误" : str);
            }
        }
    }
}
