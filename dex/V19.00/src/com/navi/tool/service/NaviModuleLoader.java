package com.navi.tool.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import com.navi.tool.navi.api.NaviEngine;
import com.navi.tool.utils.NativeBridge;
import dalvik.system.InMemoryDexClassLoader;
import java.io.File;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class NaviModuleLoader {
    private static final int HOST_API = 1;
    private static final String IMPL_CLASS = "com.navi.tool.navi.engine.NaviEngineImpl";
    private static final NaviModuleLoader INSTANCE = new NaviModuleLoader();
    private static final String KEY_BODY = "body";
    private static final String KEY_DATA = "data";
    private static final String KEY_FETCHED_AT = "fetched_at";
    private static final String KEY_IV = "iv";
    private static final String KEY_NONCE = "nonce";
    private static final String KEY_TAG = "tag";
    private static final String KEY_UID = "uid";
    private static final String KEY_WRAPPED = "wrapped_key";
    private static final String MODULE = "navi-engine";
    private static final int MODULE_ID = 3;
    private static final int NONCE_LEN = 32;
    private static final String PREFS = "navi_module";
    private static final long REFRESH_INTERVAL_MS = 21600000;
    private static final String TAG = "NaviModuleLoader";
    private volatile NaviEngine engine;
    private boolean loading;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler main = new Handler(Looper.getMainLooper());
    private final List<Callback> callbacks = new ArrayList();

    public static final class CachedEngine {
        final NaviEngine engine;
        final long fetchedAt;

        public CachedEngine(NaviEngine naviEngine, long j7) {
            this.engine = naviEngine;
            this.fetchedAt = j7;
        }

        public boolean isStale() {
            return this.fetchedAt <= 0 || System.currentTimeMillis() - this.fetchedAt >= NaviModuleLoader.REFRESH_INTERVAL_MS;
        }
    }

    public interface Callback {
        void onError(String str);

        void onReady(NaviEngine naviEngine);
    }

    private byte[] decrypt(JSONObject jSONObject, String str, String str2, String str3, String str4) {
        byte[] decryptDex = ModuleCrypto.decryptDex(Base64.decode(str, 0), Base64.decode(str2, 0), Base64.decode(str3, 0), Base64.decode(str4, 0));
        if (jSONObject.optInt("size", -1) != decryptDex.length) {
            throw new Exception("模块大小不匹配");
        }
        if (sha256(decryptDex).equalsIgnoreCase(jSONObject.optString("sha256"))) {
            return decryptDex;
        }
        throw new Exception("模块 hash 不匹配");
    }

    private static void deleteLegacyCache(Context context) {
        File dir = context.getDir("navi_engine", 0);
        File file = new File(dir, "navi-engine.jar");
        File file2 = new File(dir, "navi-engine.jar.tmp");
        if (file.exists()) {
            file.delete();
        }
        if (file2.exists()) {
            file2.delete();
        }
    }

    private NaviEngine downloadEngine(Context context) {
        String runtimeVin = runtimeVin();
        if (runtimeVin.isEmpty()) {
            throw new Exception("缺少 VIN");
        }
        byte[] fetchLicensedModule = NativeBridge.fetchLicensedModule(context, 3, ModuleCrypto.getPublicKeyBase64());
        if (fetchLicensedModule == null || fetchLicensedModule.length <= 32) {
            throw new Exception("模块下载失败");
        }
        String str = new String(fetchLicensedModule, 0, 32, "UTF-8");
        try {
            JSONObject jSONObject = new JSONObject(new String(fetchLicensedModule, 32, fetchLicensedModule.length - 32, "UTF-8"));
            if (!jSONObject.optBoolean("ok", false)) {
                throw new Exception(jSONObject.optString(ConfigBackupService.STAGE_ERROR, "模块下载被拒绝"));
            }
            String string = jSONObject.getString(KEY_BODY);
            JSONObject verifyBody = verifyBody(string, runtimeVin, str);
            String string2 = jSONObject.getString("key");
            String string3 = jSONObject.getString(KEY_IV);
            String string4 = jSONObject.getString(KEY_TAG);
            String string5 = jSONObject.getString(KEY_DATA);
            NaviEngine instantiate = instantiate(decrypt(verifyBody, string2, string3, string4, string5));
            context.getSharedPreferences(PREFS, 0).edit().putString(KEY_BODY, string).putString(KEY_NONCE, str).putString(KEY_UID, runtimeVin).putString(KEY_WRAPPED, string2).putString(KEY_IV, string3).putString(KEY_TAG, string4).putString(KEY_DATA, string5).putLong(KEY_FETCHED_AT, System.currentTimeMillis()).apply();
            deleteLegacyCache(context);
            verifyBody.optString("moduleVersion");
            return instantiate;
        } catch (JSONException unused) {
            throw new Exception("模块下载响应异常");
        }
    }

    private void finish(String str, NaviEngine naviEngine) {
        ArrayList arrayList;
        synchronized (this.callbacks) {
            arrayList = new ArrayList(this.callbacks);
            this.callbacks.clear();
            this.loading = false;
        }
        this.main.post(new y0.n(arrayList, naviEngine, str, 13));
    }

    public static NaviModuleLoader getInstance() {
        return INSTANCE;
    }

    private NaviEngine instantiate(byte[] bArr) {
        return (NaviEngine) new InMemoryDexClassLoader(ByteBuffer.wrap(bArr), NaviEngine.class.getClassLoader()).loadClass(IMPL_CLASS).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureEngine$1(Context context) {
        try {
            NaviEngine loadBestEngine = loadBestEngine(context);
            this.engine = loadBestEngine;
            finish(null, loadBestEngine);
        } catch (Exception e7) {
            finish(e7.getMessage() == null ? e7.toString() : e7.getMessage(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$finish$2(List list, NaviEngine naviEngine, String str) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Callback callback = (Callback) it.next();
            if (naviEngine != null) {
                callback.onReady(naviEngine);
            } else {
                callback.onError(str == null ? "未知错误" : str);
            }
        }
    }

    private NaviEngine loadBestEngine(Context context) {
        CachedEngine loadCachedEngine = loadCachedEngine(context);
        if (loadCachedEngine != null && !loadCachedEngine.isStale()) {
            return loadCachedEngine.engine;
        }
        try {
            return downloadEngine(context);
        } catch (Exception e7) {
            if (loadCachedEngine == null) {
                throw e7;
            }
            e7.getMessage();
            return loadCachedEngine.engine;
        }
    }

    private CachedEngine loadCachedEngine(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
            String string = sharedPreferences.getString(KEY_BODY, s5.h0.FRAGMENT_ENCODE_SET);
            String string2 = sharedPreferences.getString(KEY_NONCE, s5.h0.FRAGMENT_ENCODE_SET);
            String string3 = sharedPreferences.getString(KEY_UID, s5.h0.FRAGMENT_ENCODE_SET);
            String string4 = sharedPreferences.getString(KEY_WRAPPED, s5.h0.FRAGMENT_ENCODE_SET);
            String string5 = sharedPreferences.getString(KEY_IV, s5.h0.FRAGMENT_ENCODE_SET);
            String string6 = sharedPreferences.getString(KEY_TAG, s5.h0.FRAGMENT_ENCODE_SET);
            String string7 = sharedPreferences.getString(KEY_DATA, s5.h0.FRAGMENT_ENCODE_SET);
            String runtimeVin = runtimeVin();
            if (!string.isEmpty() && !string2.isEmpty() && !string3.isEmpty() && !string4.isEmpty() && !string5.isEmpty() && !string6.isEmpty() && !string7.isEmpty() && string3.equals(runtimeVin)) {
                JSONObject verifyBody = verifyBody(string, string3, string2);
                if (verifyBody.optLong("expiresAt", 0L) * 1000 <= System.currentTimeMillis()) {
                    return null;
                }
                return new CachedEngine(instantiate(decrypt(verifyBody, string4, string5, string6, string7)), sharedPreferences.getLong(KEY_FETCHED_AT, 0L));
            }
            return null;
        } catch (Exception e7) {
            e7.getMessage();
            return null;
        }
    }

    private static String runtimeVin() {
        String systemVin = NativeBridge.getSystemVin();
        return systemVin == null ? s5.h0.FRAGMENT_ENCODE_SET : systemVin.trim();
    }

    private static String sha256(byte[] bArr) {
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(bArr);
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b7 : digest) {
            sb.append(String.format(Locale.US, "%02x", Integer.valueOf(b7 & 255)));
        }
        return sb.toString();
    }

    private JSONObject verifyBody(String str, String str2, String str3) {
        String verifySignedLicense = NativeBridge.verifySignedLicense(str, str2, str3);
        if (verifySignedLicense == null || !verifySignedLicense.startsWith("OK|1|0|")) {
            throw new Exception(a.a.B("模块签名或授权校验失败: ", verifySignedLicense));
        }
        int lastIndexOf = str.lastIndexOf(124);
        if (lastIndexOf <= 0) {
            throw new Exception("模块报文格式错误");
        }
        JSONObject jSONObject = new JSONObject(str.substring(0, lastIndexOf));
        if (!MODULE.equals(jSONObject.optString("module"))) {
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

    public void ensureEngine(Context context, Callback callback) {
        if (callback == null) {
            return;
        }
        NaviEngine naviEngine = this.engine;
        if (naviEngine != null) {
            this.main.post(new l0(7, callback, naviEngine));
            return;
        }
        synchronized (this.callbacks) {
            try {
                this.callbacks.add(callback);
                if (this.loading) {
                    return;
                }
                this.loading = true;
                this.executor.execute(new l0(8, this, context.getApplicationContext()));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
