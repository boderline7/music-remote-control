package k;

import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.v1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends v1 {

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f7058j = 0;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ View f7059k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f7059k = actionMenuItemView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ActivityChooserView activityChooserView, FrameLayout frameLayout) {
        super(frameLayout);
        this.f7059k = activityChooserView;
    }

    @Override // androidx.appcompat.widget.v1
    public final g0 b() {
        androidx.appcompat.widget.h hVar;
        int i7 = this.f7058j;
        View view = this.f7059k;
        switch (i7) {
            case 0:
                c cVar = ((ActionMenuItemView) view).f899m;
                if (cVar == null || (hVar = ((androidx.appcompat.widget.i) cVar).f1288a.f1343u) == null) {
                    return null;
                }
                return hVar.a();
            default:
                return ((ActivityChooserView) view).getListPopupWindow();
        }
    }

    @Override // androidx.appcompat.widget.v1
    public final boolean c() {
        int i7 = this.f7058j;
        View view = this.f7059k;
        switch (i7) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) view;
                n nVar = actionMenuItemView.f897k;
                if (nVar == null || !nVar.a(actionMenuItemView.f894h)) {
                    return false;
                }
                g0 b7 = b();
                return b7 != null && b7.b();
            default:
                ActivityChooserView activityChooserView = (ActivityChooserView) view;
                if (activityChooserView.b() || !activityChooserView.f1002k) {
                    return true;
                }
                activityChooserView.f992a.getClass();
                throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
    }

    @Override // androidx.appcompat.widget.v1
    public final boolean d() {
        switch (this.f7058j) {
            case 1:
                ((ActivityChooserView) this.f7059k).a();
                break;
            default:
                g0 b7 = b();
                if (b7 != null && b7.b()) {
                    b7.dismiss();
                    break;
                }
                break;
        }
        return true;
    }
}
