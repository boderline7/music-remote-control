package androidx.appcompat.widget;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t2 implements View.OnFocusChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SearchView f1416a;

    public t2(SearchView searchView) {
        this.f1416a = searchView;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z6) {
        SearchView searchView = this.f1416a;
        View.OnFocusChangeListener onFocusChangeListener = searchView.L;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(searchView, z6);
        }
    }
}
