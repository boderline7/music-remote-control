package com.navi.tool.ui.adapter;

import android.content.Context;
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
import g4.p;
import g4.q;
import g4.s;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SelectAppAdapter extends h1 {
    private final List<AppInfo> appList;
    private final Context context;
    private final OnAppClickListener listener;

    public interface OnAppClickListener {
        void onAppClick(AppInfo appInfo);
    }

    public static class ViewHolder extends j2 {
        private final ImageView appIconView;
        private final TextView appNameView;
        private final TextView appPackageView;
        private final View container;

        public ViewHolder(View view) {
            super(view);
            this.appIconView = (ImageView) view.findViewById(p.app_icon);
            this.appNameView = (TextView) view.findViewById(p.app_name);
            this.appPackageView = (TextView) view.findViewById(p.package_name);
            this.container = view.findViewById(p.container);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$bind$0(OnAppClickListener onAppClickListener, AppInfo appInfo, View view) {
            if (onAppClickListener != null) {
                onAppClickListener.onAppClick(appInfo);
            }
        }

        public void bind(AppInfo appInfo, OnAppClickListener onAppClickListener, Context context) {
            this.appNameView.setText(appInfo.getAppName());
            this.appPackageView.setText(appInfo.getPackageName());
            Drawable appIcon = AppDetectionUtils.getAppIcon(context, appInfo.getPackageName());
            if (appIcon != null) {
                this.appIconView.setImageDrawable(appIcon);
            } else {
                this.appIconView.setImageResource(s.ic_launcher);
            }
            this.container.setOnClickListener(new d(onAppClickListener, appInfo, 2));
        }
    }

    public SelectAppAdapter(Context context, List<AppInfo> list, OnAppClickListener onAppClickListener) {
        this.context = context;
        this.appList = list;
        this.listener = onAppClickListener;
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.appList.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        viewHolder.bind(this.appList.get(i7), this.listener, this.context);
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(q.item_select_app, viewGroup, false));
    }
}
