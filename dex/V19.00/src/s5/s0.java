package s5;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s0 extends v0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8892a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l0 f8893b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8894c;

    public /* synthetic */ s0(l0 l0Var, Object obj, int i7) {
        this.f8892a = i7;
        this.f8893b = l0Var;
        this.f8894c = obj;
    }

    @Override // s5.v0
    public final long contentLength() {
        int i7 = this.f8892a;
        Object obj = this.f8894c;
        switch (i7) {
            case 0:
                return ((File) obj).length();
            default:
                return ((g6.l) obj).c();
        }
    }

    @Override // s5.v0
    public final l0 contentType() {
        return this.f8893b;
    }

    @Override // s5.v0
    public final void writeTo(g6.i iVar) {
        int i7 = this.f8892a;
        Object obj = this.f8894c;
        switch (i7) {
            case 0:
                File file = (File) obj;
                Logger logger = g6.p.f6587a;
                i5.a.g("<this>", file);
                g6.e eVar = new g6.e(new FileInputStream(file), g6.d0.NONE);
                try {
                    ((g6.s) iVar).B(eVar);
                    n1.z.A(eVar, null);
                    return;
                } finally {
                }
            default:
                ((g6.s) iVar).l((g6.l) obj);
                return;
        }
    }
}
