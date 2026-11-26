package o3;

import android.graphics.Paint;
import android.graphics.Path;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f7907i = new int[3];

    /* renamed from: j, reason: collision with root package name */
    public static final float[] f7908j = {CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 1.0f};

    /* renamed from: k, reason: collision with root package name */
    public static final int[] f7909k = new int[4];

    /* renamed from: l, reason: collision with root package name */
    public static final float[] f7910l = {CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 0.5f, 1.0f};

    /* renamed from: a, reason: collision with root package name */
    public final Paint f7911a;

    /* renamed from: b, reason: collision with root package name */
    public final Paint f7912b;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f7913c;

    /* renamed from: d, reason: collision with root package name */
    public int f7914d;

    /* renamed from: e, reason: collision with root package name */
    public int f7915e;

    /* renamed from: f, reason: collision with root package name */
    public int f7916f;

    /* renamed from: g, reason: collision with root package name */
    public final Path f7917g = new Path();

    /* renamed from: h, reason: collision with root package name */
    public final Paint f7918h;

    public a() {
        Paint paint = new Paint();
        this.f7918h = paint;
        this.f7911a = new Paint();
        a(d1.MEASURED_STATE_MASK);
        paint.setColor(0);
        Paint paint2 = new Paint(4);
        this.f7912b = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f7913c = new Paint(paint2);
    }

    public final void a(int i7) {
        this.f7914d = i0.a.e(i7, 68);
        this.f7915e = i0.a.e(i7, 20);
        this.f7916f = i0.a.e(i7, 0);
        this.f7911a.setColor(this.f7914d);
    }
}
