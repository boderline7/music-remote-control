package com.navi.tool.amapflying.util.netease;

import a5.n;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.p0;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NativeNeteaseEngine {
    private static final String TAG = "NativeNeteaseEngine";
    private static NativeNeteaseEngine sInstance;
    private Callback mCallback;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public interface Callback {
        void onLyricsResolved(String str);
    }

    static {
        try {
            System.loadLibrary("navitool");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    private NativeNeteaseEngine() {
        nativeInit();
    }

    public static synchronized NativeNeteaseEngine getInstance() {
        NativeNeteaseEngine nativeNeteaseEngine;
        synchronized (NativeNeteaseEngine.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NativeNeteaseEngine();
                }
                nativeNeteaseEngine = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeNeteaseEngine;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLyricsResolved$0(String str) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onLyricsResolved(str);
        }
    }

    private native void nativeInit();

    private native void nativeSearchAndFetch(String str);

    public byte[] httpPost(String str, String str2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setReadTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36");
            httpURLConnection.setRequestProperty("origin", "https://music.163.com");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(str2.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            int i7 = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                i7 += read;
            }
            inputStream.close();
            httpURLConnection.disconnect();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (i7 > 0) {
                new String(byteArray, 0, Math.min(byteArray.length, 50), StandardCharsets.UTF_8);
            }
            return byteArray;
        } catch (Exception unused) {
            return null;
        }
    }

    public void onLyricsResolved(String str) {
        Objects.toString(str != null ? Integer.valueOf(str.length()) : "null");
        this.mMainHandler.post(new p0(13, this, str));
    }

    public void searchAndFetch(String str) {
        nativeSearchAndFetch(str);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
