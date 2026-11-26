package com.navi.tool.ui.extra;

import android.widget.ImageView;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class y1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5571a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5572b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5573c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ File f5574d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f5575e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f5576f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ ImageView f5577g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ File f5578h;

    public /* synthetic */ y1(DesktopAssistantFragment desktopAssistantFragment, int i7, File file, String str, File file2, String str2, ImageView imageView) {
        this.f5572b = desktopAssistantFragment;
        this.f5573c = i7;
        this.f5574d = file;
        this.f5575e = str;
        this.f5578h = file2;
        this.f5576f = str2;
        this.f5577g = imageView;
    }

    public /* synthetic */ y1(DesktopAssistantFragment desktopAssistantFragment, int i7, File file, String str, String str2, ImageView imageView, File file2) {
        this.f5572b = desktopAssistantFragment;
        this.f5573c = i7;
        this.f5574d = file;
        this.f5575e = str;
        this.f5576f = str2;
        this.f5577g = imageView;
        this.f5578h = file2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f5571a) {
            case 0:
                this.f5572b.lambda$generateManagedVideoThumbnail$77(this.f5573c, this.f5574d, this.f5575e, this.f5578h, this.f5576f, this.f5577g);
                break;
            default:
                this.f5572b.lambda$loadManagedVideoThumbnail$75(this.f5573c, this.f5574d, this.f5575e, this.f5576f, this.f5577g, this.f5578h);
                break;
        }
    }
}
