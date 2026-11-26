package androidx.fragment.app;

import androidx.activity.result.ActivityResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p0 implements d.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2032a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ x0 f2033b;

    public /* synthetic */ p0(x0 x0Var, int i7) {
        this.f2032a = i7;
        this.f2033b = x0Var;
    }

    @Override // d.a
    public final void a(Object obj) {
        switch (this.f2032a) {
            case 0:
                b((ActivityResult) obj);
                break;
            case 1:
                Map map = (Map) obj;
                String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    iArr[i7] = ((Boolean) arrayList.get(i7)).booleanValue() ? 0 : -1;
                }
                x0 x0Var = this.f2033b;
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) x0Var.f2126y.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo != null) {
                    z c7 = x0Var.f2104c.c(fragmentManager$LaunchedFragmentInfo.f1881a);
                    if (c7 != null) {
                        c7.onRequestPermissionsResult(fragmentManager$LaunchedFragmentInfo.f1882b, strArr, iArr);
                        break;
                    }
                }
                break;
            default:
                b((ActivityResult) obj);
                break;
        }
    }

    public final void b(ActivityResult activityResult) {
        int i7 = this.f2032a;
        x0 x0Var = this.f2033b;
        switch (i7) {
            case 0:
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = (FragmentManager$LaunchedFragmentInfo) x0Var.f2126y.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo != null) {
                    z c7 = x0Var.f2104c.c(fragmentManager$LaunchedFragmentInfo.f1881a);
                    if (c7 != null) {
                        c7.onActivityResult(fragmentManager$LaunchedFragmentInfo.f1882b, activityResult.f648a, activityResult.f649b);
                        break;
                    }
                }
                break;
            default:
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo2 = (FragmentManager$LaunchedFragmentInfo) x0Var.f2126y.pollFirst();
                if (fragmentManager$LaunchedFragmentInfo2 != null) {
                    z c8 = x0Var.f2104c.c(fragmentManager$LaunchedFragmentInfo2.f1881a);
                    if (c8 != null) {
                        c8.onActivityResult(fragmentManager$LaunchedFragmentInfo2.f1882b, activityResult.f648a, activityResult.f649b);
                        break;
                    }
                }
                break;
        }
    }

    public final void c(z zVar, l0.d dVar) {
        boolean z6;
        synchronized (dVar) {
            z6 = dVar.f7269a;
        }
        if (z6) {
            return;
        }
        x0 x0Var = this.f2033b;
        Map map = x0Var.f2112k;
        HashSet hashSet = (HashSet) map.get(zVar);
        if (hashSet != null && hashSet.remove(dVar) && hashSet.isEmpty()) {
            map.remove(zVar);
            if (zVar.mState < 5) {
                zVar.performDestroyView();
                x0Var.f2114m.p(false);
                zVar.mContainer = null;
                zVar.mView = null;
                zVar.mViewLifecycleOwner = null;
                zVar.mViewLifecycleOwnerLiveData.h(null);
                zVar.mInLayout = false;
                x0Var.F(zVar, x0Var.f2116o);
            }
        }
    }

    public final void d(z zVar, l0.d dVar) {
        Map map = this.f2033b.f2112k;
        if (map.get(zVar) == null) {
            map.put(zVar, new HashSet());
        }
        ((HashSet) map.get(zVar)).add(dVar);
    }
}
