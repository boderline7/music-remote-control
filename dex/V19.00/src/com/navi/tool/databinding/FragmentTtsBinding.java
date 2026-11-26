package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class FragmentTtsBinding {

    /* renamed from: a, reason: collision with root package name */
    public final CoordinatorLayout f4809a;

    /* renamed from: b, reason: collision with root package name */
    public final MaterialCardView f4810b;

    /* renamed from: c, reason: collision with root package name */
    public final MaterialCardView f4811c;

    /* renamed from: d, reason: collision with root package name */
    public final MaterialCardView f4812d;

    /* renamed from: e, reason: collision with root package name */
    public final LinearLayout f4813e;

    /* renamed from: f, reason: collision with root package name */
    public final RecyclerView f4814f;

    /* renamed from: g, reason: collision with root package name */
    public final SeekBar f4815g;

    /* renamed from: h, reason: collision with root package name */
    public final SeekBar f4816h;

    /* renamed from: i, reason: collision with root package name */
    public final SeekBar f4817i;

    /* renamed from: j, reason: collision with root package name */
    public final SeekBar f4818j;

    /* renamed from: k, reason: collision with root package name */
    public final SeekBar f4819k;

    /* renamed from: l, reason: collision with root package name */
    public final SwitchMaterial f4820l;

    /* renamed from: m, reason: collision with root package name */
    public final SwitchMaterial f4821m;

    /* renamed from: n, reason: collision with root package name */
    public final SwitchMaterial f4822n;

    /* renamed from: o, reason: collision with root package name */
    public final SwitchMaterial f4823o;

    /* renamed from: p, reason: collision with root package name */
    public final SwitchMaterial f4824p;

    /* renamed from: q, reason: collision with root package name */
    public final TextView f4825q;

    /* renamed from: r, reason: collision with root package name */
    public final TextView f4826r;

    /* renamed from: s, reason: collision with root package name */
    public final TextView f4827s;

    /* renamed from: t, reason: collision with root package name */
    public final TextView f4828t;

    /* renamed from: u, reason: collision with root package name */
    public final TextView f4829u;

    /* renamed from: v, reason: collision with root package name */
    public final TextView f4830v;

    public FragmentTtsBinding(CoordinatorLayout coordinatorLayout, MaterialCardView materialCardView, MaterialCardView materialCardView2, MaterialCardView materialCardView3, LinearLayout linearLayout, RecyclerView recyclerView, SeekBar seekBar, SeekBar seekBar2, SeekBar seekBar3, SeekBar seekBar4, SeekBar seekBar5, SwitchMaterial switchMaterial, SwitchMaterial switchMaterial2, SwitchMaterial switchMaterial3, SwitchMaterial switchMaterial4, SwitchMaterial switchMaterial5, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        this.f4809a = coordinatorLayout;
        this.f4810b = materialCardView;
        this.f4811c = materialCardView2;
        this.f4812d = materialCardView3;
        this.f4813e = linearLayout;
        this.f4814f = recyclerView;
        this.f4815g = seekBar;
        this.f4816h = seekBar2;
        this.f4817i = seekBar3;
        this.f4818j = seekBar4;
        this.f4819k = seekBar5;
        this.f4820l = switchMaterial;
        this.f4821m = switchMaterial2;
        this.f4822n = switchMaterial3;
        this.f4823o = switchMaterial4;
        this.f4824p = switchMaterial5;
        this.f4825q = textView;
        this.f4826r = textView2;
        this.f4827s = textView3;
        this.f4828t = textView4;
        this.f4829u = textView5;
        this.f4830v = textView6;
    }

    public static FragmentTtsBinding bind(View view) {
        int i7 = p.cardDestinationWeather;
        MaterialCardView materialCardView = (MaterialCardView) z.c0(i7, view);
        if (materialCardView != null) {
            i7 = p.cardTrafficLightSpeedLimit;
            MaterialCardView materialCardView2 = (MaterialCardView) z.c0(i7, view);
            if (materialCardView2 != null) {
                i7 = p.cardZoneSpeedVoiceSettings;
                MaterialCardView materialCardView3 = (MaterialCardView) z.c0(i7, view);
                if (materialCardView3 != null) {
                    i7 = p.layoutDestinationWeatherSettings;
                    LinearLayout linearLayout = (LinearLayout) z.c0(i7, view);
                    if (linearLayout != null) {
                        i7 = p.recyclerViewApps;
                        RecyclerView recyclerView = (RecyclerView) z.c0(i7, view);
                        if (recyclerView != null) {
                            i7 = p.seekBarDestinationWeather;
                            SeekBar seekBar = (SeekBar) z.c0(i7, view);
                            if (seekBar != null) {
                                i7 = p.seekBarTrafficLightSpeedLimit;
                                SeekBar seekBar2 = (SeekBar) z.c0(i7, view);
                                if (seekBar2 != null) {
                                    i7 = p.seekBarZoneSpeedCount;
                                    SeekBar seekBar3 = (SeekBar) z.c0(i7, view);
                                    if (seekBar3 != null) {
                                        i7 = p.seekBarZoneSpeedInterval;
                                        SeekBar seekBar4 = (SeekBar) z.c0(i7, view);
                                        if (seekBar4 != null) {
                                            i7 = p.seekBarZoneSpeedThreshold;
                                            SeekBar seekBar5 = (SeekBar) z.c0(i7, view);
                                            if (seekBar5 != null) {
                                                i7 = p.switchCruiseTrafficLight;
                                                SwitchMaterial switchMaterial = (SwitchMaterial) z.c0(i7, view);
                                                if (switchMaterial != null) {
                                                    i7 = p.switchDestinationWeather;
                                                    SwitchMaterial switchMaterial2 = (SwitchMaterial) z.c0(i7, view);
                                                    if (switchMaterial2 != null) {
                                                        i7 = p.switchTtsEnabled;
                                                        SwitchMaterial switchMaterial3 = (SwitchMaterial) z.c0(i7, view);
                                                        if (switchMaterial3 != null) {
                                                            i7 = p.switchTtsRoadName;
                                                            SwitchMaterial switchMaterial4 = (SwitchMaterial) z.c0(i7, view);
                                                            if (switchMaterial4 != null) {
                                                                i7 = p.switchZoneSpeedVoice;
                                                                SwitchMaterial switchMaterial5 = (SwitchMaterial) z.c0(i7, view);
                                                                if (switchMaterial5 != null) {
                                                                    i7 = p.textDestinationWeatherValue;
                                                                    TextView textView = (TextView) z.c0(i7, view);
                                                                    if (textView != null) {
                                                                        i7 = p.textNoApps;
                                                                        TextView textView2 = (TextView) z.c0(i7, view);
                                                                        if (textView2 != null) {
                                                                            i7 = p.textTrafficLightSpeedLimitValue;
                                                                            TextView textView3 = (TextView) z.c0(i7, view);
                                                                            if (textView3 != null) {
                                                                                i7 = p.textZoneSpeedCountValue;
                                                                                TextView textView4 = (TextView) z.c0(i7, view);
                                                                                if (textView4 != null) {
                                                                                    i7 = p.textZoneSpeedIntervalValue;
                                                                                    TextView textView5 = (TextView) z.c0(i7, view);
                                                                                    if (textView5 != null) {
                                                                                        i7 = p.textZoneSpeedThresholdValue;
                                                                                        TextView textView6 = (TextView) z.c0(i7, view);
                                                                                        if (textView6 != null) {
                                                                                            return new FragmentTtsBinding((CoordinatorLayout) view, materialCardView, materialCardView2, materialCardView3, linearLayout, recyclerView, seekBar, seekBar2, seekBar3, seekBar4, seekBar5, switchMaterial, switchMaterial2, switchMaterial3, switchMaterial4, switchMaterial5, textView, textView2, textView3, textView4, textView5, textView6);
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

    public static FragmentTtsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentTtsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.fragment_tts, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
