package p4;

import android.content.Context;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8352a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Auto3DBroadcastReceiver f8353b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f8354c;

    public /* synthetic */ a(Auto3DBroadcastReceiver auto3DBroadcastReceiver, Context context, int i7) {
        this.f8352a = i7;
        this.f8353b = auto3DBroadcastReceiver;
        this.f8354c = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f8352a;
        Context context = this.f8354c;
        Auto3DBroadcastReceiver auto3DBroadcastReceiver = this.f8353b;
        switch (i7) {
            case 0:
                auto3DBroadcastReceiver.lambda$onReceive$0(context);
                break;
            case 1:
                auto3DBroadcastReceiver.lambda$onReceive$1(context);
                break;
            case 2:
                auto3DBroadcastReceiver.lambda$sendNavigationScaleBroadcast$5(context);
                break;
            default:
                auto3DBroadcastReceiver.lambda$sendNavigationScaleBroadcast$6(context);
                break;
        }
    }
}
