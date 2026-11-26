package androidx.appcompat.widget;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i3 extends Property {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1289a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i3(Class cls, String str, int i7) {
        super(cls, str);
        this.f1289a = i7;
    }

    public final Float a(View view) {
        switch (this.f1289a) {
            case 6:
                return Float.valueOf(n1.b0.f7590a.N(view));
            case 7:
            default:
                int i7 = p0.d1.OVER_SCROLL_ALWAYS;
                return Float.valueOf(p0.m0.e(view));
            case 8:
                return Float.valueOf(view.getLayoutParams().width);
            case 9:
                return Float.valueOf(view.getLayoutParams().height);
            case 10:
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                return Float.valueOf(p0.m0.f(view));
        }
    }

    public final void b(View view, PointF pointF) {
        switch (this.f1289a) {
            case 3:
                n1.b0.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
                break;
            case 4:
                n1.b0.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
                break;
            default:
                int round = Math.round(pointF.x);
                int round2 = Math.round(pointF.y);
                n1.b0.a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
                break;
        }
    }

    public final void c(View view, Float f7) {
        switch (this.f1289a) {
            case 6:
                n1.b0.f7590a.O(view, f7.floatValue());
                break;
            case 7:
            default:
                int i7 = p0.d1.OVER_SCROLL_ALWAYS;
                p0.m0.k(view, p0.m0.f(view), view.getPaddingTop(), f7.intValue(), view.getPaddingBottom());
                break;
            case 8:
                view.getLayoutParams().width = f7.intValue();
                view.requestLayout();
                break;
            case 9:
                view.getLayoutParams().height = f7.intValue();
                view.requestLayout();
                break;
            case 10:
                int intValue = f7.intValue();
                int paddingTop = view.getPaddingTop();
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                p0.m0.k(view, intValue, paddingTop, p0.m0.e(view), view.getPaddingBottom());
                break;
        }
    }

    public final void d(l3.g gVar, Float f7) {
        switch (this.f1289a) {
            case 12:
                float floatValue = f7.floatValue();
                gVar.f7365i = floatValue;
                int i7 = (int) (5400.0f * floatValue);
                float[] fArr = (float[]) gVar.f7061b;
                float f8 = floatValue * 1520.0f;
                fArr[0] = (-20.0f) + f8;
                fArr[1] = f8;
                int i8 = 0;
                while (true) {
                    c1.b bVar = gVar.f7362f;
                    if (i8 >= 4) {
                        float[] fArr2 = (float[]) gVar.f7061b;
                        float f9 = fArr2[0];
                        float f10 = fArr2[1];
                        float f11 = ((f10 - f9) * gVar.f7366j) + f9;
                        fArr2[0] = f11;
                        fArr2[0] = f11 / 360.0f;
                        fArr2[1] = f10 / 360.0f;
                        int i9 = 0;
                        while (true) {
                            if (i9 < 4) {
                                float f12 = (i7 - l3.g.f7357n[i9]) / 333;
                                if (f12 < CropImageView.DEFAULT_ASPECT_RATIO || f12 > 1.0f) {
                                    i9++;
                                } else {
                                    int i10 = i9 + gVar.f7364h;
                                    l3.h hVar = gVar.f7363g;
                                    int[] iArr = hVar.f7345c;
                                    int length = i10 % iArr.length;
                                    int length2 = (length + 1) % iArr.length;
                                    ((int[]) gVar.f7062c)[0] = r2.b.a(bVar.getInterpolation(f12), Integer.valueOf(b3.a.a(iArr[length], ((l3.n) gVar.f7060a).f7389j)), Integer.valueOf(b3.a.a(hVar.f7345c[length2], ((l3.n) gVar.f7060a).f7389j))).intValue();
                                }
                            }
                        }
                        ((l3.n) gVar.f7060a).invalidateSelf();
                        break;
                    } else {
                        float f13 = 667;
                        float[] fArr3 = (float[]) gVar.f7061b;
                        fArr3[1] = (bVar.getInterpolation((i7 - l3.g.f7355l[i8]) / f13) * 250.0f) + fArr3[1];
                        float f14 = (i7 - l3.g.f7356m[i8]) / f13;
                        float[] fArr4 = (float[]) gVar.f7061b;
                        fArr4[0] = (bVar.getInterpolation(f14) * 250.0f) + fArr4[0];
                        i8++;
                    }
                }
                break;
            default:
                gVar.f7366j = f7.floatValue();
                break;
        }
    }

    public final void e(n1.e eVar, PointF pointF) {
        switch (this.f1289a) {
            case 1:
                eVar.getClass();
                eVar.f7595a = Math.round(pointF.x);
                int round = Math.round(pointF.y);
                eVar.f7596b = round;
                int i7 = eVar.f7600f + 1;
                eVar.f7600f = i7;
                if (i7 == eVar.f7601g) {
                    n1.b0.a(eVar.f7599e, eVar.f7595a, round, eVar.f7597c, eVar.f7598d);
                    eVar.f7600f = 0;
                    eVar.f7601g = 0;
                    break;
                }
                break;
            default:
                eVar.getClass();
                eVar.f7597c = Math.round(pointF.x);
                int round2 = Math.round(pointF.y);
                eVar.f7598d = round2;
                int i8 = eVar.f7601g + 1;
                eVar.f7601g = i8;
                if (eVar.f7600f == i8) {
                    n1.b0.a(eVar.f7599e, eVar.f7595a, eVar.f7596b, eVar.f7597c, round2);
                    eVar.f7600f = 0;
                    eVar.f7601g = 0;
                    break;
                }
                break;
        }
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        int i7 = this.f1289a;
        switch (i7) {
            case 0:
                return Float.valueOf(((SwitchCompat) obj).f1151z);
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                return a((View) obj);
            case 7:
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                return p0.n0.a((View) obj);
            case 8:
                return a((View) obj);
            case 9:
                return a((View) obj);
            case 10:
                return a((View) obj);
            case 11:
                return a((View) obj);
            case 12:
                l3.g gVar = (l3.g) obj;
                switch (i7) {
                    case 12:
                        return Float.valueOf(gVar.f7365i);
                    default:
                        return Float.valueOf(gVar.f7366j);
                }
            case 13:
                l3.g gVar2 = (l3.g) obj;
                switch (i7) {
                    case 12:
                        return Float.valueOf(gVar2.f7365i);
                    default:
                        return Float.valueOf(gVar2.f7366j);
                }
            case 14:
                return Float.valueOf(((l3.l) obj).b());
            case 15:
                return Float.valueOf(((l3.p) obj).f7404i);
            default:
                return Float.valueOf(((l3.r) obj).f7416j);
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.f1289a) {
            case 0:
                ((SwitchCompat) obj).setThumbPosition(((Float) obj2).floatValue());
                break;
            case 1:
                e((n1.e) obj, (PointF) obj2);
                break;
            case 2:
                e((n1.e) obj, (PointF) obj2);
                break;
            case 3:
                b((View) obj, (PointF) obj2);
                break;
            case 4:
                b((View) obj, (PointF) obj2);
                break;
            case 5:
                b((View) obj, (PointF) obj2);
                break;
            case 6:
                c((View) obj, (Float) obj2);
                break;
            case 7:
                int i7 = p0.d1.OVER_SCROLL_ALWAYS;
                p0.n0.c((View) obj, (Rect) obj2);
                break;
            case 8:
                c((View) obj, (Float) obj2);
                break;
            case 9:
                c((View) obj, (Float) obj2);
                break;
            case 10:
                c((View) obj, (Float) obj2);
                break;
            case 11:
                c((View) obj, (Float) obj2);
                break;
            case 12:
                d((l3.g) obj, (Float) obj2);
                break;
            case 13:
                d((l3.g) obj, (Float) obj2);
                break;
            case 14:
                l3.l lVar = (l3.l) obj;
                float floatValue = ((Float) obj2).floatValue();
                if (lVar.f7387h != floatValue) {
                    lVar.f7387h = floatValue;
                    lVar.invalidateSelf();
                    break;
                }
                break;
            case 15:
                l3.p pVar = (l3.p) obj;
                pVar.f7404i = ((Float) obj2).floatValue();
                Object obj3 = pVar.f7061b;
                ((float[]) obj3)[0] = 0.0f;
                float f7 = ((int) (r10 * 333.0f)) / 667;
                float[] fArr = (float[]) obj3;
                c1.b bVar = pVar.f7400e;
                float interpolation = bVar.getInterpolation(f7);
                fArr[2] = interpolation;
                fArr[1] = interpolation;
                float[] fArr2 = (float[]) pVar.f7061b;
                float interpolation2 = bVar.getInterpolation(f7 + 0.49925038f);
                fArr2[4] = interpolation2;
                fArr2[3] = interpolation2;
                float[] fArr3 = (float[]) pVar.f7061b;
                fArr3[5] = 1.0f;
                if (pVar.f7403h && fArr3[3] < 1.0f) {
                    int[] iArr = (int[]) pVar.f7062c;
                    iArr[2] = iArr[1];
                    iArr[1] = iArr[0];
                    iArr[0] = b3.a.a(pVar.f7401f.f7345c[pVar.f7402g], ((l3.n) pVar.f7060a).f7389j);
                    pVar.f7403h = false;
                }
                ((l3.n) pVar.f7060a).invalidateSelf();
                break;
            default:
                l3.r rVar = (l3.r) obj;
                float floatValue2 = ((Float) obj2).floatValue();
                rVar.f7416j = floatValue2;
                int i8 = (int) (floatValue2 * 1800.0f);
                for (int i9 = 0; i9 < 4; i9++) {
                    ((float[]) rVar.f7061b)[i9] = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(1.0f, rVar.f7412f[i9].getInterpolation((i8 - l3.r.f7408m[i9]) / l3.r.f7407l[i9])));
                }
                if (rVar.f7415i) {
                    Arrays.fill((int[]) rVar.f7062c, b3.a.a(rVar.f7413g.f7345c[rVar.f7414h], ((l3.n) rVar.f7060a).f7389j));
                    rVar.f7415i = false;
                }
                ((l3.n) rVar.f7060a).invalidateSelf();
                break;
        }
    }
}
