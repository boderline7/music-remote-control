package a2;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements c2.d, e2.i, Comparable {
    public static final String PREFIX = "v";

    /* renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap f235c = new ConcurrentHashMap(10000, 0.75f);

    /* renamed from: d, reason: collision with root package name */
    public static final k f236d = new k(0);

    /* renamed from: a, reason: collision with root package name */
    public final int f237a;

    /* renamed from: b, reason: collision with root package name */
    public final c2.d f238b;

    public m(int i7, c2.d dVar) {
        if (i7 < 0) {
            throw new IllegalArgumentException("reg < 0");
        }
        if (dVar == null) {
            throw new NullPointerException("type == null");
        }
        this.f237a = i7;
        this.f238b = dVar;
    }

    public static m f(int i7, c2.d dVar) {
        l lVar = (l) f236d.get();
        lVar.f233a = i7;
        lVar.f234b = dVar;
        ConcurrentHashMap concurrentHashMap = f235c;
        m mVar = (m) concurrentHashMap.get(lVar);
        if (mVar == null) {
            mVar = new m(lVar.f233a, lVar.f234b);
            m mVar2 = (m) concurrentHashMap.putIfAbsent(mVar, mVar);
            if (mVar2 != null) {
                return mVar2;
            }
        }
        return mVar;
    }

    public final boolean a(int i7, c2.d dVar) {
        return this.f237a == i7 && this.f238b.equals(dVar);
    }

    @Override // c2.d
    public final c2.c b() {
        return this.f238b.b();
    }

    @Override // c2.d
    public final int c() {
        return this.f238b.c();
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        m mVar = (m) obj;
        int i7 = mVar.f237a;
        int i8 = this.f237a;
        if (i8 < i7) {
            return -1;
        }
        if (i8 > i7) {
            return 1;
        }
        if (this != mVar) {
            int compareTo = this.f238b.b().f3040a.compareTo(mVar.f238b.b().f3040a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    @Override // e2.i
    public final String d() {
        return i(true);
    }

    public final int e() {
        return this.f238b.b().e();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            return a(mVar.f237a, mVar.f238b);
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return a(lVar.f233a, lVar.f234b);
    }

    public final boolean g() {
        int i7 = this.f238b.b().f3041b;
        return i7 == 4 || i7 == 7;
    }

    public final String h() {
        return PREFIX + this.f237a;
    }

    public final int hashCode() {
        return (this.f238b.hashCode() * 31) + this.f237a;
    }

    public final String i(boolean z6) {
        StringBuilder sb = new StringBuilder(40);
        sb.append(h());
        sb.append(":");
        c2.d dVar = this.f238b;
        c2.c b7 = dVar.b();
        sb.append(b7);
        if (b7 != dVar) {
            sb.append("=");
            if (z6 && (dVar instanceof b2.u)) {
                sb.append(((b2.u) dVar).g());
            } else if (z6 && (dVar instanceof b2.a)) {
                sb.append(dVar.d());
            } else {
                sb.append(dVar);
            }
        }
        return sb.toString();
    }

    public final String toString() {
        return i(false);
    }
}
