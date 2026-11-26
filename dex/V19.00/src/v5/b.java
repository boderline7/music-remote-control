package v5;

import java.util.ArrayList;
import o5.k;
import s5.a1;
import s5.d0;
import s5.e;
import s5.j0;
import s5.q0;
import s5.w0;
import s5.x0;
import s5.y;
import x5.j;
import y5.h;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements j0 {
    public static final a Companion = new a();

    @Override // s5.j0
    public final x0 a(h hVar) {
        y yVar;
        String str;
        d0 d0Var;
        String str2;
        System.currentTimeMillis();
        androidx.appcompat.widget.y yVar2 = hVar.f10220e;
        i5.a.g("request", yVar2);
        d dVar = new d(yVar2, null);
        e eVar = (e) yVar2.f1487g;
        if (eVar == null) {
            s5.d dVar2 = e.Companion;
            d0 d0Var2 = (d0) yVar2.f1484d;
            dVar2.getClass();
            eVar = s5.d.a(d0Var2);
            yVar2.f1487g = eVar;
        }
        if (eVar.f8779j) {
            dVar = new d(null, null);
        }
        j jVar = hVar.f10216a;
        j jVar2 = jVar instanceof j ? jVar : null;
        if (jVar2 == null || (yVar = jVar2.f9690e) == null) {
            yVar = y.NONE;
        }
        androidx.appcompat.widget.y yVar3 = dVar.f9401a;
        x0 x0Var = dVar.f9402b;
        if (yVar3 == null && x0Var == null) {
            w0 w0Var = new w0();
            w0Var.c(yVar2);
            q0 q0Var = q0.HTTP_1_1;
            i5.a.g("protocol", q0Var);
            w0Var.f8901b = q0Var;
            w0Var.f8902c = 504;
            w0Var.f8903d = "Unsatisfiable Request (only-if-cached)";
            w0Var.f8906g = t5.b.EMPTY_RESPONSE;
            w0Var.f8910k = -1L;
            w0Var.f8911l = System.currentTimeMillis();
            x0 a7 = w0Var.a();
            yVar.getClass();
            i5.a.g("call", jVar);
            return a7;
        }
        if (yVar3 == null) {
            i5.a.d(x0Var);
            w0 C = x0Var.C();
            x0 a8 = a.a(Companion, x0Var);
            w0.b("cacheResponse", a8);
            C.f8908i = a8;
            x0 a9 = C.a();
            yVar.getClass();
            i5.a.g("call", jVar);
            return a9;
        }
        if (x0Var != null) {
            yVar.getClass();
            i5.a.g("call", jVar);
        }
        x0 b7 = hVar.b(yVar3);
        String str3 = "networkResponse";
        if (x0Var == null) {
            str = "networkResponse";
        } else {
            if (b7.f8916d == 304) {
                w0 C2 = x0Var.C();
                Companion.getClass();
                ArrayList arrayList = new ArrayList(20);
                d0 d0Var3 = x0Var.f8918f;
                int size = d0Var3.size();
                int i7 = 0;
                while (true) {
                    d0Var = b7.f8918f;
                    if (i7 >= size) {
                        break;
                    }
                    String b8 = d0Var3.b(i7);
                    int i8 = size;
                    String d7 = d0Var3.d(i7);
                    d0 d0Var4 = d0Var3;
                    if (k.C("Warning", b8)) {
                        str2 = str3;
                        if (k.R(d7, "1", false)) {
                            i7++;
                            size = i8;
                            d0Var3 = d0Var4;
                            str3 = str2;
                        }
                    } else {
                        str2 = str3;
                    }
                    if (k.C("Content-Length", b8) || k.C("Content-Encoding", b8) || k.C("Content-Type", b8) || !a.b(b8) || d0Var.a(b8) == null) {
                        i5.a.g("name", b8);
                        i5.a.g("value", d7);
                        arrayList.add(b8);
                        arrayList.add(k.U(d7).toString());
                    }
                    i7++;
                    size = i8;
                    d0Var3 = d0Var4;
                    str3 = str2;
                }
                String str4 = str3;
                int size2 = d0Var.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    String b9 = d0Var.b(i9);
                    if (!k.C("Content-Length", b9) && !k.C("Content-Encoding", b9) && !k.C("Content-Type", b9) && a.b(b9)) {
                        String d8 = d0Var.d(i9);
                        i5.a.g("name", b9);
                        i5.a.g("value", d8);
                        arrayList.add(b9);
                        arrayList.add(k.U(d8).toString());
                    }
                }
                C2.f8905f = new d0((String[]) arrayList.toArray(new String[0])).c();
                C2.f8910k = b7.f8923k;
                C2.f8911l = b7.f8924l;
                a aVar = Companion;
                x0 a10 = a.a(aVar, x0Var);
                w0.b("cacheResponse", a10);
                C2.f8908i = a10;
                x0 a11 = a.a(aVar, b7);
                w0.b(str4, a11);
                C2.f8907h = a11;
                C2.a();
                a1 a1Var = b7.f8919g;
                i5.a.d(a1Var);
                a1Var.close();
                i5.a.d(null);
                throw null;
            }
            str = "networkResponse";
            a1 a1Var2 = x0Var.f8919g;
            if (a1Var2 != null) {
                t5.b.c(a1Var2);
            }
        }
        w0 C3 = b7.C();
        a aVar2 = Companion;
        x0 a12 = a.a(aVar2, x0Var);
        w0.b("cacheResponse", a12);
        C3.f8908i = a12;
        x0 a13 = a.a(aVar2, b7);
        w0.b(str, a13);
        C3.f8907h = a13;
        return C3.a();
    }
}
