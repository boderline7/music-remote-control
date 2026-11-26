package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class i0 {
    public static g0 a(Context context, z zVar, boolean z6, boolean z7) {
        int nextTransition = zVar.getNextTransition();
        int popEnterAnim = z7 ? z6 ? zVar.getPopEnterAnim() : zVar.getPopExitAnim() : z6 ? zVar.getEnterAnim() : zVar.getExitAnim();
        zVar.setAnimations(0, 0, 0, 0);
        ViewGroup viewGroup = zVar.mContainer;
        if (viewGroup != null && viewGroup.getTag(b1.b.visible_removing_fragment_view_tag) != null) {
            zVar.mContainer.setTag(b1.b.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup2 = zVar.mContainer;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation onCreateAnimation = zVar.onCreateAnimation(nextTransition, z6, popEnterAnim);
        if (onCreateAnimation != null) {
            return new g0(onCreateAnimation);
        }
        Animator onCreateAnimator = zVar.onCreateAnimator(nextTransition, z6, popEnterAnim);
        if (onCreateAnimator != null) {
            return new g0(onCreateAnimator);
        }
        if (popEnterAnim == 0 && nextTransition != 0) {
            popEnterAnim = nextTransition != 4097 ? nextTransition != 4099 ? nextTransition != 8194 ? -1 : z6 ? b1.a.fragment_close_enter : b1.a.fragment_close_exit : z6 ? b1.a.fragment_fade_enter : b1.a.fragment_fade_exit : z6 ? b1.a.fragment_open_enter : b1.a.fragment_open_exit;
        }
        if (popEnterAnim != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(popEnterAnim));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, popEnterAnim);
                    if (loadAnimation != null) {
                        return new g0(loadAnimation);
                    }
                } catch (Resources.NotFoundException e7) {
                    throw e7;
                } catch (RuntimeException unused) {
                }
            }
            try {
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, popEnterAnim);
                if (loadAnimator != null) {
                    return new g0(loadAnimator);
                }
            } catch (RuntimeException e8) {
                if (equals) {
                    throw e8;
                }
                Animation loadAnimation2 = AnimationUtils.loadAnimation(context, popEnterAnim);
                if (loadAnimation2 != null) {
                    return new g0(loadAnimation2);
                }
            }
        }
        return null;
    }
}
