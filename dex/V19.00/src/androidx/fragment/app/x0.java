package androidx.fragment.app;

import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class x0 {
    public static final int POP_BACK_STACK_INCLUSIVE = 1;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public ArrayList E;
    public ArrayList F;
    public ArrayList G;
    public a1 H;
    public final e0 I;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2103b;

    /* renamed from: d, reason: collision with root package name */
    public ArrayList f2105d;

    /* renamed from: e, reason: collision with root package name */
    public ArrayList f2106e;

    /* renamed from: g, reason: collision with root package name */
    public androidx.activity.g0 f2108g;

    /* renamed from: k, reason: collision with root package name */
    public final Map f2112k;

    /* renamed from: l, reason: collision with root package name */
    public final p0 f2113l;

    /* renamed from: m, reason: collision with root package name */
    public final e f2114m;

    /* renamed from: n, reason: collision with root package name */
    public final CopyOnWriteArrayList f2115n;

    /* renamed from: o, reason: collision with root package name */
    public int f2116o;

    /* renamed from: p, reason: collision with root package name */
    public l0 f2117p;

    /* renamed from: q, reason: collision with root package name */
    public j0 f2118q;

    /* renamed from: r, reason: collision with root package name */
    public z f2119r;

    /* renamed from: s, reason: collision with root package name */
    public z f2120s;

    /* renamed from: t, reason: collision with root package name */
    public final r0 f2121t;

    /* renamed from: u, reason: collision with root package name */
    public final p0 f2122u;

    /* renamed from: v, reason: collision with root package name */
    public d.d f2123v;

    /* renamed from: w, reason: collision with root package name */
    public d.d f2124w;

    /* renamed from: x, reason: collision with root package name */
    public d.d f2125x;

    /* renamed from: y, reason: collision with root package name */
    public ArrayDeque f2126y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f2127z;

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2102a = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final e1 f2104c = new e1();

    /* renamed from: f, reason: collision with root package name */
    public final n0 f2107f = new n0(this);

    /* renamed from: h, reason: collision with root package name */
    public final q0 f2109h = new q0(this);

    /* renamed from: i, reason: collision with root package name */
    public final AtomicInteger f2110i = new AtomicInteger();

    /* renamed from: j, reason: collision with root package name */
    public final Map f2111j = Collections.synchronizedMap(new HashMap());

    public x0() {
        Collections.synchronizedMap(new HashMap());
        this.f2112k = Collections.synchronizedMap(new HashMap());
        int i7 = 3;
        this.f2113l = new p0(this, i7);
        this.f2114m = new e(this);
        this.f2115n = new CopyOnWriteArrayList();
        this.f2116o = -1;
        this.f2121t = new r0(this);
        this.f2122u = new p0(this, 4);
        this.f2126y = new ArrayDeque();
        this.I = new e0(i7, this);
    }

    public static boolean B(z zVar) {
        if (!zVar.mHasMenu || !zVar.mMenuVisible) {
            Iterator it = zVar.mChildFragmentManager.f2104c.e().iterator();
            boolean z6 = false;
            while (it.hasNext()) {
                z zVar2 = (z) it.next();
                if (zVar2 != null) {
                    z6 = B(zVar2);
                }
                if (z6) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean C(z zVar) {
        if (zVar == null) {
            return true;
        }
        x0 x0Var = zVar.mFragmentManager;
        return zVar.equals(x0Var.f2120s) && C(x0Var.f2119r);
    }

    public final void A(z zVar) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        if (zVar.mHidden) {
            return;
        }
        zVar.mHidden = true;
        zVar.mHiddenChanged = true ^ zVar.mHiddenChanged;
        T(zVar);
    }

    public final boolean D() {
        return this.A || this.B;
    }

    public final void E(int i7, boolean z6) {
        HashMap hashMap;
        l0 l0Var;
        if (this.f2117p == null && i7 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z6 || i7 != this.f2116o) {
            this.f2116o = i7;
            e1 e1Var = this.f2104c;
            Iterator it = e1Var.f1940a.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                hashMap = e1Var.f1941b;
                if (!hasNext) {
                    break;
                }
                d1 d1Var = (d1) hashMap.get(((z) it.next()).mWho);
                if (d1Var != null) {
                    d1Var.j();
                }
            }
            for (d1 d1Var2 : hashMap.values()) {
                if (d1Var2 != null) {
                    d1Var2.j();
                    z zVar = d1Var2.f1933c;
                    if (zVar.mRemoving && !zVar.isInBackStack()) {
                        e1Var.h(d1Var2);
                    }
                }
            }
            Iterator it2 = e1Var.d().iterator();
            while (it2.hasNext()) {
                d1 d1Var3 = (d1) it2.next();
                z zVar2 = d1Var3.f1933c;
                if (zVar2.mDeferStart) {
                    if (this.f2103b) {
                        this.D = true;
                    } else {
                        zVar2.mDeferStart = false;
                        d1Var3.j();
                    }
                }
            }
            if (this.f2127z && (l0Var = this.f2117p) != null && this.f2116o == 7) {
                ((c0) l0Var).f1924e.supportInvalidateOptionsMenu();
                this.f2127z = false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void F(androidx.fragment.app.z r19, int r20) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.x0.F(androidx.fragment.app.z, int):void");
    }

    public final void G() {
        if (this.f2117p == null) {
            return;
        }
        this.A = false;
        this.B = false;
        this.H.f1920f = false;
        for (z zVar : this.f2104c.f()) {
            if (zVar != null) {
                zVar.noteStateNotSaved();
            }
        }
    }

    public final boolean H() {
        s(false);
        r(true);
        z zVar = this.f2120s;
        if (zVar != null && zVar.getChildFragmentManager().H()) {
            return true;
        }
        boolean I = I(this.E, this.F, -1, 0);
        if (I) {
            this.f2103b = true;
            try {
                L(this.E, this.F);
            } finally {
                d();
            }
        }
        V();
        boolean z6 = this.D;
        e1 e1Var = this.f2104c;
        if (z6) {
            this.D = false;
            Iterator it = e1Var.d().iterator();
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                z zVar2 = d1Var.f1933c;
                if (zVar2.mDeferStart) {
                    if (this.f2103b) {
                        this.D = true;
                    } else {
                        zVar2.mDeferStart = false;
                        d1Var.j();
                    }
                }
            }
        }
        e1Var.f1941b.values().removeAll(Collections.singleton(null));
        return I;
    }

    public final boolean I(ArrayList arrayList, ArrayList arrayList2, int i7, int i8) {
        int i9;
        a aVar;
        ArrayList arrayList3 = this.f2105d;
        if (arrayList3 == null) {
            return false;
        }
        if (i7 >= 0 || (i8 & 1) != 0) {
            if (i7 >= 0) {
                i9 = arrayList3.size() - 1;
                while (i9 >= 0) {
                    a aVar2 = (a) this.f2105d.get(i9);
                    if (i7 >= 0 && i7 == aVar2.f1912s) {
                        break;
                    }
                    i9--;
                }
                if (i9 < 0) {
                    return false;
                }
                if ((i8 & 1) != 0) {
                    do {
                        i9--;
                        if (i9 < 0) {
                            break;
                        }
                        aVar = (a) this.f2105d.get(i9);
                        if (i7 < 0) {
                            break;
                        }
                    } while (i7 == aVar.f1912s);
                }
            } else {
                i9 = -1;
            }
            if (i9 == this.f2105d.size() - 1) {
                return false;
            }
            for (int size = this.f2105d.size() - 1; size > i9; size--) {
                arrayList.add(this.f2105d.remove(size));
                arrayList2.add(Boolean.TRUE);
            }
        } else {
            int size2 = arrayList3.size() - 1;
            if (size2 < 0) {
                return false;
            }
            arrayList.add(this.f2105d.remove(size2));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void J(Bundle bundle, String str, z zVar) {
        if (zVar.mFragmentManager == this) {
            bundle.putString(str, zVar.mWho);
        } else {
            U(new IllegalStateException(a.a.f("Fragment ", zVar, " is not currently in the FragmentManager")));
            throw null;
        }
    }

    public final void K(z zVar) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        boolean z6 = !zVar.isInBackStack();
        if (!zVar.mDetached || z6) {
            e1 e1Var = this.f2104c;
            synchronized (e1Var.f1940a) {
                e1Var.f1940a.remove(zVar);
            }
            zVar.mAdded = false;
            if (B(zVar)) {
                this.f2127z = true;
            }
            zVar.mRemoving = true;
            T(zVar);
        }
    }

    public final void L(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            if (!((a) arrayList.get(i7)).f1989p) {
                if (i8 != i7) {
                    u(arrayList, arrayList2, i8, i7);
                }
                i8 = i7 + 1;
                if (((Boolean) arrayList2.get(i7)).booleanValue()) {
                    while (i8 < size && ((Boolean) arrayList2.get(i8)).booleanValue() && !((a) arrayList.get(i8)).f1989p) {
                        i8++;
                    }
                }
                u(arrayList, arrayList2, i7, i8);
                i7 = i8 - 1;
            }
            i7++;
        }
        if (i8 != size) {
            u(arrayList, arrayList2, i8, size);
        }
    }

    public final void M(Parcelable parcelable) {
        e eVar;
        int i7;
        d1 d1Var;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1883a == null) {
            return;
        }
        e1 e1Var = this.f2104c;
        e1Var.f1941b.clear();
        Iterator it = fragmentManagerState.f1883a.iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            eVar = this.f2114m;
            if (!hasNext) {
                break;
            }
            FragmentState fragmentState = (FragmentState) it.next();
            if (fragmentState != null) {
                z zVar = (z) this.H.f1915a.get(fragmentState.f1892b);
                if (zVar != null) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        zVar.toString();
                    }
                    d1Var = new d1(eVar, e1Var, zVar, fragmentState);
                } else {
                    d1Var = new d1(this.f2114m, this.f2104c, this.f2117p.f2008b.getClassLoader(), y(), fragmentState);
                }
                z zVar2 = d1Var.f1933c;
                zVar2.mFragmentManager = this;
                if (Log.isLoggable("FragmentManager", 2)) {
                    zVar2.toString();
                }
                d1Var.k(this.f2117p.f2008b.getClassLoader());
                e1Var.g(d1Var);
                d1Var.f1935e = this.f2116o;
            }
        }
        a1 a1Var = this.H;
        a1Var.getClass();
        Iterator it2 = new ArrayList(a1Var.f1915a.values()).iterator();
        while (it2.hasNext()) {
            z zVar3 = (z) it2.next();
            if (!(e1Var.f1941b.get(zVar3.mWho) != null)) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    zVar3.toString();
                    Objects.toString(fragmentManagerState.f1883a);
                }
                this.H.c(zVar3);
                zVar3.mFragmentManager = this;
                d1 d1Var2 = new d1(eVar, e1Var, zVar3);
                d1Var2.f1935e = 1;
                d1Var2.j();
                zVar3.mRemoving = true;
                d1Var2.j();
            }
        }
        ArrayList<String> arrayList = fragmentManagerState.f1884b;
        e1Var.f1940a.clear();
        if (arrayList != null) {
            for (String str : arrayList) {
                z b7 = e1Var.b(str);
                if (b7 == null) {
                    throw new IllegalStateException(a.a.h("No instantiated fragment for (", str, ")"));
                }
                if (Log.isLoggable("FragmentManager", 2)) {
                    b7.toString();
                }
                e1Var.a(b7);
            }
        }
        z zVar4 = null;
        if (fragmentManagerState.f1885c != null) {
            this.f2105d = new ArrayList(fragmentManagerState.f1885c.length);
            int i8 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f1885c;
                if (i8 >= backStackStateArr.length) {
                    break;
                }
                BackStackState backStackState = backStackStateArr[i8];
                backStackState.getClass();
                a aVar = new a(this);
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    int[] iArr = backStackState.f1862a;
                    if (i9 >= iArr.length) {
                        break;
                    }
                    g1 g1Var = new g1();
                    int i11 = i9 + 1;
                    g1Var.f1958a = iArr[i9];
                    if (Log.isLoggable("FragmentManager", 2)) {
                        aVar.toString();
                        int i12 = iArr[i11];
                    }
                    String str2 = (String) backStackState.f1863b.get(i10);
                    if (str2 != null) {
                        g1Var.f1959b = e1Var.b(str2);
                    } else {
                        g1Var.f1959b = zVar4;
                    }
                    g1Var.f1964g = androidx.lifecycle.r.values()[backStackState.f1864c[i10]];
                    g1Var.f1965h = androidx.lifecycle.r.values()[backStackState.f1865d[i10]];
                    int i13 = iArr[i11];
                    g1Var.f1960c = i13;
                    int i14 = iArr[i9 + 2];
                    g1Var.f1961d = i14;
                    int i15 = i9 + 4;
                    int i16 = iArr[i9 + 3];
                    g1Var.f1962e = i16;
                    i9 += 5;
                    int i17 = iArr[i15];
                    g1Var.f1963f = i17;
                    aVar.f1975b = i13;
                    aVar.f1976c = i14;
                    aVar.f1977d = i16;
                    aVar.f1978e = i17;
                    aVar.b(g1Var);
                    i10++;
                    zVar4 = null;
                }
                aVar.f1979f = backStackState.f1866e;
                aVar.f1982i = backStackState.f1867f;
                aVar.f1912s = backStackState.f1868g;
                aVar.f1980g = true;
                aVar.f1983j = backStackState.f1869h;
                aVar.f1984k = backStackState.f1870i;
                aVar.f1985l = backStackState.f1871j;
                aVar.f1986m = backStackState.f1872k;
                aVar.f1987n = backStackState.f1873l;
                aVar.f1988o = backStackState.f1874m;
                aVar.f1989p = backStackState.f1875n;
                aVar.d(1);
                if (Log.isLoggable("FragmentManager", 2)) {
                    aVar.toString();
                    PrintWriter printWriter = new PrintWriter(new s1());
                    aVar.f("  ", printWriter, false);
                    printWriter.close();
                }
                this.f2105d.add(aVar);
                i8++;
                zVar4 = null;
            }
            i7 = 0;
        } else {
            i7 = 0;
            this.f2105d = null;
        }
        this.f2110i.set(fragmentManagerState.f1886d);
        String str3 = fragmentManagerState.f1887e;
        if (str3 != null) {
            z b8 = e1Var.b(str3);
            this.f2120s = b8;
            m(b8);
        }
        ArrayList arrayList2 = fragmentManagerState.f1888f;
        if (arrayList2 != null) {
            while (i7 < arrayList2.size()) {
                Bundle bundle = (Bundle) fragmentManagerState.f1889g.get(i7);
                bundle.setClassLoader(this.f2117p.f2008b.getClassLoader());
                this.f2111j.put(arrayList2.get(i7), bundle);
                i7++;
            }
        }
        this.f2126y = new ArrayDeque(fragmentManagerState.f1890h);
    }

    public final Parcelable N() {
        int i7;
        ArrayList arrayList;
        BackStackState[] backStackStateArr;
        int size;
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            y1 y1Var = (y1) it.next();
            if (y1Var.f2133e) {
                y1Var.f2133e = false;
                y1Var.c();
            }
        }
        Iterator it2 = e().iterator();
        while (it2.hasNext()) {
            ((y1) it2.next()).e();
        }
        s(true);
        this.A = true;
        this.H.f1920f = true;
        e1 e1Var = this.f2104c;
        e1Var.getClass();
        HashMap hashMap = e1Var.f1941b;
        ArrayList arrayList2 = new ArrayList(hashMap.size());
        for (d1 d1Var : hashMap.values()) {
            if (d1Var != null) {
                z zVar = d1Var.f1933c;
                FragmentState fragmentState = new FragmentState(zVar);
                if (zVar.mState <= -1 || fragmentState.f1903m != null) {
                    fragmentState.f1903m = zVar.mSavedFragmentState;
                } else {
                    Bundle m6 = d1Var.m();
                    fragmentState.f1903m = m6;
                    if (zVar.mTargetWho != null) {
                        if (m6 == null) {
                            fragmentState.f1903m = new Bundle();
                        }
                        fragmentState.f1903m.putString("android:target_state", zVar.mTargetWho);
                        int i8 = zVar.mTargetRequestCode;
                        if (i8 != 0) {
                            fragmentState.f1903m.putInt("android:target_req_state", i8);
                        }
                    }
                }
                arrayList2.add(fragmentState);
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(zVar);
                    Objects.toString(fragmentState.f1903m);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            Log.isLoggable("FragmentManager", 2);
            return null;
        }
        e1 e1Var2 = this.f2104c;
        synchronized (e1Var2.f1940a) {
            try {
                if (e1Var2.f1940a.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(e1Var2.f1940a.size());
                    Iterator it3 = e1Var2.f1940a.iterator();
                    while (it3.hasNext()) {
                        z zVar2 = (z) it3.next();
                        arrayList.add(zVar2.mWho);
                        if (Log.isLoggable("FragmentManager", 2)) {
                            zVar2.toString();
                        }
                    }
                }
            } finally {
            }
        }
        ArrayList arrayList3 = this.f2105d;
        if (arrayList3 == null || (size = arrayList3.size()) <= 0) {
            backStackStateArr = null;
        } else {
            backStackStateArr = new BackStackState[size];
            for (i7 = 0; i7 < size; i7++) {
                backStackStateArr[i7] = new BackStackState((a) this.f2105d.get(i7));
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(this.f2105d.get(i7));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1887e = null;
        ArrayList arrayList4 = new ArrayList();
        fragmentManagerState.f1888f = arrayList4;
        ArrayList arrayList5 = new ArrayList();
        fragmentManagerState.f1889g = arrayList5;
        fragmentManagerState.f1883a = arrayList2;
        fragmentManagerState.f1884b = arrayList;
        fragmentManagerState.f1885c = backStackStateArr;
        fragmentManagerState.f1886d = this.f2110i.get();
        z zVar3 = this.f2120s;
        if (zVar3 != null) {
            fragmentManagerState.f1887e = zVar3.mWho;
        }
        arrayList4.addAll(this.f2111j.keySet());
        arrayList5.addAll(this.f2111j.values());
        fragmentManagerState.f1890h = new ArrayList(this.f2126y);
        return fragmentManagerState;
    }

    public final Fragment$SavedState O(z zVar) {
        Bundle m6;
        d1 d1Var = (d1) this.f2104c.f1941b.get(zVar.mWho);
        if (d1Var != null) {
            z zVar2 = d1Var.f1933c;
            if (zVar2.equals(zVar)) {
                if (zVar2.mState <= -1 || (m6 = d1Var.m()) == null) {
                    return null;
                }
                return new Fragment$SavedState(m6);
            }
        }
        U(new IllegalStateException(a.a.f("Fragment ", zVar, " is not currently in the FragmentManager")));
        throw null;
    }

    public final void P() {
        synchronized (this.f2102a) {
            try {
                if (this.f2102a.size() == 1) {
                    this.f2117p.f2009c.removeCallbacks(this.I);
                    this.f2117p.f2009c.post(this.I);
                    V();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void Q(z zVar, boolean z6) {
        ViewGroup x6 = x(zVar);
        if (x6 == null || !(x6 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) x6).setDrawDisappearingViewsLast(!z6);
    }

    public final void R(z zVar, androidx.lifecycle.r rVar) {
        if (zVar.equals(this.f2104c.b(zVar.mWho)) && (zVar.mHost == null || zVar.mFragmentManager == this)) {
            zVar.mMaxState = rVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + zVar + " is not an active fragment of FragmentManager " + this);
    }

    public final void S(z zVar) {
        if (zVar != null) {
            if (!zVar.equals(this.f2104c.b(zVar.mWho)) || (zVar.mHost != null && zVar.mFragmentManager != this)) {
                throw new IllegalArgumentException("Fragment " + zVar + " is not an active fragment of FragmentManager " + this);
            }
        }
        z zVar2 = this.f2120s;
        this.f2120s = zVar;
        m(zVar2);
        m(this.f2120s);
    }

    public final void T(z zVar) {
        ViewGroup x6 = x(zVar);
        if (x6 != null) {
            if (zVar.getPopExitAnim() + zVar.getPopEnterAnim() + zVar.getExitAnim() + zVar.getEnterAnim() > 0) {
                if (x6.getTag(b1.b.visible_removing_fragment_view_tag) == null) {
                    x6.setTag(b1.b.visible_removing_fragment_view_tag, zVar);
                }
                ((z) x6.getTag(b1.b.visible_removing_fragment_view_tag)).setPopDirection(zVar.getPopDirection());
            }
        }
    }

    public final void U(IllegalStateException illegalStateException) {
        illegalStateException.getMessage();
        PrintWriter printWriter = new PrintWriter(new s1());
        l0 l0Var = this.f2117p;
        try {
            if (l0Var != null) {
                ((c0) l0Var).f1924e.dump("  ", null, printWriter, new String[0]);
            } else {
                p("  ", null, printWriter, new String[0]);
            }
            throw illegalStateException;
        } catch (Exception unused) {
            throw illegalStateException;
        }
    }

    public final void V() {
        synchronized (this.f2102a) {
            try {
                if (!this.f2102a.isEmpty()) {
                    q0 q0Var = this.f2109h;
                    q0Var.f2039a = true;
                    h5.a aVar = q0Var.f2041c;
                    if (aVar != null) {
                        aVar.a();
                    }
                    return;
                }
                q0 q0Var2 = this.f2109h;
                ArrayList arrayList = this.f2105d;
                q0Var2.f2039a = arrayList != null && arrayList.size() > 0 && C(this.f2119r);
                h5.a aVar2 = q0Var2.f2041c;
                if (aVar2 != null) {
                    aVar2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final d1 a(z zVar) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        d1 f7 = f(zVar);
        zVar.mFragmentManager = this;
        e1 e1Var = this.f2104c;
        e1Var.g(f7);
        if (!zVar.mDetached) {
            e1Var.a(zVar);
            zVar.mRemoving = false;
            if (zVar.mView == null) {
                zVar.mHiddenChanged = false;
            }
            if (B(zVar)) {
                this.f2127z = true;
            }
        }
        return f7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b(l0 l0Var, j0 j0Var, z zVar) {
        if (this.f2117p != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f2117p = l0Var;
        this.f2118q = j0Var;
        this.f2119r = zVar;
        CopyOnWriteArrayList copyOnWriteArrayList = this.f2115n;
        if (zVar != null) {
            copyOnWriteArrayList.add(new s0(zVar));
        } else if (l0Var instanceof b1) {
            copyOnWriteArrayList.add((b1) l0Var);
        }
        if (this.f2119r != null) {
            V();
        }
        int i7 = 0;
        if (l0Var instanceof androidx.activity.h0) {
            androidx.activity.h0 h0Var = (androidx.activity.h0) l0Var;
            androidx.activity.g0 onBackPressedDispatcher = h0Var.getOnBackPressedDispatcher();
            this.f2108g = onBackPressedDispatcher;
            z zVar2 = h0Var;
            if (zVar != null) {
                zVar2 = zVar;
            }
            onBackPressedDispatcher.getClass();
            q0 q0Var = this.f2109h;
            i5.a.g("onBackPressedCallback", q0Var);
            androidx.lifecycle.s lifecycle = zVar2.getLifecycle();
            if (((androidx.lifecycle.a0) lifecycle).f2136c != androidx.lifecycle.r.DESTROYED) {
                q0Var.f2040b.add(new androidx.activity.d0(onBackPressedDispatcher, lifecycle, q0Var));
                onBackPressedDispatcher.c();
                q0Var.f2041c = new androidx.activity.f0(0, onBackPressedDispatcher);
            }
        }
        if (zVar != null) {
            a1 a1Var = zVar.mFragmentManager.H;
            HashMap hashMap = a1Var.f1916b;
            a1 a1Var2 = (a1) hashMap.get(zVar.mWho);
            if (a1Var2 == null) {
                a1Var2 = new a1(a1Var.f1918d);
                hashMap.put(zVar.mWho, a1Var2);
            }
            this.H = a1Var2;
        } else if (l0Var instanceof androidx.lifecycle.p1) {
            this.H = (a1) new androidx.appcompat.app.g(((androidx.lifecycle.p1) l0Var).getViewModelStore(), a1.f1914g).C(a1.class);
        } else {
            this.H = new a1(false);
        }
        this.H.f1920f = D();
        this.f2104c.f1942c = this.H;
        Object obj = this.f2117p;
        if (obj instanceof d.h) {
            d.g activityResultRegistry = ((d.h) obj).getActivityResultRegistry();
            String B = a.a.B("FragmentManager:", zVar != null ? a.a.i(new StringBuilder(), zVar.mWho, ":") : s5.h0.FRAGMENT_ENCODE_SET);
            this.f2123v = activityResultRegistry.d(a.a.g(B, "StartActivityForResult"), new e.f(), new p0(this, 2));
            this.f2124w = activityResultRegistry.d(a.a.g(B, "StartIntentSenderForResult"), new t0(), new p0(this, i7));
            this.f2125x = activityResultRegistry.d(a.a.g(B, "RequestPermissions"), new e.d(), new p0(this, 1));
        }
    }

    public final void c(z zVar) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        if (zVar.mDetached) {
            zVar.mDetached = false;
            if (zVar.mAdded) {
                return;
            }
            this.f2104c.a(zVar);
            if (Log.isLoggable("FragmentManager", 2)) {
                zVar.toString();
            }
            if (B(zVar)) {
                this.f2127z = true;
            }
        }
    }

    public final void d() {
        this.f2103b = false;
        this.F.clear();
        this.E.clear();
    }

    public final HashSet e() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f2104c.d().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((d1) it.next()).f1933c.mContainer;
            if (viewGroup != null) {
                hashSet.add(y1.f(viewGroup, z()));
            }
        }
        return hashSet;
    }

    public final d1 f(z zVar) {
        String str = zVar.mWho;
        e1 e1Var = this.f2104c;
        d1 d1Var = (d1) e1Var.f1941b.get(str);
        if (d1Var != null) {
            return d1Var;
        }
        d1 d1Var2 = new d1(this.f2114m, e1Var, zVar);
        d1Var2.k(this.f2117p.f2008b.getClassLoader());
        d1Var2.f1935e = this.f2116o;
        return d1Var2;
    }

    public final void g(z zVar) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        if (zVar.mDetached) {
            return;
        }
        zVar.mDetached = true;
        if (zVar.mAdded) {
            if (Log.isLoggable("FragmentManager", 2)) {
                zVar.toString();
            }
            e1 e1Var = this.f2104c;
            synchronized (e1Var.f1940a) {
                e1Var.f1940a.remove(zVar);
            }
            zVar.mAdded = false;
            if (B(zVar)) {
                this.f2127z = true;
            }
            T(zVar);
        }
    }

    public final boolean h(MenuItem menuItem) {
        if (this.f2116o < 1) {
            return false;
        }
        for (z zVar : this.f2104c.f()) {
            if (zVar != null && zVar.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final boolean i(Menu menu, MenuInflater menuInflater) {
        if (this.f2116o < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z6 = false;
        for (z zVar : this.f2104c.f()) {
            if (zVar != null && zVar.isMenuVisible() && zVar.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(zVar);
                z6 = true;
            }
        }
        if (this.f2106e != null) {
            for (int i7 = 0; i7 < this.f2106e.size(); i7++) {
                z zVar2 = (z) this.f2106e.get(i7);
                if (arrayList == null || !arrayList.contains(zVar2)) {
                    zVar2.onDestroyOptionsMenu();
                }
            }
        }
        this.f2106e = arrayList;
        return z6;
    }

    public final void j() {
        this.C = true;
        s(true);
        Iterator it = e().iterator();
        while (it.hasNext()) {
            ((y1) it.next()).e();
        }
        o(-1);
        this.f2117p = null;
        this.f2118q = null;
        this.f2119r = null;
        if (this.f2108g != null) {
            Iterator it2 = this.f2109h.f2040b.iterator();
            while (it2.hasNext()) {
                ((androidx.activity.d) it2.next()).cancel();
            }
            this.f2108g = null;
        }
        d.d dVar = this.f2123v;
        if (dVar != null) {
            dVar.b();
            this.f2124w.b();
            this.f2125x.b();
        }
    }

    public final boolean k(MenuItem menuItem) {
        if (this.f2116o < 1) {
            return false;
        }
        for (z zVar : this.f2104c.f()) {
            if (zVar != null && zVar.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public final void l(Menu menu) {
        if (this.f2116o < 1) {
            return;
        }
        for (z zVar : this.f2104c.f()) {
            if (zVar != null) {
                zVar.performOptionsMenuClosed(menu);
            }
        }
    }

    public final void m(z zVar) {
        if (zVar != null) {
            if (zVar.equals(this.f2104c.b(zVar.mWho))) {
                zVar.performPrimaryNavigationFragmentChanged();
            }
        }
    }

    public final boolean n(Menu menu) {
        boolean z6 = false;
        if (this.f2116o < 1) {
            return false;
        }
        for (z zVar : this.f2104c.f()) {
            if (zVar != null && zVar.isMenuVisible() && zVar.performPrepareOptionsMenu(menu)) {
                z6 = true;
            }
        }
        return z6;
    }

    public final void o(int i7) {
        try {
            this.f2103b = true;
            for (d1 d1Var : this.f2104c.f1941b.values()) {
                if (d1Var != null) {
                    d1Var.f1935e = i7;
                }
            }
            E(i7, false);
            Iterator it = e().iterator();
            while (it.hasNext()) {
                ((y1) it.next()).e();
            }
            this.f2103b = false;
            s(true);
        } catch (Throwable th) {
            this.f2103b = false;
            throw th;
        }
    }

    public final void p(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String g7 = a.a.g(str, "    ");
        e1 e1Var = this.f2104c;
        e1Var.getClass();
        String str2 = str + "    ";
        HashMap hashMap = e1Var.f1941b;
        if (!hashMap.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (d1 d1Var : hashMap.values()) {
                printWriter.print(str);
                if (d1Var != null) {
                    z zVar = d1Var.f1933c;
                    printWriter.println(zVar);
                    zVar.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        ArrayList arrayList = e1Var.f1940a;
        int size3 = arrayList.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i7 = 0; i7 < size3; i7++) {
                z zVar2 = (z) arrayList.get(i7);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i7);
                printWriter.print(": ");
                printWriter.println(zVar2.toString());
            }
        }
        ArrayList arrayList2 = this.f2106e;
        if (arrayList2 != null && (size2 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i8 = 0; i8 < size2; i8++) {
                z zVar3 = (z) this.f2106e.get(i8);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i8);
                printWriter.print(": ");
                printWriter.println(zVar3.toString());
            }
        }
        ArrayList arrayList3 = this.f2105d;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i9 = 0; i9 < size; i9++) {
                a aVar = (a) this.f2105d.get(i9);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i9);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.f(g7, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f2110i.get());
        synchronized (this.f2102a) {
            try {
                int size4 = this.f2102a.size();
                if (size4 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i10 = 0; i10 < size4; i10++) {
                        Object obj = (u0) this.f2102a.get(i10);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i10);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.f2117p);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f2118q);
        if (this.f2119r != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f2119r);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f2116o);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.A);
        printWriter.print(" mStopped=");
        printWriter.print(this.B);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.C);
        if (this.f2127z) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f2127z);
        }
    }

    public final void q(u0 u0Var, boolean z6) {
        if (!z6) {
            if (this.f2117p == null) {
                if (!this.C) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            if (D()) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.f2102a) {
            try {
                if (this.f2117p == null) {
                    if (!z6) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.f2102a.add(u0Var);
                    P();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void r(boolean z6) {
        if (this.f2103b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f2117p == null) {
            if (!this.C) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.f2117p.f2009c.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z6 && D()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.E == null) {
            this.E = new ArrayList();
            this.F = new ArrayList();
        }
        this.f2103b = false;
    }

    public final boolean s(boolean z6) {
        r(z6);
        boolean z7 = false;
        while (true) {
            ArrayList arrayList = this.E;
            ArrayList arrayList2 = this.F;
            synchronized (this.f2102a) {
                try {
                    if (this.f2102a.isEmpty()) {
                        break;
                    }
                    int size = this.f2102a.size();
                    boolean z8 = false;
                    for (int i7 = 0; i7 < size; i7++) {
                        z8 |= ((u0) this.f2102a.get(i7)).a(arrayList, arrayList2);
                    }
                    this.f2102a.clear();
                    this.f2117p.f2009c.removeCallbacks(this.I);
                    if (!z8) {
                        break;
                    }
                    this.f2103b = true;
                    try {
                        L(this.E, this.F);
                        d();
                        z7 = true;
                    } catch (Throwable th) {
                        d();
                        throw th;
                    }
                } finally {
                }
            }
        }
        V();
        if (this.D) {
            this.D = false;
            Iterator it = this.f2104c.d().iterator();
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                z zVar = d1Var.f1933c;
                if (zVar.mDeferStart) {
                    if (this.f2103b) {
                        this.D = true;
                    } else {
                        zVar.mDeferStart = false;
                        d1Var.j();
                    }
                }
            }
        }
        this.f2104c.f1941b.values().removeAll(Collections.singleton(null));
        return z7;
    }

    public final void t(u0 u0Var, boolean z6) {
        if (z6 && (this.f2117p == null || this.C)) {
            return;
        }
        r(z6);
        if (u0Var.a(this.E, this.F)) {
            this.f2103b = true;
            try {
                L(this.E, this.F);
            } finally {
                d();
            }
        }
        V();
        boolean z7 = this.D;
        e1 e1Var = this.f2104c;
        if (z7) {
            this.D = false;
            Iterator it = e1Var.d().iterator();
            while (it.hasNext()) {
                d1 d1Var = (d1) it.next();
                z zVar = d1Var.f1933c;
                if (zVar.mDeferStart) {
                    if (this.f2103b) {
                        this.D = true;
                    } else {
                        zVar.mDeferStart = false;
                        d1Var.j();
                    }
                }
            }
        }
        e1Var.f1941b.values().removeAll(Collections.singleton(null));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        z zVar = this.f2119r;
        if (zVar != null) {
            sb.append(zVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f2119r)));
            sb.append("}");
        } else {
            l0 l0Var = this.f2117p;
            if (l0Var != null) {
                sb.append(l0Var.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.f2117p)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u(ArrayList arrayList, ArrayList arrayList2, int i7, int i8) {
        ViewGroup viewGroup;
        e1 e1Var;
        e1 e1Var2;
        e1 e1Var3;
        int i9;
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        boolean z6 = ((a) arrayList3.get(i7)).f1989p;
        ArrayList arrayList5 = this.G;
        if (arrayList5 == null) {
            this.G = new ArrayList();
        } else {
            arrayList5.clear();
        }
        ArrayList arrayList6 = this.G;
        e1 e1Var4 = this.f2104c;
        arrayList6.addAll(e1Var4.f());
        z zVar = this.f2120s;
        int i10 = i7;
        boolean z7 = false;
        while (true) {
            int i11 = 1;
            if (i10 >= i8) {
                e1 e1Var5 = e1Var4;
                this.G.clear();
                if (!z6 && this.f2116o >= 1) {
                    for (int i12 = i7; i12 < i8; i12++) {
                        Iterator it = ((a) arrayList.get(i12)).f1974a.iterator();
                        while (it.hasNext()) {
                            z zVar2 = ((g1) it.next()).f1959b;
                            if (zVar2 == null || zVar2.mFragmentManager == null) {
                                e1Var = e1Var5;
                            } else {
                                e1Var = e1Var5;
                                e1Var.g(f(zVar2));
                            }
                            e1Var5 = e1Var;
                        }
                    }
                }
                for (int i13 = i7; i13 < i8; i13++) {
                    a aVar = (a) arrayList.get(i13);
                    if (((Boolean) arrayList2.get(i13)).booleanValue()) {
                        aVar.d(-1);
                        aVar.h();
                    } else {
                        aVar.d(1);
                        aVar.g();
                    }
                }
                boolean booleanValue = ((Boolean) arrayList2.get(i8 - 1)).booleanValue();
                for (int i14 = i7; i14 < i8; i14++) {
                    a aVar2 = (a) arrayList.get(i14);
                    if (booleanValue) {
                        for (int size = aVar2.f1974a.size() - 1; size >= 0; size--) {
                            z zVar3 = ((g1) aVar2.f1974a.get(size)).f1959b;
                            if (zVar3 != null) {
                                f(zVar3).j();
                            }
                        }
                    } else {
                        Iterator it2 = aVar2.f1974a.iterator();
                        while (it2.hasNext()) {
                            z zVar4 = ((g1) it2.next()).f1959b;
                            if (zVar4 != null) {
                                f(zVar4).j();
                            }
                        }
                    }
                }
                E(this.f2116o, true);
                HashSet hashSet = new HashSet();
                for (int i15 = i7; i15 < i8; i15++) {
                    Iterator it3 = ((a) arrayList.get(i15)).f1974a.iterator();
                    while (it3.hasNext()) {
                        z zVar5 = ((g1) it3.next()).f1959b;
                        if (zVar5 != null && (viewGroup = zVar5.mContainer) != null) {
                            hashSet.add(y1.f(viewGroup, z()));
                        }
                    }
                }
                Iterator it4 = hashSet.iterator();
                while (it4.hasNext()) {
                    y1 y1Var = (y1) it4.next();
                    y1Var.f2132d = booleanValue;
                    y1Var.h();
                    y1Var.c();
                }
                for (int i16 = i7; i16 < i8; i16++) {
                    a aVar3 = (a) arrayList.get(i16);
                    if (((Boolean) arrayList2.get(i16)).booleanValue() && aVar3.f1912s >= 0) {
                        aVar3.f1912s = -1;
                    }
                    aVar3.getClass();
                }
                return;
            }
            a aVar4 = (a) arrayList3.get(i10);
            if (((Boolean) arrayList4.get(i10)).booleanValue()) {
                e1Var2 = e1Var4;
                int i17 = 1;
                ArrayList arrayList7 = this.G;
                ArrayList arrayList8 = aVar4.f1974a;
                int size2 = arrayList8.size() - 1;
                while (size2 >= 0) {
                    g1 g1Var = (g1) arrayList8.get(size2);
                    int i18 = g1Var.f1958a;
                    if (i18 != i17) {
                        if (i18 != 3) {
                            switch (i18) {
                                case 8:
                                    zVar = null;
                                    break;
                                case 9:
                                    zVar = g1Var.f1959b;
                                    break;
                                case 10:
                                    g1Var.f1965h = g1Var.f1964g;
                                    break;
                            }
                            size2--;
                            i17 = 1;
                        }
                        arrayList7.add(g1Var.f1959b);
                        size2--;
                        i17 = 1;
                    }
                    arrayList7.remove(g1Var.f1959b);
                    size2--;
                    i17 = 1;
                }
            } else {
                ArrayList arrayList9 = this.G;
                int i19 = 0;
                while (true) {
                    ArrayList arrayList10 = aVar4.f1974a;
                    if (i19 < arrayList10.size()) {
                        g1 g1Var2 = (g1) arrayList10.get(i19);
                        int i20 = g1Var2.f1958a;
                        if (i20 != i11) {
                            if (i20 != 2) {
                                if (i20 == 3 || i20 == 6) {
                                    arrayList9.remove(g1Var2.f1959b);
                                    z zVar6 = g1Var2.f1959b;
                                    if (zVar6 == zVar) {
                                        arrayList10.add(i19, new g1(zVar6, 9));
                                        i19++;
                                        e1Var3 = e1Var4;
                                        i9 = 1;
                                        zVar = null;
                                    }
                                } else if (i20 == 7) {
                                    e1Var3 = e1Var4;
                                    i9 = 1;
                                } else if (i20 == 8) {
                                    arrayList10.add(i19, new g1(zVar, 9));
                                    i19++;
                                    zVar = g1Var2.f1959b;
                                }
                                e1Var3 = e1Var4;
                                i9 = 1;
                            } else {
                                z zVar7 = g1Var2.f1959b;
                                int i21 = zVar7.mContainerId;
                                int size3 = arrayList9.size() - 1;
                                boolean z8 = false;
                                while (size3 >= 0) {
                                    e1 e1Var6 = e1Var4;
                                    z zVar8 = (z) arrayList9.get(size3);
                                    if (zVar8.mContainerId == i21) {
                                        if (zVar8 == zVar7) {
                                            z8 = true;
                                        } else {
                                            if (zVar8 == zVar) {
                                                arrayList10.add(i19, new g1(zVar8, 9));
                                                i19++;
                                                zVar = null;
                                            }
                                            g1 g1Var3 = new g1(zVar8, 3);
                                            g1Var3.f1960c = g1Var2.f1960c;
                                            g1Var3.f1962e = g1Var2.f1962e;
                                            g1Var3.f1961d = g1Var2.f1961d;
                                            g1Var3.f1963f = g1Var2.f1963f;
                                            arrayList10.add(i19, g1Var3);
                                            arrayList9.remove(zVar8);
                                            i19++;
                                            zVar = zVar;
                                        }
                                    }
                                    size3--;
                                    e1Var4 = e1Var6;
                                }
                                e1Var3 = e1Var4;
                                i9 = 1;
                                if (z8) {
                                    arrayList10.remove(i19);
                                    i19--;
                                } else {
                                    g1Var2.f1958a = 1;
                                    arrayList9.add(zVar7);
                                }
                            }
                            i19 += i9;
                            i11 = i9;
                            e1Var4 = e1Var3;
                        } else {
                            e1Var3 = e1Var4;
                            i9 = i11;
                        }
                        arrayList9.add(g1Var2.f1959b);
                        i19 += i9;
                        i11 = i9;
                        e1Var4 = e1Var3;
                    } else {
                        e1Var2 = e1Var4;
                    }
                }
            }
            z7 = z7 || aVar4.f1980g;
            i10++;
            arrayList3 = arrayList;
            arrayList4 = arrayList2;
            e1Var4 = e1Var2;
        }
    }

    public final void v(ArrayList arrayList, ArrayList arrayList2) {
    }

    public final z w(int i7) {
        e1 e1Var = this.f2104c;
        ArrayList arrayList = e1Var.f1940a;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            z zVar = (z) arrayList.get(size);
            if (zVar != null && zVar.mFragmentId == i7) {
                return zVar;
            }
        }
        for (d1 d1Var : e1Var.f1941b.values()) {
            if (d1Var != null) {
                z zVar2 = d1Var.f1933c;
                if (zVar2.mFragmentId == i7) {
                    return zVar2;
                }
            }
        }
        return null;
    }

    public final ViewGroup x(z zVar) {
        ViewGroup viewGroup = zVar.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (zVar.mContainerId > 0 && this.f2118q.c()) {
            View b7 = this.f2118q.b(zVar.mContainerId);
            if (b7 instanceof ViewGroup) {
                return (ViewGroup) b7;
            }
        }
        return null;
    }

    public final r0 y() {
        z zVar = this.f2119r;
        return zVar != null ? zVar.mFragmentManager.y() : this.f2121t;
    }

    public final p0 z() {
        z zVar = this.f2119r;
        return zVar != null ? zVar.mFragmentManager.z() : this.f2122u;
    }
}
