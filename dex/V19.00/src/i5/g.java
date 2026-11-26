package i5;

import androidx.activity.g0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g extends c implements m5.b {

    /* renamed from: g, reason: collision with root package name */
    public final int f6809g;

    /* renamed from: h, reason: collision with root package name */
    public final int f6810h;

    public g(g0 g0Var) {
        super(g0Var, false);
        this.f6809g = 0;
        this.f6810h = 0;
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f6804d.equals(gVar.f6804d) && this.f6805e.equals(gVar.f6805e) && this.f6810h == gVar.f6810h && this.f6809g == gVar.f6809g && a.c(this.f6802b, gVar.f6802b) && a.c(b(), gVar.b());
        }
        if (!(obj instanceof m5.b)) {
            return false;
        }
        g gVar2 = this.f6801a;
        if (gVar2 == null) {
            k.f6813a.getClass();
            this.f6801a = this;
            gVar2 = this;
        }
        return obj.equals(gVar2);
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final int hashCode() {
        return this.f6805e.hashCode() + ((this.f6804d.hashCode() + (b() == null ? 0 : b().hashCode() * 31)) * 31);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        g gVar = this.f6801a;
        if (gVar == null) {
            k.f6813a.getClass();
            this.f6801a = this;
            gVar = this;
        }
        if (gVar != this) {
            return gVar.toString();
        }
        String str = this.f6804d;
        return "<init>".equals(str) ? "constructor (Kotlin reflection is not available)" : a.a.h("function ", str, " (Kotlin reflection is not available)");
    }
}
