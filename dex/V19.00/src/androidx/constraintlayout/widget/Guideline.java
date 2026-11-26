package androidx.constraintlayout.widget;

import a0.f;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Guideline extends View {

    /* renamed from: a, reason: collision with root package name */
    public boolean f1704a;

    public Guideline(Context context) {
        super(context);
        this.f1704a = true;
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1704a = true;
        super.setVisibility(8);
    }

    public Guideline(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1704a = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z6) {
        this.f1704a = z6;
    }

    public void setGuidelineBegin(int i7) {
        f fVar = (f) getLayoutParams();
        if (this.f1704a && fVar.f11a == i7) {
            return;
        }
        fVar.f11a = i7;
        setLayoutParams(fVar);
    }

    public void setGuidelineEnd(int i7) {
        f fVar = (f) getLayoutParams();
        if (this.f1704a && fVar.f13b == i7) {
            return;
        }
        fVar.f13b = i7;
        setLayoutParams(fVar);
    }

    public void setGuidelinePercent(float f7) {
        f fVar = (f) getLayoutParams();
        if (this.f1704a && fVar.f15c == f7) {
            return;
        }
        fVar.f15c = f7;
        setLayoutParams(fVar);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
    }
}
