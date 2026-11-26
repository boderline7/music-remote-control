package com.navi.tool.ui.extra;

import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.receiver.NaviDeviceAdminReceiver;
import com.navi.tool.utils.AppFreezeManager;
import com.navi.tool.utils.AppSuspendManager;
import com.navi.tool.utils.MegaImeHelper;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PermissionManagerActivity extends androidx.appcompat.app.r {
    private static final String ACTION_ACCESSIBILITY_SERVICE = "android.accessibilityservice.AccessibilityService";
    private static final String ACTION_NOTIFICATION_LISTENER = "android.service.notification.NotificationListenerService";
    private static final String PERM_BIND_ACCESSIBILITY = "android.permission.BIND_ACCESSIBILITY_SERVICE";
    private static final String PERM_BIND_NOTIFICATION_LISTENER = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE";
    private static final String SETTING_ACCESSIBILITY_ENABLED = "accessibility_enabled";
    private static final String SETTING_ACCESSIBILITY_SERVICES = "enabled_accessibility_services";
    private static final String SETTING_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String VIRTUAL_ACCESSIBILITY = "__accessibility_service__";
    private static final String VIRTUAL_NOTIFICATION_LISTENER = "__notification_listener__";
    private AppAdapter adapter;
    private ComponentName adminComponent;
    private Button btnImeChoose;
    private DevicePolicyManager dpm;
    private EditText etSearch;
    private View imePanel;
    private BroadcastReceiver packageReceiver;
    private RadioButton rbFreezeMode;
    private RadioButton rbFreezeOnly;
    private RadioButton rbImeMode;
    private RadioButton rbPermissionMode;
    private RadioButton rbSuspendMode;
    private RadioButton rbSuspendedOnly;
    private RadioButton rbUnfrozenOnly;
    private RadioButton rbUnsuspendedOnly;
    private RecyclerView recyclerView;
    private RadioGroup rgFreezeFilter;
    private RadioGroup rgMode;
    private RadioGroup rgSuspendFilter;
    private TextView tvEmpty;
    private TextView tvImeCurrent;
    private View viewFreezeDivider;
    private List<AppInfo> allApps = new ArrayList();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Mode currentMode = Mode.PERMISSION;
    private volatile boolean megaMode = false;
    private FreezeFilter freezeFilter = FreezeFilter.UNFROZEN_ONLY;
    private SuspendFilter suspendFilter = SuspendFilter.UNSUSPENDED_ONLY;
    private boolean freezeRestorePromptChecked = false;
    private boolean suspendRestorePromptChecked = false;

    public class AppAdapter extends androidx.recyclerview.widget.h1 {
        private List<AppInfo> apps;

        public class ViewHolder extends androidx.recyclerview.widget.j2 {
            ImageView ivIcon;
            TextView tvName;
            TextView tvPackage;

            public ViewHolder(View view) {
                super(view);
                this.ivIcon = (ImageView) view.findViewById(g4.p.iv_app_icon);
                this.tvName = (TextView) view.findViewById(g4.p.tv_app_name);
                this.tvPackage = (TextView) view.findViewById(g4.p.tv_package_name);
            }
        }

        private AppAdapter() {
            this.apps = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(AppInfo appInfo, View view) {
            if (PermissionManagerActivity.this.currentMode == Mode.FREEZE) {
                PermissionManagerActivity.this.showFreezeDialog(appInfo);
            } else if (PermissionManagerActivity.this.currentMode == Mode.SUSPEND) {
                PermissionManagerActivity.this.showSuspendDialog(appInfo);
            } else {
                PermissionManagerActivity.this.showPermissionDialog(appInfo);
            }
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.apps.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(ViewHolder viewHolder, int i7) {
            String str;
            AppInfo appInfo = this.apps.get(i7);
            if (PermissionManagerActivity.this.currentMode == Mode.FREEZE) {
                str = appInfo.isSystem ? "系统" : "用户";
                String str2 = appInfo.isFrozen ? "已冻结" : "未冻结";
                String str3 = appInfo.isSuspended ? "·已挂起" : s5.h0.FRAGMENT_ENCODE_SET;
                viewHolder.tvName.setText(appInfo.name + "  [" + str + "·" + str2 + str3 + "]");
            } else if (PermissionManagerActivity.this.currentMode == Mode.SUSPEND) {
                str = appInfo.isSystem ? "系统" : "用户";
                String str4 = appInfo.isSuspended ? "已挂起" : "未挂起";
                viewHolder.tvName.setText(appInfo.name + "  [" + str + "·" + str4 + "]");
            } else {
                viewHolder.tvName.setText(appInfo.name);
            }
            viewHolder.tvPackage.setText(appInfo.packageName);
            viewHolder.ivIcon.setImageDrawable(appInfo.icon);
            viewHolder.itemView.setOnClickListener(new d1(3, this, appInfo));
        }

        @Override // androidx.recyclerview.widget.h1
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_permission_app, viewGroup, false));
        }

        public void setApps(List<AppInfo> list) {
            this.apps = list;
            notifyDataSetChanged();
        }
    }

    public static class AppInfo {
        Drawable icon;
        boolean isFrozen;
        boolean isSuspended;
        boolean isSystem;
        String name;
        String packageName;

        private AppInfo() {
        }
    }

    public enum FreezeFilter {
        FROZEN_ONLY,
        UNFROZEN_ONLY
    }

    public enum Mode {
        PERMISSION,
        FREEZE,
        SUSPEND,
        IME
    }

    public enum SuspendFilter {
        SUSPENDED_ONLY,
        UNSUSPENDED_ONLY
    }

    private SwitchMaterial addPermissionSwitch(LinearLayout linearLayout, String str, String str2) {
        String str3;
        if (VIRTUAL_ACCESSIBILITY.equals(str2) || VIRTUAL_NOTIFICATION_LISTENER.equals(str2)) {
            return addVirtualSwitch(linearLayout, str, str2);
        }
        int i7 = 0;
        try {
            PermissionInfo permissionInfo = getPackageManager().getPermissionInfo(str2, 0);
            CharSequence loadLabel = permissionInfo.loadLabel(getPackageManager());
            str3 = loadLabel != null ? loadLabel.toString() : str2;
            if (str3.contains("android.permission.") && (str3 = getPermissionNameCN(str2)) == null) {
                return null;
            }
            int i8 = permissionInfo.protectionLevel;
            if ((i8 & 15) != 1 && (i8 & 64) == 0) {
                if (getOpCode(str2) == -1) {
                    return null;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            if (getOpCode(str2) == -1) {
                return null;
            }
            String permissionNameCN = getPermissionNameCN(str2);
            str3 = permissionNameCN != null ? permissionNameCN : str2;
        }
        SwitchMaterial switchMaterial = new SwitchMaterial(this);
        switchMaterial.setText(str3);
        switchMaterial.setPadding(0, 16, 0, 16);
        switchMaterial.setTextSize(16.0f);
        switchMaterial.setChecked(isPermissionEffectivelyGranted(str, str2));
        switchMaterial.setOnCheckedChangeListener(new c5(this, str, str2, i7));
        linearLayout.addView(switchMaterial);
        return switchMaterial;
    }

    private SwitchMaterial addVirtualSwitch(LinearLayout linearLayout, String str, String str2) {
        SwitchMaterial switchMaterial = new SwitchMaterial(this);
        switchMaterial.setText(VIRTUAL_ACCESSIBILITY.equals(str2) ? "无障碍服务" : "通知使用权");
        switchMaterial.setPadding(0, 16, 0, 16);
        switchMaterial.setTextSize(16.0f);
        switchMaterial.setChecked(isPermissionEffectivelyGranted(str, str2));
        switchMaterial.setOnCheckedChangeListener(new c5(this, str, str2, 1));
        linearLayout.addView(switchMaterial);
        return switchMaterial;
    }

    private void applyInputMethod(String str, String str2) {
        DevicePolicyManager devicePolicyManager = this.dpm;
        if (devicePolicyManager != null && devicePolicyManager.isDeviceOwnerApp(getPackageName())) {
            try {
                this.dpm.setSecureSetting(this.adminComponent, "default_input_method", str);
            } catch (Exception e7) {
                ToastHelper.show(this, "切换失败：" + e7.getMessage());
                return;
            }
        } else if (!this.megaMode || !MegaImeHelper.apply(this, str)) {
            ToastHelper.show(this, "切换失败：需要 WRITE_SECURE_SETTINGS 权限");
            return;
        }
        ToastHelper.show(this, "已切换输入法：" + str2);
        refreshImePanel();
    }

    private void applyModeUi() {
        Mode mode = this.currentMode;
        boolean z6 = mode == Mode.IME;
        boolean z7 = mode == Mode.FREEZE;
        boolean z8 = mode == Mode.SUSPEND;
        this.etSearch.setVisibility(z6 ? 8 : 0);
        this.viewFreezeDivider.setVisibility((z7 || z8) ? 0 : 8);
        this.rgFreezeFilter.setVisibility(z7 ? 0 : 8);
        this.rgSuspendFilter.setVisibility(z8 ? 0 : 8);
        this.recyclerView.setVisibility(z6 ? 8 : 0);
        if (z6) {
            this.tvEmpty.setVisibility(8);
        }
        this.imePanel.setVisibility(z6 ? 0 : 8);
        if (z7) {
            this.etSearch.setHint("搜索应用(冻结)...");
        } else if (z8) {
            this.etSearch.setHint("搜索应用(挂起)...");
        } else {
            this.etSearch.setHint("搜索应用...");
        }
    }

    private boolean applyPermissionState(String str, String str2, boolean z6) {
        boolean z7;
        if (VIRTUAL_ACCESSIBILITY.equals(str2) || VIRTUAL_NOTIFICATION_LISTENER.equals(str2)) {
            return setVirtualGranted(str, str2, z6) && isVirtualGranted(str, str2) == z6;
        }
        try {
            this.dpm.setPermissionGrantState(this.adminComponent, str, str2, z6 ? 1 : 2);
            z7 = true;
        } catch (Exception unused) {
            z7 = false;
        }
        if (hasAppOpSupport(str2)) {
            trySetAppOp(str, str2, z6);
            z7 = true;
        }
        return z7 && isPermissionEffectivelyGranted(str, str2) == z6;
    }

    private void checkAndPromptFrozenMismatch() {
        if (this.freezeRestorePromptChecked) {
            return;
        }
        this.freezeRestorePromptChecked = true;
        this.executor.execute(new j5(this, 1));
    }

    private void checkAndPromptSuspendMismatch() {
        if (this.suspendRestorePromptChecked) {
            return;
        }
        this.suspendRestorePromptChecked = true;
        this.executor.execute(new j5(this, 2));
    }

    private boolean checkAppOpState(String str, String str2) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService("appops");
            int packageUid = getPackageManager().getPackageUid(str, 0);
            int opCode = getOpCode(str2);
            if (opCode != -1) {
                try {
                    Class<?> cls = appOpsManager.getClass();
                    Class<?> cls2 = Integer.TYPE;
                    if (((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(opCode), Integer.valueOf(packageUid), str)).intValue() == 0) {
                        return true;
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
            String permissionToOp = AppOpsManager.permissionToOp(str2);
            if (permissionToOp != null) {
                if (appOpsManager.checkOpNoThrow(permissionToOp, packageUid, str) == 0) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static boolean declaresA11yOrNotifService(PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr;
        String str;
        if (packageInfo != null && (serviceInfoArr = packageInfo.services) != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo != null && (str = serviceInfo.permission) != null && (PERM_BIND_ACCESSIBILITY.equals(str) || PERM_BIND_NOTIFICATION_LISTENER.equals(serviceInfo.permission))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void enterMegaUi() {
        this.megaMode = true;
        initViews();
        loadApps();
        RadioButton radioButton = this.rbFreezeMode;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
        RadioButton radioButton2 = this.rbSuspendMode;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
        View view = this.viewFreezeDivider;
        if (view != null) {
            view.setVisibility(8);
        }
        RadioGroup radioGroup = this.rgFreezeFilter;
        if (radioGroup != null) {
            radioGroup.setVisibility(8);
        }
        RadioGroup radioGroup2 = this.rgSuspendFilter;
        if (radioGroup2 != null) {
            radioGroup2.setVisibility(8);
        }
        RadioButton radioButton3 = this.rbPermissionMode;
        if (radioButton3 != null) {
            radioButton3.setChecked(true);
        }
        this.currentMode = Mode.PERMISSION;
        applyModeUi();
        invalidateOptionsMenu();
    }

    private void executeFreezeAction(AppInfo appInfo, boolean z6) {
        this.executor.execute(new k5(this, appInfo, z6, 1));
    }

    private void executeSuspendAction(AppInfo appInfo, boolean z6) {
        this.executor.execute(new k5(this, appInfo, z6, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void filterApps(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null || str.isEmpty()) {
            arrayList.addAll(this.allApps);
        } else {
            String lowerCase = str.toLowerCase();
            for (AppInfo appInfo : this.allApps) {
                if (appInfo.name.toLowerCase().contains(lowerCase) || appInfo.packageName.toLowerCase().contains(lowerCase)) {
                    arrayList.add(appInfo);
                }
            }
        }
        this.adapter.setApps(arrayList);
        if (!arrayList.isEmpty()) {
            this.tvEmpty.setVisibility(8);
        } else {
            this.tvEmpty.setVisibility(0);
            this.tvEmpty.setText("没有找到相关应用");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int getOpCode(String str) {
        char c7;
        str.getClass();
        switch (str.hashCode()) {
            case -2078357533:
                if (str.equals("android.permission.WRITE_SETTINGS")) {
                    c7 = 0;
                    break;
                }
                c7 = 65535;
                break;
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c7 = 1;
                    break;
                }
                c7 = 65535;
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c7 = 2;
                    break;
                }
                c7 = 65535;
                break;
            case -1925850455:
                if (str.equals("android.permission.POST_NOTIFICATIONS")) {
                    c7 = 3;
                    break;
                }
                c7 = 65535;
                break;
            case -1921431796:
                if (str.equals("android.permission.READ_CALL_LOG")) {
                    c7 = 4;
                    break;
                }
                c7 = 65535;
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c7 = 5;
                    break;
                }
                c7 = 65535;
                break;
            case -1813079487:
                if (str.equals("android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    c7 = 6;
                    break;
                }
                c7 = 65535;
                break;
            case -1561629405:
                if (str.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                    c7 = 7;
                    break;
                }
                c7 = 65535;
                break;
            case -1147355431:
                if (str.equals("android.permission.FOREGROUND_SERVICE")) {
                    c7 = '\b';
                    break;
                }
                c7 = 65535;
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c7 = '\t';
                    break;
                }
                c7 = 65535;
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c7 = '\n';
                    break;
                }
                c7 = 65535;
                break;
            case -162862488:
                if (str.equals("android.permission.PACKAGE_USAGE_STATS")) {
                    c7 = 11;
                    break;
                }
                c7 = 65535;
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c7 = '\f';
                    break;
                }
                c7 = 65535;
                break;
            case 169397625:
                if (str.equals("android.permission.WRITE_SMS")) {
                    c7 = '\r';
                    break;
                }
                c7 = 65535;
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c7 = 14;
                    break;
                }
                c7 = 65535;
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c7 = 15;
                    break;
                }
                c7 = 65535;
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c7 = 16;
                    break;
                }
                c7 = 65535;
                break;
            case 610633091:
                if (str.equals("android.permission.WRITE_CALL_LOG")) {
                    c7 = 17;
                    break;
                }
                c7 = 65535;
                break;
            case 1353874541:
                if (str.equals("android.permission.ACCESS_NOTIFICATIONS")) {
                    c7 = 18;
                    break;
                }
                c7 = 65535;
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c7 = 19;
                    break;
                }
                c7 = 65535;
                break;
            case 1777263169:
                if (str.equals("android.permission.REQUEST_INSTALL_PACKAGES")) {
                    c7 = 20;
                    break;
                }
                c7 = 65535;
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c7 = 21;
                    break;
                }
                c7 = 65535;
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c7 = 22;
                    break;
                }
                c7 = 65535;
                break;
            default:
                c7 = 65535;
                break;
        }
        switch (c7) {
            case 0:
                return 23;
            case 1:
                return 14;
            case 2:
                return 8;
            case 3:
                return 11;
            case 4:
                return 6;
            case 5:
                return 1;
            case 6:
                return 92;
            case 7:
                return 24;
            case '\b':
                return 76;
            case '\t':
                return 16;
            case '\n':
                return 59;
            case 11:
                return 43;
            case '\f':
                return 0;
            case '\r':
                return 15;
            case 14:
                return 5;
            case 15:
                return 26;
            case 16:
                return 9;
            case 17:
                return 7;
            case 18:
                return 11;
            case 19:
                return 60;
            case 20:
                return 66;
            case 21:
                return 27;
            case 22:
                return 4;
            default:
                try {
                    Object invoke = AppOpsManager.class.getMethod("permissionToOpCode", String.class).invoke(null, str);
                    if (invoke != null) {
                        return ((Integer) invoke).intValue();
                    }
                } catch (Exception unused) {
                }
                try {
                    String permissionToOp = AppOpsManager.permissionToOp(str);
                    if (permissionToOp != null) {
                        Object invoke2 = AppOpsManager.class.getMethod("strOpToOp", String.class).invoke(null, permissionToOp);
                        if (invoke2 instanceof Integer) {
                            return ((Integer) invoke2).intValue();
                        }
                    }
                } catch (Exception unused2) {
                }
                return -1;
        }
    }

    private String getPermissionNameCN(String str) {
        str.getClass();
        switch (str) {
            case "android.permission.WRITE_SETTINGS":
                return "修改系统设置";
            case "android.permission.READ_SMS":
                return "读取短信";
            case "android.permission.READ_CALENDAR":
                return "读取日历";
            case "android.permission.POST_NOTIFICATIONS":
                return "发送通知";
            case "android.permission.READ_CALL_LOG":
                return "读取通话记录";
            case "android.permission.ACCESS_FINE_LOCATION":
                return "精确位置信息 (GPS)";
            case "android.permission.MANAGE_EXTERNAL_STORAGE":
                return "所有文件访问权限";
            case "android.permission.SYSTEM_ALERT_WINDOW":
                return "显示在其他应用上层 (悬浮窗)";
            case "android.permission.FOREGROUND_SERVICE":
                return "前台服务";
            case "android.permission.RECEIVE_SMS":
                return "接收短信";
            case "android.permission.READ_EXTERNAL_STORAGE":
                return "读取存储卡内容";
            case "android.permission.PACKAGE_USAGE_STATS":
                return "查看应用使用情况";
            case "android.permission.ACCESS_COARSE_LOCATION":
                return "大致位置信息 (基站/Wi-Fi)";
            case "android.permission.READ_PHONE_STATE":
                return "读取手机状态和身份";
            case "android.permission.WRITE_SMS":
                return "发送/编辑短信";
            case "android.permission.WRITE_CONTACTS":
                return "修改联系人";
            case "android.permission.CAMERA":
                return "拍摄照片和录制视频";
            case "android.permission.WRITE_CALENDAR":
                return "修改日历";
            case "android.permission.WRITE_CALL_LOG":
                return "修改通话记录";
            case "android.permission.GET_ACCOUNTS":
                return "查找设备上的账号";
            case "android.permission.ACCESS_NOTIFICATIONS":
                return "通知使用权";
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                return "修改/删除存储卡内容";
            case "android.permission.REQUEST_INSTALL_PACKAGES":
                return "安装未知应用";
            case "android.permission.RECORD_AUDIO":
                return "录制音频";
            case "android.permission.READ_CONTACTS":
                return "读取联系人";
            case "android.permission.ACCESS_BACKGROUND_LOCATION":
                return "后台定位";
            default:
                return null;
        }
    }

    public static boolean grantAccessibility(Context context, String str) {
        if (context == null || str == null || str.isEmpty() || context.checkSelfPermission("android.permission.WRITE_SECURE_SETTINGS") != 0) {
            return false;
        }
        try {
            ArrayList arrayList = new ArrayList();
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(new Intent(ACTION_ACCESSIBILITY_SERVICE), 0);
            if (queryIntentServices != null) {
                Iterator<ResolveInfo> it = queryIntentServices.iterator();
                while (it.hasNext()) {
                    ServiceInfo serviceInfo = it.next().serviceInfo;
                    if (serviceInfo != null && str.equals(serviceInfo.packageName) && PERM_BIND_ACCESSIBILITY.equals(serviceInfo.permission)) {
                        arrayList.add(new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString());
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return false;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ACCESSIBILITY_SERVICES);
            if (string != null && !string.isEmpty()) {
                for (String str2 : string.split(":")) {
                    if (!str2.isEmpty()) {
                        linkedHashSet.add(str2);
                    }
                }
            }
            linkedHashSet.addAll(arrayList);
            StringBuilder sb = new StringBuilder();
            Iterator it2 = linkedHashSet.iterator();
            while (it2.hasNext()) {
                String str3 = (String) it2.next();
                if (sb.length() > 0) {
                    sb.append(':');
                }
                sb.append(str3);
            }
            Settings.Secure.putString(context.getContentResolver(), SETTING_ACCESSIBILITY_SERVICES, sb.toString());
            if (Settings.Secure.getInt(context.getContentResolver(), SETTING_ACCESSIBILITY_ENABLED, 0) == 0) {
                Settings.Secure.putInt(context.getContentResolver(), SETTING_ACCESSIBILITY_ENABLED, 1);
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean hasAppOpSupport(String str) {
        return (getOpCode(str) == -1 && AppOpsManager.permissionToOp(str) == null) ? false : true;
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(g4.p.recycler_view_apps);
        this.recyclerView = recyclerView;
        final int i7 = 1;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        AppAdapter appAdapter = new AppAdapter();
        this.adapter = appAdapter;
        this.recyclerView.setAdapter(appAdapter);
        this.tvEmpty = (TextView) findViewById(g4.p.tv_empty);
        this.etSearch = (EditText) findViewById(g4.p.et_search);
        this.rgMode = (RadioGroup) findViewById(g4.p.rg_mode);
        this.rbPermissionMode = (RadioButton) findViewById(g4.p.rb_permission_mode);
        this.rbFreezeMode = (RadioButton) findViewById(g4.p.rb_freeze_mode);
        this.rbSuspendMode = (RadioButton) findViewById(g4.p.rb_suspend_mode);
        this.rbImeMode = (RadioButton) findViewById(g4.p.rb_ime_mode);
        this.viewFreezeDivider = findViewById(g4.p.view_freeze_divider);
        this.rgFreezeFilter = (RadioGroup) findViewById(g4.p.rg_freeze_filter);
        this.rgSuspendFilter = (RadioGroup) findViewById(g4.p.rg_suspend_filter);
        this.rbFreezeOnly = (RadioButton) findViewById(g4.p.rb_freeze_only);
        this.rbUnfrozenOnly = (RadioButton) findViewById(g4.p.rb_unfrozen_only);
        this.rbSuspendedOnly = (RadioButton) findViewById(g4.p.rb_suspended_only);
        this.rbUnsuspendedOnly = (RadioButton) findViewById(g4.p.rb_unsuspended_only);
        this.imePanel = findViewById(g4.p.ime_panel);
        this.tvImeCurrent = (TextView) findViewById(g4.p.tv_ime_current);
        Button button = (Button) findViewById(g4.p.btn_ime_choose);
        this.btnImeChoose = button;
        button.setOnClickListener(new r4(this, i7));
        this.etSearch.addTextChangedListener(new TextWatcher() { // from class: com.navi.tool.ui.extra.PermissionManagerActivity.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                PermissionManagerActivity.this.filterApps(editable.toString());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
            }
        });
        final int i8 = 0;
        this.rgMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5340b;

            {
                this.f5340b = this;
            }

            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i9) {
                int i10 = i8;
                PermissionManagerActivity permissionManagerActivity = this.f5340b;
                switch (i10) {
                    case 0:
                        permissionManagerActivity.lambda$initViews$9(radioGroup, i9);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$initViews$10(radioGroup, i9);
                        break;
                    default:
                        permissionManagerActivity.lambda$initViews$11(radioGroup, i9);
                        break;
                }
            }
        });
        this.rgFreezeFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5340b;

            {
                this.f5340b = this;
            }

            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i9) {
                int i10 = i7;
                PermissionManagerActivity permissionManagerActivity = this.f5340b;
                switch (i10) {
                    case 0:
                        permissionManagerActivity.lambda$initViews$9(radioGroup, i9);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$initViews$10(radioGroup, i9);
                        break;
                    default:
                        permissionManagerActivity.lambda$initViews$11(radioGroup, i9);
                        break;
                }
            }
        });
        final int i9 = 2;
        this.rgSuspendFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5340b;

            {
                this.f5340b = this;
            }

            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i92) {
                int i10 = i9;
                PermissionManagerActivity permissionManagerActivity = this.f5340b;
                switch (i10) {
                    case 0:
                        permissionManagerActivity.lambda$initViews$9(radioGroup, i92);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$initViews$10(radioGroup, i92);
                        break;
                    default:
                        permissionManagerActivity.lambda$initViews$11(radioGroup, i92);
                        break;
                }
            }
        });
    }

    private boolean isPermissionEffectivelyGranted(String str, String str2) {
        int i7;
        if (VIRTUAL_ACCESSIBILITY.equals(str2) || VIRTUAL_NOTIFICATION_LISTENER.equals(str2)) {
            return isVirtualGranted(str, str2);
        }
        if (isSpecialAppOpPermission(str2)) {
            return checkAppOpState(str, str2);
        }
        try {
            i7 = this.dpm.getPermissionGrantState(this.adminComponent, str, str2);
        } catch (Exception unused) {
            i7 = 0;
        }
        if (i7 == 2 && isRuntimeManagedPermission(str2)) {
            return false;
        }
        if (i7 == 1 || getPackageManager().checkPermission(str2, str) == 0) {
            return true;
        }
        if (isRuntimeManagedPermission(str2) || !hasAppOpSupport(str2)) {
            return false;
        }
        return checkAppOpState(str, str2);
    }

    private boolean isPermissionSupported(String str) {
        int i7 = Build.VERSION.SDK_INT;
        return "android.permission.MANAGE_EXTERNAL_STORAGE".equals(str) ? i7 >= 30 : ("android.permission.ACCESS_BACKGROUND_LOCATION".equals(str) || "android.permission.ACTIVITY_RECOGNITION".equals(str) || "android.permission.ACCESS_MEDIA_LOCATION".equals(str)) ? i7 >= 29 : ("android.permission.POST_NOTIFICATIONS".equals(str) || "android.permission.READ_MEDIA_IMAGES".equals(str) || "android.permission.READ_MEDIA_VIDEO".equals(str) || "android.permission.READ_MEDIA_AUDIO".equals(str) || "android.permission.NEARBY_WIFI_DEVICES".equals(str) || "android.permission.BODY_SENSORS_BACKGROUND".equals(str)) ? i7 >= 33 : !("android.permission.BLUETOOTH_SCAN".equals(str) || "android.permission.BLUETOOTH_CONNECT".equals(str) || "android.permission.BLUETOOTH_ADVERTISE".equals(str)) || i7 >= 31;
    }

    private boolean isRuntimeManagedPermission(String str) {
        try {
            int i7 = getPackageManager().getPermissionInfo(str, 0).protectionLevel;
            return (i7 & 15) == 1 || (i7 & 64) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isSpecialAppOpPermission(String str) {
        str.getClass();
        switch (str) {
            case "android.permission.WRITE_SETTINGS":
            case "android.permission.MANAGE_EXTERNAL_STORAGE":
            case "android.permission.SYSTEM_ALERT_WINDOW":
            case "android.permission.PACKAGE_USAGE_STATS":
            case "android.permission.ACCESS_NOTIFICATIONS":
            case "android.permission.REQUEST_INSTALL_PACKAGES":
                return true;
            default:
                return false;
        }
    }

    private boolean isVirtualGranted(String str, String str2) {
        List<String> virtualComponents = virtualComponents(str, str2);
        if (virtualComponents.isEmpty()) {
            return false;
        }
        try {
            String string = Settings.Secure.getString(getContentResolver(), secureSettingKeyFor(str2));
            if (string != null && !string.isEmpty()) {
                HashSet hashSet = new HashSet(Arrays.asList(string.split(":")));
                Iterator<String> it = virtualComponents.iterator();
                while (it.hasNext()) {
                    if (hashSet.contains(it.next())) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addPermissionSwitch$46(String str, String str2, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            boolean applyPermissionState = applyPermissionState(str, str2, z6);
            boolean isPermissionEffectivelyGranted = isPermissionEffectivelyGranted(str, str2);
            compoundButton.post(new v4(compoundButton, isPermissionEffectivelyGranted, 1));
            if (applyPermissionState && isPermissionEffectivelyGranted == z6) {
                return;
            }
            ToastHelper.show(this, "修改失败：系统限制或不支持该权限");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addVirtualSwitch$48(String str, String str2, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            boolean applyPermissionState = applyPermissionState(str, str2, z6);
            boolean isPermissionEffectivelyGranted = isPermissionEffectivelyGranted(str, str2);
            compoundButton.post(new v4(compoundButton, isPermissionEffectivelyGranted, 0));
            if (applyPermissionState && isPermissionEffectivelyGranted == z6) {
                return;
            }
            ToastHelper.show(this, "修改失败：需要 WRITE_SECURE_SETTINGS 权限");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptFrozenMismatch$18(int i7) {
        ToastHelper.show(this, "已恢复冻结 " + i7 + " 个应用");
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptFrozenMismatch$19(Set set) {
        runOnUiThread(new i5(this, AppFreezeManager.reapplySpecificPackages(this, set), 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptFrozenMismatch$20(Set set, DialogInterface dialogInterface, int i7) {
        this.executor.execute(new w4(1, this, set));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptFrozenMismatch$21(int i7, Set set) {
        c3.b bVar = new c3.b(this);
        bVar.C("冻结状态恢复");
        bVar.y("检测到 " + i7 + " 个历史冻结应用当前未生效，是否立即恢复冻结？");
        bVar.B("立即恢复", new g5(1, this, set));
        bVar.z("稍后", null);
        bVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptFrozenMismatch$22() {
        if (AppFreezeManager.getFrozenPackagesFromPrefs(this).isEmpty()) {
            return;
        }
        Set<String> mismatchedFrozenPackages = AppFreezeManager.getMismatchedFrozenPackages(this);
        if (mismatchedFrozenPackages.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet(mismatchedFrozenPackages);
        runOnUiThread(new d5(this, hashSet.size(), hashSet, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptSuspendMismatch$23(int i7) {
        ToastHelper.show(this, "已恢复挂起 " + i7 + " 个应用");
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptSuspendMismatch$24(Set set) {
        runOnUiThread(new i5(this, AppSuspendManager.reapplySpecificSuspendedPackages(this, set), 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptSuspendMismatch$25(Set set, DialogInterface dialogInterface, int i7) {
        this.executor.execute(new w4(0, this, set));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptSuspendMismatch$26(int i7, Set set) {
        c3.b bVar = new c3.b(this);
        bVar.C("挂起状态恢复");
        bVar.y("检测到 " + i7 + " 个历史挂起应用当前未生效，是否立即恢复挂起？");
        bVar.B("立即恢复", new g5(0, this, set));
        bVar.z("稍后", null);
        bVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAndPromptSuspendMismatch$27() {
        if (AppSuspendManager.getSuspendedPackagesFromPrefs(this).isEmpty()) {
            return;
        }
        Set<String> mismatchedSuspendedPackages = AppSuspendManager.getMismatchedSuspendedPackages(this);
        if (mismatchedSuspendedPackages.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet(mismatchedSuspendedPackages);
        runOnUiThread(new d5(this, hashSet.size(), hashSet, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeFreezeAction$32(boolean z6, boolean z7) {
        if (!z6) {
            ToastHelper.show(this, (z7 ? "解冻" : "冻结").concat("失败，可能是系统保护应用"));
        } else {
            ToastHelper.show(this, (z7 ? "解冻" : "冻结").concat("成功"));
            loadApps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeFreezeAction$33(AppInfo appInfo, boolean z6) {
        runOnUiThread(new u4(this, AppFreezeManager.setFrozenState(this, appInfo.packageName, !z6), z6, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeSuspendAction$38(boolean z6, boolean z7) {
        if (!z6) {
            ToastHelper.show(this, (z7 ? "恢复" : "挂起").concat("失败，可能是系统保护应用"));
        } else {
            ToastHelper.show(this, (z7 ? "恢复" : "挂起").concat("成功"));
            loadApps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeSuspendAction$39(AppInfo appInfo, boolean z6) {
        runOnUiThread(new u4(this, AppSuspendManager.setSuspendedState(this, appInfo.packageName, !z6), z6, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$10(RadioGroup radioGroup, int i7) {
        if (i7 == g4.p.rb_freeze_only) {
            this.freezeFilter = FreezeFilter.FROZEN_ONLY;
        } else {
            this.freezeFilter = FreezeFilter.UNFROZEN_ONLY;
        }
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$11(RadioGroup radioGroup, int i7) {
        if (i7 == g4.p.rb_suspended_only) {
            this.suspendFilter = SuspendFilter.SUSPENDED_ONLY;
        } else {
            this.suspendFilter = SuspendFilter.UNSUSPENDED_ONLY;
        }
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$8(View view) {
        showInputMethodPicker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$9(RadioGroup radioGroup, int i7) {
        if (i7 == g4.p.rb_freeze_mode) {
            this.currentMode = Mode.FREEZE;
        } else if (i7 == g4.p.rb_suspend_mode) {
            this.currentMode = Mode.SUSPEND;
        } else if (i7 == g4.p.rb_ime_mode) {
            this.currentMode = Mode.IME;
        } else {
            this.currentMode = Mode.PERMISSION;
        }
        applyModeUi();
        if (this.currentMode == Mode.IME) {
            refreshImePanel();
        } else {
            loadApps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadApps$15(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.name.compareToIgnoreCase(appInfo2.name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadApps$16(List list) {
        this.allApps = list;
        filterApps(this.etSearch.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$loadApps$17() {
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(8708);
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Set<String> frozenPackagesFromPrefs = AppFreezeManager.getFrozenPackagesFromPrefs(this);
        Set<String> suspendedPackagesFromPrefs = AppSuspendManager.getSuspendedPackagesFromPrefs(this);
        Iterator<PackageInfo> it = installedPackages.iterator();
        while (true) {
            boolean z6 = false;
            if (!it.hasNext()) {
                break;
            }
            PackageInfo next = it.next();
            if (!getPackageName().equals(next.packageName) && (!this.megaMode || declaresA11yOrNotifService(next))) {
                int i7 = next.applicationInfo.flags;
                if ((i7 & 1) != 0 && (i7 & 128) == 0) {
                    z6 = true;
                }
                if (this.currentMode != Mode.PERMISSION || !z6) {
                    AppInfo appInfo = new AppInfo();
                    appInfo.packageName = next.packageName;
                    appInfo.name = next.applicationInfo.loadLabel(packageManager).toString();
                    appInfo.icon = next.applicationInfo.loadIcon(packageManager);
                    appInfo.isSystem = z6;
                    Mode mode = this.currentMode;
                    if (mode == Mode.FREEZE) {
                        appInfo.isFrozen = AppFreezeManager.isFrozen(this, next.packageName);
                        appInfo.isSuspended = suspendedPackagesFromPrefs.contains(next.packageName);
                        FreezeFilter freezeFilter = this.freezeFilter;
                        if (freezeFilter != FreezeFilter.FROZEN_ONLY || appInfo.isFrozen) {
                            if (freezeFilter == FreezeFilter.UNFROZEN_ONLY && appInfo.isFrozen) {
                            }
                            arrayList.add(appInfo);
                            hashSet.add(next.packageName);
                        }
                    } else {
                        if (mode == Mode.SUSPEND) {
                            if (!AppFreezeManager.isFrozen(this, next.packageName) && !frozenPackagesFromPrefs.contains(next.packageName)) {
                                boolean contains = suspendedPackagesFromPrefs.contains(next.packageName);
                                if (!AppSuspendManager.isSuspended(this, next.packageName) || contains) {
                                    appInfo.isSuspended = contains;
                                    SuspendFilter suspendFilter = this.suspendFilter;
                                    if (suspendFilter != SuspendFilter.SUSPENDED_ONLY || contains) {
                                        if (suspendFilter == SuspendFilter.UNSUSPENDED_ONLY && contains) {
                                        }
                                    }
                                }
                            }
                        }
                        arrayList.add(appInfo);
                        hashSet.add(next.packageName);
                    }
                }
            }
        }
        Mode mode2 = this.currentMode;
        if (mode2 == Mode.FREEZE) {
            for (String str : frozenPackagesFromPrefs) {
                if (str != null && !str.isEmpty() && !hashSet.contains(str) && !getPackageName().equals(str)) {
                    AppInfo appInfo2 = new AppInfo();
                    appInfo2.packageName = str;
                    appInfo2.isFrozen = true;
                    appInfo2.isSuspended = suspendedPackagesFromPrefs.contains(str);
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 8708);
                        appInfo2.name = applicationInfo.loadLabel(packageManager).toString();
                        appInfo2.icon = applicationInfo.loadIcon(packageManager);
                        int i8 = applicationInfo.flags;
                        appInfo2.isSystem = (i8 & 1) != 0 && (i8 & 128) == 0;
                    } catch (Exception unused) {
                        appInfo2.name = str;
                        int i9 = g4.s.ic_launcher;
                        Object obj = g0.g.f6423a;
                        appInfo2.icon = g0.c.b(this, i9);
                        appInfo2.isSystem = true;
                    }
                    if (this.freezeFilter != FreezeFilter.UNFROZEN_ONLY) {
                        arrayList.add(appInfo2);
                        hashSet.add(str);
                    }
                }
            }
        } else if (mode2 == Mode.SUSPEND) {
            for (String str2 : suspendedPackagesFromPrefs) {
                if (str2 != null && !str2.isEmpty() && !hashSet.contains(str2) && !getPackageName().equals(str2) && !frozenPackagesFromPrefs.contains(str2) && !AppFreezeManager.isFrozen(this, str2)) {
                    AppInfo appInfo3 = new AppInfo();
                    appInfo3.packageName = str2;
                    appInfo3.isSuspended = true;
                    try {
                        ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo(str2, 8708);
                        appInfo3.name = applicationInfo2.loadLabel(packageManager).toString();
                        appInfo3.icon = applicationInfo2.loadIcon(packageManager);
                        int i10 = applicationInfo2.flags;
                        appInfo3.isSystem = (i10 & 1) != 0 && (i10 & 128) == 0;
                    } catch (Exception unused2) {
                        appInfo3.name = str2;
                        int i11 = g4.s.ic_launcher;
                        Object obj2 = g0.g.f6423a;
                        appInfo3.icon = g0.c.b(this, i11);
                        appInfo3.isSystem = true;
                    }
                    if (this.suspendFilter != SuspendFilter.UNSUSPENDED_ONLY) {
                        arrayList.add(appInfo3);
                        hashSet.add(str2);
                    }
                }
            }
        }
        Collections.sort(arrayList, new x(2));
        runOnUiThread(new z0(19, this, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showActivationDialog$5(DialogInterface dialogInterface, int i7) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showActivationDialog$6(DialogInterface dialogInterface, int i7) {
        tryEnterMegaMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showActivationDialog$7(DialogInterface dialogInterface, int i7) {
        recreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFreezeDialog$28(AppInfo appInfo, boolean z6, androidx.appcompat.app.o oVar, View view) {
        executeFreezeAction(appInfo, z6);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFreezeDialog$29(androidx.appcompat.app.o oVar, int i7, AppInfo appInfo, boolean z6, DialogInterface dialogInterface) {
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        if (e7 != null) {
            e7.setTextColor(i7);
        }
        if (e8 != null) {
            e8.setTextColor(i7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new o5(this, appInfo, z6, oVar, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFreezeDialog$30(AppInfo appInfo, boolean z6, int i7, androidx.appcompat.app.o oVar, View view) {
        if (!appInfo.isSystem || z6) {
            executeFreezeAction(appInfo, z6);
            oVar.dismiss();
            return;
        }
        SpannableString spannableString = new SpannableString("当前应用为系统应用，禁用后可能造成无法开机或其他严重影响，后果须自行承担。是否继续冻结？");
        spannableString.setSpan(new ForegroundColorSpan(k0.a.CATEGORY_MASK), 0, 44, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, 44, 33);
        c3.b bVar = new c3.b(this);
        bVar.C("二次确认");
        bVar.y(spannableString);
        bVar.B("确认继续", null);
        bVar.z("取消", null);
        androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new y4(i7, 0, e7, appInfo, this, z6));
        oVar.dismiss();
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showFreezeDialog$31(androidx.appcompat.app.o oVar, AppInfo appInfo, boolean z6, DialogInterface dialogInterface) {
        int i7 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i7);
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        if (e7 != null) {
            e7.setTextColor(a7);
        }
        if (e8 != null) {
            e8.setTextColor(a7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new h5(a7, 0, oVar, appInfo, this, z6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showInputMethodPicker$12(int[] iArr, DialogInterface dialogInterface, int i7) {
        iArr[0] = i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showInputMethodPicker$13(String[] strArr, int[] iArr, String[] strArr2, DialogInterface dialogInterface, int i7) {
        int i8 = iArr[0];
        applyInputMethod(strArr[i8], strArr2[i8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showInputMethodPicker$14(androidx.appcompat.app.o oVar, DialogInterface dialogInterface) {
        int i7 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i7);
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        if (e7 != null) {
            e7.setTextColor(a7);
        }
        if (e8 != null) {
            e8.setTextColor(a7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionDialog$41(List list, List list2, AppInfo appInfo, View view) {
        int i7 = 0;
        for (int i8 = 0; i8 < list.size(); i8++) {
            String str = (String) list.get(i8);
            SwitchMaterial switchMaterial = (SwitchMaterial) list2.get(i8);
            if (!switchMaterial.isChecked()) {
                boolean applyPermissionState = applyPermissionState(appInfo.packageName, str, true);
                boolean isPermissionEffectivelyGranted = isPermissionEffectivelyGranted(appInfo.packageName, str);
                switchMaterial.post(new a1(switchMaterial, isPermissionEffectivelyGranted, 2));
                if (applyPermissionState && isPermissionEffectivelyGranted) {
                    i7++;
                }
            }
        }
        ToastHelper.show(this, i7 > 0 ? "已执行全部授权" : "部分权限受系统限制，未能全部授权");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showPermissionDialog$42(androidx.appcompat.app.o oVar, List list, List list2, AppInfo appInfo, DialogInterface dialogInterface) {
        Button e7 = oVar.e(-3);
        Button e8 = oVar.e(-1);
        int i7 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i7);
        if (e7 != null) {
            e7.setTextColor(a7);
        }
        if (e8 != null) {
            e8.setTextColor(a7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new f5(this, list, list2, appInfo, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionDialog$43(List list, LinearLayout linearLayout, AppInfo appInfo, List list2, List list3) {
        if (list.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setText("此应用没有可管理的权限");
            textView.setPadding(16, 16, 16, 16);
            linearLayout.addView(textView);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            SwitchMaterial addPermissionSwitch = addPermissionSwitch(linearLayout, appInfo.packageName, str);
            if (addPermissionSwitch != null) {
                list2.add(addPermissionSwitch);
                list3.add(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionDialog$44(AppInfo appInfo, LinearLayout linearLayout, List list, List list2) {
        try {
            String[] strArr = getPackageManager().getPackageInfo(appInfo.packageName, 4096).requestedPermissions;
            ArrayList arrayList = new ArrayList();
            if (!this.megaMode && strArr != null) {
                for (String str : strArr) {
                    if (isPermissionSupported(str)) {
                        arrayList.add(str);
                    }
                }
            }
            if (!queryServiceComponents(appInfo.packageName, ACTION_ACCESSIBILITY_SERVICE, PERM_BIND_ACCESSIBILITY).isEmpty()) {
                arrayList.add(VIRTUAL_ACCESSIBILITY);
            }
            if (!queryServiceComponents(appInfo.packageName, ACTION_NOTIFICATION_LISTENER, PERM_BIND_NOTIFICATION_LISTENER).isEmpty()) {
                arrayList.add(VIRTUAL_NOTIFICATION_LISTENER);
            }
            runOnUiThread(new l5(this, arrayList, linearLayout, appInfo, list, list2));
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRemoveOwnerDialog$0(boolean z6, int i7, boolean z7, int i8, androidx.appcompat.app.o oVar) {
        String e7 = z6 ? a.a.e("已解冻", i7, "个应用") : s5.h0.FRAGMENT_ENCODE_SET;
        if (z7) {
            if (!e7.isEmpty()) {
                e7 = e7.concat("，");
            }
            e7 = e7 + "已恢复挂起" + i8 + "个应用";
        }
        if (e7.isEmpty()) {
            ToastHelper.showLong(this, "已移除管理权限，现在可以卸载应用了");
        } else {
            ToastHelper.showLong(this, e7.concat("，并移除管理权限，现在可以卸载应用了"));
        }
        oVar.dismiss();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRemoveOwnerDialog$1(Exception exc) {
        ToastHelper.show(this, "移除失败: " + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRemoveOwnerDialog$2(final boolean z6, final boolean z7, final androidx.appcompat.app.o oVar) {
        final int unfreezeFrozenPackagesFromPrefs;
        if (z6) {
            try {
                unfreezeFrozenPackagesFromPrefs = AppFreezeManager.unfreezeFrozenPackagesFromPrefs(this);
            } catch (Exception e7) {
                runOnUiThread(new z0(20, this, e7));
                return;
            }
        } else {
            unfreezeFrozenPackagesFromPrefs = 0;
        }
        final int unsuspendAllFromPrefs = z7 ? AppSuspendManager.unsuspendAllFromPrefs(this) : 0;
        this.dpm.clearDeviceOwnerApp(getPackageName());
        runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.e5
            @Override // java.lang.Runnable
            public final void run() {
                PermissionManagerActivity.this.lambda$showRemoveOwnerDialog$0(z6, unfreezeFrozenPackagesFromPrefs, z7, unsuspendAllFromPrefs, oVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRemoveOwnerDialog$3(CheckBox checkBox, CheckBox checkBox2, androidx.appcompat.app.o oVar, View view) {
        this.executor.execute(new o4.v(this, checkBox != null && checkBox.isChecked(), checkBox2 != null && checkBox2.isChecked(), oVar, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showRemoveOwnerDialog$4(androidx.appcompat.app.o oVar, CheckBox checkBox, CheckBox checkBox2, DialogInterface dialogInterface) {
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        int i7 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i7);
        if (e7 != null) {
            e7.setTextColor(a7);
        }
        if (e8 != null) {
            e8.setTextColor(a7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new f5(this, checkBox, checkBox2, oVar, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSuspendDialog$34(AppInfo appInfo, boolean z6, androidx.appcompat.app.o oVar, View view) {
        executeSuspendAction(appInfo, z6);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSuspendDialog$35(androidx.appcompat.app.o oVar, int i7, AppInfo appInfo, boolean z6, DialogInterface dialogInterface) {
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        if (e7 != null) {
            e7.setTextColor(i7);
        }
        if (e8 != null) {
            e8.setTextColor(i7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new o5(this, appInfo, z6, oVar, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSuspendDialog$36(AppInfo appInfo, boolean z6, int i7, androidx.appcompat.app.o oVar, View view) {
        if (!appInfo.isSystem || z6) {
            executeSuspendAction(appInfo, z6);
            oVar.dismiss();
            return;
        }
        SpannableString spannableString = new SpannableString("当前应用为系统应用，挂起后可能造成无法开机或其他严重影响，后果须自行承担。是否继续挂起？");
        spannableString.setSpan(new ForegroundColorSpan(k0.a.CATEGORY_MASK), 0, 44, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, 44, 33);
        c3.b bVar = new c3.b(this);
        bVar.C("二次确认");
        bVar.y(spannableString);
        bVar.B("确认继续", null);
        bVar.z("取消", null);
        androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new y4(i7, 1, e7, appInfo, this, z6));
        oVar.dismiss();
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showSuspendDialog$37(androidx.appcompat.app.o oVar, AppInfo appInfo, boolean z6, DialogInterface dialogInterface) {
        int i7 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i7);
        Button e7 = oVar.e(-1);
        Button e8 = oVar.e(-2);
        if (e7 != null) {
            e7.setTextColor(a7);
        }
        if (e8 != null) {
            e8.setTextColor(a7);
        }
        if (e7 != null) {
            e7.setOnClickListener(new h5(a7, 1, oVar, appInfo, this, z6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadApps() {
        this.tvEmpty.setVisibility(0);
        this.tvEmpty.setText("正在加载应用列表...");
        this.executor.execute(new j5(this, 0));
    }

    private List<String> queryServiceComponents(String str, String str2, String str3) {
        List<ResolveInfo> queryIntentServices;
        ArrayList arrayList = new ArrayList();
        try {
            queryIntentServices = getPackageManager().queryIntentServices(new Intent(str2), 0);
        } catch (Exception unused) {
        }
        if (queryIntentServices == null) {
            return arrayList;
        }
        Iterator<ResolveInfo> it = queryIntentServices.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo != null && str.equals(serviceInfo.packageName) && (str3 == null || str3.equals(serviceInfo.permission))) {
                arrayList.add(new ComponentName(serviceInfo.packageName, serviceInfo.name).flattenToString());
            }
        }
        return arrayList;
    }

    private void refreshImePanel() {
        String string = Settings.Secure.getString(getContentResolver(), "default_input_method");
        this.tvImeCurrent.setText("当前输入法：" + resolveImeLabel(string));
    }

    private void registerPackageReceiver() {
        this.packageReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.PermissionManagerActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                PermissionManagerActivity.this.loadApps();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.packageReceiver, intentFilter);
    }

    private String resolveImeLabel(String str) {
        List<InputMethodInfo> inputMethodList;
        if (str == null || str.isEmpty()) {
            return "未知";
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager == null || (inputMethodList = inputMethodManager.getInputMethodList()) == null) {
            return str;
        }
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
            if (str.equals(inputMethodInfo.getId())) {
                CharSequence loadLabel = inputMethodInfo.loadLabel(getPackageManager());
                return loadLabel != null ? loadLabel.toString() : str;
            }
        }
        return str;
    }

    private String secureSettingKeyFor(String str) {
        return VIRTUAL_ACCESSIBILITY.equals(str) ? SETTING_ACCESSIBILITY_SERVICES : SETTING_NOTIFICATION_LISTENERS;
    }

    private boolean setVirtualGranted(String str, String str2, boolean z6) {
        List<String> virtualComponents = virtualComponents(str, str2);
        if (virtualComponents.isEmpty() || checkSelfPermission("android.permission.WRITE_SECURE_SETTINGS") != 0) {
            return false;
        }
        try {
            String secureSettingKeyFor = secureSettingKeyFor(str2);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String string = Settings.Secure.getString(getContentResolver(), secureSettingKeyFor);
            if (string != null && !string.isEmpty()) {
                for (String str3 : string.split(":")) {
                    if (!str3.isEmpty()) {
                        linkedHashSet.add(str3);
                    }
                }
            }
            if (z6) {
                linkedHashSet.addAll(virtualComponents);
            } else {
                linkedHashSet.removeAll(virtualComponents);
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (sb.length() > 0) {
                    sb.append(':');
                }
                sb.append(str4);
            }
            Settings.Secure.putString(getContentResolver(), secureSettingKeyFor, sb.toString());
            if (VIRTUAL_ACCESSIBILITY.equals(str2) && z6) {
                Settings.Secure.putInt(getContentResolver(), SETTING_ACCESSIBILITY_ENABLED, 1);
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.navi.tool.ui.extra.n5] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.navi.tool.ui.extra.n5] */
    private void showActivationDialog() {
        c3.b bVar = new c3.b(this);
        bVar.C("未激活权限");
        bVar.y("本功能需要 Device Owner 权限才能使用。使用最新版手机助手或者电脑助手连接车机一键激活权限助手设备管理员权限");
        final int i7 = 0;
        bVar.z("退出", new DialogInterface.OnClickListener(this) { // from class: com.navi.tool.ui.extra.n5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5358b;

            {
                this.f5358b = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i8) {
                int i9 = i7;
                PermissionManagerActivity permissionManagerActivity = this.f5358b;
                switch (i9) {
                    case 0:
                        permissionManagerActivity.lambda$showActivationDialog$5(dialogInterface, i8);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$showActivationDialog$6(dialogInterface, i8);
                        break;
                    default:
                        permissionManagerActivity.lambda$showActivationDialog$7(dialogInterface, i8);
                        break;
                }
            }
        });
        final int i8 = 1;
        bVar.A("8PRO专用", new DialogInterface.OnClickListener(this) { // from class: com.navi.tool.ui.extra.n5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5358b;

            {
                this.f5358b = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i82) {
                int i9 = i8;
                PermissionManagerActivity permissionManagerActivity = this.f5358b;
                switch (i9) {
                    case 0:
                        permissionManagerActivity.lambda$showActivationDialog$5(dialogInterface, i82);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$showActivationDialog$6(dialogInterface, i82);
                        break;
                    default:
                        permissionManagerActivity.lambda$showActivationDialog$7(dialogInterface, i82);
                        break;
                }
            }
        });
        final int i9 = 2;
        bVar.B("已激活，重试", new DialogInterface.OnClickListener(this) { // from class: com.navi.tool.ui.extra.n5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ PermissionManagerActivity f5358b;

            {
                this.f5358b = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i82) {
                int i92 = i9;
                PermissionManagerActivity permissionManagerActivity = this.f5358b;
                switch (i92) {
                    case 0:
                        permissionManagerActivity.lambda$showActivationDialog$5(dialogInterface, i82);
                        break;
                    case 1:
                        permissionManagerActivity.lambda$showActivationDialog$6(dialogInterface, i82);
                        break;
                    default:
                        permissionManagerActivity.lambda$showActivationDialog$7(dialogInterface, i82);
                        break;
                }
            }
        });
        bVar.w();
        androidx.appcompat.app.o v6 = bVar.v();
        int i10 = g4.m.dialog_button_text;
        Object obj = g0.g.f6423a;
        int a7 = g0.d.a(this, i10);
        v6.e(-1).setTextColor(a7);
        v6.e(-2).setTextColor(a7);
        v6.e(-3).setTextColor(a7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFreezeDialog(AppInfo appInfo) {
        boolean isFrozen = AppFreezeManager.isFrozen(this, appInfo.packageName);
        String str = appInfo.isSystem ? "系统应用" : "用户应用";
        String str2 = isFrozen ? "已冻结" : "未冻结";
        String str3 = isFrozen ? "解冻" : "冻结";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "类型：").append((CharSequence) str).append((CharSequence) "\n状态：").append((CharSequence) str2).append((CharSequence) "\n包名：").append((CharSequence) appInfo.packageName).append((CharSequence) "\n\n");
        if (!appInfo.isSystem || isFrozen) {
            spannableStringBuilder.append((CharSequence) "确认").append((CharSequence) str3).append((CharSequence) "？");
        } else {
            SpannableString spannableString = new SpannableString("当前应用为系统应用，冻结之后可能造成无法开机或者其他影响，确认冻结？");
            spannableString.setSpan(new ForegroundColorSpan(k0.a.CATEGORY_MASK), 0, 34, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, 34, 33);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        c3.b bVar = new c3.b(this);
        bVar.C(appInfo.name);
        bVar.y(spannableStringBuilder);
        bVar.B("确认", null);
        bVar.z("取消", null);
        androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new a5(this, e7, appInfo, isFrozen, 1));
        e7.show();
    }

    private void showInputMethodPicker() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        List<InputMethodInfo> inputMethodList = inputMethodManager == null ? null : inputMethodManager.getInputMethodList();
        if (inputMethodList == null || inputMethodList.isEmpty()) {
            ToastHelper.show(this, "未发现可用输入法");
            return;
        }
        PackageManager packageManager = getPackageManager();
        String string = Settings.Secure.getString(getContentResolver(), "default_input_method");
        String[] strArr = new String[inputMethodList.size()];
        String[] strArr2 = new String[inputMethodList.size()];
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < inputMethodList.size(); i9++) {
            InputMethodInfo inputMethodInfo = inputMethodList.get(i9);
            strArr[i9] = inputMethodInfo.getId();
            CharSequence loadLabel = inputMethodInfo.loadLabel(packageManager);
            strArr2[i9] = loadLabel != null ? loadLabel.toString() : inputMethodInfo.getId();
            if (strArr[i9].equals(string)) {
                i8 = i9;
            }
        }
        int[] iArr = {i8};
        c3.b bVar = new c3.b(this);
        bVar.C("选择输入法");
        z4 z4Var = new z4(i7, iArr);
        androidx.appcompat.app.j jVar = (androidx.appcompat.app.j) bVar.f831c;
        jVar.f760p = strArr2;
        jVar.f762r = z4Var;
        jVar.f765u = i8;
        jVar.f764t = true;
        bVar.B("应用", new a(this, strArr, iArr, strArr2, 2));
        bVar.z("取消", null);
        androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new s3(this, e7, 1));
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionDialog(final AppInfo appInfo) {
        View inflate = LayoutInflater.from(this).inflate(g4.q.dialog_permission_detail, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(g4.p.layout_permissions_container);
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        c3.b bVar = new c3.b(this);
        bVar.C(appInfo.name);
        bVar.x(appInfo.icon);
        bVar.D(inflate);
        bVar.B("关闭", null);
        if (!this.megaMode) {
            bVar.A("全部授权", null);
        }
        final androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.navi.tool.ui.extra.b5
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                PermissionManagerActivity.this.lambda$showPermissionDialog$42(e7, arrayList2, arrayList, appInfo, dialogInterface);
            }
        });
        this.executor.execute(new j4(this, appInfo, linearLayout, arrayList, arrayList2, 2));
        e7.show();
    }

    private void showRemoveOwnerDialog() {
        final CheckBox checkBox;
        final CheckBox checkBox2;
        Set<String> frozenPackagesFromPrefs = AppFreezeManager.getFrozenPackagesFromPrefs(this);
        int size = frozenPackagesFromPrefs == null ? 0 : frozenPackagesFromPrefs.size();
        Set<String> suspendedPackagesFromPrefs = AppSuspendManager.getSuspendedPackagesFromPrefs(this);
        int size2 = suspendedPackagesFromPrefs == null ? 0 : suspendedPackagesFromPrefs.size();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int i7 = (int) (getResources().getDisplayMetrics().density * 16.0f);
        linearLayout.setPadding(i7, i7, i7, 0);
        TextView textView = new TextView(this);
        textView.setText("确定要移除权限助手的管理权限吗？\n\n移除后：\n1. 本功能将无法使用\n2. 您可以正常卸载本应用");
        textView.setTextSize(16.0f);
        linearLayout.addView(textView);
        if (size > 0) {
            checkBox = new CheckBox(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = (int) (getResources().getDisplayMetrics().density * 12.0f);
            checkBox.setLayoutParams(layoutParams);
            checkBox.setText("解冻已冻结应用");
            linearLayout.addView(checkBox);
        } else {
            checkBox = null;
        }
        if (size2 > 0) {
            checkBox2 = new CheckBox(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.topMargin = (int) (getResources().getDisplayMetrics().density * 8.0f);
            checkBox2.setLayoutParams(layoutParams2);
            checkBox2.setText("解除已挂起应用");
            linearLayout.addView(checkBox2);
        } else {
            checkBox2 = null;
        }
        c3.b bVar = new c3.b(this);
        bVar.C("移除权限助手");
        bVar.D(linearLayout);
        bVar.B("确认移除", null);
        bVar.z("取消", null);
        final androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.navi.tool.ui.extra.x4
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                PermissionManagerActivity.this.lambda$showRemoveOwnerDialog$4(e7, checkBox, checkBox2, dialogInterface);
            }
        });
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuspendDialog(AppInfo appInfo) {
        boolean z6 = appInfo.isSuspended || AppSuspendManager.isSuspended(this, appInfo.packageName);
        String str = appInfo.isSystem ? "系统应用" : "用户应用";
        String str2 = z6 ? "已挂起" : "未挂起";
        String str3 = z6 ? "恢复" : "挂起";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "类型：").append((CharSequence) str).append((CharSequence) "\n状态：").append((CharSequence) str2).append((CharSequence) "\n包名：").append((CharSequence) appInfo.packageName).append((CharSequence) "\n\n");
        if (!appInfo.isSystem || z6) {
            spannableStringBuilder.append((CharSequence) "确认").append((CharSequence) str3).append((CharSequence) "？");
        } else {
            SpannableString spannableString = new SpannableString("当前应用为系统应用，挂起之后可能造成功能异常或其他影响，确认挂起？");
            spannableString.setSpan(new ForegroundColorSpan(k0.a.CATEGORY_MASK), 0, 33, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(18, true), 0, 33, 33);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        c3.b bVar = new c3.b(this);
        bVar.C(appInfo.name);
        bVar.y(spannableStringBuilder);
        bVar.B("确认", null);
        bVar.z("取消", null);
        androidx.appcompat.app.o e7 = bVar.e();
        e7.setOnShowListener(new a5(this, e7, appInfo, z6, 0));
        e7.show();
    }

    private void tryEnterMegaMode() {
        if (MegaImeHelper.hasWriteSecureSettings(this)) {
            SharedPreferencesUtils.getAppPrefs(this).edit().putBoolean("mega_permission_mode", true).apply();
            enterMegaUi();
        } else {
            ToastHelper.showLong(this, "需要先执行：adb shell pm grant " + getPackageName() + " android.permission.WRITE_SECURE_SETTINGS");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x005a A[Catch: Exception -> 0x008a, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x008a, blocks: (B:3:0x0005, B:8:0x005a), top: B:2:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean trySetAppOp(java.lang.String r18, java.lang.String r19, boolean r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = 0
            java.lang.String r3 = "appops"
            java.lang.Object r3 = r1.getSystemService(r3)     // Catch: java.lang.Exception -> L8a
            android.app.AppOpsManager r3 = (android.app.AppOpsManager) r3     // Catch: java.lang.Exception -> L8a
            android.content.pm.PackageManager r4 = r17.getPackageManager()     // Catch: java.lang.Exception -> L8a
            int r4 = r4.getPackageUid(r0, r2)     // Catch: java.lang.Exception -> L8a
            r5 = 1
            r6 = r20 ^ 1
            r7 = r19
            int r8 = r1.getOpCode(r7)     // Catch: java.lang.Exception -> L8a
            r9 = -1
            java.lang.String r10 = "setMode"
            java.lang.Class<java.lang.String> r11 = java.lang.String.class
            r12 = 3
            r13 = 2
            r14 = 4
            if (r8 == r9) goto L57
            java.lang.Class r9 = r3.getClass()     // Catch: java.lang.Exception -> L57
            java.lang.Class[] r15 = new java.lang.Class[r14]     // Catch: java.lang.Exception -> L57
            java.lang.Class r16 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L57
            r15[r2] = r16     // Catch: java.lang.Exception -> L57
            r15[r5] = r16     // Catch: java.lang.Exception -> L57
            r15[r13] = r11     // Catch: java.lang.Exception -> L57
            r15[r12] = r16     // Catch: java.lang.Exception -> L57
            java.lang.reflect.Method r9 = r9.getMethod(r10, r15)     // Catch: java.lang.Exception -> L57
            java.lang.Object[] r15 = new java.lang.Object[r14]     // Catch: java.lang.Exception -> L57
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Exception -> L57
            r15[r2] = r8     // Catch: java.lang.Exception -> L57
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L57
            r15[r5] = r8     // Catch: java.lang.Exception -> L57
            r15[r13] = r0     // Catch: java.lang.Exception -> L57
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L57
            r15[r12] = r8     // Catch: java.lang.Exception -> L57
            r9.invoke(r3, r15)     // Catch: java.lang.Exception -> L57
            r8 = r5
            goto L58
        L57:
            r8 = r2
        L58:
            if (r8 != 0) goto L8c
            java.lang.String r7 = android.app.AppOpsManager.permissionToOp(r19)     // Catch: java.lang.Exception -> L8a
            if (r7 == 0) goto L8c
            java.lang.Class r9 = r3.getClass()     // Catch: java.lang.Exception -> L8c
            java.lang.Class[] r15 = new java.lang.Class[r14]     // Catch: java.lang.Exception -> L8c
            r15[r2] = r11     // Catch: java.lang.Exception -> L8c
            java.lang.Class r16 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L8c
            r15[r5] = r16     // Catch: java.lang.Exception -> L8c
            r15[r13] = r11     // Catch: java.lang.Exception -> L8c
            r15[r12] = r16     // Catch: java.lang.Exception -> L8c
            java.lang.reflect.Method r9 = r9.getMethod(r10, r15)     // Catch: java.lang.Exception -> L8c
            java.lang.Object[] r10 = new java.lang.Object[r14]     // Catch: java.lang.Exception -> L8c
            r10[r2] = r7     // Catch: java.lang.Exception -> L8c
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L8c
            r10[r5] = r2     // Catch: java.lang.Exception -> L8c
            r10[r13] = r0     // Catch: java.lang.Exception -> L8c
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L8c
            r10[r12] = r0     // Catch: java.lang.Exception -> L8c
            r9.invoke(r3, r10)     // Catch: java.lang.Exception -> L8c
            goto L8d
        L8a:
            r0 = move-exception
            goto L8e
        L8c:
            r5 = r8
        L8d:
            return r5
        L8e:
            r0.printStackTrace()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.PermissionManagerActivity.trySetAppOp(java.lang.String, java.lang.String, boolean):boolean");
    }

    private List<String> virtualComponents(String str, String str2) {
        return VIRTUAL_ACCESSIBILITY.equals(str2) ? queryServiceComponents(str, ACTION_ACCESSIBILITY_SERVICE, PERM_BIND_ACCESSIBILITY) : queryServiceComponents(str, ACTION_NOTIFICATION_LISTENER, PERM_BIND_NOTIFICATION_LISTENER);
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreate();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(g4.q.activity_permission_manager);
        setSupportActionBar((Toolbar) findViewById(g4.p.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().n(true);
        }
        this.dpm = (DevicePolicyManager) getSystemService("device_policy");
        this.adminComponent = new ComponentName(this, (Class<?>) NaviDeviceAdminReceiver.class);
        boolean isDeviceOwnerApp = this.dpm.isDeviceOwnerApp(getPackageName());
        boolean z6 = SharedPreferencesUtils.getAppPrefs(this).getBoolean("mega_permission_mode", false);
        if (isDeviceOwnerApp) {
            initViews();
            loadApps();
            checkAndPromptFrozenMismatch();
            checkAndPromptSuspendMismatch();
            registerPackageReceiver();
            return;
        }
        if (z6 && MegaImeHelper.hasWriteSecureSettings(this)) {
            enterMegaUi();
        } else {
            showActivationDialog();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.megaMode) {
            return true;
        }
        getMenuInflater().inflate(g4.r.menu_permission_manager, menu);
        return true;
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BroadcastReceiver broadcastReceiver = this.packageReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        if (menuItem.getItemId() != g4.p.action_remove_owner) {
            return super.onOptionsItemSelected(menuItem);
        }
        showRemoveOwnerDialog();
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUI();
        }
    }
}
