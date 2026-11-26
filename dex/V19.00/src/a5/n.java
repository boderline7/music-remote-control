package a5;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n {
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final int SOCKET_READ_TIMEOUT = 5000;

    /* renamed from: h, reason: collision with root package name */
    public static final Pattern f354h = Pattern.compile("([ |\t]*Content-Disposition[ |\t]*:)(.*)", 2);

    /* renamed from: i, reason: collision with root package name */
    public static final Pattern f355i = Pattern.compile("([ |\t]*content-type[ |\t]*:)(.*)", 2);

    /* renamed from: j, reason: collision with root package name */
    public static final Pattern f356j = Pattern.compile("[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]");

    /* renamed from: k, reason: collision with root package name */
    public static final Logger f357k = Logger.getLogger(n.class.getName());

    /* renamed from: b, reason: collision with root package name */
    public final int f359b;

    /* renamed from: c, reason: collision with root package name */
    public volatile ServerSocket f360c;

    /* renamed from: e, reason: collision with root package name */
    public Thread f362e;

    /* renamed from: d, reason: collision with root package name */
    public final p3.f f361d = new p3.f(29, 0);

    /* renamed from: a, reason: collision with root package name */
    public final String f358a = null;

    /* renamed from: g, reason: collision with root package name */
    public final v2.a f364g = new v2.a(this);

    /* renamed from: f, reason: collision with root package name */
    public final androidx.recyclerview.widget.i f363f = new androidx.recyclerview.widget.i(2);

    public n(int i7) {
        this.f359b = i7;
    }

    public static String b(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException e7) {
            f357k.log(Level.WARNING, "Encoding not supported, ignored", (Throwable) e7);
            return null;
        }
    }

    public static l c(k kVar, String str, String str2) {
        byte[] bArr;
        b bVar = new b(str);
        if (str2 == null) {
            return new l(kVar, str, new ByteArrayInputStream(new byte[0]), 0L);
        }
        try {
            if (!Charset.forName(bVar.a()).newEncoder().canEncode(str2) && bVar.f314c == null) {
                bVar = new b(str.concat("; charset=UTF-8"));
            }
            bArr = str2.getBytes(bVar.a());
        } catch (UnsupportedEncodingException e7) {
            f357k.log(Level.SEVERE, "encoding problem, responding nothing", (Throwable) e7);
            bArr = new byte[0];
        }
        return new l(kVar, bVar.f312a, new ByteArrayInputStream(bArr), bArr.length);
    }

    public static final void d(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e7) {
                f357k.log(Level.SEVERE, "Could not close", (Throwable) e7);
            }
        }
    }

    public static boolean h(l lVar) {
        String str = lVar.f341b;
        return str != null && (str.toLowerCase().contains("text/") || lVar.f341b.toLowerCase().contains("/json"));
    }

    public abstract l e(f fVar);

    public final void f() {
        this.f361d.getClass();
        this.f360c = new ServerSocket();
        this.f360c.setReuseAddress(true);
        m mVar = new m(this);
        Thread thread = new Thread(mVar);
        this.f362e = thread;
        thread.setDaemon(true);
        this.f362e.setName("NanoHttpd Main Listener");
        this.f362e.start();
        while (!mVar.f352c && mVar.f351b == null) {
            try {
                Thread.sleep(10L);
            } catch (Throwable unused) {
            }
        }
        IOException iOException = mVar.f351b;
        if (iOException != null) {
            throw iOException;
        }
    }

    public final void g() {
        try {
            d(this.f360c);
            androidx.recyclerview.widget.i iVar = this.f363f;
            iVar.getClass();
            Iterator it = new ArrayList((List) iVar.f2488c).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                d(aVar.f306a);
                d(aVar.f307b);
            }
            Thread thread = this.f362e;
            if (thread != null) {
                thread.join();
            }
        } catch (Exception e7) {
            f357k.log(Level.SEVERE, "Could not stop all connections", (Throwable) e7);
        }
    }
}
