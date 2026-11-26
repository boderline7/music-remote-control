package p0;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t1 implements View.OnApplyWindowInsetsListener {

    /* renamed from: a, reason: collision with root package name */
    public final q1 f8191a;

    /* renamed from: b, reason: collision with root package name */
    public l2 f8192b;

    public t1(View view, w2.d dVar) {
        l2 l2Var;
        this.f8191a = dVar;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l2 a7 = s0.a(view);
        if (a7 != null) {
            int i8 = Build.VERSION.SDK_INT;
            l2Var = (i8 >= 30 ? new c2(a7) : i8 >= 29 ? new b2(a7) : new a2(a7)).b();
        } else {
            l2Var = null;
        }
        this.f8192b = l2Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        j2 j2Var;
        if (!view.isLaidOut()) {
            this.f8192b = l2.h(view, windowInsets);
            return u1.i(view, windowInsets);
        }
        l2 h7 = l2.h(view, windowInsets);
        if (this.f8192b == null) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            this.f8192b = s0.a(view);
        }
        if (this.f8192b == null) {
            this.f8192b = h7;
            return u1.i(view, windowInsets);
        }
        q1 j7 = u1.j(view);
        if (j7 != null && Objects.equals(j7.f8172a, windowInsets)) {
            return u1.i(view, windowInsets);
        }
        l2 l2Var = this.f8192b;
        int i8 = 1;
        int i9 = 0;
        while (true) {
            j2Var = h7.f8161a;
            if (i8 > 256) {
                break;
            }
            if (!j2Var.f(i8).equals(l2Var.f8161a.f(i8))) {
                i9 |= i8;
            }
            i8 <<= 1;
        }
        if (i9 == 0) {
            return u1.i(view, windowInsets);
        }
        l2 l2Var2 = this.f8192b;
        y1 y1Var = new y1(i9, new DecelerateInterpolator(), 160L);
        y1Var.f8207a.d(CropImageView.DEFAULT_ASPECT_RATIO);
        ValueAnimator duration = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f).setDuration(y1Var.f8207a.a());
        i0.c f7 = j2Var.f(i9);
        i0.c f8 = l2Var2.f8161a.f(i9);
        int min = Math.min(f7.f6773a, f8.f6773a);
        int i10 = f7.f6774b;
        int i11 = f8.f6774b;
        int min2 = Math.min(i10, i11);
        int i12 = f7.f6775c;
        int i13 = f8.f6775c;
        int min3 = Math.min(i12, i13);
        int i14 = f7.f6776d;
        int i15 = i9;
        int i16 = f8.f6776d;
        i6.i iVar = new i6.i(8, i0.c.b(min, min2, min3, Math.min(i14, i16)), i0.c.b(Math.max(f7.f6773a, f8.f6773a), Math.max(i10, i11), Math.max(i12, i13), Math.max(i14, i16)));
        u1.f(view, y1Var, windowInsets, false);
        duration.addUpdateListener(new r1(y1Var, h7, l2Var2, i15, view));
        duration.addListener(new j1(this, y1Var, view, 1));
        e0.a(view, new s1(view, y1Var, iVar, duration));
        this.f8192b = h7;
        return u1.i(view, windowInsets);
    }
}
