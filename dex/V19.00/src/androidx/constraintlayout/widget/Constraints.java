package androidx.constraintlayout.widget;

import a0.f;
import a0.k;
import a0.l;
import a0.p;
import a0.q;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Constraints extends ViewGroup {
    public static final String TAG = "Constraints";

    /* renamed from: a, reason: collision with root package name */
    public p f1703a;

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        super.setVisibility(8);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new q();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        q qVar = new q(context, attributeSet);
        qVar.f175r0 = 1.0f;
        qVar.f176s0 = false;
        qVar.f177t0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.f178u0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.f179v0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.f180w0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.f181x0 = 1.0f;
        qVar.f182y0 = 1.0f;
        qVar.f183z0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.A0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.B0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.C0 = CropImageView.DEFAULT_ASPECT_RATIO;
        qVar.D0 = CropImageView.DEFAULT_ASPECT_RATIO;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.ConstraintSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.ConstraintSet_android_alpha) {
                qVar.f175r0 = obtainStyledAttributes.getFloat(index, qVar.f175r0);
            } else if (index == t.ConstraintSet_android_elevation) {
                qVar.f177t0 = obtainStyledAttributes.getFloat(index, qVar.f177t0);
                qVar.f176s0 = true;
            } else if (index == t.ConstraintSet_android_rotationX) {
                qVar.f179v0 = obtainStyledAttributes.getFloat(index, qVar.f179v0);
            } else if (index == t.ConstraintSet_android_rotationY) {
                qVar.f180w0 = obtainStyledAttributes.getFloat(index, qVar.f180w0);
            } else if (index == t.ConstraintSet_android_rotation) {
                qVar.f178u0 = obtainStyledAttributes.getFloat(index, qVar.f178u0);
            } else if (index == t.ConstraintSet_android_scaleX) {
                qVar.f181x0 = obtainStyledAttributes.getFloat(index, qVar.f181x0);
            } else if (index == t.ConstraintSet_android_scaleY) {
                qVar.f182y0 = obtainStyledAttributes.getFloat(index, qVar.f182y0);
            } else if (index == t.ConstraintSet_android_transformPivotX) {
                qVar.f183z0 = obtainStyledAttributes.getFloat(index, qVar.f183z0);
            } else if (index == t.ConstraintSet_android_transformPivotY) {
                qVar.A0 = obtainStyledAttributes.getFloat(index, qVar.A0);
            } else if (index == t.ConstraintSet_android_translationX) {
                qVar.B0 = obtainStyledAttributes.getFloat(index, qVar.B0);
            } else if (index == t.ConstraintSet_android_translationY) {
                qVar.C0 = obtainStyledAttributes.getFloat(index, qVar.C0);
            } else if (index == t.ConstraintSet_android_translationZ) {
                qVar.D0 = obtainStyledAttributes.getFloat(index, qVar.D0);
            }
        }
        obtainStyledAttributes.recycle();
        return qVar;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new f(layoutParams);
    }

    public p getConstraintSet() {
        if (this.f1703a == null) {
            this.f1703a = new p();
        }
        p pVar = this.f1703a;
        pVar.getClass();
        int childCount = getChildCount();
        HashMap hashMap = pVar.f174f;
        hashMap.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            q qVar = (q) childAt.getLayoutParams();
            int id = childAt.getId();
            if (pVar.f173e && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!hashMap.containsKey(Integer.valueOf(id))) {
                hashMap.put(Integer.valueOf(id), new k());
            }
            k kVar = (k) hashMap.get(Integer.valueOf(id));
            if (kVar != null) {
                if (childAt instanceof ConstraintHelper) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) childAt;
                    kVar.d(id, qVar);
                    if (constraintHelper instanceof Barrier) {
                        l lVar = kVar.f85e;
                        lVar.f107i0 = 1;
                        Barrier barrier = (Barrier) constraintHelper;
                        lVar.f103g0 = barrier.getType();
                        lVar.f109j0 = barrier.getReferencedIds();
                        lVar.f105h0 = barrier.getMargin();
                    }
                }
                kVar.d(id, qVar);
            }
        }
        return this.f1703a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
    }
}
