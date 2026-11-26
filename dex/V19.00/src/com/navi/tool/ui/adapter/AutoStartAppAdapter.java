package com.navi.tool.ui.adapter;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.n;
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
public class AutoStartAppAdapter extends h1 {
    private final List<AppInfo> apps;
    private final Context context;

    public static class ViewHolder extends j2 {
        ImageView appIcon;
        TextView appName;
        TextView appPackage;
        Button btnDelay;
        SwitchMaterial switchAutoStart;

        public ViewHolder(View view) {
            super(view);
            this.appIcon = (ImageView) view.findViewById(p.app_icon);
            this.appName = (TextView) view.findViewById(p.app_name);
            this.appPackage = (TextView) view.findViewById(p.app_package);
            this.switchAutoStart = (SwitchMaterial) view.findViewById(p.switch_app_auto_start);
            this.btnDelay = (Button) view.findViewById(p.btn_delay);
        }
    }

    public AutoStartAppAdapter(Context context, List<AppInfo> list) {
        this.context = context;
        this.apps = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(AppInfo appInfo, ViewHolder viewHolder, CompoundButton compoundButton, boolean z6) {
        if (z6) {
            SharedPreferencesUtils.setAutoStartAppDelay(this.context, appInfo.getPackageName(), SharedPreferencesUtils.getNextAutoStartDelay(this.context));
        }
        SharedPreferencesUtils.setAutoStartAppEnabled(this.context, appInfo.getPackageName(), z6);
        updateDelayButton(viewHolder, appInfo.getPackageName(), z6);
        if ("com.navi.tool.desktopassistant".equals(appInfo.getPackageName())) {
            return;
        }
        refreshDesktopAssistantItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(ViewHolder viewHolder, AppInfo appInfo, View view) {
        showDelayDialog(viewHolder, appInfo.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDelayDialog$2(EditText editText, String str, ViewHolder viewHolder, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            return;
        }
        try {
            int parseInt = Integer.parseInt(c7);
            if (parseInt < 0) {
                parseInt = 0;
            }
            SharedPreferencesUtils.setAutoStartAppDelay(this.context, str, parseInt);
            viewHolder.btnDelay.setText("延迟启动时间：" + parseInt + "s");
            if ("com.navi.tool.desktopassistant".equals(str)) {
                return;
            }
            refreshDesktopAssistantItem();
        } catch (NumberFormatException unused) {
        }
    }

    private void refreshDesktopAssistantItem() {
        for (int i7 = 0; i7 < this.apps.size(); i7++) {
            if ("com.navi.tool.desktopassistant".equals(this.apps.get(i7).getPackageName())) {
                notifyItemChanged(i7);
                return;
            }
        }
    }

    private void showDelayDialog(final ViewHolder viewHolder, final String str) {
        int autoStartAppDelay = SharedPreferencesUtils.getAutoStartAppDelay(this.context, str);
        final EditText editText = new EditText(this.context);
        editText.setInputType(2);
        editText.setText(String.valueOf(autoStartAppDelay));
        editText.setSelectAllOnFocus(true);
        FrameLayout frameLayout = new FrameLayout(this.context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        int i7 = (int) (this.context.getResources().getDisplayMetrics().density * 20.0f);
        layoutParams.setMargins(i7, i7, i7, i7);
        frameLayout.addView(editText, layoutParams);
        n nVar = new n(this.context);
        nVar.t("设置延时启动时间(秒)");
        nVar.u(frameLayout);
        nVar.r("确定", new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.adapter.g
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i8) {
                AutoStartAppAdapter.this.lambda$showDelayDialog$2(editText, str, viewHolder, dialogInterface, i8);
            }
        });
        nVar.o("取消", null);
        nVar.v();
    }

    private void updateDelayButton(ViewHolder viewHolder, String str, boolean z6) {
        if (!z6) {
            viewHolder.btnDelay.setVisibility(8);
            return;
        }
        viewHolder.btnDelay.setVisibility(0);
        int autoStartAppDelay = SharedPreferencesUtils.getAutoStartAppDelay(this.context, str);
        viewHolder.btnDelay.setText("延迟启动时间：" + autoStartAppDelay + "s");
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.apps.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(final ViewHolder viewHolder, int i7) {
        final AppInfo appInfo = this.apps.get(i7);
        viewHolder.appName.setText(appInfo.getAppName());
        viewHolder.appPackage.setText(appInfo.getPackageName());
        Drawable appIcon = AppDetectionUtils.getAppIcon(this.context, appInfo.getPackageName());
        if (appIcon != null) {
            viewHolder.appIcon.setImageDrawable(appIcon);
        } else {
            viewHolder.appIcon.setImageResource(R.drawable.sym_def_app_icon);
        }
        boolean isAutoStartAppEnabled = SharedPreferencesUtils.isAutoStartAppEnabled(this.context, appInfo.getPackageName());
        viewHolder.switchAutoStart.setOnCheckedChangeListener(null);
        viewHolder.switchAutoStart.setChecked(isAutoStartAppEnabled);
        updateDelayButton(viewHolder, appInfo.getPackageName(), isAutoStartAppEnabled);
        viewHolder.switchAutoStart.setOnCheckedChangeListener(new e(this, appInfo, viewHolder, 0));
        viewHolder.btnDelay.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.adapter.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutoStartAppAdapter.this.lambda$onBindViewHolder$1(viewHolder, appInfo, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_auto_start_app, viewGroup, false));
    }
}
