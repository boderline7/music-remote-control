package y;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.utils.widget.MotionLabel;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;

    /* renamed from: b, reason: collision with root package name */
    public final View f9867b;

    /* renamed from: c, reason: collision with root package name */
    public final int f9868c;

    /* renamed from: j, reason: collision with root package name */
    public t.d[] f9875j;

    /* renamed from: k, reason: collision with root package name */
    public t.b f9876k;

    /* renamed from: o, reason: collision with root package name */
    public int[] f9880o;

    /* renamed from: p, reason: collision with root package name */
    public double[] f9881p;

    /* renamed from: q, reason: collision with root package name */
    public double[] f9882q;

    /* renamed from: r, reason: collision with root package name */
    public String[] f9883r;

    /* renamed from: s, reason: collision with root package name */
    public int[] f9884s;

    /* renamed from: x, reason: collision with root package name */
    public HashMap f9889x;

    /* renamed from: y, reason: collision with root package name */
    public HashMap f9890y;

    /* renamed from: z, reason: collision with root package name */
    public HashMap f9891z;

    /* renamed from: a, reason: collision with root package name */
    public final Rect f9866a = new Rect();

    /* renamed from: d, reason: collision with root package name */
    public boolean f9869d = false;

    /* renamed from: e, reason: collision with root package name */
    public int f9870e = -1;

    /* renamed from: f, reason: collision with root package name */
    public final x f9871f = new x();

    /* renamed from: g, reason: collision with root package name */
    public final x f9872g = new x();

    /* renamed from: h, reason: collision with root package name */
    public final l f9873h = new l();

    /* renamed from: i, reason: collision with root package name */
    public final l f9874i = new l();

    /* renamed from: l, reason: collision with root package name */
    public float f9877l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    public float f9878m = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: n, reason: collision with root package name */
    public float f9879n = 1.0f;

    /* renamed from: t, reason: collision with root package name */
    public final float[] f9885t = new float[4];

    /* renamed from: u, reason: collision with root package name */
    public final ArrayList f9886u = new ArrayList();

    /* renamed from: v, reason: collision with root package name */
    public final float[] f9887v = new float[1];

    /* renamed from: w, reason: collision with root package name */
    public final ArrayList f9888w = new ArrayList();
    public int A = -1;
    public int B = -1;
    public View C = null;
    public int D = -1;
    public float E = Float.NaN;
    public Interpolator F = null;
    public boolean G = false;

    public n(View view) {
        this.f9867b = view;
        this.f9868c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof a0.f) {
            ((a0.f) layoutParams).getClass();
        }
    }

    public static void g(Rect rect, Rect rect2, int i7, int i8, int i9) {
        if (i7 == 1) {
            int i10 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i9 - ((rect.height() + i10) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i7 == 2) {
            int i11 = rect.left + rect.right;
            rect2.left = i8 - ((rect.width() + (rect.top + rect.bottom)) / 2);
            rect2.top = (i11 - rect.height()) / 2;
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i7 == 3) {
            int i12 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i12 / 2);
            rect2.top = i9 - ((rect.height() + i12) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i7 != 4) {
            return;
        }
        int i13 = rect.left + rect.right;
        rect2.left = i8 - ((rect.width() + (rect.bottom + rect.top)) / 2);
        rect2.top = (i13 - rect.height()) / 2;
        rect2.right = rect.width() + rect2.left;
        rect2.bottom = rect.height() + rect2.top;
    }

    public final void a(c cVar) {
        this.f9888w.add(cVar);
    }

    public final float b(float f7, float[] fArr) {
        float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f9 = this.f9879n;
            if (f9 != 1.0d) {
                float f10 = this.f9878m;
                if (f7 < f10) {
                    f7 = 0.0f;
                }
                if (f7 > f10 && f7 < 1.0d) {
                    f7 = Math.min((f7 - f10) * f9, 1.0f);
                }
            }
        }
        t.f fVar = this.f9871f.f9927a;
        Iterator it = this.f9886u.iterator();
        float f11 = Float.NaN;
        while (it.hasNext()) {
            x xVar = (x) it.next();
            t.f fVar2 = xVar.f9927a;
            if (fVar2 != null) {
                float f12 = xVar.f9929c;
                if (f12 < f7) {
                    fVar = fVar2;
                    f8 = f12;
                } else if (Float.isNaN(f11)) {
                    f11 = xVar.f9929c;
                }
            }
        }
        if (fVar != null) {
            float f13 = (Float.isNaN(f11) ? 1.0f : f11) - f8;
            double d7 = (f7 - f8) / f13;
            f7 = (((float) fVar.a(d7)) * f13) + f8;
            if (fArr != null) {
                fArr[0] = (float) fVar.b(d7);
            }
        }
        return f7;
    }

    public final void c(double d7, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.f9875j[0].c(d7, dArr);
        this.f9875j[0].f(d7, dArr2);
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        Arrays.fill(fArr2, CropImageView.DEFAULT_ASPECT_RATIO);
        int[] iArr = this.f9880o;
        x xVar = this.f9871f;
        float f8 = xVar.f9931e;
        float f9 = xVar.f9932f;
        float f10 = xVar.f9933g;
        float f11 = xVar.f9934h;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        for (int i7 = 0; i7 < iArr.length; i7++) {
            float f15 = (float) dArr[i7];
            float f16 = (float) dArr2[i7];
            int i8 = iArr[i7];
            if (i8 == 1) {
                f8 = f15;
                f7 = f16;
            } else if (i8 == 2) {
                f9 = f15;
                f14 = f16;
            } else if (i8 == 3) {
                f10 = f15;
                f12 = f16;
            } else if (i8 == 4) {
                f11 = f15;
                f13 = f16;
            }
        }
        float f17 = 2.0f;
        float f18 = (f12 / 2.0f) + f7;
        float f19 = (f13 / 2.0f) + f14;
        n nVar = xVar.f9939m;
        if (nVar != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            nVar.c(d7, fArr3, fArr4);
            float f20 = fArr3[0];
            float f21 = fArr3[1];
            float f22 = fArr4[0];
            float f23 = fArr4[1];
            double d8 = f8;
            double d9 = f9;
            float sin = (float) (((Math.sin(d9) * d8) + f20) - (f10 / 2.0f));
            float cos = (float) ((f21 - (Math.cos(d9) * d8)) - (f11 / 2.0f));
            double d10 = f22;
            double d11 = f7;
            double d12 = f14;
            float cos2 = (float) ((Math.cos(d9) * d12) + (Math.sin(d9) * d11) + d10);
            f19 = (float) ((Math.sin(d9) * d12) + (f23 - (Math.cos(d9) * d11)));
            f8 = sin;
            f9 = cos;
            f18 = cos2;
            f17 = 2.0f;
        }
        fArr[0] = (f10 / f17) + f8 + CropImageView.DEFAULT_ASPECT_RATIO;
        fArr[1] = (f11 / f17) + f9 + CropImageView.DEFAULT_ASPECT_RATIO;
        fArr2[0] = f18;
        fArr2[1] = f19;
    }

    public final void d(float f7, float f8, float f9, float[] fArr) {
        double[] dArr;
        float[] fArr2 = this.f9887v;
        float b7 = b(f7, fArr2);
        t.d[] dVarArr = this.f9875j;
        int i7 = 0;
        if (dVarArr == null) {
            x xVar = this.f9872g;
            float f10 = xVar.f9931e;
            x xVar2 = this.f9871f;
            float f11 = f10 - xVar2.f9931e;
            float f12 = xVar.f9932f - xVar2.f9932f;
            float f13 = xVar.f9933g - xVar2.f9933g;
            float f14 = (xVar.f9934h - xVar2.f9934h) + f12;
            fArr[0] = ((f13 + f11) * f8) + ((1.0f - f8) * f11);
            fArr[1] = (f14 * f9) + ((1.0f - f9) * f12);
            return;
        }
        double d7 = b7;
        dVarArr[0].f(d7, this.f9882q);
        this.f9875j[0].c(d7, this.f9881p);
        float f15 = fArr2[0];
        while (true) {
            dArr = this.f9882q;
            if (i7 >= dArr.length) {
                break;
            }
            dArr[i7] = dArr[i7] * f15;
            i7++;
        }
        t.b bVar = this.f9876k;
        if (bVar == null) {
            x xVar3 = this.f9871f;
            int[] iArr = this.f9880o;
            double[] dArr2 = this.f9881p;
            xVar3.getClass();
            x.g(f8, f9, fArr, iArr, dArr, dArr2);
            return;
        }
        double[] dArr3 = this.f9881p;
        if (dArr3.length > 0) {
            bVar.c(d7, dArr3);
            this.f9876k.f(d7, this.f9882q);
            x xVar4 = this.f9871f;
            int[] iArr2 = this.f9880o;
            double[] dArr4 = this.f9882q;
            double[] dArr5 = this.f9881p;
            xVar4.getClass();
            x.g(f8, f9, fArr, iArr2, dArr4, dArr5);
        }
    }

    public final boolean e(float f7, long j7, View view, t.g gVar) {
        x.o oVar;
        boolean z6;
        float f8;
        int i7;
        boolean z7;
        double d7;
        float f9;
        x xVar;
        x.o oVar2;
        boolean z8;
        double d8;
        float f10;
        float f11;
        boolean z9;
        float f12;
        double d9;
        float f13;
        n nVar = this;
        View view2 = view;
        float b7 = nVar.b(f7, null);
        int i8 = nVar.D;
        if (i8 != -1) {
            float f14 = 1.0f / i8;
            float floor = ((float) Math.floor(b7 / f14)) * f14;
            float f15 = (b7 % f14) / f14;
            if (!Float.isNaN(nVar.E)) {
                f15 = (f15 + nVar.E) % 1.0f;
            }
            Interpolator interpolator = nVar.F;
            b7 = ((interpolator != null ? interpolator.getInterpolation(f15) : ((double) f15) > 0.5d ? 1.0f : CropImageView.DEFAULT_ASPECT_RATIO) * f14) + floor;
        }
        float f16 = b7;
        HashMap hashMap = nVar.f9890y;
        if (hashMap != null) {
            Iterator it = hashMap.values().iterator();
            while (it.hasNext()) {
                ((x.l) it.next()).c(view2, f16);
            }
        }
        HashMap hashMap2 = nVar.f9889x;
        if (hashMap2 != null) {
            oVar = null;
            z6 = false;
            for (x.q qVar : hashMap2.values()) {
                if (qVar instanceof x.o) {
                    oVar = (x.o) qVar;
                } else {
                    z6 |= qVar.c(f16, j7, view, gVar);
                }
            }
        } else {
            oVar = null;
            z6 = false;
        }
        t.d[] dVarArr = nVar.f9875j;
        x xVar2 = nVar.f9871f;
        if (dVarArr != null) {
            double d10 = f16;
            dVarArr[0].c(d10, nVar.f9881p);
            nVar.f9875j[0].f(d10, nVar.f9882q);
            t.b bVar = nVar.f9876k;
            if (bVar != null) {
                double[] dArr = nVar.f9881p;
                if (dArr.length > 0) {
                    bVar.c(d10, dArr);
                    nVar.f9876k.f(d10, nVar.f9882q);
                }
            }
            if (nVar.G) {
                d7 = d10;
                f9 = f16;
                xVar = xVar2;
                oVar2 = oVar;
                z8 = z6;
            } else {
                int[] iArr = nVar.f9880o;
                double[] dArr2 = nVar.f9881p;
                double[] dArr3 = nVar.f9882q;
                boolean z10 = nVar.f9869d;
                float f17 = xVar2.f9931e;
                float f18 = xVar2.f9932f;
                float f19 = xVar2.f9933g;
                float f20 = xVar2.f9934h;
                if (iArr.length != 0) {
                    f11 = f17;
                    if (xVar2.f9942p.length <= iArr[iArr.length - 1]) {
                        int i9 = iArr[iArr.length - 1] + 1;
                        xVar2.f9942p = new double[i9];
                        xVar2.f9943q = new double[i9];
                    }
                } else {
                    f11 = f17;
                }
                Arrays.fill(xVar2.f9942p, Double.NaN);
                for (int i10 = 0; i10 < iArr.length; i10++) {
                    double[] dArr4 = xVar2.f9942p;
                    int i11 = iArr[i10];
                    dArr4[i11] = dArr2[i10];
                    xVar2.f9943q[i11] = dArr3[i10];
                }
                float f21 = Float.NaN;
                f9 = f16;
                oVar2 = oVar;
                float f22 = f20;
                float f23 = f11;
                float f24 = f18;
                float f25 = CropImageView.DEFAULT_ASPECT_RATIO;
                int i12 = 0;
                float f26 = CropImageView.DEFAULT_ASPECT_RATIO;
                float f27 = CropImageView.DEFAULT_ASPECT_RATIO;
                z8 = z6;
                float f28 = CropImageView.DEFAULT_ASPECT_RATIO;
                while (true) {
                    double[] dArr5 = xVar2.f9942p;
                    z9 = z10;
                    if (i12 >= dArr5.length) {
                        break;
                    }
                    if (Double.isNaN(dArr5[i12])) {
                        d9 = d10;
                        f13 = f21;
                    } else {
                        d9 = d10;
                        float f29 = (float) (Double.isNaN(xVar2.f9942p[i12]) ? 0.0d : xVar2.f9942p[i12] + 0.0d);
                        f13 = f21;
                        float f30 = (float) xVar2.f9943q[i12];
                        if (i12 == 1) {
                            f25 = f30;
                            f23 = f29;
                        } else if (i12 == 2) {
                            f28 = f30;
                            f24 = f29;
                        } else if (i12 == 3) {
                            f26 = f30;
                            f19 = f29;
                        } else if (i12 == 4) {
                            f27 = f30;
                            f22 = f29;
                        } else if (i12 == 5) {
                            f21 = f29;
                            i12++;
                            z10 = z9;
                            d10 = d9;
                        }
                    }
                    f21 = f13;
                    i12++;
                    z10 = z9;
                    d10 = d9;
                }
                d7 = d10;
                float f31 = f21;
                n nVar2 = xVar2.f9939m;
                if (nVar2 != null) {
                    float[] fArr = new float[2];
                    float[] fArr2 = new float[2];
                    nVar2.c(d7, fArr, fArr2);
                    float f32 = fArr[0];
                    float f33 = fArr[1];
                    xVar = xVar2;
                    float f34 = fArr2[0];
                    float f35 = fArr2[1];
                    double d11 = f23;
                    double d12 = f24;
                    float sin = (float) (((Math.sin(d12) * d11) + f32) - (f19 / 2.0f));
                    f24 = (float) ((f33 - (Math.cos(d12) * d11)) - (f22 / 2.0f));
                    double d13 = f25;
                    double d14 = f28;
                    float cos = (float) ((Math.cos(d12) * d11 * d14) + (Math.sin(d12) * d13) + f34);
                    f12 = f19;
                    float sin2 = (float) ((Math.sin(d12) * d11 * d14) + (f35 - (Math.cos(d12) * d13)));
                    if (dArr3.length >= 2) {
                        dArr3[0] = cos;
                        dArr3[1] = sin2;
                    }
                    if (Float.isNaN(f31)) {
                        view2 = view;
                    } else {
                        view2 = view;
                        view2.setRotation((float) (Math.toDegrees(Math.atan2(sin2, cos)) + f31));
                    }
                    f23 = sin;
                } else {
                    float f36 = f25;
                    f12 = f19;
                    xVar = xVar2;
                    if (!Float.isNaN(f31)) {
                        view2.setRotation((float) (Math.toDegrees(Math.atan2((f27 / 2.0f) + f28, (f26 / 2.0f) + f36)) + f31 + CropImageView.DEFAULT_ASPECT_RATIO));
                    }
                }
                if (view2 instanceof b) {
                    ((MotionLabel) ((b) view2)).c(f23, f24, f23 + f12, f22 + f24);
                } else {
                    float f37 = f23 + 0.5f;
                    int i13 = (int) f37;
                    float f38 = f24 + 0.5f;
                    int i14 = (int) f38;
                    int i15 = (int) (f37 + f12);
                    int i16 = (int) (f38 + f22);
                    int i17 = i15 - i13;
                    int i18 = i16 - i14;
                    if (i17 != view.getMeasuredWidth() || i18 != view.getMeasuredHeight() || z9) {
                        view2.measure(View.MeasureSpec.makeMeasureSpec(i17, v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(i18, v.c.EXACTLY));
                    }
                    view2.layout(i13, i14, i15, i16);
                }
                nVar = this;
                nVar.f9869d = false;
            }
            if (nVar.B != -1) {
                if (nVar.C == null) {
                    nVar.C = ((View) view.getParent()).findViewById(nVar.B);
                }
                if (nVar.C != null) {
                    float bottom = (nVar.C.getBottom() + r1.getTop()) / 2.0f;
                    float right = (nVar.C.getRight() + nVar.C.getLeft()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view2.setPivotX(right - view.getLeft());
                        view2.setPivotY(bottom - view.getTop());
                    }
                }
            }
            HashMap hashMap3 = nVar.f9890y;
            if (hashMap3 != null) {
                for (x.l lVar : hashMap3.values()) {
                    if (lVar instanceof x.j) {
                        double[] dArr6 = nVar.f9882q;
                        if (dArr6.length > 1) {
                            f10 = f9;
                            view2.setRotation(((x.j) lVar).a(f10) + ((float) Math.toDegrees(Math.atan2(dArr6[1], dArr6[0]))));
                            f9 = f10;
                        }
                    }
                    f10 = f9;
                    f9 = f10;
                }
            }
            f8 = f9;
            if (oVar2 != null) {
                double[] dArr7 = nVar.f9882q;
                d8 = d7;
                i7 = 1;
                view2.setRotation(oVar2.b(f8, j7, view, gVar) + ((float) Math.toDegrees(Math.atan2(dArr7[1], dArr7[0]))));
                z7 = z8 | oVar2.f9009d;
            } else {
                d8 = d7;
                i7 = 1;
                z7 = z8;
            }
            int i19 = i7;
            while (true) {
                t.d[] dVarArr2 = nVar.f9875j;
                if (i19 >= dVarArr2.length) {
                    break;
                }
                t.d dVar = dVarArr2[i19];
                float[] fArr3 = nVar.f9885t;
                dVar.d(d8, fArr3);
                l6.f.z0((a0.c) xVar.f9940n.get(nVar.f9883r[i19 - 1]), view2, fArr3);
                i19++;
            }
            l lVar2 = nVar.f9873h;
            if (lVar2.f9848b == 0) {
                if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    view2.setVisibility(lVar2.f9849c);
                } else {
                    l lVar3 = nVar.f9874i;
                    if (f8 >= 1.0f) {
                        view2.setVisibility(lVar3.f9849c);
                    } else if (lVar3.f9849c != lVar2.f9849c) {
                        view2.setVisibility(0);
                    }
                }
            }
        } else {
            f8 = f16;
            boolean z11 = z6;
            i7 = 1;
            float f39 = xVar2.f9931e;
            x xVar3 = nVar.f9872g;
            float a7 = a.a.a(xVar3.f9931e, f39, f8, f39);
            float f40 = xVar2.f9932f;
            float a8 = a.a.a(xVar3.f9932f, f40, f8, f40);
            float f41 = xVar2.f9933g;
            float f42 = xVar3.f9933g;
            float a9 = a.a.a(f42, f41, f8, f41);
            float f43 = xVar2.f9934h;
            float f44 = xVar3.f9934h;
            float f45 = a7 + 0.5f;
            int i20 = (int) f45;
            float f46 = a8 + 0.5f;
            int i21 = (int) f46;
            int i22 = (int) (f45 + a9);
            int a10 = (int) (f46 + a.a.a(f44, f43, f8, f43));
            int i23 = i22 - i20;
            int i24 = a10 - i21;
            if (f42 != f41 || f44 != f43 || nVar.f9869d) {
                view2.measure(View.MeasureSpec.makeMeasureSpec(i23, v.c.EXACTLY), View.MeasureSpec.makeMeasureSpec(i24, v.c.EXACTLY));
                nVar.f9869d = false;
            }
            view2.layout(i20, i21, i22, a10);
            z7 = z11;
        }
        HashMap hashMap4 = nVar.f9891z;
        if (hashMap4 != null) {
            for (x.g gVar2 : hashMap4.values()) {
                if (gVar2 instanceof x.e) {
                    double[] dArr8 = nVar.f9882q;
                    view2.setRotation(((x.e) gVar2).a(f8) + ((float) Math.toDegrees(Math.atan2(dArr8[i7], dArr8[0]))));
                } else {
                    gVar2.d(view2, f8);
                }
            }
        }
        return z7;
    }

    public final void f(x xVar) {
        xVar.f((int) this.f9867b.getX(), (int) this.f9867b.getY(), this.f9867b.getWidth(), this.f9867b.getHeight());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:376:0x084f. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:331:0x08c8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:335:0x08c5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x04c6  */
    /* JADX WARN: Type inference failed for: r7v19, types: [x.p, x.q] */
    /* JADX WARN: Type inference failed for: r7v25, types: [x.q] */
    /* JADX WARN: Type inference failed for: r7v47, types: [x.n, x.q] */
    /* JADX WARN: Type inference failed for: r8v113, types: [x.i, x.l] */
    /* JADX WARN: Type inference failed for: r8v84, types: [x.k, x.l] */
    /* JADX WARN: Type inference failed for: r8v92, types: [x.l] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(int r30, int r31, long r32) {
        /*
            Method dump skipped, instructions count: 3204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y.n.h(int, int, long):void");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(" start: x: ");
        x xVar = this.f9871f;
        sb.append(xVar.f9931e);
        sb.append(" y: ");
        sb.append(xVar.f9932f);
        sb.append(" end: x: ");
        x xVar2 = this.f9872g;
        sb.append(xVar2.f9931e);
        sb.append(" y: ");
        sb.append(xVar2.f9932f);
        return sb.toString();
    }
}
