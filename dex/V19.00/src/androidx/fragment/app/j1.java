package androidx.fragment.app;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class j1 {

    /* renamed from: a, reason: collision with root package name */
    public static final o1 f1999a = new o1();

    /* renamed from: b, reason: collision with root package name */
    public static final q1 f2000b;

    static {
        q1 q1Var;
        try {
            q1Var = (q1) n1.l.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            q1Var = null;
        }
        f2000b = q1Var;
    }

    public static void a(z zVar, z zVar2, boolean z6) {
        if (z6) {
            zVar2.getEnterTransitionCallback();
        } else {
            zVar.getEnterTransitionCallback();
        }
    }

    public static void b(int i7, ArrayList arrayList) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i7);
        }
    }
}
