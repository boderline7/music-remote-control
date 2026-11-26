package com.navi.tool.ui.adapter;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.model.AppInfo;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.o;
import g4.p;
import g4.q;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarSettingAdapter extends h1 {
    private final Context context;
    private final OnSettingChangeListener listener;
    private final List<AppInfo> settings;

    public interface OnSettingChangeListener {
        void onSettingChanged();
    }

    public static class ViewHolder extends j2 {
        ImageView appIcon;
        TextView appName;
        TextView appPackage;
        View btnDelay;
        SwitchMaterial switchAutoStart;

        public ViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(p.app_icon);
            this.appName = (TextView) view.findViewById(p.app_name);
            this.appPackage = (TextView) view.findViewById(p.app_package);
            this.switchAutoStart = (SwitchMaterial) view.findViewById(p.switch_app_auto_start);
            this.btnDelay = view.findViewById(p.btn_delay);
        }
    }

    public CarSettingAdapter(Context context, List<AppInfo> list, OnSettingChangeListener onSettingChangeListener) {
        this.context = context;
        this.settings = list;
        this.listener = onSettingChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(AppInfo appInfo, CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setCarSettingAutoStartEnabled(this.context, appInfo.getPackageName(), z6);
        OnSettingChangeListener onSettingChangeListener = this.listener;
        if (onSettingChangeListener != null) {
            onSettingChangeListener.onSettingChanged();
        }
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.settings.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        AppInfo appInfo = this.settings.get(i7);
        viewHolder.appName.setText(appInfo.getAppName());
        viewHolder.appPackage.setVisibility(8);
        viewHolder.btnDelay.setVisibility(8);
        String packageName = appInfo.getPackageName();
        if (MobileHudService.CONNECTION_MODE_WIFI.equals(packageName)) {
            viewHolder.appIcon.setImageResource(o.ic_car_setting_wifi);
        } else if ("hotspot".equals(packageName)) {
            viewHolder.appIcon.setImageResource(o.ic_car_setting_hotspot);
        } else if (MobileHudService.CONNECTION_MODE_BLUETOOTH.equals(packageName)) {
            viewHolder.appIcon.setImageResource(o.ic_car_setting_bluetooth);
        } else {
            viewHolder.appIcon.setImageResource(R.drawable.sym_def_app_icon);
        }
        boolean isCarSettingAutoStartEnabled = SharedPreferencesUtils.isCarSettingAutoStartEnabled(this.context, appInfo.getPackageName());
        viewHolder.switchAutoStart.setOnCheckedChangeListener(null);
        viewHolder.switchAutoStart.setChecked(isCarSettingAutoStartEnabled);
        viewHolder.switchAutoStart.setOnCheckedChangeListener(new c(this, appInfo, 1));
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_auto_start_app, viewGroup, false));
    }
}
