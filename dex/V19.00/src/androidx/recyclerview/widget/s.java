package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s {
    public static final int NO_POSITION = -1;

    /* renamed from: a, reason: collision with root package name */
    public final List f2636a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f2637b;

    /* renamed from: c, reason: collision with root package name */
    public final int[] f2638c;

    /* renamed from: d, reason: collision with root package name */
    public final b1 f2639d;

    /* renamed from: e, reason: collision with root package name */
    public final int f2640e;

    /* renamed from: f, reason: collision with root package name */
    public final int f2641f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f2642g;

    public s(b1 b1Var, ArrayList arrayList, int[] iArr, int[] iArr2) {
        this.f2636a = arrayList;
        this.f2637b = iArr;
        this.f2638c = iArr2;
        Arrays.fill(iArr, 0);
        Arrays.fill(iArr2, 0);
        this.f2639d = b1Var;
        Object obj = b1Var.f2385a;
        int size = ((d) obj).f2394a.size();
        this.f2640e = size;
        int size2 = ((d) obj).f2395b.size();
        this.f2641f = size2;
        this.f2642g = true;
        w wVar = arrayList.isEmpty() ? null : (w) arrayList.get(0);
        if (wVar == null || wVar.f2699a != 0 || wVar.f2700b != 0) {
            w wVar2 = new w();
            wVar2.f2699a = 0;
            wVar2.f2700b = 0;
            wVar2.f2702d = false;
            wVar2.f2701c = 0;
            wVar2.f2703e = false;
            arrayList.add(0, wVar2);
        }
        for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
            w wVar3 = (w) arrayList.get(size3);
            int i7 = wVar3.f2699a;
            int i8 = wVar3.f2701c;
            int i9 = i7 + i8;
            int i10 = wVar3.f2700b + i8;
            boolean z6 = this.f2642g;
            int[] iArr3 = this.f2638c;
            int[] iArr4 = this.f2637b;
            if (z6) {
                while (size > i9) {
                    if (iArr4[size - 1] == 0) {
                        a(size, size2, size3, false);
                    }
                    size--;
                }
                while (size2 > i10) {
                    if (iArr3[size2 - 1] == 0) {
                        a(size, size2, size3, true);
                    }
                    size2--;
                }
            }
            for (int i11 = 0; i11 < wVar3.f2701c; i11++) {
                int i12 = wVar3.f2699a + i11;
                int i13 = wVar3.f2700b + i11;
                int i14 = this.f2639d.e(i12, i13) ? 1 : 2;
                iArr4[i12] = (i13 << 5) | i14;
                iArr3[i13] = (i12 << 5) | i14;
            }
            size = wVar3.f2699a;
            size2 = wVar3.f2700b;
        }
    }

    public static u b(int i7, ArrayList arrayList, boolean z6) {
        int size = arrayList.size() - 1;
        while (size >= 0) {
            u uVar = (u) arrayList.get(size);
            if (uVar.f2672a == i7 && uVar.f2674c == z6) {
                arrayList.remove(size);
                while (size < arrayList.size()) {
                    ((u) arrayList.get(size)).f2673b += z6 ? 1 : -1;
                    size++;
                }
                return uVar;
            }
            size--;
        }
        return null;
    }

    public final void a(int i7, int i8, int i9, boolean z6) {
        int i10;
        int i11;
        int i12;
        if (z6) {
            i8--;
            i11 = i7;
            i10 = i8;
        } else {
            i10 = i7 - 1;
            i11 = i10;
        }
        while (i9 >= 0) {
            w wVar = (w) this.f2636a.get(i9);
            int i13 = wVar.f2699a;
            int i14 = wVar.f2701c;
            int i15 = i13 + i14;
            int i16 = wVar.f2700b + i14;
            int[] iArr = this.f2637b;
            int[] iArr2 = this.f2638c;
            b1 b1Var = this.f2639d;
            if (z6) {
                for (int i17 = i11 - 1; i17 >= i15; i17--) {
                    if (b1Var.f(i17, i10)) {
                        i12 = b1Var.e(i17, i10) ? 8 : 4;
                        iArr2[i10] = (i17 << 5) | 16;
                        iArr[i17] = (i10 << 5) | i12;
                        return;
                    }
                }
            } else {
                for (int i18 = i8 - 1; i18 >= i16; i18--) {
                    if (b1Var.f(i10, i18)) {
                        i12 = b1Var.e(i10, i18) ? 8 : 4;
                        int i19 = i7 - 1;
                        iArr[i19] = (i18 << 5) | 16;
                        iArr2[i18] = (i19 << 5) | i12;
                        return;
                    }
                }
            }
            i11 = wVar.f2699a;
            i8 = wVar.f2700b;
            i9--;
        }
    }
}
