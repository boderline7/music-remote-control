package com.google.gson.internal;

import a.a;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class PreJava9DateFormatProvider {
    private static String getDateFormatPattern(int i7) {
        if (i7 == 0) {
            return "EEEE, MMMM d, y";
        }
        if (i7 == 1) {
            return "MMMM d, y";
        }
        if (i7 == 2) {
            return "MMM d, y";
        }
        if (i7 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException(a.d("Unknown DateFormat style: ", i7));
    }

    private static String getDatePartOfDateTimePattern(int i7) {
        if (i7 == 0) {
            return "EEEE, MMMM d, yyyy";
        }
        if (i7 == 1) {
            return "MMMM d, yyyy";
        }
        if (i7 == 2) {
            return "MMM d, yyyy";
        }
        if (i7 == 3) {
            return "M/d/yy";
        }
        throw new IllegalArgumentException(a.d("Unknown DateFormat style: ", i7));
    }

    private static String getTimePartOfDateTimePattern(int i7) {
        if (i7 == 0 || i7 == 1) {
            return "h:mm:ss a z";
        }
        if (i7 == 2) {
            return "h:mm:ss a";
        }
        if (i7 == 3) {
            return "h:mm a";
        }
        throw new IllegalArgumentException(a.d("Unknown DateFormat style: ", i7));
    }

    public static DateFormat getUSDateFormat(int i7) {
        return new SimpleDateFormat(getDateFormatPattern(i7), Locale.US);
    }

    public static DateFormat getUSDateTimeFormat(int i7, int i8) {
        return new SimpleDateFormat(getDatePartOfDateTimePattern(i7) + " " + getTimePartOfDateTimePattern(i8), Locale.US);
    }
}
