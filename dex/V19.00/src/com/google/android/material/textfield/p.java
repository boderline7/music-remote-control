package com.google.android.material.textfield;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p extends ArrayAdapter {

    /* renamed from: a, reason: collision with root package name */
    public ColorStateList f4415a;

    /* renamed from: b, reason: collision with root package name */
    public ColorStateList f4416b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MaterialAutoCompleteTextView f4417c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(MaterialAutoCompleteTextView materialAutoCompleteTextView, Context context, int i7, String[] strArr) {
        super(context, i7, strArr);
        this.f4417c = materialAutoCompleteTextView;
        a();
    }

    public final void a() {
        ColorStateList colorStateList;
        MaterialAutoCompleteTextView materialAutoCompleteTextView = this.f4417c;
        ColorStateList colorStateList2 = materialAutoCompleteTextView.f4266l;
        ColorStateList colorStateList3 = null;
        if (colorStateList2 != null) {
            int[] iArr = {R.attr.state_pressed};
            colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList2.getColorForState(iArr, 0), 0});
        } else {
            colorStateList = null;
        }
        this.f4416b = colorStateList;
        if (materialAutoCompleteTextView.f4265k != 0 && materialAutoCompleteTextView.f4266l != null) {
            int[] iArr2 = {R.attr.state_hovered, -16842919};
            int[] iArr3 = {R.attr.state_selected, -16842919};
            colorStateList3 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{i0.a.c(materialAutoCompleteTextView.f4266l.getColorForState(iArr3, 0), materialAutoCompleteTextView.f4265k), i0.a.c(materialAutoCompleteTextView.f4266l.getColorForState(iArr2, 0), materialAutoCompleteTextView.f4265k), materialAutoCompleteTextView.f4265k});
        }
        this.f4415a = colorStateList3;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i7, view, viewGroup);
        if (view2 instanceof TextView) {
            TextView textView = (TextView) view2;
            MaterialAutoCompleteTextView materialAutoCompleteTextView = this.f4417c;
            Drawable drawable = null;
            if (materialAutoCompleteTextView.getText().toString().contentEquals(textView.getText()) && materialAutoCompleteTextView.f4265k != 0) {
                ColorDrawable colorDrawable = new ColorDrawable(materialAutoCompleteTextView.f4265k);
                if (this.f4416b != null) {
                    j0.b.h(colorDrawable, this.f4415a);
                    drawable = new RippleDrawable(this.f4416b, colorDrawable, null);
                } else {
                    drawable = colorDrawable;
                }
            }
            int i8 = d1.OVER_SCROLL_ALWAYS;
            l0.q(textView, drawable);
        }
        return view2;
    }
}
