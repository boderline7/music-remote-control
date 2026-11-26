package j4;

import android.content.Context;
import android.content.Intent;
import android.widget.SeekBar;
import android.widget.TextView;
import com.navi.tool.amapflying.util.FloatingWindowService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements SeekBar.OnSeekBarChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6986a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g f6987b;

    public /* synthetic */ f(g gVar, int i7) {
        this.f6986a = i7;
        this.f6987b = gVar;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
        int i8 = this.f6986a;
        g gVar = this.f6987b;
        switch (i8) {
            case 0:
                int i9 = i7 + x2.d.DEFAULT_FADE_ANIM_DURATION;
                TextView textView = gVar.f6989a.f4782d0;
                if (textView != null) {
                    textView.setText(String.format("歌词宽度: %ddp", Integer.valueOf(i9)));
                }
                if (z6) {
                    a.a.l(gVar.f6990b, "lyric_width_dp", i9);
                    if (gVar.f6989a.Q.isChecked()) {
                        Context requireContext = gVar.requireContext();
                        boolean z7 = FloatingWindowService.f4529p;
                        Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                        intent.setAction("update_lyric_config");
                        intent.putExtra("width_dp", i9);
                        FloatingWindowService.n(requireContext, intent);
                        break;
                    }
                }
                break;
            case 1:
                int i10 = i7 + 20;
                TextView textView2 = gVar.f6989a.f4778b0;
                if (textView2 != null) {
                    textView2.setText(String.format("歌词大小: %dsp", Integer.valueOf(i10)));
                    break;
                }
                break;
            case 2:
                int[] iArr = g.f6988g;
                gVar.getClass();
                int d7 = g.d(i7 + 25, 25, 40);
                TextView textView3 = gVar.f6989a.f4786f0;
                if (textView3 != null) {
                    textView3.setText(String.format("字体大小: %dsp", Integer.valueOf(d7)));
                    break;
                }
                break;
            case 3:
                int[] iArr2 = g.f6988g;
                gVar.getClass();
                int d8 = g.d(i7, 0, 100);
                TextView textView4 = gVar.f6989a.f4784e0;
                if (textView4 != null) {
                    textView4.setText(String.format("背景透明度: %d%%", Integer.valueOf(d8)));
                    break;
                }
                break;
            default:
                int[] iArr3 = g.f6988g;
                gVar.getClass();
                int d9 = g.d(i7 + 50, 50, z1.c.INVOKE_INTERFACE_RANGE);
                gVar.f6989a.Z.setText(String.format("高度: %ddp", Integer.valueOf(d9)));
                if (gVar.f6989a.O.isChecked()) {
                    Context requireContext2 = gVar.requireContext();
                    boolean z8 = FloatingWindowService.f4529p;
                    Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                    intent2.setAction("update_lane_lines_height");
                    intent2.putExtra("height_dp", d9);
                    FloatingWindowService.n(requireContext2, intent2);
                    break;
                }
                break;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        int i7 = this.f6986a;
        g gVar = this.f6987b;
        switch (i7) {
            case 0:
                break;
            case 1:
                int progress = seekBar.getProgress() + 20;
                a.a.l(gVar.f6990b, "lyric_font_size", progress);
                if (gVar.f6989a.Q.isChecked()) {
                    Context requireContext = gVar.requireContext();
                    boolean z6 = FloatingWindowService.f4529p;
                    Intent intent = new Intent(requireContext, (Class<?>) FloatingWindowService.class);
                    intent.setAction("update_lyric_config");
                    intent.putExtra("font_size", progress);
                    FloatingWindowService.n(requireContext, intent);
                    break;
                }
                break;
            case 2:
                int progress2 = seekBar.getProgress() + 25;
                int[] iArr = g.f6988g;
                gVar.getClass();
                int d7 = g.d(progress2, 25, 40);
                a.a.l(gVar.f6990b, "navigation_eta_font_size_sp", d7);
                if (gVar.f6989a.S.isChecked()) {
                    Context requireContext2 = gVar.requireContext();
                    boolean z7 = FloatingWindowService.f4529p;
                    Intent intent2 = new Intent(requireContext2, (Class<?>) FloatingWindowService.class);
                    intent2.setAction("update_navigation_eta_font_size");
                    intent2.putExtra("font_size_sp", d7);
                    FloatingWindowService.n(requireContext2, intent2);
                    break;
                }
                break;
            case 3:
                int progress3 = seekBar.getProgress();
                int[] iArr2 = g.f6988g;
                gVar.getClass();
                int d8 = g.d(progress3, 0, 100);
                a.a.l(gVar.f6990b, "navigation_eta_bg_alpha_percent", d8);
                if (gVar.f6989a.S.isChecked()) {
                    Context requireContext3 = gVar.requireContext();
                    boolean z8 = FloatingWindowService.f4529p;
                    Intent intent3 = new Intent(requireContext3, (Class<?>) FloatingWindowService.class);
                    intent3.setAction("update_navigation_eta_bg_alpha");
                    intent3.putExtra("bg_alpha_percent", d8);
                    FloatingWindowService.n(requireContext3, intent3);
                    break;
                }
                break;
            default:
                int progress4 = seekBar.getProgress() + 50;
                int[] iArr3 = g.f6988g;
                gVar.getClass();
                int d9 = g.d(progress4, 50, z1.c.INVOKE_INTERFACE_RANGE);
                a.a.l(gVar.f6990b, "lane_lines_height", d9);
                if (gVar.f6989a.O.isChecked()) {
                    Context requireContext4 = gVar.requireContext();
                    boolean z9 = FloatingWindowService.f4529p;
                    Intent intent4 = new Intent(requireContext4, (Class<?>) FloatingWindowService.class);
                    intent4.setAction("update_lane_lines_height");
                    intent4.putExtra("height_dp", d9);
                    FloatingWindowService.n(requireContext4, intent4);
                    break;
                }
                break;
        }
    }
}
