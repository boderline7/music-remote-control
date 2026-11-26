package com.navi.tool.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.search.c;
import com.yalantis.ucrop.view.CropImageView;
import r4.e;
import r4.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ZoomableImageView extends AppCompatImageView {

    /* renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ int f6088r = 0;

    /* renamed from: d, reason: collision with root package name */
    public final Matrix f6089d;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f6090e;

    /* renamed from: f, reason: collision with root package name */
    public final PointF f6091f;

    /* renamed from: g, reason: collision with root package name */
    public final PointF f6092g;

    /* renamed from: h, reason: collision with root package name */
    public ScaleGestureDetector f6093h;

    /* renamed from: i, reason: collision with root package name */
    public GestureDetector f6094i;

    /* renamed from: j, reason: collision with root package name */
    public int f6095j;

    /* renamed from: k, reason: collision with root package name */
    public int f6096k;

    /* renamed from: l, reason: collision with root package name */
    public float f6097l;

    /* renamed from: m, reason: collision with root package name */
    public float f6098m;

    /* renamed from: n, reason: collision with root package name */
    public final float f6099n;

    /* renamed from: o, reason: collision with root package name */
    public float f6100o;

    /* renamed from: p, reason: collision with root package name */
    public float f6101p;

    /* renamed from: q, reason: collision with root package name */
    public int f6102q;

    public ZoomableImageView(Context context) {
        super(context);
        this.f6089d = new Matrix();
        this.f6090e = new float[9];
        this.f6091f = new PointF();
        this.f6092g = new PointF();
        this.f6097l = 1.0f;
        this.f6098m = 1.0f;
        this.f6099n = 5.0f;
        this.f6102q = 0;
        setup(context);
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6089d = new Matrix();
        this.f6090e = new float[9];
        this.f6091f = new PointF();
        this.f6092g = new PointF();
        this.f6097l = 1.0f;
        this.f6098m = 1.0f;
        this.f6099n = 5.0f;
        this.f6102q = 0;
        setup(context);
    }

    public ZoomableImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6089d = new Matrix();
        this.f6090e = new float[9];
        this.f6091f = new PointF();
        this.f6092g = new PointF();
        this.f6097l = 1.0f;
        this.f6098m = 1.0f;
        this.f6099n = 5.0f;
        this.f6102q = 0;
        setup(context);
    }

    public static float e(float f7, float f8, float f9) {
        float f10;
        float f11;
        if (f9 <= f8) {
            f11 = f8 - f9;
            f10 = 0.0f;
        } else {
            f10 = f8 - f9;
            f11 = 0.0f;
        }
        return f7 < f10 ? (-f7) + f10 : f7 > f11 ? (-f7) + f11 : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    private void setup(Context context) {
        super.setClickable(true);
        this.f6093h = new ScaleGestureDetector(context, new k(this));
        this.f6094i = new GestureDetector(context, new e(this));
        Matrix matrix = this.f6089d;
        matrix.setTranslate(1.0f, 1.0f);
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setOnTouchListener(new c(2, this));
    }

    public final void c() {
        Drawable drawable = getDrawable();
        if (drawable == null || this.f6095j == 0 || this.f6096k == 0) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float min = Math.min(this.f6095j / intrinsicWidth, this.f6096k / intrinsicHeight);
        Matrix matrix = this.f6089d;
        matrix.setScale(min, min);
        float f7 = (this.f6096k - (intrinsicHeight * min)) / 2.0f;
        float f8 = (this.f6095j - (min * intrinsicWidth)) / 2.0f;
        matrix.postTranslate(f8, f7);
        this.f6100o = this.f6095j - (f8 * 2.0f);
        this.f6101p = this.f6096k - (f7 * 2.0f);
        setImageMatrix(matrix);
        this.f6098m = 1.0f;
        if (this.f6097l < 1.0f) {
            this.f6097l = 1.0f;
        }
    }

    public final void d() {
        Matrix matrix = this.f6089d;
        float[] fArr = this.f6090e;
        matrix.getValues(fArr);
        float f7 = fArr[2];
        float f8 = fArr[5];
        float e7 = e(f7, this.f6095j, this.f6100o * this.f6097l);
        float e8 = e(f8, this.f6096k, this.f6101p * this.f6097l);
        if (e7 == CropImageView.DEFAULT_ASPECT_RATIO && e8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        matrix.postTranslate(e7, e8);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        this.f6095j = View.MeasureSpec.getSize(i7);
        this.f6096k = View.MeasureSpec.getSize(i8);
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        post(new androidx.activity.e(21, this));
    }
}
