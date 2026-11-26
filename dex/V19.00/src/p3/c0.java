package p3;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c0 extends b0 {

    /* renamed from: f, reason: collision with root package name */
    public boolean f8233f = false;

    /* renamed from: g, reason: collision with root package name */
    public float f8234g = CropImageView.DEFAULT_ASPECT_RATIO;

    public c0(View view) {
        d(view);
    }

    private void d(View view) {
        view.setOutlineProvider(new z2.c(1, this));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x010a  */
    @Override // p3.b0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.view.View r9) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.c0.a(android.view.View):void");
    }

    @Override // p3.b0
    public final boolean b() {
        return !this.f8233f || this.f8227a;
    }
}
