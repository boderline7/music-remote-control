package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q implements h5.a {

    /* renamed from: a, reason: collision with root package name */
    public final b0 f459a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f460b;

    public q(w wVar, b0 b0Var) {
        this.f460b = wVar;
        this.f459a = b0Var;
    }

    @Override // h5.a
    public final Object a() {
        b bVar;
        w wVar = this.f460b;
        b0 b0Var = this.f459a;
        b bVar2 = b.INTERNAL_ERROR;
        IOException e7 = null;
        try {
            try {
                b0Var.B(this);
                while (b0Var.A(false, this)) {
                }
                bVar = b.NO_ERROR;
                try {
                    bVar2 = b.CANCEL;
                    wVar.A(bVar, bVar2, null);
                } catch (IOException e8) {
                    e7 = e8;
                    bVar2 = b.PROTOCOL_ERROR;
                    wVar.A(bVar2, bVar2, e7);
                    t5.b.c(b0Var);
                    return b5.d.INSTANCE;
                }
            } catch (Throwable th) {
                th = th;
                wVar.A(bVar, bVar2, e7);
                t5.b.c(b0Var);
                throw th;
            }
        } catch (IOException e9) {
            e7 = e9;
            bVar = bVar2;
        } catch (Throwable th2) {
            th = th2;
            bVar = bVar2;
            wVar.A(bVar, bVar2, e7);
            t5.b.c(b0Var);
            throw th;
        }
        t5.b.c(b0Var);
        return b5.d.INSTANCE;
    }
}
