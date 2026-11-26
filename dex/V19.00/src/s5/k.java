package s5;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public final String f8808a;

    /* renamed from: b, reason: collision with root package name */
    public final Map f8809b;

    public k(String str, Map map) {
        String str2;
        this.f8808a = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            String str3 = (String) entry.getKey();
            String str4 = (String) entry.getValue();
            if (str3 != null) {
                Locale locale = Locale.US;
                i5.a.f("US", locale);
                str2 = str3.toLowerCase(locale);
                i5.a.f("this as java.lang.String).toLowerCase(locale)", str2);
            } else {
                str2 = null;
            }
            linkedHashMap.put(str2, str4);
        }
        Map unmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        i5.a.f("unmodifiableMap<String?, String>(newAuthParams)", unmodifiableMap);
        this.f8809b = unmodifiableMap;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            k kVar = (k) obj;
            if (i5.a.c(kVar.f8808a, this.f8808a) && i5.a.c(kVar.f8809b, this.f8809b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f8809b.hashCode() + ((this.f8808a.hashCode() + 899) * 31);
    }

    public final String toString() {
        return this.f8808a + " authParams=" + this.f8809b;
    }
}
