package com.navi.tool.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.j2;
import androidx.recyclerview.widget.t;
import androidx.recyclerview.widget.z0;
import com.navi.tool.databinding.ItemAppBinding;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.utils.AppDetectionUtils;
import g4.s;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppAdapter extends z0 {
    private static final ExecutorService ICON_EXECUTOR = Executors.newFixedThreadPool(4);
    private final OnAppToggleListener listener;

    public static class AppDiffCallback extends t {
        private AppDiffCallback() {
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areContentsTheSame(AppInfo appInfo, AppInfo appInfo2) {
            return appInfo.getAppName().equals(appInfo2.getAppName()) && appInfo.isEnabled() == appInfo2.isEnabled();
        }

        @Override // androidx.recyclerview.widget.t
        public boolean areItemsTheSame(AppInfo appInfo, AppInfo appInfo2) {
            return appInfo.getPackageName().equals(appInfo2.getPackageName());
        }
    }

    public static class AppViewHolder extends j2 {
        private final ItemAppBinding binding;

        public AppViewHolder(ItemAppBinding itemAppBinding) {
            super(itemAppBinding.f4831a);
            this.binding = itemAppBinding;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$0(String str, Drawable drawable) {
            if (str.equals(this.binding.f4832b.getTag())) {
                this.binding.f4832b.setImageDrawable(drawable);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1(final String str) {
            final Drawable appIcon = AppDetectionUtils.getAppIcon(this.itemView.getContext().getApplicationContext(), str);
            if (appIcon != null) {
                this.binding.f4832b.post(new Runnable() { // from class: com.navi.tool.ui.adapter.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppAdapter.AppViewHolder.this.lambda$bind$0(str, appIcon);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$2(OnAppToggleListener onAppToggleListener, AppInfo appInfo, CompoundButton compoundButton, boolean z6) {
            if (!compoundButton.isPressed() || onAppToggleListener == null) {
                return;
            }
            onAppToggleListener.onAppToggle(appInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$3(OnAppToggleListener onAppToggleListener, AppInfo appInfo, View view) {
            if (onAppToggleListener != null) {
                onAppToggleListener.onAppToggle(appInfo);
            }
        }

        public void bind(AppInfo appInfo, OnAppToggleListener onAppToggleListener) {
            this.binding.f4833c.setText(appInfo.getAppName());
            this.binding.f4835e.setText(appInfo.getPackageName());
            this.binding.f4834d.setChecked(appInfo.isEnabled());
            this.binding.f4832b.setImageResource(s.ic_launcher);
            final String packageName = appInfo.getPackageName();
            this.binding.f4832b.setTag(packageName);
            AppAdapter.ICON_EXECUTOR.execute(new Runnable() { // from class: com.navi.tool.ui.adapter.b
                @Override // java.lang.Runnable
                public final void run() {
                    AppAdapter.AppViewHolder.this.lambda$bind$1(packageName);
                }
            });
            int i7 = 0;
            this.binding.f4834d.setOnCheckedChangeListener(new c(onAppToggleListener, appInfo, i7));
            this.binding.f4831a.setOnClickListener(new d(onAppToggleListener, appInfo, i7));
        }
    }

    public interface OnAppToggleListener {
        void onAppToggle(AppInfo appInfo);
    }

    public AppAdapter(OnAppToggleListener onAppToggleListener) {
        super(new AppDiffCallback());
        this.listener = onAppToggleListener;
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.h1
    public long getItemId(int i7) {
        if (((AppInfo) getItem(i7)) == null) {
            return -1L;
        }
        return r3.getPackageName().hashCode();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(AppViewHolder appViewHolder, int i7) {
        appViewHolder.bind((AppInfo) getItem(i7), this.listener);
    }

    @Override // androidx.recyclerview.widget.h1
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new AppViewHolder(ItemAppBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }
}
