package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.s1;
import androidx.recyclerview.widget.t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g implements t1 {
    @Override // androidx.recyclerview.widget.t1
    public final void b(View view) {
        s1 s1Var = (s1) view.getLayoutParams();
        if (((ViewGroup.MarginLayoutParams) s1Var).width != -1 || ((ViewGroup.MarginLayoutParams) s1Var).height != -1) {
            throw new IllegalStateException("Pages must fill the whole ViewPager2 (use match_parent)");
        }
    }

    @Override // androidx.recyclerview.widget.t1
    public final void d(View view) {
    }
}
