package o4;

import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.g0;
import com.desaysv.ivi.vdb.event.VDEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static c N;
    public final g0 A;
    public final g0 B;
    public final g0 C;
    public final g0 D;
    public final g0 E;
    public final g0 F;
    public final g0 G;
    public final g0 H;
    public final g0 I;
    public final g0 J;
    public final g0 K;
    public int L;
    public int M;

    /* renamed from: a, reason: collision with root package name */
    public final g0 f7933a;

    /* renamed from: b, reason: collision with root package name */
    public final g0 f7934b;

    /* renamed from: c, reason: collision with root package name */
    public final g0 f7935c;

    /* renamed from: d, reason: collision with root package name */
    public final g0 f7936d;

    /* renamed from: e, reason: collision with root package name */
    public final g0 f7937e;

    /* renamed from: f, reason: collision with root package name */
    public final g0 f7938f;

    /* renamed from: g, reason: collision with root package name */
    public final g0 f7939g;

    /* renamed from: h, reason: collision with root package name */
    public final g0 f7940h;

    /* renamed from: i, reason: collision with root package name */
    public final g0 f7941i;

    /* renamed from: j, reason: collision with root package name */
    public final g0 f7942j;

    /* renamed from: k, reason: collision with root package name */
    public final g0 f7943k;

    /* renamed from: l, reason: collision with root package name */
    public final g0 f7944l;

    /* renamed from: m, reason: collision with root package name */
    public final g0 f7945m;

    /* renamed from: n, reason: collision with root package name */
    public final g0 f7946n;

    /* renamed from: o, reason: collision with root package name */
    public final g0 f7947o;

    /* renamed from: p, reason: collision with root package name */
    public final g0 f7948p;

    /* renamed from: q, reason: collision with root package name */
    public final g0 f7949q;

    /* renamed from: r, reason: collision with root package name */
    public final g0 f7950r;

    /* renamed from: s, reason: collision with root package name */
    public final g0 f7951s;

    /* renamed from: t, reason: collision with root package name */
    public final g0 f7952t;

    /* renamed from: u, reason: collision with root package name */
    public final g0 f7953u;

    /* renamed from: v, reason: collision with root package name */
    public final g0 f7954v;

    /* renamed from: w, reason: collision with root package name */
    public final g0 f7955w;

    /* renamed from: x, reason: collision with root package name */
    public final g0 f7956x;

    /* renamed from: y, reason: collision with root package name */
    public final g0 f7957y;

    /* renamed from: z, reason: collision with root package name */
    public final g0 f7958z;

    public c() {
        Boolean bool = Boolean.FALSE;
        this.f7933a = new g0(bool);
        this.f7934b = new g0(bool);
        this.f7935c = new g0(bool);
        this.f7936d = new g0(bool);
        this.f7937e = new g0(bool);
        this.f7938f = new g0("--");
        this.f7939g = new g0("--");
        this.f7940h = new g0("--");
        this.f7941i = new g0("--");
        this.f7942j = new g0("--");
        this.f7943k = new g0("--");
        this.f7944l = new g0("--");
        this.f7945m = new g0("--");
        this.f7946n = new g0("--");
        this.f7947o = new g0("--");
        this.f7948p = new g0(null);
        this.f7949q = new g0("--");
        this.f7950r = new g0("--");
        this.f7951s = new g0("--");
        this.f7952t = new g0("--");
        this.f7953u = new g0("--");
        this.f7954v = new g0("--");
        this.f7955w = new g0("--");
        this.f7956x = new g0(new ConcurrentHashMap());
        this.f7957y = new g0("--");
        this.f7958z = new g0(new ConcurrentHashMap());
        this.A = new g0(new ConcurrentHashMap());
        this.B = new g0("关闭");
        this.C = new g0(new ConcurrentHashMap());
        this.D = new g0("--");
        this.E = new g0("--");
        this.F = new g0(new ConcurrentHashMap());
        this.G = new g0(new ConcurrentHashMap());
        this.H = new g0(new ConcurrentHashMap());
        this.I = new g0("--");
        this.J = new g0("--");
        this.K = new g0(-1);
        this.L = -1;
        this.M = 0;
    }

    public static String a(int i7) {
        return (i7 < 1 || i7 > 3) ? a.a.e("未知(", i7, ")") : a.a.d("香位", i7);
    }

    public static String b(int i7) {
        return i7 == 1 ? "清新自然" : i7 == 2 ? "治愈舒缓" : i7 == 3 ? "运动激情" : i7 == 0 ? "未配置" : a.a.e("未知(", i7, ")");
    }

    public static synchronized c c() {
        c cVar;
        synchronized (c.class) {
            try {
                if (N == null) {
                    N = new c();
                }
                cVar = N;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    public static String d(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? a.a.e("未知(", i7, ")") : "跳跃" : "舒缓" : "交叉" : "猫步" : "波浪";
    }

    public static String e(int i7) {
        if (i7 == 0) {
            return "关";
        }
        return i7 + " 档";
    }

    public static String f(int i7) {
        return (i7 == 0 || i7 == 2) ? "关" : (i7 == 3 || i7 == 4 || i7 == 5 || i7 == 6) ? "开" : "未知";
    }

    public static int l(int[] iArr) {
        if (iArr == null || iArr.length < 4) {
            return 0;
        }
        return (iArr[3] & 255) | ((iArr[0] & 255) << 24) | ((iArr[1] & 255) << 16) | ((iArr[2] & 255) << 8);
    }

    public static int m(int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0;
        }
        return (iArr[1] & 255) | ((iArr[0] & 255) << 8);
    }

    public final void g(int i7, int i8, int[] iArr) {
        String str;
        str = "开启";
        if (i7 == 5) {
            switch (i8) {
                case 0:
                case 2:
                    str = "关";
                    break;
                case 1:
                    str = "翘起";
                    break;
                case 3:
                    str = "开启中";
                    break;
                case 4:
                    str = "已停止";
                    break;
                case 5:
                    str = "关闭中";
                    break;
                case 6:
                    break;
                case 7:
                    str = "舒适";
                    break;
                default:
                    str = a.a.e("未知(", i8, ")");
                    break;
            }
            w("sunroof", str);
        }
        if (i7 == 10) {
            w("sunshade", (i8 < 2 || i8 > 6) ? i8 == 1 ? "关" : f(i8) : "开启");
            return;
        }
        if (i7 == 52) {
            int i9 = (iArr == null || iArr.length <= 1) ? -1 : iArr[1];
            if (i8 == 16 || i9 == 16) {
                u("memory_apply_status", "恢复失败(16)");
                u("memory_apply_slot", "--");
                return;
            }
            if (i8 >= 1 && i8 <= 3) {
                u("memory_apply_status", "成功");
                u("memory_apply_slot", "配置" + i8);
                return;
            }
            u("memory_apply_status", "未知(" + i8 + ")");
            u("memory_apply_slot", "--");
            return;
        }
        if (i7 == 54) {
            if (iArr != null && iArr.length > 1) {
                i8 = iArr[1];
            }
            u("memory_save_result", i8 != 1 ? a.a.e("失败(", i8, ")") : "成功");
            return;
        }
        if (i7 == 91) {
            g0 g0Var = this.E;
            if (i8 < 0 || i8 > 3) {
                g0Var.i("未知(" + i8 + ")");
                return;
            }
            g0Var.i(i8 + "档");
            return;
        }
        if (i7 == 139) {
            u("heat_rl", e(i8));
            return;
        }
        if (i7 == 141) {
            u("heat_rr", e(i8));
            return;
        }
        if (i7 == 168) {
            this.f7951s.i(i8 == 1 ? "锁定" : "未锁");
            return;
        }
        if (i7 == 44) {
            r("wireless_charging_state_raw", String.valueOf(i8));
            if (i8 < 0 || i8 > 3) {
                return;
            }
            r("wireless_charging_state", String.valueOf(i8));
            return;
        }
        if (i7 == 45) {
            r("wireless_charging_raw", String.valueOf(i8));
            if (i8 == 1) {
                r("wireless_charging", "开");
                return;
            } else {
                if (i8 == 0) {
                    r("wireless_charging", "关");
                    r("wireless_charging_state", "0");
                    r("wireless_charging_state_raw", "0");
                    return;
                }
                return;
            }
        }
        if (i7 == 114) {
            u("msg_pass", e(i8));
            return;
        }
        if (i7 == 115) {
            u("msg_mode_pass", d(i8));
            return;
        }
        if (i7 == 160) {
            r("steering_heat", i8 == 1 ? "开" : "关");
            return;
        }
        if (i7 == 161) {
            String e7 = a.a.e("未知(", i8, ")");
            if (i8 == 1) {
                e7 = "1档(低)";
            } else if (i8 == 2) {
                e7 = "2档";
            } else if (i8 == 3) {
                e7 = "3档";
            } else if (i8 == 4) {
                e7 = "4档";
            }
            this.D.i(e7);
            return;
        }
        if (i7 == 189) {
            q("suspension_link", i8 == 2 ? "已关联" : i8 == 1 ? "未关联" : a.a.e("未知(", i8, ")"));
            q("suspension_link_raw", String.valueOf(i8));
            return;
        }
        if (i7 == 190) {
            q("suspension_mode", i8 != 1 ? i8 == 2 ? "标准" : i8 == 3 ? "运动" : a.a.e("未知(", i8, ")") : "舒适");
            q("suspension_mode_raw", String.valueOf(i8));
            return;
        }
        if (i7 == 197) {
            u("msg_mode_driver", d(i8));
            return;
        }
        if (i7 == 198) {
            u("msg_driver", e(i8));
            return;
        }
        switch (i7) {
            case z1.c.IPUT_BYTE /* 93 */:
                u("vent_fl", e(i8));
                break;
            case z1.c.IPUT_CHAR /* 94 */:
                u("vent_fr", e(i8));
                break;
            case z1.c.IPUT_SHORT /* 95 */:
                u("heat_fl", e(i8));
                break;
            case z1.c.SGET /* 96 */:
                u("heat_fr", e(i8));
                break;
            default:
                switch (i7) {
                    case 100:
                        w("fl", f(i8));
                        break;
                    case z1.c.SGET_CHAR /* 101 */:
                        w("fr", f(i8));
                        break;
                    case z1.c.SGET_SHORT /* 102 */:
                        w("rl", f(i8));
                        break;
                    case z1.c.SPUT /* 103 */:
                        w("rr", f(i8));
                        break;
                }
        }
    }

    public final void h(int i7, int i8) {
        String e7;
        if (i7 == 1) {
            r("ac", i8 == 1 ? "开" : "关");
        } else if (i7 == 6) {
            String str = "除霜";
            switch (i8) {
                case 0:
                    str = "吹脸";
                    break;
                case 1:
                    str = "吹脸+吹脚";
                    break;
                case 2:
                    str = "吹脚";
                    break;
                case 3:
                    str = "除霜+吹脚";
                    break;
                case 4:
                case 5:
                    break;
                case 6:
                    str = "除霜+吹脸";
                    break;
                case 7:
                    str = "混合";
                    break;
                default:
                    str = a.a.e("未知(", i8, ")");
                    break;
            }
            r("mode", str);
        } else if (i7 == 8) {
            r("circ", i8 == 1 ? "外" : "内");
        } else if (i7 == 10) {
            r("temp", (i8 + 15) + "°C");
        } else if (i7 == 12) {
            r("fan", String.valueOf(i8));
        } else if (i7 == 24) {
            r("front_defrost", i8 == 1 ? "开" : "关");
        } else if (i7 != 48) {
            String str2 = "关闭";
            if (i7 == 64) {
                if (i8 == 1) {
                    str2 = "开启";
                } else if (i8 != 2 && i8 != 0) {
                    str2 = a.a.e("未知(", i8, ")");
                }
                r("fragrance_display", str2);
                r("fragrance_display_raw", String.valueOf(i8));
            } else if (i7 == 71) {
                r("fragrance_effective_pos", a(i8));
                r("fragrance_effective_pos_raw", String.valueOf(i8));
            } else if (i7 == 80) {
                r("rear_defrost", i8 == 1 ? "开" : "关");
            } else if (i7 == 3) {
                r("dual", i8 != 1 ? "开" : "关");
            } else if (i7 == 4) {
                r("auto", i8 == 1 ? "开" : "关");
            } else if (i7 == 18) {
                r("power", i8 == 1 ? "开" : "关");
            } else if (i7 != 19) {
                switch (i7) {
                    case z1.c.IF_LTZ /* 58 */:
                        r("fragrance_pos1_type", b(i8));
                        r("fragrance_pos1_type_raw", String.valueOf(i8));
                        break;
                    case z1.c.IF_GEZ /* 59 */:
                        r("fragrance_pos2_type", b(i8));
                        r("fragrance_pos2_type_raw", String.valueOf(i8));
                        break;
                    case 60:
                        r("fragrance_pos3_type", b(i8));
                        r("fragrance_pos3_type_raw", String.valueOf(i8));
                        break;
                    case z1.c.IF_LEZ /* 61 */:
                        if (i8 == 0) {
                            str2 = "AUTO";
                        } else if (i8 != 1) {
                            str2 = i8 == 2 ? "手动" : a.a.e("未知(", i8, ")");
                        }
                        r("fragrance_mode", str2);
                        r("fragrance_mode_raw", String.valueOf(i8));
                        break;
                    case 62:
                        if (i8 < 1 || i8 > 5) {
                            e7 = a.a.e("未知(", i8, ")");
                        } else {
                            e7 = i8 + "档";
                        }
                        r("fragrance_level", e7);
                        r("fragrance_level_raw", String.valueOf(i8));
                        break;
                    default:
                        switch (i7) {
                            case a0.e.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                                r("fragrance_shortage", i8 == 0 ? "正常(0)" : i8 == 1 ? "告警(1)" : a.a.e("未知(", i8, ")"));
                                r("fragrance_shortage_raw", String.valueOf(i8));
                                break;
                            case a0.e.GUIDELINE_USE_RTL /* 67 */:
                                r("rear_blower", i8 == 1 ? "开" : "关");
                                break;
                            case z1.c.AGET /* 68 */:
                                r("rear_fan", String.valueOf(i8));
                                break;
                        }
                }
            } else {
                String format = String.format("%.1f°C", Float.valueOf((i8 / 2.0f) - 55.0f));
                this.f7957y.i(format);
                r("outside_temp", format);
            }
        } else {
            r("fragrance_selected_pos", a(i8));
            r("fragrance_selected_pos_raw", String.valueOf(i8));
        }
        Map map = (Map) this.F.d();
        if (map != null) {
            this.B.i(String.format("内温:%s 外温:%s 风量:%s", (String) map.getOrDefault("temp", "--"), (String) map.getOrDefault("outside_temp", "--"), (String) map.getOrDefault("fan", "--")));
        }
    }

    public final void i(int i7, int i8) {
        String str = "运动";
        if (i7 == 4) {
            if (i8 == 0) {
                str = "经济";
            } else if (i8 == 1) {
                str = "标准";
            } else if (i8 != 2) {
                str = i8 == 3 ? "雪地" : i8 == 4 ? "泥地" : i8 == 5 ? "越野" : i8 == 7 ? "沙地" : a.a.e("未知(", i8, ")");
            }
            q("drive_mode", str);
            q("drive_mode_raw", String.valueOf(i8));
        }
        if (i7 == 8) {
            this.K.i(Integer.valueOf(i8));
            return;
        }
        if (i7 == 10) {
            this.I.i(i8 + "%");
            return;
        }
        if (i7 == 102) {
            r("pedestrian_alert", i8 == 1 ? "1" : "0");
            return;
        }
        switch (i7) {
            case 23:
                if (i8 == 1) {
                    q("brake_link", "已关联");
                } else if (i8 == 0) {
                    q("brake_link", "未关联");
                } else {
                    q("brake_link", "--");
                }
                q("brake_link_raw", String.valueOf(i8));
                break;
            case 24:
                if (i8 == 0) {
                    str = "舒适";
                } else if (i8 != 1) {
                    str = a.a.e("未知(", i8, ")");
                }
                q("brake_feel_mode", str);
                q("brake_feel_mode_raw", String.valueOf(i8));
                break;
            case 25:
                this.J.i(i8 + " %");
                break;
        }
    }

    public final void j(int i7, int i8, int[] iArr) {
        int i9;
        int m6;
        if (i7 == 1) {
            s("parking", i8 == 1);
        }
        String str = "运行";
        if (i7 == 13) {
            if (i8 == 0) {
                str = "OFF";
            } else if (i8 == 1) {
                str = "ACC";
            } else if (i8 == 2) {
                str = "ON";
            } else if (i8 == 3) {
                str = "START";
            } else if (i8 != 4) {
                str = a.a.e("未知(", i8, ")");
            }
            this.f7950r.i(str);
            return;
        }
        String str2 = "熄火";
        if (i7 == 26) {
            if (i8 != 26) {
                switch (i8) {
                    case 0:
                        break;
                    case 1:
                        str2 = "P";
                        break;
                    case 2:
                        str2 = "R";
                        break;
                    case 3:
                        str2 = "N";
                        break;
                    case 4:
                        str2 = "D";
                        break;
                    case 5:
                        str2 = "D1";
                        break;
                    case 6:
                        str2 = "D2";
                        break;
                    case 7:
                        str2 = "D3";
                        break;
                    case 8:
                        str2 = "D4";
                        break;
                    case 9:
                        str2 = "D5";
                        break;
                    case 10:
                        str2 = "D6";
                        break;
                    case 11:
                        str2 = "D7";
                        break;
                    default:
                        str2 = String.valueOf(i8);
                        break;
                }
            } else {
                str2 = "D8";
            }
            this.f7939g.i(str2);
            return;
        }
        if (i7 == 47) {
            p("hood", i8 == 1);
            return;
        }
        if (i7 == 62) {
            if (iArr == null || iArr.length < 2) {
                return;
            }
            int i10 = iArr[0] & 255;
            int i11 = 255 & iArr[1];
            g0 g0Var = this.f7940h;
            if (i10 != 0) {
                this.M = i10;
                this.L = i11;
                g0Var.i((((i10 << 8) | i11) * 4) + " rpm");
                return;
            }
            int i12 = this.L;
            if (i12 != -1) {
                int i13 = i11 - i12;
                if (i13 < -100) {
                    this.M++;
                } else if (i13 > 100 && (i9 = this.M) > 0) {
                    this.M = i9 - 1;
                }
            }
            this.L = i11;
            g0Var.i((((this.M << 8) | i11) * 4) + " rpm");
            return;
        }
        if (i7 == 68) {
            if (iArr == null || iArr.length < 2) {
                return;
            }
            this.f7941i.i(String.format("%.1f %%", Float.valueOf(m(iArr) * 0.1f)));
            return;
        }
        if (i7 == 91) {
            s("head", i8 == 1);
            return;
        }
        if (i7 == 110) {
            if (iArr == null || iArr.length < 2) {
                return;
            }
            this.f7944l.i(String.format("%.1f V", Double.valueOf((m(iArr) / 1024.0d) + 3.0d)));
            return;
        }
        if (i7 == 117) {
            q("wiper", i8 != 0 ? i8 != 1 ? i8 != 2 ? i8 != 3 ? "未知" : "高速" : "低速" : "AUTO" : "关闭");
            return;
        }
        if (i7 == 119) {
            g0 g0Var2 = this.f7938f;
            if (iArr == null || iArr.length < 2) {
                g0Var2.i(String.valueOf(i8));
                return;
            } else {
                g0Var2.i(String.valueOf(iArr[1]));
                return;
            }
        }
        if (i7 == 22) {
            s("fog", i8 == 1);
            return;
        }
        if (i7 == 23) {
            s("high", i8 == 1);
            return;
        }
        if (i7 == 28) {
            if (iArr == null || iArr.length < 4) {
                return;
            }
            int l7 = l(iArr);
            g0 g0Var3 = this.f7952t;
            if (l7 == -1 || l7 == -1) {
                g0Var3.i("-- km");
                return;
            } else {
                g0Var3.i(String.format("%.1f km", Float.valueOf(l7 / 10.0f)));
                return;
            }
        }
        if (i7 == 29) {
            if (iArr == null || iArr.length < 3) {
                return;
            }
            int i14 = iArr[1];
            int i15 = iArr[2];
            g0 g0Var4 = this.f7953u;
            if (i14 == 255 && i15 == 255) {
                g0Var4.i("--:--");
                return;
            } else {
                g0Var4.i(String.format("%02d:%02d", Integer.valueOf(i14), Integer.valueOf(i15)));
                return;
            }
        }
        if (i7 == 32) {
            s("turn_right", i8 == 1);
            return;
        }
        if (i7 == 33) {
            s("turn_left", i8 == 1);
            return;
        }
        if (i7 == 36) {
            if (iArr == null || iArr.length < 2) {
                return;
            }
            int m7 = m(iArr);
            g0 g0Var5 = this.f7954v;
            if (m7 >= 65530) {
                g0Var5.i("--");
                return;
            } else {
                g0Var5.i(String.format("%.1f L", Float.valueOf(m7 * 0.1f)));
                return;
            }
        }
        if (i7 == 37) {
            if (iArr == null || iArr.length < 2) {
                return;
            }
            int m8 = m(iArr);
            g0 g0Var6 = this.f7955w;
            if (m8 >= 65530) {
                g0Var6.i("--");
                return;
            } else {
                g0Var6.i(String.format("%.1f L", Float.valueOf(m8 * 0.1f)));
                return;
            }
        }
        if (i7 == 71) {
            g0 g0Var7 = this.f7943k;
            if (iArr != null && iArr.length >= 4) {
                g0Var7.i(l(iArr) + " km");
                return;
            }
            if (iArr != null && iArr.length >= 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(iArr.length < 3 ? 0 : ((iArr[1] & 255) << 8) | ((iArr[0] & 255) << 16) | (iArr[2] & 255));
                sb.append(" km");
                g0Var7.i(sb.toString());
                return;
            }
            if (iArr == null || iArr.length < 2) {
                return;
            }
            g0Var7.i(m(iArr) + " km");
            return;
        }
        if (i7 == 72) {
            if (iArr == null || iArr.length < 2 || (m6 = m(iArr)) >= 10000) {
                return;
            }
            this.f7942j.i(m6 + " km");
            return;
        }
        if (i7 == 74) {
            if (i8 == 0) {
                str = "熄火";
            } else if (i8 != 1) {
                str = a.a.e("未知(", i8, ")");
            }
            this.f7949q.i(str);
            return;
        }
        if (i7 == 75) {
            q("handbrake", i8 == 1 ? "拉起" : "放下");
            return;
        }
        if (i7 == 79) {
            s("hazard", i8 == 1);
            return;
        }
        if (i7 == 80) {
            q("brake", i8 == 1 ? "踩下" : "松开");
            return;
        }
        switch (i7) {
            case 15:
                p("fl", i8 == 1);
                break;
            case 16:
                p("fr", i8 == 1);
                break;
            case 17:
                p("rl", i8 == 1);
                break;
            case 18:
                p("rr", i8 == 1);
                break;
            case 19:
                p("trunk", i8 == 1);
                break;
            default:
                switch (i7) {
                    case z1.c.IPUT_SHORT /* 95 */:
                        v("fl", (i8 & 1) != 0);
                        v("fr", (i8 & 2) != 0);
                        v("rl", (i8 & 4) != 0);
                        v("rr", (i8 & 8) != 0);
                        break;
                    case z1.c.SGET /* 96 */:
                        this.f7945m.i(i8 + " %");
                        break;
                    case z1.c.SGET_WIDE /* 97 */:
                        this.f7946n.i(i8 + " %");
                        break;
                }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
    
        r0.i(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(android.content.Context r7) {
        /*
            r6 = this;
            androidx.lifecycle.g0 r0 = r6.f7948p
            java.lang.Object r1 = r0.d()
            if (r1 != 0) goto L45
            if (r7 != 0) goto Lb
            goto L45
        Lb:
            r1 = 0
            android.content.Context r7 = r7.getApplicationContext()     // Catch: java.lang.Exception -> L40
            java.lang.String r2 = "tire"
            r3 = 0
            android.content.SharedPreferences r7 = r7.getSharedPreferences(r2, r3)     // Catch: java.lang.Exception -> L40
            java.lang.String r2 = "payload"
            java.lang.String r3 = ""
            java.lang.String r2 = r7.getString(r2, r3)     // Catch: java.lang.Exception -> L40
            if (r2 == 0) goto L40
            java.lang.String r3 = r2.trim()     // Catch: java.lang.Exception -> L40
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Exception -> L40
            if (r3 == 0) goto L2c
            goto L40
        L2c:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L40
            r3.<init>(r2)     // Catch: java.lang.Exception -> L40
            o4.s r2 = o4.s.a(r3)     // Catch: java.lang.Exception -> L40
            java.lang.String r3 = "receive_ts"
            r4 = 0
            long r3 = r7.getLong(r3, r4)     // Catch: java.lang.Exception -> L40
            r2.f8029k = r3     // Catch: java.lang.Exception -> L40
            r1 = r2
        L40:
            if (r1 == 0) goto L45
            r0.i(r1)
        L45:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o4.c.k(android.content.Context):void");
    }

    public final void n(boolean z6) {
        this.f7933a.i(Boolean.valueOf(z6));
    }

    public final void o(VDEvent vDEvent) {
        int i7;
        int i8;
        Bundle bundle = vDEvent.f3190b;
        if (bundle == null) {
            return;
        }
        int i9 = vDEvent.f3189a;
        if (i9 == 29987 || i9 == 29523 || i9 == 17668 || i9 == 31014) {
            int[] intArray = bundle.getIntArray("IntVector");
            if (intArray == null || intArray.length == 0) {
                return;
            }
            int i10 = 0;
            while (i10 < intArray.length && (i7 = i10 + 2) < intArray.length) {
                int i11 = intArray[i10];
                int i12 = intArray[i10 + 1];
                int i13 = intArray[i7];
                int i14 = i10 + 3;
                int i15 = i14 + i13;
                if (i15 > intArray.length) {
                    return;
                }
                int[] iArr = new int[i13];
                System.arraycopy(intArray, i14, iArr, 0, i13);
                int i16 = i13 > 0 ? iArr[0] : 0;
                if (i11 == 254 || i12 == 110 || i12 == 13) {
                    j(i12, i16, iArr);
                } else if (i11 == 10) {
                    g(i12, i16, iArr);
                } else if (i11 == 7) {
                    h(i12, i16);
                } else if (i11 == 16) {
                    i(i12, i16);
                }
                i10 = i15;
            }
            return;
        }
        int i17 = bundle.getInt("CMD_ID", -1);
        if (i17 == -1) {
            i17 = bundle.getInt("type", -1);
        }
        if (i17 == -1) {
            i17 = bundle.getInt("id", -1);
        }
        int[] intArray2 = bundle.getIntArray("VALUE");
        if (intArray2 == null || intArray2.length <= 0) {
            int i18 = bundle.getInt("VALUE", -1);
            if (i18 == -1) {
                i18 = bundle.getInt("value", -1);
            }
            i8 = i18 == -1 ? bundle.getInt("data", -1) : i18;
        } else {
            i8 = intArray2[0];
        }
        if (i9 == 110) {
            j(z1.c.INVOKE_VIRTUAL, i8, intArray2);
            return;
        }
        if (i9 == 327684 || i9 == 26 || i9 == 68 || i9 == 71 || i9 == 72 || ((i9 >= 15 && i9 <= 19) || i9 == 47 || i9 == 110 || i17 == 110 || i9 == 74 || i9 == 28 || i9 == 29 || i9 == 13)) {
            j(i17, i8, intArray2);
            return;
        }
        if (i9 == 327681 || ((i9 >= 100 && i9 <= 103) || i9 == 45 || i9 == 52 || i9 == 54 || i9 == 93 || i9 == 94 || i9 == 95 || i9 == 96 || i9 == 114 || i9 == 139 || i9 == 141 || i9 == 198 || i9 == 168 || i9 == 161)) {
            g(i17, i8, intArray2);
            return;
        }
        if (i9 == 327690 || i9 == 10 || i9 == 12 || i9 == 1 || i9 == 8 || i9 == 6 || i9 == 3 || i9 == 24 || i9 == 80 || i9 == 67 || i9 == 68) {
            h(i17, i8);
        } else if (i9 == 327682 || i9 == 25) {
            i(i17, i8);
        }
    }

    public final void p(String str, boolean z6) {
        g0 g0Var = this.f7956x;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, Boolean.valueOf(z6));
        g0Var.i(map);
    }

    public final void q(String str, String str2) {
        g0 g0Var = this.A;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0Var.i(map);
    }

    public final void r(String str, String str2) {
        g0 g0Var = this.F;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0Var.i(map);
    }

    public final void s(String str, boolean z6) {
        g0 g0Var = this.f7958z;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, Boolean.valueOf(z6));
        g0Var.i(map);
    }

    public final void t(Context context, JSONObject jSONObject) {
        if (context != null) {
            context.getApplicationContext().getSharedPreferences("tire", 0).edit().putString("payload", jSONObject.toString()).putLong("receive_ts", System.currentTimeMillis()).apply();
        }
        this.f7948p.i(s.a(jSONObject));
        jSONObject.toString();
    }

    public final void u(String str, String str2) {
        g0 g0Var = this.G;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0Var.i(map);
    }

    public final void v(String str, boolean z6) {
        g0 g0Var = this.H;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, z6 ? "异常" : "正常");
        g0Var.i(map);
    }

    public final void w(String str, String str2) {
        g0 g0Var = this.C;
        Map map = (Map) g0Var.d();
        if (map == null) {
            map = new ConcurrentHashMap();
        }
        map.put(str, str2);
        g0Var.i(map);
    }
}
