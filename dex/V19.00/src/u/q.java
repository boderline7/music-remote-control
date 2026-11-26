package u;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class q {
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CACHE_MEASURES = 256;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DEPENDENCY_ORDERING = 512;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GRAPH = 64;
    public static final int OPTIMIZATION_GRAPH_WRAP = 128;
    public static final int OPTIMIZATION_GROUPING = 1024;
    public static final int OPTIMIZATION_GROUPS = 32;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 257;

    /* renamed from: a, reason: collision with root package name */
    public static final boolean[] f9212a = new boolean[3];

    public static void a(j jVar, s.d dVar, i iVar) {
        iVar.f9156o = -1;
        iVar.f9158p = -1;
        h hVar = jVar.U[0];
        h hVar2 = h.WRAP_CONTENT;
        if (hVar != hVar2 && iVar.U[0] == h.MATCH_PARENT) {
            f fVar = iVar.J;
            int i7 = fVar.f9122g;
            int s6 = jVar.s();
            f fVar2 = iVar.L;
            int i8 = s6 - fVar2.f9122g;
            fVar.f9124i = dVar.k(fVar);
            fVar2.f9124i = dVar.k(fVar2);
            dVar.d(fVar.f9124i, i7);
            dVar.d(fVar2.f9124i, i8);
            iVar.f9156o = 2;
            iVar.f9129a0 = i7;
            int i9 = i8 - i7;
            iVar.W = i9;
            int i10 = iVar.f9135d0;
            if (i9 < i10) {
                iVar.W = i10;
            }
        }
        if (jVar.U[1] == hVar2 || iVar.U[1] != h.MATCH_PARENT) {
            return;
        }
        f fVar3 = iVar.K;
        int i11 = fVar3.f9122g;
        int m6 = jVar.m();
        f fVar4 = iVar.M;
        int i12 = m6 - fVar4.f9122g;
        fVar3.f9124i = dVar.k(fVar3);
        fVar4.f9124i = dVar.k(fVar4);
        dVar.d(fVar3.f9124i, i11);
        dVar.d(fVar4.f9124i, i12);
        if (iVar.f9133c0 > 0 || iVar.f9145i0 == 8) {
            f fVar5 = iVar.N;
            fVar5.f9124i = dVar.k(fVar5);
            dVar.d(fVar5.f9124i, iVar.f9133c0 + i11);
        }
        iVar.f9158p = 2;
        iVar.f9131b0 = i11;
        int i13 = i12 - i11;
        iVar.X = i13;
        int i14 = iVar.f9137e0;
        if (i13 < i14) {
            iVar.X = i14;
        }
    }

    public static final boolean b(int i7, int i8) {
        return (i7 & i8) == i8;
    }
}
