package g6;

import java.security.MessageDigest;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x extends l {

    /* renamed from: d, reason: collision with root package name */
    public final transient byte[][] f6606d;

    /* renamed from: e, reason: collision with root package name */
    public final transient int[] f6607e;

    public x(byte[][] bArr, int[] iArr) {
        super(l.EMPTY.f6574a);
        this.f6606d = bArr;
        this.f6607e = iArr;
    }

    @Override // g6.l
    public final String a() {
        throw null;
    }

    @Override // g6.l
    public final l b(String str) {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[][] bArr = this.f6606d;
        int length = bArr.length;
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int[] iArr = this.f6607e;
            int i9 = iArr[length + i7];
            int i10 = iArr[i7];
            messageDigest.update(bArr[i7], i9, i10 - i8);
            i7++;
            i8 = i10;
        }
        byte[] digest = messageDigest.digest();
        i5.a.d(digest);
        return new l(digest);
    }

    @Override // g6.l
    public final int c() {
        return this.f6607e[this.f6606d.length - 1];
    }

    @Override // g6.l
    public final String d() {
        return new l(l()).d();
    }

    @Override // g6.l
    public final byte[] e() {
        return l();
    }

    @Override // g6.l
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            if (lVar.c() == c() && h(lVar, c())) {
                return true;
            }
        }
        return false;
    }

    @Override // g6.l
    public final byte f(int i7) {
        byte[][] bArr = this.f6606d;
        int length = bArr.length - 1;
        int[] iArr = this.f6607e;
        n1.z.w(iArr[length], i7, 1L);
        int p6 = i5.a.p(this, i7);
        return bArr[p6][(i7 - (p6 == 0 ? 0 : iArr[p6 - 1])) + iArr[bArr.length + p6]];
    }

    @Override // g6.l
    public final boolean g(int i7, byte[] bArr, int i8, int i9) {
        i5.a.g("other", bArr);
        if (i7 < 0 || i7 > c() - i9 || i8 < 0 || i8 > bArr.length - i9) {
            return false;
        }
        int i10 = i9 + i7;
        int p6 = i5.a.p(this, i7);
        while (i7 < i10) {
            int[] iArr = this.f6607e;
            int i11 = p6 == 0 ? 0 : iArr[p6 - 1];
            int i12 = iArr[p6] - i11;
            byte[][] bArr2 = this.f6606d;
            int i13 = iArr[bArr2.length + p6];
            int min = Math.min(i10, i12 + i11) - i7;
            if (!n1.z.i((i7 - i11) + i13, i8, min, bArr2[p6], bArr)) {
                return false;
            }
            i8 += min;
            i7 += min;
            p6++;
        }
        return true;
    }

    @Override // g6.l
    public final boolean h(l lVar, int i7) {
        i5.a.g("other", lVar);
        if (c() - i7 < 0) {
            return false;
        }
        int p6 = i5.a.p(this, 0);
        int i8 = 0;
        int i9 = 0;
        while (i8 < i7) {
            int[] iArr = this.f6607e;
            int i10 = p6 == 0 ? 0 : iArr[p6 - 1];
            int i11 = iArr[p6] - i10;
            byte[][] bArr = this.f6606d;
            int i12 = iArr[bArr.length + p6];
            int min = Math.min(i7, i11 + i10) - i8;
            if (!lVar.g(i9, bArr[p6], (i8 - i10) + i12, min)) {
                return false;
            }
            i9 += min;
            i8 += min;
            p6++;
        }
        return true;
    }

    @Override // g6.l
    public final int hashCode() {
        int i7 = this.f6575b;
        if (i7 != 0) {
            return i7;
        }
        byte[][] bArr = this.f6606d;
        int length = bArr.length;
        int i8 = 0;
        int i9 = 1;
        int i10 = 0;
        while (i8 < length) {
            int[] iArr = this.f6607e;
            int i11 = iArr[length + i8];
            int i12 = iArr[i8];
            byte[] bArr2 = bArr[i8];
            int i13 = (i12 - i10) + i11;
            while (i11 < i13) {
                i9 = (i9 * 31) + bArr2[i11];
                i11++;
            }
            i8++;
            i10 = i12;
        }
        this.f6575b = i9;
        return i9;
    }

    @Override // g6.l
    public final l i() {
        return new l(l()).i();
    }

    @Override // g6.l
    public final void k(h hVar, int i7) {
        i5.a.g("buffer", hVar);
        int p6 = i5.a.p(this, 0);
        int i8 = 0;
        while (i8 < i7) {
            int[] iArr = this.f6607e;
            int i9 = p6 == 0 ? 0 : iArr[p6 - 1];
            int i10 = iArr[p6] - i9;
            byte[][] bArr = this.f6606d;
            int i11 = iArr[bArr.length + p6];
            int min = Math.min(i7, i10 + i9) - i8;
            int i12 = (i8 - i9) + i11;
            v vVar = new v(bArr[p6], i12, i12 + min, true);
            v vVar2 = hVar.f6572a;
            if (vVar2 == null) {
                vVar.f6602g = vVar;
                vVar.f6601f = vVar;
                hVar.f6572a = vVar;
            } else {
                v vVar3 = vVar2.f6602g;
                i5.a.d(vVar3);
                vVar3.b(vVar);
            }
            i8 += min;
            p6++;
        }
        hVar.f6573b += i7;
    }

    public final byte[] l() {
        byte[] bArr = new byte[c()];
        byte[][] bArr2 = this.f6606d;
        int length = bArr2.length;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < length) {
            int[] iArr = this.f6607e;
            int i10 = iArr[length + i7];
            int i11 = iArr[i7];
            int i12 = i11 - i8;
            c5.l.y1(i9, i10, i10 + i12, bArr2[i7], bArr);
            i9 += i12;
            i7++;
            i8 = i11;
        }
        return bArr;
    }

    @Override // g6.l
    public final String toString() {
        return new l(l()).toString();
    }
}
