package p3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {
    public static final d PILL = new l(0.5f);

    /* renamed from: a, reason: collision with root package name */
    public l6.f f8301a = new m();

    /* renamed from: b, reason: collision with root package name */
    public l6.f f8302b = new m();

    /* renamed from: c, reason: collision with root package name */
    public l6.f f8303c = new m();

    /* renamed from: d, reason: collision with root package name */
    public l6.f f8304d = new m();

    /* renamed from: e, reason: collision with root package name */
    public d f8305e = new a(CropImageView.DEFAULT_ASPECT_RATIO);

    /* renamed from: f, reason: collision with root package name */
    public d f8306f = new a(CropImageView.DEFAULT_ASPECT_RATIO);

    /* renamed from: g, reason: collision with root package name */
    public d f8307g = new a(CropImageView.DEFAULT_ASPECT_RATIO);

    /* renamed from: h, reason: collision with root package name */
    public d f8308h = new a(CropImageView.DEFAULT_ASPECT_RATIO);

    /* renamed from: i, reason: collision with root package name */
    public f f8309i = l6.f.s();

    /* renamed from: j, reason: collision with root package name */
    public f f8310j = l6.f.s();

    /* renamed from: k, reason: collision with root package name */
    public f f8311k = l6.f.s();

    /* renamed from: l, reason: collision with root package name */
    public f f8312l = l6.f.s();

    public static n a(Context context, int i7, int i8) {
        return b(context, i7, i8, new a(0));
    }

    public static n b(Context context, int i7, int i8, d dVar) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i7);
        if (i8 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i8);
        }
        TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(q2.m.ShapeAppearance);
        try {
            int i9 = obtainStyledAttributes.getInt(q2.m.ShapeAppearance_cornerFamily, 0);
            int i10 = obtainStyledAttributes.getInt(q2.m.ShapeAppearance_cornerFamilyTopLeft, i9);
            int i11 = obtainStyledAttributes.getInt(q2.m.ShapeAppearance_cornerFamilyTopRight, i9);
            int i12 = obtainStyledAttributes.getInt(q2.m.ShapeAppearance_cornerFamilyBottomRight, i9);
            int i13 = obtainStyledAttributes.getInt(q2.m.ShapeAppearance_cornerFamilyBottomLeft, i9);
            d e7 = e(obtainStyledAttributes, q2.m.ShapeAppearance_cornerSize, dVar);
            d e8 = e(obtainStyledAttributes, q2.m.ShapeAppearance_cornerSizeTopLeft, e7);
            d e9 = e(obtainStyledAttributes, q2.m.ShapeAppearance_cornerSizeTopRight, e7);
            d e10 = e(obtainStyledAttributes, q2.m.ShapeAppearance_cornerSizeBottomRight, e7);
            d e11 = e(obtainStyledAttributes, q2.m.ShapeAppearance_cornerSizeBottomLeft, e7);
            n nVar = new n();
            l6.f r6 = l6.f.r(i10);
            nVar.f8289a = r6;
            n.b(r6);
            nVar.f8293e = e8;
            l6.f r7 = l6.f.r(i11);
            nVar.f8290b = r7;
            n.b(r7);
            nVar.f8294f = e9;
            l6.f r8 = l6.f.r(i12);
            nVar.f8291c = r8;
            n.b(r8);
            nVar.f8295g = e10;
            l6.f r9 = l6.f.r(i13);
            nVar.f8292d = r9;
            n.b(r9);
            nVar.f8296h = e11;
            return nVar;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static n c(Context context, AttributeSet attributeSet, int i7, int i8) {
        return d(context, attributeSet, i7, i8, new a(0));
    }

    public static n d(Context context, AttributeSet attributeSet, int i7, int i8, d dVar) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q2.m.MaterialShape, i7, i8);
        int resourceId = obtainStyledAttributes.getResourceId(q2.m.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(q2.m.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return b(context, resourceId, resourceId2, dVar);
    }

    public static d e(TypedArray typedArray, int i7, d dVar) {
        TypedValue peekValue = typedArray.peekValue(i7);
        if (peekValue == null) {
            return dVar;
        }
        int i8 = peekValue.type;
        return i8 == 5 ? new a(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics())) : i8 == 6 ? new l(peekValue.getFraction(1.0f, 1.0f)) : dVar;
    }

    public final boolean f(RectF rectF) {
        boolean z6 = this.f8312l.getClass().equals(f.class) && this.f8310j.getClass().equals(f.class) && this.f8309i.getClass().equals(f.class) && this.f8311k.getClass().equals(f.class);
        float a7 = this.f8305e.a(rectF);
        return z6 && ((this.f8306f.a(rectF) > a7 ? 1 : (this.f8306f.a(rectF) == a7 ? 0 : -1)) == 0 && (this.f8308h.a(rectF) > a7 ? 1 : (this.f8308h.a(rectF) == a7 ? 0 : -1)) == 0 && (this.f8307g.a(rectF) > a7 ? 1 : (this.f8307g.a(rectF) == a7 ? 0 : -1)) == 0) && ((this.f8302b instanceof m) && (this.f8301a instanceof m) && (this.f8303c instanceof m) && (this.f8304d instanceof m));
    }

    public final n g() {
        n nVar = new n();
        nVar.f8289a = new m();
        nVar.f8290b = new m();
        nVar.f8291c = new m();
        nVar.f8292d = new m();
        nVar.f8293e = new a(CropImageView.DEFAULT_ASPECT_RATIO);
        nVar.f8294f = new a(CropImageView.DEFAULT_ASPECT_RATIO);
        nVar.f8295g = new a(CropImageView.DEFAULT_ASPECT_RATIO);
        nVar.f8296h = new a(CropImageView.DEFAULT_ASPECT_RATIO);
        nVar.f8297i = l6.f.s();
        nVar.f8298j = l6.f.s();
        nVar.f8299k = l6.f.s();
        nVar.f8289a = this.f8301a;
        nVar.f8290b = this.f8302b;
        nVar.f8291c = this.f8303c;
        nVar.f8292d = this.f8304d;
        nVar.f8293e = this.f8305e;
        nVar.f8294f = this.f8306f;
        nVar.f8295g = this.f8307g;
        nVar.f8296h = this.f8308h;
        nVar.f8297i = this.f8309i;
        nVar.f8298j = this.f8310j;
        nVar.f8299k = this.f8311k;
        nVar.f8300l = this.f8312l;
        return nVar;
    }

    public final p h(o oVar) {
        n g7 = g();
        g7.f8293e = oVar.a(this.f8305e);
        g7.f8294f = oVar.a(this.f8306f);
        g7.f8296h = oVar.a(this.f8308h);
        g7.f8295g = oVar.a(this.f8307g);
        return g7.a();
    }
}
