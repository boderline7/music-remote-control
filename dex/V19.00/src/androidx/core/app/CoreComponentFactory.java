package androidx.core.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CoreComponentFactory extends AppComponentFactory {
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) {
        return super.instantiateActivity(classLoader, str, intent);
    }

    public final Application instantiateApplication(ClassLoader classLoader, String str) {
        return super.instantiateApplication(classLoader, str);
    }

    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return super.instantiateProvider(classLoader, str);
    }

    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) {
        return super.instantiateReceiver(classLoader, str, intent);
    }

    public final Service instantiateService(ClassLoader classLoader, String str, Intent intent) {
        return super.instantiateService(classLoader, str, intent);
    }
}
