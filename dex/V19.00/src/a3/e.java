package a3;

import android.util.Property;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends Property {
    public static final Property<g, Integer> CIRCULAR_REVEAL_SCRIM_COLOR = new e(Integer.class, "circularRevealScrimColor");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return Integer.valueOf(((g) obj).getCircularRevealScrimColor());
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((g) obj).setCircularRevealScrimColor(((Integer) obj2).intValue());
    }
}
