package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.service.ModuleCrypto;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class CrashReportUploader {

    public static final class Result {
        public boolean ok;
        public String id = h0.FRAGMENT_ENCODE_SET;
        public String error = h0.FRAGMENT_ENCODE_SET;
        public String raw = h0.FRAGMENT_ENCODE_SET;
    }

    private CrashReportUploader() {
    }

    private static String readAll(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return h0.FRAGMENT_ENCODE_SET;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            String str = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
                            byteArrayOutputStream.close();
                            inputStream.close();
                            return str;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable unused) {
            return h0.FRAGMENT_ENCODE_SET;
        }
    }

    public static Result upload(Context context, String str, String str2, String str3, String str4, String str5) {
        return upload(context, str, str2, str3, str4, str5, 0);
    }

    public static Result upload(Context context, String str, String str2, String str3, String str4, String str5, int i7) {
        Result result = new Result();
        if (str != null) {
            try {
            } catch (Throwable th) {
                result.ok = false;
                result.error = th.getMessage() == null ? th.getClass().getSimpleName() : th.getMessage();
            }
            if (!str.trim().isEmpty()) {
                String licenseToken = SharedPreferencesUtils.getLicenseToken(context);
                if (licenseToken != null && !licenseToken.isEmpty()) {
                    String publicKeyBase64 = ModuleCrypto.getPublicKeyBase64();
                    if (publicKeyBase64 != null && !publicKeyBase64.isEmpty()) {
                        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
                        String signStatusRequest = NativeBridge.signStatusRequest(str, str2, valueOf);
                        if (signStatusRequest != null && !signStatusRequest.isEmpty()) {
                            String crashUploadUrl = NativeBridge.getCrashUploadUrl();
                            if (crashUploadUrl != null && !crashUploadUrl.isEmpty()) {
                                String str6 = crashUploadUrl + "?pk=" + URLEncoder.encode(publicKeyBase64, "UTF-8") + "&ts=" + URLEncoder.encode(valueOf, "UTF-8") + "&sig=" + URLEncoder.encode(signStatusRequest, "UTF-8");
                                String str7 = "----NaviCrash" + UUID.randomUUID().toString().replace("-", h0.FRAGMENT_ENCODE_SET);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                writeField(byteArrayOutputStream, str7, "tk", licenseToken);
                                writeField(byteArrayOutputStream, str7, "uid", str);
                                writeField(byteArrayOutputStream, str7, "name", str2);
                                writeField(byteArrayOutputStream, str7, "summary", str3 == null ? h0.FRAGMENT_ENCODE_SET : str3);
                                if (i7 > 0) {
                                    writeField(byteArrayOutputStream, str7, "task_id", String.valueOf(i7));
                                }
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream2);
                                if (str4 != null) {
                                    try {
                                        if (!str4.isEmpty()) {
                                            zipOutputStream.putNextEntry(new ZipEntry("logcat.txt"));
                                            zipOutputStream.write(str4.getBytes(StandardCharsets.UTF_8));
                                            zipOutputStream.closeEntry();
                                        }
                                    } finally {
                                    }
                                }
                                if (str5 != null && !str5.isEmpty()) {
                                    zipOutputStream.putNextEntry(new ZipEntry("crash.txt"));
                                    zipOutputStream.write(str5.getBytes(StandardCharsets.UTF_8));
                                    zipOutputStream.closeEntry();
                                }
                                zipOutputStream.close();
                                writeFileBytes(byteArrayOutputStream, str7, "archive", "report.zip", byteArrayOutputStream2.toByteArray());
                                byteArrayOutputStream.write(("--" + str7 + "--\r\n").getBytes(StandardCharsets.UTF_8));
                                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str6).openConnection();
                                httpURLConnection.setRequestMethod("POST");
                                httpURLConnection.setConnectTimeout(10000);
                                httpURLConnection.setReadTimeout(30000);
                                httpURLConnection.setDoOutput(true);
                                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str7);
                                httpURLConnection.setFixedLengthStreamingMode(byteArrayOutputStream.size());
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                try {
                                    byteArrayOutputStream.writeTo(outputStream);
                                    outputStream.flush();
                                    outputStream.close();
                                    int responseCode = httpURLConnection.getResponseCode();
                                    String readAll = readAll((responseCode < 200 || responseCode >= 300) ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream());
                                    result.raw = readAll;
                                    if (readAll.isEmpty()) {
                                        result.error = "http_" + responseCode;
                                        return result;
                                    }
                                    JSONObject jSONObject = new JSONObject(readAll);
                                    result.ok = jSONObject.optBoolean("ok", false);
                                    result.id = jSONObject.optString("id", h0.FRAGMENT_ENCODE_SET);
                                    if (!result.ok) {
                                        result.error = jSONObject.optString(ConfigBackupService.STAGE_ERROR, "http_" + responseCode);
                                    }
                                    return result;
                                } finally {
                                }
                            }
                            result.error = "url_empty";
                            return result;
                        }
                        result.error = "sign_failed";
                        return result;
                    }
                    result.error = "pubkey_empty";
                    return result;
                }
                result.error = "token_empty";
                return result;
            }
        }
        result.error = "uid_empty";
        return result;
    }

    private static void writeField(ByteArrayOutputStream byteArrayOutputStream, String str, String str2, String str3) {
        String str4 = "--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n";
        Charset charset = StandardCharsets.UTF_8;
        byteArrayOutputStream.write(str4.getBytes(charset));
        if (str3 == null) {
            str3 = h0.FRAGMENT_ENCODE_SET;
        }
        byteArrayOutputStream.write(str3.getBytes(charset));
        byteArrayOutputStream.write("\r\n".getBytes(charset));
    }

    private static void writeFileBytes(ByteArrayOutputStream byteArrayOutputStream, String str, String str2, String str3, byte[] bArr) {
        StringBuilder sb = new StringBuilder("--");
        sb.append(str);
        sb.append("\r\nContent-Disposition: form-data; name=\"");
        sb.append(str2);
        sb.append("\"; filename=\"");
        String i7 = a.a.i(sb, str3, "\"\r\nContent-Type: application/zip\r\n\r\n");
        Charset charset = StandardCharsets.UTF_8;
        byteArrayOutputStream.write(i7.getBytes(charset));
        byteArrayOutputStream.write(bArr);
        byteArrayOutputStream.write("\r\n".getBytes(charset));
    }
}
