package androidx.activity;

import android.window.BackEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {
    public static final a INSTANCE = new a();

    public final BackEvent a(float f7, float f8, float f9, int i7) {
        return new BackEvent(f7, f8, f9, i7);
    }

    public final float b(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        return backEvent.getProgress();
    }

    public final int c(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        return backEvent.getSwipeEdge();
    }

    public final float d(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        return backEvent.getTouchX();
    }

    public final float e(BackEvent backEvent) {
        i5.a.g("backEvent", backEvent);
        return backEvent.getTouchY();
    }
}
