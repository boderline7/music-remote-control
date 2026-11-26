package com.navi.tool.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.navi.tool.model.StoreConfig;
import com.navi.tool.utils.StoreDownloadManager;
import g4.p;
import g4.q;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StoreConfigAdapter extends h1 {
    private List<StoreConfig> configList;
    private Context context;
    private OnConfigActionListener listener;

    public interface OnConfigActionListener {
        void onDownload(StoreConfig storeConfig);

        void onDownloadAndExtract(StoreConfig storeConfig);

        void onExtract(StoreConfig storeConfig);
    }

    public static class ViewHolder extends j2 {
        Button btnDownload;
        Button btnDownloadExtract;
        Button btnExtract;
        ChipGroup chipGroupTags;
        TextView tvDescription;
        TextView tvName;
        TextView tvTime;

        public ViewHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(p.tv_name);
            this.tvTime = (TextView) view.findViewById(p.tv_time);
            this.tvDescription = (TextView) view.findViewById(p.tv_description);
            this.chipGroupTags = (ChipGroup) view.findViewById(p.chip_group_tags);
            this.btnDownload = (Button) view.findViewById(p.btn_download);
            this.btnExtract = (Button) view.findViewById(p.btn_extract);
            this.btnDownloadExtract = (Button) view.findViewById(p.btn_download_extract);
        }
    }

    public StoreConfigAdapter(Context context, List<StoreConfig> list, OnConfigActionListener onConfigActionListener) {
        this.context = context;
        this.configList = list;
        this.listener = onConfigActionListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(StoreConfig storeConfig, View view) {
        OnConfigActionListener onConfigActionListener = this.listener;
        if (onConfigActionListener != null) {
            onConfigActionListener.onDownload(storeConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(StoreConfig storeConfig, View view) {
        OnConfigActionListener onConfigActionListener = this.listener;
        if (onConfigActionListener != null) {
            onConfigActionListener.onExtract(storeConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$2(StoreConfig storeConfig, View view) {
        OnConfigActionListener onConfigActionListener = this.listener;
        if (onConfigActionListener != null) {
            onConfigActionListener.onDownloadAndExtract(storeConfig);
        }
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        List<StoreConfig> list = this.configList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        int i8;
        int i9;
        StoreConfig storeConfig = this.configList.get(i7);
        viewHolder.tvName.setText(storeConfig.getName());
        viewHolder.tvTime.setText("更新时间: " + storeConfig.getTime());
        viewHolder.tvDescription.setText(storeConfig.getDescription());
        viewHolder.chipGroupTags.removeAllViews();
        Iterator<String> it = storeConfig.getTags().iterator();
        while (true) {
            i8 = 2;
            i9 = 0;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            Chip chip = new Chip(this.context);
            chip.setText(next);
            chip.setTextSize(2, 12.0f);
            chip.setChipMinHeight(this.context.getResources().getDisplayMetrics().density * 24.0f);
            chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#E0E0E0")));
            chip.setTextColor(Color.parseColor("#424242"));
            chip.setCheckable(false);
            chip.setClickable(false);
            viewHolder.chipGroupTags.addView(chip);
        }
        int i10 = 1;
        if (StoreDownloadManager.isConfigDownloaded(this.context, storeConfig)) {
            viewHolder.btnDownload.setEnabled(false);
            viewHolder.btnDownload.setText("已下载");
            viewHolder.btnDownload.setAlpha(0.5f);
            viewHolder.btnDownloadExtract.setEnabled(false);
            viewHolder.btnDownloadExtract.setAlpha(0.5f);
        } else {
            viewHolder.btnDownload.setEnabled(true);
            viewHolder.btnDownload.setText("下载");
            viewHolder.btnDownload.setAlpha(1.0f);
            viewHolder.btnDownloadExtract.setEnabled(true);
            viewHolder.btnDownloadExtract.setAlpha(1.0f);
        }
        viewHolder.btnDownload.setOnClickListener(new a(i9, this, storeConfig));
        viewHolder.btnExtract.setOnClickListener(new a(i10, this, storeConfig));
        viewHolder.btnDownloadExtract.setOnClickListener(new a(i8, this, storeConfig));
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(q.item_store_config, viewGroup, false));
    }
}
