package com.navi.tool.view;

import a.a;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.activity.e;
import androidx.appcompat.app.p0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Random;
import r4.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SpectrumView extends View {

    /* renamed from: a, reason: collision with root package name */
    public Paint f6026a;

    /* renamed from: b, reason: collision with root package name */
    public int f6027b;

    /* renamed from: c, reason: collision with root package name */
    public float[] f6028c;

    /* renamed from: d, reason: collision with root package name */
    public float[] f6029d;

    /* renamed from: e, reason: collision with root package name */
    public float f6030e;

    /* renamed from: f, reason: collision with root package name */
    public float f6031f;

    /* renamed from: g, reason: collision with root package name */
    public float f6032g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f6033h;

    /* renamed from: i, reason: collision with root package name */
    public LinearGradient f6034i;

    /* renamed from: j, reason: collision with root package name */
    public final Path f6035j;

    /* renamed from: k, reason: collision with root package name */
    public final Random f6036k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f6037l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f6038m;

    /* renamed from: n, reason: collision with root package name */
    public int f6039n;

    /* renamed from: o, reason: collision with root package name */
    public i f6040o;

    /* renamed from: p, reason: collision with root package name */
    public float[] f6041p;

    /* renamed from: q, reason: collision with root package name */
    public float f6042q;

    /* renamed from: r, reason: collision with root package name */
    public final float f6043r;

    /* renamed from: s, reason: collision with root package name */
    public float f6044s;

    /* renamed from: t, reason: collision with root package name */
    public final int[] f6045t;

    public SpectrumView(Context context) {
        super(context);
        this.f6027b = 0;
        this.f6028c = new float[0];
        this.f6029d = new float[0];
        this.f6033h = false;
        this.f6035j = new Path();
        this.f6036k = new Random();
        this.f6037l = true;
        this.f6038m = false;
        this.f6039n = -1275068417;
        this.f6041p = new float[0];
        this.f6042q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6043r = 0.15f;
        this.f6044s = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6045t = new int[]{-426148609, -427972353, -428028929, -428283918, -427824701, -424087392, -420224116, -420039736, -426002433};
        d();
    }

    public SpectrumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6027b = 0;
        this.f6028c = new float[0];
        this.f6029d = new float[0];
        this.f6033h = false;
        this.f6035j = new Path();
        this.f6036k = new Random();
        this.f6037l = true;
        this.f6038m = false;
        this.f6039n = -1275068417;
        this.f6041p = new float[0];
        this.f6042q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6043r = 0.15f;
        this.f6044s = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6045t = new int[]{-426148609, -427972353, -428028929, -428283918, -427824701, -424087392, -420224116, -420039736, -426002433};
        d();
    }

    public SpectrumView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6027b = 0;
        this.f6028c = new float[0];
        this.f6029d = new float[0];
        this.f6033h = false;
        this.f6035j = new Path();
        this.f6036k = new Random();
        this.f6037l = true;
        this.f6038m = false;
        this.f6039n = -1275068417;
        this.f6041p = new float[0];
        this.f6042q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6043r = 0.15f;
        this.f6044s = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6045t = new int[]{-426148609, -427972353, -428028929, -428283918, -427824701, -424087392, -420224116, -420039736, -426002433};
        d();
    }

    public static float a(float f7) {
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        if (f7 > 1.0f) {
            return 1.0f;
        }
        return f7;
    }

    public final void b(Canvas canvas, float f7, float f8, float f9, float f10) {
        float f11 = (f7 + f9) / 2.0f;
        float min = Math.min(this.f6030e * 0.9f, (f10 - f8) / 2.0f);
        float f12 = f8 + min;
        float f13 = f10 - min;
        Path path = this.f6035j;
        path.reset();
        path.moveTo(f11, f8);
        path.lineTo(f9, f12);
        path.lineTo(f9, f13);
        path.lineTo(f11, f10);
        path.lineTo(f7, f13);
        path.lineTo(f7, f12);
        path.close();
        canvas.drawPath(path, this.f6026a);
    }

    public final void c() {
        float[] fArr = this.f6028c;
        int length = fArr.length;
        int i7 = this.f6027b;
        if (length == i7 && this.f6029d.length == i7) {
            return;
        }
        float[] fArr2 = new float[i7];
        System.arraycopy(fArr, 0, fArr2, 0, Math.min(fArr.length, i7));
        this.f6028c = fArr2;
        int i8 = this.f6027b;
        float[] fArr3 = new float[i8];
        float[] fArr4 = this.f6029d;
        System.arraycopy(fArr4, 0, fArr3, 0, Math.min(fArr4.length, i8));
        this.f6029d = fArr3;
    }

    public final void d() {
        Paint paint = new Paint();
        this.f6026a = paint;
        paint.setColor(this.f6039n);
        this.f6026a.setStyle(Paint.Style.FILL);
        this.f6026a.setAntiAlias(true);
    }

    public final void e() {
        if (this.f6033h) {
            return;
        }
        this.f6033h = true;
        invalidate();
    }

    public final void f() {
        if (this.f6033h) {
            this.f6033h = false;
            for (int i7 = 0; i7 < this.f6027b; i7++) {
                this.f6028c[i7] = 0.0f;
                this.f6029d[i7] = 0.0f;
                this.f6041p[i7] = 0.0f;
            }
            invalidate();
        }
    }

    public final void g() {
        if (!this.f6037l) {
            this.f6026a.setShader(null);
            this.f6026a.setColor(this.f6039n);
            return;
        }
        int width = getWidth();
        if (width > 0) {
            this.f6034i = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, CropImageView.DEFAULT_ASPECT_RATIO, this.f6045t, (float[]) null, Shader.TileMode.CLAMP);
        }
        this.f6026a.setShader(this.f6034i);
        this.f6026a.setColor(-1);
    }

    public int getBarCount() {
        return this.f6027b;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int height;
        float f7;
        float f8;
        super.onDraw(canvas);
        if (!this.f6033h || this.f6027b <= 0 || (height = getHeight()) <= 0) {
            return;
        }
        if (this.f6038m) {
            for (int i7 = 0; i7 < this.f6027b; i7++) {
                float a7 = a(this.f6029d[i7]);
                float[] fArr = this.f6028c;
                float f9 = fArr[i7];
                fArr[i7] = a.a(a7, f9, a7 > f9 ? 0.35f : 0.12f, f9);
            }
            float f10 = height;
            float f11 = f10 * 0.14f;
            float f12 = this.f6032g;
            for (int i8 = 0; i8 < this.f6027b; i8++) {
                float a8 = a(this.f6028c[i8]) * f10;
                if (a8 > CropImageView.DEFAULT_ASPECT_RATIO && a8 < f11) {
                    a8 = f11;
                }
                b(canvas, f12, f10 - a8, f12 + this.f6030e, f10);
                f12 += this.f6030e + this.f6031f;
            }
        } else {
            float f13 = 0.05f;
            float f14 = this.f6044s + 0.05f;
            this.f6044s = f14;
            this.f6042q += (((float) Math.sin(f14)) * 0.08f) + this.f6043r + CropImageView.DEFAULT_ASPECT_RATIO;
            int i9 = 0;
            while (true) {
                int i10 = this.f6027b;
                if (i9 >= i10) {
                    break;
                }
                float f15 = i9 / i10;
                if (f15 < 0.33f) {
                    f7 = 1.2f;
                    f8 = 8.0f;
                } else if (f15 < 0.66f) {
                    f7 = 1.0f;
                    f8 = 12.0f;
                } else {
                    f7 = 0.8f;
                    f8 = 18.0f;
                }
                double sin = (((Math.sin((this.f6042q * 3.0f) + ((f8 * 4.0f) * f15)) * 0.25d) + ((Math.sin(((2.0f * f8) * f15) - (this.f6042q * 1.5d)) * 0.5d) + Math.sin((f15 * f8) + this.f6042q))) + 1.75d) / 3.5d;
                Random random = this.f6036k;
                float nextFloat = random.nextFloat() < f13 ? random.nextFloat() * 0.4f : 0.0f;
                float f16 = height;
                float nextFloat2 = (float) ((0.5f * f16 * sin * f7) + (f16 * 0.15f) + (f16 * nextFloat) + ((random.nextFloat() - 0.5f) * 0.15f * f16));
                if (nextFloat2 < CropImageView.DEFAULT_ASPECT_RATIO) {
                    nextFloat2 = 0.0f;
                }
                if (nextFloat2 <= f16) {
                    f16 = nextFloat2;
                }
                float f17 = nextFloat > CropImageView.DEFAULT_ASPECT_RATIO ? 0.4f : 0.15f;
                float[] fArr2 = this.f6041p;
                float f18 = fArr2[i9];
                fArr2[i9] = a.a(f16, f18, f17, f18);
                i9++;
                f13 = 0.05f;
            }
            float f19 = this.f6032g;
            for (int i11 = 0; i11 < this.f6027b; i11++) {
                float f20 = height;
                b(canvas, f19, f20 - this.f6041p[i11], f19 + this.f6030e, f20);
                f19 += this.f6030e + this.f6031f;
            }
        }
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.f6030e = 4.0f * getResources().getDisplayMetrics().density;
        this.f6031f = 3.0f * getResources().getDisplayMetrics().density;
        float f7 = i7;
        int max = Math.max(1, (int) Math.floor((f7 + r7) / (this.f6030e + r7)));
        boolean z6 = this.f6027b != max;
        this.f6027b = max;
        this.f6032g = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (f7 - ((Math.max(0, max - 1) * this.f6031f) + (max * this.f6030e))) / 2.0f);
        c();
        float[] fArr = this.f6041p;
        int length = fArr.length;
        int i11 = this.f6027b;
        if (length != i11) {
            float[] fArr2 = new float[i11];
            System.arraycopy(fArr, 0, fArr2, 0, Math.min(fArr.length, i11));
            this.f6041p = fArr2;
        }
        if (i7 > 0) {
            g();
        }
        if (!z6 || this.f6040o == null) {
            return;
        }
        post(new e(20, this));
    }

    public void setColor(int i7) {
        this.f6039n = i7;
        this.f6037l = false;
        g();
        invalidate();
    }

    public void setOnBarCountReadyListener(i iVar) {
        this.f6040o = iVar;
        if (iVar == null || this.f6027b <= 0) {
            return;
        }
        post(new p0(24, this, iVar));
    }

    public void setSpectrumLevels(float[] fArr) {
        if (!this.f6038m || fArr == null || fArr.length == 0 || this.f6027b <= 0) {
            return;
        }
        c();
        int i7 = 0;
        if (fArr.length != this.f6027b) {
            while (true) {
                int i8 = this.f6027b;
                if (i7 >= i8) {
                    break;
                }
                int min = Math.min(fArr.length - 1, (int) Math.floor((i7 * fArr.length) / i8));
                int i9 = i7 + 1;
                int min2 = Math.min(fArr.length, (int) Math.ceil((i9 * fArr.length) / this.f6027b));
                if (min2 <= min) {
                    min2 = Math.min(fArr.length, min + 1);
                }
                float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
                for (int i10 = min; i10 < min2; i10++) {
                    f7 += a(fArr[i10]);
                }
                this.f6029d[i7] = a(f7 / Math.max(1, min2 - min));
                i7 = i9;
            }
        } else {
            while (i7 < this.f6027b) {
                this.f6029d[i7] = a(fArr[i7]);
                i7++;
            }
        }
        if (this.f6033h) {
            postInvalidateOnAnimation();
        }
    }

    public void setUseExternalLevels(boolean z6) {
        if (this.f6038m == z6) {
            return;
        }
        this.f6038m = z6;
        for (int i7 = 0; i7 < this.f6027b; i7++) {
            this.f6028c[i7] = 0.0f;
            this.f6029d[i7] = 0.0f;
            this.f6041p[i7] = 0.0f;
        }
        if (this.f6033h) {
            invalidate();
        }
    }

    public void setUseGradient(boolean z6) {
        this.f6037l = z6;
        g();
        invalidate();
    }
}
