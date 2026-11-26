package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.ui.extra.StorageAnalysisActivity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u5 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5500a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f5501b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5502c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5503d;

    public /* synthetic */ u5(int i7, CloudDiskCredential cloudDiskCredential, FileManagerActivity fileManagerActivity) {
        this.f5502c = fileManagerActivity;
        this.f5501b = i7;
        this.f5503d = cloudDiskCredential;
    }

    public /* synthetic */ u5(StorageAnalysisActivity storageAnalysisActivity, StorageAnalysisActivity.FileItem fileItem, int i7) {
        this.f5502c = storageAnalysisActivity;
        this.f5503d = fileItem;
        this.f5501b = i7;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5500a;
        int i9 = this.f5501b;
        Object obj = this.f5503d;
        androidx.appcompat.app.r rVar = this.f5502c;
        switch (i8) {
            case 0:
                ((StorageAnalysisActivity) rVar).lambda$deleteFile$10((StorageAnalysisActivity.FileItem) obj, i9, dialogInterface, i7);
                break;
            default:
                ((FileManagerActivity) rVar).lambda$refreshCloudDisks$11(i9, (CloudDiskCredential) obj, dialogInterface, i7);
                break;
        }
    }
}
