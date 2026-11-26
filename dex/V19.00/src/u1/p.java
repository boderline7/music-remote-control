package u1;

import b2.s;
import b2.u;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    public final q f9296a;

    /* renamed from: b, reason: collision with root package name */
    public final q f9297b;

    /* renamed from: c, reason: collision with root package name */
    public final String f9298c;

    /* renamed from: d, reason: collision with root package name */
    public final r f9299d;

    /* renamed from: e, reason: collision with root package name */
    public final b2.r f9300e;

    public p(q qVar, q qVar2, String str, r rVar) {
        if (qVar == null || qVar2 == null || str == null) {
            throw null;
        }
        this.f9296a = qVar;
        this.f9297b = qVar2;
        this.f9298c = str;
        this.f9299d = rVar;
        this.f9300e = new b2.r(qVar.f9304c, new s(new u(str), new u(a(false))));
    }

    public final String a(boolean z6) {
        StringBuilder sb = new StringBuilder("(");
        if (z6) {
            sb.append(this.f9296a.f9302a);
        }
        for (q qVar : this.f9299d.f9305a) {
            sb.append(qVar.f9302a);
        }
        sb.append(")");
        sb.append(this.f9297b.f9302a);
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (pVar.f9296a.equals(this.f9296a) && pVar.f9298c.equals(this.f9298c) && pVar.f9299d.equals(this.f9299d) && pVar.f9297b.equals(this.f9297b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f9297b.f9302a.hashCode() + ((Arrays.hashCode(this.f9299d.f9305a) + ((this.f9298c.hashCode() + ((this.f9296a.f9302a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return this.f9296a + "." + this.f9298c + "(" + this.f9299d + ")";
    }
}
