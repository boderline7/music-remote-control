package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ProgressBar;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.TireLoginActivity;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f4 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5160a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5161b;

    public /* synthetic */ f4(int i7, Object obj) {
        this.f5160a = i7;
        this.f5161b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5160a;
        Object obj = this.f5161b;
        switch (i7) {
            case 0:
                ((FileManagerActivity.AnonymousClass4) obj).lambda$onDeviceAlive$0();
                break;
            case 1:
                ((FileManagerActivity.AnonymousClass6) obj).lambda$onSuccess$0();
                break;
            case 2:
                ((TireLoginActivity.AnonymousClass3) obj).lambda$onReady$0();
                break;
            case 3:
                ((AdditionalFeaturesFragment) obj).updatePermissionStatus();
                break;
            case 4:
                DesktopAssistantActivity.lambda$saveMusicCover$31((Bitmap) obj);
                break;
            case 5:
                DesktopAssistantActivity.lambda$saveLyrics$30((TreeMap) obj);
                break;
            case 6:
                DesktopAssistantCarControlHelper.lambda$updateLightState$79((View) obj);
                break;
            case 7:
                FileManagerActivity.lambda$executePaste$101((ProgressBar) obj);
                break;
            case 8:
                ((FileVideoPreviewActivity) obj).hideControls();
                break;
            default:
                ((MiscSettingsFragment) obj).launchAmapCar();
                break;
        }
    }
}
