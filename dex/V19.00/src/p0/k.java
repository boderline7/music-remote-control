package p0;

import android.view.DisplayCutout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public final DisplayCutout f8159a;

    public k(DisplayCutout displayCutout) {
        this.f8159a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        return o0.b.a(this.f8159a, ((k) obj).f8159a);
    }

    public final int hashCode() {
        int hashCode;
        DisplayCutout displayCutout = this.f8159a;
        if (displayCutout == null) {
            return 0;
        }
        hashCode = displayCutout.hashCode();
        return hashCode;
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f8159a + "}";
    }
}
