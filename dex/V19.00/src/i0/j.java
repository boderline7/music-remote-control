package i0;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class j extends i {

    /* renamed from: h, reason: collision with root package name */
    public final Class f6788h;

    /* renamed from: i, reason: collision with root package name */
    public final Constructor f6789i;

    /* renamed from: j, reason: collision with root package name */
    public final Method f6790j;

    /* renamed from: k, reason: collision with root package name */
    public final Method f6791k;

    /* renamed from: l, reason: collision with root package name */
    public final Method f6792l;

    /* renamed from: m, reason: collision with root package name */
    public final Method f6793m;

    /* renamed from: n, reason: collision with root package name */
    public final Method f6794n;

    public j() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = K(cls);
            method3 = L(cls);
            method4 = cls.getMethod("freeze", new Class[0]);
            method = cls.getMethod("abortCreation", new Class[0]);
            method5 = M(cls);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
            method3 = null;
            method4 = null;
            method5 = null;
        }
        this.f6788h = cls;
        this.f6789i = constructor;
        this.f6790j = method2;
        this.f6791k = method3;
        this.f6792l = method4;
        this.f6793m = method;
        this.f6794n = method5;
    }

    public static Method K(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public static Method L(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public final void F(Object obj) {
        try {
            this.f6793m.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public final boolean G(Context context, Object obj, String str, int i7, int i8, int i9, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f6790j.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface H(Object obj) {
        try {
            Object newInstance = Array.newInstance((Class<?>) this.f6788h, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f6794n.invoke(null, newInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean I(Object obj) {
        try {
            return ((Boolean) this.f6792l.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final Object J() {
        try {
            return this.f6789i.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public Method M(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    @Override // i0.i, e.a
    public final Typeface t(Context context, h0.f fVar, Resources resources, int i7) {
        if (this.f6790j == null) {
            return super.t(context, fVar, resources, i7);
        }
        Object J = J();
        if (J == null) {
            return null;
        }
        for (h0.g gVar : fVar.f6620a) {
            if (!G(context, J, gVar.f6621a, gVar.f6625e, gVar.f6622b, gVar.f6623c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(gVar.f6624d))) {
                F(J);
                return null;
            }
        }
        if (I(J)) {
            return H(J);
        }
        return null;
    }

    @Override // e.a
    public final Typeface u(Context context, m0.i[] iVarArr, int i7) {
        Typeface H;
        if (iVarArr.length < 1) {
            return null;
        }
        if (this.f6790j == null) {
            m0.i A = A(i7, iVarArr);
            try {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(A.f7503a, "r", null);
                if (openFileDescriptor == null) {
                    if (openFileDescriptor != null) {
                        openFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface build = new Typeface.Builder(openFileDescriptor.getFileDescriptor()).setWeight(A.f7505c).setItalic(A.f7506d).build();
                    openFileDescriptor.close();
                    return build;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        HashMap hashMap = new HashMap();
        for (m0.i iVar : iVarArr) {
            if (iVar.f7507e == 0) {
                Uri uri = iVar.f7503a;
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, z.M0(context, uri));
                }
            }
        }
        Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
        Object J = J();
        if (J == null) {
            return null;
        }
        int length = iVarArr.length;
        int i8 = 0;
        boolean z6 = false;
        while (i8 < length) {
            m0.i iVar2 = iVarArr[i8];
            ByteBuffer byteBuffer = (ByteBuffer) unmodifiableMap.get(iVar2.f7503a);
            if (byteBuffer != null) {
                if (!((Boolean) this.f6791k.invoke(J, byteBuffer, Integer.valueOf(iVar2.f7504b), null, Integer.valueOf(iVar2.f7505c), Integer.valueOf(iVar2.f7506d ? 1 : 0))).booleanValue()) {
                    F(J);
                    return null;
                }
                z6 = true;
            }
            i8++;
            z6 = z6;
        }
        if (!z6) {
            F(J);
            return null;
        }
        if (I(J) && (H = H(J)) != null) {
            return Typeface.create(H, i7);
        }
        return null;
    }

    @Override // e.a
    public final Typeface w(Context context, Resources resources, int i7, String str, int i8) {
        if (this.f6790j == null) {
            return super.w(context, resources, i7, str, i8);
        }
        Object J = J();
        if (J == null) {
            return null;
        }
        if (!G(context, J, str, 0, -1, -1, null)) {
            F(J);
            return null;
        }
        if (I(J)) {
            return H(J);
        }
        return null;
    }
}
