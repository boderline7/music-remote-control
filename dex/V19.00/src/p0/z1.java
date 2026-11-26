package p0;

import android.view.View;
import java.lang.reflect.Field;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class z1 {

    /* renamed from: a, reason: collision with root package name */
    public static final Field f8208a;

    /* renamed from: b, reason: collision with root package name */
    public static final Field f8209b;

    /* renamed from: c, reason: collision with root package name */
    public static final Field f8210c;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f8211d;

    static {
        try {
            Field declaredField = View.class.getDeclaredField("mAttachInfo");
            f8208a = declaredField;
            declaredField.setAccessible(true);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            Field declaredField2 = cls.getDeclaredField("mStableInsets");
            f8209b = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mContentInsets");
            f8210c = declaredField3;
            declaredField3.setAccessible(true);
            f8211d = true;
        } catch (ReflectiveOperationException e7) {
            e7.getMessage();
        }
    }
}
