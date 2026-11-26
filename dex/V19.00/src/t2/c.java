package t2;

import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {
    public static final boolean USE_COMPAT_PARENT = false;

    public static void a(a aVar, View view, FrameLayout frameLayout) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        aVar.setBounds(rect);
        aVar.i(view, frameLayout);
        if (aVar.d() != null) {
            aVar.d().setForeground(aVar);
        } else {
            if (USE_COMPAT_PARENT) {
                throw new IllegalArgumentException("Trying to reference null customBadgeParent");
            }
            view.getOverlay().add(aVar);
        }
    }
}
