package x2;

import android.R;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.card.MaterialCardView;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;
import p0.d1;
import p0.m0;
import p3.e;
import p3.j;
import p3.n;
import p3.p;
import q2.g;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {
    public static final ColorDrawable A;
    public static final int DEFAULT_FADE_ANIM_DURATION = 300;

    /* renamed from: z, reason: collision with root package name */
    public static final double f9585z = Math.cos(Math.toRadians(45.0d));

    /* renamed from: a, reason: collision with root package name */
    public final MaterialCardView f9586a;

    /* renamed from: b, reason: collision with root package name */
    public final Rect f9587b;

    /* renamed from: c, reason: collision with root package name */
    public final j f9588c;

    /* renamed from: d, reason: collision with root package name */
    public final j f9589d;

    /* renamed from: e, reason: collision with root package name */
    public int f9590e;

    /* renamed from: f, reason: collision with root package name */
    public int f9591f;

    /* renamed from: g, reason: collision with root package name */
    public int f9592g;

    /* renamed from: h, reason: collision with root package name */
    public int f9593h;

    /* renamed from: i, reason: collision with root package name */
    public Drawable f9594i;

    /* renamed from: j, reason: collision with root package name */
    public Drawable f9595j;

    /* renamed from: k, reason: collision with root package name */
    public ColorStateList f9596k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f9597l;

    /* renamed from: m, reason: collision with root package name */
    public p f9598m;

    /* renamed from: n, reason: collision with root package name */
    public ColorStateList f9599n;

    /* renamed from: o, reason: collision with root package name */
    public Drawable f9600o;

    /* renamed from: p, reason: collision with root package name */
    public LayerDrawable f9601p;

    /* renamed from: q, reason: collision with root package name */
    public j f9602q;

    /* renamed from: r, reason: collision with root package name */
    public j f9603r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f9604s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f9605t;

    /* renamed from: u, reason: collision with root package name */
    public ValueAnimator f9606u;

    /* renamed from: v, reason: collision with root package name */
    public final TimeInterpolator f9607v;

    /* renamed from: w, reason: collision with root package name */
    public final int f9608w;

    /* renamed from: x, reason: collision with root package name */
    public final int f9609x;

    /* renamed from: y, reason: collision with root package name */
    public float f9610y;

    static {
        A = Build.VERSION.SDK_INT <= 28 ? new ColorDrawable() : null;
    }

    public d(MaterialCardView materialCardView, AttributeSet attributeSet, int i7) {
        int i8 = MaterialCardView.f3484o;
        this.f9587b = new Rect();
        this.f9604s = false;
        this.f9610y = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9586a = materialCardView;
        j jVar = new j(materialCardView.getContext(), attributeSet, i7, i8);
        this.f9588c = jVar;
        jVar.l(materialCardView.getContext());
        jVar.r();
        n g7 = jVar.f8263a.f8241a.g();
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, m.CardView, i7, l.CardView);
        if (obtainStyledAttributes.hasValue(m.CardView_cardCornerRadius)) {
            g7.c(obtainStyledAttributes.getDimension(m.CardView_cardCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO));
        }
        this.f9589d = new j();
        h(g7.a());
        this.f9607v = f.v0(materialCardView.getContext(), q2.c.motionEasingLinearInterpolator, r2.a.LINEAR_INTERPOLATOR);
        this.f9608w = f.u0(materialCardView.getContext(), q2.c.motionDurationShort2, DEFAULT_FADE_ANIM_DURATION);
        this.f9609x = f.u0(materialCardView.getContext(), q2.c.motionDurationShort1, DEFAULT_FADE_ANIM_DURATION);
        obtainStyledAttributes.recycle();
    }

    public static float b(f fVar, float f7) {
        return fVar instanceof p3.m ? (float) ((1.0d - f9585z) * f7) : fVar instanceof e ? f7 / 2.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final float a() {
        f fVar = this.f9598m.f8301a;
        j jVar = this.f9588c;
        return Math.max(Math.max(b(fVar, jVar.j()), b(this.f9598m.f8302b, jVar.f8263a.f8241a.f8306f.a(jVar.h()))), Math.max(b(this.f9598m.f8303c, jVar.f8263a.f8241a.f8307g.a(jVar.h())), b(this.f9598m.f8304d, jVar.f8263a.f8241a.f8308h.a(jVar.h()))));
    }

    public final LayerDrawable c() {
        Drawable drawable;
        if (this.f9600o == null) {
            if (n3.a.USE_FRAMEWORK_RIPPLE) {
                this.f9603r = new j(this.f9598m);
                drawable = new RippleDrawable(this.f9596k, null, this.f9603r);
            } else {
                StateListDrawable stateListDrawable = new StateListDrawable();
                j jVar = new j(this.f9598m);
                this.f9602q = jVar;
                jVar.o(this.f9596k);
                stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.f9602q);
                drawable = stateListDrawable;
            }
            this.f9600o = drawable;
        }
        if (this.f9601p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.f9600o, this.f9589d, this.f9595j});
            this.f9601p = layerDrawable;
            layerDrawable.setId(2, g.mtrl_card_checked_layer_id);
        }
        return this.f9601p;
    }

    public final c d(Drawable drawable) {
        int i7;
        int i8;
        MaterialCardView materialCardView = this.f9586a;
        if (materialCardView.getUseCompatPadding()) {
            float maxCardElevation = materialCardView.getMaxCardElevation() * 1.5f;
            boolean i9 = i();
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
            int ceil = (int) Math.ceil(maxCardElevation + (i9 ? a() : 0.0f));
            float maxCardElevation2 = materialCardView.getMaxCardElevation();
            if (i()) {
                f7 = a();
            }
            i7 = (int) Math.ceil(maxCardElevation2 + f7);
            i8 = ceil;
        } else {
            i7 = 0;
            i8 = 0;
        }
        return new c(drawable, i7, i8, i7, i8);
    }

    public final void e(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        if (this.f9601p != null) {
            MaterialCardView materialCardView = this.f9586a;
            if (materialCardView.getUseCompatPadding()) {
                float maxCardElevation = materialCardView.getMaxCardElevation() * 1.5f;
                boolean i13 = i();
                float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
                i9 = (int) Math.ceil((maxCardElevation + (i13 ? a() : 0.0f)) * 2.0f);
                float maxCardElevation2 = materialCardView.getMaxCardElevation();
                if (i()) {
                    f7 = a();
                }
                i10 = (int) Math.ceil((maxCardElevation2 + f7) * 2.0f);
            } else {
                i9 = 0;
                i10 = 0;
            }
            int i14 = this.f9592g;
            int i15 = (i14 & 8388613) == 8388613 ? ((i7 - this.f9590e) - this.f9591f) - i10 : this.f9590e;
            int i16 = (i14 & 80) == 80 ? this.f9590e : ((i8 - this.f9590e) - this.f9591f) - i9;
            int i17 = (i14 & 8388613) == 8388613 ? this.f9590e : ((i7 - this.f9590e) - this.f9591f) - i10;
            int i18 = (i14 & 80) == 80 ? ((i8 - this.f9590e) - this.f9591f) - i9 : this.f9590e;
            int i19 = d1.OVER_SCROLL_ALWAYS;
            if (m0.d(materialCardView) == 1) {
                i12 = i17;
                i11 = i15;
            } else {
                i11 = i17;
                i12 = i15;
            }
            this.f9601p.setLayerInset(2, i12, i18, i11, i16);
        }
    }

    public final void f(boolean z6, boolean z7) {
        Drawable drawable = this.f9595j;
        if (drawable != null) {
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
            if (!z7) {
                drawable.setAlpha(z6 ? 255 : 0);
                if (z6) {
                    f7 = 1.0f;
                }
                this.f9610y = f7;
                return;
            }
            if (z6) {
                f7 = 1.0f;
            }
            float f8 = z6 ? 1.0f - this.f9610y : this.f9610y;
            ValueAnimator valueAnimator = this.f9606u;
            if (valueAnimator != null) {
                valueAnimator.cancel();
                this.f9606u = null;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f9610y, f7);
            this.f9606u = ofFloat;
            ofFloat.addUpdateListener(new b(0, this));
            this.f9606u.setInterpolator(this.f9607v);
            this.f9606u.setDuration((long) ((z6 ? this.f9608w : this.f9609x) * f8));
            this.f9606u.start();
        }
    }

    public final void g(Drawable drawable) {
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            this.f9595j = mutate;
            j0.b.h(mutate, this.f9597l);
            f(this.f9586a.isChecked(), false);
        } else {
            this.f9595j = A;
        }
        LayerDrawable layerDrawable = this.f9601p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(g.mtrl_card_checked_layer_id, this.f9595j);
        }
    }

    public final void h(p pVar) {
        this.f9598m = pVar;
        j jVar = this.f9588c;
        jVar.setShapeAppearanceModel(pVar);
        jVar.f8285w = !jVar.m();
        j jVar2 = this.f9589d;
        if (jVar2 != null) {
            jVar2.setShapeAppearanceModel(pVar);
        }
        j jVar3 = this.f9603r;
        if (jVar3 != null) {
            jVar3.setShapeAppearanceModel(pVar);
        }
        j jVar4 = this.f9602q;
        if (jVar4 != null) {
            jVar4.setShapeAppearanceModel(pVar);
        }
    }

    public final boolean i() {
        MaterialCardView materialCardView = this.f9586a;
        return materialCardView.getPreventCornerOverlap() && this.f9588c.m() && materialCardView.getUseCompatPadding();
    }

    public final boolean j() {
        View view = this.f9586a;
        if (view.isClickable()) {
            return true;
        }
        while (view.isDuplicateParentStateEnabled() && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        return view.isClickable();
    }

    public final void k() {
        Drawable drawable = this.f9594i;
        Drawable c7 = j() ? c() : this.f9589d;
        this.f9594i = c7;
        if (drawable != c7) {
            MaterialCardView materialCardView = this.f9586a;
            if (materialCardView.getForeground() instanceof InsetDrawable) {
                ((InsetDrawable) materialCardView.getForeground()).setDrawable(c7);
            } else {
                materialCardView.setForeground(d(c7));
            }
        }
    }

    public final void l() {
        MaterialCardView materialCardView = this.f9586a;
        boolean z6 = materialCardView.getPreventCornerOverlap() && !this.f9588c.m();
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float a7 = (z6 || i()) ? a() : 0.0f;
        if (materialCardView.getPreventCornerOverlap() && materialCardView.getUseCompatPadding()) {
            f7 = (float) ((1.0d - f9585z) * materialCardView.getCardViewRadius());
        }
        int i7 = (int) (a7 - f7);
        Rect rect = this.f9587b;
        materialCardView.e(rect.left + i7, rect.top + i7, rect.right + i7, rect.bottom + i7);
    }

    public final void m() {
        boolean z6 = this.f9604s;
        MaterialCardView materialCardView = this.f9586a;
        if (!z6) {
            materialCardView.setBackgroundInternal(d(this.f9588c));
        }
        materialCardView.setForeground(d(this.f9594i));
    }
}
