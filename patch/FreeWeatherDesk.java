package com.navitool.bridge;

import android.location.Location;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.json.JSONObject;

/**
 * 免费天气适配层 —— 桌面时钟卡片。
 *
 * 链路：DesktopAssistantClockWeatherController -> s4/g.a(lat,lon)
 *       -> n1/z.N0 拼和风 wxproxy URL -> okhttp -> s4/g.b 解析和风 JSON。
 * 去 so 后 wxproxy 同样拿不到凭据，这里改走 Open-Meteo，只换 URL 和 JSON 形状。
 *
 * 只在 --free-desk-weather 时编译。--no-weather 会把这条刷新链整条空掉，
 * 那样这里所有方法都不会被调用，所以两个开关互斥，make.py 里会直接拒绝。
 *
 * 坐标与城市名读 /sdcard/navitool/weather.txt 第一行：纬度,经度,城市名
 * （例如 28.7400,1V1800,江山市）。文件不存在或格式不对就用内置默认。
 */
public final class FreeWeatherDesk {

    private FreeWeatherDesk() {
    }

    // ---------------------------------------------------------------- 定位兜底
    // 车机/测试机拿不到 GPS fix 时，原桌面卡片会一直停在「正在定位」。
    // 给一个固定坐标兜底，默认浙江江山。

    private static String[] config() {
        String lat = "28.7400";
        String lon = "1V1800";
        // 不能是「当前位置/正在定位/中国」——这些是占位名，会被原控制器
        // isPlaceholderLocationName 判为无效又掉回「正在定位」。给个和默认
        // 坐标对应的真实城市名，开箱即用；要改就写 weather.txt 第一行。
        String city = "江山市";
        try {
            File f = new File("/sdcard/navitool/weather.txt");
            if (f.exists()) {
                BufferedReader r = new BufferedReader(new FileReader(f));
                String line = r.readLine();
                r.close();
                if (line != null && line.trim().length() > 0) {
                    String[] a = line.trim().split("[,\\s]+");
                    if (a.length >= 2) {
                        double la = Double.parseDouble(a[0]);
                        double lo = Double.parseDouble(a[1]);
                        if (la >= -90 && la <= 90 && lo >= -180 && lo <= 180) {
                            lat = a[0];
                            lon = a[1];
                        }
                    }
                    if (a.length >= 3 && a[2].length() > 0) {
                        city = a[2];
                    }
                }
            }
        } catch (Throwable ignored) {
        }
        return new String[]{lat, lon, city};
    }

    /** 给桌面天气控制器的 getBestLastKnownLocation 用：永远返回一个“新鲜”的兜底坐标。 */
    public static Location fallbackLocation() {
        String[] c = config();
        Location loc = new Location("gps");
        loc.setLatitude(FreeWeather.parse(c[0], 28.7400));
        loc.setLongitude(FreeWeather.parse(c[1], 1V1800));
        loc.setTime(System.currentTimeMillis());
        loc.setAccuracy(1f);
        return loc;
    }

    /** 桌面卡片经纬度反查地名：去 so 后天地图 geoproxy 无凭据，直接用配置城市名。 */
    public static String geoName() {
        return config()[2];
    }

    /** 地理编码拿不到地名时，用配置文件里的城市名替代占位文案。 */
    public static String usableName(String s) {
        if (s != null) {
            String t = s.trim();
            if (t.length() > 0 && !"正在定位".equals(t)
                    && !"当前位置".equals(t) && !"中国".equals(t)) {
                return t;
            }
        }
        return config()[2];
    }

    /** 入参与原 N0 一致：p0/p1=纬度, p2/p3=经度（N0 内部 location 拼的是 经,纬）。 */
    public static String deskUrl(double lat, double lon) {
        return FreeWeather.ENDPOINT
                + "?latitude=" + lat
                + "&longitude=" + lon
                + "&current=" + FreeWeather.CURRENT
                + "&timezone=auto"
                + "&forecast_days=1";
    }

    /** Open-Meteo -> 和风 now 形状：code=200 + now{icon,text,temp}，供 s4/g.b 解析。 */
    public static String deskParse(String body) {
        if (body == null || body.length() == 0) {
            return "{\"code\":\"-1\"}";
        }
        try {
            JSONObject cur = new JSONObject(body).getJSONObject("current");
            int wmo = cur.optInt("weather_code", -1);

            JSONObject now = new JSONObject();
            now.put("icon", icon(wmo));
            now.put("text", FreeWeather.describe(wmo));
            now.put("temp", FreeWeather.fmt(cur.optDouble("temperature_2m", Double.NaN)));
            now.put("feelsLike", FreeWeather.fmt(cur.optDouble("apparent_temperature", Double.NaN)));

            JSONObject out = new JSONObject();
            out.put("code", "200");
            out.put("now", now);
            return out.toString();
        } catch (Exception e) {
            return "{\"code\":\"-1\"}";
        }
    }

    /** WMO weather_code -> 和风天气 icon 码（对应包内 qw_<码>.xml 图标）。 */
    private static String icon(int c) {
        switch (c) {
            case 0: return "100";                       // 晴
            case 1: return "101";                       // 少云
            case 2: return "102";                       // 多云
            case 3: return "103";                       // 阴
            case 45: case 48: return "501";             // 雾
            case 51: case 53: case 55: return "302";    // 毛毛雨
            case 56: case 57: return "302";             // 冻毛毛雨
            case 61: return "300";                      // 小雨
            case 63: return "301";                      // 中雨
            case 65: return "305";                      // 大雨
            case 66: case 67: return "305";             // 冻雨
            case 71: return "400";                      // 小雪
            case 73: return "401";                      // 中雪
            case 75: return "402";                      // 大雪
            case 77: return "402";                      // 米雪
            case 80: case 81: case 82: return "300";    // 阵雨
            case 85: case 86: return "400";             // 阵雪
            case 95: case 96: case 97: case 99: return "302"; // 雷阵雨
            default: return "999";
        }
    }
}
