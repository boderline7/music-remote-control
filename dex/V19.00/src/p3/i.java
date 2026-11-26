package p3;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class i extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    public p f8241a;

    /* renamed from: b, reason: collision with root package name */
    public e3.a f8242b;

    /* renamed from: c, reason: collision with root package name */
    public ColorStateList f8243c;

    /* renamed from: d, reason: collision with root package name */
    public ColorStateList f8244d;

    /* renamed from: e, reason: collision with root package name */
    public final ColorStateList f8245e;

    /* renamed from: f, reason: collision with root package name */
    public ColorStateList f8246f;

    /* renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f8247g;

    /* renamed from: h, reason: collision with root package name */
    public Rect f8248h;

    /* renamed from: i, reason: collision with root package name */
    public final float f8249i;

    /* renamed from: j, reason: collision with root package name */
    public float f8250j;

    /* renamed from: k, reason: collision with root package name */
    public float f8251k;

    /* renamed from: l, reason: collision with root package name */
    public int f8252l;

    /* renamed from: m, reason: collision with root package name */
    public float f8253m;

    /* renamed from: n, reason: collision with root package name */
    public float f8254n;

    /* renamed from: o, reason: collision with root package name */
    public final float f8255o;

    /* renamed from: p, reason: collision with root package name */
    public int f8256p;

    /* renamed from: q, reason: collision with root package name */
    public int f8257q;

    /* renamed from: r, reason: collision with root package name */
    public int f8258r;

    /* renamed from: s, reason: collision with root package name */
    public final int f8259s;

    /* renamed from: t, reason: collision with root package name */
    public boolean f8260t;

    /* renamed from: u, reason: collision with root package name */
    public Paint.Style f8261u;

    public i(i iVar) {
        this.f8243c = null;
        this.f8244d = null;
        this.f8245e = null;
        this.f8246f = null;
        this.f8247g = PorterDuff.Mode.SRC_IN;
        this.f8248h = null;
        this.f8249i = 1.0f;
        this.f8250j = 1.0f;
        this.f8252l = 255;
        this.f8253m = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8254n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8255o = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8256p = 0;
        this.f8257q = 0;
        this.f8258r = 0;
        this.f8259s = 0;
        this.f8260t = false;
        this.f8261u = Paint.Style.FILL_AND_STROKE;
        this.f8241a = iVar.f8241a;
        this.f8242b = iVar.f8242b;
        this.f8251k = iVar.f8251k;
        this.f8243c = iVar.f8243c;
        this.f8244d = iVar.f8244d;
        this.f8247g = iVar.f8247g;
        this.f8246f = iVar.f8246f;
        this.f8252l = iVar.f8252l;
        this.f8249i = iVar.f8249i;
        this.f8258r = iVar.f8258r;
        this.f8256p = iVar.f8256p;
        this.f8260t = iVar.f8260t;
        this.f8250j = iVar.f8250j;
        this.f8253m = iVar.f8253m;
        this.f8254n = iVar.f8254n;
        this.f8255o = iVar.f8255o;
        this.f8257q = iVar.f8257q;
        this.f8259s = iVar.f8259s;
        this.f8245e = iVar.f8245e;
        this.f8261u = iVar.f8261u;
        if (iVar.f8248h != null) {
            this.f8248h = new Rect(iVar.f8248h);
        }
    }

    public i(p pVar) {
        this.f8243c = null;
        this.f8244d = null;
        this.f8245e = null;
        this.f8246f = null;
        this.f8247g = PorterDuff.Mode.SRC_IN;
        this.f8248h = null;
        this.f8249i = 1.0f;
        this.f8250j = 1.0f;
        this.f8252l = 255;
        this.f8253m = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8254n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8255o = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8256p = 0;
        this.f8257q = 0;
        this.f8258r = 0;
        this.f8259s = 0;
        this.f8260t = false;
        this.f8261u = Paint.Style.FILL_AND_STROKE;
        this.f8241a = pVar;
        this.f8242b = null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        j jVar = new j(this);
        jVar.f8267e = true;
        return jVar;
    }
}
