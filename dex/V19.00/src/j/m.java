package j;

import android.view.View;
import androidx.appcompat.widget.w3;
import p0.m1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f6931d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f6932e;

    /* renamed from: f, reason: collision with root package name */
    public int f6933f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f6934g;

    public m(w3 w3Var, int i7) {
        this.f6931d = 1;
        this.f6934g = w3Var;
        this.f6933f = i7;
        this.f6932e = false;
    }

    public m(n nVar) {
        this.f6931d = 0;
        this.f6934g = nVar;
        this.f6932e = false;
        this.f6933f = 0;
    }

    @Override // p0.m1
    public final void a() {
        int i7 = this.f6931d;
        Object obj = this.f6934g;
        switch (i7) {
            case 0:
                int i8 = this.f6933f + 1;
                this.f6933f = i8;
                n nVar = (n) obj;
                if (i8 == nVar.f6935a.size()) {
                    m1 m1Var = nVar.f6938d;
                    if (m1Var != null) {
                        m1Var.a();
                    }
                    this.f6933f = 0;
                    this.f6932e = false;
                    nVar.f6939e = false;
                    break;
                }
                break;
            default:
                if (!this.f6932e) {
                    ((w3) obj).f1442a.setVisibility(this.f6933f);
                    break;
                }
                break;
        }
    }

    @Override // l6.f, p0.m1
    public final void b(View view) {
        switch (this.f6931d) {
            case 1:
                this.f6932e = true;
                break;
        }
    }

    @Override // l6.f, p0.m1
    public final void c() {
        int i7 = this.f6931d;
        Object obj = this.f6934g;
        switch (i7) {
            case 0:
                if (!this.f6932e) {
                    this.f6932e = true;
                    m1 m1Var = ((n) obj).f6938d;
                    if (m1Var != null) {
                        m1Var.c();
                        break;
                    }
                }
                break;
            default:
                ((w3) obj).f1442a.setVisibility(0);
                break;
        }
    }
}
