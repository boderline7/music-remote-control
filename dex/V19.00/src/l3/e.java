package l3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends m {

    /* renamed from: c, reason: collision with root package name */
    public int f7349c;

    /* renamed from: d, reason: collision with root package name */
    public float f7350d;

    /* renamed from: e, reason: collision with root package name */
    public float f7351e;

    /* renamed from: f, reason: collision with root package name */
    public float f7352f;

    public e(h hVar) {
        super(hVar);
        this.f7349c = 1;
    }

    @Override // l3.m
    public final void a(Canvas canvas, Rect rect, float f7) {
        float width = rect.width() / g();
        float height = rect.height() / g();
        d dVar = this.f7390a;
        float f8 = (((h) dVar).f7368g / 2.0f) + ((h) dVar).f7369h;
        canvas.translate((f8 * width) + rect.left, (f8 * height) + rect.top);
        canvas.scale(width, height);
        canvas.rotate(-90.0f);
        float f9 = -f8;
        canvas.clipRect(f9, f9, f8, f8);
        this.f7349c = ((h) dVar).f7370i == 0 ? 1 : -1;
        this.f7350d = ((h) dVar).f7343a * f7;
        this.f7351e = ((h) dVar).f7344b * f7;
        this.f7352f = (((h) dVar).f7368g - ((h) dVar).f7343a) / 2.0f;
        if ((this.f7391b.d() && ((h) dVar).f7347e == 2) || (this.f7391b.c() && ((h) dVar).f7348f == 1)) {
            this.f7352f = (((1.0f - f7) * ((h) dVar).f7343a) / 2.0f) + this.f7352f;
        } else if ((this.f7391b.d() && ((h) dVar).f7347e == 1) || (this.f7391b.c() && ((h) dVar).f7348f == 2)) {
            this.f7352f -= ((1.0f - f7) * ((h) dVar).f7343a) / 2.0f;
        }
    }

    @Override // l3.m
    public final void b(Canvas canvas, Paint paint, float f7, float f8, int i7) {
        if (f7 == f8) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i7);
        paint.setStrokeWidth(this.f7350d);
        float f9 = this.f7349c;
        float f10 = f7 * 360.0f * f9;
        float f11 = (f8 >= f7 ? f8 - f7 : (1.0f + f8) - f7) * 360.0f * f9;
        float f12 = this.f7352f;
        float f13 = -f12;
        canvas.drawArc(new RectF(f13, f13, f12, f12), f10, f11, false, paint);
        if (this.f7351e <= CropImageView.DEFAULT_ASPECT_RATIO || Math.abs(f11) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        f(canvas, paint, this.f7350d, this.f7351e, f10);
        f(canvas, paint, this.f7350d, this.f7351e, f10 + f11);
    }

    @Override // l3.m
    public final void c(Canvas canvas, Paint paint) {
        int a7 = b3.a.a(((h) this.f7390a).f7346d, this.f7391b.f7389j);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(a7);
        paint.setStrokeWidth(this.f7350d);
        float f7 = this.f7352f;
        canvas.drawArc(new RectF(-f7, -f7, f7, f7), CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, false, paint);
    }

    @Override // l3.m
    public final int d() {
        return g();
    }

    @Override // l3.m
    public final int e() {
        return g();
    }

    public final void f(Canvas canvas, Paint paint, float f7, float f8, float f9) {
        canvas.save();
        canvas.rotate(f9);
        float f10 = this.f7352f;
        float f11 = f7 / 2.0f;
        canvas.drawRoundRect(new RectF(f10 - f11, f8, f10 + f11, -f8), f8, f8, paint);
        canvas.restore();
    }

    public final int g() {
        d dVar = this.f7390a;
        return (((h) dVar).f7369h * 2) + ((h) dVar).f7368g;
    }
}
