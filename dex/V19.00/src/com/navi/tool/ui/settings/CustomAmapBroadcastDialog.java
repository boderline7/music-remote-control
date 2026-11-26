package com.navi.tool.ui.settings;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.appcompat.app.n;
import androidx.fragment.app.q;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.NavigationStatusSimulatorService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import e1.a;
import e1.c;
import g4.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CustomAmapBroadcastDialog extends q {
    private EditText broadcastValueEditText;
    private LinearLayout clusterCrossMaskLayout;
    private Switch clusterCrossMaskSwitch;
    private Context context;
    private Switch customBroadcastSwitch;
    private Button saveButton;

    /* JADX INFO: Access modifiers changed from: private */
    public void restartAllServices() {
        final Intent intent = new Intent(this.context, (Class<?>) AmapBroadcastService.class);
        this.context.stopService(intent);
        final Intent intent2 = new Intent(this.context, (Class<?>) ThemeMonitorService.class);
        this.context.stopService(intent2);
        new Handler().postDelayed(new Runnable() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.6
            @Override // java.lang.Runnable
            public void run() {
                CustomAmapBroadcastDialog.this.context.startForegroundService(intent);
                CustomAmapBroadcastDialog.this.context.startForegroundService(intent2);
                ToastHelper.show(CustomAmapBroadcastDialog.this.context, "服务已重启");
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRestartDialog() {
        n nVar = new n(this.context);
        nVar.t("确认重启");
        nVar.m("关闭自定义广播将重启所有服务，是否继续？");
        nVar.r("确定", new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i7) {
                SharedPreferencesUtils.setCustomAmapBroadcastEnabled(CustomAmapBroadcastDialog.this.context, false);
                SharedPreferencesUtils.setClusterCrossMaskEnabled(CustomAmapBroadcastDialog.this.context, false);
                CustomAmapBroadcastDialog.this.clusterCrossMaskSwitch.setChecked(false);
                AmapBroadcastConstants.resetAmapBroadcast();
                CustomAmapBroadcastDialog.this.context.stopService(new Intent(CustomAmapBroadcastDialog.this.context, (Class<?>) NavigationStatusSimulatorService.class));
                CustomAmapBroadcastDialog.this.restartAllServices();
            }
        });
        nVar.o("取消", new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i7) {
                CustomAmapBroadcastDialog.this.customBroadcastSwitch.setChecked(true);
                CustomAmapBroadcastDialog.this.broadcastValueEditText.setText(SharedPreferencesUtils.getCustomAmapBroadcastValue(CustomAmapBroadcastDialog.this.context));
            }
        });
        nVar.v();
    }

    @Override // androidx.lifecycle.k
    public c getDefaultViewModelCreationExtras() {
        return a.INSTANCE;
    }

    @Override // androidx.fragment.app.q
    public Dialog onCreateDialog(Bundle bundle) {
        this.context = getActivity();
        View inflate = getActivity().getLayoutInflater().inflate(g4.q.dialog_custom_amap_broadcast, (ViewGroup) null);
        this.customBroadcastSwitch = (Switch) inflate.findViewById(p.switch_custom_broadcast);
        this.clusterCrossMaskLayout = (LinearLayout) inflate.findViewById(p.layout_cluster_cross_mask);
        this.clusterCrossMaskSwitch = (Switch) inflate.findViewById(p.switch_cluster_cross_mask);
        this.broadcastValueEditText = (EditText) inflate.findViewById(p.edit_broadcast_value);
        this.saveButton = (Button) inflate.findViewById(p.btn_save);
        boolean isCustomAmapBroadcastEnabled = SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this.context);
        String customAmapBroadcastValue = SharedPreferencesUtils.getCustomAmapBroadcastValue(this.context);
        this.customBroadcastSwitch.setChecked(isCustomAmapBroadcastEnabled);
        this.broadcastValueEditText.setText(customAmapBroadcastValue);
        this.broadcastValueEditText.setEnabled(isCustomAmapBroadcastEnabled);
        this.saveButton.setEnabled(isCustomAmapBroadcastEnabled);
        this.clusterCrossMaskSwitch.setChecked(SharedPreferencesUtils.isClusterCrossMaskEnabled(this.context));
        this.clusterCrossMaskLayout.setVisibility(isCustomAmapBroadcastEnabled ? 0 : 8);
        this.customBroadcastSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                CustomAmapBroadcastDialog.this.broadcastValueEditText.setEnabled(z6);
                CustomAmapBroadcastDialog.this.saveButton.setEnabled(z6);
                CustomAmapBroadcastDialog.this.clusterCrossMaskLayout.setVisibility(z6 ? 0 : 8);
                if (z6) {
                    CustomAmapBroadcastDialog.this.broadcastValueEditText.setText(AmapBroadcastConstants.DEFAULT_CUSTOM_AMAP_BROADCAST);
                } else {
                    CustomAmapBroadcastDialog.this.broadcastValueEditText.setText(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
                    CustomAmapBroadcastDialog.this.showRestartDialog();
                }
            }
        });
        this.saveButton.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String trim = CustomAmapBroadcastDialog.this.broadcastValueEditText.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastHelper.show(CustomAmapBroadcastDialog.this.context, "广播名称不能为空");
                    return;
                }
                SharedPreferencesUtils.setCustomAmapBroadcastEnabled(CustomAmapBroadcastDialog.this.context, true);
                SharedPreferencesUtils.setCustomAmapBroadcastValue(CustomAmapBroadcastDialog.this.context, trim);
                SharedPreferencesUtils.setClusterCrossMaskEnabled(CustomAmapBroadcastDialog.this.context, CustomAmapBroadcastDialog.this.clusterCrossMaskSwitch.isChecked());
                AmapBroadcastConstants.setAmapBroadcast(trim);
                ToastHelper.show(CustomAmapBroadcastDialog.this.context, "保存成功，正在重启服务...");
                CustomAmapBroadcastDialog.this.context.startForegroundService(new Intent(CustomAmapBroadcastDialog.this.context, (Class<?>) NavigationStatusSimulatorService.class));
                CustomAmapBroadcastDialog.this.restartAllServices();
                CustomAmapBroadcastDialog.this.dismiss();
            }
        });
        n nVar = new n(getActivity());
        nVar.t("自定义高德广播设置");
        nVar.u(inflate);
        nVar.o("取消", new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.settings.CustomAmapBroadcastDialog.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i7) {
                boolean isCustomAmapBroadcastEnabled2 = SharedPreferencesUtils.isCustomAmapBroadcastEnabled(CustomAmapBroadcastDialog.this.context);
                String customAmapBroadcastValue2 = SharedPreferencesUtils.getCustomAmapBroadcastValue(CustomAmapBroadcastDialog.this.context);
                if (isCustomAmapBroadcastEnabled2) {
                    AmapBroadcastConstants.setAmapBroadcast(customAmapBroadcastValue2);
                } else {
                    AmapBroadcastConstants.resetAmapBroadcast();
                }
                CustomAmapBroadcastDialog.this.dismiss();
            }
        });
        return nVar.e();
    }
}
