package l0;

import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: b, reason: collision with root package name */
    public static final g f7273b = new g(new i(f.a(new Locale[0])));

    /* renamed from: a, reason: collision with root package name */
    public final h f7274a;

    public g(i iVar) {
        this.f7274a = iVar;
    }

    public static g a(String str) {
        if (str == null || str.isEmpty()) {
            return f7273b;
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i7 = 0; i7 < length; i7++) {
            localeArr[i7] = e.a(split[i7]);
        }
        return new g(new i(f.a(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            if (this.f7274a.equals(((g) obj).f7274a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f7274a.hashCode();
    }

    public final String toString() {
        return this.f7274a.toString();
    }
}
