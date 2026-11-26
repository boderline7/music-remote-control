package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    private static boolean checkOffset(String str, int i7, char c7) {
        return i7 < str.length() && str.charAt(i7) == c7;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z6) {
        return format(date, z6, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z6, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z6 ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z6) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i7 = offset / 60000;
            int abs = Math.abs(i7 / 60);
            int abs2 = Math.abs(i7 % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    private static int indexOfNonDigit(String str, int i7) {
        while (i7 < str.length()) {
            char charAt = str.charAt(i7);
            if (charAt < '0' || charAt > '9') {
                return i7;
            }
            i7++;
        }
        return str.length();
    }

    private static void padInt(StringBuilder sb, int i7, int i8) {
        String num = Integer.toString(i7);
        for (int length = i8 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) {
        /*
            Method dump skipped, instructions count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static int parseInt(String str, int i7, int i8) {
        int i9;
        int i10;
        if (i7 < 0 || i8 > str.length() || i7 > i8) {
            throw new NumberFormatException(str);
        }
        if (i7 < i8) {
            i10 = i7 + 1;
            int digit = Character.digit(str.charAt(i7), 10);
            if (digit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i8));
            }
            i9 = -digit;
        } else {
            i9 = 0;
            i10 = i7;
        }
        while (i10 < i8) {
            int i11 = i10 + 1;
            int digit2 = Character.digit(str.charAt(i10), 10);
            if (digit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i7, i8));
            }
            i9 = (i9 * 10) - digit2;
            i10 = i11;
        }
        return -i9;
    }
}
