package p4;

import android.content.Context;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8355a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Auto3DBroadcastReceiver f8356b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f8357c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8358d;

    public /* synthetic */ b(Auto3DBroadcastReceiver auto3DBroadcastReceiver, Context context, int i7, int i8) {
        this.f8355a = i8;
        this.f8356b = auto3DBroadcastReceiver;
        this.f8357c = context;
        this.f8358d = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f8355a;
        Auto3DBroadcastReceiver auto3DBroadcastReceiver = this.f8356b;
        int i8 = this.f8358d;
        Context context = this.f8357c;
        switch (i7) {
            case 0:
                auto3DBroadcastReceiver.lambda$sendNavigation3DBroadcast$2(context, i8);
                break;
            default:
                auto3DBroadcastReceiver.lambda$sendCruise3DBroadcast$3(context, i8);
                break;
        }
    }
}
