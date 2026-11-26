package androidx.appcompat.widget;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ViewGroup;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r2 implements TextWatcher {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1390a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1391b;

    public /* synthetic */ r2(ViewGroup viewGroup, int i7) {
        this.f1390a = i7;
        this.f1391b = viewGroup;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        switch (this.f1390a) {
            case 0:
            case 1:
                break;
            default:
                TextInputLayout textInputLayout = (TextInputLayout) this.f1391b;
                textInputLayout.u(!textInputLayout.f4320z0, false);
                if (textInputLayout.f4289k) {
                    textInputLayout.n(editable);
                }
                if (textInputLayout.f4305s) {
                    textInputLayout.v(editable);
                    break;
                }
                break;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        int i10 = this.f1390a;
        ViewGroup viewGroup = this.f1391b;
        switch (i10) {
            case 0:
                SearchView searchView = (SearchView) viewGroup;
                Editable text = searchView.f1110p.getText();
                searchView.f1102a0 = text;
                boolean isEmpty = TextUtils.isEmpty(text);
                searchView.x(!isEmpty);
                if (searchView.V && !searchView.O && isEmpty) {
                    searchView.f1115u.setVisibility(8);
                    r4 = 0;
                }
                searchView.f1117w.setVisibility(r4);
                searchView.t();
                searchView.w();
                if (searchView.K != null && !TextUtils.equals(charSequence, searchView.W)) {
                    searchView.K.onQueryTextChange(charSequence.toString());
                }
                searchView.W = charSequence.toString();
                break;
            case 1:
                ((com.google.android.material.search.SearchView) viewGroup).f4064k.setVisibility(charSequence.length() > 0 ? 0 : 8);
                break;
        }
    }
}
