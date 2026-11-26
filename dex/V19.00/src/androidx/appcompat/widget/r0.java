package androidx.appcompat.widget;

import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r0 extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1385d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1386e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ WeakReference f1387f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ x0 f1388g;

    public r0(x0 x0Var, int i7, int i8, WeakReference weakReference) {
        this.f1388g = x0Var;
        this.f1385d = i7;
        this.f1386e = i8;
        this.f1387f = weakReference;
    }

    @Override // l6.f
    public final void a0(int i7) {
    }

    @Override // l6.f
    public final void b0(Typeface typeface) {
        int i7;
        if (Build.VERSION.SDK_INT >= 28 && (i7 = this.f1385d) != -1) {
            typeface = w0.a(typeface, i7, (this.f1386e & 2) != 0);
        }
        x0 x0Var = this.f1388g;
        if (x0Var.f1477m) {
            x0Var.f1476l = typeface;
            TextView textView = (TextView) this.f1387f.get();
            if (textView != null) {
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                if (p0.o0.b(textView)) {
                    textView.post(new s0(textView, typeface, x0Var.f1474j));
                } else {
                    textView.setTypeface(typeface, x0Var.f1474j);
                }
            }
        }
    }
}
