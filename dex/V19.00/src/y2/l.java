package y2;

import android.view.View;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.google.android.material.textfield.t;
import com.navi.tool.MainActivity;
import com.yalantis.ucrop.UCropActivity;
import p0.b0;
import p0.l2;
import p3.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l implements o, com.google.android.material.internal.j, t, s3.h, b0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10167a;

    public /* synthetic */ l(int i7) {
        this.f10167a = i7;
    }

    @Override // p3.o
    public final p3.d a(p3.d dVar) {
        int i7 = MaskableFrameLayout.f3500f;
        return dVar instanceof p3.a ? new p3.c(((p3.a) dVar).f8224a) : dVar;
    }

    @Override // s3.h
    public final void b(com.google.android.material.tabs.b bVar, int i7) {
        MainActivity.lambda$setupTabsAndPager$21(bVar, i7);
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        int i7 = UCropActivity.DEFAULT_COMPRESS_QUALITY;
        i0.c f7 = l2Var.f8161a.f(7);
        view.setPaddingRelative(f7.f6773a, f7.f6774b, f7.f6775c, 0);
        return l2Var;
    }
}
