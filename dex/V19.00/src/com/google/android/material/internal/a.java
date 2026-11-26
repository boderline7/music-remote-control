package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f3791a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public final HashSet f3792b = new HashSet();

    /* renamed from: c, reason: collision with root package name */
    public v2.a f3793c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f3794d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f3795e;

    public final boolean a(i iVar) {
        int id = iVar.getId();
        HashSet hashSet = this.f3792b;
        if (hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        i iVar2 = (i) this.f3791a.get(Integer.valueOf(c()));
        if (iVar2 != null) {
            e(iVar2, false);
        }
        boolean add = hashSet.add(Integer.valueOf(id));
        if (!iVar.isChecked()) {
            iVar.setChecked(true);
        }
        return add;
    }

    public final ArrayList b(ViewGroup viewGroup) {
        HashSet hashSet = new HashSet(this.f3792b);
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if ((childAt instanceof i) && hashSet.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    public final int c() {
        if (this.f3794d) {
            HashSet hashSet = this.f3792b;
            if (!hashSet.isEmpty()) {
                return ((Integer) hashSet.iterator().next()).intValue();
            }
        }
        return -1;
    }

    public final void d() {
        v2.a aVar = this.f3793c;
        if (aVar != null) {
            new HashSet(this.f3792b);
            ChipGroup chipGroup = (ChipGroup) aVar.f9388b;
            z2.i iVar = chipGroup.f3554g;
            if (iVar != null) {
                chipGroup.f3555h.b(chipGroup);
                i6.i iVar2 = (i6.i) iVar;
                if (((ChipGroup) iVar2.f6849c).f3555h.f3794d) {
                    a.a.u(iVar2.f6848b);
                    ((ChipGroup) iVar2.f6849c).getCheckedChipId();
                    throw null;
                }
            }
        }
    }

    public final boolean e(i iVar, boolean z6) {
        int id = iVar.getId();
        HashSet hashSet = this.f3792b;
        if (!hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        if (z6 && hashSet.size() == 1 && hashSet.contains(Integer.valueOf(id))) {
            iVar.setChecked(true);
            return false;
        }
        boolean remove = hashSet.remove(Integer.valueOf(id));
        if (iVar.isChecked()) {
            iVar.setChecked(false);
        }
        return remove;
    }
}
