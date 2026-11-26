package androidx.constraintlayout.widget;

import a0.f;
import a0.t;
import a0.u;
import a0.v;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ReactiveGuide extends View implements u {

    /* renamed from: a, reason: collision with root package name */
    public int f1708a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1709b;

    /* renamed from: c, reason: collision with root package name */
    public int f1710c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1711d;

    public ReactiveGuide(Context context) {
        super(context);
        this.f1708a = -1;
        this.f1709b = false;
        this.f1710c = 0;
        this.f1711d = true;
        super.setVisibility(8);
        a(null);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1708a = -1;
        this.f1709b = false;
        this.f1710c = 0;
        this.f1711d = true;
        super.setVisibility(8);
        a(attributeSet);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1708a = -1;
        this.f1709b = false;
        this.f1710c = 0;
        this.f1711d = true;
        super.setVisibility(8);
        a(attributeSet);
    }

    public final void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_ReactiveGuide);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_ReactiveGuide_reactiveGuide_valueId) {
                    this.f1708a = obtainStyledAttributes.getResourceId(index, this.f1708a);
                } else if (index == t.ConstraintLayout_ReactiveGuide_reactiveGuide_animateChange) {
                    this.f1709b = obtainStyledAttributes.getBoolean(index, this.f1709b);
                } else if (index == t.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToConstraintSet) {
                    this.f1710c = obtainStyledAttributes.getResourceId(index, this.f1710c);
                } else if (index == t.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToAllConstraintSets) {
                    this.f1711d = obtainStyledAttributes.getBoolean(index, this.f1711d);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.f1708a != -1) {
            ConstraintLayout.getSharedValues().a(this.f1708a, this);
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    public int getApplyToConstraintSetId() {
        return this.f1710c;
    }

    public int getAttributeId() {
        return this.f1708a;
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void setAnimateChange(boolean z6) {
        this.f1709b = z6;
    }

    public void setApplyToConstraintSetId(int i7) {
        this.f1710c = i7;
    }

    public void setAttributeId(int i7) {
        HashSet hashSet;
        v sharedValues = ConstraintLayout.getSharedValues();
        int i8 = this.f1708a;
        if (i8 != -1 && (hashSet = (HashSet) sharedValues.f184a.get(Integer.valueOf(i8))) != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                u uVar = (u) weakReference.get();
                if (uVar == null || uVar == this) {
                    arrayList.add(weakReference);
                }
            }
            hashSet.removeAll(arrayList);
        }
        this.f1708a = i7;
        if (i7 != -1) {
            sharedValues.a(i7, this);
        }
    }

    public void setGuidelineBegin(int i7) {
        f fVar = (f) getLayoutParams();
        fVar.f11a = i7;
        setLayoutParams(fVar);
    }

    public void setGuidelineEnd(int i7) {
        f fVar = (f) getLayoutParams();
        fVar.f13b = i7;
        setLayoutParams(fVar);
    }

    public void setGuidelinePercent(float f7) {
        f fVar = (f) getLayoutParams();
        fVar.f15c = f7;
        setLayoutParams(fVar);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
    }
}
