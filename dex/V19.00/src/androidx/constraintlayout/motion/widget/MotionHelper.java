package androidx.constraintlayout.motion.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;
import y.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionHelper extends ConstraintHelper implements v {

    /* renamed from: i, reason: collision with root package name */
    public boolean f1555i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f1556j;

    /* renamed from: k, reason: collision with root package name */
    public float f1557k;

    /* renamed from: l, reason: collision with root package name */
    public View[] f1558l;

    public MotionHelper(Context context) {
        super(context);
        this.f1555i = false;
        this.f1556j = false;
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1555i = false;
        this.f1556j = false;
        l(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1555i = false;
        this.f1556j = false;
        l(attributeSet);
    }

    public void a(int i7) {
    }

    public void b() {
    }

    public float getProgress() {
        return this.f1557k;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.MotionHelper);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.MotionHelper_onShow) {
                    this.f1555i = obtainStyledAttributes.getBoolean(index, this.f1555i);
                } else if (index == t.MotionHelper_onHide) {
                    this.f1556j = obtainStyledAttributes.getBoolean(index, this.f1556j);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void s(MotionLayout motionLayout, HashMap hashMap) {
    }

    public void setProgress(float f7) {
        this.f1557k = f7;
        int i7 = 0;
        if (this.f1678b > 0) {
            this.f1558l = k((ConstraintLayout) getParent());
            while (i7 < this.f1678b) {
                setProgress(this.f1558l[i7], f7);
                i7++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i7 < childCount) {
            View childAt = viewGroup.getChildAt(i7);
            if (!(childAt instanceof MotionHelper)) {
                setProgress(childAt, f7);
            }
            i7++;
        }
    }

    public void setProgress(View view, float f7) {
    }
}
