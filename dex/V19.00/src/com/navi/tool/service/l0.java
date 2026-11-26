package com.navi.tool.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import com.navi.tool.log.api.LogEngine;
import com.navi.tool.navi.api.NaviAction;
import com.navi.tool.navi.api.NaviEngine;
import com.navi.tool.scene.api.SceneEngine;
import com.navi.tool.service.LogModuleLoader;
import com.navi.tool.service.NaviModuleLoader;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.service.SceneModuleLoader;
import java.net.Socket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4889a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4890b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4891c;

    public /* synthetic */ l0(int i7, Object obj, Object obj2) {
        this.f4889a = i7;
        this.f4890b = obj;
        this.f4891c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4889a;
        Object obj = this.f4891c;
        Object obj2 = this.f4890b;
        switch (i7) {
            case 0:
                ((PhoneNotificationRelayService) obj2).lambda$showOverlay$8((PhoneNotificationRelayService.OverlayMessage) obj);
                break;
            case 1:
                LogLinkageManager.lambda$playMediaFile$12((Context) obj2, (String) obj);
                break;
            case 2:
                ((LogLinkageManager) obj2).lambda$triggerNeteaseMusicCar$6((String) obj);
                break;
            case 3:
                ((LogModuleLoader.Callback) obj2).onReady((LogEngine) obj);
                break;
            case 4:
                ((LogModuleLoader) obj2).lambda$ensureEngine$1((Context) obj);
                break;
            case 5:
                ((LogMonitorService) obj2).lambda$executeNaviAction$15((NaviAction) obj);
                break;
            case 6:
                ((MusicIslandService) obj2).lambda$onAlbumArtUpdateEvent$9((Bitmap) obj);
                break;
            case 7:
                ((NaviModuleLoader.Callback) obj2).onReady((NaviEngine) obj);
                break;
            case 8:
                ((NaviModuleLoader) obj2).lambda$ensureEngine$1((Context) obj);
                break;
            case 9:
                ((PhoneNotificationRelayService) obj2).lambda$startServerLoopIfNeeded$1((Socket) obj);
                break;
            case 10:
                ((SceneModeManager) obj2).lambda$evaluateRules$12((String) obj);
                break;
            case 11:
                ((SceneModuleLoader.Callback) obj2).onReady((SceneEngine) obj);
                break;
            case 12:
                ((SceneModuleLoader) obj2).lambda$ensureEngine$1((Context) obj);
                break;
            default:
                ((ThemeMonitorService) obj2).lambda$startAmapBroadcastService$0((Intent) obj);
                break;
        }
    }
}
