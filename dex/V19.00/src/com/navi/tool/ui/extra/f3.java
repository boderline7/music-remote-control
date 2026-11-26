package com.navi.tool.ui.extra;

import java.io.File;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5155a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5156b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5157c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5158d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Serializable f5159e;

    public /* synthetic */ f3(FileManagerActivity fileManagerActivity, androidx.appcompat.app.o oVar, File file, String str) {
        this.f5156b = fileManagerActivity;
        this.f5158d = oVar;
        this.f5159e = file;
        this.f5157c = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ f3(FileManagerActivity fileManagerActivity, String str, int[] iArr, boolean[] zArr) {
        this.f5156b = fileManagerActivity;
        this.f5157c = str;
        this.f5158d = iArr;
        this.f5159e = zArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5155a;
        String str = this.f5157c;
        FileManagerActivity fileManagerActivity = this.f5156b;
        Object obj = this.f5159e;
        Object obj2 = this.f5158d;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$uploadToCloudDisk$96((androidx.appcompat.app.o) obj2, (File) obj, str);
                break;
            default:
                fileManagerActivity.lambda$promptForConflict$110(str, (int[]) obj2, (boolean[]) obj);
                break;
        }
    }
}
