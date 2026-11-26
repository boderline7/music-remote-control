package p0;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c1 {

    /* renamed from: d, reason: collision with root package name */
    public static final ArrayList f8105d = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    public WeakHashMap f8106a;

    /* renamed from: b, reason: collision with root package name */
    public SparseArray f8107b;

    /* renamed from: c, reason: collision with root package name */
    public WeakReference f8108c;

    public final View a(View view) {
        int size;
        WeakHashMap weakHashMap = this.f8106a;
        if (weakHashMap != null && weakHashMap.containsKey(view)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View a7 = a(viewGroup.getChildAt(childCount));
                    if (a7 != null) {
                        return a7;
                    }
                }
            }
            ArrayList arrayList = (ArrayList) view.getTag(d0.c.tag_unhandled_key_listeners);
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                a.a.u(arrayList.get(size));
                throw null;
            }
        }
        return null;
    }
}
