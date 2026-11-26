package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f implements Animation.AnimationListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1943a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1944b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1945c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1946d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1947e;

    public f(View view, ViewGroup viewGroup, i iVar, l lVar) {
        this.f1947e = lVar;
        this.f1944b = viewGroup;
        this.f1945c = view;
        this.f1946d = iVar;
    }

    public f(ViewGroup viewGroup, z zVar, p0 p0Var, l0.d dVar) {
        this.f1944b = viewGroup;
        this.f1945c = zVar;
        this.f1946d = p0Var;
        this.f1947e = dVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        int i7 = this.f1943a;
        ViewGroup viewGroup = this.f1944b;
        switch (i7) {
            case 0:
                viewGroup.post(new e0(1, this));
                break;
            default:
                viewGroup.post(new e0(0, this));
                break;
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
