package y;

import android.graphics.Rect;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements Comparable {
    public static final boolean DEBUG = false;
    public static final String TAG = "MotionPaths";

    /* renamed from: c, reason: collision with root package name */
    public int f9849c;

    /* renamed from: a, reason: collision with root package name */
    public float f9847a = 1.0f;

    /* renamed from: b, reason: collision with root package name */
    public int f9848b = 0;

    /* renamed from: d, reason: collision with root package name */
    public float f9850d = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: e, reason: collision with root package name */
    public float f9851e = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: f, reason: collision with root package name */
    public float f9852f = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: g, reason: collision with root package name */
    public float f9853g = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: h, reason: collision with root package name */
    public float f9854h = 1.0f;

    /* renamed from: i, reason: collision with root package name */
    public float f9855i = 1.0f;

    /* renamed from: j, reason: collision with root package name */
    public float f9856j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    public float f9857k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    public float f9858l = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: m, reason: collision with root package name */
    public float f9859m = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: n, reason: collision with root package name */
    public float f9860n = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: o, reason: collision with root package name */
    public float f9861o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    public float f9862p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    public final LinkedHashMap f9863q = new LinkedHashMap();

    public static boolean b(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void a(HashMap hashMap, int i7) {
        for (String str : hashMap.keySet()) {
            x.l lVar = (x.l) hashMap.get(str);
            str.getClass();
            char c7 = 65535;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals(c.ROTATION_X)) {
                        c7 = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str.equals(c.ROTATION_Y)) {
                        c7 = 1;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str.equals(c.TRANSLATION_X)) {
                        c7 = 2;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str.equals(c.TRANSLATION_Y)) {
                        c7 = 3;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str.equals(c.TRANSLATION_Z)) {
                        c7 = 4;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str.equals(c.PROGRESS)) {
                        c7 = 5;
                        break;
                    }
                    break;
                case -908189618:
                    if (str.equals(c.SCALE_X)) {
                        c7 = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (str.equals(c.SCALE_Y)) {
                        c7 = 7;
                        break;
                    }
                    break;
                case -760884510:
                    if (str.equals(c.PIVOT_X)) {
                        c7 = '\b';
                        break;
                    }
                    break;
                case -760884509:
                    if (str.equals(c.PIVOT_Y)) {
                        c7 = '\t';
                        break;
                    }
                    break;
                case -40300674:
                    if (str.equals(c.ROTATION)) {
                        c7 = '\n';
                        break;
                    }
                    break;
                case -4379043:
                    if (str.equals(c.ELEVATION)) {
                        c7 = 11;
                        break;
                    }
                    break;
                case 37232917:
                    if (str.equals(c.TRANSITION_PATH_ROTATE)) {
                        c7 = '\f';
                        break;
                    }
                    break;
                case 92909918:
                    if (str.equals(c.ALPHA)) {
                        c7 = '\r';
                        break;
                    }
                    break;
            }
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
            switch (c7) {
                case 0:
                    if (!Float.isNaN(this.f9852f)) {
                        f7 = this.f9852f;
                    }
                    lVar.b(f7, i7);
                    break;
                case 1:
                    if (!Float.isNaN(this.f9853g)) {
                        f7 = this.f9853g;
                    }
                    lVar.b(f7, i7);
                    break;
                case 2:
                    if (!Float.isNaN(this.f9858l)) {
                        f7 = this.f9858l;
                    }
                    lVar.b(f7, i7);
                    break;
                case 3:
                    if (!Float.isNaN(this.f9859m)) {
                        f7 = this.f9859m;
                    }
                    lVar.b(f7, i7);
                    break;
                case 4:
                    if (!Float.isNaN(this.f9860n)) {
                        f7 = this.f9860n;
                    }
                    lVar.b(f7, i7);
                    break;
                case 5:
                    if (!Float.isNaN(this.f9862p)) {
                        f7 = this.f9862p;
                    }
                    lVar.b(f7, i7);
                    break;
                case 6:
                    lVar.b(Float.isNaN(this.f9854h) ? 1.0f : this.f9854h, i7);
                    break;
                case 7:
                    lVar.b(Float.isNaN(this.f9855i) ? 1.0f : this.f9855i, i7);
                    break;
                case '\b':
                    if (!Float.isNaN(this.f9856j)) {
                        f7 = this.f9856j;
                    }
                    lVar.b(f7, i7);
                    break;
                case '\t':
                    if (!Float.isNaN(this.f9857k)) {
                        f7 = this.f9857k;
                    }
                    lVar.b(f7, i7);
                    break;
                case '\n':
                    if (!Float.isNaN(this.f9851e)) {
                        f7 = this.f9851e;
                    }
                    lVar.b(f7, i7);
                    break;
                case 11:
                    if (!Float.isNaN(this.f9850d)) {
                        f7 = this.f9850d;
                    }
                    lVar.b(f7, i7);
                    break;
                case '\f':
                    if (!Float.isNaN(this.f9861o)) {
                        f7 = this.f9861o;
                    }
                    lVar.b(f7, i7);
                    break;
                case '\r':
                    lVar.b(Float.isNaN(this.f9847a) ? 1.0f : this.f9847a, i7);
                    break;
                default:
                    if (str.startsWith(c.CUSTOM)) {
                        String str2 = str.split(",")[1];
                        LinkedHashMap linkedHashMap = this.f9863q;
                        if (linkedHashMap.containsKey(str2)) {
                            a0.c cVar = (a0.c) linkedHashMap.get(str2);
                            if (lVar instanceof x.i) {
                                ((x.i) lVar).f9531f.append(i7, cVar);
                                break;
                            } else {
                                cVar.a();
                                Objects.toString(lVar);
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
            }
        }
    }

    public final void c(Rect rect, a0.p pVar, int i7, int i8) {
        rect.width();
        rect.height();
        a0.k h7 = pVar.h(i8);
        a0.n nVar = h7.f83c;
        int i9 = nVar.f148c;
        this.f9848b = i9;
        int i10 = nVar.f147b;
        this.f9849c = i10;
        this.f9847a = (i10 == 0 || i9 != 0) ? nVar.f149d : CropImageView.DEFAULT_ASPECT_RATIO;
        a0.o oVar = h7.f86f;
        boolean z6 = oVar.f164m;
        this.f9850d = oVar.f165n;
        this.f9851e = oVar.f153b;
        this.f9852f = oVar.f154c;
        this.f9853g = oVar.f155d;
        this.f9854h = oVar.f156e;
        this.f9855i = oVar.f157f;
        this.f9856j = oVar.f158g;
        this.f9857k = oVar.f159h;
        this.f9858l = oVar.f161j;
        this.f9859m = oVar.f162k;
        this.f9860n = oVar.f163l;
        a0.m mVar = h7.f84d;
        t.f.c(mVar.f136d);
        this.f9861o = mVar.f140h;
        this.f9862p = h7.f83c.f150e;
        for (String str : h7.f87g.keySet()) {
            a0.c cVar = (a0.c) h7.f87g.get(str);
            cVar.getClass();
            int i11 = a0.a.f0a[cVar.f4c.ordinal()];
            if (i11 != 1 && i11 != 2 && i11 != 3) {
                this.f9863q.put(str, cVar);
            }
        }
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 != 3) {
                    if (i7 != 4) {
                        return;
                    }
                }
            }
            float f7 = this.f9851e + 90.0f;
            this.f9851e = f7;
            if (f7 > 180.0f) {
                this.f9851e = f7 - 360.0f;
                return;
            }
            return;
        }
        this.f9851e -= 90.0f;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ((l) obj).getClass();
        return Float.compare(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
    }
}
