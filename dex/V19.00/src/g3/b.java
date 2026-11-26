package g3;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends Drawable {

    /* renamed from: b, reason: collision with root package name */
    public final Paint f6440b;

    /* renamed from: h, reason: collision with root package name */
    public float f6446h;

    /* renamed from: i, reason: collision with root package name */
    public int f6447i;

    /* renamed from: j, reason: collision with root package name */
    public int f6448j;

    /* renamed from: k, reason: collision with root package name */
    public int f6449k;

    /* renamed from: l, reason: collision with root package name */
    public int f6450l;

    /* renamed from: m, reason: collision with root package name */
    public int f6451m;

    /* renamed from: o, reason: collision with root package name */
    public p3.p f6453o;

    /* renamed from: p, reason: collision with root package name */
    public ColorStateList f6454p;

    /* renamed from: a, reason: collision with root package name */
    public final p3.r f6439a = p3.q.f8313a;

    /* renamed from: c, reason: collision with root package name */
    public final Path f6441c = new Path();

    /* renamed from: d, reason: collision with root package name */
    public final Rect f6442d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    public final RectF f6443e = new RectF();

    /* renamed from: f, reason: collision with root package name */
    public final RectF f6444f = new RectF();

    /* renamed from: g, reason: collision with root package name */
    public final o1.e f6445g = new o1.e(this, 0);

    /* renamed from: n, reason: collision with root package name */
    public boolean f6452n = true;

    public b(p3.p pVar) {
        this.f6453o = pVar;
        Paint paint = new Paint(1);
        this.f6440b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        boolean z6 = this.f6452n;
        Paint paint = this.f6440b;
        Rect rect = this.f6442d;
        if (z6) {
            copyBounds(rect);
            float height = this.f6446h / rect.height();
            paint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, rect.top, CropImageView.DEFAULT_ASPECT_RATIO, rect.bottom, new int[]{i0.a.c(this.f6447i, this.f6451m), i0.a.c(this.f6448j, this.f6451m), i0.a.c(i0.a.e(this.f6448j, 0), this.f6451m), i0.a.c(i0.a.e(this.f6450l, 0), this.f6451m), i0.a.c(this.f6450l, this.f6451m), i0.a.c(this.f6449k, this.f6451m)}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, height, 0.5f, 0.5f, 1.0f - height, 1.0f}, Shader.TileMode.CLAMP));
            this.f6452n = false;
        }
        float strokeWidth = paint.getStrokeWidth() / 2.0f;
        copyBounds(rect);
        RectF rectF = this.f6443e;
        rectF.set(rect);
        p3.d dVar = this.f6453o.f8305e;
        RectF rectF2 = this.f6444f;
        rectF2.set(getBounds());
        float min = Math.min(dVar.a(rectF2), rectF.width() / 2.0f);
        p3.p pVar = this.f6453o;
        rectF2.set(getBounds());
        if (pVar.f(rectF2)) {
            rectF.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(rectF, min, min, paint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.f6445g;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.f6446h > CropImageView.DEFAULT_ASPECT_RATIO ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        p3.p pVar = this.f6453o;
        RectF rectF = this.f6444f;
        rectF.set(getBounds());
        if (pVar.f(rectF)) {
            p3.d dVar = this.f6453o.f8305e;
            rectF.set(getBounds());
            outline.setRoundRect(getBounds(), dVar.a(rectF));
            return;
        }
        Rect rect = this.f6442d;
        copyBounds(rect);
        RectF rectF2 = this.f6443e;
        rectF2.set(rect);
        p3.r rVar = this.f6439a;
        p3.p pVar2 = this.f6453o;
        Path path = this.f6441c;
        rVar.a(pVar2, 1.0f, rectF2, null, path);
        d3.a.e(outline, path);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        p3.p pVar = this.f6453o;
        RectF rectF = this.f6444f;
        rectF.set(getBounds());
        if (!pVar.f(rectF)) {
            return true;
        }
        int round = Math.round(this.f6446h);
        rect.set(round, round, round, round);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList = this.f6454p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        this.f6452n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.f6454p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.f6451m)) != this.f6451m) {
            this.f6452n = true;
            this.f6451m = colorForState;
        }
        if (this.f6452n) {
            invalidateSelf();
        }
        return this.f6452n;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f6440b.setAlpha(i7);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f6440b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
