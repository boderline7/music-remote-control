package com.navi.tool.ui.extra;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.utils.CrashReportCollector;
import com.navi.tool.utils.CrashReportUploader;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MiscSettingsFragment extends androidx.fragment.app.z {
    private static final String ACTION_NAVI_TOOL = "NAVI.TOOL.ACTION";
    private static final String KEY_DPI = "config_dpi";
    private static final String KEY_MODE = "config_mode";
    private static final String PREF_NAME = "amap_config_pref";
    private static final String TAG = "MiscSettingsFragment";
    private Button buttonUploadCrashLog;
    private CheckBox checkEnableDpiEdit;
    private EditText editDpiValue;
    private RadioGroup radioGroupDisplayMode;
    private TextView textConfiguredDpi;
    private TextView textCrashUploadStatus;
    private TextView textCurrentMode;
    private boolean crashUploadRunning = false;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private void applyDisplayMode(int i7) {
    }

    private void applyDpiConfig() {
        int i7;
        EditText editText = this.editDpiValue;
        if (editText == null || this.textConfiguredDpi == null) {
            return;
        }
        String c7 = editText.getText() != null ? a.a.c(this.editDpiValue) : s5.h0.FRAGMENT_ENCODE_SET;
        CheckBox checkBox = this.checkEnableDpiEdit;
        if (checkBox == null || !checkBox.isChecked()) {
            ToastHelper.show(requireContext(), "已执行应用操作（未修改DPI）");
            i7 = 0;
        } else {
            if (TextUtils.isEmpty(c7)) {
                ToastHelper.show(requireContext(), "DPI配置数值不能为空");
                return;
            }
            try {
                i7 = Integer.parseInt(c7);
                if (i7 < 100) {
                    i7 = 100;
                }
                if (i7 > 160) {
                    i7 = 160;
                }
                String format = String.format("%03d", Integer.valueOf(i7));
                this.editDpiValue.setText(format);
                saveDpi(format);
                updateConfiguredDpiText();
            } catch (NumberFormatException unused) {
                ToastHelper.show(requireContext(), "请输入3位数字");
                return;
            }
        }
        sendConfigBroadcast(i7);
        sendAmapBroadcast();
        launchAmapCarWithDelay();
    }

    private String getStoredDpi() {
        try {
            return requireContext().getSharedPreferences(PREF_NAME, 0).getString(KEY_DPI, s5.h0.FRAGMENT_ENCODE_SET);
        } catch (Exception unused) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private int getStoredMode() {
        try {
            return requireContext().getSharedPreferences(PREF_NAME, 0).getInt(KEY_MODE, 0);
        } catch (Exception unused) {
            return 0;
        }
    }

    private void initDisplayModeUI() {
        updateDisplayModeState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        applyDpiConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$1(View view) {
        uploadCrashReport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postCrashUi$3(boolean z6, String str, CrashReportUploader.Result result) {
        if (!isAdded()) {
            this.crashUploadRunning = false;
            return;
        }
        if (z6) {
            this.crashUploadRunning = false;
            Button button = this.buttonUploadCrashLog;
            if (button != null) {
                button.setEnabled(true);
                this.buttonUploadCrashLog.setText("一键抓取并上传闪退日志");
            }
        }
        TextView textView = this.textCrashUploadStatus;
        if (textView != null) {
            textView.setText(str);
        }
        if (z6 && result != null && result.ok) {
            Toast.makeText(requireContext(), "闪退日志已上传：" + result.id, 1).show();
            return;
        }
        if (!z6 || result == null) {
            return;
        }
        Toast.makeText(requireContext(), "上传失败：" + result.error, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDisplayModeState$4(RadioGroup radioGroup, int i7) {
        int i8;
        String string = getString(g4.u.display_mode_default);
        if (i7 == g4.p.radio_mode_immersive) {
            string = getString(g4.u.display_mode_immersive);
            i8 = 1006;
        } else if (i7 == g4.p.radio_mode_transparent) {
            string = getString(g4.u.display_mode_transparent);
            i8 = 700;
        } else {
            i8 = 0;
        }
        saveMode(i8);
        this.textCurrentMode.setText(String.format(getString(g4.u.current_mode_fmt), string));
        ToastHelper.show(requireContext(), "模式已选择，请点击下方应用按钮生效");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadCrashReport$2(Context context) {
        String str;
        CrashReportCollector.Report collect = CrashReportCollector.collect(context);
        String str2 = collect.logcat;
        if (str2.isEmpty()) {
            str2 = collect.installedApps;
        }
        String str3 = str2;
        if (str3.isEmpty()) {
            postCrashUi("未找到闪退/ANR 日志", true, null);
            return;
        }
        postCrashUi("正在上传闪退报告…", false, null);
        String licenseUid = SharedPreferencesUtils.getLicenseUid(context);
        if (TextUtils.isEmpty(licenseUid)) {
            licenseUid = NativeBridge.getSystemVin();
        }
        CrashReportUploader.Result upload = CrashReportUploader.upload(context, licenseUid, "crash_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date()) + ".txt", collect.summary, str3, collect.crash);
        if (upload.ok) {
            str = "上传成功，报告编号：" + upload.id;
        } else {
            str = "上传失败：" + upload.error;
        }
        postCrashUi(str, true, upload);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchAmapCar() {
        ResolveInfo resolveInfo;
        try {
            Context requireContext = requireContext();
            PackageManager packageManager = requireContext.getPackageManager();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    resolveInfo = null;
                    break;
                }
                resolveInfo = it.next();
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && "com.autonavi.amapcar".equals(activityInfo.packageName)) {
                    break;
                }
            }
            if (resolveInfo == null) {
                return;
            }
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            ActivityInfo activityInfo2 = resolveInfo.activityInfo;
            intent2.setClassName(activityInfo2.packageName, activityInfo2.name);
            intent2.addFlags(872546304);
            requireContext.startActivity(intent2);
        } catch (Exception unused) {
        }
    }

    private void launchAmapCarWithDelay() {
        new Handler(Looper.getMainLooper()).postDelayed(new f4(9, this), PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
    }

    private void postCrashUi(String str, boolean z6, CrashReportUploader.Result result) {
        this.handler.post(new j2(this, z6, str, result, 1));
    }

    private void saveDpi(String str) {
        try {
            requireContext().getSharedPreferences(PREF_NAME, 0).edit().putString(KEY_DPI, str).apply();
        } catch (Exception unused) {
        }
    }

    private void saveMode(int i7) {
        try {
            requireContext().getSharedPreferences(PREF_NAME, 0).edit().putInt(KEY_MODE, i7).apply();
        } catch (Exception unused) {
        }
    }

    private void sendAmapBroadcast() {
        try {
            Intent intent = new Intent(Auto3DBroadcastReceiver.AUTONAVI_STANDARD_BROADCAST_RECV);
            intent.setClassName("com.autonavi.amapcar", Auto3DBroadcastReceiver.AMAP_AUTO_BROADCAST_RECEIVER);
            intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, 10021);
            requireContext().sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    private void sendConfigBroadcast(int i7) {
        Intent intent = new Intent(ACTION_NAVI_TOOL);
        if (i7 > 0) {
            intent.putExtra("DPI", i7);
        }
        int storedMode = getStoredMode();
        if (storedMode == 1006) {
            intent.putExtra("ImmersiveMode", 1006);
        } else if (storedMode == 700) {
            intent.putExtra("ImmersiveMode", 700);
            intent.putExtra("statusBarColor", 0);
            intent.putExtra("navigationBarColor", 0);
            intent.putExtra("DysmorphismInfo", 80);
        }
        try {
            requireContext().sendBroadcast(intent);
            ToastHelper.show(requireContext(), "配置已应用");
        } catch (Exception e7) {
            ToastHelper.show(requireContext(), "广播发送失败: " + e7.getMessage());
        }
    }

    private void updateConfiguredDpiText() {
        if (this.textConfiguredDpi == null) {
            return;
        }
        String str = getString(g4.u.gaode_dpi_configured_label) + "：";
        String storedDpi = getStoredDpi();
        if (TextUtils.isEmpty(storedDpi)) {
            TextView textView = this.textConfiguredDpi;
            StringBuilder j7 = a.a.j(str);
            j7.append(getString(g4.u.gaode_dpi_not_configured));
            textView.setText(j7.toString());
            return;
        }
        this.textConfiguredDpi.setText(str + storedDpi);
    }

    private void updateDisplayModeState() {
        String string;
        int storedMode = getStoredMode();
        getString(g4.u.display_mode_default);
        this.radioGroupDisplayMode.setOnCheckedChangeListener(null);
        this.radioGroupDisplayMode.clearCheck();
        if (storedMode == 1006) {
            string = getString(g4.u.display_mode_immersive);
            this.radioGroupDisplayMode.check(g4.p.radio_mode_immersive);
        } else if (storedMode == 700) {
            string = getString(g4.u.display_mode_transparent);
            this.radioGroupDisplayMode.check(g4.p.radio_mode_transparent);
        } else {
            string = getString(g4.u.display_mode_default);
            this.radioGroupDisplayMode.check(g4.p.radio_mode_default);
        }
        this.textCurrentMode.setText(String.format(getString(g4.u.current_mode_fmt), string));
        this.radioGroupDisplayMode.setOnCheckedChangeListener(new s4(0, this));
    }

    private void uploadCrashReport() {
        Button button = this.buttonUploadCrashLog;
        if (button == null || this.textCrashUploadStatus == null || this.crashUploadRunning) {
            return;
        }
        this.crashUploadRunning = true;
        button.setEnabled(false);
        this.buttonUploadCrashLog.setText("正在抓取并上传…");
        this.textCrashUploadStatus.setText("正在抓取现场 logcat…");
        new Thread(new z0(18, this, requireContext().getApplicationContext())).start();
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(g4.q.fragment_misc_settings, viewGroup, false);
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & SharedPreferencesUtils.CAP_FEATURES) == 0) {
            view.setVisibility(8);
            return;
        }
        this.editDpiValue = (EditText) view.findViewById(g4.p.edit_gaode_dpi_value);
        this.checkEnableDpiEdit = (CheckBox) view.findViewById(g4.p.checkbox_enable_dpi_edit);
        this.textConfiguredDpi = (TextView) view.findViewById(g4.p.text_gaode_dpi_configured);
        Button button = (Button) view.findViewById(g4.p.button_apply_gaode_dpi);
        this.textCurrentMode = (TextView) view.findViewById(g4.p.text_current_mode);
        this.radioGroupDisplayMode = (RadioGroup) view.findViewById(g4.p.radio_group_display_mode);
        updateConfiguredDpiText();
        initDisplayModeUI();
        final int i7 = 0;
        button.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t4

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MiscSettingsFragment f5476b;

            {
                this.f5476b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i8 = i7;
                MiscSettingsFragment miscSettingsFragment = this.f5476b;
                switch (i8) {
                    case 0:
                        miscSettingsFragment.lambda$onViewCreated$0(view2);
                        break;
                    default:
                        miscSettingsFragment.lambda$onViewCreated$1(view2);
                        break;
                }
            }
        });
        this.buttonUploadCrashLog = (Button) view.findViewById(g4.p.button_upload_crash_log);
        this.textCrashUploadStatus = (TextView) view.findViewById(g4.p.text_crash_upload_status);
        Button button2 = this.buttonUploadCrashLog;
        if (button2 != null) {
            final int i8 = 1;
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t4

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ MiscSettingsFragment f5476b;

                {
                    this.f5476b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i82 = i8;
                    MiscSettingsFragment miscSettingsFragment = this.f5476b;
                    switch (i82) {
                        case 0:
                            miscSettingsFragment.lambda$onViewCreated$0(view2);
                            break;
                        default:
                            miscSettingsFragment.lambda$onViewCreated$1(view2);
                            break;
                    }
                }
            });
        }
    }
}
