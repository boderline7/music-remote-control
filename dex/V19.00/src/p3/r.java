package p3;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public final z[] f8314a = new z[4];

    /* renamed from: b, reason: collision with root package name */
    public final Matrix[] f8315b = new Matrix[4];

    /* renamed from: c, reason: collision with root package name */
    public final Matrix[] f8316c = new Matrix[4];

    /* renamed from: d, reason: collision with root package name */
    public final PointF f8317d = new PointF();

    /* renamed from: e, reason: collision with root package name */
    public final Path f8318e = new Path();

    /* renamed from: f, reason: collision with root package name */
    public final Path f8319f = new Path();

    /* renamed from: g, reason: collision with root package name */
    public final z f8320g = new z();

    /* renamed from: h, reason: collision with root package name */
    public final float[] f8321h = new float[2];

    /* renamed from: i, reason: collision with root package name */
    public final float[] f8322i = new float[2];

    /* renamed from: j, reason: collision with root package name */
    public final Path f8323j = new Path();

    /* renamed from: k, reason: collision with root package name */
    public final Path f8324k = new Path();

    /* renamed from: l, reason: collision with root package name */
    public final boolean f8325l = true;

    public r() {
        for (int i7 = 0; i7 < 4; i7++) {
            this.f8314a[i7] = new z();
            this.f8315b[i7] = new Matrix();
            this.f8316c[i7] = new Matrix();
        }
    }

    public final void a(p pVar, float f7, RectF rectF, v2.a aVar, Path path) {
        int i7;
        Matrix[] matrixArr;
        float[] fArr;
        Matrix[] matrixArr2;
        z[] zVarArr;
        Path path2;
        Path path3;
        v2.a aVar2;
        Path path4;
        r rVar = this;
        p pVar2 = pVar;
        v2.a aVar3 = aVar;
        Path path5 = path;
        path.rewind();
        Path path6 = rVar.f8318e;
        path6.rewind();
        Path path7 = rVar.f8319f;
        path7.rewind();
        path7.addRect(rectF, Path.Direction.CW);
        int i8 = 0;
        while (true) {
            i7 = 4;
            matrixArr = rVar.f8316c;
            fArr = rVar.f8321h;
            matrixArr2 = rVar.f8315b;
            zVarArr = rVar.f8314a;
            if (i8 >= 4) {
                break;
            }
            d dVar = i8 != 1 ? i8 != 2 ? i8 != 3 ? pVar2.f8306f : pVar2.f8305e : pVar2.f8308h : pVar2.f8307g;
            l6.f fVar = i8 != 1 ? i8 != 2 ? i8 != 3 ? pVar2.f8302b : pVar2.f8301a : pVar2.f8304d : pVar2.f8303c;
            z zVar = zVarArr[i8];
            fVar.getClass();
            fVar.y(f7, dVar.a(rectF), zVar);
            int i9 = i8 + 1;
            float f8 = (i9 % 4) * 90;
            matrixArr2[i8].reset();
            PointF pointF = rVar.f8317d;
            if (i8 == 1) {
                path4 = path6;
                pointF.set(rectF.right, rectF.bottom);
            } else if (i8 == 2) {
                path4 = path6;
                pointF.set(rectF.left, rectF.bottom);
            } else if (i8 != 3) {
                path4 = path6;
                pointF.set(rectF.right, rectF.top);
            } else {
                path4 = path6;
                pointF.set(rectF.left, rectF.top);
            }
            matrixArr2[i8].setTranslate(pointF.x, pointF.y);
            matrixArr2[i8].preRotate(f8);
            z zVar2 = zVarArr[i8];
            fArr[0] = zVar2.f8346c;
            fArr[1] = zVar2.f8347d;
            matrixArr2[i8].mapPoints(fArr);
            matrixArr[i8].reset();
            matrixArr[i8].setTranslate(fArr[0], fArr[1]);
            matrixArr[i8].preRotate(f8);
            i8 = i9;
            path6 = path4;
        }
        Path path8 = path6;
        int i10 = 0;
        while (i10 < i7) {
            z zVar3 = zVarArr[i10];
            fArr[0] = zVar3.f8344a;
            fArr[1] = zVar3.f8345b;
            matrixArr2[i10].mapPoints(fArr);
            if (i10 == 0) {
                path5.moveTo(fArr[0], fArr[1]);
            } else {
                path5.lineTo(fArr[0], fArr[1]);
            }
            zVarArr[i10].c(matrixArr2[i10], path5);
            if (aVar3 != null) {
                z zVar4 = zVarArr[i10];
                Matrix matrix = matrixArr2[i10];
                BitSet bitSet = ((j) aVar3.f9388b).f8266d;
                zVar4.getClass();
                bitSet.set(i10, false);
                y[] yVarArr = ((j) aVar3.f9388b).f8264b;
                zVar4.b(zVar4.f8349f);
                yVarArr[i10] = new s(new ArrayList(zVar4.f8351h), new Matrix(matrix));
            }
            int i11 = i10 + 1;
            int i12 = i11 % 4;
            z zVar5 = zVarArr[i10];
            fArr[0] = zVar5.f8346c;
            fArr[1] = zVar5.f8347d;
            matrixArr2[i10].mapPoints(fArr);
            z zVar6 = zVarArr[i12];
            float f9 = zVar6.f8344a;
            float[] fArr2 = rVar.f8322i;
            fArr2[0] = f9;
            fArr2[1] = zVar6.f8345b;
            matrixArr2[i12].mapPoints(fArr2);
            float max = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, CropImageView.DEFAULT_ASPECT_RATIO);
            z zVar7 = zVarArr[i10];
            fArr[0] = zVar7.f8346c;
            fArr[1] = zVar7.f8347d;
            matrixArr2[i10].mapPoints(fArr);
            float abs = (i10 == 1 || i10 == 3) ? Math.abs(rectF.centerX() - fArr[0]) : Math.abs(rectF.centerY() - fArr[1]);
            z zVar8 = rVar.f8320g;
            zVar8.e(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 270.0f, CropImageView.DEFAULT_ASPECT_RATIO);
            f fVar2 = i10 != 1 ? i10 != 2 ? i10 != 3 ? pVar2.f8310j : pVar2.f8309i : pVar2.f8312l : pVar2.f8311k;
            fVar2.p(max, abs, f7, zVar8);
            Path path9 = rVar.f8323j;
            path9.reset();
            zVar8.c(matrixArr[i10], path9);
            if (rVar.f8325l && (fVar2.o() || rVar.b(path9, i10) || rVar.b(path9, i12))) {
                path9.op(path9, path7, Path.Op.DIFFERENCE);
                fArr[0] = zVar8.f8344a;
                fArr[1] = zVar8.f8345b;
                matrixArr[i10].mapPoints(fArr);
                path2 = path8;
                path2.moveTo(fArr[0], fArr[1]);
                zVar8.c(matrixArr[i10], path2);
                aVar2 = aVar;
                path3 = path;
            } else {
                path2 = path8;
                path3 = path;
                zVar8.c(matrixArr[i10], path3);
                aVar2 = aVar;
            }
            if (aVar2 != null) {
                Matrix matrix2 = matrixArr[i10];
                ((j) aVar2.f9388b).f8266d.set(i10 + 4, false);
                y[] yVarArr2 = ((j) aVar2.f9388b).f8265c;
                zVar8.b(zVar8.f8349f);
                yVarArr2[i10] = new s(new ArrayList(zVar8.f8351h), new Matrix(matrix2));
            }
            rVar = this;
            pVar2 = pVar;
            path5 = path3;
            i10 = i11;
            path8 = path2;
            i7 = 4;
            aVar3 = aVar2;
        }
        Path path10 = path5;
        Path path11 = path8;
        path.close();
        path11.close();
        if (path11.isEmpty()) {
            return;
        }
        path10.op(path11, Path.Op.UNION);
    }

    public final boolean b(Path path, int i7) {
        Path path2 = this.f8324k;
        path2.reset();
        this.f8314a[i7].c(this.f8315b[i7], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }
}
