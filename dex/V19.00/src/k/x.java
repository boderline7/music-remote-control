package k;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class x implements g0, c0, AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public Rect f7202a;

    public static int o(l lVar, Context context, int i7) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = lVar.getCount();
        int i8 = 0;
        int i9 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i10 = 0; i10 < count; i10++) {
            int itemViewType = lVar.getItemViewType(i10);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = lVar.getView(i10, view, frameLayout);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i7) {
                return i7;
            }
            if (measuredWidth > i8) {
                i8 = measuredWidth;
            }
        }
        return i8;
    }

    public static boolean w(o oVar) {
        int size = oVar.f7144f.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = oVar.getItem(i7);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // k.c0
    public final boolean c(q qVar) {
        return false;
    }

    @Override // k.c0
    public final boolean e(q qVar) {
        return false;
    }

    @Override // k.c0
    public final void f(Context context, o oVar) {
    }

    @Override // k.c0
    public final int getId() {
        return 0;
    }

    public abstract void n(o oVar);

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        (listAdapter instanceof HeaderViewListAdapter ? (l) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter() : (l) listAdapter).f7132a.q((MenuItem) listAdapter.getItem(i7), this, (this instanceof i) ^ true ? 0 : 4);
    }

    public abstract void p(View view);

    public abstract void q(boolean z6);

    public abstract void r(int i7);

    public abstract void s(int i7);

    public abstract void t(PopupWindow.OnDismissListener onDismissListener);

    public abstract void u(boolean z6);

    public abstract void v(int i7);
}
