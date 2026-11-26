package e3;

import android.content.Context;
import android.graphics.Color;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;
import q2.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: f, reason: collision with root package name */
    public static final int f6365f = (int) Math.round(5.1000000000000005d);

    /* renamed from: a, reason: collision with root package name */
    public final boolean f6366a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6367b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6368c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6369d;

    /* renamed from: e, reason: collision with root package name */
    public final float f6370e;

    public a(Context context) {
        boolean t02 = f.t0(context, c.elevationOverlayEnabled, false);
        int c7 = b3.a.c(context, c.elevationOverlayColor, 0);
        int c8 = b3.a.c(context, c.elevationOverlayAccentColor, 0);
        int c9 = b3.a.c(context, c.colorSurface, 0);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.f6366a = t02;
        this.f6367b = c7;
        this.f6368c = c8;
        this.f6369d = c9;
        this.f6370e = f7;
    }

    public final int a(float f7, int i7) {
        int i8;
        if (!this.f6366a || i0.a.e(i7, 255) != this.f6369d) {
            return i7;
        }
        float min = (this.f6370e <= CropImageView.DEFAULT_ASPECT_RATIO || f7 <= CropImageView.DEFAULT_ASPECT_RATIO) ? 0.0f : Math.min(((((float) Math.log1p(f7 / r1)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int alpha = Color.alpha(i7);
        int f8 = b3.a.f(i0.a.e(i7, 255), min, this.f6367b);
        if (min > CropImageView.DEFAULT_ASPECT_RATIO && (i8 = this.f6368c) != 0) {
            f8 = i0.a.c(i0.a.e(i8, f6365f), f8);
        }
        return i0.a.e(f8, alpha);
    }
}
