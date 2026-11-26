package q0;

import android.view.accessibility.AccessibilityNodeInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_SINGLE = 1;

    /* renamed from: a, reason: collision with root package name */
    public final Object f8425a;

    public h(AccessibilityNodeInfo.CollectionInfo collectionInfo) {
        this.f8425a = collectionInfo;
    }

    public static h a(int i7, int i8, int i9) {
        return new h(AccessibilityNodeInfo.CollectionInfo.obtain(i7, i8, false, i9));
    }
}
