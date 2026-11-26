package f1;

import androidx.lifecycle.e1;
import q.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends e1 {

    /* renamed from: b, reason: collision with root package name */
    public static final b f6396b = new b();

    /* renamed from: a, reason: collision with root package name */
    public final l f6397a = new l();

    @Override // androidx.lifecycle.e1
    public final void onCleared() {
        super.onCleared();
        l lVar = this.f6397a;
        int i7 = lVar.f8416c;
        if (i7 > 0) {
            a.a.u(lVar.f8415b[0]);
            throw null;
        }
        Object[] objArr = lVar.f8415b;
        for (int i8 = 0; i8 < i7; i8++) {
            objArr[i8] = null;
        }
        lVar.f8416c = 0;
    }
}
