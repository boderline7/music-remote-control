package p3;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v extends x {

    /* renamed from: h, reason: collision with root package name */
    public static final RectF f8332h = new RectF();

    /* renamed from: b, reason: collision with root package name */
    public final float f8333b;

    /* renamed from: c, reason: collision with root package name */
    public final float f8334c;

    /* renamed from: d, reason: collision with root package name */
    public final float f8335d;

    /* renamed from: e, reason: collision with root package name */
    public final float f8336e;

    /* renamed from: f, reason: collision with root package name */
    public float f8337f;

    /* renamed from: g, reason: collision with root package name */
    public float f8338g;

    public v(float f7, float f8, float f9, float f10) {
        this.f8333b = f7;
        this.f8334c = f8;
        this.f8335d = f9;
        this.f8336e = f10;
    }

    @Override // p3.x
    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f8341a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        RectF rectF = f8332h;
        rectF.set(this.f8333b, this.f8334c, this.f8335d, this.f8336e);
        path.arcTo(rectF, this.f8337f, this.f8338g, false);
        path.transform(matrix);
    }
}
