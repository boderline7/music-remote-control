package e;

import android.content.Intent;
import androidx.activity.o;
import c5.l;
import g0.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends b {
    public static final String ACTION_REQUEST_PERMISSIONS = "androidx.activity.result.contract.action.REQUEST_PERMISSIONS";
    public static final c Companion = new c();
    public static final String EXTRA_PERMISSIONS = "androidx.activity.result.contract.extra.PERMISSIONS";
    public static final String EXTRA_PERMISSION_GRANT_RESULTS = "androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS";

    @Override // e.b
    public final Intent a(o oVar, Object obj) {
        String[] strArr = (String[]) obj;
        i5.a.g("context", oVar);
        i5.a.g("input", strArr);
        Companion.getClass();
        Intent putExtra = new Intent(ACTION_REQUEST_PERMISSIONS).putExtra(EXTRA_PERMISSIONS, strArr);
        i5.a.f("Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)", putExtra);
        return putExtra;
    }

    @Override // e.b
    public final a b(o oVar, Object obj) {
        String[] strArr = (String[]) obj;
        i5.a.g("context", oVar);
        i5.a.g("input", strArr);
        if (strArr.length == 0) {
            return new a(0, l.B1());
        }
        for (String str : strArr) {
            if (g.a(oVar, str) != 0) {
                return null;
            }
        }
        int I0 = z.I0(strArr.length);
        if (I0 < 16) {
            I0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(I0);
        for (String str2 : strArr) {
            linkedHashMap.put(str2, Boolean.TRUE);
        }
        return new a(0, linkedHashMap);
    }

    @Override // e.b
    public final Object c(Intent intent, int i7) {
        if (i7 == -1 && intent != null) {
            String[] stringArrayExtra = intent.getStringArrayExtra(EXTRA_PERMISSIONS);
            int[] intArrayExtra = intent.getIntArrayExtra(EXTRA_PERMISSION_GRANT_RESULTS);
            if (intArrayExtra == null || stringArrayExtra == null) {
                return l.B1();
            }
            ArrayList arrayList = new ArrayList(intArrayExtra.length);
            for (int i8 : intArrayExtra) {
                arrayList.add(Boolean.valueOf(i8 == 0));
            }
            ArrayList arrayList2 = new ArrayList();
            for (String str : stringArrayExtra) {
                if (str != null) {
                    arrayList2.add(str);
                }
            }
            Iterator it = arrayList2.iterator();
            Iterator it2 = arrayList.iterator();
            ArrayList arrayList3 = new ArrayList(Math.min(l.x1(arrayList2), l.x1(arrayList)));
            while (it.hasNext() && it2.hasNext()) {
                arrayList3.add(new b5.a(it.next(), it2.next()));
            }
            return l.E1(arrayList3);
        }
        return l.B1();
    }
}
