package y1;

import com.android.dex.util.ExceptionWithContext;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final v1.a f10072a;

    /* renamed from: b, reason: collision with root package name */
    public final z f10073b;

    /* renamed from: c, reason: collision with root package name */
    public final z f10074c;

    /* renamed from: d, reason: collision with root package name */
    public final z f10075d;

    /* renamed from: e, reason: collision with root package name */
    public final z f10076e;

    /* renamed from: f, reason: collision with root package name */
    public final u f10077f;

    /* renamed from: g, reason: collision with root package name */
    public final u f10078g;

    /* renamed from: h, reason: collision with root package name */
    public final u f10079h;

    /* renamed from: i, reason: collision with root package name */
    public final l f10080i;

    /* renamed from: j, reason: collision with root package name */
    public final w f10081j;

    /* renamed from: k, reason: collision with root package name */
    public final b f10082k;

    /* renamed from: l, reason: collision with root package name */
    public final z f10083l;

    /* renamed from: m, reason: collision with root package name */
    public final b f10084m;

    /* renamed from: n, reason: collision with root package name */
    public final u f10085n;

    /* renamed from: o, reason: collision with root package name */
    public final z f10086o;

    /* renamed from: p, reason: collision with root package name */
    public final u f10087p;

    /* renamed from: q, reason: collision with root package name */
    public final c0[] f10088q;

    /* renamed from: r, reason: collision with root package name */
    public int f10089r;

    public f(v1.a aVar) {
        this.f10072a = aVar;
        u uVar = new u(this, 4);
        this.f10087p = uVar;
        y yVar = y.NONE;
        z zVar = new z(null, this, 4, yVar);
        this.f10074c = zVar;
        y yVar2 = y.TYPE;
        z zVar2 = new z("word_data", this, 4, yVar2);
        this.f10073b = zVar2;
        z zVar3 = new z("string_data", this, 1, y.INSTANCE);
        this.f10076e = zVar3;
        z zVar4 = new z(null, this, 1, yVar);
        this.f10083l = zVar4;
        z zVar5 = new z("byte_data", this, 1, yVar2);
        this.f10086o = zVar5;
        u uVar2 = new u(this, 2);
        this.f10077f = uVar2;
        u uVar3 = new u(this, 3);
        this.f10078g = uVar3;
        u uVar4 = new u(this, 1);
        this.f10079h = uVar4;
        l lVar = new l(this);
        this.f10080i = lVar;
        w wVar = new w(this);
        this.f10081j = wVar;
        b bVar = new b(this, 1);
        this.f10082k = bVar;
        z zVar6 = new z("map", this, 4, yVar);
        this.f10075d = zVar6;
        if (aVar.f9386a >= 26) {
            b bVar2 = new b(this, 0);
            this.f10084m = bVar2;
            u uVar5 = new u(this, 0);
            this.f10085n = uVar5;
            this.f10088q = new c0[]{uVar, uVar2, uVar3, uVar4, lVar, wVar, bVar, bVar2, uVar5, zVar2, zVar, zVar3, zVar5, zVar4, zVar6};
        } else {
            this.f10084m = null;
            this.f10085n = null;
            this.f10088q = new c0[]{uVar, uVar2, uVar3, uVar4, lVar, wVar, bVar, zVar2, zVar, zVar3, zVar5, zVar4, zVar6};
        }
        this.f10089r = -1;
    }

    public final void a(b2.a aVar) {
        if (aVar == null) {
            throw new NullPointerException("cst == null");
        }
        if (aVar instanceof b2.u) {
            this.f10077f.p((b2.u) aVar);
            return;
        }
        if (aVar instanceof b2.v) {
            this.f10078g.q((b2.v) aVar);
        } else if (aVar instanceof b2.d) {
            this.f10081j.m((b2.d) aVar);
        } else if (aVar instanceof b2.i) {
            this.f10080i.m((b2.i) aVar);
        }
    }

    public final byte[] b() {
        this.f10082k.d();
        this.f10083l.d();
        this.f10073b.d();
        v1.a aVar = this.f10072a;
        boolean z6 = aVar.f9386a >= 26;
        b bVar = this.f10084m;
        if (z6) {
            bVar.d();
        }
        this.f10086o.d();
        boolean z7 = aVar.f9386a >= 26;
        u uVar = this.f10085n;
        if (z7) {
            uVar.d();
        }
        this.f10081j.d();
        this.f10080i.d();
        this.f10079h.d();
        this.f10074c.d();
        this.f10078g.d();
        this.f10077f.d();
        this.f10076e.d();
        this.f10087p.d();
        c0[] c0VarArr = this.f10088q;
        int length = c0VarArr.length;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            c0 c0Var = c0VarArr[i8];
            if ((c0Var != bVar && c0Var != uVar) || !c0Var.c().isEmpty()) {
                if (i7 < 0) {
                    c0Var.getClass();
                    throw new IllegalArgumentException("fileOffset < 0");
                }
                if (c0Var.f10054d >= 0) {
                    throw new RuntimeException("fileOffset already set");
                }
                int i9 = c0Var.f10053c - 1;
                int i10 = (~i9) & (i7 + i9);
                c0Var.f10054d = i10;
                if (i10 < i7) {
                    throw new RuntimeException(a.a.d("bogus placement for section ", i8));
                }
                z zVar = this.f10075d;
                if (c0Var == zVar) {
                    try {
                        r.l(c0VarArr, zVar);
                        zVar.d();
                    } catch (RuntimeException e7) {
                        throw ExceptionWithContext.a("...while writing section " + i8, e7);
                    }
                }
                if (c0Var instanceof z) {
                    ((z) c0Var).m();
                }
                i7 = c0Var.h() + i10;
            }
        }
        this.f10089r = i7;
        byte[] bArr = new byte[i7];
        e2.c cVar = new e2.c(bArr, false);
        for (int i11 = 0; i11 < length; i11++) {
            try {
                c0 c0Var2 = c0VarArr[i11];
                if ((c0Var2 != bVar && c0Var2 != uVar) || !c0Var2.c().isEmpty()) {
                    int b7 = c0Var2.b() - cVar.f6349c;
                    if (b7 < 0) {
                        throw new ExceptionWithContext("excess write of " + (-b7), null);
                    }
                    cVar.n(b7);
                    c0Var2.i(cVar);
                }
            } catch (RuntimeException e8) {
                ExceptionWithContext exceptionWithContext = e8 instanceof ExceptionWithContext ? (ExceptionWithContext) e8 : new ExceptionWithContext(null, e8);
                String d7 = a.a.d("...while writing section ", i11);
                if (d7 == null) {
                    throw new NullPointerException("str == null");
                }
                StringBuffer stringBuffer = exceptionWithContext.f3188a;
                stringBuffer.append(d7);
                if (!d7.endsWith("\n")) {
                    stringBuffer.append('\n');
                }
                throw exceptionWithContext;
            }
        }
        int i12 = cVar.f6349c;
        if (i12 != this.f10089r) {
            throw new RuntimeException("foreshortened write");
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bArr, 32, i12 - 32);
            try {
                int digest = messageDigest.digest(bArr, 12, 20);
                if (digest != 20) {
                    throw new RuntimeException("unexpected digest write: " + digest + " bytes");
                }
                int i13 = cVar.f6349c;
                Adler32 adler32 = new Adler32();
                adler32.update(bArr, 12, i13 - 12);
                int value = (int) adler32.getValue();
                bArr[8] = (byte) value;
                bArr[9] = (byte) (value >> 8);
                bArr[10] = (byte) (value >> 16);
                bArr[11] = (byte) (value >> 24);
                return cVar.f6348b;
            } catch (DigestException e9) {
                throw new RuntimeException(e9);
            }
        } catch (NoSuchAlgorithmException e10) {
            throw new RuntimeException(e10);
        }
    }
}
