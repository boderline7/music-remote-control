package o1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {

    /* renamed from: p, reason: collision with root package name */
    public static final Matrix f7838p = new Matrix();

    /* renamed from: a, reason: collision with root package name */
    public final Path f7839a;

    /* renamed from: b, reason: collision with root package name */
    public final Path f7840b;

    /* renamed from: c, reason: collision with root package name */
    public final Matrix f7841c;

    /* renamed from: d, reason: collision with root package name */
    public Paint f7842d;

    /* renamed from: e, reason: collision with root package name */
    public Paint f7843e;

    /* renamed from: f, reason: collision with root package name */
    public PathMeasure f7844f;

    /* renamed from: g, reason: collision with root package name */
    public final j f7845g;

    /* renamed from: h, reason: collision with root package name */
    public float f7846h;

    /* renamed from: i, reason: collision with root package name */
    public float f7847i;

    /* renamed from: j, reason: collision with root package name */
    public float f7848j;

    /* renamed from: k, reason: collision with root package name */
    public float f7849k;

    /* renamed from: l, reason: collision with root package name */
    public int f7850l;

    /* renamed from: m, reason: collision with root package name */
    public String f7851m;

    /* renamed from: n, reason: collision with root package name */
    public Boolean f7852n;

    /* renamed from: o, reason: collision with root package name */
    public final q.b f7853o;

    public m() {
        this.f7841c = new Matrix();
        this.f7846h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7847i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7848j = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7849k = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7850l = 255;
        this.f7851m = null;
        this.f7852n = null;
        this.f7853o = new q.b();
        this.f7845g = new j();
        this.f7839a = new Path();
        this.f7840b = new Path();
    }

    public m(m mVar) {
        this.f7841c = new Matrix();
        this.f7846h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7847i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7848j = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7849k = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f7850l = 255;
        this.f7851m = null;
        this.f7852n = null;
        q.b bVar = new q.b();
        this.f7853o = bVar;
        this.f7845g = new j(mVar.f7845g, bVar);
        this.f7839a = new Path(mVar.f7839a);
        this.f7840b = new Path(mVar.f7840b);
        this.f7846h = mVar.f7846h;
        this.f7847i = mVar.f7847i;
        this.f7848j = mVar.f7848j;
        this.f7849k = mVar.f7849k;
        this.f7850l = mVar.f7850l;
        this.f7851m = mVar.f7851m;
        String str = mVar.f7851m;
        if (str != null) {
            bVar.put(str, this);
        }
        this.f7852n = mVar.f7852n;
    }

    public final void a(j jVar, Matrix matrix, Canvas canvas, int i7, int i8) {
        int i9;
        float f7;
        jVar.f7822a.set(matrix);
        Matrix matrix2 = jVar.f7822a;
        matrix2.preConcat(jVar.f7831j);
        canvas.save();
        char c7 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = jVar.f7823b;
            if (i10 >= arrayList.size()) {
                canvas.restore();
                return;
            }
            k kVar = (k) arrayList.get(i10);
            if (kVar instanceof j) {
                a((j) kVar, matrix2, canvas, i7, i8);
            } else if (kVar instanceof l) {
                l lVar = (l) kVar;
                float f8 = i7 / this.f7848j;
                float f9 = i8 / this.f7849k;
                float min = Math.min(f8, f9);
                Matrix matrix3 = this.f7841c;
                matrix3.set(matrix2);
                matrix3.postScale(f8, f9);
                float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO};
                matrix2.mapVectors(fArr);
                float hypot = (float) Math.hypot(fArr[c7], fArr[1]);
                i9 = i10;
                float hypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                float f10 = (fArr[0] * fArr[3]) - (fArr[1] * fArr[2]);
                float max = Math.max(hypot, hypot2);
                float abs = max > CropImageView.DEFAULT_ASPECT_RATIO ? Math.abs(f10) / max : 0.0f;
                if (abs != CropImageView.DEFAULT_ASPECT_RATIO) {
                    lVar.getClass();
                    Path path = this.f7839a;
                    path.reset();
                    i0.f[] fVarArr = lVar.f7834a;
                    if (fVarArr != null) {
                        i0.f.b(fVarArr, path);
                    }
                    Path path2 = this.f7840b;
                    path2.reset();
                    if (lVar instanceof h) {
                        path2.setFillType(lVar.f7836c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        path2.addPath(path, matrix3);
                        canvas.clipPath(path2);
                    } else {
                        i iVar = (i) lVar;
                        float f11 = iVar.f7816j;
                        if (f11 != CropImageView.DEFAULT_ASPECT_RATIO || iVar.f7817k != 1.0f) {
                            float f12 = iVar.f7818l;
                            float f13 = (f11 + f12) % 1.0f;
                            float f14 = (iVar.f7817k + f12) % 1.0f;
                            if (this.f7844f == null) {
                                this.f7844f = new PathMeasure();
                            }
                            this.f7844f.setPath(path, false);
                            float length = this.f7844f.getLength();
                            float f15 = f13 * length;
                            float f16 = f14 * length;
                            path.reset();
                            if (f15 > f16) {
                                this.f7844f.getSegment(f15, length, path, true);
                                PathMeasure pathMeasure = this.f7844f;
                                f7 = CropImageView.DEFAULT_ASPECT_RATIO;
                                pathMeasure.getSegment(CropImageView.DEFAULT_ASPECT_RATIO, f16, path, true);
                            } else {
                                f7 = 0.0f;
                                this.f7844f.getSegment(f15, f16, path, true);
                            }
                            path.rLineTo(f7, f7);
                        }
                        path2.addPath(path, matrix3);
                        k4.a aVar = iVar.f7813g;
                        if ((((Shader) aVar.f7259b) == null && aVar.f7258a == 0) ? false : true) {
                            if (this.f7843e == null) {
                                Paint paint = new Paint(1);
                                this.f7843e = paint;
                                paint.setStyle(Paint.Style.FILL);
                            }
                            Paint paint2 = this.f7843e;
                            Object obj = aVar.f7259b;
                            if (((Shader) obj) != null) {
                                Shader shader = (Shader) obj;
                                shader.setLocalMatrix(matrix3);
                                paint2.setShader(shader);
                                paint2.setAlpha(Math.round(iVar.f7815i * 255.0f));
                            } else {
                                paint2.setShader(null);
                                paint2.setAlpha(255);
                                int i11 = aVar.f7258a;
                                float f17 = iVar.f7815i;
                                PorterDuff.Mode mode = p.f7867j;
                                paint2.setColor((i11 & d1.MEASURED_SIZE_MASK) | (((int) (Color.alpha(i11) * f17)) << 24));
                            }
                            paint2.setColorFilter(null);
                            path2.setFillType(iVar.f7836c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas.drawPath(path2, paint2);
                        }
                        k4.a aVar2 = iVar.f7811e;
                        if (((Shader) aVar2.f7259b) != null || aVar2.f7258a != 0) {
                            if (this.f7842d == null) {
                                Paint paint3 = new Paint(1);
                                this.f7842d = paint3;
                                paint3.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint4 = this.f7842d;
                            Paint.Join join = iVar.f7820n;
                            if (join != null) {
                                paint4.setStrokeJoin(join);
                            }
                            Paint.Cap cap = iVar.f7819m;
                            if (cap != null) {
                                paint4.setStrokeCap(cap);
                            }
                            paint4.setStrokeMiter(iVar.f7821o);
                            Object obj2 = aVar2.f7259b;
                            if (((Shader) obj2) != null) {
                                Shader shader2 = (Shader) obj2;
                                shader2.setLocalMatrix(matrix3);
                                paint4.setShader(shader2);
                                paint4.setAlpha(Math.round(iVar.f7814h * 255.0f));
                            } else {
                                paint4.setShader(null);
                                paint4.setAlpha(255);
                                int i12 = aVar2.f7258a;
                                float f18 = iVar.f7814h;
                                PorterDuff.Mode mode2 = p.f7867j;
                                paint4.setColor((i12 & d1.MEASURED_SIZE_MASK) | (((int) (Color.alpha(i12) * f18)) << 24));
                            }
                            paint4.setColorFilter(null);
                            paint4.setStrokeWidth(iVar.f7812f * abs * min);
                            canvas.drawPath(path2, paint4);
                        }
                    }
                }
                i10 = i9 + 1;
                c7 = 0;
            }
            i9 = i10;
            i10 = i9 + 1;
            c7 = 0;
        }
    }

    public float getAlpha() {
        return getRootAlpha() / 255.0f;
    }

    public int getRootAlpha() {
        return this.f7850l;
    }

    public void setAlpha(float f7) {
        setRootAlpha((int) (f7 * 255.0f));
    }

    public void setRootAlpha(int i7) {
        this.f7850l = i7;
    }
}
