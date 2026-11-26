package z5;

import android.support.v4.media.session.PlaybackStateCompat;
import o5.k;
import s5.b0;
import s5.d0;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final a Companion = new a();

    /* renamed from: a, reason: collision with root package name */
    public final g6.j f10296a;

    /* renamed from: b, reason: collision with root package name */
    public long f10297b = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    public b(g6.j jVar) {
        this.f10296a = jVar;
    }

    public final d0 a() {
        b0 b0Var = new b0();
        while (true) {
            String g7 = this.f10296a.g(this.f10297b);
            this.f10297b -= g7.length();
            if (g7.length() == 0) {
                return b0Var.b();
            }
            int F = k.F(g7, ':', 1, false, 4);
            if (F != -1) {
                String substring = g7.substring(0, F);
                i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
                String substring2 = g7.substring(F + 1);
                i5.a.f("this as java.lang.String).substring(startIndex)", substring2);
                b0Var.a(substring, substring2);
            } else if (g7.charAt(0) == ':') {
                String substring3 = g7.substring(1);
                i5.a.f("this as java.lang.String).substring(startIndex)", substring3);
                b0Var.a(h0.FRAGMENT_ENCODE_SET, substring3);
            } else {
                b0Var.a(h0.FRAGMENT_ENCODE_SET, g7);
            }
        }
    }
}
