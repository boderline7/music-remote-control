package p3;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u extends y {

    /* renamed from: c, reason: collision with root package name */
    public final w f8329c;

    /* renamed from: d, reason: collision with root package name */
    public final float f8330d;

    /* renamed from: e, reason: collision with root package name */
    public final float f8331e;

    public u(w wVar, float f7, float f8) {
        this.f8329c = wVar;
        this.f8330d = f7;
        this.f8331e = f8;
    }

    @Override // p3.y
    public final void a(Matrix matrix, o3.a aVar, int i7, Canvas canvas) {
        w wVar = this.f8329c;
        float f7 = wVar.f8340c;
        float f8 = this.f8331e;
        float f9 = wVar.f8339b;
        float f10 = this.f8330d;
        RectF rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) Math.hypot(f7 - f8, f9 - f10), CropImageView.DEFAULT_ASPECT_RATIO);
        Matrix matrix2 = this.f8343a;
        matrix2.set(matrix);
        matrix2.preTranslate(f10, f8);
        matrix2.preRotate(b());
        aVar.getClass();
        rectF.bottom += i7;
        rectF.offset(CropImageView.DEFAULT_ASPECT_RATIO, -i7);
        int[] iArr = o3.a.f7907i;
        iArr[0] = aVar.f7916f;
        iArr[1] = aVar.f7915e;
        iArr[2] = aVar.f7914d;
        Paint paint = aVar.f7913c;
        float f11 = rectF.left;
        paint.setShader(new LinearGradient(f11, rectF.top, f11, rectF.bottom, iArr, o3.a.f7908j, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix2);
        canvas.drawRect(rectF, paint);
        canvas.restore();
    }

    public final float b() {
        w wVar = this.f8329c;
        return (float) Math.toDegrees(Math.atan((wVar.f8340c - this.f8331e) / (wVar.f8339b - this.f8330d)));
    }
}
