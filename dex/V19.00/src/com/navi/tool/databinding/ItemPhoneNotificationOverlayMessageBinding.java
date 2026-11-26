package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ItemPhoneNotificationOverlayMessageBinding {
    public static ItemPhoneNotificationOverlayMessageBinding bind(View view) {
        int i7 = p.text_phone_notification_overlay_item_app;
        if (((TextView) z.c0(i7, view)) != null) {
            i7 = p.text_phone_notification_overlay_item_body;
            if (((TextView) z.c0(i7, view)) != null) {
                i7 = p.text_phone_notification_overlay_item_title;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.view_phone_notification_overlay_item_divider;
                    if (z.c0(i7, view) != null) {
                        return new ItemPhoneNotificationOverlayMessageBinding();
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ItemPhoneNotificationOverlayMessageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPhoneNotificationOverlayMessageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.item_phone_notification_overlay_message, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
