package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g implements e2.i {

    /* renamed from: a, reason: collision with root package name */
    public final p f228a;

    /* renamed from: b, reason: collision with root package name */
    public final s f229b;

    /* renamed from: c, reason: collision with root package name */
    public final m f230c;

    /* renamed from: d, reason: collision with root package name */
    public final n f231d;

    public g(p pVar, s sVar, m mVar, n nVar) {
        if (pVar == null) {
            throw new NullPointerException("opcode == null");
        }
        if (sVar == null) {
            throw new NullPointerException("position == null");
        }
        if (nVar == null) {
            throw new NullPointerException("sources == null");
        }
        this.f228a = pVar;
        this.f229b = sVar;
        this.f230c = mVar;
        this.f231d = nVar;
    }

    public abstract void a(f fVar);

    @Override // e2.i
    public final String d() {
        String f7 = f();
        StringBuilder sb = new StringBuilder(80);
        sb.append(this.f229b);
        sb.append(": ");
        p pVar = this.f228a;
        String str = pVar.f247g;
        if (str == null) {
            str = pVar.toString();
        }
        sb.append(str);
        if (f7 != null) {
            sb.append("(");
            sb.append(f7);
            sb.append(")");
        }
        m mVar = this.f230c;
        if (mVar == null) {
            sb.append(" .");
        } else {
            sb.append(" ");
            sb.append(mVar.i(true));
        }
        sb.append(" <-");
        n nVar = this.f231d;
        int length = nVar.f6354b.length;
        if (length == 0) {
            sb.append(" .");
        } else {
            for (int i7 = 0; i7 < length; i7++) {
                sb.append(" ");
                sb.append(((m) nVar.f(i7)).i(true));
            }
        }
        return sb.toString();
    }

    public abstract c2.e e();

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public String f() {
        return null;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        String f7 = f();
        StringBuilder sb = new StringBuilder(80);
        sb.append("Insn{");
        sb.append(this.f229b);
        sb.append(' ');
        sb.append(this.f228a);
        if (f7 != null) {
            sb.append(' ');
            sb.append(f7);
        }
        sb.append(" :: ");
        m mVar = this.f230c;
        if (mVar != null) {
            sb.append(mVar);
            sb.append(" <- ");
        }
        sb.append(this.f231d);
        sb.append('}');
        return sb.toString();
    }
}
