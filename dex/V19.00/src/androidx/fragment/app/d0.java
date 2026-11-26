package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class d0 extends androidx.activity.o implements e0.d, e0.e {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    boolean mCreated;
    final k0 mFragments;
    boolean mResumed;
    final androidx.lifecycle.a0 mFragmentLifecycleRegistry = new androidx.lifecycle.a0(this);
    boolean mStopped = true;

    public d0() {
        androidx.appcompat.app.r rVar = (androidx.appcompat.app.r) this;
        this.mFragments = new k0(new c0(rVar));
        getSavedStateRegistry().c(FRAGMENTS_TAG, new a0(rVar));
        addOnContextAvailableListener(new b0(rVar));
    }

    public static boolean c(x0 x0Var, androidx.lifecycle.r rVar) {
        boolean z6 = false;
        for (z zVar : x0Var.f2104c.f()) {
            if (zVar != null) {
                if (zVar.getHost() != null) {
                    z6 |= c(zVar.getChildFragmentManager(), rVar);
                }
                r1 r1Var = zVar.mViewLifecycleOwner;
                if (r1Var != null) {
                    r1Var.b();
                    if (r1Var.f2050d.f2136c.a(androidx.lifecycle.r.STARTED)) {
                        zVar.mViewLifecycleOwner.f2050d.g(rVar);
                        z6 = true;
                    }
                }
                if (zVar.mLifecycleRegistry.f2136c.a(androidx.lifecycle.r.STARTED)) {
                    zVar.mLifecycleRegistry.g(rVar);
                    z6 = true;
                }
            }
        }
        return z6;
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.f2004a.f2010d.f2107f.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            f1.a.a(this).b(str2, printWriter);
        }
        this.mFragments.f2004a.f2010d.p(str, fileDescriptor, printWriter, strArr);
    }

    public x0 getSupportFragmentManager() {
        return this.mFragments.f2004a.f2010d;
    }

    @Deprecated
    public f1.a getSupportLoaderManager() {
        return f1.a.a(this);
    }

    public void markFragmentsCreated() {
        while (c(getSupportFragmentManager(), androidx.lifecycle.r.CREATED)) {
        }
    }

    @Override // androidx.activity.o, android.app.Activity
    public void onActivityResult(int i7, int i8, Intent intent) {
        this.mFragments.a();
        super.onActivityResult(i7, i8, intent);
    }

    @Deprecated
    public void onAttachFragment(z zVar) {
    }

    @Override // androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mFragments.a();
        super.onConfigurationChanged(configuration);
        for (z zVar : this.mFragments.f2004a.f2010d.f2104c.f()) {
            if (zVar != null) {
                zVar.performConfigurationChanged(configuration);
            }
        }
    }

    @Override // androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_CREATE);
        y0 y0Var = this.mFragments.f2004a.f2010d;
        y0Var.A = false;
        y0Var.B = false;
        y0Var.H.f1920f = false;
        y0Var.o(1);
    }

    @Override // androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i7, Menu menu) {
        if (i7 != 0) {
            super.onCreatePanelMenu(i7, menu);
            return true;
        }
        super.onCreatePanelMenu(i7, menu);
        k0 k0Var = this.mFragments;
        return k0Var.f2004a.f2010d.i(menu, getMenuInflater()) | true;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.f2004a.f2010d.j();
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        for (z zVar : this.mFragments.f2004a.f2010d.f2104c.f()) {
            if (zVar != null) {
                zVar.performLowMemory();
            }
        }
    }

    @Override // androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i7, MenuItem menuItem) {
        if (super.onMenuItemSelected(i7, menuItem)) {
            return true;
        }
        if (i7 == 0) {
            return this.mFragments.f2004a.f2010d.k(menuItem);
        }
        if (i7 != 6) {
            return false;
        }
        return this.mFragments.f2004a.f2010d.h(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z6) {
        for (z zVar : this.mFragments.f2004a.f2010d.f2104c.f()) {
            if (zVar != null) {
                zVar.performMultiWindowModeChanged(z6);
            }
        }
    }

    @Override // androidx.activity.o, android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        this.mFragments.a();
        super.onNewIntent(intent);
    }

    @Override // androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i7, Menu menu) {
        if (i7 == 0) {
            this.mFragments.f2004a.f2010d.l(menu);
        }
        super.onPanelClosed(i7, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.f2004a.f2010d.o(5);
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z6) {
        for (z zVar : this.mFragments.f2004a.f2010d.f2104c.f()) {
            if (zVar != null) {
                zVar.performPictureInPictureModeChanged(z6);
            }
        }
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Deprecated
    public boolean onPrepareOptionsPanel(View view, Menu menu) {
        super.onPreparePanel(0, view, menu);
        return true;
    }

    @Override // androidx.activity.o, android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i7, View view, Menu menu) {
        if (i7 == 0) {
            return onPrepareOptionsPanel(view, menu) | this.mFragments.f2004a.f2010d.n(menu);
        }
        super.onPreparePanel(i7, view, menu);
        return true;
    }

    @Override // androidx.activity.o, android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        this.mFragments.a();
        super.onRequestPermissionsResult(i7, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        this.mFragments.a();
        super.onResume();
        this.mResumed = true;
        this.mFragments.f2004a.f2010d.s(true);
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_RESUME);
        y0 y0Var = this.mFragments.f2004a.f2010d;
        y0Var.A = false;
        y0Var.B = false;
        y0Var.H.f1920f = false;
        y0Var.o(7);
    }

    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.a();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            y0 y0Var = this.mFragments.f2004a.f2010d;
            y0Var.A = false;
            y0Var.B = false;
            y0Var.H.f1920f = false;
            y0Var.o(4);
        }
        this.mFragments.f2004a.f2010d.s(true);
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_START);
        y0 y0Var2 = this.mFragments.f2004a.f2010d;
        y0Var2.A = false;
        y0Var2.B = false;
        y0Var2.H.f1920f = false;
        y0Var2.o(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        y0 y0Var = this.mFragments.f2004a.f2010d;
        y0Var.B = true;
        y0Var.H.f1920f = true;
        y0Var.o(4);
        this.mFragmentLifecycleRegistry.e(androidx.lifecycle.q.ON_STOP);
    }

    public void setEnterSharedElementCallback(e0.z zVar) {
        int i7 = e0.g.f6295b;
        e0.b.c(this, null);
    }

    public void setExitSharedElementCallback(e0.z zVar) {
        int i7 = e0.g.f6295b;
        e0.b.d(this, null);
    }

    public void startActivityFromFragment(z zVar, @SuppressLint({"UnknownNullness"}) Intent intent, int i7) {
        startActivityFromFragment(zVar, intent, i7, (Bundle) null);
    }

    public void startActivityFromFragment(z zVar, @SuppressLint({"UnknownNullness"}) Intent intent, int i7, Bundle bundle) {
        if (i7 != -1) {
            zVar.startActivityForResult(intent, i7, bundle);
        } else {
            int i8 = e0.g.f6295b;
            e0.a.b(this, intent, -1, bundle);
        }
    }

    @Deprecated
    public void startIntentSenderFromFragment(z zVar, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) {
        if (i7 != -1) {
            zVar.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
        } else {
            int i11 = e0.g.f6295b;
            e0.a.c(this, intentSender, i7, intent, i8, i9, i10, bundle);
        }
    }

    public void supportFinishAfterTransition() {
        int i7 = e0.g.f6295b;
        e0.b.a(this);
    }

    public abstract void supportInvalidateOptionsMenu();

    public void supportPostponeEnterTransition() {
        int i7 = e0.g.f6295b;
        e0.b.b(this);
    }

    public void supportStartPostponedEnterTransition() {
        int i7 = e0.g.f6295b;
        e0.b.e(this);
    }

    @Override // e0.e
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i7) {
    }
}
