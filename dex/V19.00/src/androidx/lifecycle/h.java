package androidx.lifecycle;

import android.widget.FrameLayout;
import java.util.HashMap;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h implements v {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2183a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2184b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2185c;

    public h(f fVar, v vVar) {
        i5.a.g("defaultLifecycleObserver", fVar);
        this.f2184b = fVar;
        this.f2185c = vVar;
    }

    public h(s sVar, j1.g gVar) {
        this.f2184b = sVar;
        this.f2185c = gVar;
    }

    public h(androidx.viewpager2.adapter.g gVar, androidx.viewpager2.adapter.h hVar) {
        this.f2185c = gVar;
        this.f2184b = hVar;
    }

    public h(Object obj) {
        this.f2184b = obj;
        this.f2185c = d.f2159c.b(obj.getClass());
    }

    @Override // androidx.lifecycle.v
    public final void a(x xVar, q qVar) {
        int i7 = this.f2183a;
        Object obj = this.f2184b;
        Object obj2 = this.f2185c;
        switch (i7) {
            case 0:
                switch (g.$EnumSwitchMapping$0[qVar.ordinal()]) {
                    case 1:
                        ((f) obj).onCreate(xVar);
                        break;
                    case 2:
                        ((f) obj).onStart(xVar);
                        break;
                    case 3:
                        ((f) obj).onResume(xVar);
                        break;
                    case 4:
                        ((f) obj).onPause(xVar);
                        break;
                    case 5:
                        ((f) obj).onStop(xVar);
                        break;
                    case 6:
                        ((f) obj).onDestroy(xVar);
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                }
                v vVar = (v) obj2;
                if (vVar != null) {
                    vVar.a(xVar, qVar);
                    return;
                }
                return;
            case 1:
                if (qVar == q.ON_START) {
                    ((s) obj).b(this);
                    ((j1.g) obj2).d();
                    return;
                }
                return;
            case 2:
                HashMap hashMap = ((b) obj2).f2147a;
                b.a((List) hashMap.get(qVar), xVar, qVar, obj);
                b.a((List) hashMap.get(q.ON_ANY), xVar, qVar, obj);
                return;
            default:
                androidx.viewpager2.adapter.g gVar = (androidx.viewpager2.adapter.g) obj2;
                if (gVar.shouldDelayFragmentTransactions()) {
                    return;
                }
                xVar.getLifecycle().b(this);
                androidx.viewpager2.adapter.h hVar = (androidx.viewpager2.adapter.h) obj;
                FrameLayout frameLayout = (FrameLayout) hVar.itemView;
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                if (p0.o0.b(frameLayout)) {
                    gVar.placeFragmentInViewHolder(hVar);
                    return;
                }
                return;
        }
    }
}
