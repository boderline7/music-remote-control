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
import androidx.appcompat.widget.AppCompatImageButton;
import com.yalantis.ucrop.view.CropImageView;
import l6.f;
import z.a;
import z.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ImageFilterButton extends AppCompatImageButton {

    /* renamed from: d, reason: collision with root package name */
    public final c f1593d;

    /* renamed from: e, reason: collision with root package name */
    public float f1594e;

    /* renamed from: f, reason: collision with root package name */
    public float f1595f;

    /* renamed from: g, reason: collision with root package name */
    public float f1596g;

    /* renamed from: h, reason: collision with root package name */
    public Path f1597h;

    /* renamed from: i, reason: collision with root package name */
    public a f1598i;

    /* renamed from: j, reason: collision with root package name */
    public RectF f1599j;

    /* renamed from: k, reason: collision with root package name */
    public final Drawable[] f1600k;

    /* renamed from: l, reason: collision with root package name */
    public LayerDrawable f1601l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1602m;

    /* renamed from: n, reason: collision with root package name */
    public Drawable f1603n;

    /* renamed from: o, reason: collision with root package name */
    public Drawable f1604o;

    /* renamed from: p, reason: collision with root package name */
    public float f1605p;

    /* renamed from: q, reason: collision with root package name */
    public float f1606q;

    /* renamed from: r, reason: collision with root package name */
    public float f1607r;

    /* renamed from: s, reason: collision with root package name */
    public float f1608s;

    public ImageFilterButton(Context context) {
        super(context);
        this.f1593d = new c();
        this.f1594e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1595f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1596g = Float.NaN;
        this.f1600k = new Drawable[2];
        this.f1602m = true;
        this.f1603n = null;
        this.f1604o = null;
        this.f1605p = Float.NaN;
        this.f1606q = Float.NaN;
        this.f1607r = Float.NaN;
        this.f1608s = Float.NaN;
        a(null);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1593d = new c();
        this.f1594e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1595f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1596g = Float.NaN;
        this.f1600k = new Drawable[2];
        this.f1602m = true;
        this.f1603n = null;
        this.f1604o = null;
        this.f1605p = Float.NaN;
        this.f1606q = Float.NaN;
        this.f1607r = Float.NaN;
        this.f1608s = Float.NaN;
        a(attributeSet);
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1593d = new c();
        this.f1594e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1595f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1596g = Float.NaN;
        this.f1600k = new Drawable[2];
        this.f1602m = true;
        this.f1603n = null;
        this.f1604o = null;
        this.f1605p = Float.NaN;
        this.f1606q = Float.NaN;
        this.f1607r = Float.NaN;
        this.f1608s = Float.NaN;
        a(attributeSet);
    }

    private void setOverlay(boolean z6) {
        this.f1602m = z6;
    }

    public final void a(AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            this.f1603n = obtainStyledAttributes.getDrawable(t.ImageFilterView_altSrc);
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ImageFilterView_crossfade) {
                    this.f1594e = obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO);
                } else if (index == t.ImageFilterView_warmth) {
                    setWarmth(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_saturation) {
                    setSaturation(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_contrast) {
                    setContrast(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_round) {
                    setRound(obtainStyledAttributes.getDimension(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_roundPercent) {
                    setRoundPercent(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_overlay) {
                    setOverlay(obtainStyledAttributes.getBoolean(index, this.f1602m));
                } else if (index == t.ImageFilterView_imagePanX) {
                    setImagePanX(obtainStyledAttributes.getFloat(index, this.f1605p));
                } else if (index == t.ImageFilterView_imagePanY) {
                    setImagePanY(obtainStyledAttributes.getFloat(index, this.f1606q));
                } else if (index == t.ImageFilterView_imageRotate) {
                    setImageRotate(obtainStyledAttributes.getFloat(index, this.f1608s));
                } else if (index == t.ImageFilterView_imageZoom) {
                    setImageZoom(obtainStyledAttributes.getFloat(index, this.f1607r));
                }
            }
            obtainStyledAttributes.recycle();
            Drawable drawable = getDrawable();
            this.f1604o = drawable;
            Drawable drawable2 = this.f1603n;
            Drawable[] drawableArr = this.f1600k;
            if (drawable2 == null || drawable == null) {
                Drawable drawable3 = getDrawable();
                this.f1604o = drawable3;
                if (drawable3 != null) {
                    Drawable mutate = drawable3.mutate();
                    this.f1604o = mutate;
                    drawableArr[0] = mutate;
                    return;
                }
                return;
            }
            Drawable mutate2 = getDrawable().mutate();
            this.f1604o = mutate2;
            drawableArr[0] = mutate2;
            drawableArr[1] = this.f1603n.mutate();
            LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
            this.f1601l = layerDrawable;
            layerDrawable.getDrawable(1).setAlpha((int) (this.f1594e * 255.0f));
            if (!this.f1602m) {
                this.f1601l.getDrawable(0).setAlpha((int) ((1.0f - this.f1594e) * 255.0f));
            }
            super.setImageDrawable(this.f1601l);
        }
    }

    public final void b() {
        if (Float.isNaN(this.f1605p) && Float.isNaN(this.f1606q) && Float.isNaN(this.f1607r) && Float.isNaN(this.f1608s)) {
            return;
        }
        boolean isNaN = Float.isNaN(this.f1605p);
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float f8 = isNaN ? 0.0f : this.f1605p;
        float f9 = Float.isNaN(this.f1606q) ? 0.0f : this.f1606q;
        float f10 = Float.isNaN(this.f1607r) ? 1.0f : this.f1607r;
        if (!Float.isNaN(this.f1608s)) {
            f7 = this.f1608s;
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

    public final void c() {
        if (Float.isNaN(this.f1605p) && Float.isNaN(this.f1606q) && Float.isNaN(this.f1607r) && Float.isNaN(this.f1608s)) {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            b();
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public float getContrast() {
        return this.f1593d.f10238f;
    }

    public float getCrossfade() {
        return this.f1594e;
    }

    public float getImagePanX() {
        return this.f1605p;
    }

    public float getImagePanY() {
        return this.f1606q;
    }

    public float getImageRotate() {
        return this.f1608s;
    }

    public float getImageZoom() {
        return this.f1607r;
    }

    public float getRound() {
        return this.f1596g;
    }

    public float getRoundPercent() {
        return this.f1595f;
    }

    public float getSaturation() {
        return this.f1593d.f10237e;
    }

    public float getWarmth() {
        return this.f1593d.f10239g;
    }

    @Override // android.view.View
    public final void layout(int i7, int i8, int i9, int i10) {
        super.layout(i7, i8, i9, i10);
        b();
    }

    public void setAltImageResource(int i7) {
        Drawable mutate = f.A(getContext(), i7).mutate();
        this.f1603n = mutate;
        Drawable drawable = this.f1604o;
        Drawable[] drawableArr = this.f1600k;
        drawableArr[0] = drawable;
        drawableArr[1] = mutate;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1601l = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1594e);
    }

    public void setBrightness(float f7) {
        c cVar = this.f1593d;
        cVar.f10236d = f7;
        cVar.a(this);
    }

    public void setContrast(float f7) {
        c cVar = this.f1593d;
        cVar.f10238f = f7;
        cVar.a(this);
    }

    public void setCrossfade(float f7) {
        this.f1594e = f7;
        if (this.f1600k != null) {
            if (!this.f1602m) {
                this.f1601l.getDrawable(0).setAlpha((int) ((1.0f - this.f1594e) * 255.0f));
            }
            this.f1601l.getDrawable(1).setAlpha((int) (this.f1594e * 255.0f));
            super.setImageDrawable(this.f1601l);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (this.f1603n == null || drawable == null) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable mutate = drawable.mutate();
        this.f1604o = mutate;
        Drawable[] drawableArr = this.f1600k;
        drawableArr[0] = mutate;
        drawableArr[1] = this.f1603n;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1601l = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1594e);
    }

    public void setImagePanX(float f7) {
        this.f1605p = f7;
        c();
    }

    public void setImagePanY(float f7) {
        this.f1606q = f7;
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageResource(int i7) {
        if (this.f1603n == null) {
            super.setImageResource(i7);
            return;
        }
        Drawable mutate = f.A(getContext(), i7).mutate();
        this.f1604o = mutate;
        Drawable[] drawableArr = this.f1600k;
        drawableArr[0] = mutate;
        drawableArr[1] = this.f1603n;
        LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
        this.f1601l = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.f1594e);
    }

    public void setImageRotate(float f7) {
        this.f1608s = f7;
        c();
    }

    public void setImageZoom(float f7) {
        this.f1607r = f7;
        c();
    }

    public void setRound(float f7) {
        if (Float.isNaN(f7)) {
            this.f1596g = f7;
            float f8 = this.f1595f;
            this.f1595f = -1.0f;
            setRoundPercent(f8);
            return;
        }
        boolean z6 = this.f1596g != f7;
        this.f1596g = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1597h == null) {
                this.f1597h = new Path();
            }
            if (this.f1599j == null) {
                this.f1599j = new RectF();
            }
            if (this.f1598i == null) {
                a aVar = new a(this, 1);
                this.f1598i = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            this.f1599j.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, getWidth(), getHeight());
            this.f1597h.reset();
            Path path = this.f1597h;
            RectF rectF = this.f1599j;
            float f9 = this.f1596g;
            path.addRoundRect(rectF, f9, f9, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f7) {
        boolean z6 = this.f1595f != f7;
        this.f1595f = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1597h == null) {
                this.f1597h = new Path();
            }
            if (this.f1599j == null) {
                this.f1599j = new RectF();
            }
            if (this.f1598i == null) {
                a aVar = new a(this, 0);
                this.f1598i = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.f1595f) / 2.0f;
            this.f1599j.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height);
            this.f1597h.reset();
            this.f1597h.addRoundRect(this.f1599j, min, min, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setSaturation(float f7) {
        c cVar = this.f1593d;
        cVar.f10237e = f7;
        cVar.a(this);
    }

    public void setWarmth(float f7) {
        c cVar = this.f1593d;
        cVar.f10239g = f7;
        cVar.a(this);
    }
}
