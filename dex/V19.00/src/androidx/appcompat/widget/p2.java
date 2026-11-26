package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p2 extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ScrollingTabContainerView f1376a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p2(ScrollingTabContainerView scrollingTabContainerView, Context context) {
        super(context, null, f.a.actionBarTabStyle);
        int resourceId;
        this.f1376a = scrollingTabContainerView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.background}, f.a.actionBarTabStyle, 0);
        if (obtainStyledAttributes.hasValue(0)) {
            setBackgroundDrawable((!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) ? obtainStyledAttributes.getDrawable(0) : l6.f.A(context, resourceId));
        }
        obtainStyledAttributes.recycle();
        setGravity(8388627);
        throw null;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.app.ActionBar$Tab");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.app.ActionBar$Tab");
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        ScrollingTabContainerView scrollingTabContainerView = this.f1376a;
        if (scrollingTabContainerView.f1097e > 0) {
            int measuredWidth = getMeasuredWidth();
            int i9 = scrollingTabContainerView.f1097e;
            if (measuredWidth > i9) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i9, v.c.EXACTLY), i8);
            }
        }
    }

    @Override // android.view.View
    public final void setSelected(boolean z6) {
        boolean z7 = isSelected() != z6;
        super.setSelected(z6);
        if (z7 && z6) {
            sendAccessibilityEvent(4);
        }
    }
}
