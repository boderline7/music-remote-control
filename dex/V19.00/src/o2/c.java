package o2;

import androidx.recyclerview.widget.e0;
import java.util.Vector;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends a {
    public static final int VM_FIXEDGLOBALSIZE = 64;
    public static final int VM_GLOBALMEMADDR = 245760;
    public static final int VM_GLOBALMEMSIZE = 8192;
    public static final int VM_MEMMASK = 262143;
    public static final int VM_MEMSIZE = 262144;

    /* renamed from: f, reason: collision with root package name */
    public int f7883f;

    /* renamed from: h, reason: collision with root package name */
    public int f7885h;

    /* renamed from: i, reason: collision with root package name */
    public int f7886i;

    /* renamed from: e, reason: collision with root package name */
    public final int[] f7882e = new int[8];

    /* renamed from: g, reason: collision with root package name */
    public int f7884g = 25000000;

    /* renamed from: d, reason: collision with root package name */
    public byte[] f7881d = null;

    public static int c(a aVar) {
        int b7 = aVar.b();
        int i7 = 49152 & b7;
        if (i7 == 0) {
            aVar.a(6);
            return (b7 >>> 10) & 15;
        }
        if (i7 == 16384) {
            if ((b7 & 15360) == 0) {
                int i8 = ((b7 >>> 2) & 255) | (-256);
                aVar.a(14);
                return i8;
            }
            int i9 = (b7 >>> 6) & 255;
            aVar.a(10);
            return i9;
        }
        if (i7 == 32768) {
            aVar.a(2);
            int b8 = aVar.b();
            aVar.a(16);
            return b8;
        }
        aVar.a(2);
        int b9 = aVar.b() << 16;
        aVar.a(16);
        int b10 = b9 | aVar.b();
        aVar.a(16);
        return b10;
    }

    public static void i(Vector vector, int i7, int i8) {
        vector.set(i7, Byte.valueOf((byte) (i8 & 255)));
        vector.set(i7 + 1, Byte.valueOf((byte) ((i8 >>> 8) & 255)));
        vector.set(i7 + 2, Byte.valueOf((byte) ((i8 >>> 16) & 255)));
        vector.set(i7 + 3, Byte.valueOf((byte) ((i8 >>> 24) & 255)));
    }

    public final void d(e0 e0Var, boolean z6) {
        int b7 = b();
        if ((32768 & b7) != 0) {
            e0Var.f2437d = g.VM_OPREG;
            int i7 = (b7 >>> 12) & 7;
            e0Var.f2434a = i7;
            e0Var.f2436c = i7;
            a(4);
            return;
        }
        if ((49152 & b7) == 0) {
            e0Var.f2437d = g.VM_OPINT;
            if (z6) {
                e0Var.f2434a = (b7 >>> 6) & 255;
                a(10);
                return;
            } else {
                a(2);
                e0Var.f2434a = c(this);
                return;
            }
        }
        e0Var.f2437d = g.VM_OPREGMEM;
        if ((b7 & 8192) == 0) {
            int i8 = (b7 >>> 10) & 7;
            e0Var.f2434a = i8;
            e0Var.f2436c = i8;
            e0Var.f2435b = 0;
            a(6);
            return;
        }
        if ((b7 & 4096) == 0) {
            int i9 = (b7 >>> 9) & 7;
            e0Var.f2434a = i9;
            e0Var.f2436c = i9;
            a(7);
        } else {
            e0Var.f2434a = 0;
            a(4);
        }
        e0Var.f2435b = c(this);
    }

    public final int e(int i7, int i8, int i9) {
        int i10 = i8 / 8;
        byte[] bArr = this.f7881d;
        int i11 = ((bArr[(i10 + 1) + i7] & 255) << 8) | (bArr[i7 + i10] & 255);
        return ((((bArr[i7 + (i10 + 3)] & 255) << 24) | (i11 | ((bArr[(i10 + 2) + i7] & 255) << 16))) >>> (i8 & 7)) & ((-1) >>> (32 - i9));
    }

    public final int f(e0 e0Var) {
        if (((g) e0Var.f2437d) == g.VM_OPREGMEM) {
            return z.W0(this.f7881d, 262143 & (e0Var.f2436c + e0Var.f2435b));
        }
        return z.W0(this.f7881d, e0Var.f2436c);
    }

    public final int g(boolean z6, byte[] bArr, int i7) {
        return z6 ? this.f7881d == bArr ? bArr[i7] : bArr[i7] & 255 : this.f7881d == bArr ? z.W0(bArr, i7) : ((((((bArr[i7] & 255) << 8) | (bArr[i7 + 1] & 255)) << 8) | (bArr[i7 + 2] & 255)) << 8) | (bArr[i7 + 3] & 255);
    }

    public final void h(int i7) {
        if (i7 >= this.f7885h) {
            return;
        }
        int i8 = this.f7884g - 1;
        this.f7884g = i8;
        if (i8 <= 0) {
            return;
        }
        this.f7886i = i7;
    }

    public final void j(int i7, byte[] bArr, int i8, int i9) {
        if (i7 < 262144) {
            for (int i10 = 0; i10 < Math.min(bArr.length - i8, i9) && 262144 - i7 >= i10; i10++) {
                this.f7881d[i7 + i10] = bArr[i8 + i10];
            }
        }
    }

    public final void k(boolean z6, byte[] bArr, int i7, int i8) {
        if (z6) {
            if (this.f7881d == bArr) {
                bArr[i7] = (byte) i8;
                return;
            } else {
                byte b7 = bArr[i7];
                bArr[i7] = (byte) (i8 & 255);
                return;
            }
        }
        if (this.f7881d == bArr) {
            z.r1(bArr, i7, i8);
            return;
        }
        bArr[i7] = (byte) ((i8 >>> 24) & 255);
        bArr[i7 + 1] = (byte) ((i8 >>> 16) & 255);
        bArr[i7 + 2] = (byte) ((i8 >>> 8) & 255);
        bArr[i7 + 3] = (byte) (i8 & 255);
    }
}
