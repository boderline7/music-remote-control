package p3;

import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements d {

    /* renamed from: a, reason: collision with root package name */
    public final d f8225a;

    /* renamed from: b, reason: collision with root package name */
    public final float f8226b;

    public b(float f7, d dVar) {
        while (dVar instanceof b) {
            dVar = ((b) dVar).f8225a;
            f7 += ((b) dVar).f8226b;
        }
        this.f8225a = dVar;
        this.f8226b = f7;
    }

    @Override // p3.d
    public final float a(RectF rectF) {
        return Math.max(CropImageView.DEFAULT_ASPECT_RATIO, this.f8225a.a(rectF) + this.f8226b);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f8225a.equals(bVar.f8225a) && this.f8226b == bVar.f8226b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f8225a, Float.valueOf(this.f8226b)});
    }
}
