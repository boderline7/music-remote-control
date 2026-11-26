package s5;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k0 {
    public static l0 a(String str) {
        i5.a.g("<this>", str);
        Matcher matcher = l0.f8810c.matcher(str);
        if (!matcher.lookingAt()) {
            throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
        }
        String group = matcher.group(1);
        i5.a.f("typeSubtype.group(1)", group);
        Locale locale = Locale.US;
        i5.a.f("US", locale);
        i5.a.f("this as java.lang.String).toLowerCase(locale)", group.toLowerCase(locale));
        String group2 = matcher.group(2);
        i5.a.f("typeSubtype.group(2)", group2);
        i5.a.f("this as java.lang.String).toLowerCase(locale)", group2.toLowerCase(locale));
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = l0.f8811d.matcher(str);
        int end = matcher.end();
        while (end < str.length()) {
            matcher2.region(end, str.length());
            if (!matcher2.lookingAt()) {
                StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                String substring = str.substring(end);
                i5.a.f("this as java.lang.String).substring(startIndex)", substring);
                sb.append(substring);
                sb.append("\" for: \"");
                sb.append(str);
                sb.append('\"');
                throw new IllegalArgumentException(sb.toString().toString());
            }
            String group3 = matcher2.group(1);
            if (group3 == null) {
                end = matcher2.end();
            } else {
                String group4 = matcher2.group(2);
                if (group4 == null) {
                    group4 = matcher2.group(3);
                } else if (o5.k.R(group4, "'", false) && o5.k.B(group4, "'") && group4.length() > 2) {
                    group4 = group4.substring(1, group4.length() - 1);
                    i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", group4);
                }
                arrayList.add(group3);
                arrayList.add(group4);
                end = matcher2.end();
            }
        }
        return new l0(str, (String[]) arrayList.toArray(new String[0]));
    }

    public static l0 b(String str) {
        i5.a.g("<this>", str);
        try {
            return a(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
