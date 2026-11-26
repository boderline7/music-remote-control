package r2;

import android.util.Property;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends Property {
    public static final Property<ViewGroup, Float> CHILDREN_ALPHA = new c(Float.class, "childrenAlpha");

    @Override // android.util.Property
    public final Object get(Object obj) {
        Float f7 = (Float) ((ViewGroup) obj).getTag(q2.g.mtrl_internal_children_alpha_tag);
        return f7 != null ? f7 : Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ViewGroup viewGroup = (ViewGroup) obj;
        float floatValue = ((Float) obj2).floatValue();
        viewGroup.setTag(q2.g.mtrl_internal_children_alpha_tag, Float.valueOf(floatValue));
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            viewGroup.getChildAt(i7).setAlpha(floatValue);
        }
    }
}
