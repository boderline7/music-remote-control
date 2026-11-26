package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityFileVideoPreviewBinding {
    public static ActivityFileVideoPreviewBinding bind(View view) {
        int i7 = p.btn_close_video_preview;
        if (((ImageButton) z.c0(i7, view)) != null) {
            i7 = p.btn_video_mute;
            if (((ImageButton) z.c0(i7, view)) != null) {
                i7 = p.btn_video_play_pause;
                if (((ImageButton) z.c0(i7, view)) != null) {
                    i7 = p.layout_video_controls;
                    if (((LinearLayout) z.c0(i7, view)) != null) {
                        i7 = p.layout_video_top_bar;
                        if (((LinearLayout) z.c0(i7, view)) != null) {
                            i7 = p.seek_video_progress;
                            if (((SeekBar) z.c0(i7, view)) != null) {
                                i7 = p.text_video_current_time;
                                if (((TextView) z.c0(i7, view)) != null) {
                                    i7 = p.text_video_title;
                                    if (((TextView) z.c0(i7, view)) != null) {
                                        i7 = p.text_video_total_time;
                                        if (((TextView) z.c0(i7, view)) != null) {
                                            i7 = p.texture_video_preview;
                                            if (((TextureView) z.c0(i7, view)) != null) {
                                                return new ActivityFileVideoPreviewBinding();
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

    public static ActivityFileVideoPreviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityFileVideoPreviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_file_video_preview, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
