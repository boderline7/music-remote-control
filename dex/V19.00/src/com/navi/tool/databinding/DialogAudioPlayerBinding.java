package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class DialogAudioPlayerBinding {
    public static DialogAudioPlayerBinding bind(View view) {
        int i7 = p.btn_play_pause;
        if (((ImageButton) z.c0(i7, view)) != null) {
            i7 = p.seek_bar;
            if (((SeekBar) z.c0(i7, view)) != null) {
                i7 = p.tv_current_time;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.tv_title;
                    if (((TextView) z.c0(i7, view)) != null) {
                        i7 = p.tv_total_time;
                        if (((TextView) z.c0(i7, view)) != null) {
                            return new DialogAudioPlayerBinding();
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static DialogAudioPlayerBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogAudioPlayerBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.dialog_audio_player, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
