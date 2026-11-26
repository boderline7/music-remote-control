package p3;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends y {

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f8326c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Matrix f8327d;

    public s(ArrayList arrayList, Matrix matrix) {
        this.f8326c = arrayList;
        this.f8327d = matrix;
    }

    @Override // p3.y
    public final void a(Matrix matrix, o3.a aVar, int i7, Canvas canvas) {
        Iterator it = this.f8326c.iterator();
        while (it.hasNext()) {
            ((y) it.next()).a(this.f8327d, aVar, i7, canvas);
        }
    }
}
