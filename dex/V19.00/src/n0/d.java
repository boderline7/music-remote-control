package n0;

import android.icu.util.ULocale;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {
    public static ULocale a(Object obj) {
        return ULocale.addLikelySubtags((ULocale) obj);
    }

    public static ULocale b(Locale locale) {
        return ULocale.forLocale(locale);
    }

    public static String c(Object obj) {
        return ((ULocale) obj).getScript();
    }
}
