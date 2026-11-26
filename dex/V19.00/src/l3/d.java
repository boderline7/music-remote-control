package l3;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.internal.e0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {

    /* renamed from: a, reason: collision with root package name */
    public int f7343a;

    /* renamed from: b, reason: collision with root package name */
    public int f7344b;

    /* renamed from: c, reason: collision with root package name */
    public int[] f7345c;

    /* renamed from: d, reason: collision with root package name */
    public int f7346d;

    /* renamed from: e, reason: collision with root package name */
    public int f7347e;

    /* renamed from: f, reason: collision with root package name */
    public int f7348f;

    public d(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f7345c = new int[0];
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(q2.e.mtrl_progress_track_thickness);
        int[] iArr = q2.m.BaseProgressIndicator;
        e0.a(context, attributeSet, i7, i8);
        e0.b(context, attributeSet, iArr, i7, i8, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, i8);
        this.f7343a = l6.f.z(context, obtainStyledAttributes, q2.m.BaseProgressIndicator_trackThickness, dimensionPixelSize);
        this.f7344b = Math.min(l6.f.z(context, obtainStyledAttributes, q2.m.BaseProgressIndicator_trackCornerRadius, 0), this.f7343a / 2);
        this.f7347e = obtainStyledAttributes.getInt(q2.m.BaseProgressIndicator_showAnimationBehavior, 0);
        this.f7348f = obtainStyledAttributes.getInt(q2.m.BaseProgressIndicator_hideAnimationBehavior, 0);
        if (!obtainStyledAttributes.hasValue(q2.m.BaseProgressIndicator_indicatorColor)) {
            this.f7345c = new int[]{b3.a.c(context, q2.c.colorPrimary, -1)};
        } else if (obtainStyledAttributes.peekValue(q2.m.BaseProgressIndicator_indicatorColor).type != 1) {
            this.f7345c = new int[]{obtainStyledAttributes.getColor(q2.m.BaseProgressIndicator_indicatorColor, -1)};
        } else {
            int[] intArray = context.getResources().getIntArray(obtainStyledAttributes.getResourceId(q2.m.BaseProgressIndicator_indicatorColor, -1));
            this.f7345c = intArray;
            if (intArray.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
        if (obtainStyledAttributes.hasValue(q2.m.BaseProgressIndicator_trackColor)) {
            this.f7346d = obtainStyledAttributes.getColor(q2.m.BaseProgressIndicator_trackColor, -1);
        } else {
            this.f7346d = this.f7345c[0];
            TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{R.attr.disabledAlpha});
            float f7 = obtainStyledAttributes2.getFloat(0, 0.2f);
            obtainStyledAttributes2.recycle();
            this.f7346d = b3.a.a(this.f7346d, (int) (f7 * 255.0f));
        }
        obtainStyledAttributes.recycle();
    }

    public abstract void a();
}
