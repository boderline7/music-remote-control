package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.support.v4.media.m;
import androidx.media.MediaBrowserServiceCompat;
import g0.e;
import g0.g;
import g1.a;
import g1.b;
import java.util.List;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    public static ComponentName a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (queryIntentServices.isEmpty()) {
            return null;
        }
        throw new IllegalStateException("Expected 1 service that handles " + str + ", found " + queryIntentServices.size());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Objects.toString(intent);
            return;
        }
        ComponentName a7 = a(context, "android.intent.action.MEDIA_BUTTON");
        if (a7 != null) {
            intent.setComponent(a7);
            try {
                Object obj = g.f6423a;
                e.b(context, intent);
                return;
            } catch (IllegalStateException e7) {
                if (Build.VERSION.SDK_INT < 31 || !a.b(e7)) {
                    throw e7;
                }
                a.a(e7).getMessage();
                return;
            }
        }
        ComponentName a8 = a(context, MediaBrowserServiceCompat.SERVICE_INTERFACE);
        if (a8 == null) {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
        BroadcastReceiver.PendingResult goAsync = goAsync();
        Context applicationContext = context.getApplicationContext();
        b bVar = new b(applicationContext, intent, goAsync);
        m mVar = new m(applicationContext, a8, bVar);
        bVar.f6430d = mVar;
        mVar.f551a.f533b.connect();
    }
}
