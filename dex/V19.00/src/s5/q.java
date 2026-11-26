package s5;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q {
    public static int a(String str, int i7, int i8, boolean z6) {
        while (i7 < i8) {
            char charAt = str.charAt(i7);
            if (((charAt < ' ' && charAt != '\t') || charAt >= 127 || ('0' <= charAt && charAt < ':') || (('a' <= charAt && charAt < '{') || (('A' <= charAt && charAt < '[') || charAt == ':'))) == (!z6)) {
                return i7;
            }
            i7++;
        }
        return i8;
    }

    public static long b(int i7, String str) {
        int a7 = a(str, 0, i7, false);
        Matcher matcher = r.f8876m.matcher(str);
        int i8 = -1;
        int i9 = -1;
        int i10 = -1;
        int i11 = -1;
        int i12 = -1;
        int i13 = -1;
        while (a7 < i7) {
            int a8 = a(str, a7 + 1, i7, true);
            matcher.region(a7, a8);
            if (i9 == -1 && matcher.usePattern(r.f8876m).matches()) {
                String group = matcher.group(1);
                i5.a.f("matcher.group(1)", group);
                i9 = Integer.parseInt(group);
                String group2 = matcher.group(2);
                i5.a.f("matcher.group(2)", group2);
                i12 = Integer.parseInt(group2);
                String group3 = matcher.group(3);
                i5.a.f("matcher.group(3)", group3);
                i13 = Integer.parseInt(group3);
            } else if (i10 == -1 && matcher.usePattern(r.f8875l).matches()) {
                String group4 = matcher.group(1);
                i5.a.f("matcher.group(1)", group4);
                i10 = Integer.parseInt(group4);
            } else {
                if (i11 == -1) {
                    Pattern pattern = r.f8874k;
                    if (matcher.usePattern(pattern).matches()) {
                        String group5 = matcher.group(1);
                        i5.a.f("matcher.group(1)", group5);
                        Locale locale = Locale.US;
                        i5.a.f("US", locale);
                        String lowerCase = group5.toLowerCase(locale);
                        i5.a.f("this as java.lang.String).toLowerCase(locale)", lowerCase);
                        String pattern2 = pattern.pattern();
                        i5.a.f("MONTH_PATTERN.pattern()", pattern2);
                        i11 = o5.k.G(pattern2, lowerCase, 0, false, 6) / 4;
                    }
                }
                if (i8 == -1 && matcher.usePattern(r.f8873j).matches()) {
                    String group6 = matcher.group(1);
                    i5.a.f("matcher.group(1)", group6);
                    i8 = Integer.parseInt(group6);
                }
            }
            a7 = a(str, a8 + 1, i7, false);
        }
        if (70 <= i8 && i8 < 100) {
            i8 += 1900;
        }
        if (i8 >= 0 && i8 < 70) {
            i8 += 2000;
        }
        if (i8 < 1601) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i11 == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (1 > i10 || i10 >= 32) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i9 < 0 || i9 >= 24) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i12 < 0 || i12 >= 60) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i13 < 0 || i13 >= 60) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(t5.b.UTC);
        gregorianCalendar.setLenient(false);
        gregorianCalendar.set(1, i8);
        gregorianCalendar.set(2, i11 - 1);
        gregorianCalendar.set(5, i10);
        gregorianCalendar.set(11, i9);
        gregorianCalendar.set(12, i12);
        gregorianCalendar.set(13, i13);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }
}
