package androidx.appcompat.widget;

import android.text.StaticLayout;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f1 {
    public abstract void a(StaticLayout.Builder builder, TextView textView);

    public boolean b(TextView textView) {
        return ((Boolean) g1.e(textView, Boolean.FALSE, "getHorizontallyScrolling")).booleanValue();
    }
}
