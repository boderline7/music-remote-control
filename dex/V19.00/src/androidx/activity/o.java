package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.a1;
import androidx.lifecycle.c1;
import androidx.lifecycle.h1;
import androidx.lifecycle.j1;
import androidx.lifecycle.o1;
import androidx.lifecycle.p0;
import androidx.lifecycle.p1;
import androidx.lifecycle.s0;
import androidx.lifecycle.t0;
import androidx.lifecycle.y0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class o extends e0.n implements p1, androidx.lifecycle.k, j1.j, h0, d.h {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private final d.g mActivityResultRegistry;
    private int mContentLayoutId;
    private j1 mDefaultFactory;
    private boolean mDispatchingOnMultiWindowModeChanged;
    private boolean mDispatchingOnPictureInPictureModeChanged;
    final w mFullyDrawnReporter;
    private final p0.s mMenuHostHelper;
    private final AtomicInteger mNextLocalRequestCode;
    private g0 mOnBackPressedDispatcher;
    private final CopyOnWriteArrayList<o0.a> mOnConfigurationChangedListeners;
    private final CopyOnWriteArrayList<o0.a> mOnMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<o0.a> mOnNewIntentListeners;
    private final CopyOnWriteArrayList<o0.a> mOnPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<o0.a> mOnTrimMemoryListeners;
    final m mReportFullyDrawnExecutor;
    final j1.i mSavedStateRegistryController;
    private o1 mViewModelStore;
    final c.a mContextAwareHelper = new c.a();
    private final androidx.lifecycle.a0 mLifecycleRegistry = new androidx.lifecycle.a0(this);

    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.activity.f] */
    public o() {
        int i7 = 0;
        this.mMenuHostHelper = new p0.s(new e(i7, this));
        j1.i.Companion.getClass();
        j1.i a7 = j1.h.a(this);
        this.mSavedStateRegistryController = a7;
        this.mOnBackPressedDispatcher = null;
        final androidx.fragment.app.d0 d0Var = (androidx.fragment.app.d0) this;
        n nVar = new n(d0Var);
        this.mReportFullyDrawnExecutor = nVar;
        this.mFullyDrawnReporter = new w(nVar, new h5.a() { // from class: androidx.activity.f
            @Override // h5.a
            public final Object a() {
                d0Var.reportFullyDrawn();
                return null;
            }
        });
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mActivityResultRegistry = new h(d0Var);
        this.mOnConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.mOnNewIntentListeners = new CopyOnWriteArrayList<>();
        this.mOnMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mDispatchingOnMultiWindowModeChanged = false;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        getLifecycle().a(new i(this, 1));
        getLifecycle().a(new i(this, i7));
        getLifecycle().a(new i(this, 2));
        a7.a();
        androidx.lifecycle.r rVar = ((androidx.lifecycle.a0) getLifecycle()).f2136c;
        if (rVar != androidx.lifecycle.r.INITIALIZED && rVar != androidx.lifecycle.r.CREATED) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (getSavedStateRegistry().b() == null) {
            a1 a1Var = new a1(getSavedStateRegistry(), d0Var);
            getSavedStateRegistry().c("androidx.lifecycle.internal.SavedStateHandlesProvider", a1Var);
            getLifecycle().a(new androidx.lifecycle.e(a1Var));
        }
        getSavedStateRegistry().c(ACTIVITY_RESULT_TAG, new t0(2, this));
        addOnContextAvailableListener(new c.b() { // from class: androidx.activity.g
            @Override // c.b
            public final void a(Context context) {
                o.b(d0Var);
            }
        });
    }

    public static Bundle a(o oVar) {
        oVar.getClass();
        Bundle bundle = new Bundle();
        d.g gVar = oVar.mActivityResultRegistry;
        gVar.getClass();
        HashMap hashMap = gVar.f6251b;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(hashMap.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(hashMap.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(gVar.f6253d));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) gVar.f6256g.clone());
        return bundle;
    }

    public static void b(o oVar) {
        Bundle a7 = oVar.getSavedStateRegistry().a(ACTIVITY_RESULT_TAG);
        if (a7 != null) {
            d.g gVar = oVar.mActivityResultRegistry;
            gVar.getClass();
            ArrayList<Integer> integerArrayList = a7.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = a7.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            gVar.f6253d = a7.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            Bundle bundle = a7.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            Bundle bundle2 = gVar.f6256g;
            bundle2.putAll(bundle);
            for (int i7 = 0; i7 < stringArrayList.size(); i7++) {
                String str = stringArrayList.get(i7);
                HashMap hashMap = gVar.f6251b;
                boolean containsKey = hashMap.containsKey(str);
                HashMap hashMap2 = gVar.f6250a;
                if (containsKey) {
                    Integer num = (Integer) hashMap.remove(str);
                    if (!bundle2.containsKey(str)) {
                        hashMap2.remove(num);
                    }
                }
                int intValue = integerArrayList.get(i7).intValue();
                String str2 = stringArrayList.get(i7);
                hashMap2.put(Integer.valueOf(intValue), str2);
                hashMap.put(str2, Integer.valueOf(intValue));
            }
        }
    }

    public void addMenuProvider(p0.u uVar) {
        p0.s sVar = this.mMenuHostHelper;
        sVar.f8184b.add(null);
        sVar.f8183a.run();
    }

    public void addMenuProvider(p0.u uVar, androidx.lifecycle.x xVar) {
        final p0.s sVar = this.mMenuHostHelper;
        sVar.f8184b.add(null);
        sVar.f8183a.run();
        androidx.lifecycle.s lifecycle = xVar.getLifecycle();
        HashMap hashMap = sVar.f8185c;
        p0.r rVar = (p0.r) hashMap.remove(uVar);
        if (rVar != null) {
            rVar.f8174a.b(rVar.f8175b);
            rVar.f8175b = null;
        }
        hashMap.put(uVar, new p0.r(lifecycle, new androidx.lifecycle.v() { // from class: p0.p
            @Override // androidx.lifecycle.v
            public final void a(androidx.lifecycle.x xVar2, androidx.lifecycle.q qVar) {
                androidx.lifecycle.q qVar2 = androidx.lifecycle.q.ON_DESTROY;
                s sVar2 = s.this;
                if (qVar == qVar2) {
                    sVar2.a();
                } else {
                    sVar2.getClass();
                }
            }
        }));
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(p0.u uVar, androidx.lifecycle.x xVar, final androidx.lifecycle.r rVar) {
        final p0.s sVar = this.mMenuHostHelper;
        sVar.getClass();
        androidx.lifecycle.s lifecycle = xVar.getLifecycle();
        HashMap hashMap = sVar.f8185c;
        p0.r rVar2 = (p0.r) hashMap.remove(uVar);
        if (rVar2 != null) {
            rVar2.f8174a.b(rVar2.f8175b);
            rVar2.f8175b = null;
        }
        hashMap.put(uVar, new p0.r(lifecycle, new androidx.lifecycle.v() { // from class: p0.q
            @Override // androidx.lifecycle.v
            public final void a(androidx.lifecycle.x xVar2, androidx.lifecycle.q qVar) {
                s sVar2 = s.this;
                sVar2.getClass();
                androidx.lifecycle.q.Companion.getClass();
                androidx.lifecycle.r rVar3 = rVar;
                i5.a.g("state", rVar3);
                int i7 = androidx.lifecycle.n.$EnumSwitchMapping$0[rVar3.ordinal()];
                androidx.lifecycle.q qVar2 = i7 != 1 ? i7 != 2 ? i7 != 3 ? null : androidx.lifecycle.q.ON_RESUME : androidx.lifecycle.q.ON_START : androidx.lifecycle.q.ON_CREATE;
                Runnable runnable = sVar2.f8183a;
                CopyOnWriteArrayList copyOnWriteArrayList = sVar2.f8184b;
                if (qVar == qVar2) {
                    copyOnWriteArrayList.add(null);
                    runnable.run();
                } else if (qVar == androidx.lifecycle.q.ON_DESTROY) {
                    sVar2.a();
                } else if (qVar == androidx.lifecycle.o.a(rVar3)) {
                    copyOnWriteArrayList.remove((Object) null);
                    runnable.run();
                }
            }
        }));
    }

    public final void addOnConfigurationChangedListener(o0.a aVar) {
        this.mOnConfigurationChangedListeners.add(aVar);
    }

    public final void addOnContextAvailableListener(c.b bVar) {
        c.a aVar = this.mContextAwareHelper;
        aVar.getClass();
        i5.a.g("listener", bVar);
        Context context = aVar.f3005b;
        if (context != null) {
            bVar.a(context);
        }
        aVar.f3004a.add(bVar);
    }

    public final void addOnMultiWindowModeChangedListener(o0.a aVar) {
        this.mOnMultiWindowModeChangedListeners.add(aVar);
    }

    public final void addOnNewIntentListener(o0.a aVar) {
        this.mOnNewIntentListeners.add(aVar);
    }

    public final void addOnPictureInPictureModeChangedListener(o0.a aVar) {
        this.mOnPictureInPictureModeChangedListeners.add(aVar);
    }

    public final void addOnTrimMemoryListener(o0.a aVar) {
        this.mOnTrimMemoryListeners.add(aVar);
    }

    public void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            l lVar = (l) getLastNonConfigurationInstance();
            if (lVar != null) {
                this.mViewModelStore = lVar.f635b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new o1();
            }
        }
    }

    @Override // d.h
    public final d.g getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        e1.d dVar = new e1.d(e1.a.INSTANCE);
        if (getApplication() != null) {
            dVar.b(h1.APPLICATION_KEY, getApplication());
        }
        dVar.b(y0.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        dVar.b(y0.VIEW_MODEL_STORE_OWNER_KEY, this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            dVar.b(y0.DEFAULT_ARGS_KEY, getIntent().getExtras());
        }
        return dVar;
    }

    @Override // androidx.lifecycle.k
    public j1 getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new c1(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.mDefaultFactory;
    }

    public w getFullyDrawnReporter() {
        return this.mFullyDrawnReporter;
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        l lVar = (l) getLastNonConfigurationInstance();
        if (lVar != null) {
            return lVar.f634a;
        }
        return null;
    }

    @Override // androidx.lifecycle.x
    public androidx.lifecycle.s getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.h0
    public final g0 getOnBackPressedDispatcher() {
        if (this.mOnBackPressedDispatcher == null) {
            this.mOnBackPressedDispatcher = new g0(new j(this));
            getLifecycle().a(new i(this, 3));
        }
        return this.mOnBackPressedDispatcher;
    }

    @Override // j1.j
    public final j1.g getSavedStateRegistry() {
        return this.mSavedStateRegistryController.f6950b;
    }

    @Override // androidx.lifecycle.p1
    public o1 getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        ensureViewModelStore();
        return this.mViewModelStore;
    }

    public void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        i5.a.g("<this>", decorView);
        decorView.setTag(d1.a.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        i5.a.g("<this>", decorView2);
        decorView2.setTag(e1.e.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        i5.a.g("<this>", decorView3);
        decorView3.setTag(j1.a.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        i5.a.g("<this>", decorView4);
        decorView4.setTag(i0.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        i5.a.g("<this>", decorView5);
        decorView5.setTag(i0.report_drawn, this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i7, int i8, Intent intent) {
        if (this.mActivityResultRegistry.a(i7, i8, intent)) {
            return;
        }
        super.onActivityResult(i7, i8, intent);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onBackPressed() {
        getOnBackPressedDispatcher().a();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Iterator<o0.a> it = this.mOnConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            ((m0.e) it.next()).a(configuration);
        }
    }

    @Override // e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mSavedStateRegistryController.b(bundle);
        c.a aVar = this.mContextAwareHelper;
        aVar.getClass();
        aVar.f3005b = this;
        Iterator it = aVar.f3004a.iterator();
        while (it.hasNext()) {
            ((c.b) it.next()).a(this);
        }
        super.onCreate(bundle);
        s0.Companion.getClass();
        p0.b(this);
        int i7 = this.mContentLayoutId;
        if (i7 != 0) {
            setContentView(i7);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i7, Menu menu) {
        if (i7 != 0) {
            return true;
        }
        super.onCreatePanelMenu(i7, menu);
        p0.s sVar = this.mMenuHostHelper;
        getMenuInflater();
        Iterator it = sVar.f8184b.iterator();
        if (!it.hasNext()) {
            return true;
        }
        a.a.u(it.next());
        throw null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        if (i7 != 0) {
            return false;
        }
        Iterator it = this.mMenuHostHelper.f8184b.iterator();
        if (!it.hasNext()) {
            return false;
        }
        a.a.u(it.next());
        throw null;
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z6, Configuration configuration) {
        this.mDispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z6, configuration);
            this.mDispatchingOnMultiWindowModeChanged = false;
            Iterator<o0.a> it = this.mOnMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((m0.e) it.next()).a(new a6.k0());
            }
        } catch (Throwable th) {
            this.mDispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator<o0.a> it = this.mOnNewIntentListeners.iterator();
        while (it.hasNext()) {
            ((m0.e) it.next()).a(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i7, Menu menu) {
        Iterator it = this.mMenuHostHelper.f8184b.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z6, Configuration configuration) {
        this.mDispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z6, configuration);
            this.mDispatchingOnPictureInPictureModeChanged = false;
            Iterator<o0.a> it = this.mOnPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((m0.e) it.next()).a(new a6.k0());
            }
        } catch (Throwable th) {
            this.mDispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i7, View view, Menu menu) {
        if (i7 != 0) {
            return true;
        }
        super.onPreparePanel(i7, view, menu);
        Iterator it = this.mMenuHostHelper.f8184b.iterator();
        if (!it.hasNext()) {
            return true;
        }
        a.a.u(it.next());
        throw null;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        if (this.mActivityResultRegistry.a(i7, -1, new Intent().putExtra(e.d.EXTRA_PERMISSIONS, strArr).putExtra(e.d.EXTRA_PERMISSION_GRANT_RESULTS, iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i7, strArr, iArr);
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        l lVar;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        o1 o1Var = this.mViewModelStore;
        if (o1Var == null && (lVar = (l) getLastNonConfigurationInstance()) != null) {
            o1Var = lVar.f635b;
        }
        if (o1Var == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        l lVar2 = new l();
        lVar2.f634a = onRetainCustomNonConfigurationInstance;
        lVar2.f635b = o1Var;
        return lVar2;
    }

    @Override // e0.n, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        androidx.lifecycle.s lifecycle = getLifecycle();
        if (lifecycle instanceof androidx.lifecycle.a0) {
            ((androidx.lifecycle.a0) lifecycle).g(androidx.lifecycle.r.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.c(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i7) {
        super.onTrimMemory(i7);
        Iterator<o0.a> it = this.mOnTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            ((m0.e) it.next()).a(Integer.valueOf(i7));
        }
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.f3005b;
    }

    public final <I, O> d.b registerForActivityResult(e.b bVar, d.a aVar) {
        return registerForActivityResult(bVar, this.mActivityResultRegistry, aVar);
    }

    public final <I, O> d.b registerForActivityResult(e.b bVar, d.g gVar, d.a aVar) {
        return gVar.c("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, bVar, aVar);
    }

    public void removeMenuProvider(p0.u uVar) {
        this.mMenuHostHelper.a();
    }

    public final void removeOnConfigurationChangedListener(o0.a aVar) {
        this.mOnConfigurationChangedListeners.remove(aVar);
    }

    public final void removeOnContextAvailableListener(c.b bVar) {
        c.a aVar = this.mContextAwareHelper;
        aVar.getClass();
        i5.a.g("listener", bVar);
        aVar.f3004a.remove(bVar);
    }

    public final void removeOnMultiWindowModeChangedListener(o0.a aVar) {
        this.mOnMultiWindowModeChangedListeners.remove(aVar);
    }

    public final void removeOnNewIntentListener(o0.a aVar) {
        this.mOnNewIntentListeners.remove(aVar);
    }

    public final void removeOnPictureInPictureModeChangedListener(o0.a aVar) {
        this.mOnPictureInPictureModeChangedListeners.remove(aVar);
    }

    public final void removeOnTrimMemoryListener(o0.a aVar) {
        this.mOnTrimMemoryListeners.remove(aVar);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (n1.z.E0()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            w wVar = this.mFullyDrawnReporter;
            synchronized (wVar.f654a) {
                try {
                    wVar.f655b = true;
                    Iterator it = wVar.f656c.iterator();
                    while (it.hasNext()) {
                        ((h5.a) it.next()).a();
                    }
                    wVar.f656c.clear();
                } finally {
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public abstract void setContentView(int i7);

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        initializeViewTreeOwners();
        this.mReportFullyDrawnExecutor.a(getWindow().getDecorView());
        super.setContentView(view);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i7) {
        super.startActivityForResult(intent, i7);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(Intent intent, int i7, Bundle bundle) {
        super.startActivityForResult(intent, i7, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10) {
        super.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) {
        super.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
    }
}
