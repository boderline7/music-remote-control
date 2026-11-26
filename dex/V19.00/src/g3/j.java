package g3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public boolean f6467a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f6468b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i6.i f6469c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ q f6470d;

    public j(q qVar, boolean z6, i6.i iVar) {
        this.f6470d = qVar;
        this.f6468b = z6;
        this.f6469c = iVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f6467a = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        q qVar = this.f6470d;
        qVar.f6511r = 0;
        qVar.f6505l = null;
        if (this.f6467a) {
            return;
        }
        boolean z6 = this.f6468b;
        qVar.f6515v.a(z6 ? 8 : 4, z6);
        i6.i iVar = this.f6469c;
        if (iVar != null) {
            ((l6.f) iVar.f6848b).d0((FloatingActionButton) iVar.f6849c);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        q qVar = this.f6470d;
        qVar.f6515v.a(0, this.f6468b);
        qVar.f6511r = 1;
        qVar.f6505l = animator;
        this.f6467a = false;
    }
}
