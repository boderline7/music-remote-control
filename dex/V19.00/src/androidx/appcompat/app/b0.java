package androidx.appcompat.app;

import android.content.res.Configuration;
import android.os.LocaleList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class b0 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static l0.g b(Configuration configuration) {
        return l0.g.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(l0.g gVar) {
        LocaleList.setDefault(LocaleList.forLanguageTags(((l0.i) gVar.f7274a).f7275a.toLanguageTags()));
    }

    public static void d(Configuration configuration, l0.g gVar) {
        configuration.setLocales(LocaleList.forLanguageTags(((l0.i) gVar.f7274a).f7275a.toLanguageTags()));
    }
}
