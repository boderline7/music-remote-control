package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.yalantis.ucrop.view.CropImageView;
import g0.g;
import p3.a0;
import p3.j;
import p3.p;
import p3.r;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ShapeableImageView extends AppCompatImageView implements a0 {

    /* renamed from: v, reason: collision with root package name */
    public static final int f3740v = l.Widget_MaterialComponents_ShapeableImageView;

    /* renamed from: d, reason: collision with root package name */
    public final r f3741d;

    /* renamed from: e, reason: collision with root package name */
    public final RectF f3742e;

    /* renamed from: f, reason: collision with root package name */
    public final RectF f3743f;

    /* renamed from: g, reason: collision with root package name */
    public final Paint f3744g;

    /* renamed from: h, reason: collision with root package name */
    public final Paint f3745h;

    /* renamed from: i, reason: collision with root package name */
    public final Path f3746i;

    /* renamed from: j, reason: collision with root package name */
    public ColorStateList f3747j;

    /* renamed from: k, reason: collision with root package name */
    public j f3748k;

    /* renamed from: l, reason: collision with root package name */
    public p f3749l;

    /* renamed from: m, reason: collision with root package name */
    public float f3750m;

    /* renamed from: n, reason: collision with root package name */
    public final Path f3751n;

    /* renamed from: o, reason: collision with root package name */
    public int f3752o;

    /* renamed from: p, reason: collision with root package name */
    public int f3753p;

    /* renamed from: q, reason: collision with root package name */
    public int f3754q;

    /* renamed from: r, reason: collision with root package name */
    public int f3755r;

    /* renamed from: s, reason: collision with root package name */
    public int f3756s;

    /* renamed from: t, reason: collision with root package name */
    public int f3757t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f3758u;

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ShapeableImageView(android.content.Context r7, android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r0 = com.google.android.material.imageview.ShapeableImageView.f3740v
            android.content.Context r7 = t3.a.a(r7, r8, r9, r0)
            r6.<init>(r7, r8, r9)
            p3.r r7 = p3.q.f8313a
            r6.f3741d = r7
            android.graphics.Path r7 = new android.graphics.Path
            r7.<init>()
            r6.f3746i = r7
            r7 = 0
            r6.f3758u = r7
            android.content.Context r1 = r6.getContext()
            android.graphics.Paint r2 = new android.graphics.Paint
            r2.<init>()
            r6.f3745h = r2
            r3 = 1
            r2.setAntiAlias(r3)
            r4 = -1
            r2.setColor(r4)
            android.graphics.PorterDuffXfermode r4 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.DST_OUT
            r4.<init>(r5)
            r2.setXfermode(r4)
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.f3742e = r2
            android.graphics.RectF r2 = new android.graphics.RectF
            r2.<init>()
            r6.f3743f = r2
            android.graphics.Path r2 = new android.graphics.Path
            r2.<init>()
            r6.f3751n = r2
            int[] r2 = q2.m.ShapeableImageView
            android.content.res.TypedArray r2 = r1.obtainStyledAttributes(r8, r2, r9, r0)
            r4 = 2
            r5 = 0
            r6.setLayerType(r4, r5)
            int r4 = q2.m.ShapeableImageView_strokeColor
            android.content.res.ColorStateList r4 = l6.f.w(r1, r2, r4)
            r6.f3747j = r4
            int r4 = q2.m.ShapeableImageView_strokeWidth
            int r4 = r2.getDimensionPixelSize(r4, r7)
            float r4 = (float) r4
            r6.f3750m = r4
            int r4 = q2.m.ShapeableImageView_contentPadding
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.f3752o = r7
            r6.f3753p = r7
            r6.f3754q = r7
            r6.f3755r = r7
            int r4 = q2.m.ShapeableImageView_contentPaddingLeft
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.f3752o = r4
            int r4 = q2.m.ShapeableImageView_contentPaddingTop
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.f3753p = r4
            int r4 = q2.m.ShapeableImageView_contentPaddingRight
            int r4 = r2.getDimensionPixelSize(r4, r7)
            r6.f3754q = r4
            int r4 = q2.m.ShapeableImageView_contentPaddingBottom
            int r7 = r2.getDimensionPixelSize(r4, r7)
            r6.f3755r = r7
            int r7 = q2.m.ShapeableImageView_contentPaddingStart
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.f3756s = r7
            int r7 = q2.m.ShapeableImageView_contentPaddingEnd
            int r7 = r2.getDimensionPixelSize(r7, r4)
            r6.f3757t = r7
            r2.recycle()
            android.graphics.Paint r7 = new android.graphics.Paint
            r7.<init>()
            r6.f3744g = r7
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.STROKE
            r7.setStyle(r2)
            r7.setAntiAlias(r3)
            p3.n r7 = p3.p.c(r1, r8, r9, r0)
            p3.p r7 = r7.a()
            r6.f3749l = r7
            h3.a r7 = new h3.a
            r7.<init>(r6)
            r6.setOutlineProvider(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.imageview.ShapeableImageView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final boolean c() {
        return getLayoutDirection() == 1;
    }

    public final void d(int i7, int i8) {
        RectF rectF = this.f3742e;
        rectF.set(getPaddingLeft(), getPaddingTop(), i7 - getPaddingRight(), i8 - getPaddingBottom());
        r rVar = this.f3741d;
        p pVar = this.f3749l;
        Path path = this.f3746i;
        rVar.a(pVar, 1.0f, rectF, null, path);
        Path path2 = this.f3751n;
        path2.rewind();
        path2.addPath(path);
        RectF rectF2 = this.f3743f;
        rectF2.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7, i8);
        path2.addRect(rectF2, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.f3755r;
    }

    public final int getContentPaddingEnd() {
        int i7 = this.f3757t;
        return i7 != Integer.MIN_VALUE ? i7 : c() ? this.f3752o : this.f3754q;
    }

    public int getContentPaddingLeft() {
        int i7;
        int i8;
        if (this.f3756s != Integer.MIN_VALUE || this.f3757t != Integer.MIN_VALUE) {
            if (c() && (i8 = this.f3757t) != Integer.MIN_VALUE) {
                return i8;
            }
            if (!c() && (i7 = this.f3756s) != Integer.MIN_VALUE) {
                return i7;
            }
        }
        return this.f3752o;
    }

    public int getContentPaddingRight() {
        int i7;
        int i8;
        if (this.f3756s != Integer.MIN_VALUE || this.f3757t != Integer.MIN_VALUE) {
            if (c() && (i8 = this.f3756s) != Integer.MIN_VALUE) {
                return i8;
            }
            if (!c() && (i7 = this.f3757t) != Integer.MIN_VALUE) {
                return i7;
            }
        }
        return this.f3754q;
    }

    public final int getContentPaddingStart() {
        int i7 = this.f3756s;
        return i7 != Integer.MIN_VALUE ? i7 : c() ? this.f3754q : this.f3752o;
    }

    public int getContentPaddingTop() {
        return this.f3753p;
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    public p getShapeAppearanceModel() {
        return this.f3749l;
    }

    public ColorStateList getStrokeColor() {
        return this.f3747j;
    }

    public float getStrokeWidth() {
        return this.f3750m;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.f3751n, this.f3745h);
        if (this.f3747j == null) {
            return;
        }
        Paint paint = this.f3744g;
        paint.setStrokeWidth(this.f3750m);
        int colorForState = this.f3747j.getColorForState(getDrawableState(), this.f3747j.getDefaultColor());
        if (this.f3750m <= CropImageView.DEFAULT_ASPECT_RATIO || colorForState == 0) {
            return;
        }
        paint.setColor(colorForState);
        canvas.drawPath(this.f3746i, paint);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (!this.f3758u && isLayoutDirectionResolved()) {
            this.f3758u = true;
            if (!isPaddingRelative() && this.f3756s == Integer.MIN_VALUE && this.f3757t == Integer.MIN_VALUE) {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            } else {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        d(i7, i8);
    }

    public void setContentPadding(int i7, int i8, int i9, int i10) {
        this.f3756s = Integer.MIN_VALUE;
        this.f3757t = Integer.MIN_VALUE;
        super.setPadding((super.getPaddingLeft() - this.f3752o) + i7, (super.getPaddingTop() - this.f3753p) + i8, (super.getPaddingRight() - this.f3754q) + i9, (super.getPaddingBottom() - this.f3755r) + i10);
        this.f3752o = i7;
        this.f3753p = i8;
        this.f3754q = i9;
        this.f3755r = i10;
    }

    public void setContentPaddingRelative(int i7, int i8, int i9, int i10) {
        super.setPaddingRelative((super.getPaddingStart() - getContentPaddingStart()) + i7, (super.getPaddingTop() - this.f3753p) + i8, (super.getPaddingEnd() - getContentPaddingEnd()) + i9, (super.getPaddingBottom() - this.f3755r) + i10);
        this.f3752o = c() ? i9 : i7;
        this.f3753p = i8;
        if (!c()) {
            i7 = i9;
        }
        this.f3754q = i7;
        this.f3755r = i10;
    }

    @Override // android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        super.setPadding(getContentPaddingLeft() + i7, getContentPaddingTop() + i8, getContentPaddingRight() + i9, getContentPaddingBottom() + i10);
    }

    @Override // android.view.View
    public void setPaddingRelative(int i7, int i8, int i9, int i10) {
        super.setPaddingRelative(getContentPaddingStart() + i7, getContentPaddingTop() + i8, getContentPaddingEnd() + i9, getContentPaddingBottom() + i10);
    }

    @Override // p3.a0
    public void setShapeAppearanceModel(p pVar) {
        this.f3749l = pVar;
        j jVar = this.f3748k;
        if (jVar != null) {
            jVar.setShapeAppearanceModel(pVar);
        }
        d(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.f3747j = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i7) {
        setStrokeColor(g.b(getContext(), i7));
    }

    public void setStrokeWidth(float f7) {
        if (this.f3750m != f7) {
            this.f3750m = f7;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i7) {
        setStrokeWidth(getResources().getDimensionPixelSize(i7));
    }
}
