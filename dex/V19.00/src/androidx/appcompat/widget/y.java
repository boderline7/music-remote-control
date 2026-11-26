package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1481a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f1482b;

    /* renamed from: c, reason: collision with root package name */
    public final Serializable f1483c;

    /* renamed from: d, reason: collision with root package name */
    public Object f1484d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f1485e;

    /* renamed from: f, reason: collision with root package name */
    public Object f1486f;

    /* renamed from: g, reason: collision with root package name */
    public Object f1487g;

    /* JADX WARN: Type inference failed for: r2v1, types: [int[], java.io.Serializable] */
    public y() {
        this.f1481a = 0;
        this.f1482b = new int[]{f.e.abc_textfield_search_default_mtrl_alpha, f.e.abc_textfield_default_mtrl_alpha, f.e.abc_ab_share_pack_mtrl_alpha};
        this.f1483c = new int[]{f.e.abc_ic_commit_search_api_mtrl_alpha, f.e.abc_seekbar_tick_mark_material, f.e.abc_ic_menu_share_mtrl_alpha, f.e.abc_ic_menu_copy_mtrl_am_alpha, f.e.abc_ic_menu_cut_mtrl_alpha, f.e.abc_ic_menu_selectall_mtrl_alpha, f.e.abc_ic_menu_paste_mtrl_am_alpha};
        this.f1484d = new int[]{f.e.abc_textfield_activated_mtrl_alpha, f.e.abc_textfield_search_activated_mtrl_alpha, f.e.abc_cab_background_top_mtrl_alpha, f.e.abc_text_cursor_material, f.e.abc_text_select_handle_left_mtrl, f.e.abc_text_select_handle_middle_mtrl, f.e.abc_text_select_handle_right_mtrl};
        this.f1485e = new int[]{f.e.abc_popup_background_mtrl_mult, f.e.abc_cab_background_internal_bg, f.e.abc_menu_hardkey_panel_mtrl_mult};
        this.f1486f = new int[]{f.e.abc_tab_indicator_material, f.e.abc_textfield_search_material};
        this.f1487g = new int[]{f.e.abc_btn_check_material, f.e.abc_btn_radio_material, f.e.abc_btn_check_material_anim, f.e.abc_btn_radio_material_anim};
    }

    public y(MotionLayout motionLayout) {
        this.f1481a = 1;
        this.f1483c = new ArrayList();
        this.f1485e = "ViewTransitionController";
        this.f1487g = new ArrayList();
        this.f1482b = motionLayout;
    }

    public y(s5.h0 h0Var, String str, s5.d0 d0Var, s5.v0 v0Var, Map map) {
        this.f1481a = 2;
        i5.a.g("method", str);
        this.f1482b = h0Var;
        this.f1483c = str;
        this.f1484d = d0Var;
        this.f1485e = v0Var;
        this.f1486f = map;
    }

    public static boolean a(int i7, int[] iArr) {
        for (int i8 : iArr) {
            if (i8 == i7) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList b(Context context, int i7) {
        int c7 = l3.c(context, f.a.colorControlHighlight);
        return new ColorStateList(new int[][]{l3.f1317b, l3.f1319d, l3.f1318c, l3.f1321f}, new int[]{l3.b(context, f.a.colorButtonNormal), i0.a.c(c7, i7), i0.a.c(c7, i7), i7});
    }

    public static LayerDrawable c(l2 l2Var, Context context, int i7) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i7);
        Drawable e7 = l2Var.e(context, f.e.abc_star_black_48dp);
        Drawable e8 = l2Var.e(context, f.e.abc_star_half_black_48dp);
        if ((e7 instanceof BitmapDrawable) && e7.getIntrinsicWidth() == dimensionPixelSize && e7.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) e7;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            e7.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            e7.draw(canvas);
            bitmapDrawable = new BitmapDrawable(createBitmap);
            bitmapDrawable2 = new BitmapDrawable(createBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((e8 instanceof BitmapDrawable) && e8.getIntrinsicWidth() == dimensionPixelSize && e8.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) e8;
        } else {
            Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            e8.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            e8.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(createBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        return layerDrawable;
    }

    public static void g(Drawable drawable, int i7, PorterDuff.Mode mode) {
        int[] iArr = m1.f1350a;
        Drawable mutate = drawable.mutate();
        if (mode == null) {
            mode = z.f1492b;
        }
        mutate.setColorFilter(z.c(i7, mode));
    }

    public final ColorStateList d(Context context, int i7) {
        if (i7 == f.e.abc_edit_text_material) {
            return g0.g.b(context, f.c.abc_tint_edittext);
        }
        if (i7 == f.e.abc_switch_track_mtrl_alpha) {
            return g0.g.b(context, f.c.abc_tint_switch_track);
        }
        if (i7 != f.e.abc_switch_thumb_material) {
            if (i7 == f.e.abc_btn_default_mtrl_shape) {
                return b(context, l3.c(context, f.a.colorButtonNormal));
            }
            if (i7 == f.e.abc_btn_borderless_material) {
                return b(context, 0);
            }
            if (i7 == f.e.abc_btn_colored_material) {
                return b(context, l3.c(context, f.a.colorAccent));
            }
            if (i7 == f.e.abc_spinner_mtrl_am_alpha || i7 == f.e.abc_spinner_textfield_background_material) {
                return g0.g.b(context, f.c.abc_tint_spinner);
            }
            if (a(i7, (int[]) this.f1483c)) {
                return l3.d(context, f.a.colorControlNormal);
            }
            if (a(i7, (int[]) this.f1486f)) {
                return g0.g.b(context, f.c.abc_tint_default);
            }
            if (a(i7, (int[]) this.f1487g)) {
                return g0.g.b(context, f.c.abc_tint_btn_checkable);
            }
            if (i7 == f.e.abc_seekbar_thumb_material) {
                return g0.g.b(context, f.c.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList d7 = l3.d(context, f.a.colorSwitchThumbNormal);
        if (d7 == null || !d7.isStateful()) {
            iArr[0] = l3.f1317b;
            iArr2[0] = l3.b(context, f.a.colorSwitchThumbNormal);
            iArr[1] = l3.f1320e;
            iArr2[1] = l3.c(context, f.a.colorControlActivated);
            iArr[2] = l3.f1321f;
            iArr2[2] = l3.c(context, f.a.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = l3.f1317b;
            iArr[0] = iArr3;
            iArr2[0] = d7.getColorForState(iArr3, 0);
            iArr[1] = l3.f1320e;
            iArr2[1] = l3.c(context, f.a.colorControlActivated);
            iArr[2] = l3.f1321f;
            iArr2[2] = d7.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public final String e(String str) {
        return ((s5.d0) this.f1484d).a(str);
    }

    public final s5.r0 f() {
        LinkedHashMap linkedHashMap;
        s5.r0 r0Var = new s5.r0();
        r0Var.f8890e = new LinkedHashMap();
        r0Var.f8886a = (s5.h0) this.f1482b;
        r0Var.f8887b = (String) this.f1483c;
        r0Var.f8889d = (s5.v0) this.f1485e;
        if (((Map) this.f1486f).isEmpty()) {
            linkedHashMap = new LinkedHashMap();
        } else {
            Map map = (Map) this.f1486f;
            i5.a.g("<this>", map);
            linkedHashMap = new LinkedHashMap(map);
        }
        r0Var.f8890e = linkedHashMap;
        r0Var.f8888c = ((s5.d0) this.f1484d).c();
        return r0Var;
    }

    public final String toString() {
        switch (this.f1481a) {
            case 2:
                StringBuilder sb = new StringBuilder("Request{method=");
                sb.append((String) this.f1483c);
                sb.append(", url=");
                sb.append((s5.h0) this.f1482b);
                if (((s5.d0) this.f1484d).size() != 0) {
                    sb.append(", headers=[");
                    Iterator it = ((s5.d0) this.f1484d).iterator();
                    int i7 = 0;
                    while (true) {
                        c5.d dVar = (c5.d) it;
                        if (dVar.hasNext()) {
                            Object next = dVar.next();
                            int i8 = i7 + 1;
                            if (i7 < 0) {
                                throw new ArithmeticException("Index overflow has happened.");
                            }
                            b5.a aVar = (b5.a) next;
                            String str = (String) aVar.f2975a;
                            String str2 = (String) aVar.f2976b;
                            if (i7 > 0) {
                                sb.append(", ");
                            }
                            sb.append(str);
                            sb.append(':');
                            sb.append(str2);
                            i7 = i8;
                        } else {
                            sb.append(']');
                        }
                    }
                }
                if (!((Map) this.f1486f).isEmpty()) {
                    sb.append(", tags=");
                    sb.append((Map) this.f1486f);
                }
                sb.append('}');
                String sb2 = sb.toString();
                i5.a.f("StringBuilder().apply(builderAction).toString()", sb2);
                return sb2;
            default:
                return super.toString();
        }
    }
}
