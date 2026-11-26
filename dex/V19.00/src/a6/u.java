package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ w f472e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f473f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(String str, w wVar, long j7) {
        super(str, true);
        this.f472e = wVar;
        this.f473f = j7;
    }

    @Override // w5.a
    public final long a() {
        w wVar;
        boolean z6;
        synchronized (this.f472e) {
            wVar = this.f472e;
            long j7 = wVar.f490n;
            long j8 = wVar.f489m;
            if (j7 < j8) {
                z6 = true;
            } else {
                wVar.f489m = j8 + 1;
                z6 = false;
            }
        }
        if (z6) {
            wVar.B(null);
            return -1L;
        }
        try {
            wVar.f501y.E(1, 0, false);
        } catch (IOException e7) {
            wVar.B(e7);
        }
        return this.f473f;
    }
}
