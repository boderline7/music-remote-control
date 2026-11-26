package android.support.v4.media;

import android.app.Application;
import android.content.ComponentName;
import android.view.View;
import androidx.appcompat.widget.ScrollingTabContainerView;
import java.lang.reflect.Method;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f544a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f545b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f546c;

    public /* synthetic */ i(int i7, Object obj, Object obj2) {
        this.f544a = i7;
        this.f546c = obj;
        this.f545b = obj2;
    }

    public i(i6.e eVar) {
        this.f544a = 6;
        this.f546c = eVar;
        this.f545b = new i6.i(22);
    }

    public /* synthetic */ i(Object obj, Object obj2, int i7) {
        this.f544a = i7;
        this.f545b = obj;
        this.f546c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        k.m mVar;
        int i7 = this.f544a;
        Object obj = this.f546c;
        switch (i7) {
            case 0:
                if (!m.f550b) {
                    ((j) obj).a();
                    throw null;
                }
                Objects.toString((ComponentName) this.f545b);
                a.a.u(((j) obj).f548b);
                throw null;
            case 1:
                androidx.appcompat.widget.m mVar2 = (androidx.appcompat.widget.m) obj;
                k.o oVar = mVar2.f1325c;
                if (oVar != null && (mVar = oVar.f7143e) != null) {
                    mVar.s(oVar);
                }
                View view = (View) mVar2.f1330h;
                if (view != null && view.getWindowToken() != null) {
                    androidx.appcompat.widget.h hVar = (androidx.appcompat.widget.h) this.f545b;
                    if (!hVar.b()) {
                        if (hVar.f7051f != null) {
                            hVar.d(0, 0, false, false);
                        }
                    }
                    mVar2.f1342t = (androidx.appcompat.widget.h) this.f545b;
                }
                mVar2.f1344v = null;
                return;
            case 2:
                ScrollingTabContainerView scrollingTabContainerView = (ScrollingTabContainerView) obj;
                scrollingTabContainerView.smoothScrollTo(((View) this.f545b).getLeft() - ((scrollingTabContainerView.getWidth() - ((View) this.f545b).getWidth()) / 2), 0);
                scrollingTabContainerView.f1093a = null;
                return;
            case 3:
                ((e0.h) this.f545b).f6296a = obj;
                return;
            case 4:
                ((Application) this.f545b).unregisterActivityLifecycleCallbacks((e0.h) obj);
                return;
            case 5:
                try {
                    Method method = e0.i.f6305d;
                    if (method != null) {
                        method.invoke(this.f545b, obj, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        e0.i.f6306e.invoke(this.f545b, obj, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e7) {
                    if (e7.getClass() == RuntimeException.class && e7.getMessage() != null && e7.getMessage().startsWith("Unable to stop")) {
                        throw e7;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            default:
                i6.j t6 = ((i6.i) this.f545b).t();
                if (t6 == null) {
                    throw new IllegalStateException("No pending post available");
                }
                ((i6.e) obj).c(t6);
                return;
        }
    }
}
