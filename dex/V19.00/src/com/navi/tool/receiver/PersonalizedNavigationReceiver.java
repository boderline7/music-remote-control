package com.navi.tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.t;
import com.navi.tool.service.ReturnAppService;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PersonalizedNavigationReceiver extends BroadcastReceiver {
    private static final String TAG = "PersonalizedNavReceiver";

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onReceive$0(Context context) {
        List<String> installedNavigationPackages = AppDetectionUtils.getInstalledNavigationPackages(context);
        ArrayList arrayList = new ArrayList();
        for (String str : installedNavigationPackages) {
            if (AppDetectionUtils.isAmapPackage(str) && SharedPreferencesUtils.isVoiceNavAppEnabled(context, str)) {
                arrayList.add(str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            try {
                Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
                intent.setClassName(str2, Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
                intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10031);
                context.sendBroadcast(intent);
            } catch (Throwable th) {
                th.getMessage();
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(Auto3DBroadcastReceiver.KEY_TYPE, -1);
        int intExtra2 = intent.getIntExtra(Auto3DBroadcastReceiver.EXTRA_STATE, -1);
        if (intExtra == 10019 && intExtra2 == 8 && SharedPreferencesUtils.isNavigationPersonalizedEnabled(context)) {
            if (SharedPreferencesUtils.isAmapAutoBackgroundEnabled(context)) {
                new Handler(Looper.getMainLooper()).postDelayed(new t(context, 6), SharedPreferencesUtils.getAmapAutoBackgroundDelaySeconds(context) * 1000);
            }
            Intent intent2 = new Intent(context, (Class<?>) ReturnAppService.class);
            intent2.putExtra(ReturnAppService.EXTRA_DELAY_MS, Math.max(0, SharedPreferencesUtils.getReturnAppDelaySeconds(context)) * 1000);
            context.startForegroundService(intent2);
        }
    }
}
