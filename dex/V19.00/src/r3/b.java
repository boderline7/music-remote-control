package r3;

import android.animation.TimeInterpolator;
import android.os.Handler;
import android.os.Looper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {
    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;

    static {
        TimeInterpolator timeInterpolator = r2.a.LINEAR_INTERPOLATOR;
        new Handler(Looper.getMainLooper(), new a());
    }
}
