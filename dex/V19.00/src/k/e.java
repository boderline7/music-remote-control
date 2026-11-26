package k;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.j2;
import androidx.appcompat.widget.n0;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;
import com.navi.tool.amapflying.util.NavigationETAFloatingView;
import java.util.ArrayList;
import java.util.Iterator;
import p0.d1;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7063a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7064b;

    public /* synthetic */ e(int i7, Object obj) {
        this.f7063a = i7;
        this.f7064b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        androidx.appcompat.widget.m mVar;
        b0 b0Var;
        Activity activity;
        Rect rect;
        WindowMetrics currentWindowMetrics;
        int i7 = this.f7063a;
        Object obj = this.f7064b;
        switch (i7) {
            case 0:
                i iVar = (i) obj;
                if (iVar.b()) {
                    ArrayList arrayList = iVar.f7104i;
                    if (arrayList.size() > 0 && !((h) arrayList.get(0)).f7073a.f1236y) {
                        View view = iVar.f7111p;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((h) it.next()).f7073a.g();
                            }
                            break;
                        } else {
                            iVar.dismiss();
                            break;
                        }
                    }
                }
                break;
            case 1:
                h0 h0Var = (h0) obj;
                if (h0Var.b()) {
                    j2 j2Var = h0Var.f7084i;
                    if (!j2Var.f1236y) {
                        View view2 = h0Var.f7089n;
                        if (view2 != null && view2.isShown()) {
                            j2Var.g();
                            break;
                        } else {
                            h0Var.dismiss();
                            break;
                        }
                    }
                }
                break;
            case 2:
                ActivityChooserView activityChooserView = (ActivityChooserView) obj;
                if (activityChooserView.b()) {
                    if (!activityChooserView.isShown()) {
                        activityChooserView.getListPopupWindow().dismiss();
                        break;
                    } else {
                        activityChooserView.getListPopupWindow().g();
                        p0.d dVar = activityChooserView.f998g;
                        if (dVar != null && (mVar = dVar.f8109a) != null && (b0Var = mVar.f1327e) != null) {
                            b0Var.c(mVar.f1325c);
                            break;
                        }
                    }
                }
                break;
            case 3:
                AppCompatSpinner appCompatSpinner = (AppCompatSpinner) obj;
                if (!appCompatSpinner.getInternalPopup().b()) {
                    appCompatSpinner.f1047f.f(androidx.appcompat.widget.i0.b(appCompatSpinner), androidx.appcompat.widget.i0.a(appCompatSpinner));
                }
                ViewTreeObserver viewTreeObserver = appCompatSpinner.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    androidx.appcompat.widget.h0.a(viewTreeObserver, this);
                    break;
                }
                break;
            case 4:
                n0 n0Var = (n0) obj;
                AppCompatSpinner appCompatSpinner2 = n0Var.G;
                int i8 = d1.OVER_SCROLL_ALWAYS;
                if (!o0.b(appCompatSpinner2) || !appCompatSpinner2.getGlobalVisibleRect(n0Var.E)) {
                    n0Var.dismiss();
                    break;
                } else {
                    n0Var.s();
                    n0Var.g();
                    break;
                }
                break;
            case 5:
                NavigationView navigationView = (NavigationView) obj;
                navigationView.getLocationOnScreen(navigationView.f3997l);
                int[] iArr = navigationView.f3997l;
                boolean z6 = iArr[1] == 0;
                com.google.android.material.internal.v vVar = navigationView.f3994i;
                if (vVar.f3904x != z6) {
                    vVar.f3904x = z6;
                    int i9 = (vVar.f3882b.getChildCount() <= 0 && vVar.f3904x) ? vVar.f3906z : 0;
                    NavigationMenuView navigationMenuView = vVar.f3881a;
                    navigationMenuView.setPadding(0, i9, 0, navigationMenuView.getPaddingBottom());
                }
                navigationView.setDrawTopInsetForeground(z6 && navigationView.f4000o);
                int i10 = iArr[0];
                navigationView.setDrawLeftInsetForeground(i10 == 0 || navigationView.getWidth() + i10 == 0);
                Context context = navigationView.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        activity = null;
                    } else if (context instanceof Activity) {
                        activity = (Activity) context;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
                if (activity != null) {
                    int i11 = Build.VERSION.SDK_INT;
                    WindowManager windowManager = (WindowManager) activity.getSystemService("window");
                    if (i11 >= 30) {
                        currentWindowMetrics = windowManager.getCurrentWindowMetrics();
                        rect = currentWindowMetrics.getBounds();
                    } else {
                        Display defaultDisplay = windowManager.getDefaultDisplay();
                        Point point = new Point();
                        defaultDisplay.getRealSize(point);
                        rect = new Rect();
                        rect.right = point.x;
                        rect.bottom = point.y;
                    }
                    navigationView.setDrawBottomInsetForeground((rect.height() - navigationView.getHeight() == iArr[1]) && (Color.alpha(activity.getWindow().getNavigationBarColor()) != 0) && navigationView.f4001p);
                    navigationView.setDrawRightInsetForeground(rect.width() == iArr[0] || rect.width() - navigationView.getWidth() == iArr[0]);
                    break;
                }
                break;
            default:
                NavigationETAFloatingView navigationETAFloatingView = (NavigationETAFloatingView) obj;
                int i12 = NavigationETAFloatingView.P;
                navigationETAFloatingView.j();
                navigationETAFloatingView.f4565b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                break;
        }
    }
}
