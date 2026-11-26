package okhttp3.internal.publicsuffix;

import b6.s;
import c5.o;
import c5.p;
import c5.r;
import e6.a;
import g6.d0;
import g6.e;
import g6.n;
import g6.t;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import n1.z;
import n5.b;
import o5.k;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class PublicSuffixDatabase {
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f8072a = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    public final CountDownLatch f8073b = new CountDownLatch(1);

    /* renamed from: c, reason: collision with root package name */
    public byte[] f8074c;

    /* renamed from: d, reason: collision with root package name */
    public byte[] f8075d;
    public static final a Companion = new a();

    /* renamed from: e, reason: collision with root package name */
    public static final byte[] f8069e = {42};

    /* renamed from: f, reason: collision with root package name */
    public static final List f8070f = z.G0("*");

    /* renamed from: g, reason: collision with root package name */
    public static final PublicSuffixDatabase f8071g = new PublicSuffixDatabase();

    public static List c(String str) {
        List P = k.P(str, new char[]{'.'});
        if (P.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (!i5.a.c(P.get(z.m0(P)), h0.FRAGMENT_ENCODE_SET)) {
            return P;
        }
        int size = P.size() - 1;
        if (size < 0) {
            size = 0;
        }
        if (size < 0) {
            throw new IllegalArgumentException(a.a.e("Requested element count ", size, " is less than zero.").toString());
        }
        if (size == 0) {
            return r.INSTANCE;
        }
        if (size >= P.size()) {
            return p.I1(P);
        }
        if (size == 1) {
            if (P.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            return z.G0(P.get(0));
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator it = P.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i7++;
            if (i7 == size) {
                break;
            }
        }
        int size2 = arrayList.size();
        return size2 != 0 ? size2 != 1 ? arrayList : z.G0(arrayList.get(0)) : r.INSTANCE;
    }

    public final String a(String str) {
        String str2;
        String str3;
        String str4;
        List P;
        int size;
        int size2;
        n5.a aVar;
        String unicode = IDN.toUnicode(str);
        i5.a.f("unicodeDomain", unicode);
        List c7 = c(unicode);
        int i7 = 0;
        if (this.f8072a.get() || !this.f8072a.compareAndSet(false, true)) {
            try {
                this.f8073b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z6 = false;
            while (true) {
                try {
                    try {
                        b();
                        break;
                    } finally {
                        if (z6) {
                            Thread.currentThread().interrupt();
                        }
                    }
                } catch (InterruptedIOException unused2) {
                    Thread.interrupted();
                    z6 = true;
                } catch (IOException e7) {
                    s.Companion.getClass();
                    s.f3002a.getClass();
                    s.i("Failed to read public suffix list", 5, e7);
                    if (z6) {
                    }
                }
            }
        }
        if (this.f8074c == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.".toString());
        }
        int size3 = c7.size();
        byte[][] bArr = new byte[size3][];
        for (int i8 = 0; i8 < size3; i8++) {
            String str5 = (String) c7.get(i8);
            Charset charset = StandardCharsets.UTF_8;
            i5.a.f("UTF_8", charset);
            byte[] bytes = str5.getBytes(charset);
            i5.a.f("this as java.lang.String).getBytes(charset)", bytes);
            bArr[i8] = bytes;
        }
        int i9 = 0;
        while (true) {
            if (i9 >= size3) {
                str2 = null;
                break;
            }
            a aVar2 = Companion;
            byte[] bArr2 = this.f8074c;
            if (bArr2 == null) {
                i5.a.t("publicSuffixListBytes");
                throw null;
            }
            str2 = a.a(aVar2, bArr2, bArr, i9);
            if (str2 != null) {
                break;
            }
            i9++;
        }
        if (size3 > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i10 = 0; i10 < length; i10++) {
                bArr3[i10] = f8069e;
                a aVar3 = Companion;
                byte[] bArr4 = this.f8074c;
                if (bArr4 == null) {
                    i5.a.t("publicSuffixListBytes");
                    throw null;
                }
                str3 = a.a(aVar3, bArr4, bArr3, i10);
                if (str3 != null) {
                    break;
                }
            }
        }
        str3 = null;
        if (str3 != null) {
            int i11 = size3 - 1;
            for (int i12 = 0; i12 < i11; i12++) {
                a aVar4 = Companion;
                byte[] bArr5 = this.f8075d;
                if (bArr5 == null) {
                    i5.a.t("publicSuffixExceptionListBytes");
                    throw null;
                }
                str4 = a.a(aVar4, bArr5, bArr, i12);
                if (str4 != null) {
                    break;
                }
            }
        }
        str4 = null;
        if (str4 != null) {
            P = k.P("!".concat(str4), new char[]{'.'});
        } else if (str2 == null && str3 == null) {
            P = f8070f;
        } else {
            List P2 = str2 != null ? k.P(str2, new char[]{'.'}) : r.INSTANCE;
            P = str3 != null ? k.P(str3, new char[]{'.'}) : r.INSTANCE;
            if (P2.size() > P.size()) {
                P = P2;
            }
        }
        if (c7.size() == P.size() && ((String) P.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) P.get(0)).charAt(0) == '!') {
            size = c7.size();
            size2 = P.size();
        } else {
            size = c7.size();
            size2 = P.size() + 1;
        }
        int i13 = size - size2;
        List c8 = c(str);
        i5.a.g("<this>", c8);
        b oVar = new o(c8);
        if (i13 < 0) {
            throw new IllegalArgumentException(a.a.e("Requested element count ", i13, " is less than zero.").toString());
        }
        if (i13 != 0) {
            if (oVar instanceof n5.a) {
                n5.a aVar5 = (n5.a) oVar;
                int i14 = aVar5.f7786b + i13;
                if (i14 < 0) {
                    aVar = new n5.a(aVar5, i13);
                } else {
                    oVar = new n5.a(aVar5.f7785a, i14);
                }
            } else {
                aVar = new n5.a(oVar, i13);
            }
            oVar = aVar;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) h0.FRAGMENT_ENCODE_SET);
        for (Object obj : oVar) {
            i7++;
            if (i7 > 1) {
                sb.append((CharSequence) ".");
            }
            i5.a.b(sb, obj, null);
        }
        sb.append((CharSequence) h0.FRAGMENT_ENCODE_SET);
        String sb2 = sb.toString();
        i5.a.f("joinTo(StringBuilder(), …ed, transform).toString()", sb2);
        return sb2;
    }

    public final void b() {
        try {
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
            if (resourceAsStream == null) {
                return;
            }
            Logger logger = g6.p.f6587a;
            t tVar = new t(new n(new e(resourceAsStream, new d0())));
            try {
                long n6 = tVar.n();
                tVar.q(n6);
                byte[] G = tVar.f6594b.G(n6);
                long n7 = tVar.n();
                tVar.q(n7);
                byte[] G2 = tVar.f6594b.G(n7);
                z.A(tVar, null);
                synchronized (this) {
                    this.f8074c = G;
                    this.f8075d = G2;
                }
            } finally {
            }
        } finally {
            this.f8073b.countDown();
        }
    }
}
