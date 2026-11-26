package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5493a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5494b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5495c;

    public /* synthetic */ u3(FileManagerActivity fileManagerActivity, int i7, int i8) {
        this.f5493a = i8;
        this.f5494b = fileManagerActivity;
        this.f5495c = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5493a;
        int i8 = this.f5495c;
        FileManagerActivity fileManagerActivity = this.f5494b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$downloadCloudFile$19(i8);
                break;
            case 1:
                fileManagerActivity.lambda$refreshCloudBackupAvailability$6(i8);
                break;
            default:
                fileManagerActivity.lambda$startDeleteSelected$87(i8);
                break;
        }
    }
}
