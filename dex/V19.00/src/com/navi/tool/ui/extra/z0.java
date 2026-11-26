package com.navi.tool.ui.extra;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireBrand;
import com.navi.tool.tire.api.TireSnapshot;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.ImageViewerActivity;
import com.navi.tool.ui.extra.TireLoginActivity;
import com.navi.tool.utils.Pan123Api;
import java.io.File;
import java.util.List;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5595a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5596b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5597c;

    public /* synthetic */ z0(int i7, Object obj, Object obj2) {
        this.f5595a = i7;
        this.f5596b = obj;
        this.f5597c = obj2;
    }

    public /* synthetic */ z0(int i7, Object obj, String str) {
        this.f5595a = i7;
        this.f5597c = str;
        this.f5596b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5595a;
        Object obj = this.f5597c;
        Object obj2 = this.f5596b;
        switch (i7) {
            case 0:
                ((DesktopAssistantActivity.AnonymousClass12) obj2).lambda$onError$1((String) obj);
                break;
            case 1:
                DesktopAssistantFragment.AnonymousClass21.lambda$onBindViewHolder$0((ImageView) obj2, (Bitmap) obj);
                break;
            case 2:
                ((FileManagerActivity.AnonymousClass6) obj2).lambda$onError$1((String) obj);
                break;
            case 3:
                ((ImageViewerActivity.ImagePagerAdapter.ImageHolder) obj2).lambda$bind$1((String) obj);
                break;
            case 4:
                ((TireLoginActivity.AnonymousClass3) obj2).lambda$onError$1((String) obj);
                break;
            case 5:
                ((DesktopAssistantActivity) obj2).lambda$loadCachedMusicInfo$32((TreeMap) obj);
                break;
            case 6:
                ((DesktopAssistantActivity) obj2).lambda$ensureDesktopSpectrumAnalyzer$40((float[]) obj);
                break;
            case 7:
                DesktopAssistantActivity.lambda$saveMusicMeta$29((String) obj, (String) obj2);
                break;
            case 8:
                DesktopAssistantCarControlHelper.lambda$showSeatPanelOrderDialog$56((RecyclerView) obj2, (androidx.recyclerview.widget.h1) obj);
                break;
            case 9:
                ((DesktopAssistantClockWeatherController) obj2).lambda$updateLocationTextOnly$3((String) obj);
                break;
            case 10:
                ((DesktopAssistantFragment) obj2).lambda$autoCropAndSave$144((Uri) obj);
                break;
            case 11:
                ((DesktopAssistantFragment) obj2).lambda$autoCropAndSave$143((Exception) obj);
                break;
            case 12:
                DesktopAssistantMusicUiHelper.lambda$downloadCoverAsync$2((String) obj, (DesktopAssistantMusicUiHelper.BitmapCallback) obj2);
                break;
            case 13:
                FileManagerActivity.lambda$uploadToCloudDisk$91((TextView) obj2, (File) obj);
                break;
            case 14:
                ((FileManagerActivity) obj2).lambda$copyAndSend$30((Uri) obj);
                break;
            case 15:
                ((FileManagerActivity) obj2).lambda$showCloudDiskLoginDialog$2((CloudDiskCredential) obj);
                break;
            case 16:
                ((FileManagerActivity) obj2).lambda$downloadCloudFile$18((Pan123Api.CloudFileInfo) obj);
                break;
            case 17:
                ((FileManagerActivity) obj2).lambda$scanCategoryFiles$60((String[]) obj);
                break;
            case 18:
                ((MiscSettingsFragment) obj2).lambda$uploadCrashReport$2((Context) obj);
                break;
            case 19:
                ((PermissionManagerActivity) obj2).lambda$loadApps$16((List) obj);
                break;
            case 20:
                ((PermissionManagerActivity) obj2).lambda$showRemoveOwnerDialog$1((Exception) obj);
                break;
            case 21:
                ((QrCodeLoginDialog) obj2).lambda$startPolling$10((Pan123Api.QrCodeResult) obj);
                break;
            case 22:
                ((TireLoginActivity) obj2).lambda$doFetch$19((TireAccount) obj);
                break;
            case 23:
                ((TireLoginActivity) obj2).lambda$sendCode$11((TireBrand) obj);
                break;
            default:
                ((TireLoginActivity) obj2).lambda$doFetch$17((TireSnapshot) obj);
                break;
        }
    }
}
