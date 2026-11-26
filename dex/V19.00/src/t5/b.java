package t5;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.appcompat.widget.u;
import c5.d;
import g6.a0;
import g6.h;
import g6.j;
import g6.r;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import o5.e;
import o5.k;
import s5.a1;
import s5.d0;
import s5.g0;
import s5.h0;
import s5.v0;
import s5.x0;
import z1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final d0 EMPTY_HEADERS;
    public static final v0 EMPTY_REQUEST;
    public static final a1 EMPTY_RESPONSE;
    public static final TimeZone UTC;

    /* renamed from: a, reason: collision with root package name */
    public static final r f9092a;
    public static final boolean assertionsEnabled;

    /* renamed from: b, reason: collision with root package name */
    public static final e f9093b;
    public static final String okHttpName;
    public static final String userAgent = "okhttp/4.12.0";

    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ac, code lost:
    
        continue;
     */
    static {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t5.b.<clinit>():void");
    }

    public static final boolean a(h0 h0Var, h0 h0Var2) {
        i5.a.g("<this>", h0Var);
        i5.a.g("other", h0Var2);
        return i5.a.c(h0Var.f8795d, h0Var2.f8795d) && h0Var.f8796e == h0Var2.f8796e && i5.a.c(h0Var.f8792a, h0Var2.f8792a);
    }

    public static final int b(long j7, TimeUnit timeUnit) {
        if (j7 < 0) {
            throw new IllegalStateException("timeout".concat(" < 0").toString());
        }
        if (timeUnit == null) {
            throw new IllegalStateException("unit == null".toString());
        }
        long millis = timeUnit.toMillis(j7);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("timeout".concat(" too large.").toString());
        }
        if (millis != 0 || j7 <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException("timeout".concat(" too small.").toString());
    }

    public static final void c(Closeable closeable) {
        i5.a.g("<this>", closeable);
        try {
            closeable.close();
        } catch (RuntimeException e7) {
            throw e7;
        } catch (Exception unused) {
        }
    }

    public static final void d(Socket socket) {
        try {
            socket.close();
        } catch (AssertionError e7) {
            throw e7;
        } catch (RuntimeException e8) {
            if (!i5.a.c(e8.getMessage(), "bio == null")) {
                throw e8;
            }
        } catch (Exception unused) {
        }
    }

    public static final int e(int i7, int i8, String str, String str2) {
        i5.a.g("<this>", str);
        while (i7 < i8) {
            if (k.z(str2, str.charAt(i7))) {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static final int f(String str, char c7, int i7, int i8) {
        i5.a.g("<this>", str);
        while (i7 < i8) {
            if (str.charAt(i7) == c7) {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static final boolean g(a0 a0Var, TimeUnit timeUnit) {
        i5.a.g("<this>", a0Var);
        i5.a.g("timeUnit", timeUnit);
        try {
            return u(a0Var, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String h(String str, Object... objArr) {
        i5.a.g("format", str);
        Locale locale = Locale.US;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        String format = String.format(locale, str, Arrays.copyOf(copyOf, copyOf.length));
        i5.a.f("format(locale, format, *args)", format);
        return format;
    }

    public static final boolean i(String[] strArr, String[] strArr2, Comparator comparator) {
        i5.a.g("<this>", strArr);
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                d dVar = new d(strArr2);
                while (dVar.hasNext()) {
                    if (comparator.compare(str, (String) dVar.next()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final long j(x0 x0Var) {
        String a7 = x0Var.f8918f.a("Content-Length");
        if (a7 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(a7);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final List k(Object... objArr) {
        List list;
        i5.a.g("elements", objArr);
        Object[] objArr2 = (Object[]) objArr.clone();
        Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length);
        i5.a.g("elements", copyOf);
        if (copyOf.length > 0) {
            list = Arrays.asList(copyOf);
            i5.a.f("asList(this)", list);
        } else {
            list = c5.r.INSTANCE;
        }
        List unmodifiableList = Collections.unmodifiableList(list);
        i5.a.f("unmodifiableList(listOf(*elements.clone()))", unmodifiableList);
        return unmodifiableList;
    }

    public static final int l(String str) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            if (i5.a.i(charAt, 31) <= 0 || i5.a.i(charAt, c.NEG_FLOAT) >= 0) {
                return i7;
            }
        }
        return -1;
    }

    public static final int m(int i7, int i8, String str) {
        while (i7 < i8) {
            char charAt = str.charAt(i7);
            if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static final int n(int i7, int i8, String str) {
        int i9 = i8 - 1;
        if (i7 <= i9) {
            while (true) {
                char charAt = str.charAt(i9);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i9 + 1;
                }
                if (i9 == i7) {
                    break;
                }
                i9--;
            }
        }
        return i7;
    }

    public static final String[] o(String[] strArr, String[] strArr2, Comparator comparator) {
        i5.a.g("other", strArr2);
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i7]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i7++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean p(String str) {
        i5.a.g("name", str);
        return k.C(str, "Authorization") || k.C(str, "Cookie") || k.C(str, "Proxy-Authorization") || k.C(str, "Set-Cookie");
    }

    public static final int q(char c7) {
        if ('0' <= c7 && c7 < ':') {
            return c7 - '0';
        }
        if ('a' <= c7 && c7 < 'g') {
            return c7 - 'W';
        }
        if ('A' > c7 || c7 >= 'G') {
            return -1;
        }
        return c7 - '7';
    }

    public static final Charset r(j jVar, Charset charset) {
        Charset charset2;
        i5.a.g("<this>", jVar);
        i5.a.g("default", charset);
        int d7 = jVar.d(f9092a);
        if (d7 == -1) {
            return charset;
        }
        if (d7 == 0) {
            Charset charset3 = StandardCharsets.UTF_8;
            i5.a.f("UTF_8", charset3);
            return charset3;
        }
        if (d7 == 1) {
            Charset charset4 = StandardCharsets.UTF_16BE;
            i5.a.f("UTF_16BE", charset4);
            return charset4;
        }
        if (d7 == 2) {
            Charset charset5 = StandardCharsets.UTF_16LE;
            i5.a.f("UTF_16LE", charset5);
            return charset5;
        }
        if (d7 == 3) {
            o5.a.INSTANCE.getClass();
            charset2 = o5.a.f8051b;
            if (charset2 == null) {
                charset2 = Charset.forName("UTF-32BE");
                i5.a.f("forName(\"UTF-32BE\")", charset2);
                o5.a.f8051b = charset2;
            }
        } else {
            if (d7 != 4) {
                throw new AssertionError();
            }
            o5.a.INSTANCE.getClass();
            charset2 = o5.a.f8050a;
            if (charset2 == null) {
                charset2 = Charset.forName("UTF-32LE");
                i5.a.f("forName(\"UTF-32LE\")", charset2);
                o5.a.f8050a = charset2;
            }
        }
        return charset2;
    }

    public static final int s(j jVar) {
        i5.a.g("<this>", jVar);
        return (jVar.y() & 255) | ((jVar.y() & 255) << 16) | ((jVar.y() & 255) << 8);
    }

    public static final int t(h hVar) {
        int i7 = 0;
        while (!hVar.t() && hVar.C(0L) == 61) {
            i7++;
            hVar.y();
        }
        return i7;
    }

    public static final boolean u(a0 a0Var, int i7, TimeUnit timeUnit) {
        i5.a.g("<this>", a0Var);
        i5.a.g("timeUnit", timeUnit);
        long nanoTime = System.nanoTime();
        long c7 = a0Var.a().e() ? a0Var.a().c() - nanoTime : Long.MAX_VALUE;
        a0Var.a().d(Math.min(c7, timeUnit.toNanos(i7)) + nanoTime);
        try {
            h hVar = new h();
            while (a0Var.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                hVar.h(hVar.f6573b);
            }
            if (c7 == Long.MAX_VALUE) {
                a0Var.a().a();
            } else {
                a0Var.a().d(nanoTime + c7);
            }
            return true;
        } catch (InterruptedIOException unused) {
            if (c7 == Long.MAX_VALUE) {
                a0Var.a().a();
            } else {
                a0Var.a().d(nanoTime + c7);
            }
            return false;
        } catch (Throwable th) {
            if (c7 == Long.MAX_VALUE) {
                a0Var.a().a();
            } else {
                a0Var.a().d(nanoTime + c7);
            }
            throw th;
        }
    }

    public static final d0 v(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            a6.d dVar = (a6.d) it.next();
            String j7 = dVar.f378a.j();
            String j8 = dVar.f379b.j();
            arrayList.add(j7);
            arrayList.add(k.U(j8).toString());
        }
        return new d0((String[]) arrayList.toArray(new String[0]));
    }

    public static final String w(h0 h0Var, boolean z6) {
        i5.a.g("<this>", h0Var);
        String str = h0Var.f8795d;
        if (k.A(str, ":")) {
            str = "[" + str + ']';
        }
        int i7 = h0Var.f8796e;
        if (!z6) {
            h0.Companion.getClass();
            if (i7 == g0.b(h0Var.f8792a)) {
                return str;
            }
        }
        return str + ':' + i7;
    }

    public static final List x(List list) {
        i5.a.g("<this>", list);
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        i5.a.f("unmodifiableList(toMutableList())", unmodifiableList);
        return unmodifiableList;
    }

    public static final int y(int i7, String str) {
        if (str == null) {
            return i7;
        }
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return u.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i7;
        }
    }

    public static final String z(int i7, int i8, String str) {
        int m6 = m(i7, i8, str);
        String substring = str.substring(m6, n(m6, i8, str));
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }
}
