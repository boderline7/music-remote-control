package com.navi.tool.ui.adapter;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.navi.tool.model.AppInfo;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.o;
import g4.p;
import g4.q;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DockAppAdapter extends h1 {
    private static final String KEY_FLOATING_MAP_DISPLAY_MODE = "desktop_map_display_mode";
    private static final String KEY_THEME_MODE = "theme_mode";
    private static final String KEY_WALLPAPER_DISPLAY_MODE = "desktop_wallpaper_display_mode";
    private static final int MAP_DISPLAY_MODE_MAP = 0;
    private static final String PKG_FLOATING_MAP_MODE = "com.navi.tool.floatingmapmode";
    private static final String PKG_THEME_MODE = "com.navi.tool.thememode";
    private static final String PKG_WALLPAPER_MODE = "com.navi.tool.wallpapermode";
    private static final int THEME_DARK = 1;
    private static final int THEME_LIGHT = 0;
    private static final int THEME_SYSTEM = 2;
    private static final int WALLPAPER_MODE_DYNAMIC = 0;
    private final List<AppInfo> apps;
    private final Context context;
    private boolean isNightMode = false;
    private final OnAppClickListener listener;
    private final PackageManager pm;

    public interface OnAppClickListener {
        void onAppClick(AppInfo appInfo);
    }

    public static class ViewHolder extends j2 {
        final ImageView iconView;
        final TextView nameView;

        public ViewHolder(View view) {
            super(view);
            this.iconView = (ImageView) view.findViewById(p.app_icon);
            this.nameView = (TextView) view.findViewById(p.app_name);
        }

        private int getThemeModeIconRes(int i7) {
            return i7 != 0 ? i7 != 1 ? o.ic_theme_auto : o.ic_theme_dark : o.ic_theme_light;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$0(OnAppClickListener onAppClickListener, AppInfo appInfo, View view) {
            if (onAppClickListener != null) {
                onAppClickListener.onAppClick(appInfo);
            }
        }

        public void bind(AppInfo appInfo, OnAppClickListener onAppClickListener, PackageManager packageManager, boolean z6) {
            Context context = this.itemView.getContext();
            this.nameView.setText(appInfo.getAppName());
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(context);
            int i7 = 1;
            this.nameView.setVisibility(appPrefs.getBoolean("dock_app_show_name", true) ? 0 : 8);
            this.nameView.setTextColor(z6 ? -1711276033 : -1);
            this.iconView.setAlpha(z6 ? 0.6f : 1.0f);
            try {
                Drawable customAppIcon = AppDetectionUtils.getCustomAppIcon(context, appInfo.getPackageName());
                if (customAppIcon != null) {
                    this.iconView.setImageDrawable(customAppIcon);
                    this.iconView.clearColorFilter();
                } else if ("com.navi.tool.desktopassistant".equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(o.ic_settings);
                    this.iconView.setColorFilter(-1);
                } else if ("com.navi.tool.originalhome".equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(o.ic_original_home);
                    this.iconView.setColorFilter(-1);
                } else if ("com.navi.tool.action.adbswitch".equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(o.ic_adb_switch);
                    this.iconView.setColorFilter(-1);
                } else if ("com.navi.tool.fileassistant".equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(o.ic_folder_yellow);
                    this.iconView.clearColorFilter();
                } else if ("com.navi.tool.permissionassistant".equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(o.ic_security);
                    this.iconView.clearColorFilter();
                } else if (DockAppAdapter.PKG_THEME_MODE.equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(getThemeModeIconRes(appPrefs.getInt(DockAppAdapter.KEY_THEME_MODE, 2)));
                    this.iconView.setColorFilter(-1);
                } else if (DockAppAdapter.PKG_FLOATING_MAP_MODE.equals(appInfo.getPackageName())) {
                    Drawable floatingMapModeIcon = AppDetectionUtils.getFloatingMapModeIcon(context, appPrefs);
                    if (floatingMapModeIcon != null) {
                        this.iconView.setImageDrawable(floatingMapModeIcon);
                        this.iconView.clearColorFilter();
                    } else {
                        this.iconView.setImageResource(appPrefs.getInt(DockAppAdapter.KEY_FLOATING_MAP_DISPLAY_MODE, 0) == 0 ? o.ic_floating_map_mode : o.ic_floating_calendar_mode);
                        this.iconView.setColorFilter(-1);
                    }
                } else if (DockAppAdapter.PKG_WALLPAPER_MODE.equals(appInfo.getPackageName())) {
                    this.iconView.setImageResource(appPrefs.getInt(DockAppAdapter.KEY_WALLPAPER_DISPLAY_MODE, 0) == 0 ? o.ic_wallpaper_dynamic_mode : o.ic_wallpaper_static_mode);
                    this.iconView.setColorFilter(-1);
                } else {
                    this.iconView.clearColorFilter();
                    Drawable appIcon = AppDetectionUtils.getAppIcon(context, appInfo.getPackageName());
                    if (appIcon != null) {
                        this.iconView.setImageDrawable(appIcon);
                    } else {
                        this.iconView.setImageResource(R.drawable.sym_def_app_icon);
                    }
                }
            } catch (Exception unused) {
                this.iconView.clearColorFilter();
                this.iconView.setImageResource(R.drawable.sym_def_app_icon);
            }
            this.itemView.setOnClickListener(new d(onAppClickListener, appInfo, i7));
        }
    }

    public DockAppAdapter(Context context, List<AppInfo> list, OnAppClickListener onAppClickListener) {
        this.context = context;
        this.apps = list;
        this.listener = onAppClickListener;
        this.pm = context.getPackageManager();
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.apps.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        viewHolder.bind(this.apps.get(i7), this.listener, this.pm, this.isNightMode);
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_dock_app, viewGroup, false));
    }

    public void setNightMode(boolean z6) {
        this.isNightMode = z6;
        notifyDataSetChanged();
    }
}
