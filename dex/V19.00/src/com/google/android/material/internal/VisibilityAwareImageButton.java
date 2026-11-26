package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class VisibilityAwareImageButton extends ImageButton {

    /* renamed from: a, reason: collision with root package name */
    public int f3790a;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3790a = getVisibility();
    }

    public final void a(int i7, boolean z6) {
        super.setVisibility(i7);
        if (z6) {
            this.f3790a = i7;
        }
    }

    public final int getUserSetVisibility() {
        return this.f3790a;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i7) {
        a(i7, true);
    }
}
