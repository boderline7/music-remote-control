package androidx.viewpager2.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o extends RecyclerView {

    /* renamed from: z0, reason: collision with root package name */
    public final /* synthetic */ ViewPager2 f2919z0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(ViewPager2 viewPager2, Context context) {
        super(context);
        this.f2919z0 = viewPager2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public final CharSequence getAccessibilityClassName() {
        this.f2919z0.f2887t.getClass();
        return super.getAccessibilityClassName();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        ViewPager2 viewPager2 = this.f2919z0;
        accessibilityEvent.setFromIndex(viewPager2.f2871d);
        accessibilityEvent.setToIndex(viewPager2.f2871d);
        viewPager2.f2887t.j(accessibilityEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f2919z0.f2885r && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f2919z0.f2885r && super.onTouchEvent(motionEvent);
    }
}
