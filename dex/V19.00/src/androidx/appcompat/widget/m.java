package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m implements k.c0 {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1323a;

    /* renamed from: b, reason: collision with root package name */
    public Context f1324b;

    /* renamed from: c, reason: collision with root package name */
    public k.o f1325c;

    /* renamed from: d, reason: collision with root package name */
    public final LayoutInflater f1326d;

    /* renamed from: e, reason: collision with root package name */
    public k.b0 f1327e;

    /* renamed from: f, reason: collision with root package name */
    public final int f1328f;

    /* renamed from: g, reason: collision with root package name */
    public final int f1329g;

    /* renamed from: h, reason: collision with root package name */
    public k.e0 f1330h;

    /* renamed from: i, reason: collision with root package name */
    public int f1331i;

    /* renamed from: j, reason: collision with root package name */
    public k f1332j;

    /* renamed from: k, reason: collision with root package name */
    public Drawable f1333k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f1334l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1335m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1336n;

    /* renamed from: o, reason: collision with root package name */
    public int f1337o;

    /* renamed from: p, reason: collision with root package name */
    public int f1338p;

    /* renamed from: q, reason: collision with root package name */
    public int f1339q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1340r;

    /* renamed from: s, reason: collision with root package name */
    public final SparseBooleanArray f1341s;

    /* renamed from: t, reason: collision with root package name */
    public h f1342t;

    /* renamed from: u, reason: collision with root package name */
    public h f1343u;

    /* renamed from: v, reason: collision with root package name */
    public android.support.v4.media.i f1344v;

    /* renamed from: w, reason: collision with root package name */
    public i f1345w;

    /* renamed from: x, reason: collision with root package name */
    public final e.a f1346x;

    /* renamed from: y, reason: collision with root package name */
    public int f1347y;

    public m(Context context) {
        int i7 = f.g.abc_action_menu_layout;
        int i8 = f.g.abc_action_menu_item_layout;
        this.f1323a = context;
        this.f1326d = LayoutInflater.from(context);
        this.f1328f = i7;
        this.f1329g = i8;
        this.f1341s = new SparseBooleanArray();
        this.f1346x = new e.a(5, this);
    }

    @Override // k.c0
    public final void a(k.o oVar, boolean z6) {
        g();
        h hVar = this.f1343u;
        if (hVar != null && hVar.b()) {
            hVar.f7055j.dismiss();
        }
        k.b0 b0Var = this.f1327e;
        if (b0Var != null) {
            b0Var.a(oVar, z6);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r5v4, types: [k.d0] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    public final View b(k.q qVar, View view, ViewGroup viewGroup) {
        View actionView = qVar.getActionView();
        if (actionView == null || qVar.e()) {
            ActionMenuItemView actionMenuItemView = view instanceof k.d0 ? (k.d0) view : (k.d0) this.f1326d.inflate(this.f1329g, viewGroup, false);
            actionMenuItemView.c(qVar);
            ActionMenuItemView actionMenuItemView2 = actionMenuItemView;
            actionMenuItemView2.setItemInvoker((ActionMenuView) this.f1330h);
            if (this.f1345w == null) {
                this.f1345w = new i(this);
            }
            actionMenuItemView2.setPopupCallback(this.f1345w);
            actionView = actionMenuItemView;
        }
        actionView.setVisibility(qVar.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!((ActionMenuView) viewGroup).checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.m(layoutParams));
        }
        return actionView;
    }

    @Override // k.c0
    public final /* bridge */ /* synthetic */ boolean c(k.q qVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // k.c0
    public final boolean d(k.i0 i0Var) {
        boolean z6;
        if (!i0Var.hasVisibleItems()) {
            return false;
        }
        k.i0 i0Var2 = i0Var;
        while (true) {
            k.o oVar = i0Var2.f7122z;
            if (oVar == this.f1325c) {
                break;
            }
            i0Var2 = (k.i0) oVar;
        }
        ViewGroup viewGroup = (ViewGroup) this.f1330h;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i7);
                if ((childAt instanceof k.d0) && ((k.d0) childAt).getItemData() == i0Var2.A) {
                    view = childAt;
                    break;
                }
                i7++;
            }
        }
        if (view == null) {
            return false;
        }
        this.f1347y = i0Var.A.f7166a;
        int size = i0Var.f7144f.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                z6 = false;
                break;
            }
            MenuItem item = i0Var.getItem(i8);
            if (item.isVisible() && item.getIcon() != null) {
                z6 = true;
                break;
            }
            i8++;
        }
        h hVar = new h(this, this.f1324b, i0Var, view);
        this.f1343u = hVar;
        hVar.f7053h = z6;
        k.x xVar = hVar.f7055j;
        if (xVar != null) {
            xVar.q(z6);
        }
        h hVar2 = this.f1343u;
        if (!hVar2.b()) {
            if (hVar2.f7051f == null) {
                throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
            }
            hVar2.d(0, 0, false, false);
        }
        k.b0 b0Var = this.f1327e;
        if (b0Var != null) {
            b0Var.c(i0Var);
        }
        return true;
    }

    @Override // k.c0
    public final /* bridge */ /* synthetic */ boolean e(k.q qVar) {
        return false;
    }

    @Override // k.c0
    public final void f(Context context, k.o oVar) {
        this.f1324b = context;
        LayoutInflater.from(context);
        this.f1325c = oVar;
        Resources resources = context.getResources();
        j.a b7 = j.a.b(context);
        if (!this.f1336n) {
            this.f1335m = true;
        }
        this.f1337o = b7.f6874a.getResources().getDisplayMetrics().widthPixels / 2;
        this.f1339q = b7.c();
        int i7 = this.f1337o;
        if (this.f1335m) {
            if (this.f1332j == null) {
                k kVar = new k(this, this.f1323a);
                this.f1332j = kVar;
                if (this.f1334l) {
                    kVar.setImageDrawable(this.f1333k);
                    this.f1333k = null;
                    this.f1334l = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1332j.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i7 -= this.f1332j.getMeasuredWidth();
        } else {
            this.f1332j = null;
        }
        this.f1338p = i7;
        float f7 = resources.getDisplayMetrics().density;
    }

    public final boolean g() {
        Object obj;
        android.support.v4.media.i iVar = this.f1344v;
        if (iVar != null && (obj = this.f1330h) != null) {
            ((View) obj).removeCallbacks(iVar);
            this.f1344v = null;
            return true;
        }
        h hVar = this.f1342t;
        if (hVar == null) {
            return false;
        }
        if (hVar.b()) {
            hVar.f7055j.dismiss();
        }
        return true;
    }

    @Override // k.c0
    public final int getId() {
        return this.f1331i;
    }

    @Override // k.c0
    public final boolean h() {
        int i7;
        ArrayList arrayList;
        int i8;
        boolean z6;
        k.o oVar = this.f1325c;
        if (oVar != null) {
            arrayList = oVar.l();
            i7 = arrayList.size();
        } else {
            i7 = 0;
            arrayList = null;
        }
        int i9 = this.f1339q;
        int i10 = this.f1338p;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f1330h;
        int i11 = 0;
        boolean z7 = false;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            i8 = 2;
            z6 = true;
            if (i11 >= i7) {
                break;
            }
            k.q qVar = (k.q) arrayList.get(i11);
            int i14 = qVar.f7190y;
            if ((i14 & 2) == 2) {
                i12++;
            } else if ((i14 & 1) == 1) {
                i13++;
            } else {
                z7 = true;
            }
            if (this.f1340r && qVar.C) {
                i9 = 0;
            }
            i11++;
        }
        if (this.f1335m && (z7 || i13 + i12 > i9)) {
            i9--;
        }
        int i15 = i9 - i12;
        SparseBooleanArray sparseBooleanArray = this.f1341s;
        sparseBooleanArray.clear();
        int i16 = 0;
        int i17 = 0;
        while (i16 < i7) {
            k.q qVar2 = (k.q) arrayList.get(i16);
            int i18 = qVar2.f7190y;
            boolean z8 = (i18 & 2) == i8 ? z6 : false;
            int i19 = qVar2.f7167b;
            if (z8) {
                View b7 = b(qVar2, null, viewGroup);
                b7.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = b7.getMeasuredWidth();
                i10 -= measuredWidth;
                if (i17 == 0) {
                    i17 = measuredWidth;
                }
                if (i19 != 0) {
                    sparseBooleanArray.put(i19, z6);
                }
                qVar2.h(z6);
            } else if ((i18 & 1) == z6) {
                boolean z9 = sparseBooleanArray.get(i19);
                boolean z10 = ((i15 > 0 || z9) && i10 > 0) ? z6 : false;
                if (z10) {
                    View b8 = b(qVar2, null, viewGroup);
                    b8.measure(makeMeasureSpec, makeMeasureSpec);
                    int measuredWidth2 = b8.getMeasuredWidth();
                    i10 -= measuredWidth2;
                    if (i17 == 0) {
                        i17 = measuredWidth2;
                    }
                    z10 &= i10 + i17 > 0;
                }
                if (z10 && i19 != 0) {
                    sparseBooleanArray.put(i19, true);
                } else if (z9) {
                    sparseBooleanArray.put(i19, false);
                    for (int i20 = 0; i20 < i16; i20++) {
                        k.q qVar3 = (k.q) arrayList.get(i20);
                        if (qVar3.f7167b == i19) {
                            if (qVar3.f()) {
                                i15++;
                            }
                            qVar3.h(false);
                        }
                    }
                }
                if (z10) {
                    i15--;
                }
                qVar2.h(z10);
            } else {
                qVar2.h(false);
                i16++;
                i8 = 2;
                z6 = true;
            }
            i16++;
            i8 = 2;
            z6 = true;
        }
        return z6;
    }

    @Override // k.c0
    public final Parcelable i() {
        ActionMenuPresenter$SavedState actionMenuPresenter$SavedState = new ActionMenuPresenter$SavedState();
        actionMenuPresenter$SavedState.f980a = this.f1347y;
        return actionMenuPresenter$SavedState;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
        int i7;
        MenuItem findItem;
        if ((parcelable instanceof ActionMenuPresenter$SavedState) && (i7 = ((ActionMenuPresenter$SavedState) parcelable).f980a) > 0 && (findItem = this.f1325c.findItem(i7)) != null) {
            d((k.i0) findItem.getSubMenu());
        }
    }

    @Override // k.c0
    public final void k(k.b0 b0Var) {
        this.f1327e = b0Var;
    }

    public final boolean l() {
        h hVar = this.f1342t;
        return hVar != null && hVar.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // k.c0
    public final void m(boolean z6) {
        int size;
        int i7;
        ViewGroup viewGroup = (ViewGroup) this.f1330h;
        ArrayList arrayList = null;
        if (viewGroup != null) {
            k.o oVar = this.f1325c;
            if (oVar != null) {
                oVar.i();
                ArrayList l7 = this.f1325c.l();
                int size2 = l7.size();
                i7 = 0;
                for (int i8 = 0; i8 < size2; i8++) {
                    k.q qVar = (k.q) l7.get(i8);
                    if (qVar.f()) {
                        View childAt = viewGroup.getChildAt(i7);
                        k.q itemData = childAt instanceof k.d0 ? ((k.d0) childAt).getItemData() : null;
                        View b7 = b(qVar, childAt, viewGroup);
                        if (qVar != itemData) {
                            b7.setPressed(false);
                            b7.jumpDrawablesToCurrentState();
                        }
                        if (b7 != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) b7.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(b7);
                            }
                            ((ViewGroup) this.f1330h).addView(b7, i7);
                        }
                        i7++;
                    }
                }
            } else {
                i7 = 0;
            }
            while (i7 < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i7) == this.f1332j) {
                    i7++;
                } else {
                    viewGroup.removeViewAt(i7);
                }
            }
        }
        ((View) this.f1330h).requestLayout();
        k.o oVar2 = this.f1325c;
        if (oVar2 != null) {
            oVar2.i();
            ArrayList arrayList2 = oVar2.f7147i;
            int size3 = arrayList2.size();
            for (int i9 = 0; i9 < size3; i9++) {
                p0.d dVar = ((k.q) arrayList2.get(i9)).A;
                if (dVar != null) {
                    dVar.f8109a = this;
                }
            }
        }
        k.o oVar3 = this.f1325c;
        if (oVar3 != null) {
            oVar3.i();
            arrayList = oVar3.f7148j;
        }
        if (!this.f1335m || arrayList == null || ((size = arrayList.size()) != 1 ? size <= 0 : !(!((k.q) arrayList.get(0)).C))) {
            k kVar = this.f1332j;
            if (kVar != null) {
                Object parent = kVar.getParent();
                Object obj = this.f1330h;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f1332j);
                }
            }
        } else {
            if (this.f1332j == null) {
                this.f1332j = new k(this, this.f1323a);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.f1332j.getParent();
            if (viewGroup3 != this.f1330h) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.f1332j);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f1330h;
                k kVar2 = this.f1332j;
                actionMenuView.getClass();
                p l8 = ActionMenuView.l();
                l8.f1370a = true;
                actionMenuView.addView(kVar2, l8);
            }
        }
        ((ActionMenuView) this.f1330h).setOverflowReserved(this.f1335m);
    }

    public final boolean n() {
        k.o oVar;
        if (!this.f1335m || l() || (oVar = this.f1325c) == null || this.f1330h == null || this.f1344v != null) {
            return false;
        }
        oVar.i();
        if (oVar.f7148j.isEmpty()) {
            return false;
        }
        android.support.v4.media.i iVar = new android.support.v4.media.i(1, this, new h(this, this.f1324b, this.f1325c, this.f1332j));
        this.f1344v = iVar;
        ((View) this.f1330h).post(iVar);
        return true;
    }
}
