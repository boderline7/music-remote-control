package com.navi.tool.view;

import a.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import n1.z;
import p0.d1;
import r4.e;
import r4.f;
import r4.g;
import r4.h;
import x0.b;
import z1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LyricsFxView extends View {
    public static final int[] Z0 = {-44462, -26624, -5317, -10044566, -14235942, -12409355, -8497214, -1294214};
    public final float[] A;
    public final int[] A0;
    public final float[] B;
    public int B0;
    public final float[] C;
    public h C0;
    public final float[] D;
    public final float[] D0;
    public final float[] E;
    public final float[] E0;
    public final float[] F;
    public final float[] F0;
    public final float[] G;
    public final float[] G0;
    public int H;
    public final float[] H0;
    public long I;
    public final float[] I0;
    public boolean J;
    public final float[] J0;
    public final Rect K;
    public int K0;
    public final RectF L;
    public long L0;
    public long M;
    public final float[] M0;
    public Bitmap N;
    public final float[] N0;
    public int O;
    public final float[] O0;
    public int P;
    public final float[] P0;
    public int Q;
    public final float[] Q0;
    public final TreeMap R;
    public final float[] R0;
    public final ArrayList S;
    public int S0;
    public final ArrayList T;
    public boolean T0;
    public int[] U;
    public final b U0;
    public boolean V;
    public Runnable V0;
    public int W;
    public final GestureDetector W0;
    public final Camera X0;
    public final Matrix Y0;

    /* renamed from: a, reason: collision with root package name */
    public final Paint f5926a;

    /* renamed from: a0, reason: collision with root package name */
    public float f5927a0;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f5928b;

    /* renamed from: b0, reason: collision with root package name */
    public int f5929b0;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f5930c;

    /* renamed from: c0, reason: collision with root package name */
    public long f5931c0;

    /* renamed from: d, reason: collision with root package name */
    public final Paint f5932d;

    /* renamed from: d0, reason: collision with root package name */
    public long f5933d0;

    /* renamed from: e, reason: collision with root package name */
    public final Random f5934e;

    /* renamed from: e0, reason: collision with root package name */
    public boolean f5935e0;

    /* renamed from: f, reason: collision with root package name */
    public LinearGradient f5936f;

    /* renamed from: f0, reason: collision with root package name */
    public int f5937f0;

    /* renamed from: g, reason: collision with root package name */
    public LinearGradient f5938g;

    /* renamed from: g0, reason: collision with root package name */
    public long f5939g0;

    /* renamed from: h, reason: collision with root package name */
    public float f5940h;

    /* renamed from: h0, reason: collision with root package name */
    public int f5941h0;

    /* renamed from: i, reason: collision with root package name */
    public final Path f5942i;

    /* renamed from: i0, reason: collision with root package name */
    public final int[] f5943i0;

    /* renamed from: j, reason: collision with root package name */
    public final Paint f5944j;

    /* renamed from: j0, reason: collision with root package name */
    public int f5945j0;

    /* renamed from: k, reason: collision with root package name */
    public final float[] f5946k;

    /* renamed from: k0, reason: collision with root package name */
    public final ArrayList f5947k0;

    /* renamed from: l, reason: collision with root package name */
    public final float[] f5948l;

    /* renamed from: l0, reason: collision with root package name */
    public float f5949l0;

    /* renamed from: m, reason: collision with root package name */
    public final float[] f5950m;

    /* renamed from: m0, reason: collision with root package name */
    public float f5951m0;

    /* renamed from: n, reason: collision with root package name */
    public final float[] f5952n;

    /* renamed from: n0, reason: collision with root package name */
    public int f5953n0;

    /* renamed from: o, reason: collision with root package name */
    public final float[] f5954o;

    /* renamed from: o0, reason: collision with root package name */
    public final float[] f5955o0;

    /* renamed from: p, reason: collision with root package name */
    public final float[] f5956p;

    /* renamed from: p0, reason: collision with root package name */
    public final float[] f5957p0;

    /* renamed from: q, reason: collision with root package name */
    public final float[] f5958q;

    /* renamed from: q0, reason: collision with root package name */
    public final float[] f5959q0;

    /* renamed from: r, reason: collision with root package name */
    public final float[] f5960r;

    /* renamed from: r0, reason: collision with root package name */
    public final float[] f5961r0;

    /* renamed from: s, reason: collision with root package name */
    public final float[] f5962s;

    /* renamed from: s0, reason: collision with root package name */
    public final float[] f5963s0;

    /* renamed from: t, reason: collision with root package name */
    public final float[] f5964t;

    /* renamed from: t0, reason: collision with root package name */
    public final float[] f5965t0;

    /* renamed from: u, reason: collision with root package name */
    public final int[] f5966u;

    /* renamed from: u0, reason: collision with root package name */
    public final float[] f5967u0;

    /* renamed from: v, reason: collision with root package name */
    public int f5968v;

    /* renamed from: v0, reason: collision with root package name */
    public int f5969v0;

    /* renamed from: w, reason: collision with root package name */
    public int f5970w;

    /* renamed from: w0, reason: collision with root package name */
    public int f5971w0;

    /* renamed from: x, reason: collision with root package name */
    public final Path f5972x;

    /* renamed from: x0, reason: collision with root package name */
    public int f5973x0;

    /* renamed from: y, reason: collision with root package name */
    public Bitmap f5974y;

    /* renamed from: y0, reason: collision with root package name */
    public final int[] f5975y0;

    /* renamed from: z, reason: collision with root package name */
    public final int[] f5976z;

    /* renamed from: z0, reason: collision with root package name */
    public int f5977z0;

    public LyricsFxView(Context context) {
        this(context, null);
    }

    public LyricsFxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5926a = new Paint(4);
        Paint paint = new Paint(1);
        this.f5928b = paint;
        Paint paint2 = new Paint(1);
        this.f5930c = paint2;
        Paint paint3 = new Paint(1);
        this.f5932d = paint3;
        this.f5934e = new Random();
        this.f5942i = new Path();
        this.f5944j = new Paint(1);
        this.f5946k = new float[c.INT_TO_FLOAT];
        this.f5948l = new float[c.INT_TO_FLOAT];
        this.f5950m = new float[c.INT_TO_FLOAT];
        this.f5952n = new float[c.INT_TO_FLOAT];
        this.f5954o = new float[c.INT_TO_FLOAT];
        this.f5956p = new float[c.INT_TO_FLOAT];
        this.f5958q = new float[c.INT_TO_FLOAT];
        this.f5960r = new float[c.INT_TO_FLOAT];
        this.f5962s = new float[c.INT_TO_FLOAT];
        this.f5964t = new float[c.INT_TO_FLOAT];
        this.f5966u = new int[c.INT_TO_FLOAT];
        this.f5972x = new Path();
        this.f5976z = new int[14];
        this.A = new float[14];
        this.B = new float[14];
        this.C = new float[14];
        this.D = new float[14];
        this.E = new float[14];
        this.F = new float[14];
        this.G = new float[14];
        this.K = new Rect();
        this.L = new RectF();
        this.R = new TreeMap();
        this.S = new ArrayList();
        this.T = new ArrayList();
        this.U = new int[]{-14338980, -15326657, -10779452};
        this.W = -1;
        this.f5927a0 = 1.0f;
        this.f5929b0 = -7351041;
        this.f5937f0 = -1;
        this.f5943i0 = new int[49];
        this.f5945j0 = 49;
        this.f5947k0 = new ArrayList();
        this.f5949l0 = 128.0f;
        this.f5951m0 = 166.4f;
        this.f5955o0 = new float[80];
        this.f5957p0 = new float[80];
        this.f5959q0 = new float[80];
        this.f5961r0 = new float[80];
        this.f5963s0 = new float[80];
        this.f5965t0 = new float[80];
        this.f5967u0 = new float[80];
        this.f5975y0 = new int[10];
        this.f5977z0 = 10;
        this.A0 = new int[9];
        this.B0 = 9;
        this.D0 = new float[c.REM_INT_2ADDR];
        this.E0 = new float[c.REM_INT_2ADDR];
        this.F0 = new float[c.REM_INT_2ADDR];
        this.G0 = new float[c.REM_INT_2ADDR];
        this.H0 = new float[c.REM_INT_2ADDR];
        this.I0 = new float[c.REM_INT_2ADDR];
        this.J0 = new float[c.REM_INT_2ADDR];
        this.M0 = new float[340];
        this.N0 = new float[340];
        this.O0 = new float[340];
        this.P0 = new float[340];
        this.Q0 = new float[340];
        this.R0 = new float[340];
        this.U0 = new b(1, this);
        Camera camera = new Camera();
        this.X0 = camera;
        this.Y0 = new Matrix();
        Typeface create = Typeface.create(Typeface.DEFAULT, 1);
        paint.setTypeface(create);
        paint.setColor(this.W);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setTypeface(create);
        paint3.setStyle(Paint.Style.FILL);
        camera.setLocation(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, context.getResources().getDisplayMetrics().density * (-6.0f));
        this.W0 = new GestureDetector(context, new e(0, this));
    }

    public static float b(float f7) {
        float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f7 >= CropImageView.DEFAULT_ASPECT_RATIO) {
            f8 = 1.0f;
            if (f7 <= 1.0f) {
                return f7;
            }
        }
        return f8;
    }

    public static int d0(int i7) {
        return Color.rgb(Math.min(255, (int) (((255 - Color.red(i7)) * 0.55f) + Color.red(i7))), Math.min(255, (int) (((255 - Color.green(i7)) * 0.55f) + Color.green(i7))), Math.min(255, (int) (((255 - Color.blue(i7)) * 0.55f) + Color.blue(i7))));
    }

    public static float l(float f7) {
        float f8 = f7 - 1.0f;
        return a.A(f8, 1.70158f, f8, (2.70158f * f8 * f8 * f8) + 1.0f);
    }

    public static float m(float f7) {
        if (f7 < 0.3632f) {
            return 7.5625f * f7 * f7;
        }
        if (f7 < 0.7272f) {
            float f8 = f7 - 0.5453f;
            return a.A(f8, 7.5625f, f8, 0.75f);
        }
        if (f7 < 0.9091f) {
            float f9 = f7 - 0.8181f;
            return a.A(f9, 7.5625f, f9, 0.9375f);
        }
        float f10 = f7 - 0.9545f;
        return a.A(f10, 7.5625f, f10, 0.9844f);
    }

    public static float n(float f7) {
        float f8 = 1.0f - f7;
        return 1.0f - ((f8 * f8) * f8);
    }

    public static float o(float f7) {
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        return 1.0f - ((float) Math.pow(2.0d, f7 * (-10.0f)));
    }

    public final void A(Canvas canvas, int i7, int i8, float f7) {
        float o6 = o(f7);
        float f8 = i7;
        float f9 = (1.0f - o6) * f8 * 0.5f;
        a(i8);
        for (int i9 = 3; i9 >= 1; i9--) {
            Paint paint = this.f5928b;
            paint.setTextSize(this.f5949l0);
            paint.setColor(this.f5929b0);
            paint.setAlpha((int) ((70.0f * o6) / i9));
            int i10 = 0;
            while (true) {
                ArrayList arrayList = this.f5947k0;
                if (i10 < arrayList.size()) {
                    f fVar = (f) arrayList.get(i10);
                    float f10 = fVar.f8559b;
                    c(22.0f);
                    canvas.drawText(fVar.f8558a, (f8 - fVar.f8559b) * 0.5f, (i10 * this.f5951m0) + a(i8), paint);
                    i10++;
                }
            }
        }
        d(canvas, i7, i8, f9, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, Math.min(1.0f, 1.8f * f7));
    }

    public final void B(Canvas canvas, int i7, int i8, float f7) {
        a(i8);
        float abs = (f7 <= 0.12f || f7 >= 0.78f) ? 0.0f : 1.0f - (Math.abs(f7 - 0.45f) / 0.33f);
        float b7 = b(f7 * 1.8f);
        if (abs < 0.03f) {
            d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, b7);
            return;
        }
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        int i9 = 0;
        while (i9 < 11) {
            float f8 = 11;
            float f9 = (i8 * i9) / f8;
            i9++;
            float f10 = (i8 * i9) / f8;
            int save = canvas.save();
            canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, f9, i7, f10);
            float c7 = c(46.0f) * (this.f5934e.nextFloat() - 0.5f) * abs;
            paint.setAlpha((int) (255.0f * b7));
            paint.setColor(-53931);
            g(canvas, c7 - (c(7.0f) * abs));
            paint.setColor(-16718337);
            g(canvas, (c(7.0f) * abs) + c7);
            paint.setColor(this.W);
            g(canvas, c7);
            canvas.restoreToCount(save);
        }
    }

    public final void C(Canvas canvas, int i7, int i8, float f7) {
        float o6 = o(f7);
        float max = Math.max(i7, i8) * o6 * 0.85f;
        Paint paint = this.f5932d;
        paint.setStyle(Paint.Style.STROKE);
        float f8 = 1.0f - o6;
        paint.setStrokeWidth((c(8.0f) * f8) + 1.0f);
        paint.setColor(-1);
        paint.setAlpha((int) (200.0f * f8 * f8));
        canvas.drawCircle(i7 * 0.5f, i8 * 0.5f, max, paint);
        paint.setStyle(Paint.Style.FILL);
        float f9 = (0.6f * f8) + 1.0f;
        float c7 = c(14.0f) * f8 * f8;
        int save = canvas.save();
        Random random = this.f5934e;
        canvas.translate((random.nextFloat() - 0.5f) * c7, (random.nextFloat() - 0.5f) * c7);
        d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f9, b(2.4f * f7));
        canvas.restoreToCount(save);
    }

    public final void D(Canvas canvas, int i7, int i8, float f7) {
        float n6 = n(f7) * Math.max(i7, i8) * 0.78f;
        if (n6 < 1.0f) {
            return;
        }
        int save = canvas.save();
        Path path = this.f5972x;
        path.reset();
        float f8 = i7 * 0.5f;
        float f9 = i8 * 0.5f;
        path.addCircle(f8, f9, n6, Path.Direction.CW);
        canvas.clipPath(path);
        d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f);
        canvas.restoreToCount(save);
        float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f - (1.1f * f7));
        if (max > 0.02f) {
            Paint paint = this.f5932d;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(c(5.0f) * max);
            paint.setColor(this.f5929b0);
            paint.setAlpha((int) (max * 150.0f));
            canvas.drawCircle(f8, f9, n6, paint);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    public final void E(Canvas canvas, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        float c7 = c(12.0f);
        int i7 = this.f5929b0;
        float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
        paint.setShadowLayer(c7, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7);
        int i8 = 0;
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i8 >= arrayList.size()) {
                float f9 = f8;
                paint.setShadowLayer(f9, f9, f9, 0);
                return;
            }
            f fVar = (f) arrayList.get(i8);
            int i10 = 0;
            while (i10 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i10));
                float measureText = paint.measureText(valueOf);
                float b7 = b((f7 - ((i9 / Math.max(1, this.f5953n0)) * 0.42f)) / 0.56f);
                float c8 = c(80.0f) * ((float) Math.sin(b7 * 3.141592653589793d));
                float f10 = (b7 * 0.5f) + 0.5f;
                paint.setColor(this.W);
                paint.setAlpha((int) (Math.min(1.0f, b7 * 1.9f) * 255.0f));
                int save = canvas.save();
                canvas.translate((measureText * 0.5f) + this.f5955o0[i9], this.f5957p0[i9] - c8);
                canvas.rotate(720.0f * b7);
                canvas.scale(f10, f10);
                canvas.drawText(valueOf, (-measureText) * 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, paint);
                canvas.restoreToCount(save);
                i9++;
                i10++;
                f8 = 0.0f;
            }
            i8++;
        }
    }

    public final void F(Canvas canvas, int i7, int i8, float f7) {
        ArrayList arrayList;
        float f8;
        a(i8);
        float f9 = i7;
        float n6 = (n(f7) * (c(60.0f) + f9)) + (-c(30.0f));
        Paint paint = this.f5930c;
        paint.setTextSize(this.f5949l0);
        paint.setStrokeWidth(c(1.4f));
        paint.setColor(1442840575);
        paint.setAlpha(85);
        int i9 = 0;
        while (true) {
            arrayList = this.f5947k0;
            f8 = 0.5f;
            if (i9 >= arrayList.size()) {
                break;
            }
            f fVar = (f) arrayList.get(i9);
            canvas.drawText(fVar.f8558a, (f9 - fVar.f8559b) * 0.5f, (i9 * this.f5951m0) + a(i8), paint);
            i9++;
        }
        int save = canvas.save();
        float f10 = i8;
        canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, n6, f10);
        Paint paint2 = this.f5928b;
        paint2.setTextSize(this.f5949l0);
        paint2.setColor(this.W);
        paint2.setAlpha(255);
        paint2.setShadowLayer(c(14.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5929b0);
        int i10 = 0;
        while (i10 < arrayList.size()) {
            f fVar2 = (f) arrayList.get(i10);
            canvas.drawText(fVar2.f8558a, (f9 - fVar2.f8559b) * f8, (i10 * this.f5951m0) + a(i8), paint2);
            i10++;
            f8 = 0.5f;
        }
        canvas.restoreToCount(save);
        paint2.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
        Paint paint3 = this.f5932d;
        paint3.setColor(this.f5929b0);
        paint3.setAlpha(60);
        canvas.drawRect(n6 - c(7.0f), CropImageView.DEFAULT_ASPECT_RATIO, c(7.0f) + n6, f10, paint3);
        paint3.setAlpha(200);
        canvas.drawRect(n6 - c(1.6f), CropImageView.DEFAULT_ASPECT_RATIO, c(1.6f) + n6, f10, paint3);
    }

    public final void G(Canvas canvas, int i7, int i8, float f7) {
        float n6 = n(f7);
        float f8 = i7;
        float f9 = 0.5f;
        float A = a.A((float) Math.sin(3.14f * f7), f8, 0.12f, f8 * 0.5f);
        float c7 = c(95.0f);
        if (this.f5936f == null) {
            this.f5936f = new LinearGradient(-c7, CropImageView.DEFAULT_ASPECT_RATIO, c7, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{d1.MEASURED_SIZE_MASK, 1895825407, d1.MEASURED_SIZE_MASK}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        }
        int save = canvas.save();
        canvas.translate(A, CropImageView.DEFAULT_ASPECT_RATIO);
        Paint paint = this.f5932d;
        paint.setShader(this.f5936f);
        paint.setAlpha((int) ((1.0f - (n6 * 0.45f)) * 200.0f));
        canvas.drawRect(-c7, CropImageView.DEFAULT_ASPECT_RATIO, c7, i8, paint);
        paint.setShader(null);
        canvas.restoreToCount(save);
        Paint paint2 = this.f5928b;
        paint2.setTextSize(this.f5949l0);
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            int i11 = 0;
            while (i11 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float measureText = paint2.measureText(valueOf);
                float[] fArr = this.f5955o0;
                float b7 = b(1.0f - (Math.abs(((measureText * f9) + fArr[i10]) - A) / (c7 * 2.4f)));
                float b8 = b((2.4f * f7) - ((i10 / this.f5953n0) * 0.9f));
                paint2.setColor(this.W);
                paint2.setAlpha((int) (((b7 * 0.65f) + 0.35f) * b8 * 255.0f));
                canvas.drawText(valueOf, fArr[i10], this.f5957p0[i10], paint2);
                i10++;
                i11++;
                f9 = 0.5f;
            }
            i9++;
            f9 = 0.5f;
        }
    }

    public final void H(Canvas canvas, int i7, int i8, float f7) {
        ArrayList arrayList;
        float a7 = a(i8);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(1442840575);
        paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
        int i9 = 0;
        while (true) {
            arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                break;
            }
            f fVar = (f) arrayList.get(i9);
            canvas.drawText(fVar.f8558a, (i7 - fVar.f8559b) * 0.5f, (i9 * this.f5951m0) + a7, paint);
            i9++;
        }
        float c7 = c(130.0f);
        float f8 = i7;
        float n6 = (n(f7) * ((c7 * 2.0f) + f8)) + (-c7);
        int save = canvas.save();
        canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, n6, i8);
        paint.setColor(this.W);
        paint.setAlpha(255);
        paint.setShadowLayer(c(18.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5929b0);
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            f fVar2 = (f) arrayList.get(i10);
            canvas.drawText(fVar2.f8558a, (f8 - fVar2.f8559b) * 0.5f, (i10 * this.f5951m0) + a(i8), paint);
        }
        canvas.restoreToCount(save);
        paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
    }

    public final void I(Canvas canvas, float f7) {
        int b7 = (int) (b(f7 * 1.25f) * this.f5953n0);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setShadowLayer(c(8.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1711276032);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length(); i9++) {
                char charAt = fVar.f8558a.charAt(i9);
                float[] fArr = this.f5957p0;
                float[] fArr2 = this.f5955o0;
                if (i8 < b7) {
                    paint.setColor(this.W);
                    paint.setAlpha(255);
                    canvas.drawText(String.valueOf(charAt), fArr2[i8], fArr[i8], paint);
                } else {
                    paint.setColor(this.f5929b0);
                    paint.setAlpha(c.OR_INT);
                    canvas.drawText(String.valueOf((char) (this.f5934e.nextInt(26) + 65)), fArr2[i8], fArr[i8], paint);
                }
                i8++;
            }
            i7++;
        }
    }

    public final void J(Canvas canvas, int i7, float f7) {
        ArrayList arrayList;
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        float[] fArr4;
        float[] fArr5;
        float[] fArr6;
        a(i7);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        int i8 = 0;
        while (true) {
            arrayList = this.f5947k0;
            fArr = this.f5961r0;
            fArr2 = this.f5959q0;
            float f8 = 1.8f;
            fArr3 = this.f5957p0;
            fArr4 = this.f5955o0;
            if (i8 >= 2) {
                break;
            }
            Paint paint2 = this.f5930c;
            paint2.setTextSize(this.f5949l0);
            paint2.setStrokeWidth(c(10.0f - (i8 * 4.0f)));
            paint2.setColor(this.f5929b0);
            int i9 = 0;
            int i10 = 0;
            while (i9 < arrayList.size()) {
                f fVar = (f) arrayList.get(i9);
                int i11 = i10;
                int i12 = 0;
                while (i12 < fVar.f8558a.length() && i11 < this.f5969v0) {
                    String valueOf = String.valueOf(fVar.f8558a.charAt(i12));
                    f fVar2 = fVar;
                    float b7 = b((f7 * f8) - ((i11 / this.f5953n0) * 0.8f));
                    if (b7 > 0.02f) {
                        paint2.setAlpha((int) (((i8 * 45) + 70) * b7));
                        int save = canvas.save();
                        fArr5 = fArr;
                        fArr6 = fArr2;
                        canvas.rotate(fArr2[i11], (fArr[i11] * 0.5f) + fArr4[i11], fArr3[i11] - (this.f5949l0 * 0.35f));
                        canvas.drawText(valueOf, fArr4[i11], fArr3[i11], paint2);
                        canvas.restoreToCount(save);
                    } else {
                        fArr5 = fArr;
                        fArr6 = fArr2;
                    }
                    i11++;
                    i12++;
                    fVar = fVar2;
                    fArr2 = fArr6;
                    fArr = fArr5;
                    f8 = 1.8f;
                }
                i9++;
                i10 = i11;
                fArr2 = fArr2;
                fArr = fArr;
                f8 = 1.8f;
            }
            i8++;
        }
        paint.setColor(this.W);
        int i13 = 0;
        for (int i14 = 0; i14 < arrayList.size(); i14++) {
            f fVar3 = (f) arrayList.get(i14);
            for (int i15 = 0; i15 < fVar3.f8558a.length() && i13 < this.f5969v0; i15++) {
                String valueOf2 = String.valueOf(fVar3.f8558a.charAt(i15));
                paint.setAlpha((int) (b((f7 * 1.8f) - ((i13 / this.f5953n0) * 0.8f)) * 255.0f));
                int save2 = canvas.save();
                canvas.rotate(fArr2[i13], (fArr[i13] * 0.5f) + fArr4[i13], fArr3[i13] - (this.f5949l0 * 0.35f));
                canvas.drawText(valueOf2, fArr4[i13], fArr3[i13], paint);
                canvas.restoreToCount(save2);
                i13++;
            }
        }
    }

    public final void K(Canvas canvas, int i7, int i8, float f7) {
        float o6 = o(f7);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setShadowLayer(c(16.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5929b0);
        k(o6, 0.55f, i7, i8, canvas);
        paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
        k(o6, 1.0f, i7, i8, canvas);
    }

    public final void L(Canvas canvas, int i7, int i8, float f7) {
        float b7 = b(f7 / 0.55f);
        float b8 = b((f7 - 0.52f) / 0.38f);
        Paint paint = this.f5932d;
        paint.setColor(this.f5929b0);
        for (int i9 = 0; i9 < this.S0; i9++) {
            float b9 = b((1.35f * b7) - (this.Q0[i9] * 0.4f));
            float n6 = n(b9);
            float f8 = this.M0[i9];
            float a7 = a.a(this.O0[i9], f8, n6, f8);
            float f9 = this.N0[i9];
            float a8 = a.a(this.P0[i9], f9, n6, f9);
            paint.setAlpha((int) (((0.65f * b9) + 0.35f) * 230.0f));
            canvas.drawCircle(a7, a8, ((b9 * 0.6f) + 0.7f) * this.R0[i9], paint);
        }
        if (b8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        Paint paint2 = this.f5928b;
        paint2.setTextSize(this.f5949l0);
        paint2.setColor(this.W);
        paint2.setAlpha((int) (255.0f * b8));
        paint2.setShadowLayer(c(22.0f) * b8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5929b0);
        a(i8);
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i10 >= arrayList.size()) {
                paint2.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                return;
            }
            f fVar = (f) arrayList.get(i10);
            canvas.drawText(fVar.f8558a, (i7 - fVar.f8559b) * 0.5f, (i10 * this.f5951m0) + a(i8), paint2);
            i10++;
        }
    }

    public final void M(Canvas canvas, float f7) {
        int uptimeMillis = (int) ((SystemClock.uptimeMillis() / 1000.0f) * 7.0f);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setShadowLayer(c(10.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1711276032);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length(); i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                paint.measureText(valueOf);
                float b7 = b((1.8f * f7) - ((i8 / this.f5953n0) * 0.5f));
                paint.setColor(Z0[Math.abs(i8 + uptimeMillis) % 8]);
                paint.setAlpha((int) (b7 * 255.0f));
                canvas.drawText(valueOf, this.f5955o0[i8], this.f5957p0[i8], paint);
                i8++;
            }
            i7++;
        }
    }

    public final void N(Canvas canvas, int i7, int i8, float f7) {
        float c7 = c(70.0f) * (1.0f - n(f7));
        float b7 = b(f7 * 1.6f);
        float a7 = a(i8);
        ArrayList arrayList = this.f5947k0;
        float size = ((arrayList.size() - 1) * this.f5951m0) + a7 + this.f5949l0;
        int save = canvas.save();
        canvas.scale(1.0f, -0.55f, CropImageView.DEFAULT_ASPECT_RATIO, size);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.f5929b0);
        paint.setAlpha((int) (90.0f * b7));
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            f fVar = (f) arrayList.get(i9);
            canvas.drawText(fVar.f8558a, (i7 - fVar.f8559b) * 0.5f, (i9 * this.f5951m0) + a(i8), paint);
        }
        canvas.restoreToCount(save);
        e(c7, b7, canvas);
    }

    public final void O(Canvas canvas, int i7, int i8, float f7) {
        float c7 = c(26.0f) * (1.0f - n(f7));
        float b7 = b(f7 * 2.0f);
        a(i8);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setAlpha((int) (b7 * 255.0f));
        paint.setColor(-53931);
        g(canvas, -c7);
        paint.setColor(-16718337);
        g(canvas, c7);
        paint.setColor(this.W);
        g(canvas, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public final void P(Canvas canvas, int i7, int i8, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            int i11 = 0;
            for (f fVar = (f) arrayList.get(i9); i11 < fVar.f8558a.length(); fVar = fVar) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float measureText = paint.measureText(valueOf);
                float f8 = ((i10 * c.DIV_INT_LIT16) % 97) / 97.0f;
                float b7 = b((f7 * 1.6f) - (f8 * 0.5f));
                float f9 = f8 - 0.5f;
                float l7 = 1.0f - l(b7);
                float f10 = i7 * f9 * 0.8f * l7;
                float f11 = (((f8 * 3.1f) % 1.0f) - 0.5f) * i8 * 0.7f * l7;
                float f12 = l7 * f9 * 180.0f;
                paint.setColor(b7 > 0.95f ? -1 : this.f5929b0);
                paint.setAlpha((int) (Math.min(1.0f, b7 * 1.6f) * 255.0f));
                int save = canvas.save();
                float[] fArr = this.f5955o0;
                float f13 = (measureText * 0.5f) + fArr[i10] + f10;
                float[] fArr2 = this.f5957p0;
                canvas.rotate(f12, f13, (fArr2[i10] + f11) - (this.f5949l0 * 0.3f));
                canvas.drawText(valueOf, fArr[i10] + f10, fArr2[i10] + f11, paint);
                canvas.restoreToCount(save);
                i10++;
                i11++;
            }
            i9++;
        }
    }

    public final void Q(Canvas canvas, float f7) {
        float c7 = c(26.0f) * (1.0f - n(f7));
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 2.5f) * 255.0f));
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                Random random = this.f5934e;
                canvas.drawText(valueOf, this.f5955o0[i8] + ((random.nextFloat() - 0.5f) * c7), this.f5957p0[i8] + ((random.nextFloat() - 0.5f) * c7), paint);
                i8++;
            }
            i7++;
        }
    }

    public final void R(Canvas canvas, int i7, int i8, float f7) {
        float n6 = n(f7);
        float f8 = 0.7f;
        float max = Math.max(i7, i8) * n6 * 0.7f;
        Paint paint = this.f5932d;
        paint.setStyle(Paint.Style.STROKE);
        float f9 = 1.0f - n6;
        paint.setStrokeWidth((c(4.0f) * f9) + 1.0f);
        paint.setColor(this.f5929b0);
        paint.setAlpha((int) (f9 * 160.0f));
        canvas.drawCircle(i7 * 0.5f, i8 * 0.5f, max, paint);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = this.f5928b;
        paint2.setTextSize(this.f5949l0);
        paint2.setColor(this.W);
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            int i11 = 0;
            while (i11 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float measureText = paint2.measureText(valueOf);
                float b7 = b((1.9f * f7) - ((i10 / this.f5953n0) * f8));
                float l7 = (l(b7) * f8) + 0.3f;
                int save = canvas.save();
                canvas.translate((measureText * 0.5f) + this.f5955o0[i10], this.f5957p0[i10] - (this.f5949l0 * 0.32f));
                canvas.scale(l7, l7);
                paint2.setAlpha((int) (b7 * 255.0f));
                canvas.drawText(valueOf, (-measureText) * 0.5f, this.f5949l0 * 0.32f, paint2);
                canvas.restoreToCount(save);
                i10++;
                i11++;
                f8 = 0.7f;
            }
            i9++;
            f8 = 0.7f;
        }
    }

    public final void S(Canvas canvas, int i7, int i8, float f7) {
        float m6 = m(f7);
        float A = a.A(this.f5947k0.size() - 1, this.f5951m0, 0.5f, a(i8));
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 3.0f) * 255.0f));
        int save = canvas.save();
        float f8 = 1.0f - m6;
        canvas.translate(i7 * f8 * 0.12f, (-f8) * i8 * 0.35f);
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, A);
        canvas.skew(CropImageView.DEFAULT_ASPECT_RATIO, f8 * 0.4f);
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, -A);
        f(canvas, i7, i8, paint);
        canvas.restoreToCount(save);
    }

    public final void T(Canvas canvas, int i7, int i8, float f7) {
        float n6 = n(f7);
        float A = a.A(this.f5947k0.size() - 1, this.f5951m0, 0.5f, a(i8));
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 2.2f) * 255.0f));
        int save = canvas.save();
        float f8 = -(1.0f - n6);
        canvas.translate(i7 * f8 * 0.7f, CropImageView.DEFAULT_ASPECT_RATIO);
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, A);
        canvas.skew(f8 * 0.9f, CropImageView.DEFAULT_ASPECT_RATIO);
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, -A);
        f(canvas, i7, i8, paint);
        canvas.restoreToCount(save);
    }

    public final void U(Canvas canvas, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length(); i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                paint.measureText(valueOf);
                float b7 = b((f7 - ((i8 / this.f5953n0) * 0.35f)) / 0.55f);
                float n6 = 1.0f - n(b7);
                float c7 = c(280.0f) * n6;
                double d7 = 5.2f * n6;
                float cos = ((float) Math.cos(d7)) * c7;
                float sin = ((float) Math.sin(d7)) * c7;
                paint.setAlpha((int) (b7 * 255.0f));
                canvas.drawText(valueOf, this.f5955o0[i8] + cos, this.f5957p0[i8] + sin, paint);
                i8++;
            }
            i7++;
        }
    }

    public final void V(Canvas canvas, int i7, int i8, float f7) {
        float c7 = c(90.0f) * (1.0f - n(f7));
        float b7 = b(f7 * 2.0f);
        a(i8);
        int save = canvas.save();
        float f8 = i7;
        float f9 = i8;
        float f10 = 0.5f * f9;
        canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f8, f10);
        e(-c7, b7, canvas);
        canvas.restoreToCount(save);
        int save2 = canvas.save();
        canvas.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, f10, f8, f9);
        e(c7, b7, canvas);
        canvas.restoreToCount(save2);
    }

    public final void W(Canvas canvas, float f7) {
        float f8;
        Paint paint;
        float uptimeMillis = SystemClock.uptimeMillis() / 1000.0f;
        Paint paint2 = this.f5928b;
        paint2.setTextSize(this.f5949l0);
        paint2.setColor(this.W);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i7);
            int i9 = 0;
            while (i9 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                float measureText = paint2.measureText(valueOf);
                float f9 = i8;
                float b7 = b((1.9f * f7) - ((f9 / this.f5953n0) * 0.6f));
                paint2.setAlpha((int) (255.0f * b7));
                float[] fArr = this.f5955o0;
                float f10 = fArr[i8];
                float[] fArr2 = this.f5957p0;
                canvas.drawText(valueOf, f10, fArr2[i8], paint2);
                if (b7 > 0.35f) {
                    float f11 = (0.5f * measureText) + fArr[i8];
                    float f12 = fArr2[i8];
                    float f13 = this.f5949l0;
                    float f14 = f12 - (0.33f * f13);
                    float f15 = measureText * 0.78f;
                    float f16 = f13 * 0.34f;
                    Paint paint3 = this.f5932d;
                    paint3.setColor(this.f5929b0);
                    paint = paint2;
                    int i10 = 0;
                    while (i10 < 2) {
                        float f17 = uptimeMillis;
                        double d7 = (i10 * 3.1416f) + (0.62f * f9) + (2.1f * uptimeMillis);
                        f fVar2 = fVar;
                        float cos = (((float) Math.cos(d7)) * f15) + f11;
                        float sin = (((float) Math.sin(d7)) * f16) + f14;
                        paint3.setAlpha((int) (190.0f * b7));
                        canvas.drawCircle(cos, sin, c(2.3f), paint3);
                        i10++;
                        uptimeMillis = f17;
                        fVar = fVar2;
                        i9 = i9;
                    }
                    f8 = uptimeMillis;
                } else {
                    f8 = uptimeMillis;
                    paint = paint2;
                }
                i8++;
                i9++;
                paint2 = paint;
                uptimeMillis = f8;
                fVar = fVar;
            }
            i7++;
        }
    }

    public final void X(Canvas canvas, int i7, float f7) {
        float n6 = ((1.0f - n(f7)) * 1.3f) + 1.0f;
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 2.5f) * 255.0f));
        float f8 = i7 * 0.5f;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i8 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i8);
            for (int i10 = 0; i10 < fVar.f8558a.length() && i9 < this.f5969v0; i10++) {
                canvas.drawText(String.valueOf(fVar.f8558a.charAt(i10)), a.a(this.f5955o0[i9], f8, n6, f8), this.f5957p0[i9], paint);
                i9++;
            }
            i8++;
        }
    }

    public final void Y(Canvas canvas, int i7, float f7) {
        float n6 = (n(f7) * 0.58f) + 0.42f;
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 2.5f) * 255.0f));
        float f8 = i7 * 0.5f;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i8 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i8);
            for (int i10 = 0; i10 < fVar.f8558a.length() && i9 < this.f5969v0; i10++) {
                canvas.drawText(String.valueOf(fVar.f8558a.charAt(i10)), a.a(this.f5955o0[i9], f8, n6, f8), this.f5957p0[i9], paint);
                i9++;
            }
            i8++;
        }
    }

    public final void Z(Canvas canvas, int i7, int i8, float f7) {
        float o6 = o(f7);
        float f8 = (0.82f * o6) + 0.18f;
        a(i8);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        for (int i9 = 4; i9 >= 1; i9--) {
            float f9 = i9;
            float f10 = (1.0f - (0.055f * f9)) * f8;
            int i10 = (int) ((46.0f * o6) / f9);
            if (i10 > 2) {
                int save = canvas.save();
                float f11 = i7;
                float f12 = 0.5f;
                canvas.scale(f10, f10, f11 * 0.5f, i8 * 0.5f);
                paint.setColor(this.f5929b0);
                paint.setAlpha(i10);
                int i11 = 0;
                while (true) {
                    ArrayList arrayList = this.f5947k0;
                    if (i11 >= arrayList.size()) {
                        break;
                    }
                    f fVar = (f) arrayList.get(i11);
                    canvas.drawText(fVar.f8558a, (f11 - fVar.f8559b) * f12, (i11 * this.f5951m0) + a(i8), paint);
                    i11++;
                    f12 = 0.5f;
                }
                canvas.restoreToCount(save);
            }
        }
        d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f8, b(2.1f * f7));
    }

    public final float a(int i7) {
        return ((i7 - (this.f5947k0.size() * this.f5951m0)) * 0.5f) + this.f5949l0;
    }

    public final void a0(Canvas canvas, float f7) {
        float f8 = 1.0f;
        float n6 = (1.0f - n(f7)) * 0.34f;
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(2.5f * f7) * 255.0f));
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                paint.setAlpha(255);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            int i9 = 0;
            while (i9 < fVar.f8558a.length() && i8 < this.f5969v0) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                float sin = (((float) Math.sin((12.566f * f7) - (i8 * 0.5f))) * n6) + f8;
                int save = canvas.save();
                float f9 = this.f5955o0[i8];
                float[] fArr = this.f5961r0;
                canvas.translate((fArr[i8] * 0.5f) + f9, this.f5957p0[i8] - (this.f5949l0 * 0.35f));
                canvas.scale(sin, sin);
                canvas.drawText(valueOf, (-fArr[i8]) * 0.5f, this.f5949l0 * 0.35f, paint);
                canvas.restoreToCount(save);
                i8++;
                i9++;
                f8 = 1.0f;
            }
            i7++;
            f8 = 1.0f;
        }
    }

    public final void b0() {
        this.T0 = false;
        Choreographer.getInstance().removeFrameCallback(this.U0);
        this.R.clear();
        this.S.clear();
        this.T.clear();
        this.f5947k0.clear();
        this.K0 = 0;
        this.S0 = 0;
        this.C0 = null;
        setVisibility(8);
    }

    public final float c(float f7) {
        return f7 * getResources().getDisplayMetrics().density;
    }

    public final boolean c0() {
        return getVisibility() == 0 && this.T0;
    }

    public final void d(Canvas canvas, int i7, int i8, float f7, float f8, float f9, float f10) {
        if (f10 <= 0.01f) {
            return;
        }
        a(i8);
        ArrayList arrayList = this.f5947k0;
        arrayList.size();
        int save = canvas.save();
        canvas.translate(f7, f8);
        canvas.scale(f9, f9, i7 * 0.5f, i8 * 0.5f);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f10) * 255.0f));
        paint.setShadowLayer(c(12.0f), CropImageView.DEFAULT_ASPECT_RATIO, c(3.0f), -2013265920);
        int i9 = 0;
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            f fVar = (f) arrayList.get(i10);
            for (int i11 = 0; i11 < fVar.f8558a.length() && i9 < this.f5969v0; i11++) {
                h(canvas, i9, String.valueOf(fVar.f8558a.charAt(i11)), b(f10));
                i9++;
            }
        }
        paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
        canvas.restoreToCount(save);
    }

    public final void e(float f7, float f8, Canvas canvas) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f8) * 255.0f));
        paint.setShadowLayer(c(12.0f), CropImageView.DEFAULT_ASPECT_RATIO, c(3.0f), -2013265920);
        int save = canvas.save();
        canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, f7);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                canvas.restoreToCount(save);
                paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                h(canvas, i8, String.valueOf(fVar.f8558a.charAt(i9)), b(f8));
                i8++;
            }
            i7++;
        }
    }

    public final void e0(float f7, float f8, float f9, float f10) {
        Camera camera = this.X0;
        camera.save();
        if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
            camera.rotateY(f8);
        }
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            camera.rotateX(f7);
        }
        Matrix matrix = this.Y0;
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f9, -f10);
        matrix.postTranslate(f9, f10);
    }

    public final void f(Canvas canvas, int i7, int i8, Paint paint) {
        float a7 = a(i8);
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            canvas.drawText(fVar.f8558a, (i7 - fVar.f8559b) * 0.5f, (i9 * this.f5951m0) + a7, paint);
            i9++;
        }
    }

    public final void f0() {
        ArrayList arrayList = this.S;
        arrayList.clear();
        ArrayList arrayList2 = this.T;
        arrayList2.clear();
        for (Map.Entry entry : this.R.entrySet()) {
            String str = (String) entry.getValue();
            if (!TextUtils.isEmpty(str)) {
                arrayList.add((Long) entry.getKey());
                arrayList2.add(str);
            }
        }
    }

    public final void g(Canvas canvas, float f7) {
        this.f5928b.setTextSize(this.f5949l0);
        int save = canvas.save();
        canvas.translate(f7, CropImageView.DEFAULT_ASPECT_RATIO);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                canvas.restoreToCount(save);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                h(canvas, i8, String.valueOf(fVar.f8558a.charAt(i9)), 1.0f);
                i8++;
            }
            i7++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:161:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0760  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0763  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g0(int r40) {
        /*
            Method dump skipped, instructions count: 2200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.view.LyricsFxView.g0(int):void");
    }

    public final void h(Canvas canvas, int i7, String str, float f7) {
        float n6 = this.f5973x0 == 5 ? f7 * f7 : n(f7);
        float f8 = this.f5963s0[i7];
        float a7 = a.a(this.f5955o0[i7], f8, n6, f8);
        float f9 = this.f5965t0[i7];
        float a8 = a.a(this.f5957p0[i7], f9, n6, f9);
        float f10 = this.f5959q0[i7];
        int i8 = this.f5973x0;
        float f11 = i8 == 3 ? (0.66f * n6) + 0.34f : 1.0f;
        if (i8 == 7) {
            f11 = 2.0f - n6;
        }
        if (i8 == 8) {
            f10 = a.a(1.0f, n6, 520.0f, f10);
        }
        if (i8 == 4) {
            f10 = a.a(1.0f, n6, ((float) Math.sin(n6 * 3.141592653589793d * 1.399999976158142d)) * 20.0f, f10);
        }
        canvas.save();
        float[] fArr = this.f5961r0;
        canvas.translate((fArr[i7] * 0.5f) + a7, a8 - (this.f5949l0 * 0.35f));
        canvas.rotate(f10);
        canvas.scale(f11, f11);
        canvas.drawText(str, (-fArr[i7]) * 0.5f, this.f5949l0 * 0.35f, this.f5928b);
        canvas.restore();
    }

    public final void h0(String str) {
        if (!this.T0 || str == null) {
            return;
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return;
        }
        ArrayList arrayList = this.T;
        if (arrayList.isEmpty()) {
            return;
        }
        int i7 = 0;
        int max = Math.max(0, this.f5937f0);
        while (true) {
            if (max >= arrayList.size()) {
                max = -1;
                break;
            } else if (((String) arrayList.get(max)).equals(trim)) {
                break;
            } else {
                max++;
            }
        }
        if (max < 0) {
            while (true) {
                if (i7 >= arrayList.size()) {
                    break;
                }
                if (((String) arrayList.get(i7)).equals(trim)) {
                    max = i7;
                    break;
                }
                i7++;
            }
        }
        if (max < 0 || max == this.f5937f0) {
            return;
        }
        g0(max);
    }

    public final void i(Canvas canvas) {
        for (int i7 = 0; i7 < this.f5968v; i7++) {
            float f7 = this.f5958q[i7];
            if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                float f8 = f7 / this.f5960r[i7];
                Paint paint = this.f5932d;
                paint.setColor(this.f5966u[i7]);
                paint.setAlpha((int) (Math.min(1.0f, f8 * 2.6f) * 255.0f * this.f5927a0));
                int save = canvas.save();
                canvas.translate(this.f5946k[i7], this.f5948l[i7]);
                canvas.rotate(this.f5954o[i7]);
                float f9 = this.f5962s[i7];
                float f10 = this.f5964t[i7];
                canvas.drawRect((-f9) * 0.5f, (-f10) * 0.5f, f9 * 0.5f, f10 * 0.5f, paint);
                canvas.restoreToCount(save);
            }
        }
    }

    public final void i0(TreeMap treeMap) {
        if (this.T0) {
            TreeMap treeMap2 = this.R;
            treeMap2.clear();
            if (treeMap != null) {
                treeMap2.putAll(treeMap);
            }
            f0();
            this.f5937f0 = -1;
            this.f5947k0.clear();
            this.S0 = 0;
            this.K0 = 0;
            this.C0 = null;
            if (!this.T.isEmpty()) {
                this.I = 0L;
                this.J = false;
                this.H = 0;
            } else if (this.I == 0) {
                this.I = SystemClock.uptimeMillis();
            }
            this.f5939g0 = SystemClock.uptimeMillis();
            invalidate();
        }
    }

    public final void j(Canvas canvas) {
        for (int i7 = 0; i7 < this.K0; i7++) {
            float f7 = this.H0[i7];
            if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                float f8 = f7 / this.I0[i7];
                Paint paint = this.f5932d;
                paint.setColor(this.f5929b0);
                paint.setAlpha((int) (200.0f * f8 * f8 * this.f5927a0));
                canvas.drawCircle(this.D0[i7], this.E0[i7], ((f8 * 0.8f) + 0.6f) * this.J0[i7], paint);
            }
        }
    }

    public final void k(float f7, float f8, int i7, int i8, Canvas canvas) {
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            for (int i11 = 0; i11 < fVar.f8558a.length() && i10 < this.f5969v0; i11++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float b7 = b((1.5f * f7) - ((i10 / this.f5953n0) * 0.5f));
                float f9 = ((i10 * c.FLOAT_TO_DOUBLE) % 100) / 100.0f;
                float f10 = 1.0f - b7;
                this.f5928b.setAlpha((int) (255.0f * b7 * f8));
                int save = canvas.save();
                canvas.translate((f9 - 0.5f) * i7 * 0.9f * f10, (((f9 * 7.0f) % 1.0f) - 0.5f) * i8 * 0.8f * f10);
                h(canvas, i10, valueOf, b7);
                canvas.restoreToCount(save);
                i10++;
            }
            i9++;
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        this.T0 = false;
        Choreographer.getInstance().removeFrameCallback(this.U0);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i7;
        int i8;
        int i9;
        float f7;
        float f8;
        int i10;
        int i11;
        f fVar;
        Paint paint;
        int i12;
        float f9;
        Canvas canvas2 = canvas;
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        int[] iArr = this.U;
        int i13 = ((iArr[2] << 7) ^ (iArr[0] ^ (iArr[1] << 3))) ^ (this.V ? -1640531527 : 0);
        Bitmap bitmap = this.N;
        if (bitmap == null || this.O != width || this.P != height || this.Q != i13) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.N = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas3 = new Canvas(this.N);
            float f10 = width;
            float f11 = height;
            int[] iArr2 = this.U;
            int[] iArr3 = {iArr2[0], iArr2[1], iArr2[2]};
            float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 0.55f, 1.0f};
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            LinearGradient linearGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f10, f11, iArr3, fArr, tileMode);
            Paint paint2 = this.f5926a;
            paint2.setShader(linearGradient);
            canvas3.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f10, f11, paint2);
            paint2.setShader(new RadialGradient(f10 * 0.5f, f11 * 0.5f, f11 * 0.8f, new int[]{654311423, d1.MEASURED_SIZE_MASK}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f}, tileMode));
            canvas3.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f10, f11, paint2);
            paint2.setShader(null);
            if (this.V) {
                paint2.setColor(1543503872);
                canvas3.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f10, f11, paint2);
                paint2.setColor(d1.MEASURED_STATE_MASK);
            }
            this.O = width;
            this.P = height;
            this.Q = i13;
        }
        canvas2.drawBitmap(this.N, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        long uptimeMillis = SystemClock.uptimeMillis();
        float f12 = uptimeMillis / 1000.0f;
        float f13 = height;
        float c7 = c(16.0f) + f13;
        int i14 = 0;
        for (int i15 = 2; i14 < i15; i15 = 2) {
            boolean z6 = i14 == 0;
            float f14 = width;
            float f15 = z6 ? f14 * 0.05f : f14 * 0.95f;
            float sin = (float) Math.sin((i14 * 2.2f) + (0.4f * f12));
            double A = a.A(sin, width, 0.14f, width * (z6 ? 0.36f : 0.64f)) - f15;
            long j7 = uptimeMillis;
            double A2 = c7 - a.A(sin, f13, 0.05f, 0.06f * f13);
            float f16 = f12;
            float hypot = (float) Math.hypot(A, A2);
            float degrees = (float) Math.toDegrees(Math.atan2(A, A2));
            if (this.f5938g == null || this.f5940h < hypot) {
                this.f5940h = Math.max(1.08f * hypot, c(300.0f));
                int i16 = this.f5929b0 & d1.MEASURED_SIZE_MASK;
                this.f5938g = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -this.f5940h, new int[]{1879048192 | i16, i16 | 570425344, d1.MEASURED_SIZE_MASK}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.45f, 1.0f}, Shader.TileMode.CLAMP);
            }
            float c8 = c(24.0f);
            float c9 = c(215.0f);
            Path path = this.f5942i;
            path.reset();
            path.moveTo(-c8, CropImageView.DEFAULT_ASPECT_RATIO);
            float f17 = -hypot;
            path.lineTo(-c9, f17);
            a.o(path, c9, f17, c8, CropImageView.DEFAULT_ASPECT_RATIO);
            int save = canvas.save();
            canvas.translate(f15, c7);
            canvas.rotate(degrees);
            Paint paint3 = this.f5944j;
            paint3.setShader(this.f5938g);
            paint3.setAlpha((int) (this.f5927a0 * 160.0f));
            canvas.drawPath(path, paint3);
            paint3.setShader(null);
            paint3.setColor(this.f5929b0);
            paint3.setAlpha((int) (this.f5927a0 * 210.0f));
            canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, c(7.0f), paint3);
            paint3.setAlpha(60);
            canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, c(22.0f), paint3);
            canvas.restoreToCount(save);
            i14++;
            canvas2 = canvas;
            f12 = f16;
            uptimeMillis = j7;
        }
        Canvas canvas4 = canvas2;
        long j8 = uptimeMillis;
        float f18 = f12;
        h hVar = this.C0;
        float f19 = 100.0f;
        if (hVar != null) {
            float f20 = (j8 - hVar.f8569i) / 620.0f;
            if (f20 >= 1.0f) {
                this.C0 = null;
            } else {
                hVar.f8561a.size();
                h hVar2 = this.C0;
                float f21 = hVar2.f8567g;
                Paint paint4 = this.f5928b;
                paint4.setTextSize(hVar2.f8566f);
                paint4.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                int max = Math.max(1, this.C0.f8568h);
                int i17 = 0;
                int i18 = 0;
                while (i17 < this.C0.f8561a.size()) {
                    g gVar = (g) this.C0.f8561a.get(i17);
                    int i19 = i18;
                    int i20 = 0;
                    while (i20 < gVar.f8560a.length() && i19 < this.C0.f8565e) {
                        String valueOf = String.valueOf(gVar.f8560a.charAt(i20));
                        float measureText = paint4.measureText(valueOf);
                        g gVar2 = gVar;
                        float f22 = ((i19 * 89) % 100) / f19;
                        float f23 = f18;
                        float b7 = b((f20 - ((i19 / max) * 0.35f)) / 0.65f);
                        if (b7 < 1.0f) {
                            float c10 = (f22 + 0.5f) * c(70.0f) * b7;
                            i9 = max;
                            float f24 = 1.0f - (b7 * 0.25f);
                            float f25 = 1.0f - b7;
                            int i21 = (int) (f25 * 255.0f * f25);
                            f8 = f20;
                            f7 = f13;
                            i8 = height;
                            i7 = width;
                            i10 = i17;
                            paint4.setColor(Color.argb((int) (((Color.alpha(-37632) - Color.alpha(-1)) * b7) + Color.alpha(-1)), (int) (((Color.red(-37632) - Color.red(-1)) * b7) + Color.red(-1)), (int) (((Color.green(-37632) - Color.green(-1)) * b7) + Color.green(-1)), (int) (((Color.blue(-37632) - Color.blue(-1)) * b7) + Color.blue(-1))));
                            paint4.setAlpha(i21);
                            int save2 = canvas.save();
                            h hVar3 = this.C0;
                            canvas4.translate((measureText * 0.5f) + hVar3.f8562b[i19], hVar3.f8563c[i19] - c10);
                            canvas4.rotate(this.C0.f8564d[i19]);
                            canvas4.scale(f24, f24);
                            canvas4.drawText(valueOf, (-measureText) * 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, paint4);
                            canvas4.restoreToCount(save2);
                        } else {
                            i7 = width;
                            i8 = height;
                            i9 = max;
                            f7 = f13;
                            f8 = f20;
                            i10 = i17;
                        }
                        i19++;
                        i20++;
                        gVar = gVar2;
                        f18 = f23;
                        f20 = f8;
                        max = i9;
                        i17 = i10;
                        f13 = f7;
                        height = i8;
                        width = i7;
                        f19 = 100.0f;
                    }
                    i17++;
                    i18 = i19;
                    f18 = f18;
                    f20 = f20;
                    max = max;
                    f13 = f13;
                    height = height;
                    width = width;
                    f19 = 100.0f;
                }
            }
        }
        int i22 = width;
        int i23 = height;
        float f26 = f13;
        float f27 = f18;
        int i24 = 255;
        if (this.T.isEmpty()) {
            if (this.J) {
                if (this.H <= 0 && i22 > 0 && i23 > 0) {
                    z.b0();
                    Bitmap bitmap2 = z.f7690c;
                    if (bitmap2 != null) {
                        this.f5974y = bitmap2;
                        float c11 = c(1.0f);
                        this.H = this.f5976z.length;
                        int i25 = 0;
                        while (i25 < this.H) {
                            int nextInt = this.f5934e.nextInt(70);
                            this.f5976z[i25] = nextInt;
                            this.E[i25] = Math.max(14.0f, ((this.f5934e.nextFloat() * 0.6f) + 0.75f) * r4.b.f8520c[nextInt] * c11 * 2.4f);
                            boolean nextBoolean = this.f5934e.nextBoolean();
                            float[] fArr2 = this.A;
                            if (nextBoolean) {
                                f9 = -this.E[i25];
                                i12 = i22;
                            } else {
                                i12 = i22;
                                f9 = i12 + this.E[i25];
                            }
                            fArr2[i25] = f9;
                            float f28 = f26;
                            this.B[i25] = a.b(this.f5934e, 0.78f, 0.08f, f28);
                            float b8 = a.b(this.f5934e, 58.0f, 26.0f, c11);
                            float[] fArr3 = this.C;
                            if (!nextBoolean) {
                                b8 = -b8;
                            }
                            fArr3[i25] = b8;
                            this.D[i25] = (this.f5934e.nextFloat() - 0.5f) * 10.0f * c11;
                            this.F[i25] = (this.f5934e.nextFloat() * 0.42f) + 0.5f;
                            this.G[i25] = this.f5934e.nextFloat() * 6.28f;
                            i25++;
                            i22 = i12;
                            f26 = f28;
                        }
                    }
                }
                float f29 = f26;
                int i26 = i22;
                if (this.H != 0 && this.f5974y != null) {
                    int i27 = 0;
                    while (true) {
                        int i28 = this.H;
                        paint = this.f5932d;
                        if (i27 >= i28) {
                            break;
                        }
                        int i29 = r4.b.f8519b[this.f5976z[i27]];
                        int i30 = (i29 % 8) * 64;
                        int i31 = (i29 / 8) * 64;
                        Rect rect = this.K;
                        rect.set(i30, i31, i30 + 64, i31 + 64);
                        RectF rectF = this.L;
                        float f30 = this.A[i27];
                        float f31 = this.E[i27];
                        float f32 = this.B[i27];
                        rectF.set(f30 - f31, f32 - f31, f30 + f31, f32 + f31);
                        paint.setAlpha((int) (this.F[i27] * 255.0f));
                        canvas4.drawBitmap(this.f5974y, rect, rectF, paint);
                        i27++;
                    }
                    paint.setAlpha(255);
                }
                Paint paint5 = this.f5928b;
                paint5.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                paint5.setTextSize(c(46.0f));
                paint5.setColor(this.W);
                paint5.setAlpha(72);
                canvas4.drawText("暂无歌词", (i26 - paint5.measureText("暂无歌词")) * 0.5f, f29 * 0.5f, paint5);
                paint5.setAlpha(255);
            }
            j(canvas);
            i(canvas);
            return;
        }
        float f33 = f26;
        if (this.f5937f0 < 0 || this.f5947k0.isEmpty()) {
            j(canvas);
            return;
        }
        float b9 = b((j8 - this.f5939g0) / 1150.0f);
        long j9 = j8 - this.f5939g0;
        float sin2 = (((float) Math.sin(1.9f * f27)) * 0.016f) + 1.0f;
        float sin3 = ((float) Math.sin(f27 * 0.9f)) * c(4.5f);
        float sin4 = ((float) Math.sin(f27 * 0.55f)) * 0.7f;
        if (j9 < 1150) {
            sin3 = CropImageView.DEFAULT_ASPECT_RATIO;
            sin4 = CropImageView.DEFAULT_ASPECT_RATIO;
            sin2 = 1.0f;
        }
        int save3 = canvas.save();
        float f34 = i22;
        float f35 = f34 * 0.5f;
        float f36 = f33 * 0.5f;
        canvas4.rotate(sin4, f35, f36);
        canvas4.translate(CropImageView.DEFAULT_ASPECT_RATIO, sin3);
        canvas4.scale(sin2, sin2, f35, f36);
        float f37 = 1.6f;
        char c12 = 23593;
        float f38 = 1.4f;
        float f39 = 2.0f;
        switch (this.f5941h0) {
            case 0:
                i11 = save3;
                K(canvas4, i22, i23, b9);
                break;
            case 1:
                i11 = save3;
                J(canvas4, i23, b9);
                break;
            case 2:
                i11 = save3;
                O(canvas4, i22, i23, b9);
                break;
            case 3:
                i11 = save3;
                I(canvas4, b9);
                break;
            case 4:
                i11 = save3;
                z(canvas4, b9);
                break;
            case 5:
                i11 = save3;
                y(canvas4, i22, i23, b9);
                break;
            case 6:
                i11 = save3;
                H(canvas4, i22, i23, b9);
                break;
            case 7:
                i11 = save3;
                A(canvas4, i22, i23, b9);
                break;
            case 8:
                i11 = save3;
                U(canvas4, b9);
                break;
            case 9:
                i11 = save3;
                V(canvas4, i22, i23, b9);
                break;
            case 10:
                i11 = save3;
                Q(canvas4, b9);
                break;
            case 11:
                i11 = save3;
                N(canvas4, i22, i23, b9);
                break;
            case 12:
                i11 = save3;
                p(canvas4, i22, i23, b9);
                break;
            case 13:
                i11 = save3;
                R(canvas4, i22, i23, b9);
                break;
            case 14:
                i11 = save3;
                P(canvas4, i22, i23, b9);
                break;
            case 15:
                i11 = save3;
                G(canvas4, i22, i23, b9);
                break;
            case 16:
                i11 = save3;
                L(canvas4, i22, i23, b9);
                break;
            case 17:
                i11 = save3;
                B(canvas4, i22, i23, b9);
                break;
            case 18:
                i11 = save3;
                C(canvas4, i22, i23, b9);
                break;
            case 19:
                i11 = save3;
                E(canvas4, b9);
                break;
            case 20:
                i11 = save3;
                u(canvas4, i22, i23, b9);
                break;
            case 21:
                i11 = save3;
                w(canvas4, i23, b9);
                break;
            case 22:
                i11 = save3;
                F(canvas4, i22, i23, b9);
                break;
            case 23:
                i11 = save3;
                D(canvas4, i22, i23, b9);
                break;
            case 24:
                i11 = save3;
                Z(canvas4, i22, i23, b9);
                break;
            case 25:
                i11 = save3;
                v(canvas4, i22, i23, b9);
                break;
            case 26:
                i11 = save3;
                M(canvas4, b9);
                break;
            case 27:
                i11 = save3;
                W(canvas4, b9);
                break;
            case 28:
                i11 = save3;
                t(canvas4, i22, i23, b9);
                break;
            case 29:
                i11 = save3;
                T(canvas4, i22, i23, b9);
                break;
            case 30:
                i11 = save3;
                S(canvas4, i22, i23, b9);
                break;
            case 31:
                i11 = save3;
                Y(canvas4, i22, b9);
                break;
            case 32:
                i11 = save3;
                X(canvas4, i22, b9);
                break;
            case 33:
                i11 = save3;
                s(canvas4, b9);
                break;
            case 34:
                i11 = save3;
                r(canvas4, b9);
                break;
            case 35:
                i11 = save3;
                q(canvas4, i22, i23, b9);
                break;
            case 36:
                i11 = save3;
                float n6 = n(b9);
                Paint paint6 = this.f5928b;
                paint6.setTextSize(this.f5949l0);
                paint6.setColor(1442840575);
                paint6.setAlpha(c.INVOKE_INTERFACE_RANGE);
                f(canvas4, i22, i23, paint6);
                int save4 = canvas.save();
                float f40 = n6 * f34;
                canvas4.clipRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f40, f33);
                paint6.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f34, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{-10036737, -5011201, -32597, -10036737}, (float[]) null, Shader.TileMode.CLAMP));
                paint6.setAlpha(255);
                f(canvas4, i22, i23, paint6);
                paint6.setShader(null);
                canvas4.restoreToCount(save4);
                if (b9 < 1.0f) {
                    Paint paint7 = this.f5932d;
                    paint7.setColor(-1);
                    paint7.setAlpha((int) ((1.0f - b9) * 120.0f));
                    canvas.drawRect(f40 - c(2.0f), 0.3f * f33, c(2.0f) + f40, f33 * 0.7f, paint7);
                }
                paint6.setAlpha(255);
                break;
            case 37:
                i11 = save3;
                a0(canvas4, b9);
                break;
            case 38:
                i11 = save3;
                x(canvas4, i23, b9);
                break;
            case 39:
                i11 = save3;
                Paint paint8 = this.f5928b;
                paint8.setTextSize(this.f5949l0);
                paint8.setColor(this.W);
                int i32 = 0;
                int i33 = 0;
                while (true) {
                    ArrayList arrayList = this.f5947k0;
                    if (i32 >= arrayList.size()) {
                        paint8.setAlpha(255);
                        break;
                    } else {
                        f fVar2 = (f) arrayList.get(i32);
                        int i34 = i33;
                        int i35 = 0;
                        while (i35 < fVar2.f8558a.length() && i34 < this.f5969v0) {
                            String valueOf2 = String.valueOf(fVar2.f8558a.charAt(i35));
                            float b10 = b((b9 - ((i34 / Math.max(1, this.f5953n0)) * 0.35f)) / 0.4f);
                            if (b10 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                                i34++;
                                fVar = fVar2;
                            } else {
                                float n7 = ((1.0f - n(b10)) * 1.4f) + 1.0f;
                                float f41 = this.f5955o0[i34];
                                float[] fArr4 = this.f5961r0;
                                float f42 = (fArr4[i34] * 0.5f) + f41;
                                float[] fArr5 = this.f5957p0;
                                float f43 = fArr5[i34] - (this.f5949l0 * 0.35f);
                                fVar = fVar2;
                                paint8.setAlpha(255);
                                int save5 = canvas.save();
                                canvas4.translate(f42, f43);
                                canvas4.scale(n7, n7);
                                canvas4.drawText(valueOf2, (-fArr4[i34]) * 0.5f, this.f5949l0 * 0.35f, paint8);
                                canvas4.restoreToCount(save5);
                                if (b10 > 0.45f && b10 < 1.0f) {
                                    float f44 = (b10 - 0.45f) / 0.55f;
                                    Paint paint9 = this.f5932d;
                                    paint9.setStyle(Paint.Style.STROKE);
                                    float f45 = 1.0f - f44;
                                    paint9.setStrokeWidth((c(2.4f) * f45) + 1.0f);
                                    paint9.setColor(this.f5929b0);
                                    paint9.setAlpha((int) (this.f5927a0 * f45 * 130.0f));
                                    canvas4.drawCircle(f42, c(4.0f) + fArr5[i34], c(28.0f) * f44, paint9);
                                    paint9.setStyle(Paint.Style.FILL);
                                }
                                i34++;
                            }
                            i35++;
                            fVar2 = fVar;
                        }
                        i32++;
                        i33 = i34;
                    }
                }
                break;
            case 40:
                float n8 = n(b9);
                Paint paint10 = this.f5928b;
                paint10.setTextSize(this.f5949l0);
                paint10.setColor(this.W);
                paint10.setAlpha((int) (b(b9 * 2.0f) * 255.0f));
                int i36 = 0;
                int i37 = 0;
                while (true) {
                    ArrayList arrayList2 = this.f5947k0;
                    if (i36 >= arrayList2.size()) {
                        i11 = save3;
                        paint10.setAlpha(255);
                        break;
                    } else {
                        f fVar3 = (f) arrayList2.get(i36);
                        int i38 = i37;
                        int i39 = 0;
                        while (i39 < fVar3.f8558a.length() && i38 < this.f5969v0) {
                            float f46 = 1.0f - n8;
                            canvas4.drawText(String.valueOf(fVar3.f8558a.charAt(i39)), this.f5955o0[i38] + (((float) Math.sin((b9 * 2.2f) + (i38 * 0.7f) + 1.2f)) * f34 * 0.33f * f46), this.f5957p0[i38] + (((float) Math.sin(r15 * 2.0f)) * f33 * 0.28f * f46), paint10);
                            i38++;
                            i39++;
                            save3 = save3;
                            c12 = 23593;
                        }
                        i36++;
                        i37 = i38;
                        save3 = save3;
                        c12 = c12;
                    }
                }
            case 41:
                float n9 = n(b9);
                Paint paint11 = this.f5928b;
                paint11.setTextSize(this.f5949l0);
                paint11.setColor(this.W);
                paint11.setAlpha((int) (b(b9 * 2.0f) * 255.0f));
                int i40 = 0;
                while (true) {
                    ArrayList arrayList3 = this.f5947k0;
                    if (i40 >= arrayList3.size()) {
                        paint11.setAlpha(255);
                        i11 = save3;
                        break;
                    } else {
                        f fVar4 = (f) arrayList3.get(i40);
                        float f47 = (1.0f - n9) * (i40 % 2 == 0 ? -1.0f : 1.0f);
                        canvas4.drawText(fVar4.f8558a, a.a(f34, fVar4.f8559b, 0.5f, f47 * f34 * 0.6f), (i40 * this.f5951m0) + a(i23) + (c(22.0f) * f47), paint11);
                        i40++;
                    }
                }
            case 42:
                Paint paint12 = this.f5928b;
                paint12.setTextSize(this.f5949l0);
                paint12.setColor(this.W);
                int i41 = 0;
                int i42 = 0;
                while (true) {
                    ArrayList arrayList4 = this.f5947k0;
                    if (i41 >= arrayList4.size()) {
                        paint12.setAlpha(255);
                        i11 = save3;
                        break;
                    } else {
                        f fVar5 = (f) arrayList4.get(i41);
                        int i43 = i42;
                        for (int i44 = 0; i44 < fVar5.f8558a.length() && i43 < this.f5969v0; i44++) {
                            String valueOf3 = String.valueOf(fVar5.f8558a.charAt(i44));
                            float b11 = b((1.7f * b9) - ((i43 / Math.max(1, this.f5953n0)) * 0.7f));
                            float l7 = (l(b11) * 0.92f) + 0.08f;
                            paint12.setAlpha((int) (Math.min(1.0f, b11 * 1.6f) * 255.0f));
                            int save6 = canvas.save();
                            float f48 = this.f5955o0[i43];
                            float[] fArr6 = this.f5961r0;
                            canvas4.translate((fArr6[i43] * 0.5f) + f48, this.f5957p0[i43] - (this.f5949l0 * 0.35f));
                            canvas4.scale(l7, l7);
                            canvas4.drawText(valueOf3, (-fArr6[i43]) * 0.5f, this.f5949l0 * 0.35f, paint12);
                            canvas4.restoreToCount(save6);
                            i43++;
                        }
                        i41++;
                        i42 = i43;
                    }
                }
            case 43:
                int i45 = 0;
                Paint paint13 = this.f5928b;
                paint13.setTextSize(this.f5949l0);
                paint13.setColor(this.W);
                int i46 = 0;
                int i47 = 0;
                while (true) {
                    ArrayList arrayList5 = this.f5947k0;
                    if (i46 >= arrayList5.size()) {
                        paint13.setAlpha(i24);
                        i11 = save3;
                        break;
                    } else {
                        f fVar6 = (f) arrayList5.get(i46);
                        int i48 = i47;
                        int i49 = i45;
                        while (i49 < fVar6.f8558a.length() && i48 < this.f5969v0) {
                            String valueOf4 = String.valueOf(fVar6.f8558a.charAt(i49));
                            float b12 = b((b9 * f37) - ((i48 / Math.max(1, this.f5953n0)) * 0.5f));
                            float n10 = (n(b12) * f39) - 1.0f;
                            paint13.setAlpha((int) (Math.min(1.0f, b12 * 2.5f) * 255.0f));
                            int save7 = canvas.save();
                            float f49 = this.f5955o0[i48];
                            float[] fArr7 = this.f5961r0;
                            canvas4.translate((fArr7[i48] * 0.5f) + f49, this.f5957p0[i48] - (this.f5949l0 * 0.35f));
                            canvas4.scale(1.0f, n10);
                            canvas4.drawText(valueOf4, (-fArr7[i48]) * 0.5f, this.f5949l0 * 0.35f, paint13);
                            canvas4.restoreToCount(save7);
                            i48++;
                            i49++;
                            f39 = 2.0f;
                            f37 = 1.6f;
                        }
                        i46++;
                        i47 = i48;
                        i45 = 0;
                        f39 = 2.0f;
                        f37 = 1.6f;
                        i24 = 255;
                    }
                }
            case 44:
                float n11 = n(b9);
                float A3 = a.A(this.f5947k0.size() - 1, this.f5951m0, 0.5f, a(i23));
                Paint paint14 = this.f5928b;
                paint14.setTextSize(this.f5949l0);
                paint14.setColor(this.W);
                paint14.setAlpha((int) (b(2.0f * b9) * 255.0f));
                int save8 = canvas.save();
                canvas4.translate(CropImageView.DEFAULT_ASPECT_RATIO, A3);
                canvas4.skew((1.0f - n11) * 0.45f * ((float) Math.sin(b9 * 9.0f)), CropImageView.DEFAULT_ASPECT_RATIO);
                canvas4.translate(CropImageView.DEFAULT_ASPECT_RATIO, -A3);
                f(canvas4, i22, i23, paint14);
                canvas4.restoreToCount(save8);
                i11 = save3;
                break;
            case 45:
                float f50 = (b9 >= 0.5f || Math.abs((float) Math.sin((double) (((float) ((int) (b9 * 24.0f))) * 12.9898f))) > 0.45f) ? 1.0f : 0.08f;
                Paint paint15 = this.f5928b;
                paint15.setTextSize(this.f5949l0);
                paint15.setColor(this.W);
                paint15.setAlpha((int) (255.0f * f50));
                paint15.setShadowLayer(c(14.0f), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5929b0);
                f(canvas4, i22, i23, paint15);
                paint15.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                paint15.setAlpha(255);
                i11 = save3;
                break;
            case 46:
                float n12 = n(b9);
                float A4 = a.A(this.f5947k0.size() - 1, this.f5951m0, 0.5f, a(i23));
                Paint paint16 = this.f5928b;
                paint16.setTextSize(this.f5949l0);
                paint16.setColor(this.W);
                paint16.setAlpha((int) (b(b9 * 2.5f) * 255.0f));
                int save9 = canvas.save();
                canvas4.translate((1.0f - n12) * f34 * 0.9f, CropImageView.DEFAULT_ASPECT_RATIO);
                canvas4.scale((0.28f * n12) + 0.72f, 1.0f, f35, A4);
                f(canvas4, i22, i23, paint16);
                canvas4.restoreToCount(save9);
                i11 = save3;
                break;
            case 47:
                float n13 = (1.0f - n(b9)) * ((float) Math.sin(10.0f * b9)) * 11.0f;
                Paint paint17 = this.f5928b;
                paint17.setTextSize(this.f5949l0);
                paint17.setColor(this.W);
                paint17.setAlpha((int) (b(b9 * 2.0f) * 255.0f));
                int save10 = canvas.save();
                canvas4.rotate(n13, f35, f33 * 0.1f);
                f(canvas4, i22, i23, paint17);
                canvas4.restoreToCount(save10);
                i11 = save3;
                break;
            default:
                Paint paint18 = this.f5928b;
                paint18.setTextSize(this.f5949l0);
                paint18.setColor(this.W);
                int i50 = 0;
                int i51 = 0;
                while (true) {
                    ArrayList arrayList6 = this.f5947k0;
                    if (i50 >= arrayList6.size()) {
                        paint18.setAlpha(255);
                        i11 = save3;
                        break;
                    } else {
                        f fVar7 = (f) arrayList6.get(i50);
                        int i52 = i51;
                        int i53 = 0;
                        while (i53 < fVar7.f8558a.length() && i52 < this.f5969v0) {
                            String valueOf5 = String.valueOf(fVar7.f8558a.charAt(i53));
                            float f51 = ((i52 * c.FLOAT_TO_DOUBLE) % 100) / 100.0f;
                            float b13 = b((b9 * f38) - (f51 * 0.4f));
                            float n14 = 1.0f - n(b13);
                            float f52 = ((f51 * f33 * 0.25f) + (0.2f * f33)) * n14;
                            float f53 = f33;
                            float c13 = c(24.0f) * ((float) Math.sin((f51 * 6.2832f) + (5.5f * b13))) * n14;
                            paint18.setAlpha((int) (Math.min(1.0f, b13 * 2.5f) * 255.0f));
                            canvas4.drawText(valueOf5, this.f5955o0[i52] + c13, this.f5957p0[i52] - f52, paint18);
                            i52++;
                            i53++;
                            f33 = f53;
                            f38 = 1.4f;
                        }
                        i50++;
                        i51 = i52;
                        f33 = f33;
                        f38 = 1.4f;
                    }
                }
        }
        canvas4.restoreToCount(i11);
        j(canvas);
        i(canvas);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.W0.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
        if (i7 == 0 && this.T0) {
            Choreographer choreographer = Choreographer.getInstance();
            b bVar = this.U0;
            choreographer.removeFrameCallback(bVar);
            Choreographer.getInstance().postFrameCallback(bVar);
        }
    }

    public final void p(Canvas canvas, int i7, int i8, float f7) {
        float n6 = n(f7);
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            float f8 = i7;
            float f9 = fVar.f8559b;
            int i11 = 0;
            while (i11 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float measureText = paint.measureText(valueOf);
                float[] fArr = this.f5955o0;
                float f10 = fArr[i10];
                float f11 = (this.f5953n0 <= 1 ? 0.5f : i10 / (r15 - 1)) - 0.5f;
                float f12 = 0.32f * f8;
                f fVar2 = fVar;
                double d7 = 2.4f * f11;
                float f13 = f8;
                float f14 = measureText * 0.5f;
                float sin = ((((float) Math.sin(d7)) * f12) + (f8 * 0.5f)) - f14;
                float cos = (this.f5949l0 * 0.35f) + ((i8 * 0.5f) - ((((float) Math.cos(d7)) * f12) * 0.5f));
                float a7 = a.a(f10, sin, n6, sin);
                float a8 = a.a(this.f5957p0[i10], cos, n6, cos);
                paint.setAlpha((int) (b(2.0f * f7) * 255.0f));
                float f15 = (1.0f - n6) * f11 * 40.0f;
                int save = canvas.save();
                canvas.rotate(f15, a7 + f14, a8 - (this.f5949l0 * 0.3f));
                canvas.drawText(valueOf, fArr[i10], a8, paint);
                canvas.restoreToCount(save);
                i10++;
                i11++;
                f8 = f13;
                fVar = fVar2;
            }
            i9++;
        }
    }

    public final void q(Canvas canvas, int i7, int i8, float f7) {
        float n6 = 1.0f - n(f7);
        float c7 = c(18.0f) * n6;
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        for (int i9 = 0; i9 < 5; i9++) {
            paint.setColor(this.W);
            paint.setAlpha((int) (50.0f * n6));
            int save = canvas.save();
            double d7 = ((float) ((i9 * 6.283185307179586d) / 5)) + 0.7f;
            canvas.translate(((float) Math.cos(d7)) * c7, ((float) Math.sin(d7)) * c7);
            f(canvas, i7, i8, paint);
            canvas.restoreToCount(save);
        }
        paint.setColor(this.W);
        paint.setAlpha((int) (b(f7 * 1.8f) * 255.0f));
        f(canvas, i7, i8, paint);
    }

    public final void r(Canvas canvas, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                paint.setAlpha(255);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                float b7 = b((1.6f * f7) - ((i8 / this.f5953n0) * 0.6f));
                float l7 = (1.0f - l(b7)) * 82.0f;
                float[] fArr = this.f5955o0;
                float f8 = (this.f5961r0[i8] * 0.5f) + fArr[i8];
                float[] fArr2 = this.f5957p0;
                float f9 = fArr2[i8] - (this.f5949l0 * 0.35f);
                paint.setAlpha((int) (Math.min(1.0f, b7 * 2.0f) * 255.0f));
                e0(CropImageView.DEFAULT_ASPECT_RATIO, l7, f8, f9);
                int save = canvas.save();
                canvas.concat(this.Y0);
                canvas.drawText(valueOf, fArr[i8], fArr2[i8], paint);
                canvas.restoreToCount(save);
                i8++;
            }
            i7++;
        }
    }

    public final void s(Canvas canvas, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                paint.setAlpha(255);
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                float b7 = b((1.6f * f7) - ((i8 / this.f5953n0) * 0.6f));
                float l7 = (1.0f - l(b7)) * 82.0f;
                float[] fArr = this.f5955o0;
                float f8 = (this.f5961r0[i8] * 0.5f) + fArr[i8];
                float[] fArr2 = this.f5957p0;
                float f9 = fArr2[i8] - (this.f5949l0 * 0.35f);
                paint.setAlpha((int) (Math.min(1.0f, b7 * 2.0f) * 255.0f));
                e0(l7, CropImageView.DEFAULT_ASPECT_RATIO, f8, f9);
                int save = canvas.save();
                canvas.concat(this.Y0);
                canvas.drawText(valueOf, fArr[i8], fArr2[i8], paint);
                canvas.restoreToCount(save);
                i8++;
            }
            i7++;
        }
    }

    public void setNightMode(boolean z6) {
        if (this.V == z6) {
            return;
        }
        this.V = z6;
        this.W = z6 ? -2963012 : -1;
        this.f5927a0 = z6 ? 0.66f : 1.0f;
        this.N = null;
        invalidate();
    }

    public void setOnExitListener(Runnable runnable) {
        this.V0 = runnable;
    }

    public final void t(Canvas canvas, int i7, int i8, float f7) {
        LyricsFxView lyricsFxView = this;
        Paint paint = lyricsFxView.f5928b;
        paint.setTextSize(lyricsFxView.f5949l0);
        int i9 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = lyricsFxView.f5947k0;
            if (i9 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i9);
            int i11 = 0;
            while (i11 < fVar.f8558a.length()) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i11));
                float measureText = paint.measureText(valueOf);
                int i12 = 0;
                while (i12 < 3) {
                    float f8 = (((i12 * 29) + (i10 * 13)) % 97) / 97.0f;
                    float b7 = b((1.7f * f7) - (f8 * 0.5f));
                    float l7 = 1.0f - l(b7);
                    float f9 = (f8 - 0.5f) * i7 * 0.55f * l7;
                    float f10 = (((f8 * 2.3f) % 1.0f) - 0.5f) * i8 * 0.55f * l7;
                    float[] fArr = lyricsFxView.f5957p0;
                    float f11 = fArr[i10];
                    f fVar2 = fVar;
                    float f12 = lyricsFxView.f5949l0;
                    float f13 = f11 - ((1.0f - (i12 / 3.0f)) * f12);
                    int i13 = i9;
                    float f14 = i12 == 2 ? (f12 * 0.34f) + f11 : f11 - ((1.0f - ((i12 + 1) / 3.0f)) * f12);
                    paint.setColor(b7 > 0.9f ? -1 : lyricsFxView.f5929b0);
                    paint.setAlpha((int) (Math.min(1.0f, b7 * 2.0f) * 255.0f));
                    int save = canvas.save();
                    float[] fArr2 = lyricsFxView.f5955o0;
                    float f15 = fArr2[i10];
                    canvas.clipRect(f15 + f9, f13 + f10, f15 + measureText + f9, f14 + f10);
                    canvas.drawText(valueOf, fArr2[i10] + f9, fArr[i10] + f10, paint);
                    canvas.restoreToCount(save);
                    i12++;
                    lyricsFxView = this;
                    fVar = fVar2;
                    i9 = i13;
                }
                i10++;
                i11++;
                lyricsFxView = this;
            }
            i9++;
            lyricsFxView = this;
        }
    }

    public final void u(Canvas canvas, int i7, int i8, float f7) {
        float o6 = 1.0f - o(f7);
        d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, c(30.0f) * (-o6), (0.85f * o6) + 1.0f, b(f7 * 2.6f));
    }

    public final void v(Canvas canvas, int i7, int i8, float f7) {
        float max = Math.max(0.03f, (float) Math.sin(n(f7) * 3.141592653589793d * 0.5d));
        float b7 = b(f7 * 1.6f);
        int save = canvas.save();
        canvas.scale(max, 1.0f, i7 * 0.5f, i8 * 0.5f);
        a(i8);
        e(CropImageView.DEFAULT_ASPECT_RATIO, b7, canvas);
        canvas.restoreToCount(save);
    }

    public final void w(Canvas canvas, int i7, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        paint.setShadowLayer(c(10.0f), CropImageView.DEFAULT_ASPECT_RATIO, c(3.0f), -2013265920);
        int i8 = 0;
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i8 >= arrayList.size()) {
                paint.setShadowLayer(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                return;
            }
            f fVar = (f) arrayList.get(i8);
            for (int i10 = 0; i10 < fVar.f8558a.length(); i10++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i10));
                paint.measureText(valueOf);
                float b7 = b((f7 - ((i9 / Math.max(1, this.f5953n0)) * 0.32f)) / 0.62f);
                float m6 = i7 * 0.55f * (1.0f - m(b7));
                paint.setAlpha((int) (Math.min(1.0f, b7 * 4.0f) * 255.0f));
                canvas.drawText(valueOf, this.f5955o0[i9], this.f5957p0[i9] - m6, paint);
                i9++;
            }
            i8++;
        }
    }

    public final void x(Canvas canvas, int i7, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i8 = 0;
        int i9 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i8 >= arrayList.size()) {
                paint.setAlpha(255);
                return;
            }
            f fVar = (f) arrayList.get(i8);
            for (int i10 = 0; i10 < fVar.f8558a.length() && i9 < this.f5969v0; i10++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i10));
                float b7 = b((f7 - ((i9 / Math.max(1, this.f5953n0)) * 0.3f)) / 0.7f);
                float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
                if (b7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                    if (b7 >= 1.0f) {
                        f8 = 1.0f;
                    } else {
                        double d7 = b7;
                        f8 = (float) ((Math.sin(((d7 * 10.0d) - 0.75d) * 2.0943952f) * Math.pow(2.0d, (-10.0d) * d7)) + 1.0d);
                    }
                }
                paint.setAlpha((int) (Math.min(1.0f, b7 * 3.0f) * 255.0f));
                canvas.drawText(valueOf, this.f5955o0[i9], this.f5957p0[i9] - (((1.0f - f8) * i7) * 0.5f), paint);
                i9++;
            }
            i8++;
        }
    }

    public final void y(Canvas canvas, int i7, int i8, float f7) {
        float o6 = 1.0f - o(f7);
        float f8 = (1.5f * o6) + 1.0f;
        float c7 = c(12.0f) * o6;
        int save = canvas.save();
        Random random = this.f5934e;
        canvas.translate((random.nextFloat() - 0.5f) * c7, (random.nextFloat() - 0.5f) * c7);
        d(canvas, i7, i8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f8, Math.min(1.0f, f7 * 2.2f));
        canvas.restoreToCount(save);
    }

    public final void z(Canvas canvas, float f7) {
        Paint paint = this.f5928b;
        paint.setTextSize(this.f5949l0);
        paint.setColor(this.W);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            ArrayList arrayList = this.f5947k0;
            if (i7 >= arrayList.size()) {
                return;
            }
            f fVar = (f) arrayList.get(i7);
            for (int i9 = 0; i9 < fVar.f8558a.length() && i8 < this.f5969v0; i9++) {
                String valueOf = String.valueOf(fVar.f8558a.charAt(i9));
                float f8 = this.f5961r0[i8];
                float b7 = b((f7 - ((i8 / this.f5953n0) * 0.55f)) / 0.45f);
                float max = Math.max(0.05f, l(b7));
                int save = canvas.save();
                canvas.translate((f8 * 0.5f) + this.f5955o0[i8], this.f5957p0[i8] - (this.f5949l0 * 0.35f));
                canvas.scale(max, 1.0f);
                paint.setAlpha((int) (Math.min(1.0f, b7 * 1.5f) * 255.0f));
                canvas.drawText(valueOf, (-f8) * 0.5f, this.f5949l0 * 0.35f, paint);
                canvas.restoreToCount(save);
                i8++;
            }
            i7++;
        }
    }
}
