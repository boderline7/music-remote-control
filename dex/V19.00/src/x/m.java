package x;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends q {

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f9539g;

    @Override // x.q
    public final boolean c(float f7, long j7, View view, t.g gVar) {
        switch (this.f9539g) {
            case 0:
                view.setAlpha(b(f7, j7, view, gVar));
                break;
            case 1:
                view.setElevation(b(f7, j7, view, gVar));
                break;
            case 2:
                view.setRotation(b(f7, j7, view, gVar));
                break;
            case 3:
                view.setRotationX(b(f7, j7, view, gVar));
                break;
            case 4:
                view.setRotationY(b(f7, j7, view, gVar));
                break;
            case 5:
                view.setScaleX(b(f7, j7, view, gVar));
                break;
            case 6:
                view.setScaleY(b(f7, j7, view, gVar));
                break;
            case 7:
                view.setTranslationX(b(f7, j7, view, gVar));
                break;
            case 8:
                view.setTranslationY(b(f7, j7, view, gVar));
                break;
            default:
                view.setTranslationZ(b(f7, j7, view, gVar));
                break;
        }
        return this.f9009d;
    }
}
