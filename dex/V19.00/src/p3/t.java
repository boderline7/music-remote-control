package p3;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class t extends y {

    /* renamed from: c, reason: collision with root package name */
    public final v f8328c;

    public t(v vVar) {
        this.f8328c = vVar;
    }

    @Override // p3.y
    public final void a(Matrix matrix, o3.a aVar, int i7, Canvas canvas) {
        v vVar = this.f8328c;
        float f7 = vVar.f8337f;
        float f8 = vVar.f8338g;
        RectF rectF = new RectF(vVar.f8333b, vVar.f8334c, vVar.f8335d, vVar.f8336e);
        aVar.getClass();
        boolean z6 = f8 < CropImageView.DEFAULT_ASPECT_RATIO;
        Path path = aVar.f7917g;
        int[] iArr = o3.a.f7909k;
        if (z6) {
            iArr[0] = 0;
            iArr[1] = aVar.f7916f;
            iArr[2] = aVar.f7915e;
            iArr[3] = aVar.f7914d;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF, f7, f8);
            path.close();
            float f9 = -i7;
            rectF.inset(f9, f9);
            iArr[0] = 0;
            iArr[1] = aVar.f7914d;
            iArr[2] = aVar.f7915e;
            iArr[3] = aVar.f7916f;
        }
        float width = rectF.width() / 2.0f;
        if (width <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        float f10 = 1.0f - (i7 / width);
        float[] fArr = o3.a.f7910l;
        fArr[1] = f10;
        fArr[2] = ((1.0f - f10) / 2.0f) + f10;
        RadialGradient radialGradient = new RadialGradient(rectF.centerX(), rectF.centerY(), width, iArr, fArr, Shader.TileMode.CLAMP);
        Paint paint = aVar.f7912b;
        paint.setShader(radialGradient);
        canvas.save();
        canvas.concat(matrix);
        canvas.scale(1.0f, rectF.height() / rectF.width());
        if (!z6) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawPath(path, aVar.f7918h);
        }
        canvas.drawArc(rectF, f7, f8, true, paint);
        canvas.restore();
    }
}
