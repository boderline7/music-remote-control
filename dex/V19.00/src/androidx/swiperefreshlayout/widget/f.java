package androidx.swiperefreshlayout.widget;

import android.view.animation.Animation;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f implements Animation.AnimationListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2790a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SwipeRefreshLayout f2791b;

    public /* synthetic */ f(SwipeRefreshLayout swipeRefreshLayout, int i7) {
        this.f2790a = i7;
        this.f2791b = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        j jVar;
        int i7 = this.f2790a;
        SwipeRefreshLayout swipeRefreshLayout = this.f2791b;
        switch (i7) {
            case 0:
                if (!swipeRefreshLayout.f2728c) {
                    swipeRefreshLayout.l();
                    break;
                } else {
                    swipeRefreshLayout.B.setAlpha(255);
                    swipeRefreshLayout.B.start();
                    if (swipeRefreshLayout.H && (jVar = swipeRefreshLayout.f2727b) != null) {
                        ((com.navi.tool.ui.main.b) jVar).f5766a.fetchStoreConfigs();
                    }
                    swipeRefreshLayout.f2739n = swipeRefreshLayout.f2746u.getTop();
                    break;
                }
            default:
                if (!swipeRefreshLayout.f2744s) {
                    g gVar = new g(swipeRefreshLayout, 1);
                    swipeRefreshLayout.D = gVar;
                    gVar.setDuration(150L);
                    a aVar = swipeRefreshLayout.f2746u;
                    aVar.f2753a = null;
                    aVar.clearAnimation();
                    swipeRefreshLayout.f2746u.startAnimation(swipeRefreshLayout.D);
                    break;
                }
                break;
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
