package s5;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b0 {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f8763a = new ArrayList(20);

    public final void a(String str, String str2) {
        i5.a.g("name", str);
        i5.a.g("value", str2);
        ArrayList arrayList = this.f8763a;
        arrayList.add(str);
        arrayList.add(o5.k.U(str2).toString());
    }

    public final d0 b() {
        return new d0((String[]) this.f8763a.toArray(new String[0]));
    }

    public final void c(String str) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f8763a;
            if (i7 >= arrayList.size()) {
                return;
            }
            if (o5.k.C(str, (String) arrayList.get(i7))) {
                arrayList.remove(i7);
                arrayList.remove(i7);
                i7 -= 2;
            }
            i7 += 2;
        }
    }
}
