package androidx.fragment.app;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t0 extends e.b {
    @Override // e.b
    public final Intent a(androidx.activity.o oVar, Object obj) {
        Bundle bundleExtra;
        IntentSenderRequest intentSenderRequest = (IntentSenderRequest) obj;
        Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
        Intent intent2 = intentSenderRequest.f651b;
        if (intent2 != null && (bundleExtra = intent2.getBundleExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) != null) {
            intent.putExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundleExtra);
            intent2.removeExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
            if (intent2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                IntentSender intentSender = intentSenderRequest.f650a;
                i5.a.g("intentSender", intentSender);
                intentSenderRequest = new IntentSenderRequest(intentSender, null, intentSenderRequest.f652c, intentSenderRequest.f653d);
            }
        }
        intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
        if (Log.isLoggable("FragmentManager", 2)) {
            intent.toString();
        }
        return intent;
    }

    @Override // e.b
    public final Object c(Intent intent, int i7) {
        return new ActivityResult(intent, i7);
    }
}
