package androidx.activity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.result.IntentSenderRequest;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h extends d.g {

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ o f630h;

    public h(androidx.fragment.app.d0 d0Var) {
        this.f630h = d0Var;
    }

    @Override // d.g
    public final void b(int i7, e.b bVar, Object obj) {
        Bundle bundle;
        o oVar = this.f630h;
        e.a b7 = bVar.b(oVar, obj);
        if (b7 != null) {
            new Handler(Looper.getMainLooper()).post(new b.d(this, i7, b7, 1));
            return;
        }
        Intent a7 = bVar.a(oVar, obj);
        if (a7.getExtras() != null && a7.getExtras().getClassLoader() == null) {
            a7.setExtrasClassLoader(oVar.getClassLoader());
        }
        if (a7.hasExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) {
            Bundle bundleExtra = a7.getBundleExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
            a7.removeExtra(e.f.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
            bundle = bundleExtra;
        } else {
            bundle = null;
        }
        if (e.d.ACTION_REQUEST_PERMISSIONS.equals(a7.getAction())) {
            String[] stringArrayExtra = a7.getStringArrayExtra(e.d.EXTRA_PERMISSIONS);
            if (stringArrayExtra == null) {
                stringArrayExtra = new String[0];
            }
            e0.g.d(oVar, stringArrayExtra, i7);
            return;
        }
        if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(a7.getAction())) {
            int i8 = e0.g.f6295b;
            e0.a.b(oVar, a7, i7, bundle);
            return;
        }
        IntentSenderRequest intentSenderRequest = (IntentSenderRequest) a7.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
        try {
            IntentSender intentSender = intentSenderRequest.f650a;
            Intent intent = intentSenderRequest.f651b;
            int i9 = intentSenderRequest.f652c;
            int i10 = intentSenderRequest.f653d;
            int i11 = e0.g.f6295b;
            e0.a.c(oVar, intentSender, i7, intent, i9, i10, 0, bundle);
        } catch (IntentSender.SendIntentException e7) {
            new Handler(Looper.getMainLooper()).post(new b.d(this, i7, e7, 2));
        }
    }
}
