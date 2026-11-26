package s5;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends i5.h implements h5.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8801a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f8802b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8803c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f8804d;

    public i(j jVar, Object obj, Object obj2, int i7) {
        this.f8801a = i7;
        this.f8802b = jVar;
        this.f8803c = obj;
        this.f8804d = obj2;
    }

    @Override // h5.a
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.f8801a) {
        }
        return c();
    }

    public final List c() {
        List<Certificate> list;
        int i7 = this.f8801a;
        Object obj = this.f8804d;
        Object obj2 = this.f8803c;
        j jVar = this.f8802b;
        switch (i7) {
            case 0:
                f6.e eVar = jVar.f8807b;
                if (eVar == null || (list = eVar.a((List) obj2, (String) obj)) == null) {
                    list = (List) obj2;
                }
                ArrayList arrayList = new ArrayList(c5.l.x1(list));
                for (Certificate certificate : list) {
                    i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", certificate);
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            default:
                f6.e eVar2 = jVar.f8807b;
                i5.a.d(eVar2);
                return eVar2.a(((a0) obj2).a(), ((a) obj).f8755i.f8795d);
        }
    }
}
