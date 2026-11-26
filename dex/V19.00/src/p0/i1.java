package p0;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i1 implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8145a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8146b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8147c;

    public /* synthetic */ i1(int i7, Object obj, Object obj2) {
        this.f8145a = i7;
        this.f8146b = obj;
        this.f8147c = obj2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i7 = this.f8145a;
        Object obj = this.f8146b;
        switch (i7) {
            case 0:
                ((View) ((androidx.appcompat.app.a1) ((e.a) obj).f6290b).f677d.getParent()).invalidate();
                return;
            default:
                AppBarLayout appBarLayout = (AppBarLayout) obj;
                p3.j jVar = (p3.j) this.f8147c;
                int i8 = AppBarLayout.f3203z;
                appBarLayout.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                jVar.n(floatValue);
                Drawable drawable = appBarLayout.f3225v;
                if (drawable instanceof p3.j) {
                    ((p3.j) drawable).n(floatValue);
                }
                Iterator it = appBarLayout.f3221r.iterator();
                if (it.hasNext()) {
                    a.a.u(it.next());
                    throw null;
                }
                return;
        }
    }
}
