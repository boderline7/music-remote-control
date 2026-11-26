package com.navi.tool.ui.extra;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GlowProgressView extends View {
    private static final long ANIM_DURATION_MS = 900;
    private static final int DEFAULT_END_COLOR = -4176;
    private static final int DEFAULT_HALO_COLOR = 654301578;
    private static final int DEFAULT_START_COLOR = -11904;
    private ValueAnimator animator;
    private final float barHeight;
    private final Paint barPaint;
    private final Paint corePaint;
    private final float density;
    private int endColor;
    private final Paint glowPaint;
    private final float glowRadius;
    private int haloColor;
    private final float haloHeight;
    private final Paint haloPaint;
    private final RectF oval;
    private final RectF rect;
    private float shownProgress;
    private int startColor;
    private final float tailHeight;
    private final float tailLength;
    private final Matrix tailMatrix;
    private final Paint tailPaint;
    private LinearGradient tailShader;
    private int trackColor;
    private final Paint trackPaint;
    private final Paint wispPaint;
    private static final int[] DEFAULT_GLOW_COLORS = {-1426071422, 1442824778, 16761418};
    private static final int[] DEFAULT_TAIL_COLORS = {16765312, 1728043402, -419433536};

    public GlowProgressView(Context context) {
        this(context, null);
    }

    public GlowProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint(1);
        this.trackPaint = paint;
        Paint paint2 = new Paint(1);
        this.haloPaint = paint2;
        Paint paint3 = new Paint(1);
        this.barPaint = paint3;
        Paint paint4 = new Paint(1);
        this.glowPaint = paint4;
        Paint paint5 = new Paint(1);
        this.corePaint = paint5;
        Paint paint6 = new Paint(1);
        this.tailPaint = paint6;
        Paint paint7 = new Paint(1);
        this.wispPaint = paint7;
        this.rect = new RectF();
        this.oval = new RectF();
        this.tailMatrix = new Matrix();
        this.startColor = DEFAULT_START_COLOR;
        this.endColor = DEFAULT_END_COLOR;
        this.trackColor = 872415231;
        this.haloColor = DEFAULT_HALO_COLOR;
        this.shownProgress = CropImageView.DEFAULT_ASPECT_RATIO;
        float f7 = getResources().getDisplayMetrics().density;
        this.density = f7;
        this.barHeight = 8.0f * f7;
        this.glowRadius = 10.0f * f7;
        this.haloHeight = 13.0f * f7;
        this.tailHeight = 14.0f * f7;
        this.tailLength = f7 * 36.0f;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint2.setStyle(style);
        paint3.setStyle(style);
        paint4.setStyle(style);
        paint5.setStyle(style);
        int[] iArr = DEFAULT_GLOW_COLORS;
        float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 1.0f};
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint4.setShader(new RadialGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, iArr, fArr, tileMode));
        paint5.setShader(new RadialGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, new int[]{-855638017, p0.d1.MEASURED_SIZE_MASK}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f}, tileMode));
        paint6.setStyle(style);
        paint7.setStyle(style);
        LinearGradient linearGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, DEFAULT_TAIL_COLORS, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.55f, 1.0f}, tileMode);
        this.tailShader = linearGradient;
        paint6.setShader(linearGradient);
        paint7.setShader(this.tailShader);
        paint7.setAlpha(z1.c.INVOKE_VIRTUAL);
        setClickable(false);
        setFocusable(false);
    }

    private static int clamp(int i7) {
        if (i7 < 0) {
            return 0;
        }
        return Math.min(i7, 255);
    }

    private static int ensureReadableAccent(int i7) {
        float luminance = luminance(i7);
        return luminance < 0.45f ? lighten(i7, (0.45f - luminance) / Math.max(0.55f, 1.0f - luminance)) : luminance > 0.8f ? scaleRgb(i7, 0.8f / Math.max(luminance, 0.001f)) : i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setProgress$0(ValueAnimator valueAnimator) {
        this.shownProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    private static int lighten(int i7, float f7) {
        return Color.rgb(clamp((int) (((255 - r0) * f7) + Color.red(i7))), clamp((int) (((255 - r1) * f7) + Color.green(i7))), clamp((int) (((255 - r3) * f7) + Color.blue(i7))));
    }

    private static float luminance(int i7) {
        return ((Color.blue(i7) / 255.0f) * 0.0722f) + ((Color.green(i7) / 255.0f) * 0.7152f) + ((Color.red(i7) / 255.0f) * 0.2126f);
    }

    private void rebuildBarShader(int i7) {
        if (i7 <= 0) {
            this.barPaint.setShader(null);
        } else {
            this.barPaint.setShader(new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7, CropImageView.DEFAULT_ASPECT_RATIO, this.startColor, this.endColor, Shader.TileMode.CLAMP));
        }
    }

    private static int scaleRgb(int i7, float f7) {
        return Color.rgb(clamp((int) (Color.red(i7) * f7)), clamp((int) (Color.green(i7) * f7)), clamp((int) (Color.blue(i7) * f7)));
    }

    private static int withAlpha(int i7, int i8) {
        return (i7 & p0.d1.MEASURED_SIZE_MASK) | ((i8 & 255) << 24);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        float f7 = height / 2.0f;
        float f8 = this.barHeight;
        float f9 = f7 - (f8 / 2.0f);
        float f10 = (f8 / 2.0f) + f7;
        float f11 = f8 / 2.0f;
        float f12 = width;
        this.rect.set(CropImageView.DEFAULT_ASPECT_RATIO, f9, f12, f10);
        this.trackPaint.setColor(this.trackColor);
        canvas.drawRoundRect(this.rect, f11, f11, this.trackPaint);
        float f13 = this.shownProgress;
        if (f13 <= 0.001f) {
            return;
        }
        float max = Math.max(f11 * 2.0f, f12 * f13);
        float f14 = max / 2.0f;
        float min = Math.min(this.haloHeight / 2.0f, f14);
        RectF rectF = this.rect;
        float f15 = this.haloHeight;
        rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, f7 - (f15 / 2.0f), max, (f15 / 2.0f) + f7);
        this.haloPaint.setColor(this.haloColor);
        canvas.drawRoundRect(this.rect, min, min, this.haloPaint);
        canvas.save();
        canvas.translate(max, f7);
        float f16 = this.glowRadius;
        canvas.scale(2.4f * f16, f16 * 1.15f);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, this.glowPaint);
        canvas.restore();
        float min2 = Math.min(f11, f14);
        this.rect.set(CropImageView.DEFAULT_ASPECT_RATIO, f9, max, f10);
        canvas.drawRoundRect(this.rect, min2, min2, this.barPaint);
        float min3 = Math.min(this.tailLength, max);
        if (min3 > this.density * 2.0f) {
            this.tailMatrix.reset();
            this.tailMatrix.setScale(min3, 1.0f);
            float f17 = max - min3;
            this.tailMatrix.postTranslate(f17, CropImageView.DEFAULT_ASPECT_RATIO);
            this.tailShader.setLocalMatrix(this.tailMatrix);
            RectF rectF2 = this.oval;
            float f18 = this.tailHeight;
            rectF2.set(f17, f7 - (f18 / 2.0f), max, (f18 / 2.0f) + f7);
            canvas.drawOval(this.oval, this.tailPaint);
            float min4 = Math.min(min3 * 1.25f, max);
            if (min4 > this.density * 2.0f) {
                this.tailMatrix.reset();
                this.tailMatrix.setScale(min4, 1.0f);
                float f19 = max - min4;
                this.tailMatrix.postTranslate(f19, CropImageView.DEFAULT_ASPECT_RATIO);
                this.tailShader.setLocalMatrix(this.tailMatrix);
                float f20 = this.density * 1.4f;
                for (int i7 = -1; i7 <= 1; i7 += 2) {
                    float f21 = (i7 * 5.2f * this.density) + f7;
                    float f22 = f20 / 2.0f;
                    this.oval.set(f19, f21 - f22, max, f21 + f22);
                    canvas.drawOval(this.oval, this.wispPaint);
                }
            }
        }
        canvas.save();
        canvas.translate(max - (0.65f * min2), f7);
        canvas.scale(1.5f * min2, min2 * 0.85f);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, this.corePaint);
        canvas.restore();
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        rebuildBarShader(i7);
    }

    public void resetDefaultColors() {
        this.startColor = DEFAULT_START_COLOR;
        this.endColor = DEFAULT_END_COLOR;
        this.haloColor = DEFAULT_HALO_COLOR;
        rebuildBarShader(getWidth());
        Paint paint = this.glowPaint;
        int[] iArr = DEFAULT_GLOW_COLORS;
        float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 1.0f};
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new RadialGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, iArr, fArr, tileMode));
        LinearGradient linearGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, DEFAULT_TAIL_COLORS, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.55f, 1.0f}, tileMode);
        this.tailShader = linearGradient;
        this.tailPaint.setShader(linearGradient);
        this.wispPaint.setShader(this.tailShader);
        invalidate();
    }

    public void setBarColors(int i7, int i8) {
        this.startColor = i7;
        this.endColor = i8;
        rebuildBarShader(getWidth());
        invalidate();
    }

    public void setCoverAccent(int i7) {
        int ensureReadableAccent = ensureReadableAccent(i7);
        int lighten = lighten(ensureReadableAccent, 0.45f);
        this.startColor = ensureReadableAccent;
        this.endColor = lighten;
        this.haloColor = withAlpha(ensureReadableAccent, 38);
        rebuildBarShader(getWidth());
        Paint paint = this.glowPaint;
        int[] iArr = {withAlpha(lighten(ensureReadableAccent, 0.45f), z1.c.REM_FLOAT), withAlpha(ensureReadableAccent, 85), withAlpha(ensureReadableAccent, 0)};
        float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, 0.4f, 1.0f};
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new RadialGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, iArr, fArr, tileMode));
        LinearGradient linearGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{withAlpha(ensureReadableAccent, 0), withAlpha(ensureReadableAccent, z1.c.SGET_SHORT), withAlpha(lighten(ensureReadableAccent, 0.55f), 230)}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.55f, 1.0f}, tileMode);
        this.tailShader = linearGradient;
        this.tailPaint.setShader(linearGradient);
        this.wispPaint.setShader(this.tailShader);
        invalidate();
    }

    public void setProgress(float f7) {
        setProgress(f7, true);
    }

    public void setProgress(float f7, boolean z6) {
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            f7 = 0.0f;
        }
        if (f7 > 1.0f) {
            f7 = 1.0f;
        }
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator = null;
        }
        if (Math.abs(f7 - this.shownProgress) > 0.04f) {
            this.shownProgress = f7;
            invalidate();
            return;
        }
        if (z6 && isAttachedToWindow()) {
            float f8 = this.shownProgress;
            if (f7 > f8 - 0.002f) {
                if (Math.abs(f7 - f8) < 0.001f) {
                    return;
                }
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.shownProgress, f7);
                ofFloat.setDuration(ANIM_DURATION_MS);
                ofFloat.setInterpolator(new LinearInterpolator());
                ofFloat.addUpdateListener(new j0(this, 1));
                ofFloat.start();
                this.animator = ofFloat;
                return;
            }
        }
        this.shownProgress = f7;
        invalidate();
    }
}
