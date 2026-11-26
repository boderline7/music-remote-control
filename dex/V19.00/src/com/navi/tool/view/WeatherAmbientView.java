package com.navi.tool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import n1.z;
import q0.i;
import r4.a;
import r4.c;
import r4.j;
import t1.f;
import x0.b;
import x2.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class WeatherAmbientView extends View {
    public static final int WEATHER_CLOUDY = 4;
    public static final int WEATHER_DRIZZLE = 6;
    public static final int WEATHER_FOG = 3;
    public static final int WEATHER_HAIL = 8;
    public static final int WEATHER_HAZE = 9;
    public static final int WEATHER_NONE = 0;
    public static final int WEATHER_RAIN = 1;
    public static final int WEATHER_SAND = 10;
    public static final int WEATHER_SHOWER = 7;
    public static final int WEATHER_SLEET = 5;
    public static final int WEATHER_SNOW = 2;

    /* renamed from: o0, reason: collision with root package name */
    public static final float[] f6046o0 = {0.2f, 0.4f, 0.6f, 0.8f};

    /* renamed from: p0, reason: collision with root package name */
    public static final int[] f6047p0 = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, i.ACTION_COLLAPSE};
    public final float[] A;
    public final float[] B;
    public final float[] C;
    public final float[] D;
    public final float[] E;
    public final Shader[] F;
    public boolean G;
    public int H;
    public int I;
    public int J;
    public float K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public int P;
    public int Q;
    public float R;
    public final ArrayList S;
    public final float[] T;
    public float U;
    public final int V;
    public int W;

    /* renamed from: a, reason: collision with root package name */
    public float f6048a;

    /* renamed from: a0, reason: collision with root package name */
    public int f6049a0;

    /* renamed from: b, reason: collision with root package name */
    public final float f6050b;

    /* renamed from: b0, reason: collision with root package name */
    public final Calendar f6051b0;

    /* renamed from: c, reason: collision with root package name */
    public final Random f6052c;

    /* renamed from: c0, reason: collision with root package name */
    public long f6053c0;

    /* renamed from: d, reason: collision with root package name */
    public final float[] f6054d;

    /* renamed from: d0, reason: collision with root package name */
    public int f6055d0;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f6056e;

    /* renamed from: e0, reason: collision with root package name */
    public boolean f6057e0;

    /* renamed from: f, reason: collision with root package name */
    public final float[] f6058f;

    /* renamed from: f0, reason: collision with root package name */
    public float f6059f0;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f6060g;

    /* renamed from: g0, reason: collision with root package name */
    public float f6061g0;

    /* renamed from: h, reason: collision with root package name */
    public final float[] f6062h;

    /* renamed from: h0, reason: collision with root package name */
    public float f6063h0;

    /* renamed from: i, reason: collision with root package name */
    public final float[] f6064i;

    /* renamed from: i0, reason: collision with root package name */
    public boolean f6065i0;

    /* renamed from: j, reason: collision with root package name */
    public final Paint f6066j;

    /* renamed from: j0, reason: collision with root package name */
    public long f6067j0;

    /* renamed from: k, reason: collision with root package name */
    public final Paint f6068k;

    /* renamed from: k0, reason: collision with root package name */
    public long f6069k0;

    /* renamed from: l, reason: collision with root package name */
    public final Paint f6070l;

    /* renamed from: l0, reason: collision with root package name */
    public long f6071l0;

    /* renamed from: m, reason: collision with root package name */
    public final Paint f6072m;

    /* renamed from: m0, reason: collision with root package name */
    public int f6073m0;

    /* renamed from: n, reason: collision with root package name */
    public final Paint f6074n;

    /* renamed from: n0, reason: collision with root package name */
    public final b f6075n0;

    /* renamed from: o, reason: collision with root package name */
    public final Paint f6076o;

    /* renamed from: p, reason: collision with root package name */
    public final Paint f6077p;

    /* renamed from: q, reason: collision with root package name */
    public final Paint f6078q;

    /* renamed from: r, reason: collision with root package name */
    public final Paint f6079r;

    /* renamed from: s, reason: collision with root package name */
    public final Path f6080s;

    /* renamed from: t, reason: collision with root package name */
    public final Rect f6081t;

    /* renamed from: u, reason: collision with root package name */
    public final RectF f6082u;

    /* renamed from: v, reason: collision with root package name */
    public LinearGradient f6083v;

    /* renamed from: w, reason: collision with root package name */
    public float f6084w;

    /* renamed from: x, reason: collision with root package name */
    public final Paint f6085x;

    /* renamed from: y, reason: collision with root package name */
    public final Paint f6086y;

    /* renamed from: z, reason: collision with root package name */
    public final Paint f6087z;

    public WeatherAmbientView(Context context) {
        this(context, null);
    }

    public WeatherAmbientView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6048a = 2.0f;
        this.f6052c = new Random();
        this.f6054d = new float[420];
        this.f6056e = new float[420];
        this.f6058f = new float[420];
        this.f6060g = new float[420];
        this.f6062h = new float[420];
        this.f6064i = new float[420];
        Paint paint = new Paint(1);
        this.f6066j = paint;
        Paint paint2 = new Paint(1);
        this.f6068k = paint2;
        Paint paint3 = new Paint(1);
        this.f6070l = paint3;
        Paint paint4 = new Paint(1);
        this.f6072m = paint4;
        Paint paint5 = new Paint(3);
        this.f6074n = paint5;
        Paint paint6 = new Paint(1);
        this.f6076o = paint6;
        Paint paint7 = new Paint(1);
        this.f6077p = paint7;
        Paint paint8 = new Paint(1);
        this.f6078q = paint8;
        this.f6079r = new Paint(1);
        this.f6080s = new Path();
        new RectF();
        this.f6081t = new Rect();
        this.f6082u = new RectF();
        this.f6085x = new Paint(1);
        this.f6086y = new Paint(1);
        this.f6087z = new Paint(1);
        this.A = new float[6];
        this.B = new float[6];
        this.C = new float[6];
        this.D = new float[6];
        this.E = new float[6];
        this.F = new Shader[6];
        int i7 = 0;
        this.J = 0;
        this.K = 0.5f;
        this.N = true;
        this.O = true;
        this.R = 1.0f;
        this.S = new ArrayList(64);
        this.T = new float[70];
        this.U = 1.5f;
        this.V = -1;
        this.f6049a0 = -1;
        this.f6051b0 = Calendar.getInstance();
        this.f6053c0 = -1L;
        this.f6055d0 = -1;
        this.f6063h0 = 5.0f;
        this.f6069k0 = 33L;
        this.f6075n0 = new b(2, this);
        this.f6050b = getResources().getDisplayMetrics().density;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        Paint.Style style2 = Paint.Style.FILL;
        paint2.setStyle(style2);
        paint3.setStyle(style2);
        paint4.setStyle(style2);
        paint6.setStyle(style);
        paint6.setStrokeCap(cap);
        paint6.setStrokeJoin(Paint.Join.ROUND);
        paint7.setTextAlign(Paint.Align.CENTER);
        paint7.setFakeBoldText(true);
        paint8.setStyle(style2);
        paint5.setFilterBitmap(true);
        setClickable(false);
        setFocusable(false);
        while (true) {
            float[] fArr = this.T;
            if (i7 >= fArr.length) {
                z.b0();
                return;
            } else {
                fArr[i7] = -9999.0f;
                i7++;
            }
        }
    }

    public static float e(j jVar) {
        if (jVar.f8578i >= 9000.0f) {
            return 1.0f;
        }
        return Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(Math.min(1.0f, jVar.f8579j / 0.6f), Math.min(1.0f, jVar.f8577h / 1.0f)));
    }

    public static String g(int i7) {
        return (i7 <= 0 || i7 > 20) ? "自动（跟随系统日期）" : a.a(f6047p0[i7 - 1]);
    }

    public final int a() {
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.S;
            if (i7 >= arrayList.size()) {
                return i8;
            }
            if (r4.b.f8522e[((j) arrayList.get(i7)).f8570a] == 9) {
                i8++;
            }
            i7++;
        }
    }

    public final j b(int i7, int i8, int i9) {
        j jVar = new j();
        jVar.f8570a = i7;
        Random random = this.f6052c;
        jVar.f8580k = random.nextFloat() * 6.283f;
        jVar.f8579j = random.nextFloat() * 2.0f;
        float f7 = r4.b.f8520c[i7];
        float f8 = this.f6050b;
        jVar.f8575f = a.a.b(random, 0.28f, 0.86f, f7 * f8 * this.f6048a);
        jVar.f8576g = (random.nextFloat() * 0.32f) + 0.62f;
        int i10 = r4.b.f8522e[i7];
        float f9 = r4.b.f8521d[i7] * f8;
        switch (i10) {
            case 0:
                int i11 = random.nextBoolean() ? 1 : -1;
                jVar.f8571b = i11 > 0 ? (-jVar.f8575f) * 3.0f : i8 + (jVar.f8575f * 3.0f);
                jVar.f8572c = a.a.b(random, 0.44f, 0.06f, i9);
                jVar.f8573d = a.a.b(random, 0.2f, 0.9f, i11 * f9);
                jVar.f8574e = (random.nextFloat() - 0.5f) * f9 * 0.12f;
                jVar.f8577h = 30.0f;
                break;
            case 1:
                jVar.f8571b = a.a.b(random, 0.88f, 0.06f, i8);
                jVar.f8572c = (jVar.f8575f * 2.5f) + i9;
                jVar.f8573d = (random.nextFloat() - 0.5f) * 8.0f * f8;
                jVar.f8574e = a.a.b(random, 0.3f, 0.85f, -f9);
                jVar.f8577h = 40.0f;
                break;
            case 2:
                jVar.f8571b = random.nextFloat() * i8;
                jVar.f8572c = (-jVar.f8575f) * 2.5f;
                jVar.f8573d = (random.nextFloat() - 0.5f) * f9 * 0.5f;
                jVar.f8574e = a.a.b(random, 0.4f, 0.8f, f9);
                jVar.f8583n = (random.nextFloat() - 0.5f) * 3.2f;
                jVar.f8577h = 34.0f;
                break;
            case 3:
                jVar.f8571b = a.a.b(random, 0.8f, 0.1f, i8);
                jVar.f8572c = a.a.b(random, 0.55f, 0.12f, i9);
                jVar.f8573d = (random.nextFloat() - 0.5f) * 10.0f * f8;
                jVar.f8574e = (random.nextFloat() - 0.5f) * 8.0f * f8;
                jVar.f8577h = 36.0f;
                break;
            case 4:
                jVar.f8571b = (random.nextBoolean() ? 1 : -1) > 0 ? a.a.b(random, 0.45f, 0.55f, i8) : a.a.b(random, 0.45f, CropImageView.DEFAULT_ASPECT_RATIO, i8);
                jVar.f8572c = (-jVar.f8575f) * 2.0f;
                jVar.f8573d = a.a.b(random, 0.4f, 0.7f, (-r1) * f9);
                jVar.f8574e = a.a.b(random, 0.35f, 0.55f, f9);
                jVar.f8577h = Math.max(2.5f, (i8 + i9) / Math.max(1.0f, f9 * 1.2f));
                break;
            case 5:
                jVar.f8571b = a.a.b(random, 0.64f, 0.18f, i8);
                jVar.f8572c = a.a.b(random, 0.34f, 0.1f, i9);
                jVar.f8573d = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8577h = 2.8f;
                jVar.f8576g = 0.95f;
                break;
            case 6:
                jVar.f8584o = a.a.b(random, 0.5f, 0.25f, i8);
                jVar.f8585p = a.a.b(random, 0.3f, 0.12f, i9);
                jVar.f8586q = a.a.b(random, 0.16f, 0.12f, Math.min(i8, i9));
                jVar.f8587r = random.nextFloat() * 6.283f;
                jVar.f8573d = random.nextBoolean() ? 1.0f : -1.0f;
                jVar.f8571b = (((float) Math.cos(jVar.f8587r)) * jVar.f8586q) + jVar.f8584o;
                jVar.f8572c = a.a.A((float) Math.sin(jVar.f8587r), jVar.f8586q, 0.42f, jVar.f8585p);
                jVar.f8577h = 34.0f;
                break;
            case 7:
                jVar.f8571b = a.a.b(random, 0.76f, 0.12f, i8);
                jVar.f8572c = a.a.b(random, 0.6f, 0.1f, i9);
                jVar.f8573d = (random.nextFloat() - 0.5f) * f9;
                jVar.f8574e = (random.nextFloat() - 0.5f) * f9 * 0.7f;
                jVar.f8577h = 30.0f;
                break;
            case 8:
                int i12 = random.nextBoolean() ? 1 : -1;
                jVar.f8571b = i12 > 0 ? (-jVar.f8575f) * 3.0f : i8 + (jVar.f8575f * 3.0f);
                jVar.f8572c = a.a.b(random, 0.32f, 0.12f, i9);
                jVar.f8573d = i12 * f9;
                jVar.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8577h = 30.0f;
                break;
            case 9:
                if (a() >= 3) {
                    return null;
                }
                int f10 = f();
                String a7 = i7 == 44 ? c.a(f10) : c.b(f10);
                if (a7.isEmpty() && this.f6057e0) {
                    a7 = i7 == 44 ? "12:00" : r4.b.f8518a[i7];
                }
                if (a7.isEmpty()) {
                    return null;
                }
                if (i7 == 44) {
                    jVar.f8571b = i8 * 0.5f;
                    jVar.f8572c = i9 * 0.2f;
                    jVar.f8576g = 0.94f;
                } else {
                    jVar.f8571b = i8 * f6046o0[random.nextInt(4)];
                    jVar.f8572c = a.a.b(random, 0.26f, 0.14f, i9);
                    jVar.f8576g = 0.85f;
                }
                jVar.f8573d = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8577h = 9.0f;
                jVar.f8591v = a7;
                break;
                break;
            case 10:
                jVar.f8571b = a.a.b(random, 0.6f, 0.2f, i8);
                jVar.f8572c = i9 * 0.95f;
                jVar.f8573d = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                jVar.f8577h = 26.0f;
                break;
            case 11:
                if (i7 == 49) {
                    jVar.f8571b = i8 * 0.5f;
                    jVar.f8572c = i9 * 0.42f;
                    jVar.f8573d = CropImageView.DEFAULT_ASPECT_RATIO;
                    jVar.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                    jVar.f8577h = 10.0f;
                    jVar.f8576g = 0.85f;
                    jVar.f8589t = new float[72];
                    float f11 = jVar.f8575f * 0.16f;
                    for (int i13 = 0; i13 < 36; i13++) {
                        double d7 = (i13 / 36) * 6.2832f;
                        float pow = ((float) Math.pow(Math.sin(d7), 3.0d)) * 16.0f;
                        float f12 = -((((((float) Math.cos(d7)) * 13.0f) - (((float) Math.cos(r5 * 2.0f)) * 5.0f)) - (((float) Math.cos(r5 * 3.0f)) * 2.0f)) - ((float) Math.cos(r5 * 4.0f)));
                        float[] fArr = jVar.f8589t;
                        int i14 = i13 * 2;
                        fArr[i14] = (pow * f11) + jVar.f8571b;
                        fArr[i14 + 1] = (f12 * f11) + jVar.f8572c;
                    }
                    jVar.f8590u = 36;
                    break;
                } else {
                    int i15 = random.nextBoolean() ? 1 : -1;
                    jVar.f8571b = i15 > 0 ? (-jVar.f8575f) * 2.0f : i8 + (jVar.f8575f * 2.0f);
                    jVar.f8572c = a.a.b(random, 0.26f, 0.16f, i9);
                    jVar.f8573d = i15 * f9 * 0.55f;
                    jVar.f8574e = (random.nextFloat() - 0.5f) * f9 * 0.15f;
                    jVar.f8577h = 30.0f;
                    jVar.f8589t = new float[48];
                    jVar.f8590u = 0;
                    break;
                }
            default:
                return null;
        }
        jVar.f8578i = jVar.f8577h;
        return jVar;
    }

    public final void c(Canvas canvas, float f7) {
        Paint paint = this.f6066j;
        paint.setStrokeWidth(this.f6050b * 1.6f);
        paint.setColor(-6301441);
        for (int i7 = 0; i7 < this.P; i7++) {
            paint.setAlpha((int) (this.f6062h[i7] * 255.0f * f7));
            float f8 = this.f6054d[i7];
            float f9 = this.f6056e[i7];
            float f10 = this.f6060g[i7];
            canvas.drawLine(f8, f9, f8 - (0.18f * f10), f9 + f10, paint);
        }
    }

    public final void d(Canvas canvas, Bitmap bitmap, j jVar) {
        int i7 = r4.b.f8519b[jVar.f8570a];
        int i8 = (i7 % 8) * 64;
        int i9 = (i7 / 8) * 64;
        Rect rect = this.f6081t;
        rect.set(i8, i9, i8 + 64, i9 + 64);
        float f7 = jVar.f8575f * 1.0f;
        int e7 = (int) (jVar.f8576g * e(jVar) * 255.0f);
        int i10 = 2;
        if (e7 <= 2) {
            return;
        }
        boolean z6 = r4.b.f8530m[jVar.f8570a];
        Paint paint = this.f6074n;
        RectF rectF = this.f6082u;
        if (z6) {
            paint.setAlpha(Math.min(255, e7 / 3));
            float f8 = jVar.f8571b;
            float f9 = 1.55f * f7;
            float f10 = jVar.f8572c;
            rectF.set(f8 - f9, f10 - f9, f8 + f9, f10 + f9);
            canvas.drawBitmap(bitmap, rect, rectF, paint);
        }
        paint.setAlpha(e7);
        if (jVar.f8582m) {
            canvas.save();
            canvas.translate(jVar.f8571b, jVar.f8572c);
            canvas.scale(-1.0f, 1.0f);
            canvas.rotate((float) Math.toDegrees(jVar.f8581l));
            float f11 = -f7;
            rectF.set(f11, f11, f7, f7);
            canvas.drawBitmap(bitmap, rect, rectF, paint);
            canvas.restore();
        } else {
            float f12 = jVar.f8571b;
            float f13 = jVar.f8572c;
            rectF.set(f12 - f7, f13 - f7, f12 + f7, f13 + f7);
            if (jVar.f8581l != CropImageView.DEFAULT_ASPECT_RATIO) {
                canvas.save();
                canvas.rotate((float) Math.toDegrees(jVar.f8581l), jVar.f8571b, jVar.f8572c);
                canvas.drawBitmap(bitmap, rect, rectF, paint);
                canvas.restore();
            } else {
                canvas.drawBitmap(bitmap, rect, rectF, paint);
            }
        }
        int i11 = jVar.f8570a;
        Paint paint2 = this.f6079r;
        if (i11 == 10 || i11 == 35) {
            float f14 = jVar.f8572c;
            float f15 = jVar.f8575f;
            float f16 = f14 - (0.36f * f15);
            float f17 = f15 * 0.62f;
            int e8 = (int) (e(jVar) * jVar.f8576g * 195.0f);
            if (e8 <= 4) {
                return;
            }
            paint2.setColor(-14273992);
            paint2.setStrokeWidth(Math.max(1.2f, ((this.f6050b * 2.2f) * jVar.f8575f) / 40.0f));
            paint2.setAlpha(e8);
            canvas.save();
            canvas.rotate((float) Math.toDegrees(jVar.f8579j * 8.0f), jVar.f8571b, f16);
            float f18 = jVar.f8571b;
            canvas.drawLine(f18 - f17, f16, f18 + f17, f16, paint2);
            canvas.rotate(90.0f, jVar.f8571b, f16);
            float f19 = jVar.f8571b;
            float f20 = f17 * 0.45f;
            canvas.drawLine(f19 - f20, f16, f19 + f20, f16, paint2);
            canvas.restore();
            canvas.drawCircle(jVar.f8571b, f16, Math.max(1.2f, jVar.f8575f * 0.05f), paint2);
            return;
        }
        if (i11 == 27 || i11 == 30) {
            float f21 = jVar.f8575f / 32.0f;
            float f22 = f21 * 13.0f;
            float f23 = f21 * 9.0f;
            float max = Math.max(4.0f, 5.0f * f21);
            int e9 = (int) (e(jVar) * jVar.f8576g * 190.0f);
            if (e9 <= 4) {
                return;
            }
            paint2.setColor(-5194043);
            paint2.setStrokeWidth(Math.max(1.0f, f21 * 1.4f));
            paint2.setAlpha(e9);
            float degrees = (float) Math.toDegrees(jVar.f8579j * 22.0f);
            int i12 = 0;
            while (i12 < 4) {
                float f24 = jVar.f8571b + ((i12 & 1) == 0 ? -f22 : f22);
                float f25 = jVar.f8572c + (i12 < i10 ? -f23 : f23);
                canvas.save();
                canvas.rotate(degrees, f24, f25);
                float f26 = f24 - max;
                float f27 = f24 + max;
                canvas.drawLine(f26, f25, f27, f25, paint2);
                canvas.rotate(90.0f, f24, f25);
                canvas.drawLine(f26, f25, f27, f25, paint2);
                canvas.restore();
                i12++;
                degrees = degrees;
                i10 = 2;
            }
        }
    }

    public final int f() {
        int i7 = this.f6055d0;
        if (i7 >= 0) {
            return i7;
        }
        long currentTimeMillis = System.currentTimeMillis() / f.T_MS_MINUTE;
        if (currentTimeMillis == this.f6053c0) {
            return this.W;
        }
        this.f6053c0 = currentTimeMillis;
        long currentTimeMillis2 = System.currentTimeMillis();
        Calendar calendar = this.f6051b0;
        calendar.setTimeInMillis(currentTimeMillis2);
        int i8 = calendar.get(6) + (calendar.get(1) * 1000);
        if (i8 != this.f6049a0) {
            this.f6049a0 = i8;
            this.W = a.b();
        }
        return this.W;
    }

    public float getAmbientScale() {
        return this.f6048a;
    }

    public String getFestivalLabel() {
        return a.a(f());
    }

    public int getFestivalOption() {
        if (this.f6055d0 < 0) {
            return 0;
        }
        for (int i7 = 0; i7 < 20; i7++) {
            if (f6047p0[i7] == this.f6055d0) {
                return i7 + 1;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float h(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.view.WeatherAmbientView.h(int, int):float");
    }

    public final void i() {
        int m6 = m(this.K, this.J);
        this.Q = m6;
        this.P = m6;
        int max = Math.max(1, this.H);
        int max2 = Math.max(1, this.I);
        for (int i7 = 0; i7 < this.P; i7++) {
            j(i7, max, max2, true);
        }
    }

    public final void j(int i7, int i8, int i9, boolean z6) {
        float f7;
        float f8;
        Random random = this.f6052c;
        float f9 = i8;
        float nextFloat = random.nextFloat() * f9 * 1.15f;
        float[] fArr = this.f6054d;
        fArr[i7] = nextFloat;
        int i10 = this.J;
        float[] fArr2 = this.f6064i;
        float[] fArr3 = this.f6062h;
        float[] fArr4 = this.f6060g;
        float[] fArr5 = this.f6058f;
        float[] fArr6 = this.f6056e;
        float f10 = this.f6050b;
        if (i10 == 2) {
            float nextFloat2 = random.nextFloat();
            if (z6) {
                f7 = i9;
            } else {
                nextFloat2 = (-nextFloat2) * i9;
                f7 = 0.15f;
            }
            fArr6[i7] = nextFloat2 * f7;
            fArr5[i7] = a.a.b(random, 34.0f, 18.0f, f10);
            fArr4[i7] = a.a.b(random, 3.2f, 2.4f, f10);
            fArr3[i7] = (random.nextFloat() * 0.45f) + 0.45f;
            fArr2[i7] = random.nextFloat() * 6.283f;
        }
        if (i10 == 5) {
            if ((i7 & 1) != 1) {
                float nextFloat3 = random.nextFloat();
                fArr6[i7] = z6 ? nextFloat3 * i9 : (-nextFloat3) * i9 * 0.2f;
                fArr5[i7] = a.a.b(random, 460.0f, 400.0f, f10);
                fArr4[i7] = a.a.b(random, 28.0f, 20.0f, f10);
                fArr3[i7] = (random.nextFloat() * 0.26f) + 0.18f;
                fArr2[i7] = 0.0f;
                return;
            }
            float nextFloat4 = random.nextFloat();
            if (z6) {
                f8 = i9;
            } else {
                nextFloat4 = (-nextFloat4) * i9;
                f8 = 0.15f;
            }
            fArr6[i7] = nextFloat4 * f8;
            fArr5[i7] = a.a.b(random, 34.0f, 20.0f, f10);
            fArr4[i7] = a.a.b(random, 3.2f, 2.4f, f10);
            fArr3[i7] = (random.nextFloat() * 0.45f) + 0.45f;
            fArr2[i7] = random.nextFloat() * 6.283f;
            return;
        }
        if (i10 == 6) {
            float nextFloat5 = random.nextFloat();
            fArr6[i7] = z6 ? nextFloat5 * i9 : (-nextFloat5) * i9 * 0.1f;
            fArr5[i7] = a.a.b(random, 70.0f, 90.0f, f10);
            fArr4[i7] = a.a.b(random, 6.0f, 5.0f, f10);
            fArr3[i7] = (random.nextFloat() * 0.18f) + 0.12f;
            fArr2[i7] = 0.0f;
            return;
        }
        switch (i10) {
            case 8:
                float nextFloat6 = random.nextFloat();
                fArr6[i7] = z6 ? nextFloat6 * i9 : (-nextFloat6) * i9 * 0.2f;
                fArr5[i7] = a.a.b(random, 420.0f, 900.0f, f10);
                fArr4[i7] = a.a.b(random, 1.6f, 1.6f, f10);
                fArr3[i7] = (random.nextFloat() * 0.35f) + 0.55f;
                fArr2[i7] = random.nextFloat() * 6.283f;
                break;
            case 9:
                fArr[i7] = random.nextFloat() * f9;
                fArr6[i7] = random.nextFloat() * i9;
                fArr5[i7] = a.a.b(random, 12.0f, 5.0f, f10);
                fArr4[i7] = a.a.b(random, 1.6f, 1.0f, f10);
                fArr3[i7] = (random.nextFloat() * 0.12f) + 0.06f;
                fArr2[i7] = random.nextFloat() * 6.283f;
                break;
            case 10:
                fArr[i7] = z6 ? random.nextFloat() * f9 * 1.3f : (random.nextFloat() * f9 * 0.35f) + f9;
                fArr6[i7] = random.nextFloat() * i9;
                fArr5[i7] = a.a.b(random, 380.0f, 260.0f, f10);
                fArr4[i7] = a.a.b(random, 18.0f, 8.0f, f10);
                fArr3[i7] = (random.nextFloat() * 0.22f) + 0.1f;
                fArr2[i7] = random.nextFloat() * 6.283f;
                break;
            default:
                float nextFloat7 = random.nextFloat();
                fArr6[i7] = z6 ? nextFloat7 * i9 : (-nextFloat7) * i9 * 0.2f;
                fArr5[i7] = a.a.b(random, 520.0f, 420.0f, f10);
                fArr4[i7] = a.a.b(random, 32.0f, 20.0f, f10);
                fArr3[i7] = (random.nextFloat() * 0.3f) + 0.2f;
                fArr2[i7] = 0.0f;
                break;
        }
    }

    public final void k(int i7, int i8) {
        int i9;
        int i10;
        WeatherAmbientView weatherAmbientView = this;
        int i11 = i7;
        int f7 = f();
        float f8 = 0.0f;
        int i12 = 0;
        while (true) {
            i9 = weatherAmbientView.V;
            if (i12 >= 70) {
                break;
            }
            if (i9 < 0 || i12 == i9) {
                f8 += weatherAmbientView.h(i12, f7);
            }
            i12++;
        }
        if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        Random random = weatherAmbientView.f6052c;
        float nextFloat = random.nextFloat() * f8;
        int i13 = 0;
        while (true) {
            if (i13 >= 70) {
                i13 = -1;
                break;
            }
            if (i9 < 0 || i13 == i9) {
                float h7 = weatherAmbientView.h(i13, f7);
                if (h7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                    nextFloat -= h7;
                    if (nextFloat <= CropImageView.DEFAULT_ASPECT_RATIO) {
                        break;
                    }
                } else {
                    continue;
                }
            }
            i13++;
        }
        if (i13 < 0) {
            return;
        }
        weatherAmbientView.T[i13] = weatherAmbientView.f6059f0;
        int[] iArr = r4.b.f8522e;
        int i14 = iArr[i13];
        int max = Math.max(1, r4.b.f8529l[i13]);
        ArrayList arrayList = weatherAmbientView.S;
        int size = 32 - arrayList.size();
        if (max > size) {
            max = Math.max(1, size);
        }
        if (i14 != 8 && i14 != 0 && i14 != 7 && i14 != 4) {
            for (int i15 = 0; i15 < max; i15++) {
                j b7 = weatherAmbientView.b(i13, i11, i8);
                if (b7 != null) {
                    arrayList.add(b7);
                }
            }
            return;
        }
        int i16 = iArr[i13];
        float f9 = r4.b.f8520c[i13];
        float f10 = weatherAmbientView.f6050b;
        float f11 = f9 * f10 * weatherAmbientView.f6048a;
        float f12 = r4.b.f8521d[i13] * f10;
        if (i16 == 8) {
            i10 = random.nextBoolean() ? 1 : -1;
            float b8 = a.a.b(random, 0.32f, 0.12f, i8);
            float f13 = i10 > 0 ? (-f11) * 2.0f : i11 + (2.0f * f11);
            int min = Math.min(max, 6);
            for (int i17 = 0; i17 < min; i17++) {
                j b9 = weatherAmbientView.b(i13, i11, i8);
                if (b9 == null) {
                    return;
                }
                b9.f8571b = f13 - (((i10 * i17) * f11) * 1.3f);
                b9.f8572c = a.a.A((float) Math.sin(i17 * 0.9f), f11, 0.5f, b8);
                b9.f8573d = i10 * f12;
                b9.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                b9.f8588s = i17;
                b9.f8577h = 30.0f;
                b9.f8578i = 30.0f;
                arrayList.add(b9);
            }
            return;
        }
        if (i16 == 7) {
            for (int i18 = 0; i18 < max; i18++) {
                j b10 = weatherAmbientView.b(i13, i11, i8);
                if (b10 == null) {
                    return;
                }
                arrayList.add(b10);
            }
            return;
        }
        i10 = random.nextBoolean() ? 1 : -1;
        float b11 = a.a.b(random, 0.4f, 0.08f, i8);
        float f14 = i10 > 0 ? (-f11) * 3.0f : i11 + (3.0f * f11);
        int i19 = 0;
        while (i19 < max) {
            j b12 = weatherAmbientView.b(i13, i11, i8);
            if (b12 == null) {
                return;
            }
            float f15 = i10;
            b12.f8571b = (random.nextFloat() * f11 * 9.0f * f15) + f14;
            b12.f8572c = ((random.nextFloat() - 0.5f) * f11 * 6.0f) + b11;
            b12.f8573d = a.a.b(random, 0.3f, 0.85f, f15 * f12);
            b12.f8574e = i16 == 4 ? a.a.b(random, 0.3f, 0.85f, f12 * 0.45f) : CropImageView.DEFAULT_ASPECT_RATIO;
            b12.f8577h = 34.0f;
            b12.f8578i = 34.0f;
            arrayList.add(b12);
            i19++;
            weatherAmbientView = this;
            i11 = i7;
        }
    }

    public final void l() {
        if (this.f6065i0) {
            return;
        }
        this.f6065i0 = true;
        this.f6067j0 = 0L;
        this.f6071l0 = 0L;
        this.f6073m0 = 0;
        Choreographer.getInstance().postFrameCallback(this.f6075n0);
    }

    public final int m(float f7, int i7) {
        int i8;
        if (!this.N) {
            return 0;
        }
        if (i7 == 1) {
            i8 = 320;
        } else if (i7 != 2) {
            switch (i7) {
                case 5:
                    i8 = 280;
                    break;
                case 6:
                    i8 = 260;
                    break;
                case 7:
                    i8 = 380;
                    break;
                case 8:
                    i8 = z1.c.INT_TO_FLOAT;
                    break;
                case 9:
                    i8 = 90;
                    break;
                case 10:
                    i8 = d.DEFAULT_FADE_ANIM_DURATION;
                    break;
                default:
                    return 0;
            }
        } else {
            i8 = 230;
        }
        return Math.max(40, Math.min(420, (int) (Math.max(0.25f, Math.min(1.0f, f7)) * i8 * this.R)));
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == 0) {
            l();
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.f6065i0 = false;
        Choreographer.getInstance().removeFrameCallback(this.f6075n0);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0271 A[EDGE_INSN: B:79:0x0271->B:80:0x0271 BREAK  A[LOOP:2: B:70:0x022b->B:76:0x0241], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0221  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDraw(android.graphics.Canvas r34) {
        /*
            Method dump skipped, instructions count: 1582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.view.WeatherAmbientView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.H = i7;
        this.I = i8;
        this.f6083v = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, Math.max(1, i8), new int[]{788529151, 184549375, 520093695}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.55f, 1.0f}, Shader.TileMode.CLAMP);
        i();
    }

    public void setAmbientScale(float f7) {
        float max = Math.max(1.5f, Math.min(3.0f, f7));
        if (Math.abs(max - this.f6048a) < 0.01f) {
            return;
        }
        float f8 = max / this.f6048a;
        this.f6048a = max;
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.S;
            if (i7 >= arrayList.size()) {
                invalidate();
                return;
            } else {
                ((j) arrayList.get(i7)).f8575f *= f8;
                i7++;
            }
        }
    }

    public void setEffectsEnabled(boolean z6, boolean z7) {
        this.N = z6;
        this.O = z7;
        if (!z6) {
            this.P = 0;
        } else if (this.P == 0) {
            i();
        }
        if (!this.O) {
            this.S.clear();
        }
        invalidate();
    }

    public void setFestivalOption(int i7) {
        if (i7 <= 0 || i7 > 20) {
            setFestivalOverride(-1);
        } else {
            setFestivalOverride(f6047p0[i7 - 1]);
        }
    }

    public void setFestivalOverride(int i7) {
        float[] fArr;
        int i8;
        if (this.f6055d0 == i7) {
            return;
        }
        this.f6055d0 = i7;
        this.f6049a0 = -1;
        this.f6053c0 = -1L;
        if (i7 >= 0) {
            this.W = i7;
        } else {
            this.W = a.b();
        }
        ArrayList arrayList = this.S;
        arrayList.clear();
        int i9 = 0;
        while (true) {
            fArr = this.T;
            if (i9 >= fArr.length) {
                break;
            }
            fArr[i9] = -9999.0f;
            i9++;
        }
        if (i7 >= 0 && this.H > 0 && this.I > 0) {
            int i10 = 0;
            for (int i11 = 0; i11 < 12 && i10 < 5; i11++) {
                Random random = this.f6052c;
                if (i7 != 0) {
                    int[][] iArr = a.f8517a;
                    int i12 = 0;
                    for (int i13 = 0; i13 < 19; i13++) {
                        if ((iArr[i13][0] & i7) != 0) {
                            i12 += r10.length - 1;
                        }
                    }
                    if (i12 > 0) {
                        int nextInt = random.nextInt(i12);
                        for (int i14 = 0; i14 < 19; i14++) {
                            int[] iArr2 = iArr[i14];
                            if ((iArr2[0] & i7) != 0) {
                                int length = iArr2.length - 1;
                                if (nextInt < length) {
                                    i8 = iArr2[nextInt + 1];
                                    break;
                                }
                                nextInt -= length;
                            }
                        }
                    }
                }
                i8 = -1;
                if (i8 < 0) {
                    break;
                }
                j b7 = b(i8, this.H, this.I);
                if (b7 != null) {
                    int i15 = r4.b.f8522e[i8];
                    if (i15 != 10 && i15 != 9 && i8 != 49) {
                        b7.f8571b = a.a.b(random, 0.76f, 0.12f, this.H);
                        b7.f8572c = a.a.b(random, 0.62f, 0.12f, this.I);
                    }
                    arrayList.add(b7);
                    fArr[i8] = this.f6059f0;
                    i10++;
                }
            }
        }
        this.U = 0.3f;
        invalidate();
    }

    public void setNightMode(boolean z6) {
        this.M = z6;
        invalidate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setWeather(java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 596
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.view.WeatherAmbientView.setWeather(java.lang.String, java.lang.String):void");
    }
}
