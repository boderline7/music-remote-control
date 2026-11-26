package com.navi.tool.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.navi.tool.ui.extra.p3;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MusicCoverLoader {
    private static final String TAG = "MusicCoverLoader";

    public interface Callback {
        void onCoverLoaded(Bitmap bitmap);

        void onError(String str);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int i7, int i8) {
        int i9 = options.outHeight;
        int i10 = options.outWidth;
        int i11 = 1;
        if (i9 > i8 || i10 > i7) {
            int i12 = i9 / 2;
            int i13 = i10 / 2;
            while (i12 / i11 >= i8 && i13 / i11 >= i7) {
                i11 *= 2;
            }
        }
        return i11;
    }

    public static Bitmap downloadBitmap(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.setReadTimeout(8000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    byte[] readStreamBytes = readStreamBytes(httpURLConnection.getInputStream());
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(readStreamBytes, 0, readStreamBytes.length, options);
                    options.inSampleSize = calculateInSampleSize(options, 1024, 1024);
                    options.inJustDecodeBounds = false;
                    return BitmapFactory.decodeByteArray(readStreamBytes, 0, readStreamBytes.length, options);
                }
                if (responseCode != 301 && responseCode != 302 && responseCode != 303 && responseCode != 307 && responseCode != 308) {
                    return null;
                }
                return downloadBitmap(httpURLConnection.getHeaderField("Location"));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static String getKugouCoverUrlByHash(String str) {
        JSONArray optJSONArray;
        JSONObject optJSONObject;
        String optString;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hash", str);
        jSONObject.put("type", "audio");
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("appid", 1001);
        jSONObject2.put("area_code", "1");
        jSONObject2.put("behavior", "play");
        jSONObject2.put("clientver", "9020");
        jSONObject2.put("resource", jSONArray);
        jSONObject2.put("userid", 2626431536L);
        jSONObject2.put("vip", 1);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://media.store.kugou.com/v1/get_res_privilege").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("KG-RC", "1");
        httpURLConnection.setRequestProperty("KG-THash", "expand_search_manager.cpp:852736169:451");
        httpURLConnection.setRequestProperty("User-Agent", "KuGou2012-9020-ExpandSearchManager");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(jSONObject2.toString().getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        JSONObject jSONObject3 = new JSONObject(readStream(httpURLConnection.getInputStream()));
        if (jSONObject3.optInt("error_code") != 0 || (optJSONArray = jSONObject3.optJSONArray("data")) == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.getJSONObject(0).optJSONObject("info")) == null || (optString = optJSONObject.optString("image")) == null) {
            return null;
        }
        return optString.replace("{size}", "480");
    }

    private static String getKugouCoverUrlByName(String str, String str2) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a.a.h("http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword=", URLEncoder.encode(str + " " + str2, "UTF-8"), "&page=1&pagesize=5")).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(readStream(httpURLConnection.getInputStream()));
        if (jSONObject.optInt("status") != 1 || (optJSONObject = jSONObject.optJSONObject("data")) == null || (optJSONArray = optJSONObject.optJSONArray("info")) == null || optJSONArray.length() <= 0) {
            return null;
        }
        String optString = optJSONArray.getJSONObject(0).optString("hash");
        for (int i7 = 0; i7 < optJSONArray.length(); i7++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i7);
            String optString2 = jSONObject2.optString("singername");
            if (optString2 != null && str2 != null && (optString2.contains(str2) || str2.contains(optString2))) {
                optString = jSONObject2.optString("hash");
                break;
            }
        }
        return getKugouCoverUrlByHash(optString);
    }

    private static String getKuwoCoverUrlByName(String str, String str2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a.a.h("http://search.kuwo.cn/r.s?all=", URLEncoder.encode(str + " " + str2, "UTF-8"), "&ft=music&itemset=web_2013&client=kt&rformat=json&rn=1")).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        Matcher matcher = Pattern.compile("MUSICRID\":\"MUSIC_(\\d+)\"").matcher(readStream(httpURLConnection.getInputStream()));
        if (matcher.find()) {
            return getKuwoCoverUrlByRid(matcher.group(1));
        }
        return null;
    }

    private static String getKuwoCoverUrlByRid(String str) {
        if (str != null && str.contains("MUSIC_")) {
            str = str.replace("MUSIC_", h0.FRAGMENT_ENCODE_SET);
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a.a.B("http://artistpicserver.kuwo.cn/pic.web?corp=kuwo&type=rid_pic&pictype=500&size=500&rid=", str)).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        String trim = readStream(httpURLConnection.getInputStream()).trim();
        if (trim.startsWith("http")) {
            return trim;
        }
        return null;
    }

    private static String getNeteaseCoverUrlById(String str) {
        JSONArray optJSONArray;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://music.163.com/api/song/detail/?id=" + str + "&ids=[" + str + "]").openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        httpURLConnection.setRequestProperty("Referer", "http://music.163.com/");
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(readStream(httpURLConnection.getInputStream()));
        if (jSONObject.optInt("code") != 200 || (optJSONArray = jSONObject.optJSONArray("songs")) == null || optJSONArray.length() <= 0) {
            return null;
        }
        JSONObject jSONObject2 = optJSONArray.getJSONObject(0);
        JSONObject optJSONObject = jSONObject2.optJSONObject("album");
        if (optJSONObject != null) {
            return optJSONObject.optString("picUrl");
        }
        JSONObject optJSONObject2 = jSONObject2.optJSONObject("al");
        if (optJSONObject2 != null) {
            return optJSONObject2.optString("picUrl");
        }
        return null;
    }

    private static String getNeteaseCoverUrlByName(String str, String str2) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        String optString;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a.a.h("http://music.163.com/api/search/get/web?s=", URLEncoder.encode(str + " " + str2, "UTF-8"), "&type=1&limit=1")).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(readStream(httpURLConnection.getInputStream()));
        if (jSONObject.optInt("code") != 200 || (optJSONObject = jSONObject.optJSONObject("result")) == null || (optJSONArray = optJSONObject.optJSONArray("songs")) == null || optJSONArray.length() <= 0 || (optString = optJSONArray.getJSONObject(0).optString("id")) == null || optString.isEmpty()) {
            return null;
        }
        return getNeteaseCoverUrlById(optString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadCover$0(int i7, String str, String str2, String str3, String str4, String str5, Callback callback) {
        Bitmap downloadBitmap;
        if (i7 == 6) {
            i7 = 3;
        } else if (i7 == 4) {
            i7 = 1;
        }
        String str6 = null;
        try {
            if (i7 == 1) {
                if (str != null && !str.isEmpty()) {
                    str6 = getKuwoCoverUrlByRid(str);
                }
                if (str6 == null || str6.isEmpty()) {
                    str6 = getKuwoCoverUrlByName(str2, str3);
                }
            } else if (i7 == 2) {
                if (str4 != null && !str4.isEmpty()) {
                    str6 = getKugouCoverUrlByHash(str4);
                }
                if (str6 == null || str6.isEmpty()) {
                    str6 = getKugouCoverUrlByName(str2, str3);
                }
            } else if (i7 == 3) {
                if (str5 != null && str5.matches("\\d+")) {
                    str6 = getNeteaseCoverUrlById(str5);
                }
                if (str6 == null || str6.isEmpty()) {
                    str6 = getNeteaseCoverUrlByName(str2, str3);
                }
            }
            if (str6 == null || str6.isEmpty() || (downloadBitmap = downloadBitmap(str6)) == null) {
                callback.onError("Cover not found");
            } else {
                callback.onCoverLoaded(downloadBitmap);
            }
        } catch (Exception e7) {
            callback.onError(e7.getMessage());
        }
    }

    public static void loadCover(int i7, String str, String str2, String str3, String str4, String str5, Callback callback) {
        new Thread(new p3(i7, str3, str, str2, str4, str5, callback)).start();
    }

    private static String readStream(InputStream inputStream) {
        return new String(readStreamBytes(inputStream), "UTF-8");
    }

    private static byte[] readStreamBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }
}
