package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatSeekBar extends SeekBar {

    /* renamed from: a, reason: collision with root package name */
    public final g0 f1040a;

    public AppCompatSeekBar(Context context) {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        l3.a(this, getContext());
        g0 g0Var = new g0(this);
        this.f1040a = g0Var;
        g0Var.a(attributeSet, i7);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        g0 g0Var = this.f1040a;
        Drawable drawable = g0Var.f1246e;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        SeekBar seekBar = g0Var.f1245d;
        if (drawable.setState(seekBar.getDrawableState())) {
            seekBar.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1040a.f1246e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1040a.d(canvas);
    }
}
