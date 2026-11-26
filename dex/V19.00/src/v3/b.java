package v3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends AnimatorListenerAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f9393a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f9394b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f9395c;

    public b(boolean z6, View view, View view2) {
        this.f9393a = z6;
        this.f9394b = view;
        this.f9395c = view2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.f9393a) {
            return;
        }
        this.f9394b.setVisibility(4);
        View view = this.f9395c;
        view.setAlpha(1.0f);
        view.setVisibility(0);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        if (this.f9393a) {
            this.f9394b.setVisibility(0);
            View view = this.f9395c;
            view.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            view.setVisibility(4);
        }
    }
}
