package n1;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements q {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f7616a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7617b;

    public j(View view, ArrayList arrayList) {
        this.f7616a = view;
        this.f7617b = arrayList;
    }

    @Override // n1.q
    public final void a() {
    }

    @Override // n1.q
    public final void b() {
    }

    @Override // n1.q
    public final void c() {
    }

    @Override // n1.q
    public final void d(r rVar) {
        rVar.v(this);
        this.f7616a.setVisibility(8);
        ArrayList arrayList = this.f7617b;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((View) arrayList.get(i7)).setVisibility(0);
        }
    }

    @Override // n1.q
    public final void e() {
    }
}
