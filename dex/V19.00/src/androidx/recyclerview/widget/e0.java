package androidx.recyclerview.widget;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e0 {

    /* renamed from: a, reason: collision with root package name */
    public int f2434a;

    /* renamed from: b, reason: collision with root package name */
    public int f2435b;

    /* renamed from: c, reason: collision with root package name */
    public int f2436c;

    /* renamed from: d, reason: collision with root package name */
    public Serializable f2437d;

    /* JADX WARN: Type inference failed for: r0v2, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [int[], java.io.Serializable] */
    public final void a(int i7, int i8) {
        if (i7 < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        int i9 = this.f2436c;
        int i10 = i9 * 2;
        Object obj = this.f2437d;
        if (((int[]) obj) == null) {
            ?? r02 = new int[4];
            this.f2437d = r02;
            Arrays.fill((int[]) r02, -1);
        } else if (i10 >= ((int[]) obj).length) {
            int[] iArr = (int[]) obj;
            ?? r03 = new int[i9 * 4];
            this.f2437d = r03;
            System.arraycopy(iArr, 0, (int[]) r03, 0, iArr.length);
        }
        Object obj2 = this.f2437d;
        ((int[]) obj2)[i10] = i7;
        ((int[]) obj2)[i10 + 1] = i8;
        this.f2436c++;
    }

    public final void b(RecyclerView recyclerView, boolean z6) {
        this.f2436c = 0;
        int[] iArr = (int[]) this.f2437d;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        r1 r1Var = recyclerView.f2321m;
        if (recyclerView.f2319l == null || r1Var == null || !r1Var.f2629i) {
            return;
        }
        if (z6) {
            if (!recyclerView.f2303d.g()) {
                r1Var.j(recyclerView.f2319l.getItemCount(), this);
            }
        } else if (!recyclerView.N()) {
            r1Var.i(this.f2434a, this.f2435b, recyclerView.f2308f0, this);
        }
        int i7 = this.f2436c;
        if (i7 > r1Var.f2630j) {
            r1Var.f2630j = i7;
            r1Var.f2631k = z6;
            recyclerView.f2299b.k();
        }
    }

    public final androidx.appcompat.app.n c() {
        return ((androidx.appcompat.app.n[]) this.f2437d)[this.f2434a];
    }

    public final byte[][] d(int i7, int i8) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.f2435b * i8, this.f2436c * i7);
        int i9 = this.f2435b * i8;
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = (i9 - i10) - 1;
            byte[] bArr2 = (byte[]) ((androidx.appcompat.app.n[]) this.f2437d)[i10 / i8].f831c;
            int length = bArr2.length * i7;
            byte[] bArr3 = new byte[length];
            for (int i12 = 0; i12 < length; i12++) {
                bArr3[i12] = bArr2[i12 / i7];
            }
            bArr[i11] = bArr3;
        }
        return bArr;
    }

    public final boolean e(int i7) {
        if (((int[]) this.f2437d) != null) {
            int i8 = this.f2436c * 2;
            for (int i9 = 0; i9 < i8; i9 += 2) {
                if (((int[]) this.f2437d)[i9] == i7) {
                    return true;
                }
            }
        }
        return false;
    }
}
