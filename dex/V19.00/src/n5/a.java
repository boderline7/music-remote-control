package n5;

import c5.d;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements b {

    /* renamed from: a, reason: collision with root package name */
    public final b f7785a;

    /* renamed from: b, reason: collision with root package name */
    public final int f7786b;

    public a(b bVar, int i7) {
        i5.a.g("sequence", bVar);
        this.f7785a = bVar;
        this.f7786b = i7;
        if (i7 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i7 + '.').toString());
    }

    @Override // n5.b
    public final Iterator iterator() {
        return new d(this);
    }
}
