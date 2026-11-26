package n1;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y {

    /* renamed from: b, reason: collision with root package name */
    public final View f7686b;

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f7685a = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f7687c = new ArrayList();

    public y(View view) {
        this.f7686b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return this.f7686b == yVar.f7686b && this.f7685a.equals(yVar.f7685a);
    }

    public final int hashCode() {
        return this.f7685a.hashCode() + (this.f7686b.hashCode() * 31);
    }

    public final String toString() {
        String g7 = a.a.g(("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f7686b + "\n", "    values:");
        HashMap hashMap = this.f7685a;
        for (String str : hashMap.keySet()) {
            g7 = g7 + "    " + str + ": " + hashMap.get(str) + "\n";
        }
        return g7;
    }
}
