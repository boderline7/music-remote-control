package com.navi.tool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SmartCornerView extends View {
    public static final int CORNER_BOTTOM_LEFT = 2;
    public static final int CORNER_BOTTOM_RIGHT = 3;
    public static final int CORNER_TOP_LEFT = 0;
    public static final int CORNER_TOP_RIGHT = 1;

    /* renamed from: a, reason: collision with root package name */
    public int f6004a;

    /* renamed from: b, reason: collision with root package name */
    public Paint f6005b;

    /* renamed from: c, reason: collision with root package name */
    public Paint f6006c;

    /* renamed from: d, reason: collision with root package name */
    public Paint f6007d;

    /* renamed from: e, reason: collision with root package name */
    public Paint f6008e;

    /* renamed from: f, reason: collision with root package name */
    public Path f6009f;

    /* renamed from: g, reason: collision with root package name */
    public RectF f6010g;

    /* renamed from: h, reason: collision with root package name */
    public RectF f6011h;

    /* renamed from: i, reason: collision with root package name */
    public float f6012i;

    /* renamed from: j, reason: collision with root package name */
    public float f6013j;

    /* renamed from: k, reason: collision with root package name */
    public int[] f6014k;

    /* renamed from: l, reason: collision with root package name */
    public float[] f6015l;

    /* renamed from: m, reason: collision with root package name */
    public float f6016m;

    /* renamed from: n, reason: collision with root package name */
    public float f6017n;

    /* renamed from: o, reason: collision with root package name */
    public float f6018o;

    /* renamed from: p, reason: collision with root package name */
    public float f6019p;

    /* renamed from: q, reason: collision with root package name */
    public Bitmap f6020q;

    /* renamed from: r, reason: collision with root package name */
    public BitmapShader f6021r;

    /* renamed from: s, reason: collision with root package name */
    public Matrix f6022s;

    /* renamed from: t, reason: collision with root package name */
    public int f6023t;

    /* renamed from: u, reason: collision with root package name */
    public BitmapShader f6024u;

    /* renamed from: v, reason: collision with root package name */
    public float f6025v;

    public SmartCornerView(Context context) {
        super(context);
        this.f6004a = 0;
        this.f6023t = 0;
        this.f6025v = 1.0f;
        b();
    }

    public SmartCornerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6004a = 0;
        this.f6023t = 0;
        this.f6025v = 1.0f;
        b();
    }

    public final void a(Canvas canvas, RectF rectF, Paint paint) {
        int i7 = this.f6004a;
        canvas.drawArc(rectF, i7 != 0 ? i7 != 1 ? i7 != 2 ? CropImageView.DEFAULT_ASPECT_RATIO : 90.0f : 270.0f : 180.0f, 90.0f, false, paint);
    }

    public final void b() {
        Paint paint = new Paint(1);
        this.f6005b = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        Paint paint2 = new Paint(1);
        this.f6006c = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint(1);
        this.f6007d = paint3;
        paint3.setStyle(style);
        Paint paint4 = new Paint(1);
        this.f6008e = paint4;
        paint4.setStyle(Paint.Style.STROKE);
        this.f6009f = new Path();
        this.f6010g = new RectF();
        this.f6011h = new RectF();
        this.f6022s = new Matrix();
        this.f6012i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6013j = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void c() {
        this.f6009f.reset();
        if (this.f6010g.isEmpty()) {
            return;
        }
        Path path = this.f6009f;
        RectF rectF = this.f6010g;
        Path.Direction direction = Path.Direction.CW;
        path.addRect(rectF, direction);
        Path path2 = new Path();
        float f7 = this.f6012i;
        float width = this.f6010g.width();
        float height = this.f6010g.height();
        int i7 = this.f6004a;
        if (i7 == 0) {
            this.f6011h.set(width - f7, height - f7, width + f7, height + f7);
        } else if (i7 == 1) {
            this.f6011h.set(CropImageView.DEFAULT_ASPECT_RATIO - f7, height - f7, CropImageView.DEFAULT_ASPECT_RATIO + f7, height + f7);
        } else if (i7 == 2) {
            this.f6011h.set(width - f7, CropImageView.DEFAULT_ASPECT_RATIO - f7, width + f7, CropImageView.DEFAULT_ASPECT_RATIO + f7);
        } else if (i7 == 3) {
            RectF rectF2 = this.f6011h;
            float f8 = CropImageView.DEFAULT_ASPECT_RATIO - f7;
            float f9 = CropImageView.DEFAULT_ASPECT_RATIO + f7;
            rectF2.set(f8, f8, f9, f9);
        }
        path2.addRoundRect(this.f6011h, f7, f7, direction);
        this.f6009f.op(path2, Path.Op.DIFFERENCE);
    }

    public final void d() {
        if (this.f6014k != null) {
            float f7 = this.f6016m;
            if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
                float f8 = this.f6017n;
                if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    return;
                }
                float f9 = this.f6018o;
                float f10 = this.f6019p;
                LinearGradient linearGradient = new LinearGradient(-f9, (-f10) + f8, (-f9) + f7, -f10, this.f6014k, this.f6015l, Shader.TileMode.CLAMP);
                this.f6005b.setShader(linearGradient);
                this.f6008e.setShader(linearGradient);
            }
        }
    }

    public final void e(boolean z6) {
        float f7;
        float f8;
        if (this.f6020q != null) {
            float f9 = this.f6016m;
            float f10 = CropImageView.DEFAULT_ASPECT_RATIO;
            if (f9 > CropImageView.DEFAULT_ASPECT_RATIO && this.f6017n > CropImageView.DEFAULT_ASPECT_RATIO) {
                if (z6 || this.f6021r == null) {
                    Bitmap bitmap = this.f6020q;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    this.f6021r = new BitmapShader(bitmap, tileMode, tileMode);
                }
                BitmapShader bitmapShader = this.f6021r;
                Bitmap bitmap2 = this.f6020q;
                this.f6022s.reset();
                float width = bitmap2.getWidth();
                float height = bitmap2.getHeight();
                float f11 = this.f6016m;
                float f12 = this.f6017n;
                if (width * f12 > f11 * height) {
                    float f13 = f12 / height;
                    float f14 = (f11 - (width * f13)) * 0.5f;
                    f7 = f13;
                    f8 = 0.0f;
                    f10 = f14;
                } else {
                    f7 = f11 / width;
                    f8 = (f12 - (height * f7)) * 0.5f;
                }
                this.f6022s.setScale(f7, f7);
                this.f6022s.postTranslate(f10, f8);
                this.f6022s.postTranslate(-this.f6018o, -this.f6019p);
                bitmapShader.setLocalMatrix(this.f6022s);
                this.f6005b.setShader(this.f6021r);
                this.f6008e.setShader(this.f6021r);
                return;
            }
        }
        d();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BitmapShader bitmapShader = this.f6024u;
        int i7 = 255;
        if (bitmapShader != null && this.f6025v < 1.0f) {
            this.f6006c.setShader(bitmapShader);
            this.f6006c.setAlpha(255);
            canvas.drawPath(this.f6009f, this.f6006c);
            if (this.f6013j > CropImageView.DEFAULT_ASPECT_RATIO) {
                this.f6008e.setShader(this.f6024u);
                this.f6008e.setAlpha(255);
                a(canvas, this.f6011h, this.f6008e);
            }
        }
        if (this.f6020q != null && this.f6024u != null) {
            i7 = (int) (this.f6025v * 255.0f);
        }
        this.f6005b.setAlpha(i7);
        this.f6008e.setAlpha(i7);
        if (this.f6020q != null) {
            this.f6005b.setShader(this.f6021r);
            this.f6008e.setShader(this.f6021r);
        } else if (this.f6014k == null) {
            return;
        }
        if (i7 > 0) {
            canvas.drawPath(this.f6009f, this.f6005b);
            if (this.f6013j > CropImageView.DEFAULT_ASPECT_RATIO) {
                a(canvas, this.f6011h, this.f6008e);
            }
        }
        int i8 = this.f6023t;
        if (i8 != 0) {
            this.f6007d.setColor(i8);
            Paint paint = this.f6007d;
            Paint.Style style = Paint.Style.FILL;
            paint.setStyle(style);
            canvas.drawPath(this.f6009f, this.f6007d);
            if (this.f6013j > CropImageView.DEFAULT_ASPECT_RATIO) {
                this.f6007d.setStyle(Paint.Style.STROKE);
                this.f6007d.setStrokeWidth(this.f6013j);
                a(canvas, this.f6011h, this.f6007d);
                this.f6007d.setStyle(style);
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.f6010g.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7, i8);
        c();
        if (this.f6020q != null) {
            e(false);
        } else {
            d();
        }
    }

    public void setCornerType(int i7) {
        this.f6004a = i7;
        c();
        invalidate();
    }

    public void setGradientConfig(int[] iArr, float[] fArr, float f7, float f8, float f9, float f10) {
        this.f6014k = iArr;
        this.f6015l = fArr;
        this.f6016m = f7;
        this.f6017n = f8;
        this.f6018o = f9;
        this.f6019p = f10;
        this.f6020q = null;
        d();
        invalidate();
    }

    public void setOverlayColor(int i7) {
        this.f6023t = i7;
        invalidate();
    }

    public void setRadius(float f7, float f8, int i7) {
        this.f6012i = f7;
        this.f6013j = f8;
        this.f6008e.setStrokeWidth(f8);
        this.f6008e.setColor(i7);
        c();
        invalidate();
    }

    public void setTransitionProgress(float f7) {
        float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(1.0f, f7));
        this.f6025v = max;
        if (max >= 1.0f) {
            this.f6024u = null;
        }
        invalidate();
    }

    public void setWallpaperConfig(Bitmap bitmap, float f7, float f8, float f9, float f10) {
        Bitmap bitmap2 = this.f6020q;
        boolean z6 = bitmap != bitmap2;
        boolean z7 = (f7 == this.f6016m && f8 == this.f6017n && f9 == this.f6018o && f10 == this.f6019p) ? false : true;
        if (z6 || z7) {
            if (bitmap2 != null && z6) {
                this.f6024u = this.f6021r;
                this.f6025v = CropImageView.DEFAULT_ASPECT_RATIO;
            } else if (z6) {
                this.f6024u = null;
                this.f6025v = 1.0f;
            }
            this.f6020q = bitmap;
            this.f6016m = f7;
            this.f6017n = f8;
            this.f6018o = f9;
            this.f6019p = f10;
            e(z6);
            invalidate();
        }
    }
}
