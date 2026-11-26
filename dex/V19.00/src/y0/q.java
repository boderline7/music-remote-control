package y0;

import java.nio.ByteBuffer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q {
    public static final int HAS_GLYPH_ABSENT = 1;
    public static final int HAS_GLYPH_EXISTS = 2;
    public static final int HAS_GLYPH_UNKNOWN = 0;

    /* renamed from: d, reason: collision with root package name */
    public static final ThreadLocal f9995d = new ThreadLocal();

    /* renamed from: a, reason: collision with root package name */
    public final int f9996a;

    /* renamed from: b, reason: collision with root package name */
    public final j.h f9997b;

    /* renamed from: c, reason: collision with root package name */
    public volatile int f9998c = 0;

    public q(j.h hVar, int i7) {
        this.f9997b = hVar;
        this.f9996a = i7;
    }

    public final int a(int i7) {
        z0.a c7 = c();
        int a7 = c7.a(16);
        if (a7 == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = c7.f10245b;
        int i8 = a7 + c7.f10244a;
        return byteBuffer.getInt((i7 * 4) + byteBuffer.getInt(i8) + i8 + 4);
    }

    public final int b() {
        z0.a c7 = c();
        int a7 = c7.a(16);
        if (a7 == 0) {
            return 0;
        }
        int i7 = a7 + c7.f10244a;
        return c7.f10245b.getInt(c7.f10245b.getInt(i7) + i7);
    }

    public final z0.a c() {
        ThreadLocal threadLocal = f9995d;
        z0.a aVar = (z0.a) threadLocal.get();
        if (aVar == null) {
            aVar = new z0.a();
            threadLocal.set(aVar);
        }
        z0.b bVar = (z0.b) this.f9997b.f6890a;
        int a7 = bVar.a(6);
        if (a7 != 0) {
            int i7 = a7 + bVar.f10244a;
            int i8 = (this.f9996a * 4) + bVar.f10245b.getInt(i7) + i7 + 4;
            int i9 = bVar.f10245b.getInt(i8) + i8;
            ByteBuffer byteBuffer = bVar.f10245b;
            aVar.f10245b = byteBuffer;
            if (byteBuffer != null) {
                aVar.f10244a = i9;
                int i10 = i9 - byteBuffer.getInt(i9);
                aVar.f10246c = i10;
                aVar.f10247d = aVar.f10245b.getShort(i10);
            } else {
                aVar.f10244a = 0;
                aVar.f10246c = 0;
                aVar.f10247d = 0;
            }
        }
        return aVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        z0.a c7 = c();
        int a7 = c7.a(4);
        sb.append(Integer.toHexString(a7 != 0 ? c7.f10245b.getInt(a7 + c7.f10244a) : 0));
        sb.append(", codepoints:");
        int b7 = b();
        for (int i7 = 0; i7 < b7; i7++) {
            sb.append(Integer.toHexString(a(i7)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
