package androidx.appcompat.widget;

import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class v0 {
    public static int a(TextView textView) {
        return textView.getAutoSizeStepGranularity();
    }

    public static void b(TextView textView, int i7, int i8, int i9, int i10) {
        textView.setAutoSizeTextTypeUniformWithConfiguration(i7, i8, i9, i10);
    }

    public static void c(TextView textView, int[] iArr, int i7) {
        textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
    }

    public static boolean d(TextView textView, String str) {
        return textView.setFontVariationSettings(str);
    }
}
