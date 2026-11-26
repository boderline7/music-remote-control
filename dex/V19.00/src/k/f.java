package k;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import com.google.android.material.search.SearchBar;
import p0.d1;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements View.OnAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7065a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7066b;

    public /* synthetic */ f(int i7, Object obj) {
        this.f7065a = i7;
        this.f7066b = obj;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        AccessibilityManager accessibilityManager;
        int i7 = this.f7065a;
        Object obj = this.f7066b;
        switch (i7) {
            case 0:
            case 1:
                break;
            case 2:
                SearchBar searchBar = (SearchBar) obj;
                q0.c.a(searchBar.f4050k0, searchBar.f4051l0);
                break;
            default:
                com.google.android.material.textfield.l lVar = (com.google.android.material.textfield.l) obj;
                int i8 = com.google.android.material.textfield.l.f4357w;
                if (lVar.f4378u != null && (accessibilityManager = lVar.f4377t) != null) {
                    int i9 = d1.OVER_SCROLL_ALWAYS;
                    if (o0.b(lVar)) {
                        q0.c.a(accessibilityManager, lVar.f4378u);
                        break;
                    }
                }
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        AccessibilityManager accessibilityManager;
        int i7 = this.f7065a;
        Object obj = this.f7066b;
        switch (i7) {
            case 0:
                i iVar = (i) obj;
                ViewTreeObserver viewTreeObserver = iVar.f7120y;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        iVar.f7120y = view.getViewTreeObserver();
                    }
                    iVar.f7120y.removeGlobalOnLayoutListener(iVar.f7105j);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            case 1:
                h0 h0Var = (h0) obj;
                ViewTreeObserver viewTreeObserver2 = h0Var.f7091p;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        h0Var.f7091p = view.getViewTreeObserver();
                    }
                    h0Var.f7091p.removeGlobalOnLayoutListener(h0Var.f7085j);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            case 2:
                SearchBar searchBar = (SearchBar) obj;
                q0.c.b(searchBar.f4050k0, searchBar.f4051l0);
                break;
            default:
                com.google.android.material.textfield.l lVar = (com.google.android.material.textfield.l) obj;
                int i8 = com.google.android.material.textfield.l.f4357w;
                q0.d dVar = lVar.f4378u;
                if (dVar != null && (accessibilityManager = lVar.f4377t) != null) {
                    q0.c.b(accessibilityManager, dVar);
                    break;
                }
                break;
        }
    }
}
