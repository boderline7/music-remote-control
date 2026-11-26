package com.yalantis.ucrop.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import n1.z;
import t4.c;
import u4.b;
import y4.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class OverlayView extends View {
    public static final boolean DEFAULT_CIRCLE_DIMMED_LAYER = false;
    public static final int DEFAULT_CROP_GRID_COLUMN_COUNT = 2;
    public static final int DEFAULT_CROP_GRID_ROW_COUNT = 2;
    public static final int DEFAULT_FREESTYLE_CROP_MODE = 0;
    public static final boolean DEFAULT_SHOW_CROP_FRAME = true;
    public static final boolean DEFAULT_SHOW_CROP_GRID = true;
    public static final int FREESTYLE_CROP_MODE_DISABLE = 0;
    public static final int FREESTYLE_CROP_MODE_ENABLE = 1;
    public static final int FREESTYLE_CROP_MODE_ENABLE_WITH_PASS_THROUGH = 2;
    public boolean A;

    /* renamed from: a, reason: collision with root package name */
    public final RectF f6175a;

    /* renamed from: b, reason: collision with root package name */
    public final RectF f6176b;

    /* renamed from: c, reason: collision with root package name */
    public int f6177c;

    /* renamed from: d, reason: collision with root package name */
    public int f6178d;

    /* renamed from: e, reason: collision with root package name */
    public float[] f6179e;

    /* renamed from: f, reason: collision with root package name */
    public int f6180f;

    /* renamed from: g, reason: collision with root package name */
    public int f6181g;

    /* renamed from: h, reason: collision with root package name */
    public float f6182h;

    /* renamed from: i, reason: collision with root package name */
    public float[] f6183i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f6184j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f6185k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f6186l;

    /* renamed from: m, reason: collision with root package name */
    public int f6187m;

    /* renamed from: n, reason: collision with root package name */
    public final Path f6188n;

    /* renamed from: o, reason: collision with root package name */
    public final Paint f6189o;

    /* renamed from: p, reason: collision with root package name */
    public final Paint f6190p;

    /* renamed from: q, reason: collision with root package name */
    public final Paint f6191q;

    /* renamed from: r, reason: collision with root package name */
    public final Paint f6192r;

    /* renamed from: s, reason: collision with root package name */
    public int f6193s;

    /* renamed from: t, reason: collision with root package name */
    public float f6194t;

    /* renamed from: u, reason: collision with root package name */
    public float f6195u;

    /* renamed from: v, reason: collision with root package name */
    public int f6196v;

    /* renamed from: w, reason: collision with root package name */
    public final int f6197w;

    /* renamed from: x, reason: collision with root package name */
    public final int f6198x;

    /* renamed from: y, reason: collision with root package name */
    public final int f6199y;

    /* renamed from: z, reason: collision with root package name */
    public b f6200z;

    public OverlayView(Context context) {
        this(context, null);
    }

    public OverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OverlayView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6175a = new RectF();
        this.f6176b = new RectF();
        this.f6183i = null;
        this.f6188n = new Path();
        this.f6189o = new Paint(1);
        this.f6190p = new Paint(1);
        this.f6191q = new Paint(1);
        this.f6192r = new Paint(1);
        this.f6193s = 0;
        this.f6194t = -1.0f;
        this.f6195u = -1.0f;
        this.f6196v = -1;
        this.f6197w = getResources().getDimensionPixelSize(c.ucrop_default_crop_rect_corner_touch_threshold);
        this.f6198x = getResources().getDimensionPixelSize(c.ucrop_default_crop_rect_min_size);
        this.f6199y = getResources().getDimensionPixelSize(c.ucrop_default_crop_rect_corner_touch_area_line_length);
    }

    public final void a() {
        RectF rectF = this.f6175a;
        this.f6179e = z.g0(rectF);
        rectF.centerX();
        rectF.centerY();
        this.f6183i = null;
        Path path = this.f6188n;
        path.reset();
        path.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
    }

    public RectF getCropViewRect() {
        return this.f6175a;
    }

    public int getFreestyleCropMode() {
        return this.f6193s;
    }

    public b getOverlayViewChangeListener() {
        return this.f6200z;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        boolean z6 = this.f6186l;
        RectF rectF = this.f6175a;
        if (z6) {
            canvas.clipPath(this.f6188n, Region.Op.DIFFERENCE);
        } else {
            canvas.clipRect(rectF, Region.Op.DIFFERENCE);
        }
        canvas.drawColor(this.f6187m);
        canvas.restore();
        if (this.f6186l) {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, this.f6189o);
        }
        if (this.f6185k) {
            if (this.f6183i == null && !rectF.isEmpty()) {
                this.f6183i = new float[(this.f6181g * 4) + (this.f6180f * 4)];
                int i7 = 0;
                for (int i8 = 0; i8 < this.f6180f; i8++) {
                    float[] fArr = this.f6183i;
                    fArr[i7] = rectF.left;
                    float f7 = i8 + 1.0f;
                    fArr[i7 + 1] = ((f7 / (this.f6180f + 1)) * rectF.height()) + rectF.top;
                    float[] fArr2 = this.f6183i;
                    int i9 = i7 + 3;
                    fArr2[i7 + 2] = rectF.right;
                    i7 += 4;
                    fArr2[i9] = ((f7 / (this.f6180f + 1)) * rectF.height()) + rectF.top;
                }
                for (int i10 = 0; i10 < this.f6181g; i10++) {
                    float f8 = i10 + 1.0f;
                    this.f6183i[i7] = ((f8 / (this.f6181g + 1)) * rectF.width()) + rectF.left;
                    float[] fArr3 = this.f6183i;
                    fArr3[i7 + 1] = rectF.top;
                    int i11 = i7 + 3;
                    fArr3[i7 + 2] = ((f8 / (this.f6181g + 1)) * rectF.width()) + rectF.left;
                    i7 += 4;
                    this.f6183i[i11] = rectF.bottom;
                }
            }
            float[] fArr4 = this.f6183i;
            if (fArr4 != null) {
                canvas.drawLines(fArr4, this.f6190p);
            }
        }
        if (this.f6184j) {
            canvas.drawRect(rectF, this.f6191q);
        }
        if (this.f6193s != 0) {
            canvas.save();
            RectF rectF2 = this.f6176b;
            rectF2.set(rectF);
            int i12 = this.f6199y;
            float f9 = i12;
            float f10 = -i12;
            rectF2.inset(f9, f10);
            Region.Op op = Region.Op.DIFFERENCE;
            canvas.clipRect(rectF2, op);
            rectF2.set(rectF);
            rectF2.inset(f10, f9);
            canvas.clipRect(rectF2, op);
            canvas.drawRect(rectF, this.f6192r);
            canvas.restore();
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        if (z6) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            this.f6177c = width - paddingLeft;
            this.f6178d = height - paddingTop;
            if (this.A) {
                this.A = false;
                setTargetAspectRatio(this.f6182h);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instructions count: 427
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.view.OverlayView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setCircleDimmedLayer(boolean z6) {
        this.f6186l = z6;
    }

    public void setCropFrameColor(int i7) {
        this.f6191q.setColor(i7);
    }

    public void setCropFrameStrokeWidth(int i7) {
        this.f6191q.setStrokeWidth(i7);
    }

    public void setCropGridColor(int i7) {
        this.f6190p.setColor(i7);
    }

    public void setCropGridColumnCount(int i7) {
        this.f6181g = i7;
        this.f6183i = null;
    }

    public void setCropGridRowCount(int i7) {
        this.f6180f = i7;
        this.f6183i = null;
    }

    public void setCropGridStrokeWidth(int i7) {
        this.f6190p.setStrokeWidth(i7);
    }

    public void setDimmedColor(int i7) {
        this.f6187m = i7;
    }

    @Deprecated
    public void setFreestyleCropEnabled(boolean z6) {
        this.f6193s = z6 ? 1 : 0;
    }

    public void setFreestyleCropMode(int i7) {
        this.f6193s = i7;
        postInvalidate();
    }

    public void setOverlayViewChangeListener(b bVar) {
        this.f6200z = bVar;
    }

    public void setShowCropFrame(boolean z6) {
        this.f6184j = z6;
    }

    public void setShowCropGrid(boolean z6) {
        this.f6185k = z6;
    }

    public void setTargetAspectRatio(float f7) {
        this.f6182h = f7;
        if (this.f6177c <= 0) {
            this.A = true;
        } else {
            setupCropBounds();
            postInvalidate();
        }
    }

    public void setupCropBounds() {
        int i7 = this.f6177c;
        float f7 = this.f6182h;
        int i8 = (int) (i7 / f7);
        int i9 = this.f6178d;
        RectF rectF = this.f6175a;
        if (i8 > i9) {
            int i10 = (i7 - ((int) (i9 * f7))) / 2;
            rectF.set(getPaddingLeft() + i10, getPaddingTop(), getPaddingLeft() + r1 + i10, getPaddingTop() + this.f6178d);
        } else {
            int i11 = (i9 - i8) / 2;
            rectF.set(getPaddingLeft(), getPaddingTop() + i11, getPaddingLeft() + this.f6177c, getPaddingTop() + i8 + i11);
        }
        b bVar = this.f6200z;
        if (bVar != null) {
            ((e) bVar).f10209a.f6218a.setCropRect(rectF);
        }
        a();
    }
}
