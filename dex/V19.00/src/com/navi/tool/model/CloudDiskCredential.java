package com.navi.tool.model;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CloudDiskCredential {
    private static final String KEY_CREDENTIALS = "credentials_list";
    private static final String KEY_CURRENT_INDEX = "current_credential_index";
    private static final String PREFS_NAME = "cloud_disk_credentials";
    private static final Gson gson = new Gson();
    public String domain;
    public String name;
    public String password;
    public int port;
    public String tag;
    public String token;
    public int type;
    public String uniId;
    public String user;
    public String userId;
    public String uuid;

    public CloudDiskCredential() {
        this.type = 0;
        this.name = "添加";
        this.domain = h0.FRAGMENT_ENCODE_SET;
        this.port = 0;
        this.user = h0.FRAGMENT_ENCODE_SET;
        this.password = h0.FRAGMENT_ENCODE_SET;
        this.token = h0.FRAGMENT_ENCODE_SET;
        this.uuid = h0.FRAGMENT_ENCODE_SET;
        this.uniId = h0.FRAGMENT_ENCODE_SET;
        this.userId = h0.FRAGMENT_ENCODE_SET;
        this.tag = h0.FRAGMENT_ENCODE_SET;
    }

    public CloudDiskCredential(int i7, String str) {
        this();
        this.type = i7;
        this.name = str;
    }

    public static int addOrUpdate(Context context, CloudDiskCredential cloudDiskCredential, int i7) {
        List<CloudDiskCredential> loadAll = loadAll(context);
        for (int i8 = 0; i8 < loadAll.size(); i8++) {
            CloudDiskCredential cloudDiskCredential2 = loadAll.get(i8);
            if (cloudDiskCredential2.type == cloudDiskCredential.type && cloudDiskCredential2.user.equals(cloudDiskCredential.user)) {
                loadAll.set(i8, cloudDiskCredential);
                saveAll(context, loadAll);
                setCurrentIndex(context, i8);
                return i8;
            }
        }
        if (i7 < 0 || i7 > loadAll.size()) {
            loadAll.add(cloudDiskCredential);
        } else {
            loadAll.add(i7, cloudDiskCredential);
        }
        saveAll(context, loadAll);
        int size = loadAll.size() - 1;
        setCurrentIndex(context, size);
        return size;
    }

    public static CloudDiskCredential getCurrent(Context context) {
        int currentIndex = getCurrentIndex(context);
        if (currentIndex < 0) {
            return null;
        }
        List<CloudDiskCredential> loadAll = loadAll(context);
        if (currentIndex < loadAll.size()) {
            return loadAll.get(currentIndex);
        }
        return null;
    }

    public static int getCurrentIndex(Context context) {
        return context.getSharedPreferences(PREFS_NAME, 0).getInt(KEY_CURRENT_INDEX, -1);
    }

    public static List<CloudDiskCredential> loadAll(Context context) {
        String string = context.getSharedPreferences(PREFS_NAME, 0).getString(KEY_CREDENTIALS, null);
        if (string == null || string.isEmpty()) {
            return new ArrayList();
        }
        try {
            List<CloudDiskCredential> list = (List) gson.fromJson(string, new TypeToken<List<CloudDiskCredential>>() { // from class: com.navi.tool.model.CloudDiskCredential.1
            }.getType());
            return list != null ? list : new ArrayList();
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void saveAll(Context context, List<CloudDiskCredential> list) {
        context.getSharedPreferences(PREFS_NAME, 0).edit().putString(KEY_CREDENTIALS, gson.toJson(list)).apply();
    }

    public static void setCurrentIndex(Context context, int i7) {
        context.getSharedPreferences(PREFS_NAME, 0).edit().putInt(KEY_CURRENT_INDEX, i7).apply();
    }
}
