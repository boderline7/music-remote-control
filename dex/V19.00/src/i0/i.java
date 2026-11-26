package i0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i extends e.a {

    /* renamed from: c, reason: collision with root package name */
    public static Class f6783c;

    /* renamed from: d, reason: collision with root package name */
    public static Constructor f6784d;

    /* renamed from: e, reason: collision with root package name */
    public static Method f6785e;

    /* renamed from: f, reason: collision with root package name */
    public static Method f6786f;

    /* renamed from: g, reason: collision with root package name */
    public static boolean f6787g;

    public static boolean D(int i7, Object obj, String str, boolean z6) {
        E();
        try {
            return ((Boolean) f6785e.invoke(obj, str, Integer.valueOf(i7), Boolean.valueOf(z6))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e7) {
            throw new RuntimeException(e7);
        }
    }

    public static void E() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (f6787g) {
            return;
        }
        f6787g = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f6784d = constructor;
        f6783c = cls;
        f6785e = method2;
        f6786f = method;
    }

    @Override // e.a
    public Typeface t(Context context, h0.f fVar, Resources resources, int i7) {
        E();
        try {
            Object newInstance = f6784d.newInstance(new Object[0]);
            for (h0.g gVar : fVar.f6620a) {
                File s02 = z.s0(context);
                if (s02 == null) {
                    return null;
                }
                try {
                    if (!z.I(s02, resources, gVar.f6626f)) {
                        return null;
                    }
                    if (!D(gVar.f6622b, newInstance, s02.getPath(), gVar.f6623c)) {
                        return null;
                    }
                    s02.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    s02.delete();
                }
            }
            E();
            try {
                Object newInstance2 = Array.newInstance((Class<?>) f6783c, 1);
                Array.set(newInstance2, 0, newInstance);
                return (Typeface) f6786f.invoke(null, newInstance2);
            } catch (IllegalAccessException | InvocationTargetException e7) {
                throw new RuntimeException(e7);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e8) {
            throw new RuntimeException(e8);
        }
    }
}
