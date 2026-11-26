package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j0 {

    /* renamed from: a, reason: collision with root package name */
    public static final AtomicReference f3637a = new AtomicReference();

    public static long a(long j7) {
        Calendar g7 = g(null);
        g7.setTimeInMillis(j7);
        return c(g7).getTimeInMillis();
    }

    public static DateFormat b(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return instanceForSkeleton;
    }

    public static Calendar c(Calendar calendar) {
        Calendar g7 = g(calendar);
        Calendar g8 = g(null);
        g8.set(g7.get(1), g7.get(2), g7.get(5));
        return g8;
    }

    public static SimpleDateFormat d() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern().replaceAll("[^dMy/\\-.]", s5.h0.FRAGMENT_ENCODE_SET).replaceAll("d{1,2}", "dd").replaceAll("M{1,2}", "MM").replaceAll("y{1,4}", "yyyy").replaceAll("\\.$", s5.h0.FRAGMENT_ENCODE_SET).replaceAll("My", "M/y"), Locale.getDefault());
        simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    public static String e(Resources resources, SimpleDateFormat simpleDateFormat) {
        String pattern = simpleDateFormat.toPattern();
        String string = resources.getString(q2.k.mtrl_picker_text_input_year_abbr);
        String string2 = resources.getString(q2.k.mtrl_picker_text_input_month_abbr);
        String string3 = resources.getString(q2.k.mtrl_picker_text_input_day_abbr);
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage())) {
            pattern = pattern.replaceAll("d+", "d").replaceAll("M+", "M").replaceAll("y+", "y");
        }
        return pattern.replace("d", string3).replace("M", string2).replace("y", string);
    }

    public static Calendar f() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return calendar;
    }

    public static Calendar g(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }
}
