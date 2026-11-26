package androidx.fragment.app;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r0 {

    /* renamed from: b, reason: collision with root package name */
    public static final q.k f2045b = new q.k();

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x0 f2046a;

    public r0(x0 x0Var) {
        this.f2046a = x0Var;
    }

    public static Class a(ClassLoader classLoader, String str) {
        q.k kVar = f2045b;
        q.k kVar2 = (q.k) kVar.getOrDefault(classLoader, null);
        if (kVar2 == null) {
            kVar2 = new q.k();
            kVar.put(classLoader, kVar2);
        }
        Class cls = (Class) kVar2.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        kVar2.put(str, cls2);
        return cls2;
    }

    public static Class b(ClassLoader classLoader, String str) {
        try {
            return a(classLoader, str);
        } catch (ClassCastException e7) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e7);
        } catch (ClassNotFoundException e8) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": make sure class name exists"), e8);
        }
    }
}
