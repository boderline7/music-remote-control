package e5;

import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a INSTANCE = new a();
    public static final Method addSuppressed;
    public static final Method getSuppressed;

    static {
        Method method;
        Method method2;
        Method[] methods = Throwable.class.getMethods();
        i5.a.f("throwableMethods", methods);
        int length = methods.length;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            method = null;
            if (i8 >= length) {
                method2 = null;
                break;
            }
            method2 = methods[i8];
            if (i5.a.c(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                i5.a.f("it.parameterTypes", parameterTypes);
                if (i5.a.c(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    break;
                }
            }
            i8++;
        }
        addSuppressed = method2;
        int length2 = methods.length;
        while (true) {
            if (i7 >= length2) {
                break;
            }
            Method method3 = methods[i7];
            if (i5.a.c(method3.getName(), "getSuppressed")) {
                method = method3;
                break;
            }
            i7++;
        }
        getSuppressed = method;
    }
}
