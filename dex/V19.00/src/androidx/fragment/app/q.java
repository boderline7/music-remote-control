package androidx.fragment.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class q extends z implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private boolean mCreatingDialog;
    private Dialog mDialog;
    private boolean mDismissed;
    private Handler mHandler;
    private boolean mShownByMe;
    private boolean mViewDestroyed;
    private Runnable mDismissRunnable = new e0(2, this);
    private DialogInterface.OnCancelListener mOnCancelListener = new m(this);
    private DialogInterface.OnDismissListener mOnDismissListener = new n(this);
    private int mStyle = 0;
    private int mTheme = 0;
    private boolean mCancelable = true;
    private boolean mShowsDialog = true;
    private int mBackStackId = -1;
    private androidx.lifecycle.h0 mObserver = new o(this);
    private boolean mDialogCreated = false;

    @Override // androidx.fragment.app.z
    public j0 createFragmentContainer() {
        return new p(this, super.createFragmentContainer());
    }

    public final void d(boolean z6, boolean z7) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!z7) {
                if (Looper.myLooper() == this.mHandler.getLooper()) {
                    onDismiss(this.mDialog);
                } else {
                    this.mHandler.post(this.mDismissRunnable);
                }
            }
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            x0 parentFragmentManager = getParentFragmentManager();
            int i7 = this.mBackStackId;
            parentFragmentManager.getClass();
            if (i7 < 0) {
                throw new IllegalArgumentException(a.a.d("Bad id: ", i7));
            }
            parentFragmentManager.q(new v0(parentFragmentManager, i7), false);
            this.mBackStackId = -1;
            return;
        }
        x0 parentFragmentManager2 = getParentFragmentManager();
        parentFragmentManager2.getClass();
        a aVar = new a(parentFragmentManager2);
        aVar.i(this);
        if (z6) {
            aVar.e(true);
        } else {
            aVar.e(false);
        }
    }

    public void dismiss() {
        d(false, false);
    }

    public void dismissAllowingStateLoss() {
        d(true, false);
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // androidx.fragment.app.z
    public void onAttach(Context context) {
        super.onAttach(context);
        getViewLifecycleOwnerLiveData().f(this.mObserver);
        if (this.mShownByMe) {
            return;
        }
        this.mDismissed = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.z
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHandler = new Handler();
        this.mShowsDialog = this.mContainerId == 0;
        if (bundle != null) {
            this.mStyle = bundle.getInt(SAVED_STYLE, 0);
            this.mTheme = bundle.getInt(SAVED_THEME, 0);
            this.mCancelable = bundle.getBoolean(SAVED_CANCELABLE, true);
            this.mShowsDialog = bundle.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
            this.mBackStackId = bundle.getInt(SAVED_BACK_STACK_ID, -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        return new Dialog(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.z
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = true;
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            if (!this.mDismissed) {
                onDismiss(this.mDialog);
            }
            this.mDialog = null;
            this.mDialogCreated = false;
        }
    }

    @Override // androidx.fragment.app.z
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
        getViewLifecycleOwnerLiveData().g(this.mObserver);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mViewDestroyed) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        d(true, true);
    }

    public View onFindViewById(int i7) {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            return dialog.findViewById(i7);
        }
        return null;
    }

    @Override // androidx.fragment.app.z
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater onGetLayoutInflater = super.onGetLayoutInflater(bundle);
        boolean z6 = this.mShowsDialog;
        if (!z6 || this.mCreatingDialog) {
            if (Log.isLoggable("FragmentManager", 2)) {
                new StringBuilder("getting layout inflater for DialogFragment ").append(this);
            }
            return onGetLayoutInflater;
        }
        if (z6 && !this.mDialogCreated) {
            try {
                this.mCreatingDialog = true;
                Dialog onCreateDialog = onCreateDialog(bundle);
                this.mDialog = onCreateDialog;
                if (this.mShowsDialog) {
                    setupDialog(onCreateDialog, this.mStyle);
                    Context context = getContext();
                    if (context instanceof Activity) {
                        this.mDialog.setOwnerActivity((Activity) context);
                    }
                    this.mDialog.setCancelable(this.mCancelable);
                    this.mDialog.setOnCancelListener(this.mOnCancelListener);
                    this.mDialog.setOnDismissListener(this.mOnDismissListener);
                    this.mDialogCreated = true;
                } else {
                    this.mDialog = null;
                }
                this.mCreatingDialog = false;
            } catch (Throwable th) {
                this.mCreatingDialog = false;
                throw th;
            }
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
        }
        Dialog dialog = this.mDialog;
        return dialog != null ? onGetLayoutInflater.cloneInContext(dialog.getContext()) : onGetLayoutInflater;
    }

    public boolean onHasView() {
        return this.mDialogCreated;
    }

    @Override // androidx.fragment.app.z
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean(SAVED_INTERNAL_DIALOG_SHOWING, false);
            bundle.putBundle(SAVED_DIALOG_STATE_TAG, onSaveInstanceState);
        }
        int i7 = this.mStyle;
        if (i7 != 0) {
            bundle.putInt(SAVED_STYLE, i7);
        }
        int i8 = this.mTheme;
        if (i8 != 0) {
            bundle.putInt(SAVED_THEME, i8);
        }
        boolean z6 = this.mCancelable;
        if (!z6) {
            bundle.putBoolean(SAVED_CANCELABLE, z6);
        }
        boolean z7 = this.mShowsDialog;
        if (!z7) {
            bundle.putBoolean(SAVED_SHOWS_DIALOG, z7);
        }
        int i9 = this.mBackStackId;
        if (i9 != -1) {
            bundle.putInt(SAVED_BACK_STACK_ID, i9);
        }
    }

    @Override // androidx.fragment.app.z
    public void onStart() {
        super.onStart();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            this.mViewDestroyed = false;
            dialog.show();
            View decorView = this.mDialog.getWindow().getDecorView();
            i5.a.g("<this>", decorView);
            decorView.setTag(d1.a.view_tree_lifecycle_owner, this);
            decorView.setTag(e1.e.view_tree_view_model_store_owner, this);
            decorView.setTag(j1.a.view_tree_saved_state_registry_owner, this);
        }
    }

    @Override // androidx.fragment.app.z
    public void onStop() {
        super.onStop();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.z
    public void onViewStateRestored(Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    @Override // androidx.fragment.app.z
    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.performCreateView(layoutInflater, viewGroup, bundle);
        if (this.mView != null || this.mDialog == null || bundle == null || (bundle2 = bundle.getBundle(SAVED_DIALOG_STATE_TAG)) == null) {
            return;
        }
        this.mDialog.onRestoreInstanceState(bundle2);
    }

    public final Dialog requireDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void setCancelable(boolean z6) {
        this.mCancelable = z6;
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setCancelable(z6);
        }
    }

    public void setShowsDialog(boolean z6) {
        this.mShowsDialog = z6;
    }

    public void setStyle(int i7, int i8) {
        if (Log.isLoggable("FragmentManager", 2)) {
            toString();
        }
        this.mStyle = i7;
        if (i7 == 2 || i7 == 3) {
            this.mTheme = R.style.Theme.Panel;
        }
        if (i8 != 0) {
            this.mTheme = i8;
        }
    }

    public void setupDialog(Dialog dialog, int i7) {
        if (i7 != 1 && i7 != 2) {
            if (i7 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public int show(h1 h1Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        h1Var.c(0, this, str, 1);
        this.mViewDestroyed = false;
        int e7 = ((a) h1Var).e(false);
        this.mBackStackId = e7;
        return e7;
    }

    public void show(x0 x0Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        x0Var.getClass();
        a aVar = new a(x0Var);
        aVar.c(0, this, str, 1);
        aVar.e(false);
    }

    public void showNow(x0 x0Var, String str) {
        this.mDismissed = false;
        this.mShownByMe = true;
        x0Var.getClass();
        a aVar = new a(x0Var);
        aVar.c(0, this, str, 1);
        if (aVar.f1980g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        aVar.f1981h = false;
        aVar.f1910q.t(aVar, false);
    }
}
