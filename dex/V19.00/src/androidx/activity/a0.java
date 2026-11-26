package androidx.activity;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 {
    public static final a0 INSTANCE = new a0();

    public final OnBackInvokedCallback a(h5.a aVar) {
        i5.a.g("onBackInvoked", aVar);
        return new z(0, aVar);
    }

    public final void b(Object obj, int i7, Object obj2) {
        i5.a.g("dispatcher", obj);
        i5.a.g("callback", obj2);
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i7, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        i5.a.g("dispatcher", obj);
        i5.a.g("callback", obj2);
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
