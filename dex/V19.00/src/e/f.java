package e;

import android.content.Intent;
import androidx.activity.o;
import androidx.activity.result.ActivityResult;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends b {
    public static final e Companion = new e();
    public static final String EXTRA_ACTIVITY_OPTIONS_BUNDLE = "androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE";

    @Override // e.b
    public final Intent a(o oVar, Object obj) {
        Intent intent = (Intent) obj;
        i5.a.g("context", oVar);
        i5.a.g("input", intent);
        return intent;
    }

    @Override // e.b
    public final Object c(Intent intent, int i7) {
        return new ActivityResult(intent, i7);
    }
}
