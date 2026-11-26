package l3;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.i3;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends k.d {

    /* renamed from: l, reason: collision with root package name */
    public static final int[] f7407l = {533, 567, 850, 750};

    /* renamed from: m, reason: collision with root package name */
    public static final int[] f7408m = {1267, 1000, 333, 0};

    /* renamed from: n, reason: collision with root package name */
    public static final i3 f7409n = new i3(Float.class, "animationFraction", 16);

    /* renamed from: d, reason: collision with root package name */
    public ObjectAnimator f7410d;

    /* renamed from: e, reason: collision with root package name */
    public ObjectAnimator f7411e;

    /* renamed from: f, reason: collision with root package name */
    public final Interpolator[] f7412f;

    /* renamed from: g, reason: collision with root package name */
    public final s f7413g;

    /* renamed from: h, reason: collision with root package name */
    public int f7414h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f7415i;

    /* renamed from: j, reason: collision with root package name */
    public float f7416j;

    /* renamed from: k, reason: collision with root package name */
    public o1.c f7417k;

    public r(Context context, s sVar) {
        super(2);
        this.f7414h = 0;
        this.f7417k = null;
        this.f7413g = sVar;
        this.f7412f = new Interpolator[]{AnimationUtils.loadInterpolator(context, q2.a.linear_indeterminate_line1_head_interpolator), AnimationUtils.loadInterpolator(context, q2.a.linear_indeterminate_line1_tail_interpolator), AnimationUtils.loadInterpolator(context, q2.a.linear_indeterminate_line2_head_interpolator), AnimationUtils.loadInterpolator(context, q2.a.linear_indeterminate_line2_tail_interpolator)};
    }

    @Override // k.d
    public final void c() {
        ObjectAnimator objectAnimator = this.f7410d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // k.d
    public final void o() {
        v();
    }

    @Override // k.d
    public final void p(c cVar) {
        this.f7417k = cVar;
    }

    @Override // k.d
    public final void q() {
        ObjectAnimator objectAnimator = this.f7411e;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        c();
        if (((n) this.f7060a).isVisible()) {
            this.f7411e.setFloatValues(this.f7416j, 1.0f);
            this.f7411e.setDuration((long) ((1.0f - this.f7416j) * 1800.0f));
            this.f7411e.start();
        }
    }

    @Override // k.d
    public final void s() {
        ObjectAnimator objectAnimator = this.f7410d;
        i3 i3Var = f7409n;
        if (objectAnimator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, i3Var, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.f7410d = ofFloat;
            ofFloat.setDuration(1800L);
            this.f7410d.setInterpolator(null);
            this.f7410d.setRepeatCount(-1);
            this.f7410d.addListener(new q(this, 0));
        }
        if (this.f7411e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, i3Var, 1.0f);
            this.f7411e = ofFloat2;
            ofFloat2.setDuration(1800L);
            this.f7411e.setInterpolator(null);
            this.f7411e.addListener(new q(this, 1));
        }
        v();
        this.f7410d.start();
    }

    @Override // k.d
    public final void u() {
        this.f7417k = null;
    }

    public final void v() {
        this.f7414h = 0;
        int a7 = b3.a.a(this.f7413g.f7345c[0], ((n) this.f7060a).f7389j);
        int[] iArr = (int[]) this.f7062c;
        iArr[0] = a7;
        iArr[1] = a7;
    }
}
