package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AlertController$RecycleListView extends ListView {

    /* renamed from: a, reason: collision with root package name */
    public final int f663a;

    /* renamed from: b, reason: collision with root package name */
    public final int f664b;

    public AlertController$RecycleListView(Context context) {
        this(context, null);
    }

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.RecycleListView);
        this.f664b = obtainStyledAttributes.getDimensionPixelOffset(f.j.RecycleListView_paddingBottomNoButtons, -1);
        this.f663a = obtainStyledAttributes.getDimensionPixelOffset(f.j.RecycleListView_paddingTopNoTitle, -1);
    }

    public void setHasDecor(boolean z6, boolean z7) {
        if (z7 && z6) {
            return;
        }
        setPadding(getPaddingLeft(), z6 ? getPaddingTop() : this.f663a, getPaddingRight(), z7 ? getPaddingBottom() : this.f664b);
    }
}
