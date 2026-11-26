package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.IntentSenderRequest;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class z implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.x, androidx.lifecycle.p1, androidx.lifecycle.k, j1.j {
    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = new Object();
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    w mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    private boolean mCalled;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    androidx.lifecycle.j1 mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    x0 mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    l0 mHost;
    boolean mInLayout;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    z mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    j1.i mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray<Parcelable> mSavedViewState;
    String mTag;
    z mTarget;
    int mTargetRequestCode;
    View mView;
    r1 mViewLifecycleOwner;
    int mState = -1;
    String mWho = UUID.randomUUID().toString();
    String mTargetWho = null;
    private Boolean mIsPrimaryNavigationFragment = null;
    x0 mChildFragmentManager = new y0();
    boolean mMenuVisible = true;
    boolean mUserVisibleHint = true;
    Runnable mPostponedDurationRunnable = new r(this, 0);
    androidx.lifecycle.r mMaxState = androidx.lifecycle.r.RESUMED;
    androidx.lifecycle.g0 mViewLifecycleOwnerLiveData = new androidx.lifecycle.g0();
    private final AtomicInteger mNextLocalRequestCode = new AtomicInteger();
    private final ArrayList<v> mOnPreAttachedListeners = new ArrayList<>();
    androidx.lifecycle.a0 mLifecycleRegistry = new androidx.lifecycle.a0(this);

    public z() {
        j1.i.Companion.getClass();
        this.mSavedStateRegistryController = j1.h.a(this);
        this.mDefaultFactory = null;
    }

    @Deprecated
    public static z instantiate(Context context, String str) {
        return instantiate(context, str, null);
    }

    @Deprecated
    public static z instantiate(Context context, String str, Bundle bundle) {
        try {
            z zVar = (z) r0.b(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(zVar.getClass().getClassLoader());
                zVar.setArguments(bundle);
            }
            return zVar;
        } catch (IllegalAccessException e7) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e7);
        } catch (InstantiationException e8) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e8);
        } catch (NoSuchMethodException e9) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e9);
        } catch (InvocationTargetException e10) {
            throw new Fragment$InstantiationException(a.a.h("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e10);
        }
    }

    public final w a() {
        if (this.mAnimationInfo == null) {
            w wVar = new w();
            wVar.f2088k = null;
            Object obj = USE_DEFAULT_TRANSITION;
            wVar.f2089l = obj;
            wVar.f2090m = null;
            wVar.f2091n = obj;
            wVar.f2092o = null;
            wVar.f2093p = obj;
            wVar.f2096s = 1.0f;
            wVar.f2097t = null;
            this.mAnimationInfo = wVar;
        }
        return this.mAnimationInfo;
    }

    public final int b() {
        androidx.lifecycle.r rVar = this.mMaxState;
        return (rVar == androidx.lifecycle.r.INITIALIZED || this.mParentFragment == null) ? rVar.ordinal() : Math.min(rVar.ordinal(), this.mParentFragment.b());
    }

    public final d.d c(e.b bVar, n.a aVar, d.a aVar2) {
        if (this.mState > 1) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate())."));
        }
        AtomicReference atomicReference = new AtomicReference();
        v vVar = new v(this, aVar, atomicReference, bVar, aVar2);
        if (this.mState >= 0) {
            atomicReference.set(aVar.a().c(generateActivityResultKey(), this, bVar, aVar2));
        } else {
            this.mOnPreAttachedListeners.add(vVar);
        }
        return new d.d(this, atomicReference, bVar, 2);
    }

    public void callStartTransitionListener(boolean z6) {
        ViewGroup viewGroup;
        x0 x0Var;
        w wVar = this.mAnimationInfo;
        if (wVar != null) {
            wVar.f2098u = false;
            wVar.getClass();
            wVar.getClass();
        }
        if (this.mView == null || (viewGroup = this.mContainer) == null || (x0Var = this.mFragmentManager) == null) {
            return;
        }
        y1 g7 = y1.g(viewGroup, x0Var);
        g7.h();
        if (z6) {
            this.mHost.f2009c.post(new h(2, this, g7));
        } else {
            g7.c();
        }
    }

    public j0 createFragmentContainer() {
        return new s(this);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mSavedViewRegistryState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.mSavedViewRegistryState);
        }
        z targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(getPopDirection());
        if (getEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(getEnterAnim());
        }
        if (getExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(getExitAnim());
        }
        if (getPopEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(getPopEnterAnim());
        }
        if (getPopExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(getPopExitAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
        }
        if (getContext() != null) {
            f1.a.a(this).b(str, printWriter);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.p(a.a.g(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public z findFragmentByWho(String str) {
        return str.equals(this.mWho) ? this : this.mChildFragmentManager.f2104c.c(str);
    }

    public String generateActivityResultKey() {
        return "fragment_" + this.mWho + "_rq#" + this.mNextLocalRequestCode.getAndIncrement();
    }

    public final d0 getActivity() {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            return null;
        }
        return (d0) l0Var.f2007a;
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        w wVar = this.mAnimationInfo;
        if (wVar == null || (bool = wVar.f2095r) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        w wVar = this.mAnimationInfo;
        if (wVar == null || (bool = wVar.f2094q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2078a;
    }

    public Animator getAnimator() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2079b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final x0 getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " has not been attached yet."));
    }

    public Context getContext() {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            return null;
        }
        return l0Var.f2008b;
    }

    @Override // androidx.lifecycle.k
    public androidx.lifecycle.j1 getDefaultViewModelProviderFactory() {
        Application application;
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.mDefaultFactory == null) {
            Context applicationContext = requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    application = null;
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && Log.isLoggable("FragmentManager", 3)) {
                Objects.toString(requireContext().getApplicationContext());
            }
            this.mDefaultFactory = new androidx.lifecycle.c1(application, this, getArguments());
        }
        return this.mDefaultFactory;
    }

    public int getEnterAnim() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 0;
        }
        return wVar.f2081d;
    }

    public Object getEnterTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2088k;
    }

    public e0.z getEnterTransitionCallback() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        wVar.getClass();
        return null;
    }

    public int getExitAnim() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 0;
        }
        return wVar.f2082e;
    }

    public Object getExitTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2090m;
    }

    public e0.z getExitTransitionCallback() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        wVar.getClass();
        return null;
    }

    public View getFocusedView() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2097t;
    }

    @Deprecated
    public final x0 getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            return null;
        }
        return ((c0) l0Var).f1924e;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        return layoutInflater == null ? performGetLayoutInflater(null) : layoutInflater;
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        d0 d0Var = ((c0) l0Var).f1924e;
        LayoutInflater cloneInContext = d0Var.getLayoutInflater().cloneInContext(d0Var);
        cloneInContext.setFactory2(this.mChildFragmentManager.f2107f);
        return cloneInContext;
    }

    @Override // androidx.lifecycle.x
    public androidx.lifecycle.s getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public f1.a getLoaderManager() {
        return f1.a.a(this);
    }

    public int getNextTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 0;
        }
        return wVar.f2085h;
    }

    public final z getParentFragment() {
        return this.mParentFragment;
    }

    public final x0 getParentFragmentManager() {
        x0 x0Var = this.mFragmentManager;
        if (x0Var != null) {
            return x0Var;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " not associated with a fragment manager."));
    }

    public boolean getPopDirection() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return false;
        }
        return wVar.f2080c;
    }

    public int getPopEnterAnim() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 0;
        }
        return wVar.f2083f;
    }

    public int getPopExitAnim() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 0;
        }
        return wVar.f2084g;
    }

    public float getPostOnViewCreatedAlpha() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return 1.0f;
        }
        return wVar.f2096s;
    }

    public Object getReenterTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        Object obj = wVar.f2091n;
        return obj == USE_DEFAULT_TRANSITION ? getExitTransition() : obj;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        Object obj = wVar.f2089l;
        return obj == USE_DEFAULT_TRANSITION ? getEnterTransition() : obj;
    }

    @Override // j1.j
    public final j1.g getSavedStateRegistry() {
        return this.mSavedStateRegistryController.f6950b;
    }

    public Object getSharedElementEnterTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        return wVar.f2092o;
    }

    public Object getSharedElementReturnTransition() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return null;
        }
        Object obj = wVar.f2093p;
        return obj == USE_DEFAULT_TRANSITION ? getSharedElementEnterTransition() : obj;
    }

    public ArrayList<String> getSharedElementSourceNames() {
        ArrayList<String> arrayList;
        w wVar = this.mAnimationInfo;
        return (wVar == null || (arrayList = wVar.f2086i) == null) ? new ArrayList<>() : arrayList;
    }

    public ArrayList<String> getSharedElementTargetNames() {
        ArrayList<String> arrayList;
        w wVar = this.mAnimationInfo;
        return (wVar == null || (arrayList = wVar.f2087j) == null) ? new ArrayList<>() : arrayList;
    }

    public final String getString(int i7) {
        return getResources().getString(i7);
    }

    public final String getString(int i7, Object... objArr) {
        return getResources().getString(i7, objArr);
    }

    public final String getTag() {
        return this.mTag;
    }

    @Deprecated
    public final z getTargetFragment() {
        String str;
        z zVar = this.mTarget;
        if (zVar != null) {
            return zVar;
        }
        x0 x0Var = this.mFragmentManager;
        if (x0Var == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return x0Var.f2104c.b(str);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int i7) {
        return getResources().getText(i7);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public androidx.lifecycle.x getViewLifecycleOwner() {
        r1 r1Var = this.mViewLifecycleOwner;
        if (r1Var != null) {
            return r1Var;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public androidx.lifecycle.f0 getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override // androidx.lifecycle.p1
    public androidx.lifecycle.o1 getViewModelStore() {
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (b() == androidx.lifecycle.r.INITIALIZED.ordinal()) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        HashMap hashMap = this.mFragmentManager.H.f1917c;
        androidx.lifecycle.o1 o1Var = (androidx.lifecycle.o1) hashMap.get(this.mWho);
        if (o1Var != null) {
            return o1Var;
        }
        androidx.lifecycle.o1 o1Var2 = new androidx.lifecycle.o1();
        hashMap.put(this.mWho, o1Var2);
        return o1Var2;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public void initState() {
        this.mLifecycleRegistry = new androidx.lifecycle.a0(this);
        j1.i.Companion.getClass();
        this.mSavedStateRegistryController = j1.h.a(this);
        this.mDefaultFactory = null;
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new y0();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public boolean isHideReplaced() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return false;
        }
        return wVar.f2099v;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        z zVar;
        return this.mMenuVisible && (this.mFragmentManager == null || (zVar = this.mParentFragment) == null || zVar.isMenuVisible());
    }

    public boolean isPostponed() {
        w wVar = this.mAnimationInfo;
        if (wVar == null) {
            return false;
        }
        return wVar.f2098u;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isRemovingParent() {
        z parentFragment = getParentFragment();
        return parentFragment != null && (parentFragment.isRemoving() || parentFragment.isRemovingParent());
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    public final boolean isStateSaved() {
        x0 x0Var = this.mFragmentManager;
        if (x0Var == null) {
            return false;
        }
        return x0Var.D();
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) ? false : true;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.G();
    }

    @Deprecated
    public void onActivityCreated(Bundle bundle) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int i7, int i8, Intent intent) {
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
            Objects.toString(intent);
        }
    }

    @Deprecated
    public void onAttach(Activity activity) {
        this.mCalled = true;
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        l0 l0Var = this.mHost;
        Activity activity = l0Var == null ? null : l0Var.f2007a;
        if (activity != null) {
            this.mCalled = false;
            onAttach(activity);
        }
    }

    @Deprecated
    public void onAttachFragment(z zVar) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        x0 x0Var = this.mChildFragmentManager;
        if (x0Var.f2116o >= 1) {
            return;
        }
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(1);
    }

    public Animation onCreateAnimation(int i7, boolean z6, int i8) {
        return null;
    }

    public Animator onCreateAnimator(int i7, boolean z6, int i8) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i7 = this.mContentLayoutId;
        if (i7 != 0) {
            return layoutInflater.inflate(i7, viewGroup, false);
        }
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void onHiddenChanged(boolean z6) {
    }

    @Deprecated
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        l0 l0Var = this.mHost;
        Activity activity = l0Var == null ? null : l0Var.f2007a;
        if (activity != null) {
            this.mCalled = false;
            onInflate(activity, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z6) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z6) {
    }

    public void onPrepareOptionsMenu(Menu menu) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z6) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle) {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.G();
        this.mState = 3;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onActivityCreated()"));
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        if (this.mView != null) {
            restoreViewState(this.mSavedFragmentState);
        }
        this.mSavedFragmentState = null;
        x0 x0Var = this.mChildFragmentManager;
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(4);
    }

    public void performAttach() {
        Iterator<v> it = this.mOnPreAttachedListeners.iterator();
        while (it.hasNext()) {
            v next = it.next();
            z zVar = next.f2066e;
            next.f2063b.set(next.f2062a.a().c(zVar.generateActivityResultKey(), zVar, next.f2064c, next.f2065d));
        }
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.b(this.mHost, createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach(this.mHost.f2008b);
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onAttach()"));
        }
        Iterator it2 = this.mFragmentManager.f2115n.iterator();
        while (it2.hasNext()) {
            ((b1) it2.next()).a(this);
        }
        x0 x0Var = this.mChildFragmentManager;
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(0);
    }

    public void performConfigurationChanged(Configuration configuration) {
        onConfigurationChanged(configuration);
        for (z zVar : this.mChildFragmentManager.f2104c.f()) {
            if (zVar != null) {
                zVar.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean performContextItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.h(menuItem);
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.G();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.a(new t(this));
        this.mSavedStateRegistryController.b(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onCreate()"));
        }
        this.mLifecycleRegistry.e(androidx.lifecycle.q.ON_CREATE);
    }

    public boolean performCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z6 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onCreateOptionsMenu(menu, menuInflater);
            z6 = true;
        }
        return z6 | this.mChildFragmentManager.i(menu, menuInflater);
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.G();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new r1(this, getViewModelStore());
        View onCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = onCreateView;
        if (onCreateView == null) {
            if (this.mViewLifecycleOwner.f2050d != null) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.mViewLifecycleOwner = null;
            return;
        }
        this.mViewLifecycleOwner.b();
        View view = this.mView;
        r1 r1Var = this.mViewLifecycleOwner;
        i5.a.g("<this>", view);
        view.setTag(d1.a.view_tree_lifecycle_owner, r1Var);
        View view2 = this.mView;
        r1 r1Var2 = this.mViewLifecycleOwner;
        i5.a.g("<this>", view2);
        view2.setTag(e1.e.view_tree_view_model_store_owner, r1Var2);
        View view3 = this.mView;
        r1 r1Var3 = this.mViewLifecycleOwner;
        i5.a.g("<this>", view3);
        view3.setTag(j1.a.view_tree_saved_state_registry_owner, r1Var3);
        this.mViewLifecycleOwnerLiveData.h(this.mViewLifecycleOwner);
    }

    public void performDestroy() {
        this.mChildFragmentManager.j();
        this.mLifecycleRegistry.e(androidx.lifecycle.q.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onDestroy()"));
        }
    }

    public void performDestroyView() {
        this.mChildFragmentManager.o(1);
        if (this.mView != null) {
            r1 r1Var = this.mViewLifecycleOwner;
            r1Var.b();
            if (r1Var.f2050d.f2136c.a(androidx.lifecycle.r.CREATED)) {
                this.mViewLifecycleOwner.a(androidx.lifecycle.q.ON_DESTROY);
            }
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onDestroyView()"));
        }
        q.l lVar = f1.a.a(this).f6399b.f6397a;
        if (lVar.f8416c <= 0) {
            this.mPerformedCreateView = false;
        } else {
            a.a.u(lVar.f8415b[0]);
            throw null;
        }
    }

    public void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onDetach()"));
        }
        x0 x0Var = this.mChildFragmentManager;
        if (x0Var.C) {
            return;
        }
        x0Var.j();
        this.mChildFragmentManager = new y0();
    }

    public LayoutInflater performGetLayoutInflater(Bundle bundle) {
        LayoutInflater onGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = onGetLayoutInflater;
        return onGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
        for (z zVar : this.mChildFragmentManager.f2104c.f()) {
            if (zVar != null) {
                zVar.performLowMemory();
            }
        }
    }

    public void performMultiWindowModeChanged(boolean z6) {
        onMultiWindowModeChanged(z6);
        for (z zVar : this.mChildFragmentManager.f2104c.f()) {
            if (zVar != null) {
                zVar.performMultiWindowModeChanged(z6);
            }
        }
    }

    public boolean performOptionsItemSelected(MenuItem menuItem) {
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible && onOptionsItemSelected(menuItem)) {
            return true;
        }
        return this.mChildFragmentManager.k(menuItem);
    }

    public void performOptionsMenuClosed(Menu menu) {
        if (this.mHidden) {
            return;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onOptionsMenuClosed(menu);
        }
        this.mChildFragmentManager.l(menu);
    }

    public void performPause() {
        this.mChildFragmentManager.o(5);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.q.ON_PAUSE);
        }
        this.mLifecycleRegistry.e(androidx.lifecycle.q.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onPause()"));
        }
    }

    public void performPictureInPictureModeChanged(boolean z6) {
        onPictureInPictureModeChanged(z6);
        for (z zVar : this.mChildFragmentManager.f2104c.f()) {
            if (zVar != null) {
                zVar.performPictureInPictureModeChanged(z6);
            }
        }
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        boolean z6 = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            onPrepareOptionsMenu(menu);
            z6 = true;
        }
        return z6 | this.mChildFragmentManager.n(menu);
    }

    public void performPrimaryNavigationFragmentChanged() {
        this.mFragmentManager.getClass();
        boolean C = x0.C(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != C) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(C);
            onPrimaryNavigationFragmentChanged(C);
            x0 x0Var = this.mChildFragmentManager;
            x0Var.V();
            x0Var.m(x0Var.f2120s);
        }
    }

    public void performResume() {
        this.mChildFragmentManager.G();
        this.mChildFragmentManager.s(true);
        this.mState = 7;
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onResume()"));
        }
        androidx.lifecycle.a0 a0Var = this.mLifecycleRegistry;
        androidx.lifecycle.q qVar = androidx.lifecycle.q.ON_RESUME;
        a0Var.e(qVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(qVar);
        }
        x0 x0Var = this.mChildFragmentManager;
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(7);
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.c(bundle);
        Parcelable N = this.mChildFragmentManager.N();
        if (N != null) {
            bundle.putParcelable("android:support:fragments", N);
        }
    }

    public void performStart() {
        this.mChildFragmentManager.G();
        this.mChildFragmentManager.s(true);
        this.mState = 5;
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onStart()"));
        }
        androidx.lifecycle.a0 a0Var = this.mLifecycleRegistry;
        androidx.lifecycle.q qVar = androidx.lifecycle.q.ON_START;
        a0Var.e(qVar);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(qVar);
        }
        x0 x0Var = this.mChildFragmentManager;
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(5);
    }

    public void performStop() {
        x0 x0Var = this.mChildFragmentManager;
        x0Var.B = true;
        x0Var.H.f1920f = true;
        x0Var.o(4);
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.q.ON_STOP);
        }
        this.mLifecycleRegistry.e(androidx.lifecycle.q.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onStop()"));
        }
    }

    public void performViewCreated() {
        onViewCreated(this.mView, this.mSavedFragmentState);
        this.mChildFragmentManager.o(2);
    }

    public void postponeEnterTransition() {
        a().f2098u = true;
    }

    public final void postponeEnterTransition(long j7, TimeUnit timeUnit) {
        a().f2098u = true;
        x0 x0Var = this.mFragmentManager;
        Handler handler = x0Var != null ? x0Var.f2117p.f2009c : new Handler(Looper.getMainLooper());
        handler.removeCallbacks(this.mPostponedDurationRunnable);
        handler.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j7));
    }

    public final <I, O> d.b registerForActivityResult(e.b bVar, d.a aVar) {
        return c(bVar, new u(this), aVar);
    }

    public final <I, O> d.b registerForActivityResult(e.b bVar, d.g gVar, d.a aVar) {
        return c(bVar, new e(this, gVar), aVar);
    }

    public void registerForContextMenu(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(String[] strArr, int i7) {
        if (this.mHost == null) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to Activity"));
        }
        x0 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f2125x == null) {
            parentFragmentManager.f2117p.getClass();
            return;
        }
        parentFragmentManager.f2126y.addLast(new FragmentManager$LaunchedFragmentInfo(this.mWho, i7));
        parentFragmentManager.f2125x.a(strArr);
    }

    public final d0 requireActivity() {
        d0 activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to an activity."));
    }

    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " does not have any arguments."));
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to a context."));
    }

    @Deprecated
    public final x0 requireFragmentManager() {
        return getParentFragmentManager();
    }

    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to a host."));
    }

    public final z requireParentFragment() {
        z parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " is not attached to any Fragment or host"));
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(a.a.f("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public void restoreChildFragmentState(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.mChildFragmentManager.M(parcelable);
        x0 x0Var = this.mChildFragmentManager;
        x0Var.A = false;
        x0Var.B = false;
        x0Var.H.f1920f = false;
        x0Var.o(1);
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        if (this.mView != null) {
            r1 r1Var = this.mViewLifecycleOwner;
            r1Var.f2051e.b(this.mSavedViewRegistryState);
            this.mSavedViewRegistryState = null;
        }
        this.mCalled = false;
        onViewStateRestored(bundle);
        if (!this.mCalled) {
            throw new z1(a.a.f("Fragment ", this, " did not call through to super.onViewStateRestored()"));
        }
        if (this.mView != null) {
            this.mViewLifecycleOwner.a(androidx.lifecycle.q.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z6) {
        a().f2095r = Boolean.valueOf(z6);
    }

    public void setAllowReturnTransitionOverlap(boolean z6) {
        a().f2094q = Boolean.valueOf(z6);
    }

    public void setAnimatingAway(View view) {
        a().f2078a = view;
    }

    public void setAnimations(int i7, int i8, int i9, int i10) {
        if (this.mAnimationInfo == null && i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) {
            return;
        }
        a().f2081d = i7;
        a().f2082e = i8;
        a().f2083f = i9;
        a().f2084g = i10;
    }

    public void setAnimator(Animator animator) {
        a().f2079b = animator;
    }

    public void setArguments(Bundle bundle) {
        if (this.mFragmentManager != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle;
    }

    public void setEnterSharedElementCallback(e0.z zVar) {
        a().getClass();
    }

    public void setEnterTransition(Object obj) {
        a().f2088k = obj;
    }

    public void setExitSharedElementCallback(e0.z zVar) {
        a().getClass();
    }

    public void setExitTransition(Object obj) {
        a().f2090m = obj;
    }

    public void setFocusedView(View view) {
        a().f2097t = view;
    }

    public void setHasOptionsMenu(boolean z6) {
        if (this.mHasMenu != z6) {
            this.mHasMenu = z6;
            if (!isAdded() || isHidden()) {
                return;
            }
            ((c0) this.mHost).f1924e.supportInvalidateOptionsMenu();
        }
    }

    public void setHideReplaced(boolean z6) {
        a().f2099v = z6;
    }

    public void setInitialSavedState(Fragment$SavedState fragment$SavedState) {
        Bundle bundle;
        if (this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (fragment$SavedState == null || (bundle = fragment$SavedState.f1876a) == null) {
            bundle = null;
        }
        this.mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean z6) {
        if (this.mMenuVisible != z6) {
            this.mMenuVisible = z6;
            if (this.mHasMenu && isAdded() && !isHidden()) {
                ((c0) this.mHost).f1924e.supportInvalidateOptionsMenu();
            }
        }
    }

    public void setNextTransition(int i7) {
        if (this.mAnimationInfo == null && i7 == 0) {
            return;
        }
        a();
        this.mAnimationInfo.f2085h = i7;
    }

    public void setOnStartEnterTransitionListener(x xVar) {
        a();
        w wVar = this.mAnimationInfo;
        wVar.getClass();
        if (xVar == null) {
            return;
        }
        if (wVar.f2098u) {
            wVar.getClass();
        }
        if (xVar != null) {
            ((w0) xVar).f2100a++;
        }
    }

    public void setPopDirection(boolean z6) {
        if (this.mAnimationInfo == null) {
            return;
        }
        a().f2080c = z6;
    }

    public void setPostOnViewCreatedAlpha(float f7) {
        a().f2096s = f7;
    }

    public void setReenterTransition(Object obj) {
        a().f2091n = obj;
    }

    @Deprecated
    public void setRetainInstance(boolean z6) {
        this.mRetainInstance = z6;
        x0 x0Var = this.mFragmentManager;
        if (x0Var == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z6) {
            x0Var.H.b(this);
        } else {
            x0Var.H.c(this);
        }
    }

    public void setReturnTransition(Object obj) {
        a().f2089l = obj;
    }

    public void setSharedElementEnterTransition(Object obj) {
        a().f2092o = obj;
    }

    public void setSharedElementNames(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        a();
        w wVar = this.mAnimationInfo;
        wVar.f2086i = arrayList;
        wVar.f2087j = arrayList2;
    }

    public void setSharedElementReturnTransition(Object obj) {
        a().f2093p = obj;
    }

    @Deprecated
    public void setTargetFragment(z zVar, int i7) {
        x0 x0Var = this.mFragmentManager;
        x0 x0Var2 = zVar != null ? zVar.mFragmentManager : null;
        if (x0Var != null && x0Var2 != null && x0Var != x0Var2) {
            throw new IllegalArgumentException(a.a.f("Fragment ", zVar, " must share the same FragmentManager to be set as a target fragment"));
        }
        for (z zVar2 = zVar; zVar2 != null; zVar2 = zVar2.getTargetFragment()) {
            if (zVar2.equals(this)) {
                throw new IllegalArgumentException("Setting " + zVar + " as the target of " + this + " would create a target cycle");
            }
        }
        if (zVar == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        } else if (this.mFragmentManager == null || zVar.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = zVar;
        } else {
            this.mTargetWho = zVar.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = i7;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z6) {
        boolean z7 = false;
        if (!this.mUserVisibleHint && z6 && this.mState < 5 && this.mFragmentManager != null && isAdded() && this.mIsCreated) {
            x0 x0Var = this.mFragmentManager;
            d1 f7 = x0Var.f(this);
            z zVar = f7.f1933c;
            if (zVar.mDeferStart) {
                if (x0Var.f2103b) {
                    x0Var.D = true;
                } else {
                    zVar.mDeferStart = false;
                    f7.j();
                }
            }
        }
        this.mUserVisibleHint = z6;
        if (this.mState < 5 && !z6) {
            z7 = true;
        }
        this.mDeferStart = z7;
        if (this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z6);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String str) {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            return false;
        }
        c0 c0Var = (c0) l0Var;
        c0Var.getClass();
        int i7 = e0.g.f6295b;
        if (n1.z.B0() || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) {
            return e0.c.c(c0Var.f1924e, str);
        }
        return false;
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, null);
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, Bundle bundle) {
        l0 l0Var = this.mHost;
        if (l0Var == null) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to Activity"));
        }
        Object obj = g0.g.f6423a;
        g0.a.b(l0Var.f2008b, intent, bundle);
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i7) {
        startActivityForResult(intent, i7, null);
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i7, Bundle bundle) {
        if (this.mHost == null) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to Activity"));
        }
        x0 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f2123v != null) {
            parentFragmentManager.f2126y.addLast(new FragmentManager$LaunchedFragmentInfo(this.mWho, i7));
            if (intent != null && bundle != null) {
                intent.putExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
            }
            parentFragmentManager.f2123v.a(intent);
            return;
        }
        l0 l0Var = parentFragmentManager.f2117p;
        l0Var.getClass();
        if (i7 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        Object obj = g0.g.f6423a;
        g0.a.b(l0Var.f2008b, intent, bundle);
    }

    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) {
        Intent intent2;
        if (this.mHost == null) {
            throw new IllegalStateException(a.a.f("Fragment ", this, " not attached to Activity"));
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
            Objects.toString(intentSender);
            Objects.toString(intent);
            Objects.toString(bundle);
        }
        x0 parentFragmentManager = getParentFragmentManager();
        if (parentFragmentManager.f2124w == null) {
            l0 l0Var = parentFragmentManager.f2117p;
            if (i7 != -1) {
                l0Var.getClass();
                throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
            }
            Activity activity = l0Var.f2007a;
            int i11 = e0.g.f6295b;
            e0.a.c(activity, intentSender, i7, intent, i8, i9, i10, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            } else {
                intent2 = intent;
            }
            if (Log.isLoggable("FragmentManager", 2)) {
                bundle.toString();
                intent2.toString();
                Objects.toString(this);
            }
            intent2.putExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        } else {
            intent2 = intent;
        }
        i5.a.g("intentSender", intentSender);
        IntentSenderRequest intentSenderRequest = new IntentSenderRequest(intentSender, intent2, i8, i9);
        parentFragmentManager.f2126y.addLast(new FragmentManager$LaunchedFragmentInfo(this.mWho, i7));
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
        }
        parentFragmentManager.f2124w.a(intentSenderRequest);
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo == null || !a().f2098u) {
            return;
        }
        if (this.mHost == null) {
            a().f2098u = false;
        } else if (Looper.myLooper() != this.mHost.f2009c.getLooper()) {
            this.mHost.f2009c.postAtFrontOfQueue(new r(this, 1));
        } else {
            callStartTransitionListener(true);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} (");
        sb.append(this.mWho);
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        sb.append(")");
        return sb.toString();
    }

    public void unregisterForContextMenu(View view) {
        view.setOnCreateContextMenuListener(null);
    }
}
