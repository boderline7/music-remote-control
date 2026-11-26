package androidx.constraintlayout.utils.widget;

import a0.t;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import f.a;
import l6.f;
import v.c;
import y.b;
import z.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionLabel extends View implements b {
    public float A;
    public float B;
    public Drawable C;
    public Matrix D;
    public Bitmap E;
    public BitmapShader F;
    public Matrix G;
    public float H;
    public float I;
    public float J;
    public float K;
    public final Paint L;
    public int M;
    public Rect N;
    public Paint O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;

    /* renamed from: a, reason: collision with root package name */
    public final TextPaint f1641a;

    /* renamed from: b, reason: collision with root package name */
    public Path f1642b;

    /* renamed from: c, reason: collision with root package name */
    public int f1643c;

    /* renamed from: d, reason: collision with root package name */
    public int f1644d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1645e;

    /* renamed from: f, reason: collision with root package name */
    public float f1646f;

    /* renamed from: g, reason: collision with root package name */
    public float f1647g;

    /* renamed from: h, reason: collision with root package name */
    public e f1648h;

    /* renamed from: i, reason: collision with root package name */
    public RectF f1649i;

    /* renamed from: j, reason: collision with root package name */
    public float f1650j;

    /* renamed from: k, reason: collision with root package name */
    public float f1651k;

    /* renamed from: l, reason: collision with root package name */
    public int f1652l;

    /* renamed from: m, reason: collision with root package name */
    public int f1653m;

    /* renamed from: n, reason: collision with root package name */
    public float f1654n;

    /* renamed from: o, reason: collision with root package name */
    public String f1655o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1656p;

    /* renamed from: q, reason: collision with root package name */
    public final Rect f1657q;

    /* renamed from: r, reason: collision with root package name */
    public int f1658r;

    /* renamed from: s, reason: collision with root package name */
    public int f1659s;

    /* renamed from: t, reason: collision with root package name */
    public int f1660t;

    /* renamed from: u, reason: collision with root package name */
    public int f1661u;

    /* renamed from: v, reason: collision with root package name */
    public String f1662v;

    /* renamed from: w, reason: collision with root package name */
    public int f1663w;

    /* renamed from: x, reason: collision with root package name */
    public int f1664x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f1665y;

    /* renamed from: z, reason: collision with root package name */
    public float f1666z;

    public MotionLabel(Context context) {
        super(context);
        this.f1641a = new TextPaint();
        this.f1642b = new Path();
        this.f1643c = 65535;
        this.f1644d = 65535;
        this.f1645e = false;
        this.f1646f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1647g = Float.NaN;
        this.f1650j = 48.0f;
        this.f1651k = Float.NaN;
        this.f1654n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1655o = "Hello World";
        this.f1656p = true;
        this.f1657q = new Rect();
        this.f1658r = 1;
        this.f1659s = 1;
        this.f1660t = 1;
        this.f1661u = 1;
        this.f1663w = 8388659;
        this.f1664x = 0;
        this.f1665y = false;
        this.H = Float.NaN;
        this.I = Float.NaN;
        this.J = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = CropImageView.DEFAULT_ASPECT_RATIO;
        this.L = new Paint();
        this.M = 0;
        this.Q = Float.NaN;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        b(context, null);
    }

    public MotionLabel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1641a = new TextPaint();
        this.f1642b = new Path();
        this.f1643c = 65535;
        this.f1644d = 65535;
        this.f1645e = false;
        this.f1646f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1647g = Float.NaN;
        this.f1650j = 48.0f;
        this.f1651k = Float.NaN;
        this.f1654n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1655o = "Hello World";
        this.f1656p = true;
        this.f1657q = new Rect();
        this.f1658r = 1;
        this.f1659s = 1;
        this.f1660t = 1;
        this.f1661u = 1;
        this.f1663w = 8388659;
        this.f1664x = 0;
        this.f1665y = false;
        this.H = Float.NaN;
        this.I = Float.NaN;
        this.J = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = CropImageView.DEFAULT_ASPECT_RATIO;
        this.L = new Paint();
        this.M = 0;
        this.Q = Float.NaN;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        b(context, attributeSet);
    }

    public MotionLabel(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1641a = new TextPaint();
        this.f1642b = new Path();
        this.f1643c = 65535;
        this.f1644d = 65535;
        this.f1645e = false;
        this.f1646f = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1647g = Float.NaN;
        this.f1650j = 48.0f;
        this.f1651k = Float.NaN;
        this.f1654n = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1655o = "Hello World";
        this.f1656p = true;
        this.f1657q = new Rect();
        this.f1658r = 1;
        this.f1659s = 1;
        this.f1660t = 1;
        this.f1661u = 1;
        this.f1663w = 8388659;
        this.f1664x = 0;
        this.f1665y = false;
        this.H = Float.NaN;
        this.I = Float.NaN;
        this.J = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = CropImageView.DEFAULT_ASPECT_RATIO;
        this.L = new Paint();
        this.M = 0;
        this.Q = Float.NaN;
        this.R = Float.NaN;
        this.S = Float.NaN;
        this.T = Float.NaN;
        b(context, attributeSet);
    }

    private float getHorizontalOffset() {
        float f7 = Float.isNaN(this.f1651k) ? 1.0f : this.f1650j / this.f1651k;
        String str = this.f1655o;
        return ((this.J + 1.0f) * ((((Float.isNaN(this.A) ? getMeasuredWidth() : this.A) - getPaddingLeft()) - getPaddingRight()) - (this.f1641a.measureText(str, 0, str.length()) * f7))) / 2.0f;
    }

    private float getVerticalOffset() {
        float f7 = Float.isNaN(this.f1651k) ? 1.0f : this.f1650j / this.f1651k;
        Paint.FontMetrics fontMetrics = this.f1641a.getFontMetrics();
        float measuredHeight = ((Float.isNaN(this.B) ? getMeasuredHeight() : this.B) - getPaddingTop()) - getPaddingBottom();
        float f8 = fontMetrics.descent;
        float f9 = fontMetrics.ascent;
        return (((1.0f - this.K) * (measuredHeight - ((f8 - f9) * f7))) / 2.0f) - (f7 * f9);
    }

    public final void a(float f7) {
        if (this.f1645e || f7 != 1.0f) {
            this.f1642b.reset();
            String str = this.f1655o;
            int length = str.length();
            TextPaint textPaint = this.f1641a;
            Rect rect = this.f1657q;
            textPaint.getTextBounds(str, 0, length, rect);
            this.f1641a.getTextPath(str, 0, length, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f1642b);
            if (f7 != 1.0f) {
                f.D();
                Matrix matrix = new Matrix();
                matrix.postScale(f7, f7);
                this.f1642b.transform(matrix);
            }
            rect.right--;
            rect.left++;
            rect.bottom++;
            rect.top--;
            RectF rectF = new RectF();
            rectF.bottom = getHeight();
            rectF.right = getWidth();
            this.f1656p = false;
        }
    }

    public final void b(Context context, AttributeSet attributeSet) {
        Typeface typeface;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.colorPrimary, typedValue, true);
        int i7 = typedValue.data;
        this.f1643c = i7;
        TextPaint textPaint = this.f1641a;
        textPaint.setColor(i7);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.MotionLabel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = obtainStyledAttributes.getIndex(i8);
                if (index == t.MotionLabel_android_text) {
                    setText(obtainStyledAttributes.getText(index));
                } else if (index == t.MotionLabel_android_fontFamily) {
                    this.f1662v = obtainStyledAttributes.getString(index);
                } else if (index == t.MotionLabel_scaleFromTextSize) {
                    this.f1651k = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.f1651k);
                } else if (index == t.MotionLabel_android_textSize) {
                    this.f1650j = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.f1650j);
                } else if (index == t.MotionLabel_android_textStyle) {
                    this.f1652l = obtainStyledAttributes.getInt(index, this.f1652l);
                } else if (index == t.MotionLabel_android_typeface) {
                    this.f1653m = obtainStyledAttributes.getInt(index, this.f1653m);
                } else if (index == t.MotionLabel_android_textColor) {
                    this.f1643c = obtainStyledAttributes.getColor(index, this.f1643c);
                } else if (index == t.MotionLabel_borderRound) {
                    float dimension = obtainStyledAttributes.getDimension(index, this.f1647g);
                    this.f1647g = dimension;
                    setRound(dimension);
                } else if (index == t.MotionLabel_borderRoundPercent) {
                    float f7 = obtainStyledAttributes.getFloat(index, this.f1646f);
                    this.f1646f = f7;
                    setRoundPercent(f7);
                } else if (index == t.MotionLabel_android_gravity) {
                    setGravity(obtainStyledAttributes.getInt(index, -1));
                } else if (index == t.MotionLabel_android_autoSizeTextType) {
                    this.f1664x = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.MotionLabel_textOutlineColor) {
                    this.f1644d = obtainStyledAttributes.getInt(index, this.f1644d);
                    this.f1645e = true;
                } else if (index == t.MotionLabel_textOutlineThickness) {
                    this.f1654n = obtainStyledAttributes.getDimension(index, this.f1654n);
                    this.f1645e = true;
                } else if (index == t.MotionLabel_textBackground) {
                    this.C = obtainStyledAttributes.getDrawable(index);
                    this.f1645e = true;
                } else if (index == t.MotionLabel_textBackgroundPanX) {
                    this.Q = obtainStyledAttributes.getFloat(index, this.Q);
                } else if (index == t.MotionLabel_textBackgroundPanY) {
                    this.R = obtainStyledAttributes.getFloat(index, this.R);
                } else if (index == t.MotionLabel_textPanX) {
                    this.J = obtainStyledAttributes.getFloat(index, this.J);
                } else if (index == t.MotionLabel_textPanY) {
                    this.K = obtainStyledAttributes.getFloat(index, this.K);
                } else if (index == t.MotionLabel_textBackgroundRotate) {
                    this.T = obtainStyledAttributes.getFloat(index, this.T);
                } else if (index == t.MotionLabel_textBackgroundZoom) {
                    this.S = obtainStyledAttributes.getFloat(index, this.S);
                } else if (index == t.MotionLabel_textureHeight) {
                    this.H = obtainStyledAttributes.getDimension(index, this.H);
                } else if (index == t.MotionLabel_textureWidth) {
                    this.I = obtainStyledAttributes.getDimension(index, this.I);
                } else if (index == t.MotionLabel_textureEffect) {
                    this.M = obtainStyledAttributes.getInt(index, this.M);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.C != null) {
            this.G = new Matrix();
            int intrinsicWidth = this.C.getIntrinsicWidth();
            int intrinsicHeight = this.C.getIntrinsicHeight();
            if (intrinsicWidth <= 0 && (intrinsicWidth = getWidth()) == 0) {
                intrinsicWidth = Float.isNaN(this.I) ? 128 : (int) this.I;
            }
            if (intrinsicHeight <= 0 && (intrinsicHeight = getHeight()) == 0) {
                intrinsicHeight = Float.isNaN(this.H) ? 128 : (int) this.H;
            }
            if (this.M != 0) {
                intrinsicWidth /= 2;
                intrinsicHeight /= 2;
            }
            this.E = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.E);
            this.C.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.C.setFilterBitmap(true);
            this.C.draw(canvas);
            if (this.M != 0) {
                Bitmap bitmap = this.E;
                System.nanoTime();
                int width = bitmap.getWidth() / 2;
                int height = bitmap.getHeight() / 2;
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                for (int i9 = 0; i9 < 4 && width >= 32 && height >= 32; i9++) {
                    width /= 2;
                    height /= 2;
                    createScaledBitmap = Bitmap.createScaledBitmap(createScaledBitmap, width, height, true);
                }
                this.E = createScaledBitmap;
            }
            Bitmap bitmap2 = this.E;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            this.F = new BitmapShader(bitmap2, tileMode, tileMode);
        }
        this.f1658r = getPaddingLeft();
        this.f1659s = getPaddingRight();
        this.f1660t = getPaddingTop();
        this.f1661u = getPaddingBottom();
        String str = this.f1662v;
        int i10 = this.f1653m;
        int i11 = this.f1652l;
        if (str != null) {
            typeface = Typeface.create(str, i11);
            if (typeface != null) {
                setTypeface(typeface);
                textPaint.setColor(this.f1643c);
                textPaint.setStrokeWidth(this.f1654n);
                textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                textPaint.setFlags(128);
                setTextSize(this.f1650j);
                textPaint.setAntiAlias(true);
            }
        } else {
            typeface = null;
        }
        if (i10 == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i10 == 2) {
            typeface = Typeface.SERIF;
        } else if (i10 == 3) {
            typeface = Typeface.MONOSPACE;
        }
        float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (i11 > 0) {
            Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i11) : Typeface.create(typeface, i11);
            setTypeface(defaultFromStyle);
            int i12 = (~(defaultFromStyle != null ? defaultFromStyle.getStyle() : 0)) & i11;
            textPaint.setFakeBoldText((i12 & 1) != 0);
            if ((i12 & 2) != 0) {
                f8 = -0.25f;
            }
            textPaint.setTextSkewX(f8);
        } else {
            textPaint.setFakeBoldText(false);
            textPaint.setTextSkewX(CropImageView.DEFAULT_ASPECT_RATIO);
            setTypeface(typeface);
        }
        textPaint.setColor(this.f1643c);
        textPaint.setStrokeWidth(this.f1654n);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setFlags(128);
        setTextSize(this.f1650j);
        textPaint.setAntiAlias(true);
    }

    public final void c(float f7, float f8, float f9, float f10) {
        int i7 = (int) (f7 + 0.5f);
        this.f1666z = f7 - i7;
        int i8 = (int) (f9 + 0.5f);
        int i9 = i8 - i7;
        int i10 = (int) (f10 + 0.5f);
        int i11 = (int) (0.5f + f8);
        int i12 = i10 - i11;
        float f11 = f9 - f7;
        this.A = f11;
        float f12 = f10 - f8;
        this.B = f12;
        if (this.G != null) {
            this.A = f11;
            this.B = f12;
            d();
        }
        if (getMeasuredHeight() == i12 && getMeasuredWidth() == i9) {
            super.layout(i7, i11, i8, i10);
        } else {
            measure(View.MeasureSpec.makeMeasureSpec(i9, c.EXACTLY), View.MeasureSpec.makeMeasureSpec(i12, c.EXACTLY));
            super.layout(i7, i11, i8, i10);
        }
        if (this.f1665y) {
            Rect rect = this.N;
            TextPaint textPaint = this.f1641a;
            if (rect == null) {
                this.O = new Paint();
                this.N = new Rect();
                this.O.set(textPaint);
                this.P = this.O.getTextSize();
            }
            this.A = f11;
            this.B = f12;
            Paint paint = this.O;
            String str = this.f1655o;
            paint.getTextBounds(str, 0, str.length(), this.N);
            float height = this.N.height() * 1.3f;
            float f13 = (f11 - this.f1659s) - this.f1658r;
            float f14 = (f12 - this.f1661u) - this.f1660t;
            float width = this.N.width();
            if (width * f14 > height * f13) {
                textPaint.setTextSize((this.P * f13) / width);
            } else {
                textPaint.setTextSize((this.P * f14) / height);
            }
            if (this.f1645e || !Float.isNaN(this.f1651k)) {
                a(Float.isNaN(this.f1651k) ? 1.0f : this.f1650j / this.f1651k);
            }
        }
    }

    public final void d() {
        boolean isNaN = Float.isNaN(this.Q);
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float f8 = isNaN ? 0.0f : this.Q;
        float f9 = Float.isNaN(this.R) ? 0.0f : this.R;
        float f10 = Float.isNaN(this.S) ? 1.0f : this.S;
        if (!Float.isNaN(this.T)) {
            f7 = this.T;
        }
        this.G.reset();
        float width = this.E.getWidth();
        float height = this.E.getHeight();
        float f11 = Float.isNaN(this.I) ? this.A : this.I;
        float f12 = Float.isNaN(this.H) ? this.B : this.H;
        float f13 = f10 * (width * f12 < height * f11 ? f11 / width : f12 / height);
        this.G.postScale(f13, f13);
        float f14 = width * f13;
        float f15 = f11 - f14;
        float f16 = f13 * height;
        float f17 = f12 - f16;
        if (!Float.isNaN(this.H)) {
            f17 = this.H / 2.0f;
        }
        if (!Float.isNaN(this.I)) {
            f15 = this.I / 2.0f;
        }
        this.G.postTranslate((((f8 * f15) + f11) - f14) * 0.5f, (((f9 * f17) + f12) - f16) * 0.5f);
        this.G.postRotate(f7, f11 / 2.0f, f12 / 2.0f);
        this.F.setLocalMatrix(this.G);
    }

    public float getRound() {
        return this.f1647g;
    }

    public float getRoundPercent() {
        return this.f1646f;
    }

    public float getScaleFromTextSize() {
        return this.f1651k;
    }

    public float getTextBackgroundPanX() {
        return this.Q;
    }

    public float getTextBackgroundPanY() {
        return this.R;
    }

    public float getTextBackgroundRotate() {
        return this.T;
    }

    public float getTextBackgroundZoom() {
        return this.S;
    }

    public int getTextOutlineColor() {
        return this.f1644d;
    }

    public float getTextPanX() {
        return this.J;
    }

    public float getTextPanY() {
        return this.K;
    }

    public float getTextureHeight() {
        return this.H;
    }

    public float getTextureWidth() {
        return this.I;
    }

    public Typeface getTypeface() {
        return this.f1641a.getTypeface();
    }

    @Override // android.view.View
    public final void layout(int i7, int i8, int i9, int i10) {
        super.layout(i7, i8, i9, i10);
        boolean isNaN = Float.isNaN(this.f1651k);
        float f7 = isNaN ? 1.0f : this.f1650j / this.f1651k;
        this.A = i9 - i7;
        this.B = i10 - i8;
        if (this.f1665y) {
            Rect rect = this.N;
            TextPaint textPaint = this.f1641a;
            if (rect == null) {
                this.O = new Paint();
                this.N = new Rect();
                this.O.set(textPaint);
                this.P = this.O.getTextSize();
            }
            Paint paint = this.O;
            String str = this.f1655o;
            paint.getTextBounds(str, 0, str.length(), this.N);
            int width = this.N.width();
            int height = (int) (this.N.height() * 1.3f);
            float f8 = (this.A - this.f1659s) - this.f1658r;
            float f9 = (this.B - this.f1661u) - this.f1660t;
            if (isNaN) {
                float f10 = width;
                float f11 = height;
                if (f10 * f9 > f11 * f8) {
                    textPaint.setTextSize((this.P * f8) / f10);
                } else {
                    textPaint.setTextSize((this.P * f9) / f11);
                }
            } else {
                float f12 = width;
                float f13 = height;
                f7 = f12 * f9 > f13 * f8 ? f8 / f12 : f9 / f13;
            }
        }
        if (this.f1645e || !isNaN) {
            float f14 = i7;
            float f15 = i8;
            float f16 = i9;
            float f17 = i10;
            if (this.G != null) {
                this.A = f16 - f14;
                this.B = f17 - f15;
                d();
            }
            a(f7);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f7 = Float.isNaN(this.f1651k) ? 1.0f : this.f1650j / this.f1651k;
        super.onDraw(canvas);
        boolean z6 = this.f1645e;
        TextPaint textPaint = this.f1641a;
        if (!z6 && f7 == 1.0f) {
            canvas.drawText(this.f1655o, this.f1666z + this.f1658r + getHorizontalOffset(), this.f1660t + getVerticalOffset(), textPaint);
            return;
        }
        if (this.f1656p) {
            a(f7);
        }
        if (this.D == null) {
            this.D = new Matrix();
        }
        if (!this.f1645e) {
            float horizontalOffset = this.f1658r + getHorizontalOffset();
            float verticalOffset = this.f1660t + getVerticalOffset();
            this.D.reset();
            this.D.preTranslate(horizontalOffset, verticalOffset);
            this.f1642b.transform(this.D);
            textPaint.setColor(this.f1643c);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setStrokeWidth(this.f1654n);
            canvas.drawPath(this.f1642b, textPaint);
            this.D.reset();
            this.D.preTranslate(-horizontalOffset, -verticalOffset);
            this.f1642b.transform(this.D);
            return;
        }
        Paint paint = this.L;
        paint.set(textPaint);
        this.D.reset();
        float horizontalOffset2 = this.f1658r + getHorizontalOffset();
        float verticalOffset2 = this.f1660t + getVerticalOffset();
        this.D.postTranslate(horizontalOffset2, verticalOffset2);
        this.D.preScale(f7, f7);
        this.f1642b.transform(this.D);
        if (this.F != null) {
            textPaint.setFilterBitmap(true);
            textPaint.setShader(this.F);
        } else {
            textPaint.setColor(this.f1643c);
        }
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(this.f1654n);
        canvas.drawPath(this.f1642b, textPaint);
        if (this.F != null) {
            textPaint.setShader(null);
        }
        textPaint.setColor(this.f1644d);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(this.f1654n);
        canvas.drawPath(this.f1642b, textPaint);
        this.D.reset();
        this.D.postTranslate(-horizontalOffset2, -verticalOffset2);
        this.f1642b.transform(this.D);
        textPaint.set(paint);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        this.f1665y = false;
        this.f1658r = getPaddingLeft();
        this.f1659s = getPaddingRight();
        this.f1660t = getPaddingTop();
        this.f1661u = getPaddingBottom();
        if (mode != 1073741824 || mode2 != 1073741824) {
            String str = this.f1655o;
            int length = str.length();
            this.f1641a.getTextBounds(str, 0, length, this.f1657q);
            if (mode != 1073741824) {
                size = (int) (r7.width() + 0.99999f);
            }
            size += this.f1658r + this.f1659s;
            if (mode2 != 1073741824) {
                int fontMetricsInt = (int) (r6.getFontMetricsInt(null) + 0.99999f);
                if (mode2 == Integer.MIN_VALUE) {
                    fontMetricsInt = Math.min(size2, fontMetricsInt);
                }
                size2 = this.f1660t + this.f1661u + fontMetricsInt;
            }
        } else if (this.f1664x != 0) {
            this.f1665y = true;
        }
        setMeasuredDimension(size, size2);
    }

    @SuppressLint({"RtlHardcoded"})
    public void setGravity(int i7) {
        if ((i7 & 8388615) == 0) {
            i7 |= 8388611;
        }
        if ((i7 & z1.c.INVOKE_DIRECT) == 0) {
            i7 |= 48;
        }
        if (i7 != this.f1663w) {
            invalidate();
        }
        this.f1663w = i7;
        int i8 = i7 & z1.c.INVOKE_DIRECT;
        if (i8 == 48) {
            this.K = -1.0f;
        } else if (i8 != 80) {
            this.K = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.K = 1.0f;
        }
        int i9 = i7 & 8388615;
        if (i9 != 3) {
            if (i9 != 5) {
                if (i9 != 8388611) {
                    if (i9 != 8388613) {
                        this.J = CropImageView.DEFAULT_ASPECT_RATIO;
                        return;
                    }
                }
            }
            this.J = 1.0f;
            return;
        }
        this.J = -1.0f;
    }

    public void setRound(float f7) {
        if (Float.isNaN(f7)) {
            this.f1647g = f7;
            float f8 = this.f1646f;
            this.f1646f = -1.0f;
            setRoundPercent(f8);
            return;
        }
        boolean z6 = this.f1647g != f7;
        this.f1647g = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1642b == null) {
                this.f1642b = new Path();
            }
            if (this.f1649i == null) {
                this.f1649i = new RectF();
            }
            if (this.f1648h == null) {
                e eVar = new e(this, 1);
                this.f1648h = eVar;
                setOutlineProvider(eVar);
            }
            setClipToOutline(true);
            this.f1649i.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, getWidth(), getHeight());
            this.f1642b.reset();
            Path path = this.f1642b;
            RectF rectF = this.f1649i;
            float f9 = this.f1647g;
            path.addRoundRect(rectF, f9, f9, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f7) {
        boolean z6 = this.f1646f != f7;
        this.f1646f = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1642b == null) {
                this.f1642b = new Path();
            }
            if (this.f1649i == null) {
                this.f1649i = new RectF();
            }
            if (this.f1648h == null) {
                e eVar = new e(this, 0);
                this.f1648h = eVar;
                setOutlineProvider(eVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.f1646f) / 2.0f;
            this.f1649i.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height);
            this.f1642b.reset();
            this.f1642b.addRoundRect(this.f1649i, min, min, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setScaleFromTextSize(float f7) {
        this.f1651k = f7;
    }

    public void setText(CharSequence charSequence) {
        this.f1655o = charSequence.toString();
        invalidate();
    }

    public void setTextBackgroundPanX(float f7) {
        this.Q = f7;
        d();
        invalidate();
    }

    public void setTextBackgroundPanY(float f7) {
        this.R = f7;
        d();
        invalidate();
    }

    public void setTextBackgroundRotate(float f7) {
        this.T = f7;
        d();
        invalidate();
    }

    public void setTextBackgroundZoom(float f7) {
        this.S = f7;
        d();
        invalidate();
    }

    public void setTextFillColor(int i7) {
        this.f1643c = i7;
        invalidate();
    }

    public void setTextOutlineColor(int i7) {
        this.f1644d = i7;
        this.f1645e = true;
        invalidate();
    }

    public void setTextOutlineThickness(float f7) {
        this.f1654n = f7;
        this.f1645e = true;
        if (Float.isNaN(f7)) {
            this.f1654n = 1.0f;
            this.f1645e = false;
        }
        invalidate();
    }

    public void setTextPanX(float f7) {
        this.J = f7;
        invalidate();
    }

    public void setTextPanY(float f7) {
        this.K = f7;
        invalidate();
    }

    public void setTextSize(float f7) {
        this.f1650j = f7;
        f.D();
        if (!Float.isNaN(this.f1651k)) {
            f7 = this.f1651k;
        }
        this.f1641a.setTextSize(f7);
        a(Float.isNaN(this.f1651k) ? 1.0f : this.f1650j / this.f1651k);
        requestLayout();
        invalidate();
    }

    public void setTextureHeight(float f7) {
        this.H = f7;
        d();
        invalidate();
    }

    public void setTextureWidth(float f7) {
        this.I = f7;
        d();
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        TextPaint textPaint = this.f1641a;
        if (textPaint.getTypeface() != typeface) {
            textPaint.setTypeface(typeface);
        }
    }
}
