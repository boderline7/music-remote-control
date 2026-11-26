package androidx.recyclerview.widget;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t0 {

    /* renamed from: a, reason: collision with root package name */
    public c1 f2662a;

    /* renamed from: b, reason: collision with root package name */
    public int f2663b;

    /* renamed from: c, reason: collision with root package name */
    public int f2664c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2665d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2666e;

    public t0() {
        d();
    }

    public final void a() {
        this.f2664c = this.f2665d ? this.f2662a.e() : this.f2662a.f();
    }

    public final void b(int i7, View view) {
        if (this.f2665d) {
            this.f2664c = this.f2662a.h() + this.f2662a.b(view);
        } else {
            this.f2664c = this.f2662a.d(view);
        }
        this.f2663b = i7;
    }

    public final void c(int i7, View view) {
        int h7 = this.f2662a.h();
        if (h7 >= 0) {
            b(i7, view);
            return;
        }
        this.f2663b = i7;
        if (!this.f2665d) {
            int d7 = this.f2662a.d(view);
            int f7 = d7 - this.f2662a.f();
            this.f2664c = d7;
            if (f7 > 0) {
                int e7 = (this.f2662a.e() - Math.min(0, (this.f2662a.e() - h7) - this.f2662a.b(view))) - (this.f2662a.c(view) + d7);
                if (e7 < 0) {
                    this.f2664c -= Math.min(f7, -e7);
                    return;
                }
                return;
            }
            return;
        }
        int e8 = (this.f2662a.e() - h7) - this.f2662a.b(view);
        this.f2664c = this.f2662a.e() - e8;
        if (e8 > 0) {
            int c7 = this.f2664c - this.f2662a.c(view);
            int f8 = this.f2662a.f();
            int min = c7 - (Math.min(this.f2662a.d(view) - f8, 0) + f8);
            if (min < 0) {
                this.f2664c = Math.min(e8, -min) + this.f2664c;
            }
        }
    }

    public final void d() {
        this.f2663b = -1;
        this.f2664c = Integer.MIN_VALUE;
        this.f2665d = false;
        this.f2666e = false;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.f2663b + ", mCoordinate=" + this.f2664c + ", mLayoutFromEnd=" + this.f2665d + ", mValid=" + this.f2666e + '}';
    }
}
