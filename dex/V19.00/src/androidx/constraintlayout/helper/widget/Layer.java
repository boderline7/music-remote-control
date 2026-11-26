package androidx.constraintlayout.helper.widget;

import a0.f;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.yalantis.ucrop.view.CropImageView;
import u.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Layer extends ConstraintHelper {

    /* renamed from: i, reason: collision with root package name */
    public float f1529i;

    /* renamed from: j, reason: collision with root package name */
    public float f1530j;

    /* renamed from: k, reason: collision with root package name */
    public float f1531k;

    /* renamed from: l, reason: collision with root package name */
    public ConstraintLayout f1532l;

    /* renamed from: m, reason: collision with root package name */
    public float f1533m;

    /* renamed from: n, reason: collision with root package name */
    public float f1534n;

    /* renamed from: o, reason: collision with root package name */
    public float f1535o;

    /* renamed from: p, reason: collision with root package name */
    public float f1536p;

    /* renamed from: q, reason: collision with root package name */
    public float f1537q;

    /* renamed from: r, reason: collision with root package name */
    public float f1538r;

    /* renamed from: s, reason: collision with root package name */
    public float f1539s;

    /* renamed from: t, reason: collision with root package name */
    public float f1540t;

    /* renamed from: u, reason: collision with root package name */
    public final boolean f1541u;

    /* renamed from: v, reason: collision with root package name */
    public View[] f1542v;

    /* renamed from: w, reason: collision with root package name */
    public float f1543w;

    /* renamed from: x, reason: collision with root package name */
    public float f1544x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f1545y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f1546z;

    public Layer(Context context) {
        super(context);
        this.f1529i = Float.NaN;
        this.f1530j = Float.NaN;
        this.f1531k = Float.NaN;
        this.f1533m = 1.0f;
        this.f1534n = 1.0f;
        this.f1535o = Float.NaN;
        this.f1536p = Float.NaN;
        this.f1537q = Float.NaN;
        this.f1538r = Float.NaN;
        this.f1539s = Float.NaN;
        this.f1540t = Float.NaN;
        this.f1541u = true;
        this.f1542v = null;
        this.f1543w = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1544x = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1529i = Float.NaN;
        this.f1530j = Float.NaN;
        this.f1531k = Float.NaN;
        this.f1533m = 1.0f;
        this.f1534n = 1.0f;
        this.f1535o = Float.NaN;
        this.f1536p = Float.NaN;
        this.f1537q = Float.NaN;
        this.f1538r = Float.NaN;
        this.f1539s = Float.NaN;
        this.f1540t = Float.NaN;
        this.f1541u = true;
        this.f1542v = null;
        this.f1543w = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1544x = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public Layer(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1529i = Float.NaN;
        this.f1530j = Float.NaN;
        this.f1531k = Float.NaN;
        this.f1533m = 1.0f;
        this.f1534n = 1.0f;
        this.f1535o = Float.NaN;
        this.f1536p = Float.NaN;
        this.f1537q = Float.NaN;
        this.f1538r = Float.NaN;
        this.f1539s = Float.NaN;
        this.f1540t = Float.NaN;
        this.f1541u = true;
        this.f1542v = null;
        this.f1543w = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1544x = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(ConstraintLayout constraintLayout) {
        g(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_android_visibility) {
                    this.f1545y = true;
                } else if (index == t.ConstraintLayout_Layout_android_elevation) {
                    this.f1546z = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void o() {
        t();
        this.f1535o = Float.NaN;
        this.f1536p = Float.NaN;
        i iVar = ((f) getLayoutParams()).f44q0;
        iVar.Q(0);
        iVar.N(0);
        s();
        layout(((int) this.f1539s) - getPaddingLeft(), ((int) this.f1540t) - getPaddingTop(), getPaddingRight() + ((int) this.f1537q), getPaddingBottom() + ((int) this.f1538r));
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1532l = (ConstraintLayout) getParent();
        if (this.f1545y || this.f1546z) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i7 = 0; i7 < this.f1678b; i7++) {
                View c7 = this.f1532l.c(this.f1677a[i7]);
                if (c7 != null) {
                    if (this.f1545y) {
                        c7.setVisibility(visibility);
                    }
                    if (this.f1546z && elevation > CropImageView.DEFAULT_ASPECT_RATIO) {
                        c7.setTranslationZ(c7.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void p(ConstraintLayout constraintLayout) {
        this.f1532l = constraintLayout;
        float rotation = getRotation();
        if (rotation != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f1531k = rotation;
        } else {
            if (Float.isNaN(this.f1531k)) {
                return;
            }
            this.f1531k = rotation;
        }
    }

    public final void s() {
        if (this.f1532l == null) {
            return;
        }
        if (this.f1541u || Float.isNaN(this.f1535o) || Float.isNaN(this.f1536p)) {
            if (!Float.isNaN(this.f1529i) && !Float.isNaN(this.f1530j)) {
                this.f1536p = this.f1530j;
                this.f1535o = this.f1529i;
                return;
            }
            View[] k7 = k(this.f1532l);
            int left = k7[0].getLeft();
            int top = k7[0].getTop();
            int right = k7[0].getRight();
            int bottom = k7[0].getBottom();
            for (int i7 = 0; i7 < this.f1678b; i7++) {
                View view = k7[i7];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.f1537q = right;
            this.f1538r = bottom;
            this.f1539s = left;
            this.f1540t = top;
            if (Float.isNaN(this.f1529i)) {
                this.f1535o = (left + right) / 2;
            } else {
                this.f1535o = this.f1529i;
            }
            if (Float.isNaN(this.f1530j)) {
                this.f1536p = (top + bottom) / 2;
            } else {
                this.f1536p = this.f1530j;
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        f();
    }

    @Override // android.view.View
    public void setPivotX(float f7) {
        this.f1529i = f7;
        u();
    }

    @Override // android.view.View
    public void setPivotY(float f7) {
        this.f1530j = f7;
        u();
    }

    @Override // android.view.View
    public void setRotation(float f7) {
        this.f1531k = f7;
        u();
    }

    @Override // android.view.View
    public void setScaleX(float f7) {
        this.f1533m = f7;
        u();
    }

    @Override // android.view.View
    public void setScaleY(float f7) {
        this.f1534n = f7;
        u();
    }

    @Override // android.view.View
    public void setTranslationX(float f7) {
        this.f1543w = f7;
        u();
    }

    @Override // android.view.View
    public void setTranslationY(float f7) {
        this.f1544x = f7;
        u();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        f();
    }

    public final void t() {
        int i7;
        if (this.f1532l == null || (i7 = this.f1678b) == 0) {
            return;
        }
        View[] viewArr = this.f1542v;
        if (viewArr == null || viewArr.length != i7) {
            this.f1542v = new View[i7];
        }
        for (int i8 = 0; i8 < this.f1678b; i8++) {
            this.f1542v[i8] = this.f1532l.c(this.f1677a[i8]);
        }
    }

    public final void u() {
        if (this.f1532l == null) {
            return;
        }
        if (this.f1542v == null) {
            t();
        }
        s();
        double radians = Float.isNaN(this.f1531k) ? 0.0d : Math.toRadians(this.f1531k);
        float sin = (float) Math.sin(radians);
        float cos = (float) Math.cos(radians);
        float f7 = this.f1533m;
        float f8 = f7 * cos;
        float f9 = this.f1534n;
        float f10 = (-f9) * sin;
        float f11 = f7 * sin;
        float f12 = f9 * cos;
        for (int i7 = 0; i7 < this.f1678b; i7++) {
            View view = this.f1542v[i7];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f13 = right - this.f1535o;
            float f14 = bottom - this.f1536p;
            float f15 = (((f10 * f14) + (f8 * f13)) - f13) + this.f1543w;
            float f16 = (((f12 * f14) + (f13 * f11)) - f14) + this.f1544x;
            view.setTranslationX(f15);
            view.setTranslationY(f16);
            view.setScaleY(this.f1534n);
            view.setScaleX(this.f1533m);
            if (!Float.isNaN(this.f1531k)) {
                view.setRotation(this.f1531k);
            }
        }
    }
}
