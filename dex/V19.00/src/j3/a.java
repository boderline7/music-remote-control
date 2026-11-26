package j3;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final TimeInterpolator f6953a;

    /* renamed from: b, reason: collision with root package name */
    public final View f6954b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6955c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6956d;

    /* renamed from: e, reason: collision with root package name */
    public final int f6957e;

    /* renamed from: f, reason: collision with root package name */
    public androidx.activity.c f6958f;

    public a(View view) {
        this.f6954b = view;
        Context context = view.getContext();
        this.f6953a = l6.f.v0(context, q2.c.motionEasingStandardDecelerateInterpolator, r0.a.b(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f));
        this.f6955c = l6.f.u0(context, q2.c.motionDurationMedium2, x2.d.DEFAULT_FADE_ANIM_DURATION);
        this.f6956d = l6.f.u0(context, q2.c.motionDurationShort3, z1.c.OR_INT);
        this.f6957e = l6.f.u0(context, q2.c.motionDurationShort2, 100);
    }
}
