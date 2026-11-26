package com.navi.tool.service;

import android.app.Service;
import android.content.SharedPreferences;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p0 implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4908a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Service f4909b;

    public /* synthetic */ p0(Service service, int i7) {
        this.f4908a = i7;
        this.f4909b = service;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        int i7 = this.f4908a;
        Service service = this.f4909b;
        switch (i7) {
            case 0:
                ((PhoneNotificationRelayService) service).lambda$new$0(sharedPreferences, str);
                break;
            default:
                ((ThemeMonitorService) service).lambda$setupStrWakeLogMonitoring$2(sharedPreferences, str);
                break;
        }
    }
}
