package k;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements k0.b {

    /* renamed from: a, reason: collision with root package name */
    public CharSequence f7030a;

    /* renamed from: b, reason: collision with root package name */
    public CharSequence f7031b;

    /* renamed from: c, reason: collision with root package name */
    public Intent f7032c;

    /* renamed from: d, reason: collision with root package name */
    public char f7033d;

    /* renamed from: f, reason: collision with root package name */
    public char f7035f;

    /* renamed from: h, reason: collision with root package name */
    public Drawable f7037h;

    /* renamed from: i, reason: collision with root package name */
    public final Context f7038i;

    /* renamed from: j, reason: collision with root package name */
    public CharSequence f7039j;

    /* renamed from: k, reason: collision with root package name */
    public CharSequence f7040k;

    /* renamed from: e, reason: collision with root package name */
    public int f7034e = 4096;

    /* renamed from: g, reason: collision with root package name */
    public int f7036g = 4096;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f7041l = null;

    /* renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f7042m = null;

    /* renamed from: n, reason: collision with root package name */
    public boolean f7043n = false;

    /* renamed from: o, reason: collision with root package name */
    public boolean f7044o = false;

    /* renamed from: p, reason: collision with root package name */
    public int f7045p = 16;

    public a(Context context, CharSequence charSequence) {
        this.f7038i = context;
        this.f7030a = charSequence;
    }

    @Override // k0.b
    public final p0.d a() {
        return null;
    }

    @Override // k0.b
    public final k0.b b(p0.d dVar) {
        throw new UnsupportedOperationException();
    }

    public final void c() {
        Drawable drawable = this.f7037h;
        if (drawable != null) {
            if (this.f7043n || this.f7044o) {
                this.f7037h = drawable;
                Drawable mutate = drawable.mutate();
                this.f7037h = mutate;
                if (this.f7043n) {
                    j0.b.h(mutate, this.f7041l);
                }
                if (this.f7044o) {
                    j0.b.i(this.f7037h, this.f7042m);
                }
            }
        }
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f7036g;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f7035f;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f7039j;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.f7037h;
    }

    @Override // k0.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f7041l;
    }

    @Override // k0.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f7042m;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f7032c;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f7034e;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f7033d;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f7030a;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7031b;
        return charSequence != null ? charSequence : this.f7030a;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f7040k;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f7045p & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f7045p & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f7045p & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f7045p & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c7) {
        this.f7035f = Character.toLowerCase(c7);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c7, int i7) {
        this.f7035f = Character.toLowerCase(c7);
        this.f7036g = KeyEvent.normalizeMetaState(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z6) {
        this.f7045p = (z6 ? 1 : 0) | (this.f7045p & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z6) {
        this.f7045p = (z6 ? 2 : 0) | (this.f7045p & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f7039j = charSequence;
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setContentDescription(CharSequence charSequence) {
        this.f7039j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z6) {
        this.f7045p = (z6 ? 16 : 0) | (this.f7045p & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i7) {
        Context context = this.f7038i;
        Object obj = g0.g.f6423a;
        this.f7037h = g0.c.b(context, i7);
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f7037h = drawable;
        c();
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f7041l = colorStateList;
        this.f7043n = true;
        c();
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f7042m = mode;
        this.f7044o = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f7032c = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c7) {
        this.f7033d = c7;
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c7, int i7) {
        this.f7033d = c7;
        this.f7034e = KeyEvent.normalizeMetaState(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c7, char c8) {
        this.f7033d = c7;
        this.f7035f = Character.toLowerCase(c8);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setShortcut(char c7, char c8, int i7, int i8) {
        this.f7033d = c7;
        this.f7034e = KeyEvent.normalizeMetaState(i7);
        this.f7035f = Character.toLowerCase(c8);
        this.f7036g = KeyEvent.normalizeMetaState(i8);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i7) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i7) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i7) {
        this.f7030a = this.f7038i.getResources().getString(i7);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f7030a = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7031b = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.f7040k = charSequence;
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setTooltipText(CharSequence charSequence) {
        this.f7040k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z6) {
        this.f7045p = (this.f7045p & 8) | (z6 ? 0 : 8);
        return this;
    }
}
