package com.navi.tool.ui.extra;

import com.navi.tool.ui.extra.FileManagerActivity;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5207a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity.FileAdapter f5208b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ File f5209c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f5210d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity.FileAdapter.ViewHolder f5211e;

    public /* synthetic */ h4(FileManagerActivity.FileAdapter fileAdapter, File file, String str, FileManagerActivity.FileAdapter.ViewHolder viewHolder, int i7) {
        this.f5207a = i7;
        this.f5208b = fileAdapter;
        this.f5209c = file;
        this.f5210d = str;
        this.f5211e = viewHolder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5207a;
        File file = this.f5209c;
        FileManagerActivity.FileAdapter fileAdapter = this.f5208b;
        FileManagerActivity.FileAdapter.ViewHolder viewHolder = this.f5211e;
        String str = this.f5210d;
        switch (i7) {
            case 0:
                fileAdapter.lambda$loadApkInfo$10(file, str, viewHolder);
                break;
            case 1:
                fileAdapter.lambda$loadImageThumbnail$4(file, str, viewHolder);
                break;
            default:
                fileAdapter.lambda$loadVideoThumbnail$6(file, str, viewHolder);
                break;
        }
    }
}
