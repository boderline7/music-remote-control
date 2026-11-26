package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class d1 {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    /* renamed from: a, reason: collision with root package name */
    public final r1 f2425a;

    /* renamed from: b, reason: collision with root package name */
    public int f2426b = Integer.MIN_VALUE;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f2427c = new Rect();

    public d1(r1 r1Var) {
        this.f2425a = r1Var;
    }

    public static c1 a(r1 r1Var, int i7) {
        if (i7 == 0) {
            return new c1(r1Var, 0);
        }
        int i8 = 1;
        if (i7 == 1) {
            return new c1(r1Var, i8);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public abstract int b(View view);

    public abstract int c(View view);

    public abstract int d(View view);

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public final int h() {
        if (Integer.MIN_VALUE == this.f2426b) {
            return 0;
        }
        return g() - this.f2426b;
    }

    public abstract int i(View view);

    public abstract int j(View view);

    public abstract void k(int i7);
}
