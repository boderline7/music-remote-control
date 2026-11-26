package q0;

import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import com.google.android.material.search.SearchBar;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements AccessibilityManager.TouchExplorationStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final d f8420a;

    public e(d dVar) {
        this.f8420a = dVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            return this.f8420a.equals(((e) obj).f8420a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f8420a.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z6) {
        e0.f fVar = (e0.f) this.f8420a;
        int i7 = fVar.f6293a;
        Object obj = fVar.f6294b;
        switch (i7) {
            case 5:
                int i8 = SearchBar.f4039m0;
                ((SearchBar) obj).setFocusableInTouchMode(z6);
                break;
            default:
                com.google.android.material.textfield.i iVar = (com.google.android.material.textfield.i) obj;
                AutoCompleteTextView autoCompleteTextView = iVar.f4344h;
                if (autoCompleteTextView != null && autoCompleteTextView.getInputType() == 0) {
                    int i9 = z6 ? 2 : 1;
                    int i10 = d1.OVER_SCROLL_ALWAYS;
                    l0.s(iVar.f4383d, i9);
                    break;
                }
                break;
        }
    }
}
