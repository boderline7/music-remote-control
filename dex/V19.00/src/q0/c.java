package q0;

import android.view.accessibility.AccessibilityManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {
    public static boolean a(AccessibilityManager accessibilityManager, d dVar) {
        return accessibilityManager.addTouchExplorationStateChangeListener(new e(dVar));
    }

    public static boolean b(AccessibilityManager accessibilityManager, d dVar) {
        return accessibilityManager.removeTouchExplorationStateChangeListener(new e(dVar));
    }
}
