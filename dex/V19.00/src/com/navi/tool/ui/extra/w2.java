package com.navi.tool.ui.extra;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.navi.tool.tire.api.TireBrand;
import com.navi.tool.ui.extra.DesktopAssistantFragment;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.ImageViewerActivity;
import com.navi.tool.utils.Pan123Api;
import com.navi.tool.view.WeatherAmbientView;
import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5536a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5537b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5538c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5539d;

    public /* synthetic */ w2(FileManagerActivity fileManagerActivity, Pan123Api.CloudFileInfo cloudFileInfo, File file) {
        this.f5536a = 12;
        this.f5538c = fileManagerActivity;
        this.f5539d = cloudFileInfo;
        this.f5537b = file;
    }

    public /* synthetic */ w2(Object obj, Object obj2, Object obj3, int i7) {
        this.f5536a = i7;
        this.f5538c = obj;
        this.f5537b = obj2;
        this.f5539d = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5536a;
        Object obj = this.f5539d;
        Object obj2 = this.f5537b;
        Object obj3 = this.f5538c;
        switch (i7) {
            case 0:
                ((DesktopAssistantFragment.AnonymousClass21) obj3).lambda$onBindViewHolder$1((File) obj2, (ImageView) obj);
                break;
            case 1:
                ((FileManagerActivity.AnonymousClass4) obj3).lambda$onFileReceived$5((File) obj2, (String) obj);
                break;
            case 2:
                FileManagerActivity.FileAdapter.lambda$loadImageThumbnail$3((FileManagerActivity.FileAdapter.ViewHolder) obj3, (File) obj2, (Bitmap) obj);
                break;
            case 3:
                ((ImageViewerActivity.ImagePagerAdapter.ImageHolder) obj3).lambda$bind$0((String) obj2, (Bitmap) obj);
                break;
            case 4:
                ((DesktopAssistantActivity) obj3).lambda$loadDockApps$50((List) obj2, (PackageManager) obj);
                break;
            case 5:
                ((DesktopAssistantActivity) obj3).lambda$setWallpaperWithAnimation$93((TransitionDrawable) obj2, (Bitmap) obj);
                break;
            case 6:
                ((WeatherAmbientView) obj3).setWeather((String) obj2, (String) obj);
                break;
            case 7:
                DesktopAssistantFragment.lambda$submitManagedVideoThumbnailTask$78((Runnable) obj3, (Future[]) obj2, (List) obj);
                break;
            case 8:
                ((DesktopAssistantFragment) obj3).lambda$postToast$68((Context) obj2, (String) obj);
                break;
            case 9:
                ((FileManagerActivity) obj3).lambda$requestDirectoryItemCount$80((File) obj2, (String) obj);
                break;
            case 10:
                FileManagerActivity.lambda$executePaste$100((TextView) obj3, (ProgressBar) obj2, (TextView) obj);
                break;
            case 11:
                FileManagerActivity.lambda$uploadToCloudDisk$92((TextView) obj3, (File) obj2, (TextView) obj);
                break;
            case 12:
                ((FileManagerActivity) obj3).lambda$downloadCloudFile$22((Pan123Api.CloudFileInfo) obj, (File) obj2);
                break;
            case 13:
                ((FileManagerActivity) obj3).lambda$compressSelection$49((File) obj2, (List) obj);
                break;
            case 14:
                ((FileManagerActivity) obj3).lambda$showSingleFileProperties$44((File) obj2, (androidx.appcompat.app.o) obj);
                break;
            default:
                ((TireLoginActivity) obj3).lambda$sendCode$12((String) obj2, (TireBrand) obj);
                break;
        }
    }
}
