package m0;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements o0.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7493a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7494b;

    public /* synthetic */ e(int i7, Object obj) {
        this.f7493a = i7;
        this.f7494b = obj;
    }

    public final /* bridge */ /* synthetic */ void a(Object obj) {
        switch (this.f7493a) {
            case 0:
                b((f) obj);
                break;
            default:
                b((f) obj);
                break;
        }
    }

    public final void b(f fVar) {
        switch (this.f7493a) {
            case 0:
                if (fVar == null) {
                    fVar = new f(-3);
                }
                ((i6.i) this.f7494b).r(fVar);
                return;
            default:
                synchronized (g.f7499c) {
                    try {
                        q.k kVar = g.f7500d;
                        ArrayList arrayList = (ArrayList) kVar.getOrDefault((String) this.f7494b, null);
                        if (arrayList == null) {
                            return;
                        }
                        kVar.remove((String) this.f7494b);
                        for (int i7 = 0; i7 < arrayList.size(); i7++) {
                            ((e) ((o0.a) arrayList.get(i7))).a(fVar);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
