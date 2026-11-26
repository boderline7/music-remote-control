package l4;

import android.os.SystemClock;
import android.text.TextUtils;
import h4.k;
import h4.m;
import java.util.Map;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7421a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f7422b;

    public /* synthetic */ a(e eVar, int i7) {
        this.f7421a = i7;
        this.f7422b = eVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j7;
        long currentTimeMillis;
        long j8;
        Map.Entry higherEntry;
        switch (this.f7421a) {
            case 0:
                e eVar = this.f7422b;
                if (!eVar.f7434f || eVar.f7435g) {
                    return;
                }
                eVar.f();
                if (eVar.f7435g) {
                    return;
                }
                eVar.f7444p.postDelayed(this, 1000L);
                return;
            default:
                e eVar2 = this.f7422b;
                if (eVar2.f7435g) {
                    if (eVar2.f7442n) {
                        j7 = eVar2.f7439k;
                        currentTimeMillis = SystemClock.elapsedRealtime();
                        j8 = this.f7422b.f7441m;
                    } else {
                        j7 = eVar2.f7439k;
                        currentTimeMillis = System.currentTimeMillis();
                        j8 = this.f7422b.f7441m;
                    }
                    long j9 = (currentTimeMillis - j8) + j7;
                    e eVar3 = this.f7422b;
                    String str = h0.FRAGMENT_ENCODE_SET;
                    synchronized (eVar3.f7443o) {
                        try {
                            if (!eVar3.f7443o.isEmpty()) {
                                Map.Entry floorEntry = eVar3.f7443o.floorEntry(Long.valueOf(j9));
                                if (floorEntry != null) {
                                    str = (String) floorEntry.getValue();
                                    if (e.f7428x && (higherEntry = eVar3.f7443o.higherEntry((Long) floorEntry.getKey())) != null) {
                                        str = str + "\n" + ((String) higherEntry.getValue());
                                    }
                                }
                                if (!TextUtils.equals(str, eVar3.f7445q)) {
                                    eVar3.f7445q = str;
                                    i6.e b7 = i6.e.b();
                                    k kVar = new k(str);
                                    kVar.f6754b = 6;
                                    b7.f(kVar);
                                }
                            }
                        } finally {
                        }
                    }
                    e eVar4 = this.f7422b;
                    m mVar = new m(eVar4.f7436h, eVar4.f7437i, true, j9, eVar4.f7440l);
                    mVar.f6768l = 6;
                    mVar.f6765i = eVar4.f7438j;
                    i6.e.b().f(mVar);
                    this.f7422b.f7444p.postDelayed(this, 200L);
                    return;
                }
                return;
        }
    }
}
