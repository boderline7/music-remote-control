package q0;

import android.view.accessibility.AccessibilityRecord;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n {
    public static int a(AccessibilityRecord accessibilityRecord) {
        return accessibilityRecord.getMaxScrollX();
    }

    public static int b(AccessibilityRecord accessibilityRecord) {
        return accessibilityRecord.getMaxScrollY();
    }

    public static void c(AccessibilityRecord accessibilityRecord, int i7) {
        accessibilityRecord.setMaxScrollX(i7);
    }

    public static void d(AccessibilityRecord accessibilityRecord, int i7) {
        accessibilityRecord.setMaxScrollY(i7);
    }
}
