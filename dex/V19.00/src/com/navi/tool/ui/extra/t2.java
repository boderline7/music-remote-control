package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5464a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5465b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5466c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f5467d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ImageView f5468e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Bitmap f5469f;

    public /* synthetic */ t2(DesktopAssistantFragment desktopAssistantFragment, int i7, String str, ImageView imageView, Bitmap bitmap, int i8) {
        this.f5464a = i8;
        this.f5465b = desktopAssistantFragment;
        this.f5466c = i7;
        this.f5467d = str;
        this.f5468e = imageView;
        this.f5469f = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5464a;
        String str = this.f5467d;
        int i8 = this.f5466c;
        DesktopAssistantFragment desktopAssistantFragment = this.f5465b;
        Bitmap bitmap = this.f5469f;
        ImageView imageView = this.f5468e;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$generateManagedVideoThumbnail$76(i8, str, imageView, bitmap);
                break;
            default:
                desktopAssistantFragment.lambda$loadManagedVideoThumbnail$74(i8, str, imageView, bitmap);
                break;
        }
    }
}
