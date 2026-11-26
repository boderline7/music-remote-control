package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.utils.ZipHelper;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5901a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ File f5902b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ File f5903c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Context f5904d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ZipHelper.ZipCallback f5905e;

    public /* synthetic */ u(File file, File file2, Context context, ZipHelper.ZipCallback zipCallback, int i7) {
        this.f5901a = i7;
        this.f5902b = file;
        this.f5903c = file2;
        this.f5904d = context;
        this.f5905e = zipCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5901a;
        File file = this.f5903c;
        File file2 = this.f5902b;
        ZipHelper.ZipCallback zipCallback = this.f5905e;
        Context context = this.f5904d;
        switch (i7) {
            case 0:
                ZipHelper.lambda$unrar$0(file2, file, context, zipCallback);
                break;
            default:
                ZipHelper.lambda$unzip$1(file2, file, context, zipCallback);
                break;
        }
    }
}
