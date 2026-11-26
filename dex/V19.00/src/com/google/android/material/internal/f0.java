package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f0 {

    /* renamed from: a, reason: collision with root package name */
    public static final c0.g f3856a = new c0.g(7);

    /* renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f3857b = 0;

    public static ActionMenuView a(Toolbar toolbar) {
        for (int i7 = 0; i7 < toolbar.getChildCount(); i7++) {
            View childAt = toolbar.getChildAt(i7);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    public static ImageButton b(Toolbar toolbar) {
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon == null) {
            return null;
        }
        for (int i7 = 0; i7 < toolbar.getChildCount(); i7++) {
            View childAt = toolbar.getChildAt(i7);
            if (childAt instanceof ImageButton) {
                ImageButton imageButton = (ImageButton) childAt;
                if (imageButton.getDrawable() == navigationIcon) {
                    return imageButton;
                }
            }
        }
        return null;
    }

    public static ArrayList c(Toolbar toolbar, CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < toolbar.getChildCount(); i7++) {
            View childAt = toolbar.getChildAt(i7);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (TextUtils.equals(textView.getText(), charSequence)) {
                    arrayList.add(textView);
                }
            }
        }
        return arrayList;
    }
}
