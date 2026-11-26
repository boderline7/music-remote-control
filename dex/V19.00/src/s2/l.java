package s2;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f8692a = {R.attr.stateListAnimator};

    public static void a(View view, float f7) {
        int integer = view.getResources().getInteger(q2.h.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j7 = integer;
        stateListAnimator.addState(new int[]{R.attr.state_enabled, q2.c.state_liftable, -q2.c.state_lifted}, ObjectAnimator.ofFloat(view, y.c.ELEVATION, CropImageView.DEFAULT_ASPECT_RATIO).setDuration(j7));
        stateListAnimator.addState(new int[]{R.attr.state_enabled}, ObjectAnimator.ofFloat(view, y.c.ELEVATION, f7).setDuration(j7));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, y.c.ELEVATION, CropImageView.DEFAULT_ASPECT_RATIO).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }
}
