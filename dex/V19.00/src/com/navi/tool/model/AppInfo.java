package com.navi.tool.model;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppInfo {
    private String appName;
    private boolean enabled;
    private int iconRes;
    private String packageName;

    public AppInfo(String str, String str2, boolean z6) {
        this.packageName = str;
        this.appName = str2;
        this.enabled = z6;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        String str = this.packageName;
        String str2 = ((AppInfo) obj).packageName;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public String getAppName() {
        return this.appName;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int hashCode() {
        String str = this.packageName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setEnabled(boolean z6) {
        this.enabled = z6;
    }

    public void setIconRes(int i7) {
        this.iconRes = i7;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
