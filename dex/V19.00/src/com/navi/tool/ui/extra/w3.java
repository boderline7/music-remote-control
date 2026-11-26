package com.navi.tool.ui.extra;

import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5540a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5541b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ File f5542c;

    public /* synthetic */ w3(FileManagerActivity fileManagerActivity, File file, int i7) {
        this.f5540a = i7;
        this.f5541b = fileManagerActivity;
        this.f5542c = file;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5540a;
        File file = this.f5542c;
        FileManagerActivity fileManagerActivity = this.f5541b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$downloadCloudFile$20(file);
                break;
            case 1:
                fileManagerActivity.lambda$compressSelection$47(file);
                break;
            default:
                fileManagerActivity.lambda$enterZipMode$85(file);
                break;
        }
    }
}
