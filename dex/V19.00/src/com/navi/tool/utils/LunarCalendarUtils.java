package com.navi.tool.utils;

import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class LunarCalendarUtils {
    private static final int MAX_YEAR = 2099;
    private static final long MILLIS_PER_DAY = 86400000;
    private static final int MIN_YEAR = 1900;
    private static final long SOLAR_TERM_BASE_UTC = -2208549300000L;
    private static final long[] LUNAR_INFO = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 23232, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448, 84835, 37744, 18936, 18800, 25776, 92326, 59984, 27296, 108228, 43744, 41696, 53987, 51552, 54615, 54432, 55888, 23893, 22176, 42704, 21972, 21200, 43448, 43344, 46240, 46758, 44368, 21920, 43940, 42416, 21168, 45683, 26928, 29495, 27296, 44368, 84821, 19296, 42352, 21732, 53600, 59752, 54560, 55968, 92838, 22224, 19168, 43476, 41680, 53584, 62034};
    private static final String[] MONTH_NAMES = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
    private static final String[] DAY_PREFIX = {"初", "十", "廿", "三"};
    private static final String[] DAY_NAMES = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    private static final String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] SOLAR_TERMS = {"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};
    private static final int[] SOLAR_TERM_INFO = {0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};

    public static final class LunarDate {
        private final int day;
        private final boolean leap;
        private final int month;
        private final int year;

        private LunarDate(int i7, int i8, int i9, boolean z6) {
            this.year = i7;
            this.month = i8;
            this.day = i9;
            this.leap = z6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getDisplayText() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.leap ? "闰" : h0.FRAGMENT_ENCODE_SET);
            sb.append(LunarCalendarUtils.formatMonth(this.month));
            sb.append("月");
            sb.append(LunarCalendarUtils.formatDay(this.day));
            sb.append("  ");
            sb.append(LunarCalendarUtils.formatGanzhiYear(this.year));
            sb.append("年");
            return sb.toString();
        }

        public int getDay() {
            return this.day;
        }

        public String getDayText() {
            return LunarCalendarUtils.formatDay(this.day);
        }

        public String getGanzhiYearText() {
            return LunarCalendarUtils.formatGanzhiYear(this.year) + "年";
        }

        public int getMonth() {
            return this.month;
        }

        public String getMonthText() {
            return LunarCalendarUtils.formatMonth(this.month) + "月";
        }

        public int getYear() {
            return this.year;
        }

        public boolean isLeap() {
            return this.leap;
        }
    }

    private LunarCalendarUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatDay(int i7) {
        if (i7 <= 0 || i7 > 30) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        if (i7 == 10) {
            return "初十";
        }
        if (i7 == 20) {
            return "二十";
        }
        if (i7 == 30) {
            return "三十";
        }
        return DAY_PREFIX[i7 / 10] + DAY_NAMES[(i7 - 1) % 10];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatGanzhiYear(int i7) {
        int i8 = i7 - 4;
        String[] strArr = HEAVENLY_STEMS;
        int floorMod = Math.floorMod(i8, strArr.length);
        String[] strArr2 = EARTHLY_BRANCHES;
        return strArr[floorMod] + strArr2[Math.floorMod(i8, strArr2.length)];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatMonth(int i7) {
        String[] strArr = MONTH_NAMES;
        return strArr[Math.max(1, Math.min(i7, strArr.length)) - 1];
    }

    private static int getLeapDays(int i7) {
        if (getLeapMonth(i7) != 0) {
            return (LUNAR_INFO[i7 + (-1900)] & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0 ? 30 : 29;
        }
        return 0;
    }

    private static int getLeapMonth(int i7) {
        return (int) (LUNAR_INFO[i7 - 1900] & 15);
    }

    public static LunarDate getLunarDate(long j7) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j7);
        if (calendar.get(1) < MIN_YEAR || calendar.get(1) > 2099) {
            return null;
        }
        return solarToLunar(calendar);
    }

    public static String getLunarText() {
        return getLunarText(System.currentTimeMillis());
    }

    public static String getLunarText(long j7) {
        LunarDate lunarDate = getLunarDate(j7);
        return lunarDate == null ? h0.FRAGMENT_ENCODE_SET : lunarDate.getDisplayText();
    }

    private static int getMonthDays(int i7, int i8) {
        return (((long) (65536 >> i8)) & LUNAR_INFO[i7 + (-1900)]) != 0 ? 30 : 29;
    }

    public static String getSolarTerm(long j7) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j7);
        int i7 = calendar.get(1);
        if (i7 >= MIN_YEAR && i7 <= 2099) {
            int i8 = calendar.get(2);
            int i9 = calendar.get(5);
            int i10 = i8 * 2;
            if (i9 == getSolarTermDay(i7, i10)) {
                return SOLAR_TERMS[i10];
            }
            int i11 = i10 + 1;
            if (i9 == getSolarTermDay(i7, i11)) {
                return SOLAR_TERMS[i11];
            }
        }
        return h0.FRAGMENT_ENCODE_SET;
    }

    private static int getSolarTermDay(int i7, int i8) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(((long) (((i7 - 1900) * 3.15569259747E10d) + (SOLAR_TERM_INFO[i8] * t1.f.T_MS_MINUTE))) + SOLAR_TERM_BASE_UTC);
        return calendar.get(5);
    }

    private static int getYearDays(int i7) {
        long j7 = LUNAR_INFO[i7 - 1900];
        int i8 = 348;
        for (int i9 = 32768; i9 > 8; i9 >>= 1) {
            i8 += (((long) i9) & j7) != 0 ? 1 : 0;
        }
        return i8 + getLeapDays(i7);
    }

    public static boolean isLunarYearEve(LunarDate lunarDate) {
        return lunarDate != null && !lunarDate.isLeap() && lunarDate.getMonth() == 12 && lunarDate.getDay() == getMonthDays(lunarDate.getYear(), 12);
    }

    private static void normalizeToDate(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }

    private static LunarDate solarToLunar(Calendar calendar) {
        int i7;
        boolean z6;
        boolean z7;
        int monthDays;
        int yearDays;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(MIN_YEAR, 0, 31);
        normalizeToDate(gregorianCalendar);
        Calendar calendar2 = (Calendar) calendar.clone();
        normalizeToDate(calendar2);
        int timeInMillis = (int) ((calendar2.getTimeInMillis() - gregorianCalendar.getTimeInMillis()) / 86400000);
        if (timeInMillis < 0) {
            return null;
        }
        int i8 = MIN_YEAR;
        while (i8 <= 2099 && timeInMillis >= (yearDays = getYearDays(i8))) {
            timeInMillis -= yearDays;
            i8++;
        }
        if (i8 > 2099) {
            return null;
        }
        int leapMonth = getLeapMonth(i8);
        int i9 = 1;
        boolean z8 = false;
        while (true) {
            if (i9 > 12) {
                i7 = i9;
                z6 = z8;
                break;
            }
            if (leapMonth <= 0 || i9 != leapMonth + 1 || z8) {
                z7 = z8;
                monthDays = getMonthDays(i8, i9);
            } else {
                i9--;
                monthDays = getLeapDays(i8);
                z7 = true;
            }
            if (timeInMillis < monthDays) {
                z6 = z7;
                i7 = i9;
                break;
            }
            timeInMillis -= monthDays;
            z8 = (z7 && i9 == leapMonth) ? false : z7;
            i9++;
        }
        return new LunarDate(i8, i7, timeInMillis + 1, z6);
    }
}
