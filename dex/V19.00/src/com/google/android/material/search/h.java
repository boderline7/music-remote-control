package com.google.android.material.search;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import p0.d1;
import p0.s2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4090a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchView f4091b;

    public /* synthetic */ h(SearchView searchView, int i7) {
        this.f4090a = i7;
        this.f4091b = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s2 h7;
        s2 h8;
        int i7 = this.f4090a;
        SearchView searchView = this.f4091b;
        switch (i7) {
            case 0:
                searchView.l();
                break;
            case 1:
                EditText editText = searchView.f4063j;
                if (editText.requestFocus()) {
                    editText.sendAccessibilityEvent(8);
                }
                if (searchView.f4079z && (h7 = d1.h(editText)) != null) {
                    h7.f8190a.J();
                    break;
                } else {
                    Context context = editText.getContext();
                    Object obj = g0.g.f6423a;
                    ((InputMethodManager) g0.d.b(context, InputMethodManager.class)).showSoftInput(editText, 1);
                    break;
                }
            case 2:
                EditText editText2 = searchView.f4063j;
                editText2.clearFocus();
                SearchBar searchBar = searchView.f4073t;
                if (searchBar != null) {
                    searchBar.requestFocus();
                }
                if (searchView.f4079z && (h8 = d1.h(editText2)) != null) {
                    h8.f8190a.y(8);
                    break;
                } else {
                    Context context2 = editText2.getContext();
                    Object obj2 = g0.g.f6423a;
                    InputMethodManager inputMethodManager = (InputMethodManager) g0.d.b(context2, InputMethodManager.class);
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                        break;
                    }
                }
                break;
            default:
                searchView.j();
                break;
        }
    }
}
