package p3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import com.yalantis.ucrop.view.CropImageView;
import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class j extends Drawable implements a0 {
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;

    /* renamed from: x, reason: collision with root package name */
    public static final Paint f8262x;

    /* renamed from: a, reason: collision with root package name */
    public i f8263a;

    /* renamed from: b, reason: collision with root package name */
    public final y[] f8264b;

    /* renamed from: c, reason: collision with root package name */
    public final y[] f8265c;

    /* renamed from: d, reason: collision with root package name */
    public final BitSet f8266d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f8267e;

    /* renamed from: f, reason: collision with root package name */
    public final Matrix f8268f;

    /* renamed from: g, reason: collision with root package name */
    public final Path f8269g;

    /* renamed from: h, reason: collision with root package name */
    public final Path f8270h;

    /* renamed from: i, reason: collision with root package name */
    public final RectF f8271i;

    /* renamed from: j, reason: collision with root package name */
    public final RectF f8272j;

    /* renamed from: k, reason: collision with root package name */
    public final Region f8273k;

    /* renamed from: l, reason: collision with root package name */
    public final Region f8274l;

    /* renamed from: m, reason: collision with root package name */
    public p f8275m;

    /* renamed from: n, reason: collision with root package name */
    public final Paint f8276n;

    /* renamed from: o, reason: collision with root package name */
    public final Paint f8277o;

    /* renamed from: p, reason: collision with root package name */
    public final o3.a f8278p;

    /* renamed from: q, reason: collision with root package name */
    public final v2.a f8279q;

    /* renamed from: r, reason: collision with root package name */
    public final r f8280r;

    /* renamed from: s, reason: collision with root package name */
    public PorterDuffColorFilter f8281s;

    /* renamed from: t, reason: collision with root package name */
    public PorterDuffColorFilter f8282t;

    /* renamed from: u, reason: collision with root package name */
    public int f8283u;

    /* renamed from: v, reason: collision with root package name */
    public final RectF f8284v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f8285w;

    static {
        Paint paint = new Paint(1);
        f8262x = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public j() {
        this(new p());
    }

    public j(Context context, AttributeSet attributeSet, int i7, int i8) {
        this(p.c(context, attributeSet, i7, i8).a());
    }

    public j(i iVar) {
        this.f8264b = new y[4];
        this.f8265c = new y[4];
        this.f8266d = new BitSet(8);
        this.f8268f = new Matrix();
        this.f8269g = new Path();
        this.f8270h = new Path();
        this.f8271i = new RectF();
        this.f8272j = new RectF();
        this.f8273k = new Region();
        this.f8274l = new Region();
        Paint paint = new Paint(1);
        this.f8276n = paint;
        Paint paint2 = new Paint(1);
        this.f8277o = paint2;
        this.f8278p = new o3.a();
        this.f8280r = Looper.getMainLooper().getThread() == Thread.currentThread() ? q.f8313a : new r();
        this.f8284v = new RectF();
        this.f8285w = true;
        this.f8263a = iVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        w();
        v(getState());
        this.f8279q = new v2.a(11, this);
    }

    public j(p pVar) {
        this(new i(pVar));
    }

    public void a() {
        invalidateSelf();
    }

    public final void b(RectF rectF, Path path) {
        r rVar = this.f8280r;
        i iVar = this.f8263a;
        rVar.a(iVar.f8241a, iVar.f8250j, rectF, this.f8279q, path);
        if (this.f8263a.f8249i != 1.0f) {
            Matrix matrix = this.f8268f;
            matrix.reset();
            float f7 = this.f8263a.f8249i;
            matrix.setScale(f7, f7, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.f8284v, true);
    }

    public final PorterDuffColorFilter c(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z6) {
        PorterDuffColorFilter porterDuffColorFilter;
        if (colorStateList != null && mode != null) {
            int colorForState = colorStateList.getColorForState(getState(), 0);
            if (z6) {
                colorForState = d(colorForState);
            }
            this.f8283u = colorForState;
            return new PorterDuffColorFilter(colorForState, mode);
        }
        if (z6) {
            int color = paint.getColor();
            int d7 = d(color);
            this.f8283u = d7;
            if (d7 != color) {
                porterDuffColorFilter = new PorterDuffColorFilter(d7, PorterDuff.Mode.SRC_IN);
                return porterDuffColorFilter;
            }
        }
        porterDuffColorFilter = null;
        return porterDuffColorFilter;
    }

    public final int d(int i7) {
        i iVar = this.f8263a;
        float f7 = iVar.f8254n + iVar.f8255o + iVar.f8253m;
        e3.a aVar = iVar.f8242b;
        return aVar != null ? aVar.a(f7, i7) : i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b0, code lost:
    
        if (r1 < 29) goto L24;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.j.draw(android.graphics.Canvas):void");
    }

    public final void e(Canvas canvas) {
        this.f8266d.cardinality();
        int i7 = this.f8263a.f8258r;
        Path path = this.f8269g;
        o3.a aVar = this.f8278p;
        if (i7 != 0) {
            canvas.drawPath(path, aVar.f7911a);
        }
        for (int i8 = 0; i8 < 4; i8++) {
            y yVar = this.f8264b[i8];
            int i9 = this.f8263a.f8257q;
            Matrix matrix = y.f8342b;
            yVar.a(matrix, aVar, i9, canvas);
            this.f8265c[i8].a(matrix, aVar, this.f8263a.f8257q, canvas);
        }
        if (this.f8285w) {
            i iVar = this.f8263a;
            int sin = (int) (Math.sin(Math.toRadians(iVar.f8259s)) * iVar.f8258r);
            int i10 = i();
            canvas.translate(-sin, -i10);
            canvas.drawPath(path, f8262x);
            canvas.translate(sin, i10);
        }
    }

    public final void f(Canvas canvas, Paint paint, Path path, p pVar, RectF rectF) {
        if (!pVar.f(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float a7 = pVar.f8306f.a(rectF) * this.f8263a.f8250j;
            canvas.drawRoundRect(rectF, a7, a7, paint);
        }
    }

    public void g(Canvas canvas) {
        Paint paint = this.f8277o;
        Path path = this.f8270h;
        p pVar = this.f8275m;
        RectF rectF = this.f8272j;
        rectF.set(h());
        float strokeWidth = k() ? paint.getStrokeWidth() / 2.0f : CropImageView.DEFAULT_ASPECT_RATIO;
        rectF.inset(strokeWidth, strokeWidth);
        f(canvas, paint, path, pVar, rectF);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f8263a.f8252l;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.f8263a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f8263a.f8256p == 2) {
            return;
        }
        if (m()) {
            outline.setRoundRect(getBounds(), j() * this.f8263a.f8250j);
        } else {
            RectF h7 = h();
            Path path = this.f8269g;
            b(h7, path);
            d3.a.e(outline, path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.f8263a.f8248h;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.f8273k;
        region.set(bounds);
        RectF h7 = h();
        Path path = this.f8269g;
        b(h7, path);
        Region region2 = this.f8274l;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final RectF h() {
        RectF rectF = this.f8271i;
        rectF.set(getBounds());
        return rectF;
    }

    public final int i() {
        i iVar = this.f8263a;
        return (int) (Math.cos(Math.toRadians(iVar.f8259s)) * iVar.f8258r);
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.f8267e = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f8263a.f8246f) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f8263a.f8245e) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f8263a.f8244d) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f8263a.f8243c) != null && colorStateList4.isStateful())));
    }

    public final float j() {
        return this.f8263a.f8241a.f8305e.a(h());
    }

    public final boolean k() {
        Paint.Style style = this.f8263a.f8261u;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f8277o.getStrokeWidth() > CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void l(Context context) {
        this.f8263a.f8242b = new e3.a(context);
        x();
    }

    public final boolean m() {
        return this.f8263a.f8241a.f(h());
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f8263a = new i(this.f8263a);
        return this;
    }

    public final void n(float f7) {
        i iVar = this.f8263a;
        if (iVar.f8254n != f7) {
            iVar.f8254n = f7;
            x();
        }
    }

    public final void o(ColorStateList colorStateList) {
        i iVar = this.f8263a;
        if (iVar.f8243c != colorStateList) {
            iVar.f8243c = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f8267e = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z6 = v(iArr) || w();
        if (z6) {
            invalidateSelf();
        }
        return z6;
    }

    public final void p(float f7) {
        i iVar = this.f8263a;
        if (iVar.f8250j != f7) {
            iVar.f8250j = f7;
            this.f8267e = true;
            invalidateSelf();
        }
    }

    public final void q(Paint.Style style) {
        this.f8263a.f8261u = style;
        super.invalidateSelf();
    }

    public final void r() {
        this.f8278p.a(-12303292);
        this.f8263a.f8260t = false;
        super.invalidateSelf();
    }

    public final void s(int i7) {
        i iVar = this.f8263a;
        if (iVar.f8256p != i7) {
            iVar.f8256p = i7;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        i iVar = this.f8263a;
        if (iVar.f8252l != i7) {
            iVar.f8252l = i7;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f8263a.getClass();
        super.invalidateSelf();
    }

    @Override // p3.a0
    public final void setShapeAppearanceModel(p pVar) {
        this.f8263a.f8241a = pVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i7) {
        setTintList(ColorStateList.valueOf(i7));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f8263a.f8246f = colorStateList;
        w();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        i iVar = this.f8263a;
        if (iVar.f8247g != mode) {
            iVar.f8247g = mode;
            w();
            super.invalidateSelf();
        }
    }

    public final void t(ColorStateList colorStateList) {
        i iVar = this.f8263a;
        if (iVar.f8244d != colorStateList) {
            iVar.f8244d = colorStateList;
            onStateChange(getState());
        }
    }

    public final void u(float f7) {
        this.f8263a.f8251k = f7;
        invalidateSelf();
    }

    public final boolean v(int[] iArr) {
        boolean z6;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.f8263a.f8243c == null || color2 == (colorForState2 = this.f8263a.f8243c.getColorForState(iArr, (color2 = (paint2 = this.f8276n).getColor())))) {
            z6 = false;
        } else {
            paint2.setColor(colorForState2);
            z6 = true;
        }
        if (this.f8263a.f8244d == null || color == (colorForState = this.f8263a.f8244d.getColorForState(iArr, (color = (paint = this.f8277o).getColor())))) {
            return z6;
        }
        paint.setColor(colorForState);
        return true;
    }

    public final boolean w() {
        PorterDuffColorFilter porterDuffColorFilter = this.f8281s;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f8282t;
        i iVar = this.f8263a;
        this.f8281s = c(iVar.f8246f, iVar.f8247g, this.f8276n, true);
        i iVar2 = this.f8263a;
        this.f8282t = c(iVar2.f8245e, iVar2.f8247g, this.f8277o, false);
        i iVar3 = this.f8263a;
        if (iVar3.f8260t) {
            this.f8278p.a(iVar3.f8246f.getColorForState(getState(), 0));
        }
        return (o0.b.a(porterDuffColorFilter, this.f8281s) && o0.b.a(porterDuffColorFilter2, this.f8282t)) ? false : true;
    }

    public final void x() {
        i iVar = this.f8263a;
        float f7 = iVar.f8254n + iVar.f8255o;
        iVar.f8257q = (int) Math.ceil(0.75f * f7);
        this.f8263a.f8258r = (int) Math.ceil(f7 * 0.25f);
        w();
        super.invalidateSelf();
    }
}
