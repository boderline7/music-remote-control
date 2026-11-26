package com.google.android.material.internal;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import java.util.ArrayList;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n extends h1 {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f3872a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public k.q f3873b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3874c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ v f3875d;

    public n(v vVar) {
        this.f3875d = vVar;
        a();
    }

    public final void a() {
        boolean z6;
        if (this.f3874c) {
            return;
        }
        this.f3874c = true;
        ArrayList arrayList = this.f3872a;
        arrayList.clear();
        arrayList.add(new o());
        v vVar = this.f3875d;
        int size = vVar.f3883c.l().size();
        boolean z7 = false;
        int i7 = -1;
        int i8 = 0;
        boolean z8 = false;
        int i9 = 0;
        while (i8 < size) {
            k.q qVar = (k.q) vVar.f3883c.l().get(i8);
            if (qVar.isChecked()) {
                b(qVar);
            }
            if (qVar.isCheckable()) {
                qVar.g(z7);
            }
            if (qVar.hasSubMenu()) {
                k.i0 i0Var = qVar.f7180o;
                if (i0Var.hasVisibleItems()) {
                    if (i8 != 0) {
                        arrayList.add(new q(vVar.A, z7 ? 1 : 0));
                    }
                    arrayList.add(new r(qVar));
                    int size2 = i0Var.f7144f.size();
                    int i10 = z7 ? 1 : 0;
                    int i11 = i10;
                    while (i10 < size2) {
                        k.q qVar2 = (k.q) i0Var.getItem(i10);
                        if (qVar2.isVisible()) {
                            if (i11 == 0 && qVar2.getIcon() != null) {
                                i11 = 1;
                            }
                            if (qVar2.isCheckable()) {
                                qVar2.g(z7);
                            }
                            if (qVar.isChecked()) {
                                b(qVar);
                            }
                            arrayList.add(new r(qVar2));
                        }
                        i10++;
                        z7 = false;
                    }
                    if (i11 != 0) {
                        int size3 = arrayList.size();
                        for (int size4 = arrayList.size(); size4 < size3; size4++) {
                            ((r) arrayList.get(size4)).f3879b = true;
                        }
                    }
                }
                z6 = true;
            } else {
                int i12 = qVar.f7167b;
                if (i12 != i7) {
                    i9 = arrayList.size();
                    z8 = qVar.getIcon() != null;
                    if (i8 != 0) {
                        i9++;
                        int i13 = vVar.A;
                        arrayList.add(new q(i13, i13));
                    }
                } else if (!z8 && qVar.getIcon() != null) {
                    int size5 = arrayList.size();
                    for (int i14 = i9; i14 < size5; i14++) {
                        ((r) arrayList.get(i14)).f3879b = true;
                    }
                    z6 = true;
                    z8 = true;
                    r rVar = new r(qVar);
                    rVar.f3879b = z8;
                    arrayList.add(rVar);
                    i7 = i12;
                }
                z6 = true;
                r rVar2 = new r(qVar);
                rVar2.f3879b = z8;
                arrayList.add(rVar2);
                i7 = i12;
            }
            i8++;
            z7 = false;
        }
        this.f3874c = z7 ? 1 : 0;
    }

    public final void b(k.q qVar) {
        if (this.f3873b == qVar || !qVar.isCheckable()) {
            return;
        }
        k.q qVar2 = this.f3873b;
        if (qVar2 != null) {
            qVar2.setChecked(false);
        }
        this.f3873b = qVar;
        qVar.setChecked(true);
    }

    @Override // androidx.recyclerview.widget.h1
    public final int getItemCount() {
        return this.f3872a.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public final long getItemId(int i7) {
        return i7;
    }

    @Override // androidx.recyclerview.widget.h1
    public final int getItemViewType(int i7) {
        p pVar = (p) this.f3872a.get(i7);
        if (pVar instanceof q) {
            return 2;
        }
        if (pVar instanceof o) {
            return 3;
        }
        if (pVar instanceof r) {
            return ((r) pVar).f3878a.hasSubMenu() ? 1 : 0;
        }
        throw new RuntimeException("Unknown item type.");
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onBindViewHolder(j2 j2Var, int i7) {
        u uVar = (u) j2Var;
        int itemViewType = getItemViewType(i7);
        ArrayList arrayList = this.f3872a;
        v vVar = this.f3875d;
        if (itemViewType != 0) {
            if (itemViewType != 1) {
                if (itemViewType != 2) {
                    return;
                }
                q qVar = (q) arrayList.get(i7);
                uVar.itemView.setPadding(vVar.f3899s, qVar.f3876a, vVar.f3900t, qVar.f3877b);
                return;
            }
            TextView textView = (TextView) uVar.itemView;
            textView.setText(((r) arrayList.get(i7)).f3878a.f7170e);
            textView.setTextAppearance(vVar.f3887g);
            textView.setPadding(vVar.f3901u, textView.getPaddingTop(), vVar.f3902v, textView.getPaddingBottom());
            ColorStateList colorStateList = vVar.f3888h;
            if (colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
            d1.o(textView, new m(this, i7, true));
            return;
        }
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) uVar.itemView;
        navigationMenuItemView.setIconTintList(vVar.f3892l);
        navigationMenuItemView.setTextAppearance(vVar.f3889i);
        ColorStateList colorStateList2 = vVar.f3891k;
        if (colorStateList2 != null) {
            navigationMenuItemView.setTextColor(colorStateList2);
        }
        Drawable drawable = vVar.f3893m;
        Drawable newDrawable = drawable != null ? drawable.getConstantState().newDrawable() : null;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        p0.l0.q(navigationMenuItemView, newDrawable);
        RippleDrawable rippleDrawable = vVar.f3894n;
        if (rippleDrawable != null) {
            navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
        }
        r rVar = (r) arrayList.get(i7);
        navigationMenuItemView.setNeedsEmptyIcon(rVar.f3879b);
        int i9 = vVar.f3895o;
        int i10 = vVar.f3896p;
        navigationMenuItemView.setPadding(i9, i10, i9, i10);
        navigationMenuItemView.setIconPadding(vVar.f3897q);
        if (vVar.f3903w) {
            navigationMenuItemView.setIconSize(vVar.f3898r);
        }
        navigationMenuItemView.setMaxLines(vVar.f3905y);
        navigationMenuItemView.f3780y = vVar.f3890j;
        navigationMenuItemView.c(rVar.f3878a);
        d1.o(navigationMenuItemView, new m(this, i7, false));
    }

    @Override // androidx.recyclerview.widget.h1
    public final j2 onCreateViewHolder(ViewGroup viewGroup, int i7) {
        j2 tVar;
        v vVar = this.f3875d;
        if (i7 == 0) {
            tVar = new t(vVar.f3886f.inflate(q2.i.design_navigation_item, viewGroup, false));
            tVar.itemView.setOnClickListener(vVar.C);
        } else if (i7 == 1) {
            tVar = new l(2, vVar.f3886f, viewGroup);
        } else {
            if (i7 != 2) {
                if (i7 != 3) {
                    return null;
                }
                return new l(vVar.f3882b);
            }
            tVar = new l(1, vVar.f3886f, viewGroup);
        }
        return tVar;
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onViewRecycled(j2 j2Var) {
        u uVar = (u) j2Var;
        if (uVar instanceof t) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) uVar.itemView;
            FrameLayout frameLayout = navigationMenuItemView.A;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            navigationMenuItemView.f3781z.setCompoundDrawables(null, null, null, null);
        }
    }
}
