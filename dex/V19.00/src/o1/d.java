package o1;

import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    public p f7799a;

    /* renamed from: b, reason: collision with root package name */
    public AnimatorSet f7800b;

    /* renamed from: c, reason: collision with root package name */
    public ArrayList f7801c;

    /* renamed from: d, reason: collision with root package name */
    public q.b f7802d;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
