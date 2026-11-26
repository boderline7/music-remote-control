package n1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7642a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7643b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f7644c;

    public /* synthetic */ o(int i7, Object obj, Object obj2) {
        this.f7642a = i7;
        this.f7644c = obj;
        this.f7643b = obj2;
    }

    public o(DrawerLayout drawerLayout, View view) {
        this.f7642a = 2;
        this.f7643b = drawerLayout;
        this.f7644c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i7 = this.f7642a;
        Object obj = this.f7644c;
        Object obj2 = this.f7643b;
        switch (i7) {
            case 0:
                ((q.b) obj2).remove(animator);
                ((r) obj).f7665m.remove(animator);
                break;
            case 1:
                View view = (View) obj2;
                if (view != null) {
                    view.setVisibility(0);
                    break;
                }
                break;
            case 2:
                DrawerLayout drawerLayout = (DrawerLayout) obj2;
                drawerLayout.c((View) obj, false);
                drawerLayout.setScrimColor(-1728053248);
                break;
            default:
                a3.g gVar = (a3.g) obj2;
                a3.f revealInfo = gVar.getRevealInfo();
                revealInfo.f266c = Float.MAX_VALUE;
                gVar.setRevealInfo(revealInfo);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f7642a) {
            case 0:
                ((r) this.f7644c).f7665m.add(animator);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }
}
