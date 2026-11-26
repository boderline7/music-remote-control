package com.navi.tool.ui.cast;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.n;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.ui.cast.ScreenCastingFragment;
import com.navi.tool.ui.common.DialogListDividers;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import g4.p;
import g4.q;
import g4.u;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ScreenCastingFragment extends z {
    private AppsAdapter appsAdapter;
    private Button btnCustomCasting;
    private View cardApps;
    private View cardClusterEnlargeCasting;
    private View cardCustomCasting;
    private DisplayManager displayManager;
    private View layoutScreenCastingPermission;
    private RecyclerView recyclerApps;
    private SeekBar seekIconAlpha;
    private SeekBar seekIconSize;
    private SwitchMaterial switchClusterEnlargeCasting;
    private SwitchMaterial switchScreenCasting;
    private TextView tvIconAlpha;
    private TextView tvIconSize;

    public class AppItem {
        String activity;
        Drawable icon;
        String label;
        String pkg;

        public AppItem(String str, String str2, String str3, Drawable drawable) {
            this.pkg = str;
            this.label = str2;
            this.activity = str3;
            this.icon = drawable;
        }
    }

    public class AppViewHolder extends j2 {
        ImageView appIcon;
        TextView appName;
        SwitchMaterial appSwitch;
        TextView packageName;

        public AppViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(p.app_icon);
            this.appName = (TextView) view.findViewById(p.app_name);
            this.packageName = (TextView) view.findViewById(p.package_name);
            this.appSwitch = (SwitchMaterial) view.findViewById(p.app_switch);
        }
    }

    public class AppsAdapter extends h1 {
        List<AppItem> data;

        public AppsAdapter(List<AppItem> list) {
            this.data = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0() {
            notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1() {
            notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$2(AppItem appItem, CompoundButton compoundButton, boolean z6) {
            String screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(ScreenCastingFragment.this.requireContext());
            if (z6) {
                SharedPreferencesUtils.setScreenCastingSelectedPkg(ScreenCastingFragment.this.requireContext(), appItem.pkg);
                ScreenCastingFragment.this.launchOnMain(appItem);
                ScreenCastingFragment.this.startFloatingService();
                final int i7 = 0;
                ScreenCastingFragment.this.recyclerApps.post(new Runnable(this) { // from class: com.navi.tool.ui.cast.h

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ ScreenCastingFragment.AppsAdapter f4989b;

                    {
                        this.f4989b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i8 = i7;
                        ScreenCastingFragment.AppsAdapter appsAdapter = this.f4989b;
                        switch (i8) {
                            case 0:
                                appsAdapter.lambda$onBindViewHolder$0();
                                break;
                            default:
                                appsAdapter.lambda$onBindViewHolder$1();
                                break;
                        }
                    }
                });
                return;
            }
            if (screenCastingSelectedPkg == null || !screenCastingSelectedPkg.equals(appItem.pkg)) {
                return;
            }
            ScreenCastingFragment.this.launchOnMain(appItem);
            SharedPreferencesUtils.setScreenCastingSelectedPkg(ScreenCastingFragment.this.requireContext(), null);
            ScreenCastingFragment.this.startFloatingService();
            final int i8 = 1;
            ScreenCastingFragment.this.recyclerApps.post(new Runnable(this) { // from class: com.navi.tool.ui.cast.h

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ ScreenCastingFragment.AppsAdapter f4989b;

                {
                    this.f4989b = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i82 = i8;
                    ScreenCastingFragment.AppsAdapter appsAdapter = this.f4989b;
                    switch (i82) {
                        case 0:
                            appsAdapter.lambda$onBindViewHolder$0();
                            break;
                        default:
                            appsAdapter.lambda$onBindViewHolder$1();
                            break;
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.data.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(AppViewHolder appViewHolder, int i7) {
            AppItem appItem = this.data.get(i7);
            appViewHolder.appName.setText(appItem.label);
            appViewHolder.appIcon.setImageDrawable(appItem.icon);
            appViewHolder.packageName.setText(appItem.pkg);
            String screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(ScreenCastingFragment.this.requireContext());
            appViewHolder.appSwitch.setOnCheckedChangeListener(null);
            boolean z6 = true;
            int i8 = 0;
            appViewHolder.appSwitch.setChecked(screenCastingSelectedPkg != null && screenCastingSelectedPkg.equals(appItem.pkg));
            SwitchMaterial switchMaterial = appViewHolder.appSwitch;
            if (screenCastingSelectedPkg != null && !screenCastingSelectedPkg.equals(appItem.pkg)) {
                z6 = false;
            }
            switchMaterial.setEnabled(z6);
            appViewHolder.appSwitch.setOnCheckedChangeListener(new i(this, appItem, i8));
        }

        @Override // androidx.recyclerview.widget.h1
        public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return ScreenCastingFragment.this.new AppViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(q.item_app, viewGroup, false));
        }
    }

    public class SelectAppsAdapter extends h1 {
        List<AppItem> data;
        HashSet<String> selected;

        public SelectAppsAdapter(List<AppItem> list, HashSet<String> hashSet) {
            this.data = list;
            this.selected = hashSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(AppItem appItem, CompoundButton compoundButton, boolean z6) {
            HashSet<String> hashSet = this.selected;
            String str = appItem.pkg;
            if (z6) {
                hashSet.add(str);
            } else {
                hashSet.remove(str);
            }
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.data.size();
        }

        public HashSet<String> getSelected() {
            return this.selected;
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(SelectViewHolder selectViewHolder, int i7) {
            AppItem appItem = this.data.get(i7);
            selectViewHolder.appName.setText(appItem.label);
            selectViewHolder.appIcon.setImageDrawable(appItem.icon);
            selectViewHolder.packageName.setText(appItem.pkg);
            selectViewHolder.appSwitch.setChecked(this.selected.contains(appItem.pkg));
            selectViewHolder.appSwitch.setEnabled(true);
            selectViewHolder.appSwitch.setOnCheckedChangeListener(new i(this, appItem, 1));
        }

        @Override // androidx.recyclerview.widget.h1
        public SelectViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return ScreenCastingFragment.this.new SelectViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(q.item_app, viewGroup, false));
        }

        public void setData(List<AppItem> list) {
            this.data = list;
        }
    }

    public class SelectViewHolder extends j2 {
        ImageView appIcon;
        TextView appName;
        SwitchMaterial appSwitch;
        TextView packageName;

        public SelectViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(p.app_icon);
            this.appName = (TextView) view.findViewById(p.app_name);
            this.packageName = (TextView) view.findViewById(p.package_name);
            this.appSwitch = (SwitchMaterial) view.findViewById(p.app_switch);
        }
    }

    private boolean hasSecondaryDisplay() {
        for (Display display : this.displayManager.getDisplays()) {
            if (display.getDisplayId() != 0) {
                return true;
            }
        }
        return false;
    }

    private void initAlphaSeekBar() {
        int screenCastingIconAlpha = SharedPreferencesUtils.getScreenCastingIconAlpha(requireContext());
        a.a.v("悬浮图标透明度: ", screenCastingIconAlpha, "%", this.tvIconAlpha);
        this.seekIconAlpha.setMax(100);
        this.seekIconAlpha.setProgress(screenCastingIconAlpha);
        this.seekIconAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.cast.ScreenCastingFragment.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
                a.a.v("悬浮图标透明度: ", i7, "%", ScreenCastingFragment.this.tvIconAlpha);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferencesUtils.setScreenCastingIconAlpha(ScreenCastingFragment.this.requireContext(), seekBar.getProgress());
                if (SharedPreferencesUtils.isScreenCastingEnabled(ScreenCastingFragment.this.requireContext())) {
                    ScreenCastingFragment.this.startFloatingService();
                }
            }
        });
    }

    private void initClusterEnlargeSwitch() {
        SwitchMaterial switchMaterial = this.switchClusterEnlargeCasting;
        if (switchMaterial != null) {
            switchMaterial.setChecked(SharedPreferencesUtils.isClusterEnlargeOnCasting(requireContext()));
            this.switchClusterEnlargeCasting.setOnCheckedChangeListener(new e(this, 0));
        }
    }

    private void initCustomCasting() {
        Button button = this.btnCustomCasting;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.cast.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ScreenCastingFragment.this.lambda$initCustomCasting$0(view);
                }
            });
        }
    }

    private void initRecycler() {
        RecyclerView recyclerView = this.recyclerApps;
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        AppsAdapter appsAdapter = new AppsAdapter(new ArrayList());
        this.appsAdapter = appsAdapter;
        this.recyclerApps.setAdapter(appsAdapter);
    }

    private void initSeekBar() {
        int screenCastingIconSize = SharedPreferencesUtils.getScreenCastingIconSize(requireContext());
        this.tvIconSize.setText("图标大小: " + screenCastingIconSize);
        this.seekIconSize.setMax(50);
        this.seekIconSize.setProgress(screenCastingIconSize - 50);
        this.seekIconSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.cast.ScreenCastingFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
                ScreenCastingFragment.this.tvIconSize.setText("图标大小: " + (i7 + 50));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferencesUtils.setScreenCastingIconSize(ScreenCastingFragment.this.requireContext(), seekBar.getProgress() + 50);
                if (SharedPreferencesUtils.isScreenCastingEnabled(ScreenCastingFragment.this.requireContext())) {
                    ScreenCastingFragment.this.startFloatingService();
                }
            }
        });
    }

    private void initSwitch() {
        this.switchScreenCasting.setChecked(SharedPreferencesUtils.isScreenCastingEnabled(requireContext()));
        this.switchScreenCasting.setOnCheckedChangeListener(new e(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initClusterEnlargeSwitch$7(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setClusterEnlargeOnCasting(requireContext(), z6);
        if (z6) {
            String screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(requireContext());
            if (screenCastingSelectedPkg == null) {
                ToastHelper.show(requireContext(), "当前未选择投屏应用");
            } else {
                ToastHelper.show(requireContext(), SharedPreferencesUtils.getScreenCastingAppState(requireContext(), screenCastingSelectedPkg).equals("main") ? "当前应用在主屏" : "当前应用在仪表");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCustomCasting$0(View view) {
        showCustomCastingDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initSwitch$5(DialogInterface dialogInterface, int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSwitch$6(CompoundButton compoundButton, boolean z6) {
        if (!z6) {
            SharedPreferencesUtils.setScreenCastingEnabled(requireContext(), false);
            stopFloatingService();
            SharedPreferencesUtils.setScreenCastingSelectedPkg(requireContext(), null);
            this.cardApps.setVisibility(8);
            this.cardCustomCasting.setVisibility(8);
            this.cardClusterEnlargeCasting.setVisibility(8);
            this.tvIconSize.setVisibility(8);
            this.seekIconSize.setVisibility(8);
            this.tvIconAlpha.setVisibility(8);
            this.seekIconAlpha.setVisibility(8);
            AppsAdapter appsAdapter = new AppsAdapter(new ArrayList());
            this.appsAdapter = appsAdapter;
            this.recyclerApps.setAdapter(appsAdapter);
            return;
        }
        int i7 = 1;
        if (Build.VERSION.SDK_INT <= 28) {
            n nVar = new n(requireContext());
            nVar.t("提示");
            nVar.m("由于安卓限制，安卓9及以下无法实现快捷投屏，仅支持视频录制，而这种录制方式较为落后，车机助手不打算支持这种方式");
            nVar.r("关闭窗口", new g4.g(i7));
            nVar.v();
            this.switchScreenCasting.setChecked(false);
            SharedPreferencesUtils.setScreenCastingEnabled(requireContext(), false);
            return;
        }
        if (!hasSecondaryDisplay()) {
            this.switchScreenCasting.setChecked(false);
            ToastHelper.show(requireContext(), "当前未找到仪表");
            return;
        }
        if (!Settings.canDrawOverlays(requireContext())) {
            startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + requireContext().getPackageName())));
            this.switchScreenCasting.setChecked(false);
            return;
        }
        SharedPreferencesUtils.setScreenCastingEnabled(requireContext(), true);
        startFloatingService();
        this.cardApps.setVisibility(0);
        this.cardCustomCasting.setVisibility(0);
        this.cardClusterEnlargeCasting.setVisibility(0);
        this.tvIconSize.setVisibility(0);
        this.seekIconSize.setVisibility(0);
        this.tvIconAlpha.setVisibility(0);
        this.seekIconAlpha.setVisibility(0);
        reloadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadAllLauncherApps$4(Collator collator, AppItem appItem, AppItem appItem2) {
        return collator.compare(appItem.label, appItem2.label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadLauncherApps$8(Collator collator, AppItem appItem, AppItem appItem2) {
        return collator.compare(appItem.label, appItem2.label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCustomCastingDialog$1(SelectAppsAdapter selectAppsAdapter, DialogInterface dialogInterface, int i7) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = selectAppsAdapter.getSelected().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\n");
        }
        SharedPreferencesUtils.setScreenCastingCustomPackages(requireContext(), sb.toString());
        reloadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCustomCastingDialog$2(SelectAppsAdapter selectAppsAdapter, List list) {
        selectAppsAdapter.setData(list);
        selectAppsAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCustomCastingDialog$3(SelectAppsAdapter selectAppsAdapter) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        List<AppItem> loadAllLauncherApps = loadAllLauncherApps(context);
        if (getActivity() != null) {
            getActivity().runOnUiThread(new f(selectAppsAdapter, loadAllLauncherApps));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchOnMain(AppItem appItem) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setClassName(appItem.pkg, appItem.activity);
        intent.addFlags(268435456);
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchDisplayId(0);
        startActivity(intent, makeBasic.toBundle());
        SharedPreferencesUtils.setScreenCastingAppState(requireContext(), appItem.pkg, "main");
    }

    private List<AppItem> loadAllLauncherApps(Context context) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = context.getPackageManager();
        int i7 = 0;
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            ApplicationInfo applicationInfo = activityInfo.applicationInfo;
            if (applicationInfo != null) {
                int i8 = applicationInfo.flags;
                if ((i8 & 1) == 0 && (i8 & 128) == 0) {
                }
            }
            String str = activityInfo.packageName;
            if (!str.startsWith("com.autonavi") && !str.startsWith("com.baidu") && !str.startsWith("com.tencent")) {
                arrayList.add(new AppItem(str, resolveInfo.loadLabel(packageManager).toString(), resolveInfo.activityInfo.name, resolveInfo.loadIcon(packageManager)));
            }
        }
        arrayList.sort(new c(Collator.getInstance(Locale.getDefault()), i7));
        return arrayList;
    }

    private List<AppItem> loadLauncherApps() {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = requireContext().getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        HashSet hashSet = new HashSet();
        String screenCastingCustomPackages = SharedPreferencesUtils.getScreenCastingCustomPackages(requireContext());
        if (screenCastingCustomPackages != null && !screenCastingCustomPackages.trim().isEmpty()) {
            for (String str : screenCastingCustomPackages.split("\n")) {
                String trim = str.trim();
                if (!trim.isEmpty()) {
                    hashSet.add(trim);
                }
            }
        }
        HashSet hashSet2 = new HashSet();
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (true) {
            int i7 = 1;
            if (!it.hasNext()) {
                arrayList.sort(new c(Collator.getInstance(Locale.getDefault()), i7));
                return arrayList;
            }
            ResolveInfo next = it.next();
            ActivityInfo activityInfo = next.activityInfo;
            ApplicationInfo applicationInfo = activityInfo.applicationInfo;
            if (applicationInfo != null) {
                int i8 = applicationInfo.flags;
                if ((i8 & 1) == 0 && (i8 & 128) == 0) {
                }
            }
            String str2 = activityInfo.packageName;
            if (!str2.startsWith(AppDetectionUtils.AUTONAVI_PREFIX) && !str2.startsWith("com.baidu") && !str2.startsWith("com.tencent")) {
                i7 = 0;
            }
            boolean contains = hashSet.contains(str2);
            if (i7 != 0 || contains) {
                if (!hashSet2.contains(str2)) {
                    arrayList.add(new AppItem(str2, next.loadLabel(packageManager).toString(), next.activityInfo.name, next.loadIcon(packageManager)));
                    hashSet2.add(str2);
                }
            }
        }
    }

    private void reloadApps() {
        AppsAdapter appsAdapter = new AppsAdapter(loadLauncherApps());
        this.appsAdapter = appsAdapter;
        this.recyclerApps.setAdapter(appsAdapter);
    }

    private void showCustomCastingDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(q.dialog_custom_casting_list, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(p.recycler_all_apps);
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        HashSet hashSet = new HashSet();
        String screenCastingCustomPackages = SharedPreferencesUtils.getScreenCastingCustomPackages(requireContext());
        if (screenCastingCustomPackages != null && !screenCastingCustomPackages.trim().isEmpty()) {
            for (String str : screenCastingCustomPackages.split("\n")) {
                String trim = str.trim();
                if (!trim.isEmpty()) {
                    hashSet.add(trim);
                }
            }
        }
        final SelectAppsAdapter selectAppsAdapter = new SelectAppsAdapter(new ArrayList(), hashSet);
        recyclerView.setAdapter(selectAppsAdapter);
        recyclerView.setHasFixedSize(true);
        n nVar = new n(requireContext());
        nVar.s(u.custom_casting_dialog_title);
        nVar.u(inflate);
        nVar.q(u.ok, new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.cast.g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i7) {
                ScreenCastingFragment.this.lambda$showCustomCastingDialog$1(selectAppsAdapter, dialogInterface, i7);
            }
        });
        nVar.n(u.cancel);
        nVar.e().show();
        new Thread(new f(this, selectAppsAdapter)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFloatingService() {
        requireContext().startForegroundService(new Intent(requireContext(), (Class<?>) ScreenCastingFloatingService.class));
    }

    private void stopFloatingService() {
        requireContext().stopService(new Intent(requireContext(), (Class<?>) ScreenCastingFloatingService.class));
    }

    private void updateScreenCastingPermissionUI() {
        if (Settings.canDrawOverlays(requireContext())) {
            this.switchScreenCasting.setEnabled(true);
            View view = this.layoutScreenCastingPermission;
            if (view != null) {
                view.setVisibility(8);
                return;
            }
            return;
        }
        this.switchScreenCasting.setEnabled(false);
        this.switchScreenCasting.setChecked(false);
        SharedPreferencesUtils.setScreenCastingEnabled(requireContext(), false);
        View view2 = this.layoutScreenCastingPermission;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        this.cardApps.setVisibility(8);
        this.cardCustomCasting.setVisibility(8);
        this.cardClusterEnlargeCasting.setVisibility(8);
        this.tvIconSize.setVisibility(8);
        this.seekIconSize.setVisibility(8);
        this.tvIconAlpha.setVisibility(8);
        this.seekIconAlpha.setVisibility(8);
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(q.fragment_screen_casting, viewGroup, false);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 8) == 0) {
            inflate.setVisibility(8);
            return inflate;
        }
        this.switchScreenCasting = (SwitchMaterial) inflate.findViewById(p.switch_screen_casting);
        this.switchClusterEnlargeCasting = (SwitchMaterial) inflate.findViewById(p.switch_cluster_enlarge_casting);
        this.recyclerApps = (RecyclerView) inflate.findViewById(p.recycler_apps);
        this.cardApps = inflate.findViewById(p.card_apps_list);
        this.tvIconSize = (TextView) inflate.findViewById(p.tv_icon_size);
        this.seekIconSize = (SeekBar) inflate.findViewById(p.seek_icon_size);
        this.tvIconAlpha = (TextView) inflate.findViewById(p.tv_icon_alpha);
        this.seekIconAlpha = (SeekBar) inflate.findViewById(p.seek_icon_alpha);
        this.btnCustomCasting = (Button) inflate.findViewById(p.btn_custom_casting);
        this.cardCustomCasting = inflate.findViewById(p.card_custom_casting);
        this.cardClusterEnlargeCasting = inflate.findViewById(p.card_cluster_enlarge_casting);
        this.layoutScreenCastingPermission = inflate.findViewById(p.layout_screen_casting_permission);
        this.displayManager = (DisplayManager) requireContext().getSystemService("display");
        initSwitch();
        initSeekBar();
        initAlphaSeekBar();
        initRecycler();
        initCustomCasting();
        initClusterEnlargeSwitch();
        boolean isScreenCastingEnabled = SharedPreferencesUtils.isScreenCastingEnabled(requireContext());
        this.cardApps.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.cardCustomCasting.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.cardClusterEnlargeCasting.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.tvIconSize.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.seekIconSize.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.tvIconAlpha.setVisibility(isScreenCastingEnabled ? 0 : 8);
        this.seekIconAlpha.setVisibility(isScreenCastingEnabled ? 0 : 8);
        if (isScreenCastingEnabled) {
            startFloatingService();
            reloadApps();
        }
        updateScreenCastingPermissionUI();
        return inflate;
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        updateScreenCastingPermissionUI();
    }
}
