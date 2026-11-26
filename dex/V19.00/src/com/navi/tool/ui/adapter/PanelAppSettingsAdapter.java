package com.navi.tool.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import g4.o;
import g4.p;
import g4.q;
import g4.s;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PanelAppSettingsAdapter extends h1 {
    private static final String KEY_FLOATING_MAP_DISPLAY_MODE = "desktop_map_display_mode";
    private static final int MAP_DISPLAY_MODE_MAP = 0;
    private final List<AppInfo> appList;
    private final Context context;
    private final OnIconChangeRequestListener listener;
    private boolean showDockSwitch = false;
    private static final String PKG_FLOATING_MAP_MODE = "com.navi.tool.floatingmapmode";
    private static final Set<String> DEFAULT_DOCK_APPS = new HashSet(Arrays.asList("com.navi.tool.originalhome", "com.navi.tool.desktopassistant", PKG_FLOATING_MAP_MODE, "com.navi.tool.wallpapermode"));

    public interface OnIconChangeRequestListener {
        void onRequestChangeIcon(AppInfo appInfo);

        void onRequestChangeName(AppInfo appInfo);

        void onRequestRestoreIcon(AppInfo appInfo);
    }

    public static class ViewHolder extends j2 {
        private final ImageView appIconView;
        private final TextView appNameView;
        private final TextView appPackageView;
        private final Button changeIconButton;
        private final Button changeNameButton;
        private final Switch dockSwitch;
        private final Button restoreIconButton;

        public ViewHolder(View view) {
            super(view);
            this.appIconView = (ImageView) view.findViewById(p.image_app_icon);
            this.appNameView = (TextView) view.findViewById(p.text_app_name);
            this.appPackageView = (TextView) view.findViewById(p.text_app_package);
            this.changeIconButton = (Button) view.findViewById(p.button_change_icon);
            this.changeNameButton = (Button) view.findViewById(p.button_change_name);
            this.restoreIconButton = (Button) view.findViewById(p.button_restore_icon);
            this.dockSwitch = (Switch) view.findViewById(p.switch_dock_enable);
        }

        private int getFloatingMapModeIconRes(SharedPreferences sharedPreferences) {
            return sharedPreferences.getInt(PanelAppSettingsAdapter.KEY_FLOATING_MAP_DISPLAY_MODE, 0) == 0 ? o.ic_floating_map_mode : o.ic_floating_calendar_mode;
        }

        private String getFloatingMapModeLabel(Context context, SharedPreferences sharedPreferences) {
            return AppDetectionUtils.getFloatingMapModeLabel(context, sharedPreferences);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$0(OnIconChangeRequestListener onIconChangeRequestListener, AppInfo appInfo, View view) {
            if (onIconChangeRequestListener != null) {
                onIconChangeRequestListener.onRequestChangeName(appInfo);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$1(OnIconChangeRequestListener onIconChangeRequestListener, AppInfo appInfo, View view) {
            if (onIconChangeRequestListener != null) {
                onIconChangeRequestListener.onRequestRestoreIcon(appInfo);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$2(OnIconChangeRequestListener onIconChangeRequestListener, AppInfo appInfo, View view) {
            if (onIconChangeRequestListener != null) {
                onIconChangeRequestListener.onRequestChangeIcon(appInfo);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$3(SharedPreferences sharedPreferences, AppInfo appInfo, Context context, CompoundButton compoundButton, boolean z6) {
            sharedPreferences.edit().putBoolean("dock_app_enabled_" + appInfo.getPackageName(), z6).apply();
            Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
        }

        public void bind(final AppInfo appInfo, final OnIconChangeRequestListener onIconChangeRequestListener, Context context, boolean z6) {
            Drawable drawable;
            boolean z7;
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(context);
            String string = appPrefs.getString("app_custom_name_" + appInfo.getPackageName(), null);
            if (PanelAppSettingsAdapter.PKG_FLOATING_MAP_MODE.equals(appInfo.getPackageName())) {
                this.appNameView.setText(getFloatingMapModeLabel(context, appPrefs));
            } else if (string == null || string.isEmpty()) {
                this.appNameView.setText(appInfo.getAppName());
            } else {
                this.appNameView.setText(string);
            }
            final int i7 = 0;
            this.changeNameButton.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.adapter.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i8 = i7;
                    AppInfo appInfo2 = appInfo;
                    PanelAppSettingsAdapter.OnIconChangeRequestListener onIconChangeRequestListener2 = onIconChangeRequestListener;
                    switch (i8) {
                        case 0:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$0(onIconChangeRequestListener2, appInfo2, view);
                            break;
                        case 1:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$1(onIconChangeRequestListener2, appInfo2, view);
                            break;
                        default:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$2(onIconChangeRequestListener2, appInfo2, view);
                            break;
                    }
                }
            });
            this.appNameView.setOnClickListener(null);
            this.appPackageView.setText(appInfo.getPackageName());
            String string2 = appPrefs.getString("gesture_panel_app_icon_" + appInfo.getPackageName(), null);
            final int i8 = 1;
            if (string2 == null || string2.isEmpty()) {
                drawable = null;
                z7 = false;
            } else {
                drawable = Drawable.createFromPath(string2);
                z7 = true;
            }
            if (drawable == null) {
                if ("com.navi.tool.originalhome".equals(appInfo.getPackageName())) {
                    drawable = context.getDrawable(o.ic_original_home);
                } else if ("com.navi.tool.action.adbswitch".equals(appInfo.getPackageName())) {
                    drawable = AppDetectionUtils.getAppIcon(context, appInfo.getPackageName());
                } else {
                    try {
                        drawable = context.getPackageManager().getApplicationIcon(appInfo.getPackageName());
                    } catch (Exception unused) {
                        drawable = context.getDrawable(s.ic_launcher);
                    }
                }
                z7 = false;
            }
            this.appIconView.setImageDrawable(drawable);
            if (z7) {
                this.restoreIconButton.setVisibility(0);
                this.restoreIconButton.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.adapter.h
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i82 = i8;
                        AppInfo appInfo2 = appInfo;
                        PanelAppSettingsAdapter.OnIconChangeRequestListener onIconChangeRequestListener2 = onIconChangeRequestListener;
                        switch (i82) {
                            case 0:
                                PanelAppSettingsAdapter.ViewHolder.lambda$bind$0(onIconChangeRequestListener2, appInfo2, view);
                                break;
                            case 1:
                                PanelAppSettingsAdapter.ViewHolder.lambda$bind$1(onIconChangeRequestListener2, appInfo2, view);
                                break;
                            default:
                                PanelAppSettingsAdapter.ViewHolder.lambda$bind$2(onIconChangeRequestListener2, appInfo2, view);
                                break;
                        }
                    }
                });
            } else {
                this.restoreIconButton.setVisibility(8);
                this.restoreIconButton.setOnClickListener(null);
            }
            final int i9 = 2;
            this.changeIconButton.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.adapter.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i82 = i9;
                    AppInfo appInfo2 = appInfo;
                    PanelAppSettingsAdapter.OnIconChangeRequestListener onIconChangeRequestListener2 = onIconChangeRequestListener;
                    switch (i82) {
                        case 0:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$0(onIconChangeRequestListener2, appInfo2, view);
                            break;
                        case 1:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$1(onIconChangeRequestListener2, appInfo2, view);
                            break;
                        default:
                            PanelAppSettingsAdapter.ViewHolder.lambda$bind$2(onIconChangeRequestListener2, appInfo2, view);
                            break;
                    }
                }
            });
            if (!z6) {
                this.dockSwitch.setVisibility(8);
                return;
            }
            this.dockSwitch.setVisibility(0);
            boolean z8 = appPrefs.getBoolean("dock_app_enabled_" + appInfo.getPackageName(), PanelAppSettingsAdapter.DEFAULT_DOCK_APPS.contains(appInfo.getPackageName()));
            this.dockSwitch.setOnCheckedChangeListener(null);
            this.dockSwitch.setChecked(z8);
            this.dockSwitch.setOnCheckedChangeListener(new e(appPrefs, appInfo, context, i8));
        }
    }

    public PanelAppSettingsAdapter(Context context, List<AppInfo> list, OnIconChangeRequestListener onIconChangeRequestListener) {
        this.context = context;
        this.appList = new ArrayList(list);
        this.listener = onIconChangeRequestListener;
        setHasStableIds(true);
    }

    public List<String> getCurrentPackageOrder() {
        ArrayList arrayList = new ArrayList();
        Iterator<AppInfo> it = this.appList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPackageName());
        }
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.appList.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public long getItemId(int i7) {
        if (this.appList.get(i7) == null) {
            return -1L;
        }
        return r3.getPackageName().hashCode();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        viewHolder.bind(this.appList.get(i7), this.listener, this.context, this.showDockSwitch);
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(q.item_panel_app_setting, viewGroup, false));
    }

    public boolean onItemMove(int i7, int i8) {
        if (i7 < 0 || i8 < 0 || i7 >= this.appList.size() || i8 >= this.appList.size() || i7 == i8) {
            return false;
        }
        Collections.swap(this.appList, i7, i8);
        notifyItemMoved(i7, i8);
        return true;
    }

    public void setShowDockSwitch(boolean z6) {
        this.showDockSwitch = z6;
    }
}
