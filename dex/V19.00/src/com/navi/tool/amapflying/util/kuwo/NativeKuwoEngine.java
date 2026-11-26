package com.navi.tool.amapflying.util.kuwo;

import a5.n;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.p0;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NativeKuwoEngine {
    private static final String TAG = "NativeKuwoEngine";
    private static NativeKuwoEngine sInstance;
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

    private NativeKuwoEngine() {
        nativeInit();
    }

    public static synchronized NativeKuwoEngine getInstance() {
        NativeKuwoEngine nativeKuwoEngine;
        synchronized (NativeKuwoEngine.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NativeKuwoEngine();
                }
                nativeKuwoEngine = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeKuwoEngine;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLyricsResolved$0(byte[] bArr) {
        if (this.mCallback == null || bArr == null) {
            return;
        }
        try {
            this.mCallback.onLyricsResolved(new String(bArr, "GB18030"));
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private native void nativeFetchLyrics(String str);

    private native void nativeInit();

    private native void nativeStop();

    public void fetchLyrics(String str) {
        nativeFetchLyrics(str);
    }

    public byte[] httpGet(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setReadTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public void onLyricsResolved(byte[] bArr) {
        this.mMainHandler.post(new p0(12, this, bArr));
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void stop() {
        nativeStop();
    }
}
