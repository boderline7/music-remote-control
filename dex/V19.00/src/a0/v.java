package a0;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v {
    public static final int UNSET = -1;

    /* renamed from: a, reason: collision with root package name */
    public HashMap f184a;

    public final void a(int i7, u uVar) {
        HashMap hashMap = this.f184a;
        HashSet hashSet = (HashSet) hashMap.get(Integer.valueOf(i7));
        if (hashSet == null) {
            hashSet = new HashSet();
            hashMap.put(Integer.valueOf(i7), hashSet);
        }
        hashSet.add(new WeakReference(uVar));
    }
}
