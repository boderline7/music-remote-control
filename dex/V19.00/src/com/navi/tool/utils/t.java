package com.navi.tool.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import com.navi.tool.utils.UpdateManager;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5896a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ File f5897b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5898c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5899d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5900e;

    public /* synthetic */ t(Context context, String str, String str2, File file) {
        this.f5898c = context;
        this.f5899d = str;
        this.f5900e = str2;
        this.f5897b = file;
    }

    public /* synthetic */ t(TextView textView, Activity activity, File file, Button button) {
        this.f5898c = textView;
        this.f5899d = activity;
        this.f5897b = file;
        this.f5900e = button;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5896a;
        File file = this.f5897b;
        Object obj = this.f5900e;
        Object obj2 = this.f5899d;
        Object obj3 = this.f5898c;
        switch (i7) {
            case 0:
                UpdateManager.AnonymousClass1.lambda$onResponse$4((TextView) obj3, (Activity) obj2, file, (Button) obj);
                break;
            default:
                AnrWatchdog.lambda$upload$3((Context) obj3, (String) obj2, (String) obj, file);
                break;
        }
    }
}
