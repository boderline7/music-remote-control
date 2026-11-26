package androidx.appcompat.app;

import android.content.ClipDescription;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.c1;
import androidx.lifecycle.e1;
import androidx.lifecycle.j1;
import androidx.lifecycle.m1;
import androidx.lifecycle.n1;
import androidx.lifecycle.o1;
import androidx.lifecycle.p1;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g implements d, androidx.core.widget.m, s0.d, g3.h, j6.a {

    /* renamed from: e, reason: collision with root package name */
    public static g f712e;

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f713a;

    /* renamed from: b, reason: collision with root package name */
    public Object f714b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f715c;

    /* renamed from: d, reason: collision with root package name */
    public Object f716d;

    public g() {
        this.f713a = 4;
        this.f714b = null;
        this.f715c = null;
        this.f716d = null;
        T();
        try {
            Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
            this.f714b = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
        try {
            Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
            this.f715c = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (NoSuchMethodException unused2) {
        }
        try {
            Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
            this.f716d = method;
            method.setAccessible(true);
        } catch (NoSuchMethodException unused3) {
        }
    }

    public g(a2.q qVar) {
        this.f713a = 11;
        Object obj = qVar.f249b;
        int i7 = ((a2.c) obj).i();
        this.f714b = new w1.e[i7];
        this.f715c = new w1.e[i7];
        this.f716d = new w1.e[i7];
        a2.c cVar = (a2.c) obj;
        int length = cVar.f6354b.length;
        for (int i8 = 0; i8 < length; i8++) {
            a2.b bVar = (a2.b) cVar.f(i8);
            a2.h hVar = bVar.f222b;
            a2.g gVar = (a2.g) hVar.f(0);
            w1.e[] eVarArr = (w1.e[]) this.f714b;
            w1.e eVar = new w1.e(gVar.f229b);
            int i9 = bVar.f221a;
            eVarArr[i9] = eVar;
            a2.g i10 = hVar.i();
            w1.e[] eVarArr2 = (w1.e[]) this.f715c;
            a2.s sVar = i10.f229b;
            eVarArr2[i9] = new w1.e(sVar);
            ((w1.e[]) this.f716d)[i9] = new w1.e(sVar);
        }
    }

    public g(a2.q qVar, int[] iArr, g gVar) {
        this.f713a = 12;
        if (qVar == null) {
            throw new NullPointerException("method == null");
        }
        if (iArr == null) {
            throw new NullPointerException("order == null");
        }
        if (gVar == null) {
            throw new NullPointerException("addresses == null");
        }
        this.f714b = qVar;
        this.f715c = iArr;
        this.f716d = gVar;
    }

    public g(Context context, TypedArray typedArray) {
        this.f713a = 5;
        this.f714b = context;
        this.f715c = typedArray;
    }

    public g(Context context, LocationManager locationManager) {
        this.f713a = 3;
        this.f716d = new x0();
        this.f714b = context;
        this.f715c = locationManager;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g(o1 o1Var, j1 j1Var) {
        this(o1Var, j1Var, 0);
        this.f713a = 8;
        i5.a.g("store", o1Var);
        i5.a.g("factory", j1Var);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(o1 o1Var, j1 j1Var, int i7) {
        this(o1Var, j1Var, e1.a.INSTANCE);
        this.f713a = 8;
    }

    public g(o1 o1Var, j1 j1Var, e1.c cVar) {
        this.f713a = 8;
        i5.a.g("store", o1Var);
        i5.a.g("factory", j1Var);
        i5.a.g("defaultCreationExtras", cVar);
        this.f714b = o1Var;
        this.f715c = j1Var;
        this.f716d = cVar;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public g(androidx.lifecycle.p1 r3) {
        /*
            r2 = this;
            r0 = 8
            r2.f713a = r0
            java.lang.String r0 = "owner"
            i5.a.g(r0, r3)
            androidx.lifecycle.o1 r0 = r3.getViewModelStore()
            androidx.lifecycle.g1 r1 = androidx.lifecycle.h1.Companion
            r1.getClass()
            boolean r1 = r3 instanceof androidx.lifecycle.k
            if (r1 == 0) goto L1e
            r1 = r3
            androidx.lifecycle.k r1 = (androidx.lifecycle.k) r1
            androidx.lifecycle.j1 r1 = r1.getDefaultViewModelProviderFactory()
            goto L27
        L1e:
            androidx.lifecycle.l1 r1 = androidx.lifecycle.m1.Companion
            r1.getClass()
            androidx.lifecycle.m1 r1 = androidx.lifecycle.l1.a()
        L27:
            e1.c r3 = n1.z.R(r3)
            r2.<init>(r0, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.g.<init>(androidx.lifecycle.p1):void");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g(p1 p1Var, androidx.lifecycle.x0 x0Var) {
        this(p1Var.getViewModelStore(), x0Var, n1.z.R(p1Var));
        this.f713a = 8;
        i5.a.g("owner", p1Var);
    }

    public g(androidx.viewpager2.adapter.g gVar, androidx.fragment.app.z zVar, FrameLayout frameLayout) {
        this.f713a = 9;
        this.f716d = gVar;
        this.f714b = zVar;
        this.f715c = frameLayout;
    }

    public g(MaterialToolbar materialToolbar) {
        this.f713a = 0;
        this.f714b = materialToolbar;
        this.f715c = materialToolbar.getNavigationIcon();
        this.f716d = materialToolbar.getNavigationContentDescription();
    }

    public /* synthetic */ g(Object obj, androidx.viewpager2.widget.d dVar, RecyclerView recyclerView) {
        this.f713a = 10;
        this.f714b = obj;
        this.f715c = dVar;
        this.f716d = recyclerView;
    }

    public /* synthetic */ g(Object obj, i6.i iVar, g3.c cVar) {
        this.f713a = 13;
        this.f716d = obj;
        this.f714b = iVar;
        this.f715c = cVar;
    }

    public g(l6.c cVar, Queue queue) {
        this.f713a = 15;
        this.f715c = cVar;
        this.f714b = cVar.f7458a;
        this.f716d = queue;
    }

    public g(s.g gVar, s.g gVar2) {
        this.f713a = 6;
        this.f716d = gVar;
        this.f715c = gVar2;
    }

    public g(u1.l lVar, d4.c cVar, e4.f fVar) {
        int i7;
        d4.b bVar;
        int i8;
        this.f713a = 14;
        this.f716d = lVar;
        this.f714b = new ArrayList();
        e4.f fVar2 = fVar;
        int i9 = 0;
        int i10 = 0;
        while (true) {
            if (fVar2 == null) {
                break;
            }
            int i11 = i9 + fVar2.f6385d;
            d4.b bVar2 = fVar2.f6382a;
            d4.b bVar3 = d4.b.BYTE;
            int i12 = fVar2.f6384c;
            e4.f fVar3 = fVar2.f6386e;
            boolean z6 = (bVar2 == bVar3 && fVar3 == null && i12 != 0) || !(fVar3 == null || i12 == fVar3.f6384c);
            i7 = z6 ? 1 : i10;
            if (fVar3 == null || fVar3.f6382a != bVar2 || z6) {
                ((List) this.f714b).add(0, new e4.g(this, bVar2, fVar2.f6383b, i12, i11));
                i8 = 0;
            } else {
                i8 = i11;
            }
            if (z6) {
                ((List) this.f714b).add(0, new e4.g(this, d4.b.ECI, fVar2.f6383b, fVar2.f6384c, 0));
            }
            i10 = i7;
            fVar2 = fVar3;
            i9 = i8;
        }
        if (lVar.f9278a) {
            e4.g gVar = (e4.g) ((List) this.f714b).get(0);
            if (gVar != null && gVar.f6388a != (bVar = d4.b.ECI) && i10 != 0) {
                ((List) this.f714b).add(0, new e4.g(this, bVar, 0, 0, 0));
            }
            ((List) this.f714b).add(((e4.g) ((List) this.f714b).get(0)).f6388a == d4.b.ECI ? 1 : 0, new e4.g(this, d4.b.FNC1_FIRST_POSITION, 0, 0, 0));
        }
        int i13 = cVar.f6286a;
        int i14 = 26;
        int i15 = e4.e.f6380a[(i13 <= 9 ? e4.h.SMALL : i13 <= 26 ? e4.h.MEDIUM : e4.h.LARGE).ordinal()];
        if (i15 == 1) {
            i14 = 9;
        } else if (i15 != 2) {
            i7 = 27;
            i14 = 40;
        } else {
            i7 = 10;
        }
        int O = O(cVar);
        while (i13 < i14 && !e4.c.c(O, d4.c.b(i13), (d4.a) lVar.f9281d)) {
            i13++;
        }
        while (i13 > i7 && e4.c.c(O, d4.c.b(i13 - 1), (d4.a) lVar.f9281d)) {
            i13--;
        }
        this.f715c = d4.c.b(i13);
    }

    public static g S(Context context, AttributeSet attributeSet, int[] iArr, int i7) {
        return new g(context, context.obtainStyledAttributes(attributeSet, iArr, i7, 0));
    }

    public static void T() {
        if (Build.VERSION.SDK_INT >= 29) {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
        }
    }

    @Override // j6.a
    public final String A() {
        return (String) this.f714b;
    }

    @Override // androidx.appcompat.app.d
    public final Drawable B() {
        return (Drawable) this.f715c;
    }

    public final e1 C(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return D("androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName), cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public final e1 D(String str, Class cls) {
        e1 a7;
        i5.a.g("key", str);
        o1 o1Var = (o1) this.f714b;
        o1Var.getClass();
        e1 e1Var = (e1) o1Var.f2200a.get(str);
        if (!cls.isInstance(e1Var)) {
            e1.d dVar = new e1.d((e1.c) this.f716d);
            dVar.b(m1.VIEW_MODEL_KEY, str);
            try {
                a7 = ((j1) this.f715c).b(cls, dVar);
            } catch (AbstractMethodError unused) {
                a7 = ((j1) this.f715c).a(cls);
            }
            o1 o1Var2 = (o1) this.f714b;
            o1Var2.getClass();
            i5.a.g("viewModel", a7);
            e1 e1Var2 = (e1) o1Var2.f2200a.put(str, a7);
            if (e1Var2 != null) {
                e1Var2.onCleared();
            }
            return a7;
        }
        Object obj = (j1) this.f715c;
        n1 n1Var = obj instanceof n1 ? (n1) obj : null;
        if (n1Var != null) {
            i5.a.d(e1Var);
            c1 c1Var = (c1) n1Var;
            androidx.lifecycle.s sVar = c1Var.f2157d;
            if (sVar != null) {
                j1.g gVar = c1Var.f2158e;
                i5.a.d(gVar);
                androidx.lifecycle.m.a(e1Var, gVar, sVar);
            }
        }
        i5.a.e("null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get", e1Var);
        return e1Var;
    }

    public final boolean E(int i7, boolean z6) {
        return ((TypedArray) this.f715c).getBoolean(i7, z6);
    }

    public final ColorStateList F(int i7) {
        int resourceId;
        ColorStateList b7;
        return (!((TypedArray) this.f715c).hasValue(i7) || (resourceId = ((TypedArray) this.f715c).getResourceId(i7, 0)) == 0 || (b7 = g0.g.b((Context) this.f714b, resourceId)) == null) ? ((TypedArray) this.f715c).getColorStateList(i7) : b7;
    }

    public final int G(int i7, int i8) {
        return ((TypedArray) this.f715c).getDimensionPixelOffset(i7, i8);
    }

    public final int H(int i7, int i8) {
        return ((TypedArray) this.f715c).getDimensionPixelSize(i7, i8);
    }

    public final Drawable I(int i7) {
        int resourceId;
        return (!((TypedArray) this.f715c).hasValue(i7) || (resourceId = ((TypedArray) this.f715c).getResourceId(i7, 0)) == 0) ? ((TypedArray) this.f715c).getDrawable(i7) : l6.f.A((Context) this.f714b, resourceId);
    }

    public final Drawable J(int i7) {
        int resourceId;
        Drawable f7;
        if (!((TypedArray) this.f715c).hasValue(i7) || (resourceId = ((TypedArray) this.f715c).getResourceId(i7, 0)) == 0) {
            return null;
        }
        androidx.appcompat.widget.z a7 = androidx.appcompat.widget.z.a();
        Context context = (Context) this.f714b;
        synchronized (a7) {
            f7 = a7.f1494a.f(context, resourceId, true);
        }
        return f7;
    }

    public final Typeface K(int i7, int i8, androidx.appcompat.widget.r0 r0Var) {
        int resourceId = ((TypedArray) this.f715c).getResourceId(i7, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.f716d) == null) {
            this.f716d = new TypedValue();
        }
        Context context = (Context) this.f714b;
        TypedValue typedValue = (TypedValue) this.f716d;
        ThreadLocal threadLocal = h0.q.f6642a;
        if (context.isRestricted()) {
            return null;
        }
        return h0.q.b(context, resourceId, typedValue, i8, r0Var, true, false);
    }

    public final int L(int i7, int i8) {
        return ((TypedArray) this.f715c).getInt(i7, i8);
    }

    public final Location M(String str) {
        try {
            if (((LocationManager) this.f715c).isProviderEnabled(str)) {
                return ((LocationManager) this.f715c).getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public final int N(int i7, int i8) {
        return ((TypedArray) this.f715c).getResourceId(i7, i8);
    }

    public final int O(d4.c cVar) {
        int i7 = 0;
        for (e4.g gVar : (List) this.f714b) {
            d4.b bVar = gVar.f6388a;
            int a7 = bVar.a(cVar);
            int i8 = a7 + 4;
            int i9 = e4.e.f6381b[bVar.ordinal()];
            int i10 = gVar.f6391d;
            if (i9 == 1) {
                i8 += i10 * 13;
            } else if (i9 == 2) {
                i8 = ((i10 / 2) * 11) + i8 + (i10 % 2 == 1 ? 6 : 0);
            } else if (i9 == 3) {
                int i11 = ((i10 / 3) * 10) + i8;
                int i12 = i10 % 3;
                i8 = i11 + (i12 != 1 ? i12 == 2 ? 7 : 0 : 4);
            } else if (i9 == 4) {
                i8 += gVar.a() * 8;
            } else if (i9 == 5) {
                i8 = a7 + 12;
            }
            i7 += i8;
        }
        return i7;
    }

    public final String P(int i7) {
        return ((TypedArray) this.f715c).getString(i7);
    }

    public final CharSequence Q(int i7) {
        return ((TypedArray) this.f715c).getText(i7);
    }

    public final boolean R(int i7) {
        return ((TypedArray) this.f715c).hasValue(i7);
    }

    public final void U(Object[] objArr) {
        k6.a aVar = new k6.a();
        System.currentTimeMillis();
        aVar.f7261a = (l6.c) this.f715c;
        Thread.currentThread().getName();
        aVar.f7262b = objArr;
        ((Queue) this.f716d).add(aVar);
    }

    public final void V() {
        ((TypedArray) this.f715c).recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j6.a
    public final void a(Long l7, Long l8) {
        if (!(l8 instanceof Throwable)) {
            U(new Object[]{l7, l8});
        } else {
            U(new Object[]{l7});
        }
    }

    @Override // androidx.appcompat.app.d
    public final void b(int i7) {
        if (i7 == 0) {
            ((Toolbar) this.f714b).setNavigationContentDescription((CharSequence) this.f716d);
        } else {
            ((Toolbar) this.f714b).setNavigationContentDescription(i7);
        }
    }

    @Override // j6.a
    public final boolean c() {
        return true;
    }

    @Override // j6.a
    public final void d(String str, Object obj) {
        U(new Object[]{obj});
    }

    @Override // j6.a
    public final void e(String str) {
        U(new Object[]{str});
    }

    @Override // s0.d
    public final void f() {
    }

    @Override // androidx.appcompat.app.d
    public final void g(Drawable drawable, int i7) {
        ((Toolbar) this.f714b).setNavigationIcon(drawable);
        b(i7);
    }

    @Override // s0.d
    public final Uri h() {
        return (Uri) this.f716d;
    }

    @Override // j6.a
    public final void i(String str, Exception exc) {
        U(null);
    }

    @Override // j6.a
    public final void j(Object... objArr) {
        Throwable th = null;
        if (objArr.length != 0) {
            Object obj = objArr[objArr.length - 1];
            if (obj instanceof Throwable) {
                th = (Throwable) obj;
            }
        }
        if (th == null) {
            U(objArr);
            return;
        }
        if (objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        if (length > 0) {
            System.arraycopy(objArr, 0, objArr2, 0, length);
        }
        U(objArr2);
    }

    @Override // g3.h
    public final int k() {
        Object obj = this.f716d;
        return ((ExtendedFloatingActionButton) obj).H == -1 ? ((g3.h) this.f714b).k() : (((ExtendedFloatingActionButton) obj).H == 0 || ((ExtendedFloatingActionButton) obj).H == -2) ? ((g3.h) this.f715c).k() : ((ExtendedFloatingActionButton) obj).H;
    }

    @Override // g3.h
    public final int l() {
        Object obj = this.f716d;
        return ((ExtendedFloatingActionButton) obj).I == -1 ? ((g3.h) this.f714b).l() : (((ExtendedFloatingActionButton) obj).I == 0 || ((ExtendedFloatingActionButton) obj).I == -2) ? ((g3.h) this.f715c).l() : ((ExtendedFloatingActionButton) obj).I;
    }

    @Override // androidx.appcompat.app.d
    public final Context m() {
        return ((Toolbar) this.f714b).getContext();
    }

    @Override // g3.h
    public final int n() {
        return ((ExtendedFloatingActionButton) this.f716d).B;
    }

    @Override // androidx.appcompat.app.d
    public final boolean o() {
        return true;
    }

    @Override // g3.h
    public final int p() {
        return ((ExtendedFloatingActionButton) this.f716d).A;
    }

    @Override // j6.a
    public final void q(String str, IOException iOException) {
        U(new Object[]{str});
    }

    @Override // s0.d
    public final ClipDescription r() {
        return (ClipDescription) this.f715c;
    }

    @Override // g3.h
    public final ViewGroup.LayoutParams s() {
        Object obj = this.f716d;
        return new ViewGroup.LayoutParams(((ExtendedFloatingActionButton) obj).H == 0 ? -2 : ((ExtendedFloatingActionButton) obj).H, ((ExtendedFloatingActionButton) obj).I != 0 ? ((ExtendedFloatingActionButton) obj).I : -2);
    }

    @Override // j6.a
    public final void t(String str) {
        U(null);
    }

    public final String toString() {
        switch (this.f713a) {
            case 6:
                String str = "[ ";
                if (((s.i) this.f714b) != null) {
                    for (int i7 = 0; i7 < 9; i7++) {
                        StringBuilder j7 = a.a.j(str);
                        j7.append(((s.i) this.f714b).f8644h[i7]);
                        j7.append(" ");
                        str = j7.toString();
                    }
                }
                return str + "] " + ((s.i) this.f714b);
            case 14:
                StringBuilder sb = new StringBuilder();
                e4.g gVar = null;
                for (e4.g gVar2 : (List) this.f714b) {
                    if (gVar != null) {
                        sb.append(",");
                    }
                    sb.append(gVar2.toString());
                    gVar = gVar2;
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }

    @Override // j6.a
    public final void u(Exception exc) {
        U(null);
    }

    @Override // j6.a
    public final void v() {
        U(null);
    }

    @Override // j6.a
    public final void w(String str) {
        U(null);
    }

    @Override // androidx.core.widget.m
    public final void x(NestedScrollView nestedScrollView) {
        m.b(nestedScrollView, (View) this.f714b, (View) this.f715c);
    }

    @Override // s0.d
    public final Object y() {
        return null;
    }

    @Override // s0.d
    public final Uri z() {
        return (Uri) this.f714b;
    }
}
