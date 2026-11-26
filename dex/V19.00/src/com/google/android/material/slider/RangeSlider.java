package com.google.android.material.slider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.widget.SeekBar;
import com.google.android.material.internal.e0;
import com.yalantis.ucrop.view.CropImageView;
import g0.g;
import java.util.ArrayList;
import java.util.List;
import p3.j;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class RangeSlider extends c {

    /* renamed from: n0, reason: collision with root package name */
    public float f4147n0;

    /* renamed from: o0, reason: collision with root package name */
    public int f4148o0;

    public static class RangeSliderState extends AbsSavedState {
        public static final Parcelable.Creator<RangeSliderState> CREATOR = new e();

        /* renamed from: a, reason: collision with root package name */
        public float f4149a;

        /* renamed from: b, reason: collision with root package name */
        public int f4150b;

        public RangeSliderState(Parcel parcel) {
            super(parcel.readParcelable(RangeSliderState.class.getClassLoader()));
            this.f4149a = parcel.readFloat();
            this.f4150b = parcel.readInt();
        }

        public RangeSliderState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeFloat(this.f4149a);
            parcel.writeInt(this.f4150b);
        }
    }

    public RangeSlider(Context context) {
        this(context, null);
    }

    public RangeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.sliderStyle);
    }

    public RangeSlider(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        int[] iArr = m.RangeSlider;
        int i8 = c.f4153i0;
        e0.a(context, attributeSet, i7, i8);
        e0.b(context, attributeSet, iArr, i7, i8, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, i8);
        if (obtainStyledAttributes.hasValue(m.RangeSlider_values)) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(obtainStyledAttributes.getResourceId(m.RangeSlider_values, 0));
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < obtainTypedArray.length(); i9++) {
                arrayList.add(Float.valueOf(obtainTypedArray.getFloat(i9, -1.0f)));
            }
            setValues(arrayList);
        }
        this.f4147n0 = obtainStyledAttributes.getDimension(m.RangeSlider_minSeparation, CropImageView.DEFAULT_ASPECT_RATIO);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public int getActiveThumbIndex() {
        return this.L;
    }

    public int getFocusedThumbIndex() {
        return this.M;
    }

    public int getHaloRadius() {
        return this.D;
    }

    public ColorStateList getHaloTintList() {
        return this.V;
    }

    public int getLabelBehavior() {
        return this.f4191z;
    }

    @Override // com.google.android.material.slider.c
    public float getMinSeparation() {
        return this.f4147n0;
    }

    public float getStepSize() {
        return this.N;
    }

    public float getThumbElevation() {
        return this.f4165d0.f8263a.f8254n;
    }

    public int getThumbRadius() {
        return this.C;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.f4165d0.f8263a.f8244d;
    }

    public float getThumbStrokeWidth() {
        return this.f4165d0.f8263a.f8251k;
    }

    public ColorStateList getThumbTintList() {
        return this.f4165d0.f8263a.f8243c;
    }

    public int getTickActiveRadius() {
        return this.Q;
    }

    public ColorStateList getTickActiveTintList() {
        return this.W;
    }

    public int getTickInactiveRadius() {
        return this.R;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.f4159a0;
    }

    public ColorStateList getTickTintList() {
        if (this.f4159a0.equals(this.W)) {
            return this.W;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.f4161b0;
    }

    public int getTrackHeight() {
        return this.A;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.f4163c0;
    }

    public int getTrackSidePadding() {
        return this.B;
    }

    public ColorStateList getTrackTintList() {
        if (this.f4163c0.equals(this.f4161b0)) {
            return this.f4161b0;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.S;
    }

    @Override // com.google.android.material.slider.c
    public float getValueFrom() {
        return this.I;
    }

    @Override // com.google.android.material.slider.c
    public float getValueTo() {
        return this.J;
    }

    @Override // com.google.android.material.slider.c
    public List<Float> getValues() {
        return super.getValues();
    }

    @Override // com.google.android.material.slider.c, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        RangeSliderState rangeSliderState = (RangeSliderState) parcelable;
        super.onRestoreInstanceState(rangeSliderState.getSuperState());
        this.f4147n0 = rangeSliderState.f4149a;
        int i7 = rangeSliderState.f4150b;
        this.f4148o0 = i7;
        setSeparationUnit(i7);
    }

    @Override // com.google.android.material.slider.c, android.view.View
    public final Parcelable onSaveInstanceState() {
        RangeSliderState rangeSliderState = new RangeSliderState(super.onSaveInstanceState());
        rangeSliderState.f4149a = this.f4147n0;
        rangeSliderState.f4150b = this.f4148o0;
        return rangeSliderState;
    }

    public void setCustomThumbDrawable(int i7) {
        setCustomThumbDrawable(getResources().getDrawable(i7));
    }

    public void setCustomThumbDrawable(Drawable drawable) {
        Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
        a(newDrawable);
        this.f4167e0 = newDrawable;
        this.f4169f0.clear();
        postInvalidate();
    }

    @Override // com.google.android.material.slider.c
    public void setCustomThumbDrawablesForValues(int... iArr) {
        super.setCustomThumbDrawablesForValues(iArr);
    }

    @Override // com.google.android.material.slider.c
    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        super.setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // com.google.android.material.slider.c, android.view.View
    public /* bridge */ /* synthetic */ void setEnabled(boolean z6) {
        super.setEnabled(z6);
    }

    @Override // com.google.android.material.slider.c
    public /* bridge */ /* synthetic */ void setFocusedThumbIndex(int i7) {
        super.setFocusedThumbIndex(i7);
    }

    @Override // com.google.android.material.slider.c
    public /* bridge */ /* synthetic */ void setHaloRadius(int i7) {
        super.setHaloRadius(i7);
    }

    public void setHaloRadiusResource(int i7) {
        setHaloRadius(getResources().getDimensionPixelSize(i7));
    }

    @Override // com.google.android.material.slider.c
    public /* bridge */ /* synthetic */ void setHaloTintList(ColorStateList colorStateList) {
        super.setHaloTintList(colorStateList);
    }

    @Override // com.google.android.material.slider.c
    public void setLabelBehavior(int i7) {
        if (this.f4191z != i7) {
            this.f4191z = i7;
            requestLayout();
        }
    }

    public /* bridge */ /* synthetic */ void setLabelFormatter(d dVar) {
    }

    public void setMinSeparation(float f7) {
        this.f4147n0 = f7;
        this.f4148o0 = 0;
        setSeparationUnit(0);
    }

    public void setMinSeparationValue(float f7) {
        this.f4147n0 = f7;
        this.f4148o0 = 1;
        setSeparationUnit(1);
    }

    @Override // com.google.android.material.slider.c
    public /* bridge */ /* synthetic */ void setStepSize(float f7) {
        super.setStepSize(f7);
    }

    @Override // com.google.android.material.slider.c
    public void setThumbElevation(float f7) {
        this.f4165d0.n(f7);
    }

    public void setThumbElevationResource(int i7) {
        setThumbElevation(getResources().getDimension(i7));
    }

    @Override // com.google.android.material.slider.c
    public /* bridge */ /* synthetic */ void setThumbRadius(int i7) {
        super.setThumbRadius(i7);
    }

    public void setThumbRadiusResource(int i7) {
        setThumbRadius(getResources().getDimensionPixelSize(i7));
    }

    @Override // com.google.android.material.slider.c
    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.f4165d0.t(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int i7) {
        if (i7 != 0) {
            setThumbStrokeColor(g.b(getContext(), i7));
        }
    }

    @Override // com.google.android.material.slider.c
    public void setThumbStrokeWidth(float f7) {
        this.f4165d0.u(f7);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int i7) {
        if (i7 != 0) {
            setThumbStrokeWidth(getResources().getDimension(i7));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        j jVar = this.f4165d0;
        if (colorStateList.equals(jVar.f8263a.f8243c)) {
            return;
        }
        jVar.o(colorStateList);
        invalidate();
    }

    @Override // com.google.android.material.slider.c
    public void setTickActiveRadius(int i7) {
        if (this.Q != i7) {
            this.Q = i7;
            this.f4168f.setStrokeWidth(i7 * 2);
            v();
        }
    }

    @Override // com.google.android.material.slider.c
    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.W)) {
            return;
        }
        this.W = colorStateList;
        this.f4168f.setColor(f(colorStateList));
        invalidate();
    }

    @Override // com.google.android.material.slider.c
    public void setTickInactiveRadius(int i7) {
        if (this.R != i7) {
            this.R = i7;
            this.f4166e.setStrokeWidth(i7 * 2);
            v();
        }
    }

    @Override // com.google.android.material.slider.c
    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.f4159a0)) {
            return;
        }
        this.f4159a0 = colorStateList;
        this.f4166e.setColor(f(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z6) {
        if (this.P != z6) {
            this.P = z6;
            postInvalidate();
        }
    }

    @Override // com.google.android.material.slider.c
    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.f4161b0)) {
            return;
        }
        this.f4161b0 = colorStateList;
        this.f4160b.setColor(f(colorStateList));
        invalidate();
    }

    @Override // com.google.android.material.slider.c
    public void setTrackHeight(int i7) {
        if (this.A != i7) {
            this.A = i7;
            this.f4158a.setStrokeWidth(i7);
            this.f4160b.setStrokeWidth(this.A);
            v();
        }
    }

    @Override // com.google.android.material.slider.c
    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.f4163c0)) {
            return;
        }
        this.f4163c0 = colorStateList;
        this.f4158a.setColor(f(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f7) {
        this.I = f7;
        this.U = true;
        postInvalidate();
    }

    public void setValueTo(float f7) {
        this.J = f7;
        this.U = true;
        postInvalidate();
    }

    @Override // com.google.android.material.slider.c
    public void setValues(List<Float> list) {
        super.setValues(list);
    }

    @Override // com.google.android.material.slider.c
    public void setValues(Float... fArr) {
        super.setValues(fArr);
    }
}
