package com.sagittarius.m1;

import android.os.Build;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import org.json.JSONObject;

/* renamed from: com.sagittarius.m1.a, reason: case insensitive filesystem */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class C0000a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    private static Thread.UncaughtExceptionHandler f6103a;

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f6104b = null;

    static {
        d();
    }

    public static int a(String str, JSONObject jSONObject) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty(f6104b[6], f6104b[7]);
            httpURLConnection.setRequestProperty(f6104b[8], f6104b[9]);
            httpURLConnection.setRequestMethod(f6104b[10]);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(jSONObject.toString());
            dataOutputStream.flush();
            dataOutputStream.close();
            httpURLConnection.getResponseCode();
            r1 = 200 == httpURLConnection.getResponseCode() ? 0 : 1;
            httpURLConnection.disconnect();
        } catch (Exception e7) {
        }
        return r1;
    }

    public static String a() {
        return UUID.randomUUID().toString();
    }

    private static String a(String str, String str2) {
        byte[] a7 = com.sagittarius.v6.a.a.a(str);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str2.toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        return sb.toString();
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return jSONObject;
        }
        try {
            jSONObject.put(f6104b[0], Build.VERSION.RELEASE);
            jSONObject.put(f6104b[1], Build.VERSION.SDK_INT);
            jSONObject.put(f6104b[2], Build.MANUFACTURER);
            jSONObject.put(f6104b[3], Build.MODEL);
            jSONObject.put(f6104b[4], Build.DEVICE);
            jSONObject.put(f6104b[5], Build.BRAND);
            return jSONObject;
        } catch (Exception e7) {
            return null;
        }
    }

    public static JSONObject a(JSONObject jSONObject, String str, int i7) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            try {
                jSONObject2 = new JSONObject();
            } catch (Exception e7) {
                return null;
            }
        } else {
            jSONObject2 = jSONObject;
        }
        jSONObject2.put(str, i7);
        return jSONObject2;
    }

    public static JSONObject a(JSONObject jSONObject, String str, String str2) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            try {
                jSONObject2 = new JSONObject();
            } catch (Exception e7) {
                return null;
            }
        } else {
            jSONObject2 = jSONObject;
        }
        jSONObject2.put(str, str2);
        return jSONObject2;
    }

    public static String b(JSONObject jSONObject) {
        try {
            return jSONObject.toString();
        } catch (Exception e7) {
            return null;
        }
    }

    public static void b() {
        f6103a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new C0000a());
    }

    public static void c() {
        try {
            new Thread(new b()).start();
        } catch (Exception e7) {
        }
    }

    private static void d() {
        String[] strArr = new String[12];
        f6104b = strArr;
        strArr[0] = a("HxYkMhgeFg==", "MShwY");
        f6104b[1] = a("GwcoLSQGFw==", "HCcrm");
        f6104b[2] = a("Lyg5HicjKiMeMyc7", "biwKa");
        f6104b[3] = a("Dic9Big=", "ChyCd");
        f6104b[4] = a("PBA1DQg9", "xUcDK");
        f6104b[5] = a("EiEmFBA=", "PsgZT");
        f6104b[6] = a("KjwJDhIHJ0ouDhk2", "iSgzw");
        f6104b[7] = a("OD8gJRA6LiQgFjdgOjoWNw==", "YOPIy");
        f6104b[8] = a("BDosCBMx", "EYOmc");
        f6104b[9] = a("EDc8IzMSJjgmNR9oJjw1Hw==", "qGLOZ");
        f6104b[10] = a("HT8YJg==", "MpKrJ");
        f6104b[11] = a("LnQTIBAAKg==", "lYFTy");
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        A.b(stringWriter.toString());
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = f6103a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            System.exit(-1);
        }
    }
}
