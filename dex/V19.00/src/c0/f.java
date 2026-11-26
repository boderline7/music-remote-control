package c0;

import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.navi.tool.amapflying.util.NavigationETAFloatingView;
import com.navi.tool.view.RealtimeBlurView;
import g3.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3024a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3025b;

    public /* synthetic */ f(int i7, Object obj) {
        this.f3024a = i7;
        this.f3025b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        int i7 = this.f3024a;
        Object obj = this.f3025b;
        switch (i7) {
            case 0:
                ((CoordinatorLayout) obj).t(0);
                break;
            case 1:
                q qVar = (q) obj;
                float rotation = qVar.f6515v.getRotation();
                if (qVar.f6508o != rotation) {
                    qVar.f6508o = rotation;
                    qVar.q();
                    break;
                }
                break;
            case 2:
                NavigationETAFloatingView navigationETAFloatingView = (NavigationETAFloatingView) obj;
                if (navigationETAFloatingView.I != 0) {
                    navigationETAFloatingView.f4564a.getViewTreeObserver().removeOnPreDrawListener(this);
                    navigationETAFloatingView.H = false;
                    navigationETAFloatingView.I = 0;
                    break;
                } else {
                    LinearLayout linearLayout = navigationETAFloatingView.f4565b;
                    if (linearLayout != null) {
                        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                        layoutParams.width = -2;
                        navigationETAFloatingView.f4565b.setLayoutParams(layoutParams);
                    }
                    LinearLayout linearLayout2 = navigationETAFloatingView.f4571h;
                    if (linearLayout2 != null) {
                        ViewGroup.LayoutParams layoutParams2 = linearLayout2.getLayoutParams();
                        layoutParams2.width = -2;
                        navigationETAFloatingView.f4571h.setLayoutParams(layoutParams2);
                    }
                    LinearLayout linearLayout3 = navigationETAFloatingView.f4573j;
                    if (linearLayout3 != null) {
                        ViewGroup.LayoutParams layoutParams3 = linearLayout3.getLayoutParams();
                        layoutParams3.width = -2;
                        navigationETAFloatingView.f4573j.setLayoutParams(layoutParams3);
                    }
                    LinearLayout linearLayout4 = navigationETAFloatingView.f4578o;
                    if (linearLayout4 != null) {
                        ViewGroup.LayoutParams layoutParams4 = linearLayout4.getLayoutParams();
                        layoutParams4.width = -2;
                        navigationETAFloatingView.f4578o.setLayoutParams(layoutParams4);
                    }
                    LinearLayout linearLayout5 = navigationETAFloatingView.f4579p;
                    if (linearLayout5 != null) {
                        ViewGroup.LayoutParams layoutParams5 = linearLayout5.getLayoutParams();
                        layoutParams5.width = -2;
                        navigationETAFloatingView.f4579p.setLayoutParams(layoutParams5);
                    }
                    int d7 = navigationETAFloatingView.d(16);
                    LinearLayout linearLayout6 = navigationETAFloatingView.f4565b;
                    int a7 = (linearLayout6 == null || linearLayout6.getVisibility() != 0) ? 0 : NavigationETAFloatingView.a(navigationETAFloatingView, navigationETAFloatingView.f4565b);
                    int a8 = NavigationETAFloatingView.a(navigationETAFloatingView, navigationETAFloatingView.f4571h) + d7;
                    int a9 = NavigationETAFloatingView.a(navigationETAFloatingView, navigationETAFloatingView.f4573j) + d7;
                    LinearLayout linearLayout7 = navigationETAFloatingView.f4578o;
                    int a10 = (linearLayout7 == null || linearLayout7.getVisibility() != 0) ? 0 : NavigationETAFloatingView.a(navigationETAFloatingView, navigationETAFloatingView.f4578o) + d7;
                    LinearLayout linearLayout8 = navigationETAFloatingView.f4579p;
                    int max = Math.max(a7, Math.max(a8, Math.max(a9, Math.max(a10, (linearLayout8 == null || linearLayout8.getVisibility() != 0) ? 0 : NavigationETAFloatingView.a(navigationETAFloatingView, navigationETAFloatingView.f4579p) + d7))));
                    if (max > 0) {
                        LinearLayout linearLayout9 = navigationETAFloatingView.f4565b;
                        if (linearLayout9 != null) {
                            ViewGroup.LayoutParams layoutParams6 = linearLayout9.getLayoutParams();
                            layoutParams6.width = max;
                            navigationETAFloatingView.f4565b.setLayoutParams(layoutParams6);
                        }
                        LinearLayout linearLayout10 = navigationETAFloatingView.f4571h;
                        if (linearLayout10 != null) {
                            ViewGroup.LayoutParams layoutParams7 = linearLayout10.getLayoutParams();
                            layoutParams7.width = max;
                            navigationETAFloatingView.f4571h.setLayoutParams(layoutParams7);
                        }
                        LinearLayout linearLayout11 = navigationETAFloatingView.f4573j;
                        if (linearLayout11 != null) {
                            ViewGroup.LayoutParams layoutParams8 = linearLayout11.getLayoutParams();
                            layoutParams8.width = max;
                            navigationETAFloatingView.f4573j.setLayoutParams(layoutParams8);
                        }
                        LinearLayout linearLayout12 = navigationETAFloatingView.f4578o;
                        if (linearLayout12 != null) {
                            ViewGroup.LayoutParams layoutParams9 = linearLayout12.getLayoutParams();
                            layoutParams9.width = max;
                            navigationETAFloatingView.f4578o.setLayoutParams(layoutParams9);
                        }
                        LinearLayout linearLayout13 = navigationETAFloatingView.f4579p;
                        if (linearLayout13 != null) {
                            ViewGroup.LayoutParams layoutParams10 = linearLayout13.getLayoutParams();
                            layoutParams10.width = max;
                            navigationETAFloatingView.f4579p.setLayoutParams(layoutParams10);
                        }
                    }
                    navigationETAFloatingView.I = 1;
                    navigationETAFloatingView.f4564a.requestLayout();
                    break;
                }
                break;
            default:
                RealtimeBlurView realtimeBlurView = (RealtimeBlurView) obj;
                if (realtimeBlurView.f5982e && realtimeBlurView.isShown()) {
                    realtimeBlurView.c();
                    break;
                }
                break;
        }
        return true;
    }
}
