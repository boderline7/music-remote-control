package com.navi.tool.ui.extra;

import android.content.Context;
import com.navi.tool.NaviApplication;
import com.navi.tool.utils.DeviceTaskRunner;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g3 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5176a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f5177b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f5178c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f5179d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Serializable f5180e;

    public /* synthetic */ g3(NaviApplication naviApplication, String str, int i7, String str2) {
        this.f5178c = naviApplication;
        this.f5179d = str;
        this.f5177b = i7;
        this.f5180e = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ g3(FileManagerActivity fileManagerActivity, androidx.appcompat.app.o oVar, int[] iArr, int i7) {
        this.f5178c = fileManagerActivity;
        this.f5179d = oVar;
        this.f5180e = iArr;
        this.f5177b = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5176a;
        int i8 = this.f5177b;
        Context context = this.f5178c;
        Object obj = this.f5180e;
        Object obj2 = this.f5179d;
        switch (i7) {
            case 0:
                ((FileManagerActivity) context).lambda$uploadToCloudDisk$97((androidx.appcompat.app.o) obj2, (int[]) obj, i8);
                break;
            default:
                DeviceTaskRunner.run(context, (String) obj2, i8, (String) obj);
                break;
        }
    }
}
