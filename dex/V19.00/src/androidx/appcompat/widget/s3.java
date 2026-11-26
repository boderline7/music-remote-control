package androidx.appcompat.widget;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s3 implements k.c0 {

    /* renamed from: a, reason: collision with root package name */
    public k.o f1411a;

    /* renamed from: b, reason: collision with root package name */
    public k.q f1412b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1413c;

    public s3(Toolbar toolbar) {
        this.f1413c = toolbar;
    }

    @Override // k.c0
    public final void a(k.o oVar, boolean z6) {
    }

    @Override // k.c0
    public final boolean c(k.q qVar) {
        Toolbar toolbar = this.f1413c;
        toolbar.c();
        ViewParent parent = toolbar.f1159h.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.f1159h);
            }
            toolbar.addView(toolbar.f1159h);
        }
        View actionView = qVar.getActionView();
        toolbar.f1160i = actionView;
        this.f1412b = qVar;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.f1160i);
            }
            t3 h7 = Toolbar.h();
            h7.f670a = (toolbar.f1165n & z1.c.INVOKE_DIRECT) | 8388611;
            h7.f1417b = 2;
            toolbar.f1160i.setLayoutParams(h7);
            toolbar.addView(toolbar.f1160i);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((t3) childAt.getLayoutParams()).f1417b != 2 && childAt != toolbar.f1152a) {
                toolbar.removeViewAt(childCount);
                toolbar.E.add(childAt);
            }
        }
        toolbar.requestLayout();
        qVar.C = true;
        qVar.f7179n.p(false);
        KeyEvent.Callback callback = toolbar.f1160i;
        if (callback instanceof j.d) {
            ((j.d) callback).b();
        }
        toolbar.x();
        return true;
    }

    @Override // k.c0
    public final boolean d(k.i0 i0Var) {
        return false;
    }

    @Override // k.c0
    public final boolean e(k.q qVar) {
        Toolbar toolbar = this.f1413c;
        KeyEvent.Callback callback = toolbar.f1160i;
        if (callback instanceof j.d) {
            ((j.d) callback).e();
        }
        toolbar.removeView(toolbar.f1160i);
        toolbar.removeView(toolbar.f1159h);
        toolbar.f1160i = null;
        ArrayList arrayList = toolbar.E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.f1412b = null;
        toolbar.requestLayout();
        qVar.C = false;
        qVar.f7179n.p(false);
        toolbar.x();
        return true;
    }

    @Override // k.c0
    public final void f(Context context, k.o oVar) {
        k.q qVar;
        k.o oVar2 = this.f1411a;
        if (oVar2 != null && (qVar = this.f1412b) != null) {
            oVar2.d(qVar);
        }
        this.f1411a = oVar;
    }

    @Override // k.c0
    public final int getId() {
        return 0;
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        return null;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
    }

    @Override // k.c0
    public final void m(boolean z6) {
        if (this.f1412b != null) {
            k.o oVar = this.f1411a;
            if (oVar != null) {
                int size = oVar.f7144f.size();
                for (int i7 = 0; i7 < size; i7++) {
                    if (this.f1411a.getItem(i7) == this.f1412b) {
                        return;
                    }
                }
            }
            e(this.f1412b);
        }
    }
}
