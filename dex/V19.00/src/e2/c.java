package e2;

import androidx.appcompat.widget.u;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements a {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f6347a;

    /* renamed from: b, reason: collision with root package name */
    public byte[] f6348b;

    /* renamed from: c, reason: collision with root package name */
    public int f6349c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f6350d;

    /* renamed from: e, reason: collision with root package name */
    public final ArrayList f6351e;

    /* renamed from: f, reason: collision with root package name */
    public final int f6352f;

    /* renamed from: g, reason: collision with root package name */
    public final int f6353g;

    public c() {
        this(new byte[1000], true);
    }

    public c(byte[] bArr, boolean z6) {
        this.f6347a = z6;
        this.f6348b = bArr;
        this.f6349c = 0;
        this.f6350d = false;
        this.f6351e = null;
        this.f6352f = 0;
        this.f6353g = 0;
    }

    public static void g() {
        throw new IndexOutOfBoundsException("attempt to write past the end");
    }

    public final void a(int i7) {
        int i8 = i7 - 1;
        if (i7 < 0 || (i7 & i8) != 0) {
            throw new IllegalArgumentException("bogus alignment");
        }
        int i9 = (this.f6349c + i8) & (~i8);
        if (this.f6347a) {
            f(i9);
        } else if (i9 > this.f6348b.length) {
            g();
            throw null;
        }
        Arrays.fill(this.f6348b, this.f6349c, i9, (byte) 0);
        this.f6349c = i9;
    }

    public final void b(int i7, String str) {
        if (this.f6351e == null) {
            return;
        }
        e();
        int size = this.f6351e.size();
        int i8 = size == 0 ? 0 : ((b) this.f6351e.get(size - 1)).f6346a;
        int i9 = this.f6349c;
        if (i8 <= i9) {
            i8 = i9;
        }
        this.f6351e.add(new b(i8, i7 + i8, str));
    }

    public final void c(String str) {
        if (this.f6351e == null) {
            return;
        }
        e();
        this.f6351e.add(new b(this.f6349c, u.MAX_ACTIVITY_COUNT_UNLIMITED, str));
    }

    public final boolean d() {
        return this.f6351e != null;
    }

    public final void e() {
        int size;
        ArrayList arrayList = this.f6351e;
        if (arrayList == null || (size = arrayList.size()) == 0) {
            return;
        }
        b bVar = (b) this.f6351e.get(size - 1);
        int i7 = this.f6349c;
        if (bVar.f6346a == Integer.MAX_VALUE) {
            bVar.f6346a = i7;
        }
    }

    public final void f(int i7) {
        byte[] bArr = this.f6348b;
        if (bArr.length < i7) {
            byte[] bArr2 = new byte[(i7 * 2) + 1000];
            System.arraycopy(bArr, 0, bArr2, 0, this.f6349c);
            this.f6348b = bArr2;
        }
    }

    public final void h(byte[] bArr) {
        int length = bArr.length;
        int i7 = this.f6349c;
        int i8 = i7 + length;
        if ((length | i8) < 0 || length > bArr.length) {
            throw new IndexOutOfBoundsException("bytes.length " + bArr.length + "; 0..!" + i8);
        }
        if (this.f6347a) {
            f(i8);
        } else if (i8 > this.f6348b.length) {
            g();
            throw null;
        }
        System.arraycopy(bArr, 0, this.f6348b, i7, length);
        this.f6349c = i8;
    }

    public final void i(int i7) {
        int i8 = this.f6349c;
        int i9 = i8 + 1;
        if (this.f6347a) {
            f(i9);
        } else if (i9 > this.f6348b.length) {
            g();
            throw null;
        }
        this.f6348b[i8] = (byte) i7;
        this.f6349c = i9;
    }

    public final void j(int i7) {
        int i8 = this.f6349c;
        int i9 = i8 + 4;
        if (this.f6347a) {
            f(i9);
        } else if (i9 > this.f6348b.length) {
            g();
            throw null;
        }
        byte[] bArr = this.f6348b;
        bArr[i8] = (byte) i7;
        bArr[i8 + 1] = (byte) (i7 >> 8);
        bArr[i8 + 2] = (byte) (i7 >> 16);
        bArr[i8 + 3] = (byte) (i7 >> 24);
        this.f6349c = i9;
    }

    public final void k(int i7) {
        int i8 = this.f6349c;
        int i9 = i8 + 2;
        if (this.f6347a) {
            f(i9);
        } else if (i9 > this.f6348b.length) {
            g();
            throw null;
        }
        byte[] bArr = this.f6348b;
        bArr[i8] = (byte) i7;
        bArr[i8 + 1] = (byte) (i7 >> 8);
        this.f6349c = i9;
    }

    public final void l(int i7) {
        if (this.f6347a) {
            f(this.f6349c + 5);
        }
        int i8 = i7 >> 7;
        int i9 = (Integer.MIN_VALUE & i7) == 0 ? 0 : -1;
        boolean z6 = true;
        while (true) {
            int i10 = i8;
            int i11 = i7;
            i7 = i10;
            if (!z6) {
                return;
            }
            z6 = (i7 == i9 && (i7 & 1) == ((i11 >> 6) & 1)) ? false : true;
            i((byte) ((i11 & z1.c.NEG_FLOAT) | (z6 ? 128 : 0)));
            i8 = i7 >> 7;
        }
    }

    public final int m(int i7) {
        if (this.f6347a) {
            f(this.f6349c + 5);
        }
        int i8 = this.f6349c;
        while (true) {
            int i9 = i7;
            i7 >>>= 7;
            if (i7 == 0) {
                i((byte) (i9 & z1.c.NEG_FLOAT));
                return this.f6349c - i8;
            }
            i((byte) ((i9 & z1.c.NEG_FLOAT) | 128));
        }
    }

    public final void n(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("count < 0");
        }
        int i8 = this.f6349c + i7;
        if (this.f6347a) {
            f(i8);
        } else if (i8 > this.f6348b.length) {
            g();
            throw null;
        }
        Arrays.fill(this.f6348b, this.f6349c, i8, (byte) 0);
        this.f6349c = i8;
    }
}
