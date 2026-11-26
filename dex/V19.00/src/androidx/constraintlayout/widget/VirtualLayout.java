package androidx.constraintlayout.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import com.yalantis.ucrop.view.CropImageView;
import u.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class VirtualLayout extends ConstraintHelper {

    /* renamed from: i, reason: collision with root package name */
    public boolean f1712i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1713j;

    public VirtualLayout(Context context) {
        super(context);
    }

    public VirtualLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VirtualLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void h(ConstraintLayout constraintLayout) {
        g(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_android_visibility) {
                    this.f1712i = true;
                } else if (index == t.ConstraintLayout_Layout_android_elevation) {
                    this.f1713j = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1712i || this.f1713j) {
            ViewParent parent = getParent();
            if (parent instanceof ConstraintLayout) {
                ConstraintLayout constraintLayout = (ConstraintLayout) parent;
                int visibility = getVisibility();
                float elevation = getElevation();
                for (int i7 = 0; i7 < this.f1678b; i7++) {
                    View c7 = constraintLayout.c(this.f1677a[i7]);
                    if (c7 != null) {
                        if (this.f1712i) {
                            c7.setVisibility(visibility);
                        }
                        if (this.f1713j && elevation > CropImageView.DEFAULT_ASPECT_RATIO) {
                            c7.setTranslationZ(c7.getTranslationZ() + elevation);
                        }
                    }
                }
            }
        }
    }

    public void s(s sVar, int i7, int i8) {
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        f();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        f();
    }
}
