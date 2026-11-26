package j4;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.ui.theme.PositionSettingsDialog;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements d.a, PositionSettingsDialog.OnPositionSetListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6976a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g f6977b;

    public /* synthetic */ a(g gVar, int i7) {
        this.f6976a = i7;
        this.f6977b = gVar;
    }

    @Override // d.a
    public final void a(Object obj) {
        int[] iArr = g.f6988g;
        g gVar = this.f6977b;
        if (!Settings.canDrawOverlays(gVar.requireContext())) {
            ToastHelper.showLong(gVar.getContext(), "需要悬浮窗权限才能显示仪表扩展");
            gVar.f6989a.O.setChecked(false);
            gVar.f6989a.S.setChecked(false);
            SwitchMaterial switchMaterial = gVar.f6989a.W;
            if (switchMaterial != null) {
                switchMaterial.setChecked(false);
            }
            SwitchMaterial switchMaterial2 = gVar.f6989a.X;
            if (switchMaterial2 != null) {
                switchMaterial2.setChecked(false);
            }
            SwitchMaterial switchMaterial3 = gVar.f6989a.Q;
            if (switchMaterial3 != null) {
                switchMaterial3.setChecked(false);
            }
            gVar.m();
            return;
        }
        ToastHelper.show(gVar.getContext(), "悬浮窗权限已授予");
        if (gVar.f6989a.O.isChecked()) {
            gVar.e();
        }
        if (gVar.f6989a.S.isChecked()) {
            gVar.f();
        }
        SwitchMaterial switchMaterial4 = gVar.f6989a.W;
        if (switchMaterial4 != null && switchMaterial4.isChecked()) {
            Context requireContext = gVar.requireContext();
            boolean z6 = FloatingWindowService.f4529p;
            a.a.k(requireContext, FloatingWindowService.class, "create_road_speed", requireContext);
        }
        SwitchMaterial switchMaterial5 = gVar.f6989a.X;
        if (switchMaterial5 != null && switchMaterial5.isChecked()) {
            Context requireContext2 = gVar.requireContext();
            boolean z7 = FloatingWindowService.f4529p;
            a.a.k(requireContext2, FloatingWindowService.class, "create_tmc_bar", requireContext2);
        }
        SwitchMaterial switchMaterial6 = gVar.f6989a.Q;
        if (switchMaterial6 != null && switchMaterial6.isChecked()) {
            Context requireContext3 = gVar.requireContext();
            boolean z8 = FloatingWindowService.f4529p;
            a.a.k(requireContext3, FloatingWindowService.class, "create_lyric_view", requireContext3);
        }
        gVar.m();
    }

    @Override // com.navi.tool.ui.theme.PositionSettingsDialog.OnPositionSetListener
    public final void onPositionSet(int i7, int i8) {
        int max;
        int i9 = this.f6976a;
        g gVar = this.f6977b;
        switch (i9) {
            case 1:
                gVar.f6990b.edit().putInt("navigation_eta_x", i7).putInt("navigation_eta_y", i8).apply();
                gVar.n();
                if (gVar.f6989a.S.isChecked()) {
                    gVar.h();
                    gVar.f();
                    break;
                }
                break;
            case 2:
                gVar.f6990b.edit().putInt("lyric_position_x", i7).putInt("lyric_position_y", i8).apply();
                gVar.n();
                if (gVar.f6989a.Q.isChecked()) {
                    Context requireContext = gVar.requireContext();
                    boolean z6 = FloatingWindowService.f4529p;
                    Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                    intent.setAction("update_lyric_config");
                    intent.putExtra("x", i7);
                    intent.putExtra("y", i8);
                    FloatingWindowService.n(requireContext, intent);
                    break;
                }
                break;
            case 3:
                gVar.f6990b.edit().putInt("lane_lines_x", i7).putInt("lane_lines_y", i8).apply();
                gVar.n();
                if (gVar.f6989a.O.isChecked()) {
                    gVar.g();
                    gVar.e();
                }
                Context requireContext2 = gVar.requireContext();
                boolean z7 = FloatingWindowService.f4529p;
                Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                intent2.setAction("show_lane_preview");
                intent2.putExtra("x", i7);
                intent2.putExtra("y", i8);
                intent2.putExtra("width", x2.d.DEFAULT_FADE_ANIM_DURATION);
                int i10 = SharedPreferencesUtils.getAppPrefs(requireContext2).getInt("lane_lines_height", 80);
                float f7 = requireContext2.getResources().getDisplayMetrics().density;
                if (i10 > 120) {
                    float f8 = i10;
                    if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
                        f8 /= f7;
                    }
                    max = Math.max(50, Math.min(z1.c.INVOKE_INTERFACE_RANGE, Math.round(f8)));
                } else {
                    max = Math.max(50, Math.min(z1.c.INVOKE_INTERFACE_RANGE, i10));
                }
                intent2.putExtra("height", Math.round(max * f7));
                intent2.putExtra("duration_ms", a5.n.SOCKET_READ_TIMEOUT);
                FloatingWindowService.n(requireContext2, intent2);
                break;
            default:
                gVar.f6990b.edit().putInt("dashboard_tire_card_x", i7).putInt("dashboard_tire_card_y", i8).apply();
                gVar.n();
                SwitchMaterial switchMaterial = gVar.f6989a.M;
                if (switchMaterial != null && switchMaterial.isChecked()) {
                    Context requireContext3 = gVar.requireContext();
                    boolean z8 = FloatingWindowService.f4529p;
                    Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                    intent3.setAction("update_tire_card_position");
                    intent3.putExtra("x", i7);
                    intent3.putExtra("y", i8);
                    FloatingWindowService.n(requireContext3, intent3);
                    break;
                }
                break;
        }
    }
}
