package androidx.viewpager2.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.z1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i extends LinearLayoutManager {
    public final /* synthetic */ ViewPager2 E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(ViewPager2 viewPager2) {
        super(1);
        this.E = viewPager2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void F0(g2 g2Var, int[] iArr) {
        ViewPager2 viewPager2 = this.E;
        int offscreenPageLimit = viewPager2.getOffscreenPageLimit();
        if (offscreenPageLimit == -1) {
            super.F0(g2Var, iArr);
            return;
        }
        int pageSize = viewPager2.getPageSize() * offscreenPageLimit;
        iArr[0] = pageSize;
        iArr[1] = pageSize;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void X(z1 z1Var, g2 g2Var, q0.i iVar) {
        super.X(z1Var, g2Var, iVar);
        this.E.f2887t.getClass();
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean k0(z1 z1Var, g2 g2Var, int i7, Bundle bundle) {
        this.E.f2887t.getClass();
        return super.k0(z1Var, g2Var, i7, bundle);
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean p0(RecyclerView recyclerView, View view, Rect rect, boolean z6, boolean z7) {
        return false;
    }
}
