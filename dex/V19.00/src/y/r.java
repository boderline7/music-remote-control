package y;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.yalantis.ucrop.view.CropImageView;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public float[] f9897a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f9898b;

    /* renamed from: c, reason: collision with root package name */
    public final float[] f9899c;

    /* renamed from: d, reason: collision with root package name */
    public Path f9900d;

    /* renamed from: e, reason: collision with root package name */
    public final Paint f9901e;

    /* renamed from: f, reason: collision with root package name */
    public final Paint f9902f;

    /* renamed from: g, reason: collision with root package name */
    public final Paint f9903g;

    /* renamed from: h, reason: collision with root package name */
    public final Paint f9904h;

    /* renamed from: i, reason: collision with root package name */
    public final Paint f9905i;

    /* renamed from: j, reason: collision with root package name */
    public final float[] f9906j;

    /* renamed from: k, reason: collision with root package name */
    public int f9907k;

    /* renamed from: l, reason: collision with root package name */
    public final Rect f9908l = new Rect();

    /* renamed from: m, reason: collision with root package name */
    public final int f9909m = 1;

    /* renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MotionLayout f9910n;

    public r(MotionLayout motionLayout) {
        this.f9910n = motionLayout;
        Paint paint = new Paint();
        this.f9901e = paint;
        paint.setAntiAlias(true);
        paint.setColor(-21965);
        paint.setStrokeWidth(2.0f);
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint paint2 = new Paint();
        this.f9902f = paint2;
        paint2.setAntiAlias(true);
        paint2.setColor(-2067046);
        paint2.setStrokeWidth(2.0f);
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.f9903g = paint3;
        paint3.setAntiAlias(true);
        paint3.setColor(-13391360);
        paint3.setStrokeWidth(2.0f);
        paint3.setStyle(style);
        Paint paint4 = new Paint();
        this.f9904h = paint4;
        paint4.setAntiAlias(true);
        paint4.setColor(-13391360);
        paint4.setTextSize(motionLayout.getContext().getResources().getDisplayMetrics().density * 12.0f);
        this.f9906j = new float[8];
        Paint paint5 = new Paint();
        this.f9905i = paint5;
        paint5.setAntiAlias(true);
        paint3.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, CropImageView.DEFAULT_ASPECT_RATIO));
        this.f9899c = new float[100];
        this.f9898b = new int[50];
    }

    public final void a(Canvas canvas, int i7, int i8, n nVar) {
        int i9;
        int i10;
        Paint paint;
        float f7;
        float f8;
        int i11;
        int[] iArr = this.f9898b;
        int i12 = 4;
        if (i7 == 4) {
            boolean z6 = false;
            boolean z7 = false;
            for (int i13 = 0; i13 < this.f9907k; i13++) {
                int i14 = iArr[i13];
                if (i14 == 1) {
                    z6 = true;
                }
                if (i14 == 0) {
                    z7 = true;
                }
            }
            if (z6) {
                float[] fArr = this.f9897a;
                canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f9903g);
            }
            if (z7) {
                b(canvas);
            }
        }
        if (i7 == 2) {
            float[] fArr2 = this.f9897a;
            canvas.drawLine(fArr2[0], fArr2[1], fArr2[fArr2.length - 2], fArr2[fArr2.length - 1], this.f9903g);
        }
        if (i7 == 3) {
            b(canvas);
        }
        canvas.drawLines(this.f9897a, this.f9901e);
        View view = nVar.f9867b;
        if (view != null) {
            i9 = view.getWidth();
            i10 = nVar.f9867b.getHeight();
        } else {
            i9 = 0;
            i10 = 0;
        }
        int i15 = 1;
        while (i15 < i8 - 1) {
            if (i7 == i12 && iArr[i15 - 1] == 0) {
                i11 = i15;
            } else {
                int i16 = i15 * 2;
                float[] fArr3 = this.f9899c;
                float f9 = fArr3[i16];
                float f10 = fArr3[i16 + 1];
                this.f9900d.reset();
                this.f9900d.moveTo(f9, f10 + 10.0f);
                this.f9900d.lineTo(f9 + 10.0f, f10);
                this.f9900d.lineTo(f9, f10 - 10.0f);
                this.f9900d.lineTo(f9 - 10.0f, f10);
                this.f9900d.close();
                int i17 = i15 - 1;
                Paint paint2 = this.f9905i;
                if (i7 == i12) {
                    int i18 = iArr[i17];
                    if (i18 == 1) {
                        d(canvas, f9 - CropImageView.DEFAULT_ASPECT_RATIO, f10 - CropImageView.DEFAULT_ASPECT_RATIO);
                    } else if (i18 == 0) {
                        c(canvas, f9 - CropImageView.DEFAULT_ASPECT_RATIO, f10 - CropImageView.DEFAULT_ASPECT_RATIO);
                    } else if (i18 == 2) {
                        paint = paint2;
                        f7 = f10;
                        f8 = f9;
                        i11 = i15;
                        e(f9 - CropImageView.DEFAULT_ASPECT_RATIO, f10 - CropImageView.DEFAULT_ASPECT_RATIO, i9, i10, canvas);
                        canvas.drawPath(this.f9900d, paint);
                    }
                    paint = paint2;
                    f7 = f10;
                    f8 = f9;
                    i11 = i15;
                    canvas.drawPath(this.f9900d, paint);
                } else {
                    paint = paint2;
                    f7 = f10;
                    f8 = f9;
                    i11 = i15;
                }
                if (i7 == 2) {
                    d(canvas, f8 - CropImageView.DEFAULT_ASPECT_RATIO, f7 - CropImageView.DEFAULT_ASPECT_RATIO);
                }
                if (i7 == 3) {
                    c(canvas, f8 - CropImageView.DEFAULT_ASPECT_RATIO, f7 - CropImageView.DEFAULT_ASPECT_RATIO);
                }
                if (i7 == 6) {
                    e(f8 - CropImageView.DEFAULT_ASPECT_RATIO, f7 - CropImageView.DEFAULT_ASPECT_RATIO, i9, i10, canvas);
                }
                canvas.drawPath(this.f9900d, paint);
            }
            i15 = i11 + 1;
            i12 = 4;
        }
        float[] fArr4 = this.f9897a;
        if (fArr4.length > 1) {
            float f11 = fArr4[0];
            float f12 = fArr4[1];
            Paint paint3 = this.f9902f;
            canvas.drawCircle(f11, f12, 8.0f, paint3);
            float[] fArr5 = this.f9897a;
            canvas.drawCircle(fArr5[fArr5.length - 2], fArr5[fArr5.length - 1], 8.0f, paint3);
        }
    }

    public final void b(Canvas canvas) {
        float[] fArr = this.f9897a;
        float f7 = fArr[0];
        float f8 = fArr[1];
        float f9 = fArr[fArr.length - 2];
        float f10 = fArr[fArr.length - 1];
        float min = Math.min(f7, f9);
        float max = Math.max(f8, f10);
        float max2 = Math.max(f7, f9);
        float max3 = Math.max(f8, f10);
        Paint paint = this.f9903g;
        canvas.drawLine(min, max, max2, max3, paint);
        canvas.drawLine(Math.min(f7, f9), Math.min(f8, f10), Math.min(f7, f9), Math.max(f8, f10), paint);
    }

    public final void c(Canvas canvas, float f7, float f8) {
        float[] fArr = this.f9897a;
        float f9 = fArr[0];
        float f10 = fArr[1];
        float f11 = fArr[fArr.length - 2];
        float f12 = fArr[fArr.length - 1];
        float min = Math.min(f9, f11);
        float max = Math.max(f10, f12);
        float min2 = f7 - Math.min(f9, f11);
        float max2 = Math.max(f10, f12) - f8;
        String str = h0.FRAGMENT_ENCODE_SET + (((int) (((min2 * 100.0f) / Math.abs(f11 - f9)) + 0.5d)) / 100.0f);
        Paint paint = this.f9904h;
        paint.getTextBounds(str, 0, str.length(), this.f9908l);
        Rect rect = this.f9908l;
        canvas.drawText(str, ((min2 / 2.0f) - (rect.width() / 2)) + min, f8 - 20.0f, paint);
        float min3 = Math.min(f9, f11);
        Paint paint2 = this.f9903g;
        canvas.drawLine(f7, f8, min3, f8, paint2);
        String str2 = h0.FRAGMENT_ENCODE_SET + (((int) (((max2 * 100.0f) / Math.abs(f12 - f10)) + 0.5d)) / 100.0f);
        paint.getTextBounds(str2, 0, str2.length(), this.f9908l);
        canvas.drawText(str2, f7 + 5.0f, max - ((max2 / 2.0f) - (rect.height() / 2)), paint);
        canvas.drawLine(f7, f8, f7, Math.max(f10, f12), paint2);
    }

    public final void d(Canvas canvas, float f7, float f8) {
        float[] fArr = this.f9897a;
        float f9 = fArr[0];
        float f10 = fArr[1];
        float f11 = fArr[fArr.length - 2];
        float f12 = fArr[fArr.length - 1];
        float hypot = (float) Math.hypot(f9 - f11, f10 - f12);
        float f13 = f11 - f9;
        float f14 = f12 - f10;
        float f15 = (((f8 - f10) * f14) + ((f7 - f9) * f13)) / (hypot * hypot);
        float f16 = f9 + (f13 * f15);
        float f17 = f10 + (f15 * f14);
        Path path = new Path();
        path.moveTo(f7, f8);
        path.lineTo(f16, f17);
        float hypot2 = (float) Math.hypot(f16 - f7, f17 - f8);
        String str = h0.FRAGMENT_ENCODE_SET + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
        Paint paint = this.f9904h;
        paint.getTextBounds(str, 0, str.length(), this.f9908l);
        canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.f9908l.width() / 2), -20.0f, paint);
        canvas.drawLine(f7, f8, f16, f17, this.f9903g);
    }

    public final void e(float f7, float f8, int i7, int i8, Canvas canvas) {
        StringBuilder sb = new StringBuilder(h0.FRAGMENT_ENCODE_SET);
        MotionLayout motionLayout = this.f9910n;
        sb.append(((int) ((((f7 - (i7 / 2)) * 100.0f) / (motionLayout.getWidth() - i7)) + 0.5d)) / 100.0f);
        String sb2 = sb.toString();
        Paint paint = this.f9904h;
        paint.getTextBounds(sb2, 0, sb2.length(), this.f9908l);
        Rect rect = this.f9908l;
        canvas.drawText(sb2, ((f7 / 2.0f) - (rect.width() / 2)) + CropImageView.DEFAULT_ASPECT_RATIO, f8 - 20.0f, paint);
        float min = Math.min(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        Paint paint2 = this.f9903g;
        canvas.drawLine(f7, f8, min, f8, paint2);
        String str = h0.FRAGMENT_ENCODE_SET + (((int) ((((f8 - (i8 / 2)) * 100.0f) / (motionLayout.getHeight() - i8)) + 0.5d)) / 100.0f);
        paint.getTextBounds(str, 0, str.length(), this.f9908l);
        canvas.drawText(str, f7 + 5.0f, CropImageView.DEFAULT_ASPECT_RATIO - ((f8 / 2.0f) - (rect.height() / 2)), paint);
        canvas.drawLine(f7, f8, f7, Math.max(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f), paint2);
    }
}
