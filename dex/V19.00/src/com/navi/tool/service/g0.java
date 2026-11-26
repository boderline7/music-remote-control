package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4867a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MusicIslandService f4868b;

    public /* synthetic */ g0(MusicIslandService musicIslandService, int i7) {
        this.f4867a = i7;
        this.f4868b = musicIslandService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4867a;
        MusicIslandService musicIslandService = this.f4868b;
        switch (i7) {
            case 0:
                musicIslandService.refreshOverlayForInteraction();
                break;
            case 1:
                musicIslandService.lambda$showExpandedCard$6();
                break;
            case 2:
                musicIslandService.refreshOverlayForInteraction();
                break;
            default:
                musicIslandService.refreshOverlayForInteraction();
                break;
        }
    }
}
