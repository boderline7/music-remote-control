package p;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    public float f8079a;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f8080b;

    /* renamed from: c, reason: collision with root package name */
    public final RectF f8081c;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f8082d;

    /* renamed from: e, reason: collision with root package name */
    public float f8083e;

    /* renamed from: h, reason: collision with root package name */
    public ColorStateList f8086h;

    /* renamed from: i, reason: collision with root package name */
    public PorterDuffColorFilter f8087i;

    /* renamed from: j, reason: collision with root package name */
    public ColorStateList f8088j;

    /* renamed from: f, reason: collision with root package name */
    public boolean f8084f = false;

    /* renamed from: g, reason: collision with root package name */
    public boolean f8085g = true;

    /* renamed from: k, reason: collision with root package name */
    public PorterDuff.Mode f8089k = PorterDuff.Mode.SRC_IN;

    public a(float f7, ColorStateList colorStateList) {
        this.f8079a = f7;
        Paint paint = new Paint(5);
        this.f8080b = paint;
        colorStateList = colorStateList == null ? ColorStateList.valueOf(0) : colorStateList;
        this.f8086h = colorStateList;
        paint.setColor(colorStateList.getColorForState(getState(), this.f8086h.getDefaultColor()));
        this.f8081c = new RectF();
        this.f8082d = new Rect();
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public final void b(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        RectF rectF = this.f8081c;
        rectF.set(rect.left, rect.top, rect.right, rect.bottom);
        Rect rect2 = this.f8082d;
        rect2.set(rect);
        if (this.f8084f) {
            rect2.inset((int) Math.ceil(b.a(this.f8083e, this.f8079a, this.f8085g)), (int) Math.ceil(b.b(this.f8083e, this.f8079a, this.f8085g)));
            rectF.set(rect2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean z6;
        Paint paint = this.f8080b;
        if (this.f8087i == null || paint.getColorFilter() != null) {
            z6 = false;
        } else {
            paint.setColorFilter(this.f8087i);
            z6 = true;
        }
        RectF rectF = this.f8081c;
        float f7 = this.f8079a;
        canvas.drawRoundRect(rectF, f7, f7, paint);
        if (z6) {
            paint.setColorFilter(null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        outline.setRoundRect(this.f8082d, this.f8079a);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f8088j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f8086h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        b(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f8086h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        Paint paint = this.f8080b;
        boolean z6 = colorForState != paint.getColor();
        if (z6) {
            paint.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f8088j;
        if (colorStateList2 == null || (mode = this.f8089k) == null) {
            return z6;
        }
        this.f8087i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f8080b.setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f8080b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        this.f8088j = colorStateList;
        this.f8087i = a(colorStateList, this.f8089k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        this.f8089k = mode;
        this.f8087i = a(this.f8088j, mode);
        invalidateSelf();
    }
}
