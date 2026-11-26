package k;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t extends FrameLayout implements j.d {

    /* renamed from: a, reason: collision with root package name */
    public final CollapsibleActionView f7195a;

    /* JADX WARN: Multi-variable type inference failed */
    public t(View view) {
        super(view.getContext());
        this.f7195a = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // j.d
    public final void b() {
        this.f7195a.onActionViewExpanded();
    }

    @Override // j.d
    public final void e() {
        this.f7195a.onActionViewCollapsed();
    }
}
