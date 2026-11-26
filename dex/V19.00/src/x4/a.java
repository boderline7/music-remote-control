package x4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends Drawable {

    /* renamed from: b, reason: collision with root package name */
    public final Bitmap f9630b;

    /* renamed from: d, reason: collision with root package name */
    public final int f9632d;

    /* renamed from: e, reason: collision with root package name */
    public final int f9633e;

    /* renamed from: a, reason: collision with root package name */
    public final Paint f9629a = new Paint(2);

    /* renamed from: c, reason: collision with root package name */
    public int f9631c = 255;

    public a(Bitmap bitmap) {
        this.f9630b = bitmap;
        if (bitmap != null) {
            this.f9632d = bitmap.getWidth();
            this.f9633e = this.f9630b.getHeight();
        } else {
            this.f9633e = 0;
            this.f9632d = 0;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Bitmap bitmap = this.f9630b;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        canvas.drawBitmap(this.f9630b, (Rect) null, getBounds(), this.f9629a);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f9631c;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f9633e;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f9632d;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        return this.f9633e;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        return this.f9632d;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f9631c = i7;
        this.f9629a.setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f9629a.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setFilterBitmap(boolean z6) {
        this.f9629a.setFilterBitmap(z6);
    }
}
