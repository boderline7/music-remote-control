package n1;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends s {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f7624a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7625b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f7626c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7627d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f7628e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7629f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ l f7630g;

    public k(l lVar, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.f7630g = lVar;
        this.f7624a = obj;
        this.f7625b = arrayList;
        this.f7626c = obj2;
        this.f7627d = arrayList2;
        this.f7628e = obj3;
        this.f7629f = arrayList3;
    }

    @Override // n1.s, n1.q
    public final void b() {
        l lVar = this.f7630g;
        Object obj = this.f7624a;
        if (obj != null) {
            lVar.u(obj, this.f7625b, null);
        }
        Object obj2 = this.f7626c;
        if (obj2 != null) {
            lVar.u(obj2, this.f7627d, null);
        }
        Object obj3 = this.f7628e;
        if (obj3 != null) {
            lVar.u(obj3, this.f7629f, null);
        }
    }

    @Override // n1.q
    public final void d(r rVar) {
        rVar.v(this);
    }
}
