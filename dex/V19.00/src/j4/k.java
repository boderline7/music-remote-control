package j4;

import com.navi.tool.amapflying.util.NavigationETAFloatingView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7010a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ NavigationETAFloatingView f7011b;

    public /* synthetic */ k(NavigationETAFloatingView navigationETAFloatingView, int i7) {
        this.f7010a = i7;
        this.f7011b = navigationETAFloatingView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f7010a;
        NavigationETAFloatingView navigationETAFloatingView = this.f7011b;
        switch (i7) {
            case 0:
                int i8 = NavigationETAFloatingView.P;
                navigationETAFloatingView.setVisibility(8);
                navigationETAFloatingView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
                break;
            default:
                int i9 = NavigationETAFloatingView.P;
                navigationETAFloatingView.j();
                break;
        }
    }
}
