package androidx.appcompat.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f0 {

    /* renamed from: a, reason: collision with root package name */
    public BroadcastReceiver f710a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j0 f711b;

    public f0(j0 j0Var) {
        this.f711b = j0Var;
    }

    public final void a() {
        BroadcastReceiver broadcastReceiver = this.f710a;
        if (broadcastReceiver != null) {
            try {
                this.f711b.f778k.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
            }
            this.f710a = null;
        }
    }

    public abstract int b();

    public final void c() {
        IntentFilter intentFilter;
        a();
        switch (((e0) this).f707c) {
            case 0:
                intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                break;
            default:
                intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.TIME_SET");
                intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                intentFilter.addAction("android.intent.action.TIME_TICK");
                break;
        }
        if (intentFilter.countActions() == 0) {
            return;
        }
        if (this.f710a == null) {
            this.f710a = new BroadcastReceiver() { // from class: androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager$1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    e0 e0Var = (e0) f0.this;
                    int i7 = e0Var.f707c;
                    j0 j0Var = e0Var.f708d;
                    switch (i7) {
                        case 0:
                            j0Var.n(true, true);
                            break;
                        default:
                            j0Var.n(true, true);
                            break;
                    }
                }
            };
        }
        this.f711b.f778k.registerReceiver(this.f710a, intentFilter);
    }
}
