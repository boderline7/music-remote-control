package l0;

import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    public static final Locale[] f7272a = {new Locale("en", "XA"), new Locale("ar", "XB")};

    public static Locale a(String str) {
        return Locale.forLanguageTag(str);
    }

    public static boolean b(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return true;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage())) {
            return false;
        }
        Locale[] localeArr = f7272a;
        int length = localeArr.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                for (Locale locale3 : localeArr) {
                    if (!locale3.equals(locale2)) {
                    }
                }
                String c7 = n0.d.c(n0.d.a(n0.d.b(locale)));
                if (!c7.isEmpty()) {
                    return c7.equals(n0.d.c(n0.d.a(n0.d.b(locale2))));
                }
                String country = locale.getCountry();
                return country.isEmpty() || country.equals(locale2.getCountry());
            }
            if (localeArr[i7].equals(locale)) {
                break;
            }
            i7++;
        }
        return false;
    }
}
