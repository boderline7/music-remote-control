package androidx.appcompat.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l0 implements ListAdapter, SpinnerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public SpinnerAdapter f1306a;

    /* renamed from: b, reason: collision with root package name */
    public ListAdapter f1307b;

    @Override // android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.f1307b;
        if (listAdapter != null) {
            return listAdapter.areAllItemsEnabled();
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter == null) {
            return 0;
        }
        return spinnerAdapter.getCount();
    }

    @Override // android.widget.SpinnerAdapter
    public final View getDropDownView(int i7, View view, ViewGroup viewGroup) {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getDropDownView(i7, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i7) {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getItem(i7);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter == null) {
            return -1L;
        }
        return spinnerAdapter.getItemId(i7);
    }

    @Override // android.widget.Adapter
    public final int getItemViewType(int i7) {
        return 0;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        return getDropDownView(i7, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.Adapter
    public final boolean hasStableIds() {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        return spinnerAdapter != null && spinnerAdapter.hasStableIds();
    }

    @Override // android.widget.Adapter
    public final boolean isEmpty() {
        return getCount() == 0;
    }

    @Override // android.widget.ListAdapter
    public final boolean isEnabled(int i7) {
        ListAdapter listAdapter = this.f1307b;
        if (listAdapter != null) {
            return listAdapter.isEnabled(i7);
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter != null) {
            spinnerAdapter.registerDataSetObserver(dataSetObserver);
        }
    }

    @Override // android.widget.Adapter
    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1306a;
        if (spinnerAdapter != null) {
            spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
