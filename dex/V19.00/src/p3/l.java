package p3;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements d {

    /* renamed from: a, reason: collision with root package name */
    public final float f8288a;

    public l(float f7) {
        this.f8288a = f7;
    }

    @Override // p3.d
    public final float a(RectF rectF) {
        return Math.min(rectF.width(), rectF.height()) * this.f8288a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof l) && this.f8288a == ((l) obj).f8288a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f8288a)});
    }
}
