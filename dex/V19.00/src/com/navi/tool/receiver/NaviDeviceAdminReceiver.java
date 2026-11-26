package com.navi.tool.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import com.navi.tool.utils.ToastHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NaviDeviceAdminReceiver extends DeviceAdminReceiver {
    @Override // android.app.admin.DeviceAdminReceiver
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        ToastHelper.show(context, "权限助手核心服务已停用");
    }

    @Override // android.app.admin.DeviceAdminReceiver
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        ToastHelper.show(context, "权限助手核心服务已激活");
    }
}
