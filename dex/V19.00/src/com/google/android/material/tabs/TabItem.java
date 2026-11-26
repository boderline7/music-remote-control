package com.google.android.material.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import l6.f;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class TabItem extends View {

    /* renamed from: a, reason: collision with root package name */
    public final CharSequence f4208a;

    /* renamed from: b, reason: collision with root package name */
    public final Drawable f4209b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4210c;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.TabItem);
        this.f4208a = obtainStyledAttributes.getText(m.TabItem_android_text);
        int i7 = m.TabItem_android_icon;
        this.f4209b = (!obtainStyledAttributes.hasValue(i7) || (resourceId = obtainStyledAttributes.getResourceId(i7, 0)) == 0) ? obtainStyledAttributes.getDrawable(i7) : f.A(context, resourceId);
        this.f4210c = obtainStyledAttributes.getResourceId(m.TabItem_android_layout, 0);
        obtainStyledAttributes.recycle();
    }
}
