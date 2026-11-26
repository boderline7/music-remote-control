package i0;

import android.content.res.Resources;
import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h {

    /* renamed from: a, reason: collision with root package name */
    public static final e.a f6781a;

    /* renamed from: b, reason: collision with root package name */
    public static final q.f f6782b;

    static {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 29) {
            f6781a = new l();
        } else if (i7 >= 28) {
            f6781a = new k();
        } else {
            f6781a = new j();
        }
        f6782b = new q.f(16);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002b, code lost:
    
        if (r3.equals(r5) == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface a(android.content.Context r15, h0.e r16, android.content.res.Resources r17, int r18, java.lang.String r19, int r20, int r21, l6.f r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.h.a(android.content.Context, h0.e, android.content.res.Resources, int, java.lang.String, int, int, l6.f, boolean):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i7, String str, int i8, int i9) {
        return resources.getResourcePackageName(i7) + '-' + str + '-' + i8 + '-' + i7 + '-' + i9;
    }
}
