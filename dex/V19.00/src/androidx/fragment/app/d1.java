package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d1 {

    /* renamed from: a, reason: collision with root package name */
    public final e f1931a;

    /* renamed from: b, reason: collision with root package name */
    public final e1 f1932b;

    /* renamed from: c, reason: collision with root package name */
    public final z f1933c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1934d = false;

    /* renamed from: e, reason: collision with root package name */
    public int f1935e = -1;

    public d1(e eVar, e1 e1Var, z zVar) {
        this.f1931a = eVar;
        this.f1932b = e1Var;
        this.f1933c = zVar;
    }

    public d1(e eVar, e1 e1Var, z zVar, FragmentState fragmentState) {
        this.f1931a = eVar;
        this.f1932b = e1Var;
        this.f1933c = zVar;
        zVar.mSavedViewState = null;
        zVar.mSavedViewRegistryState = null;
        zVar.mBackStackNesting = 0;
        zVar.mInLayout = false;
        zVar.mAdded = false;
        z zVar2 = zVar.mTarget;
        zVar.mTargetWho = zVar2 != null ? zVar2.mWho : null;
        zVar.mTarget = null;
        Bundle bundle = fragmentState.f1903m;
        if (bundle != null) {
            zVar.mSavedFragmentState = bundle;
        } else {
            zVar.mSavedFragmentState = new Bundle();
        }
    }

    public d1(e eVar, e1 e1Var, ClassLoader classLoader, r0 r0Var, FragmentState fragmentState) {
        this.f1931a = eVar;
        this.f1932b = e1Var;
        z instantiate = z.instantiate(r0Var.f2046a.f2117p.f2008b, fragmentState.f1891a, null);
        this.f1933c = instantiate;
        Bundle bundle = fragmentState.f1900j;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        instantiate.setArguments(bundle);
        instantiate.mWho = fragmentState.f1892b;
        instantiate.mFromLayout = fragmentState.f1893c;
        instantiate.mRestored = true;
        instantiate.mFragmentId = fragmentState.f1894d;
        instantiate.mContainerId = fragmentState.f1895e;
        instantiate.mTag = fragmentState.f1896f;
        instantiate.mRetainInstance = fragmentState.f1897g;
        instantiate.mRemoving = fragmentState.f1898h;
        instantiate.mDetached = fragmentState.f1899i;
        instantiate.mHidden = fragmentState.f1901k;
        instantiate.mMaxState = androidx.lifecycle.r.values()[fragmentState.f1902l];
        Bundle bundle2 = fragmentState.f1903m;
        if (bundle2 != null) {
            instantiate.mSavedFragmentState = bundle2;
        } else {
            instantiate.mSavedFragmentState = new Bundle();
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(instantiate);
        }
    }

    public final void a() {
        View view;
        View view2;
        e1 e1Var = this.f1932b;
        e1Var.getClass();
        z zVar = this.f1933c;
        ViewGroup viewGroup = zVar.mContainer;
        int i7 = -1;
        if (viewGroup != null) {
            ArrayList arrayList = e1Var.f1940a;
            int indexOf = arrayList.indexOf(zVar);
            int i8 = indexOf - 1;
            while (true) {
                if (i8 < 0) {
                    while (true) {
                        indexOf++;
                        if (indexOf >= arrayList.size()) {
                            break;
                        }
                        z zVar2 = (z) arrayList.get(indexOf);
                        if (zVar2.mContainer == viewGroup && (view = zVar2.mView) != null) {
                            i7 = viewGroup.indexOfChild(view);
                            break;
                        }
                    }
                } else {
                    z zVar3 = (z) arrayList.get(i8);
                    if (zVar3.mContainer == viewGroup && (view2 = zVar3.mView) != null) {
                        i7 = viewGroup.indexOfChild(view2) + 1;
                        break;
                    }
                    i8--;
                }
            }
        }
        zVar.mContainer.addView(zVar.mView, i7);
    }

    public final void b() {
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        z zVar2 = zVar.mTarget;
        d1 d1Var = null;
        e1 e1Var = this.f1932b;
        if (zVar2 != null) {
            d1 d1Var2 = (d1) e1Var.f1941b.get(zVar2.mWho);
            if (d1Var2 == null) {
                throw new IllegalStateException("Fragment " + zVar + " declared target fragment " + zVar.mTarget + " that does not belong to this FragmentManager!");
            }
            zVar.mTargetWho = zVar.mTarget.mWho;
            zVar.mTarget = null;
            d1Var = d1Var2;
        } else {
            String str = zVar.mTargetWho;
            if (str != null && (d1Var = (d1) e1Var.f1941b.get(str)) == null) {
                StringBuilder sb = new StringBuilder("Fragment ");
                sb.append(zVar);
                sb.append(" declared target fragment ");
                throw new IllegalStateException(a.a.i(sb, zVar.mTargetWho, " that does not belong to this FragmentManager!"));
            }
        }
        if (d1Var != null) {
            d1Var.j();
        }
        x0 x0Var = zVar.mFragmentManager;
        zVar.mHost = x0Var.f2117p;
        zVar.mParentFragment = x0Var.f2119r;
        e eVar = this.f1931a;
        eVar.i(false);
        zVar.performAttach();
        eVar.c(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [androidx.fragment.app.w1] */
    /* JADX WARN: Type inference failed for: r2v17, types: [androidx.fragment.app.w1] */
    public final int c() {
        z zVar = this.f1933c;
        if (zVar.mFragmentManager == null) {
            return zVar.mState;
        }
        int i7 = this.f1935e;
        int i8 = c1.f1925a[zVar.mMaxState.ordinal()];
        if (i8 != 1) {
            i7 = i8 != 2 ? i8 != 3 ? i8 != 4 ? Math.min(i7, -1) : Math.min(i7, 0) : Math.min(i7, 1) : Math.min(i7, 5);
        }
        if (zVar.mFromLayout) {
            if (zVar.mInLayout) {
                i7 = Math.max(this.f1935e, 2);
                View view = zVar.mView;
                if (view != null && view.getParent() == null) {
                    i7 = Math.min(i7, 2);
                }
            } else {
                i7 = this.f1935e < 4 ? Math.min(i7, zVar.mState) : Math.min(i7, 1);
            }
        }
        if (!zVar.mAdded) {
            i7 = Math.min(i7, 1);
        }
        ViewGroup viewGroup = zVar.mContainer;
        v1 v1Var = null;
        if (viewGroup != null) {
            y1 g7 = y1.g(viewGroup, zVar.getParentFragmentManager());
            g7.getClass();
            v1 d7 = g7.d(zVar);
            v1 v1Var2 = d7 != null ? d7.f2071b : null;
            Iterator it = g7.f2131c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                v1 v1Var3 = (v1) it.next();
                if (v1Var3.f2072c.equals(zVar) && !v1Var3.f2075f) {
                    v1Var = v1Var3;
                    break;
                }
            }
            v1Var = (v1Var == null || !(v1Var2 == null || v1Var2 == w1.NONE)) ? v1Var2 : v1Var.f2071b;
        }
        if (v1Var == w1.ADDING) {
            i7 = Math.min(i7, 6);
        } else if (v1Var == w1.REMOVING) {
            i7 = Math.max(i7, 3);
        } else if (zVar.mRemoving) {
            i7 = zVar.isInBackStack() ? Math.min(i7, 1) : Math.min(i7, -1);
        }
        if (zVar.mDeferStart && zVar.mState < 5) {
            i7 = Math.min(i7, 4);
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(zVar);
        }
        return i7;
    }

    public final void d() {
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        if (zVar.mIsCreated) {
            zVar.restoreChildFragmentState(zVar.mSavedFragmentState);
            zVar.mState = 1;
        } else {
            e eVar = this.f1931a;
            eVar.j(false);
            zVar.performCreate(zVar.mSavedFragmentState);
            eVar.d(false);
        }
    }

    public final void e() {
        String str;
        z zVar = this.f1933c;
        if (zVar.mFromLayout) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(zVar);
        }
        LayoutInflater performGetLayoutInflater = zVar.performGetLayoutInflater(zVar.mSavedFragmentState);
        ViewGroup viewGroup = zVar.mContainer;
        if (viewGroup == null) {
            int i7 = zVar.mContainerId;
            if (i7 == 0) {
                viewGroup = null;
            } else {
                if (i7 == -1) {
                    throw new IllegalArgumentException(a.a.f("Cannot create fragment ", zVar, " for a container view with no id"));
                }
                viewGroup = (ViewGroup) zVar.mFragmentManager.f2118q.b(i7);
                if (viewGroup == null && !zVar.mRestored) {
                    try {
                        str = zVar.getResources().getResourceName(zVar.mContainerId);
                    } catch (Resources.NotFoundException unused) {
                        str = "unknown";
                    }
                    throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(zVar.mContainerId) + " (" + str + ") for fragment " + zVar);
                }
            }
        }
        zVar.mContainer = viewGroup;
        zVar.performCreateView(performGetLayoutInflater, viewGroup, zVar.mSavedFragmentState);
        View view = zVar.mView;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            zVar.mView.setTag(b1.b.fragment_container_view_tag, zVar);
            if (viewGroup != null) {
                a();
            }
            if (zVar.mHidden) {
                zVar.mView.setVisibility(8);
            }
            View view2 = zVar.mView;
            int i8 = p0.d1.OVER_SCROLL_ALWAYS;
            if (p0.o0.b(view2)) {
                p0.p0.c(zVar.mView);
            } else {
                View view3 = zVar.mView;
                view3.addOnAttachStateChangeListener(new m0(this, view3));
            }
            zVar.performViewCreated();
            this.f1931a.o(zVar, zVar.mView, false);
            int visibility = zVar.mView.getVisibility();
            zVar.setPostOnViewCreatedAlpha(zVar.mView.getAlpha());
            if (zVar.mContainer != null && visibility == 0) {
                View findFocus = zVar.mView.findFocus();
                if (findFocus != null) {
                    zVar.setFocusedView(findFocus);
                    if (Log.isLoggable("FragmentManager", 2)) {
                        findFocus.toString();
                        Objects.toString(zVar);
                    }
                }
                zVar.mView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            }
        }
        zVar.mState = 2;
    }

    public final void f() {
        z b7;
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        boolean z6 = true;
        boolean z7 = zVar.mRemoving && !zVar.isInBackStack();
        e1 e1Var = this.f1932b;
        if (!z7) {
            a1 a1Var = e1Var.f1942c;
            if (a1Var.f1915a.containsKey(zVar.mWho) && a1Var.f1918d && !a1Var.f1919e) {
                String str = zVar.mTargetWho;
                if (str != null && (b7 = e1Var.b(str)) != null && b7.mRetainInstance) {
                    zVar.mTarget = b7;
                }
                zVar.mState = 0;
                return;
            }
        }
        l0 l0Var = zVar.mHost;
        if (l0Var instanceof androidx.lifecycle.p1) {
            z6 = e1Var.f1942c.f1919e;
        } else {
            Context context = l0Var.f2008b;
            if (context instanceof Activity) {
                z6 = true ^ ((Activity) context).isChangingConfigurations();
            }
        }
        if (z7 || z6) {
            a1 a1Var2 = e1Var.f1942c;
            a1Var2.getClass();
            if (Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(zVar);
            }
            HashMap hashMap = a1Var2.f1916b;
            a1 a1Var3 = (a1) hashMap.get(zVar.mWho);
            if (a1Var3 != null) {
                a1Var3.onCleared();
                hashMap.remove(zVar.mWho);
            }
            HashMap hashMap2 = a1Var2.f1917c;
            androidx.lifecycle.o1 o1Var = (androidx.lifecycle.o1) hashMap2.get(zVar.mWho);
            if (o1Var != null) {
                o1Var.a();
                hashMap2.remove(zVar.mWho);
            }
        }
        zVar.performDestroy();
        this.f1931a.e(false);
        Iterator it = e1Var.d().iterator();
        while (it.hasNext()) {
            d1 d1Var = (d1) it.next();
            if (d1Var != null) {
                String str2 = zVar.mWho;
                z zVar2 = d1Var.f1933c;
                if (str2.equals(zVar2.mTargetWho)) {
                    zVar2.mTarget = zVar;
                    zVar2.mTargetWho = null;
                }
            }
        }
        String str3 = zVar.mTargetWho;
        if (str3 != null) {
            zVar.mTarget = e1Var.b(str3);
        }
        e1Var.h(this);
    }

    public final void g() {
        View view;
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        ViewGroup viewGroup = zVar.mContainer;
        if (viewGroup != null && (view = zVar.mView) != null) {
            viewGroup.removeView(view);
        }
        zVar.performDestroyView();
        this.f1931a.p(false);
        zVar.mContainer = null;
        zVar.mView = null;
        zVar.mViewLifecycleOwner = null;
        zVar.mViewLifecycleOwnerLiveData.h(null);
        zVar.mInLayout = false;
    }

    public final void h() {
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        zVar.performDetach();
        this.f1931a.f(false);
        zVar.mState = -1;
        zVar.mHost = null;
        zVar.mParentFragment = null;
        zVar.mFragmentManager = null;
        if (!zVar.mRemoving || zVar.isInBackStack()) {
            a1 a1Var = this.f1932b.f1942c;
            if (a1Var.f1915a.containsKey(zVar.mWho) && a1Var.f1918d && !a1Var.f1919e) {
                return;
            }
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Objects.toString(zVar);
        }
        zVar.initState();
    }

    public final void i() {
        z zVar = this.f1933c;
        if (zVar.mFromLayout && zVar.mInLayout && !zVar.mPerformedCreateView) {
            if (Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(zVar);
            }
            zVar.performCreateView(zVar.performGetLayoutInflater(zVar.mSavedFragmentState), null, zVar.mSavedFragmentState);
            View view = zVar.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                zVar.mView.setTag(b1.b.fragment_container_view_tag, zVar);
                if (zVar.mHidden) {
                    zVar.mView.setVisibility(8);
                }
                zVar.performViewCreated();
                this.f1931a.o(zVar, zVar.mView, false);
                zVar.mState = 2;
            }
        }
    }

    public final void j() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        boolean z6 = this.f1934d;
        z zVar = this.f1933c;
        if (z6) {
            if (Log.isLoggable("FragmentManager", 2)) {
                Objects.toString(zVar);
                return;
            }
            return;
        }
        try {
            this.f1934d = true;
            while (true) {
                int c7 = c();
                int i7 = zVar.mState;
                if (c7 == i7) {
                    if (zVar.mHiddenChanged) {
                        if (zVar.mView != null && (viewGroup = zVar.mContainer) != null) {
                            y1 g7 = y1.g(viewGroup, zVar.getParentFragmentManager());
                            if (zVar.mHidden) {
                                g7.getClass();
                                if (Log.isLoggable("FragmentManager", 2)) {
                                    Objects.toString(zVar);
                                }
                                g7.a(x1.GONE, w1.NONE, this);
                            } else {
                                g7.getClass();
                                if (Log.isLoggable("FragmentManager", 2)) {
                                    Objects.toString(zVar);
                                }
                                g7.a(x1.VISIBLE, w1.NONE, this);
                            }
                        }
                        x0 x0Var = zVar.mFragmentManager;
                        if (x0Var != null && zVar.mAdded && x0.B(zVar)) {
                            x0Var.f2127z = true;
                        }
                        zVar.mHiddenChanged = false;
                        zVar.onHiddenChanged(zVar.mHidden);
                    }
                    this.f1934d = false;
                    return;
                }
                e eVar = this.f1931a;
                if (c7 <= i7) {
                    switch (i7 - 1) {
                        case -1:
                            h();
                            break;
                        case 0:
                            f();
                            break;
                        case 1:
                            g();
                            zVar.mState = 1;
                            break;
                        case 2:
                            zVar.mInLayout = false;
                            zVar.mState = 2;
                            break;
                        case 3:
                            if (Log.isLoggable("FragmentManager", 3)) {
                                Objects.toString(zVar);
                            }
                            if (zVar.mView != null && zVar.mSavedViewState == null) {
                                n();
                            }
                            if (zVar.mView != null && (viewGroup3 = zVar.mContainer) != null) {
                                y1 g8 = y1.g(viewGroup3, zVar.getParentFragmentManager());
                                g8.getClass();
                                if (Log.isLoggable("FragmentManager", 2)) {
                                    Objects.toString(zVar);
                                }
                                g8.a(x1.REMOVED, w1.REMOVING, this);
                            }
                            zVar.mState = 3;
                            break;
                        case 4:
                            if (Log.isLoggable("FragmentManager", 3)) {
                                Objects.toString(zVar);
                            }
                            zVar.performStop();
                            eVar.n(false);
                            break;
                        case 5:
                            zVar.mState = 5;
                            break;
                        case 6:
                            if (Log.isLoggable("FragmentManager", 3)) {
                                Objects.toString(zVar);
                            }
                            zVar.performPause();
                            eVar.g(false);
                            break;
                    }
                } else {
                    switch (i7 + 1) {
                        case 0:
                            b();
                            break;
                        case 1:
                            d();
                            break;
                        case 2:
                            i();
                            e();
                            break;
                        case 3:
                            if (Log.isLoggable("FragmentManager", 3)) {
                                Objects.toString(zVar);
                            }
                            zVar.performActivityCreated(zVar.mSavedFragmentState);
                            eVar.b(false);
                            break;
                        case 4:
                            if (zVar.mView != null && (viewGroup2 = zVar.mContainer) != null) {
                                y1 g9 = y1.g(viewGroup2, zVar.getParentFragmentManager());
                                x1 b7 = x1.b(zVar.mView.getVisibility());
                                g9.getClass();
                                if (Log.isLoggable("FragmentManager", 2)) {
                                    Objects.toString(zVar);
                                }
                                g9.a(b7, w1.ADDING, this);
                            }
                            zVar.mState = 4;
                            break;
                        case 5:
                            if (Log.isLoggable("FragmentManager", 3)) {
                                Objects.toString(zVar);
                            }
                            zVar.performStart();
                            eVar.m(false);
                            break;
                        case 6:
                            zVar.mState = 6;
                            break;
                        case 7:
                            l();
                            break;
                    }
                }
            }
        } catch (Throwable th) {
            this.f1934d = false;
            throw th;
        }
    }

    public final void k(ClassLoader classLoader) {
        z zVar = this.f1933c;
        Bundle bundle = zVar.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        zVar.mSavedViewState = zVar.mSavedFragmentState.getSparseParcelableArray("android:view_state");
        zVar.mSavedViewRegistryState = zVar.mSavedFragmentState.getBundle("android:view_registry_state");
        zVar.mTargetWho = zVar.mSavedFragmentState.getString("android:target_state");
        if (zVar.mTargetWho != null) {
            zVar.mTargetRequestCode = zVar.mSavedFragmentState.getInt("android:target_req_state", 0);
        }
        Boolean bool = zVar.mSavedUserVisibleHint;
        if (bool != null) {
            zVar.mUserVisibleHint = bool.booleanValue();
            zVar.mSavedUserVisibleHint = null;
        } else {
            zVar.mUserVisibleHint = zVar.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
        }
        if (zVar.mUserVisibleHint) {
            return;
        }
        zVar.mDeferStart = true;
    }

    public final void l() {
        boolean isLoggable = Log.isLoggable("FragmentManager", 3);
        z zVar = this.f1933c;
        if (isLoggable) {
            Objects.toString(zVar);
        }
        View focusedView = zVar.getFocusedView();
        if (focusedView != null) {
            if (focusedView != zVar.mView) {
                for (ViewParent parent = focusedView.getParent(); parent != null; parent = parent.getParent()) {
                    if (parent != zVar.mView) {
                    }
                }
            }
            focusedView.requestFocus();
            if (Log.isLoggable("FragmentManager", 2)) {
                focusedView.toString();
                Objects.toString(zVar);
                Objects.toString(zVar.mView.findFocus());
            }
        }
        zVar.setFocusedView(null);
        zVar.performResume();
        this.f1931a.k(false);
        zVar.mSavedFragmentState = null;
        zVar.mSavedViewState = null;
        zVar.mSavedViewRegistryState = null;
    }

    public final Bundle m() {
        Bundle bundle = new Bundle();
        z zVar = this.f1933c;
        zVar.performSaveInstanceState(bundle);
        this.f1931a.l(false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (zVar.mView != null) {
            n();
        }
        if (zVar.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", zVar.mSavedViewState);
        }
        if (zVar.mSavedViewRegistryState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", zVar.mSavedViewRegistryState);
        }
        if (!zVar.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", zVar.mUserVisibleHint);
        }
        return bundle;
    }

    public final void n() {
        z zVar = this.f1933c;
        if (zVar.mView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        zVar.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            zVar.mSavedViewState = sparseArray;
        }
        Bundle bundle = new Bundle();
        zVar.mViewLifecycleOwner.f2051e.c(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        zVar.mSavedViewRegistryState = bundle;
    }
}
