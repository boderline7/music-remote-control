package d;

import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f6238a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a f6239b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ e.b f6240c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g f6241d;

    public c(g gVar, String str, a aVar, e.b bVar) {
        this.f6241d = gVar;
        this.f6238a = str;
        this.f6239b = aVar;
        this.f6240c = bVar;
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        boolean equals = q.ON_START.equals(qVar);
        String str = this.f6238a;
        g gVar = this.f6241d;
        if (!equals) {
            if (q.ON_STOP.equals(qVar)) {
                gVar.f6254e.remove(str);
                return;
            } else {
                if (q.ON_DESTROY.equals(qVar)) {
                    gVar.f(str);
                    return;
                }
                return;
            }
        }
        HashMap hashMap = gVar.f6254e;
        e.b bVar = this.f6240c;
        a aVar = this.f6239b;
        hashMap.put(str, new e(bVar, aVar));
        HashMap hashMap2 = gVar.f6255f;
        if (hashMap2.containsKey(str)) {
            Object obj = hashMap2.get(str);
            hashMap2.remove(str);
            aVar.a(obj);
        }
        Bundle bundle = gVar.f6256g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            aVar.a(bVar.c(activityResult.f649b, activityResult.f648a));
        }
    }
}
