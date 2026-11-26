package n1;

import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class x {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f7682a;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f7683b;

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f7684c;

    static {
        int i7 = Build.VERSION.SDK_INT;
        f7682a = true;
        f7683b = true;
        f7684c = i7 >= 28;
    }
}
