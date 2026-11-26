package u;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: b, reason: collision with root package name */
    public int f9117b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9118c;

    /* renamed from: d, reason: collision with root package name */
    public final i f9119d;

    /* renamed from: e, reason: collision with root package name */
    public final e f9120e;

    /* renamed from: f, reason: collision with root package name */
    public f f9121f;

    /* renamed from: i, reason: collision with root package name */
    public s.i f9124i;

    /* renamed from: a, reason: collision with root package name */
    public HashSet f9116a = null;

    /* renamed from: g, reason: collision with root package name */
    public int f9122g = 0;

    /* renamed from: h, reason: collision with root package name */
    public int f9123h = Integer.MIN_VALUE;

    public f(i iVar, e eVar) {
        this.f9119d = iVar;
        this.f9120e = eVar;
    }

    public final void a(f fVar, int i7) {
        b(fVar, i7, Integer.MIN_VALUE, false);
    }

    public final boolean b(f fVar, int i7, int i8, boolean z6) {
        if (fVar == null) {
            j();
            return true;
        }
        if (!z6 && !i(fVar)) {
            return false;
        }
        this.f9121f = fVar;
        if (fVar.f9116a == null) {
            fVar.f9116a = new HashSet();
        }
        HashSet hashSet = this.f9121f.f9116a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.f9122g = i7;
        this.f9123h = i8;
        return true;
    }

    public final void c(int i7, v.s sVar, ArrayList arrayList) {
        HashSet hashSet = this.f9116a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                l6.f.t(((f) it.next()).f9119d, i7, arrayList, sVar);
            }
        }
    }

    public final int d() {
        if (this.f9118c) {
            return this.f9117b;
        }
        return 0;
    }

    public final int e() {
        f fVar;
        if (this.f9119d.f9145i0 == 8) {
            return 0;
        }
        int i7 = this.f9123h;
        return (i7 == Integer.MIN_VALUE || (fVar = this.f9121f) == null || fVar.f9119d.f9145i0 != 8) ? this.f9122g : i7;
    }

    public final f f() {
        int[] iArr = d.f9114a;
        e eVar = this.f9120e;
        int i7 = iArr[eVar.ordinal()];
        i iVar = this.f9119d;
        switch (i7) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return iVar.L;
            case 3:
                return iVar.J;
            case 4:
                return iVar.M;
            case 5:
                return iVar.K;
            default:
                throw new AssertionError(eVar.name());
        }
    }

    public final boolean g() {
        HashSet hashSet = this.f9116a;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((f) it.next()).f().h()) {
                return true;
            }
        }
        return false;
    }

    public final boolean h() {
        return this.f9121f != null;
    }

    public final boolean i(f fVar) {
        if (fVar == null) {
            return false;
        }
        e eVar = this.f9120e;
        i iVar = fVar.f9119d;
        e eVar2 = fVar.f9120e;
        if (eVar2 == eVar) {
            return eVar != e.BASELINE || (iVar.E && this.f9119d.E);
        }
        switch (d.f9114a[eVar.ordinal()]) {
            case 1:
                return (eVar2 == e.BASELINE || eVar2 == e.CENTER_X || eVar2 == e.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z6 = eVar2 == e.LEFT || eVar2 == e.RIGHT;
                if (iVar instanceof n) {
                    return z6 || eVar2 == e.CENTER_X;
                }
                return z6;
            case 4:
            case 5:
                boolean z7 = eVar2 == e.TOP || eVar2 == e.BOTTOM;
                if (iVar instanceof n) {
                    return z7 || eVar2 == e.CENTER_Y;
                }
                return z7;
            case 6:
                return (eVar2 == e.LEFT || eVar2 == e.RIGHT) ? false : true;
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(eVar.name());
        }
    }

    public final void j() {
        HashSet hashSet;
        f fVar = this.f9121f;
        if (fVar != null && (hashSet = fVar.f9116a) != null) {
            hashSet.remove(this);
            if (this.f9121f.f9116a.size() == 0) {
                this.f9121f.f9116a = null;
            }
        }
        this.f9116a = null;
        this.f9121f = null;
        this.f9122g = 0;
        this.f9123h = Integer.MIN_VALUE;
        this.f9118c = false;
        this.f9117b = 0;
    }

    public final void k() {
        s.i iVar = this.f9124i;
        if (iVar == null) {
            this.f9124i = new s.i(s.h.UNRESTRICTED);
        } else {
            iVar.c();
        }
    }

    public final void l(int i7) {
        this.f9117b = i7;
        this.f9118c = true;
    }

    public final String toString() {
        return this.f9119d.f9149k0 + ":" + this.f9120e.toString();
    }
}
