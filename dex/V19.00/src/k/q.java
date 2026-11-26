package k;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q implements k0.b {
    public p0.d A;
    public MenuItem.OnActionExpandListener B;

    /* renamed from: a, reason: collision with root package name */
    public final int f7166a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7167b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7168c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7169d;

    /* renamed from: e, reason: collision with root package name */
    public CharSequence f7170e;

    /* renamed from: f, reason: collision with root package name */
    public CharSequence f7171f;

    /* renamed from: g, reason: collision with root package name */
    public Intent f7172g;

    /* renamed from: h, reason: collision with root package name */
    public char f7173h;

    /* renamed from: j, reason: collision with root package name */
    public char f7175j;

    /* renamed from: l, reason: collision with root package name */
    public Drawable f7177l;

    /* renamed from: n, reason: collision with root package name */
    public final o f7179n;

    /* renamed from: o, reason: collision with root package name */
    public i0 f7180o;

    /* renamed from: p, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f7181p;

    /* renamed from: q, reason: collision with root package name */
    public CharSequence f7182q;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f7183r;

    /* renamed from: y, reason: collision with root package name */
    public int f7190y;

    /* renamed from: z, reason: collision with root package name */
    public View f7191z;

    /* renamed from: i, reason: collision with root package name */
    public int f7174i = 4096;

    /* renamed from: k, reason: collision with root package name */
    public int f7176k = 4096;

    /* renamed from: m, reason: collision with root package name */
    public int f7178m = 0;

    /* renamed from: s, reason: collision with root package name */
    public ColorStateList f7184s = null;

    /* renamed from: t, reason: collision with root package name */
    public PorterDuff.Mode f7185t = null;

    /* renamed from: u, reason: collision with root package name */
    public boolean f7186u = false;

    /* renamed from: v, reason: collision with root package name */
    public boolean f7187v = false;

    /* renamed from: w, reason: collision with root package name */
    public boolean f7188w = false;

    /* renamed from: x, reason: collision with root package name */
    public int f7189x = 16;
    public boolean C = false;

    public q(o oVar, int i7, int i8, int i9, int i10, CharSequence charSequence, int i11) {
        this.f7179n = oVar;
        this.f7166a = i8;
        this.f7167b = i7;
        this.f7168c = i9;
        this.f7169d = i10;
        this.f7170e = charSequence;
        this.f7190y = i11;
    }

    public static void c(int i7, int i8, String str, StringBuilder sb) {
        if ((i7 & i8) == i8) {
            sb.append(str);
        }
    }

    @Override // k0.b
    public final p0.d a() {
        return this.A;
    }

    @Override // k0.b
    public final k0.b b(p0.d dVar) {
        p0.d dVar2 = this.A;
        if (dVar2 != null) {
            dVar2.f8109a = null;
        }
        this.f7191z = null;
        this.A = dVar;
        this.f7179n.p(true);
        p0.d dVar3 = this.A;
        if (dVar3 != null) {
            dVar3.d(new e.a(4, this));
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.f7190y & 8) == 0) {
            return false;
        }
        if (this.f7191z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f7179n.d(this);
        }
        return false;
    }

    public final Drawable d(Drawable drawable) {
        if (drawable != null && this.f7188w && (this.f7186u || this.f7187v)) {
            drawable = drawable.mutate();
            if (this.f7186u) {
                j0.b.h(drawable, this.f7184s);
            }
            if (this.f7187v) {
                j0.b.i(drawable, this.f7185t);
            }
            this.f7188w = false;
        }
        return drawable;
    }

    public final boolean e() {
        p0.d dVar;
        if ((this.f7190y & 8) == 0) {
            return false;
        }
        if (this.f7191z == null && (dVar = this.A) != null) {
            this.f7191z = dVar.b(this);
        }
        return this.f7191z != null;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!e()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f7179n.f(this);
        }
        return false;
    }

    public final boolean f() {
        return (this.f7189x & 32) == 32;
    }

    public final void g(boolean z6) {
        this.f7189x = (z6 ? 4 : 0) | (this.f7189x & (-5));
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.f7191z;
        if (view != null) {
            return view;
        }
        p0.d dVar = this.A;
        if (dVar == null) {
            return null;
        }
        View b7 = dVar.b(this);
        this.f7191z = b7;
        return b7;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f7176k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f7175j;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f7182q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f7167b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.f7177l;
        if (drawable != null) {
            return d(drawable);
        }
        int i7 = this.f7178m;
        if (i7 == 0) {
            return null;
        }
        Drawable A = l6.f.A(this.f7179n.f7139a, i7);
        this.f7178m = 0;
        this.f7177l = A;
        return d(A);
    }

    @Override // k0.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f7184s;
    }

    @Override // k0.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f7185t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f7172g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.f7166a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f7174i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f7173h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.f7168c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.f7180o;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f7170e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7171f;
        return charSequence != null ? charSequence : this.f7170e;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f7183r;
    }

    public final void h(boolean z6) {
        if (z6) {
            this.f7189x |= 32;
        } else {
            this.f7189x &= -33;
        }
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return this.f7180o != null;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.C;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f7189x & 1) == 1;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f7189x & 2) == 2;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f7189x & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        p0.d dVar = this.A;
        return (dVar == null || !dVar.c()) ? (this.f7189x & 8) == 0 : (this.f7189x & 8) == 0 && this.A.a();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i7) {
        int i8;
        Context context = this.f7179n.f7139a;
        View inflate = LayoutInflater.from(context).inflate(i7, (ViewGroup) new LinearLayout(context), false);
        this.f7191z = inflate;
        this.A = null;
        if (inflate != null && inflate.getId() == -1 && (i8 = this.f7166a) > 0) {
            inflate.setId(i8);
        }
        o oVar = this.f7179n;
        oVar.f7149k = true;
        oVar.p(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i7;
        this.f7191z = view;
        this.A = null;
        if (view != null && view.getId() == -1 && (i7 = this.f7166a) > 0) {
            view.setId(i7);
        }
        o oVar = this.f7179n;
        oVar.f7149k = true;
        oVar.p(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c7) {
        if (this.f7175j == c7) {
            return this;
        }
        this.f7175j = Character.toLowerCase(c7);
        this.f7179n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c7, int i7) {
        if (this.f7175j == c7 && this.f7176k == i7) {
            return this;
        }
        this.f7175j = Character.toLowerCase(c7);
        this.f7176k = KeyEvent.normalizeMetaState(i7);
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z6) {
        int i7 = this.f7189x;
        int i8 = (z6 ? 1 : 0) | (i7 & (-2));
        this.f7189x = i8;
        if (i7 != i8) {
            this.f7179n.p(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z6) {
        int i7 = this.f7189x;
        if ((i7 & 4) != 0) {
            o oVar = this.f7179n;
            oVar.getClass();
            ArrayList arrayList = oVar.f7144f;
            int size = arrayList.size();
            oVar.y();
            for (int i8 = 0; i8 < size; i8++) {
                q qVar = (q) arrayList.get(i8);
                if (qVar.f7167b == this.f7167b && (qVar.f7189x & 4) != 0 && qVar.isCheckable()) {
                    boolean z7 = qVar == this;
                    int i9 = qVar.f7189x;
                    int i10 = (z7 ? 2 : 0) | (i9 & (-3));
                    qVar.f7189x = i10;
                    if (i9 != i10) {
                        qVar.f7179n.p(false);
                    }
                }
            }
            oVar.x();
        } else {
            int i11 = (i7 & (-3)) | (z6 ? 2 : 0);
            this.f7189x = i11;
            if (i7 != i11) {
                this.f7179n.p(false);
            }
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setContentDescription(CharSequence charSequence) {
        this.f7182q = charSequence;
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z6) {
        if (z6) {
            this.f7189x |= 16;
        } else {
            this.f7189x &= -17;
        }
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i7) {
        this.f7177l = null;
        this.f7178m = i7;
        this.f7188w = true;
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f7178m = 0;
        this.f7177l = drawable;
        this.f7188w = true;
        this.f7179n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7184s = colorStateList;
        this.f7186u = true;
        this.f7188w = true;
        this.f7179n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7185t = mode;
        this.f7187v = true;
        this.f7188w = true;
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f7172g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c7) {
        if (this.f7173h == c7) {
            return this;
        }
        this.f7173h = c7;
        this.f7179n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c7, int i7) {
        if (this.f7173h == c7 && this.f7174i == i7) {
            return this;
        }
        this.f7173h = c7;
        this.f7174i = KeyEvent.normalizeMetaState(i7);
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.B = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7181p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c7, char c8) {
        this.f7173h = c7;
        this.f7175j = Character.toLowerCase(c8);
        this.f7179n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setShortcut(char c7, char c8, int i7, int i8) {
        this.f7173h = c7;
        this.f7174i = KeyEvent.normalizeMetaState(i7);
        this.f7175j = Character.toLowerCase(c8);
        this.f7176k = KeyEvent.normalizeMetaState(i8);
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i7) {
        int i8 = i7 & 3;
        if (i8 != 0 && i8 != 1 && i8 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f7190y = i7;
        o oVar = this.f7179n;
        oVar.f7149k = true;
        oVar.p(true);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i7) {
        setShowAsAction(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i7) {
        setTitle(this.f7179n.f7139a.getString(i7));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f7170e = charSequence;
        this.f7179n.p(false);
        i0 i0Var = this.f7180o;
        if (i0Var != null) {
            i0Var.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7171f = charSequence;
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setTooltipText(CharSequence charSequence) {
        this.f7183r = charSequence;
        this.f7179n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z6) {
        int i7 = this.f7189x;
        int i8 = (z6 ? 0 : 8) | (i7 & (-9));
        this.f7189x = i8;
        if (i7 != i8) {
            o oVar = this.f7179n;
            oVar.f7146h = true;
            oVar.p(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f7170e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }
}
