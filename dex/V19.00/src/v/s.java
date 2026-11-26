package v;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s {

    /* renamed from: f, reason: collision with root package name */
    public static int f9368f;

    /* renamed from: a, reason: collision with root package name */
    public ArrayList f9369a;

    /* renamed from: b, reason: collision with root package name */
    public int f9370b;

    /* renamed from: c, reason: collision with root package name */
    public int f9371c;

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f9372d;

    /* renamed from: e, reason: collision with root package name */
    public int f9373e;

    public final void a(ArrayList arrayList) {
        int size = this.f9369a.size();
        if (this.f9373e != -1 && size > 0) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                s sVar = (s) arrayList.get(i7);
                if (this.f9373e == sVar.f9370b) {
                    c(this.f9371c, sVar);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public final int b(s.d dVar, int i7) {
        int n6;
        int n7;
        ArrayList arrayList = this.f9369a;
        if (arrayList.size() == 0) {
            return 0;
        }
        u.j jVar = (u.j) ((u.i) arrayList.get(0)).V;
        dVar.t();
        jVar.c(dVar, false);
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            ((u.i) arrayList.get(i8)).c(dVar, false);
        }
        if (i7 == 0 && jVar.E0 > 0) {
            u.b.a(jVar, dVar, arrayList, 0);
        }
        if (i7 == 1 && jVar.F0 > 0) {
            u.b.a(jVar, dVar, arrayList, 1);
        }
        try {
            dVar.p();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        this.f9372d = new ArrayList();
        for (int i9 = 0; i9 < arrayList.size(); i9++) {
            this.f9372d.add(new r(this, (u.i) arrayList.get(i9), dVar, i7));
        }
        if (i7 == 0) {
            n6 = s.d.n(jVar.J);
            n7 = s.d.n(jVar.L);
            dVar.t();
        } else {
            n6 = s.d.n(jVar.K);
            n7 = s.d.n(jVar.M);
            dVar.t();
        }
        return n7 - n6;
    }

    public final void c(int i7, s sVar) {
        Iterator it = this.f9369a.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            ArrayList arrayList = sVar.f9369a;
            if (!arrayList.contains(iVar)) {
                arrayList.add(iVar);
            }
            int i8 = sVar.f9370b;
            if (i7 == 0) {
                iVar.f9167t0 = i8;
            } else {
                iVar.f9169u0 = i8;
            }
        }
        this.f9373e = sVar.f9370b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int i7 = this.f9371c;
        sb.append(i7 == 0 ? "Horizontal" : i7 == 1 ? "Vertical" : i7 == 2 ? "Both" : "Unknown");
        sb.append(" [");
        sb.append(this.f9370b);
        sb.append("] <");
        String sb2 = sb.toString();
        Iterator it = this.f9369a.iterator();
        while (it.hasNext()) {
            sb2 = sb2 + " " + ((u.i) it.next()).f9149k0;
        }
        return a.a.g(sb2, " >");
    }
}
