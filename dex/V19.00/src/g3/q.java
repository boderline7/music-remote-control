package g3;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;
import n1.z;
import p3.a0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class q {
    public static final TimeInterpolator C = r2.a.FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final int D = q2.c.motionDurationLong2;
    public static final int E = q2.c.motionEasingEmphasizedInterpolator;
    public static final int F = q2.c.motionDurationMedium1;
    public static final int G = q2.c.motionEasingEmphasizedAccelerateInterpolator;
    public static final int[] H = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] I = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    public static final int[] J = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] K = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] L = {R.attr.state_enabled};
    public static final int[] M = new int[0];
    public c0.f B;

    /* renamed from: a, reason: collision with root package name */
    public p3.p f6494a;

    /* renamed from: b, reason: collision with root package name */
    public p3.j f6495b;

    /* renamed from: c, reason: collision with root package name */
    public Drawable f6496c;

    /* renamed from: d, reason: collision with root package name */
    public b f6497d;

    /* renamed from: e, reason: collision with root package name */
    public LayerDrawable f6498e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f6499f;

    /* renamed from: h, reason: collision with root package name */
    public float f6501h;

    /* renamed from: i, reason: collision with root package name */
    public float f6502i;

    /* renamed from: j, reason: collision with root package name */
    public float f6503j;

    /* renamed from: k, reason: collision with root package name */
    public int f6504k;

    /* renamed from: l, reason: collision with root package name */
    public Animator f6505l;

    /* renamed from: m, reason: collision with root package name */
    public r2.e f6506m;

    /* renamed from: n, reason: collision with root package name */
    public r2.e f6507n;

    /* renamed from: o, reason: collision with root package name */
    public float f6508o;

    /* renamed from: q, reason: collision with root package name */
    public int f6510q;

    /* renamed from: s, reason: collision with root package name */
    public ArrayList f6512s;

    /* renamed from: t, reason: collision with root package name */
    public ArrayList f6513t;

    /* renamed from: u, reason: collision with root package name */
    public ArrayList f6514u;

    /* renamed from: v, reason: collision with root package name */
    public final FloatingActionButton f6515v;

    /* renamed from: w, reason: collision with root package name */
    public final v2.a f6516w;

    /* renamed from: g, reason: collision with root package name */
    public boolean f6500g = true;

    /* renamed from: p, reason: collision with root package name */
    public float f6509p = 1.0f;

    /* renamed from: r, reason: collision with root package name */
    public int f6511r = 0;

    /* renamed from: x, reason: collision with root package name */
    public final Rect f6517x = new Rect();

    /* renamed from: y, reason: collision with root package name */
    public final RectF f6518y = new RectF();

    /* renamed from: z, reason: collision with root package name */
    public final RectF f6519z = new RectF();
    public final Matrix A = new Matrix();

    public q(FloatingActionButton floatingActionButton, v2.a aVar) {
        int i7 = 1;
        this.f6515v = floatingActionButton;
        this.f6516w = aVar;
        j.h hVar = new j.h(8);
        s sVar = (s) this;
        hVar.k(H, d(new o(sVar, 2)));
        hVar.k(I, d(new o(sVar, i7)));
        hVar.k(J, d(new o(sVar, i7)));
        hVar.k(K, d(new o(sVar, i7)));
        hVar.k(L, d(new o(sVar, 3)));
        hVar.k(M, d(new o(sVar, 0)));
        this.f6508o = floatingActionButton.getRotation();
    }

    public static ValueAnimator d(o oVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(C);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(oVar);
        valueAnimator.addUpdateListener(oVar);
        valueAnimator.setFloatValues(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        return valueAnimator;
    }

    public final void a(float f7, Matrix matrix) {
        matrix.reset();
        if (this.f6515v.getDrawable() == null || this.f6510q == 0) {
            return;
        }
        RectF rectF = this.f6518y;
        RectF rectF2 = this.f6519z;
        rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i7 = this.f6510q;
        rectF2.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7, i7);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i8 = this.f6510q;
        matrix.postScale(f7, f7, i8 / 2.0f, i8 / 2.0f);
    }

    public final AnimatorSet b(r2.e eVar, float f7, float f8, float f9) {
        ArrayList arrayList = new ArrayList();
        Property property = View.ALPHA;
        float[] fArr = {f7};
        FloatingActionButton floatingActionButton = this.f6515v;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) property, fArr);
        eVar.f("opacity").a(ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_X, f8);
        eVar.f("scale").a(ofFloat2);
        int i7 = Build.VERSION.SDK_INT;
        if (i7 == 26) {
            n nVar = new n();
            nVar.f6487a = new FloatEvaluator();
            ofFloat2.setEvaluator(nVar);
        }
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_Y, f8);
        eVar.f("scale").a(ofFloat3);
        if (i7 == 26) {
            n nVar2 = new n();
            nVar2.f6487a = new FloatEvaluator();
            ofFloat3.setEvaluator(nVar2);
        }
        arrayList.add(ofFloat3);
        Matrix matrix = this.A;
        a(f9, matrix);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(floatingActionButton, new n1.b(), new l(this), new Matrix(matrix));
        eVar.f("iconScale").a(ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        z.S0(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet c(float f7, float f8, float f9, int i7, int i8) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        FloatingActionButton floatingActionButton = this.f6515v;
        ofFloat.addUpdateListener(new m(this, floatingActionButton.getAlpha(), f7, floatingActionButton.getScaleX(), f8, floatingActionButton.getScaleY(), this.f6509p, f9, new Matrix(this.A)));
        arrayList.add(ofFloat);
        z.S0(animatorSet, arrayList);
        animatorSet.setDuration(l6.f.u0(floatingActionButton.getContext(), i7, floatingActionButton.getContext().getResources().getInteger(q2.h.material_motion_duration_long_1)));
        animatorSet.setInterpolator(l6.f.v0(floatingActionButton.getContext(), i8, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    public abstract float e();

    public void f(Rect rect) {
        int max = this.f6499f ? Math.max((this.f6504k - this.f6515v.getSizeDimension()) / 2, 0) : 0;
        int max2 = Math.max(max, (int) Math.ceil(this.f6500g ? e() + this.f6503j : CropImageView.DEFAULT_ASPECT_RATIO));
        int max3 = Math.max(max, (int) Math.ceil(r0 * 1.5f));
        rect.set(max2, max3, max2, max3);
    }

    public abstract void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i7);

    public abstract void h();

    public abstract void i();

    public abstract void j(int[] iArr);

    public abstract void k(float f7, float f8, float f9);

    public final void l() {
        ArrayList arrayList = this.f6514u;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i iVar = (i) it.next();
                com.google.android.material.bottomappbar.b bVar = iVar.f6465a;
                bVar.getClass();
                BottomAppBar bottomAppBar = bVar.f3359a;
                p3.j jVar = bottomAppBar.V;
                FloatingActionButton floatingActionButton = iVar.f6466b;
                jVar.p((floatingActionButton.getVisibility() == 0 && bottomAppBar.f3333d0 == 1) ? floatingActionButton.getScaleY() : CropImageView.DEFAULT_ASPECT_RATIO);
            }
        }
    }

    public final void m() {
        com.google.android.material.bottomappbar.i topEdgeTreatment;
        com.google.android.material.bottomappbar.i topEdgeTreatment2;
        com.google.android.material.bottomappbar.i topEdgeTreatment3;
        com.google.android.material.bottomappbar.i topEdgeTreatment4;
        ArrayList arrayList = this.f6514u;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i iVar = (i) it.next();
                com.google.android.material.bottomappbar.b bVar = iVar.f6465a;
                bVar.getClass();
                BottomAppBar bottomAppBar = bVar.f3359a;
                if (bottomAppBar.f3333d0 == 1) {
                    FloatingActionButton floatingActionButton = iVar.f6466b;
                    float translationX = floatingActionButton.getTranslationX();
                    topEdgeTreatment = bottomAppBar.getTopEdgeTreatment();
                    float f7 = topEdgeTreatment.f3377i;
                    p3.j jVar = bottomAppBar.V;
                    if (f7 != translationX) {
                        topEdgeTreatment4 = bottomAppBar.getTopEdgeTreatment();
                        topEdgeTreatment4.f3377i = translationX;
                        jVar.invalidateSelf();
                    }
                    float f8 = -floatingActionButton.getTranslationY();
                    float f9 = CropImageView.DEFAULT_ASPECT_RATIO;
                    float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f8);
                    topEdgeTreatment2 = bottomAppBar.getTopEdgeTreatment();
                    if (topEdgeTreatment2.f3376h != max) {
                        topEdgeTreatment3 = bottomAppBar.getTopEdgeTreatment();
                        topEdgeTreatment3.N(max);
                        jVar.invalidateSelf();
                    }
                    if (floatingActionButton.getVisibility() == 0) {
                        f9 = floatingActionButton.getScaleY();
                    }
                    jVar.p(f9);
                }
            }
        }
    }

    public void n(ColorStateList colorStateList) {
        Drawable drawable = this.f6496c;
        if (drawable != null) {
            j0.b.h(drawable, n3.a.c(colorStateList));
        }
    }

    public final void o(p3.p pVar) {
        this.f6494a = pVar;
        p3.j jVar = this.f6495b;
        if (jVar != null) {
            jVar.setShapeAppearanceModel(pVar);
        }
        Object obj = this.f6496c;
        if (obj instanceof a0) {
            ((a0) obj).setShapeAppearanceModel(pVar);
        }
        b bVar = this.f6497d;
        if (bVar != null) {
            bVar.f6453o = pVar;
            bVar.invalidateSelf();
        }
    }

    public abstract boolean p();

    public abstract void q();

    public final void r() {
        Rect rect = this.f6517x;
        f(rect);
        z.v(this.f6498e, "Didn't initialize content background");
        boolean p6 = p();
        v2.a aVar = this.f6516w;
        if (p6) {
            super/*android.view.View*/.setBackgroundDrawable(new InsetDrawable((Drawable) this.f6498e, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            LayerDrawable layerDrawable = this.f6498e;
            if (layerDrawable != null) {
                super/*android.view.View*/.setBackgroundDrawable(layerDrawable);
            } else {
                aVar.getClass();
            }
        }
        int i7 = rect.left;
        int i8 = rect.top;
        int i9 = rect.right;
        int i10 = rect.bottom;
        ((FloatingActionButton) aVar.f9388b).f3733l.set(i7, i8, i9, i10);
        FloatingActionButton floatingActionButton = (FloatingActionButton) aVar.f9388b;
        int i11 = floatingActionButton.f3730i;
        floatingActionButton.setPadding(i7 + i11, i8 + i11, i9 + i11, i10 + i11);
    }
}
