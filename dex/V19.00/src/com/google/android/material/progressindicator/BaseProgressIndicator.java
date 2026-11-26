package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.internal.e0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import l3.a;
import l3.b;
import l3.c;
import l3.d;
import l3.j;
import l3.n;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class BaseProgressIndicator<S extends d> extends ProgressBar {
    public static final int HIDE_INWARD = 2;
    public static final int HIDE_NONE = 0;
    public static final int HIDE_OUTWARD = 1;
    public static final int SHOW_INWARD = 2;
    public static final int SHOW_NONE = 0;
    public static final int SHOW_OUTWARD = 1;

    /* renamed from: m, reason: collision with root package name */
    public static final int f4022m = l.Widget_MaterialComponents_ProgressIndicator;

    /* renamed from: a, reason: collision with root package name */
    public final d f4023a;

    /* renamed from: b, reason: collision with root package name */
    public int f4024b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f4025c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f4026d;

    /* renamed from: e, reason: collision with root package name */
    public final int f4027e;

    /* renamed from: f, reason: collision with root package name */
    public a f4028f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f4029g;

    /* renamed from: h, reason: collision with root package name */
    public int f4030h;

    /* renamed from: i, reason: collision with root package name */
    public final b f4031i;

    /* renamed from: j, reason: collision with root package name */
    public final b f4032j;

    /* renamed from: k, reason: collision with root package name */
    public final c f4033k;

    /* renamed from: l, reason: collision with root package name */
    public final c f4034l;

    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(t3.a.a(context, attributeSet, i7, f4022m), attributeSet, i7);
        this.f4029g = false;
        this.f4030h = 4;
        this.f4031i = new b(this, 0);
        this.f4032j = new b(this, 1);
        this.f4033k = new c(0, this);
        this.f4034l = new c(1, this);
        Context context2 = getContext();
        this.f4023a = a(context2, attributeSet);
        TypedArray d7 = e0.d(context2, attributeSet, m.BaseProgressIndicator, i7, i8, new int[0]);
        d7.getInt(m.BaseProgressIndicator_showDelay, -1);
        this.f4027e = Math.min(d7.getInt(m.BaseProgressIndicator_minHideDelay, -1), 1000);
        d7.recycle();
        this.f4028f = new a();
        this.f4026d = true;
    }

    private l3.m getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().f7392l;
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().f7372l;
    }

    public abstract d a(Context context, AttributeSet attributeSet);

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b() {
        /*
            r2 = this;
            int r0 = p0.d1.OVER_SCROLL_ALWAYS
            boolean r0 = p0.o0.b(r2)
            if (r0 == 0) goto L2c
            int r0 = r2.getWindowVisibility()
            if (r0 != 0) goto L2c
            r0 = r2
        Lf:
            int r1 = r0.getVisibility()
            if (r1 == 0) goto L16
            goto L2c
        L16:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L23
            int r0 = r2.getWindowVisibility()
            if (r0 != 0) goto L2c
            goto L27
        L23:
            boolean r1 = r0 instanceof android.view.View
            if (r1 != 0) goto L29
        L27:
            r0 = 1
            goto L2d
        L29:
            android.view.View r0 = (android.view.View) r0
            goto Lf
        L2c:
            r0 = 0
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.progressindicator.BaseProgressIndicator.b():boolean");
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.f4023a.f7348f;
    }

    @Override // android.widget.ProgressBar
    public n getIndeterminateDrawable() {
        return (n) super.getIndeterminateDrawable();
    }

    public int[] getIndicatorColor() {
        return this.f4023a.f7345c;
    }

    @Override // android.widget.ProgressBar
    public j getProgressDrawable() {
        return (j) super.getProgressDrawable();
    }

    public int getShowAnimationBehavior() {
        return this.f4023a.f7347e;
    }

    public int getTrackColor() {
        return this.f4023a.f7346d;
    }

    public int getTrackCornerRadius() {
        return this.f4023a.f7344b;
    }

    public int getTrackThickness() {
        return this.f4023a.f7343a;
    }

    @Override // android.view.View
    public final void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getProgressDrawable() != null && getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().f7393m.p(this.f4033k);
        }
        j progressDrawable = getProgressDrawable();
        c cVar = this.f4034l;
        if (progressDrawable != null) {
            j progressDrawable2 = getProgressDrawable();
            if (progressDrawable2.f7385f == null) {
                progressDrawable2.f7385f = new ArrayList();
            }
            if (!progressDrawable2.f7385f.contains(cVar)) {
                progressDrawable2.f7385f.add(cVar);
            }
        }
        if (getIndeterminateDrawable() != null) {
            n indeterminateDrawable = getIndeterminateDrawable();
            if (indeterminateDrawable.f7385f == null) {
                indeterminateDrawable.f7385f = new ArrayList();
            }
            if (!indeterminateDrawable.f7385f.contains(cVar)) {
                indeterminateDrawable.f7385f.add(cVar);
            }
        }
        if (b()) {
            if (this.f4027e > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.f4032j);
        removeCallbacks(this.f4031i);
        ((l3.l) getCurrentDrawable()).e(false, false, false);
        n indeterminateDrawable = getIndeterminateDrawable();
        c cVar = this.f4034l;
        if (indeterminateDrawable != null) {
            getIndeterminateDrawable().g(cVar);
            getIndeterminateDrawable().f7393m.u();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().g(cVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        try {
            int save = canvas.save();
            if (getPaddingLeft() == 0) {
                if (getPaddingTop() != 0) {
                }
                if (getPaddingRight() == 0 || getPaddingBottom() != 0) {
                    canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
                }
                getCurrentDrawable().draw(canvas);
                canvas.restoreToCount(save);
            }
            canvas.translate(getPaddingLeft(), getPaddingTop());
            if (getPaddingRight() == 0) {
            }
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
            getCurrentDrawable().draw(canvas);
            canvas.restoreToCount(save);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i7, int i8) {
        try {
            l3.m currentDrawingDelegate = getCurrentDrawingDelegate();
            if (currentDrawingDelegate == null) {
                return;
            }
            setMeasuredDimension(currentDrawingDelegate.e() < 0 ? View.getDefaultSize(getSuggestedMinimumWidth(), i7) : currentDrawingDelegate.e() + getPaddingLeft() + getPaddingRight(), currentDrawingDelegate.d() < 0 ? View.getDefaultSize(getSuggestedMinimumHeight(), i8) : currentDrawingDelegate.d() + getPaddingTop() + getPaddingBottom());
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i7) {
        super.onVisibilityChanged(view, i7);
        boolean z6 = i7 == 0;
        if (this.f4026d) {
            ((l3.l) getCurrentDrawable()).e(b(), false, z6);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
        if (this.f4026d) {
            ((l3.l) getCurrentDrawable()).e(b(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(a aVar) {
        this.f4028f = aVar;
        if (getProgressDrawable() != null) {
            getProgressDrawable().f7382c = aVar;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().f7382c = aVar;
        }
    }

    public void setHideAnimationBehavior(int i7) {
        this.f4023a.f7348f = i7;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z6) {
        try {
            if (z6 == isIndeterminate()) {
                return;
            }
            l3.l lVar = (l3.l) getCurrentDrawable();
            if (lVar != null) {
                lVar.e(false, false, false);
            }
            super.setIndeterminate(z6);
            l3.l lVar2 = (l3.l) getCurrentDrawable();
            if (lVar2 != null) {
                lVar2.e(b(), false, false);
            }
            if ((lVar2 instanceof n) && b()) {
                ((n) lVar2).f7393m.s();
            }
            this.f4029g = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else {
            if (!(drawable instanceof n)) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            ((l3.l) drawable).e(false, false, false);
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{b3.a.c(getContext(), q2.c.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.f4023a.f7345c = iArr;
        getIndeterminateDrawable().f7393m.o();
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i7) {
        if (isIndeterminate()) {
            return;
        }
        setProgressCompat(i7, false);
    }

    public void setProgressCompat(int i7, boolean z6) {
        if (!isIndeterminate()) {
            super.setProgress(i7);
            if (getProgressDrawable() == null || z6) {
                return;
            }
            getProgressDrawable().jumpToCurrentState();
            return;
        }
        if (getProgressDrawable() != null) {
            this.f4024b = i7;
            this.f4025c = z6;
            this.f4029g = true;
            if (getIndeterminateDrawable().isVisible()) {
                a aVar = this.f4028f;
                ContentResolver contentResolver = getContext().getContentResolver();
                aVar.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != CropImageView.DEFAULT_ASPECT_RATIO) {
                    getIndeterminateDrawable().f7393m.q();
                    return;
                }
            }
            this.f4033k.a(getIndeterminateDrawable());
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else {
            if (!(drawable instanceof j)) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            j jVar = (j) drawable;
            jVar.e(false, false, false);
            super.setProgressDrawable(jVar);
            jVar.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
        }
    }

    public void setShowAnimationBehavior(int i7) {
        this.f4023a.f7347e = i7;
        invalidate();
    }

    public void setTrackColor(int i7) {
        d dVar = this.f4023a;
        if (dVar.f7346d != i7) {
            dVar.f7346d = i7;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i7) {
        d dVar = this.f4023a;
        if (dVar.f7344b != i7) {
            dVar.f7344b = Math.min(i7, dVar.f7343a / 2);
        }
    }

    public void setTrackThickness(int i7) {
        d dVar = this.f4023a;
        if (dVar.f7343a != i7) {
            dVar.f7343a = i7;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i7) {
        if (i7 != 0 && i7 != 4 && i7 != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.f4030h = i7;
    }
}
