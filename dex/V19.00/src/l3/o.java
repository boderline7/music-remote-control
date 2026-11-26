package l3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends m {

    /* renamed from: c, reason: collision with root package name */
    public float f7394c;

    /* renamed from: d, reason: collision with root package name */
    public float f7395d;

    /* renamed from: e, reason: collision with root package name */
    public float f7396e;

    /* renamed from: f, reason: collision with root package name */
    public Path f7397f;

    public o(s sVar) {
        super(sVar);
        this.f7394c = 300.0f;
    }

    @Override // l3.m
    public final void a(Canvas canvas, Rect rect, float f7) {
        this.f7394c = rect.width();
        d dVar = this.f7390a;
        float f8 = ((s) dVar).f7343a;
        canvas.translate((rect.width() / 2.0f) + rect.left, Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (rect.height() - ((s) dVar).f7343a) / 2.0f) + (rect.height() / 2.0f) + rect.top);
        if (((s) dVar).f7420i) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.f7391b.d() && ((s) dVar).f7347e == 1) || (this.f7391b.c() && ((s) dVar).f7348f == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.f7391b.d() || this.f7391b.c()) {
            canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, ((f7 - 1.0f) * ((s) dVar).f7343a) / 2.0f);
        }
        float f9 = this.f7394c;
        canvas.clipRect((-f9) / 2.0f, (-f8) / 2.0f, f9 / 2.0f, f8 / 2.0f);
        this.f7395d = ((s) dVar).f7343a * f7;
        this.f7396e = ((s) dVar).f7344b * f7;
    }

    @Override // l3.m
    public final void b(Canvas canvas, Paint paint, float f7, float f8, int i7) {
        if (f7 == f8) {
            return;
        }
        float f9 = this.f7394c;
        float f10 = (-f9) / 2.0f;
        float f11 = ((f7 * f9) + f10) - (this.f7396e * 2.0f);
        float f12 = (f8 * f9) + f10;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i7);
        canvas.save();
        canvas.clipPath(this.f7397f);
        float f13 = this.f7395d;
        RectF rectF = new RectF(f11, (-f13) / 2.0f, f12, f13 / 2.0f);
        float f14 = this.f7396e;
        canvas.drawRoundRect(rectF, f14, f14, paint);
        canvas.restore();
    }

    @Override // l3.m
    public final void c(Canvas canvas, Paint paint) {
        int a7 = b3.a.a(((s) this.f7390a).f7346d, this.f7391b.f7389j);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(a7);
        Path path = new Path();
        this.f7397f = path;
        float f7 = this.f7394c;
        float f8 = this.f7395d;
        RectF rectF = new RectF((-f7) / 2.0f, (-f8) / 2.0f, f7 / 2.0f, f8 / 2.0f);
        float f9 = this.f7396e;
        path.addRoundRect(rectF, f9, f9, Path.Direction.CCW);
        canvas.drawPath(this.f7397f, paint);
    }

    @Override // l3.m
    public final int d() {
        return ((s) this.f7390a).f7343a;
    }

    @Override // l3.m
    public final int e() {
        return -1;
    }
}
