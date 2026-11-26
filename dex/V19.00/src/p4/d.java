package p4;

import com.navi.tool.receiver.BootReceiver;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.ui.theme.ThemeViewModel;
import java.util.concurrent.ThreadFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8363a;

    public /* synthetic */ d(int i7) {
        this.f8363a = i7;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread lambda$scheduleGestureRetries$2;
        Thread lambda$static$0;
        Thread lambda$onCreate$0;
        Thread lambda$static$02;
        switch (this.f8363a) {
            case 0:
                lambda$scheduleGestureRetries$2 = BootReceiver.lambda$scheduleGestureRetries$2(runnable);
                return lambda$scheduleGestureRetries$2;
            case 1:
                lambda$static$0 = ThemeChangeReceiver.lambda$static$0(runnable);
                return lambda$static$0;
            case 2:
                lambda$onCreate$0 = ConfigBackupService.lambda$onCreate$0(runnable);
                return lambda$onCreate$0;
            default:
                lambda$static$02 = ThemeViewModel.lambda$static$0(runnable);
                return lambda$static$02;
        }
    }
}
