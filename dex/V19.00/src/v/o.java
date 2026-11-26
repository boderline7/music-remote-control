package v;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o {
    public static final int BASELINE = 2;
    public static final int END = 1;
    public static final int START = 0;

    /* renamed from: a, reason: collision with root package name */
    public v f9355a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f9356b;

    public static long a(h hVar, long j7) {
        v vVar = hVar.f9342d;
        if (vVar instanceof l) {
            return j7;
        }
        ArrayList arrayList = hVar.f9349k;
        int size = arrayList.size();
        long j8 = j7;
        for (int i7 = 0; i7 < size; i7++) {
            e eVar = (e) arrayList.get(i7);
            if (eVar instanceof h) {
                h hVar2 = (h) eVar;
                if (hVar2.f9342d != vVar) {
                    j8 = Math.min(j8, a(hVar2, hVar2.f9344f + j7));
                }
            }
        }
        if (hVar != vVar.f9384i) {
            return j8;
        }
        long j9 = vVar.j();
        long j10 = j7 - j9;
        return Math.min(Math.min(j8, a(vVar.f9383h, j10)), j10 - r9.f9344f);
    }

    public static long b(h hVar, long j7) {
        v vVar = hVar.f9342d;
        if (vVar instanceof l) {
            return j7;
        }
        ArrayList arrayList = hVar.f9349k;
        int size = arrayList.size();
        long j8 = j7;
        for (int i7 = 0; i7 < size; i7++) {
            e eVar = (e) arrayList.get(i7);
            if (eVar instanceof h) {
                h hVar2 = (h) eVar;
                if (hVar2.f9342d != vVar) {
                    j8 = Math.max(j8, b(hVar2, hVar2.f9344f + j7));
                }
            }
        }
        if (hVar != vVar.f9383h) {
            return j8;
        }
        long j9 = vVar.j();
        long j10 = j7 + j9;
        return Math.max(Math.max(j8, b(vVar.f9384i, j10)), j10 - r9.f9344f);
    }
}
