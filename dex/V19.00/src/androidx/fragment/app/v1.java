package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v1 {

    /* renamed from: a, reason: collision with root package name */
    public x1 f2070a;

    /* renamed from: b, reason: collision with root package name */
    public w1 f2071b;

    /* renamed from: c, reason: collision with root package name */
    public final z f2072c;

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f2073d;

    /* renamed from: e, reason: collision with root package name */
    public final HashSet f2074e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2075f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2076g;

    /* renamed from: h, reason: collision with root package name */
    public final d1 f2077h;

    public v1(x1 x1Var, w1 w1Var, d1 d1Var, l0.d dVar) {
        z zVar = d1Var.f1933c;
        this.f2073d = new ArrayList();
        this.f2074e = new HashSet();
        this.f2075f = false;
        this.f2076g = false;
        this.f2070a = x1Var;
        this.f2071b = w1Var;
        this.f2072c = zVar;
        dVar.b(new o(this));
        this.f2077h = d1Var;
    }

    public final void a() {
        if (this.f2075f) {
            return;
        }
        this.f2075f = true;
        HashSet hashSet = this.f2074e;
        if (hashSet.isEmpty()) {
            b();
            return;
        }
        Iterator it = new ArrayList(hashSet).iterator();
        while (it.hasNext()) {
            ((l0.d) it.next()).a();
        }
    }

    public final void b() {
        if (!this.f2076g) {
            if (Log.isLoggable("FragmentManager", 2)) {
                toString();
            }
            this.f2076g = true;
            Iterator it = this.f2073d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
        this.f2077h.j();
    }

    public final void c(x1 x1Var, w1 w1Var) {
        int i7 = u1.f2061b[w1Var.ordinal()];
        z zVar = this.f2072c;
        if (i7 == 1) {
            if (this.f2070a == x1.REMOVED) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(zVar);
                    Objects.toString(this.f2071b);
                }
                this.f2070a = x1.VISIBLE;
                this.f2071b = w1.ADDING;
                return;
            }
            return;
        }
        if (i7 == 2) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(zVar);
                Objects.toString(this.f2070a);
                Objects.toString(this.f2071b);
            }
            this.f2070a = x1.REMOVED;
            this.f2071b = w1.REMOVING;
            return;
        }
        if (i7 == 3 && this.f2070a != x1.REMOVED) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(zVar);
                Objects.toString(this.f2070a);
                Objects.toString(x1Var);
            }
            this.f2070a = x1Var;
        }
    }

    public final void d() {
        if (this.f2071b == w1.ADDING) {
            d1 d1Var = this.f2077h;
            z zVar = d1Var.f1933c;
            View findFocus = zVar.mView.findFocus();
            if (findFocus != null) {
                zVar.setFocusedView(findFocus);
                if (Log.isLoggable("FragmentManager", 2)) {
                    findFocus.toString();
                    zVar.toString();
                }
            }
            View requireView = this.f2072c.requireView();
            if (requireView.getParent() == null) {
                d1Var.a();
                requireView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            if (requireView.getAlpha() == CropImageView.DEFAULT_ASPECT_RATIO && requireView.getVisibility() == 0) {
                requireView.setVisibility(4);
            }
            requireView.setAlpha(zVar.getPostOnViewCreatedAlpha());
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.f2070a + "} {mLifecycleImpact = " + this.f2071b + "} {mFragment = " + this.f2072c + "}";
    }
}
