package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a4 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5016a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ FileManagerActivity f5017b;

    public /* synthetic */ a4(FileManagerActivity fileManagerActivity, int i7) {
        this.f5016a = i7;
        this.f5017b = fileManagerActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5016a;
        FileManagerActivity fileManagerActivity = this.f5017b;
        switch (i7) {
            case 0:
                fileManagerActivity.lambda$setupStorageInfo$50(view);
                break;
            case 1:
                fileManagerActivity.lambda$refreshCloudDisks$13(view);
                break;
            case 2:
                fileManagerActivity.lambda$searchFiles$65(view);
                break;
            case 3:
                fileManagerActivity.lambda$setupActions$41(view);
                break;
            case 4:
                fileManagerActivity.lambda$setupActions$42(view);
                break;
            case 5:
                fileManagerActivity.lambda$setupActions$33(view);
                break;
            case 6:
                fileManagerActivity.lambda$setupActions$34(view);
                break;
            case 7:
                fileManagerActivity.lambda$setupActions$35(view);
                break;
            case 8:
                fileManagerActivity.lambda$setupActions$36(view);
                break;
            case 9:
                fileManagerActivity.lambda$setupActions$37(view);
                break;
            case 10:
                fileManagerActivity.lambda$setupActions$38(view);
                break;
            case 11:
                fileManagerActivity.lambda$setupActions$39(view);
                break;
            case 12:
                fileManagerActivity.lambda$setupActions$40(view);
                break;
            case 13:
                fileManagerActivity.lambda$setupCategories$51(view);
                break;
            case 14:
                fileManagerActivity.lambda$setupCategories$52(view);
                break;
            case 15:
                fileManagerActivity.lambda$setupCategories$53(view);
                break;
            case 16:
                fileManagerActivity.lambda$setupCategories$54(view);
                break;
            case 17:
                fileManagerActivity.lambda$setupCategories$55(view);
                break;
            case 18:
                fileManagerActivity.lambda$setupCategories$56(view);
                break;
            case 19:
                fileManagerActivity.lambda$setupCategories$57(view);
                break;
            case 20:
                fileManagerActivity.lambda$updateBreadcrumbs$68(view);
                break;
            case 21:
                fileManagerActivity.lambda$updateBreadcrumbs$69(view);
                break;
            case 22:
                fileManagerActivity.lambda$scanInstalledApps$61(view);
                break;
            case 23:
                fileManagerActivity.lambda$scanCategoryFiles$58(view);
                break;
            case 24:
                fileManagerActivity.lambda$showCloudBackupDialog$9(view);
                break;
            case 25:
                fileManagerActivity.lambda$initViews$23(view);
                break;
            default:
                fileManagerActivity.lambda$initViews$24(view);
                break;
        }
    }
}
