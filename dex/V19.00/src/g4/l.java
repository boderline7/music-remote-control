package g4;

import com.navi.tool.NaviApplication;
import com.navi.tool.receiver.ThemeChangeReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements ThemeChangeReceiver.OnThemeChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ NaviApplication f6551a;

    public l(NaviApplication naviApplication) {
        this.f6551a = naviApplication;
    }

    @Override // com.navi.tool.receiver.ThemeChangeReceiver.OnThemeChangeListener
    public final void onThemeChanged(boolean z6) {
        ThemeChangeReceiver.OnThemeChangeListener onThemeChangeListener;
        ThemeChangeReceiver.OnThemeChangeListener onThemeChangeListener2;
        NaviApplication naviApplication = this.f6551a;
        onThemeChangeListener = naviApplication.globalThemeChangeListener;
        if (onThemeChangeListener != null) {
            onThemeChangeListener2 = naviApplication.globalThemeChangeListener;
            onThemeChangeListener2.onThemeChanged(z6);
        }
    }
}
