package androidx.appcompat.widget;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class o1 {

    /* renamed from: a, reason: collision with root package name */
    public static final Method f1365a;

    /* renamed from: b, reason: collision with root package name */
    public static final Method f1366b;

    /* renamed from: c, reason: collision with root package name */
    public static final Method f1367c;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f1368d;

    static {
        try {
            Class cls = Integer.TYPE;
            Class cls2 = Float.TYPE;
            Method declaredMethod = AbsListView.class.getDeclaredMethod("positionSelector", cls, View.class, Boolean.TYPE, cls2, cls2);
            f1365a = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", cls);
            f1366b = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", cls);
            f1367c = declaredMethod3;
            declaredMethod3.setAccessible(true);
            f1368d = true;
        } catch (NoSuchMethodException e7) {
            e7.printStackTrace();
        }
    }
}
