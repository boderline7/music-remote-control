package k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class f0 extends d implements Menu {

    /* renamed from: d, reason: collision with root package name */
    public final k0.a f7067d;

    public f0(Context context, k0.a aVar) {
        super(context);
        if (aVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f7067d = aVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7) {
        return n(((o) this.f7067d).add(i7));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i8, int i9, int i10) {
        return n(((o) this.f7067d).add(i7, i8, i9, i10));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i8, int i9, CharSequence charSequence) {
        return n(((o) this.f7067d).a(i7, i8, i9, charSequence));
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return n(((o) this.f7067d).a(0, 0, 0, charSequence));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i7, int i8, int i9, ComponentName componentName, Intent[] intentArr, Intent intent, int i10, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = ((o) this.f7067d).addIntentOptions(i7, i8, i9, componentName, intentArr, intent, i10, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i11 = 0; i11 < length; i11++) {
                menuItemArr[i11] = n(menuItemArr2[i11]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7) {
        return ((o) this.f7067d).addSubMenu(i7);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7, int i8, int i9, int i10) {
        return ((o) this.f7067d).addSubMenu(i7, i8, i9, i10);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        return this.f7067d.addSubMenu(i7, i8, i9, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return ((o) this.f7067d).addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final void clear() {
        q.k kVar = (q.k) this.f7061b;
        if (kVar != null) {
            kVar.clear();
        }
        q.k kVar2 = (q.k) this.f7062c;
        if (kVar2 != null) {
            kVar2.clear();
        }
        ((o) this.f7067d).clear();
    }

    @Override // android.view.Menu
    public final void close() {
        ((o) this.f7067d).c(true);
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i7) {
        return n(((o) this.f7067d).findItem(i7));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i7) {
        return n(((o) this.f7067d).getItem(i7));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return ((o) this.f7067d).hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i7, KeyEvent keyEvent) {
        return ((o) this.f7067d).isShortcutKey(i7, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i7, int i8) {
        return ((o) this.f7067d).performIdentifierAction(i7, i8);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i7, KeyEvent keyEvent, int i8) {
        return ((o) this.f7067d).performShortcut(i7, keyEvent, i8);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i7) {
        if (((q.k) this.f7061b) != null) {
            int i8 = 0;
            while (true) {
                q.k kVar = (q.k) this.f7061b;
                if (i8 >= kVar.f8412c) {
                    break;
                }
                if (((k0.b) kVar.h(i8)).getGroupId() == i7) {
                    ((q.k) this.f7061b).i(i8);
                    i8--;
                }
                i8++;
            }
        }
        ((o) this.f7067d).removeGroup(i7);
    }

    @Override // android.view.Menu
    public final void removeItem(int i7) {
        if (((q.k) this.f7061b) != null) {
            int i8 = 0;
            while (true) {
                q.k kVar = (q.k) this.f7061b;
                if (i8 >= kVar.f8412c) {
                    break;
                }
                if (((k0.b) kVar.h(i8)).getItemId() == i7) {
                    ((q.k) this.f7061b).i(i8);
                    break;
                }
                i8++;
            }
        }
        ((o) this.f7067d).removeItem(i7);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i7, boolean z6, boolean z7) {
        ((o) this.f7067d).setGroupCheckable(i7, z6, z7);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i7, boolean z6) {
        ((o) this.f7067d).setGroupEnabled(i7, z6);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i7, boolean z6) {
        ((o) this.f7067d).setGroupVisible(i7, z6);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z6) {
        this.f7067d.setQwertyMode(z6);
    }

    @Override // android.view.Menu
    public final int size() {
        return ((o) this.f7067d).f7144f.size();
    }
}
