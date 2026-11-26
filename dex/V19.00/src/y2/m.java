package y2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.s1;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.yalantis.ucrop.view.CropImageView;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends l6.f {

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f10168e = {1};

    /* renamed from: f, reason: collision with root package name */
    public static final int[] f10169f = {1, 0};

    /* renamed from: d, reason: collision with root package name */
    public int f10170d = 0;

    @Override // l6.f
    public final boolean C0(CarouselLayoutManager carouselLayoutManager, int i7) {
        return (i7 < this.f10170d && carouselLayoutManager.E() >= this.f10170d) || (i7 >= this.f10170d && carouselLayoutManager.E() < this.f10170d);
    }

    @Override // l6.f
    public final i Z(CarouselLayoutManager carouselLayoutManager, View view) {
        int[] iArr;
        int[] iArr2;
        g gVar;
        float f7;
        float f8 = carouselLayoutManager.f2635o;
        if (carouselLayoutManager.U0()) {
            f8 = carouselLayoutManager.f2634n;
        }
        s1 s1Var = (s1) view.getLayoutParams();
        float f9 = ((ViewGroup.MarginLayoutParams) s1Var).topMargin + ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carouselLayoutManager.U0()) {
            f9 = ((ViewGroup.MarginLayoutParams) s1Var).leftMargin + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float f10 = f9;
        float dimension = view.getContext().getResources().getDimension(q2.e.m3_carousel_small_item_size_min) + f10;
        float dimension2 = view.getContext().getResources().getDimension(q2.e.m3_carousel_small_item_size_max) + f10;
        float min = Math.min(measuredHeight + f10, f8);
        float x6 = z.x((measuredHeight / 3.0f) + f10, view.getContext().getResources().getDimension(q2.e.m3_carousel_small_item_size_min) + f10, view.getContext().getResources().getDimension(q2.e.m3_carousel_small_item_size_max) + f10);
        float f11 = (min + x6) / 2.0f;
        int[] iArr3 = f10168e;
        boolean z6 = false;
        if (f8 < dimension * 2.0f) {
            iArr3 = new int[]{0};
        }
        int[] iArr4 = f10169f;
        if (carouselLayoutManager.C == 1) {
            int length = iArr3.length;
            int[] iArr5 = new int[length];
            for (int i7 = 0; i7 < length; i7++) {
                iArr5[i7] = iArr3[i7] * 2;
            }
            int[] iArr6 = new int[2];
            for (int i8 = 0; i8 < 2; i8++) {
                iArr6[i8] = iArr4[i8] * 2;
            }
            iArr2 = iArr6;
            iArr = iArr5;
        } else {
            iArr = iArr3;
            iArr2 = iArr4;
        }
        int i9 = Integer.MIN_VALUE;
        int i10 = Integer.MIN_VALUE;
        for (int i11 : iArr2) {
            if (i11 > i10) {
                i10 = i11;
            }
        }
        float f12 = f8 - (i10 * f11);
        for (int i12 : iArr) {
            if (i12 > i9) {
                i9 = i12;
            }
        }
        int max = (int) Math.max(1.0d, Math.floor((f12 - (i9 * dimension2)) / min));
        int ceil = (int) Math.ceil(f8 / min);
        int i13 = (ceil - max) + 1;
        int[] iArr7 = new int[i13];
        for (int i14 = 0; i14 < i13; i14++) {
            iArr7[i14] = ceil - i14;
        }
        a a7 = a.a(f8, x6, dimension, dimension2, iArr, f11, iArr2, min, iArr7);
        int i15 = a7.f10123c + a7.f10124d;
        int i16 = a7.f10127g;
        this.f10170d = i15 + i16;
        int E = carouselLayoutManager.E();
        int i17 = a7.f10123c;
        int i18 = a7.f10124d;
        int i19 = ((i17 + i18) + i16) - E;
        if (i19 > 0 && (i17 > 0 || i18 > 1)) {
            z6 = true;
        }
        while (i19 > 0) {
            int i20 = a7.f10123c;
            if (i20 > 0) {
                a7.f10123c = i20 - 1;
            } else {
                int i21 = a7.f10124d;
                if (i21 > 1) {
                    a7.f10124d = i21 - 1;
                }
            }
            i19--;
        }
        if (z6) {
            a7 = a.a(f8, x6, dimension, dimension2, new int[]{a7.f10123c}, f11, new int[]{a7.f10124d}, min, new int[]{i16});
        }
        Context context = view.getContext();
        if (carouselLayoutManager.C != 1) {
            float min2 = Math.min(context.getResources().getDimension(q2.e.m3_carousel_gone_size) + f10, a7.f10126f);
            float f13 = min2 / 2.0f;
            float f14 = CropImageView.DEFAULT_ASPECT_RATIO - f13;
            float f15 = a7.f10126f;
            int i22 = a7.f10127g;
            float d7 = z.d(CropImageView.DEFAULT_ASPECT_RATIO, f15, i22);
            float q12 = z.q1(CropImageView.DEFAULT_ASPECT_RATIO, z.c(d7, a7.f10126f, i22), a7.f10126f, i22);
            float d8 = z.d(q12, a7.f10125e, a7.f10124d);
            float d9 = z.d(z.q1(q12, d8, a7.f10125e, a7.f10124d), a7.f10122b, a7.f10123c);
            float f16 = f13 + f8;
            float v6 = l6.f.v(min2, a7.f10126f, f10);
            float v7 = l6.f.v(a7.f10122b, a7.f10126f, f10);
            float v8 = l6.f.v(a7.f10125e, a7.f10126f, f10);
            g gVar2 = new g(a7.f10126f, f8);
            gVar2.a(f14, v6, min2, false, true);
            gVar2.c(d7, CropImageView.DEFAULT_ASPECT_RATIO, a7.f10126f, a7.f10127g, true);
            if (a7.f10124d > 0) {
                gVar2.a(d8, v8, a7.f10125e, false, false);
            }
            int i23 = a7.f10123c;
            if (i23 > 0) {
                gVar2.c(d9, v7, a7.f10122b, i23, false);
            }
            gVar2.a(f16, v6, min2, false, true);
            return gVar2.d();
        }
        float min3 = Math.min(context.getResources().getDimension(q2.e.m3_carousel_gone_size) + f10, a7.f10126f);
        float f17 = min3 / 2.0f;
        float f18 = CropImageView.DEFAULT_ASPECT_RATIO - f17;
        float d10 = z.d(CropImageView.DEFAULT_ASPECT_RATIO, a7.f10122b, a7.f10123c);
        float q13 = z.q1(CropImageView.DEFAULT_ASPECT_RATIO, z.c(d10, a7.f10122b, (int) Math.floor(a7.f10123c / 2.0f)), a7.f10122b, a7.f10123c);
        float d11 = z.d(q13, a7.f10125e, a7.f10124d);
        float q14 = z.q1(q13, z.c(d11, a7.f10125e, (int) Math.floor(a7.f10124d / 2.0f)), a7.f10125e, a7.f10124d);
        float f19 = a7.f10126f;
        int i24 = a7.f10127g;
        float d12 = z.d(q14, f19, i24);
        float q15 = z.q1(q14, z.c(d12, a7.f10126f, i24), a7.f10126f, i24);
        float d13 = z.d(q15, a7.f10125e, a7.f10124d);
        float d14 = z.d(z.q1(q15, z.c(d13, a7.f10125e, (int) Math.ceil(a7.f10124d / 2.0f)), a7.f10125e, a7.f10124d), a7.f10122b, a7.f10123c);
        float f20 = f17 + f8;
        float v9 = l6.f.v(min3, a7.f10126f, f10);
        float v10 = l6.f.v(a7.f10122b, a7.f10126f, f10);
        float v11 = l6.f.v(a7.f10125e, a7.f10126f, f10);
        g gVar3 = new g(a7.f10126f, f8);
        gVar3.a(f18, v9, min3, false, true);
        if (a7.f10123c > 0) {
            float f21 = a7.f10122b;
            int floor = (int) Math.floor(r1 / 2.0f);
            gVar = gVar3;
            f7 = d13;
            gVar3.c(d10, v10, f21, floor, false);
        } else {
            gVar = gVar3;
            f7 = d13;
        }
        if (a7.f10124d > 0) {
            gVar.c(d11, v11, a7.f10125e, (int) Math.floor(r5 / 2.0f), false);
        }
        gVar.c(d12, CropImageView.DEFAULT_ASPECT_RATIO, a7.f10126f, a7.f10127g, true);
        if (a7.f10124d > 0) {
            gVar.c(f7, v11, a7.f10125e, (int) Math.ceil(r5 / 2.0f), false);
        }
        if (a7.f10123c > 0) {
            gVar.c(d14, v10, a7.f10122b, (int) Math.ceil(r1 / 2.0f), false);
        }
        gVar.a(f20, v9, min3, false, true);
        return gVar.d();
    }
}
