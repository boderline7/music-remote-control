package com.navi.tool.utils;

import android.util.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.service.ConfigBackupService;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import s5.a1;
import s5.h0;
import s5.k0;
import s5.l0;
import s5.m0;
import s5.o0;
import s5.r0;
import s5.v0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class Pan123Api {
    private static final String[] DATE_DIGIT_MAP;
    private static final l0 JSON;
    private static final o0 client;

    public static class CloudFileInfo {
        public String createTime;
        public String etag;
        public String fileId;
        public boolean isDir;
        public String name;
        public JsonObject raw;
        public String s3keyFlag;
        public long size;
        public int type;
    }

    public static class QrCodeResult {
        public int code;
        public int loginStatus;
        public String message;
        public String token;
    }

    public interface UploadCallback {
        void onProgress(int i7);
    }

    public interface UploadControl {
        boolean isCancelled();

        void onCall(s5.f fVar);
    }

    public static class UploadInfo {
        public String bucket;
        public String fileId;
        public String key;
        public boolean reuse;
        public long sliceSize = 16777216;
        public String storageNode;
        public String uploadId;
    }

    static {
        l0.Companion.getClass();
        JSON = k0.b("application/json;charset=UTF-8");
        DATE_DIGIT_MAP = "a,d,e,f,g,h,l,m,y,i,j,n,o,p,k,q,r,s,t,u,b,c,v,w,s,z".split(",");
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(15L, timeUnit);
        m0Var.b(30L, timeUnit);
        m0Var.f8836t = t5.b.b(30L, timeUnit);
        client = new o0(m0Var);
    }

    public static String authPost(CloudDiskCredential cloudDiskCredential, String str, String str2, String str3) {
        a1 a1Var;
        v0 create = v0.create(str2, JSON);
        r0 r0Var = new r0();
        r0Var.f(str);
        i5.a.g("body", create);
        r0Var.d("POST", create);
        r0Var.c("Referer", str3);
        r0Var.c("App-Version", "3");
        r0Var.c("Platform", "web");
        r0Var.c("Accept-Language", "zh-CN,zh;q=0.9");
        r0Var.c("Authorization", "Bearer " + cloudDiskCredential.token);
        r0Var.c("sec-fetch-dest", "empty");
        r0Var.c("sec-fetch-mode", "cors");
        r0Var.c("sec-fetch-site", "same-origin");
        r0Var.c("X-Requested-With", "com.quark.browser");
        r0Var.c("loginuuid", cloudDiskCredential.uuid);
        r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        x0 f7 = client.a(r0Var.a()).f();
        return (!f7.B() || (a1Var = f7.f8919g) == null) ? h0.FRAGMENT_ENCODE_SET : a1Var.A();
    }

    public static void completeUpload(CloudDiskCredential cloudDiskCredential, UploadInfo uploadInfo, long j7, boolean z6) {
        String str;
        if (uploadInfo == null || (str = uploadInfo.fileId) == null || str.isEmpty()) {
            throw new Exception("上传完成失败：缺少 FileId");
        }
        String str2 = cloudDiskCredential.domain + "/b/api/file/upload_complete/v2?" + sign("/b/api/file/upload_complete/v2");
        String i7 = a.a.i(new StringBuilder(), cloudDiskCredential.domain, "/");
        StringBuilder sb = new StringBuilder("{\"StorageNode\":\"");
        sb.append(escapeJson(uploadInfo.storageNode));
        sb.append("\",\"bucket\":\"");
        sb.append(escapeJson(uploadInfo.bucket));
        sb.append("\",\"fileId\":");
        sb.append(uploadInfo.fileId);
        sb.append(",\"fileSize\":");
        sb.append(j7);
        sb.append(",\"isMultipart\":");
        sb.append(z6);
        sb.append(",\"key\":\"");
        sb.append(escapeJson(uploadInfo.key));
        sb.append("\",\"uploadId\":\"");
        String authPost = authPost(cloudDiskCredential, str2, a.a.i(sb, escapeJson(uploadInfo.uploadId), "\"}"), i7);
        if (authPost == null || authPost.isEmpty()) {
            throw new Exception("上传完成请求失败");
        }
        JsonObject asJsonObject = JsonParser.parseString(authPost).getAsJsonObject();
        if (asJsonObject.has("code") && asJsonObject.get("code").getAsInt() == 0) {
        } else {
            throw new Exception((!asJsonObject.has(ConfigBackupService.EXTRA_MESSAGE) || asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).isJsonNull()) ? "上传完成出错" : asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString());
        }
    }

    public static CloudFileInfo createFolder(CloudDiskCredential cloudDiskCredential, String str, String str2) {
        String str3 = cloudDiskCredential.domain;
        if (str3 == null || str3.isEmpty()) {
            cloudDiskCredential.domain = "https://www.123pan.com";
        }
        String authPost = authPost(cloudDiskCredential, cloudDiskCredential.domain + "/b/api/file/upload_request?" + sign("/b/api/file/upload_request"), "{\"driveId\":0,\"etag\":\"\",\"fileName\":\"" + escapeJson(str2) + "\",\"parentFileId\":" + str + ",\"size\":0,\"type\":1,\"duplicate\":1,\"NotReuse\":true,\"event\":\"newCreateFolder\",\"operateType\":1,\"RequestSource\":null}", cloudDiskCredential.domain + "/?homeFilePath=" + str);
        if (authPost.isEmpty()) {
            throw new Exception("请求出错");
        }
        JsonObject asJsonObject = JsonParser.parseString(authPost).getAsJsonObject();
        if (asJsonObject.get("code").getAsInt() == 0) {
            JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("data").getAsJsonObject("Info");
            CloudFileInfo cloudFileInfo = new CloudFileInfo();
            cloudFileInfo.raw = asJsonObject2;
            cloudFileInfo.name = asJsonObject2.get("FileName").getAsString();
            cloudFileInfo.fileId = asJsonObject2.get("FileId").getAsString();
            cloudFileInfo.isDir = true;
            return cloudFileInfo;
        }
        String asString = asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
        if (asString != null && asString.contains("token is expired")) {
            throw new Exception("登录失效，请重新登录123网盘");
        }
        if (asString == null || asString.isEmpty()) {
            asString = "新建出错";
        }
        throw new Exception(asString);
    }

    public static x0 downloadFileStream(String str, String str2) {
        r0 r0Var = new r0();
        r0Var.f(str);
        r0Var.b();
        r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        r0Var.c("Referer", str2);
        r0Var.c("Accept-Language", "zh-CN,zh;q=0.9");
        return client.a(r0Var.a()).f();
    }

    private static String escapeJson(String str) {
        return str == null ? h0.FRAGMENT_ENCODE_SET : str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    public static String generateQrCode(CloudDiskCredential cloudDiskCredential) {
        a1 a1Var;
        a1 a1Var2;
        cloudDiskCredential.domain = "https://www.123pan.com";
        cloudDiskCredential.uuid = generateUuid();
        String str = h0.FRAGMENT_ENCODE_SET;
        int i7 = 0;
        IOException e7 = null;
        String str2 = h0.FRAGMENT_ENCODE_SET;
        int i8 = 0;
        while (i8 < 3) {
            i8++;
            try {
                r0 r0Var = new r0();
                r0Var.f("https://login.123pan.com/api/user/qr-code/generate");
                r0Var.b();
                r0Var.c("Referer", "https://login.123pan.com/centerlogin?redirect_url=https%3A%2F%2Fwww.123pan.com&source_page=website");
                r0Var.c("loginuuid", cloudDiskCredential.uuid);
                r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
                x0 f7 = client.a(r0Var.a()).f();
                if (f7.B() && (a1Var2 = f7.f8919g) != null) {
                    str2 = a1Var2.A();
                    str2.substring(0, Math.min(200, str2.length()));
                    break;
                }
            } catch (IOException e8) {
                e7 = e8;
                e7.getMessage();
            }
        }
        if (str2.isEmpty()) {
            cloudDiskCredential.domain = "https://www.123pan.cn";
            while (i7 < 3) {
                i7++;
                try {
                    r0 r0Var2 = new r0();
                    r0Var2.f("https://login.123pan.com/api/user/qr-code/generate");
                    r0Var2.b();
                    r0Var2.c("Referer", "https://login.123pan.com/centerlogin?redirect_url=https%3A%2F%2Fwww.123pan.com&source_page=website");
                    r0Var2.c("loginuuid", cloudDiskCredential.uuid);
                    r0Var2.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
                    x0 f8 = client.a(r0Var2.a()).f();
                    if (f8.B() && (a1Var = f8.f8919g) != null) {
                        str2 = a1Var.A();
                        break;
                    }
                } catch (IOException unused) {
                }
            }
        }
        if (str2.isEmpty()) {
            StringBuilder sb = new StringBuilder("无法获取二维码");
            if (e7 != null) {
                str = ": " + e7.getMessage();
            }
            sb.append(str);
            throw new Exception(sb.toString());
        }
        JsonObject asJsonObject = JsonParser.parseString(str2).getAsJsonObject();
        int asInt = asJsonObject.get("code").getAsInt();
        if (asInt != 0) {
            throw new Exception("服务端返回错误(" + asInt + "): " + (asJsonObject.has(ConfigBackupService.EXTRA_MESSAGE) ? asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString() : "未知错误"));
        }
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("data");
        cloudDiskCredential.uniId = asJsonObject2.get("uniID").getAsString();
        String asString = asJsonObject2.get("url").getAsString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(asString);
        sb2.append("?env=production&uniID=");
        return a.a.i(sb2, cloudDiskCredential.uniId, "&source=123pan&type=login");
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", h0.FRAGMENT_ENCODE_SET);
    }

    public static String getDownloadUrl(CloudDiskCredential cloudDiskCredential, CloudFileInfo cloudFileInfo, String str) {
        a1 a1Var;
        String str2 = cloudDiskCredential.domain + "/b/api/file/download_info?" + sign("/b/api/file/download_info");
        String str3 = cloudDiskCredential.domain + "/?homeFilePath=" + str;
        StringBuilder sb = new StringBuilder("{\"driveId\":0,\"etag\":\"");
        String str4 = cloudFileInfo.etag;
        String str5 = h0.FRAGMENT_ENCODE_SET;
        if (str4 == null) {
            str4 = h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(str4);
        sb.append("\",\"fileId\":");
        sb.append(cloudFileInfo.fileId);
        sb.append(",\"fileName\":\"");
        sb.append(escapeJson(cloudFileInfo.name));
        sb.append("\",\"s3keyFlag\":\"");
        String str6 = cloudFileInfo.s3keyFlag;
        if (str6 != null) {
            str5 = str6;
        }
        sb.append(str5);
        sb.append("\",\"size\":");
        sb.append(cloudFileInfo.size);
        sb.append(",\"type\":");
        sb.append(cloudFileInfo.type);
        sb.append("}");
        String authPost = authPost(cloudDiskCredential, str2, sb.toString(), str3);
        if (authPost.isEmpty()) {
            throw new Exception("获取下载链接失败");
        }
        JsonObject asJsonObject = JsonParser.parseString(authPost).getAsJsonObject();
        if (asJsonObject.get("code").getAsInt() != 0) {
            String asString = asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
            if (asString != null && asString.contains("token is expired")) {
                throw new Exception("登录失效，请重新登录123网盘");
            }
            if (asString == null || asString.isEmpty()) {
                asString = "获取失败";
            }
            throw new Exception(asString);
        }
        String asString2 = asJsonObject.getAsJsonObject("data").get("DownloadUrl").getAsString();
        int indexOf = asString2.indexOf("params=");
        if (indexOf == -1) {
            throw new Exception("解析下载链接失败");
        }
        String substring = asString2.substring(indexOf + 7);
        int indexOf2 = substring.indexOf("&");
        if (indexOf2 != -1) {
            substring = substring.substring(0, indexOf2);
        }
        String str7 = new String(Base64.decode(substring, 0));
        String str8 = str7.substring(0, str7.indexOf("/", 10)) + "/";
        r0 r0Var = new r0();
        r0Var.f(str7);
        r0Var.b();
        r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        r0Var.c("Referer", str8);
        x0 f7 = client.a(r0Var.a()).f();
        if (!f7.B() || (a1Var = f7.f8919g) == null) {
            throw new Exception("请求下载链接失败");
        }
        JsonObject asJsonObject2 = JsonParser.parseString(a1Var.A()).getAsJsonObject();
        if (asJsonObject2.get("code").getAsInt() == 0) {
            return asJsonObject2.getAsJsonObject("data").get("redirect_url").getAsString();
        }
        throw new Exception("获取下载重定向失败");
    }

    public static String getFileMd5(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[q0.i.ACTION_DISMISS];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b7 : digest) {
                sb.append(String.format("%02x", Integer.valueOf(b7 & 255)));
            }
            String sb2 = sb.toString();
            fileInputStream.close();
            return sb2;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    private static Map<String, String> getPresignedUrls(CloudDiskCredential cloudDiskCredential, UploadInfo uploadInfo, boolean z6, int i7, int i8) {
        String str = z6 ? "/b/api/file/s3_repare_upload_parts_batch" : "/b/api/file/s3_upload_object/auth";
        String str2 = cloudDiskCredential.domain + str + "?" + sign(str);
        String i9 = a.a.i(new StringBuilder(), cloudDiskCredential.domain, "/");
        StringBuilder sb = new StringBuilder("{");
        if (z6) {
            sb.append("\"bucket\":\"");
            sb.append(escapeJson(uploadInfo.bucket));
            sb.append("\",\"key\":\"");
            sb.append(escapeJson(uploadInfo.key));
            sb.append("\",\"partNumberEnd\":");
            sb.append(i8);
            sb.append(",\"partNumberStart\":");
            sb.append(i7);
            sb.append(",\"uploadId\":\"");
            sb.append(escapeJson(uploadInfo.uploadId));
            sb.append("\",\"StorageNode\":\"");
            sb.append(escapeJson(uploadInfo.storageNode));
            sb.append("\"");
        } else {
            sb.append("\"StorageNode\":\"");
            sb.append(escapeJson(uploadInfo.storageNode));
            sb.append("\",\"bucket\":\"");
            sb.append(escapeJson(uploadInfo.bucket));
            sb.append("\",\"key\":\"");
            sb.append(escapeJson(uploadInfo.key));
            sb.append("\",\"partNumberEnd\":");
            sb.append(i8);
            sb.append(",\"partNumberStart\":");
            sb.append(i7);
            sb.append(",\"uploadId\":\"");
            sb.append(escapeJson(uploadInfo.uploadId));
            sb.append("\"");
        }
        sb.append("}");
        String authPost = authPost(cloudDiskCredential, str2, sb.toString(), i9);
        String str3 = "获取上传地址失败";
        if (authPost == null || authPost.isEmpty()) {
            throw new Exception("获取上传地址失败");
        }
        JsonObject asJsonObject = JsonParser.parseString(authPost).getAsJsonObject();
        if (!asJsonObject.has("code") || asJsonObject.get("code").getAsInt() != 0) {
            if (asJsonObject.has(ConfigBackupService.EXTRA_MESSAGE) && !asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).isJsonNull()) {
                str3 = asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
            }
            throw new Exception(str3);
        }
        HashMap hashMap = new HashMap();
        if (asJsonObject.has("data") && asJsonObject.get("data").isJsonObject()) {
            JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("data");
            if (asJsonObject2.has("presignedUrls") && asJsonObject2.get("presignedUrls").isJsonObject()) {
                for (Map.Entry<String, JsonElement> entry : asJsonObject2.getAsJsonObject("presignedUrls").entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue().getAsString());
                }
            }
        }
        Objects.toString(hashMap.keySet());
        return hashMap;
    }

    public static JsonObject getRequest(CloudDiskCredential cloudDiskCredential, String str) {
        a1 a1Var;
        String str2 = cloudDiskCredential.uuid;
        if (str2 == null || str2.isEmpty()) {
            cloudDiskCredential.uuid = generateUuid();
        }
        r0 r0Var = new r0();
        r0Var.f(str);
        r0Var.b();
        r0Var.c("Referer", "https://login.123pan.com/centerlogin?redirect_url=https%3A%2F%2Fwww.123pan.com&source_page=website");
        r0Var.c("loginuuid", cloudDiskCredential.uuid);
        r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        x0 f7 = client.a(r0Var.a()).f();
        if (!f7.B() || (a1Var = f7.f8919g) == null) {
            throw new Exception("请求出错");
        }
        return JsonParser.parseString(a1Var.A()).getAsJsonObject();
    }

    public static UploadInfo getUploadUrl(CloudDiskCredential cloudDiskCredential, String str, String str2, long j7, String str3) {
        String str4 = cloudDiskCredential.domain;
        if (str4 == null || str4.isEmpty()) {
            cloudDiskCredential.domain = "https://www.123pan.com";
        }
        String authPost = authPost(cloudDiskCredential, cloudDiskCredential.domain + "/b/api/file/upload_request?" + sign("/b/api/file/upload_request"), "{\"RequestSource\":null,\"driveId\":0,\"duplicate\":2,\"etag\":\"" + escapeJson(str3) + "\",\"fileName\":\"" + escapeJson(str2) + "\",\"parentFileId\":" + str + ",\"size\":" + j7 + ",\"type\":0}", cloudDiskCredential.domain + "/?homeFilePath=" + str);
        if (authPost.isEmpty()) {
            throw new Exception("上传请求发送失败");
        }
        JsonObject asJsonObject = JsonParser.parseString(authPost).getAsJsonObject();
        authPost.substring(0, Math.min(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, authPost.length()));
        if (asJsonObject.get("code").getAsInt() != 0) {
            String asString = (!asJsonObject.has(ConfigBackupService.EXTRA_MESSAGE) || asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).isJsonNull()) ? "上传出错" : asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
            if (asString.contains("token is expired")) {
                throw new Exception("登录失效，请重新登录123网盘");
            }
            throw new Exception(asString);
        }
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("data");
        if (asJsonObject2.has("Reuse") && asJsonObject2.get("Reuse").getAsBoolean() && asJsonObject2.has("Info") && !asJsonObject2.get("Info").isJsonNull()) {
            String valueOf = String.valueOf(asJsonObject2.getAsJsonObject("Info").get("FileId").getAsLong());
            UploadInfo uploadInfo = new UploadInfo();
            uploadInfo.reuse = true;
            uploadInfo.fileId = valueOf;
            return uploadInfo;
        }
        String asString2 = asJsonObject2.has("Bucket") ? asJsonObject2.get("Bucket").getAsString() : null;
        String asString3 = asJsonObject2.has("Key") ? asJsonObject2.get("Key").getAsString() : null;
        String asString4 = asJsonObject2.has("UploadId") ? asJsonObject2.get("UploadId").getAsString() : null;
        String asString5 = asJsonObject2.has("StorageNode") ? asJsonObject2.get("StorageNode").getAsString() : null;
        String valueOf2 = asJsonObject2.has("FileId") ? String.valueOf(asJsonObject2.get("FileId").getAsLong()) : null;
        if (asString2 == null || asString2.isEmpty() || asString3 == null || asString3.isEmpty() || asString4 == null || asString4.isEmpty() || asString5 == null || asString5.isEmpty()) {
            Objects.toString(asJsonObject2.keySet());
            throw new Exception("上传地址信息不完整");
        }
        long j8 = 16777216;
        try {
            if (asJsonObject2.has("SliceSize")) {
                j8 = Long.parseLong(asJsonObject2.get("SliceSize").getAsString());
            }
        } catch (Exception unused) {
        }
        UploadInfo uploadInfo2 = new UploadInfo();
        uploadInfo2.fileId = valueOf2;
        uploadInfo2.uploadId = asString4;
        uploadInfo2.bucket = asString2;
        uploadInfo2.key = asString3;
        uploadInfo2.storageNode = asString5;
        uploadInfo2.sliceSize = j8;
        uploadInfo2.reuse = false;
        return uploadInfo2;
    }

    public static List<CloudFileInfo> listFiles(CloudDiskCredential cloudDiskCredential, String str, int i7) {
        String str2;
        int i8;
        String str3;
        String str4;
        String str5;
        int i9;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        CloudDiskCredential cloudDiskCredential2;
        String str15;
        String str16;
        String str17;
        String str18;
        String str19;
        int i10;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        r0 r0Var;
        String str25;
        String str26;
        String str27;
        x0 f7;
        a1 a1Var;
        String str28;
        String str29;
        x0 f8;
        int i11;
        a1 a1Var2;
        String str30 = cloudDiskCredential.domain;
        if (str30 == null || str30.isEmpty()) {
            cloudDiskCredential.domain = "https://www.123pan.com";
        }
        String str31 = str.equals("0") ? "1" : "4";
        String sign = sign("/b/api/file/list/new");
        StringBuilder sb = new StringBuilder();
        sb.append(cloudDiskCredential.domain);
        String str32 = "/b/api/file/list/new?";
        sb.append("/b/api/file/list/new?");
        sb.append(sign);
        String str33 = "&driveId=0&limit=100&next=0&orderBy=file_id&orderDirection=desc&parentFileId=";
        sb.append("&driveId=0&limit=100&next=0&orderBy=file_id&orderDirection=desc&parentFileId=");
        sb.append(str);
        String str34 = "&trashed=false&SearchData=&Page=";
        sb.append("&trashed=false&SearchData=&Page=");
        int i12 = i7 - 1;
        sb.append(i12);
        String str35 = "&OnlyLookAbnormalFile=0&event=homeListFile&operateType=";
        sb.append("&OnlyLookAbnormalFile=0&event=homeListFile&operateType=");
        sb.append(str31);
        String str36 = "&inDirectSpace=false";
        sb.append("&inDirectSpace=false");
        String sb2 = sb.toString();
        String str37 = cloudDiskCredential.domain + "/?homeFilePath=" + str;
        String str38 = cloudDiskCredential.token;
        if (str38 != null) {
            str2 = "/?homeFilePath=";
            str38.substring(0, Math.min(20, str38.length()));
        } else {
            str2 = "/?homeFilePath=";
        }
        String str39 = h0.FRAGMENT_ENCODE_SET;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            String str40 = str39;
            i8 = i14;
            str3 = str31;
            str4 = str36;
            str5 = str35;
            i9 = i12;
            str6 = str34;
            str7 = str33;
            str8 = sign;
            str9 = str32;
            str10 = "Authorization";
            str11 = "Bearer ";
            str12 = "User-Agent";
            str13 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";
            str14 = "loginuuid";
            if (i13 >= 2) {
                cloudDiskCredential2 = cloudDiskCredential;
                str15 = "3";
                str16 = "X-Requested-With";
                str17 = "com.quark.browser";
                str18 = "sec-fetch-site";
                str19 = str40;
                break;
            }
            int i15 = i13 + 1;
            try {
                r0 r0Var2 = new r0();
                r0Var2.f(sb2);
                r0Var2.b();
                r0Var2.c("Referer", str37);
                r0Var2.c("App-Version", "3");
                r0Var2.c("Platform", "web");
                r0Var2.c("Accept-Language", "zh-CN,zh;q=0.9");
                r0Var2.c("sec-fetch-dest", "empty");
                r0Var2.c("sec-fetch-mode", "cors");
                r0Var2.c("sec-fetch-site", "same-origin");
                str18 = "sec-fetch-site";
                r0Var2.c("X-Requested-With", "com.quark.browser");
                str17 = "com.quark.browser";
                str28 = sb2;
                cloudDiskCredential2 = cloudDiskCredential;
                str29 = str37;
                str15 = "3";
                try {
                    str16 = "X-Requested-With";
                    r0Var2.c(str14, cloudDiskCredential2.uuid);
                    str14 = str14;
                    r0Var2.c(str12, str13);
                    str13 = str13;
                    StringBuilder sb3 = new StringBuilder();
                    str12 = str12;
                    sb3.append(str11);
                    str11 = str11;
                    sb3.append(cloudDiskCredential2.token);
                    r0Var2.c(str10, sb3.toString());
                    f8 = client.a(r0Var2.a()).f();
                    i11 = f8.f8916d;
                    try {
                    } catch (IOException e7) {
                        e = e7;
                        i8 = i11;
                        str39 = str40;
                        i14 = i8;
                        e.getMessage();
                        str31 = str3;
                        str36 = str4;
                        str35 = str5;
                        i12 = i9;
                        str34 = str6;
                        str33 = str7;
                        sign = str8;
                        str32 = str9;
                        sb2 = str28;
                        str37 = str29;
                        i13 = i15;
                    }
                } catch (IOException e8) {
                    e = e8;
                }
            } catch (IOException e9) {
                e = e9;
                str28 = sb2;
                str29 = str37;
                cloudDiskCredential2 = cloudDiskCredential;
            }
            if (!f8.B() || (a1Var2 = f8.f8919g) == null) {
                str39 = str40;
                i14 = i11;
                str31 = str3;
                str36 = str4;
                str35 = str5;
                i12 = i9;
                str34 = str6;
                str33 = str7;
                sign = str8;
                str32 = str9;
                sb2 = str28;
                str37 = str29;
                i13 = i15;
            } else {
                i8 = i11;
                str19 = a1Var2.A();
                try {
                    str10 = str10;
                    str19.substring(0, Math.min(200, str19.length()));
                    break;
                } catch (IOException e10) {
                    e = e10;
                    str39 = str19;
                    i14 = i8;
                    e.getMessage();
                    str31 = str3;
                    str36 = str4;
                    str35 = str5;
                    i12 = i9;
                    str34 = str6;
                    str33 = str7;
                    sign = str8;
                    str32 = str9;
                    sb2 = str28;
                    str37 = str29;
                    i13 = i15;
                }
            }
        }
        int i16 = i8;
        if (str19.isEmpty()) {
            String str41 = cloudDiskCredential2.domain;
            String str42 = str19;
            if (str41 == null || !str41.contains("123pan.com")) {
                i10 = i16;
            } else {
                cloudDiskCredential2.domain = "https://www.123pan.cn";
                StringBuilder sb4 = new StringBuilder();
                sb4.append(cloudDiskCredential2.domain);
                sb4.append(str9);
                sb4.append(str8);
                sb4.append(str7);
                i10 = i16;
                sb4.append(str);
                String str43 = str18;
                sb4.append(str6);
                sb4.append(i9);
                sb4.append(str5);
                String str44 = "same-origin";
                String i17 = a.a.i(sb4, str3, str4);
                String str45 = cloudDiskCredential2.domain + str2 + str;
                int i18 = 0;
                while (i18 < 2) {
                    int i19 = i18 + 1;
                    try {
                        r0Var = new r0();
                        r0Var.f(i17);
                        r0Var.b();
                        r0Var.c("Referer", str45);
                        r0Var.c("App-Version", str15);
                        r0Var.c("Platform", "web");
                        r0Var.c("Accept-Language", "zh-CN,zh;q=0.9");
                        r0Var.c("sec-fetch-dest", "empty");
                        r0Var.c("sec-fetch-mode", "cors");
                        str20 = str45;
                        str22 = str44;
                        str25 = str43;
                    } catch (IOException e11) {
                        e = e11;
                        str20 = str45;
                        str21 = i17;
                        str22 = str44;
                    }
                    try {
                        r0Var.c(str25, str22);
                        str43 = str25;
                        str21 = i17;
                        String str46 = str17;
                        String str47 = str16;
                        try {
                            r0Var.c(str47, str46);
                            str23 = str46;
                            try {
                                str24 = str47;
                                String str48 = str14;
                                try {
                                    r0Var.c(str48, cloudDiskCredential2.uuid);
                                    str14 = str48;
                                    str26 = str12;
                                    String str49 = str13;
                                    try {
                                        r0Var.c(str26, str49);
                                        str13 = str49;
                                    } catch (IOException e12) {
                                        e = e12;
                                        str13 = str49;
                                    }
                                } catch (IOException e13) {
                                    e = e13;
                                    str14 = str48;
                                }
                            } catch (IOException e14) {
                                e = e14;
                                str24 = str47;
                                e.getMessage();
                                str45 = str20;
                                i17 = str21;
                                str44 = str22;
                                i18 = i19;
                                str16 = str24;
                                str17 = str23;
                            }
                            try {
                                StringBuilder sb5 = new StringBuilder();
                                str12 = str26;
                                String str50 = str11;
                                try {
                                    sb5.append(str50);
                                    str11 = str50;
                                    try {
                                        sb5.append(cloudDiskCredential2.token);
                                        str27 = str10;
                                        try {
                                            r0Var.c(str27, sb5.toString());
                                            f7 = client.a(r0Var.a()).f();
                                        } catch (IOException e15) {
                                            e = e15;
                                            str10 = str27;
                                        }
                                    } catch (IOException e16) {
                                        e = e16;
                                    }
                                } catch (IOException e17) {
                                    e = e17;
                                    str11 = str50;
                                }
                            } catch (IOException e18) {
                                e = e18;
                                str12 = str26;
                                e.getMessage();
                                str45 = str20;
                                i17 = str21;
                                str44 = str22;
                                i18 = i19;
                                str16 = str24;
                                str17 = str23;
                            }
                        } catch (IOException e19) {
                            e = e19;
                            str23 = str46;
                        }
                    } catch (IOException e20) {
                        e = e20;
                        str43 = str25;
                        str21 = i17;
                        str23 = str17;
                        str24 = str16;
                        e.getMessage();
                        str45 = str20;
                        i17 = str21;
                        str44 = str22;
                        i18 = i19;
                        str16 = str24;
                        str17 = str23;
                    }
                    if (!f7.B() || (a1Var = f7.f8919g) == null) {
                        str10 = str27;
                        str45 = str20;
                        i17 = str21;
                        str44 = str22;
                        i18 = i19;
                        str16 = str24;
                        str17 = str23;
                    } else {
                        str19 = a1Var.A();
                        try {
                            str10 = str27;
                        } catch (IOException e21) {
                            e = e21;
                            str10 = str27;
                        }
                        try {
                            str19.substring(0, Math.min(200, str19.length()));
                            break;
                        } catch (IOException e22) {
                            e = e22;
                            str42 = str19;
                            e.getMessage();
                            str45 = str20;
                            i17 = str21;
                            str44 = str22;
                            i18 = i19;
                            str16 = str24;
                            str17 = str23;
                        }
                    }
                }
            }
            str19 = str42;
            if (str19.isEmpty()) {
                throw new Exception(a.a.d("请求失败 HTTP=", i10));
            }
        }
        JsonObject asJsonObject = JsonParser.parseString(str19).getAsJsonObject();
        if (asJsonObject.get("code").getAsInt() != 0) {
            String asString = asJsonObject.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
            if (asString != null && asString.contains("token is expired")) {
                throw new Exception("登录失效，请重新登录123网盘");
            }
            if (asString == null || asString.isEmpty()) {
                asString = "出错";
            }
            throw new Exception(asString);
        }
        ArrayList arrayList = new ArrayList();
        JsonArray asJsonArray = asJsonObject.getAsJsonObject("data").getAsJsonArray("InfoList");
        if (asJsonArray != null) {
            Iterator<JsonElement> it = asJsonArray.iterator();
            while (it.hasNext()) {
                JsonObject asJsonObject2 = it.next().getAsJsonObject();
                CloudFileInfo cloudFileInfo = new CloudFileInfo();
                cloudFileInfo.raw = asJsonObject2;
                cloudFileInfo.name = asJsonObject2.get("FileName").getAsString();
                cloudFileInfo.fileId = String.valueOf(asJsonObject2.get("FileId").getAsLong());
                boolean z6 = asJsonObject2.get("Type").getAsInt() == 1;
                cloudFileInfo.isDir = z6;
                if (!z6) {
                    cloudFileInfo.size = asJsonObject2.get("Size").getAsLong();
                }
                String replace = asJsonObject2.get("CreateAt").getAsString().replace("T", " ");
                cloudFileInfo.createTime = replace;
                int indexOf = replace.indexOf("+");
                if (indexOf == -1) {
                    indexOf = cloudFileInfo.createTime.indexOf(".");
                }
                if (indexOf != -1) {
                    cloudFileInfo.createTime = cloudFileInfo.createTime.substring(0, indexOf);
                }
                try {
                    cloudFileInfo.etag = asJsonObject2.get("Etag").getAsString();
                } catch (Exception unused) {
                }
                try {
                    cloudFileInfo.s3keyFlag = asJsonObject2.get("S3KeyFlag").getAsString();
                } catch (Exception unused2) {
                }
                try {
                    cloudFileInfo.type = asJsonObject2.get("Type").getAsInt();
                } catch (Exception unused3) {
                }
                arrayList.add(cloudFileInfo);
            }
        }
        return arrayList;
    }

    private static long md5Hash(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            long j7 = 0;
            for (int i7 = 0; i7 < 8 && i7 < digest.length; i7++) {
                j7 = (j7 << 8) | (digest[i7] & 255);
            }
            return Math.abs(j7);
        } catch (NoSuchAlgorithmException unused) {
            return Math.abs(str.hashCode());
        }
    }

    public static QrCodeResult pollQrCodeResult(CloudDiskCredential cloudDiskCredential) {
        JsonObject request = getRequest(cloudDiskCredential, "https://login.123pan.com/api/user/qr-code/result?uniID=" + cloudDiskCredential.uniId);
        QrCodeResult qrCodeResult = new QrCodeResult();
        int asInt = request.get("code").getAsInt();
        if (asInt == 200) {
            qrCodeResult.code = 200;
            qrCodeResult.token = request.getAsJsonObject("data").get("token").getAsString();
            return qrCodeResult;
        }
        qrCodeResult.code = asInt;
        if (asInt == 0) {
            int asInt2 = request.getAsJsonObject("data").get("loginStatus").getAsInt();
            qrCodeResult.loginStatus = asInt2;
            if (asInt2 == 3) {
                JsonObject postRequest = postRequest(cloudDiskCredential, "https://login.123pan.com/api/user/qr-code/wx_code", "{\"uniID\":\"" + cloudDiskCredential.uniId + "\"}");
                if (postRequest.get("code").getAsInt() == 0) {
                    JsonObject postRequest2 = postRequest(cloudDiskCredential, "https://login.123pan.com/api/user/sign_in", "{\"from\":\"web\",\"wechat_code\":\"" + postRequest.getAsJsonObject("data").get("wxCode").getAsString() + "\",\"type\":4}");
                    if (postRequest2.get("code").getAsInt() == 200) {
                        JsonObject asJsonObject = postRequest2.getAsJsonObject("data");
                        if (asJsonObject.get("bind").getAsBoolean()) {
                            qrCodeResult.code = 200;
                            qrCodeResult.token = asJsonObject.get("token").getAsString();
                        } else {
                            qrCodeResult.message = "此微信号未绑定123云盘账号";
                        }
                    } else {
                        qrCodeResult.message = "登录失败：" + postRequest2.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
                    }
                } else {
                    qrCodeResult.message = "登录失败：" + postRequest.get(ConfigBackupService.EXTRA_MESSAGE).getAsString();
                }
            }
        }
        return qrCodeResult;
    }

    public static JsonObject postRequest(CloudDiskCredential cloudDiskCredential, String str, String str2) {
        a1 a1Var;
        String str3 = cloudDiskCredential.uuid;
        if (str3 == null || str3.isEmpty()) {
            cloudDiskCredential.uuid = generateUuid();
        }
        v0 create = v0.create(str2, JSON);
        r0 r0Var = new r0();
        r0Var.f(str);
        i5.a.g("body", create);
        r0Var.d("POST", create);
        r0Var.c("Referer", "https://login.123pan.com/centerlogin?redirect_url=https%3A%2F%2Fwww.123pan.com&source_page=website");
        r0Var.c("loginuuid", cloudDiskCredential.uuid);
        r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        x0 f7 = client.a(r0Var.a()).f();
        if (!f7.B() || (a1Var = f7.f8919g) == null) {
            throw new Exception("请求出错");
        }
        return JsonParser.parseString(a1Var.A()).getAsJsonObject();
    }

    private static int putChunk(o0 o0Var, String str, final byte[] bArr, final long j7, final long j8, final UploadCallback uploadCallback, final UploadControl uploadControl) {
        v0 v0Var = new v0() { // from class: com.navi.tool.utils.Pan123Api.1
            @Override // s5.v0
            public long contentLength() {
                return bArr.length;
            }

            @Override // s5.v0
            public l0 contentType() {
                l0.Companion.getClass();
                return k0.b("application/octet-stream");
            }

            @Override // s5.v0
            public void writeTo(g6.i iVar) {
                int i7 = 0;
                while (i7 < bArr.length) {
                    UploadControl uploadControl2 = uploadControl;
                    if (uploadControl2 != null && uploadControl2.isCancelled()) {
                        throw new IOException(ConfigBackupService.STAGE_CANCELLED);
                    }
                    int min = Math.min(65536, bArr.length - i7);
                    iVar.c(bArr, i7, min);
                    i7 += min;
                    if (uploadCallback != null) {
                        long j9 = j8;
                        if (j9 > 0) {
                            uploadCallback.onProgress((int) Math.min(99L, ((j7 + i7) * 100) / j9));
                        }
                    }
                }
            }
        };
        r0 r0Var = new r0();
        r0Var.f(str);
        r0Var.d("PUT", v0Var);
        x5.j a7 = o0Var.a(r0Var.a());
        if (uploadControl != null) {
            uploadControl.onCall(a7);
        }
        try {
            x0 f7 = a7.f();
            int i7 = f7.f8916d;
            a1 a1Var = f7.f8919g;
            String A = a1Var != null ? a1Var.A() : h0.FRAGMENT_ENCODE_SET;
            f7.close();
            A.substring(0, Math.min(200, A.length()));
            if (uploadControl != null) {
                uploadControl.onCall(null);
            }
            return i7;
        } catch (Throwable th) {
            if (uploadControl != null) {
                uploadControl.onCall(null);
            }
            throw th;
        }
    }

    public static String sign(String str) {
        long nextInt = new Random().nextInt(u1.a.PER_USER_RANGE) * 1000;
        if (nextInt < 10000) {
            nextInt = 1000000;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1000 * currentTimeMillis);
        String format = String.format(Locale.US, "%d%02d%02d%02d%02d", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)));
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < format.length(); i7++) {
            sb.append(DATE_DIGIT_MAP[format.charAt(i7) - '0']);
        }
        String valueOf = String.valueOf(md5Hash(sb.toString()));
        return valueOf + "=" + currentTimeMillis + "-" + nextInt + "-" + String.valueOf(md5Hash(currentTimeMillis + "|" + nextInt + "|" + str + "|web|3|" + valueOf));
    }

    public static boolean uploadFileData(CloudDiskCredential cloudDiskCredential, UploadInfo uploadInfo, File file, UploadCallback uploadCallback) {
        return uploadFileData(cloudDiskCredential, uploadInfo, file, uploadCallback, null);
    }

    public static boolean uploadFileData(CloudDiskCredential cloudDiskCredential, UploadInfo uploadInfo, File file, UploadCallback uploadCallback, UploadControl uploadControl) {
        RandomAccessFile randomAccessFile;
        int i7;
        Map<String, String> map;
        long length = file.length();
        String str = ConfigBackupService.STAGE_CANCELLED;
        if (uploadControl != null && uploadControl.isCancelled()) {
            throw new IOException(ConfigBackupService.STAGE_CANCELLED);
        }
        long j7 = uploadInfo.sliceSize;
        if (j7 <= 0 || j7 > 67108864) {
            j7 = 16777216;
        }
        long j8 = j7;
        int i8 = (int) (((length + j8) - 1) / j8);
        int i9 = i8 <= 0 ? 1 : i8;
        boolean z6 = i9 > 1;
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(30L, timeUnit);
        m0Var.f8836t = t5.b.b(120L, timeUnit);
        m0Var.b(120L, timeUnit);
        o0 o0Var = new o0(m0Var);
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
        int i10 = z6 ? 10 : 1;
        int i11 = 1;
        while (i11 <= i9) {
            int i12 = i11 + i10;
            try {
                int min = Math.min(i12, i9 + 1);
                if (uploadControl != null && uploadControl.isCancelled()) {
                    throw new IOException(str);
                }
                int i13 = i11;
                Map<String, String> presignedUrls = getPresignedUrls(cloudDiskCredential, uploadInfo, z6, i11, min);
                while (i13 < min) {
                    if (uploadControl != null && uploadControl.isCancelled()) {
                        throw new IOException(str);
                    }
                    String str2 = str;
                    String valueOf = String.valueOf(i13);
                    String str3 = presignedUrls.get(valueOf);
                    Map<String, String> map2 = presignedUrls;
                    if (str3 == null || str3.isEmpty()) {
                        throw new Exception("获取上传地址失败: part " + i13);
                    }
                    int i14 = min;
                    int i15 = i12;
                    long j9 = (i13 - 1) * j8;
                    int i16 = i9;
                    boolean z7 = z6;
                    byte[] bArr = new byte[(int) Math.min(j8, length - j9)];
                    randomAccessFile2.seek(j9);
                    randomAccessFile2.readFully(bArr);
                    int i17 = i13;
                    int i18 = i11;
                    o0 o0Var2 = o0Var;
                    randomAccessFile = randomAccessFile2;
                    long j10 = j8;
                    try {
                        int putChunk = putChunk(o0Var, str3, bArr, j9, length, uploadCallback, uploadControl);
                        if (putChunk == 403) {
                            Map<String, String> presignedUrls2 = getPresignedUrls(cloudDiskCredential, uploadInfo, z7, i18, i14);
                            String str4 = presignedUrls2.get(valueOf);
                            if (str4 == null || str4.isEmpty()) {
                                throw new Exception("获取上传地址失败: part " + i17);
                            }
                            map = presignedUrls2;
                            putChunk = putChunk(o0Var2, str4, bArr, j9, length, uploadCallback, uploadControl);
                            i7 = i17;
                        } else {
                            i7 = i17;
                            map = map2;
                        }
                        if (putChunk != 200 && putChunk != 204) {
                            throw new Exception("上传分片失败 HTTP " + putChunk + " (part " + i7 + ")");
                        }
                        int i19 = i7 + 1;
                        i11 = i18;
                        z6 = z7;
                        i12 = i15;
                        o0Var = o0Var2;
                        i9 = i16;
                        presignedUrls = map;
                        randomAccessFile2 = randomAccessFile;
                        j8 = j10;
                        i13 = i19;
                        min = i14;
                        str = str2;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile.close();
                        throw th;
                    }
                }
                i11 = i12;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = randomAccessFile2;
            }
        }
        boolean z8 = z6;
        String str5 = str;
        randomAccessFile2.close();
        if (uploadControl != null && uploadControl.isCancelled()) {
            throw new IOException(str5);
        }
        completeUpload(cloudDiskCredential, uploadInfo, length, z8);
        if (uploadCallback == null) {
            return true;
        }
        uploadCallback.onProgress(100);
        return true;
    }

    public static boolean validateToken(CloudDiskCredential cloudDiskCredential) {
        a1 a1Var;
        try {
            String str = cloudDiskCredential.domain + "/b/api/user/info?" + sign("/b/api/user/info");
            r0 r0Var = new r0();
            r0Var.f(str);
            r0Var.b();
            r0Var.c("Referer", cloudDiskCredential.domain + "/Setting");
            r0Var.c("App-Version", "3");
            r0Var.c("Platform", "web");
            r0Var.c("Accept-Language", "zh-CN,zh;q=0.9");
            r0Var.c("Authorization", "Bearer " + cloudDiskCredential.token);
            r0Var.c("sec-fetch-dest", "empty");
            r0Var.c("sec-fetch-mode", "cors");
            r0Var.c("sec-fetch-site", "same-origin");
            r0Var.c("X-Requested-With", "com.quark.browser");
            r0Var.c("loginuuid", cloudDiskCredential.uuid);
            r0Var.c("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            x0 f7 = client.a(r0Var.a()).f();
            if (!f7.B() || (a1Var = f7.f8919g) == null) {
                return false;
            }
            return JsonParser.parseString(a1Var.A()).getAsJsonObject().get("code").getAsInt() == 0;
        } catch (Exception unused) {
            return false;
        }
    }
}
