package v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class v implements e {

    /* renamed from: a, reason: collision with root package name */
    public int f9376a;

    /* renamed from: b, reason: collision with root package name */
    public u.i f9377b;

    /* renamed from: c, reason: collision with root package name */
    public o f9378c;

    /* renamed from: d, reason: collision with root package name */
    public u.h f9379d;

    /* renamed from: e, reason: collision with root package name */
    public final i f9380e = new i(this);

    /* renamed from: f, reason: collision with root package name */
    public int f9381f = 0;

    /* renamed from: g, reason: collision with root package name */
    public boolean f9382g = false;

    /* renamed from: h, reason: collision with root package name */
    public final h f9383h = new h(this);

    /* renamed from: i, reason: collision with root package name */
    public final h f9384i = new h(this);

    /* renamed from: j, reason: collision with root package name */
    public u f9385j = u.NONE;

    public v(u.i iVar) {
        this.f9377b = iVar;
    }

    public static void b(h hVar, h hVar2, int i7) {
        hVar.f9350l.add(hVar2);
        hVar.f9344f = i7;
        hVar2.f9349k.add(hVar);
    }

    public static h h(u.f fVar) {
        u.f fVar2 = fVar.f9121f;
        if (fVar2 == null) {
            return null;
        }
        int i7 = t.f9374a[fVar2.f9120e.ordinal()];
        u.i iVar = fVar2.f9119d;
        if (i7 == 1) {
            return iVar.f9134d.f9383h;
        }
        if (i7 == 2) {
            return iVar.f9134d.f9384i;
        }
        if (i7 == 3) {
            return iVar.f9136e.f9383h;
        }
        if (i7 == 4) {
            return iVar.f9136e.f9358k;
        }
        if (i7 != 5) {
            return null;
        }
        return iVar.f9136e.f9384i;
    }

    public static h i(u.f fVar, int i7) {
        u.f fVar2 = fVar.f9121f;
        if (fVar2 == null) {
            return null;
        }
        u.i iVar = fVar2.f9119d;
        v vVar = i7 == 0 ? iVar.f9134d : iVar.f9136e;
        int i8 = t.f9374a[fVar2.f9120e.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                if (i8 != 3) {
                    if (i8 != 5) {
                        return null;
                    }
                }
            }
            return vVar.f9384i;
        }
        return vVar.f9383h;
    }

    public final void c(h hVar, h hVar2, int i7, i iVar) {
        hVar.f9350l.add(hVar2);
        hVar.f9350l.add(this.f9380e);
        hVar.f9346h = i7;
        hVar.f9347i = iVar;
        hVar2.f9349k.add(hVar);
        iVar.f9349k.add(hVar);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i7, int i8) {
        int max;
        if (i8 == 0) {
            u.i iVar = this.f9377b;
            int i9 = iVar.f9170v;
            max = Math.max(iVar.f9168u, i7);
            if (i9 > 0) {
                max = Math.min(i9, i7);
            }
            if (max == i7) {
                return i7;
            }
        } else {
            u.i iVar2 = this.f9377b;
            int i10 = iVar2.f9173y;
            max = Math.max(iVar2.f9172x, i7);
            if (i10 > 0) {
                max = Math.min(i10, i7);
            }
            if (max == i7) {
                return i7;
            }
        }
        return max;
    }

    public long j() {
        if (this.f9380e.f9348j) {
            return r0.f9345g;
        }
        return 0L;
    }

    public abstract boolean k();

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
    
        if (r10.f9376a == 3) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(u.f r13, u.f r14, int r15) {
        /*
            Method dump skipped, instructions count: 234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v.v.l(u.f, u.f, int):void");
    }
}
