package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v implements k.c0 {
    public static final int NO_TEXT_APPEARANCE_SET = 0;
    public int A;

    /* renamed from: a, reason: collision with root package name */
    public NavigationMenuView f3881a;

    /* renamed from: b, reason: collision with root package name */
    public LinearLayout f3882b;

    /* renamed from: c, reason: collision with root package name */
    public k.o f3883c;

    /* renamed from: d, reason: collision with root package name */
    public int f3884d;

    /* renamed from: e, reason: collision with root package name */
    public n f3885e;

    /* renamed from: f, reason: collision with root package name */
    public LayoutInflater f3886f;

    /* renamed from: h, reason: collision with root package name */
    public ColorStateList f3888h;

    /* renamed from: k, reason: collision with root package name */
    public ColorStateList f3891k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f3892l;

    /* renamed from: m, reason: collision with root package name */
    public Drawable f3893m;

    /* renamed from: n, reason: collision with root package name */
    public RippleDrawable f3894n;

    /* renamed from: o, reason: collision with root package name */
    public int f3895o;

    /* renamed from: p, reason: collision with root package name */
    public int f3896p;

    /* renamed from: q, reason: collision with root package name */
    public int f3897q;

    /* renamed from: r, reason: collision with root package name */
    public int f3898r;

    /* renamed from: s, reason: collision with root package name */
    public int f3899s;

    /* renamed from: t, reason: collision with root package name */
    public int f3900t;

    /* renamed from: u, reason: collision with root package name */
    public int f3901u;

    /* renamed from: v, reason: collision with root package name */
    public int f3902v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f3903w;

    /* renamed from: y, reason: collision with root package name */
    public int f3905y;

    /* renamed from: z, reason: collision with root package name */
    public int f3906z;

    /* renamed from: g, reason: collision with root package name */
    public int f3887g = 0;

    /* renamed from: i, reason: collision with root package name */
    public int f3889i = 0;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3890j = true;

    /* renamed from: x, reason: collision with root package name */
    public boolean f3904x = true;
    public int B = -1;
    public final androidx.appcompat.app.c C = new androidx.appcompat.app.c(5, this);

    @Override // k.c0
    public final void a(k.o oVar, boolean z6) {
    }

    @Override // k.c0
    public final boolean c(k.q qVar) {
        return false;
    }

    @Override // k.c0
    public final boolean d(k.i0 i0Var) {
        return false;
    }

    @Override // k.c0
    public final boolean e(k.q qVar) {
        return false;
    }

    @Override // k.c0
    public final void f(Context context, k.o oVar) {
        this.f3886f = LayoutInflater.from(context);
        this.f3883c = oVar;
        this.A = context.getResources().getDimensionPixelOffset(q2.e.design_navigation_separator_vertical_padding);
    }

    @Override // k.c0
    public final int getId() {
        return this.f3884d;
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        Bundle bundle = new Bundle();
        if (this.f3881a != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.f3881a.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(k.k.VIEWS_TAG, sparseArray);
        }
        n nVar = this.f3885e;
        if (nVar != null) {
            nVar.getClass();
            Bundle bundle2 = new Bundle();
            k.q qVar = nVar.f3873b;
            if (qVar != null) {
                bundle2.putInt("android:menu:checked", qVar.f7166a);
            }
            SparseArray<? extends Parcelable> sparseArray2 = new SparseArray<>();
            ArrayList arrayList = nVar.f3872a;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                p pVar = (p) arrayList.get(i7);
                if (pVar instanceof r) {
                    k.q qVar2 = ((r) pVar).f3878a;
                    View actionView = qVar2 != null ? qVar2.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray2.put(qVar2.f7166a, parcelableSparseArray);
                    }
                }
            }
            bundle2.putSparseParcelableArray("android:menu:action_views", sparseArray2);
            bundle.putBundle("android:menu:adapter", bundle2);
        }
        if (this.f3882b != null) {
            SparseArray<Parcelable> sparseArray3 = new SparseArray<>();
            this.f3882b.saveHierarchyState(sparseArray3);
            bundle.putSparseParcelableArray("android:menu:header", sparseArray3);
        }
        return bundle;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
        k.q qVar;
        View actionView;
        ParcelableSparseArray parcelableSparseArray;
        k.q qVar2;
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(k.k.VIEWS_TAG);
            if (sparseParcelableArray != null) {
                this.f3881a.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle("android:menu:adapter");
            if (bundle2 != null) {
                n nVar = this.f3885e;
                nVar.getClass();
                int i7 = bundle2.getInt("android:menu:checked", 0);
                ArrayList arrayList = nVar.f3872a;
                if (i7 != 0) {
                    nVar.f3874c = true;
                    int size = arrayList.size();
                    int i8 = 0;
                    while (true) {
                        if (i8 >= size) {
                            break;
                        }
                        p pVar = (p) arrayList.get(i8);
                        if ((pVar instanceof r) && (qVar2 = ((r) pVar).f3878a) != null && qVar2.f7166a == i7) {
                            nVar.b(qVar2);
                            break;
                        }
                        i8++;
                    }
                    nVar.f3874c = false;
                    nVar.a();
                }
                SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
                if (sparseParcelableArray2 != null) {
                    int size2 = arrayList.size();
                    for (int i9 = 0; i9 < size2; i9++) {
                        p pVar2 = (p) arrayList.get(i9);
                        if ((pVar2 instanceof r) && (qVar = ((r) pVar2).f3878a) != null && (actionView = qVar.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray2.get(qVar.f7166a)) != null) {
                            actionView.restoreHierarchyState(parcelableSparseArray);
                        }
                    }
                }
            }
            SparseArray<Parcelable> sparseParcelableArray3 = bundle.getSparseParcelableArray("android:menu:header");
            if (sparseParcelableArray3 != null) {
                this.f3882b.restoreHierarchyState(sparseParcelableArray3);
            }
        }
    }

    @Override // k.c0
    public final void m(boolean z6) {
        n nVar = this.f3885e;
        if (nVar != null) {
            nVar.a();
            nVar.notifyDataSetChanged();
        }
    }
}
