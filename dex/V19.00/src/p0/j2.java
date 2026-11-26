package p0;

import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class j2 {

    /* renamed from: b, reason: collision with root package name */
    public static final l2 f8157b;

    /* renamed from: a, reason: collision with root package name */
    public final l2 f8158a;

    static {
        int i7 = Build.VERSION.SDK_INT;
        f8157b = (i7 >= 30 ? new c2() : i7 >= 29 ? new b2() : new a2()).b().f8161a.a().f8161a.b().f8161a.c();
    }

    public j2(l2 l2Var) {
        this.f8158a = l2Var;
    }

    public l2 a() {
        return this.f8158a;
    }

    public l2 b() {
        return this.f8158a;
    }

    public l2 c() {
        return this.f8158a;
    }

    public void d(View view) {
    }

    public k e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j2)) {
            return false;
        }
        j2 j2Var = (j2) obj;
        return n() == j2Var.n() && m() == j2Var.m() && o0.b.a(j(), j2Var.j()) && o0.b.a(h(), j2Var.h()) && o0.b.a(e(), j2Var.e());
    }

    public i0.c f(int i7) {
        return i0.c.NONE;
    }

    public i0.c g() {
        return j();
    }

    public i0.c h() {
        return i0.c.NONE;
    }

    public int hashCode() {
        return o0.b.b(Boolean.valueOf(n()), Boolean.valueOf(m()), j(), h(), e());
    }

    public i0.c i() {
        return j();
    }

    public i0.c j() {
        return i0.c.NONE;
    }

    public i0.c k() {
        return j();
    }

    public l2 l(int i7, int i8, int i9, int i10) {
        return f8157b;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public boolean o(int i7) {
        return true;
    }

    public void p(i0.c[] cVarArr) {
    }

    public void q(l2 l2Var) {
    }

    public void r(i0.c cVar) {
    }
}
