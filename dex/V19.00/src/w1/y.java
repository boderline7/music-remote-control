package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y extends l {

    /* renamed from: e, reason: collision with root package name */
    public final e f9455e;

    public y(j jVar, a2.s sVar, a2.n nVar, e eVar) {
        super(jVar, sVar, nVar);
        if (eVar == null) {
            throw new NullPointerException("target == null");
        }
        this.f9455e = eVar;
    }

    @Override // w1.h
    public final String a() {
        e eVar = this.f9455e;
        return eVar == null ? "????" : eVar.f();
    }

    @Override // w1.h
    public final h j(j jVar) {
        return new y(jVar, this.f9428c, this.f9429d, this.f9455e);
    }

    @Override // w1.h
    public final h l(a2.n nVar) {
        return new y(this.f9427b, this.f9428c, nVar, this.f9455e);
    }

    public final int n() {
        return this.f9455e.e() - e();
    }

    public final boolean o() {
        return this.f9426a >= 0 && this.f9455e.f9426a >= 0;
    }

    public final y p(e eVar) {
        j jVar;
        j jVar2 = this.f9427b;
        switch (jVar2.f9431a) {
            case 50:
                jVar = k.IF_NE;
                break;
            case 51:
                jVar = k.IF_EQ;
                break;
            case 52:
                jVar = k.IF_GE;
                break;
            case 53:
                jVar = k.IF_LT;
                break;
            case 54:
                jVar = k.IF_LE;
                break;
            case 55:
                jVar = k.IF_GT;
                break;
            case z1.c.IF_EQZ /* 56 */:
                jVar = k.IF_NEZ;
                break;
            case z1.c.IF_NEZ /* 57 */:
                jVar = k.IF_EQZ;
                break;
            case z1.c.IF_LTZ /* 58 */:
                jVar = k.IF_GEZ;
                break;
            case z1.c.IF_GEZ /* 59 */:
                jVar = k.IF_LTZ;
                break;
            case 60:
                jVar = k.IF_LEZ;
                break;
            case z1.c.IF_LEZ /* 61 */:
                jVar = k.IF_GTZ;
                break;
            default:
                throw new IllegalArgumentException("bogus opcode: " + jVar2);
        }
        return new y(jVar, this.f9428c, this.f9429d, eVar);
    }
}
