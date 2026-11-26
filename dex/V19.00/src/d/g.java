package d;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.fragment.app.p0;
import androidx.lifecycle.a0;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f6250a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f6251b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f6252c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f6253d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public final transient HashMap f6254e = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    public final HashMap f6255f = new HashMap();

    /* renamed from: g, reason: collision with root package name */
    public final Bundle f6256g = new Bundle();

    public final boolean a(int i7, int i8, Intent intent) {
        a aVar;
        String str = (String) this.f6250a.get(Integer.valueOf(i7));
        if (str == null) {
            return false;
        }
        e eVar = (e) this.f6254e.get(str);
        if (eVar == null || (aVar = eVar.f6246a) == null || !this.f6253d.contains(str)) {
            this.f6255f.remove(str);
            this.f6256g.putParcelable(str, new ActivityResult(intent, i8));
            return true;
        }
        aVar.a(eVar.f6247b.c(intent, i8));
        this.f6253d.remove(str);
        return true;
    }

    public abstract void b(int i7, e.b bVar, Object obj);

    public final d c(String str, x xVar, e.b bVar, a aVar) {
        s lifecycle = xVar.getLifecycle();
        a0 a0Var = (a0) lifecycle;
        if (a0Var.f2136c.a(r.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + xVar + " is attempting to register while current state is " + a0Var.f2136c + ". LifecycleOwners must call register before they are STARTED.");
        }
        e(str);
        HashMap hashMap = this.f6252c;
        f fVar = (f) hashMap.get(str);
        if (fVar == null) {
            fVar = new f(lifecycle);
        }
        c cVar = new c(this, str, aVar, bVar);
        fVar.f6248a.a(cVar);
        fVar.f6249b.add(cVar);
        hashMap.put(str, fVar);
        return new d(this, str, bVar, 0);
    }

    public final d d(String str, e.b bVar, p0 p0Var) {
        e(str);
        this.f6254e.put(str, new e(bVar, p0Var));
        HashMap hashMap = this.f6255f;
        if (hashMap.containsKey(str)) {
            Object obj = hashMap.get(str);
            hashMap.remove(str);
            p0Var.a(obj);
        }
        Bundle bundle = this.f6256g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            p0Var.a(bVar.c(activityResult.f649b, activityResult.f648a));
        }
        return new d(this, str, bVar, 1);
    }

    public final void e(String str) {
        HashMap hashMap = this.f6251b;
        if (((Integer) hashMap.get(str)) != null) {
            return;
        }
        j5.d.Default.getClass();
        int nextInt = j5.d.f7023a.a().nextInt(2147418112);
        while (true) {
            int i7 = nextInt + 65536;
            HashMap hashMap2 = this.f6250a;
            if (!hashMap2.containsKey(Integer.valueOf(i7))) {
                hashMap2.put(Integer.valueOf(i7), str);
                hashMap.put(str, Integer.valueOf(i7));
                return;
            } else {
                j5.d.Default.getClass();
                nextInt = j5.d.f7023a.a().nextInt(2147418112);
            }
        }
    }

    public final void f(String str) {
        Integer num;
        if (!this.f6253d.contains(str) && (num = (Integer) this.f6251b.remove(str)) != null) {
            this.f6250a.remove(num);
        }
        this.f6254e.remove(str);
        HashMap hashMap = this.f6255f;
        if (hashMap.containsKey(str)) {
            Objects.toString(hashMap.get(str));
            hashMap.remove(str);
        }
        Bundle bundle = this.f6256g;
        if (bundle.containsKey(str)) {
            Objects.toString(bundle.getParcelable(str));
            bundle.remove(str);
        }
        HashMap hashMap2 = this.f6252c;
        f fVar = (f) hashMap2.get(str);
        if (fVar != null) {
            ArrayList arrayList = fVar.f6249b;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                fVar.f6248a.b((v) it.next());
            }
            arrayList.clear();
            hashMap2.remove(str);
        }
    }
}
