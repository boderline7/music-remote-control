package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MenuItem;
import com.google.android.material.badge.BadgeState$State;
import com.google.android.material.internal.ParcelableSparseArray;
import k.c0;
import k.i0;
import k.o;
import k.q;
import n1.u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g implements c0 {

    /* renamed from: a, reason: collision with root package name */
    public NavigationBarMenuView f4012a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f4013b;

    /* renamed from: c, reason: collision with root package name */
    public int f4014c;

    @Override // k.c0
    public final void a(o oVar, boolean z6) {
    }

    @Override // k.c0
    public final boolean c(q qVar) {
        return false;
    }

    @Override // k.c0
    public final boolean d(i0 i0Var) {
        return false;
    }

    @Override // k.c0
    public final boolean e(q qVar) {
        return false;
    }

    @Override // k.c0
    public final void f(Context context, o oVar) {
        this.f4012a.E = oVar;
    }

    @Override // k.c0
    public final int getId() {
        return this.f4014c;
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = new NavigationBarPresenter$SavedState();
        navigationBarPresenter$SavedState.f3983a = this.f4012a.getSelectedItemId();
        SparseArray<t2.a> badgeDrawables = this.f4012a.getBadgeDrawables();
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i7 = 0; i7 < badgeDrawables.size(); i7++) {
            int keyAt = badgeDrawables.keyAt(i7);
            t2.a valueAt = badgeDrawables.valueAt(i7);
            parcelableSparseArray.put(keyAt, valueAt != null ? valueAt.f9062e.f9071a : null);
        }
        navigationBarPresenter$SavedState.f3984b = parcelableSparseArray;
        return navigationBarPresenter$SavedState;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
        SparseArray sparseArray;
        if (parcelable instanceof NavigationBarPresenter$SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.f4012a;
            NavigationBarPresenter$SavedState navigationBarPresenter$SavedState = (NavigationBarPresenter$SavedState) parcelable;
            int i7 = navigationBarPresenter$SavedState.f3983a;
            int size = navigationBarMenuView.E.f7144f.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.E.getItem(i8);
                if (i7 == item.getItemId()) {
                    navigationBarMenuView.f3963g = i7;
                    navigationBarMenuView.f3964h = i8;
                    item.setChecked(true);
                    break;
                }
                i8++;
            }
            Context context = this.f4012a.getContext();
            ParcelableSparseArray parcelableSparseArray = navigationBarPresenter$SavedState.f3984b;
            SparseArray sparseArray2 = new SparseArray(parcelableSparseArray.size());
            for (int i9 = 0; i9 < parcelableSparseArray.size(); i9++) {
                int keyAt = parcelableSparseArray.keyAt(i9);
                BadgeState$State badgeState$State = (BadgeState$State) parcelableSparseArray.valueAt(i9);
                sparseArray2.put(keyAt, badgeState$State != null ? new t2.a(context, badgeState$State) : null);
            }
            NavigationBarMenuView navigationBarMenuView2 = this.f4012a;
            navigationBarMenuView2.getClass();
            int i10 = 0;
            while (true) {
                int size2 = sparseArray2.size();
                sparseArray = navigationBarMenuView2.f3975s;
                if (i10 >= size2) {
                    break;
                }
                int keyAt2 = sparseArray2.keyAt(i10);
                if (sparseArray.indexOfKey(keyAt2) < 0) {
                    sparseArray.append(keyAt2, (t2.a) sparseArray2.get(keyAt2));
                }
                i10++;
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.f3962f;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    t2.a aVar = (t2.a) sparseArray.get(navigationBarItemView.getId());
                    if (aVar != null) {
                        navigationBarItemView.setBadge(aVar);
                    }
                }
            }
        }
    }

    @Override // k.c0
    public final void m(boolean z6) {
        n1.a aVar;
        if (this.f4013b) {
            return;
        }
        if (z6) {
            this.f4012a.a();
            return;
        }
        NavigationBarMenuView navigationBarMenuView = this.f4012a;
        o oVar = navigationBarMenuView.E;
        if (oVar == null || navigationBarMenuView.f3962f == null) {
            return;
        }
        int size = oVar.f7144f.size();
        if (size != navigationBarMenuView.f3962f.length) {
            navigationBarMenuView.a();
            return;
        }
        int i7 = navigationBarMenuView.f3963g;
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = navigationBarMenuView.E.getItem(i8);
            if (item.isChecked()) {
                navigationBarMenuView.f3963g = item.getItemId();
                navigationBarMenuView.f3964h = i8;
            }
        }
        if (i7 != navigationBarMenuView.f3963g && (aVar = navigationBarMenuView.f3957a) != null) {
            u.a(navigationBarMenuView, aVar);
        }
        boolean f7 = NavigationBarMenuView.f(navigationBarMenuView.f3961e, navigationBarMenuView.E.l().size());
        for (int i9 = 0; i9 < size; i9++) {
            navigationBarMenuView.D.f4013b = true;
            navigationBarMenuView.f3962f[i9].setLabelVisibilityMode(navigationBarMenuView.f3961e);
            navigationBarMenuView.f3962f[i9].setShifting(f7);
            navigationBarMenuView.f3962f[i9].c((q) navigationBarMenuView.E.getItem(i9));
            navigationBarMenuView.D.f4013b = false;
        }
    }
}
