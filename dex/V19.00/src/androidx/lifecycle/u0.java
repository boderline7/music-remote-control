package androidx.lifecycle;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class u0 {
    public static v0 a(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            if (bundle2 == null) {
                return new v0();
            }
            HashMap hashMap = new HashMap();
            for (String str : bundle2.keySet()) {
                i5.a.f("key", str);
                hashMap.put(str, bundle2.get(str));
            }
            return new v0(hashMap);
        }
        ClassLoader classLoader = v0.class.getClassLoader();
        i5.a.d(classLoader);
        bundle.setClassLoader(classLoader);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state".toString());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = parcelableArrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Object obj = parcelableArrayList.get(i7);
            i5.a.e("null cannot be cast to non-null type kotlin.String", obj);
            linkedHashMap.put((String) obj, parcelableArrayList2.get(i7));
        }
        return new v0(linkedHashMap);
    }
}
