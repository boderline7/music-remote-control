package a3;

import android.util.Property;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends Property {
    public static final Property<g, f> CIRCULAR_REVEAL = new d(f.class, "circularReveal");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return ((g) obj).getRevealInfo();
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((g) obj).setRevealInfo((f) obj2);
    }
}
