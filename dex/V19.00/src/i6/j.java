package i6;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {

    /* renamed from: d, reason: collision with root package name */
    public static final ArrayList f6850d = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    public Object f6851a;

    /* renamed from: b, reason: collision with root package name */
    public p f6852b;

    /* renamed from: c, reason: collision with root package name */
    public j f6853c;

    public static j a(Object obj, p pVar) {
        ArrayList arrayList = f6850d;
        synchronized (arrayList) {
            try {
                int size = arrayList.size();
                if (size <= 0) {
                    j jVar = new j();
                    jVar.f6851a = obj;
                    jVar.f6852b = pVar;
                    return jVar;
                }
                j jVar2 = (j) arrayList.remove(size - 1);
                jVar2.f6851a = obj;
                jVar2.f6852b = pVar;
                jVar2.f6853c = null;
                return jVar2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
