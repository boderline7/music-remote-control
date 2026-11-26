package com.yalantis.ucrop.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.CropImageView;
import com.yalantis.ucrop.view.GestureCropImageView;
import g0.d;
import g0.g;
import java.util.Locale;
import t4.b;
import t4.c;
import t4.k;
import z4.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class HorizontalProgressWheelView extends View {

    /* renamed from: a, reason: collision with root package name */
    public final Rect f6227a;

    /* renamed from: b, reason: collision with root package name */
    public a f6228b;

    /* renamed from: c, reason: collision with root package name */
    public float f6229c;

    /* renamed from: d, reason: collision with root package name */
    public final Paint f6230d;

    /* renamed from: e, reason: collision with root package name */
    public final Paint f6231e;

    /* renamed from: f, reason: collision with root package name */
    public final int f6232f;

    /* renamed from: g, reason: collision with root package name */
    public final int f6233g;

    /* renamed from: h, reason: collision with root package name */
    public final int f6234h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f6235i;

    /* renamed from: j, reason: collision with root package name */
    public float f6236j;

    /* renamed from: k, reason: collision with root package name */
    public int f6237k;

    public HorizontalProgressWheelView(Context context) {
        this(context, null);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalProgressWheelView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6227a = new Rect();
        Context context2 = getContext();
        int i8 = b.ucrop_color_widget_rotate_mid_line;
        Object obj = g.f6423a;
        this.f6237k = d.a(context2, i8);
        this.f6232f = getContext().getResources().getDimensionPixelSize(c.ucrop_width_horizontal_wheel_progress_line);
        this.f6233g = getContext().getResources().getDimensionPixelSize(c.ucrop_height_horizontal_wheel_progress_line);
        this.f6234h = getContext().getResources().getDimensionPixelSize(c.ucrop_margin_horizontal_wheel_progress_line);
        Paint paint = new Paint(1);
        this.f6230d = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f6230d.setStrokeWidth(this.f6232f);
        this.f6230d.setColor(getResources().getColor(b.ucrop_color_progress_wheel_line));
        Paint paint2 = new Paint(this.f6230d);
        this.f6231e = paint2;
        paint2.setColor(this.f6237k);
        this.f6231e.setStrokeCap(Paint.Cap.ROUND);
        this.f6231e.setStrokeWidth(getContext().getResources().getDimensionPixelSize(c.ucrop_width_middle_wheel_progress_line));
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = this.f6227a;
        canvas.getClipBounds(rect);
        int width = rect.width() / (this.f6232f + this.f6234h);
        float f7 = this.f6236j % (r3 + r2);
        for (int i7 = 0; i7 < width; i7++) {
            int i8 = width / 4;
            if (i7 < i8) {
                this.f6230d.setAlpha((int) ((i7 / i8) * 255.0f));
            } else if (i7 > (width * 3) / 4) {
                this.f6230d.setAlpha((int) (((width - i7) / i8) * 255.0f));
            } else {
                this.f6230d.setAlpha(255);
            }
            float f8 = -f7;
            canvas.drawLine(rect.left + f8 + ((this.f6232f + this.f6234h) * i7), rect.centerY() - (this.f6233g / 4.0f), f8 + rect.left + ((this.f6232f + this.f6234h) * i7), (this.f6233g / 4.0f) + rect.centerY(), this.f6230d);
        }
        canvas.drawLine(rect.centerX(), rect.centerY() - (this.f6233g / 2.0f), rect.centerX(), (this.f6233g / 2.0f) + rect.centerY(), this.f6231e);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f6229c = motionEvent.getX();
        } else if (action == 1) {
            a aVar = this.f6228b;
            if (aVar != null) {
                this.f6235i = false;
                k kVar = (k) aVar;
                int i7 = kVar.f9086a;
                UCropActivity uCropActivity = kVar.f9087b;
                switch (i7) {
                    case 1:
                        uCropActivity.f6151l.setImageToWrapCropBounds();
                        break;
                    default:
                        uCropActivity.f6151l.setImageToWrapCropBounds();
                        break;
                }
            }
        } else if (action == 2) {
            float x6 = motionEvent.getX() - this.f6229c;
            if (x6 != CropImageView.DEFAULT_ASPECT_RATIO) {
                if (!this.f6235i) {
                    this.f6235i = true;
                    a aVar2 = this.f6228b;
                    if (aVar2 != null) {
                        k kVar2 = (k) aVar2;
                        int i8 = kVar2.f9086a;
                        UCropActivity uCropActivity2 = kVar2.f9087b;
                        switch (i8) {
                            case 1:
                                uCropActivity2.f6151l.h();
                                break;
                            default:
                                uCropActivity2.f6151l.h();
                                break;
                        }
                    }
                }
                this.f6236j -= x6;
                postInvalidate();
                this.f6229c = motionEvent.getX();
                a aVar3 = this.f6228b;
                if (aVar3 != null) {
                    float f7 = -x6;
                    k kVar3 = (k) aVar3;
                    int i9 = kVar3.f9086a;
                    UCropActivity uCropActivity3 = kVar3.f9087b;
                    switch (i9) {
                        case 1:
                            GestureCropImageView gestureCropImageView = uCropActivity3.f6151l;
                            float f8 = f7 / 42.0f;
                            RectF rectF = gestureCropImageView.f6169u;
                            float centerX = rectF.centerX();
                            float centerY = rectF.centerY();
                            if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
                                Matrix matrix = gestureCropImageView.f6204g;
                                matrix.postRotate(f8, centerX, centerY);
                                gestureCropImageView.setImageMatrix(matrix);
                                y4.d dVar = gestureCropImageView.f6207j;
                                if (dVar != null) {
                                    float[] fArr = gestureCropImageView.f6203f;
                                    matrix.getValues(fArr);
                                    double d7 = fArr[1];
                                    matrix.getValues(fArr);
                                    float f9 = (float) (-(Math.atan2(d7, fArr[0]) * 57.29577951308232d));
                                    TextView textView = ((k) dVar).f9087b.f6160u;
                                    if (textView != null) {
                                        textView.setText(String.format(Locale.getDefault(), "%.1f°", Float.valueOf(f9)));
                                        break;
                                    }
                                }
                            }
                            break;
                        default:
                            if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                                GestureCropImageView gestureCropImageView2 = uCropActivity3.f6151l;
                                float maxScale = (((uCropActivity3.f6151l.getMaxScale() - uCropActivity3.f6151l.getMinScale()) / 15000.0f) * f7) + gestureCropImageView2.getCurrentScale();
                                RectF rectF2 = gestureCropImageView2.f6169u;
                                float centerX2 = rectF2.centerX();
                                float centerY2 = rectF2.centerY();
                                if (maxScale >= gestureCropImageView2.getMinScale()) {
                                    gestureCropImageView2.j(maxScale / gestureCropImageView2.getCurrentScale(), centerX2, centerY2);
                                    break;
                                }
                            } else {
                                GestureCropImageView gestureCropImageView3 = uCropActivity3.f6151l;
                                float maxScale2 = (((uCropActivity3.f6151l.getMaxScale() - uCropActivity3.f6151l.getMinScale()) / 15000.0f) * f7) + gestureCropImageView3.getCurrentScale();
                                RectF rectF3 = gestureCropImageView3.f6169u;
                                gestureCropImageView3.k(maxScale2, rectF3.centerX(), rectF3.centerY());
                                break;
                            }
                            break;
                    }
                }
            }
        }
        return true;
    }

    public void setMiddleLineColor(int i7) {
        this.f6237k = i7;
        this.f6231e.setColor(i7);
        invalidate();
    }

    public void setScrollingListener(a aVar) {
        this.f6228b = aVar;
    }
}
