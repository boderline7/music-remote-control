package r4;

import android.icu.util.ChineseCalendar;
import java.util.Calendar;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final int[][] f8517a = {new int[]{524289, 44, 29, 47, 67, 59, 14}, new int[]{2, 46, 45, 29, 47, 48, 14}, new int[]{4, 48, 14, 29, 46, 40, 47}, new int[]{8, 23, 52, 38, 66, 24, 47}, new int[]{16, 51, 39, 49, 40, 41, 47}, new int[]{32, 65, 58, 39, 48, 53, 47}, new int[]{64, 54, 23, 52, 38, 24, 47}, new int[]{128, 40, 53, 28, 23, 66, 47}, new int[]{256, 55, 39, 41, 40, 51, 47}, new int[]{512, 65, 21, 39, 48, 28, 47}, new int[]{1024, 56, 57, 14, 48, 24, 47}, new int[]{2048, 21, 22, 50, 29, 26, 30, 47}, new int[]{4096, 23, 18, 14, 24, 37, 47}, new int[]{8192, 60, 61, 62, 42, 6, 5, 47}, new int[]{16384, 43, 63, 64, 67, 14, 47}, new int[]{32768, 33, 32, 12, 31, 29, 47}, new int[]{65536, 65, 39, 48, 40, 53, 47}, new int[]{131072, 66, 21, 22, 26, 23, 47}, new int[]{262144, 14, 24, 46, 67, 37, 47}};

    public static String a(int i7) {
        return i7 == 0 ? h0.FRAGMENT_ENCODE_SET : (524288 & i7) != 0 ? "除夕" : (i7 & 2) != 0 ? "春节" : (i7 & 4) != 0 ? "元宵" : (i7 & 1) != 0 ? "元旦" : (i7 & 16) != 0 ? "情人节" : (i7 & 32) != 0 ? "妇女节" : (i7 & 8) != 0 ? "清明" : (65536 & i7) != 0 ? "母亲节" : (i7 & 128) != 0 ? "儿童节" : (131072 & i7) != 0 ? "父亲节" : (i7 & 64) != 0 ? "端午" : (i7 & 256) != 0 ? "七夕" : (i7 & 512) != 0 ? "教师节" : (i7 & 1024) != 0 ? "中秋" : (i7 & 2048) != 0 ? "国庆" : (i7 & 4096) != 0 ? "重阳" : (i7 & 8192) != 0 ? "万圣节" : (i7 & 16384) != 0 ? "圣诞" : (32768 & i7) != 0 ? "流星雨" : (i7 & 262144) != 0 ? "冬至" : h0.FRAGMENT_ENCODE_SET;
    }

    public static int b() {
        Calendar calendar = Calendar.getInstance();
        int i7 = calendar.get(2) + 1;
        int i8 = calendar.get(5);
        int i9 = calendar.get(7);
        int i10 = (i7 != 1 || i8 > 3) ? 0 : 1;
        if (i7 == 2 && i8 == 14) {
            i10 |= 16;
        }
        if (i7 == 3 && i8 == 8) {
            i10 |= 32;
        }
        if (i7 == 4 && i8 >= 4 && i8 <= 6) {
            i10 |= 8;
        }
        if (i7 == 5 && i9 == 1 && i8 >= 8 && i8 <= 14) {
            i10 |= 65536;
        }
        if (i7 == 6 && i8 == 1) {
            i10 |= 128;
        }
        if (i7 == 6 && i9 == 1 && i8 >= 15 && i8 <= 21) {
            i10 |= 131072;
        }
        if (i7 == 9 && i8 == 10) {
            i10 |= 512;
        }
        if (i7 == 10 && i8 >= 1 && i8 <= 7) {
            i10 |= 2048;
        }
        if (i7 == 10 && i8 == 31) {
            i10 |= 8192;
        }
        if (i7 == 12 && i8 >= 13 && i8 <= 14) {
            i10 |= 32768;
        }
        if (i7 == 12 && (i8 == 21 || i8 == 22)) {
            i10 |= 262144;
        }
        if (i7 == 12 && i8 >= 24 && i8 <= 25) {
            i10 |= 16384;
        }
        if (i7 == 12 && i8 == 31) {
            i10 |= q0.i.ACTION_COLLAPSE;
        }
        try {
            ChineseCalendar chineseCalendar = new ChineseCalendar();
            chineseCalendar.setTimeInMillis(calendar.getTimeInMillis());
            int i11 = chineseCalendar.get(2) + 1;
            int i12 = chineseCalendar.get(5);
            boolean z6 = chineseCalendar.get(22) == 1;
            if (!z6) {
                if (i11 == 1 && i12 >= 1 && i12 <= 3) {
                    i10 |= 2;
                }
                if (i11 == 1 && i12 == 15) {
                    i10 |= 4;
                }
                if (i11 == 5 && i12 == 5) {
                    i10 |= 64;
                }
                if (i11 == 7 && i12 == 7) {
                    i10 |= 256;
                }
                if (i11 == 8 && i12 == 15) {
                    i10 |= 1024;
                }
                if (i11 == 9 && i12 == 9) {
                    i10 |= 4096;
                }
            }
            chineseCalendar.add(5, 1);
            if (z6) {
                return i10;
            }
            return (chineseCalendar.get(2) + 1 == 1 && chineseCalendar.get(5) == 1) ? i10 | q0.i.ACTION_COLLAPSE : i10;
        } catch (Throwable unused) {
            return i10;
        }
    }
}
