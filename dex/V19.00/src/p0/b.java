package p0;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeProvider;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {
    public static AccessibilityNodeProvider a(View.AccessibilityDelegate accessibilityDelegate, View view) {
        return accessibilityDelegate.getAccessibilityNodeProvider(view);
    }

    public static boolean b(View.AccessibilityDelegate accessibilityDelegate, View view, int i7, Bundle bundle) {
        return accessibilityDelegate.performAccessibilityAction(view, i7, bundle);
    }
}
