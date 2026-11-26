package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class e0 {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f3850a = {q2.c.colorPrimary};

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f3851b = {q2.c.colorPrimaryVariant};

    public static void a(Context context, AttributeSet attributeSet, int i7, int i8) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q2.m.ThemeEnforcement, i7, i8);
        boolean z6 = obtainStyledAttributes.getBoolean(q2.m.ThemeEnforcement_enforceMaterialTheme, false);
        obtainStyledAttributes.recycle();
        if (z6) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(q2.c.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                c(context, f3851b, "Theme.MaterialComponents");
            }
        }
        c(context, f3850a, "Theme.AppCompat");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
    
        if (r0.getResourceId(q2.m.ThemeEnforcement_android_textAppearance, -1) != (-1)) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(android.content.Context r5, android.util.AttributeSet r6, int[] r7, int r8, int r9, int... r10) {
        /*
            int[] r0 = q2.m.ThemeEnforcement
            android.content.res.TypedArray r0 = r5.obtainStyledAttributes(r6, r0, r8, r9)
            int r1 = q2.m.ThemeEnforcement_enforceTextAppearance
            r2 = 0
            boolean r1 = r0.getBoolean(r1, r2)
            if (r1 != 0) goto L13
            r0.recycle()
            return
        L13:
            int r1 = r10.length
            r3 = 1
            r4 = -1
            if (r1 != 0) goto L22
            int r5 = q2.m.ThemeEnforcement_android_textAppearance
            int r5 = r0.getResourceId(r5, r4)
            if (r5 == r4) goto L3d
        L20:
            r2 = r3
            goto L3d
        L22:
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7, r8, r9)
            int r6 = r10.length
            r7 = r2
        L28:
            if (r7 >= r6) goto L39
            r8 = r10[r7]
            int r8 = r5.getResourceId(r8, r4)
            if (r8 != r4) goto L36
            r5.recycle()
            goto L3d
        L36:
            int r7 = r7 + 1
            goto L28
        L39:
            r5.recycle()
            goto L20
        L3d:
            r0.recycle()
            if (r2 == 0) goto L43
            return
        L43:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant)."
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.internal.e0.b(android.content.Context, android.util.AttributeSet, int[], int, int, int[]):void");
    }

    public static void c(Context context, int[] iArr, String str) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        for (int i7 = 0; i7 < iArr.length; i7++) {
            if (!obtainStyledAttributes.hasValue(i7)) {
                obtainStyledAttributes.recycle();
                throw new IllegalArgumentException(a.a.h("The style on this component requires your app theme to be ", str, " (or a descendant)."));
            }
        }
        obtainStyledAttributes.recycle();
    }

    public static TypedArray d(Context context, AttributeSet attributeSet, int[] iArr, int i7, int i8, int... iArr2) {
        a(context, attributeSet, i7, i8);
        b(context, attributeSet, iArr, i7, i8, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i7, i8);
    }

    public static androidx.appcompat.app.g e(Context context, AttributeSet attributeSet, int[] iArr, int i7, int i8, int... iArr2) {
        a(context, attributeSet, i7, i8);
        b(context, attributeSet, iArr, i7, i8, iArr2);
        return new androidx.appcompat.app.g(context, context.obtainStyledAttributes(attributeSet, iArr, i7, i8));
    }
}
