package g4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6534a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f6535b;

    public /* synthetic */ f(int i7, Object obj) {
        this.f6534a = i7;
        this.f6535b = obj;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        int i7 = this.f6534a;
        Object obj = this.f6535b;
        switch (i7) {
            case 0:
                ((MainActivity) obj).lambda$registerMainShortcutPrefListener$19(sharedPreferences, str);
                break;
            case 1:
                AmapBroadcastService amapBroadcastService = (AmapBroadcastService) obj;
                String str2 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                if ("extscreen_activation_enabled".equals(str)) {
                    if (!amapBroadcastService.f4487b.getBoolean("extscreen_activation_enabled", false)) {
                        amapBroadcastService.c();
                        break;
                    } else {
                        amapBroadcastService.c();
                        new Handler(Looper.getMainLooper()).postDelayed(new com.navi.tool.amapflying.a(amapBroadcastService, 8), 2100L);
                        break;
                    }
                }
                break;
            default:
                o4.r rVar = (o4.r) obj;
                String str3 = o4.r.PREF_OIL_RECORD_ENABLED;
                rVar.getClass();
                boolean equals = o4.r.PREF_OIL_RECORD_ENABLED.equals(str);
                Context context = rVar.f8007a;
                if (!equals) {
                    if (!o4.r.PREF_OIL_TANK_CAPACITY.equals(str)) {
                        if ("lion_sync_service_enabled".equals(str) || "car_link_service_enabled".equals(str)) {
                            if (!rVar.g()) {
                                rVar.i();
                                break;
                            } else if (!rVar.f()) {
                                o4.r.n(context);
                                rVar.i();
                                break;
                            }
                        }
                    } else {
                        o4.r.o(context, sharedPreferences.getFloat(o4.r.PREF_OIL_TANK_CAPACITY, CropImageView.DEFAULT_ASPECT_RATIO));
                        rVar.i();
                        if (sharedPreferences.getBoolean(o4.r.PREF_OIL_RECORD_ENABLED, false) && !rVar.f()) {
                            o4.r.n(context);
                            rVar.i();
                            break;
                        }
                    }
                } else if (sharedPreferences.getBoolean(o4.r.PREF_OIL_RECORD_ENABLED, false)) {
                    o4.r.n(context);
                    rVar.i();
                    break;
                }
                break;
        }
    }
}
