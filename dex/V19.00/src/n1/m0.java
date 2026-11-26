package n1;

import android.view.View;
import android.view.WindowId;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m0 implements n0 {

    /* renamed from: a, reason: collision with root package name */
    public final WindowId f7639a;

    public m0(View view) {
        this.f7639a = view.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof m0) && ((m0) obj).f7639a.equals(this.f7639a);
    }

    public final int hashCode() {
        return this.f7639a.hashCode();
    }
}
