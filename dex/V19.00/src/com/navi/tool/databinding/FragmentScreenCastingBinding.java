package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentScreenCastingBinding {
    public static FragmentScreenCastingBinding bind(View view) {
        int i7 = p.btn_custom_casting;
        if (((MaterialButton) z.c0(i7, view)) != null) {
            i7 = p.card_apps_list;
            if (((MaterialCardView) z.c0(i7, view)) != null) {
                i7 = p.card_cluster_enlarge_casting;
                if (((MaterialCardView) z.c0(i7, view)) != null) {
                    i7 = p.card_custom_casting;
                    if (((MaterialCardView) z.c0(i7, view)) != null) {
                        i7 = p.layout_screen_casting_permission;
                        if (((TextView) z.c0(i7, view)) != null) {
                            i7 = p.recycler_apps;
                            if (((RecyclerView) z.c0(i7, view)) != null) {
                                i7 = p.seek_icon_alpha;
                                if (((SeekBar) z.c0(i7, view)) != null) {
                                    i7 = p.seek_icon_size;
                                    if (((SeekBar) z.c0(i7, view)) != null) {
                                        i7 = p.switch_cluster_enlarge_casting;
                                        if (((SwitchMaterial) z.c0(i7, view)) != null) {
                                            i7 = p.switch_screen_casting;
                                            if (((SwitchMaterial) z.c0(i7, view)) != null) {
                                                i7 = p.tv_icon_alpha;
                                                if (((TextView) z.c0(i7, view)) != null) {
                                                    i7 = p.tv_icon_size;
                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                        return new FragmentScreenCastingBinding();
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

    public static FragmentScreenCastingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentScreenCastingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_screen_casting, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
