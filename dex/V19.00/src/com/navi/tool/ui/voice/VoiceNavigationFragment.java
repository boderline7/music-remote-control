package com.navi.tool.ui.voice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.n;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h1;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.model.AppInfo;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.cast.ScreenCastingFloatingService;
import com.navi.tool.ui.common.DialogListDividers;
import com.navi.tool.ui.theme.h;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.NavigationStatusSimulatorService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import e0.f;
import g0.d;
import g0.g;
import g4.m;
import g4.p;
import g4.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class VoiceNavigationFragment extends z {
    private static final int READ_LOGS_PERMISSION_REQUEST_CODE = 1001;
    private static final String TAG = "VoiceNavigationFragment";
    private View amapAutoBgContainer;
    private Button buttonRestartApp;
    private Button buttonReturnAppCustomize;
    private Button buttonSelectNativeAmap;
    private View delayedNavContainer;
    private View instructionContainer;
    private boolean isServiceRunning = false;
    private View manualRouteSelectContainer;
    private View nativeAmapMenuContainer;
    private View permissionStatusContainer;
    private TextView permissionStatusText;
    private View personalizedNavigationContainer;
    private View postActionContainer;
    private RadioButton radioExitApp;
    private RadioButton radioExitState;
    private RadioGroup radioPostActionGroup;
    private RadioButton radioSteeringModeBg;
    private RadioButton radioSteeringModeFg;
    private RadioGroup radioSteeringModeGroup;
    private RecyclerView recyclerViewVoiceNavApps;
    private View restartServiceContainer;
    private View returnAppContainer;
    private View routeTransferContainer;
    private SeekBar seekAmapAutoBgDelay;
    private SeekBar seekDelayedNavDelay;
    private SeekBar seekReturnAppDelay;
    private SharedPreferences sharedPreferences;
    private View steeringDivider;
    private View steeringFlyingContainer;
    private SwitchMaterial switchAmapAutoBg;
    private SwitchMaterial switchDelayedNav;
    private SwitchMaterial switchManualRouteSelect;
    private SwitchMaterial switchNavigationPersonalized;
    private SwitchMaterial switchPostAction;
    private SwitchMaterial switchReturnApp;
    private SwitchMaterial switchRouteTransfer;
    private SwitchMaterial switchSteeringFlying;
    private SwitchMaterial switchTraditionalCluster;
    private TextView textAmapAutoBgDelay;
    private TextView textDelayedNavDelay;
    private TextView textReturnAppDelay;
    private TextView textReturnAppSummary;
    private TextView textSelectedNativeAmap;
    private View traditionalClusterContainer;
    private AppAdapter voiceNavAppAdapter;
    private View voiceNavAppsContainer;
    private View voiceNavigationContainer;
    private SwitchMaterial voiceNavigationSwitch;

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPermissionAndStartService() {
        if (g.a(requireContext(), "android.permission.READ_LOGS") == 0) {
            startLogMonitorService();
            return;
        }
        this.voiceNavigationSwitch.setChecked(false);
        a.a.m(this.sharedPreferences, "voice_navigation_switch_state", false);
        updateVoiceNavigationSwitchByPermission();
    }

    private List<AppInfo> getLaunchableUserApps() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = requireContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (this.sharedPreferences.getBoolean("desktop_assistant_enabled", false)) {
            a.a.x("com.navi.tool.desktopassistant", "桌面助手", false, arrayList);
        }
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            if (!str.equals(requireContext().getPackageName())) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                    if ((applicationInfo.flags & 1) == 0) {
                        arrayList.add(new AppInfo(str, packageManager.getApplicationLabel(applicationInfo).toString(), false));
                    }
                } catch (Exception unused) {
                }
            }
        }
        arrayList.sort(new m0.a(8));
        return arrayList;
    }

    private void initFeatureControls() {
        int amapAutoBackgroundDelaySeconds = SharedPreferencesUtils.getAmapAutoBackgroundDelaySeconds(requireContext());
        TextView textView = this.textAmapAutoBgDelay;
        if (textView != null) {
            a.a.v("时间: ", amapAutoBackgroundDelaySeconds, "秒", textView);
        }
        SeekBar seekBar = this.seekAmapAutoBgDelay;
        int i7 = 5;
        if (seekBar != null) {
            seekBar.setProgress(amapAutoBackgroundDelaySeconds - 5);
            this.seekAmapAutoBgDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.voice.VoiceNavigationFragment.2
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i8, boolean z6) {
                    int i9 = i8 + 5;
                    if (VoiceNavigationFragment.this.textAmapAutoBgDelay != null) {
                        a.a.v("时间: ", i9, "秒", VoiceNavigationFragment.this.textAmapAutoBgDelay);
                    }
                    SharedPreferencesUtils.setAmapAutoBackgroundDelaySeconds(VoiceNavigationFragment.this.requireContext(), i9);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                }
            });
        }
        if (this.switchAmapAutoBg != null) {
            this.switchAmapAutoBg.setChecked(SharedPreferencesUtils.isAmapAutoBackgroundEnabled(requireContext()));
            this.switchAmapAutoBg.setOnCheckedChangeListener(new a(this, 3));
        }
        if (this.switchNavigationPersonalized != null) {
            this.switchNavigationPersonalized.setChecked(SharedPreferencesUtils.isNavigationPersonalizedEnabled(requireContext()));
            this.switchNavigationPersonalized.setOnCheckedChangeListener(new a(this, 4));
        }
        int i8 = 1;
        if (this.switchManualRouteSelect != null) {
            this.switchManualRouteSelect.setChecked(SharedPreferencesUtils.isManualRouteSelectEnabled(requireContext()));
            this.switchManualRouteSelect.setEnabled(!SharedPreferencesUtils.isRouteTransferEnabled(requireContext()));
            this.switchManualRouteSelect.setOnCheckedChangeListener(new a(this, i7));
        }
        int delayedNavDelaySeconds = SharedPreferencesUtils.getDelayedNavDelaySeconds(requireContext());
        TextView textView2 = this.textDelayedNavDelay;
        if (textView2 != null) {
            a.a.v("延时: ", delayedNavDelaySeconds, "秒", textView2);
        }
        SeekBar seekBar2 = this.seekDelayedNavDelay;
        if (seekBar2 != null) {
            seekBar2.setProgress(delayedNavDelaySeconds - 5);
            this.seekDelayedNavDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.voice.VoiceNavigationFragment.3
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar3, int i9, boolean z6) {
                    int i10 = i9 + 5;
                    if (VoiceNavigationFragment.this.textDelayedNavDelay != null) {
                        a.a.v("延时: ", i10, "秒", VoiceNavigationFragment.this.textDelayedNavDelay);
                    }
                    SharedPreferencesUtils.setDelayedNavDelaySeconds(VoiceNavigationFragment.this.requireContext(), i10);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar3) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar3) {
                }
            });
        }
        if (this.switchDelayedNav != null) {
            this.switchDelayedNav.setChecked(SharedPreferencesUtils.isDelayedNavEnabled(requireContext()));
        }
        if (this.switchSteeringFlying != null) {
            boolean isSteeringWheelExtScreenEnabled = SharedPreferencesUtils.isSteeringWheelExtScreenEnabled(requireContext());
            this.switchSteeringFlying.setChecked(isSteeringWheelExtScreenEnabled);
            RadioGroup radioGroup = this.radioSteeringModeGroup;
            if (radioGroup != null) {
                radioGroup.setVisibility(isSteeringWheelExtScreenEnabled ? 0 : 8);
            }
            View view = this.traditionalClusterContainer;
            if (view != null) {
                view.setVisibility(isSteeringWheelExtScreenEnabled ? 0 : 8);
            }
            View view2 = this.steeringDivider;
            if (view2 != null) {
                view2.setVisibility(isSteeringWheelExtScreenEnabled ? 0 : 8);
            }
            if (this.radioSteeringModeGroup != null && this.radioSteeringModeBg != null && this.radioSteeringModeFg != null) {
                if (SharedPreferencesUtils.isSteeringWheelBackgroundMode(requireContext())) {
                    this.radioSteeringModeBg.setChecked(true);
                } else {
                    this.radioSteeringModeFg.setChecked(true);
                }
                this.radioSteeringModeGroup.setOnCheckedChangeListener(new c(this, i8));
            }
            if (this.switchTraditionalCluster != null) {
                this.switchTraditionalCluster.setChecked(SharedPreferencesUtils.isTraditionalClusterEnabled(requireContext()));
                this.switchTraditionalCluster.setOnCheckedChangeListener(new a(this, 6));
            }
            this.switchSteeringFlying.setOnCheckedChangeListener(new a(this, 7));
        }
    }

    private void initRestartControls() {
        Button button = this.buttonRestartApp;
        if (button != null) {
            button.setOnClickListener(new b(this, 1));
        }
        updateRestartVisibility();
    }

    private void initReturnAppControls() {
        int i7 = 0;
        if (this.switchReturnApp != null) {
            boolean isReturnAppEnabled = SharedPreferencesUtils.isReturnAppEnabled(requireContext());
            this.switchReturnApp.setChecked(isReturnAppEnabled);
            this.switchReturnApp.setOnCheckedChangeListener(new a(this, i7));
            Button button = this.buttonReturnAppCustomize;
            if (button != null) {
                button.setVisibility(isReturnAppEnabled ? 0 : 8);
            }
            TextView textView = this.textReturnAppSummary;
            if (textView != null) {
                textView.setVisibility(isReturnAppEnabled ? 0 : 8);
            }
        }
        int returnAppDelaySeconds = SharedPreferencesUtils.getReturnAppDelaySeconds(requireContext());
        TextView textView2 = this.textReturnAppDelay;
        if (textView2 != null) {
            a.a.v("延时: ", returnAppDelaySeconds, "秒", textView2);
        }
        SeekBar seekBar = this.seekReturnAppDelay;
        if (seekBar != null) {
            seekBar.setProgress(returnAppDelaySeconds - 5);
            this.seekReturnAppDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.voice.VoiceNavigationFragment.4
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i8, boolean z6) {
                    int i9 = i8 + 5;
                    if (VoiceNavigationFragment.this.textReturnAppDelay != null) {
                        a.a.v("延时: ", i9, "秒", VoiceNavigationFragment.this.textReturnAppDelay);
                    }
                    SharedPreferencesUtils.setReturnAppDelaySeconds(VoiceNavigationFragment.this.requireContext(), i9);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                }
            });
        }
        if (this.returnAppContainer != null) {
            this.returnAppContainer.setVisibility(this.sharedPreferences.getBoolean("voice_navigation_switch_state", false) ? 0 : 8);
        }
        updateReturnAppSummary();
        Button button2 = this.buttonReturnAppCustomize;
        if (button2 != null) {
            button2.setOnClickListener(new b(this, i7));
        }
    }

    private void initRouteTransferControls() {
        boolean isRouteTransferEnabled = SharedPreferencesUtils.isRouteTransferEnabled(requireContext());
        SwitchMaterial switchMaterial = this.switchRouteTransfer;
        int i7 = 1;
        if (switchMaterial != null) {
            switchMaterial.setChecked(isRouteTransferEnabled);
            this.switchRouteTransfer.setOnCheckedChangeListener(new a(this, i7));
        }
        updateRouteTransferUi(isRouteTransferEnabled);
        Button button = this.buttonSelectNativeAmap;
        int i8 = 2;
        if (button != null) {
            button.setOnClickListener(new b(this, i8));
        }
        int i9 = 0;
        if (this.switchPostAction != null) {
            boolean isRouteTransferPostActionEnabled = SharedPreferencesUtils.isRouteTransferPostActionEnabled(requireContext());
            this.switchPostAction.setChecked(isRouteTransferPostActionEnabled);
            RadioGroup radioGroup = this.radioPostActionGroup;
            if (radioGroup != null) {
                radioGroup.setVisibility(isRouteTransferPostActionEnabled ? 0 : 8);
            }
            this.switchPostAction.setOnCheckedChangeListener(new a(this, i8));
        }
        if (this.radioExitApp != null && this.radioExitState != null) {
            if (SharedPreferencesUtils.getRouteTransferPostActionType(requireContext()) == 10010) {
                this.radioExitState.setChecked(true);
            } else {
                this.radioExitApp.setChecked(true);
            }
            this.radioPostActionGroup.setOnCheckedChangeListener(new c(this, i9));
        }
        updateSelectedNativeAmapText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getLaunchableUserApps$16(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$1(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setAmapAutoBackgroundEnabled(requireContext(), z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$2(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setNavigationPersonalizedEnabled(requireContext(), z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$3(CompoundButton compoundButton, boolean z6) {
        if (!SharedPreferencesUtils.isRouteTransferEnabled(requireContext()) || !z6) {
            SharedPreferencesUtils.setManualRouteSelectEnabled(requireContext(), z6);
        } else {
            this.switchManualRouteSelect.setChecked(false);
            SharedPreferencesUtils.setManualRouteSelectEnabled(requireContext(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$4(RadioGroup radioGroup, int i7) {
        SharedPreferencesUtils.setSteeringWheelBackgroundMode(requireContext(), i7 == p.radio_steering_mode_bg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$5(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setTraditionalClusterEnabled(requireContext(), z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFeatureControls$6(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setSteeringWheelExtScreenEnabled(requireContext(), z6);
        RadioGroup radioGroup = this.radioSteeringModeGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(z6 ? 0 : 8);
        }
        View view = this.traditionalClusterContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        View view2 = this.steeringDivider;
        if (view2 != null) {
            view2.setVisibility(z6 ? 0 : 8);
        }
        Intent intent = new Intent(requireContext(), (Class<?>) LogMonitorService.class);
        intent.setAction(LogMonitorService.ACTION_UPDATE_STEERING_FLYING);
        requireContext().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRestartControls$17(DialogInterface dialogInterface, int i7) {
        ToastHelper.show(requireContext(), "正在重启APP...");
        restartApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRestartControls$18(View view) {
        n nVar = new n(requireContext());
        nVar.t("确认重启");
        nVar.m("重启后需要手动重新打开应用，是否继续？");
        nVar.r("重启", new com.navi.tool.ui.log.n(this, 1));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initReturnAppControls$13(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setReturnAppEnabled(requireContext(), z6);
        Button button = this.buttonReturnAppCustomize;
        if (button != null) {
            button.setVisibility(z6 ? 0 : 8);
        }
        TextView textView = this.textReturnAppSummary;
        if (textView != null) {
            textView.setVisibility(z6 ? 0 : 8);
        }
        if (z6) {
            updateReturnAppSummary();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initReturnAppControls$14(View view) {
        showReturnAppSelectionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRouteTransferControls$10(RadioGroup radioGroup, int i7) {
        SharedPreferencesUtils.setRouteTransferPostActionType(requireContext(), i7 == p.radio_exit_state ? 10010 : 10021);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRouteTransferControls$7(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setRouteTransferEnabled(requireContext(), z6);
        updateRouteTransferUi(z6);
        if (!z6) {
            SwitchMaterial switchMaterial = this.switchManualRouteSelect;
            if (switchMaterial != null) {
                switchMaterial.setEnabled(true);
            }
            stopRouteTransferService();
            return;
        }
        SwitchMaterial switchMaterial2 = this.switchManualRouteSelect;
        if (switchMaterial2 != null) {
            if (switchMaterial2.isChecked()) {
                this.switchManualRouteSelect.setChecked(false);
                SharedPreferencesUtils.setManualRouteSelectEnabled(requireContext(), false);
            }
            this.switchManualRouteSelect.setEnabled(false);
        }
        SwitchMaterial switchMaterial3 = this.voiceNavigationSwitch;
        if (switchMaterial3 != null && switchMaterial3.isChecked()) {
            this.voiceNavigationSwitch.setChecked(false);
            a.a.m(this.sharedPreferences, "voice_navigation_switch_state", false);
            stopLogMonitorService();
        }
        startRouteTransferService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRouteTransferControls$8(View view) {
        showNativeAmapSelectionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRouteTransferControls$9(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setRouteTransferPostActionEnabled(requireContext(), z6);
        RadioGroup radioGroup = this.radioPostActionGroup;
        if (radioGroup != null) {
            radioGroup.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(AppInfo appInfo) {
        SharedPreferencesUtils.setVoiceNavAppEnabled(requireContext(), appInfo.getPackageName(), !appInfo.isEnabled());
        refreshVoiceNavApps();
        updateFeatureCards();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showNativeAmapSelectionDialog$11(List list, RecyclerView recyclerView, AppInfo appInfo) {
        String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(requireContext());
        if (nativeAmapSelectedPackage != null && nativeAmapSelectedPackage.equals(appInfo.getPackageName()) && appInfo.isEnabled()) {
            appInfo.setEnabled(false);
            SharedPreferencesUtils.setNativeAmapSelectedPackage(requireContext(), null);
            SharedPreferencesUtils.setNativeAmapSelectedName(requireContext(), null);
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((AppInfo) it.next()).setEnabled(false);
            }
            appInfo.setEnabled(true);
            SharedPreferencesUtils.setNativeAmapSelectedPackage(requireContext(), appInfo.getPackageName());
            SharedPreferencesUtils.setNativeAmapSelectedName(requireContext(), appInfo.getAppName());
        }
        h1 adapter = recyclerView.getAdapter();
        if (adapter instanceof AppAdapter) {
            AppAdapter appAdapter = (AppAdapter) adapter;
            appAdapter.submitList(new ArrayList(list));
            appAdapter.notifyDataSetChanged();
        }
        updateSelectedNativeAmapText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showReturnAppSelectionDialog$15(RecyclerView recyclerView, AppInfo appInfo) {
        boolean z6 = !appInfo.isEnabled();
        appInfo.setEnabled(z6);
        SharedPreferencesUtils.setReturnAppEnabled(requireContext(), appInfo.getPackageName(), z6);
        h1 adapter = recyclerView.getAdapter();
        if (adapter instanceof AppAdapter) {
            AppAdapter appAdapter = (AppAdapter) adapter;
            int indexOf = appAdapter.getCurrentList().indexOf(appInfo);
            if (indexOf >= 0) {
                appAdapter.notifyItemChanged(indexOf);
            }
        }
        updateReturnAppSummary();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        if (r5.equals(r2) == false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void refreshVoiceNavApps() {
        /*
            r9 = this;
            android.content.Context r0 = r9.requireContext()
            java.util.List r0 = com.navi.tool.utils.AppDetectionUtils.getInstalledNavigationApps(r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.content.Context r2 = r9.requireContext()
            boolean r2 = com.navi.tool.utils.SharedPreferencesUtils.isRouteTransferEnabled(r2)
            r3 = 0
            if (r2 == 0) goto L21
            android.content.Context r2 = r9.requireContext()
            java.lang.String r2 = com.navi.tool.utils.SharedPreferencesUtils.getNativeAmapSelectedPackage(r2)
            goto L22
        L21:
            r2 = r3
        L22:
            java.util.Iterator r0 = r0.iterator()
        L26:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L64
            java.lang.Object r4 = r0.next()
            com.navi.tool.model.AppInfo r4 = (com.navi.tool.model.AppInfo) r4
            java.lang.String r5 = r4.getPackageName()
            boolean r6 = com.navi.tool.utils.AppDetectionUtils.isAmapPackage(r5)
            java.lang.String r7 = "com.tencent.wecarnavi"
            boolean r7 = r5.startsWith(r7)
            java.lang.String r8 = "com.baidu.navi"
            boolean r8 = r5.startsWith(r8)
            if (r6 != 0) goto L4c
            if (r7 != 0) goto L4c
            if (r8 == 0) goto L26
        L4c:
            if (r2 == 0) goto L55
            boolean r6 = r5.equals(r2)
            if (r6 == 0) goto L55
            goto L26
        L55:
            android.content.Context r6 = r9.requireContext()
            boolean r5 = com.navi.tool.utils.SharedPreferencesUtils.isVoiceNavAppEnabled(r6, r5)
            r4.setEnabled(r5)
            r1.add(r4)
            goto L26
        L64:
            com.navi.tool.ui.adapter.AppAdapter r0 = r9.voiceNavAppAdapter
            if (r0 == 0) goto L6b
            r0.submitList(r1)
        L6b:
            android.view.View r0 = r9.getView()
            if (r0 == 0) goto L7e
            android.view.View r0 = r9.getView()
            int r2 = g4.p.text_voice_nav_no_apps
            android.view.View r0 = r0.findViewById(r2)
            r3 = r0
            android.widget.TextView r3 = (android.widget.TextView) r3
        L7e:
            if (r3 == 0) goto L90
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L8b
            r0 = 0
            r3.setVisibility(r0)
            goto L90
        L8b:
            r0 = 8
            r3.setVisibility(r0)
        L90:
            r9.updateFeatureCards()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.voice.VoiceNavigationFragment.refreshVoiceNavApps():void");
    }

    private void restartApp() {
        Context requireContext = requireContext();
        AlarmManager alarmManager = (AlarmManager) requireContext.getSystemService("alarm");
        Intent intent = new Intent(requireContext, (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        PendingIntent activity = PendingIntent.getActivity(requireContext, 0, intent, 335544320);
        long currentTimeMillis = System.currentTimeMillis() + 500;
        if (alarmManager != null) {
            alarmManager.setExactAndAllowWhileIdle(1, currentTimeMillis, activity);
        }
        requireContext.stopService(new Intent(requireContext, (Class<?>) LogMonitorService.class));
        requireContext.stopService(new Intent(requireContext, (Class<?>) AmapBroadcastService.class));
        requireContext.stopService(new Intent(requireContext, (Class<?>) FloatingWindowService.class));
        requireContext.stopService(new Intent(requireContext, (Class<?>) NavigationStatusSimulatorService.class));
        requireContext.stopService(new Intent(requireContext, (Class<?>) ThemeMonitorService.class));
        requireContext.stopService(new Intent(requireContext, (Class<?>) ScreenCastingFloatingService.class));
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void showNativeAmapSelectionDialog() {
        n nVar = new n(requireContext());
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        int i7 = (int) ((getResources().getDisplayMetrics().density * 16.0f) + 0.5f);
        linearLayout.setPadding(i7, i7, i7, i7);
        TextView textView = new TextView(requireContext());
        textView.setText("选择自带高德");
        textView.setTextSize(18.0f);
        textView.setTypeface(textView.getTypeface(), 1);
        linearLayout.addView(textView);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = requireContext().getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(128)) {
            String str = packageInfo.packageName;
            if (str.startsWith("com.autonavi") || str.startsWith("com.lion.appfwk.navi")) {
                try {
                    arrayList.add(new AppInfo(str, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString(), SharedPreferencesUtils.getNativeAmapSelectedPackage(requireContext()) != null && str.equals(SharedPreferencesUtils.getNativeAmapSelectedPackage(requireContext()))));
                } catch (Exception unused) {
                }
            }
        }
        AppAdapter appAdapter = new AppAdapter(new com.navi.tool.service.g(this, arrayList, recyclerView));
        appAdapter.submitList(new ArrayList(arrayList));
        recyclerView.setAdapter(appAdapter);
        linearLayout.addView(recyclerView);
        nVar.u(linearLayout);
        nVar.r("关闭", new g4.g(18));
        nVar.v();
    }

    private void showReturnAppSelectionDialog() {
        n nVar = new n(requireContext());
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        int i7 = (int) ((getResources().getDisplayMetrics().density * 16.0f) + 0.5f);
        linearLayout.setPadding(i7, i7, i7, i7);
        TextView textView = new TextView(requireContext());
        textView.setText("选择返回的应用");
        textView.setTextSize(18.0f);
        textView.setTypeface(textView.getTypeface(), 1);
        linearLayout.addView(textView);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        AppAdapter appAdapter = new AppAdapter(new h(recyclerView, this));
        List<AppInfo> launchableUserApps = getLaunchableUserApps();
        for (AppInfo appInfo : launchableUserApps) {
            appInfo.setEnabled(SharedPreferencesUtils.isReturnAppEnabled(requireContext(), appInfo.getPackageName()));
        }
        appAdapter.submitList(launchableUserApps);
        recyclerView.setAdapter(appAdapter);
        linearLayout.addView(recyclerView);
        nVar.u(linearLayout);
        nVar.r("关闭", null);
        nVar.v();
    }

    private void startLogMonitorService() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogMonitorService.class);
        intent.setAction(LogMonitorService.ACTION_START_LOG_MONITORING);
        requireContext().startService(intent);
        this.isServiceRunning = true;
    }

    private void startRouteTransferService() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogMonitorService.class);
        intent.setAction(LogMonitorService.ACTION_START_ROUTE_TRANSFER);
        requireContext().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopLogMonitorService() {
        if (this.isServiceRunning) {
            Intent intent = new Intent(requireContext(), (Class<?>) LogMonitorService.class);
            intent.setAction(LogMonitorService.ACTION_STOP_LOG_MONITORING);
            requireContext().startService(intent);
            requireContext().stopService(intent);
            this.isServiceRunning = false;
            ToastHelper.show(requireContext(), "语音导航服务已停止");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRouteTransferService() {
        Intent intent = new Intent(requireContext(), (Class<?>) LogMonitorService.class);
        intent.setAction(LogMonitorService.ACTION_STOP_ROUTE_TRANSFER);
        requireContext().startService(intent);
    }

    private void updateFeatureCards() {
        SwitchMaterial switchMaterial;
        Iterator<AppInfo> it = AppDetectionUtils.getInstalledNavigationApps(requireContext()).iterator();
        boolean z6 = false;
        int i7 = 0;
        while (it.hasNext()) {
            String packageName = it.next().getPackageName();
            if (AppDetectionUtils.isAmapPackage(packageName) || packageName.startsWith(AppDetectionUtils.TENCENT_PREFIX) || packageName.startsWith(AppDetectionUtils.BAIDU_PREFIX)) {
                if (SharedPreferencesUtils.isVoiceNavAppEnabled(requireContext(), packageName)) {
                    i7++;
                    if (AppDetectionUtils.isAmapPackage(packageName)) {
                        z6 = true;
                    }
                }
            }
        }
        View view = this.amapAutoBgContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        if (z6 && (switchMaterial = this.switchAmapAutoBg) != null) {
            switchMaterial.setChecked(SharedPreferencesUtils.isAmapAutoBackgroundEnabled(requireContext()));
        }
        boolean z7 = i7 > 1;
        View view2 = this.delayedNavContainer;
        if (view2 != null) {
            view2.setVisibility(z7 ? 0 : 8);
        }
        if (z7) {
            SharedPreferencesUtils.setDelayedNavEnabled(requireContext(), true);
            SwitchMaterial switchMaterial2 = this.switchDelayedNav;
            if (switchMaterial2 != null) {
                switchMaterial2.setChecked(true);
                return;
            }
            return;
        }
        SharedPreferencesUtils.setDelayedNavEnabled(requireContext(), false);
        SwitchMaterial switchMaterial3 = this.switchDelayedNav;
        if (switchMaterial3 != null) {
            switchMaterial3.setChecked(false);
        }
    }

    private void updatePermissionStatus() {
        boolean isRouteTransferEnabled = SharedPreferencesUtils.isRouteTransferEnabled(requireContext());
        String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(requireContext());
        if (g.a(requireContext(), "android.permission.READ_LOGS") == 0) {
            this.permissionStatusText.setText("READ_LOGS权限: 已获取");
            this.permissionStatusText.setTextColor(d.a(requireContext(), m.permission_granted));
            updateVoiceNavAppVisibility(true);
            refreshVoiceNavApps();
            updateFeatureCards();
            return;
        }
        this.permissionStatusText.setText("READ_LOGS权限: 未获取");
        this.permissionStatusText.setTextColor(d.a(requireContext(), m.permission_denied));
        boolean z6 = (!isRouteTransferEnabled || nativeAmapSelectedPackage == null || nativeAmapSelectedPackage.isEmpty()) ? false : true;
        updateVoiceNavAppVisibility(z6);
        View view = this.amapAutoBgContainer;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.delayedNavContainer;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        if (z6) {
            refreshVoiceNavApps();
        }
    }

    private void updateRestartVisibility() {
        boolean z6 = g.a(requireContext(), "android.permission.READ_LOGS") == 0;
        View view = this.restartServiceContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateReturnAppSummary() {
        if (this.textReturnAppSummary == null) {
            return;
        }
        if (!SharedPreferencesUtils.isReturnAppEnabled(requireContext())) {
            this.textReturnAppSummary.setVisibility(8);
            return;
        }
        this.textReturnAppSummary.setVisibility(0);
        PackageManager packageManager = requireContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        if (SharedPreferencesUtils.isReturnAppEnabled(requireContext(), "com.navi.tool.desktopassistant") && this.sharedPreferences.getBoolean("desktop_assistant_enabled", false)) {
            arrayList.add("桌面助手");
        }
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            if (!str.equals(requireContext().getPackageName())) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                    if ((applicationInfo.flags & 1) == 0 && SharedPreferencesUtils.isReturnAppEnabled(requireContext(), str)) {
                        arrayList.add(packageManager.getApplicationLabel(applicationInfo).toString() + " (" + str + ")");
                    }
                } catch (Exception unused) {
                }
            }
        }
        if (arrayList.isEmpty()) {
            this.textReturnAppSummary.setText("未选择");
            return;
        }
        this.textReturnAppSummary.setText("已选择: " + String.join(", ", arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRouteTransferUi(boolean z6) {
        SwitchMaterial switchMaterial = this.voiceNavigationSwitch;
        boolean z7 = switchMaterial != null && switchMaterial.isChecked();
        View view = this.voiceNavigationContainer;
        if (view != null) {
            view.setVisibility(z6 ? 8 : 0);
        }
        View view2 = this.permissionStatusContainer;
        if (view2 != null) {
            view2.setVisibility((z6 || z7) ? 8 : 0);
        }
        View view3 = this.instructionContainer;
        if (view3 != null) {
            view3.setVisibility(z6 ? 8 : 0);
        }
        View view4 = this.restartServiceContainer;
        if (view4 != null) {
            view4.setVisibility(z6 ? 8 : 0);
        }
        View view5 = this.routeTransferContainer;
        if (view5 != null) {
            view5.setVisibility(z7 ? 8 : 0);
        }
        View view6 = this.nativeAmapMenuContainer;
        if (view6 != null) {
            view6.setVisibility(z6 ? 0 : 8);
        }
        View view7 = this.postActionContainer;
        if (view7 != null) {
            view7.setVisibility(z6 ? 0 : 8);
        }
        View view8 = this.returnAppContainer;
        if (view8 != null) {
            view8.setVisibility((z6 || z7) ? 0 : 8);
        }
        View view9 = this.personalizedNavigationContainer;
        if (view9 != null) {
            view9.setVisibility((!z7 || z6) ? 8 : 0);
        }
        View view10 = this.manualRouteSelectContainer;
        if (view10 != null) {
            view10.setVisibility((!z7 || z6) ? 8 : 0);
        }
        View view11 = this.steeringFlyingContainer;
        if (view11 != null) {
            view11.setVisibility((!z7 || z6) ? 8 : 0);
        }
        View view12 = this.traditionalClusterContainer;
        if (view12 != null) {
            view12.setVisibility((!z7 || z6) ? 8 : 0);
        }
        View view13 = this.steeringDivider;
        if (view13 != null) {
            view13.setVisibility((!z7 || z6) ? 8 : 0);
        }
        updateReturnAppSummary();
        updatePermissionStatus();
    }

    private void updateSelectedNativeAmapText() {
        String nativeAmapSelectedName = SharedPreferencesUtils.getNativeAmapSelectedName(requireContext());
        String nativeAmapSelectedPackage = SharedPreferencesUtils.getNativeAmapSelectedPackage(requireContext());
        TextView textView = this.textSelectedNativeAmap;
        if (textView != null) {
            if (nativeAmapSelectedName == null || nativeAmapSelectedPackage == null) {
                textView.setText("未选择");
            } else {
                textView.setText("选中的车机高德：" + nativeAmapSelectedName + " (" + nativeAmapSelectedPackage + ")");
            }
        }
        updatePermissionStatus();
    }

    private void updateVoiceNavAppVisibility(boolean z6) {
        View view = this.voiceNavAppsContainer;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateVoiceNavigationSwitchByPermission() {
        if (g.a(requireContext(), "android.permission.READ_LOGS") == 0) {
            this.voiceNavigationSwitch.setEnabled(true);
        } else {
            this.voiceNavigationSwitch.setEnabled(false);
            this.voiceNavigationSwitch.setChecked(false);
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(q.fragment_voice_navigation, viewGroup, false);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 4) == 0) {
            inflate.setVisibility(8);
            return inflate;
        }
        this.sharedPreferences = SharedPreferencesUtils.getAppPrefs(requireContext());
        this.voiceNavigationSwitch = (SwitchMaterial) inflate.findViewById(p.voice_navigation_switch);
        this.permissionStatusText = (TextView) inflate.findViewById(p.permission_status_text);
        this.recyclerViewVoiceNavApps = (RecyclerView) inflate.findViewById(p.recycler_view_voice_nav_apps);
        this.voiceNavAppsContainer = inflate.findViewById(p.voice_nav_apps_container);
        this.amapAutoBgContainer = inflate.findViewById(p.amap_auto_bg_container);
        this.switchAmapAutoBg = (SwitchMaterial) inflate.findViewById(p.switch_amap_auto_bg);
        this.seekAmapAutoBgDelay = (SeekBar) inflate.findViewById(p.seek_amap_auto_bg_delay);
        this.textAmapAutoBgDelay = (TextView) inflate.findViewById(p.text_amap_auto_bg_delay);
        this.delayedNavContainer = inflate.findViewById(p.delayed_nav_container);
        this.switchDelayedNav = (SwitchMaterial) inflate.findViewById(p.switch_delayed_nav);
        this.seekDelayedNavDelay = (SeekBar) inflate.findViewById(p.seek_delayed_nav_delay);
        this.textDelayedNavDelay = (TextView) inflate.findViewById(p.text_delayed_nav_delay);
        this.restartServiceContainer = inflate.findViewById(p.restart_service_container);
        this.buttonRestartApp = (Button) inflate.findViewById(p.button_restart_app);
        this.returnAppContainer = inflate.findViewById(p.return_app_container);
        this.switchReturnApp = (SwitchMaterial) inflate.findViewById(p.switch_return_app);
        this.textReturnAppSummary = (TextView) inflate.findViewById(p.text_return_app_summary);
        this.buttonReturnAppCustomize = (Button) inflate.findViewById(p.button_return_app_customize);
        this.seekReturnAppDelay = (SeekBar) inflate.findViewById(p.seek_return_app_delay);
        this.textReturnAppDelay = (TextView) inflate.findViewById(p.text_return_app_delay);
        this.switchRouteTransfer = (SwitchMaterial) inflate.findViewById(p.switch_route_transfer);
        this.voiceNavigationContainer = inflate.findViewById(p.voice_navigation_container);
        this.permissionStatusContainer = inflate.findViewById(p.permission_status_container);
        this.instructionContainer = inflate.findViewById(p.instruction_container);
        this.routeTransferContainer = inflate.findViewById(p.route_transfer_container);
        this.nativeAmapMenuContainer = inflate.findViewById(p.native_amap_menu_container);
        this.buttonSelectNativeAmap = (Button) inflate.findViewById(p.button_select_native_amap);
        this.textSelectedNativeAmap = (TextView) inflate.findViewById(p.text_selected_native_amap);
        this.postActionContainer = inflate.findViewById(p.post_action_container);
        this.switchPostAction = (SwitchMaterial) inflate.findViewById(p.switch_post_action);
        this.radioPostActionGroup = (RadioGroup) inflate.findViewById(p.radio_post_action_group);
        this.radioExitApp = (RadioButton) inflate.findViewById(p.radio_exit_app);
        this.radioExitState = (RadioButton) inflate.findViewById(p.radio_exit_state);
        this.personalizedNavigationContainer = inflate.findViewById(p.personalized_navigation_container);
        this.switchNavigationPersonalized = (SwitchMaterial) inflate.findViewById(p.switch_navigation_personalized);
        this.manualRouteSelectContainer = inflate.findViewById(p.manual_route_select_container);
        this.switchManualRouteSelect = (SwitchMaterial) inflate.findViewById(p.switch_manual_route_select);
        this.steeringFlyingContainer = inflate.findViewById(p.steering_flying_container);
        this.switchSteeringFlying = (SwitchMaterial) inflate.findViewById(p.switch_steering_flying);
        this.radioSteeringModeGroup = (RadioGroup) inflate.findViewById(p.radio_steering_mode_group);
        this.radioSteeringModeBg = (RadioButton) inflate.findViewById(p.radio_steering_mode_bg);
        this.radioSteeringModeFg = (RadioButton) inflate.findViewById(p.radio_steering_mode_fg);
        this.traditionalClusterContainer = inflate.findViewById(p.traditional_cluster_container);
        this.switchTraditionalCluster = (SwitchMaterial) inflate.findViewById(p.switch_traditional_cluster);
        this.steeringDivider = inflate.findViewById(p.steering_divider);
        RecyclerView recyclerView = this.recyclerViewVoiceNavApps;
        if (recyclerView != null) {
            getContext();
            recyclerView.setLayoutManager(new LinearLayoutManager(1));
            AppAdapter appAdapter = new AppAdapter(new f(11, this));
            this.voiceNavAppAdapter = appAdapter;
            this.recyclerViewVoiceNavApps.setAdapter(appAdapter);
        }
        updatePermissionStatus();
        initFeatureControls();
        initRestartControls();
        initReturnAppControls();
        initRouteTransferControls();
        boolean z6 = this.sharedPreferences.getBoolean("voice_navigation_switch_state", false);
        this.voiceNavigationSwitch.setChecked(z6);
        if (z6) {
            checkPermissionAndStartService();
        }
        updateRouteTransferUi(SharedPreferencesUtils.isRouteTransferEnabled(requireContext()));
        updateVoiceNavigationSwitchByPermission();
        this.voiceNavigationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.voice.VoiceNavigationFragment.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                VoiceNavigationFragment.this.sharedPreferences.edit().putBoolean("voice_navigation_switch_state", z7).apply();
                if (z7) {
                    VoiceNavigationFragment.this.checkPermissionAndStartService();
                    if (VoiceNavigationFragment.this.switchRouteTransfer == null || !VoiceNavigationFragment.this.switchRouteTransfer.isChecked()) {
                        VoiceNavigationFragment.this.updateRouteTransferUi(false);
                    } else {
                        SharedPreferencesUtils.setRouteTransferEnabled(VoiceNavigationFragment.this.requireContext(), false);
                        VoiceNavigationFragment.this.switchRouteTransfer.setChecked(false);
                        VoiceNavigationFragment.this.stopRouteTransferService();
                        VoiceNavigationFragment.this.updateRouteTransferUi(false);
                    }
                } else {
                    VoiceNavigationFragment.this.stopLogMonitorService();
                    VoiceNavigationFragment.this.updateRouteTransferUi(false);
                }
                if (VoiceNavigationFragment.this.returnAppContainer != null) {
                    VoiceNavigationFragment.this.returnAppContainer.setVisibility(z7 ? 0 : 8);
                }
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.z
    public void onDestroy() {
        super.onDestroy();
        SwitchMaterial switchMaterial = this.voiceNavigationSwitch;
        if (switchMaterial == null || switchMaterial.isChecked()) {
            return;
        }
        stopLogMonitorService();
    }

    @Override // androidx.fragment.app.z
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        updatePermissionStatus();
        updateFeatureCards();
        updateRestartVisibility();
        updateRouteTransferUi(SharedPreferencesUtils.isRouteTransferEnabled(requireContext()));
        if (LogMonitorService.isServiceRunning()) {
            this.isServiceRunning = true;
        }
        updateVoiceNavigationSwitchByPermission();
    }
}
