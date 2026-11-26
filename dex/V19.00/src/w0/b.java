package w0;

import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.drawerlayout.widget.DrawerLayout;
import java.util.List;
import p0.d1;
import p0.l0;
import p0.m0;
import q0.g;
import q0.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final Rect f9403d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ DrawerLayout f9404e;

    public b(DrawerLayout drawerLayout) {
        this.f9404e = drawerLayout;
    }

    @Override // p0.c
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            return this.f8103a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }
        List<CharSequence> text = accessibilityEvent.getText();
        DrawerLayout drawerLayout = this.f9404e;
        View h7 = drawerLayout.h();
        if (h7 == null) {
            return true;
        }
        int k7 = drawerLayout.k(h7);
        drawerLayout.getClass();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int absoluteGravity = Gravity.getAbsoluteGravity(k7, m0.d(drawerLayout));
        CharSequence charSequence = absoluteGravity == 3 ? drawerLayout.f1856z : absoluteGravity == 5 ? drawerLayout.A : null;
        if (charSequence == null) {
            return true;
        }
        text.add(charSequence);
        return true;
    }

    @Override // p0.c
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        accessibilityEvent.setClassName("androidx.drawerlayout.widget.DrawerLayout");
    }

    @Override // p0.c
    public final void d(View view, i iVar) {
        boolean z6 = DrawerLayout.N;
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        if (z6) {
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        } else {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
            accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, obtain);
            iVar.f8428c = -1;
            accessibilityNodeInfo.setSource(view);
            int i7 = d1.OVER_SCROLL_ALWAYS;
            Object f7 = l0.f(view);
            if (f7 instanceof View) {
                iVar.f8427b = -1;
                accessibilityNodeInfo.setParent((View) f7);
            }
            Rect rect = this.f9403d;
            obtain.getBoundsInScreen(rect);
            accessibilityNodeInfo.setBoundsInScreen(rect);
            accessibilityNodeInfo.setVisibleToUser(obtain.isVisibleToUser());
            accessibilityNodeInfo.setPackageName(obtain.getPackageName());
            iVar.f(obtain.getClassName());
            accessibilityNodeInfo.setContentDescription(obtain.getContentDescription());
            accessibilityNodeInfo.setEnabled(obtain.isEnabled());
            accessibilityNodeInfo.setFocused(obtain.isFocused());
            accessibilityNodeInfo.setAccessibilityFocused(obtain.isAccessibilityFocused());
            accessibilityNodeInfo.setSelected(obtain.isSelected());
            iVar.a(obtain.getActions());
            obtain.recycle();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (DrawerLayout.m(childAt)) {
                    accessibilityNodeInfo.addChild(childAt);
                }
            }
        }
        iVar.f("androidx.drawerlayout.widget.DrawerLayout");
        accessibilityNodeInfo.setFocusable(false);
        accessibilityNodeInfo.setFocused(false);
        accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) g.ACTION_FOCUS.f8421a);
        accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) g.ACTION_CLEAR_FOCUS.f8421a);
    }

    @Override // p0.c
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        if (DrawerLayout.N || DrawerLayout.m(view)) {
            return this.f8103a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
        return false;
    }
}
