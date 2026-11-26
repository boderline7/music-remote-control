package w5;

import a6.w;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import x5.m;
import x5.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9502e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f9503f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i7, Object obj, String str) {
        super(str, true);
        this.f9502e = i7;
        this.f9503f = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(o oVar, String str) {
        super(str, true);
        this.f9502e = 1;
        this.f9503f = oVar;
    }

    @Override // w5.a
    public final long a() {
        int i7 = 0;
        switch (this.f9502e) {
            case 0:
                ((h5.a) this.f9503f).a();
                return -1L;
            case 1:
                o oVar = (o) this.f9503f;
                long nanoTime = System.nanoTime();
                Iterator it = oVar.f9724e.iterator();
                long j7 = Long.MIN_VALUE;
                m mVar = null;
                int i8 = 0;
                while (it.hasNext()) {
                    m mVar2 = (m) it.next();
                    i5.a.f("connection", mVar2);
                    synchronized (mVar2) {
                        if (oVar.b(mVar2, nanoTime) > 0) {
                            i8++;
                        } else {
                            i7++;
                            long j8 = nanoTime - mVar2.f9719p;
                            if (j8 > j7) {
                                mVar = mVar2;
                                j7 = j8;
                            }
                        }
                    }
                }
                long j9 = oVar.f9721b;
                if (j7 < j9 && i7 <= oVar.f9720a) {
                    if (i7 > 0) {
                        return j9 - j7;
                    }
                    if (i8 > 0) {
                        return j9;
                    }
                    return -1L;
                }
                i5.a.d(mVar);
                synchronized (mVar) {
                    if (!(!mVar.f9718o.isEmpty())) {
                        if (mVar.f9719p + j7 == nanoTime) {
                            mVar.f9712i = true;
                            oVar.f9724e.remove(mVar);
                            Socket socket = mVar.f9706c;
                            i5.a.d(socket);
                            t5.b.d(socket);
                            if (oVar.f9724e.isEmpty()) {
                                oVar.f9722c.a();
                            }
                        }
                    }
                }
                return 0L;
            default:
                w wVar = (w) this.f9503f;
                wVar.getClass();
                try {
                    wVar.f501y.E(2, 0, false);
                } catch (IOException e7) {
                    wVar.B(e7);
                }
                return -1L;
        }
    }
}
