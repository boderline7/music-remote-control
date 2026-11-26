package androidx.appcompat.widget;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class c1 {
    public static StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i7, int i8, TextView textView, TextPaint textPaint, f1 f1Var) {
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, i7);
        StaticLayout.Builder hyphenationFrequency = obtain.setAlignment(alignment).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
        if (i8 == -1) {
            i8 = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        hyphenationFrequency.setMaxLines(i8);
        try {
            f1Var.a(obtain, textView);
        } catch (ClassCastException unused) {
        }
        return obtain.build();
    }
}
