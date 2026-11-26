package androidx.fragment.app;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l extends y1 {
    public static void j(ArrayList arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (p0.g1.b(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if (childAt.getVisibility() == 0) {
                j(arrayList, childAt);
            }
        }
    }

    public static void k(q.b bVar, View view) {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        String k7 = p0.r0.k(view);
        if (k7 != null) {
            bVar.put(k7, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (childAt.getVisibility() == 0) {
                    k(bVar, childAt);
                }
            }
        }
    }

    public static void l(q.b bVar, Collection collection) {
        Iterator it = ((q.h) bVar.entrySet()).iterator();
        while (it.hasNext()) {
            View view = (View) ((Map.Entry) it.next()).getValue();
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            if (!collection.contains(p0.r0.k(view))) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:236:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0453  */
    @Override // androidx.fragment.app.y1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(java.util.ArrayList r35, boolean r36) {
        /*
            Method dump skipped, instructions count: 1592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.l.b(java.util.ArrayList, boolean):void");
    }
}
