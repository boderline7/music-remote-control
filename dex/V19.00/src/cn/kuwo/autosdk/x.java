package cn.kuwo.autosdk;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x implements Runnable {

    /* renamed from: k, reason: collision with root package name */
    public static final AtomicLong f3149k = new AtomicLong();

    /* renamed from: l, reason: collision with root package name */
    public static final Proxy f3150l = Proxy.NO_PROXY;

    /* renamed from: m, reason: collision with root package name */
    public static final int f3151m = 8192;

    /* renamed from: c, reason: collision with root package name */
    public long f3154c;

    /* renamed from: e, reason: collision with root package name */
    public int f3156e;

    /* renamed from: f, reason: collision with root package name */
    public final long f3157f;

    /* renamed from: g, reason: collision with root package name */
    public HttpURLConnection f3158g;

    /* renamed from: h, reason: collision with root package name */
    public BufferedInputStream f3159h;

    /* renamed from: i, reason: collision with root package name */
    public ByteArrayOutputStream f3160i;

    /* renamed from: j, reason: collision with root package name */
    public final w f3161j;

    /* renamed from: a, reason: collision with root package name */
    public String f3152a = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f3153b = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public final boolean f3155d = true;

    public x() {
        w wVar = new w();
        wVar.f3145b = 0;
        long currentTimeMillis = System.currentTimeMillis();
        wVar.f3147d = currentTimeMillis;
        wVar.f3148e = currentTimeMillis;
        this.f3161j = wVar;
        this.f3157f = Thread.currentThread().getId();
        this.f3153b.put("Accept", "*/*");
        this.f3153b.put("Connection", "Close");
    }

    public final w a(String str) {
        this.f3152a = str;
        boolean isEmpty = TextUtils.isEmpty(str);
        w wVar = this.f3161j;
        if (!isEmpty && this.f3152a.length() <= f3151m && Thread.currentThread().getId() == this.f3157f) {
            int i7 = this.f3156e + 1;
            this.f3156e = i7;
            if (1 == i7) {
                wVar.getClass();
                AtomicLong atomicLong = f3149k;
                boolean z6 = this.f3155d;
                if (!z6 || atomicLong.get() <= Long.MAX_VALUE) {
                    try {
                        if (b()) {
                            try {
                                try {
                                    int responseCode = this.f3158g.getResponseCode();
                                    wVar.f3145b = responseCode;
                                    if (responseCode == 200 || responseCode == 201 || responseCode == 206) {
                                        this.f3159h = new BufferedInputStream(this.f3158g.getInputStream());
                                        this.f3160i = new ByteArrayOutputStream();
                                        try {
                                            byte[] bArr = new byte[4096];
                                            int i8 = (int) this.f3154c;
                                            if (i8 > 0) {
                                                this.f3158g.setReadTimeout(i8);
                                            }
                                            while (true) {
                                                int read = this.f3159h.read(bArr, 0, 4096);
                                                if (read <= 0) {
                                                    break;
                                                }
                                                if (z6) {
                                                    atomicLong.set(atomicLong.get() + read);
                                                }
                                                try {
                                                    this.f3160i.write(bArr, 0, read);
                                                } catch (OutOfMemoryError unused) {
                                                    wVar.f3145b = -5;
                                                }
                                            }
                                            System.currentTimeMillis();
                                            try {
                                                wVar.f3146c = this.f3160i.toByteArray();
                                                wVar.f3144a = true;
                                            } catch (OutOfMemoryError unused2) {
                                                wVar.f3145b = -5;
                                            }
                                        } catch (OutOfMemoryError unused3) {
                                            wVar.f3145b = -5;
                                        }
                                    }
                                } catch (Exception unused4) {
                                    wVar.f3145b = -1;
                                }
                            } catch (IOException unused5) {
                                wVar.f3145b = -1;
                            }
                            System.currentTimeMillis();
                            c();
                        }
                    } catch (Throwable th) {
                        System.currentTimeMillis();
                        c();
                        throw th;
                    }
                } else {
                    wVar.f3145b = -4;
                }
                return wVar;
            }
        }
        wVar.f3145b = -1;
        return wVar;
    }

    public final boolean b() {
        w wVar = this.f3161j;
        String str = this.f3152a;
        if (!str.toUpperCase().startsWith("HTTP")) {
            str = "http://" + this.f3152a;
        }
        try {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection(f3150l);
                    this.f3158g = httpURLConnection;
                    httpURLConnection.setInstanceFollowRedirects(true);
                    HashMap hashMap = this.f3153b;
                    if (hashMap != null) {
                        for (String str2 : hashMap.keySet()) {
                            this.f3158g.setRequestProperty(str2, (String) hashMap.get(str2));
                        }
                    }
                    int i7 = (int) this.f3154c;
                    if (i7 != 0) {
                        if (i7 <= 0) {
                            wVar.getClass();
                            return false;
                        }
                        this.f3158g.setConnectTimeout(i7);
                    }
                    try {
                        this.f3158g.connect();
                        System.currentTimeMillis();
                        long j7 = wVar.f3148e;
                        wVar.getClass();
                        return true;
                    } catch (IOException unused) {
                        wVar.getClass();
                        return false;
                    }
                } catch (IOException e7) {
                    e7.printStackTrace();
                    wVar.getClass();
                    return false;
                }
            } catch (MalformedURLException e8) {
                e8.printStackTrace();
                wVar.getClass();
                return false;
            }
        } catch (Exception unused2) {
            wVar.getClass();
            return false;
        }
    }

    public final synchronized void c() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = this.f3160i;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        } catch (IOException unused) {
        }
        try {
            BufferedInputStream bufferedInputStream = this.f3159h;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (IOException unused2) {
        }
        try {
            HttpURLConnection httpURLConnection = this.f3158g;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable unused3) {
            this.f3158g = null;
        }
    }

    public final void d() {
        c();
        System.currentTimeMillis();
        w wVar = this.f3161j;
        long j7 = wVar.f3147d;
        wVar.getClass();
        boolean z6 = wVar.f3144a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x00fb, code lost:
    
        if ((-2) != r3) goto L126;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.kuwo.autosdk.x.run():void");
    }
}
