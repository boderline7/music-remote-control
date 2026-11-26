package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4885a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ PhoneNotificationRelayService f4886b;

    public /* synthetic */ k0(PhoneNotificationRelayService phoneNotificationRelayService, int i7) {
        this.f4885a = i7;
        this.f4886b = phoneNotificationRelayService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4885a;
        PhoneNotificationRelayService phoneNotificationRelayService = this.f4886b;
        switch (i7) {
            case 0:
                phoneNotificationRelayService.lambda$showForwardedPhoneNotification$4();
                break;
            case 1:
                phoneNotificationRelayService.lambda$refreshClusterOverlayState$15();
                break;
            case 2:
                phoneNotificationRelayService.lambda$refreshMainOverlayState$14();
                break;
            case 3:
                phoneNotificationRelayService.lambda$handleClusterPreviewIntent$3();
                break;
            case 4:
                phoneNotificationRelayService.lambda$refreshOverlayAppearance$10();
                break;
            case 5:
                phoneNotificationRelayService.clearOverlayMessagesAndDismiss();
                break;
            case 6:
                phoneNotificationRelayService.dismissClusterPreview();
                break;
            case 7:
                phoneNotificationRelayService.lambda$startServerLoopIfNeeded$2();
                break;
            default:
                phoneNotificationRelayService.lambda$refreshOverlayPosition$9();
                break;
        }
    }
}
