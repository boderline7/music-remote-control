package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5557a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5558b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Exception f5559c;

    public /* synthetic */ x3(FileManagerActivity fileManagerActivity, Exception exc, int i7) {
        this.f5557a = i7;
        this.f5558b = fileManagerActivity;
        this.f5559c = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5557a;
        Exception exc = this.f5559c;
        FileManagerActivity fileManagerActivity = this.f5558b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$downloadCloudFile$21(exc);
                break;
            case 1:
                fileManagerActivity.lambda$createFileOrFolder$117(exc);
                break;
            case 2:
                fileManagerActivity.lambda$compressSelection$48(exc);
                break;
            default:
                fileManagerActivity.lambda$loadCloudFiles$16(exc);
                break;
        }
    }
}
