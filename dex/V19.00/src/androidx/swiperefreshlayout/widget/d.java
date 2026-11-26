package androidx.swiperefreshlayout.widget;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public final RectF f2760a = new RectF();

    /* renamed from: b, reason: collision with root package name */
    public final Paint f2761b;

    /* renamed from: c, reason: collision with root package name */
    public final Paint f2762c;

    /* renamed from: d, reason: collision with root package name */
    public final Paint f2763d;

    /* renamed from: e, reason: collision with root package name */
    public float f2764e;

    /* renamed from: f, reason: collision with root package name */
    public float f2765f;

    /* renamed from: g, reason: collision with root package name */
    public float f2766g;

    /* renamed from: h, reason: collision with root package name */
    public float f2767h;

    /* renamed from: i, reason: collision with root package name */
    public int[] f2768i;

    /* renamed from: j, reason: collision with root package name */
    public int f2769j;

    /* renamed from: k, reason: collision with root package name */
    public float f2770k;

    /* renamed from: l, reason: collision with root package name */
    public float f2771l;

    /* renamed from: m, reason: collision with root package name */
    public float f2772m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f2773n;

    /* renamed from: o, reason: collision with root package name */
    public Path f2774o;

    /* renamed from: p, reason: collision with root package name */
    public float f2775p;

    /* renamed from: q, reason: collision with root package name */
    public float f2776q;

    /* renamed from: r, reason: collision with root package name */
    public int f2777r;

    /* renamed from: s, reason: collision with root package name */
    public int f2778s;

    /* renamed from: t, reason: collision with root package name */
    public int f2779t;

    /* renamed from: u, reason: collision with root package name */
    public int f2780u;

    public d() {
        Paint paint = new Paint();
        this.f2761b = paint;
        Paint paint2 = new Paint();
        this.f2762c = paint2;
        Paint paint3 = new Paint();
        this.f2763d = paint3;
        this.f2764e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f2765f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f2766g = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f2767h = 5.0f;
        this.f2775p = 1.0f;
        this.f2779t = 255;
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint3.setColor(0);
    }

    public final void a(int i7) {
        this.f2769j = i7;
        this.f2780u = this.f2768i[i7];
    }
}
