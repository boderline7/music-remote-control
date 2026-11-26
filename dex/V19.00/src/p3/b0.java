package p3;

import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b0 {

    /* renamed from: c, reason: collision with root package name */
    public p f8229c;

    /* renamed from: a, reason: collision with root package name */
    public boolean f8227a = false;

    /* renamed from: b, reason: collision with root package name */
    public boolean f8228b = false;

    /* renamed from: d, reason: collision with root package name */
    public RectF f8230d = new RectF();

    /* renamed from: e, reason: collision with root package name */
    public final Path f8231e = new Path();

    public abstract void a(View view);

    public abstract boolean b();

    public final void c() {
        p pVar;
        RectF rectF = this.f8230d;
        if (rectF.left > rectF.right || rectF.top > rectF.bottom || (pVar = this.f8229c) == null) {
            return;
        }
        q.f8313a.a(pVar, 1.0f, rectF, null, this.f8231e);
    }
}
