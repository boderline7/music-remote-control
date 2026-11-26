package j4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.z;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.databinding.FragmentDashboardExtensionBinding;
import com.navi.tool.ui.theme.PositionSettingsDialog;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.yalantis.ucrop.view.CropImageView;
import g4.p;
import g4.u;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class g extends z {

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f6988g = {-1, Color.parseColor("#FFFF00"), Color.parseColor("#00FF00"), Color.parseColor("#87CEEB"), Color.parseColor("#FF4500"), d1.MEASURED_STATE_MASK};

    /* renamed from: a, reason: collision with root package name */
    public FragmentDashboardExtensionBinding f6989a;

    /* renamed from: b, reason: collision with root package name */
    public SharedPreferences f6990b;

    /* renamed from: c, reason: collision with root package name */
    public DisplayManager f6991c;

    /* renamed from: d, reason: collision with root package name */
    public int f6992d = -1;

    /* renamed from: e, reason: collision with root package name */
    public boolean f6993e = false;

    /* renamed from: f, reason: collision with root package name */
    public d.b f6994f;

    public static int d(int i7, int i8, int i9) {
        return Math.max(i8, Math.min(i9, i7));
    }

    public final void e() {
        if (!this.f6993e) {
            ToastHelper.show(getContext(), "仪表未初始化，将在主屏幕显示");
        }
        if (!Settings.canDrawOverlays(requireContext())) {
            this.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + requireContext().getPackageName())));
            ToastHelper.showLong(getContext(), "需要悬浮窗权限才能显示车道线");
            return;
        }
        int i7 = this.f6990b.getInt("lane_lines_x", 0);
        int i8 = this.f6990b.getInt("lane_lines_y", 0);
        int i9 = this.f6992d;
        if (i9 == -1 || !this.f6993e) {
            i9 = -1;
        }
        Context requireContext = requireContext();
        boolean z6 = FloatingWindowService.f4529p;
        Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
        intent.setAction("create_lane_lines");
        intent.putExtra("display_id", i9);
        intent.putExtra("x", i7);
        intent.putExtra("y", i8);
        FloatingWindowService.n(requireContext, intent);
    }

    public final void f() {
        if (!this.f6993e) {
            ToastHelper.show(getContext(), "仪表未初始化，将在主屏幕显示");
        }
        if (!Settings.canDrawOverlays(requireContext())) {
            this.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + requireContext().getPackageName())));
            ToastHelper.showLong(getContext(), "需要悬浮窗权限才能显示导航ETA");
            return;
        }
        int i7 = this.f6990b.getInt("navigation_eta_x", 0);
        int i8 = this.f6990b.getInt("navigation_eta_y", 0);
        boolean z6 = this.f6990b.getBoolean("navigation_eta_top_info_enabled", false);
        int i9 = this.f6992d;
        if (i9 == -1 || !this.f6993e) {
            i9 = -1;
        }
        Context requireContext = requireContext();
        boolean z7 = FloatingWindowService.f4529p;
        Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
        intent.setAction("create_navigation_eta");
        intent.putExtra("display_id", i9);
        intent.putExtra("x", i7);
        intent.putExtra("y", i8);
        intent.putExtra("show_top_info", z6);
        FloatingWindowService.n(requireContext, intent);
    }

    public final void g() {
        Context requireContext = requireContext();
        boolean z6 = FloatingWindowService.f4529p;
        a.a.k(requireContext, FloatingWindowService.class, "destroy_lane_lines", requireContext);
    }

    public final void h() {
        Context requireContext = requireContext();
        boolean z6 = FloatingWindowService.f4529p;
        a.a.k(requireContext, FloatingWindowService.class, "destroy_navigation_eta", requireContext);
    }

    public final void i() {
        int displayId;
        DisplayManager displayManager = this.f6991c;
        if (displayManager != null) {
            Display[] displays = displayManager.getDisplays();
            if (displays.length > 1) {
                for (Display display : displays) {
                    if (display.getDisplayId() != 0) {
                        displayId = display.getDisplayId();
                        break;
                    }
                }
            }
        }
        displayId = -1;
        this.f6992d = displayId;
        if (displayId == -1) {
            this.f6993e = false;
            ToastHelper.show(requireContext(), getString(u.no_external_display_found));
            this.f6989a.L.setChecked(false);
            a.a.m(this.f6990b, "dashboard_enabled", false);
        } else {
            this.f6993e = true;
            boolean z6 = this.f6990b.getBoolean("lane_lines_enabled", false);
            boolean z7 = this.f6990b.getBoolean("navigation_eta_enabled", false);
            boolean z8 = this.f6990b.getBoolean("road_speed_info_enabled", false);
            boolean z9 = this.f6990b.getBoolean("tmc_bar_enabled", false);
            if (z6) {
                e();
            }
            if (z7) {
                f();
            }
            if (z8) {
                Context requireContext = requireContext();
                boolean z10 = FloatingWindowService.f4529p;
                a.a.k(requireContext, FloatingWindowService.class, "create_road_speed", requireContext);
            }
            if (z9) {
                Context requireContext2 = requireContext();
                boolean z11 = FloatingWindowService.f4529p;
                a.a.k(requireContext2, FloatingWindowService.class, "create_tmc_bar", requireContext2);
            }
            if (this.f6990b.getBoolean("lyric_enabled", false)) {
                Context requireContext3 = requireContext();
                boolean z12 = FloatingWindowService.f4529p;
                a.a.k(requireContext3, FloatingWindowService.class, "create_lyric_view", requireContext3);
            }
            if (this.f6990b.getBoolean("dashboard_tire_card_enabled", false)) {
                Context requireContext4 = requireContext();
                boolean z13 = FloatingWindowService.f4529p;
                a.a.k(requireContext4, FloatingWindowService.class, "create_tire_card", requireContext4);
            }
        }
        o();
    }

    public final void j() {
        SwitchMaterial switchMaterial = this.f6989a.O;
        if (switchMaterial == null || !switchMaterial.isChecked()) {
            return;
        }
        Context requireContext = requireContext();
        boolean z6 = this.f6990b.getBoolean("lane_lines_transparent_bg", false);
        int i7 = this.f6990b.getInt("lane_lines_arrow_color_day", -1);
        int i8 = this.f6990b.getInt("lane_lines_arrow_color_night", -1);
        boolean z7 = FloatingWindowService.f4529p;
        Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
        intent.setAction("update_lane_lines_style");
        intent.putExtra("bg_transparent", z6);
        intent.putExtra("arrow_color_day", i7);
        intent.putExtra("arrow_color_night", i8);
        FloatingWindowService.n(requireContext, intent);
    }

    public final void k(RadioGroup radioGroup, final String str) {
        View childAt;
        if (radioGroup == null) {
            return;
        }
        int i7 = this.f6990b.getInt(str, -1);
        int i8 = 0;
        int i9 = 0;
        while (true) {
            int[] iArr = f6988g;
            if (i9 >= iArr.length) {
                break;
            }
            if (iArr[i9] == i7) {
                i8 = i9;
                break;
            }
            i9++;
        }
        if (i8 < radioGroup.getChildCount() && (childAt = radioGroup.getChildAt(i8)) != null) {
            radioGroup.check(childAt.getId());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: j4.e
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i10) {
                int i11;
                int[] iArr2 = g.f6988g;
                g gVar = g.this;
                gVar.getClass();
                int i12 = 0;
                while (true) {
                    if (i12 >= radioGroup2.getChildCount()) {
                        i11 = -1;
                        break;
                    }
                    if (radioGroup2.getChildAt(i12).getId() == i10) {
                        int[] iArr3 = g.f6988g;
                        if (i12 < iArr3.length) {
                            i11 = iArr3[i12];
                            break;
                        }
                    }
                    i12++;
                }
                gVar.f6990b.edit().putInt(str, i11).apply();
                gVar.j();
            }
        });
    }

    public final void l(boolean z6) {
        LinearLayout linearLayout;
        FragmentDashboardExtensionBinding fragmentDashboardExtensionBinding = this.f6989a;
        if (fragmentDashboardExtensionBinding == null || (linearLayout = fragmentDashboardExtensionBinding.f4787g) == null) {
            return;
        }
        linearLayout.setVisibility(z6 ? 0 : 8);
    }

    public final void m() {
        if (this.f6989a == null) {
            return;
        }
        if (Settings.canDrawOverlays(requireContext())) {
            this.f6989a.L.setEnabled(true);
            View findViewById = this.f6989a.f4775a.findViewById(p.layout_dashboard_permission);
            if (findViewById != null) {
                findViewById.setVisibility(8);
                return;
            }
            return;
        }
        this.f6989a.L.setEnabled(false);
        this.f6989a.L.setChecked(false);
        SharedPreferences sharedPreferences = this.f6990b;
        if (sharedPreferences != null) {
            a.a.m(sharedPreferences, "dashboard_enabled", false);
        }
        View findViewById2 = this.f6989a.f4775a.findViewById(p.layout_dashboard_permission);
        if (findViewById2 != null) {
            findViewById2.setVisibility(0);
        }
    }

    public final void n() {
        this.f6989a.f4776a0.setText(String.format("位置: X=%d, Y=%d", Integer.valueOf(this.f6990b.getInt("lane_lines_x", 0)), Integer.valueOf(this.f6990b.getInt("lane_lines_y", 0))));
        int i7 = this.f6990b.getInt("lane_lines_height", 80);
        if (i7 > 120) {
            float f7 = i7;
            if (requireContext().getResources().getDisplayMetrics().density != CropImageView.DEFAULT_ASPECT_RATIO) {
                f7 /= requireContext().getResources().getDisplayMetrics().density;
            }
            i7 = Math.round(f7);
        }
        this.f6989a.Z.setText(String.format("高度: %ddp", Integer.valueOf(d(i7, 50, z1.c.INVOKE_INTERFACE_RANGE))));
        this.f6989a.f4788g0.setText(String.format("位置: X=%d, Y=%d", Integer.valueOf(this.f6990b.getInt("navigation_eta_x", 0)), Integer.valueOf(this.f6990b.getInt("navigation_eta_y", 0))));
        if (this.f6989a.f4780c0 != null) {
            this.f6989a.f4780c0.setText(String.format("位置: X=%d, Y=%d", Integer.valueOf(this.f6990b.getInt("lyric_position_x", 0)), Integer.valueOf(this.f6990b.getInt("lyric_position_y", 0))));
        }
        if (this.f6989a.Y != null) {
            this.f6989a.Y.setText(String.format("位置: X=%d, Y=%d", Integer.valueOf(this.f6990b.getInt("dashboard_tire_card_x", 0)), Integer.valueOf(this.f6990b.getInt("dashboard_tire_card_y", 0))));
        }
    }

    public final void o() {
        SwitchMaterial switchMaterial;
        SwitchMaterial switchMaterial2;
        boolean isChecked = this.f6989a.L.isChecked();
        this.f6989a.O.setEnabled(isChecked);
        this.f6989a.S.setEnabled(isChecked);
        SwitchMaterial switchMaterial3 = this.f6989a.W;
        if (switchMaterial3 != null) {
            switchMaterial3.setEnabled(isChecked);
        }
        SwitchMaterial switchMaterial4 = this.f6989a.X;
        if (switchMaterial4 != null) {
            switchMaterial4.setEnabled(isChecked);
        }
        SwitchMaterial switchMaterial5 = this.f6989a.Q;
        if (switchMaterial5 != null) {
            switchMaterial5.setEnabled(isChecked);
        }
        SwitchMaterial switchMaterial6 = this.f6989a.M;
        if (switchMaterial6 != null) {
            switchMaterial6.setEnabled(isChecked);
        }
        boolean z6 = isChecked && this.f6989a.S.isChecked();
        this.f6989a.V.setEnabled(z6);
        int i7 = 8;
        this.f6989a.f4793k.setVisibility(z6 ? 0 : 8);
        this.f6989a.f4789h.setVisibility(z6 ? 0 : 8);
        SwitchMaterial switchMaterial7 = this.f6989a.U;
        if (switchMaterial7 != null) {
            switchMaterial7.setEnabled(z6);
        }
        this.f6989a.f4791i.setVisibility(z6 ? 0 : 8);
        this.f6989a.T.setEnabled(z6);
        View findViewById = this.f6989a.f4775a.findViewById(p.div_navigation_eta_desc_to_turn);
        if (findViewById != null) {
            findViewById.setVisibility(z6 ? 0 : 8);
        }
        View findViewById2 = this.f6989a.f4775a.findViewById(p.div_navigation_eta_turn_to_service);
        if (findViewById2 != null) {
            findViewById2.setVisibility(z6 ? 0 : 8);
        }
        this.f6989a.f4775a.findViewById(p.card_lane_lines).setVisibility(isChecked ? 0 : 8);
        boolean z7 = isChecked && this.f6989a.O.isChecked();
        TextView textView = this.f6989a.f4776a0;
        if (textView != null) {
            textView.setVisibility(z7 ? 0 : 8);
        }
        Button button = this.f6989a.f4779c;
        if (button != null) {
            button.setVisibility(z7 ? 0 : 8);
            this.f6989a.f4779c.setEnabled(z7);
        }
        TextView textView2 = this.f6989a.Z;
        if (textView2 != null) {
            textView2.setVisibility(z7 ? 0 : 8);
        }
        SeekBar seekBar = this.f6989a.E;
        if (seekBar != null) {
            seekBar.setVisibility(z7 ? 0 : 8);
            this.f6989a.E.setEnabled(z7);
        }
        View findViewById3 = this.f6989a.f4775a.findViewById(p.div_lane_lines_desc_to_position);
        if (findViewById3 != null) {
            findViewById3.setVisibility(z7 ? 0 : 8);
        }
        View findViewById4 = this.f6989a.f4775a.findViewById(p.div_lane_lines_position_to_height);
        if (findViewById4 != null) {
            findViewById4.setVisibility(z7 ? 0 : 8);
        }
        View findViewById5 = this.f6989a.f4775a.findViewById(p.div_lane_lines_height_to_style);
        if (findViewById5 != null) {
            findViewById5.setVisibility(z7 ? 0 : 8);
        }
        View findViewById6 = this.f6989a.f4775a.findViewById(p.layout_lane_lines_style);
        if (findViewById6 != null) {
            findViewById6.setVisibility(z7 ? 0 : 8);
        }
        View findViewById7 = this.f6989a.f4775a.findViewById(p.div_lane_lines_style_to_color);
        if (findViewById7 != null) {
            findViewById7.setVisibility(z7 ? 0 : 8);
        }
        this.f6989a.f4775a.findViewById(p.card_navigation_eta).setVisibility(isChecked ? 0 : 8);
        View findViewById8 = this.f6989a.f4775a.findViewById(p.card_road_speed_info);
        if (findViewById8 != null) {
            findViewById8.setVisibility(isChecked ? 0 : 8);
        }
        View findViewById9 = this.f6989a.f4775a.findViewById(p.card_tmc_bar);
        if (findViewById9 != null) {
            findViewById9.setVisibility(isChecked ? 0 : 8);
        }
        View findViewById10 = this.f6989a.f4775a.findViewById(p.card_lyric);
        if (findViewById10 != null) {
            findViewById10.setVisibility(isChecked ? 0 : 8);
        }
        View findViewById11 = this.f6989a.f4775a.findViewById(p.card_dashboard_tire);
        if (findViewById11 != null) {
            findViewById11.setVisibility(isChecked ? 0 : 8);
        }
        l(isChecked && (switchMaterial2 = this.f6989a.M) != null && switchMaterial2.isChecked());
        View findViewById12 = this.f6989a.f4775a.findViewById(p.layout_lyric_settings);
        if (findViewById12 != null) {
            if (isChecked && (switchMaterial = this.f6989a.Q) != null && switchMaterial.isChecked()) {
                i7 = 0;
            }
            findViewById12.setVisibility(i7);
        }
        View findViewById13 = this.f6989a.f4775a.findViewById(p.card_cluster_enlarge);
        if (findViewById13 != null) {
            findViewById13.setVisibility(0);
        }
        if (!this.f6989a.L.isChecked()) {
            this.f6989a.f4790h0.setText(u.dashboard_not_enabled);
        } else if (this.f6993e) {
            this.f6989a.f4790h0.setText(u.dashboard_initialized);
        } else {
            this.f6989a.f4790h0.setText(u.external_display_not_found);
        }
    }

    @Override // androidx.fragment.app.z
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentDashboardExtensionBinding inflate = FragmentDashboardExtensionBinding.inflate(layoutInflater, viewGroup, false);
        this.f6989a = inflate;
        return inflate.f4775a;
    }

    @Override // androidx.fragment.app.z
    public final void onDestroyView() {
        super.onDestroyView();
        requireContext();
        FloatingWindowService.f4531r = true;
        this.f6989a = null;
    }

    @Override // androidx.fragment.app.z
    public final void onResume() {
        super.onResume();
        m();
    }

    @Override // androidx.fragment.app.z
    public final void onViewCreated(View view, Bundle bundle) {
        View childAt;
        super.onViewCreated(view, bundle);
        final int i7 = 2;
        final int i8 = 8;
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 2) == 0) {
            view.setVisibility(8);
            return;
        }
        final int i9 = 0;
        FloatingWindowService.f4531r = false;
        this.f6990b = SharedPreferencesUtils.getAppPrefs(requireContext());
        this.f6994f = registerForActivityResult(new e.f(), new a(this, i9));
        this.f6991c = (DisplayManager) requireContext().getSystemService("display");
        this.f6989a.L.setChecked(this.f6990b.getBoolean("dashboard_enabled", false));
        this.f6989a.O.setChecked(this.f6990b.getBoolean("lane_lines_enabled", false));
        this.f6989a.S.setChecked(this.f6990b.getBoolean("navigation_eta_enabled", false));
        SwitchMaterial switchMaterial = this.f6989a.W;
        if (switchMaterial != null) {
            switchMaterial.setChecked(this.f6990b.getBoolean("road_speed_info_enabled", false));
        }
        final int i10 = 1;
        if (this.f6989a.X != null) {
            boolean z6 = this.f6990b.getBoolean("tmc_bar_enabled", false);
            this.f6989a.X.setChecked(z6);
            LinearLayout linearLayout = this.f6989a.f4792j;
            if (linearLayout != null) {
                linearLayout.setVisibility(z6 ? 0 : 8);
            }
            View view2 = this.f6989a.f4785f;
            if (view2 != null) {
                view2.setVisibility(z6 ? 0 : 8);
            }
            if (this.f6989a.D != null) {
                int i11 = this.f6990b.getInt("tmc_bar_position", 0);
                if (i11 == 1) {
                    this.f6989a.f4806x.setChecked(true);
                } else if (i11 == 2) {
                    this.f6989a.f4804v.setChecked(true);
                } else {
                    this.f6989a.f4805w.setChecked(true);
                }
            }
        }
        SwitchMaterial switchMaterial2 = this.f6989a.Q;
        if (switchMaterial2 != null) {
            switchMaterial2.setChecked(this.f6990b.getBoolean("lyric_enabled", false));
        }
        SwitchMaterial switchMaterial3 = this.f6989a.M;
        if (switchMaterial3 != null) {
            switchMaterial3.setChecked(this.f6990b.getBoolean("dashboard_tire_card_enabled", false));
            l(this.f6989a.M.isChecked());
        }
        int d7 = d(this.f6990b.getInt("lyric_font_size", 25), 20, 40);
        SeekBar seekBar = this.f6989a.F;
        if (seekBar != null) {
            seekBar.setMax(20);
            this.f6989a.F.setProgress(d7 - 20);
        }
        TextView textView = this.f6989a.f4778b0;
        if (textView != null) {
            textView.setText(String.format("歌词大小: %dsp", Integer.valueOf(d7)));
        }
        int d8 = d(this.f6990b.getInt("lyric_width_dp", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION), x2.d.DEFAULT_FADE_ANIM_DURATION, 1000);
        SeekBar seekBar2 = this.f6989a.G;
        if (seekBar2 != null) {
            seekBar2.setMax(700);
            this.f6989a.G.setProgress(d8 - 300);
            this.f6989a.G.setOnSeekBarChangeListener(new f(this, i9));
        }
        TextView textView2 = this.f6989a.f4782d0;
        if (textView2 != null) {
            textView2.setText(String.format("歌词宽度: %ddp", Integer.valueOf(d8)));
        }
        final int i12 = 6;
        final int i13 = 4;
        final int i14 = 5;
        final int i15 = 3;
        if (this.f6989a.C != null) {
            int i16 = this.f6990b.getInt(FloatingWindowService.KEY_MUSIC_SOURCE, 0);
            if (i16 == 1) {
                this.f6989a.f4798p.setChecked(true);
            } else if (i16 == 2) {
                this.f6989a.f4797o.setChecked(true);
            } else if (i16 == 3) {
                this.f6989a.f4800r.setChecked(true);
            } else if (i16 == 4) {
                this.f6989a.f4801s.setChecked(true);
            } else if (i16 == 5) {
                this.f6989a.f4799q.setChecked(true);
            } else if (i16 == 6) {
                this.f6989a.f4803u.setChecked(true);
            } else {
                this.f6989a.f4802t.setChecked(true);
            }
        }
        if (this.f6989a.A != null) {
            int i17 = this.f6990b.getInt("lyric_color", -1);
            int i18 = 0;
            while (true) {
                int[] iArr = f6988g;
                if (i18 >= iArr.length) {
                    i18 = 0;
                    break;
                } else if (iArr[i18] == i17) {
                    break;
                } else {
                    i18++;
                }
            }
            if (i18 < this.f6989a.A.getChildCount() && (childAt = this.f6989a.A.getChildAt(i18)) != null) {
                this.f6989a.A.check(childAt.getId());
            }
            this.f6989a.A.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: j4.c

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6981b;

                {
                    this.f6981b = this;
                }

                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup, int i19) {
                    int i20;
                    int i21 = i10;
                    int i22 = 2;
                    int i23 = 0;
                    g gVar = this.f6981b;
                    switch (i21) {
                        case 0:
                            int[] iArr2 = g.f6988g;
                            gVar.getClass();
                            if (i19 == p.rb_music_source_kuwo) {
                                i22 = 1;
                            } else if (i19 != p.rb_music_source_kugou) {
                                i22 = i19 == p.rb_music_source_netease ? 3 : i19 == p.rb_music_source_new_kuwo ? 4 : i19 == p.rb_music_source_luna ? 5 : i19 == p.rb_music_source_universal ? 6 : 0;
                            }
                            gVar.f6990b.edit().putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i22).putInt("desktop_music_source", i22).apply();
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext = gVar.requireContext();
                                boolean z7 = FloatingWindowService.f4529p;
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("update_lyric_config");
                                intent.putExtra(FloatingWindowService.KEY_MUSIC_SOURCE, i22);
                                FloatingWindowService.n(requireContext, intent);
                                break;
                            }
                            break;
                        case 1:
                            int[] iArr3 = g.f6988g;
                            gVar.getClass();
                            while (true) {
                                if (i23 < radioGroup.getChildCount()) {
                                    if (radioGroup.getChildAt(i23).getId() == i19) {
                                        int[] iArr4 = g.f6988g;
                                        i20 = i23 < iArr4.length ? iArr4[i23] : -1;
                                    } else {
                                        i23++;
                                    }
                                }
                            }
                            a.a.l(gVar.f6990b, "lyric_color", i20);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext2 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("update_lyric_config");
                                intent2.putExtra("text_color", i20);
                                FloatingWindowService.n(requireContext2, intent2);
                                break;
                            }
                            break;
                        case 2:
                            int[] iArr5 = g.f6988g;
                            gVar.getClass();
                            int i24 = i19 == p.rb_lyric_gravity_left ? 19 : i19 == p.rb_lyric_gravity_right ? 21 : 17;
                            a.a.l(gVar.f6990b, "lyric_gravity", i24);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext3 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("update_lyric_config");
                                intent3.putExtra("gravity", i24);
                                FloatingWindowService.n(requireContext3, intent3);
                                break;
                            }
                            break;
                        default:
                            if (i19 == gVar.f6989a.f4806x.getId()) {
                                i22 = 1;
                            } else if (i19 != gVar.f6989a.f4804v.getId()) {
                                i22 = 0;
                            }
                            a.a.l(gVar.f6990b, "tmc_bar_position", i22);
                            if (gVar.f6989a.X.isChecked()) {
                                Context requireContext4 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext4, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_tmc_bar_position");
                                intent4.putExtra("position_mode", i22);
                                FloatingWindowService.n(requireContext4, intent4);
                                break;
                            }
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial4 = this.f6989a.R;
        if (switchMaterial4 != null) {
            switchMaterial4.setChecked(this.f6990b.getBoolean("lyric_double_line", false));
        }
        SwitchMaterial switchMaterial5 = this.f6989a.P;
        final int i19 = 15;
        if (switchMaterial5 != null) {
            switchMaterial5.setChecked(this.f6990b.getBoolean("lane_lines_transparent_bg", false));
            this.f6989a.P.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i20 = i19;
                    g gVar = this.f6979b;
                    switch (i20) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial6 = gVar.f6989a.U;
                                if (switchMaterial6 != null) {
                                    switchMaterial6.setChecked(false);
                                }
                                SwitchMaterial switchMaterial7 = gVar.f6989a.T;
                                if (switchMaterial7 != null) {
                                    switchMaterial7.setChecked(false);
                                }
                                SwitchMaterial switchMaterial8 = gVar.f6989a.W;
                                if (switchMaterial8 != null) {
                                    switchMaterial8.setChecked(false);
                                }
                                SwitchMaterial switchMaterial9 = gVar.f6989a.X;
                                if (switchMaterial9 != null) {
                                    switchMaterial9.setChecked(false);
                                }
                                SwitchMaterial switchMaterial10 = gVar.f6989a.Q;
                                if (switchMaterial10 != null) {
                                    switchMaterial10.setChecked(false);
                                }
                                SwitchMaterial switchMaterial11 = gVar.f6989a.R;
                                if (switchMaterial11 != null) {
                                    switchMaterial11.setChecked(false);
                                }
                                SwitchMaterial switchMaterial12 = gVar.f6989a.M;
                                if (switchMaterial12 != null) {
                                    switchMaterial12.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById2 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById != null) {
                                findViewById.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById2 != null) {
                                findViewById2.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial13 = gVar.f6989a.U;
                                if (switchMaterial13 != null) {
                                    switchMaterial13.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                                if (switchMaterial14 != null) {
                                    switchMaterial14.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        k(this.f6989a.f4807y, "lane_lines_arrow_color_day");
        k(this.f6989a.f4808z, "lane_lines_arrow_color_night");
        if (this.f6989a.B != null) {
            int i20 = this.f6990b.getInt("lyric_gravity", 17);
            if (i20 == 3 || i20 == 19) {
                this.f6989a.f4795m.setChecked(true);
            } else if (i20 == 5 || i20 == 21) {
                this.f6989a.f4796n.setChecked(true);
            } else {
                this.f6989a.f4794l.setChecked(true);
            }
            this.f6989a.B.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: j4.c

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6981b;

                {
                    this.f6981b = this;
                }

                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup, int i192) {
                    int i202;
                    int i21 = i7;
                    int i22 = 2;
                    int i23 = 0;
                    g gVar = this.f6981b;
                    switch (i21) {
                        case 0:
                            int[] iArr2 = g.f6988g;
                            gVar.getClass();
                            if (i192 == p.rb_music_source_kuwo) {
                                i22 = 1;
                            } else if (i192 != p.rb_music_source_kugou) {
                                i22 = i192 == p.rb_music_source_netease ? 3 : i192 == p.rb_music_source_new_kuwo ? 4 : i192 == p.rb_music_source_luna ? 5 : i192 == p.rb_music_source_universal ? 6 : 0;
                            }
                            gVar.f6990b.edit().putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i22).putInt("desktop_music_source", i22).apply();
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext = gVar.requireContext();
                                boolean z7 = FloatingWindowService.f4529p;
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("update_lyric_config");
                                intent.putExtra(FloatingWindowService.KEY_MUSIC_SOURCE, i22);
                                FloatingWindowService.n(requireContext, intent);
                                break;
                            }
                            break;
                        case 1:
                            int[] iArr3 = g.f6988g;
                            gVar.getClass();
                            while (true) {
                                if (i23 < radioGroup.getChildCount()) {
                                    if (radioGroup.getChildAt(i23).getId() == i192) {
                                        int[] iArr4 = g.f6988g;
                                        i202 = i23 < iArr4.length ? iArr4[i23] : -1;
                                    } else {
                                        i23++;
                                    }
                                }
                            }
                            a.a.l(gVar.f6990b, "lyric_color", i202);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext2 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("update_lyric_config");
                                intent2.putExtra("text_color", i202);
                                FloatingWindowService.n(requireContext2, intent2);
                                break;
                            }
                            break;
                        case 2:
                            int[] iArr5 = g.f6988g;
                            gVar.getClass();
                            int i24 = i192 == p.rb_lyric_gravity_left ? 19 : i192 == p.rb_lyric_gravity_right ? 21 : 17;
                            a.a.l(gVar.f6990b, "lyric_gravity", i24);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext3 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("update_lyric_config");
                                intent3.putExtra("gravity", i24);
                                FloatingWindowService.n(requireContext3, intent3);
                                break;
                            }
                            break;
                        default:
                            if (i192 == gVar.f6989a.f4806x.getId()) {
                                i22 = 1;
                            } else if (i192 != gVar.f6989a.f4804v.getId()) {
                                i22 = 0;
                            }
                            a.a.l(gVar.f6990b, "tmc_bar_position", i22);
                            if (gVar.f6989a.X.isChecked()) {
                                Context requireContext4 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext4, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_tmc_bar_position");
                                intent4.putExtra("position_mode", i22);
                                FloatingWindowService.n(requireContext4, intent4);
                                break;
                            }
                            break;
                    }
                }
            });
        }
        this.f6989a.V.setChecked(this.f6990b.getBoolean("navigation_eta_top_info_enabled", false));
        SwitchMaterial switchMaterial6 = this.f6989a.U;
        if (switchMaterial6 != null) {
            switchMaterial6.setChecked(this.f6990b.getBoolean("navigation_eta_service_info_enabled", false));
        }
        int d9 = d(this.f6990b.getInt("navigation_eta_font_size_sp", 35), 25, 40);
        SeekBar seekBar3 = this.f6989a.I;
        if (seekBar3 != null) {
            seekBar3.setMax(15);
            this.f6989a.I.setProgress(d9 - 25);
        }
        TextView textView3 = this.f6989a.f4786f0;
        if (textView3 != null) {
            textView3.setText(String.format("字体大小: %dsp", Integer.valueOf(d9)));
        }
        int d10 = d(this.f6990b.getInt("navigation_eta_bg_alpha_percent", 100), 0, 100);
        SeekBar seekBar4 = this.f6989a.H;
        if (seekBar4 != null) {
            seekBar4.setMax(100);
            this.f6989a.H.setProgress(d10);
        }
        TextView textView4 = this.f6989a.f4784e0;
        if (textView4 != null) {
            textView4.setText(String.format("背景透明度: %d%%", Integer.valueOf(d10)));
        }
        SwitchMaterial switchMaterial7 = this.f6989a.T;
        if (switchMaterial7 != null) {
            switchMaterial7.setChecked(this.f6990b.getBoolean("navigation_eta_bold_enabled", false));
        }
        SwitchMaterial switchMaterial8 = this.f6989a.K;
        if (switchMaterial8 != null) {
            switchMaterial8.setChecked(SharedPreferencesUtils.isClusterEnlargeOnNavigation(requireContext()));
        }
        SwitchMaterial switchMaterial9 = this.f6989a.J;
        if (switchMaterial9 != null) {
            switchMaterial9.setChecked(SharedPreferencesUtils.isClusterEnlargeOnCruise(requireContext()));
        }
        View findViewById = this.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
        View findViewById2 = this.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
        boolean isClusterEnlargeOnCruise = SharedPreferencesUtils.isClusterEnlargeOnCruise(requireContext());
        if (findViewById != null) {
            findViewById.setVisibility(isClusterEnlargeOnCruise ? 0 : 8);
        }
        if (findViewById2 != null) {
            findViewById2.setVisibility(isClusterEnlargeOnCruise ? 0 : 8);
        }
        SwitchMaterial switchMaterial10 = (SwitchMaterial) this.f6989a.f4775a.findViewById(p.switch_cluster_enlarge_cruise_auto_bg);
        if (switchMaterial10 != null) {
            switchMaterial10.setChecked(SharedPreferencesUtils.isClusterEnlargeCruiseAutoBgEnabled(requireContext()));
        }
        SwitchMaterial switchMaterial11 = this.f6989a.N;
        if (switchMaterial11 != null) {
            switchMaterial11.setChecked(this.f6990b.getBoolean("extscreen_activation_enabled", false));
        }
        if (this.f6990b.getBoolean("dashboard_enabled", false)) {
            i();
        }
        m();
        this.f6989a.L.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6979b;

            {
                this.f6979b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                int i202 = i9;
                g gVar = this.f6979b;
                switch (i202) {
                    case 0:
                        a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                        if (z7) {
                            gVar.i();
                        } else {
                            gVar.f6989a.O.setChecked(false);
                            gVar.f6989a.S.setChecked(false);
                            gVar.f6989a.V.setChecked(false);
                            SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                            if (switchMaterial62 != null) {
                                switchMaterial62.setChecked(false);
                            }
                            SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                            if (switchMaterial72 != null) {
                                switchMaterial72.setChecked(false);
                            }
                            SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                            if (switchMaterial82 != null) {
                                switchMaterial82.setChecked(false);
                            }
                            SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                            if (switchMaterial92 != null) {
                                switchMaterial92.setChecked(false);
                            }
                            SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                            if (switchMaterial102 != null) {
                                switchMaterial102.setChecked(false);
                            }
                            SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                            if (switchMaterial112 != null) {
                                switchMaterial112.setChecked(false);
                            }
                            SwitchMaterial switchMaterial12 = gVar.f6989a.M;
                            if (switchMaterial12 != null) {
                                switchMaterial12.setChecked(false);
                            }
                            gVar.g();
                            gVar.h();
                            Context requireContext = gVar.requireContext();
                            Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                            intent.setAction("destroy_road_speed");
                            FloatingWindowService.n(requireContext, intent);
                            Context requireContext2 = gVar.requireContext();
                            Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                            intent2.setAction("destroy_tmc_bar");
                            FloatingWindowService.n(requireContext2, intent2);
                            Context requireContext3 = gVar.requireContext();
                            Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                            intent3.setAction("destroy_lyric_view");
                            FloatingWindowService.n(requireContext3, intent3);
                            Context requireContext4 = gVar.requireContext();
                            a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                            gVar.f6993e = false;
                            gVar.f6992d = -1;
                            gVar.o();
                        }
                        gVar.o();
                        break;
                    case 1:
                        a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext5 = gVar.requireContext();
                            boolean z8 = FloatingWindowService.f4529p;
                            Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                            intent4.setAction("update_navigation_eta_service_info_enabled");
                            intent4.putExtra("service_info_enabled", z7);
                            FloatingWindowService.n(requireContext5, intent4);
                            break;
                        }
                        break;
                    case 2:
                        a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext6 = gVar.requireContext();
                            boolean z9 = FloatingWindowService.f4529p;
                            Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                            intent5.setAction("update_navigation_eta_bold_enabled");
                            intent5.putExtra("bold_enabled", z7);
                            FloatingWindowService.n(requireContext6, intent5);
                            break;
                        }
                        break;
                    case 3:
                        int[] iArr2 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                        break;
                    case 4:
                        int[] iArr3 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                        View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                        View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                        if (findViewById3 != null) {
                            findViewById3.setVisibility(z7 ? 0 : 8);
                        }
                        if (findViewById22 != null) {
                            findViewById22.setVisibility(z7 ? 0 : 8);
                            break;
                        }
                        break;
                    case 5:
                        int[] iArr4 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                        break;
                    case 6:
                        a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                        break;
                    case 7:
                        a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                        if (z7 && gVar.f6993e) {
                            gVar.e();
                        } else {
                            gVar.g();
                        }
                        gVar.o();
                        break;
                    case 8:
                        a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                        if (z7) {
                            gVar.f6989a.f4793k.setVisibility(0);
                            gVar.f6989a.V.setEnabled(gVar.f6993e);
                            gVar.f6989a.f4789h.setVisibility(0);
                            SwitchMaterial switchMaterial13 = gVar.f6989a.U;
                            if (switchMaterial13 != null) {
                                switchMaterial13.setEnabled(gVar.f6993e);
                            }
                            gVar.f6989a.f4791i.setVisibility(0);
                            gVar.f6989a.T.setEnabled(gVar.f6993e);
                        } else {
                            gVar.f6989a.f4793k.setVisibility(8);
                            gVar.f6989a.V.setEnabled(false);
                            gVar.f6989a.f4789h.setVisibility(8);
                            SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                            if (switchMaterial14 != null) {
                                switchMaterial14.setEnabled(false);
                            }
                            gVar.f6989a.f4791i.setVisibility(8);
                            gVar.f6989a.T.setEnabled(false);
                        }
                        if (z7 && gVar.f6993e) {
                            gVar.f();
                        } else {
                            gVar.h();
                        }
                        gVar.o();
                        break;
                    case 9:
                        a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                        if (!z7) {
                            Context requireContext7 = gVar.requireContext();
                            boolean z10 = FloatingWindowService.f4529p;
                            a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                            break;
                        } else {
                            Context requireContext8 = gVar.requireContext();
                            boolean z11 = FloatingWindowService.f4529p;
                            a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                        }
                        gVar.o();
                        break;
                    case 10:
                        a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                        LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                        if (linearLayout2 != null) {
                            linearLayout2.setVisibility(z7 ? 0 : 8);
                        }
                        View view3 = gVar.f6989a.f4785f;
                        if (view3 != null) {
                            view3.setVisibility(z7 ? 0 : 8);
                        }
                        if (!z7) {
                            Context requireContext9 = gVar.requireContext();
                            boolean z12 = FloatingWindowService.f4529p;
                            a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                            break;
                        } else {
                            Context requireContext10 = gVar.requireContext();
                            boolean z13 = FloatingWindowService.f4529p;
                            a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                        }
                        gVar.o();
                        break;
                    case 11:
                        a.a.m(gVar.f6990b, "lyric_enabled", z7);
                        if (!z7) {
                            Context requireContext11 = gVar.requireContext();
                            boolean z14 = FloatingWindowService.f4529p;
                            a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                            break;
                        } else {
                            Context requireContext12 = gVar.requireContext();
                            boolean z15 = FloatingWindowService.f4529p;
                            a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                        }
                        gVar.o();
                        break;
                    case 12:
                        gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                        gVar.l(z7);
                        if (!z7) {
                            Context requireContext13 = gVar.requireContext();
                            boolean z16 = FloatingWindowService.f4529p;
                            a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                            break;
                        } else {
                            Context requireContext14 = gVar.requireContext();
                            boolean z17 = FloatingWindowService.f4529p;
                            a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                        }
                        gVar.o();
                        break;
                    case 13:
                        a.a.m(gVar.f6990b, "lyric_double_line", z7);
                        if (gVar.f6989a.Q.isChecked()) {
                            Context requireContext15 = gVar.requireContext();
                            boolean z18 = FloatingWindowService.f4529p;
                            Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                            intent6.setAction("update_lyric_config");
                            intent6.putExtra("double_line_enabled", z7);
                            FloatingWindowService.n(requireContext15, intent6);
                            break;
                        }
                        break;
                    case 14:
                        a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            gVar.h();
                            gVar.f();
                            break;
                        }
                        break;
                    default:
                        gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                        gVar.j();
                        break;
                }
            }
        });
        final int i21 = 7;
        this.f6989a.O.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6979b;

            {
                this.f6979b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                int i202 = i21;
                g gVar = this.f6979b;
                switch (i202) {
                    case 0:
                        a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                        if (z7) {
                            gVar.i();
                        } else {
                            gVar.f6989a.O.setChecked(false);
                            gVar.f6989a.S.setChecked(false);
                            gVar.f6989a.V.setChecked(false);
                            SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                            if (switchMaterial62 != null) {
                                switchMaterial62.setChecked(false);
                            }
                            SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                            if (switchMaterial72 != null) {
                                switchMaterial72.setChecked(false);
                            }
                            SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                            if (switchMaterial82 != null) {
                                switchMaterial82.setChecked(false);
                            }
                            SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                            if (switchMaterial92 != null) {
                                switchMaterial92.setChecked(false);
                            }
                            SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                            if (switchMaterial102 != null) {
                                switchMaterial102.setChecked(false);
                            }
                            SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                            if (switchMaterial112 != null) {
                                switchMaterial112.setChecked(false);
                            }
                            SwitchMaterial switchMaterial12 = gVar.f6989a.M;
                            if (switchMaterial12 != null) {
                                switchMaterial12.setChecked(false);
                            }
                            gVar.g();
                            gVar.h();
                            Context requireContext = gVar.requireContext();
                            Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                            intent.setAction("destroy_road_speed");
                            FloatingWindowService.n(requireContext, intent);
                            Context requireContext2 = gVar.requireContext();
                            Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                            intent2.setAction("destroy_tmc_bar");
                            FloatingWindowService.n(requireContext2, intent2);
                            Context requireContext3 = gVar.requireContext();
                            Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                            intent3.setAction("destroy_lyric_view");
                            FloatingWindowService.n(requireContext3, intent3);
                            Context requireContext4 = gVar.requireContext();
                            a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                            gVar.f6993e = false;
                            gVar.f6992d = -1;
                            gVar.o();
                        }
                        gVar.o();
                        break;
                    case 1:
                        a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext5 = gVar.requireContext();
                            boolean z8 = FloatingWindowService.f4529p;
                            Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                            intent4.setAction("update_navigation_eta_service_info_enabled");
                            intent4.putExtra("service_info_enabled", z7);
                            FloatingWindowService.n(requireContext5, intent4);
                            break;
                        }
                        break;
                    case 2:
                        a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext6 = gVar.requireContext();
                            boolean z9 = FloatingWindowService.f4529p;
                            Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                            intent5.setAction("update_navigation_eta_bold_enabled");
                            intent5.putExtra("bold_enabled", z7);
                            FloatingWindowService.n(requireContext6, intent5);
                            break;
                        }
                        break;
                    case 3:
                        int[] iArr2 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                        break;
                    case 4:
                        int[] iArr3 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                        View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                        View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                        if (findViewById3 != null) {
                            findViewById3.setVisibility(z7 ? 0 : 8);
                        }
                        if (findViewById22 != null) {
                            findViewById22.setVisibility(z7 ? 0 : 8);
                            break;
                        }
                        break;
                    case 5:
                        int[] iArr4 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                        break;
                    case 6:
                        a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                        break;
                    case 7:
                        a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                        if (z7 && gVar.f6993e) {
                            gVar.e();
                        } else {
                            gVar.g();
                        }
                        gVar.o();
                        break;
                    case 8:
                        a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                        if (z7) {
                            gVar.f6989a.f4793k.setVisibility(0);
                            gVar.f6989a.V.setEnabled(gVar.f6993e);
                            gVar.f6989a.f4789h.setVisibility(0);
                            SwitchMaterial switchMaterial13 = gVar.f6989a.U;
                            if (switchMaterial13 != null) {
                                switchMaterial13.setEnabled(gVar.f6993e);
                            }
                            gVar.f6989a.f4791i.setVisibility(0);
                            gVar.f6989a.T.setEnabled(gVar.f6993e);
                        } else {
                            gVar.f6989a.f4793k.setVisibility(8);
                            gVar.f6989a.V.setEnabled(false);
                            gVar.f6989a.f4789h.setVisibility(8);
                            SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                            if (switchMaterial14 != null) {
                                switchMaterial14.setEnabled(false);
                            }
                            gVar.f6989a.f4791i.setVisibility(8);
                            gVar.f6989a.T.setEnabled(false);
                        }
                        if (z7 && gVar.f6993e) {
                            gVar.f();
                        } else {
                            gVar.h();
                        }
                        gVar.o();
                        break;
                    case 9:
                        a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                        if (!z7) {
                            Context requireContext7 = gVar.requireContext();
                            boolean z10 = FloatingWindowService.f4529p;
                            a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                            break;
                        } else {
                            Context requireContext8 = gVar.requireContext();
                            boolean z11 = FloatingWindowService.f4529p;
                            a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                        }
                        gVar.o();
                        break;
                    case 10:
                        a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                        LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                        if (linearLayout2 != null) {
                            linearLayout2.setVisibility(z7 ? 0 : 8);
                        }
                        View view3 = gVar.f6989a.f4785f;
                        if (view3 != null) {
                            view3.setVisibility(z7 ? 0 : 8);
                        }
                        if (!z7) {
                            Context requireContext9 = gVar.requireContext();
                            boolean z12 = FloatingWindowService.f4529p;
                            a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                            break;
                        } else {
                            Context requireContext10 = gVar.requireContext();
                            boolean z13 = FloatingWindowService.f4529p;
                            a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                        }
                        gVar.o();
                        break;
                    case 11:
                        a.a.m(gVar.f6990b, "lyric_enabled", z7);
                        if (!z7) {
                            Context requireContext11 = gVar.requireContext();
                            boolean z14 = FloatingWindowService.f4529p;
                            a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                            break;
                        } else {
                            Context requireContext12 = gVar.requireContext();
                            boolean z15 = FloatingWindowService.f4529p;
                            a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                        }
                        gVar.o();
                        break;
                    case 12:
                        gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                        gVar.l(z7);
                        if (!z7) {
                            Context requireContext13 = gVar.requireContext();
                            boolean z16 = FloatingWindowService.f4529p;
                            a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                            break;
                        } else {
                            Context requireContext14 = gVar.requireContext();
                            boolean z17 = FloatingWindowService.f4529p;
                            a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                        }
                        gVar.o();
                        break;
                    case 13:
                        a.a.m(gVar.f6990b, "lyric_double_line", z7);
                        if (gVar.f6989a.Q.isChecked()) {
                            Context requireContext15 = gVar.requireContext();
                            boolean z18 = FloatingWindowService.f4529p;
                            Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                            intent6.setAction("update_lyric_config");
                            intent6.putExtra("double_line_enabled", z7);
                            FloatingWindowService.n(requireContext15, intent6);
                            break;
                        }
                        break;
                    case 14:
                        a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            gVar.h();
                            gVar.f();
                            break;
                        }
                        break;
                    default:
                        gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                        gVar.j();
                        break;
                }
            }
        });
        this.f6989a.S.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6979b;

            {
                this.f6979b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                int i202 = i8;
                g gVar = this.f6979b;
                switch (i202) {
                    case 0:
                        a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                        if (z7) {
                            gVar.i();
                        } else {
                            gVar.f6989a.O.setChecked(false);
                            gVar.f6989a.S.setChecked(false);
                            gVar.f6989a.V.setChecked(false);
                            SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                            if (switchMaterial62 != null) {
                                switchMaterial62.setChecked(false);
                            }
                            SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                            if (switchMaterial72 != null) {
                                switchMaterial72.setChecked(false);
                            }
                            SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                            if (switchMaterial82 != null) {
                                switchMaterial82.setChecked(false);
                            }
                            SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                            if (switchMaterial92 != null) {
                                switchMaterial92.setChecked(false);
                            }
                            SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                            if (switchMaterial102 != null) {
                                switchMaterial102.setChecked(false);
                            }
                            SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                            if (switchMaterial112 != null) {
                                switchMaterial112.setChecked(false);
                            }
                            SwitchMaterial switchMaterial12 = gVar.f6989a.M;
                            if (switchMaterial12 != null) {
                                switchMaterial12.setChecked(false);
                            }
                            gVar.g();
                            gVar.h();
                            Context requireContext = gVar.requireContext();
                            Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                            intent.setAction("destroy_road_speed");
                            FloatingWindowService.n(requireContext, intent);
                            Context requireContext2 = gVar.requireContext();
                            Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                            intent2.setAction("destroy_tmc_bar");
                            FloatingWindowService.n(requireContext2, intent2);
                            Context requireContext3 = gVar.requireContext();
                            Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                            intent3.setAction("destroy_lyric_view");
                            FloatingWindowService.n(requireContext3, intent3);
                            Context requireContext4 = gVar.requireContext();
                            a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                            gVar.f6993e = false;
                            gVar.f6992d = -1;
                            gVar.o();
                        }
                        gVar.o();
                        break;
                    case 1:
                        a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext5 = gVar.requireContext();
                            boolean z8 = FloatingWindowService.f4529p;
                            Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                            intent4.setAction("update_navigation_eta_service_info_enabled");
                            intent4.putExtra("service_info_enabled", z7);
                            FloatingWindowService.n(requireContext5, intent4);
                            break;
                        }
                        break;
                    case 2:
                        a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext6 = gVar.requireContext();
                            boolean z9 = FloatingWindowService.f4529p;
                            Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                            intent5.setAction("update_navigation_eta_bold_enabled");
                            intent5.putExtra("bold_enabled", z7);
                            FloatingWindowService.n(requireContext6, intent5);
                            break;
                        }
                        break;
                    case 3:
                        int[] iArr2 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                        break;
                    case 4:
                        int[] iArr3 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                        View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                        View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                        if (findViewById3 != null) {
                            findViewById3.setVisibility(z7 ? 0 : 8);
                        }
                        if (findViewById22 != null) {
                            findViewById22.setVisibility(z7 ? 0 : 8);
                            break;
                        }
                        break;
                    case 5:
                        int[] iArr4 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                        break;
                    case 6:
                        a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                        break;
                    case 7:
                        a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                        if (z7 && gVar.f6993e) {
                            gVar.e();
                        } else {
                            gVar.g();
                        }
                        gVar.o();
                        break;
                    case 8:
                        a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                        if (z7) {
                            gVar.f6989a.f4793k.setVisibility(0);
                            gVar.f6989a.V.setEnabled(gVar.f6993e);
                            gVar.f6989a.f4789h.setVisibility(0);
                            SwitchMaterial switchMaterial13 = gVar.f6989a.U;
                            if (switchMaterial13 != null) {
                                switchMaterial13.setEnabled(gVar.f6993e);
                            }
                            gVar.f6989a.f4791i.setVisibility(0);
                            gVar.f6989a.T.setEnabled(gVar.f6993e);
                        } else {
                            gVar.f6989a.f4793k.setVisibility(8);
                            gVar.f6989a.V.setEnabled(false);
                            gVar.f6989a.f4789h.setVisibility(8);
                            SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                            if (switchMaterial14 != null) {
                                switchMaterial14.setEnabled(false);
                            }
                            gVar.f6989a.f4791i.setVisibility(8);
                            gVar.f6989a.T.setEnabled(false);
                        }
                        if (z7 && gVar.f6993e) {
                            gVar.f();
                        } else {
                            gVar.h();
                        }
                        gVar.o();
                        break;
                    case 9:
                        a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                        if (!z7) {
                            Context requireContext7 = gVar.requireContext();
                            boolean z10 = FloatingWindowService.f4529p;
                            a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                            break;
                        } else {
                            Context requireContext8 = gVar.requireContext();
                            boolean z11 = FloatingWindowService.f4529p;
                            a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                        }
                        gVar.o();
                        break;
                    case 10:
                        a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                        LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                        if (linearLayout2 != null) {
                            linearLayout2.setVisibility(z7 ? 0 : 8);
                        }
                        View view3 = gVar.f6989a.f4785f;
                        if (view3 != null) {
                            view3.setVisibility(z7 ? 0 : 8);
                        }
                        if (!z7) {
                            Context requireContext9 = gVar.requireContext();
                            boolean z12 = FloatingWindowService.f4529p;
                            a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                            break;
                        } else {
                            Context requireContext10 = gVar.requireContext();
                            boolean z13 = FloatingWindowService.f4529p;
                            a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                        }
                        gVar.o();
                        break;
                    case 11:
                        a.a.m(gVar.f6990b, "lyric_enabled", z7);
                        if (!z7) {
                            Context requireContext11 = gVar.requireContext();
                            boolean z14 = FloatingWindowService.f4529p;
                            a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                            break;
                        } else {
                            Context requireContext12 = gVar.requireContext();
                            boolean z15 = FloatingWindowService.f4529p;
                            a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                        }
                        gVar.o();
                        break;
                    case 12:
                        gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                        gVar.l(z7);
                        if (!z7) {
                            Context requireContext13 = gVar.requireContext();
                            boolean z16 = FloatingWindowService.f4529p;
                            a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                            break;
                        } else {
                            Context requireContext14 = gVar.requireContext();
                            boolean z17 = FloatingWindowService.f4529p;
                            a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                        }
                        gVar.o();
                        break;
                    case 13:
                        a.a.m(gVar.f6990b, "lyric_double_line", z7);
                        if (gVar.f6989a.Q.isChecked()) {
                            Context requireContext15 = gVar.requireContext();
                            boolean z18 = FloatingWindowService.f4529p;
                            Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                            intent6.setAction("update_lyric_config");
                            intent6.putExtra("double_line_enabled", z7);
                            FloatingWindowService.n(requireContext15, intent6);
                            break;
                        }
                        break;
                    case 14:
                        a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            gVar.h();
                            gVar.f();
                            break;
                        }
                        break;
                    default:
                        gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                        gVar.j();
                        break;
                }
            }
        });
        SwitchMaterial switchMaterial12 = this.f6989a.W;
        if (switchMaterial12 != null) {
            final int i22 = 9;
            switchMaterial12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i22;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial13 = gVar.f6989a.U;
                                if (switchMaterial13 != null) {
                                    switchMaterial13.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                                if (switchMaterial14 != null) {
                                    switchMaterial14.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial13 = this.f6989a.X;
        if (switchMaterial13 != null) {
            final int i23 = 10;
            switchMaterial13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i23;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial14 = gVar.f6989a.U;
                                if (switchMaterial14 != null) {
                                    switchMaterial14.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial14 = this.f6989a.Q;
        if (switchMaterial14 != null) {
            final int i24 = 11;
            switchMaterial14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i24;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial15 = this.f6989a.M;
        if (switchMaterial15 != null) {
            final int i25 = 12;
            switchMaterial15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i25;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        RadioGroup radioGroup = this.f6989a.C;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: j4.c

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6981b;

                {
                    this.f6981b = this;
                }

                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i192) {
                    int i202;
                    int i212 = i9;
                    int i222 = 2;
                    int i232 = 0;
                    g gVar = this.f6981b;
                    switch (i212) {
                        case 0:
                            int[] iArr2 = g.f6988g;
                            gVar.getClass();
                            if (i192 == p.rb_music_source_kuwo) {
                                i222 = 1;
                            } else if (i192 != p.rb_music_source_kugou) {
                                i222 = i192 == p.rb_music_source_netease ? 3 : i192 == p.rb_music_source_new_kuwo ? 4 : i192 == p.rb_music_source_luna ? 5 : i192 == p.rb_music_source_universal ? 6 : 0;
                            }
                            gVar.f6990b.edit().putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i222).putInt("desktop_music_source", i222).apply();
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext = gVar.requireContext();
                                boolean z7 = FloatingWindowService.f4529p;
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("update_lyric_config");
                                intent.putExtra(FloatingWindowService.KEY_MUSIC_SOURCE, i222);
                                FloatingWindowService.n(requireContext, intent);
                                break;
                            }
                            break;
                        case 1:
                            int[] iArr3 = g.f6988g;
                            gVar.getClass();
                            while (true) {
                                if (i232 < radioGroup2.getChildCount()) {
                                    if (radioGroup2.getChildAt(i232).getId() == i192) {
                                        int[] iArr4 = g.f6988g;
                                        i202 = i232 < iArr4.length ? iArr4[i232] : -1;
                                    } else {
                                        i232++;
                                    }
                                }
                            }
                            a.a.l(gVar.f6990b, "lyric_color", i202);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext2 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("update_lyric_config");
                                intent2.putExtra("text_color", i202);
                                FloatingWindowService.n(requireContext2, intent2);
                                break;
                            }
                            break;
                        case 2:
                            int[] iArr5 = g.f6988g;
                            gVar.getClass();
                            int i242 = i192 == p.rb_lyric_gravity_left ? 19 : i192 == p.rb_lyric_gravity_right ? 21 : 17;
                            a.a.l(gVar.f6990b, "lyric_gravity", i242);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext3 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("update_lyric_config");
                                intent3.putExtra("gravity", i242);
                                FloatingWindowService.n(requireContext3, intent3);
                                break;
                            }
                            break;
                        default:
                            if (i192 == gVar.f6989a.f4806x.getId()) {
                                i222 = 1;
                            } else if (i192 != gVar.f6989a.f4804v.getId()) {
                                i222 = 0;
                            }
                            a.a.l(gVar.f6990b, "tmc_bar_position", i222);
                            if (gVar.f6989a.X.isChecked()) {
                                Context requireContext4 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext4, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_tmc_bar_position");
                                intent4.putExtra("position_mode", i222);
                                FloatingWindowService.n(requireContext4, intent4);
                                break;
                            }
                            break;
                    }
                }
            });
        }
        SeekBar seekBar5 = this.f6989a.F;
        if (seekBar5 != null) {
            seekBar5.setOnSeekBarChangeListener(new f(this, i10));
        }
        SwitchMaterial switchMaterial16 = this.f6989a.R;
        if (switchMaterial16 != null) {
            final int i26 = 13;
            switchMaterial16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i26;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        final int i27 = 14;
        this.f6989a.V.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6979b;

            {
                this.f6979b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                int i202 = i27;
                g gVar = this.f6979b;
                switch (i202) {
                    case 0:
                        a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                        if (z7) {
                            gVar.i();
                        } else {
                            gVar.f6989a.O.setChecked(false);
                            gVar.f6989a.S.setChecked(false);
                            gVar.f6989a.V.setChecked(false);
                            SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                            if (switchMaterial62 != null) {
                                switchMaterial62.setChecked(false);
                            }
                            SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                            if (switchMaterial72 != null) {
                                switchMaterial72.setChecked(false);
                            }
                            SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                            if (switchMaterial82 != null) {
                                switchMaterial82.setChecked(false);
                            }
                            SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                            if (switchMaterial92 != null) {
                                switchMaterial92.setChecked(false);
                            }
                            SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                            if (switchMaterial102 != null) {
                                switchMaterial102.setChecked(false);
                            }
                            SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                            if (switchMaterial112 != null) {
                                switchMaterial112.setChecked(false);
                            }
                            SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                            if (switchMaterial122 != null) {
                                switchMaterial122.setChecked(false);
                            }
                            gVar.g();
                            gVar.h();
                            Context requireContext = gVar.requireContext();
                            Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                            intent.setAction("destroy_road_speed");
                            FloatingWindowService.n(requireContext, intent);
                            Context requireContext2 = gVar.requireContext();
                            Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                            intent2.setAction("destroy_tmc_bar");
                            FloatingWindowService.n(requireContext2, intent2);
                            Context requireContext3 = gVar.requireContext();
                            Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                            intent3.setAction("destroy_lyric_view");
                            FloatingWindowService.n(requireContext3, intent3);
                            Context requireContext4 = gVar.requireContext();
                            a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                            gVar.f6993e = false;
                            gVar.f6992d = -1;
                            gVar.o();
                        }
                        gVar.o();
                        break;
                    case 1:
                        a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext5 = gVar.requireContext();
                            boolean z8 = FloatingWindowService.f4529p;
                            Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                            intent4.setAction("update_navigation_eta_service_info_enabled");
                            intent4.putExtra("service_info_enabled", z7);
                            FloatingWindowService.n(requireContext5, intent4);
                            break;
                        }
                        break;
                    case 2:
                        a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            Context requireContext6 = gVar.requireContext();
                            boolean z9 = FloatingWindowService.f4529p;
                            Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                            intent5.setAction("update_navigation_eta_bold_enabled");
                            intent5.putExtra("bold_enabled", z7);
                            FloatingWindowService.n(requireContext6, intent5);
                            break;
                        }
                        break;
                    case 3:
                        int[] iArr2 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                        break;
                    case 4:
                        int[] iArr3 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                        View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                        View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                        if (findViewById3 != null) {
                            findViewById3.setVisibility(z7 ? 0 : 8);
                        }
                        if (findViewById22 != null) {
                            findViewById22.setVisibility(z7 ? 0 : 8);
                            break;
                        }
                        break;
                    case 5:
                        int[] iArr4 = g.f6988g;
                        SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                        break;
                    case 6:
                        a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                        break;
                    case 7:
                        a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                        if (z7 && gVar.f6993e) {
                            gVar.e();
                        } else {
                            gVar.g();
                        }
                        gVar.o();
                        break;
                    case 8:
                        a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                        if (z7) {
                            gVar.f6989a.f4793k.setVisibility(0);
                            gVar.f6989a.V.setEnabled(gVar.f6993e);
                            gVar.f6989a.f4789h.setVisibility(0);
                            SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                            if (switchMaterial132 != null) {
                                switchMaterial132.setEnabled(gVar.f6993e);
                            }
                            gVar.f6989a.f4791i.setVisibility(0);
                            gVar.f6989a.T.setEnabled(gVar.f6993e);
                        } else {
                            gVar.f6989a.f4793k.setVisibility(8);
                            gVar.f6989a.V.setEnabled(false);
                            gVar.f6989a.f4789h.setVisibility(8);
                            SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                            if (switchMaterial142 != null) {
                                switchMaterial142.setEnabled(false);
                            }
                            gVar.f6989a.f4791i.setVisibility(8);
                            gVar.f6989a.T.setEnabled(false);
                        }
                        if (z7 && gVar.f6993e) {
                            gVar.f();
                        } else {
                            gVar.h();
                        }
                        gVar.o();
                        break;
                    case 9:
                        a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                        if (!z7) {
                            Context requireContext7 = gVar.requireContext();
                            boolean z10 = FloatingWindowService.f4529p;
                            a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                            break;
                        } else {
                            Context requireContext8 = gVar.requireContext();
                            boolean z11 = FloatingWindowService.f4529p;
                            a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                        }
                        gVar.o();
                        break;
                    case 10:
                        a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                        LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                        if (linearLayout2 != null) {
                            linearLayout2.setVisibility(z7 ? 0 : 8);
                        }
                        View view3 = gVar.f6989a.f4785f;
                        if (view3 != null) {
                            view3.setVisibility(z7 ? 0 : 8);
                        }
                        if (!z7) {
                            Context requireContext9 = gVar.requireContext();
                            boolean z12 = FloatingWindowService.f4529p;
                            a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                            break;
                        } else {
                            Context requireContext10 = gVar.requireContext();
                            boolean z13 = FloatingWindowService.f4529p;
                            a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                        }
                        gVar.o();
                        break;
                    case 11:
                        a.a.m(gVar.f6990b, "lyric_enabled", z7);
                        if (!z7) {
                            Context requireContext11 = gVar.requireContext();
                            boolean z14 = FloatingWindowService.f4529p;
                            a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                            break;
                        } else {
                            Context requireContext12 = gVar.requireContext();
                            boolean z15 = FloatingWindowService.f4529p;
                            a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                        }
                        gVar.o();
                        break;
                    case 12:
                        gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                        gVar.l(z7);
                        if (!z7) {
                            Context requireContext13 = gVar.requireContext();
                            boolean z16 = FloatingWindowService.f4529p;
                            a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                        } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                            gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                            break;
                        } else {
                            Context requireContext14 = gVar.requireContext();
                            boolean z17 = FloatingWindowService.f4529p;
                            a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                        }
                        gVar.o();
                        break;
                    case 13:
                        a.a.m(gVar.f6990b, "lyric_double_line", z7);
                        if (gVar.f6989a.Q.isChecked()) {
                            Context requireContext15 = gVar.requireContext();
                            boolean z18 = FloatingWindowService.f4529p;
                            Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                            intent6.setAction("update_lyric_config");
                            intent6.putExtra("double_line_enabled", z7);
                            FloatingWindowService.n(requireContext15, intent6);
                            break;
                        }
                        break;
                    case 14:
                        a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                        if (gVar.f6989a.S.isChecked()) {
                            gVar.h();
                            gVar.f();
                            break;
                        }
                        break;
                    default:
                        gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                        gVar.j();
                        break;
                }
            }
        });
        SwitchMaterial switchMaterial17 = this.f6989a.U;
        if (switchMaterial17 != null) {
            switchMaterial17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i10;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SeekBar seekBar6 = this.f6989a.I;
        if (seekBar6 != null) {
            seekBar6.setOnSeekBarChangeListener(new f(this, i7));
        }
        SeekBar seekBar7 = this.f6989a.H;
        if (seekBar7 != null) {
            seekBar7.setOnSeekBarChangeListener(new f(this, i15));
        }
        SwitchMaterial switchMaterial18 = this.f6989a.T;
        if (switchMaterial18 != null) {
            switchMaterial18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i7;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial19 = this.f6989a.K;
        if (switchMaterial19 != null) {
            switchMaterial19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i15;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial20 = this.f6989a.J;
        if (switchMaterial20 != null) {
            switchMaterial20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i13;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial21 = (SwitchMaterial) this.f6989a.f4775a.findViewById(p.switch_cluster_enlarge_cruise_auto_bg);
        if (switchMaterial21 != null) {
            switchMaterial21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i14;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial22 = this.f6989a.N;
        if (switchMaterial22 != null) {
            switchMaterial22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: j4.b

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6979b;

                {
                    this.f6979b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                    int i202 = i12;
                    g gVar = this.f6979b;
                    switch (i202) {
                        case 0:
                            a.a.m(gVar.f6990b, "dashboard_enabled", z7);
                            if (z7) {
                                gVar.i();
                            } else {
                                gVar.f6989a.O.setChecked(false);
                                gVar.f6989a.S.setChecked(false);
                                gVar.f6989a.V.setChecked(false);
                                SwitchMaterial switchMaterial62 = gVar.f6989a.U;
                                if (switchMaterial62 != null) {
                                    switchMaterial62.setChecked(false);
                                }
                                SwitchMaterial switchMaterial72 = gVar.f6989a.T;
                                if (switchMaterial72 != null) {
                                    switchMaterial72.setChecked(false);
                                }
                                SwitchMaterial switchMaterial82 = gVar.f6989a.W;
                                if (switchMaterial82 != null) {
                                    switchMaterial82.setChecked(false);
                                }
                                SwitchMaterial switchMaterial92 = gVar.f6989a.X;
                                if (switchMaterial92 != null) {
                                    switchMaterial92.setChecked(false);
                                }
                                SwitchMaterial switchMaterial102 = gVar.f6989a.Q;
                                if (switchMaterial102 != null) {
                                    switchMaterial102.setChecked(false);
                                }
                                SwitchMaterial switchMaterial112 = gVar.f6989a.R;
                                if (switchMaterial112 != null) {
                                    switchMaterial112.setChecked(false);
                                }
                                SwitchMaterial switchMaterial122 = gVar.f6989a.M;
                                if (switchMaterial122 != null) {
                                    switchMaterial122.setChecked(false);
                                }
                                gVar.g();
                                gVar.h();
                                Context requireContext = gVar.requireContext();
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("destroy_road_speed");
                                FloatingWindowService.n(requireContext, intent);
                                Context requireContext2 = gVar.requireContext();
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("destroy_tmc_bar");
                                FloatingWindowService.n(requireContext2, intent2);
                                Context requireContext3 = gVar.requireContext();
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("destroy_lyric_view");
                                FloatingWindowService.n(requireContext3, intent3);
                                Context requireContext4 = gVar.requireContext();
                                a.a.k(requireContext4, FloatingWindowService.class, "destroy_tire_card", requireContext4);
                                gVar.f6993e = false;
                                gVar.f6992d = -1;
                                gVar.o();
                            }
                            gVar.o();
                            break;
                        case 1:
                            a.a.m(gVar.f6990b, "navigation_eta_service_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext5 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext5, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_navigation_eta_service_info_enabled");
                                intent4.putExtra("service_info_enabled", z7);
                                FloatingWindowService.n(requireContext5, intent4);
                                break;
                            }
                            break;
                        case 2:
                            a.a.m(gVar.f6990b, "navigation_eta_bold_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                Context requireContext6 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent5 = new Intent(requireContext6, (Class<?>) FloatingWindowService.class);
                                intent5.setAction("update_navigation_eta_bold_enabled");
                                intent5.putExtra("bold_enabled", z7);
                                FloatingWindowService.n(requireContext6, intent5);
                                break;
                            }
                            break;
                        case 3:
                            int[] iArr2 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnNavigation(gVar.requireContext(), z7);
                            break;
                        case 4:
                            int[] iArr3 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeOnCruise(gVar.requireContext(), z7);
                            View findViewById3 = gVar.f6989a.f4775a.findViewById(p.layout_cluster_enlarge_cruise_auto_bg);
                            View findViewById22 = gVar.f6989a.f4775a.findViewById(p.div_cluster_enlarge_cruise_to_auto_bg);
                            if (findViewById3 != null) {
                                findViewById3.setVisibility(z7 ? 0 : 8);
                            }
                            if (findViewById22 != null) {
                                findViewById22.setVisibility(z7 ? 0 : 8);
                                break;
                            }
                            break;
                        case 5:
                            int[] iArr4 = g.f6988g;
                            SharedPreferencesUtils.setClusterEnlargeCruiseAutoBgEnabled(gVar.requireContext(), z7);
                            break;
                        case 6:
                            a.a.m(gVar.f6990b, "extscreen_activation_enabled", z7);
                            break;
                        case 7:
                            a.a.m(gVar.f6990b, "lane_lines_enabled", z7);
                            if (z7 && gVar.f6993e) {
                                gVar.e();
                            } else {
                                gVar.g();
                            }
                            gVar.o();
                            break;
                        case 8:
                            a.a.m(gVar.f6990b, "navigation_eta_enabled", z7);
                            if (z7) {
                                gVar.f6989a.f4793k.setVisibility(0);
                                gVar.f6989a.V.setEnabled(gVar.f6993e);
                                gVar.f6989a.f4789h.setVisibility(0);
                                SwitchMaterial switchMaterial132 = gVar.f6989a.U;
                                if (switchMaterial132 != null) {
                                    switchMaterial132.setEnabled(gVar.f6993e);
                                }
                                gVar.f6989a.f4791i.setVisibility(0);
                                gVar.f6989a.T.setEnabled(gVar.f6993e);
                            } else {
                                gVar.f6989a.f4793k.setVisibility(8);
                                gVar.f6989a.V.setEnabled(false);
                                gVar.f6989a.f4789h.setVisibility(8);
                                SwitchMaterial switchMaterial142 = gVar.f6989a.U;
                                if (switchMaterial142 != null) {
                                    switchMaterial142.setEnabled(false);
                                }
                                gVar.f6989a.f4791i.setVisibility(8);
                                gVar.f6989a.T.setEnabled(false);
                            }
                            if (z7 && gVar.f6993e) {
                                gVar.f();
                            } else {
                                gVar.h();
                            }
                            gVar.o();
                            break;
                        case 9:
                            a.a.m(gVar.f6990b, "road_speed_info_enabled", z7);
                            if (!z7) {
                                Context requireContext7 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                a.a.k(requireContext7, FloatingWindowService.class, "destroy_road_speed", requireContext7);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示道路及限速信息");
                                break;
                            } else {
                                Context requireContext8 = gVar.requireContext();
                                boolean z11 = FloatingWindowService.f4529p;
                                a.a.k(requireContext8, FloatingWindowService.class, "create_road_speed", requireContext8);
                            }
                            gVar.o();
                            break;
                        case 10:
                            a.a.m(gVar.f6990b, "tmc_bar_enabled", z7);
                            LinearLayout linearLayout2 = gVar.f6989a.f4792j;
                            if (linearLayout2 != null) {
                                linearLayout2.setVisibility(z7 ? 0 : 8);
                            }
                            View view3 = gVar.f6989a.f4785f;
                            if (view3 != null) {
                                view3.setVisibility(z7 ? 0 : 8);
                            }
                            if (!z7) {
                                Context requireContext9 = gVar.requireContext();
                                boolean z12 = FloatingWindowService.f4529p;
                                a.a.k(requireContext9, FloatingWindowService.class, "destroy_tmc_bar", requireContext9);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示实时交通光柱图");
                                break;
                            } else {
                                Context requireContext10 = gVar.requireContext();
                                boolean z13 = FloatingWindowService.f4529p;
                                a.a.k(requireContext10, FloatingWindowService.class, "create_tmc_bar", requireContext10);
                            }
                            gVar.o();
                            break;
                        case 11:
                            a.a.m(gVar.f6990b, "lyric_enabled", z7);
                            if (!z7) {
                                Context requireContext11 = gVar.requireContext();
                                boolean z14 = FloatingWindowService.f4529p;
                                a.a.k(requireContext11, FloatingWindowService.class, "destroy_lyric_view", requireContext11);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表歌词");
                                break;
                            } else {
                                Context requireContext12 = gVar.requireContext();
                                boolean z15 = FloatingWindowService.f4529p;
                                a.a.k(requireContext12, FloatingWindowService.class, "create_lyric_view", requireContext12);
                            }
                            gVar.o();
                            break;
                        case 12:
                            gVar.f6990b.edit().putBoolean("dashboard_tire_card_enabled", z7).apply();
                            gVar.l(z7);
                            if (!z7) {
                                Context requireContext13 = gVar.requireContext();
                                boolean z16 = FloatingWindowService.f4529p;
                                a.a.k(requireContext13, FloatingWindowService.class, "destroy_tire_card", requireContext13);
                            } else if (!Settings.canDrawOverlays(gVar.requireContext())) {
                                gVar.f6994f.a(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + gVar.requireContext().getPackageName())));
                                ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示胎压卡片");
                                break;
                            } else {
                                Context requireContext14 = gVar.requireContext();
                                boolean z17 = FloatingWindowService.f4529p;
                                a.a.k(requireContext14, FloatingWindowService.class, "create_tire_card", requireContext14);
                            }
                            gVar.o();
                            break;
                        case 13:
                            a.a.m(gVar.f6990b, "lyric_double_line", z7);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext15 = gVar.requireContext();
                                boolean z18 = FloatingWindowService.f4529p;
                                Intent intent6 = new Intent(requireContext15, (Class<?>) FloatingWindowService.class);
                                intent6.setAction("update_lyric_config");
                                intent6.putExtra("double_line_enabled", z7);
                                FloatingWindowService.n(requireContext15, intent6);
                                break;
                            }
                            break;
                        case 14:
                            a.a.m(gVar.f6990b, "navigation_eta_top_info_enabled", z7);
                            if (gVar.f6989a.S.isChecked()) {
                                gVar.h();
                                gVar.f();
                                break;
                            }
                            break;
                        default:
                            gVar.f6990b.edit().putBoolean("lane_lines_transparent_bg", z7).apply();
                            gVar.j();
                            break;
                    }
                }
            });
        }
        SeekBar seekBar8 = this.f6989a.E;
        if (seekBar8 != null) {
            seekBar8.setMax(70);
            int i28 = this.f6990b.getInt("lane_lines_height", 80);
            if (i28 > 120) {
                float f7 = i28;
                if (requireContext().getResources().getDisplayMetrics().density != CropImageView.DEFAULT_ASPECT_RATIO) {
                    f7 /= requireContext().getResources().getDisplayMetrics().density;
                }
                i28 = Math.round(f7);
            }
            seekBar8.setProgress(d(i28, 50, z1.c.INVOKE_INTERFACE_RANGE) - 50);
            seekBar8.setOnSeekBarChangeListener(new f(this, i13));
        }
        this.f6989a.f4779c.setOnClickListener(new View.OnClickListener(this) { // from class: j4.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6983b;

            {
                this.f6983b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                int i29 = i9;
                g gVar = this.f6983b;
                switch (i29) {
                    case 0:
                        PositionSettingsDialog newInstance = PositionSettingsDialog.newInstance("车道线位置设置", gVar.f6990b.getInt("lane_lines_x", 0), gVar.f6990b.getInt("lane_lines_y", 0));
                        newInstance.setOnPositionSetListener(new a(gVar, 3));
                        newInstance.show(gVar.getParentFragmentManager(), "LaneLinesPositionDialog");
                        break;
                    case 1:
                        PositionSettingsDialog newInstance2 = PositionSettingsDialog.newInstance("导航ETA位置设置", gVar.f6990b.getInt("navigation_eta_x", 0), gVar.f6990b.getInt("navigation_eta_y", 0));
                        newInstance2.setOnPositionSetListener(new a(gVar, 1));
                        newInstance2.show(gVar.getParentFragmentManager(), "NavigationETAPositionDialog");
                        break;
                    case 2:
                        PositionSettingsDialog newInstance3 = PositionSettingsDialog.newInstance("仪表歌词位置设置", gVar.f6990b.getInt("lyric_position_x", 0), gVar.f6990b.getInt("lyric_position_y", 0));
                        newInstance3.setOnPositionSetListener(new a(gVar, 2));
                        newInstance3.show(gVar.getParentFragmentManager(), "LyricPositionDialog");
                        break;
                    default:
                        PositionSettingsDialog newInstance4 = PositionSettingsDialog.newInstance("胎压卡片位置设置", gVar.f6990b.getInt("dashboard_tire_card_x", 0), gVar.f6990b.getInt("dashboard_tire_card_y", 0));
                        newInstance4.setOnPositionSetListener(new a(gVar, 4));
                        newInstance4.show(gVar.getParentFragmentManager(), "DashboardTirePositionDialog");
                        break;
                }
            }
        });
        this.f6989a.f4783e.setOnClickListener(new View.OnClickListener(this) { // from class: j4.d

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ g f6983b;

            {
                this.f6983b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                int i29 = i10;
                g gVar = this.f6983b;
                switch (i29) {
                    case 0:
                        PositionSettingsDialog newInstance = PositionSettingsDialog.newInstance("车道线位置设置", gVar.f6990b.getInt("lane_lines_x", 0), gVar.f6990b.getInt("lane_lines_y", 0));
                        newInstance.setOnPositionSetListener(new a(gVar, 3));
                        newInstance.show(gVar.getParentFragmentManager(), "LaneLinesPositionDialog");
                        break;
                    case 1:
                        PositionSettingsDialog newInstance2 = PositionSettingsDialog.newInstance("导航ETA位置设置", gVar.f6990b.getInt("navigation_eta_x", 0), gVar.f6990b.getInt("navigation_eta_y", 0));
                        newInstance2.setOnPositionSetListener(new a(gVar, 1));
                        newInstance2.show(gVar.getParentFragmentManager(), "NavigationETAPositionDialog");
                        break;
                    case 2:
                        PositionSettingsDialog newInstance3 = PositionSettingsDialog.newInstance("仪表歌词位置设置", gVar.f6990b.getInt("lyric_position_x", 0), gVar.f6990b.getInt("lyric_position_y", 0));
                        newInstance3.setOnPositionSetListener(new a(gVar, 2));
                        newInstance3.show(gVar.getParentFragmentManager(), "LyricPositionDialog");
                        break;
                    default:
                        PositionSettingsDialog newInstance4 = PositionSettingsDialog.newInstance("胎压卡片位置设置", gVar.f6990b.getInt("dashboard_tire_card_x", 0), gVar.f6990b.getInt("dashboard_tire_card_y", 0));
                        newInstance4.setOnPositionSetListener(new a(gVar, 4));
                        newInstance4.show(gVar.getParentFragmentManager(), "DashboardTirePositionDialog");
                        break;
                }
            }
        });
        Button button = this.f6989a.f4781d;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener(this) { // from class: j4.d

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6983b;

                {
                    this.f6983b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    int i29 = i7;
                    g gVar = this.f6983b;
                    switch (i29) {
                        case 0:
                            PositionSettingsDialog newInstance = PositionSettingsDialog.newInstance("车道线位置设置", gVar.f6990b.getInt("lane_lines_x", 0), gVar.f6990b.getInt("lane_lines_y", 0));
                            newInstance.setOnPositionSetListener(new a(gVar, 3));
                            newInstance.show(gVar.getParentFragmentManager(), "LaneLinesPositionDialog");
                            break;
                        case 1:
                            PositionSettingsDialog newInstance2 = PositionSettingsDialog.newInstance("导航ETA位置设置", gVar.f6990b.getInt("navigation_eta_x", 0), gVar.f6990b.getInt("navigation_eta_y", 0));
                            newInstance2.setOnPositionSetListener(new a(gVar, 1));
                            newInstance2.show(gVar.getParentFragmentManager(), "NavigationETAPositionDialog");
                            break;
                        case 2:
                            PositionSettingsDialog newInstance3 = PositionSettingsDialog.newInstance("仪表歌词位置设置", gVar.f6990b.getInt("lyric_position_x", 0), gVar.f6990b.getInt("lyric_position_y", 0));
                            newInstance3.setOnPositionSetListener(new a(gVar, 2));
                            newInstance3.show(gVar.getParentFragmentManager(), "LyricPositionDialog");
                            break;
                        default:
                            PositionSettingsDialog newInstance4 = PositionSettingsDialog.newInstance("胎压卡片位置设置", gVar.f6990b.getInt("dashboard_tire_card_x", 0), gVar.f6990b.getInt("dashboard_tire_card_y", 0));
                            newInstance4.setOnPositionSetListener(new a(gVar, 4));
                            newInstance4.show(gVar.getParentFragmentManager(), "DashboardTirePositionDialog");
                            break;
                    }
                }
            });
        }
        Button button2 = this.f6989a.f4777b;
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: j4.d

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6983b;

                {
                    this.f6983b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    int i29 = i15;
                    g gVar = this.f6983b;
                    switch (i29) {
                        case 0:
                            PositionSettingsDialog newInstance = PositionSettingsDialog.newInstance("车道线位置设置", gVar.f6990b.getInt("lane_lines_x", 0), gVar.f6990b.getInt("lane_lines_y", 0));
                            newInstance.setOnPositionSetListener(new a(gVar, 3));
                            newInstance.show(gVar.getParentFragmentManager(), "LaneLinesPositionDialog");
                            break;
                        case 1:
                            PositionSettingsDialog newInstance2 = PositionSettingsDialog.newInstance("导航ETA位置设置", gVar.f6990b.getInt("navigation_eta_x", 0), gVar.f6990b.getInt("navigation_eta_y", 0));
                            newInstance2.setOnPositionSetListener(new a(gVar, 1));
                            newInstance2.show(gVar.getParentFragmentManager(), "NavigationETAPositionDialog");
                            break;
                        case 2:
                            PositionSettingsDialog newInstance3 = PositionSettingsDialog.newInstance("仪表歌词位置设置", gVar.f6990b.getInt("lyric_position_x", 0), gVar.f6990b.getInt("lyric_position_y", 0));
                            newInstance3.setOnPositionSetListener(new a(gVar, 2));
                            newInstance3.show(gVar.getParentFragmentManager(), "LyricPositionDialog");
                            break;
                        default:
                            PositionSettingsDialog newInstance4 = PositionSettingsDialog.newInstance("胎压卡片位置设置", gVar.f6990b.getInt("dashboard_tire_card_x", 0), gVar.f6990b.getInt("dashboard_tire_card_y", 0));
                            newInstance4.setOnPositionSetListener(new a(gVar, 4));
                            newInstance4.show(gVar.getParentFragmentManager(), "DashboardTirePositionDialog");
                            break;
                    }
                }
            });
        }
        RadioGroup radioGroup2 = this.f6989a.D;
        if (radioGroup2 != null) {
            radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(this) { // from class: j4.c

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ g f6981b;

                {
                    this.f6981b = this;
                }

                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup22, int i192) {
                    int i202;
                    int i212 = i15;
                    int i222 = 2;
                    int i232 = 0;
                    g gVar = this.f6981b;
                    switch (i212) {
                        case 0:
                            int[] iArr2 = g.f6988g;
                            gVar.getClass();
                            if (i192 == p.rb_music_source_kuwo) {
                                i222 = 1;
                            } else if (i192 != p.rb_music_source_kugou) {
                                i222 = i192 == p.rb_music_source_netease ? 3 : i192 == p.rb_music_source_new_kuwo ? 4 : i192 == p.rb_music_source_luna ? 5 : i192 == p.rb_music_source_universal ? 6 : 0;
                            }
                            gVar.f6990b.edit().putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i222).putInt("desktop_music_source", i222).apply();
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext = gVar.requireContext();
                                boolean z7 = FloatingWindowService.f4529p;
                                Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                                intent.setAction("update_lyric_config");
                                intent.putExtra(FloatingWindowService.KEY_MUSIC_SOURCE, i222);
                                FloatingWindowService.n(requireContext, intent);
                                break;
                            }
                            break;
                        case 1:
                            int[] iArr3 = g.f6988g;
                            gVar.getClass();
                            while (true) {
                                if (i232 < radioGroup22.getChildCount()) {
                                    if (radioGroup22.getChildAt(i232).getId() == i192) {
                                        int[] iArr4 = g.f6988g;
                                        i202 = i232 < iArr4.length ? iArr4[i232] : -1;
                                    } else {
                                        i232++;
                                    }
                                }
                            }
                            a.a.l(gVar.f6990b, "lyric_color", i202);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext2 = gVar.requireContext();
                                boolean z8 = FloatingWindowService.f4529p;
                                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                                intent2.setAction("update_lyric_config");
                                intent2.putExtra("text_color", i202);
                                FloatingWindowService.n(requireContext2, intent2);
                                break;
                            }
                            break;
                        case 2:
                            int[] iArr5 = g.f6988g;
                            gVar.getClass();
                            int i242 = i192 == p.rb_lyric_gravity_left ? 19 : i192 == p.rb_lyric_gravity_right ? 21 : 17;
                            a.a.l(gVar.f6990b, "lyric_gravity", i242);
                            if (gVar.f6989a.Q.isChecked()) {
                                Context requireContext3 = gVar.requireContext();
                                boolean z9 = FloatingWindowService.f4529p;
                                Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                                intent3.setAction("update_lyric_config");
                                intent3.putExtra("gravity", i242);
                                FloatingWindowService.n(requireContext3, intent3);
                                break;
                            }
                            break;
                        default:
                            if (i192 == gVar.f6989a.f4806x.getId()) {
                                i222 = 1;
                            } else if (i192 != gVar.f6989a.f4804v.getId()) {
                                i222 = 0;
                            }
                            a.a.l(gVar.f6990b, "tmc_bar_position", i222);
                            if (gVar.f6989a.X.isChecked()) {
                                Context requireContext4 = gVar.requireContext();
                                boolean z10 = FloatingWindowService.f4529p;
                                Intent intent4 = new Intent(requireContext4, (Class<?>) FloatingWindowService.class);
                                intent4.setAction("update_tmc_bar_position");
                                intent4.putExtra("position_mode", i222);
                                FloatingWindowService.n(requireContext4, intent4);
                                break;
                            }
                            break;
                    }
                }
            });
        }
        n();
        o();
    }
}
