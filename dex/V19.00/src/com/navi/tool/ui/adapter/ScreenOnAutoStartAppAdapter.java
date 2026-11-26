package com.navi.tool.ui.adapter;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.p;
import g4.q;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ScreenOnAutoStartAppAdapter extends h1 {
    private final List<AppInfo> apps;
    private final Context context;

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

    public ScreenOnAutoStartAppAdapter(Context context, List<AppInfo> list) {
        this.context = context;
        this.apps = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(AppInfo appInfo, CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setScreenOnAutoStartAppEnabled(this.context, appInfo.getPackageName(), z6);
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.apps.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        AppInfo appInfo = this.apps.get(i7);
        viewHolder.appName.setText(appInfo.getAppName());
        viewHolder.appPackage.setText(appInfo.getPackageName());
        Drawable appIcon = AppDetectionUtils.getAppIcon(this.context, appInfo.getPackageName());
        if (appIcon != null) {
            viewHolder.appIcon.setImageDrawable(appIcon);
        } else {
            viewHolder.appIcon.setImageResource(R.drawable.sym_def_app_icon);
        }
        viewHolder.btnDelay.setVisibility(8);
        viewHolder.switchAutoStart.setOnCheckedChangeListener(null);
        viewHolder.switchAutoStart.setChecked(SharedPreferencesUtils.isScreenOnAutoStartAppEnabled(this.context, appInfo.getPackageName()));
        viewHolder.switchAutoStart.setOnCheckedChangeListener(new c(this, appInfo, 2));
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_auto_start_app, viewGroup, false));
    }
}
