package b2;

import com.yalantis.ucrop.view.CropImageView;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends m {
    public static final j VALUE_0 = new j(Float.floatToIntBits(CropImageView.DEFAULT_ASPECT_RATIO));
    public static final j VALUE_1 = new j(Float.floatToIntBits(1.0f));
    public static final j VALUE_2 = new j(Float.floatToIntBits(2.0f));

    @Override // c2.d
    public final c2.c b() {
        return c2.c.FLOAT;
    }

    @Override // e2.i
    public final String d() {
        return Float.toString(Float.intBitsToFloat(this.f2934a));
    }

    @Override // b2.a
    public final String f() {
        return "float";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("float{0x");
        int i7 = this.f2934a;
        sb.append(z.m1(i7));
        sb.append(" / ");
        sb.append(Float.intBitsToFloat(i7));
        sb.append('}');
        return sb.toString();
    }
}
