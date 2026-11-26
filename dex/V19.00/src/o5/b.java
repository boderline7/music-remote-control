package o5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Iterator {

    /* renamed from: a, reason: collision with root package name */
    public int f8052a = -1;

    /* renamed from: b, reason: collision with root package name */
    public int f8053b;

    /* renamed from: c, reason: collision with root package name */
    public int f8054c;

    /* renamed from: d, reason: collision with root package name */
    public l5.e f8055d;

    /* renamed from: e, reason: collision with root package name */
    public int f8056e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c f8057f;

    public b(c cVar) {
        this.f8057f = cVar;
        int i7 = cVar.f8059b;
        int length = cVar.f8058a.length();
        if (length < 0) {
            throw new IllegalArgumentException(a.a.e("Cannot coerce value to an empty range: maximum ", length, " is less than minimum 0."));
        }
        if (i7 < 0) {
            i7 = 0;
        } else if (i7 > length) {
            i7 = length;
        }
        this.f8053b = i7;
        this.f8054c = i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        if (r7 < r3) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r8 = this;
            int r0 = r8.f8054c
            r1 = 0
            if (r0 >= 0) goto Lc
            r8.f8052a = r1
            r0 = 0
            r8.f8055d = r0
            goto L88
        Lc:
            o5.c r2 = r8.f8057f
            int r3 = r2.f8060c
            java.lang.CharSequence r4 = r2.f8058a
            r5 = -1
            r6 = 1
            if (r3 <= 0) goto L1d
            int r7 = r8.f8056e
            int r7 = r7 + r6
            r8.f8056e = r7
            if (r7 >= r3) goto L23
        L1d:
            int r3 = r4.length()
            if (r0 <= r3) goto L33
        L23:
            l5.e r0 = new l5.e
            int r1 = r8.f8053b
            int r2 = o5.k.D(r4)
            r0.<init>(r1, r2, r6)
            r8.f8055d = r0
            r8.f8054c = r5
            goto L86
        L33:
            int r0 = r8.f8054c
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            h5.p r2 = r2.f8061d
            o5.j r2 = (o5.j) r2
            int r3 = r2.f8063a
            switch(r3) {
                case 0: goto L4b;
                default: goto L42;
            }
        L42:
            int r0 = r0.intValue()
            b5.a r0 = r2.c(r0, r4)
            goto L53
        L4b:
            int r0 = r0.intValue()
            b5.a r0 = r2.c(r0, r4)
        L53:
            if (r0 != 0) goto L65
            l5.e r0 = new l5.e
            int r1 = r8.f8053b
            int r2 = o5.k.D(r4)
            r0.<init>(r1, r2, r6)
            r8.f8055d = r0
            r8.f8054c = r5
            goto L86
        L65:
            java.lang.Object r2 = r0.f2975a
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.f2976b
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r8.f8053b
            l5.e r3 = i5.a.y(r3, r2)
            r8.f8055d = r3
            int r2 = r2 + r0
            r8.f8053b = r2
            if (r0 != 0) goto L83
            r1 = r6
        L83:
            int r2 = r2 + r1
            r8.f8054c = r2
        L86:
            r8.f8052a = r6
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o5.b.a():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f8052a == -1) {
            a();
        }
        return this.f8052a == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f8052a == -1) {
            a();
        }
        if (this.f8052a == 0) {
            throw new NoSuchElementException();
        }
        l5.e eVar = this.f8055d;
        i5.a.e("null cannot be cast to non-null type kotlin.ranges.IntRange", eVar);
        this.f8055d = null;
        this.f8052a = -1;
        return eVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
