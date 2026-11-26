package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.l2;
import androidx.appcompat.widget.y3;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class r extends androidx.fragment.app.d0 implements s, e0.a0, e {
    private static final String DELEGATE_TAG = "androidx:appcompat";
    private w mDelegate;
    private Resources mResources;

    public r() {
        getSavedStateRegistry().c(DELEGATE_TAG, new p(this));
        addOnContextAvailableListener(new q(this));
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d();
        j0 j0Var = (j0) getDelegate();
        j0Var.x();
        ((ViewGroup) j0Var.A.findViewById(R.id.content)).addView(view, layoutParams);
        j0Var.f780m.a(j0Var.f779l.getCallback());
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        Configuration configuration;
        j0 j0Var = (j0) getDelegate();
        j0Var.O = true;
        int i7 = j0Var.S;
        if (i7 == -100) {
            i7 = w.f867b;
        }
        int E = j0Var.E(context, i7);
        int i8 = 0;
        if (w.b(context) && w.b(context)) {
            if (!n1.z.B0()) {
                synchronized (w.f874i) {
                    try {
                        l0.g gVar = w.f868c;
                        if (gVar == null) {
                            if (w.f869d == null) {
                                w.f869d = l0.g.a(l6.f.o0(context));
                            }
                            if (!((l0.i) w.f869d.f7274a).f7275a.isEmpty()) {
                                w.f868c = w.f869d;
                            }
                        } else if (!gVar.equals(w.f869d)) {
                            l0.g gVar2 = w.f868c;
                            w.f869d = gVar2;
                            l6.f.l0(context, ((l0.i) gVar2.f7274a).f7275a.toLanguageTags());
                        }
                    } finally {
                    }
                }
            } else if (!w.f871f) {
                w.f866a.execute(new t(context, i8));
            }
        }
        l0.g p6 = j0.p(context);
        if (j0.f769k0 && (context instanceof ContextThemeWrapper)) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(j0.u(context, E, p6, null, false));
            } catch (IllegalStateException unused) {
            }
            super.attachBaseContext(context);
        }
        if (context instanceof j.f) {
            try {
                ((j.f) context).a(j0.u(context, E, p6, null, false));
            } catch (IllegalStateException unused2) {
            }
            super.attachBaseContext(context);
        }
        if (j0.f768j0) {
            int i9 = Build.VERSION.SDK_INT;
            Configuration configuration2 = new Configuration();
            configuration2.uiMode = -1;
            configuration2.fontScale = CropImageView.DEFAULT_ASPECT_RATIO;
            Configuration configuration3 = context.createConfigurationContext(configuration2).getResources().getConfiguration();
            Configuration configuration4 = context.getResources().getConfiguration();
            configuration3.uiMode = configuration4.uiMode;
            if (configuration3.equals(configuration4)) {
                configuration = null;
            } else {
                configuration = new Configuration();
                configuration.fontScale = CropImageView.DEFAULT_ASPECT_RATIO;
                if (configuration3.diff(configuration4) != 0) {
                    float f7 = configuration3.fontScale;
                    float f8 = configuration4.fontScale;
                    if (f7 != f8) {
                        configuration.fontScale = f8;
                    }
                    int i10 = configuration3.mcc;
                    int i11 = configuration4.mcc;
                    if (i10 != i11) {
                        configuration.mcc = i11;
                    }
                    int i12 = configuration3.mnc;
                    int i13 = configuration4.mnc;
                    if (i12 != i13) {
                        configuration.mnc = i13;
                    }
                    b0.a(configuration3, configuration4, configuration);
                    int i14 = configuration3.touchscreen;
                    int i15 = configuration4.touchscreen;
                    if (i14 != i15) {
                        configuration.touchscreen = i15;
                    }
                    int i16 = configuration3.keyboard;
                    int i17 = configuration4.keyboard;
                    if (i16 != i17) {
                        configuration.keyboard = i17;
                    }
                    int i18 = configuration3.keyboardHidden;
                    int i19 = configuration4.keyboardHidden;
                    if (i18 != i19) {
                        configuration.keyboardHidden = i19;
                    }
                    int i20 = configuration3.navigation;
                    int i21 = configuration4.navigation;
                    if (i20 != i21) {
                        configuration.navigation = i21;
                    }
                    int i22 = configuration3.navigationHidden;
                    int i23 = configuration4.navigationHidden;
                    if (i22 != i23) {
                        configuration.navigationHidden = i23;
                    }
                    int i24 = configuration3.orientation;
                    int i25 = configuration4.orientation;
                    if (i24 != i25) {
                        configuration.orientation = i25;
                    }
                    int i26 = configuration3.screenLayout & 15;
                    int i27 = configuration4.screenLayout & 15;
                    if (i26 != i27) {
                        configuration.screenLayout |= i27;
                    }
                    int i28 = configuration3.screenLayout & z1.c.AND_LONG_2ADDR;
                    int i29 = configuration4.screenLayout & z1.c.AND_LONG_2ADDR;
                    if (i28 != i29) {
                        configuration.screenLayout |= i29;
                    }
                    int i30 = configuration3.screenLayout & 48;
                    int i31 = configuration4.screenLayout & 48;
                    if (i30 != i31) {
                        configuration.screenLayout |= i31;
                    }
                    int i32 = configuration3.screenLayout & 768;
                    int i33 = configuration4.screenLayout & 768;
                    if (i32 != i33) {
                        configuration.screenLayout |= i33;
                    }
                    int i34 = configuration3.colorMode & 3;
                    int i35 = configuration4.colorMode & 3;
                    if (i34 != i35) {
                        configuration.colorMode |= i35;
                    }
                    int i36 = configuration3.colorMode & 12;
                    int i37 = configuration4.colorMode & 12;
                    if (i36 != i37) {
                        configuration.colorMode |= i37;
                    }
                    int i38 = configuration3.uiMode & 15;
                    int i39 = configuration4.uiMode & 15;
                    if (i38 != i39) {
                        configuration.uiMode |= i39;
                    }
                    int i40 = configuration3.uiMode & 48;
                    int i41 = configuration4.uiMode & 48;
                    if (i40 != i41) {
                        configuration.uiMode |= i41;
                    }
                    int i42 = configuration3.screenWidthDp;
                    int i43 = configuration4.screenWidthDp;
                    if (i42 != i43) {
                        configuration.screenWidthDp = i43;
                    }
                    int i44 = configuration3.screenHeightDp;
                    int i45 = configuration4.screenHeightDp;
                    if (i44 != i45) {
                        configuration.screenHeightDp = i45;
                    }
                    int i46 = configuration3.smallestScreenWidthDp;
                    int i47 = configuration4.smallestScreenWidthDp;
                    if (i46 != i47) {
                        configuration.smallestScreenWidthDp = i47;
                    }
                    int i48 = configuration3.densityDpi;
                    int i49 = configuration4.densityDpi;
                    if (i48 != i49) {
                        configuration.densityDpi = i49;
                    }
                }
            }
            Configuration u6 = j0.u(context, E, p6, configuration, true);
            j.f fVar = new j.f(context, f.i.Theme_AppCompat_Empty);
            fVar.a(u6);
            try {
                if (context.getTheme() != null) {
                    Resources.Theme theme = fVar.getTheme();
                    if (i9 >= 29) {
                        h0.p.a(theme);
                    } else {
                        synchronized (h0.o.f6639a) {
                            if (!h0.o.f6641c) {
                                try {
                                    Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                                    h0.o.f6640b = declaredMethod;
                                    declaredMethod.setAccessible(true);
                                } catch (NoSuchMethodException unused3) {
                                }
                                h0.o.f6641c = true;
                            }
                            Method method = h0.o.f6640b;
                            if (method != null) {
                                try {
                                    method.invoke(theme, new Object[0]);
                                } catch (IllegalAccessException | InvocationTargetException unused4) {
                                    h0.o.f6640b = null;
                                }
                            }
                        }
                    }
                }
            } catch (NullPointerException unused5) {
            }
            context = fVar;
        }
        super.attachBaseContext(context);
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        b supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.a()) {
                super.closeOptionsMenu();
            }
        }
    }

    public final void d() {
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
        decorView4.setTag(androidx.activity.i0.view_tree_on_back_pressed_dispatcher_owner, this);
    }

    @Override // e0.n, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        b supportActionBar = getSupportActionBar();
        if (keyCode == 82 && supportActionBar != null && supportActionBar.k(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i7) {
        j0 j0Var = (j0) getDelegate();
        j0Var.x();
        return (T) j0Var.f779l.findViewById(i7);
    }

    public w getDelegate() {
        if (this.mDelegate == null) {
            q0 q0Var = w.f866a;
            this.mDelegate = new j0(this, null, this, this);
        }
        return this.mDelegate;
    }

    @Override // androidx.appcompat.app.e
    public d getDrawerToggleDelegate() {
        j0 j0Var = (j0) getDelegate();
        j0Var.getClass();
        return new y(j0Var, 3);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        j0 j0Var = (j0) getDelegate();
        if (j0Var.f783p == null) {
            j0Var.C();
            b bVar = j0Var.f782o;
            j0Var.f783p = new j.l(bVar != null ? bVar.e() : j0Var.f778k);
        }
        return j0Var.f783p;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = this.mResources;
        if (resources == null) {
            int i7 = y3.MAX_SDK_WHERE_REQUIRED;
        }
        return resources == null ? super.getResources() : resources;
    }

    public b getSupportActionBar() {
        j0 j0Var = (j0) getDelegate();
        j0Var.C();
        return j0Var.f782o;
    }

    @Override // e0.a0
    public Intent getSupportParentActivityIntent() {
        return e0.s.a(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().a();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j0 j0Var = (j0) getDelegate();
        if (j0Var.F && j0Var.f793z) {
            j0Var.C();
            b bVar = j0Var.f782o;
            if (bVar != null) {
                bVar.h();
            }
        }
        androidx.appcompat.widget.z a7 = androidx.appcompat.widget.z.a();
        Context context = j0Var.f778k;
        synchronized (a7) {
            l2 l2Var = a7.f1494a;
            synchronized (l2Var) {
                q.e eVar = (q.e) l2Var.f1312b.get(context);
                if (eVar != null) {
                    eVar.a();
                }
            }
        }
        j0Var.R = new Configuration(j0Var.f778k.getResources().getConfiguration());
        j0Var.n(false, false);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(super.getResources().getConfiguration(), super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        onSupportContentChanged();
    }

    public void onCreateSupportNavigateUpTaskStack(e0.b0 b0Var) {
        b0Var.getClass();
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = e0.s.a(this);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            Context context = b0Var.f6292b;
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(context.getPackageManager());
            }
            ArrayList arrayList = b0Var.f6291a;
            int size = arrayList.size();
            try {
                for (Intent b7 = e0.s.b(context, component); b7 != null; b7 = e0.s.b(context, b7.getComponent())) {
                    arrayList.add(size, b7);
                }
                arrayList.add(supportParentActivityIntent);
            } catch (PackageManager.NameNotFoundException e7) {
                throw new IllegalArgumentException(e7);
            }
        }
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().d();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i7, KeyEvent keyEvent) {
        return super.onKeyDown(i7, keyEvent);
    }

    public void onLocalesChanged(l0.g gVar) {
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        b supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.d() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i7, Menu menu) {
        return super.onMenuOpened(i7, menu);
    }

    public void onNightModeChanged(int i7) {
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i7, Menu menu) {
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((j0) getDelegate()).x();
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        j0 j0Var = (j0) getDelegate();
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null) {
            bVar.s(true);
        }
    }

    public void onPrepareSupportNavigateUpTaskStack(e0.b0 b0Var) {
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onStart() {
        super.onStart();
        ((j0) getDelegate()).n(true, false);
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onStop() {
        super.onStop();
        j0 j0Var = (j0) getDelegate();
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null) {
            bVar.s(false);
        }
    }

    @Override // androidx.appcompat.app.s
    public void onSupportActionModeFinished(j.c cVar) {
    }

    @Override // androidx.appcompat.app.s
    public void onSupportActionModeStarted(j.c cVar) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (!supportShouldUpRecreateTask(supportParentActivityIntent)) {
            supportNavigateUpTo(supportParentActivityIntent);
            return true;
        }
        e0.b0 b0Var = new e0.b0(this);
        onCreateSupportNavigateUpTaskStack(b0Var);
        onPrepareSupportNavigateUpTaskStack(b0Var);
        ArrayList arrayList = b0Var.f6291a;
        if (arrayList.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[0]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        Object obj = g0.g.f6423a;
        g0.a.a(b0Var.f6292b, intentArr, null);
        try {
            int i7 = e0.g.f6295b;
            e0.a.a(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i7) {
        super.onTitleChanged(charSequence, i7);
        getDelegate().k(charSequence);
    }

    @Override // androidx.appcompat.app.s
    public j.c onWindowStartingSupportActionMode(j.b bVar) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        b supportActionBar = getSupportActionBar();
        if (getWindow().hasFeature(0)) {
            if (supportActionBar == null || !supportActionBar.l()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // androidx.activity.o, android.app.Activity
    public void setContentView(int i7) {
        d();
        getDelegate().g(i7);
    }

    @Override // androidx.activity.o, android.app.Activity
    public void setContentView(View view) {
        d();
        getDelegate().h(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        d();
        getDelegate().i(view, layoutParams);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        j0 j0Var = (j0) getDelegate();
        if (j0Var.f777j instanceof Activity) {
            j0Var.C();
            b bVar = j0Var.f782o;
            if (bVar instanceof a1) {
                throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
            }
            j0Var.f783p = null;
            if (bVar != null) {
                bVar.i();
            }
            j0Var.f782o = null;
            if (toolbar != null) {
                Object obj = j0Var.f777j;
                v0 v0Var = new v0(toolbar, obj instanceof Activity ? ((Activity) obj).getTitle() : j0Var.f784q, j0Var.f780m);
                j0Var.f782o = v0Var;
                j0Var.f780m.f702b = v0Var.f860c;
                toolbar.setBackInvokedCallbackEnabled(true);
            } else {
                j0Var.f780m.f702b = null;
            }
            j0Var.a();
        }
    }

    @Deprecated
    public void setSupportProgress(int i7) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z6) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z6) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z6) {
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i7) {
        super.setTheme(i7);
        ((j0) getDelegate()).T = i7;
    }

    public j.c startSupportActionMode(j.b bVar) {
        return getDelegate().m(bVar);
    }

    @Override // androidx.fragment.app.d0
    public void supportInvalidateOptionsMenu() {
        getDelegate().a();
    }

    public void supportNavigateUpTo(Intent intent) {
        e0.r.b(this, intent);
    }

    public boolean supportRequestWindowFeature(int i7) {
        return getDelegate().f(i7);
    }

    public boolean supportShouldUpRecreateTask(Intent intent) {
        return e0.r.c(this, intent);
    }
}
