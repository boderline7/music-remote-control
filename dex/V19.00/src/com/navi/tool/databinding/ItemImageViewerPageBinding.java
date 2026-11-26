package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.navi.tool.view.ZoomableImageView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemImageViewerPageBinding {
    public static ItemImageViewerPageBinding bind(View view) {
        int i7 = p.image_viewer_content;
        if (((ZoomableImageView) z.c0(i7, view)) != null) {
            i7 = p.text_image_error;
            if (((TextView) z.c0(i7, view)) != null) {
                return new ItemImageViewerPageBinding();
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemImageViewerPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemImageViewerPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_image_viewer_page, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
