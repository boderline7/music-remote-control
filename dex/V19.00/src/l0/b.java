package l0;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final b INSTANCE = new b();

    public static final void a(Bundle bundle, String str, Size size) {
        i5.a.g("bundle", bundle);
        i5.a.g("key", str);
        bundle.putSize(str, size);
    }

    public static final void b(Bundle bundle, String str, SizeF sizeF) {
        i5.a.g("bundle", bundle);
        i5.a.g("key", str);
        bundle.putSizeF(str, sizeF);
    }
}
