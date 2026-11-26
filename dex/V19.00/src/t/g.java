package t;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f8960a;

    public g(int i7) {
        if (i7 != 1) {
            this.f8960a = new HashMap();
        } else {
            this.f8960a = new HashMap(50);
        }
    }

    public final float a(Object obj, String str) {
        HashMap hashMap;
        float[] fArr;
        if (this.f8960a.containsKey(obj) && (hashMap = (HashMap) this.f8960a.get(obj)) != null && hashMap.containsKey(str) && (fArr = (float[]) hashMap.get(str)) != null && fArr.length > 0) {
            return fArr[0];
        }
        return Float.NaN;
    }
}
