package com.navi.tool.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AlwaysFocusedTextView extends AppCompatTextView {
    public AlwaysFocusedTextView(Context context) {
        super(context);
    }

    public AlwaysFocusedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlwaysFocusedTextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(true, i7, rect);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(true);
    }
}
