package androidx.fragment.app;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2034a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2035b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2036c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2037d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2038e;

    public p1(int i7, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f2034a = i7;
        this.f2035b = arrayList;
        this.f2036c = arrayList2;
        this.f2037d = arrayList3;
        this.f2038e = arrayList4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i7 = 0; i7 < this.f2034a; i7++) {
            View view = (View) this.f2035b.get(i7);
            String str = (String) this.f2036c.get(i7);
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.r0.v(view, str);
            p0.r0.v((View) this.f2037d.get(i7), (String) this.f2038e.get(i7));
        }
    }
}
