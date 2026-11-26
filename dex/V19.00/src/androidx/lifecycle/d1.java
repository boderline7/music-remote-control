package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class d1 {

    /* renamed from: a, reason: collision with root package name */
    public static final List f2164a;

    /* renamed from: b, reason: collision with root package name */
    public static final List f2165b;

    static {
        List asList = Arrays.asList(Application.class, v0.class);
        i5.a.f("asList(this)", asList);
        f2164a = asList;
        f2165b = n1.z.G0(v0.class);
    }

    public static final Constructor a(List list, Class cls) {
        i5.a.g("signature", list);
        Constructor<?>[] constructors = cls.getConstructors();
        i5.a.f("modelClass.constructors", constructors);
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            i5.a.f("constructor.parameterTypes", parameterTypes);
            int length = parameterTypes.length;
            List arrayList = length != 0 ? length != 1 ? new ArrayList(new c5.i(parameterTypes, false)) : n1.z.G0(parameterTypes[0]) : c5.r.INSTANCE;
            if (i5.a.c(list, arrayList)) {
                return constructor;
            }
            if (list.size() == arrayList.size() && arrayList.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final e1 b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (e1) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e7) {
            throw new RuntimeException("Failed to access " + cls, e7);
        } catch (InstantiationException e8) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e8);
        } catch (InvocationTargetException e9) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e9.getCause());
        }
    }
}
