package u1;

import b2.s;
import b2.u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public final q f9282a;

    /* renamed from: b, reason: collision with root package name */
    public final String f9283b;

    /* renamed from: c, reason: collision with root package name */
    public final b2.i f9284c;

    public m(q qVar, q qVar2, String str) {
        if (qVar2 == null) {
            throw null;
        }
        this.f9282a = qVar;
        this.f9283b = str;
        this.f9284c = new b2.i(qVar.f9304c, new s(new u(str), new u(qVar2.f9302a)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (mVar.f9282a.equals(this.f9282a) && mVar.f9283b.equals(this.f9283b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f9283b.hashCode() * 37) + this.f9282a.f9302a.hashCode();
    }

    public final String toString() {
        return this.f9282a + "." + this.f9283b;
    }
}
