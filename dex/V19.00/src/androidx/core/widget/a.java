package androidx.core.widget;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public int f1793a;

    /* renamed from: b, reason: collision with root package name */
    public int f1794b;

    /* renamed from: c, reason: collision with root package name */
    public float f1795c;

    /* renamed from: d, reason: collision with root package name */
    public float f1796d;

    /* renamed from: e, reason: collision with root package name */
    public long f1797e;

    /* renamed from: f, reason: collision with root package name */
    public long f1798f;

    /* renamed from: g, reason: collision with root package name */
    public long f1799g;

    /* renamed from: h, reason: collision with root package name */
    public float f1800h;

    /* renamed from: i, reason: collision with root package name */
    public int f1801i;

    public final float a(long j7) {
        if (j7 < this.f1797e) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        long j8 = this.f1799g;
        if (j8 < 0 || j7 < j8) {
            return b.b((j7 - r0) / this.f1793a, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f) * 0.5f;
        }
        float f7 = this.f1800h;
        return (b.b((j7 - j8) / this.f1801i, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f) * f7) + (1.0f - f7);
    }
}
