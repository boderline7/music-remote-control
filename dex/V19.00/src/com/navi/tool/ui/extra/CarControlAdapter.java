package com.navi.tool.ui.extra;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarControlAdapter extends androidx.recyclerview.widget.h1 {
    private OnCardLongClickListener longClickListener;
    private List<CardItem> items = new ArrayList();
    private int propertyTextColor = -1;
    private int valueTextColor = -1;
    private int cardAlphaPercent = 90;
    private int contentFontSize = 24;

    public static class CardItem {
        public int columnCount;
        public List<DetailItem> details;
        public CardType type;

        public CardItem(CardType cardType, List<DetailItem> list, int i7) {
            this.type = cardType;
            this.details = list;
            this.columnCount = i7;
        }
    }

    public enum CardType {
        HVAC("空调信息"),
        DOOR("门窗信息"),
        DRIVING("行驶信息"),
        STATUS("状态信息"),
        SEAT("座椅舒适"),
        FUEL("油耗信息"),
        NEW_ENERGY("新能源信息");

        final String title;

        CardType(String str) {
            this.title = str;
        }
    }

    public static class CardViewHolder extends androidx.recyclerview.widget.j2 {
        CardView cardView;
        GridLayout gridContent;
        TextView tvTitle;

        public CardViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view;
            this.tvTitle = (TextView) view.findViewById(g4.p.tv_card_title);
            this.gridContent = (GridLayout) view.findViewById(g4.p.grid_content);
        }
    }

    public static class DetailItem {
        public int colSpan;
        public String key;
        public String value;

        public DetailItem(String str, String str2) {
            this(str, str2, 1);
        }

        public DetailItem(String str, String str2, int i7) {
            this.key = str;
            this.value = str2;
            this.colSpan = i7;
        }
    }

    public interface OnCardLongClickListener {
        void onCardLongClick(CardType cardType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onBindViewHolder$0(CardItem cardItem, View view) {
        OnCardLongClickListener onCardLongClickListener = this.longClickListener;
        if (onCardLongClickListener == null) {
            return false;
        }
        onCardLongClickListener.onCardLongClick(cardItem.type);
        return true;
    }

    public List<CardItem> getData() {
        return this.items;
    }

    @Override // androidx.recyclerview.widget.h1
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.h1
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i7) {
        float f7;
        CardItem cardItem = this.items.get(i7);
        cardViewHolder.tvTitle.setText(cardItem.type.title);
        cardViewHolder.tvTitle.setTextColor(this.propertyTextColor);
        int i8 = (int) (((100 - this.cardAlphaPercent) / 100.0f) * 255.0f);
        try {
            int i9 = cardViewHolder.itemView.getContext().getResources().getConfiguration().uiMode;
        } catch (Exception unused) {
        }
        cardViewHolder.cardView.setCardBackgroundColor(Color.argb(i8, Color.red(0), Color.green(0), Color.blue(0)));
        if (cardViewHolder.itemView.getContext().getSharedPreferences("com.navi.tool_preferences", 0).getBoolean("desktop_round_corner_enabled", false)) {
            f7 = 24.0f;
            try {
                int identifier = cardViewHolder.itemView.getContext().getResources().getIdentifier("car_control_card_corner_radius", "dimen", cardViewHolder.itemView.getContext().getPackageName());
                if (identifier != 0) {
                    f7 = cardViewHolder.itemView.getContext().getResources().getDimension(identifier);
                }
            } catch (Exception unused2) {
            }
        } else {
            f7 = 12.0f;
        }
        cardViewHolder.cardView.setRadius(f7);
        cardViewHolder.gridContent.removeAllViews();
        cardViewHolder.gridContent.setColumnCount(cardItem.columnCount);
        List<DetailItem> list = cardItem.details;
        int i10 = 2;
        if (list != null) {
            for (DetailItem detailItem : list) {
                TextView textView = new TextView(cardViewHolder.itemView.getContext());
                String i11 = a.a.i(new StringBuilder(), detailItem.key, ": ");
                String str = detailItem.value;
                if (str != null && str.startsWith("雨感灵敏度: ")) {
                    str = str.substring(7);
                }
                SpannableString spannableString = new SpannableString(a.a.g(i11, str));
                spannableString.setSpan(new ForegroundColorSpan(this.propertyTextColor), 0, i11.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(this.valueTextColor), i11.length(), spannableString.length(), 33);
                textView.setText(spannableString);
                textView.setTextSize(2, this.contentFontSize);
                textView.setGravity(8388627);
                textView.setPadding(16, 2, 8, 16);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = 0;
                layoutParams.height = -2;
                int i12 = detailItem.colSpan;
                layoutParams.columnSpec = GridLayout.spec(Integer.MIN_VALUE, i12, i12);
                textView.setLayoutParams(layoutParams);
                cardViewHolder.gridContent.addView(textView);
            }
        }
        cardViewHolder.itemView.setOnLongClickListener(new e1(this, cardItem, i10));
    }

    @Override // androidx.recyclerview.widget.h1
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_car_control_card, viewGroup, false));
    }

    public void onItemMove(int i7, int i8) {
        if (i7 < i8) {
            int i9 = i7;
            while (i9 < i8) {
                int i10 = i9 + 1;
                Collections.swap(this.items, i9, i10);
                i9 = i10;
            }
        } else {
            for (int i11 = i7; i11 > i8; i11--) {
                Collections.swap(this.items, i11, i11 - 1);
            }
        }
        notifyItemMoved(i7, i8);
    }

    public void setCardAlpha(int i7) {
        this.cardAlphaPercent = i7;
        notifyDataSetChanged();
    }

    public void setContentFontSize(int i7) {
        this.contentFontSize = i7;
        notifyDataSetChanged();
    }

    public void setData(List<CardItem> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public void setOnCardLongClickListener(OnCardLongClickListener onCardLongClickListener) {
        this.longClickListener = onCardLongClickListener;
    }

    public void setPropertyTextColor(int i7) {
        this.propertyTextColor = i7;
        notifyDataSetChanged();
    }

    public void setValueTextColor(int i7) {
        this.valueTextColor = i7;
        notifyDataSetChanged();
    }
}
