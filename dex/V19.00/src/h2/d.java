package h2;

import com.github.junrar.exception.BadRarArchiveException;
import com.github.junrar.exception.CorruptHeaderException;
import com.github.junrar.exception.CrcErrorException;
import com.github.junrar.exception.MainHeaderNullException;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.UnsupportedRarEncryptedException;
import com.github.junrar.exception.UnsupportedRarV5Exception;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import k2.g;
import k2.j;
import k2.k;
import k2.n;
import k2.s;
import n2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements Closeable, Iterable {

    /* renamed from: l, reason: collision with root package name */
    public static final j6.a f6706l = j6.b.d(d.class);

    /* renamed from: a, reason: collision with root package name */
    public j2.a f6707a;

    /* renamed from: b, reason: collision with root package name */
    public final l2.a f6708b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f6709c;

    /* renamed from: d, reason: collision with root package name */
    public k f6710d;

    /* renamed from: e, reason: collision with root package name */
    public j f6711e;

    /* renamed from: f, reason: collision with root package name */
    public l2.d f6712f;

    /* renamed from: g, reason: collision with root package name */
    public int f6713g;

    /* renamed from: h, reason: collision with root package name */
    public final e.a f6714h;

    /* renamed from: i, reason: collision with root package name */
    public p2.a f6715i;

    /* renamed from: j, reason: collision with root package name */
    public g f6716j;

    /* renamed from: k, reason: collision with root package name */
    public final String f6717k;

    static {
        ((Integer) B("junrar.extractor.buffer-size", new a(0), 32768)).intValue();
        ((Boolean) B("junrar.extractor.use-executor", new a(1), Boolean.TRUE)).booleanValue();
    }

    public d(File file) {
        e.a aVar = new e.a(26, file);
        this.f6709c = new ArrayList();
        this.f6710d = null;
        this.f6711e = null;
        this.f6714h = aVar;
        this.f6717k = null;
        try {
            G(aVar.B(this, null));
            this.f6708b = new l2.a(this);
        } catch (RarException | IOException e7) {
            try {
                close();
            } catch (IOException unused) {
                f6706l.v();
            }
            throw e7;
        }
    }

    public static Object B(String str, a aVar, Object obj) {
        Objects.requireNonNull(obj, "default value must not be null");
        try {
            String property = System.getProperty(str);
            if (property != null && !property.isEmpty()) {
                return aVar.apply(property);
            }
        } catch (NumberFormatException | SecurityException e7) {
            f6706l.j(str, obj.getClass().getTypeName(), obj, e7);
        }
        return obj;
    }

    public static byte[] F(long j7) {
        if (j7 < 0 || j7 > 20971520) {
            throw new BadRarArchiveException();
        }
        return new byte[(int) j7];
    }

    public final void A(g gVar, FileOutputStream fileOutputStream) {
        m mVar;
        l2.a aVar = this.f6708b;
        aVar.f7279c = fileOutputStream;
        aVar.f7278b = 0L;
        aVar.f7282f = -1L;
        aVar.f7281e = -1L;
        aVar.f7280d = null;
        aVar.a(gVar);
        aVar.f7281e = this.f6710d.f7242g != n.OLD ? -1L : 0L;
        if (this.f6712f == null) {
            this.f6712f = new l2.d(aVar);
        }
        if ((gVar.f7213d & 16) == 0) {
            l2.d dVar = this.f6712f;
            dVar.f7293h = new byte[4194304];
            dVar.f7876a = 0;
            dVar.w(false);
        }
        l2.d dVar2 = this.f6712f;
        dVar2.f7292g = gVar.f7236v;
        try {
            dVar2.t(gVar.f7225k, (gVar.f7213d & 16) != 0);
            if ((~((aVar.f7280d.f7213d & 2) != 0 ? aVar.f7282f : aVar.f7281e)) == r7.f7224j) {
            } else {
                throw new CrcErrorException();
            }
        } catch (Exception e7) {
            n2.c cVar = this.f6712f.f7324r0;
            if (cVar != null && (mVar = cVar.f7717u) != null) {
                mVar.g();
            }
            if (!(e7 instanceof RarException)) {
                throw new RarException(e7);
            }
            throw ((RarException) e7);
        }
    }

    public final boolean C() {
        j jVar = this.f6711e;
        if (jVar != null) {
            return (jVar.f7213d & 128) != 0;
        }
        throw new MainHeaderNullException();
    }

    public final g D() {
        k2.b bVar;
        ArrayList arrayList = this.f6709c;
        int size = arrayList.size();
        do {
            int i7 = this.f6713g;
            if (i7 >= size) {
                return null;
            }
            this.f6713g = i7 + 1;
            bVar = (k2.b) arrayList.get(i7);
        } while (bVar.b() != s.FileHeader);
        return (g) bVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x03c6, code lost:
    
        throw new com.github.junrar.exception.CorruptHeaderException("Invalid Mark Header");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void E(long r19) {
        /*
            Method dump skipped, instructions count: 994
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.d.E(long):void");
    }

    public final void G(p2.a aVar) {
        this.f6715i = aVar;
        File file = aVar.f8223a;
        j2.b bVar = new j2.b(file);
        long length = file.length();
        j6.a aVar2 = f6706l;
        close();
        this.f6707a = bVar;
        try {
            E(length);
        } catch (BadRarArchiveException e7) {
            e = e7;
            aVar2.u(e);
            throw e;
        } catch (CorruptHeaderException e8) {
            e = e8;
            aVar2.u(e);
            throw e;
        } catch (UnsupportedRarEncryptedException e9) {
            e = e9;
            aVar2.u(e);
            throw e;
        } catch (UnsupportedRarV5Exception e10) {
            e = e10;
            aVar2.u(e);
            throw e;
        } catch (Exception e11) {
            aVar2.u(e11);
        }
        Iterator it = this.f6709c.iterator();
        while (it.hasNext()) {
            k2.b bVar2 = (k2.b) it.next();
            if (bVar2.b() == s.FileHeader) {
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        n2.c cVar;
        m mVar;
        j2.a aVar = this.f6707a;
        if (aVar != null) {
            aVar.close();
            this.f6707a = null;
        }
        l2.d dVar = this.f6712f;
        if (dVar == null || (cVar = dVar.f7324r0) == null || (mVar = cVar.f7717u) == null) {
            return;
        }
        mVar.g();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new b(this);
    }
}
