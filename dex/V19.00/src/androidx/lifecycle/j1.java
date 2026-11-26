package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public interface j1 {
    public static final i1 Companion = i1.f2188a;

    default e1 a(Class cls) {
        throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
    }

    default e1 b(Class cls, e1.d dVar) {
        return a(cls);
    }
}
