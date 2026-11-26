package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import j.h;
import v2.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {

    /* renamed from: i, reason: collision with root package name */
    public final a f4192i = new a(this);

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, c0.b
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        a aVar = this.f4192i;
        aVar.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (h.f6889e == null) {
                    h.f6889e = new h(9);
                }
                h hVar = h.f6889e;
                a.a.u(aVar.f9388b);
                synchronized (hVar.f6890a) {
                    a.a.u(hVar.f6892c);
                }
            }
        } else if (coordinatorLayout.s(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (h.f6889e == null) {
                h.f6889e = new h(9);
            }
            h hVar2 = h.f6889e;
            a.a.u(aVar.f9388b);
            synchronized (hVar2.f6890a) {
                a.a.u(hVar2.f6892c);
            }
        }
        return super.k(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean w(View view) {
        this.f4192i.getClass();
        return view instanceof BaseTransientBottomBar$SnackbarBaseLayout;
    }
}
