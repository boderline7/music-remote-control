package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import l6.f;
import q2.c;
import q2.m;
import t3.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context) {
        this(context, null);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaterialTextView(Context context, AttributeSet attributeSet, int i7) {
        super(a.a(context, attributeSet, i7, 0), attributeSet, i7);
        Context context2 = getContext();
        if (f.t0(context2, c.textAppearanceLineHeightEnabled, true)) {
            Resources.Theme theme = context2.getTheme();
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, m.MaterialTextView, i7, 0);
            int[] iArr = {m.MaterialTextView_android_lineHeight, m.MaterialTextView_lineHeight};
            int i8 = -1;
            for (int i9 = 0; i9 < 2 && i8 < 0; i9++) {
                i8 = f.z(context2, obtainStyledAttributes, iArr[i9], -1);
            }
            obtainStyledAttributes.recycle();
            if (i8 != -1) {
                return;
            }
            TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, m.MaterialTextView, i7, 0);
            int resourceId = obtainStyledAttributes2.getResourceId(m.MaterialTextView_android_textAppearance, -1);
            obtainStyledAttributes2.recycle();
            if (resourceId != -1) {
                TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, m.MaterialTextAppearance);
                Context context3 = getContext();
                int[] iArr2 = {m.MaterialTextAppearance_android_lineHeight, m.MaterialTextAppearance_lineHeight};
                int i10 = -1;
                for (int i11 = 0; i11 < 2 && i10 < 0; i11++) {
                    i10 = f.z(context3, obtainStyledAttributes3, iArr2[i11], -1);
                }
                obtainStyledAttributes3.recycle();
                if (i10 >= 0) {
                    setLineHeight(i10);
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        if (f.t0(context, c.textAppearanceLineHeightEnabled, true)) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i7, m.MaterialTextAppearance);
            Context context2 = getContext();
            int[] iArr = {m.MaterialTextAppearance_android_lineHeight, m.MaterialTextAppearance_lineHeight};
            int i8 = -1;
            for (int i9 = 0; i9 < 2 && i8 < 0; i9++) {
                i8 = f.z(context2, obtainStyledAttributes, iArr[i9], -1);
            }
            obtainStyledAttributes.recycle();
            if (i8 >= 0) {
                setLineHeight(i8);
            }
        }
    }
}
