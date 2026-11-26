package com.navi.tool.ui.extra;

import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5255a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5256b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5257c;

    public /* synthetic */ j3(int i7, FileManagerActivity fileManagerActivity, ArrayList arrayList) {
        this.f5255a = i7;
        this.f5256b = fileManagerActivity;
        this.f5257c = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5255a;
        List list = this.f5257c;
        FileManagerActivity fileManagerActivity = this.f5256b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$scanCategoryFiles$59(list);
                break;
            case 1:
                fileManagerActivity.lambda$loadCloudFiles$15(list);
                break;
            case 2:
                fileManagerActivity.lambda$startDeleteSelected$88(list);
                break;
            case 3:
                fileManagerActivity.lambda$scanInstalledApps$63(list);
                break;
            default:
                fileManagerActivity.lambda$searchFiles$66(list);
                break;
        }
    }
}
