package androidx.appcompat.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ActivityChooserView f1423a;

    public v(ActivityChooserView activityChooserView) {
        this.f1423a = activityChooserView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ActivityChooserView activityChooserView = this.f1423a;
        FrameLayout frameLayout = activityChooserView.f997f;
        u uVar = activityChooserView.f992a;
        if (view == frameLayout) {
            activityChooserView.a();
            uVar.getClass();
            throw null;
        }
        if (view != activityChooserView.f995d) {
            throw new IllegalArgumentException();
        }
        uVar.getClass();
        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        m mVar;
        k.o oVar;
        ActivityChooserView activityChooserView = this.f1423a;
        PopupWindow.OnDismissListener onDismissListener = activityChooserView.f1001j;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
        p0.d dVar = activityChooserView.f998g;
        if (dVar == null || (mVar = dVar.f8109a) == null || (oVar = mVar.f1325c) == null) {
            return;
        }
        oVar.c(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        ((u) adapterView.getAdapter()).getClass();
        ActivityChooserView activityChooserView = this.f1423a;
        activityChooserView.a();
        activityChooserView.f992a.getClass();
        throw null;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        ActivityChooserView activityChooserView = this.f1423a;
        if (view != activityChooserView.f997f) {
            throw new IllegalArgumentException();
        }
        activityChooserView.f992a.getClass();
        throw null;
    }
}
