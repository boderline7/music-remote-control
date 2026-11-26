package p3;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d0 extends b0 {
    public d0(View view) {
        d(view);
    }

    private void d(View view) {
        view.setOutlineProvider(new z2.c(2, this));
    }

    @Override // p3.b0
    public final void a(View view) {
        view.setClipToOutline(!this.f8227a);
        if (this.f8227a) {
            view.invalidate();
        } else {
            view.invalidateOutline();
        }
    }

    @Override // p3.b0
    public final boolean b() {
        return this.f8227a;
    }
}
