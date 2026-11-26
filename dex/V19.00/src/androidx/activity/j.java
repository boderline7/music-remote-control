package androidx.activity;

import android.text.TextUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f633a;

    public j(o oVar) {
        this.f633a = oVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            super/*android.app.Activity*/.onBackPressed();
        } catch (IllegalStateException e7) {
            if (!TextUtils.equals(e7.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                throw e7;
            }
        } catch (NullPointerException e8) {
            if (!TextUtils.equals(e8.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                throw e8;
            }
        }
    }
}
