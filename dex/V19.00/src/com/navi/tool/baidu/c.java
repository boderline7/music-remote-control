package com.navi.tool.baidu;

import android.content.ComponentName;
import android.content.ServiceConnection;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f4627a;

    public c(e eVar) {
        this.f4627a = eVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
    
        r4 = s5.h0.FRAGMENT_ENCODE_SET;
     */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            com.baidu.naviauto.imaplbs.IMapAutoAPIService r4 = com.baidu.naviauto.imaplbs.IMapAutoAPIService.Stub.asInterface(r5)
            com.navi.tool.baidu.e r5 = r3.f4627a
            r5.f4637d = r4
            android.os.Handler r4 = r5.f4636c
            com.navi.tool.baidu.b r0 = r5.f4647n
            r4.removeCallbacks(r0)
            android.os.Handler r4 = r5.f4636c
            com.navi.tool.baidu.b r0 = r5.f4648o
            r4.removeCallbacks(r0)
            com.baidu.naviauto.imaplbs.IMapAutoAPIService r4 = r5.f4637d     // Catch: java.lang.Exception -> L9d
            java.lang.String r0 = "on_sync_connect_state"
            java.lang.String r4 = r4.send(r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r0 = ""
            if (r4 == 0) goto L40
            boolean r1 = r4.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r1 == 0) goto L29
            goto L40
        L29:
            java.lang.String r1 = r4.trim()     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = "{"
            boolean r2 = r1.startsWith(r2)     // Catch: java.lang.Exception -> L9d
            if (r2 != 0) goto L3d
            java.lang.String r2 = "["
            boolean r1 = r1.startsWith(r2)     // Catch: java.lang.Exception -> L9d
            if (r1 == 0) goto L40
        L3d:
            r5.f4640g = r0     // Catch: java.lang.Exception -> L9d
            goto L45
        L40:
            if (r4 != 0) goto L43
            r4 = r0
        L43:
            r5.f4640g = r4     // Catch: java.lang.Exception -> L9d
        L45:
            java.lang.String r4 = r5.f4640g     // Catch: java.lang.Exception -> L9d
            r4.getClass()     // Catch: java.lang.Exception -> L9d
            com.baidu.naviauto.imaplbs.IMapAutoAPIService r4 = r5.f4637d     // Catch: java.lang.Exception -> L9d
            com.navi.tool.baidu.a r0 = r5.f4643j     // Catch: java.lang.Exception -> L9d
            int r1 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L9d
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L9d
            r4.setSDKEventListener(r0, r1)     // Catch: java.lang.Exception -> L9d
            r4 = 1
            r5.f4641h = r4     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_navi_status_change_listener"
            r0 = 0
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_lane_info_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_remain_info_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_speed_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_speed_limit_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_turn_info_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_road_condition_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_enlarge_info_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_location_change_listener"
            java.lang.String r1 = "1"
            r5.s(r4, r1)     // Catch: java.lang.Exception -> L9d
            java.lang.String r4 = "add_on_highway_subscribe_info_change_listener"
            r5.s(r4, r0)     // Catch: java.lang.Exception -> L9d
            android.os.Handler r4 = r5.f4636c     // Catch: java.lang.Exception -> L9d
            androidx.activity.e r0 = new androidx.activity.e     // Catch: java.lang.Exception -> L9d
            r1 = 16
            r0.<init>(r1, r5)     // Catch: java.lang.Exception -> L9d
            r4.post(r0)     // Catch: java.lang.Exception -> L9d
        L9d:
            r4 = 0
            r5.f4638e = r4
            o4.c r4 = o4.c.c()
            androidx.lifecycle.g0 r4 = r4.f7937e
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r4.i(r0)
            android.os.Handler r4 = r5.f4636c
            com.navi.tool.baidu.b r0 = r5.f4645l
            r4.removeCallbacks(r0)
            android.os.Handler r4 = r5.f4636c
            r4.post(r0)
            com.navi.tool.baidu.e.o()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.baidu.c.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        e eVar = this.f4627a;
        eVar.f4637d = null;
        eVar.f4641h = false;
        o4.c.c().f7937e.i(Boolean.FALSE);
        if (eVar.f4639f) {
            eVar.f4636c.postDelayed(eVar.f4647n, 5000L);
        }
    }
}
