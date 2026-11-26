package com.navi.tool.amapflying.util.kugou;

import a5.n;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.p0;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NativeKugouEngine {
    private static final String TAG = "NativeKugouEngine";
    private static NativeKugouEngine sInstance;
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

    private NativeKugouEngine() {
        nativeInit();
    }

    public static synchronized NativeKugouEngine getInstance() {
        NativeKugouEngine nativeKugouEngine;
        synchronized (NativeKugouEngine.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NativeKugouEngine();
                }
                nativeKugouEngine = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeKugouEngine;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onKrcDecrypted$0(String str) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onLyricsResolved(str);
        }
    }

    private native void nativeInit();

    private native void nativeSearchAndFetch(String str, String str2, long j7);

    private native void nativeStop();

    public void fetchLyrics(String str, String str2, long j7) {
        nativeSearchAndFetch(str, str2, j7);
    }

    public byte[] httpGet(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setReadTimeout(n.SOCKET_READ_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
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

    public void onKrcDecrypted(String str) {
        this.mMainHandler.post(new p0(11, this, str));
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
