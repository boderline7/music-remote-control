package w1;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Comparable {

    /* renamed from: a, reason: collision with root package name */
    public final int f9415a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9416b;

    /* renamed from: c, reason: collision with root package name */
    public final b f9417c;

    public c(int i7, int i8, b bVar) {
        if (i7 < 0) {
            throw new IllegalArgumentException("start < 0");
        }
        if (i8 <= i7) {
            throw new IllegalArgumentException("end <= start");
        }
        if (bVar.f6364a) {
            throw new IllegalArgumentException("handlers.isMutable()");
        }
        this.f9415a = i7;
        this.f9416b = i8;
        this.f9417c = bVar;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(c cVar) {
        int i7 = cVar.f9415a;
        int i8 = this.f9415a;
        if (i8 < i7) {
            return -1;
        }
        if (i8 > i7) {
            return 1;
        }
        int i9 = this.f9416b;
        int i10 = cVar.f9416b;
        if (i9 < i10) {
            return -1;
        }
        if (i9 > i10) {
            return 1;
        }
        return this.f9417c.compareTo(cVar.f9417c);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof c) && compareTo((c) obj) == 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f9417c.f6354b) + (((this.f9415a * 31) + this.f9416b) * 31);
    }
}
