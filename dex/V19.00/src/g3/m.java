package g3;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ float f6478a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ float f6479b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ float f6480c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ float f6481d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ float f6482e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ float f6483f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f6484g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Matrix f6485h;

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q f6486i;

    public m(q qVar, float f7, float f8, float f9, float f10, float f11, float f12, float f13, Matrix matrix) {
        this.f6486i = qVar;
        this.f6478a = f7;
        this.f6479b = f8;
        this.f6480c = f9;
        this.f6481d = f10;
        this.f6482e = f11;
        this.f6483f = f12;
        this.f6484g = f13;
        this.f6485h = matrix;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        q qVar = this.f6486i;
        qVar.f6515v.setAlpha(r2.a.b(this.f6478a, this.f6479b, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, floatValue));
        FloatingActionButton floatingActionButton = qVar.f6515v;
        float f7 = this.f6480c;
        float f8 = this.f6481d;
        floatingActionButton.setScaleX(r2.a.a(f7, f8, floatValue));
        qVar.f6515v.setScaleY(r2.a.a(this.f6482e, f8, floatValue));
        float f9 = this.f6483f;
        float f10 = this.f6484g;
        qVar.f6509p = r2.a.a(f9, f10, floatValue);
        float a7 = r2.a.a(f9, f10, floatValue);
        Matrix matrix = this.f6485h;
        qVar.a(a7, matrix);
        qVar.f6515v.setImageMatrix(matrix);
    }
}
