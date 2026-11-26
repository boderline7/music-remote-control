package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g0 extends e0 {

    /* renamed from: d, reason: collision with root package name */
    public final SeekBar f1245d;

    /* renamed from: e, reason: collision with root package name */
    public Drawable f1246e;

    /* renamed from: f, reason: collision with root package name */
    public ColorStateList f1247f;

    /* renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f1248g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f1249h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f1250i;

    public g0(SeekBar seekBar) {
        super(seekBar);
        this.f1247f = null;
        this.f1248g = null;
        this.f1249h = false;
        this.f1250i = false;
        this.f1245d = seekBar;
    }

    @Override // androidx.appcompat.widget.e0
    public final void a(AttributeSet attributeSet, int i7) {
        super.a(attributeSet, i7);
        SeekBar seekBar = this.f1245d;
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(seekBar.getContext(), attributeSet, f.j.AppCompatSeekBar, i7);
        p0.d1.n(seekBar, seekBar.getContext(), f.j.AppCompatSeekBar, attributeSet, (TypedArray) S.f715c, i7);
        Drawable J = S.J(f.j.AppCompatSeekBar_android_thumb);
        if (J != null) {
            seekBar.setThumb(J);
        }
        Drawable I = S.I(f.j.AppCompatSeekBar_tickMark);
        Drawable drawable = this.f1246e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f1246e = I;
        if (I != null) {
            I.setCallback(seekBar);
            j0.c.b(I, p0.m0.d(seekBar));
            if (I.isStateful()) {
                I.setState(seekBar.getDrawableState());
            }
            c();
        }
        seekBar.invalidate();
        if (S.R(f.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.f1248g = m1.c(S.L(f.j.AppCompatSeekBar_tickMarkTintMode, -1), this.f1248g);
            this.f1250i = true;
        }
        if (S.R(f.j.AppCompatSeekBar_tickMarkTint)) {
            this.f1247f = S.F(f.j.AppCompatSeekBar_tickMarkTint);
            this.f1249h = true;
        }
        S.V();
        c();
    }

    public final void c() {
        Drawable drawable = this.f1246e;
        if (drawable != null) {
            if (this.f1249h || this.f1250i) {
                Drawable mutate = drawable.mutate();
                this.f1246e = mutate;
                if (this.f1249h) {
                    j0.b.h(mutate, this.f1247f);
                }
                if (this.f1250i) {
                    j0.b.i(this.f1246e, this.f1248g);
                }
                if (this.f1246e.isStateful()) {
                    this.f1246e.setState(this.f1245d.getDrawableState());
                }
            }
        }
    }

    public final void d(Canvas canvas) {
        if (this.f1246e != null) {
            int max = this.f1245d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1246e.getIntrinsicWidth();
                int intrinsicHeight = this.f1246e.getIntrinsicHeight();
                int i7 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i8 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1246e.setBounds(-i7, -i8, i7, i8);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int save = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i9 = 0; i9 <= max; i9++) {
                    this.f1246e.draw(canvas);
                    canvas.translate(width, CropImageView.DEFAULT_ASPECT_RATIO);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
