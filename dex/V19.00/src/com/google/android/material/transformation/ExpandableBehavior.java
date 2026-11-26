package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c0.b;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import f3.a;
import java.util.ArrayList;
import p0.d1;
import p0.o0;

@Deprecated
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class ExpandableBehavior extends b {

    /* renamed from: a, reason: collision with root package name */
    public int f4469a = 0;

    public ExpandableBehavior() {
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
    }

    @Override // c0.b
    public abstract boolean f(View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // c0.b
    public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        Object obj = (a) view2;
        boolean z6 = ((FloatingActionButton) obj).f3736o.f7560a;
        if (z6) {
            int i7 = this.f4469a;
            if (i7 != 0 && i7 != 2) {
                return false;
            }
        } else if (this.f4469a != 1) {
            return false;
        }
        this.f4469a = z6 ? 1 : 2;
        w((View) obj, view, z6, true);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // c0.b
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        a aVar;
        int i8;
        int i9 = d1.OVER_SCROLL_ALWAYS;
        if (!o0.c(view)) {
            ArrayList o6 = coordinatorLayout.o(view);
            int size = o6.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    aVar = null;
                    break;
                }
                View view2 = (View) o6.get(i10);
                if (f(view, view2)) {
                    aVar = (a) view2;
                    break;
                }
                i10++;
            }
            if (aVar != null) {
                boolean z6 = ((FloatingActionButton) aVar).f3736o.f7560a;
                if (!z6 ? this.f4469a == 1 : !((i8 = this.f4469a) != 0 && i8 != 2)) {
                    int i11 = z6 ? 1 : 2;
                    this.f4469a = i11;
                    view.getViewTreeObserver().addOnPreDrawListener(new v3.a(this, view, i11, aVar));
                }
            }
        }
        return false;
    }

    public abstract void w(View view, View view2, boolean z6, boolean z7);
}
