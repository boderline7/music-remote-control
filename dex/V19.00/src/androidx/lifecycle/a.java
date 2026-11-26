package androidx.lifecycle;

import android.app.Application;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class a extends e1 {
    private final Application application;

    public a(Application application) {
        i5.a.g("application", application);
        this.application = application;
    }

    public <T extends Application> T getApplication() {
        T t6 = (T) this.application;
        i5.a.e("null cannot be cast to non-null type T of androidx.lifecycle.AndroidViewModel.getApplication", t6);
        return t6;
    }
}
