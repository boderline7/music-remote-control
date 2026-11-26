package x;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends l {

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f9530f;

    @Override // x.l
    public final void c(View view, float f7) {
        switch (this.f9530f) {
            case 0:
                view.setAlpha(a(f7));
                break;
            case 1:
                view.setElevation(a(f7));
                break;
            case 2:
                view.setPivotX(a(f7));
                break;
            case 3:
                view.setPivotY(a(f7));
                break;
            case 4:
                view.setRotation(a(f7));
                break;
            case 5:
                view.setRotationX(a(f7));
                break;
            case 6:
                view.setRotationY(a(f7));
                break;
            case 7:
                view.setScaleX(a(f7));
                break;
            case 8:
                view.setScaleY(a(f7));
                break;
            case 9:
                view.setTranslationX(a(f7));
                break;
            case 10:
                view.setTranslationY(a(f7));
                break;
            default:
                view.setTranslationZ(a(f7));
                break;
        }
    }
}
