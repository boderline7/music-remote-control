package o5;

import h5.p;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements n5.b {

    /* renamed from: a, reason: collision with root package name */
    public final CharSequence f8058a;

    /* renamed from: b, reason: collision with root package name */
    public final int f8059b;

    /* renamed from: c, reason: collision with root package name */
    public final int f8060c;

    /* renamed from: d, reason: collision with root package name */
    public final p f8061d;

    public c(CharSequence charSequence, int i7, int i8, j jVar) {
        i5.a.g("input", charSequence);
        this.f8058a = charSequence;
        this.f8059b = i7;
        this.f8060c = i8;
        this.f8061d = jVar;
    }

    @Override // n5.b
    public final Iterator iterator() {
        return new b(this);
    }
}
