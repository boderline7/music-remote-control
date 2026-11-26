package com.google.android.material.internal;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    public CharSequence f3908a;

    /* renamed from: b, reason: collision with root package name */
    public final TextPaint f3909b;

    /* renamed from: c, reason: collision with root package name */
    public final int f3910c;

    /* renamed from: d, reason: collision with root package name */
    public int f3911d;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3918k;

    /* renamed from: e, reason: collision with root package name */
    public Layout.Alignment f3912e = Layout.Alignment.ALIGN_NORMAL;

    /* renamed from: f, reason: collision with root package name */
    public int f3913f = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* renamed from: g, reason: collision with root package name */
    public float f3914g = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: h, reason: collision with root package name */
    public float f3915h = 1.0f;

    /* renamed from: i, reason: collision with root package name */
    public int f3916i = 1;

    /* renamed from: j, reason: collision with root package name */
    public boolean f3917j = true;

    /* renamed from: l, reason: collision with root package name */
    public TextUtils.TruncateAt f3919l = null;

    public z(CharSequence charSequence, TextPaint textPaint, int i7) {
        this.f3908a = charSequence;
        this.f3909b = textPaint;
        this.f3910c = i7;
        this.f3911d = charSequence.length();
    }

    public final StaticLayout a() {
        if (this.f3908a == null) {
            this.f3908a = s5.h0.FRAGMENT_ENCODE_SET;
        }
        int max = Math.max(0, this.f3910c);
        CharSequence charSequence = this.f3908a;
        int i7 = this.f3913f;
        TextPaint textPaint = this.f3909b;
        if (i7 == 1) {
            charSequence = TextUtils.ellipsize(charSequence, textPaint, max, this.f3919l);
        }
        int min = Math.min(charSequence.length(), this.f3911d);
        this.f3911d = min;
        if (this.f3918k && this.f3913f == 1) {
            this.f3912e = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, min, textPaint, max);
        obtain.setAlignment(this.f3912e);
        obtain.setIncludePad(this.f3917j);
        obtain.setTextDirection(this.f3918k ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f3919l;
        if (truncateAt != null) {
            obtain.setEllipsize(truncateAt);
        }
        obtain.setMaxLines(this.f3913f);
        float f7 = this.f3914g;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO || this.f3915h != 1.0f) {
            obtain.setLineSpacing(f7, this.f3915h);
        }
        if (this.f3913f > 1) {
            obtain.setHyphenationFrequency(this.f3916i);
        }
        return obtain.build();
    }
}
