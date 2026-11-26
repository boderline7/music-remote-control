package i0;

import android.graphics.Insets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static final c NONE = new c(0, 0, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    public final int f6773a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6774b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6775c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6776d;

    public c(int i7, int i8, int i9, int i10) {
        this.f6773a = i7;
        this.f6774b = i8;
        this.f6775c = i9;
        this.f6776d = i10;
    }

    public static c a(c cVar, c cVar2) {
        return b(Math.max(cVar.f6773a, cVar2.f6773a), Math.max(cVar.f6774b, cVar2.f6774b), Math.max(cVar.f6775c, cVar2.f6775c), Math.max(cVar.f6776d, cVar2.f6776d));
    }

    public static c b(int i7, int i8, int i9, int i10) {
        return (i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) ? NONE : new c(i7, i8, i9, i10);
    }

    public static c c(Insets insets) {
        int i7;
        int i8;
        int i9;
        int i10;
        i7 = insets.left;
        i8 = insets.top;
        i9 = insets.right;
        i10 = insets.bottom;
        return b(i7, i8, i9, i10);
    }

    public final Insets d() {
        return b.a(this.f6773a, this.f6774b, this.f6775c, this.f6776d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return this.f6776d == cVar.f6776d && this.f6773a == cVar.f6773a && this.f6775c == cVar.f6775c && this.f6774b == cVar.f6774b;
    }

    public final int hashCode() {
        return (((((this.f6773a * 31) + this.f6774b) * 31) + this.f6775c) * 31) + this.f6776d;
    }

    public final String toString() {
        return "Insets{left=" + this.f6773a + ", top=" + this.f6774b + ", right=" + this.f6775c + ", bottom=" + this.f6776d + '}';
    }
}
