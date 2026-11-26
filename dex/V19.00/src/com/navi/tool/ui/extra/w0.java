package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5528a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5529b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f5530c;

    public /* synthetic */ w0(DesktopAssistantActivity desktopAssistantActivity, String str, int i7) {
        this.f5528a = i7;
        this.f5529b = desktopAssistantActivity;
        this.f5530c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5528a;
        String str = this.f5530c;
        DesktopAssistantActivity desktopAssistantActivity = this.f5529b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$startVideoWallpaper$90(str);
                break;
            default:
                desktopAssistantActivity.lambda$switchFloatingMap$0(str);
                break;
        }
    }
}
