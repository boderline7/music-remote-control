package com.navi.tool.ui.extra;

import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantTopInfoAdapter extends androidx.recyclerview.widget.h1 {
    private final DesktopAssistantCarControlHelper carControlHelper;
    private final SharedPreferences prefs;
    private final List<TopInfoItem> items = new ArrayList();
    private int propertyTextColor = -1;
    private int valueTextColor = -1;
    private int fontSize = 16;

    public static class TopInfoItem {
        String id;
        String name;
        String value;

        public TopInfoItem(String str, String str2, String str3) {
            this.id = str;
            this.name = str2;
            this.value = str3;
        }
    }

    public static class ViewHolder extends androidx.recyclerview.widget.j2 {
        TextView text;

        public ViewHolder(View view) {
            super(view);
            this.text = (TextView) view;
        }
    }

    public DesktopAssistantTopInfoAdapter(SharedPreferences sharedPreferences, DesktopAssistantCarControlHelper desktopAssistantCarControlHelper) {
        this.prefs = sharedPreferences;
        this.carControlHelper = desktopAssistantCarControlHelper;
        updateData();
        refreshStyle();
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(ViewHolder viewHolder, int i7) {
        TopInfoItem topInfoItem = this.items.get(i7);
        String i8 = a.a.i(new StringBuilder(), topInfoItem.name, " ");
        StringBuilder j7 = a.a.j(i8);
        j7.append(topInfoItem.value);
        String sb = j7.toString();
        SpannableString spannableString = new SpannableString(sb);
        spannableString.setSpan(new ForegroundColorSpan(this.propertyTextColor), 0, i8.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(this.valueTextColor), i8.length(), sb.length(), 33);
        viewHolder.text.setText(spannableString);
        viewHolder.text.setTextSize(2, this.fontSize);
    }

    @Override // androidx.recyclerview.widget.h1
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        TextView textView = new TextView(viewGroup.getContext());
        textView.setGravity(16);
        textView.setLayoutParams(new androidx.recyclerview.widget.s1(-2, -1));
        int i8 = (int) (viewGroup.getResources().getDisplayMetrics().density * 12.0f);
        textView.setPadding(i8, 0, i8, 0);
        return new ViewHolder(textView);
    }

    public void refreshStyle() {
        int i7 = this.prefs.getInt("car_control_property_text_color_index", 0);
        this.propertyTextColor = -1;
        if (i7 == 0) {
            this.propertyTextColor = -1;
        } else if (i7 == 1) {
            this.propertyTextColor = -256;
        } else if (i7 == 2) {
            this.propertyTextColor = -16711936;
        } else if (i7 == 3) {
            this.propertyTextColor = -16728065;
        } else if (i7 == 4) {
            this.propertyTextColor = -47872;
        } else if (i7 == 5) {
            this.propertyTextColor = p0.d1.MEASURED_STATE_MASK;
        }
        int i8 = this.prefs.getInt("car_control_text_color_index", 0);
        this.valueTextColor = -1;
        if (i8 == 0) {
            this.valueTextColor = -1;
        } else if (i8 == 1) {
            this.valueTextColor = -256;
        } else if (i8 == 2) {
            this.valueTextColor = -16711936;
        } else if (i8 == 3) {
            this.valueTextColor = -16728065;
        } else if (i8 == 4) {
            this.valueTextColor = -47872;
        } else if (i8 == 5) {
            this.valueTextColor = p0.d1.MEASURED_STATE_MASK;
        }
        this.fontSize = this.prefs.getInt("car_control_text_size", 24);
        notifyDataSetChanged();
    }

    public void updateData() {
        String carInfoNameById;
        String carInfoNameById2;
        this.items.clear();
        String string = this.prefs.getString("top_info_item_order", s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        if (string != null && !string.isEmpty()) {
            for (String str : string.split(",")) {
                String trim = str.trim();
                if (!trim.isEmpty() && DesktopAssistantCarControlHelper.isTopInfoSupportedInCurrentMode(this.prefs, trim) && (carInfoNameById2 = this.carControlHelper.getCarInfoNameById(trim)) != null && this.prefs.getBoolean("top_info_item_enabled_".concat(trim), false)) {
                    String string2 = this.prefs.getString("top_info_item_name_".concat(trim), null);
                    String carInfoValue = this.carControlHelper.getCarInfoValue(trim);
                    List<TopInfoItem> list = this.items;
                    if (string2 != null) {
                        carInfoNameById2 = string2;
                    }
                    list.add(new TopInfoItem(trim, carInfoNameById2, carInfoValue));
                    hashSet.add(trim);
                }
            }
        }
        for (String str2 : this.carControlHelper.getTopInfoIds()) {
            if (!hashSet.contains(str2)) {
                if (this.prefs.getBoolean("top_info_item_enabled_" + str2, false) && (carInfoNameById = this.carControlHelper.getCarInfoNameById(str2)) != null) {
                    String string3 = this.prefs.getString("top_info_item_name_" + str2, null);
                    String carInfoValue2 = this.carControlHelper.getCarInfoValue(str2);
                    List<TopInfoItem> list2 = this.items;
                    if (string3 != null) {
                        carInfoNameById = string3;
                    }
                    list2.add(new TopInfoItem(str2, carInfoNameById, carInfoValue2));
                    hashSet.add(str2);
                }
            }
        }
        notifyDataSetChanged();
    }
}
