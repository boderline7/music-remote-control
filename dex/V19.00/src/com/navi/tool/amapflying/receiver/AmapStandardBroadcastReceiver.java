package com.navi.tool.amapflying.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AmapStandardBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    public static String f4503a;

    public static void a(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"仪表导航\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0129  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(final android.content.Context r14, android.content.Intent r15) {
        /*
            Method dump skipped, instructions count: 750
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.amapflying.receiver.AmapStandardBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
