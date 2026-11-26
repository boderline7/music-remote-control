package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5603a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5604b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5605c;

    public /* synthetic */ z3(FileManagerActivity fileManagerActivity, String str, int i7) {
        this.f5603a = i7;
        this.f5604b = fileManagerActivity;
        this.f5605c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5603a;
        String str = this.f5605c;
        FileManagerActivity fileManagerActivity = this.f5604b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$requestDirectoryItemCount$79(str);
                break;
            case 1:
                fileManagerActivity.lambda$searchFiles$67(str);
                break;
            case 2:
                fileManagerActivity.lambda$loadCloudFiles$17(str);
                break;
            case 3:
                fileManagerActivity.lambda$createFileOrFolder$118(str);
                break;
            default:
                fileManagerActivity.lambda$showCloudDiskLoginDialog$4(str);
                break;
        }
    }
}
