package android.support.v4.media;

import android.content.ComponentName;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Objects;
import java.util.concurrent.Callable;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f540a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f541b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f542c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f543d;

    public /* synthetic */ h(Object obj, Object obj2, Object obj3, int i7) {
        this.f540a = i7;
        this.f543d = obj;
        this.f541b = obj2;
        this.f542c = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s2.h hVar;
        OverScroller overScroller;
        Object obj = this.f543d;
        Object obj2 = this.f542c;
        Object obj3 = this.f541b;
        Object obj4 = null;
        switch (this.f540a) {
            case 0:
                if (!m.f550b) {
                    ((j) obj).a();
                    throw null;
                }
                Objects.toString((ComponentName) obj3);
                Objects.toString((IBinder) obj2);
                a.a.u(((j) obj).f548b);
                throw null;
            case 1:
                Typeface typeface = (Typeface) obj2;
                l6.f fVar = ((i0.g) ((m0.j) obj3)).f6780a;
                if (fVar != null) {
                    fVar.b0(typeface);
                    return;
                }
                return;
            case 2:
                ((m0.e) ((o0.a) obj3)).a(obj2);
                return;
            case 3:
                try {
                    obj4 = ((Callable) obj3).call();
                } catch (Exception unused) {
                }
                ((Handler) obj).post(new h(this, (o0.a) obj2, obj4, 2));
                return;
            case 4:
                View view = (View) obj2;
                if (view == null || (overScroller = (hVar = (s2.h) obj).f8676d) == null) {
                    return;
                }
                if (overScroller.computeScrollOffset()) {
                    hVar.A((CoordinatorLayout) obj3, view, hVar.f8676d.getCurrY());
                    l0.m(view, this);
                    return;
                }
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) obj3;
                AppBarLayout appBarLayout = (AppBarLayout) view;
                ((AppBarLayout.BaseBehavior) hVar).F(coordinatorLayout, appBarLayout);
                if (appBarLayout.f3215l) {
                    appBarLayout.e(appBarLayout.f(AppBarLayout.BaseBehavior.C(coordinatorLayout)));
                    return;
                }
                return;
            default:
                ((t2.a) obj).i((View) obj3, (FrameLayout) obj2);
                return;
        }
    }
}
