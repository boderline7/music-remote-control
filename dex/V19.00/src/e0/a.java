package e0;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static void a(Activity activity) {
        activity.finishAffinity();
    }

    public static void b(Activity activity, Intent intent, int i7, Bundle bundle) {
        activity.startActivityForResult(intent, i7, bundle);
    }

    public static void c(Activity activity, IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) {
        activity.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
    }
}
