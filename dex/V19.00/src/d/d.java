package d;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends b {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6242a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Serializable f6243b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e.b f6244c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f6245d;

    public d(Object obj, Serializable serializable, e.b bVar, int i7) {
        this.f6242a = i7;
        this.f6245d = obj;
        this.f6243b = serializable;
        this.f6244c = bVar;
    }

    @Override // d.b
    public final void a(Object obj) {
        int i7 = this.f6242a;
        e.b bVar = this.f6244c;
        Object obj2 = this.f6245d;
        Serializable serializable = this.f6243b;
        switch (i7) {
            case 0:
                g gVar = (g) obj2;
                String str = (String) serializable;
                Integer num = (Integer) gVar.f6251b.get(str);
                if (num != null) {
                    gVar.f6253d.add(str);
                    try {
                        ((g) obj2).b(num.intValue(), bVar, obj);
                        return;
                    } catch (Exception e7) {
                        gVar.f6253d.remove(str);
                        throw e7;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + bVar + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
            case 1:
                g gVar2 = (g) obj2;
                String str2 = (String) serializable;
                Integer num2 = (Integer) gVar2.f6251b.get(str2);
                if (num2 != null) {
                    gVar2.f6253d.add(str2);
                    try {
                        ((g) obj2).b(num2.intValue(), bVar, obj);
                        return;
                    } catch (Exception e8) {
                        gVar2.f6253d.remove(str2);
                        throw e8;
                    }
                }
                throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + bVar + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
            default:
                b bVar2 = (b) ((AtomicReference) serializable).get();
                if (bVar2 == null) {
                    throw new IllegalStateException("Operation cannot be started before fragment is in created state");
                }
                bVar2.a(obj);
                return;
        }
    }

    @Override // d.b
    public final void b() {
        int i7 = this.f6242a;
        Object obj = this.f6245d;
        Serializable serializable = this.f6243b;
        switch (i7) {
            case 0:
                ((g) obj).f((String) serializable);
                break;
            case 1:
                ((g) obj).f((String) serializable);
                break;
            default:
                b bVar = (b) ((AtomicReference) serializable).getAndSet(null);
                if (bVar != null) {
                    bVar.b();
                    break;
                }
                break;
        }
    }
}
