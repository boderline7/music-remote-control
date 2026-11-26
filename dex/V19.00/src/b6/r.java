package b6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import s5.q0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {
    public static ArrayList a(List list) {
        i5.a.g("protocols", list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((q0) obj) != q0.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(c5.l.x1(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((q0) it.next()).f8872a);
        }
        return arrayList2;
    }

    public static byte[] b(List list) {
        i5.a.g("protocols", list);
        g6.h hVar = new g6.h();
        Iterator it = a(list).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            hVar.N(str.length());
            hVar.S(str);
        }
        return hVar.G(hVar.f6573b);
    }
}
