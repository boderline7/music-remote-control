package androidx.constraintlayout.utils.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.yalantis.ucrop.view.CropImageView;
import h0.a;
import java.util.HashMap;
import t.b;
import x.g;
import x.l;
import y.c;
import y.n;
import y.o;
import y.x;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionTelltales extends MockView {

    /* renamed from: l, reason: collision with root package name */
    public final Paint f1667l;

    /* renamed from: m, reason: collision with root package name */
    public MotionLayout f1668m;

    /* renamed from: n, reason: collision with root package name */
    public final float[] f1669n;

    /* renamed from: o, reason: collision with root package name */
    public final Matrix f1670o;

    /* renamed from: p, reason: collision with root package name */
    public int f1671p;

    /* renamed from: q, reason: collision with root package name */
    public int f1672q;

    /* renamed from: r, reason: collision with root package name */
    public float f1673r;

    public MotionTelltales(Context context) {
        super(context);
        this.f1667l = new Paint();
        this.f1669n = new float[2];
        this.f1670o = new Matrix();
        this.f1671p = 0;
        this.f1672q = -65281;
        this.f1673r = 0.25f;
        b(context, null);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1667l = new Paint();
        this.f1669n = new float[2];
        this.f1670o = new Matrix();
        this.f1671p = 0;
        this.f1672q = -65281;
        this.f1673r = 0.25f;
        b(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1667l = new Paint();
        this.f1669n = new float[2];
        this.f1670o = new Matrix();
        this.f1671p = 0;
        this.f1672q = -65281;
        this.f1673r = 0.25f;
        b(context, attributeSet);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.MotionTelltales);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.MotionTelltales_telltales_tailColor) {
                    this.f1672q = obtainStyledAttributes.getColor(index, this.f1672q);
                } else if (index == t.MotionTelltales_telltales_velocityMode) {
                    this.f1671p = obtainStyledAttributes.getInt(index, this.f1671p);
                } else if (index == t.MotionTelltales_telltales_tailScale) {
                    this.f1673r = obtainStyledAttributes.getFloat(index, this.f1673r);
                }
            }
            obtainStyledAttributes.recycle();
        }
        int i8 = this.f1672q;
        Paint paint = this.f1667l;
        paint.setColor(i8);
        paint.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public final void onDraw(Canvas canvas) {
        int i7;
        Matrix matrix;
        int i8;
        float f7;
        float[] fArr;
        int i9;
        int i10;
        float[] fArr2;
        float f8;
        float f9;
        int i11;
        l lVar;
        int i12;
        l lVar2;
        l lVar3;
        l lVar4;
        float[] fArr3;
        float f10;
        float f11;
        double[] dArr;
        a aVar;
        MotionTelltales motionTelltales = this;
        super.onDraw(canvas);
        Matrix matrix2 = getMatrix();
        Matrix matrix3 = motionTelltales.f1670o;
        matrix2.invert(matrix3);
        if (motionTelltales.f1668m == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                motionTelltales.f1668m = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        int i13 = 5;
        float[] fArr4 = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        int i14 = 0;
        while (i14 < i13) {
            float f12 = fArr4[i14];
            int i15 = 0;
            while (i15 < i13) {
                float f13 = fArr4[i15];
                MotionLayout motionLayout = motionTelltales.f1668m;
                float[] fArr5 = motionTelltales.f1669n;
                int i16 = motionTelltales.f1671p;
                float f14 = motionLayout.f1583v;
                float f15 = motionLayout.G;
                if (motionLayout.f1579t != null) {
                    float signum = Math.signum(motionLayout.I - f15);
                    float interpolation = motionLayout.f1579t.getInterpolation(motionLayout.G + 1.0E-5f);
                    f15 = motionLayout.f1579t.getInterpolation(motionLayout.G);
                    f14 = (((interpolation - f15) / 1.0E-5f) * signum) / motionLayout.E;
                }
                o oVar = motionLayout.f1579t;
                if (oVar instanceof o) {
                    f14 = oVar.a();
                }
                float f16 = f14;
                n nVar = (n) motionLayout.C.get(motionTelltales);
                if ((i16 & 1) == 0) {
                    int width2 = getWidth();
                    int height2 = getHeight();
                    float[] fArr6 = nVar.f9887v;
                    float b7 = nVar.b(f15, fArr6);
                    HashMap hashMap = nVar.f9890y;
                    fArr = fArr4;
                    if (hashMap == null) {
                        i12 = i16;
                        lVar = null;
                    } else {
                        lVar = (l) hashMap.get(c.TRANSLATION_X);
                        i12 = i16;
                    }
                    HashMap hashMap2 = nVar.f9890y;
                    i9 = i14;
                    if (hashMap2 == null) {
                        i11 = i15;
                        lVar2 = null;
                    } else {
                        lVar2 = (l) hashMap2.get(c.TRANSLATION_Y);
                        i11 = i15;
                    }
                    HashMap hashMap3 = nVar.f9890y;
                    i8 = height;
                    if (hashMap3 == null) {
                        i7 = width;
                        lVar3 = null;
                    } else {
                        lVar3 = (l) hashMap3.get(c.ROTATION);
                        i7 = width;
                    }
                    HashMap hashMap4 = nVar.f9890y;
                    matrix = matrix3;
                    l lVar5 = hashMap4 == null ? null : (l) hashMap4.get(c.SCALE_X);
                    HashMap hashMap5 = nVar.f9890y;
                    f7 = f16;
                    if (hashMap5 == null) {
                        fArr3 = fArr6;
                        lVar4 = null;
                    } else {
                        lVar4 = (l) hashMap5.get(c.SCALE_Y);
                        fArr3 = fArr6;
                    }
                    HashMap hashMap6 = nVar.f9891z;
                    g gVar = hashMap6 == null ? null : (g) hashMap6.get(c.TRANSLATION_X);
                    HashMap hashMap7 = nVar.f9891z;
                    g gVar2 = hashMap7 == null ? null : (g) hashMap7.get(c.TRANSLATION_Y);
                    HashMap hashMap8 = nVar.f9891z;
                    g gVar3 = hashMap8 == null ? null : (g) hashMap8.get(c.ROTATION);
                    HashMap hashMap9 = nVar.f9891z;
                    g gVar4 = hashMap9 == null ? null : (g) hashMap9.get(c.SCALE_X);
                    HashMap hashMap10 = nVar.f9891z;
                    g gVar5 = hashMap10 != null ? (g) hashMap10.get(c.SCALE_Y) : null;
                    a aVar2 = new a();
                    aVar2.f6613e = CropImageView.DEFAULT_ASPECT_RATIO;
                    aVar2.f6612d = CropImageView.DEFAULT_ASPECT_RATIO;
                    aVar2.f6611c = CropImageView.DEFAULT_ASPECT_RATIO;
                    aVar2.f6610b = CropImageView.DEFAULT_ASPECT_RATIO;
                    aVar2.f6609a = CropImageView.DEFAULT_ASPECT_RATIO;
                    if (lVar3 != null) {
                        f10 = f13;
                        f11 = f12;
                        aVar2.f6613e = (float) lVar3.f9534a.e(b7);
                        aVar2.f6614f = lVar3.a(b7);
                    } else {
                        f10 = f13;
                        f11 = f12;
                    }
                    if (lVar != null) {
                        aVar2.f6611c = (float) lVar.f9534a.e(b7);
                    }
                    if (lVar2 != null) {
                        aVar2.f6612d = (float) lVar2.f9534a.e(b7);
                    }
                    if (lVar5 != null) {
                        aVar2.f6609a = (float) lVar5.f9534a.e(b7);
                    }
                    if (lVar4 != null) {
                        aVar2.f6610b = (float) lVar4.f9534a.e(b7);
                    }
                    if (gVar3 != null) {
                        aVar2.f6613e = gVar3.b(b7);
                    }
                    if (gVar != null) {
                        aVar2.f6611c = gVar.b(b7);
                    }
                    if (gVar2 != null) {
                        aVar2.f6612d = gVar2.b(b7);
                    }
                    if (gVar4 != null) {
                        aVar2.f6609a = gVar4.b(b7);
                    }
                    if (gVar5 != null) {
                        aVar2.f6610b = gVar5.b(b7);
                    }
                    b bVar = nVar.f9876k;
                    if (bVar != null) {
                        double[] dArr2 = nVar.f9881p;
                        if (dArr2.length > 0) {
                            double d7 = b7;
                            bVar.c(d7, dArr2);
                            nVar.f9876k.f(d7, nVar.f9882q);
                            x xVar = nVar.f9871f;
                            int[] iArr = nVar.f9880o;
                            double[] dArr3 = nVar.f9882q;
                            double[] dArr4 = nVar.f9881p;
                            xVar.getClass();
                            aVar = aVar2;
                            i10 = i12;
                            fArr2 = fArr5;
                            f8 = f10;
                            x.g(f10, f11, fArr5, iArr, dArr3, dArr4);
                        } else {
                            aVar = aVar2;
                            fArr2 = fArr5;
                            i10 = i12;
                            f8 = f10;
                        }
                        aVar.a(f8, f11, width2, height2, fArr2);
                    } else {
                        float f17 = f10;
                        if (nVar.f9875j != null) {
                            float[] fArr7 = fArr3;
                            double b8 = nVar.b(b7, fArr7);
                            nVar.f9875j[0].f(b8, nVar.f9882q);
                            nVar.f9875j[0].c(b8, nVar.f9881p);
                            float f18 = fArr7[0];
                            int i17 = 0;
                            while (true) {
                                dArr = nVar.f9882q;
                                if (i17 >= dArr.length) {
                                    break;
                                }
                                dArr[i17] = dArr[i17] * f18;
                                i17++;
                            }
                            x xVar2 = nVar.f9871f;
                            int[] iArr2 = nVar.f9880o;
                            double[] dArr5 = nVar.f9881p;
                            xVar2.getClass();
                            i10 = i12;
                            f8 = f17;
                            x.g(f17, f11, fArr5, iArr2, dArr, dArr5);
                            aVar2.a(f8, f11, width2, height2, fArr5);
                            fArr2 = fArr5;
                        } else {
                            x xVar3 = nVar.f9872g;
                            float f19 = xVar3.f9931e;
                            x xVar4 = nVar.f9871f;
                            g gVar6 = gVar4;
                            float f20 = f19 - xVar4.f9931e;
                            g gVar7 = gVar2;
                            float f21 = xVar3.f9932f - xVar4.f9932f;
                            g gVar8 = gVar;
                            float f22 = xVar3.f9933g - xVar4.f9933g;
                            float f23 = (xVar3.f9934h - xVar4.f9934h) + f21;
                            fArr5[0] = ((f22 + f20) * f17) + ((1.0f - f17) * f20);
                            fArr5[1] = (f23 * f11) + ((1.0f - f11) * f21);
                            aVar2.f6613e = CropImageView.DEFAULT_ASPECT_RATIO;
                            aVar2.f6612d = CropImageView.DEFAULT_ASPECT_RATIO;
                            aVar2.f6611c = CropImageView.DEFAULT_ASPECT_RATIO;
                            aVar2.f6610b = CropImageView.DEFAULT_ASPECT_RATIO;
                            aVar2.f6609a = CropImageView.DEFAULT_ASPECT_RATIO;
                            if (lVar3 != null) {
                                aVar2.f6613e = (float) lVar3.f9534a.e(b7);
                                aVar2.f6614f = lVar3.a(b7);
                            }
                            if (lVar != null) {
                                aVar2.f6611c = (float) lVar.f9534a.e(b7);
                            }
                            if (lVar2 != null) {
                                aVar2.f6612d = (float) lVar2.f9534a.e(b7);
                            }
                            if (lVar5 != null) {
                                aVar2.f6609a = (float) lVar5.f9534a.e(b7);
                            }
                            if (lVar4 != null) {
                                aVar2.f6610b = (float) lVar4.f9534a.e(b7);
                            }
                            if (gVar3 != null) {
                                aVar2.f6613e = gVar3.b(b7);
                            }
                            if (gVar8 != null) {
                                aVar2.f6611c = gVar8.b(b7);
                            }
                            if (gVar7 != null) {
                                aVar2.f6612d = gVar7.b(b7);
                            }
                            if (gVar6 != null) {
                                aVar2.f6609a = gVar6.b(b7);
                            }
                            if (gVar5 != null) {
                                aVar2.f6610b = gVar5.b(b7);
                            }
                            i10 = i12;
                            fArr2 = fArr5;
                            f8 = f17;
                            aVar2.a(f17, f11, width2, height2, fArr2);
                        }
                    }
                    f9 = f11;
                } else {
                    i7 = width;
                    matrix = matrix3;
                    i8 = height;
                    f7 = f16;
                    fArr = fArr4;
                    i9 = i14;
                    i10 = i16;
                    fArr2 = fArr5;
                    f8 = f13;
                    f9 = f12;
                    i11 = i15;
                    nVar.d(f15, f8, f9, fArr2);
                }
                if (i10 < 2) {
                    fArr2[0] = fArr2[0] * f7;
                    fArr2[1] = fArr2[1] * f7;
                }
                motionTelltales = this;
                float[] fArr8 = motionTelltales.f1669n;
                Matrix matrix4 = matrix;
                matrix4.mapVectors(fArr8);
                int i18 = i7;
                float f24 = i18 * f8;
                int i19 = i8;
                float f25 = i19 * f9;
                float f26 = fArr8[0];
                float f27 = motionTelltales.f1673r;
                float f28 = f25 - (fArr8[1] * f27);
                matrix4.mapVectors(fArr8);
                canvas.drawLine(f24, f25, f24 - (f26 * f27), f28, motionTelltales.f1667l);
                i15 = i11 + 1;
                f12 = f9;
                width = i18;
                fArr4 = fArr;
                i14 = i9;
                height = i19;
                matrix3 = matrix4;
                i13 = 5;
            }
            i14++;
            matrix3 = matrix3;
            fArr4 = fArr4;
            i13 = 5;
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.f1630f = charSequence.toString();
        requestLayout();
    }
}
