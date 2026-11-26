package h0;

import android.content.res.Resources;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public final Resources f6634a;

    /* renamed from: b, reason: collision with root package name */
    public final Resources.Theme f6635b;

    public m(Resources resources, Resources.Theme theme) {
        this.f6634a = resources;
        this.f6635b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return this.f6634a.equals(mVar.f6634a) && o0.b.a(this.f6635b, mVar.f6635b);
    }

    public final int hashCode() {
        return o0.b.b(this.f6634a, this.f6635b);
    }
}
