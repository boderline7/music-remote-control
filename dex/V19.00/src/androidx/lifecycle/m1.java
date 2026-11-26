package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class m1 implements j1 {
    public static final l1 Companion = new l1();
    public static final e1.b VIEW_MODEL_KEY = k1.INSTANCE;

    /* renamed from: a, reason: collision with root package name */
    public static m1 f2189a;

    @Override // androidx.lifecycle.j1
    public e1 a(Class cls) {
        try {
            Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            i5.a.f("{\n                modelC…wInstance()\n            }", newInstance);
            return (e1) newInstance;
        } catch (IllegalAccessException e7) {
            throw new RuntimeException("Cannot create an instance of " + cls, e7);
        } catch (InstantiationException e8) {
            throw new RuntimeException("Cannot create an instance of " + cls, e8);
        } catch (NoSuchMethodException e9) {
            throw new RuntimeException("Cannot create an instance of " + cls, e9);
        }
    }
}
