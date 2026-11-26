package androidx.activity;

import android.window.OnBackInvokedCallback;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class z implements OnBackInvokedCallback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f661a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f662b;

    public /* synthetic */ z(int i7, Object obj) {
        this.f661a = i7;
        this.f662b = obj;
    }

    public final void onBackInvoked() {
        switch (this.f661a) {
            case 0:
                h5.a aVar = (h5.a) this.f662b;
                i5.a.g("$onBackInvoked", aVar);
                aVar.a();
                break;
            case 1:
                ((androidx.appcompat.app.j0) this.f662b).F();
                break;
            case 2:
                ((Runnable) this.f662b).run();
                break;
            default:
                ((j3.b) this.f662b).a();
                break;
        }
    }
}
