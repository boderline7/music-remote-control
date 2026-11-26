package com.navi.tool.amapflying.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import i6.e;
import i6.k;
import org.greenrobot.eventbus.ThreadMode;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LyricFloatingView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f4556a;

    /* renamed from: b, reason: collision with root package name */
    public final WindowManager f4557b;

    /* renamed from: c, reason: collision with root package name */
    public WindowManager.LayoutParams f4558c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f4559d;

    /* renamed from: e, reason: collision with root package name */
    public int f4560e;

    /* renamed from: f, reason: collision with root package name */
    public int f4561f;

    /* renamed from: g, reason: collision with root package name */
    public int f4562g;

    /* renamed from: h, reason: collision with root package name */
    public int f4563h;

    public LyricFloatingView(Context context, WindowManager windowManager) {
        super(context);
        this.f4559d = false;
        this.f4560e = 30;
        this.f4561f = 0;
        this.f4562g = 0;
        this.f4563h = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.f4557b = windowManager;
        new Handler(Looper.getMainLooper());
        TextView textView = new TextView(getContext());
        this.f4556a = textView;
        textView.setTextColor(-1);
        this.f4556a.setGravity(17);
        this.f4556a.setSingleLine(false);
        this.f4556a.setMaxLines(2);
        this.f4556a.setEllipsize(TextUtils.TruncateAt.END);
        this.f4556a.setShadowLayer(5.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, d1.MEASURED_STATE_MASK);
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(getContext());
        this.f4560e = appPrefs.getInt("lyric_font_size", 30);
        this.f4561f = appPrefs.getInt("lyric_position_x", 0);
        this.f4562g = appPrefs.getInt("lyric_position_y", 0);
        this.f4563h = appPrefs.getInt("lyric_width_dp", CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        this.f4556a.setTextColor(appPrefs.getInt("lyric_color", -1));
        this.f4556a.setGravity(appPrefs.getInt("lyric_gravity", 17));
        TextView textView2 = this.f4556a;
        if (textView2 != null) {
            textView2.setTextSize(2, this.f4560e);
        }
        addView(this.f4556a, new FrameLayout.LayoutParams(-1, -2));
        setBackgroundColor(0);
    }

    public final void a() {
        if (this.f4559d) {
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(Math.round(this.f4563h * getContext().getResources().getDisplayMetrics().density), -2, 2038, 792, -3);
        this.f4558c = layoutParams;
        layoutParams.gravity = 51;
        layoutParams.x = this.f4561f;
        layoutParams.y = this.f4562g;
        try {
            this.f4557b.addView(this, layoutParams);
            this.f4559d = true;
            e.b().j(this);
        } catch (Exception unused) {
        }
    }

    @k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdate(h4.k kVar) {
        TextView textView;
        WindowManager.LayoutParams layoutParams;
        int i7 = SharedPreferencesUtils.getAppPrefs(getContext()).getInt("desktop_music_source", 0);
        if (kVar == null || kVar.f6754b != i7 || (textView = this.f4556a) == null) {
            return;
        }
        textView.setText(kVar.f6753a);
        this.f4556a.requestLayout();
        this.f4556a.invalidate();
        requestLayout();
        invalidate();
        if (!this.f4559d || (layoutParams = this.f4558c) == null) {
            return;
        }
        layoutParams.height = -2;
        try {
            this.f4557b.updateViewLayout(this, layoutParams);
        } catch (Exception unused) {
        }
    }
}
