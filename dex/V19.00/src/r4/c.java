package r4;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    public static final Random f8531a = new Random();

    /* renamed from: b, reason: collision with root package name */
    public static final String[] f8532b = {"新年快乐", "辞旧迎新", "岁岁平安"};

    /* renamed from: c, reason: collision with root package name */
    public static final String[] f8533c = {"新年快乐", "万象更新", "元旦快乐"};

    /* renamed from: d, reason: collision with root package name */
    public static final String[] f8534d = {"新春快乐", "恭喜发财", "万事如意"};

    /* renamed from: e, reason: collision with root package name */
    public static final String[] f8535e = {"元宵快乐", "花好月圆", "团团圆圆"};

    /* renamed from: f, reason: collision with root package name */
    public static final String[] f8536f = {"清明", "踏青", "春和景明"};

    /* renamed from: g, reason: collision with root package name */
    public static final String[] f8537g = {"520", "情人节快乐", "爱你"};

    /* renamed from: h, reason: collision with root package name */
    public static final String[] f8538h = {"女神节快乐", "节日快乐", "致最美的你"};

    /* renamed from: i, reason: collision with root package name */
    public static final String[] f8539i = {"端午安康", "粽叶飘香", "龙舟竞渡"};

    /* renamed from: j, reason: collision with root package name */
    public static final String[] f8540j = {"儿童节快乐", "童心未泯", "快乐成长"};

    /* renamed from: k, reason: collision with root package name */
    public static final String[] f8541k = {"七夕快乐", "鹊桥相会", "星河为证"};

    /* renamed from: l, reason: collision with root package name */
    public static final String[] f8542l = {"老师辛苦了", "教师节快乐", "桃李天下"};

    /* renamed from: m, reason: collision with root package name */
    public static final String[] f8543m = {"中秋快乐", "花好月圆", "月圆人圆"};

    /* renamed from: n, reason: collision with root package name */
    public static final String[] f8544n = {"国庆快乐", "举国同庆", "盛世华诞"};

    /* renamed from: o, reason: collision with root package name */
    public static final String[] f8545o = {"重阳安康", "登高望远", "九九重阳"};

    /* renamed from: p, reason: collision with root package name */
    public static final String[] f8546p = {"万圣节", "南瓜夜", "不给糖就捣蛋"};

    /* renamed from: q, reason: collision with root package name */
    public static final String[] f8547q = {"圣诞快乐", "平安夜", "Merry Xmas"};

    /* renamed from: r, reason: collision with root package name */
    public static final String[] f8548r = {"流星雨", "许个愿吧", "星河滚烫"};

    /* renamed from: s, reason: collision with root package name */
    public static final String[] f8549s = {"母亲节快乐", "妈妈辛苦了", "感恩母爱"};

    /* renamed from: t, reason: collision with root package name */
    public static final String[] f8550t = {"父亲节快乐", "老爸辛苦了", "父爱如山"};

    /* renamed from: u, reason: collision with root package name */
    public static final String[] f8551u = {"冬至", "冬至大如年", "数九寒天"};

    public static String a(int i7) {
        Calendar calendar = Calendar.getInstance();
        if ((524288 & i7) == 0) {
            return (i7 & 1) != 0 ? String.valueOf(calendar.get(1)) : h0.FRAGMENT_ENCODE_SET;
        }
        if (calendar.get(11) >= 23) {
            return String.format(Locale.ROOT, "%02d:%02d", Integer.valueOf(59 - calendar.get(12)), Integer.valueOf(59 - calendar.get(13)));
        }
        return "新年快乐";
    }

    public static String b(int i7) {
        return i7 == 0 ? h0.FRAGMENT_ENCODE_SET : (524288 & i7) != 0 ? c(f8532b) : (i7 & 2) != 0 ? c(f8534d) : (i7 & 4) != 0 ? c(f8535e) : (i7 & 1) != 0 ? c(f8533c) : (i7 & 16) != 0 ? c(f8537g) : (i7 & 32) != 0 ? c(f8538h) : (i7 & 8) != 0 ? c(f8536f) : (65536 & i7) != 0 ? c(f8549s) : (i7 & 128) != 0 ? c(f8540j) : (131072 & i7) != 0 ? c(f8550t) : (i7 & 64) != 0 ? c(f8539i) : (i7 & 256) != 0 ? c(f8541k) : (i7 & 512) != 0 ? c(f8542l) : (i7 & 1024) != 0 ? c(f8543m) : (i7 & 2048) != 0 ? c(f8544n) : (i7 & 4096) != 0 ? c(f8545o) : (i7 & 8192) != 0 ? c(f8546p) : (i7 & 16384) != 0 ? c(f8547q) : (32768 & i7) != 0 ? c(f8548r) : (i7 & 262144) != 0 ? c(f8551u) : h0.FRAGMENT_ENCODE_SET;
    }

    public static String c(String[] strArr) {
        return (strArr == null || strArr.length == 0) ? h0.FRAGMENT_ENCODE_SET : strArr[f8531a.nextInt(strArr.length)];
    }
}
