package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5072a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5073b;

    public /* synthetic */ c4(FileManagerActivity fileManagerActivity, int i7) {
        this.f5072a = i7;
        this.f5073b = fileManagerActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5072a;
        FileManagerActivity fileManagerActivity = this.f5073b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$createFileOrFolder$116();
                break;
            case 1:
                fileManagerActivity.lambda$copyAndSend$29();
                break;
            case 2:
                fileManagerActivity.lambda$new$0();
                break;
            case 3:
                fileManagerActivity.lambda$scanInstalledApps$64();
                break;
            default:
                fileManagerActivity.lambda$startHttpServer$120();
                break;
        }
    }
}
