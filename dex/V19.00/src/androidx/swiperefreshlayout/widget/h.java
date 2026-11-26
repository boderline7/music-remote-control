package androidx.swiperefreshlayout.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h extends Animation {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2794a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2795b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ SwipeRefreshLayout f2796c;

    public h(SwipeRefreshLayout swipeRefreshLayout, int i7, int i8) {
        this.f2796c = swipeRefreshLayout;
        this.f2794a = i7;
        this.f2795b = i8;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f7, Transformation transformation) {
        this.f2796c.B.setAlpha((int) (((this.f2795b - r0) * f7) + this.f2794a));
    }
}
