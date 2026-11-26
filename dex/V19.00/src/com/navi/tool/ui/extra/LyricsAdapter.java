package com.navi.tool.ui.extra;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LyricsAdapter extends androidx.recyclerview.widget.h1 {
    private final Context context;
    private OnLineClickListener lineClickListener;
    private final List<Long> timeStamps = new ArrayList();
    private final List<String> lyrics = new ArrayList();
    private int currentHighlightIndex = -1;
    private int fontSize = 25;
    private int highlightColor = -1;
    private int normalColor = -3355444;

    public static class LyricViewHolder extends androidx.recyclerview.widget.j2 {
        TextView textView;

        public LyricViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public interface OnLineClickListener {
        void onLineClick(int i7);
    }

    public LyricsAdapter(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0(LyricViewHolder lyricViewHolder, View view) {
        int adapterPosition;
        if (this.lineClickListener == null || (adapterPosition = lyricViewHolder.getAdapterPosition()) == -1) {
            return;
        }
        this.lineClickListener.onLineClick(adapterPosition);
    }

    private void updateItemStyle(LyricViewHolder lyricViewHolder, int i7) {
        if (i7 == this.currentHighlightIndex) {
            lyricViewHolder.textView.setTextSize(2, this.fontSize + 4);
            lyricViewHolder.textView.setTextColor(this.highlightColor);
            lyricViewHolder.textView.setTypeface(null, 1);
            lyricViewHolder.textView.setAlpha(1.0f);
            return;
        }
        lyricViewHolder.textView.setTextSize(2, this.fontSize);
        lyricViewHolder.textView.setTextColor(this.normalColor);
        lyricViewHolder.textView.setTypeface(null, 0);
        lyricViewHolder.textView.setAlpha(0.6f);
    }

    public int getCurrentHighlightIndex() {
        return this.currentHighlightIndex;
    }

    public String getCurrentLyricText() {
        int i7 = this.currentHighlightIndex;
        return (i7 < 0 || i7 >= this.lyrics.size()) ? !this.lyrics.isEmpty() ? this.lyrics.get(0) : s5.h0.FRAGMENT_ENCODE_SET : this.lyrics.get(this.currentHighlightIndex);
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.lyrics.size();
    }

    public int getItemHeight() {
        return (int) ((TypedValue.applyDimension(2, this.fontSize + 4, this.context.getResources().getDisplayMetrics()) * 1.4f) + ((int) TypedValue.applyDimension(1, 8.0f, this.context.getResources().getDisplayMetrics())) + 2.0f);
    }

    @Override // androidx.recyclerview.widget.h1
    public /* bridge */ /* synthetic */ void onBindViewHolder(androidx.recyclerview.widget.j2 j2Var, int i7, List list) {
        onBindViewHolder((LyricViewHolder) j2Var, i7, (List<Object>) list);
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(LyricViewHolder lyricViewHolder, int i7) {
        lyricViewHolder.textView.setText(this.lyrics.get(i7));
        updateItemStyle(lyricViewHolder, i7);
    }

    public void onBindViewHolder(LyricViewHolder lyricViewHolder, int i7, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder((androidx.recyclerview.widget.j2) lyricViewHolder, i7, list);
        } else {
            updateItemStyle(lyricViewHolder, i7);
        }
    }

    @Override // androidx.recyclerview.widget.h1
    public LyricViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        textView.setGravity(17);
        int applyDimension = (int) TypedValue.applyDimension(1, 4.0f, this.context.getResources().getDisplayMetrics());
        int applyDimension2 = (int) TypedValue.applyDimension(1, 16.0f, this.context.getResources().getDisplayMetrics());
        textView.setPadding(applyDimension2, applyDimension, applyDimension2, applyDimension);
        LyricViewHolder lyricViewHolder = new LyricViewHolder(textView);
        textView.setOnClickListener(new d1(2, this, lyricViewHolder));
        return lyricViewHolder;
    }

    public void setFontSize(int i7) {
        this.fontSize = i7;
        notifyDataSetChanged();
    }

    public void setHighlightColor(int i7) {
        this.highlightColor = i7;
        notifyDataSetChanged();
    }

    public void setLyrics(TreeMap<Long, String> treeMap) {
        this.timeStamps.clear();
        this.lyrics.clear();
        if (treeMap != null) {
            for (Map.Entry<Long, String> entry : treeMap.entrySet()) {
                this.timeStamps.add(entry.getKey());
                this.lyrics.add(entry.getValue());
            }
        }
        this.currentHighlightIndex = -1;
        notifyDataSetChanged();
    }

    public void setNormalColor(int i7) {
        this.normalColor = i7;
        notifyDataSetChanged();
    }

    public void setOnLineClickListener(OnLineClickListener onLineClickListener) {
        this.lineClickListener = onLineClickListener;
    }

    public int updateProgress(long j7) {
        int i7;
        if (this.timeStamps.isEmpty()) {
            return -1;
        }
        int i8 = -1;
        for (int i9 = 0; i9 < this.timeStamps.size() && this.timeStamps.get(i9).longValue() <= j7; i9++) {
            i8 = i9;
        }
        if (i8 == -1 || i8 == (i7 = this.currentHighlightIndex)) {
            return -1;
        }
        this.currentHighlightIndex = i8;
        if (i7 != -1) {
            notifyItemChanged(i7, "highlight_update");
        }
        notifyItemChanged(this.currentHighlightIndex, "highlight_update");
        return this.currentHighlightIndex;
    }
}
