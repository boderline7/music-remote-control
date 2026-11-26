package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o2 extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ScrollingTabContainerView f1369a;

    public o2(ScrollingTabContainerView scrollingTabContainerView) {
        this.f1369a = scrollingTabContainerView;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f1369a.f1094b.getChildCount();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i7) {
        ((p2) this.f1369a.f1094b.getChildAt(i7)).getClass();
        return null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        if (view != null) {
            getItem(i7);
            throw null;
        }
        getItem(i7);
        ScrollingTabContainerView scrollingTabContainerView = this.f1369a;
        scrollingTabContainerView.getClass();
        new p2(scrollingTabContainerView, scrollingTabContainerView.getContext());
        throw null;
    }
}
