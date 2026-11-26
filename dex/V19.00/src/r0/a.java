package r0;

import android.graphics.Path;
import android.view.animation.PathInterpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static PathInterpolator a(float f7, float f8) {
        return new PathInterpolator(f7, f8);
    }

    public static PathInterpolator b(float f7, float f8, float f9, float f10) {
        return new PathInterpolator(f7, f8, f9, f10);
    }

    public static PathInterpolator c(Path path) {
        return new PathInterpolator(path);
    }
}
