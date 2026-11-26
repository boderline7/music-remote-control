package androidx.appcompat.app;

import android.content.Context;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d0 implements Window.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final Window.Callback f701a;

    /* renamed from: b, reason: collision with root package name */
    public t0 f702b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f703c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f704d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f705e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ j0 f706f;

    public d0(j0 j0Var, Window.Callback callback) {
        this.f706f = j0Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f701a = callback;
    }

    public final void a(Window.Callback callback) {
        try {
            this.f703c = true;
            callback.onContentChanged();
        } finally {
            this.f703c = false;
        }
    }

    @Override // android.view.Window.Callback
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final void onActionModeFinished(ActionMode actionMode) {
        this.f701a.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void onActionModeStarted(ActionMode actionMode) {
        this.f701a.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final void onAttachedToWindow() {
        this.f701a.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f701a.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z6 = this.f704d;
        Window.Callback callback = this.f701a;
        return z6 ? callback.dispatchKeyEvent(keyEvent) : this.f706f.v(keyEvent) || callback.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (this.f701a.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        j0 j0Var = this.f706f;
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null && bVar.j(keyCode, keyEvent)) {
            return true;
        }
        i0 i0Var = j0Var.M;
        if (i0Var != null && j0Var.H(i0Var, keyEvent.getKeyCode(), keyEvent)) {
            i0 i0Var2 = j0Var.M;
            if (i0Var2 == null) {
                return true;
            }
            i0Var2.f740l = true;
            return true;
        }
        if (j0Var.M == null) {
            i0 B = j0Var.B(0);
            j0Var.I(B, keyEvent);
            boolean H = j0Var.H(B, keyEvent.getKeyCode(), keyEvent);
            B.f739k = false;
            if (H) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f701a.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f701a.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f701a.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final void onDetachedFromWindow() {
        this.f701a.onDetachedFromWindow();
    }

    public final boolean f(int i7, Menu menu) {
        return this.f701a.onMenuOpened(i7, menu);
    }

    public final void g(int i7, Menu menu) {
        this.f701a.onPanelClosed(i7, menu);
    }

    @Override // android.view.Window.Callback
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void onPointerCaptureChanged(boolean z6) {
        j.q.a(this.f701a, z6);
    }

    public final void i(List list, Menu menu, int i7) {
        j.p.a(this.f701a, list, menu, i7);
    }

    @Override // android.view.Window.Callback
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f701a.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void onWindowFocusChanged(boolean z6) {
        this.f701a.onWindowFocusChanged(z6);
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        if (this.f703c) {
            this.f701a.onContentChanged();
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i7, Menu menu) {
        if (i7 != 0 || (menu instanceof k.o)) {
            return this.f701a.onCreatePanelMenu(i7, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final View onCreatePanelView(int i7) {
        t0 t0Var = this.f702b;
        if (t0Var != null) {
            View view = i7 == 0 ? new View(t0Var.f855a.f858a.f1442a.getContext()) : null;
            if (view != null) {
                return view;
            }
        }
        return this.f701a.onCreatePanelView(i7);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        return this.f701a.onMenuItemSelected(i7, menuItem);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i7, Menu menu) {
        f(i7, menu);
        j0 j0Var = this.f706f;
        if (i7 == 108) {
            j0Var.C();
            b bVar = j0Var.f782o;
            if (bVar != null) {
                bVar.c(true);
            }
        } else {
            j0Var.getClass();
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i7, Menu menu) {
        if (this.f705e) {
            this.f701a.onPanelClosed(i7, menu);
            return;
        }
        g(i7, menu);
        j0 j0Var = this.f706f;
        if (i7 == 108) {
            j0Var.C();
            b bVar = j0Var.f782o;
            if (bVar != null) {
                bVar.c(false);
                return;
            }
            return;
        }
        if (i7 != 0) {
            j0Var.getClass();
            return;
        }
        i0 B = j0Var.B(i7);
        if (B.f741m) {
            j0Var.t(B, false);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i7, View view, Menu menu) {
        k.o oVar = menu instanceof k.o ? (k.o) menu : null;
        if (i7 == 0 && oVar == null) {
            return false;
        }
        if (oVar != null) {
            oVar.f7162x = true;
        }
        t0 t0Var = this.f702b;
        if (t0Var != null && i7 == 0) {
            v0 v0Var = t0Var.f855a;
            if (!v0Var.f861d) {
                v0Var.f858a.f1454m = true;
                v0Var.f861d = true;
            }
        }
        boolean onPreparePanel = this.f701a.onPreparePanel(i7, view, menu);
        if (oVar != null) {
            oVar.f7162x = false;
        }
        return onPreparePanel;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i7) {
        k.o oVar = this.f706f.B(0).f736h;
        if (oVar != null) {
            i(list, oVar, i7);
        } else {
            i(list, menu, i7);
        }
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.f701a.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return j.o.a(this.f701a, searchEvent);
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i7) {
        j0 j0Var = this.f706f;
        j0Var.getClass();
        if (i7 != 0) {
            return j.o.b(this.f701a, callback, i7);
        }
        Context context = j0Var.f778k;
        j.h hVar = new j.h();
        hVar.f6891b = context;
        hVar.f6890a = callback;
        hVar.f6892c = new ArrayList();
        hVar.f6893d = new q.k();
        j.c m6 = j0Var.m(hVar);
        if (m6 != null) {
            return hVar.m(m6);
        }
        return null;
    }
}
