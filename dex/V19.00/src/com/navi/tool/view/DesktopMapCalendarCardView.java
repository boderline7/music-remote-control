package com.navi.tool.view;

import a2.q;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import g4.p;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopMapCalendarCardView extends FrameLayout {

    /* renamed from: e, reason: collision with root package name */
    public static final String[] f5918e = {"一", "二", "三", "四", "五", "六", "日"};

    /* renamed from: f, reason: collision with root package name */
    public static final HashMap f5919f;

    /* renamed from: g, reason: collision with root package name */
    public static final HashMap f5920g;

    /* renamed from: h, reason: collision with root package name */
    public static final SimpleDateFormat f5921h;

    /* renamed from: a, reason: collision with root package name */
    public TextView f5922a;

    /* renamed from: b, reason: collision with root package name */
    public LinearLayout f5923b;

    /* renamed from: c, reason: collision with root package name */
    public LinearLayout f5924c;

    /* renamed from: d, reason: collision with root package name */
    public final q[] f5925d;

    static {
        HashMap hashMap = new HashMap();
        f5919f = hashMap;
        HashMap hashMap2 = new HashMap();
        f5920g = hashMap2;
        hashMap.put("01-01", "元旦");
        hashMap.put("02-14", "情人节");
        hashMap.put("03-08", "妇女节");
        hashMap.put("03-12", "植树节");
        hashMap.put("03-15", "消费者日");
        hashMap.put("04-01", "愚人节");
        hashMap.put("04-22", "地球日");
        hashMap.put("05-01", "劳动节");
        hashMap.put("05-04", "青年节");
        hashMap.put("05-12", "护士节");
        hashMap.put("06-01", "儿童节");
        hashMap.put("07-01", "建党节");
        hashMap.put("08-01", "建军节");
        hashMap.put("09-10", "教师节");
        hashMap.put("10-01", "国庆节");
        hashMap.put("11-11", "光棍节");
        hashMap.put("12-13", "国家公祭日");
        hashMap.put("12-24", "平安夜");
        hashMap.put("12-25", "圣诞节");
        hashMap2.put("01-01", "春节");
        hashMap2.put("01-15", "元宵");
        hashMap2.put("02-02", "龙抬头");
        hashMap2.put("05-05", "端午");
        hashMap2.put("07-07", "七夕");
        hashMap2.put("07-15", "中元");
        hashMap2.put("08-15", "中秋");
        hashMap2.put("09-09", "重阳");
        hashMap2.put("10-01", "寒衣");
        hashMap2.put("10-15", "下元");
        hashMap2.put("12-08", "腊八");
        hashMap2.put("12-23", "小年");
        hashMap2.put("12-24", "小年");
        f5921h = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    public DesktopMapCalendarCardView(Context context) {
        super(context);
        this.f5925d = new q[42];
        b(context);
    }

    public DesktopMapCalendarCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5925d = new q[42];
        b(context);
    }

    public DesktopMapCalendarCardView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f5925d = new q[42];
        b(context);
    }

    public static boolean c(Calendar calendar, int i7, int i8, int i9) {
        return calendar.get(2) == i7 && calendar.get(7) == i8 && ((calendar.get(5) - 1) / 7) + 1 == i9;
    }

    public final int a(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    public final void b(Context context) {
        float f7;
        LayoutInflater.from(context).inflate(g4.q.view_map_calendar_card, (ViewGroup) this, true);
        this.f5922a = (TextView) findViewById(p.text_calendar_title);
        this.f5923b = (LinearLayout) findViewById(p.layout_calendar_week_header);
        this.f5924c = (LinearLayout) findViewById(p.layout_calendar_rows);
        this.f5923b.removeAllViews();
        int i7 = 0;
        int i8 = 0;
        while (true) {
            f7 = 1.0f;
            if (i8 >= 7) {
                break;
            }
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            textView.setGravity(17);
            textView.setText(f5918e[i8]);
            textView.setTextSize(20.0f);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setTextColor(i8 >= 5 ? -36245 : -855638017);
            this.f5923b.addView(textView);
            i8++;
        }
        this.f5924c.removeAllViews();
        int i9 = 0;
        int i10 = 0;
        while (i9 < 6) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(i7);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, i7, f7));
            int i11 = i7;
            while (i11 < 7) {
                FrameLayout frameLayout = new FrameLayout(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i7, -1, f7);
                int a7 = a(3);
                layoutParams.setMargins(a7, a7, a7, a7);
                frameLayout.setLayoutParams(layoutParams);
                frameLayout.setPadding(a(4), a(6), a(4), a(6));
                LinearLayout linearLayout2 = new LinearLayout(getContext());
                linearLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                linearLayout2.setOrientation(1);
                linearLayout2.setGravity(17);
                TextView textView2 = new TextView(getContext());
                textView2.setGravity(17);
                textView2.setTextSize(28.0f);
                textView2.setTypeface(Typeface.DEFAULT_BOLD);
                TextView textView3 = new TextView(getContext());
                textView3.setGravity(17);
                textView3.setTextSize(16.0f);
                textView3.setMaxLines(2);
                linearLayout2.addView(textView2, new LinearLayout.LayoutParams(-1, -2));
                linearLayout2.addView(textView3, new LinearLayout.LayoutParams(-1, -2));
                frameLayout.addView(linearLayout2);
                linearLayout.addView(frameLayout);
                this.f5925d[i10] = new q(frameLayout, textView2, textView3, i11);
                i11++;
                i10++;
                i7 = 0;
                f7 = 1.0f;
            }
            this.f5924c.addView(linearLayout);
            i9++;
            i7 = 0;
            f7 = 1.0f;
        }
        d();
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x024a, code lost:
    
        if (r3 == null) goto L84;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02b5  */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [android.view.View$OnClickListener, android.view.View$OnTouchListener] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d() {
        /*
            Method dump skipped, instructions count: 982
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.view.DesktopMapCalendarCardView.d():void");
    }
}
