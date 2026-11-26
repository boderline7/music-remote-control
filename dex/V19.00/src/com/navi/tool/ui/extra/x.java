package com.navi.tool.ui.extra;

import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import com.navi.tool.ui.extra.StorageAnalysisActivity;
import java.io.File;
import java.util.Comparator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5549a;

    public /* synthetic */ x(int i7) {
        this.f5549a = i7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$fetchAppList$78;
        int lambda$loadLaunchableAppsOnWorker$68;
        int lambda$loadApps$15;
        int lambda$startScan$7;
        int lambda$showScreenOnAutoStartAppsDialog$40;
        int lambda$loadAutoStartApps$37;
        int lambda$filterApps$35;
        int lambda$getAllLaunchableApps$34;
        int lambda$loadWallpaper$82;
        int lambda$loadCustomMusicApps$39;
        int lambda$collectSortedVideoWallpaperPaths$87;
        int lambda$applyFanRowPositionByOrder$49;
        int lambda$showAppSelectionDialog$92;
        int lambda$getAllLaunchableApps$11;
        int lambda$getVideoWallpaperFiles$70;
        int lambda$showManageWallpapersDialog$134;
        switch (this.f5549a) {
            case 0:
                lambda$fetchAppList$78 = DesktopAssistantActivity.lambda$fetchAppList$78((DesktopAssistantActivity.AppItem) obj, (DesktopAssistantActivity.AppItem) obj2);
                return lambda$fetchAppList$78;
            case 1:
                lambda$loadLaunchableAppsOnWorker$68 = DesktopAssistantActivity.lambda$loadLaunchableAppsOnWorker$68((DesktopAssistantActivity.LabeledApp) obj, (DesktopAssistantActivity.LabeledApp) obj2);
                return lambda$loadLaunchableAppsOnWorker$68;
            case 2:
                lambda$loadApps$15 = PermissionManagerActivity.lambda$loadApps$15((PermissionManagerActivity.AppInfo) obj, (PermissionManagerActivity.AppInfo) obj2);
                return lambda$loadApps$15;
            case 3:
                lambda$startScan$7 = StorageAnalysisActivity.lambda$startScan$7((StorageAnalysisActivity.FileItem) obj, (StorageAnalysisActivity.FileItem) obj2);
                return lambda$startScan$7;
            case 4:
                lambda$showScreenOnAutoStartAppsDialog$40 = AdditionalFeaturesFragment.lambda$showScreenOnAutoStartAppsDialog$40((AppInfo) obj, (AppInfo) obj2);
                return lambda$showScreenOnAutoStartAppsDialog$40;
            case 5:
                lambda$loadAutoStartApps$37 = AdditionalFeaturesFragment.lambda$loadAutoStartApps$37((AppInfo) obj, (AppInfo) obj2);
                return lambda$loadAutoStartApps$37;
            case 6:
                lambda$filterApps$35 = AdditionalFeaturesFragment.lambda$filterApps$35((AppInfo) obj, (AppInfo) obj2);
                return lambda$filterApps$35;
            case 7:
                lambda$getAllLaunchableApps$34 = AdditionalFeaturesFragment.lambda$getAllLaunchableApps$34((AppInfo) obj, (AppInfo) obj2);
                return lambda$getAllLaunchableApps$34;
            case 8:
                lambda$loadWallpaper$82 = DesktopAssistantActivity.lambda$loadWallpaper$82((File) obj, (File) obj2);
                return lambda$loadWallpaper$82;
            case 9:
                lambda$loadCustomMusicApps$39 = DesktopAssistantActivity.lambda$loadCustomMusicApps$39((AppInfo) obj, (AppInfo) obj2);
                return lambda$loadCustomMusicApps$39;
            case 10:
                lambda$collectSortedVideoWallpaperPaths$87 = DesktopAssistantActivity.lambda$collectSortedVideoWallpaperPaths$87((File) obj, (File) obj2);
                return lambda$collectSortedVideoWallpaperPaths$87;
            case 11:
                lambda$applyFanRowPositionByOrder$49 = DesktopAssistantCarControlHelper.lambda$applyFanRowPositionByOrder$49((int[]) obj, (int[]) obj2);
                return lambda$applyFanRowPositionByOrder$49;
            case 12:
                lambda$showAppSelectionDialog$92 = DesktopAssistantFragment.lambda$showAppSelectionDialog$92((AppInfo) obj, (AppInfo) obj2);
                return lambda$showAppSelectionDialog$92;
            case 13:
                lambda$getAllLaunchableApps$11 = DesktopAssistantFragment.lambda$getAllLaunchableApps$11((AppInfo) obj, (AppInfo) obj2);
                return lambda$getAllLaunchableApps$11;
            case 14:
                lambda$getVideoWallpaperFiles$70 = DesktopAssistantFragment.lambda$getVideoWallpaperFiles$70((File) obj, (File) obj2);
                return lambda$getVideoWallpaperFiles$70;
            default:
                lambda$showManageWallpapersDialog$134 = DesktopAssistantFragment.lambda$showManageWallpapersDialog$134((File) obj, (File) obj2);
                return lambda$showManageWallpapersDialog$134;
        }
    }
}
