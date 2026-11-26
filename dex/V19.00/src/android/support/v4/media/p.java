package android.support.v4.media;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import androidx.appcompat.widget.w;
import com.google.android.material.datepicker.t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    public final Object f552a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f553b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f554c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f555d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f556e;

    /* renamed from: f, reason: collision with root package name */
    public final Object f557f;

    /* renamed from: g, reason: collision with root package name */
    public final Object f558g;

    /* renamed from: h, reason: collision with root package name */
    public final Object f559h;

    public p(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(l6.f.w0(context, q2.c.materialCalendarStyle, t.class.getCanonicalName()).data, q2.m.MaterialCalendar);
        this.f552a = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_dayStyle, 0));
        this.f558g = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_dayInvalidStyle, 0));
        this.f553b = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_daySelectedStyle, 0));
        this.f554c = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList w6 = l6.f.w(context, obtainStyledAttributes, q2.m.MaterialCalendar_rangeFillColor);
        this.f555d = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_yearStyle, 0));
        this.f556e = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_yearSelectedStyle, 0));
        this.f557f = w.c(context, obtainStyledAttributes.getResourceId(q2.m.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f559h = paint;
        paint.setColor(w6.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
