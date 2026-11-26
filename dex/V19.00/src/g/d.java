package g;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.view.CropImageView;
import f.i;
import f.j;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends Drawable {
    public static final int ARROW_DIRECTION_END = 3;
    public static final int ARROW_DIRECTION_LEFT = 0;
    public static final int ARROW_DIRECTION_RIGHT = 1;
    public static final int ARROW_DIRECTION_START = 2;

    /* renamed from: m, reason: collision with root package name */
    public static final float f6410m = (float) Math.toRadians(45.0d);

    /* renamed from: a, reason: collision with root package name */
    public final Paint f6411a;

    /* renamed from: b, reason: collision with root package name */
    public final float f6412b;

    /* renamed from: c, reason: collision with root package name */
    public final float f6413c;

    /* renamed from: d, reason: collision with root package name */
    public final float f6414d;

    /* renamed from: e, reason: collision with root package name */
    public final float f6415e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f6416f;

    /* renamed from: g, reason: collision with root package name */
    public final Path f6417g;

    /* renamed from: h, reason: collision with root package name */
    public final int f6418h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f6419i;

    /* renamed from: j, reason: collision with root package name */
    public float f6420j;

    /* renamed from: k, reason: collision with root package name */
    public final float f6421k;

    /* renamed from: l, reason: collision with root package name */
    public final int f6422l;

    public d(Context context) {
        Paint paint = new Paint();
        this.f6411a = paint;
        this.f6417g = new Path();
        this.f6419i = false;
        this.f6422l = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, j.DrawerArrowToggle, f.a.drawerArrowStyle, i.Base_Widget_AppCompat_DrawerArrowToggle);
        int color = obtainStyledAttributes.getColor(j.DrawerArrowToggle_color, 0);
        if (color != paint.getColor()) {
            paint.setColor(color);
            invalidateSelf();
        }
        float dimension = obtainStyledAttributes.getDimension(j.DrawerArrowToggle_thickness, CropImageView.DEFAULT_ASPECT_RATIO);
        if (paint.getStrokeWidth() != dimension) {
            paint.setStrokeWidth(dimension);
            this.f6421k = (float) (Math.cos(f6410m) * (dimension / 2.0f));
            invalidateSelf();
        }
        boolean z6 = obtainStyledAttributes.getBoolean(j.DrawerArrowToggle_spinBars, true);
        if (this.f6416f != z6) {
            this.f6416f = z6;
            invalidateSelf();
        }
        float round = Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_gapBetweenBars, CropImageView.DEFAULT_ASPECT_RATIO));
        if (round != this.f6415e) {
            this.f6415e = round;
            invalidateSelf();
        }
        this.f6418h = obtainStyledAttributes.getDimensionPixelSize(j.DrawerArrowToggle_drawableSize, 0);
        this.f6413c = Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_barLength, CropImageView.DEFAULT_ASPECT_RATIO));
        this.f6412b = Math.round(obtainStyledAttributes.getDimension(j.DrawerArrowToggle_arrowHeadLength, CropImageView.DEFAULT_ASPECT_RATIO));
        this.f6414d = obtainStyledAttributes.getDimension(j.DrawerArrowToggle_arrowShaftLength, CropImageView.DEFAULT_ASPECT_RATIO);
        obtainStyledAttributes.recycle();
    }

    public static float a(float f7, float f8, float f9) {
        return a.a.a(f8, f7, f9, f7);
    }

    public final void b(float f7) {
        if (this.f6420j != f7) {
            this.f6420j = f7;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        boolean z6 = false;
        int i7 = this.f6422l;
        if (i7 != 0 && (i7 == 1 || (i7 == 3 ? j0.c.a(this) == 0 : j0.c.a(this) == 1))) {
            z6 = true;
        }
        float f7 = this.f6412b;
        float sqrt = (float) Math.sqrt(f7 * f7 * 2.0f);
        float f8 = this.f6420j;
        float f9 = this.f6413c;
        float a7 = a(f9, sqrt, f8);
        float a8 = a(f9, this.f6414d, this.f6420j);
        float round = Math.round(a(CropImageView.DEFAULT_ASPECT_RATIO, this.f6421k, this.f6420j));
        float a9 = a(CropImageView.DEFAULT_ASPECT_RATIO, f6410m, this.f6420j);
        float a10 = a(z6 ? 0.0f : -180.0f, z6 ? 180.0f : 0.0f, this.f6420j);
        double d7 = a7;
        double d8 = a9;
        float round2 = Math.round(Math.cos(d8) * d7);
        float round3 = Math.round(Math.sin(d8) * d7);
        Path path = this.f6417g;
        path.rewind();
        float f10 = this.f6415e;
        Paint paint = this.f6411a;
        float a11 = a(paint.getStrokeWidth() + f10, -this.f6421k, this.f6420j);
        float f11 = (-a8) / 2.0f;
        path.moveTo(f11 + round, CropImageView.DEFAULT_ASPECT_RATIO);
        path.rLineTo(a8 - (round * 2.0f), CropImageView.DEFAULT_ASPECT_RATIO);
        path.moveTo(f11, a11);
        path.rLineTo(round2, round3);
        path.moveTo(f11, -a11);
        path.rLineTo(round2, -round3);
        path.close();
        canvas.save();
        float strokeWidth = paint.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), (strokeWidth * 1.5f) + this.f6415e + ((((int) (height - (2.0f * r8))) / 4) * 2));
        if (this.f6416f) {
            canvas.rotate(a10 * (this.f6419i ^ z6 ? -1 : 1));
        } else if (z6) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f6418h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f6418h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        Paint paint = this.f6411a;
        if (i7 != paint.getAlpha()) {
            paint.setAlpha(i7);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f6411a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
