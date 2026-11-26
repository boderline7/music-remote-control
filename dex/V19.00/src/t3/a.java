package t3;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import j.f;
import q2.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f9082a = {R.attr.theme, c.theme};

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f9083b = {c.materialThemeOverlay};

    public static Context a(Context context, AttributeSet attributeSet, int i7, int i8) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f9083b, i7, i8);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        boolean z6 = (context instanceof f) && ((f) context).f6878a == resourceId;
        if (resourceId == 0 || z6) {
            return context;
        }
        f fVar = new f(context, resourceId);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f9082a);
        int resourceId2 = obtainStyledAttributes2.getResourceId(0, 0);
        int resourceId3 = obtainStyledAttributes2.getResourceId(1, 0);
        obtainStyledAttributes2.recycle();
        if (resourceId2 == 0) {
            resourceId2 = resourceId3;
        }
        if (resourceId2 != 0) {
            fVar.getTheme().applyStyle(resourceId2, true);
        }
        return fVar;
    }
}
