package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.yalantis.ucrop.UCropActivity;
import n1.z;
import t4.k;
import v4.a;
import w4.c;
import y4.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TransformImageView extends AppCompatImageView {

    /* renamed from: d, reason: collision with root package name */
    public final float[] f6201d;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f6202e;

    /* renamed from: f, reason: collision with root package name */
    public final float[] f6203f;

    /* renamed from: g, reason: collision with root package name */
    public final Matrix f6204g;

    /* renamed from: h, reason: collision with root package name */
    public int f6205h;

    /* renamed from: i, reason: collision with root package name */
    public int f6206i;

    /* renamed from: j, reason: collision with root package name */
    public d f6207j;

    /* renamed from: k, reason: collision with root package name */
    public float[] f6208k;

    /* renamed from: l, reason: collision with root package name */
    public float[] f6209l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f6210m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f6211n;

    /* renamed from: o, reason: collision with root package name */
    public int f6212o;

    /* renamed from: p, reason: collision with root package name */
    public String f6213p;

    /* renamed from: q, reason: collision with root package name */
    public String f6214q;

    /* renamed from: r, reason: collision with root package name */
    public Uri f6215r;

    /* renamed from: s, reason: collision with root package name */
    public Uri f6216s;

    /* renamed from: t, reason: collision with root package name */
    public a f6217t;

    public TransformImageView(Context context) {
        this(context, null);
    }

    public TransformImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TransformImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6201d = new float[8];
        this.f6202e = new float[2];
        this.f6203f = new float[9];
        this.f6204g = new Matrix();
        this.f6210m = false;
        this.f6211n = false;
        this.f6212o = 0;
        d();
    }

    public final float c(Matrix matrix) {
        float[] fArr = this.f6203f;
        matrix.getValues(fArr);
        double pow = Math.pow(fArr[0], 2.0d);
        matrix.getValues(fArr);
        return (float) Math.sqrt(Math.pow(fArr[3], 2.0d) + pow);
    }

    public void d() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public void e() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        String.format("Image size: [%d:%d]", Integer.valueOf((int) intrinsicWidth), Integer.valueOf((int) intrinsicHeight));
        RectF rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, intrinsicWidth, intrinsicHeight);
        this.f6208k = z.g0(rectF);
        this.f6209l = new float[]{rectF.centerX(), rectF.centerY()};
        this.f6211n = true;
        d dVar = this.f6207j;
        if (dVar != null) {
            UCropActivity uCropActivity = ((k) dVar).f9087b;
            uCropActivity.f6150k.animate().alpha(1.0f).setDuration(300L).setInterpolator(new AccelerateInterpolator());
            uCropActivity.f6162w.setClickable(false);
            uCropActivity.f6149j = false;
            uCropActivity.supportInvalidateOptionsMenu();
        }
    }

    public final void f(float f7, float f8) {
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO && f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        Matrix matrix = this.f6204g;
        matrix.postTranslate(f7, f8);
        setImageMatrix(matrix);
    }

    public float getCurrentAngle() {
        Matrix matrix = this.f6204g;
        float[] fArr = this.f6203f;
        matrix.getValues(fArr);
        double d7 = fArr[1];
        matrix.getValues(fArr);
        return (float) (-(Math.atan2(d7, fArr[0]) * 57.29577951308232d));
    }

    public float getCurrentScale() {
        return c(this.f6204g);
    }

    public a getExifInfo() {
        return this.f6217t;
    }

    public String getImageInputPath() {
        return this.f6213p;
    }

    public Uri getImageInputUri() {
        return this.f6215r;
    }

    public String getImageOutputPath() {
        return this.f6214q;
    }

    public Uri getImageOutputUri() {
        return this.f6216s;
    }

    public int getMaxBitmapSize() {
        int i7;
        if (this.f6212o <= 0) {
            WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
            Point point = new Point();
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getSize(point);
            }
            int i8 = point.x;
            int sqrt = (int) Math.sqrt(Math.pow(point.y, 2.0d) + Math.pow(i8, 2.0d));
            Canvas canvas = new Canvas();
            int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
            if (min > 0) {
                sqrt = Math.min(sqrt, min);
            }
            try {
                i7 = z.n0();
            } catch (Exception unused) {
                i7 = 0;
            }
            if (i7 > 0) {
                sqrt = Math.min(sqrt, i7);
            }
            this.f6212o = sqrt;
        }
        return this.f6212o;
    }

    public Bitmap getViewBitmap() {
        if (getDrawable() == null || !(getDrawable() instanceof x4.a)) {
            return null;
        }
        return ((x4.a) getDrawable()).f9630b;
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6 || (this.f6210m && !this.f6211n)) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            this.f6205h = width - paddingLeft;
            this.f6206i = height - paddingTop;
            e();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        setImageDrawable(new x4.a(bitmap));
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        Matrix matrix2 = this.f6204g;
        matrix2.set(matrix);
        matrix2.mapPoints(this.f6201d, this.f6208k);
        matrix2.mapPoints(this.f6202e, this.f6209l);
    }

    public void setImageUri(Uri uri, Uri uri2) {
        int maxBitmapSize = getMaxBitmapSize();
        new c(getContext(), uri, uri2, maxBitmapSize, maxBitmapSize, new v2.a(16, this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void setMaxBitmapSize(int i7) {
        this.f6212o = i7;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == ImageView.ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        }
    }

    public void setTransformImageListener(d dVar) {
        this.f6207j = dVar;
    }
}
