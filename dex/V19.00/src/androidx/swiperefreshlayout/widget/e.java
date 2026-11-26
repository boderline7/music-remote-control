package androidx.swiperefreshlayout.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends Drawable implements Animatable {
    public static final int DEFAULT = 1;
    public static final int LARGE = 0;

    /* renamed from: g, reason: collision with root package name */
    public static final LinearInterpolator f2781g = new LinearInterpolator();

    /* renamed from: h, reason: collision with root package name */
    public static final c1.b f2782h = new c1.b();

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f2783i = {d1.MEASURED_STATE_MASK};

    /* renamed from: a, reason: collision with root package name */
    public final d f2784a;

    /* renamed from: b, reason: collision with root package name */
    public float f2785b;

    /* renamed from: c, reason: collision with root package name */
    public final Resources f2786c;

    /* renamed from: d, reason: collision with root package name */
    public final ValueAnimator f2787d;

    /* renamed from: e, reason: collision with root package name */
    public float f2788e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2789f;

    public e(Context context) {
        context.getClass();
        this.f2786c = context.getResources();
        d dVar = new d();
        this.f2784a = dVar;
        dVar.f2768i = f2783i;
        dVar.a(0);
        dVar.f2767h = 2.5f;
        dVar.f2761b.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        ofFloat.addUpdateListener(new b(0, this, dVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(f2781g);
        ofFloat.addListener(new c(this, dVar));
        this.f2787d = ofFloat;
    }

    public static void d(float f7, d dVar) {
        if (f7 <= 0.75f) {
            dVar.f2780u = dVar.f2768i[dVar.f2769j];
            return;
        }
        float f8 = (f7 - 0.75f) / 0.25f;
        int[] iArr = dVar.f2768i;
        int i7 = dVar.f2769j;
        int i8 = iArr[i7];
        int i9 = iArr[(i7 + 1) % iArr.length];
        dVar.f2780u = ((((i8 >> 24) & 255) + ((int) ((((i9 >> 24) & 255) - r1) * f8))) << 24) | ((((i8 >> 16) & 255) + ((int) ((((i9 >> 16) & 255) - r3) * f8))) << 16) | ((((i8 >> 8) & 255) + ((int) ((((i9 >> 8) & 255) - r4) * f8))) << 8) | ((i8 & 255) + ((int) (f8 * ((i9 & 255) - r2))));
    }

    public final void a(float f7, d dVar, boolean z6) {
        float interpolation;
        float f8;
        if (this.f2789f) {
            d(f7, dVar);
            float floor = (float) (Math.floor(dVar.f2772m / 0.8f) + 1.0d);
            float f9 = dVar.f2770k;
            float f10 = dVar.f2771l;
            dVar.f2764e = (((f10 - 0.01f) - f9) * f7) + f9;
            dVar.f2765f = f10;
            float f11 = dVar.f2772m;
            dVar.f2766g = a.a.a(floor, f11, f7, f11);
            return;
        }
        if (f7 != 1.0f || z6) {
            float f12 = dVar.f2772m;
            c1.b bVar = f2782h;
            if (f7 < 0.5f) {
                interpolation = dVar.f2770k;
                f8 = (bVar.getInterpolation(f7 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float f13 = dVar.f2770k + 0.79f;
                interpolation = f13 - (((1.0f - bVar.getInterpolation((f7 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f8 = f13;
            }
            float f14 = (0.20999998f * f7) + f12;
            float f15 = (f7 + this.f2788e) * 216.0f;
            dVar.f2764e = interpolation;
            dVar.f2765f = f8;
            dVar.f2766g = f14;
            this.f2785b = f15;
        }
    }

    public final void b(float f7, float f8, float f9, float f10) {
        float f11 = this.f2786c.getDisplayMetrics().density;
        float f12 = f8 * f11;
        d dVar = this.f2784a;
        dVar.f2767h = f12;
        dVar.f2761b.setStrokeWidth(f12);
        dVar.f2776q = f7 * f11;
        dVar.a(0);
        dVar.f2777r = (int) (f9 * f11);
        dVar.f2778s = (int) (f10 * f11);
    }

    public final void c(int i7) {
        if (i7 == 0) {
            b(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            b(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f2785b, bounds.exactCenterX(), bounds.exactCenterY());
        d dVar = this.f2784a;
        RectF rectF = dVar.f2760a;
        float f7 = dVar.f2776q;
        float f8 = (dVar.f2767h / 2.0f) + f7;
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            f8 = (Math.min(bounds.width(), bounds.height()) / 2.0f) - Math.max((dVar.f2777r * dVar.f2775p) / 2.0f, dVar.f2767h / 2.0f);
        }
        rectF.set(bounds.centerX() - f8, bounds.centerY() - f8, bounds.centerX() + f8, bounds.centerY() + f8);
        float f9 = dVar.f2764e;
        float f10 = dVar.f2766g;
        float f11 = (f9 + f10) * 360.0f;
        float f12 = ((dVar.f2765f + f10) * 360.0f) - f11;
        Paint paint = dVar.f2761b;
        paint.setColor(dVar.f2780u);
        paint.setAlpha(dVar.f2779t);
        float f13 = dVar.f2767h / 2.0f;
        rectF.inset(f13, f13);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, dVar.f2763d);
        float f14 = -f13;
        rectF.inset(f14, f14);
        canvas.drawArc(rectF, f11, f12, false, paint);
        if (dVar.f2773n) {
            Path path = dVar.f2774o;
            if (path == null) {
                Path path2 = new Path();
                dVar.f2774o = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
            float f15 = (dVar.f2777r * dVar.f2775p) / 2.0f;
            dVar.f2774o.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            dVar.f2774o.lineTo(dVar.f2777r * dVar.f2775p, CropImageView.DEFAULT_ASPECT_RATIO);
            Path path3 = dVar.f2774o;
            float f16 = dVar.f2777r;
            float f17 = dVar.f2775p;
            path3.lineTo((f16 * f17) / 2.0f, dVar.f2778s * f17);
            dVar.f2774o.offset((rectF.centerX() + min) - f15, (dVar.f2767h / 2.0f) + rectF.centerY());
            dVar.f2774o.close();
            Paint paint2 = dVar.f2762c;
            paint2.setColor(dVar.f2780u);
            paint2.setAlpha(dVar.f2779t);
            canvas.save();
            canvas.rotate(f11 + f12, rectF.centerX(), rectF.centerY());
            canvas.drawPath(dVar.f2774o, paint2);
            canvas.restore();
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f2784a.f2779t;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.f2787d.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f2784a.f2779t = i7;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f2784a.f2761b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.f2787d.cancel();
        d dVar = this.f2784a;
        float f7 = dVar.f2764e;
        dVar.f2770k = f7;
        float f8 = dVar.f2765f;
        dVar.f2771l = f8;
        dVar.f2772m = dVar.f2766g;
        if (f8 != f7) {
            this.f2789f = true;
            this.f2787d.setDuration(666L);
            this.f2787d.start();
            return;
        }
        dVar.a(0);
        dVar.f2770k = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2771l = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2772m = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2764e = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2765f = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2766g = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f2787d.setDuration(1332L);
        this.f2787d.start();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.f2787d.cancel();
        this.f2785b = CropImageView.DEFAULT_ASPECT_RATIO;
        d dVar = this.f2784a;
        if (dVar.f2773n) {
            dVar.f2773n = false;
        }
        dVar.a(0);
        dVar.f2770k = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2771l = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2772m = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2764e = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2765f = CropImageView.DEFAULT_ASPECT_RATIO;
        dVar.f2766g = CropImageView.DEFAULT_ASPECT_RATIO;
        invalidateSelf();
    }
}
