package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a implements e2.i, Comparable {
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(a aVar) {
        Class<?> cls = getClass();
        Class<?> cls2 = aVar.getClass();
        return cls != cls2 ? cls.getName().compareTo(cls2.getName()) : e(aVar);
    }

    public abstract int e(a aVar);

    public abstract String f();
}
