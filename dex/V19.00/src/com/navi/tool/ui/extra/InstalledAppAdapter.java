package com.navi.tool.ui.extra;

import android.R;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class InstalledAppAdapter extends androidx.recyclerview.widget.h1 {
    private List<ApplicationInfo> appList;
    private Context context;
    private OnAppClickListener listener;
    private PackageManager packageManager;
    private boolean showPackageName = true;
    private Integer appNameColor = null;
    private int iconSize = 0;

    public interface OnAppClickListener {
        void onAppClick(ApplicationInfo applicationInfo);
    }

    public static class ViewHolder extends androidx.recyclerview.widget.j2 {
        ImageView appIcon;
        TextView appName;
        TextView appPackage;

        public ViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(g4.p.app_icon);
            this.appName = (TextView) view.findViewById(g4.p.app_name);
            this.appPackage = (TextView) view.findViewById(g4.p.app_package);
        }
    }

    public InstalledAppAdapter(Context context, List<ApplicationInfo> list, OnAppClickListener onAppClickListener) {
        this.context = context;
        this.appList = list;
        this.packageManager = context.getPackageManager();
        this.listener = onAppClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(ApplicationInfo applicationInfo, View view) {
        OnAppClickListener onAppClickListener = this.listener;
        if (onAppClickListener != null) {
            onAppClickListener.onAppClick(applicationInfo);
        }
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.appList.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        ApplicationInfo applicationInfo = this.appList.get(i7);
        String str = applicationInfo.packageName;
        if ("com.navi.tool.fileassistant".equals(str)) {
            viewHolder.appName.setText("文件助手");
        } else if ("com.navi.tool.permissionassistant".equals(str)) {
            viewHolder.appName.setText("权限助手");
        } else if ("com.navi.tool.desktopassistant".equals(str)) {
            viewHolder.appName.setText("桌面助手");
        } else {
            try {
                viewHolder.appName.setText(this.packageManager.getApplicationLabel(applicationInfo));
            } catch (Exception unused) {
                viewHolder.appName.setText(str);
            }
        }
        Integer num = this.appNameColor;
        if (num != null) {
            viewHolder.appName.setTextColor(num.intValue());
        }
        int i8 = 8;
        if (this.showPackageName) {
            viewHolder.appPackage.setVisibility(0);
            viewHolder.appPackage.setText(applicationInfo.packageName);
        } else {
            viewHolder.appPackage.setVisibility(8);
        }
        if (this.iconSize > 0) {
            ViewGroup.LayoutParams layoutParams = viewHolder.appIcon.getLayoutParams();
            int i9 = (int) (this.iconSize * this.context.getResources().getDisplayMetrics().density);
            layoutParams.width = i9;
            layoutParams.height = i9;
            viewHolder.appIcon.setLayoutParams(layoutParams);
        }
        try {
            Drawable drawable = null;
            String string = SharedPreferencesUtils.getAppPrefs(this.context).getString("gesture_panel_app_icon_" + applicationInfo.packageName, null);
            if (string != null) {
                try {
                    drawable = Drawable.createFromPath(string);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
            if (drawable == null) {
                drawable = "com.navi.tool.fileassistant".equals(str) ? this.context.getDrawable(g4.o.ic_folder_yellow) : "com.navi.tool.permissionassistant".equals(str) ? this.context.getDrawable(g4.o.ic_security) : "com.navi.tool.desktopassistant".equals(str) ? this.context.getDrawable(g4.o.ic_view_apps) : AppDetectionUtils.getAppIcon(this.context, str);
            }
            viewHolder.appIcon.setImageDrawable(drawable);
        } catch (Exception unused2) {
            viewHolder.appIcon.setImageResource(R.drawable.sym_def_app_icon);
        }
        viewHolder.itemView.setOnClickListener(new d1(i8, this, applicationInfo));
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_installed_app, viewGroup, false));
    }

    public void setAppNameColor(Integer num) {
        this.appNameColor = num;
        notifyDataSetChanged();
    }

    public void setIconSize(int i7) {
        this.iconSize = i7;
        notifyDataSetChanged();
    }

    public void setShowPackageName(boolean z6) {
        this.showPackageName = z6;
        notifyDataSetChanged();
    }
}
