package com.navi.tool.model;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StoreConfig {
    private String description;
    private String downloadUrl;
    private String name;
    private List<String> tags;
    private String time;

    public StoreConfig(String str, String str2, String str3, List<String> list, String str4) {
        this.name = str;
        this.time = str2;
        this.description = str3;
        this.tags = list;
        this.downloadUrl = str4;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getTime() {
        return this.time;
    }
}
