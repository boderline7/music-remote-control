package l3;

import android.animation.ObjectAnimator;
import androidx.appcompat.widget.i3;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p extends k.d {

    /* renamed from: j, reason: collision with root package name */
    public static final i3 f7398j = new i3(Float.class, "animationFraction", 15);

    /* renamed from: d, reason: collision with root package name */
    public ObjectAnimator f7399d;

    /* renamed from: e, reason: collision with root package name */
    public final c1.b f7400e;

    /* renamed from: f, reason: collision with root package name */
    public final s f7401f;

    /* renamed from: g, reason: collision with root package name */
    public int f7402g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f7403h;

    /* renamed from: i, reason: collision with root package name */
    public float f7404i;

    public p(s sVar) {
        super(3);
        this.f7402g = 1;
        this.f7401f = sVar;
        this.f7400e = new c1.b();
    }

    @Override // k.d
    public final void c() {
        ObjectAnimator objectAnimator = this.f7399d;
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
    }

    @Override // k.d
    public final void q() {
    }

    @Override // k.d
    public final void s() {
        if (this.f7399d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f7398j, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.f7399d = ofFloat;
            ofFloat.setDuration(333L);
            this.f7399d.setInterpolator(null);
            this.f7399d.setRepeatCount(-1);
            this.f7399d.addListener(new androidx.appcompat.widget.d(8, this));
        }
        v();
        this.f7399d.start();
    }

    @Override // k.d
    public final void u() {
    }

    public final void v() {
        this.f7403h = true;
        this.f7402g = 1;
        Arrays.fill((int[]) this.f7062c, b3.a.a(this.f7401f.f7345c[0], ((n) this.f7060a).f7389j));
    }
}
