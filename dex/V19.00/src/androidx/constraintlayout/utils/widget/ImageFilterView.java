package androidx.constraintlayout.utils.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;
import z.b;
import z.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ImageFilterView extends AppCompatImageView {

    /* renamed from: d, reason: collision with root package name */
    public final c f1609d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1610e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f1611f;

    /* renamed from: g, reason: collision with root package name */
    public Drawable f1612g;

    /* renamed from: h, reason: collision with root package name */
    public float f1613h;

    /* renamed from: i, reason: collision with root package name */
    public float f1614i;

    /* renamed from: j, reason: collision with root package name */
    public float f1615j;

    /* renamed from: k, reason: collision with root package name */
    public Path f1616k;

    /* renamed from: l, reason: collision with root package name */
    public b f1617l;

    /* renamed from: m, reason: collision with root package name */
    public RectF f1618m;

    /* renamed from: n, reason: collision with root package name */
    public final Drawable[] f1619n;

    /* renamed from: o, reason: collision with root package name */
    public LayerDrawable f1620o;

    /* renamed from: p, reason: collision with root package name */
    public float f1621p;

    /* renamed from: q, reason: collision with root package name */
    public float f1622q;

    /* renamed from: r, reason: collision with root package name */
    public float f1623r;

    /* renamed from: s, reason: collision with root package name */
    public float f1624s;

    public ImageFilterView(Context context) {
        super(context);
        this.f1609d = new c();
        this.f1610e = true;
        this.f1611f = null;
        this.f1612g = null;
        this.f1613h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1614i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1615j = Float.NaN;
        this.f1619n = new Drawable[2];
        this.f1621p = Float.NaN;
        this.f1622q = Float.NaN;
        this.f1623r = Float.NaN;
        this.f1624s = Float.NaN;
    }

    public ImageFilterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1609d = new c();
        this.f1610e = true;
        this.f1611f = null;
        this.f1612g = null;
        this.f1613h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1614i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1615j = Float.NaN;
        this.f1619n = new Drawable[2];
        this.f1621p = Float.NaN;
        this.f1622q = Float.NaN;
        this.f1623r = Float.NaN;
        this.f1624s = Float.NaN;
        c(attributeSet);
    }

    public ImageFilterView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1609d = new c();
        this.f1610e = true;
        this.f1611f = null;
        this.f1612g = null;
        this.f1613h = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1614i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1615j = Float.NaN;
        this.f1619n = new Drawable[2];
        this.f1621p = Float.NaN;
        this.f1622q = Float.NaN;
        this.f1623r = Float.NaN;
        this.f1624s = Float.NaN;
        c(attributeSet);
    }

    private void setOverlay(boolean z6) {
        this.f1610e = z6;
    }

    public final void c(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            this.f1611f = obtainStyledAttributes.getDrawable(t.ImageFilterView_altSrc);
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ImageFilterView_crossfade) {
                    this.f1613h = obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO);
                } else if (index == t.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_brightness) {
                    setBrightness(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_round) {
                    setRound(obtainStyledAttributes.getDimension(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_roundPercent) {
                    setRoundPercent(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.f1610e));
                } else if (index == t.ImageFilterView_imagePanX) {
                    setImagePanX(obtainStyledAttributes.getFloat(index, this.f1621p));
                } else if (index == t.ImageFilterView_imagePanY) {
                    setImagePanY(obtainStyledAttributes.getFloat(index, this.f1622q));
                } else if (index == t.ImageFilterView_imageRotate) {
                    setImageRotate(obtainStyledAttributes.getFloat(index, this.f1624s));
                } else if (index == t.ImageFilterView_imageZoom) {
                    setImageZoom(obtainStyledAttributes.getFloat(index, this.f1623r));
                }
            }
            obtainStyledAttributes.recycle();
            Drawable drawable = getDrawable();
            this.f1612g = drawable;
            Drawable drawable2 = this.f1611f;
            Drawable[] drawableArr = this.f1619n;
            if (drawable2 == null || drawable == null) {
                Drawable drawable3 = getDrawable();
                this.f1612g = drawable3;
                if (drawable3 != null) {
                    Drawable mutate = drawable3.mutate();
                    this.f1612g = mutate;
                    drawableArr[0] = mutate;
                    return;
                }
                return;
            }
            Drawable mutate2 = getDrawable().mutate();
            this.f1612g = mutate2;
            drawableArr[0] = mutate2;
            drawableArr[1] = this.f1611f.mutate();
            LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
            this.f1620o = layerDrawable;
            layerDrawable.getDrawable(1).setAlpha((int) (this.f1613h * 255.0f));
            if (!this.f1610e) {
                this.f1620o.getDrawable(0).setAlpha((int) ((1.0f - this.f1613h) * 255.0f));
            }
            super.setImageDrawable(this.f1620o);
        }
    }

    public final void d() {
        if (Float.isNaN(this.f1621p) && Float.isNaN(this.f1622q) && Float.isNaN(this.f1623r) && Float.isNaN(this.f1624s)) {
            return;
        }
        boolean isNaN = Float.isNaN(this.f1621p);
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float f8 = isNaN ? 0.0f : this.f1621p;
        float f9 = Float.isNaN(this.f1622q) ? 0.0f : this.f1622q;
        float f10 = Float.isNaN(this.f1623r) ? 1.0f : this.f1623r;
        if (!Float.isNaN(this.f1624s)) {
            f7 = this.f1624s;
        }
        Matrix matrix = new Matrix();
        matrix.reset();
        float intrinsicWidth = getDrawable().getIntrinsicWidth();
        float intrinsicHeight = getDrawable().getIntrinsicHeight();
        float width = getWidth();
        float height = getHeight();
        float f11 = f10 * (intrinsicWidth * height < intrinsicHeight * width ? width / intrinsicWidth : height / intrinsicHeight);
        matrix.postScale(f11, f11);
        float f12 = intrinsicWidth * f11;
        float f13 = f11 * intrinsicHeight;
        matrix.postTranslate(((((width - f12) * f8) + width) - f12) * 0.5f, ((((height - f13) * f9) + height) - f13) * 0.5f);
        matrix.postRotate(f7, width / 2.0f, height / 2.0f);
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public final void e() {
        if (Float.isNaN(this.f1621p) && Float.isNaN(this.f1622q) && Float.isNaN(this.f1623r) && Float.isNaN(this.f1624s)) {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            d();
        }
    }

    public float getBrightness() {
        return this.f1609d.f10236d;
    }

    public float getContrast() {
        return this.f1609d.f10238f;
    }

    public float getCrossfade() {
        return this.f1613h;
    }

    public float getImagePanX() {
        return this.f1621p;
    }

    public float getImagePanY() {
        return this.f1622q;
    }

    public float getImageRotate() {
        return this.f1624s;
    }

    public float getImageZoom() {
        return this.f1623r;
    }

    public float getRound() {
        return this.f1615j;
    }

    public float getRoundPercent() {
        return this.f1614i;
    }

    public float getSaturation() {
        return this.f1609d.f10237e;
    }

    public float getWarmth() {
        return this.f1609d.f10239g;
    }

    @Override // android.view.View
    public final void layout(int i7, int i8, int i9, int i10) {
        super.layout(i7, i8, i9, i10);
        d();
    }

    public void setAltImageResource(int i7) {
        Drawable mutate = f.A(getContext(), i7).mutate();
        this.f1611f = mutate;
        Drawable drawable = this.f1612g;
        Drawable[] drawableArr = this.f1619n;
        drawableArr[0] = drawable;
        drawableArr[1] = mutate;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1620o = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1613h);
    }

    public void setBrightness(float f7) {
        c cVar = this.f1609d;
        cVar.f10236d = f7;
        cVar.a(this);
    }

    public void setContrast(float f7) {
        c cVar = this.f1609d;
        cVar.f10238f = f7;
        cVar.a(this);
    }

    public void setCrossfade(float f7) {
        this.f1613h = f7;
        if (this.f1619n != null) {
            if (!this.f1610e) {
                this.f1620o.getDrawable(0).setAlpha((int) ((1.0f - this.f1613h) * 255.0f));
            }
            this.f1620o.getDrawable(1).setAlpha((int) (this.f1613h * 255.0f));
            super.setImageDrawable(this.f1620o);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.f1611f == null || drawable == null) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable mutate = drawable.mutate();
        this.f1612g = mutate;
        Drawable[] drawableArr = this.f1619n;
        drawableArr[0] = mutate;
        drawableArr[1] = this.f1611f;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1620o = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1613h);
    }

    public void setImagePanX(float f7) {
        this.f1621p = f7;
        e();
    }

    public void setImagePanY(float f7) {
        this.f1622q = f7;
        e();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i7) {
        if (this.f1611f == null) {
            super.setImageResource(i7);
            return;
        }
        Drawable mutate = f.A(getContext(), i7).mutate();
        this.f1612g = mutate;
        Drawable[] drawableArr = this.f1619n;
        drawableArr[0] = mutate;
        drawableArr[1] = this.f1611f;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1620o = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1613h);
    }

    public void setImageRotate(float f7) {
        this.f1624s = f7;
        e();
    }

    public void setImageZoom(float f7) {
        this.f1623r = f7;
        e();
    }

    public void setRound(float f7) {
        if (Float.isNaN(f7)) {
            this.f1615j = f7;
            float f8 = this.f1614i;
            this.f1614i = -1.0f;
            setRoundPercent(f8);
            return;
        }
        boolean z6 = this.f1615j != f7;
        this.f1615j = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1616k == null) {
                this.f1616k = new Path();
            }
            if (this.f1618m == null) {
                this.f1618m = new RectF();
            }
            if (this.f1617l == null) {
                b bVar = new b(this, 1);
                this.f1617l = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            this.f1618m.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, getWidth(), getHeight());
            this.f1616k.reset();
            Path path = this.f1616k;
            RectF rectF = this.f1618m;
            float f9 = this.f1615j;
            path.addRoundRect(rectF, f9, f9, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f7) {
        boolean z6 = this.f1614i != f7;
        this.f1614i = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1616k == null) {
                this.f1616k = new Path();
            }
            if (this.f1618m == null) {
                this.f1618m = new RectF();
            }
            if (this.f1617l == null) {
                b bVar = new b(this, 0);
                this.f1617l = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.f1614i) / 2.0f;
            this.f1618m.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height);
            this.f1616k.reset();
            this.f1616k.addRoundRect(this.f1618m, min, min, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f7) {
        c cVar = this.f1609d;
        cVar.f10237e = f7;
        cVar.a(this);
    }

    public void setWarmth(float f7) {
        c cVar = this.f1609d;
        cVar.f10239g = f7;
        cVar.a(this);
    }
}
