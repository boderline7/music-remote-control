package s2;

import android.view.View;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public final View f8688a;

    /* renamed from: b, reason: collision with root package name */
    public int f8689b;

    /* renamed from: c, reason: collision with root package name */
    public int f8690c;

    /* renamed from: d, reason: collision with root package name */
    public int f8691d;

    public k(View view) {
        this.f8688a = view;
    }

    public final void a() {
        int i7 = this.f8691d;
        View view = this.f8688a;
        int top = i7 - (view.getTop() - this.f8689b);
        int i8 = d1.OVER_SCROLL_ALWAYS;
        view.offsetTopAndBottom(top);
        view.offsetLeftAndRight(0 - (view.getLeft() - this.f8690c));
    }

    public final boolean b(int i7) {
        if (this.f8691d == i7) {
            return false;
        }
        this.f8691d = i7;
        a();
        return true;
    }
}
