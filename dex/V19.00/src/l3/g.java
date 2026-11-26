package l3;

import android.animation.ObjectAnimator;
import androidx.appcompat.widget.i3;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends k.d {

    /* renamed from: l, reason: collision with root package name */
    public static final int[] f7355l = {0, 1350, 2700, 4050};

    /* renamed from: m, reason: collision with root package name */
    public static final int[] f7356m = {667, 2017, 3367, 4717};

    /* renamed from: n, reason: collision with root package name */
    public static final int[] f7357n = {1000, 2350, 3700, 5050};

    /* renamed from: o, reason: collision with root package name */
    public static final i3 f7358o = new i3(Float.class, "animationFraction", 12);

    /* renamed from: p, reason: collision with root package name */
    public static final i3 f7359p = new i3(Float.class, "completeEndFraction", 13);

    /* renamed from: d, reason: collision with root package name */
    public ObjectAnimator f7360d;

    /* renamed from: e, reason: collision with root package name */
    public ObjectAnimator f7361e;

    /* renamed from: f, reason: collision with root package name */
    public final c1.b f7362f;

    /* renamed from: g, reason: collision with root package name */
    public final h f7363g;

    /* renamed from: h, reason: collision with root package name */
    public int f7364h;

    /* renamed from: i, reason: collision with root package name */
    public float f7365i;

    /* renamed from: j, reason: collision with root package name */
    public float f7366j;

    /* renamed from: k, reason: collision with root package name */
    public o1.c f7367k;

    public g(h hVar) {
        super(1);
        this.f7364h = 0;
        this.f7367k = null;
        this.f7363g = hVar;
        this.f7362f = new c1.b();
    }

    @Override // k.d
    public final void c() {
        ObjectAnimator objectAnimator = this.f7360d;
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
        this.f7367k = cVar;
    }

    @Override // k.d
    public final void q() {
        ObjectAnimator objectAnimator = this.f7361e;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        if (((n) this.f7060a).isVisible()) {
            this.f7361e.start();
        } else {
            c();
        }
    }

    @Override // k.d
    public final void s() {
        if (this.f7360d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f7358o, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.f7360d = ofFloat;
            ofFloat.setDuration(5400L);
            this.f7360d.setInterpolator(null);
            this.f7360d.setRepeatCount(-1);
            this.f7360d.addListener(new f(this, 0));
        }
        if (this.f7361e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, f7359p, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.f7361e = ofFloat2;
            ofFloat2.setDuration(333L);
            this.f7361e.setInterpolator(this.f7362f);
            this.f7361e.addListener(new f(this, 1));
        }
        v();
        this.f7360d.start();
    }

    @Override // k.d
    public final void u() {
        this.f7367k = null;
    }

    public final void v() {
        this.f7364h = 0;
        ((int[]) this.f7062c)[0] = b3.a.a(this.f7363g.f7345c[0], ((n) this.f7060a).f7389j);
        this.f7366j = CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
