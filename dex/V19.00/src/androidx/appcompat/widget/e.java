package androidx.appcompat.widget;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1207a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f1208b;

    public /* synthetic */ e(ActionBarOverlayLayout actionBarOverlayLayout, int i7) {
        this.f1207a = i7;
        this.f1208b = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f1207a;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1208b;
        switch (i7) {
            case 0:
                actionBarOverlayLayout.c();
                actionBarOverlayLayout.f976w = actionBarOverlayLayout.f957d.animate().translationY(CropImageView.DEFAULT_ASPECT_RATIO).setListener(actionBarOverlayLayout.f977x);
                break;
            default:
                actionBarOverlayLayout.c();
                actionBarOverlayLayout.f976w = actionBarOverlayLayout.f957d.animate().translationY(-actionBarOverlayLayout.f957d.getHeight()).setListener(actionBarOverlayLayout.f977x);
                break;
        }
    }
}
