package y5;

import androidx.appcompat.widget.y;
import java.util.List;
import s5.h0;
import s5.j0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public final x5.j f10216a;

    /* renamed from: b, reason: collision with root package name */
    public final List f10217b;

    /* renamed from: c, reason: collision with root package name */
    public final int f10218c;

    /* renamed from: d, reason: collision with root package name */
    public final x5.e f10219d;

    /* renamed from: e, reason: collision with root package name */
    public final y f10220e;

    /* renamed from: f, reason: collision with root package name */
    public final int f10221f;

    /* renamed from: g, reason: collision with root package name */
    public final int f10222g;

    /* renamed from: h, reason: collision with root package name */
    public final int f10223h;

    /* renamed from: i, reason: collision with root package name */
    public int f10224i;

    public h(x5.j jVar, List list, int i7, x5.e eVar, y yVar, int i8, int i9, int i10) {
        i5.a.g("call", jVar);
        i5.a.g("interceptors", list);
        i5.a.g("request", yVar);
        this.f10216a = jVar;
        this.f10217b = list;
        this.f10218c = i7;
        this.f10219d = eVar;
        this.f10220e = yVar;
        this.f10221f = i8;
        this.f10222g = i9;
        this.f10223h = i10;
    }

    public static h a(h hVar, int i7, x5.e eVar, y yVar, int i8) {
        if ((i8 & 1) != 0) {
            i7 = hVar.f10218c;
        }
        int i9 = i7;
        if ((i8 & 2) != 0) {
            eVar = hVar.f10219d;
        }
        x5.e eVar2 = eVar;
        if ((i8 & 4) != 0) {
            yVar = hVar.f10220e;
        }
        y yVar2 = yVar;
        int i10 = (i8 & 8) != 0 ? hVar.f10221f : 0;
        int i11 = (i8 & 16) != 0 ? hVar.f10222g : 0;
        int i12 = (i8 & 32) != 0 ? hVar.f10223h : 0;
        hVar.getClass();
        i5.a.g("request", yVar2);
        return new h(hVar.f10216a, hVar.f10217b, i9, eVar2, yVar2, i10, i11, i12);
    }

    public final x0 b(y yVar) {
        i5.a.g("request", yVar);
        List list = this.f10217b;
        int size = list.size();
        int i7 = this.f10218c;
        if (i7 >= size) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f10224i++;
        x5.e eVar = this.f10219d;
        if (eVar != null) {
            if (!eVar.f9666c.b((h0) yVar.f1482b)) {
                throw new IllegalStateException(("network interceptor " + list.get(i7 - 1) + " must retain the same host and port").toString());
            }
            if (this.f10224i != 1) {
                throw new IllegalStateException(("network interceptor " + list.get(i7 - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i8 = i7 + 1;
        h a7 = a(this, i8, null, yVar, 58);
        j0 j0Var = (j0) list.get(i7);
        x0 a8 = j0Var.a(a7);
        if (a8 == null) {
            throw new NullPointerException("interceptor " + j0Var + " returned null");
        }
        if (eVar != null && i8 < list.size() && a7.f10224i != 1) {
            throw new IllegalStateException(("network interceptor " + j0Var + " must call proceed() exactly once").toString());
        }
        if (a8.f8919g != null) {
            return a8;
        }
        throw new IllegalStateException(("interceptor " + j0Var + " returned a response with no body").toString());
    }
}
