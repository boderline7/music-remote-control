package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j0 implements Animator.AnimatorListener {

    /* renamed from: a, reason: collision with root package name */
    public final float f2501a;

    /* renamed from: b, reason: collision with root package name */
    public final float f2502b;

    /* renamed from: c, reason: collision with root package name */
    public final float f2503c;

    /* renamed from: d, reason: collision with root package name */
    public final float f2504d;

    /* renamed from: e, reason: collision with root package name */
    public final j2 f2505e;

    /* renamed from: f, reason: collision with root package name */
    public final int f2506f;

    /* renamed from: g, reason: collision with root package name */
    public final ValueAnimator f2507g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f2508h;

    /* renamed from: i, reason: collision with root package name */
    public float f2509i;

    /* renamed from: j, reason: collision with root package name */
    public float f2510j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2511k = false;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2512l = false;

    /* renamed from: m, reason: collision with root package name */
    public float f2513m;

    /* renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2514n;

    /* renamed from: o, reason: collision with root package name */
    public final /* synthetic */ j2 f2515o;

    /* renamed from: p, reason: collision with root package name */
    public final /* synthetic */ p0 f2516p;

    public j0(p0 p0Var, j2 j2Var, int i7, float f7, float f8, float f9, float f10, int i8, j2 j2Var2) {
        this.f2516p = p0Var;
        this.f2514n = i8;
        this.f2515o = j2Var2;
        this.f2506f = i7;
        this.f2505e = j2Var;
        this.f2501a = f7;
        this.f2502b = f8;
        this.f2503c = f9;
        this.f2504d = f10;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.f2507g = ofFloat;
        ofFloat.addUpdateListener(new c0(1, this));
        ofFloat.setTarget(j2Var.itemView);
        ofFloat.addListener(this);
        this.f2513m = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void a(Animator animator) {
        if (!this.f2512l) {
            this.f2505e.setIsRecyclable(true);
        }
        this.f2512l = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f2513m = 1.0f;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        a(animator);
        if (this.f2511k) {
            return;
        }
        int i7 = this.f2514n;
        j2 j2Var = this.f2515o;
        p0 p0Var = this.f2516p;
        if (i7 <= 0) {
            p0Var.f2583m.clearView(p0Var.f2588r, j2Var);
        } else {
            p0Var.f2571a.add(j2Var.itemView);
            this.f2508h = true;
            if (i7 > 0) {
                p0Var.f2588r.post(new b.d(p0Var, this, i7, 6));
            }
        }
        View view = p0Var.f2593w;
        View view2 = j2Var.itemView;
        if (view == view2) {
            p0Var.o(view2);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final /* bridge */ /* synthetic */ void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final /* bridge */ /* synthetic */ void onAnimationStart(Animator animator) {
    }
}
