package com.navi.tool.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import v.c;
import x2.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class CoverGradientBackgroundView extends View {

    /* renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ int f5906l = 0;

    /* renamed from: a, reason: collision with root package name */
    public final Paint f5907a;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f5908b;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f5909c;

    /* renamed from: d, reason: collision with root package name */
    public int[] f5910d;

    /* renamed from: e, reason: collision with root package name */
    public int[] f5911e;

    /* renamed from: f, reason: collision with root package name */
    public int[] f5912f;

    /* renamed from: g, reason: collision with root package name */
    public LinearGradient f5913g;

    /* renamed from: h, reason: collision with root package name */
    public RadialGradient f5914h;

    /* renamed from: i, reason: collision with root package name */
    public ValueAnimator f5915i;

    /* renamed from: j, reason: collision with root package name */
    public int f5916j;

    /* renamed from: k, reason: collision with root package name */
    public float f5917k;

    public CoverGradientBackgroundView(Context context) {
        super(context);
        this.f5907a = new Paint(1);
        this.f5908b = new Paint(1);
        this.f5909c = new Paint(1);
        this.f5910d = new int[]{-14338980, -15326657, -10779452};
        this.f5916j = -1;
        this.f5917k = 1.0f;
        b();
    }

    public CoverGradientBackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5907a = new Paint(1);
        this.f5908b = new Paint(1);
        this.f5909c = new Paint(1);
        this.f5910d = new int[]{-14338980, -15326657, -10779452};
        this.f5916j = -1;
        this.f5917k = 1.0f;
        b();
    }

    public CoverGradientBackgroundView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f5907a = new Paint(1);
        this.f5908b = new Paint(1);
        this.f5909c = new Paint(1);
        this.f5910d = new int[]{-14338980, -15326657, -10779452};
        this.f5916j = -1;
        this.f5917k = 1.0f;
        b();
    }

    public final void a() {
        ValueAnimator valueAnimator = this.f5915i;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f5915i = null;
        }
        this.f5911e = null;
        this.f5912f = null;
        this.f5916j = -1;
    }

    public final void b() {
        setWillNotDraw(false);
        this.f5907a.setStyle(Paint.Style.FILL);
        this.f5909c.setColor(0);
    }

    public final void c() {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0 || this.f5910d == null) {
            this.f5913g = null;
            this.f5914h = null;
            return;
        }
        float f7 = height;
        int[] iArr = this.f5910d;
        int[] iArr2 = {iArr[0], iArr[1]};
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        this.f5913g = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f7, iArr2, (float[]) null, tileMode);
        this.f5914h = new RadialGradient(width * 0.5f, f7 * 0.1f, Math.max(width, height) * 0.85f, new int[]{(this.f5910d[2] & d1.MEASURED_SIZE_MASK) | c.EXACTLY, 0}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f}, tileMode);
    }

    public int[] getColors() {
        int[] iArr = this.f5910d;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        a();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        if (this.f5913g == null) {
            c();
        }
        LinearGradient linearGradient = this.f5913g;
        if (linearGradient != null) {
            Paint paint = this.f5907a;
            paint.setShader(linearGradient);
            canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height, paint);
        }
        RadialGradient radialGradient = this.f5914h;
        if (radialGradient != null) {
            Paint paint2 = this.f5908b;
            paint2.setShader(radialGradient);
            canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height, paint2);
        }
        canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height, this.f5909c);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        if (i7 > 0 && i8 > 0) {
            float f7 = i8;
            this.f5909c.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, f7 * 0.45f, CropImageView.DEFAULT_ASPECT_RATIO, f7, 0, 805306368, Shader.TileMode.CLAMP));
        }
        c();
    }

    public void setBrightness(float f7) {
        float max = Math.max(0.5f, Math.min(1.7f, f7));
        float abs = Math.abs(max - this.f5917k);
        Paint paint = this.f5907a;
        if (abs >= 0.001f || paint.getColorFilter() == null) {
            this.f5917k = max;
            ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(new ColorMatrix(new float[]{max, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, max, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, max, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO}));
            paint.setColorFilter(colorMatrixColorFilter);
            this.f5908b.setColorFilter(colorMatrixColorFilter);
            invalidate();
        }
    }

    public void setColors(int[] iArr, boolean z6) {
        if (iArr == null || iArr.length == 0) {
            return;
        }
        int[] iArr2 = new int[3];
        for (int i7 = 0; i7 < 3; i7++) {
            iArr2[i7] = iArr[Math.min(i7, iArr.length - 1)];
        }
        if (!z6 || this.f5910d == null || getWidth() == 0 || getHeight() == 0) {
            a();
            this.f5910d = iArr2;
            c();
            invalidate();
            return;
        }
        a();
        this.f5911e = (int[]) this.f5910d.clone();
        this.f5912f = iArr2;
        this.f5916j = -1;
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 6);
        this.f5915i = ofInt;
        ofInt.setDuration(480L);
        this.f5915i.addUpdateListener(new b(6, this));
        this.f5915i.start();
    }
}
