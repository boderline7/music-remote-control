package k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p0.f1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class o implements k0.a {

    /* renamed from: y, reason: collision with root package name */
    public static final int[] f7138y = {1, 4, 5, 3, 2, 0};

    /* renamed from: a, reason: collision with root package name */
    public final Context f7139a;

    /* renamed from: b, reason: collision with root package name */
    public final Resources f7140b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f7141c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f7142d;

    /* renamed from: e, reason: collision with root package name */
    public m f7143e;

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f7144f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f7145g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f7146h;

    /* renamed from: i, reason: collision with root package name */
    public final ArrayList f7147i;

    /* renamed from: j, reason: collision with root package name */
    public final ArrayList f7148j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f7149k;

    /* renamed from: m, reason: collision with root package name */
    public CharSequence f7151m;

    /* renamed from: n, reason: collision with root package name */
    public Drawable f7152n;

    /* renamed from: o, reason: collision with root package name */
    public View f7153o;

    /* renamed from: v, reason: collision with root package name */
    public q f7160v;

    /* renamed from: x, reason: collision with root package name */
    public boolean f7162x;

    /* renamed from: l, reason: collision with root package name */
    public int f7150l = 0;

    /* renamed from: p, reason: collision with root package name */
    public boolean f7154p = false;

    /* renamed from: q, reason: collision with root package name */
    public boolean f7155q = false;

    /* renamed from: r, reason: collision with root package name */
    public boolean f7156r = false;

    /* renamed from: s, reason: collision with root package name */
    public boolean f7157s = false;

    /* renamed from: t, reason: collision with root package name */
    public final ArrayList f7158t = new ArrayList();

    /* renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f7159u = new CopyOnWriteArrayList();

    /* renamed from: w, reason: collision with root package name */
    public boolean f7161w = false;

    public o(Context context) {
        Resources resources;
        int identifier;
        boolean z6 = false;
        this.f7139a = context;
        Resources resources2 = context.getResources();
        this.f7140b = resources2;
        this.f7144f = new ArrayList();
        this.f7145g = new ArrayList();
        this.f7146h = true;
        this.f7147i = new ArrayList();
        this.f7148j = new ArrayList();
        this.f7149k = true;
        if (resources2.getConfiguration().keyboard != 1) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            if (Build.VERSION.SDK_INT < 28 ? !((identifier = (resources = context.getResources()).getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android")) == 0 || !resources.getBoolean(identifier)) : f1.b(viewConfiguration)) {
                z6 = true;
            }
        }
        this.f7142d = z6;
    }

    public q a(int i7, int i8, int i9, CharSequence charSequence) {
        int i10;
        int i11 = ((-65536) & i9) >> 16;
        if (i11 < 0 || i11 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i12 = (f7138y[i11] << 16) | (65535 & i9);
        q qVar = new q(this, i7, i8, i9, i12, charSequence, this.f7150l);
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                i10 = 0;
                break;
            }
            if (((q) arrayList.get(size)).f7169d <= i12) {
                i10 = size + 1;
                break;
            }
            size--;
        }
        arrayList.add(i10, qVar);
        p(true);
        return qVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7) {
        return a(0, 0, 0, this.f7140b.getString(i7));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i8, int i9, int i10) {
        return a(i7, i8, i9, this.f7140b.getString(i10));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i7, int i8, int i9, CharSequence charSequence) {
        return a(i7, i8, i9, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i7, int i8, int i9, ComponentName componentName, Intent[] intentArr, Intent intent, int i10, MenuItem[] menuItemArr) {
        int i11;
        PackageManager packageManager = this.f7139a.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i10 & 1) == 0) {
            removeGroup(i7);
        }
        for (int i12 = 0; i12 < size; i12++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i12);
            int i13 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i13 < 0 ? intent : intentArr[i13]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            q a7 = a(i7, i8, i9, resolveInfo.loadLabel(packageManager));
            a7.setIcon(resolveInfo.loadIcon(packageManager));
            a7.f7172g = intent2;
            if (menuItemArr != null && (i11 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i11] = a7;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7) {
        return addSubMenu(0, 0, 0, this.f7140b.getString(i7));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i7, int i8, int i9, int i10) {
        return addSubMenu(i7, i8, i9, this.f7140b.getString(i10));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        q a7 = a(i7, i8, i9, charSequence);
        i0 i0Var = new i0(this.f7139a, this, a7);
        a7.f7180o = i0Var;
        i0Var.setHeaderTitle(a7.f7170e);
        return i0Var;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(c0 c0Var, Context context) {
        this.f7159u.add(new WeakReference(c0Var));
        c0Var.f(context, this);
        this.f7149k = true;
    }

    public final void c(boolean z6) {
        if (this.f7157s) {
            return;
        }
        this.f7157s = true;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            c0 c0Var = (c0) weakReference.get();
            if (c0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                c0Var.a(this, z6);
            }
        }
        this.f7157s = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        q qVar = this.f7160v;
        if (qVar != null) {
            d(qVar);
        }
        this.f7144f.clear();
        p(true);
    }

    public final void clearHeader() {
        this.f7152n = null;
        this.f7151m = null;
        this.f7153o = null;
        p(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(q qVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        boolean z6 = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f7160v == qVar) {
            y();
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                c0 c0Var = (c0) weakReference.get();
                if (c0Var == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    z6 = c0Var.e(qVar);
                    if (z6) {
                        break;
                    }
                }
            }
            x();
            if (z6) {
                this.f7160v = null;
            }
        }
        return z6;
    }

    public boolean e(o oVar, MenuItem menuItem) {
        m mVar = this.f7143e;
        return mVar != null && mVar.l(oVar, menuItem);
    }

    public boolean f(q qVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        boolean z6 = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        y();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            c0 c0Var = (c0) weakReference.get();
            if (c0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                z6 = c0Var.c(qVar);
                if (z6) {
                    break;
                }
            }
        }
        x();
        if (z6) {
            this.f7160v = qVar;
        }
        return z6;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i7) {
        MenuItem findItem;
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            q qVar = (q) arrayList.get(i8);
            if (qVar.f7166a == i7) {
                return qVar;
            }
            if (qVar.hasSubMenu() && (findItem = qVar.f7180o.findItem(i7)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public final q g(int i7, KeyEvent keyEvent) {
        ArrayList arrayList = this.f7158t;
        arrayList.clear();
        h(arrayList, i7, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (q) arrayList.get(0);
        }
        boolean n6 = n();
        for (int i8 = 0; i8 < size; i8++) {
            q qVar = (q) arrayList.get(i8);
            char c7 = n6 ? qVar.f7175j : qVar.f7173h;
            char[] cArr = keyData.meta;
            if ((c7 == cArr[0] && (metaState & 2) == 0) || ((c7 == cArr[2] && (metaState & 2) != 0) || (n6 && c7 == '\b' && i7 == 67))) {
                return qVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i7) {
        return (MenuItem) this.f7144f.get(i7);
    }

    public final void h(ArrayList arrayList, int i7, KeyEvent keyEvent) {
        int i8;
        boolean n6 = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i7 == 67) {
            ArrayList arrayList2 = this.f7144f;
            int size = arrayList2.size();
            for (0; i8 < size; i8 + 1) {
                q qVar = (q) arrayList2.get(i8);
                if (qVar.hasSubMenu()) {
                    qVar.f7180o.h(arrayList, i7, keyEvent);
                }
                char c7 = n6 ? qVar.f7175j : qVar.f7173h;
                if ((modifiers & k0.a.SUPPORTED_MODIFIERS_MASK) == ((n6 ? qVar.f7176k : qVar.f7174i) & k0.a.SUPPORTED_MODIFIERS_MASK) && c7 != 0) {
                    char[] cArr = keyData.meta;
                    if (c7 != cArr[0] && c7 != cArr[2]) {
                        if (n6 && c7 == '\b') {
                            i8 = i7 != 67 ? i8 + 1 : 0;
                        }
                    }
                    if (qVar.isEnabled()) {
                        arrayList.add(qVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.f7162x) {
            return true;
        }
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (((q) arrayList.get(i7)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList l7 = l();
        if (this.f7149k) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
            Iterator it = copyOnWriteArrayList.iterator();
            boolean z6 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                c0 c0Var = (c0) weakReference.get();
                if (c0Var == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    z6 |= c0Var.h();
                }
            }
            ArrayList arrayList = this.f7147i;
            ArrayList arrayList2 = this.f7148j;
            if (z6) {
                arrayList.clear();
                arrayList2.clear();
                int size = l7.size();
                for (int i7 = 0; i7 < size; i7++) {
                    q qVar = (q) l7.get(i7);
                    if (qVar.f()) {
                        arrayList.add(qVar);
                    } else {
                        arrayList2.add(qVar);
                    }
                }
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList2.addAll(l());
            }
            this.f7149k = false;
        }
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i7, KeyEvent keyEvent) {
        return g(i7, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public o k() {
        return this;
    }

    public final ArrayList l() {
        boolean z6 = this.f7146h;
        ArrayList arrayList = this.f7145g;
        if (!z6) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList arrayList2 = this.f7144f;
        int size = arrayList2.size();
        for (int i7 = 0; i7 < size; i7++) {
            q qVar = (q) arrayList2.get(i7);
            if (qVar.isVisible()) {
                arrayList.add(qVar);
            }
        }
        this.f7146h = false;
        this.f7149k = true;
        return arrayList;
    }

    public boolean m() {
        return this.f7161w;
    }

    public boolean n() {
        return this.f7141c;
    }

    public boolean o() {
        return this.f7142d;
    }

    public void p(boolean z6) {
        if (this.f7154p) {
            this.f7155q = true;
            if (z6) {
                this.f7156r = true;
                return;
            }
            return;
        }
        if (z6) {
            this.f7146h = true;
            this.f7149k = true;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        y();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            c0 c0Var = (c0) weakReference.get();
            if (c0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                c0Var.m(z6);
            }
        }
        x();
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i7, int i8) {
        return q(findItem(i7), null, i8);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i7, KeyEvent keyEvent, int i8) {
        q g7 = g(i7, keyEvent);
        boolean q6 = g7 != null ? q(g7, null, i8) : false;
        if ((i8 & 2) != 0) {
            c(true);
        }
        return q6;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(android.view.MenuItem r7, k.c0 r8, int r9) {
        /*
            r6 = this;
            k.q r7 = (k.q) r7
            r0 = 0
            if (r7 == 0) goto Ld6
            boolean r1 = r7.isEnabled()
            if (r1 != 0) goto Ld
            goto Ld6
        Ld:
            android.view.MenuItem$OnMenuItemClickListener r1 = r7.f7181p
            r2 = 1
            if (r1 == 0) goto L1a
            boolean r1 = r1.onMenuItemClick(r7)
            if (r1 == 0) goto L1a
        L18:
            r1 = r2
            goto L3d
        L1a:
            k.o r1 = r7.f7179n
            boolean r3 = r1.e(r1, r7)
            if (r3 == 0) goto L23
            goto L18
        L23:
            android.content.Intent r3 = r7.f7172g
            if (r3 == 0) goto L2d
            android.content.Context r1 = r1.f7139a     // Catch: android.content.ActivityNotFoundException -> L2d
            r1.startActivity(r3)     // Catch: android.content.ActivityNotFoundException -> L2d
            goto L18
        L2d:
            p0.d r1 = r7.A
            if (r1 == 0) goto L3c
            k.r r1 = (k.r) r1
            android.view.ActionProvider r1 = r1.f7192b
            boolean r1 = r1.onPerformDefaultAction()
            if (r1 == 0) goto L3c
            goto L18
        L3c:
            r1 = r0
        L3d:
            p0.d r3 = r7.A
            if (r3 == 0) goto L4e
            r4 = r3
            k.r r4 = (k.r) r4
            android.view.ActionProvider r4 = r4.f7192b
            boolean r4 = r4.hasSubMenu()
            if (r4 == 0) goto L4e
            r4 = r2
            goto L4f
        L4e:
            r4 = r0
        L4f:
            boolean r5 = r7.e()
            if (r5 == 0) goto L61
            boolean r7 = r7.expandActionView()
            r1 = r1 | r7
            if (r1 == 0) goto Ld5
            r6.c(r2)
            goto Ld5
        L61:
            boolean r5 = r7.hasSubMenu()
            if (r5 != 0) goto L72
            if (r4 == 0) goto L6a
            goto L72
        L6a:
            r7 = r9 & 1
            if (r7 != 0) goto Ld5
            r6.c(r2)
            goto Ld5
        L72:
            r9 = r9 & 4
            if (r9 != 0) goto L79
            r6.c(r0)
        L79:
            boolean r9 = r7.hasSubMenu()
            if (r9 != 0) goto L8d
            k.i0 r9 = new k.i0
            android.content.Context r5 = r6.f7139a
            r9.<init>(r5, r6, r7)
            r7.f7180o = r9
            java.lang.CharSequence r5 = r7.f7170e
            r9.setHeaderTitle(r5)
        L8d:
            k.i0 r7 = r7.f7180o
            if (r4 == 0) goto L9d
            k.r r3 = (k.r) r3
            k.w r9 = r3.f7193c
            r9.getClass()
            android.view.ActionProvider r9 = r3.f7192b
            r9.onPrepareSubMenu(r7)
        L9d:
            java.util.concurrent.CopyOnWriteArrayList r9 = r6.f7159u
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto La6
            goto Lcf
        La6:
            if (r8 == 0) goto Lac
            boolean r0 = r8.d(r7)
        Lac:
            java.util.Iterator r8 = r9.iterator()
        Lb0:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto Lcf
            java.lang.Object r3 = r8.next()
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3
            java.lang.Object r4 = r3.get()
            k.c0 r4 = (k.c0) r4
            if (r4 != 0) goto Lc8
            r9.remove(r3)
            goto Lb0
        Lc8:
            if (r0 != 0) goto Lb0
            boolean r0 = r4.d(r7)
            goto Lb0
        Lcf:
            r1 = r1 | r0
            if (r1 != 0) goto Ld5
            r6.c(r2)
        Ld5:
            return r1
        Ld6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: k.o.q(android.view.MenuItem, k.c0, int):boolean");
    }

    public final void r(c0 c0Var) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            c0 c0Var2 = (c0) weakReference.get();
            if (c0Var2 == null || c0Var2 == c0Var) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i7) {
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i9 >= size) {
                i9 = -1;
                break;
            } else if (((q) arrayList.get(i9)).f7167b == i7) {
                break;
            } else {
                i9++;
            }
        }
        if (i9 >= 0) {
            int size2 = arrayList.size() - i9;
            while (true) {
                int i10 = i8 + 1;
                if (i8 >= size2 || ((q) arrayList.get(i9)).f7167b != i7) {
                    break;
                }
                if (i9 >= 0) {
                    ArrayList arrayList2 = this.f7144f;
                    if (i9 < arrayList2.size()) {
                        arrayList2.remove(i9);
                    }
                }
                i8 = i10;
            }
            p(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i7) {
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                i8 = -1;
                break;
            } else if (((q) arrayList.get(i8)).f7166a == i7) {
                break;
            } else {
                i8++;
            }
        }
        if (i8 >= 0) {
            ArrayList arrayList2 = this.f7144f;
            if (i8 >= arrayList2.size()) {
                return;
            }
            arrayList2.remove(i8);
            p(true);
        }
    }

    public final void s(Bundle bundle) {
        MenuItem findItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
        int size = this.f7144f.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = getItem(i7);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((i0) item.getSubMenu()).s(bundle);
            }
        }
        int i8 = bundle.getInt("android:menu:expandedactionview");
        if (i8 <= 0 || (findItem = findItem(i8)) == null) {
            return;
        }
        findItem.expandActionView();
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i7, boolean z6, boolean z7) {
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            q qVar = (q) arrayList.get(i8);
            if (qVar.f7167b == i7) {
                qVar.g(z7);
                qVar.setCheckable(z6);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z6) {
        this.f7161w = z6;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i7, boolean z6) {
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            q qVar = (q) arrayList.get(i8);
            if (qVar.f7167b == i7) {
                qVar.setEnabled(z6);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i7, boolean z6) {
        ArrayList arrayList = this.f7144f;
        int size = arrayList.size();
        boolean z7 = false;
        for (int i8 = 0; i8 < size; i8++) {
            q qVar = (q) arrayList.get(i8);
            if (qVar.f7167b == i7) {
                int i9 = qVar.f7189x;
                int i10 = (i9 & (-9)) | (z6 ? 0 : 8);
                qVar.f7189x = i10;
                if (i9 != i10) {
                    z7 = true;
                }
            }
        }
        if (z7) {
            p(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z6) {
        this.f7141c = z6;
        p(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f7144f.size();
    }

    public final void t(Bundle bundle) {
        Parcelable parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null) {
            CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
            if (copyOnWriteArrayList.isEmpty()) {
                return;
            }
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                c0 c0Var = (c0) weakReference.get();
                if (c0Var == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    int id = c0Var.getId();
                    if (id > 0 && (parcelable = (Parcelable) sparseParcelableArray.get(id)) != null) {
                        c0Var.j(parcelable);
                    }
                }
            }
        }
    }

    public final void u(Bundle bundle) {
        int size = this.f7144f.size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = getItem(i7);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((i0) item.getSubMenu()).u(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void v(Bundle bundle) {
        Parcelable i7;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f7159u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            c0 c0Var = (c0) weakReference.get();
            if (c0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                int id = c0Var.getId();
                if (id > 0 && (i7 = c0Var.i()) != null) {
                    sparseArray.put(id, i7);
                }
            }
        }
        bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
    }

    public final void w(int i7, CharSequence charSequence, int i8, Drawable drawable, View view) {
        Resources resources = this.f7140b;
        if (view != null) {
            this.f7153o = view;
            this.f7151m = null;
            this.f7152n = null;
        } else {
            if (i7 > 0) {
                this.f7151m = resources.getText(i7);
            } else if (charSequence != null) {
                this.f7151m = charSequence;
            }
            if (i8 > 0) {
                Context context = this.f7139a;
                Object obj = g0.g.f6423a;
                this.f7152n = g0.c.b(context, i8);
            } else if (drawable != null) {
                this.f7152n = drawable;
            }
            this.f7153o = null;
        }
        p(false);
    }

    public final void x() {
        this.f7154p = false;
        if (this.f7155q) {
            this.f7155q = false;
            p(this.f7156r);
        }
    }

    public final void y() {
        if (this.f7154p) {
            return;
        }
        this.f7154p = true;
        this.f7155q = false;
        this.f7156r = false;
    }
}
