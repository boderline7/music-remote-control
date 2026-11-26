package b2;

import com.android.dx.util.MutabilityException;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends a {

    /* renamed from: a, reason: collision with root package name */
    public final b f2930a;

    public c(b bVar) {
        if (bVar.f6364a) {
            throw new MutabilityException("mutable instance", null);
        }
        this.f2930a = bVar;
    }

    @Override // e2.i
    public final String d() {
        return this.f2930a.h("{", "}", true);
    }

    @Override // b2.a
    public final int e(a aVar) {
        return this.f2930a.compareTo(((c) aVar).f2930a);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        return this.f2930a.equals(((c) obj).f2930a);
    }

    @Override // b2.a
    public final String f() {
        return "array";
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f2930a.f6354b);
    }

    public final String toString() {
        return this.f2930a.h("array{", "}", false);
    }
}
