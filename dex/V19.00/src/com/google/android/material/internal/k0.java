package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.search.SearchBar;
import p0.d1;
import p0.m0;
import p0.o0;
import p0.p0;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class k0 {
    public static final int EDGE_TO_EDGE_FLAGS = 768;

    public static Rect a(View view, SearchBar searchBar) {
        int[] iArr = new int[2];
        searchBar.getLocationOnScreen(iArr);
        int i7 = iArr[0];
        int i8 = iArr[1];
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        int i9 = i7 - iArr2[0];
        int i10 = i8 - iArr2[1];
        return new Rect(i9, i10, searchBar.getWidth() + i9, searchBar.getHeight() + i10);
    }

    public static void b(View view, j0 j0Var) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int f7 = m0.f(view);
        int paddingTop = view.getPaddingTop();
        int e7 = m0.e(view);
        int paddingBottom = view.getPaddingBottom();
        androidx.media.a aVar = new androidx.media.a();
        aVar.f2235a = f7;
        aVar.f2236b = paddingTop;
        aVar.f2237c = e7;
        aVar.f2238d = paddingBottom;
        r0.u(view, new i6.i(19, j0Var, aVar));
        if (o0.b(view)) {
            p0.c(view);
        } else {
            view.addOnAttachStateChangeListener(new i0());
        }
    }

    public static float c(Context context, int i7) {
        return TypedValue.applyDimension(1, i7, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup d(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static n1.a0 e(View view) {
        ViewGroup d7 = d(view);
        if (d7 == null) {
            return null;
        }
        return new n1.a0(1, d7);
    }

    public static boolean f(View view) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return m0.d(view) == 1;
    }

    public static PorterDuff.Mode g(int i7, PorterDuff.Mode mode) {
        if (i7 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i7 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i7 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i7) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
