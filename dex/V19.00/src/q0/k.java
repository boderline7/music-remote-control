package q0;

import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class k extends j {
    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i7) {
        i b7 = this.f8429a.b(i7);
        if (b7 == null) {
            return null;
        }
        return b7.f8426a;
    }
}
