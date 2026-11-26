package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.ui.extra.GlowProgressView;
import com.navi.tool.view.CoverGradientBackgroundView;
import com.navi.tool.view.DesktopMapCalendarCardView;
import com.navi.tool.view.LyricsFxView;
import com.navi.tool.view.RealtimeBlurView;
import com.navi.tool.view.SpectrumView;
import com.navi.tool.view.WeatherAmbientView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityDesktopAssistantBinding {
    public static ActivityDesktopAssistantBinding bind(View view) {
        int i7 = p.btn_center_handle;
        if (((LinearLayout) z.c0(i7, view)) != null) {
            i7 = p.btn_control_handle;
            if (((ImageView) z.c0(i7, view)) != null) {
                i7 = p.btn_next;
                if (((ImageButton) z.c0(i7, view)) != null) {
                    i7 = p.btn_play_pause;
                    if (((ImageButton) z.c0(i7, view)) != null) {
                        i7 = p.btn_prev;
                        if (((ImageButton) z.c0(i7, view)) != null) {
                            i7 = p.btn_split_handle;
                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                i7 = p.btn_switch_mode;
                                if (((ImageView) z.c0(i7, view)) != null) {
                                    i7 = p.card_album_art;
                                    if (((CardView) z.c0(i7, view)) != null) {
                                        i7 = p.center_container;
                                        if (((RealtimeBlurView) z.c0(i7, view)) != null) {
                                            i7 = p.center_divider;
                                            if (((FrameLayout) z.c0(i7, view)) != null) {
                                                i7 = p.container_disc;
                                                if (((FrameLayout) z.c0(i7, view)) != null) {
                                                    i7 = p.content_container;
                                                    if (((ConstraintLayout) z.c0(i7, view)) != null) {
                                                        i7 = p.control_handle_container;
                                                        if (((LinearLayout) z.c0(i7, view)) != null) {
                                                            i7 = p.control_overlay;
                                                            if (((FrameLayout) z.c0(i7, view)) != null) {
                                                                i7 = p.cover_gradient_background;
                                                                if (((CoverGradientBackgroundView) z.c0(i7, view)) != null) {
                                                                    i7 = p.dock_container;
                                                                    if (((RealtimeBlurView) z.c0(i7, view)) != null) {
                                                                        i7 = p.img_album_art_full;
                                                                        if (((ImageView) z.c0(i7, view)) != null) {
                                                                            i7 = p.img_fav_heart;
                                                                            if (((ImageView) z.c0(i7, view)) != null) {
                                                                                i7 = p.img_loop_mode;
                                                                                if (((ImageView) z.c0(i7, view)) != null) {
                                                                                    i7 = p.img_music_app_icon;
                                                                                    if (((ImageView) z.c0(i7, view)) != null) {
                                                                                        i7 = p.img_signal_icon;
                                                                                        if (((ImageView) z.c0(i7, view)) != null) {
                                                                                            i7 = p.img_top_signal;
                                                                                            if (((ImageView) z.c0(i7, view)) != null) {
                                                                                                i7 = p.img_top_wifi;
                                                                                                if (((ImageView) z.c0(i7, view)) != null) {
                                                                                                    i7 = p.img_weather_icon;
                                                                                                    if (((ImageView) z.c0(i7, view)) != null) {
                                                                                                        i7 = p.img_wifi_icon;
                                                                                                        if (((ImageView) z.c0(i7, view)) != null) {
                                                                                                            i7 = p.layout_clock_weather_header;
                                                                                                            if (((ConstraintLayout) z.c0(i7, view)) != null) {
                                                                                                                i7 = p.layout_controls;
                                                                                                                if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                    i7 = p.layout_dock_actions;
                                                                                                                    if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                        i7 = p.layout_progress_container;
                                                                                                                        if (((ConstraintLayout) z.c0(i7, view)) != null) {
                                                                                                                            i7 = p.layout_song_title_row;
                                                                                                                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                                i7 = p.layout_status_icons;
                                                                                                                                if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                                    i7 = p.left_container;
                                                                                                                                    if (((RealtimeBlurView) z.c0(i7, view)) != null) {
                                                                                                                                        i7 = p.lyrics_fx_view;
                                                                                                                                        if (((LyricsFxView) z.c0(i7, view)) != null) {
                                                                                                                                            i7 = p.lyrics_view_content;
                                                                                                                                            if (((FrameLayout) z.c0(i7, view)) != null) {
                                                                                                                                                i7 = p.map_container;
                                                                                                                                                if (((FrameLayout) z.c0(i7, view)) != null) {
                                                                                                                                                    i7 = p.map_view_content;
                                                                                                                                                    if (((FrameLayout) z.c0(i7, view)) != null) {
                                                                                                                                                        i7 = p.recycler_app_list;
                                                                                                                                                        if (((RecyclerView) z.c0(i7, view)) != null) {
                                                                                                                                                            i7 = p.recycler_dock_apps;
                                                                                                                                                            if (((RecyclerView) z.c0(i7, view)) != null) {
                                                                                                                                                                i7 = p.recycler_lyrics;
                                                                                                                                                                if (((RecyclerView) z.c0(i7, view)) != null) {
                                                                                                                                                                    i7 = p.recycler_top_info;
                                                                                                                                                                    if (((RecyclerView) z.c0(i7, view)) != null) {
                                                                                                                                                                        i7 = p.right_container;
                                                                                                                                                                        if (((RealtimeBlurView) z.c0(i7, view)) != null) {
                                                                                                                                                                            i7 = p.split_container;
                                                                                                                                                                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                                                                                i7 = p.split_divider;
                                                                                                                                                                                if (((FrameLayout) z.c0(i7, view)) != null) {
                                                                                                                                                                                    i7 = p.text_artist;
                                                                                                                                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                        i7 = p.text_clock;
                                                                                                                                                                                        if (((TextClock) z.c0(i7, view)) != null) {
                                                                                                                                                                                            i7 = p.text_control_guide;
                                                                                                                                                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                i7 = p.text_current_duration;
                                                                                                                                                                                                if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                    i7 = p.text_current_lyric;
                                                                                                                                                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                        i7 = p.text_map_placeholder;
                                                                                                                                                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                            i7 = p.text_song_title;
                                                                                                                                                                                                            if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                i7 = p.text_top_date;
                                                                                                                                                                                                                if (((TextClock) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                    i7 = p.text_total_duration;
                                                                                                                                                                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                        i7 = p.text_weather_clock;
                                                                                                                                                                                                                        if (((TextClock) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                            i7 = p.text_weather_date;
                                                                                                                                                                                                                            if (((TextClock) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                i7 = p.text_weather_location;
                                                                                                                                                                                                                                if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                    i7 = p.text_weather_lunar;
                                                                                                                                                                                                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                        i7 = p.text_weather_summary;
                                                                                                                                                                                                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                            i7 = p.top_info_bar_container;
                                                                                                                                                                                                                                            if (((RealtimeBlurView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                i7 = p.top_info_bar_surface;
                                                                                                                                                                                                                                                if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                    i7 = p.top_info_left_container;
                                                                                                                                                                                                                                                    if (((LinearLayout) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                        i7 = p.video_wallpaper_mask;
                                                                                                                                                                                                                                                        if (z.c0(i7, view) != null) {
                                                                                                                                                                                                                                                            i7 = p.video_wallpaper_view;
                                                                                                                                                                                                                                                            if (((TextureView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                i7 = p.view_map_calendar_card;
                                                                                                                                                                                                                                                                if (((DesktopMapCalendarCardView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                    i7 = p.view_music_seek_touch_area;
                                                                                                                                                                                                                                                                    if (z.c0(i7, view) != null) {
                                                                                                                                                                                                                                                                        i7 = p.view_progress;
                                                                                                                                                                                                                                                                        if (((GlowProgressView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                            i7 = p.view_spectrum;
                                                                                                                                                                                                                                                                            if (((SpectrumView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                                i7 = p.view_weather_ambient;
                                                                                                                                                                                                                                                                                if (((WeatherAmbientView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                                    i7 = p.wallpaper_view;
                                                                                                                                                                                                                                                                                    if (((ImageView) z.c0(i7, view)) != null) {
                                                                                                                                                                                                                                                                                        return new ActivityDesktopAssistantBinding();
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

    public static ActivityDesktopAssistantBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityDesktopAssistantBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_desktop_assistant, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
