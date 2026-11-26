package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.customview.view.AbsSavedState;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f3760g = {R.attr.state_checked};

    /* renamed from: d, reason: collision with root package name */
    public boolean f3761d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f3762e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f3763f;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new b();

        /* renamed from: b, reason: collision with root package name */
        public boolean f3764b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3764b = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f3764b ? 1 : 0);
        }
    }

    public CheckableImageButton(Context context) {
        this(context, null);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.imageButtonStyle);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3762e = true;
        this.f3763f = true;
        d1.o(this, new q1.h(3, this));
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f3761d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        return this.f3761d ? View.mergeDrawableStates(super.onCreateDrawableState(i7 + 1), f3760g) : super.onCreateDrawableState(i7);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        setChecked(savedState.f3764b);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3764b = this.f3761d;
        return savedState;
    }

    public void setCheckable(boolean z6) {
        if (this.f3762e != z6) {
            this.f3762e = z6;
            sendAccessibilityEvent(0);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (!this.f3762e || this.f3761d == z6) {
            return;
        }
        this.f3761d = z6;
        refreshDrawableState();
        sendAccessibilityEvent(2048);
    }

    public void setPressable(boolean z6) {
        this.f3763f = z6;
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        if (this.f3763f) {
            super.setPressed(z6);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.f3761d);
    }
}
