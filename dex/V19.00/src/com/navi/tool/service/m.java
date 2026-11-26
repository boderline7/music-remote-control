package com.navi.tool.service;

import android.app.ActivityOptions;
import android.content.Intent;
import com.navi.tool.ui.cast.ScreenCastingFloatingService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4892a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActivityOptions f4893b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f4894c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Intent f4895d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f4896e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f4897f;

    public /* synthetic */ m(Object obj, ActivityOptions activityOptions, int i7, Intent intent, String str, int i8) {
        this.f4892a = i8;
        this.f4897f = obj;
        this.f4893b = activityOptions;
        this.f4894c = i7;
        this.f4895d = intent;
        this.f4896e = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4892a;
        int i8 = this.f4894c;
        ActivityOptions activityOptions = this.f4893b;
        String str = this.f4896e;
        Intent intent = this.f4895d;
        Object obj = this.f4897f;
        switch (i7) {
            case 0:
                ((LogLinkageManager) obj).lambda$triggerInstrumentCast$5(activityOptions, i8, intent, str);
                break;
            default:
                ((ScreenCastingFloatingService) obj).lambda$toggleScreenForSelectedApp$1(activityOptions, i8, intent, str);
                break;
        }
    }
}
