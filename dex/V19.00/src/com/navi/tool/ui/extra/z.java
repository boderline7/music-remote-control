package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.DesktopAssistantClockWeatherController;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.ui.extra.LyricsAdapter;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z implements DesktopAssistantMusicUiHelper.BitmapCallback, ThemeChangeReceiver.OnThemeChangeListener, androidx.lifecycle.h0, DesktopAssistantActivity.DesktopAppAdapter.OnAppClickListener, r4.i, LyricsAdapter.OnLineClickListener, DesktopAssistantClockWeatherController.WeatherObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5593a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5594b;

    public /* synthetic */ z(DesktopAssistantActivity desktopAssistantActivity, int i7) {
        this.f5593a = i7;
        this.f5594b = desktopAssistantActivity;
    }

    @Override // com.navi.tool.ui.extra.DesktopAssistantActivity.DesktopAppAdapter.OnAppClickListener
    public final void onAppClick(DesktopAssistantActivity.AppItem appItem) {
        this.f5594b.lambda$updateAppListUI$79(appItem);
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        int i7 = this.f5593a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5594b;
        switch (i7) {
            case 2:
                desktopAssistantActivity.lambda$onCreate$23((Map) obj);
                break;
            case 3:
                desktopAssistantActivity.lambda$onCreate$6(obj);
                break;
            default:
                desktopAssistantActivity.lambda$syncGnssDataSource$65((String) obj);
                break;
        }
    }

    @Override // com.navi.tool.ui.extra.LyricsAdapter.OnLineClickListener
    public final void onLineClick(int i7) {
        this.f5594b.lambda$initViews$53(i7);
    }

    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.BitmapCallback
    public final void onLoaded(Bitmap bitmap) {
        int i7 = this.f5593a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5594b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$onAlbumArtUpdateEvent$43(bitmap);
                break;
            default:
                desktopAssistantActivity.lambda$applyCoverGradientFromEvent$44(bitmap);
                break;
        }
    }

    @Override // com.navi.tool.receiver.ThemeChangeReceiver.OnThemeChangeListener
    public final void onThemeChanged(boolean z6) {
        this.f5594b.lambda$onCreate$4(z6);
    }

    @Override // com.navi.tool.ui.extra.DesktopAssistantClockWeatherController.WeatherObserver
    public final void onWeather(String str, String str2) {
        this.f5594b.lambda$applyWeatherEffects$36(str, str2);
    }
}
