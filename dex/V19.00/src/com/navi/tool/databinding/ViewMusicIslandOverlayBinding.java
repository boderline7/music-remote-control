package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.navi.tool.view.AlwaysFocusedTextView;
import com.navi.tool.view.SpectrumView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ViewMusicIslandOverlayBinding {
    public static ViewMusicIslandOverlayBinding bind(View view) {
        int i7 = p.btn_music_island_next;
        if (((ImageButton) z.c0(i7, view)) != null) {
            i7 = p.btn_music_island_play_pause;
            if (((ImageButton) z.c0(i7, view)) != null) {
                i7 = p.btn_music_island_play_pause_large;
                if (((ImageButton) z.c0(i7, view)) != null) {
                    i7 = p.btn_music_island_prev;
                    if (((ImageButton) z.c0(i7, view)) != null) {
                        i7 = p.card_music_island_cover;
                        if (((CardView) z.c0(i7, view)) != null) {
                            i7 = p.card_music_island_cover_large;
                            if (((CardView) z.c0(i7, view)) != null) {
                                i7 = p.img_music_island_cover;
                                if (((ImageView) z.c0(i7, view)) != null) {
                                    i7 = p.img_music_island_cover_large;
                                    if (((ImageView) z.c0(i7, view)) != null) {
                                        i7 = p.img_music_island_source;
                                        if (((ImageView) z.c0(i7, view)) != null) {
                                            i7 = p.img_music_island_source_large;
                                            if (((ImageView) z.c0(i7, view)) != null) {
                                                i7 = p.layout_music_island_progress_container;
                                                if (((ConstraintLayout) z.c0(i7, view)) != null) {
                                                    i7 = p.layout_music_island_source;
                                                    if (((CardView) z.c0(i7, view)) != null) {
                                                        i7 = p.layout_music_island_text;
                                                        if (((LinearLayout) z.c0(i7, view)) != null) {
                                                            i7 = p.music_island_capsule;
                                                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                i7 = p.music_island_expanded_card;
                                                                if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                    i7 = p.text_music_island_artist_large;
                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                        i7 = p.text_music_island_current_duration;
                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                            i7 = p.text_music_island_current_lyric_large;
                                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                                i7 = p.text_music_island_lyric;
                                                                                if (((AlwaysFocusedTextView) z.c0(i7, view)) != null) {
                                                                                    i7 = p.text_music_island_next_lyric;
                                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                                        i7 = p.text_music_island_prev_lyric;
                                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                                            i7 = p.text_music_island_source_name;
                                                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                                                i7 = p.text_music_island_title;
                                                                                                if (((AlwaysFocusedTextView) z.c0(i7, view)) != null) {
                                                                                                    i7 = p.text_music_island_title_large;
                                                                                                    if (((AlwaysFocusedTextView) z.c0(i7, view)) != null) {
                                                                                                        i7 = p.text_music_island_total_duration;
                                                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                                                            i7 = p.view_music_island_progress;
                                                                                                            if (z.c0(i7, view) != null) {
                                                                                                                i7 = p.view_music_island_progress_track;
                                                                                                                if (z.c0(i7, view) != null) {
                                                                                                                    i7 = p.view_music_island_spectrum;
                                                                                                                    if (((SpectrumView) z.c0(i7, view)) != null) {
                                                                                                                        return new ViewMusicIslandOverlayBinding();
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ViewMusicIslandOverlayBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewMusicIslandOverlayBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.view_music_island_overlay, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
