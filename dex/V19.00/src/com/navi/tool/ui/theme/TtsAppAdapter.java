package com.navi.tool.ui.theme;

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
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.o;
import g4.p;
import g4.q;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TtsAppAdapter extends h1 {
    private List<AppInfo> appList = new ArrayList();
    private final Context context;
    private final OnAppToggleListener listener;

    public interface OnAppToggleListener {
        void onToggle(AppInfo appInfo, boolean z6);
    }

    public static class ViewHolder extends j2 {
        ImageView appIcon;
        TextView appName;
        TextView appPackage;
        SwitchMaterial switchEnable;

        public ViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(p.appIcon);
            this.appName = (TextView) view.findViewById(p.appName);
            this.appPackage = (TextView) view.findViewById(p.appPackage);
            this.switchEnable = (SwitchMaterial) view.findViewById(p.switchEnable);
        }
    }

    public TtsAppAdapter(Context context, OnAppToggleListener onAppToggleListener) {
        this.context = context;
        this.listener = onAppToggleListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(AppInfo appInfo, CompoundButton compoundButton, boolean z6) {
        OnAppToggleListener onAppToggleListener = this.listener;
        if (onAppToggleListener != null) {
            onAppToggleListener.onToggle(appInfo, z6);
        }
        SharedPreferencesUtils.setTtsAppEnabled(this.context, appInfo.getPackageName(), z6);
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.appList.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        final AppInfo appInfo = this.appList.get(i7);
        viewHolder.appName.setText(appInfo.getAppName());
        viewHolder.appPackage.setText(appInfo.getPackageName());
        try {
            viewHolder.appIcon.setImageDrawable(this.context.getPackageManager().getApplicationIcon(appInfo.getPackageName()));
        } catch (Exception unused) {
            viewHolder.appIcon.setImageResource(o.ic_launcher_foreground);
        }
        viewHolder.switchEnable.setChecked(SharedPreferencesUtils.isTtsAppEnabled(this.context, appInfo.getPackageName()));
        viewHolder.switchEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.navi.tool.ui.theme.m
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
                TtsAppAdapter.this.lambda$onBindViewHolder$0(appInfo, compoundButton, z6);
            }
        });
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_tts_app, viewGroup, false));
    }

    public void refreshAppList() {
        notifyDataSetChanged();
    }

    public void updateApps(List<AppInfo> list) {
        if (list == null) {
            this.appList = new ArrayList();
        } else {
            this.appList = list;
        }
        notifyDataSetChanged();
    }
}
