package y0;

import android.util.SparseArray;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public int f9999a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final w f10000b;

    /* renamed from: c, reason: collision with root package name */
    public w f10001c;

    /* renamed from: d, reason: collision with root package name */
    public w f10002d;

    /* renamed from: e, reason: collision with root package name */
    public int f10003e;

    /* renamed from: f, reason: collision with root package name */
    public int f10004f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f10005g;

    /* renamed from: h, reason: collision with root package name */
    public final int[] f10006h;

    public r(w wVar, boolean z6, int[] iArr) {
        this.f10000b = wVar;
        this.f10001c = wVar;
        this.f10005g = z6;
        this.f10006h = iArr;
    }

    public final int a(int i7) {
        SparseArray sparseArray = this.f10001c.f10024a;
        w wVar = sparseArray == null ? null : (w) sparseArray.get(i7);
        int i8 = 1;
        int i9 = 2;
        if (this.f9999a == 2) {
            if (wVar != null) {
                this.f10001c = wVar;
                this.f10004f++;
            } else if (i7 == 65038) {
                b();
            } else if (i7 != 65039) {
                w wVar2 = this.f10001c;
                if (wVar2.f10025b != null) {
                    i9 = 3;
                    if (this.f10004f != 1) {
                        this.f10002d = wVar2;
                        b();
                    } else if (c()) {
                        this.f10002d = this.f10001c;
                        b();
                    } else {
                        b();
                    }
                } else {
                    b();
                }
            }
            i8 = i9;
        } else if (wVar == null) {
            b();
        } else {
            this.f9999a = 2;
            this.f10001c = wVar;
            this.f10004f = 1;
            i8 = i9;
        }
        this.f10003e = i7;
        return i8;
    }

    public final void b() {
        this.f9999a = 1;
        this.f10001c = this.f10000b;
        this.f10004f = 0;
    }

    public final boolean c() {
        int[] iArr;
        z0.a c7 = this.f10001c.f10025b.c();
        int a7 = c7.a(6);
        if ((a7 == 0 || c7.f10245b.get(a7 + c7.f10244a) == 0) && this.f10003e != 65039) {
            return this.f10005g && ((iArr = this.f10006h) == null || Arrays.binarySearch(iArr, this.f10001c.f10025b.a(0)) < 0);
        }
        return true;
    }
}
