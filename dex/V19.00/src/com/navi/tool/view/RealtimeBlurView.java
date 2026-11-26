package com.navi.tool.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import c0.f;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import z2.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class RealtimeBlurView extends FrameLayout {
    public static boolean B;
    public final f A;

    /* renamed from: a, reason: collision with root package name */
    public float f5978a;

    /* renamed from: b, reason: collision with root package name */
    public final float f5979b;

    /* renamed from: c, reason: collision with root package name */
    public int f5980c;

    /* renamed from: d, reason: collision with root package name */
    public ViewGroup f5981d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f5982e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f5983f;

    /* renamed from: g, reason: collision with root package name */
    public long f5984g;

    /* renamed from: h, reason: collision with root package name */
    public long f5985h;

    /* renamed from: i, reason: collision with root package name */
    public Bitmap f5986i;

    /* renamed from: j, reason: collision with root package name */
    public Bitmap f5987j;

    /* renamed from: k, reason: collision with root package name */
    public Canvas f5988k;

    /* renamed from: l, reason: collision with root package name */
    public RenderScript f5989l;

    /* renamed from: m, reason: collision with root package name */
    public ScriptIntrinsicBlur f5990m;

    /* renamed from: n, reason: collision with root package name */
    public Allocation f5991n;

    /* renamed from: o, reason: collision with root package name */
    public Allocation f5992o;

    /* renamed from: p, reason: collision with root package name */
    public Paint f5993p;

    /* renamed from: q, reason: collision with root package name */
    public float f5994q;

    /* renamed from: r, reason: collision with root package name */
    public final Path f5995r;

    /* renamed from: s, reason: collision with root package name */
    public final Path f5996s;

    /* renamed from: t, reason: collision with root package name */
    public Paint f5997t;

    /* renamed from: u, reason: collision with root package name */
    public Paint f5998u;

    /* renamed from: v, reason: collision with root package name */
    public float f5999v;

    /* renamed from: w, reason: collision with root package name */
    public final RectF f6000w;

    /* renamed from: x, reason: collision with root package name */
    public final RectF f6001x;

    /* renamed from: y, reason: collision with root package name */
    public BitmapShader f6002y;

    /* renamed from: z, reason: collision with root package name */
    public Matrix f6003z;

    public RealtimeBlurView(Context context) {
        super(context);
        this.f5978a = 20.0f;
        this.f5979b = 4.0f;
        this.f5980c = d1.MEASURED_SIZE_MASK;
        this.f5982e = true;
        this.f5983f = false;
        this.f5984g = 0L;
        this.f5985h = 33L;
        new Rect();
        new RectF();
        this.f5994q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f5995r = new Path();
        this.f5996s = new Path();
        this.f5999v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6000w = new RectF();
        this.f6001x = new RectF();
        this.A = new f(3, this);
        a();
    }

    public RealtimeBlurView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5978a = 20.0f;
        this.f5979b = 4.0f;
        this.f5980c = d1.MEASURED_SIZE_MASK;
        this.f5982e = true;
        this.f5983f = false;
        this.f5984g = 0L;
        this.f5985h = 33L;
        new Rect();
        new RectF();
        this.f5994q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f5995r = new Path();
        this.f5996s = new Path();
        this.f5999v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6000w = new RectF();
        this.f6001x = new RectF();
        this.A = new f(3, this);
        a();
    }

    public RealtimeBlurView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f5978a = 20.0f;
        this.f5979b = 4.0f;
        this.f5980c = d1.MEASURED_SIZE_MASK;
        this.f5982e = true;
        this.f5983f = false;
        this.f5984g = 0L;
        this.f5985h = 33L;
        new Rect();
        new RectF();
        this.f5994q = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f5995r = new Path();
        this.f5996s = new Path();
        this.f5999v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f6000w = new RectF();
        this.f6001x = new RectF();
        this.A = new f(3, this);
        a();
    }

    public static void setGlobalPaused(boolean z6) {
        B = z6;
    }

    public final void a() {
        Paint paint = new Paint();
        this.f5993p = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.f5997t = paint2;
        paint2.setAntiAlias(true);
        this.f5997t.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.f5998u = paint3;
        paint3.setAntiAlias(true);
        this.f5998u.setStyle(Paint.Style.FILL);
        setWillNotDraw(false);
    }

    public final void b() {
        Allocation allocation = this.f5991n;
        if (allocation != null) {
            allocation.destroy();
            this.f5991n = null;
        }
        Allocation allocation2 = this.f5992o;
        if (allocation2 != null) {
            allocation2.destroy();
            this.f5992o = null;
        }
        ScriptIntrinsicBlur scriptIntrinsicBlur = this.f5990m;
        if (scriptIntrinsicBlur != null) {
            scriptIntrinsicBlur.destroy();
            this.f5990m = null;
        }
        RenderScript renderScript = this.f5989l;
        if (renderScript != null) {
            renderScript.destroy();
            this.f5989l = null;
        }
        Bitmap bitmap = this.f5986i;
        if (bitmap != null) {
            bitmap.recycle();
            this.f5986i = null;
        }
        Bitmap bitmap2 = this.f5987j;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.f5987j = null;
        }
    }

    public final void c() {
        if (B) {
            return;
        }
        if (this.f5978a == CropImageView.DEFAULT_ASPECT_RATIO) {
            b();
            return;
        }
        if (this.f5989l == null) {
            try {
                RenderScript create = RenderScript.create(getContext());
                this.f5989l = create;
                ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
                this.f5990m = create2;
                create2.setRadius(this.f5978a);
            } catch (Exception | OutOfMemoryError unused) {
                return;
            }
        }
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        float f7 = this.f5979b;
        int max = Math.max(1, (int) (width / f7));
        int max2 = Math.max(1, (int) (height / f7));
        Bitmap bitmap = this.f5986i;
        if (bitmap == null || bitmap.getWidth() != max || this.f5986i.getHeight() != max2) {
            Bitmap bitmap2 = this.f5986i;
            if (bitmap2 != null) {
                bitmap2.recycle();
            }
            Bitmap bitmap3 = this.f5987j;
            if (bitmap3 != null) {
                bitmap3.recycle();
            }
            Allocation allocation = this.f5991n;
            if (allocation != null) {
                allocation.destroy();
                this.f5991n = null;
            }
            Allocation allocation2 = this.f5992o;
            if (allocation2 != null) {
                allocation2.destroy();
                this.f5992o = null;
            }
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap createBitmap = Bitmap.createBitmap(max, max2, config);
            this.f5986i = createBitmap;
            if (createBitmap == null) {
                return;
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(max, max2, config);
            this.f5987j = createBitmap2;
            if (createBitmap2 == null) {
                return;
            }
            Bitmap bitmap4 = this.f5987j;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f6002y = new BitmapShader(bitmap4, tileMode, tileMode);
            this.f6003z = new Matrix();
            this.f5988k = new Canvas(this.f5986i);
            Allocation createFromBitmap = Allocation.createFromBitmap(this.f5989l, this.f5986i, Allocation.MipmapControl.MIPMAP_NONE, 1);
            this.f5991n = createFromBitmap;
            this.f5992o = Allocation.createTyped(this.f5989l, createFromBitmap.getType());
        }
        if (this.f5981d == null || this.f5983f) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.f5984g < this.f5985h) {
            return;
        }
        this.f5984g = uptimeMillis;
        this.f5983f = true;
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        this.f5981d.getLocationOnScreen(iArr2);
        int i7 = iArr[0] - iArr2[0];
        int i8 = iArr[1] - iArr2[1];
        this.f5988k.save();
        float f8 = 1.0f / f7;
        this.f5988k.scale(f8, f8);
        this.f5988k.translate(-i7, -i8);
        try {
            this.f5981d.draw(this.f5988k);
        } catch (Exception unused2) {
        }
        this.f5988k.restore();
        this.f5991n.copyFrom(this.f5986i);
        this.f5990m.setInput(this.f5991n);
        this.f5990m.forEach(this.f5992o);
        this.f5992o.copyTo(this.f5987j);
        this.f5983f = false;
    }

    public final void d() {
        Path path = this.f5995r;
        path.reset();
        Path path2 = this.f5996s;
        path2.reset();
        float width = getWidth();
        float height = getHeight();
        RectF rectF = this.f6000w;
        rectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height);
        float f7 = this.f5994q;
        if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
            path.addRoundRect(rectF, f7, f7, Path.Direction.CW);
        } else {
            path.addRect(rectF, Path.Direction.CW);
        }
        float f8 = this.f5999v;
        if (f8 > CropImageView.DEFAULT_ASPECT_RATIO || this.f5994q > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f9 = f8 > CropImageView.DEFAULT_ASPECT_RATIO ? f8 / 2.0f : 0.0f;
            RectF rectF2 = this.f6001x;
            rectF2.set(f9, f9, getWidth() - f9, getHeight() - f9);
            float f10 = this.f5994q;
            if (f10 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                path2.addRect(rectF2, Path.Direction.CW);
            } else {
                float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f10 - f9);
                path2.addRoundRect(rectF2, max, max, Path.Direction.CW);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.f5983f) {
            return;
        }
        if (this.f5994q <= CropImageView.DEFAULT_ASPECT_RATIO) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.f5995r);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup viewGroup = this.f5981d;
        if (viewGroup != null) {
            viewGroup.getViewTreeObserver().addOnPreDrawListener(this.A);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewGroup viewGroup = this.f5981d;
        if (viewGroup != null) {
            viewGroup.getViewTreeObserver().removeOnPreDrawListener(this.A);
        }
        b();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.f5983f) {
            return;
        }
        float f7 = this.f5994q;
        Path path = this.f5995r;
        if (f7 > CropImageView.DEFAULT_ASPECT_RATIO) {
            canvas.save();
            canvas.clipPath(path);
        }
        if (this.f5982e && this.f5987j != null) {
            float f8 = this.f5994q;
            float f9 = this.f5979b;
            if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO || this.f6002y == null) {
                canvas.save();
                canvas.scale(f9, f9);
                canvas.drawBitmap(this.f5987j, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.f5993p);
                canvas.restore();
            } else {
                if (this.f6003z == null) {
                    this.f6003z = new Matrix();
                }
                this.f6003z.setScale(f9, f9);
                this.f6002y.setLocalMatrix(this.f6003z);
                this.f5993p.setShader(this.f6002y);
                canvas.drawPath(path, this.f5993p);
                this.f5993p.setShader(null);
            }
        }
        int i7 = this.f5980c;
        if (i7 != 0) {
            if (this.f5994q > CropImageView.DEFAULT_ASPECT_RATIO) {
                canvas.drawPath(path, this.f5998u);
            } else {
                canvas.drawColor(i7);
            }
        }
        super.onDraw(canvas);
        if (this.f5994q > CropImageView.DEFAULT_ASPECT_RATIO) {
            canvas.restore();
        }
        if (this.f5999v <= CropImageView.DEFAULT_ASPECT_RATIO || this.f5997t.getColor() == 0) {
            return;
        }
        if (this.f5994q > CropImageView.DEFAULT_ASPECT_RATIO) {
            canvas.drawPath(this.f5996s, this.f5997t);
        } else {
            float f10 = this.f5999v / 2.0f;
            canvas.drawRect(f10, f10, getWidth() - f10, getHeight() - f10, this.f5997t);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        d();
    }

    public void setBlurEnabled(boolean z6) {
        if (this.f5982e == z6) {
            return;
        }
        this.f5982e = z6;
        if (z6) {
            c();
        }
        invalidate();
    }

    public void setBlurRadius(float f7) {
        float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(25.0f, f7));
        if (this.f5978a == max) {
            return;
        }
        this.f5978a = max;
        ScriptIntrinsicBlur scriptIntrinsicBlur = this.f5990m;
        if (scriptIntrinsicBlur != null && max > CropImageView.DEFAULT_ASPECT_RATIO) {
            scriptIntrinsicBlur.setRadius(max);
        }
        invalidate();
    }

    public void setBorder(float f7, int i7) {
        if (this.f5999v == f7 && this.f5997t.getColor() == i7) {
            return;
        }
        this.f5999v = f7;
        this.f5997t.setStrokeWidth(f7);
        this.f5997t.setColor(i7);
        d();
        invalidate();
    }

    public void setCornerRadius(float f7) {
        if (this.f5994q == f7) {
            return;
        }
        this.f5994q = f7;
        d();
        if (this.f5994q > CropImageView.DEFAULT_ASPECT_RATIO) {
            setOutlineProvider(new c(3, this));
            setClipToOutline(true);
        } else {
            setClipToOutline(false);
            setOutlineProvider(null);
        }
        invalidate();
    }

    public void setMinUpdateIntervalMs(long j7) {
        this.f5985h = Math.max(16L, j7);
    }

    public void setOverlayColor(int i7) {
        if (this.f5980c == i7) {
            return;
        }
        this.f5980c = i7;
        this.f5998u.setColor(i7);
        invalidate();
    }

    public void setupWith(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = this.f5981d;
        if (viewGroup2 != viewGroup) {
            f fVar = this.A;
            if (viewGroup2 != null) {
                viewGroup2.getViewTreeObserver().removeOnPreDrawListener(fVar);
            }
            this.f5981d = viewGroup;
            if (viewGroup != null) {
                viewGroup.getViewTreeObserver().addOnPreDrawListener(fVar);
            }
        }
    }
}
