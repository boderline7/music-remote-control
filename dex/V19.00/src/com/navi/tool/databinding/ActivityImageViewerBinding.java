package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityImageViewerBinding {
    public static ActivityImageViewerBinding bind(View view) {
        int i7 = p.btn_close_image_viewer;
        if (((ImageButton) z.c0(i7, view)) != null) {
            i7 = p.text_image_counter;
            if (((TextView) z.c0(i7, view)) != null) {
                i7 = p.view_pager_images;
                if (((ViewPager2) z.c0(i7, view)) != null) {
                    return new ActivityImageViewerBinding();
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ActivityImageViewerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityImageViewerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_image_viewer, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
