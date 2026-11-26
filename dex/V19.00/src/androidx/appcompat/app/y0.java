package androidx.appcompat.app;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y0 extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f885d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a1 f886e;

    public /* synthetic */ y0(a1 a1Var, int i7) {
        this.f885d = i7;
        this.f886e = a1Var;
    }

    @Override // p0.m1
    public final void a() {
        View view;
        int i7 = this.f885d;
        a1 a1Var = this.f886e;
        switch (i7) {
            case 0:
                if (a1Var.f688o && (view = a1Var.f680g) != null) {
                    view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    a1Var.f677d.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                }
                a1Var.f677d.setVisibility(8);
                a1Var.f677d.setTransitioning(false);
                a1Var.f693t = null;
                j.b bVar = a1Var.f684k;
                if (bVar != null) {
                    bVar.d(a1Var.f683j);
                    a1Var.f683j = null;
                    a1Var.f684k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = a1Var.f676c;
                if (actionBarOverlayLayout != null) {
                    int i8 = d1.OVER_SCROLL_ALWAYS;
                    p0.p0.c(actionBarOverlayLayout);
                    break;
                }
                break;
            default:
                a1Var.f693t = null;
                a1Var.f677d.requestLayout();
                break;
        }
    }
}
