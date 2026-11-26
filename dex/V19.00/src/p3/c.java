package p3;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements d {

    /* renamed from: a, reason: collision with root package name */
    public final float f8232a;

    public c(float f7) {
        this.f8232a = f7;
    }

    @Override // p3.d
    public final float a(RectF rectF) {
        return Math.min(this.f8232a, Math.min(rectF.width() / 2.0f, rectF.height() / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && this.f8232a == ((c) obj).f8232a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f8232a)});
    }
}
