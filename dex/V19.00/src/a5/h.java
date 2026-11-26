package a5;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends HashMap {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f336a;

    public h(l lVar) {
        this.f336a = lVar;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        this.f336a.f345f.put(str == null ? str : str.toLowerCase(), str2);
        return (String) super.put(str, str2);
    }
}
