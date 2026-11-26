package d3;

import android.content.res.ColorStateList;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import androidx.activity.t;
import j0.b;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final int INTRINSIC_SIZE = -1;

    public static Drawable a(Drawable drawable, Drawable drawable2, int i7, int i8) {
        if (drawable == null) {
            return drawable2;
        }
        if (drawable2 == null) {
            return drawable;
        }
        if (i7 == -1 && (i7 = drawable2.getIntrinsicWidth()) == -1) {
            i7 = drawable.getIntrinsicWidth();
        }
        if (i8 == -1 && (i8 = drawable2.getIntrinsicHeight()) == -1) {
            i8 = drawable.getIntrinsicHeight();
        }
        if (i7 > drawable.getIntrinsicWidth() || i8 > drawable.getIntrinsicHeight()) {
            float f7 = i7 / i8;
            if (f7 >= drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight()) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                i8 = (int) (intrinsicWidth / f7);
                i7 = intrinsicWidth;
            } else {
                i8 = drawable.getIntrinsicHeight();
                i7 = (int) (f7 * i8);
            }
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable, drawable2});
        layerDrawable.setLayerSize(1, i7, i8);
        layerDrawable.setLayerGravity(1, 17);
        return layerDrawable;
    }

    public static Drawable b(Drawable drawable, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (drawable == null) {
            return null;
        }
        if (colorStateList != null) {
            drawable = drawable.mutate();
            if (mode != null) {
                b.i(drawable, mode);
            }
        }
        return drawable;
    }

    public static int[] c(int[] iArr) {
        for (int i7 = 0; i7 < iArr.length; i7++) {
            int i8 = iArr[i7];
            if (i8 == 16842912) {
                return iArr;
            }
            if (i8 == 0) {
                int[] iArr2 = (int[]) iArr.clone();
                iArr2[i7] = 16842912;
                return iArr2;
            }
        }
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[iArr.length] = 16842912;
        return copyOf;
    }

    public static ColorStateList d(Drawable drawable) {
        ColorStateList colorStateList;
        if (drawable instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) drawable).getColor());
        }
        if (Build.VERSION.SDK_INT < 29 || !t.y(drawable)) {
            return null;
        }
        colorStateList = t.d(drawable).getColorStateList();
        return colorStateList;
    }

    public static void e(Outline outline, Path path) {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30) {
            outline.setPath(path);
            return;
        }
        if (i7 >= 29) {
            try {
                outline.setConvexPath(path);
            } catch (IllegalArgumentException unused) {
            }
        } else if (path.isConvex()) {
            outline.setConvexPath(path);
        }
    }
}
