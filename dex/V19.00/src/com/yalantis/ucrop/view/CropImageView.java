package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Locale;
import n1.z;
import t4.k;
import u4.a;
import y4.b;
import y4.d;
import y4.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CropImageView extends TransformImageView {
    public static final float DEFAULT_ASPECT_RATIO = 0.0f;
    public static final int DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = 500;
    public static final int DEFAULT_MAX_BITMAP_SIZE = 0;
    public static final float DEFAULT_MAX_SCALE_MULTIPLIER = 10.0f;
    public static final float SOURCE_IMAGE_ASPECT_RATIO = 0.0f;
    public b A;
    public float B;
    public float C;
    public int D;
    public int E;
    public long F;

    /* renamed from: u, reason: collision with root package name */
    public final RectF f6169u;

    /* renamed from: v, reason: collision with root package name */
    public final Matrix f6170v;

    /* renamed from: w, reason: collision with root package name */
    public float f6171w;

    /* renamed from: x, reason: collision with root package name */
    public float f6172x;

    /* renamed from: y, reason: collision with root package name */
    public a f6173y;

    /* renamed from: z, reason: collision with root package name */
    public y4.a f6174z;

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6169u = new RectF();
        this.f6170v = new Matrix();
        this.f6172x = 10.0f;
        this.A = null;
        this.D = 0;
        this.E = 0;
        this.F = 500L;
    }

    @Override // com.yalantis.ucrop.view.TransformImageView
    public final void e() {
        super.e();
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        if (this.f6171w == DEFAULT_ASPECT_RATIO) {
            this.f6171w = intrinsicWidth / intrinsicHeight;
        }
        int i7 = this.f6205h;
        float f7 = i7;
        float f8 = this.f6171w;
        int i8 = (int) (f7 / f8);
        int i9 = this.f6206i;
        RectF rectF = this.f6169u;
        if (i8 > i9) {
            float f9 = i9;
            rectF.set((i7 - ((int) (f8 * f9))) / 2, DEFAULT_ASPECT_RATIO, r5 + r2, f9);
        } else {
            rectF.set(DEFAULT_ASPECT_RATIO, (i9 - i8) / 2, f7, i8 + r7);
        }
        g(intrinsicWidth, intrinsicHeight);
        float width = rectF.width();
        float height = rectF.height();
        float max = Math.max(rectF.width() / intrinsicWidth, rectF.height() / intrinsicHeight);
        float f10 = ((width - (intrinsicWidth * max)) / 2.0f) + rectF.left;
        float f11 = ((height - (intrinsicHeight * max)) / 2.0f) + rectF.top;
        Matrix matrix = this.f6204g;
        matrix.reset();
        matrix.postScale(max, max);
        matrix.postTranslate(f10, f11);
        setImageMatrix(matrix);
        a aVar = this.f6173y;
        if (aVar != null) {
            ((e) aVar).f10209a.f6219b.setTargetAspectRatio(this.f6171w);
        }
        d dVar = this.f6207j;
        if (dVar != null) {
            ((k) dVar).a(getCurrentScale());
            d dVar2 = this.f6207j;
            float currentAngle = getCurrentAngle();
            TextView textView = ((k) dVar2).f9087b.f6160u;
            if (textView != null) {
                textView.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(currentAngle)));
            }
        }
    }

    public final void g(float f7, float f8) {
        RectF rectF = this.f6169u;
        float min = Math.min(Math.min(rectF.width() / f7, rectF.width() / f8), Math.min(rectF.height() / f8, rectF.height() / f7));
        this.C = min;
        this.B = min * this.f6172x;
    }

    public a getCropBoundsChangeListener() {
        return this.f6173y;
    }

    public float getMaxScale() {
        return this.B;
    }

    public float getMinScale() {
        return this.C;
    }

    public float getTargetAspectRatio() {
        return this.f6171w;
    }

    public final void h() {
        removeCallbacks(this.f6174z);
        removeCallbacks(this.A);
    }

    public final boolean i(float[] fArr) {
        Matrix matrix = this.f6170v;
        matrix.reset();
        matrix.setRotate(-getCurrentAngle());
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        matrix.mapPoints(copyOf);
        float[] g02 = z.g0(this.f6169u);
        matrix.mapPoints(g02);
        return z.j1(copyOf).contains(z.j1(g02));
    }

    public final void j(float f7, float f8, float f9) {
        if (f7 > 1.0f && getCurrentScale() * f7 <= getMaxScale()) {
            if (f7 != DEFAULT_ASPECT_RATIO) {
                Matrix matrix = this.f6204g;
                matrix.postScale(f7, f7, f8, f9);
                setImageMatrix(matrix);
                d dVar = this.f6207j;
                if (dVar != null) {
                    ((k) dVar).a(c(matrix));
                    return;
                }
                return;
            }
            return;
        }
        if (f7 >= 1.0f || getCurrentScale() * f7 < getMinScale() || f7 == DEFAULT_ASPECT_RATIO) {
            return;
        }
        Matrix matrix2 = this.f6204g;
        matrix2.postScale(f7, f7, f8, f9);
        setImageMatrix(matrix2);
        d dVar2 = this.f6207j;
        if (dVar2 != null) {
            ((k) dVar2).a(c(matrix2));
        }
    }

    public final void k(float f7, float f8, float f9) {
        if (f7 <= getMaxScale()) {
            j(f7 / getCurrentScale(), f8, f9);
        }
    }

    public void setCropBoundsChangeListener(a aVar) {
        this.f6173y = aVar;
    }

    public void setCropRect(RectF rectF) {
        this.f6171w = rectF.width() / rectF.height();
        this.f6169u.set(rectF.left - getPaddingLeft(), rectF.top - getPaddingTop(), rectF.right - getPaddingRight(), rectF.bottom - getPaddingBottom());
        if (getDrawable() != null) {
            g(r6.getIntrinsicWidth(), r6.getIntrinsicHeight());
        }
        setImageToWrapCropBounds();
    }

    public void setImageToWrapCropBounds() {
        setImageToWrapCropBounds(true);
    }

    public void setImageToWrapCropBounds(boolean z6) {
        boolean z7;
        float max;
        if (this.f6211n) {
            float[] fArr = this.f6201d;
            if (i(fArr)) {
                return;
            }
            float[] fArr2 = this.f6202e;
            float f7 = fArr2[0];
            float f8 = fArr2[1];
            float currentScale = getCurrentScale();
            RectF rectF = this.f6169u;
            float centerX = rectF.centerX() - f7;
            float centerY = rectF.centerY() - f8;
            Matrix matrix = this.f6170v;
            matrix.reset();
            matrix.setTranslate(centerX, centerY);
            float[] copyOf = Arrays.copyOf(fArr, fArr.length);
            matrix.mapPoints(copyOf);
            boolean i7 = i(copyOf);
            if (i7) {
                matrix.reset();
                matrix.setRotate(-getCurrentAngle());
                float[] copyOf2 = Arrays.copyOf(fArr, fArr.length);
                float[] g02 = z.g0(rectF);
                matrix.mapPoints(copyOf2);
                matrix.mapPoints(g02);
                RectF j12 = z.j1(copyOf2);
                RectF j13 = z.j1(g02);
                float f9 = j12.left - j13.left;
                float f10 = j12.top - j13.top;
                float f11 = j12.right - j13.right;
                float f12 = j12.bottom - j13.bottom;
                float[] fArr3 = new float[4];
                if (f9 <= DEFAULT_ASPECT_RATIO) {
                    f9 = 0.0f;
                }
                fArr3[0] = f9;
                if (f10 <= DEFAULT_ASPECT_RATIO) {
                    f10 = 0.0f;
                }
                fArr3[1] = f10;
                if (f11 >= DEFAULT_ASPECT_RATIO) {
                    f11 = 0.0f;
                }
                fArr3[2] = f11;
                if (f12 >= DEFAULT_ASPECT_RATIO) {
                    f12 = 0.0f;
                }
                fArr3[3] = f12;
                matrix.reset();
                matrix.setRotate(getCurrentAngle());
                matrix.mapPoints(fArr3);
                float f13 = -(fArr3[0] + fArr3[2]);
                float f14 = -(fArr3[1] + fArr3[3]);
                centerX = f13;
                centerY = f14;
                z7 = i7;
                max = 0.0f;
            } else {
                RectF rectF2 = new RectF(rectF);
                matrix.reset();
                matrix.setRotate(getCurrentAngle());
                matrix.mapRect(rectF2);
                z7 = i7;
                float[] fArr4 = {(float) Math.sqrt(Math.pow(fArr[1] - fArr[3], 2.0d) + Math.pow(fArr[0] - fArr[2], 2.0d)), (float) Math.sqrt(Math.pow(fArr[3] - fArr[5], 2.0d) + Math.pow(fArr[2] - fArr[4], 2.0d))};
                max = (Math.max(rectF2.width() / fArr4[0], rectF2.height() / fArr4[1]) * currentScale) - currentScale;
            }
            if (z6) {
                y4.a aVar = new y4.a(this, this.F, f7, f8, centerX, centerY, currentScale, max, z7);
                this.f6174z = aVar;
                post(aVar);
            } else {
                f(centerX, centerY);
                if (z7) {
                    return;
                }
                k(currentScale + max, rectF.centerX(), rectF.centerY());
            }
        }
    }

    public void setImageToWrapCropBoundsAnimDuration(long j7) {
        if (j7 <= 0) {
            throw new IllegalArgumentException("Animation duration cannot be negative value.");
        }
        this.F = j7;
    }

    public void setMaxResultImageSizeX(int i7) {
        this.D = i7;
    }

    public void setMaxResultImageSizeY(int i7) {
        this.E = i7;
    }

    public void setMaxScaleMultiplier(float f7) {
        this.f6172x = f7;
    }

    public void setTargetAspectRatio(float f7) {
        if (getDrawable() == null) {
            this.f6171w = f7;
            return;
        }
        if (f7 == DEFAULT_ASPECT_RATIO) {
            this.f6171w = r0.getIntrinsicWidth() / r0.getIntrinsicHeight();
        } else {
            this.f6171w = f7;
        }
        a aVar = this.f6173y;
        if (aVar != null) {
            ((e) aVar).f10209a.f6219b.setTargetAspectRatio(this.f6171w);
        }
    }
}
