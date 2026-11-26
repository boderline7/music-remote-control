package r4;

import com.kugou.auto.proxy.KgMusic;
import com.navi.tool.model.SceneRule;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f8518a = new String[70];

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f8519b = new int[70];

    /* renamed from: c, reason: collision with root package name */
    public static final float[] f8520c = new float[70];

    /* renamed from: d, reason: collision with root package name */
    public static final float[] f8521d = new float[70];

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f8522e = new int[70];

    /* renamed from: f, reason: collision with root package name */
    public static final float[] f8523f = new float[70];

    /* renamed from: g, reason: collision with root package name */
    public static final float[] f8524g = new float[70];

    /* renamed from: h, reason: collision with root package name */
    public static final float[] f8525h = new float[70];

    /* renamed from: i, reason: collision with root package name */
    public static final float[] f8526i = new float[70];

    /* renamed from: j, reason: collision with root package name */
    public static final float[] f8527j = new float[70];

    /* renamed from: k, reason: collision with root package name */
    public static final int[] f8528k = new int[70];

    /* renamed from: l, reason: collision with root package name */
    public static final int[] f8529l = new int[70];

    /* renamed from: m, reason: collision with root package name */
    public static final boolean[] f8530m = new boolean[70];

    static {
        a(0, "雁阵", 0, 20.4f, 70.0f, 0, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, x2.d.DEFAULT_FADE_ANIM_DURATION, 5, false);
        a(1, "麻雀", 1, 10.2f, 95.0f, 0, 1.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 45, 4, false);
        a(2, "燕子", 2, 12.0f, 110.0f, 0, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 60, 3, false);
        a(3, "鹰", 3, 19.2f, 40.0f, 6, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, false);
        a(4, "鸽群", 4, 12.0f, 85.0f, 0, 0.9f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, z1.c.INVOKE_INTERFACE_RANGE, 6, false);
        a(5, "乌鸦", 5, 13.2f, 100.0f, 0, 0.8f, 0.3f, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 90, 3, false);
        a(6, "蝙蝠", 6, 9.6f, 70.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, 90, 4, false);
        a(7, "飞机", 7, 21.6f, 180.0f, 0, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 480, 1, false);
        a(8, "海鸥白鹭", 8, 14.4f, 80.0f, 0, 0.8f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, 90, 4, false);
        a(9, "热气球", 9, 25.2f, 22.0f, 1, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 600, 1, false);
        a(10, "直升机", 10, 20.4f, 60.0f, 0, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, false);
        a(11, "滑翔伞", 11, 20.4f, 45.0f, 0, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 480, 1, false);
        a(12, "流星", 12, 18.0f, 400.0f, 4, CropImageView.DEFAULT_ASPECT_RATIO, 1.2f, 0.1f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 30, 1, true);
        a(13, "夜航飞机", 13, 19.2f, 150.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, true);
        a(14, "孔明灯", 14, 15.6f, 18.0f, 1, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 480, 3, true);
        a(15, "夜鹭", 15, 14.4f, 75.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.8f, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, z1.c.INVOKE_INTERFACE_RANGE, 3, false);
        a(16, "椋鸟群舞", 16, 9.0f, 95.0f, 0, 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, KgMusic.imgUrlSize240, 14, false);
        a(17, "盘旋的鹰", 17, 20.4f, 38.0f, 6, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, z1.c.REM_INT_2ADDR, 1, false);
        a(18, "丹顶鹤", 18, 19.2f, 55.0f, 0, 0.3f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, x2.d.DEFAULT_FADE_ANIM_DURATION, 3, false);
        a(19, "野鸭", 19, 14.4f, 80.0f, 0, 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 90, 4, false);
        a(20, "飞艇", 20, 28.8f, 30.0f, 0, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 900, 1, false);
        a(21, "横幅飞机", 21, 24.0f, 90.0f, 0, 0.25f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, 480, 1, false);
        a(22, "飞行表演", 22, 18.0f, 120.0f, 11, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, 900, 3, false);
        a(23, "风筝", 23, 21.6f, 50.0f, 0, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 0.15f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, KgMusic.imgUrlSize240, 1, false);
        a(24, "探空气球", 24, 13.2f, 30.0f, 1, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 600, 1, false);
        a(25, "跳伞", 25, 16.8f, 40.0f, 2, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, 600, 1, false);
        a(26, "滑翔机", 26, 20.4f, 70.0f, 0, 0.25f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, false);
        a(27, "无人机", 27, 9.0f, 30.0f, 7, 0.3f, 0.3f, 0.3f, 0.2f, 0.3f, z1.c.REM_INT_2ADDR, 1, false);
        a(28, "纸飞机", 28, 12.0f, 90.0f, 2, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, 600, 1, false);
        a(29, "烟花", 29, 24.0f, CropImageView.DEFAULT_ASPECT_RATIO, 5, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.3f, 0.3f, 0.5f, z1.c.INVOKE_INTERFACE_RANGE, 1, true);
        a(30, "无人机秀", 30, 7.8f, 35.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.7f, 0.2f, 0.1f, 0.2f, 900, 10, true);
        a(31, "卫星", 31, 9.6f, 120.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.9f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(32, "彗星", 32, 20.4f, 240.0f, 4, CropImageView.DEFAULT_ASPECT_RATIO, 0.8f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, 900, 1, true);
        a(33, "流星雨", 33, 18.0f, 380.0f, 4, CropImageView.DEFAULT_ASPECT_RATIO, 1.2f, 0.05f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, z1.c.INVOKE_INTERFACE_RANGE, 4, true);
        a(34, "猫头鹰", 34, 14.4f, 45.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 0.9f, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, false);
        a(35, "夜航直升机", 35, 20.4f, 55.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, true);
        a(36, "探照灯", 36, 21.6f, CropImageView.DEFAULT_ASPECT_RATIO, 10, CropImageView.DEFAULT_ASPECT_RATIO, 0.8f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, true);
        a(37, "发光风筝", 37, 21.6f, 50.0f, 0, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, x2.d.DEFAULT_FADE_ANIM_DURATION, 1, true);
        a(38, "舞龙风筝", 38, 22.8f, 55.0f, 8, 0.6f, 0.2f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 900, 1, false);
        a(39, "心形气球", 39, 18.0f, 20.0f, 3, 0.5f, 0.3f, 0.2f, 0.1f, 0.3f, z1.c.REM_INT_2ADDR, 1, false);
        a(40, "彩色气球", 40, 20.4f, 20.0f, 1, 0.7f, 0.2f, 0.15f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, z1.c.REM_INT_2ADDR, 3, false);
        a(41, "喜鹊", 41, 14.4f, 85.0f, 0, 0.8f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, z1.c.INVOKE_INTERFACE_RANGE, 2, false);
        a(42, "女巫", 42, 21.6f, 55.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 0.7f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(43, "圣诞雪橇", 43, 27.6f, 110.0f, 0, 0.3f, 0.4f, 0.05f, 0.3f, 0.1f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(44, "零点倒计时", 44, 15.6f, CropImageView.DEFAULT_ASPECT_RATIO, 9, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.05f, 0.05f, 0.1f, 600, 1, true);
        a(45, "金龙戏珠", 45, 24.0f, 65.0f, 8, 0.6f, 0.2f, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(46, "福字灯笼", 46, 16.8f, 16.0f, 1, 0.4f, 0.3f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 600, 3, true);
        a(47, "烟花拼字", 47, 14.4f, CropImageView.DEFAULT_ASPECT_RATIO, 9, 0.4f, 1.0f, 0.05f, 0.05f, 0.2f, 90, 2, true);
        a(48, "花灯", 48, 16.8f, 18.0f, 3, 0.2f, 0.6f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, 480, 3, true);
        a(49, "拉烟画心", 49, 20.4f, CropImageView.DEFAULT_ASPECT_RATIO, 11, 0.4f, 0.4f, 0.05f, 0.05f, 0.1f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(50, "飞机喷字", 50, 20.4f, CropImageView.DEFAULT_ASPECT_RATIO, 11, 0.4f, 0.2f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(51, "丘比特之箭", 51, 19.2f, 75.0f, 0, 0.4f, 0.2f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(52, "蜈蚣风筝", 52, 19.2f, 45.0f, 8, 0.5f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 900, 1, false);
        a(53, "肥皂泡", 53, 12.0f, 22.0f, 1, 0.8f, 0.2f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, 60, 4, false);
        a(54, "龙舟", 54, 32.4f, 40.0f, 0, 0.3f, 0.1f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(55, "鹊桥相会", 55, 33.6f, 12.0f, 3, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(56, "嫦娥奔月", 56, 24.0f, 14.0f, 1, CropImageView.DEFAULT_ASPECT_RATIO, 0.7f, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(57, "玉兔提灯", 57, 16.8f, 16.0f, 1, CropImageView.DEFAULT_ASPECT_RATIO, 0.6f, 0.02f, CropImageView.DEFAULT_ASPECT_RATIO, 0.05f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(58, "彩烟开花", 58, 25.2f, CropImageView.DEFAULT_ASPECT_RATIO, 5, 0.5f, 0.3f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 900, 1, false);
        a(59, "金菊烟花", 59, 26.4f, CropImageView.DEFAULT_ASPECT_RATIO, 5, 0.2f, 0.8f, 0.05f, 0.05f, 0.3f, 900, 1, true);
        a(60, "南瓜灯", 60, 16.8f, 25.0f, 7, 0.1f, 0.7f, 0.2f, CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(61, "小幽灵", 61, 18.0f, 35.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 0.8f, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(62, "蝙蝠群舞", 62, 10.8f, 80.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 600, 7, false);
        a(63, "礼物降落伞", 63, 19.2f, 35.0f, 2, 0.3f, 0.3f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, SceneRule.MAX_DELAY_SECONDS, 2, false);
        a(64, "圣诞彩灯", 64, 18.0f, 20.0f, 8, 0.2f, 0.4f, 0.05f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, SceneRule.MAX_DELAY_SECONDS, 1, true);
        a(65, "鲜花气球", 65, 20.4f, 20.0f, 1, 0.6f, 0.2f, 0.15f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 600, 3, false);
        a(66, "领带风筝", 66, 18.0f, 48.0f, 0, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.1f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, SceneRule.MAX_DELAY_SECONDS, 1, false);
        a(67, "雪花烟花", 67, 24.0f, CropImageView.DEFAULT_ASPECT_RATIO, 5, 0.3f, 0.5f, 0.05f, 0.6f, 0.3f, 900, 1, true);
        a(68, "萤火虫", 68, 4.8f, 20.0f, 7, CropImageView.DEFAULT_ASPECT_RATIO, 1.1f, 0.6f, CropImageView.DEFAULT_ASPECT_RATIO, 0.2f, 10, 6, true);
        a(69, "落叶", 69, 8.4f, 30.0f, 2, 1.1f, 0.1f, 0.4f, CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, 20, 8, false);
    }

    public static void a(int i7, String str, int i8, float f7, float f8, int i9, float f9, float f10, float f11, float f12, float f13, int i10, int i11, boolean z6) {
        f8518a[i7] = str;
        f8519b[i7] = i8;
        f8520c[i7] = f7;
        f8521d[i7] = f8;
        f8522e[i7] = i9;
        f8523f[i7] = f9;
        f8524g[i7] = f10;
        f8525h[i7] = f11;
        f8526i[i7] = f12;
        f8527j[i7] = f13;
        f8528k[i7] = i10;
        f8529l[i7] = i11;
        f8530m[i7] = z6;
    }
}
