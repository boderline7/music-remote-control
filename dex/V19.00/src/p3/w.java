package p3;

import android.graphics.Matrix;
import android.graphics.Path;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w extends x {

    /* renamed from: b, reason: collision with root package name */
    public float f8339b;

    /* renamed from: c, reason: collision with root package name */
    public float f8340c;

    @Override // p3.x
    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f8341a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        path.lineTo(this.f8339b, this.f8340c);
        path.transform(matrix);
    }
}
