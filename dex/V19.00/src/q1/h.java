package q1;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.fragment.app.o;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.NavigationMenuItemView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8439d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f8440e;

    public /* synthetic */ h(int i7, View view) {
        this.f8439d = i7;
        this.f8440e = view;
    }

    @Override // p0.c
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        int i7 = this.f8439d;
        View view2 = this.f8440e;
        switch (i7) {
            case 0:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setClassName(ViewPager.class.getName());
                ((ViewPager) view2).getClass();
                accessibilityEvent.setScrollable(false);
                if (accessibilityEvent.getEventType() == 4096) {
                    ((ViewPager) view2).getClass();
                    break;
                }
                break;
            case 3:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setChecked(((CheckableImageButton) view2).isChecked());
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        int i7 = this.f8439d;
        int i8 = 0;
        int i9 = -1;
        View view2 = this.f8440e;
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        switch (i7) {
            case 0:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                iVar.f(ViewPager.class.getName());
                ((ViewPager) view2).getClass();
                iVar.h(false);
                ViewPager viewPager = (ViewPager) view2;
                if (viewPager.canScrollHorizontally(1)) {
                    iVar.a(4096);
                }
                if (viewPager.canScrollHorizontally(-1)) {
                    iVar.a(8192);
                    break;
                }
                break;
            case 1:
            default:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                break;
            case 2:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) view2;
                int i10 = MaterialButtonToggleGroup.f3445k;
                materialButtonToggleGroup.getClass();
                if (view instanceof MaterialButton) {
                    int i11 = 0;
                    while (true) {
                        if (i8 < materialButtonToggleGroup.getChildCount()) {
                            if (materialButtonToggleGroup.getChildAt(i8) == view) {
                                i9 = i11;
                            } else {
                                if ((materialButtonToggleGroup.getChildAt(i8) instanceof MaterialButton) && materialButtonToggleGroup.c(i8)) {
                                    i11++;
                                }
                                i8++;
                            }
                        }
                    }
                }
                iVar.g(o.b(0, 1, i9, 1, false, ((MaterialButton) view).isChecked()));
                break;
            case 3:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                CheckableImageButton checkableImageButton = (CheckableImageButton) view2;
                accessibilityNodeInfo.setCheckable(checkableImageButton.f3762e);
                accessibilityNodeInfo.setChecked(checkableImageButton.isChecked());
                break;
            case 4:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCheckable(((NavigationMenuItemView) view2).f3779x);
                break;
        }
    }

    @Override // p0.c
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.f8439d) {
            case 1:
                super.e(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 1) {
                    BottomSheetDragHandleView bottomSheetDragHandleView = (BottomSheetDragHandleView) this.f8440e;
                    int i7 = BottomSheetDragHandleView.f3417m;
                    bottomSheetDragHandleView.c();
                    break;
                }
                break;
            default:
                super.e(view, accessibilityEvent);
                break;
        }
    }

    @Override // p0.c
    public final boolean g(View view, int i7, Bundle bundle) {
        switch (this.f8439d) {
            case 0:
                if (super.g(view, i7, bundle)) {
                    return true;
                }
                View view2 = this.f8440e;
                if (i7 == 4096) {
                    ViewPager viewPager = (ViewPager) view2;
                    if (viewPager.canScrollHorizontally(1)) {
                        viewPager.getClass();
                        viewPager.setCurrentItem(0 + 1);
                        return true;
                    }
                } else if (i7 == 8192) {
                    ViewPager viewPager2 = (ViewPager) view2;
                    if (viewPager2.canScrollHorizontally(-1)) {
                        viewPager2.getClass();
                        viewPager2.setCurrentItem(0 - 1);
                        return true;
                    }
                }
                return false;
            default:
                return super.g(view, i7, bundle);
        }
    }
}
