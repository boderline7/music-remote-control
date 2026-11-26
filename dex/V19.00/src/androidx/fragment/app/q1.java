package androidx.fragment.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class q1 {
    public static void d(List list, View view) {
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (list.get(i7) == view) {
                return;
            }
        }
        int i8 = p0.d1.OVER_SCROLL_ALWAYS;
        if (p0.r0.k(view) != null) {
            list.add(view);
        }
        for (int i9 = size; i9 < list.size(); i9++) {
            View view2 = (View) list.get(i9);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i10 = 0; i10 < childCount; i10++) {
                    View childAt = viewGroup.getChildAt(i10);
                    int i11 = 0;
                    while (true) {
                        if (i11 < size) {
                            if (list.get(i11) == childAt) {
                                break;
                            } else {
                                i11++;
                            }
                        } else if (p0.r0.k(childAt) != null) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    public static void g(View view, Rect rect) {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        if (p0.o0.b(view)) {
            RectF rectF = new RectF();
            rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, view.getWidth(), view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset(view.getLeft(), view.getTop());
            Object parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset(-view2.getScrollX(), -view2.getScrollY());
                view2.getMatrix().mapRect(rectF);
                rectF.offset(view2.getLeft(), view2.getTop());
                parent = view2.getParent();
            }
            view.getRootView().getLocationOnScreen(new int[2]);
            rectF.offset(r1[0], r1[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    public static boolean h(List list) {
        return list == null || list.isEmpty();
    }

    public static ArrayList k(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view = (View) arrayList.get(i7);
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            arrayList2.add(p0.r0.k(view));
            p0.r0.v(view, null);
        }
        return arrayList2;
    }

    public static void q(ViewGroup viewGroup, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, q.b bVar) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i7 = 0; i7 < size; i7++) {
            View view = (View) arrayList.get(i7);
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            String k7 = p0.r0.k(view);
            arrayList4.add(k7);
            if (k7 != null) {
                p0.r0.v(view, null);
                String str = (String) bVar.getOrDefault(k7, null);
                int i9 = 0;
                while (true) {
                    if (i9 >= size) {
                        break;
                    }
                    if (str.equals(arrayList3.get(i9))) {
                        p0.r0.v((View) arrayList2.get(i9), k7);
                        break;
                    }
                    i9++;
                }
            }
        }
        p0.e0.a(viewGroup, new p1(size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    public abstract void a(View view, Object obj);

    public abstract void b(Object obj, ArrayList arrayList);

    public abstract void c(ViewGroup viewGroup, Object obj);

    public abstract boolean e(Object obj);

    public abstract Object f(Object obj);

    public abstract Object i(Object obj, Object obj2, Object obj3);

    public abstract Object j(Object obj, Object obj2, Object obj3);

    public abstract void l(Object obj, View view, ArrayList arrayList);

    public abstract void m(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3);

    public abstract void n(View view, Object obj);

    public abstract void o(Object obj, Rect rect);

    public void p(Object obj, Runnable runnable) {
        runnable.run();
    }

    public abstract void r(Object obj, View view, ArrayList arrayList);

    public abstract void s(Object obj, ArrayList arrayList, ArrayList arrayList2);

    public abstract Object t(Object obj);
}
