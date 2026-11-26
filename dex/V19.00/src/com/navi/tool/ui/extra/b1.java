package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5037a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5038b;

    public /* synthetic */ b1(DesktopAssistantActivity desktopAssistantActivity, int i7) {
        this.f5037a = i7;
        this.f5038b = desktopAssistantActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5037a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5038b;
        switch (i7) {
            case 0:
                DesktopAssistantActivity.access$4300(desktopAssistantActivity);
                break;
            case 1:
                DesktopAssistantActivity.access$4300(desktopAssistantActivity);
                break;
            case 2:
                desktopAssistantActivity.lambda$setupMode$70();
                break;
            case 3:
                desktopAssistantActivity.lambda$setupMode$71();
                break;
            case 4:
                desktopAssistantActivity.lambda$loadCachedMusicInfo$34();
                break;
            case 5:
                desktopAssistantActivity.lambda$openLyricsFxPage$112();
                break;
            case 6:
                desktopAssistantActivity.lambda$setupMode$71();
                break;
            case 7:
                desktopAssistantActivity.lambda$initAppDisplay$64();
                break;
            case 8:
                desktopAssistantActivity.loadInstalledApps();
                break;
            case 9:
                desktopAssistantActivity.lambda$setupMode$72();
                break;
            case 10:
                desktopAssistantActivity.lambda$sendMapBroadcast$110();
                break;
            case 11:
                desktopAssistantActivity.startAlbumArtAnimation();
                break;
            case 12:
                desktopAssistantActivity.stopAlbumArtAnimation();
                break;
            case 13:
                desktopAssistantActivity.swapCarControlAndMusic();
                break;
            case 14:
                desktopAssistantActivity.lambda$showAppSelectionDialog$67();
                break;
            case 15:
                desktopAssistantActivity.lambda$setupMode$71();
                break;
            case 16:
                desktopAssistantActivity.lambda$onCreate$3();
                break;
            case 17:
                desktopAssistantActivity.lambda$onCreate$5();
                break;
            case 18:
                desktopAssistantActivity.lambda$setupMode$71();
                break;
            case 19:
                desktopAssistantActivity.startAlbumArtAnimation();
                break;
            case 20:
                desktopAssistantActivity.stopAlbumArtAnimation();
                break;
            case 21:
                desktopAssistantActivity.updateLyricsHeight();
                break;
            case 22:
                desktopAssistantActivity.restoreDesktopAfterLyricsFx();
                break;
            case 23:
                desktopAssistantActivity.updateLyricsHeight();
                break;
            case 24:
                desktopAssistantActivity.lambda$loadCachedCoverForGradient$47();
                break;
            case 25:
                desktopAssistantActivity.lambda$loadDockApps$51();
                break;
            case 26:
                desktopAssistantActivity.updateLyricsHeight();
                break;
            default:
                desktopAssistantActivity.playNextVideoWallpaper();
                break;
        }
    }
}
