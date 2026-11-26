package androidx.cardview.widget;

import a6.k0;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.yalantis.ucrop.view.CropImageView;
import i6.i;
import o.a;
import o.b;
import o.c;
import o.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CardView extends FrameLayout {

    /* renamed from: f, reason: collision with root package name */
    public static final int[] f1496f = {R.attr.colorBackground};

    /* renamed from: g, reason: collision with root package name */
    public static final k0 f1497g = new k0();

    /* renamed from: a, reason: collision with root package name */
    public boolean f1498a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f1499b;

    /* renamed from: c, reason: collision with root package name */
    public final Rect f1500c;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f1501d;

    /* renamed from: e, reason: collision with root package name */
    public final i f1502e;

    public CardView(Context context) {
        this(context, null);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.cardViewStyle);
    }

    public CardView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        ColorStateList valueOf;
        Rect rect = new Rect();
        this.f1500c = rect;
        this.f1501d = new Rect();
        i iVar = new i(this);
        this.f1502e = iVar;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.CardView, i7, c.CardView);
        if (obtainStyledAttributes.hasValue(d.CardView_cardBackgroundColor)) {
            valueOf = obtainStyledAttributes.getColorStateList(d.CardView_cardBackgroundColor);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(f1496f);
            int color = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            valueOf = ColorStateList.valueOf(fArr[2] > 0.5f ? getResources().getColor(b.cardview_light_background) : getResources().getColor(b.cardview_dark_background));
        }
        float dimension = obtainStyledAttributes.getDimension(d.CardView_cardCornerRadius, CropImageView.DEFAULT_ASPECT_RATIO);
        float dimension2 = obtainStyledAttributes.getDimension(d.CardView_cardElevation, CropImageView.DEFAULT_ASPECT_RATIO);
        float dimension3 = obtainStyledAttributes.getDimension(d.CardView_cardMaxElevation, CropImageView.DEFAULT_ASPECT_RATIO);
        this.f1498a = obtainStyledAttributes.getBoolean(d.CardView_cardUseCompatPadding, false);
        this.f1499b = obtainStyledAttributes.getBoolean(d.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(d.CardView_contentPadding, 0);
        rect.left = obtainStyledAttributes.getDimensionPixelSize(d.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = obtainStyledAttributes.getDimensionPixelSize(d.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = obtainStyledAttributes.getDimensionPixelSize(d.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = obtainStyledAttributes.getDimensionPixelSize(d.CardView_contentPaddingBottom, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        obtainStyledAttributes.getDimensionPixelSize(d.CardView_android_minWidth, 0);
        obtainStyledAttributes.getDimensionPixelSize(d.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        k0 k0Var = f1497g;
        p.a aVar = new p.a(dimension, valueOf);
        iVar.f6848b = aVar;
        ((CardView) iVar.f6849c).setBackgroundDrawable(aVar);
        CardView cardView = (CardView) iVar.f6849c;
        cardView.setClipToOutline(true);
        cardView.setElevation(dimension2);
        k0Var.e(dimension3, iVar);
    }

    public ColorStateList getCardBackgroundColor() {
        return ((p.a) ((Drawable) this.f1502e.f6848b)).f8086h;
    }

    public float getCardElevation() {
        return ((CardView) this.f1502e.f6849c).getElevation();
    }

    public int getContentPaddingBottom() {
        return this.f1500c.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f1500c.left;
    }

    public int getContentPaddingRight() {
        return this.f1500c.right;
    }

    public int getContentPaddingTop() {
        return this.f1500c.top;
    }

    public float getMaxCardElevation() {
        return ((p.a) ((Drawable) this.f1502e.f6848b)).f8083e;
    }

    public boolean getPreventCornerOverlap() {
        return this.f1499b;
    }

    public float getRadius() {
        return ((p.a) ((Drawable) this.f1502e.f6848b)).f8079a;
    }

    public boolean getUseCompatPadding() {
        return this.f1498a;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
    }

    public void setCardBackgroundColor(int i7) {
        ColorStateList valueOf = ColorStateList.valueOf(i7);
        p.a aVar = (p.a) ((Drawable) this.f1502e.f6848b);
        if (valueOf == null) {
            aVar.getClass();
            valueOf = ColorStateList.valueOf(0);
        }
        aVar.f8086h = valueOf;
        aVar.f8080b.setColor(valueOf.getColorForState(aVar.getState(), aVar.f8086h.getDefaultColor()));
        aVar.invalidateSelf();
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        p.a aVar = (p.a) ((Drawable) this.f1502e.f6848b);
        if (colorStateList == null) {
            aVar.getClass();
            colorStateList = ColorStateList.valueOf(0);
        }
        aVar.f8086h = colorStateList;
        aVar.f8080b.setColor(colorStateList.getColorForState(aVar.getState(), aVar.f8086h.getDefaultColor()));
        aVar.invalidateSelf();
    }

    public void setCardElevation(float f7) {
        ((CardView) this.f1502e.f6849c).setElevation(f7);
    }

    public void setContentPadding(int i7, int i8, int i9, int i10) {
        this.f1500c.set(i7, i8, i9, i10);
        f1497g.g(this.f1502e);
    }

    public void setMaxCardElevation(float f7) {
        f1497g.e(f7, this.f1502e);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i7) {
        super.setMinimumHeight(i7);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i7) {
        super.setMinimumWidth(i7);
    }

    @Override // android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i7, int i8, int i9, int i10) {
    }

    public void setPreventCornerOverlap(boolean z6) {
        if (z6 != this.f1499b) {
            this.f1499b = z6;
            k0 k0Var = f1497g;
            i iVar = this.f1502e;
            k0Var.e(((p.a) ((Drawable) iVar.f6848b)).f8083e, iVar);
        }
    }

    public void setRadius(float f7) {
        p.a aVar = (p.a) ((Drawable) this.f1502e.f6848b);
        if (f7 == aVar.f8079a) {
            return;
        }
        aVar.f8079a = f7;
        aVar.b(null);
        aVar.invalidateSelf();
    }

    public void setUseCompatPadding(boolean z6) {
        if (this.f1498a != z6) {
            this.f1498a = z6;
            k0 k0Var = f1497g;
            i iVar = this.f1502e;
            k0Var.e(((p.a) ((Drawable) iVar.f6848b)).f8083e, iVar);
        }
    }
}
