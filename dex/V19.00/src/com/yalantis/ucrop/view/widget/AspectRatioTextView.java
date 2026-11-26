package com.yalantis.ucrop.view.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.yalantis.ucrop.model.AspectRatio;
import com.yalantis.ucrop.view.CropImageView;
import g0.d;
import g0.g;
import java.util.Locale;
import t4.b;
import t4.c;
import t4.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AspectRatioTextView extends AppCompatTextView {

    /* renamed from: h, reason: collision with root package name */
    public final Rect f6220h;

    /* renamed from: i, reason: collision with root package name */
    public final Paint f6221i;

    /* renamed from: j, reason: collision with root package name */
    public final int f6222j;

    /* renamed from: k, reason: collision with root package name */
    public float f6223k;

    /* renamed from: l, reason: collision with root package name */
    public String f6224l;

    /* renamed from: m, reason: collision with root package name */
    public float f6225m;

    /* renamed from: n, reason: collision with root package name */
    public float f6226n;

    public AspectRatioTextView(Context context) {
        this(context, null);
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioTextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f6220h = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ucrop_AspectRatioTextView);
        setGravity(1);
        this.f6224l = obtainStyledAttributes.getString(i.ucrop_AspectRatioTextView_ucrop_artv_ratio_title);
        this.f6225m = obtainStyledAttributes.getFloat(i.ucrop_AspectRatioTextView_ucrop_artv_ratio_x, CropImageView.DEFAULT_ASPECT_RATIO);
        float f7 = obtainStyledAttributes.getFloat(i.ucrop_AspectRatioTextView_ucrop_artv_ratio_y, CropImageView.DEFAULT_ASPECT_RATIO);
        this.f6226n = f7;
        float f8 = this.f6225m;
        if (f8 == CropImageView.DEFAULT_ASPECT_RATIO || f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f6223k = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.f6223k = f8 / f7;
        }
        this.f6222j = getContext().getResources().getDimensionPixelSize(c.ucrop_size_dot_scale_text_view);
        Paint paint = new Paint(1);
        this.f6221i = paint;
        paint.setStyle(Paint.Style.FILL);
        n();
        m(getResources().getColor(b.ucrop_color_widget_active));
        obtainStyledAttributes.recycle();
    }

    public final void m(int i7) {
        Paint paint = this.f6221i;
        if (paint != null) {
            paint.setColor(i7);
        }
        int[][] iArr = {new int[]{R.attr.state_selected}, new int[]{0}};
        Context context = getContext();
        int i8 = b.ucrop_color_widget;
        Object obj = g.f6423a;
        setTextColor(new ColorStateList(iArr, new int[]{i7, d.a(context, i8)}));
    }

    public final void n() {
        if (TextUtils.isEmpty(this.f6224l)) {
            setText(String.format(Locale.US, "%d:%d", Integer.valueOf((int) this.f6225m), Integer.valueOf((int) this.f6226n)));
        } else {
            setText(this.f6224l);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSelected()) {
            canvas.getClipBounds(this.f6220h);
            float f7 = (r0.right - r0.left) / 2.0f;
            float f8 = r0.bottom - (r0.top / 2.0f);
            int i7 = this.f6222j;
            canvas.drawCircle(f7, f8 - (i7 * 1.5f), i7 / 2.0f, this.f6221i);
        }
    }

    public void setActiveColor(int i7) {
        m(i7);
        invalidate();
    }

    public void setAspectRatio(AspectRatio aspectRatio) {
        this.f6224l = aspectRatio.f6166a;
        float f7 = aspectRatio.f6167b;
        this.f6225m = f7;
        float f8 = aspectRatio.f6168c;
        this.f6226n = f8;
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO || f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f6223k = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            this.f6223k = f7 / f8;
        }
        n();
    }
}
