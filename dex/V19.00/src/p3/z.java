package p3;

import android.graphics.Matrix;
import android.graphics.Path;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    public float f8344a;

    /* renamed from: b, reason: collision with root package name */
    public float f8345b;

    /* renamed from: c, reason: collision with root package name */
    public float f8346c;

    /* renamed from: d, reason: collision with root package name */
    public float f8347d;

    /* renamed from: e, reason: collision with root package name */
    public float f8348e;

    /* renamed from: f, reason: collision with root package name */
    public float f8349f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f8350g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    public final ArrayList f8351h = new ArrayList();

    public z() {
        e(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 270.0f, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public final void a(float f7, float f8, float f9, float f10, float f11, float f12) {
        v vVar = new v(f7, f8, f9, f10);
        vVar.f8337f = f11;
        vVar.f8338g = f12;
        this.f8350g.add(vVar);
        t tVar = new t(vVar);
        float f13 = f11 + f12;
        boolean z6 = f12 < CropImageView.DEFAULT_ASPECT_RATIO;
        if (z6) {
            f11 = (f11 + 180.0f) % 360.0f;
        }
        float f14 = z6 ? (180.0f + f13) % 360.0f : f13;
        b(f11);
        this.f8351h.add(tVar);
        this.f8348e = f14;
        double d7 = f13;
        this.f8346c = (((f9 - f7) / 2.0f) * ((float) Math.cos(Math.toRadians(d7)))) + ((f7 + f9) * 0.5f);
        this.f8347d = (((f10 - f8) / 2.0f) * ((float) Math.sin(Math.toRadians(d7)))) + ((f8 + f10) * 0.5f);
    }

    public final void b(float f7) {
        float f8 = this.f8348e;
        if (f8 == f7) {
            return;
        }
        float f9 = ((f7 - f8) + 360.0f) % 360.0f;
        if (f9 > 180.0f) {
            return;
        }
        float f10 = this.f8346c;
        float f11 = this.f8347d;
        v vVar = new v(f10, f11, f10, f11);
        vVar.f8337f = this.f8348e;
        vVar.f8338g = f9;
        this.f8351h.add(new t(vVar));
        this.f8348e = f7;
    }

    public final void c(Matrix matrix, Path path) {
        ArrayList arrayList = this.f8350g;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((x) arrayList.get(i7)).a(matrix, path);
        }
    }

    public final void d(float f7, float f8) {
        w wVar = new w();
        wVar.f8339b = f7;
        wVar.f8340c = f8;
        this.f8350g.add(wVar);
        u uVar = new u(wVar, this.f8346c, this.f8347d);
        float b7 = uVar.b() + 270.0f;
        float b8 = uVar.b() + 270.0f;
        b(b7);
        this.f8351h.add(uVar);
        this.f8348e = b8;
        this.f8346c = f7;
        this.f8347d = f8;
    }

    public final void e(float f7, float f8, float f9, float f10) {
        this.f8344a = f7;
        this.f8345b = f8;
        this.f8346c = f7;
        this.f8347d = f8;
        this.f8348e = f9;
        this.f8349f = (f9 + f10) % 360.0f;
        this.f8350g.clear();
        this.f8351h.clear();
    }
}
