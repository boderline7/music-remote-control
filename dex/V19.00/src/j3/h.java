package j3;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.search.SearchBar;
import com.yalantis.ucrop.view.CropImageView;
import n1.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends a {

    /* renamed from: g, reason: collision with root package name */
    public final float f6967g;

    /* renamed from: h, reason: collision with root package name */
    public final float f6968h;

    /* renamed from: i, reason: collision with root package name */
    public float f6969i;

    /* renamed from: j, reason: collision with root package name */
    public Rect f6970j;

    /* renamed from: k, reason: collision with root package name */
    public Rect f6971k;

    /* renamed from: l, reason: collision with root package name */
    public Integer f6972l;

    public h(ClippableRoundedCornerLayout clippableRoundedCornerLayout) {
        super(clippableRoundedCornerLayout);
        Resources resources = clippableRoundedCornerLayout.getResources();
        this.f6967g = resources.getDimension(q2.e.m3_back_progress_main_container_min_edge_gap);
        this.f6968h = resources.getDimension(q2.e.m3_back_progress_main_container_max_translation_y);
    }

    public final AnimatorSet a(SearchBar searchBar) {
        AnimatorSet animatorSet = new AnimatorSet();
        View view = this.f6954b;
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, 1.0f), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, CropImageView.DEFAULT_ASPECT_RATIO), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, CropImageView.DEFAULT_ASPECT_RATIO));
        animatorSet.addListener(new o(1, this, searchBar));
        return animatorSet;
    }

    public final int b() {
        WindowInsets rootWindowInsets;
        RoundedCorner roundedCorner;
        RoundedCorner roundedCorner2;
        RoundedCorner roundedCorner3;
        RoundedCorner roundedCorner4;
        if (this.f6972l == null) {
            int[] iArr = new int[2];
            View view = this.f6954b;
            view.getLocationOnScreen(iArr);
            if (iArr[1] == 0 && Build.VERSION.SDK_INT >= 31 && (rootWindowInsets = view.getRootWindowInsets()) != null) {
                roundedCorner = rootWindowInsets.getRoundedCorner(0);
                int radius = roundedCorner != null ? roundedCorner.getRadius() : 0;
                roundedCorner2 = rootWindowInsets.getRoundedCorner(1);
                int max = Math.max(radius, roundedCorner2 != null ? roundedCorner2.getRadius() : 0);
                roundedCorner3 = rootWindowInsets.getRoundedCorner(3);
                int radius2 = roundedCorner3 != null ? roundedCorner3.getRadius() : 0;
                roundedCorner4 = rootWindowInsets.getRoundedCorner(2);
                r4 = Math.max(max, Math.max(radius2, roundedCorner4 != null ? roundedCorner4.getRadius() : 0));
            }
            this.f6972l = Integer.valueOf(r4);
        }
        return this.f6972l.intValue();
    }
}
