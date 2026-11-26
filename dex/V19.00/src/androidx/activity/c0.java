package androidx.activity;

import android.window.OnBackInvokedCallback;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 {
    public static final c0 INSTANCE = new c0();

    public final OnBackInvokedCallback a(h5.l lVar, h5.l lVar2, h5.a aVar, h5.a aVar2) {
        i5.a.g("onBackStarted", lVar);
        i5.a.g("onBackProgressed", lVar2);
        i5.a.g("onBackInvoked", aVar);
        i5.a.g("onBackCancelled", aVar2);
        return new b0(lVar, lVar2, aVar, aVar2);
    }
}
