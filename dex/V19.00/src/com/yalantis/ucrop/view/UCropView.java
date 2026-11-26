package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import t4.b;
import t4.c;
import t4.e;
import t4.f;
import t4.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class UCropView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public final GestureCropImageView f6218a;

    /* renamed from: b, reason: collision with root package name */
    public final OverlayView f6219b;

    public UCropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UCropView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(context).inflate(f.ucrop_view, (ViewGroup) this, true);
        GestureCropImageView gestureCropImageView = (GestureCropImageView) findViewById(e.image_view_crop);
        this.f6218a = gestureCropImageView;
        OverlayView overlayView = (OverlayView) findViewById(e.view_overlay);
        this.f6219b = overlayView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, i.ucrop_UCropView);
        overlayView.getClass();
        overlayView.f6186l = obtainStyledAttributes.getBoolean(i.ucrop_UCropView_ucrop_circle_dimmed_layer, false);
        int color = obtainStyledAttributes.getColor(i.ucrop_UCropView_ucrop_dimmed_color, overlayView.getResources().getColor(b.ucrop_color_default_dimmed));
        overlayView.f6187m = color;
        Paint paint = overlayView.f6189o;
        paint.setColor(color);
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        paint.setStrokeWidth(1.0f);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(i.ucrop_UCropView_ucrop_frame_stroke_size, overlayView.getResources().getDimensionPixelSize(c.ucrop_default_crop_frame_stoke_width));
        int color2 = obtainStyledAttributes.getColor(i.ucrop_UCropView_ucrop_frame_color, overlayView.getResources().getColor(b.ucrop_color_default_crop_frame));
        Paint paint2 = overlayView.f6191q;
        paint2.setStrokeWidth(dimensionPixelSize);
        paint2.setColor(color2);
        paint2.setStyle(style);
        Paint paint3 = overlayView.f6192r;
        paint3.setStrokeWidth(dimensionPixelSize * 3);
        paint3.setColor(color2);
        paint3.setStyle(style);
        overlayView.f6184j = obtainStyledAttributes.getBoolean(i.ucrop_UCropView_ucrop_show_frame, true);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(i.ucrop_UCropView_ucrop_grid_stroke_size, overlayView.getResources().getDimensionPixelSize(c.ucrop_default_crop_grid_stoke_width));
        int color3 = obtainStyledAttributes.getColor(i.ucrop_UCropView_ucrop_grid_color, overlayView.getResources().getColor(b.ucrop_color_default_crop_grid));
        Paint paint4 = overlayView.f6190p;
        paint4.setStrokeWidth(dimensionPixelSize2);
        paint4.setColor(color3);
        overlayView.f6180f = obtainStyledAttributes.getInt(i.ucrop_UCropView_ucrop_grid_row_count, 2);
        overlayView.f6181g = obtainStyledAttributes.getInt(i.ucrop_UCropView_ucrop_grid_column_count, 2);
        overlayView.f6185k = obtainStyledAttributes.getBoolean(i.ucrop_UCropView_ucrop_show_grid, true);
        gestureCropImageView.getClass();
        float abs = Math.abs(obtainStyledAttributes.getFloat(i.ucrop_UCropView_ucrop_aspect_ratio_x, CropImageView.DEFAULT_ASPECT_RATIO));
        float abs2 = Math.abs(obtainStyledAttributes.getFloat(i.ucrop_UCropView_ucrop_aspect_ratio_y, CropImageView.DEFAULT_ASPECT_RATIO));
        if (abs == CropImageView.DEFAULT_ASPECT_RATIO || abs2 == CropImageView.DEFAULT_ASPECT_RATIO) {
            gestureCropImageView.f6171w = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            gestureCropImageView.f6171w = abs / abs2;
        }
        obtainStyledAttributes.recycle();
        gestureCropImageView.setCropBoundsChangeListener(new y4.e(this));
        overlayView.setOverlayViewChangeListener(new y4.e(this));
    }

    public GestureCropImageView getCropImageView() {
        return this.f6218a;
    }

    public OverlayView getOverlayView() {
        return this.f6219b;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
