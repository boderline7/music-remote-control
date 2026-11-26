package z2;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements ViewGroup.OnHierarchyChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public ViewGroup.OnHierarchyChangeListener f10286a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ChipGroup f10287b;

    public j(ChipGroup chipGroup) {
        this.f10287b = chipGroup;
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        ChipGroup chipGroup = this.f10287b;
        if (view == chipGroup && (view2 instanceof Chip)) {
            if (view2.getId() == -1) {
                int i7 = d1.OVER_SCROLL_ALWAYS;
                view2.setId(m0.a());
            }
            com.google.android.material.internal.a aVar = chipGroup.f3555h;
            Chip chip = (Chip) view2;
            aVar.f3791a.put(Integer.valueOf(chip.getId()), chip);
            if (chip.isChecked()) {
                aVar.a(chip);
            }
            chip.setInternalOnCheckedChangeListener(new v2.a(5, aVar));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f10286a;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        ChipGroup chipGroup = this.f10287b;
        if (view == chipGroup && (view2 instanceof Chip)) {
            com.google.android.material.internal.a aVar = chipGroup.f3555h;
            Chip chip = (Chip) view2;
            aVar.getClass();
            chip.setInternalOnCheckedChangeListener(null);
            aVar.f3791a.remove(Integer.valueOf(chip.getId()));
            aVar.f3792b.remove(Integer.valueOf(chip.getId()));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f10286a;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
