package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EdgeEffect;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class g {
    public static EdgeEffect a(Context context, AttributeSet attributeSet) {
        try {
            return new EdgeEffect(context, attributeSet);
        } catch (Throwable unused) {
            return new EdgeEffect(context);
        }
    }

    public static float b(EdgeEffect edgeEffect) {
        try {
            return edgeEffect.getDistance();
        } catch (Throwable unused) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public static float c(EdgeEffect edgeEffect, float f7, float f8) {
        try {
            return edgeEffect.onPullDistance(f7, f8);
        } catch (Throwable unused) {
            edgeEffect.onPull(f7, f8);
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }
}
