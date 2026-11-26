package com.navi.tool.ui.extra;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.FileAssistantActivity;
import com.navi.tool.PermissionAssistantActivity;
import com.navi.tool.model.AppInfo;
import com.navi.tool.service.GestureAccessibilityService;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.adapter.AutoStartAppAdapter;
import com.navi.tool.ui.adapter.CarSettingAdapter;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;
import com.navi.tool.ui.adapter.ScreenOnAutoStartAppAdapter;
import com.navi.tool.ui.common.DialogListDividers;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AdditionalFeaturesFragment extends androidx.fragment.app.z {
    private static final String PKG_ADB_SWITCH = "com.navi.tool.action.adbswitch";
    private MaterialCardView appPanelContainer;
    private AutoStartAppAdapter autoStartAppAdapter;
    private Button btnManageAutoStart;
    private Button btnManageCarSettings;
    private Button btnManageScreenOnAutoStart;
    private Button buttonSelectPanelApps;
    private Button buttonSettingsPanelApps;
    private d.b chooseIconLauncher;
    private View containerScreenOnAutoStart;
    private View containerStrWakeAutoStart;
    private View dividerScreenOnAutoStart;
    private View dividerStrWakeAutoStart;
    private MaterialCardView fileAssistantContainer;
    private MaterialCardView gestureActionsDetailContainer;
    private MaterialCardView gestureMirrorContainer;
    private MaterialCardView gestureTriggerContainer;
    private PanelAppSettingsAdapter panelAppSettingsAdapter;
    private String pendingIconChangePackageName;
    private MaterialCardView permissionAssistantContainer;
    private MaterialCardView permissionStatusContainer;
    private MaterialRadioButton radioDownActionBack;
    private MaterialRadioButton radioDownActionDesktop;
    private MaterialRadioButton radioDownActionHome;
    private MaterialRadioButton radioDownActionPanel;
    private RadioGroup radioGroupDownAction;
    private RadioGroup radioGroupRightAction;
    private RadioGroup radioGroupUpAction;
    private MaterialRadioButton radioRightActionBack;
    private MaterialRadioButton radioRightActionDesktop;
    private MaterialRadioButton radioRightActionHome;
    private MaterialRadioButton radioRightActionPanel;
    private MaterialRadioButton radioUpActionBack;
    private MaterialRadioButton radioUpActionDesktop;
    private MaterialRadioButton radioUpActionHome;
    private MaterialRadioButton radioUpActionPanel;
    private SeekBar seekGestureSensitivity;
    private SeekBar seekPanelAlpha;
    private SeekBar seekPanelTimeout;
    private SeekBar seekPanelWidth;
    private SeekBar seekTriggerHeight;
    private SeekBar seekTriggerPosition;
    private SeekBar seekTriggerWidth;
    private SharedPreferences sharedPreferences;
    private SwitchMaterial switchAutoStart;
    private SwitchMaterial switchDownSwipe;
    private SwitchMaterial switchFileAssistant;
    private SwitchMaterial switchGestureActions;
    private SwitchMaterial switchGestureAnimation;
    private SwitchMaterial switchGestureMirror;
    private SwitchMaterial switchPanelShowAppName;
    private SwitchMaterial switchPermissionAssistant;
    private SwitchMaterial switchRightSwipe;
    private SwitchMaterial switchScreenOnAutoStart;
    private SwitchMaterial switchStrWakeAutoStart;
    private SwitchMaterial switchUpSwipe;
    private TextView textAccessibilityPermissionStatus;
    private TextView textAutoStartCount;
    private TextView textCarSettingsCount;
    private TextView textGestureSensitivity;
    private TextView textInstallPermissionStatus;
    private TextView textOverlayPermissionStatus;
    private TextView textPanelAlpha;
    private TextView textPanelAppsCount;
    private TextView textPanelShowAppNameDesc;
    private TextView textPanelTimeout;
    private TextView textPanelWidth;
    private TextView textStoragePermissionStatus;
    private TextView textTriggerHeight;
    private TextView textTriggerPosition;
    private TextView textTriggerWidth;
    private int mAccessibilityRepairAttempts = 0;
    private final Handler permissionHandler = new Handler(Looper.getMainLooper());
    private final Runnable permissionRefreshRunnable = new f4(3, this);

    private void applyActionSelection(int i7, MaterialRadioButton materialRadioButton, MaterialRadioButton materialRadioButton2, MaterialRadioButton materialRadioButton3, MaterialRadioButton materialRadioButton4) {
        if (i7 == 1) {
            materialRadioButton.setChecked(true);
            return;
        }
        if (i7 == 2) {
            materialRadioButton2.setChecked(true);
        } else if (i7 == 3) {
            materialRadioButton3.setChecked(true);
        } else if (i7 == 4) {
            materialRadioButton4.setChecked(true);
        }
    }

    private void ensureIconFolderExists() {
        if (hasStoragePermission()) {
            File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool"), "icon");
            if (file.exists()) {
                return;
            }
            file.mkdirs();
        }
    }

    private List<AppInfo> filterApps(List<AppInfo> list, int i7) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (AppInfo appInfo : list) {
            boolean isSystemApp = isSystemApp(appInfo.getPackageName());
            if (i7 != 1 || !isSystemApp) {
                if (i7 != 2 || isSystemApp) {
                    arrayList.add(appInfo);
                }
            }
        }
        arrayList.sort(new x(6));
        return arrayList;
    }

    private List<AppInfo> getAllLaunchableApps() {
        String str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        PackageManager packageManager = requireContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str2 = activityInfo.packageName;
            String str3 = activityInfo.name;
            if (!"com.navi.tool.FileAssistantActivity".equals(str3) && !"com.navi.tool.PermissionAssistantActivity".equals(str3)) {
                if (!linkedHashMap.containsKey("com.navi.tool".equals(str2) ? str2 + "/" + str3 : str2)) {
                    try {
                        str = resolveInfo.loadLabel(packageManager).toString();
                    } catch (Exception unused) {
                        str = str2;
                    }
                    if (!linkedHashMap.containsKey(str2)) {
                        linkedHashMap.put(str2, new AppInfo(str2, str, false));
                    }
                }
            }
        }
        if (this.sharedPreferences.getBoolean("file_assistant_enabled", false)) {
            linkedHashMap.put("com.navi.tool.fileassistant", new AppInfo("com.navi.tool.fileassistant", "文件助手", true));
        }
        if (this.sharedPreferences.getBoolean("permission_assistant_enabled", false)) {
            linkedHashMap.put("com.navi.tool.permissionassistant", new AppInfo("com.navi.tool.permissionassistant", "权限助手", true));
        }
        if (this.sharedPreferences.getBoolean("desktop_assistant_enabled", false)) {
            linkedHashMap.put("com.navi.tool.desktopassistant", new AppInfo("com.navi.tool.desktopassistant", "桌面助手", true));
        }
        linkedHashMap.put(PKG_ADB_SWITCH, new AppInfo(PKG_ADB_SWITCH, "ADB界面", true));
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        arrayList.sort(new x(7));
        return arrayList;
    }

    private List<AppInfo> getEnabledPanelAppsInOrder() {
        String str;
        String str2;
        String str3;
        AdditionalFeaturesFragment additionalFeaturesFragment;
        boolean z6;
        int i7;
        String str4;
        String str5;
        String str6;
        PackageManager packageManager;
        AdditionalFeaturesFragment additionalFeaturesFragment2 = this;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager2 = requireContext().getPackageManager();
        String string = additionalFeaturesFragment2.sharedPreferences.getString("gesture_panel_app_order", s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        boolean z7 = additionalFeaturesFragment2.sharedPreferences.getBoolean("file_assistant_enabled", false);
        boolean z8 = additionalFeaturesFragment2.sharedPreferences.getBoolean("permission_assistant_enabled", false);
        boolean z9 = additionalFeaturesFragment2.sharedPreferences.getBoolean("desktop_assistant_enabled", false);
        String str7 = "gesture_panel_app_";
        PackageManager packageManager3 = packageManager2;
        String str8 = PKG_ADB_SWITCH;
        String str9 = "桌面助手";
        if (string == null || string.isEmpty()) {
            str = string;
            str2 = "gesture_panel_app_";
            str3 = ",";
        } else {
            String[] split = string.split(",");
            str3 = ",";
            int length = split.length;
            str = string;
            int i8 = 0;
            while (i8 < length) {
                String[] strArr = split;
                String trim = split[i8].trim();
                if (trim.isEmpty()) {
                    str4 = str7;
                    i7 = length;
                } else {
                    i7 = length;
                    str4 = str7;
                    if (additionalFeaturesFragment2.sharedPreferences.getBoolean(str7.concat(trim), str8.equals(trim))) {
                        if ("com.navi.tool.fileassistant".equals(trim)) {
                            if (z7) {
                                a.a.x(trim, "文件助手", true, arrayList);
                                hashSet.add(trim);
                            }
                        } else if (!"com.navi.tool.permissionassistant".equals(trim)) {
                            if (!"com.navi.tool.desktopassistant".equals(trim)) {
                                str5 = str9;
                                if (str8.equals(trim)) {
                                    a.a.x(trim, "ADB界面", true, arrayList);
                                    hashSet.add(trim);
                                } else {
                                    PackageManager packageManager4 = packageManager3;
                                    str6 = str8;
                                    try {
                                        packageManager = packageManager4;
                                        try {
                                            arrayList.add(new AppInfo(trim, packageManager4.getApplicationLabel(packageManager4.getApplicationInfo(trim, 0)).toString(), true));
                                            hashSet.add(trim);
                                        } catch (Exception unused) {
                                        }
                                    } catch (Exception unused2) {
                                        packageManager = packageManager4;
                                    }
                                    i8++;
                                    additionalFeaturesFragment2 = this;
                                    str8 = str6;
                                    packageManager3 = packageManager;
                                    length = i7;
                                    split = strArr;
                                    str9 = str5;
                                    str7 = str4;
                                }
                            } else if (z9) {
                                str5 = str9;
                                a.a.x(trim, str5, true, arrayList);
                                hashSet.add(trim);
                            }
                            packageManager = packageManager3;
                            str6 = str8;
                            i8++;
                            additionalFeaturesFragment2 = this;
                            str8 = str6;
                            packageManager3 = packageManager;
                            length = i7;
                            split = strArr;
                            str9 = str5;
                            str7 = str4;
                        } else if (z8) {
                            a.a.x(trim, "权限助手", true, arrayList);
                            hashSet.add(trim);
                        }
                    }
                }
                str5 = str9;
                packageManager = packageManager3;
                str6 = str8;
                i8++;
                additionalFeaturesFragment2 = this;
                str8 = str6;
                packageManager3 = packageManager;
                length = i7;
                split = strArr;
                str9 = str5;
                str7 = str4;
            }
            str2 = str7;
        }
        String str10 = str9;
        PackageManager packageManager5 = packageManager3;
        String str11 = str8;
        if (hashSet.contains("com.navi.tool.fileassistant")) {
            additionalFeaturesFragment = this;
        } else {
            additionalFeaturesFragment = this;
            if (additionalFeaturesFragment.sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.fileassistant", false) && z7) {
                a.a.x("com.navi.tool.fileassistant", "文件助手", true, arrayList);
                hashSet.add("com.navi.tool.fileassistant");
            }
        }
        if (!hashSet.contains("com.navi.tool.permissionassistant") && additionalFeaturesFragment.sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.permissionassistant", false) && z8) {
            a.a.x("com.navi.tool.permissionassistant", "权限助手", true, arrayList);
            hashSet.add("com.navi.tool.permissionassistant");
        }
        if (!hashSet.contains("com.navi.tool.desktopassistant") && additionalFeaturesFragment.sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.desktopassistant", false) && z9) {
            z6 = true;
            a.a.x("com.navi.tool.desktopassistant", str10, true, arrayList);
            hashSet.add("com.navi.tool.desktopassistant");
        } else {
            z6 = true;
        }
        if (!hashSet.contains(str11) && additionalFeaturesFragment.sharedPreferences.getBoolean("gesture_panel_app_com.navi.tool.action.adbswitch", z6)) {
            a.a.x(str11, "ADB界面", z6, arrayList);
            hashSet.add(str11);
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        Iterator<ResolveInfo> it = packageManager5.queryIntentActivities(intent, 0).iterator();
        while (it.hasNext()) {
            String str12 = it.next().activityInfo.packageName;
            if (!hashSet.contains(str12)) {
                String str13 = str2;
                if (additionalFeaturesFragment.sharedPreferences.getBoolean(str13 + str12, false)) {
                    try {
                        try {
                            arrayList.add(new AppInfo(str12, packageManager5.getApplicationLabel(packageManager5.getApplicationInfo(str12, 0)).toString(), true));
                            hashSet.add(str12);
                        } catch (Exception unused3) {
                        }
                    } catch (Exception unused4) {
                    }
                }
                str2 = str13;
            }
        }
        if (str != null && !str.isEmpty()) {
            for (String str14 : str.split(str3)) {
                if (str11.equals(str14.trim())) {
                    break;
                }
            }
        }
        int i9 = 0;
        while (true) {
            if (i9 >= arrayList.size()) {
                break;
            }
            if (str11.equals(((AppInfo) arrayList.get(i9)).getPackageName())) {
                arrayList.add(0, (AppInfo) arrayList.remove(i9));
                break;
            }
            i9++;
        }
        return arrayList;
    }

    private void handleIconChosen(String str, Uri uri) {
        int lastIndexOf;
        if (!hasStoragePermission()) {
            ToastHelper.show(requireContext(), "没有存储权限，无法保存图标");
            return;
        }
        ensureIconFolderExists();
        File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool"), "icon");
        if (!file.exists() && !file.mkdirs()) {
            ToastHelper.show(requireContext(), "无法创建图标目录");
            return;
        }
        String queryDisplayName = queryDisplayName(uri);
        File file2 = new File(file, a.a.g(str, (queryDisplayName == null || (lastIndexOf = queryDisplayName.lastIndexOf(46)) < 0 || lastIndexOf >= queryDisplayName.length() + (-1)) ? ".png" : queryDisplayName.substring(lastIndexOf)));
        try {
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                ToastHelper.show(requireContext(), "无法读取选中的文件");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read <= 0) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            fileOutputStream.flush();
            openInputStream.close();
            fileOutputStream.close();
            this.sharedPreferences.edit().putString("gesture_panel_app_icon_" + str, file2.getAbsolutePath()).apply();
            PanelAppSettingsAdapter panelAppSettingsAdapter = this.panelAppSettingsAdapter;
            if (panelAppSettingsAdapter != null) {
                panelAppSettingsAdapter.notifyDataSetChanged();
            }
            Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
            intent.setPackage(requireContext().getPackageName());
            requireContext().sendBroadcast(intent);
            ToastHelper.show(requireContext(), "图标已更新");
        } catch (Exception unused) {
            ToastHelper.show(requireContext(), "保存图标失败");
        }
    }

    private boolean hasInstallPermission() {
        return requireContext().getPackageManager().canRequestPackageInstalls();
    }

    private boolean hasStoragePermission() {
        boolean isExternalStorageManager;
        if (Build.VERSION.SDK_INT < 30) {
            return g0.g.a(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        isExternalStorageManager = Environment.isExternalStorageManager();
        return isExternalStorageManager;
    }

    private void initIconChooser() {
        this.chooseIconLauncher = registerForActivityResult(new e.f(), new f(this));
    }

    private void initSwitchAndSeekBars() {
        boolean isAutoStartEnabled = SharedPreferencesUtils.isAutoStartEnabled(requireContext());
        this.switchAutoStart.setChecked(isAutoStartEnabled);
        final int i7 = 8;
        final int i8 = 0;
        this.btnManageAutoStart.setVisibility(isAutoStartEnabled ? 0 : 8);
        this.btnManageCarSettings.setVisibility(isAutoStartEnabled ? 0 : 8);
        this.textCarSettingsCount.setVisibility(isAutoStartEnabled ? 0 : 8);
        boolean isScreenOnAutoStartEnabled = SharedPreferencesUtils.isScreenOnAutoStartEnabled(requireContext());
        this.switchScreenOnAutoStart.setChecked(isScreenOnAutoStartEnabled);
        this.btnManageScreenOnAutoStart.setVisibility(isScreenOnAutoStartEnabled ? 0 : 8);
        this.containerScreenOnAutoStart.setVisibility(isAutoStartEnabled ? 0 : 8);
        this.dividerScreenOnAutoStart.setVisibility(isAutoStartEnabled ? 0 : 8);
        this.switchStrWakeAutoStart.setChecked(SharedPreferencesUtils.isStrWakeAutoStartEnabled(requireContext()));
        updateStrWakeSwitchVisibility();
        updateAutoStartCount();
        updateCarSettingsCount();
        this.switchAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i9 = i8;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i9) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z6);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z6);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z6);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z6);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z6);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z6);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z6);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z6);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z6);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z6);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z6);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z6);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z6);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z6);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z6);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z6);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z6);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z6);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z6);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z6);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z6);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z6);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z6);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z6);
                        break;
                }
            }
        });
        final int i9 = 11;
        this.switchScreenOnAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i92 = i9;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z6);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z6);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z6);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z6);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z6);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z6);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z6);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z6);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z6);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z6);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z6);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z6);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z6);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z6);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z6);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z6);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z6);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z6);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z6);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z6);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z6);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z6);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z6);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z6);
                        break;
                }
            }
        });
        final int i10 = 19;
        this.switchStrWakeAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                int i92 = i10;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z6);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z6);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z6);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z6);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z6);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z6);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z6);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z6);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z6);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z6);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z6);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z6);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z6);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z6);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z6);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z6);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z6);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z6);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z6);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z6);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z6);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z6);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z6);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z6);
                        break;
                }
            }
        });
        final int i11 = 2;
        this.btnManageAutoStart.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5267b;

            {
                this.f5267b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i12 = i11;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5267b;
                switch (i12) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$27(view);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$28(view);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$3(view);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$4(view);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$5(view);
                        break;
                }
            }
        });
        final int i12 = 3;
        this.btnManageScreenOnAutoStart.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5267b;

            {
                this.f5267b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i122 = i12;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5267b;
                switch (i122) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$27(view);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$28(view);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$3(view);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$4(view);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$5(view);
                        break;
                }
            }
        });
        final int i13 = 4;
        this.btnManageCarSettings.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5267b;

            {
                this.f5267b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i122 = i13;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5267b;
                switch (i122) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$27(view);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$28(view);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$3(view);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$4(view);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$5(view);
                        break;
                }
            }
        });
        boolean z6 = this.sharedPreferences.getBoolean("file_assistant_enabled", false);
        this.switchFileAssistant.setChecked(z6);
        setFileAssistantComponentEnabled(z6);
        final int i14 = 20;
        this.switchFileAssistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i14;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        boolean z7 = this.sharedPreferences.getBoolean("permission_assistant_enabled", false);
        this.switchPermissionAssistant.setChecked(z7);
        setPermissionAssistantComponentEnabled(z7);
        final int i15 = 21;
        this.switchPermissionAssistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i15;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.switchGestureActions.setChecked(this.sharedPreferences.getBoolean("gesture_actions_enabled", false));
        final int i16 = 22;
        this.switchGestureActions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i16;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.switchGestureMirror.setChecked(this.sharedPreferences.getBoolean("gesture_mirror_enabled", false));
        final int i17 = 23;
        this.switchGestureMirror.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i17;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        int i18 = this.sharedPreferences.getInt("gesture_sensitivity", 4);
        final int i19 = 1;
        if (i18 < 1) {
            i18 = 4;
        }
        if (i18 > 5) {
            i18 = 5;
        }
        this.seekGestureSensitivity.setMax(4);
        this.seekGestureSensitivity.setProgress(i18 - 1);
        updateSensitivityText(i18);
        this.seekGestureSensitivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i20, boolean z8) {
                int i21 = i20 + 1;
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_sensitivity", i21).apply();
                AdditionalFeaturesFragment.this.updateSensitivityText(i21);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i20 = this.sharedPreferences.getInt("gesture_trigger_height_percent", 66);
        if (i20 < 10) {
            i20 = 10;
        }
        if (i20 > 100) {
            i20 = 100;
        }
        this.seekTriggerHeight.setMax(100);
        this.seekTriggerHeight.setProgress(i20);
        updateTriggerHeightText(i20);
        this.seekTriggerHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i21, boolean z8) {
                if (i21 < 10) {
                    i21 = 10;
                }
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_trigger_height_percent", i21).apply();
                AdditionalFeaturesFragment.this.updateTriggerHeightText(i21);
                GestureActionService.updateTriggerConfig(AdditionalFeaturesFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), false);
            }
        });
        int i21 = this.sharedPreferences.getInt("gesture_trigger_width_percent", 50);
        if (i21 < 0) {
            i21 = 0;
        }
        if (i21 > 100) {
            i21 = 100;
        }
        this.seekTriggerWidth.setMax(100);
        this.seekTriggerWidth.setProgress(i21);
        updateTriggerWidthText(i21);
        this.seekTriggerWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i22, boolean z8) {
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_trigger_width_percent", i22).apply();
                AdditionalFeaturesFragment.this.updateTriggerWidthText(i22);
                GestureActionService.updateTriggerConfig(AdditionalFeaturesFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), false);
            }
        });
        int i22 = this.sharedPreferences.getInt("gesture_trigger_position_percent", 50);
        if (i22 < 0) {
            i22 = 0;
        }
        if (i22 > 100) {
            i22 = 100;
        }
        this.seekTriggerPosition.setMax(100);
        this.seekTriggerPosition.setProgress(i22);
        updateTriggerPositionText(i22);
        this.seekTriggerPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i23, boolean z8) {
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_trigger_position_percent", i23).apply();
                AdditionalFeaturesFragment.this.updateTriggerPositionText(i23);
                GestureActionService.updateTriggerConfig(AdditionalFeaturesFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewTrigger(AdditionalFeaturesFragment.this.requireContext(), false);
            }
        });
        this.switchGestureAnimation.setChecked(this.sharedPreferences.getBoolean("gesture_animation_enabled", false));
        this.switchGestureAnimation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i19;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        boolean z8 = this.sharedPreferences.getBoolean("gesture_right_enabled", false);
        boolean z9 = this.sharedPreferences.getBoolean("gesture_up_enabled", false);
        boolean z10 = this.sharedPreferences.getBoolean("gesture_down_enabled", false);
        this.switchRightSwipe.setChecked(z8);
        this.switchUpSwipe.setChecked(z9);
        this.switchDownSwipe.setChecked(z10);
        this.switchRightSwipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i11;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.switchUpSwipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i12;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.switchDownSwipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i13;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        int i23 = this.sharedPreferences.getInt("gesture_right_action", 1);
        int i24 = this.sharedPreferences.getInt("gesture_up_action", 2);
        int i25 = this.sharedPreferences.getInt("gesture_down_action", 3);
        applyActionSelection(i23, this.radioRightActionBack, this.radioRightActionPanel, this.radioRightActionHome, this.radioRightActionDesktop);
        applyActionSelection(i24, this.radioUpActionBack, this.radioUpActionPanel, this.radioUpActionHome, this.radioUpActionDesktop);
        applyActionSelection(i25, this.radioDownActionBack, this.radioDownActionPanel, this.radioDownActionHome, this.radioDownActionDesktop);
        this.radioRightActionBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = r2;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i26 = 6;
        this.radioRightActionPanel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i26;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i27 = 7;
        this.radioRightActionHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i27;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.radioRightActionDesktop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i7;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i28 = 9;
        this.radioUpActionBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i28;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        this.radioUpActionPanel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = r2;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i29 = 12;
        this.radioUpActionHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i29;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i30 = 13;
        this.radioUpActionDesktop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i30;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i31 = 14;
        this.radioDownActionBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i31;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i32 = 15;
        this.radioDownActionPanel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i32;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i33 = 16;
        this.radioDownActionHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i33;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        final int i34 = 17;
        this.radioDownActionDesktop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i34;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        int i35 = this.sharedPreferences.getInt("gesture_panel_width_percent", 50);
        r13 = i35 >= 10 ? i35 : 10;
        if (r13 > 100) {
            r13 = 100;
        }
        this.seekPanelWidth.setMax(100);
        this.seekPanelWidth.setProgress(r13);
        updatePanelWidthText(r13);
        this.seekPanelWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.5
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i36, boolean z11) {
                if (i36 < 10) {
                    i36 = 10;
                }
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_panel_width_percent", i36).apply();
                AdditionalFeaturesFragment.this.updatePanelWidthText(i36);
                GestureActionService.updatePanelConfig(AdditionalFeaturesFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewPanel(AdditionalFeaturesFragment.this.requireContext(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewPanel(AdditionalFeaturesFragment.this.requireContext(), false);
            }
        });
        int i36 = this.sharedPreferences.getInt("gesture_panel_alpha_percent", 50);
        if (i36 < 0) {
            i36 = 0;
        }
        if (i36 > 100) {
            i36 = 100;
        }
        this.seekPanelAlpha.setMax(100);
        this.seekPanelAlpha.setProgress(i36);
        updatePanelAlphaText(i36);
        this.seekPanelAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i37, boolean z11) {
                if (i37 < 0) {
                    i37 = 0;
                }
                if (i37 > 100) {
                    i37 = 100;
                }
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_panel_alpha_percent", i37).apply();
                AdditionalFeaturesFragment.this.updatePanelAlphaText(i37);
                GestureActionService.updatePanelConfig(AdditionalFeaturesFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewPanel(AdditionalFeaturesFragment.this.requireContext(), true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                GestureActionService.previewPanel(AdditionalFeaturesFragment.this.requireContext(), false);
            }
        });
        this.switchPanelShowAppName.setChecked(this.sharedPreferences.getBoolean("gesture_panel_show_app_name", false));
        final int i37 = 18;
        this.switchPanelShowAppName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.j

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5238b;

            {
                this.f5238b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z62) {
                int i92 = i37;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5238b;
                switch (i92) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$0(compoundButton, z62);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$10(compoundButton, z62);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$11(compoundButton, z62);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$12(compoundButton, z62);
                        break;
                    case 4:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$13(compoundButton, z62);
                        break;
                    case 5:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$14(compoundButton, z62);
                        break;
                    case 6:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$15(compoundButton, z62);
                        break;
                    case 7:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$16(compoundButton, z62);
                        break;
                    case 8:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$17(compoundButton, z62);
                        break;
                    case 9:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$18(compoundButton, z62);
                        break;
                    case 10:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$19(compoundButton, z62);
                        break;
                    case 11:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$1(compoundButton, z62);
                        break;
                    case 12:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$20(compoundButton, z62);
                        break;
                    case 13:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$21(compoundButton, z62);
                        break;
                    case 14:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$22(compoundButton, z62);
                        break;
                    case 15:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$23(compoundButton, z62);
                        break;
                    case 16:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$24(compoundButton, z62);
                        break;
                    case 17:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$25(compoundButton, z62);
                        break;
                    case 18:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$26(compoundButton, z62);
                        break;
                    case 19:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$2(compoundButton, z62);
                        break;
                    case 20:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$6(compoundButton, z62);
                        break;
                    case 21:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$7(compoundButton, z62);
                        break;
                    case 22:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$8(compoundButton, z62);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$9(compoundButton, z62);
                        break;
                }
            }
        });
        int i38 = this.sharedPreferences.getInt("gesture_panel_timeout_seconds", 2);
        if (i38 < 1) {
            i38 = 1;
        }
        r12 = i38 <= 5 ? i38 : 5;
        this.seekPanelTimeout.setMax(4);
        this.seekPanelTimeout.setProgress(r12 - 1);
        updatePanelTimeoutText(r12);
        this.seekPanelTimeout.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i39, boolean z11) {
                int i40 = i39 + 1;
                int i41 = i40 >= 1 ? i40 : 1;
                if (i41 > 5) {
                    i41 = 5;
                }
                AdditionalFeaturesFragment.this.sharedPreferences.edit().putInt("gesture_panel_timeout_seconds", i41).apply();
                AdditionalFeaturesFragment.this.updatePanelTimeoutText(i41);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.buttonSelectPanelApps.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5267b;

            {
                this.f5267b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i122 = i8;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5267b;
                switch (i122) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$27(view);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$28(view);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$3(view);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$4(view);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$5(view);
                        break;
                }
            }
        });
        this.buttonSettingsPanelApps.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ AdditionalFeaturesFragment f5267b;

            {
                this.f5267b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i122 = i19;
                AdditionalFeaturesFragment additionalFeaturesFragment = this.f5267b;
                switch (i122) {
                    case 0:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$27(view);
                        break;
                    case 1:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$28(view);
                        break;
                    case 2:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$3(view);
                        break;
                    case 3:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$4(view);
                        break;
                    default:
                        additionalFeaturesFragment.lambda$initSwitchAndSeekBars$5(view);
                        break;
                }
            }
        });
        updatePanelAppsCount();
    }

    private void initViews(View view) {
        this.switchGestureActions = (SwitchMaterial) view.findViewById(g4.p.switch_gesture_actions);
        this.switchGestureMirror = (SwitchMaterial) view.findViewById(g4.p.switch_gesture_mirror);
        this.gestureMirrorContainer = (MaterialCardView) view.findViewById(g4.p.gesture_mirror_container);
        this.permissionStatusContainer = (MaterialCardView) view.findViewById(g4.p.permission_status_container);
        this.gestureTriggerContainer = (MaterialCardView) view.findViewById(g4.p.gesture_trigger_container);
        this.gestureActionsDetailContainer = (MaterialCardView) view.findViewById(g4.p.gesture_actions_detail_container);
        this.appPanelContainer = (MaterialCardView) view.findViewById(g4.p.app_panel_container);
        this.textOverlayPermissionStatus = (TextView) view.findViewById(g4.p.text_overlay_permission_status);
        this.textAccessibilityPermissionStatus = (TextView) view.findViewById(g4.p.text_accessibility_permission_status);
        this.textStoragePermissionStatus = (TextView) view.findViewById(g4.p.text_storage_permission_status);
        this.textInstallPermissionStatus = (TextView) view.findViewById(g4.p.text_install_permission_status);
        this.fileAssistantContainer = (MaterialCardView) view.findViewById(g4.p.file_assistant_container);
        this.switchFileAssistant = (SwitchMaterial) view.findViewById(g4.p.switch_file_assistant);
        this.switchPermissionAssistant = (SwitchMaterial) view.findViewById(g4.p.switch_permission_assistant);
        this.switchAutoStart = (SwitchMaterial) view.findViewById(g4.p.switch_auto_start);
        this.containerScreenOnAutoStart = view.findViewById(g4.p.container_screen_on_auto_start);
        this.dividerScreenOnAutoStart = view.findViewById(g4.p.divider_screen_on_auto_start);
        this.switchScreenOnAutoStart = (SwitchMaterial) view.findViewById(g4.p.switch_screen_on_auto_start);
        this.containerStrWakeAutoStart = view.findViewById(g4.p.container_str_wake_auto_start);
        this.dividerStrWakeAutoStart = view.findViewById(g4.p.divider_str_wake_auto_start);
        this.switchStrWakeAutoStart = (SwitchMaterial) view.findViewById(g4.p.switch_str_wake_auto_start);
        this.btnManageAutoStart = (Button) view.findViewById(g4.p.btn_manage_auto_start);
        this.btnManageScreenOnAutoStart = (Button) view.findViewById(g4.p.btn_manage_screen_on_auto_start);
        this.textAutoStartCount = (TextView) view.findViewById(g4.p.text_auto_start_count);
        this.btnManageCarSettings = (Button) view.findViewById(g4.p.btn_manage_car_settings);
        this.textCarSettingsCount = (TextView) view.findViewById(g4.p.text_car_settings_count);
        this.seekGestureSensitivity = (SeekBar) view.findViewById(g4.p.seek_gesture_sensitivity);
        this.textGestureSensitivity = (TextView) view.findViewById(g4.p.text_gesture_sensitivity);
        this.seekTriggerHeight = (SeekBar) view.findViewById(g4.p.seek_trigger_height);
        this.textTriggerHeight = (TextView) view.findViewById(g4.p.text_trigger_height);
        this.seekTriggerWidth = (SeekBar) view.findViewById(g4.p.seek_trigger_width);
        this.textTriggerWidth = (TextView) view.findViewById(g4.p.text_trigger_width);
        this.seekTriggerPosition = (SeekBar) view.findViewById(g4.p.seek_trigger_position);
        this.textTriggerPosition = (TextView) view.findViewById(g4.p.text_trigger_position);
        this.switchGestureAnimation = (SwitchMaterial) view.findViewById(g4.p.switch_gesture_animation);
        this.switchRightSwipe = (SwitchMaterial) view.findViewById(g4.p.switch_right_swipe);
        this.switchUpSwipe = (SwitchMaterial) view.findViewById(g4.p.switch_up_swipe);
        this.switchDownSwipe = (SwitchMaterial) view.findViewById(g4.p.switch_down_swipe);
        this.radioGroupRightAction = (RadioGroup) view.findViewById(g4.p.radio_group_right_action);
        this.radioGroupUpAction = (RadioGroup) view.findViewById(g4.p.radio_group_up_action);
        this.radioGroupDownAction = (RadioGroup) view.findViewById(g4.p.radio_group_down_action);
        this.radioRightActionBack = (MaterialRadioButton) view.findViewById(g4.p.radio_right_action_back);
        this.radioRightActionPanel = (MaterialRadioButton) view.findViewById(g4.p.radio_right_action_panel);
        this.radioRightActionHome = (MaterialRadioButton) view.findViewById(g4.p.radio_right_action_home);
        this.radioRightActionDesktop = (MaterialRadioButton) view.findViewById(g4.p.radio_right_action_desktop);
        this.radioUpActionBack = (MaterialRadioButton) view.findViewById(g4.p.radio_up_action_back);
        this.radioUpActionPanel = (MaterialRadioButton) view.findViewById(g4.p.radio_up_action_panel);
        this.radioUpActionHome = (MaterialRadioButton) view.findViewById(g4.p.radio_up_action_home);
        this.radioUpActionDesktop = (MaterialRadioButton) view.findViewById(g4.p.radio_up_action_desktop);
        this.radioDownActionBack = (MaterialRadioButton) view.findViewById(g4.p.radio_down_action_back);
        this.radioDownActionPanel = (MaterialRadioButton) view.findViewById(g4.p.radio_down_action_panel);
        this.radioDownActionHome = (MaterialRadioButton) view.findViewById(g4.p.radio_down_action_home);
        this.radioDownActionDesktop = (MaterialRadioButton) view.findViewById(g4.p.radio_down_action_desktop);
        this.seekPanelWidth = (SeekBar) view.findViewById(g4.p.seek_panel_width);
        this.textPanelWidth = (TextView) view.findViewById(g4.p.text_panel_width);
        this.seekPanelAlpha = (SeekBar) view.findViewById(g4.p.seek_panel_alpha);
        this.textPanelAlpha = (TextView) view.findViewById(g4.p.text_panel_alpha);
        this.buttonSelectPanelApps = (Button) view.findViewById(g4.p.button_select_panel_apps);
        this.textPanelAppsCount = (TextView) view.findViewById(g4.p.text_panel_apps_count);
        this.buttonSettingsPanelApps = (Button) view.findViewById(g4.p.button_settings_panel_apps);
        this.seekPanelTimeout = (SeekBar) view.findViewById(g4.p.seek_panel_timeout);
        this.textPanelTimeout = (TextView) view.findViewById(g4.p.text_panel_timeout);
        this.switchPanelShowAppName = (SwitchMaterial) view.findViewById(g4.p.switch_panel_show_app_name);
        this.textPanelShowAppNameDesc = (TextView) view.findViewById(g4.p.text_panel_show_app_name_desc);
    }

    private boolean isSystemApp(String str) {
        try {
            int i7 = requireContext().getPackageManager().getApplicationInfo(str, 0).flags;
            return ((i7 & 1) == 0 && (i7 & 128) == 0) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$filterApps$35(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getAllLaunchableApps$34(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$initIconChooser$44(ActivityResult activityResult) {
        if (activityResult.f648a != -1) {
            this.pendingIconChangePackageName = null;
            return;
        }
        Intent intent = activityResult.f649b;
        if (intent == null) {
            this.pendingIconChangePackageName = null;
            return;
        }
        Uri data = intent.getData();
        String str = this.pendingIconChangePackageName;
        this.pendingIconChangePackageName = null;
        if (data == null || str == null) {
            return;
        }
        handleIconChosen(str, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$0(CompoundButton compoundButton, boolean z6) {
        if (!z6 && SharedPreferencesUtils.isDesktopAssistantAutoStartEnabled(requireContext())) {
            compoundButton.setChecked(true);
            ToastHelper.showLong(requireContext(), "请先关闭桌面助手自启功能，才能关闭自启管理");
            return;
        }
        SharedPreferencesUtils.setAutoStartEnabled(requireContext(), z6);
        this.btnManageAutoStart.setVisibility(z6 ? 0 : 8);
        this.btnManageCarSettings.setVisibility(z6 ? 0 : 8);
        this.textCarSettingsCount.setVisibility(z6 ? 0 : 8);
        this.containerScreenOnAutoStart.setVisibility(z6 ? 0 : 8);
        this.dividerScreenOnAutoStart.setVisibility(z6 ? 0 : 8);
        updateAutoStartCount();
        updateStrWakeSwitchVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$1(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setScreenOnAutoStartEnabled(requireContext(), z6);
        this.btnManageScreenOnAutoStart.setVisibility(z6 ? 0 : 8);
        updateStrWakeSwitchVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$10(CompoundButton compoundButton, boolean z6) {
        this.sharedPreferences.edit().putBoolean("gesture_animation_enabled", z6).apply();
        GestureActionService.updateTriggerConfig(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$11(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "gesture_right_enabled", z6);
        updateControlsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$12(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "gesture_up_enabled", z6);
        updateControlsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$13(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "gesture_down_enabled", z6);
        updateControlsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$14(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_right_action", 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$15(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_right_action", 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$16(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_right_action", 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$17(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_right_action", 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$18(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_up_action", 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$19(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_up_action", 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$2(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setStrWakeAutoStartEnabled(requireContext(), z6);
        try {
            requireContext().startService(new Intent(requireContext(), (Class<?>) ThemeMonitorService.class));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$20(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_up_action", 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$21(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_up_action", 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$22(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_down_action", 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$23(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_down_action", 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$24(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_down_action", 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$25(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            a.a.l(this.sharedPreferences, "gesture_down_action", 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$26(CompoundButton compoundButton, boolean z6) {
        this.sharedPreferences.edit().putBoolean("gesture_panel_show_app_name", z6).apply();
        GestureActionService.updatePanelConfig(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$27(View view) {
        showSelectPanelAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$28(View view) {
        showPanelAppsSettingsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$3(View view) {
        showAutoStartAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$4(View view) {
        showScreenOnAutoStartAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$5(View view) {
        showCarSettingsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$6(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "file_assistant_enabled", z6);
        setFileAssistantComponentEnabled(z6);
        updateControlsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$7(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "permission_assistant_enabled", z6);
        setPermissionAssistantComponentEnabled(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$8(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.sharedPreferences, "gesture_actions_enabled", z6);
        if (z6) {
            ensureIconFolderExists();
            GestureActionService.start(requireContext());
        } else {
            GestureActionService.stop(requireContext());
        }
        updateControlsVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitchAndSeekBars$9(CompoundButton compoundButton, boolean z6) {
        this.sharedPreferences.edit().putBoolean("gesture_mirror_enabled", z6).apply();
        GestureActionService.updateTriggerConfig(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadAutoStartApps$37(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAutoStartApps$38(List list, RecyclerView recyclerView) {
        recyclerView.setAdapter(new AutoStartAppAdapter(requireContext(), list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAutoStartApps$39(RecyclerView recyclerView) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = requireContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        HashSet hashSet = new HashSet();
        if (this.sharedPreferences.getBoolean("desktop_assistant_enabled", false)) {
            a.a.x("com.navi.tool.desktopassistant", "桌面助手", false, arrayList);
            hashSet.add("com.navi.tool.desktopassistant");
        }
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (true) {
            if (!it.hasNext()) {
                Collections.sort(arrayList, new x(5));
                requireActivity().runOnUiThread(new l(this, arrayList, recyclerView, r6));
                return;
            }
            ResolveInfo next = it.next();
            String str = next.activityInfo.packageName;
            if (!str.equals(requireContext().getPackageName()) && !hashSet.contains(str)) {
                int i7 = next.activityInfo.applicationInfo.flags;
                boolean z6 = (i7 & 1) != 0;
                r6 = (i7 & 128) == 0 ? 0 : 1;
                if (!z6 || r6 != 0) {
                    hashSet.add(str);
                    arrayList.add(new AppInfo(str, next.loadLabel(packageManager).toString(), false));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAutoStartAppsDialog$36(DialogInterface dialogInterface) {
        updateAutoStartCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCarSettingsDialog$43(DialogInterface dialogInterface) {
        updateCarSettingsCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeNameDialog$45(EditText editText, AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            this.sharedPreferences.edit().remove("app_custom_name_" + appInfo.getPackageName()).commit();
            ToastHelper.show(requireContext(), "已恢复默认名称");
        } else {
            this.sharedPreferences.edit().putString("app_custom_name_" + appInfo.getPackageName(), c7).commit();
            ToastHelper.show(requireContext(), "名称已修改");
        }
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        GestureActionService.updatePanelConfig(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeNameDialog$46(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        this.sharedPreferences.edit().remove("app_custom_name_" + appInfo.getPackageName()).commit();
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        GestureActionService.updatePanelConfig(requireContext());
        ToastHelper.show(requireContext(), "已恢复默认名称");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showChangeNameDialog$47(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$showScreenOnAutoStartAppsDialog$40(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showScreenOnAutoStartAppsDialog$41(List list, RecyclerView recyclerView) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        if (list.isEmpty()) {
            ToastHelper.show(requireContext(), "请先在自启应用管理里选择应用");
        }
        recyclerView.setAdapter(new ScreenOnAutoStartAppAdapter(requireContext(), list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showScreenOnAutoStartAppsDialog$42(RecyclerView recyclerView) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = requireContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        int i7 = 0;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (!str.equals(requireContext().getPackageName()) && !hashSet.contains(str) && SharedPreferencesUtils.isAutoStartAppEnabled(requireContext(), str)) {
                int i8 = resolveInfo.activityInfo.applicationInfo.flags;
                boolean z6 = (i8 & 1) != 0;
                boolean z7 = (i8 & 128) != 0;
                if (!z6 || z7) {
                    hashSet.add(str);
                    arrayList.add(new AppInfo(str, resolveInfo.loadLabel(packageManager).toString(), false));
                }
            }
        }
        Collections.sort(arrayList, new x(4));
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new l(this, arrayList, recyclerView, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectPanelAppsDialog$29(DialogInterface dialogInterface) {
        updatePanelAppsCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectPanelAppsDialog$30(List list, int[] iArr, RecyclerView recyclerView, AppInfo appInfo) {
        boolean z6 = !appInfo.isEnabled();
        this.sharedPreferences.edit().putBoolean("gesture_panel_app_" + appInfo.getPackageName(), z6).apply();
        appInfo.setEnabled(z6);
        ArrayList arrayList = new ArrayList(filterApps(list, iArr[0]));
        AppAdapter appAdapter = (AppAdapter) recyclerView.getAdapter();
        if (appAdapter != null) {
            appAdapter.submitList(arrayList);
        }
        GestureActionService.updateTriggerConfig(requireContext());
        updatePanelAppsCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectPanelAppsDialog$31(RadioButton radioButton, int[] iArr, RadioButton radioButton2, List list, AppAdapter appAdapter, RadioGroup radioGroup, int i7) {
        if (i7 == radioButton.getId()) {
            iArr[0] = 1;
        } else if (i7 == radioButton2.getId()) {
            iArr[0] = 2;
        } else {
            iArr[0] = 0;
        }
        appAdapter.submitList(new ArrayList(filterApps(list, iArr[0])));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectPanelAppsDialog$32(final List list, final int[] iArr, final RecyclerView recyclerView, RadioGroup radioGroup, final RadioButton radioButton, final RadioButton radioButton2) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        final AppAdapter appAdapter = new AppAdapter(new AppAdapter.OnAppToggleListener() { // from class: com.navi.tool.ui.extra.g
            @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
            public final void onAppToggle(AppInfo appInfo) {
                AdditionalFeaturesFragment.this.lambda$showSelectPanelAppsDialog$30(list, iArr, recyclerView, appInfo);
            }
        });
        appAdapter.submitList(new ArrayList(filterApps(list, iArr[0])));
        recyclerView.setAdapter(appAdapter);
        recyclerView.setItemViewCacheSize(40);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.navi.tool.ui.extra.h
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i7) {
                AdditionalFeaturesFragment.this.lambda$showSelectPanelAppsDialog$31(radioButton, iArr, radioButton2, list, appAdapter, radioGroup2, i7);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectPanelAppsDialog$33(RecyclerView recyclerView, RadioGroup radioGroup, RadioButton radioButton, RadioButton radioButton2) {
        int[] iArr = {0};
        List<AppInfo> allLaunchableApps = getAllLaunchableApps();
        for (AppInfo appInfo : allLaunchableApps) {
            appInfo.setEnabled(this.sharedPreferences.getBoolean("gesture_panel_app_" + appInfo.getPackageName(), PKG_ADB_SWITCH.equals(appInfo.getPackageName())));
        }
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new d(this, allLaunchableApps, iArr, recyclerView, radioGroup, radioButton, radioButton2, 0));
    }

    private void loadAutoStartApps(RecyclerView recyclerView) {
        new Thread(new e(this, recyclerView, 1)).start();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        if (r10 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0032, code lost:
    
        if (r10 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0034, code lost:
    
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0045, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String queryDisplayName(android.net.Uri r10) {
        /*
            r9 = this;
            java.lang.String r0 = "_display_name"
            r1 = 0
            android.content.Context r2 = r9.requireContext()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r2 = 1
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r2 = 0
            r5[r2] = r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r6 = 0
            r7 = 0
            r8 = 0
            r4 = r10
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r10 == 0) goto L32
            boolean r2 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            if (r2 == 0) goto L32
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            if (r0 < 0) goto L32
            java.lang.String r0 = r10.getString(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            r10.close()
            return r0
        L2f:
            r0 = move-exception
            r1 = r10
            goto L3c
        L32:
            if (r10 == 0) goto L45
        L34:
            r10.close()
            goto L45
        L38:
            r0 = move-exception
            goto L3c
        L3a:
            r10 = r1
            goto L42
        L3c:
            if (r1 == 0) goto L41
            r1.close()
        L41:
            throw r0
        L42:
            if (r10 == 0) goto L45
            goto L34
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.AdditionalFeaturesFragment.queryDisplayName(android.net.Uri):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreAppIcon(AppInfo appInfo) {
        this.sharedPreferences.edit().remove("gesture_panel_app_icon_" + appInfo.getPackageName()).apply();
        PanelAppSettingsAdapter panelAppSettingsAdapter = this.panelAppSettingsAdapter;
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        ToastHelper.show(requireContext(), "图标已恢复");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePanelAppOrder(List<String> list) {
        String str;
        if (list == null || list.isEmpty()) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i7 = 0; i7 < list.size(); i7++) {
                if (i7 > 0) {
                    sb.append(',');
                }
                sb.append(list.get(i7));
            }
            str = sb.toString();
        }
        this.sharedPreferences.edit().putString("gesture_panel_app_order", str).apply();
    }

    private void setFileAssistantComponentEnabled(boolean z6) {
        try {
            ComponentName componentName = new ComponentName(requireContext(), (Class<?>) FileAssistantActivity.class);
            PackageManager packageManager = requireContext().getPackageManager();
            int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
            int i7 = z6 ? 1 : 2;
            if (componentEnabledSetting != i7) {
                packageManager.setComponentEnabledSetting(componentName, i7, 1);
                if (z6) {
                    ToastHelper.showLong(requireContext(), "文件助手图标已启用，可能需要稍等片刻或重启启动器才能看到");
                } else {
                    ToastHelper.show(requireContext(), "文件助手图标已隐藏");
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void setPermissionAssistantComponentEnabled(boolean z6) {
        try {
            ComponentName componentName = new ComponentName(requireContext(), (Class<?>) PermissionAssistantActivity.class);
            PackageManager packageManager = requireContext().getPackageManager();
            int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
            int i7 = z6 ? 1 : 2;
            if (componentEnabledSetting != i7) {
                packageManager.setComponentEnabledSetting(componentName, i7, 1);
                if (z6) {
                    ToastHelper.showLong(requireContext(), "权限助手图标已启用，可能需要稍等片刻或重启启动器才能看到");
                } else {
                    ToastHelper.show(requireContext(), "权限助手图标已隐藏");
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void showAutoStartAppsDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(g4.q.dialog_auto_start_apps, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(g4.p.recycler_auto_start_apps_dialog);
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("自启应用管理");
        nVar.u(inflate);
        nVar.r("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new i(this, 0));
        e7.show();
        loadAutoStartApps(recyclerView);
    }

    private void showCarSettingsDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(g4.q.dialog_auto_start_apps, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(g4.p.dialog_title);
        if (textView != null) {
            textView.setText("请选择需要自启的配置");
        }
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(g4.p.recycler_auto_start_apps_dialog);
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AppInfo(MobileHudService.CONNECTION_MODE_WIFI, "WiFi", false));
        arrayList.add(new AppInfo("hotspot", "热点", false));
        arrayList.add(new AppInfo(MobileHudService.CONNECTION_MODE_BLUETOOTH, "蓝牙", false));
        recyclerView.setAdapter(new CarSettingAdapter(requireContext(), arrayList, new f(this)));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("自启车机设置");
        nVar.u(inflate);
        nVar.r("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new i(this, 1));
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChangeNameDialog(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter) {
        EditText editText = new EditText(requireContext());
        editText.setSingleLine(true);
        editText.setText(this.sharedPreferences.getString("app_custom_name_" + appInfo.getPackageName(), appInfo.getAppName()));
        editText.setSelectAllOnFocus(true);
        FrameLayout frameLayout = new FrameLayout(requireContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        layoutParams.rightMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        frameLayout.addView(editText, layoutParams);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("修改应用名称");
        nVar.u(frameLayout);
        nVar.r("确定", new a(this, editText, appInfo, panelAppSettingsAdapter, 0));
        nVar.o("取消", null);
        int i7 = 0;
        nVar.p("恢复默认", new b(this, appInfo, panelAppSettingsAdapter, i7));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        editText.requestFocus();
        if (e7.getWindow() != null) {
            e7.getWindow().setSoftInputMode(5);
        }
        editText.post(new c(editText, i7));
        DialogImeNavBarHelper.install(e7, editText);
    }

    private void showPanelAppsSettingsDialog() {
        List<AppInfo> enabledPanelAppsInOrder = getEnabledPanelAppsInOrder();
        if (enabledPanelAppsInOrder.isEmpty()) {
            ToastHelper.show(requireContext(), "请先在面板应用中选择应用");
            return;
        }
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        int i7 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        linearLayout.setPadding(i7, i7, i7, i7);
        TextView textView = new TextView(requireContext());
        textView.setText("长按应用所在行，拖动可排序");
        textView.setTextSize(2, 13.0f);
        textView.setTextColor(-7829368);
        textView.setPadding(0, 0, 0, i7);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        PanelAppSettingsAdapter panelAppSettingsAdapter = new PanelAppSettingsAdapter(requireContext(), enabledPanelAppsInOrder, new PanelAppSettingsAdapter.OnIconChangeRequestListener() { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.8
            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeIcon(AppInfo appInfo) {
                AdditionalFeaturesFragment.this.pendingIconChangePackageName = appInfo.getPackageName();
                AdditionalFeaturesFragment.this.startChooseIcon();
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeName(AppInfo appInfo) {
                AdditionalFeaturesFragment additionalFeaturesFragment = AdditionalFeaturesFragment.this;
                additionalFeaturesFragment.showChangeNameDialog(appInfo, additionalFeaturesFragment.panelAppSettingsAdapter);
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestRestoreIcon(AppInfo appInfo) {
                AdditionalFeaturesFragment.this.restoreAppIcon(appInfo);
            }
        });
        this.panelAppSettingsAdapter = panelAppSettingsAdapter;
        recyclerView.setAdapter(panelAppSettingsAdapter);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.n0(3, 0) { // from class: com.navi.tool.ui.extra.AdditionalFeaturesFragment.9
            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition2 == -1 || !AdditionalFeaturesFragment.this.panelAppSettingsAdapter.onItemMove(adapterPosition, adapterPosition2)) {
                    return false;
                }
                AdditionalFeaturesFragment additionalFeaturesFragment = AdditionalFeaturesFragment.this;
                additionalFeaturesFragment.savePanelAppOrder(additionalFeaturesFragment.panelAppSettingsAdapter.getCurrentPackageOrder());
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i8) {
            }
        }).f(recyclerView);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("设置面板应用");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        e7.show();
    }

    private void showScreenOnAutoStartAppsDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(g4.q.dialog_auto_start_apps, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(g4.p.dialog_title);
        if (textView != null) {
            textView.setText("亮屏自启应用");
        }
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(g4.p.recycler_auto_start_apps_dialog);
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("亮屏自启应用");
        nVar.u(inflate);
        nVar.r("关闭", null);
        nVar.e().show();
        new Thread(new e(this, recyclerView, 0)).start();
    }

    private void showSelectPanelAppsDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        RadioGroup radioGroup = new RadioGroup(requireContext());
        radioGroup.setOrientation(0);
        RadioButton radioButton = new RadioButton(requireContext());
        radioButton.setText("全部");
        radioButton.setId(View.generateViewId());
        radioButton.setChecked(true);
        RadioButton radioButton2 = new RadioButton(requireContext());
        radioButton2.setText("用户应用");
        radioButton2.setId(View.generateViewId());
        RadioButton radioButton3 = new RadioButton(requireContext());
        radioButton3.setText("系统应用");
        radioButton3.setId(View.generateViewId());
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        radioGroup.addView(radioButton3);
        linearLayout.addView(radioGroup, new LinearLayout.LayoutParams(-1, -2));
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("选择面板应用");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new i(this, 2));
        e7.show();
        new Thread(new j4(this, recyclerView, radioGroup, radioButton2, radioButton3, 3)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startChooseIcon() {
        if (!hasStoragePermission()) {
            ToastHelper.show(requireContext(), "请先授予存储权限");
            return;
        }
        ensureIconFolderExists();
        Intent intent = new Intent(requireContext(), (Class<?>) FileManagerActivity.class);
        intent.setAction(FileManagerActivity.ACTION_PICK_FILE);
        this.chooseIconLauncher.a(intent);
    }

    private void updateAutoStartCount() {
        if (this.textAutoStartCount == null) {
            return;
        }
        if (!SharedPreferencesUtils.isAutoStartEnabled(requireContext())) {
            this.textAutoStartCount.setVisibility(8);
            return;
        }
        int autoStartAppCount = SharedPreferencesUtils.getAutoStartAppCount(requireContext());
        if (autoStartAppCount < 1) {
            this.textAutoStartCount.setVisibility(8);
        } else {
            a.a.v("已配置自启应用：", autoStartAppCount, "个", this.textAutoStartCount);
            this.textAutoStartCount.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCarSettingsCount() {
        if (this.textCarSettingsCount == null) {
            return;
        }
        if (!SharedPreferencesUtils.isAutoStartEnabled(requireContext())) {
            this.textCarSettingsCount.setVisibility(8);
            return;
        }
        int carSettingAutoStartCount = SharedPreferencesUtils.getCarSettingAutoStartCount(requireContext());
        if (carSettingAutoStartCount < 1) {
            this.textCarSettingsCount.setVisibility(8);
        } else {
            a.a.v("已配置", carSettingAutoStartCount, "个车机配置", this.textCarSettingsCount);
            this.textCarSettingsCount.setVisibility(0);
        }
    }

    private void updateControlsVisibility() {
        SwitchMaterial switchMaterial = this.switchGestureActions;
        boolean z6 = switchMaterial != null && switchMaterial.isChecked();
        SwitchMaterial switchMaterial2 = this.switchRightSwipe;
        boolean z7 = switchMaterial2 != null && switchMaterial2.isChecked();
        SwitchMaterial switchMaterial3 = this.switchUpSwipe;
        boolean z8 = switchMaterial3 != null && switchMaterial3.isChecked();
        SwitchMaterial switchMaterial4 = this.switchDownSwipe;
        boolean z9 = switchMaterial4 != null && switchMaterial4.isChecked();
        MaterialCardView materialCardView = this.permissionStatusContainer;
        int i7 = 8;
        if (materialCardView != null) {
            materialCardView.setVisibility(z6 ? 8 : 0);
        }
        MaterialCardView materialCardView2 = this.gestureTriggerContainer;
        if (materialCardView2 != null) {
            materialCardView2.setVisibility(z6 ? 0 : 8);
        }
        MaterialCardView materialCardView3 = this.gestureMirrorContainer;
        if (materialCardView3 != null) {
            materialCardView3.setVisibility(z6 ? 0 : 8);
        }
        MaterialCardView materialCardView4 = this.gestureActionsDetailContainer;
        if (materialCardView4 != null) {
            materialCardView4.setVisibility(z6 ? 0 : 8);
        }
        MaterialCardView materialCardView5 = this.appPanelContainer;
        if (materialCardView5 != null) {
            materialCardView5.setVisibility(z6 ? 0 : 8);
        }
        RadioGroup radioGroup = this.radioGroupRightAction;
        if (radioGroup != null) {
            radioGroup.setVisibility((z6 && z7) ? 0 : 8);
        }
        RadioGroup radioGroup2 = this.radioGroupUpAction;
        if (radioGroup2 != null) {
            radioGroup2.setVisibility((z6 && z8) ? 0 : 8);
        }
        RadioGroup radioGroup3 = this.radioGroupDownAction;
        if (radioGroup3 != null) {
            if (z6 && z9) {
                i7 = 0;
            }
            radioGroup3.setVisibility(i7);
        }
        MaterialRadioButton materialRadioButton = this.radioRightActionBack;
        if (materialRadioButton != null) {
            materialRadioButton.setEnabled(z6 && z7);
        }
        MaterialRadioButton materialRadioButton2 = this.radioRightActionPanel;
        if (materialRadioButton2 != null) {
            materialRadioButton2.setEnabled(z6 && z7);
        }
        MaterialRadioButton materialRadioButton3 = this.radioRightActionHome;
        if (materialRadioButton3 != null) {
            materialRadioButton3.setEnabled(z6 && z7);
        }
        MaterialRadioButton materialRadioButton4 = this.radioRightActionDesktop;
        if (materialRadioButton4 != null) {
            materialRadioButton4.setEnabled(z6 && z7);
        }
        MaterialRadioButton materialRadioButton5 = this.radioUpActionBack;
        if (materialRadioButton5 != null) {
            materialRadioButton5.setEnabled(z6 && z8);
        }
        MaterialRadioButton materialRadioButton6 = this.radioUpActionPanel;
        if (materialRadioButton6 != null) {
            materialRadioButton6.setEnabled(z6 && z8);
        }
        MaterialRadioButton materialRadioButton7 = this.radioUpActionHome;
        if (materialRadioButton7 != null) {
            materialRadioButton7.setEnabled(z6 && z8);
        }
        MaterialRadioButton materialRadioButton8 = this.radioUpActionDesktop;
        if (materialRadioButton8 != null) {
            materialRadioButton8.setEnabled(z6 && z8);
        }
        MaterialRadioButton materialRadioButton9 = this.radioDownActionBack;
        if (materialRadioButton9 != null) {
            materialRadioButton9.setEnabled(z6 && z9);
        }
        MaterialRadioButton materialRadioButton10 = this.radioDownActionPanel;
        if (materialRadioButton10 != null) {
            materialRadioButton10.setEnabled(z6 && z9);
        }
        MaterialRadioButton materialRadioButton11 = this.radioDownActionHome;
        if (materialRadioButton11 != null) {
            materialRadioButton11.setEnabled(z6 && z9);
        }
        MaterialRadioButton materialRadioButton12 = this.radioDownActionDesktop;
        if (materialRadioButton12 != null) {
            materialRadioButton12.setEnabled(z6 && z9);
        }
    }

    private void updateFileAssistantState(boolean z6, boolean z7) {
        SwitchMaterial switchMaterial = this.switchFileAssistant;
        if (switchMaterial != null) {
            boolean z8 = z6 && z7;
            switchMaterial.setEnabled(z8);
            if (z8 || !this.switchFileAssistant.isChecked()) {
                return;
            }
            this.switchFileAssistant.setChecked(false);
        }
    }

    private void updateGestureSwitchEnabledState() {
        boolean z6 = Settings.canDrawOverlays(requireContext()) && GestureAccessibilityService.isEnabled(requireContext()) && hasStoragePermission();
        this.switchGestureActions.setEnabled(z6);
        if (z6) {
            return;
        }
        this.switchGestureActions.setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePanelAlphaText(int i7) {
        TextView textView = this.textPanelAlpha;
        if (textView != null) {
            a.a.v("面板背景透明度：", i7, "%", textView);
        }
    }

    private void updatePanelAppsCount() {
        if (this.textPanelAppsCount == null) {
            return;
        }
        int size = getEnabledPanelAppsInOrder().size();
        if (size < 1) {
            this.textPanelAppsCount.setVisibility(8);
        } else {
            a.a.v("已配置面板应用：", size, "个", this.textPanelAppsCount);
            this.textPanelAppsCount.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePanelTimeoutText(int i7) {
        TextView textView = this.textPanelTimeout;
        if (textView != null) {
            a.a.v("面板自动隐藏时间：", i7, "秒", textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePanelWidthText(int i7) {
        TextView textView = this.textPanelWidth;
        if (textView != null) {
            a.a.v("面板宽度：", i7, "% 可调范围", textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePermissionStatus() {
        int i7;
        if (!isAdded() || getContext() == null || getView() == null) {
            return;
        }
        boolean canDrawOverlays = Settings.canDrawOverlays(requireContext());
        boolean isEnabled = GestureAccessibilityService.isEnabled(requireContext());
        if (!isEnabled && (i7 = this.mAccessibilityRepairAttempts) < 2) {
            this.mAccessibilityRepairAttempts = i7 + 1;
            if (PermissionManagerActivity.grantAccessibility(requireContext(), requireContext().getPackageName())) {
                this.permissionHandler.removeCallbacks(this.permissionRefreshRunnable);
                this.permissionHandler.postDelayed(this.permissionRefreshRunnable, 700L);
            }
        }
        if (isEnabled) {
            this.mAccessibilityRepairAttempts = 0;
        }
        boolean hasStoragePermission = hasStoragePermission();
        boolean hasInstallPermission = hasInstallPermission();
        TextView textView = this.textOverlayPermissionStatus;
        if (textView != null) {
            textView.setText(canDrawOverlays ? "悬浮窗权限：已授予" : "悬浮窗权限：未授予");
            Context requireContext = requireContext();
            int i8 = canDrawOverlays ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj = g0.g.f6423a;
            this.textOverlayPermissionStatus.setTextColor(g0.d.a(requireContext, i8));
        }
        TextView textView2 = this.textAccessibilityPermissionStatus;
        if (textView2 != null) {
            textView2.setText(isEnabled ? "无障碍服务：已启用" : "无障碍服务：未启用");
            Context requireContext2 = requireContext();
            int i9 = isEnabled ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj2 = g0.g.f6423a;
            this.textAccessibilityPermissionStatus.setTextColor(g0.d.a(requireContext2, i9));
        }
        TextView textView3 = this.textStoragePermissionStatus;
        if (textView3 != null) {
            textView3.setText(hasStoragePermission ? "存储权限：已授予" : "存储权限：未授予");
            Context requireContext3 = requireContext();
            int i10 = hasStoragePermission ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj3 = g0.g.f6423a;
            this.textStoragePermissionStatus.setTextColor(g0.d.a(requireContext3, i10));
        }
        TextView textView4 = this.textInstallPermissionStatus;
        if (textView4 != null) {
            textView4.setText(hasInstallPermission ? "安装应用权限：已授予" : "安装应用权限：未授予");
            Context requireContext4 = requireContext();
            int i11 = hasInstallPermission ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj4 = g0.g.f6423a;
            this.textInstallPermissionStatus.setTextColor(g0.d.a(requireContext4, i11));
        }
        updateFileAssistantState(hasStoragePermission, hasInstallPermission);
        SwitchMaterial switchMaterial = this.switchAutoStart;
        if (switchMaterial != null) {
            switchMaterial.setEnabled(canDrawOverlays);
            if (canDrawOverlays || !this.switchAutoStart.isChecked()) {
                return;
            }
            this.switchAutoStart.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSensitivityText(int i7) {
        TextView textView = this.textGestureSensitivity;
        if (textView != null) {
            textView.setText("手势灵敏度：" + i7);
        }
    }

    private void updateStrWakeSwitchVisibility() {
        SwitchMaterial switchMaterial;
        SwitchMaterial switchMaterial2 = this.switchAutoStart;
        boolean z6 = switchMaterial2 != null && switchMaterial2.isChecked() && (switchMaterial = this.switchScreenOnAutoStart) != null && switchMaterial.isChecked();
        View view = this.containerStrWakeAutoStart;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        View view2 = this.dividerStrWakeAutoStart;
        if (view2 != null) {
            view2.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTriggerHeightText(int i7) {
        TextView textView = this.textTriggerHeight;
        if (textView != null) {
            a.a.v("触发区高度：", i7, "% 屏幕高度", textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTriggerPositionText(int i7) {
        TextView textView = this.textTriggerPosition;
        if (textView != null) {
            if (i7 < 33) {
                textView.setText("触发区位置：偏下");
            } else if (i7 > 66) {
                textView.setText("触发区位置：偏上");
            } else {
                textView.setText("触发区位置：居中");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTriggerWidthText(int i7) {
        TextView textView = this.textTriggerWidth;
        if (textView != null) {
            textView.setText("触发区宽度：调节范围");
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(g4.q.fragment_additional_features, viewGroup, false);
    }

    @Override // androidx.fragment.app.z
    public void onDestroyView() {
        this.permissionHandler.removeCallbacks(this.permissionRefreshRunnable);
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.z
    public void onPause() {
        this.permissionHandler.removeCallbacks(this.permissionRefreshRunnable);
        super.onPause();
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        boolean isAutoStartEnabled;
        super.onResume();
        updatePermissionStatus();
        updateGestureSwitchEnabledState();
        updateControlsVisibility();
        SwitchMaterial switchMaterial = this.switchGestureActions;
        if (switchMaterial != null && switchMaterial.isChecked()) {
            ensureIconFolderExists();
        }
        if (this.switchAutoStart != null && this.switchAutoStart.isChecked() != (isAutoStartEnabled = SharedPreferencesUtils.isAutoStartEnabled(requireContext()))) {
            this.switchAutoStart.setChecked(isAutoStartEnabled);
        }
        updateStrWakeSwitchVisibility();
        updateAutoStartCount();
        updateCarSettingsCount();
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 64) == 0) {
            view.setVisibility(8);
            return;
        }
        this.sharedPreferences = SharedPreferencesUtils.getAppPrefs(requireContext());
        initViews(view);
        initIconChooser();
        initSwitchAndSeekBars();
        updatePermissionStatus();
        updateGestureSwitchEnabledState();
        updateControlsVisibility();
    }
}
