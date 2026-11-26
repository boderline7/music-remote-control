package com.navitool.bridge;

import java.util.Locale;

import org.json.JSONObject;

/**
 * 免费天气适配层 —— 目的地天气播报。
 *
 * 原车机助手的目的地天气（导航结束播报）走和风天气 QWeather：
 * 域名/项目 ID/凭据/Ed25519 私钥全部来自 libnavitool.so。
 * V19 去 so 版把这些 native 方法桩化后，QWeather 拿不到凭据，天气失效。
 *
 * 这里改成 Open-Meteo：无需 key、无需注册、国内可直连、HTTPS + CORS。
 * 只做协议转换，不动原 App 的 TTS / 广播 / 缓存链路：
 *   url(lon, lat)    生成 Open-Meteo 请求地址，交给原 lambda 异步取回
 *   parseNow(body)   把 Open-Meteo JSON 转成原 parseWeatherResponse 期望的 QWeather 形状
 *
 * 桌面时钟卡片那条常驻链路在 FreeWeatherDesk，只有 --free-desk-weather 才编进来。
 */
public final class FreeWeather {

    static final String ENDPOINT = "https://api.open-meteo.com/v1/forecast";
    static final String CURRENT =
            "temperature_2m,apparent_temperature,weather_code,visibility";

    private FreeWeather() {
    }

    /** 入参顺序与原 requestWeatherApi 一致：p1=经度, p2=纬度。 */
    public static String url(String lon, String lat) {
        double la = parse(lat, 28.7400);
        double lo = parse(lon, 118.6200);
        return ENDPOINT
                + "?latitude=" + la
                + "&longitude=" + lo
                + "&current=" + CURRENT
                + "&timezone=Asia%2FShanghai"
                + "&forecast_days=1";
    }

    /**
     * Open-Meteo -> QWeather now 形状。
     * 返回 null 时原链路会走「当前天气服务不可用」。
     */
    public static String parseNow(String body) {
        if (body == null || body.length() == 0) {
            return null;
        }
        try {
            JSONObject root = new JSONObject(body);
            JSONObject cur = root.getJSONObject("current");

            String text = describe(cur.optInt("weather_code", -1));
            String temp = fmt(cur.optDouble("temperature_2m", Double.NaN));
            String feels = fmt(cur.optDouble("apparent_temperature", Double.NaN));
            double vis = cur.optDouble("visibility", Double.NaN);
            String visKm = Double.isNaN(vis) ? "10"
                    : String.format(Locale.US, "%.1f", vis / 1000.0);

            JSONObject now = new JSONObject();
            now.put("text", text);
            now.put("temp", temp);
            now.put("feelsLike", feels);
            now.put("vis", visKm);

            JSONObject out = new JSONObject();
            out.put("code", "200");
            out.put("now", now);
            return out.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // ---- 下面三个是 WMO 码转换和数值格式化，FreeWeatherDesk 复用 ----

    static double parse(String s, double fallback) {
        try {
            double v = Double.parseDouble(s.trim());
            return Double.isNaN(v) || Double.isInfinite(v) ? fallback : v;
        } catch (Exception e) {
            return fallback;
        }
    }

    static String fmt(double v) {
        return Double.isNaN(v) || Double.isInfinite(v)
                ? "--" : String.format(Locale.US, "%.0f", v);
    }

    /** WMO weather_code -> 中文，对齐和风天气 text 的用法。 */
    static String describe(int c) {
        switch (c) {
            case 0: return "晴";
            case 1: return "少云";
            case 2: return "多云";
            case 3: return "阴";
            case 45: return "雾";
            case 48: return "冻雾";
            case 51: return "小毛毛雨";
            case 53: return "毛毛雨";
            case 55: return "强毛毛雨";
            case 56: return "冻毛毛雨";
            case 57: return "强冻毛毛雨";
            case 61: return "小雨";
            case 63: return "中雨";
            case 65: return "大雨";
            case 66: return "冻雨";
            case 67: return "强冻雨";
            case 71: return "小雪";
            case 73: return "中雪";
            case 75: return "大雪";
            case 77: return "米雪";
            case 80: return "阵雨";
            case 81: return "中阵雨";
            case 82: return "强阵雨";
            case 85: return "阵雪";
            case 86: return "强阵雪";
            case 95: return "雷阵雨";
            case 96: return "雷阵雨伴冰雹";
            case 97: return "强雷阵雨";
            case 99: return "强雷阵雨伴冰雹";
            default: return "天气未知";
        }
    }
}
