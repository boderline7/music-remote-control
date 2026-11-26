package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ViewPhoneNotificationOverlayBinding {
    public static ViewPhoneNotificationOverlayBinding bind(View view) {
        int i7 = p.image_phone_notification_overlay_icon;
        if (((ImageView) z.c0(i7, view)) != null) {
            i7 = p.layout_phone_notification_overlay_expanded;
            if (((LinearLayout) z.c0(i7, view)) != null) {
                i7 = p.layout_phone_notification_overlay_header;
                if (((LinearLayout) z.c0(i7, view)) != null) {
                    i7 = p.layout_phone_notification_overlay_messages;
                    if (((LinearLayout) z.c0(i7, view)) != null) {
                        i7 = p.layout_phone_notification_overlay_summary;
                        if (((LinearLayout) z.c0(i7, view)) != null) {
                            i7 = p.scroll_phone_notification_overlay_list;
                            if (((ScrollView) z.c0(i7, view)) != null) {
                                i7 = p.text_phone_notification_overlay_app;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    i7 = p.text_phone_notification_overlay_body;
                                    if (((TextView) z.c0(i7, view)) != null) {
                                        i7 = p.text_phone_notification_overlay_close;
                                        if (((TextView) z.c0(i7, view)) != null) {
                                            i7 = p.text_phone_notification_overlay_count;
                                            if (((TextView) z.c0(i7, view)) != null) {
                                                i7 = p.text_phone_notification_overlay_hint;
                                                if (((TextView) z.c0(i7, view)) != null) {
                                                    i7 = p.text_phone_notification_overlay_title;
                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                        return new ViewPhoneNotificationOverlayBinding();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ViewPhoneNotificationOverlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewPhoneNotificationOverlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.view_phone_notification_overlay, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
