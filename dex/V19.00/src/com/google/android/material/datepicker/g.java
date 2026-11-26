package com.google.android.material.datepicker;

import android.content.Context;
import com.navi.tool.receiver.BootReceiver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3613a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f3614b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f3615c;

    public /* synthetic */ g(Object obj, long j7, int i7) {
        this.f3613a = i7;
        this.f3615c = obj;
        this.f3614b = j7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f3613a;
        long j7 = this.f3614b;
        Object obj = this.f3615c;
        switch (i7) {
            case 0:
                h hVar = (h) obj;
                hVar.getClass();
                hVar.f3621a.setError(String.format(hVar.f3625e, n1.z.i0(j7).replace(' ', (char) 160)));
                hVar.a();
                break;
            default:
                BootReceiver.lambda$scheduleGestureRetries$3((Context) obj, j7);
                break;
        }
    }
}
