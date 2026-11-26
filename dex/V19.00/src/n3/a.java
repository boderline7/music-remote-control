package n3;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.StateSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final boolean USE_FRAMEWORK_RIPPLE = true;

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f7774a = {R.attr.state_pressed};

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f7775b = {R.attr.state_hovered, R.attr.state_focused};

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f7776c = {R.attr.state_focused};

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f7777d = {R.attr.state_hovered};

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f7778e = {R.attr.state_selected, R.attr.state_pressed};

    /* renamed from: f, reason: collision with root package name */
    public static final int[] f7779f = {R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f7780g = {R.attr.state_selected, R.attr.state_focused};

    /* renamed from: h, reason: collision with root package name */
    public static final int[] f7781h = {R.attr.state_selected, R.attr.state_hovered};

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f7782i = {R.attr.state_selected};

    /* renamed from: j, reason: collision with root package name */
    public static final int[] f7783j = {R.attr.state_enabled, R.attr.state_pressed};

    public static ColorStateList a(ColorStateList colorStateList) {
        boolean z6 = USE_FRAMEWORK_RIPPLE;
        int[] iArr = f7774a;
        int[] iArr2 = f7776c;
        int[] iArr3 = f7778e;
        int[] iArr4 = f7782i;
        if (z6) {
            return new ColorStateList(new int[][]{iArr4, iArr2, StateSet.NOTHING}, new int[]{b(colorStateList, iArr3), b(colorStateList, iArr2), b(colorStateList, iArr)});
        }
        int[] iArr5 = f7779f;
        int[] iArr6 = f7780g;
        int[] iArr7 = f7781h;
        int[] iArr8 = f7775b;
        int[] iArr9 = f7777d;
        return new ColorStateList(new int[][]{iArr3, iArr5, iArr6, iArr7, iArr4, iArr, iArr8, iArr2, iArr9, StateSet.NOTHING}, new int[]{b(colorStateList, iArr3), b(colorStateList, iArr5), b(colorStateList, iArr6), b(colorStateList, iArr7), 0, b(colorStateList, iArr), b(colorStateList, iArr8), b(colorStateList, iArr2), b(colorStateList, iArr9), 0});
    }

    public static int b(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? i0.a.e(colorForState, Math.min(Color.alpha(colorForState) * 2, 255)) : colorForState;
    }

    public static ColorStateList c(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        if (Build.VERSION.SDK_INT <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0) {
            Color.alpha(colorStateList.getColorForState(f7783j, 0));
        }
        return colorStateList;
    }

    public static boolean d(int[] iArr) {
        boolean z6 = false;
        boolean z7 = false;
        for (int i7 : iArr) {
            if (i7 == 16842910) {
                z6 = true;
            } else if (i7 == 16842908 || i7 == 16842919 || i7 == 16843623) {
                z7 = true;
            }
        }
        return z6 && z7;
    }
}
