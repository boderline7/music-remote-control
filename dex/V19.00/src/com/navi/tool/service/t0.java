package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4927a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SceneModeManager f4928b;

    public /* synthetic */ t0(SceneModeManager sceneModeManager, int i7) {
        this.f4927a = i7;
        this.f4928b = sceneModeManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4927a;
        SceneModeManager sceneModeManager = this.f4928b;
        switch (i7) {
            case 0:
                sceneModeManager.drainAndEvaluate();
                break;
            case 1:
                sceneModeManager.lambda$scheduleOpenAcUi$15();
                break;
            case 2:
                sceneModeManager.ensureEngineLoaded();
                break;
            case 3:
                sceneModeManager.lambda$scheduleCloseSystemUiAcUi$16();
                break;
            default:
                sceneModeManager.lambda$scheduleCloseAcUi$14();
                break;
        }
    }
}
