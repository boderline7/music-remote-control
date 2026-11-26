package j;

import android.view.MenuItem;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements MenuItem.OnMenuItemClickListener {

    /* renamed from: c, reason: collision with root package name */
    public static final Class[] f6896c = {MenuItem.class};

    /* renamed from: a, reason: collision with root package name */
    public Object f6897a;

    /* renamed from: b, reason: collision with root package name */
    public Method f6898b;

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        Method method = this.f6898b;
        try {
            Class<?> returnType = method.getReturnType();
            Class<?> cls = Boolean.TYPE;
            Object obj = this.f6897a;
            if (returnType == cls) {
                return ((Boolean) method.invoke(obj, menuItem)).booleanValue();
            }
            method.invoke(obj, menuItem);
            return true;
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }
}
