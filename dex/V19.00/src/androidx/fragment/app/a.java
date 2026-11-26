package androidx.fragment.app;

import android.util.Log;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a extends h1 implements u0 {

    /* renamed from: q, reason: collision with root package name */
    public final x0 f1910q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1911r;

    /* renamed from: s, reason: collision with root package name */
    public int f1912s;

    public a(x0 x0Var) {
        x0Var.y();
        l0 l0Var = x0Var.f2117p;
        if (l0Var != null) {
            l0Var.f2008b.getClassLoader();
        }
        this.f1974a = new ArrayList();
        this.f1981h = true;
        this.f1989p = false;
        this.f1912s = -1;
        this.f1910q = x0Var;
    }

    @Override // androidx.fragment.app.u0
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f1980g) {
            return true;
        }
        x0 x0Var = this.f1910q;
        if (x0Var.f2105d == null) {
            x0Var.f2105d = new ArrayList();
        }
        x0Var.f2105d.add(this);
        return true;
    }

    @Override // androidx.fragment.app.h1
    public final void c(int i7, z zVar, String str, int i8) {
        Class<?> cls = zVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = zVar.mTag;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + zVar + ": was " + zVar.mTag + " now " + str);
            }
            zVar.mTag = str;
        }
        if (i7 != 0) {
            if (i7 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + zVar + " with tag " + str + " to container view with no id");
            }
            int i9 = zVar.mFragmentId;
            if (i9 != 0 && i9 != i7) {
                throw new IllegalStateException("Can't change container ID of fragment " + zVar + ": was " + zVar.mFragmentId + " now " + i7);
            }
            zVar.mFragmentId = i7;
            zVar.mContainerId = i7;
        }
        b(new g1(zVar, i8));
        zVar.mFragmentManager = this.f1910q;
    }

    public final void d(int i7) {
        if (this.f1980g) {
            if (Log.isLoggable("FragmentManager", 2)) {
                toString();
            }
            ArrayList arrayList = this.f1974a;
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                g1 g1Var = (g1) arrayList.get(i8);
                z zVar = g1Var.f1959b;
                if (zVar != null) {
                    zVar.mBackStackNesting += i7;
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Objects.toString(g1Var.f1959b);
                        int i9 = g1Var.f1959b.mBackStackNesting;
                    }
                }
            }
        }
    }

    public final int e(boolean z6) {
        if (this.f1911r) {
            throw new IllegalStateException("commit already called");
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
            PrintWriter printWriter = new PrintWriter(new s1());
            f("  ", printWriter, true);
            printWriter.close();
        }
        this.f1911r = true;
        boolean z7 = this.f1980g;
        x0 x0Var = this.f1910q;
        if (z7) {
            this.f1912s = x0Var.f2110i.getAndIncrement();
        } else {
            this.f1912s = -1;
        }
        x0Var.q(this, z6);
        return this.f1912s;
    }

    public final void f(String str, PrintWriter printWriter, boolean z6) {
        String str2;
        if (z6) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f1982i);
            printWriter.print(" mIndex=");
            printWriter.print(this.f1912s);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f1911r);
            if (this.f1979f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1979f));
            }
            if (this.f1975b != 0 || this.f1976c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1975b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1976c));
            }
            if (this.f1977d != 0 || this.f1978e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1977d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1978e));
            }
            if (this.f1983j != 0 || this.f1984k != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1983j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f1984k);
            }
            if (this.f1985l != 0 || this.f1986m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1985l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f1986m);
            }
        }
        ArrayList arrayList = this.f1974a;
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            g1 g1Var = (g1) arrayList.get(i7);
            switch (g1Var.f1958a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + g1Var.f1958a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i7);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(g1Var.f1959b);
            if (z6) {
                if (g1Var.f1960c != 0 || g1Var.f1961d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(g1Var.f1960c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(g1Var.f1961d));
                }
                if (g1Var.f1962e != 0 || g1Var.f1963f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(g1Var.f1962e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(g1Var.f1963f));
                }
            }
        }
    }

    public final void g() {
        ArrayList arrayList = this.f1974a;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            g1 g1Var = (g1) arrayList.get(i7);
            z zVar = g1Var.f1959b;
            if (zVar != null) {
                zVar.setPopDirection(false);
                zVar.setNextTransition(this.f1979f);
                zVar.setSharedElementNames(this.f1987n, this.f1988o);
            }
            int i8 = g1Var.f1958a;
            x0 x0Var = this.f1910q;
            switch (i8) {
                case 1:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, false);
                    x0Var.a(zVar);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + g1Var.f1958a);
                case 3:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.K(zVar);
                    break;
                case 4:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.A(zVar);
                    break;
                case 5:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, false);
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Objects.toString(zVar);
                    }
                    if (zVar.mHidden) {
                        zVar.mHidden = false;
                        zVar.mHiddenChanged = !zVar.mHiddenChanged;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.g(zVar);
                    break;
                case 7:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, false);
                    x0Var.c(zVar);
                    break;
                case 8:
                    x0Var.S(zVar);
                    break;
                case 9:
                    x0Var.S(null);
                    break;
                case 10:
                    x0Var.R(zVar, g1Var.f1965h);
                    break;
            }
        }
    }

    public final void h() {
        ArrayList arrayList = this.f1974a;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            g1 g1Var = (g1) arrayList.get(size);
            z zVar = g1Var.f1959b;
            if (zVar != null) {
                zVar.setPopDirection(true);
                int i7 = this.f1979f;
                int i8 = h1.TRANSIT_FRAGMENT_CLOSE;
                if (i7 != 4097) {
                    i8 = i7 != 4099 ? i7 != 8194 ? 0 : 4097 : 4099;
                }
                zVar.setNextTransition(i8);
                zVar.setSharedElementNames(this.f1988o, this.f1987n);
            }
            int i9 = g1Var.f1958a;
            x0 x0Var = this.f1910q;
            switch (i9) {
                case 1:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, true);
                    x0Var.K(zVar);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + g1Var.f1958a);
                case 3:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.a(zVar);
                    break;
                case 4:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.getClass();
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Objects.toString(zVar);
                    }
                    if (zVar.mHidden) {
                        zVar.mHidden = false;
                        zVar.mHiddenChanged = !zVar.mHiddenChanged;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, true);
                    x0Var.A(zVar);
                    break;
                case 6:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.c(zVar);
                    break;
                case 7:
                    zVar.setAnimations(g1Var.f1960c, g1Var.f1961d, g1Var.f1962e, g1Var.f1963f);
                    x0Var.Q(zVar, true);
                    x0Var.g(zVar);
                    break;
                case 8:
                    x0Var.S(null);
                    break;
                case 9:
                    x0Var.S(zVar);
                    break;
                case 10:
                    x0Var.R(zVar, g1Var.f1964g);
                    break;
            }
        }
    }

    public final a i(z zVar) {
        x0 x0Var = zVar.mFragmentManager;
        if (x0Var == null || x0Var == this.f1910q) {
            b(new g1(zVar, 3));
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + zVar.toString() + " is already attached to a FragmentManager.");
    }

    public final a j(z zVar, androidx.lifecycle.r rVar) {
        x0 x0Var = zVar.mFragmentManager;
        x0 x0Var2 = this.f1910q;
        if (x0Var != x0Var2) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + x0Var2);
        }
        if (rVar == androidx.lifecycle.r.INITIALIZED && zVar.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + rVar + " after the Fragment has been created");
        }
        if (rVar == androidx.lifecycle.r.DESTROYED) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + rVar + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
        }
        g1 g1Var = new g1();
        g1Var.f1958a = 10;
        g1Var.f1959b = zVar;
        g1Var.f1964g = zVar.mMaxState;
        g1Var.f1965h = rVar;
        b(g1Var);
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f1912s >= 0) {
            sb.append(" #");
            sb.append(this.f1912s);
        }
        if (this.f1982i != null) {
            sb.append(" ");
            sb.append(this.f1982i);
        }
        sb.append("}");
        return sb.toString();
    }
}
