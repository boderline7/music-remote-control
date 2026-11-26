package com.navi.tool.baidu;

import android.os.Handler;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4625a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f4626b;

    public /* synthetic */ b(e eVar, int i7) {
        this.f4625a = i7;
        this.f4626b = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4625a) {
            case 0:
                h hVar = h.B;
                if (hVar.f4676y) {
                    hVar.f4676y = false;
                    hVar.f4677z = h0.FRAGMENT_ENCODE_SET;
                    hVar.A = 0L;
                    this.f4626b.getClass();
                    e.o();
                    break;
                }
                break;
            case 1:
                e eVar = this.f4626b;
                if (!eVar.n()) {
                    Handler handler = eVar.f4636c;
                    b bVar = eVar.f4645l;
                    handler.removeCallbacks(bVar);
                    eVar.f4636c.postDelayed(bVar, 1000L);
                    break;
                } else {
                    try {
                        e.c(eVar);
                    } catch (Exception unused) {
                    }
                    Handler handler2 = eVar.f4636c;
                    b bVar2 = eVar.f4645l;
                    handler2.removeCallbacks(bVar2);
                    eVar.f4636c.postDelayed(bVar2, 1000L);
                    break;
                }
            case 2:
                e eVar2 = this.f4626b;
                if (eVar2.f4639f) {
                    eVar2.h();
                    break;
                }
                break;
            default:
                e eVar3 = this.f4626b;
                if (!eVar3.n() && eVar3.f4639f) {
                    try {
                        eVar3.f4634a.unbindService(eVar3.f4646m);
                    } catch (Exception unused2) {
                    }
                    eVar3.f4638e = false;
                    eVar3.f4636c.postDelayed(eVar3.f4647n, 5000L);
                    break;
                }
                break;
        }
    }
}
