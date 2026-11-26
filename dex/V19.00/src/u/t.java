package u;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class t extends i {

    /* renamed from: v0, reason: collision with root package name */
    public ArrayList f9216v0;

    @Override // u.i
    public void E() {
        this.f9216v0.clear();
        super.E();
    }

    @Override // u.i
    public final void H(j.h hVar) {
        super.H(hVar);
        int size = this.f9216v0.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((i) this.f9216v0.get(i7)).H(hVar);
        }
    }

    public abstract void T();
}
