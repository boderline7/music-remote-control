package com.yalantis.ucrop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import l6.f;
import r4.e;
import r4.k;
import x4.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GestureCropImageView extends CropImageView {
    public ScaleGestureDetector G;
    public c H;
    public GestureDetector I;
    public float J;
    public float K;
    public boolean L;
    public boolean M;
    public boolean N;
    public int O;

    public GestureCropImageView(Context context) {
        super(context);
        this.L = true;
        this.M = true;
        this.N = true;
        this.O = 5;
    }

    public GestureCropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GestureCropImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.L = true;
        this.M = true;
        this.N = true;
        this.O = 5;
    }

    @Override // com.yalantis.ucrop.view.TransformImageView
    public final void d() {
        super.d();
        this.I = new GestureDetector(getContext(), new e(this), null, true);
        this.G = new ScaleGestureDetector(getContext(), new k(this));
        y4.c cVar = new y4.c(this);
        c cVar2 = new c();
        cVar2.f9645i = cVar;
        cVar2.f9641e = -1;
        cVar2.f9642f = -1;
        this.H = cVar2;
    }

    public int getDoubleTapScaleSteps() {
        return this.O;
    }

    public float getDoubleTapTargetScale() {
        return getCurrentScale() * ((float) Math.pow(getMaxScale() / getMinScale(), 1.0f / this.O));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 0) {
            h();
        }
        if (motionEvent.getPointerCount() > 1) {
            this.J = (motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f;
            this.K = (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f;
        }
        if (this.N) {
            this.I.onTouchEvent(motionEvent);
        }
        if (this.M) {
            this.G.onTouchEvent(motionEvent);
        }
        if (this.L) {
            c cVar = this.H;
            cVar.getClass();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                cVar.f9639c = motionEvent.getX();
                cVar.f9640d = motionEvent.getY();
                cVar.f9641e = motionEvent.findPointerIndex(motionEvent.getPointerId(0));
                cVar.f9643g = CropImageView.DEFAULT_ASPECT_RATIO;
                cVar.f9644h = true;
            } else if (actionMasked == 1) {
                cVar.f9641e = -1;
            } else if (actionMasked != 2) {
                if (actionMasked == 5) {
                    cVar.f9637a = motionEvent.getX();
                    cVar.f9638b = motionEvent.getY();
                    cVar.f9642f = motionEvent.findPointerIndex(motionEvent.getPointerId(motionEvent.getActionIndex()));
                    cVar.f9643g = CropImageView.DEFAULT_ASPECT_RATIO;
                    cVar.f9644h = true;
                } else if (actionMasked == 6) {
                    cVar.f9642f = -1;
                }
            } else if (cVar.f9641e != -1 && cVar.f9642f != -1 && motionEvent.getPointerCount() > cVar.f9642f) {
                float x6 = motionEvent.getX(cVar.f9641e);
                float y6 = motionEvent.getY(cVar.f9641e);
                float x7 = motionEvent.getX(cVar.f9642f);
                float y7 = motionEvent.getY(cVar.f9642f);
                if (cVar.f9644h) {
                    cVar.f9643g = CropImageView.DEFAULT_ASPECT_RATIO;
                    cVar.f9644h = false;
                } else {
                    float f7 = cVar.f9637a;
                    float degrees = (((float) Math.toDegrees((float) Math.atan2(y7 - y6, x7 - x6))) % 360.0f) - (((float) Math.toDegrees((float) Math.atan2(cVar.f9638b - cVar.f9640d, f7 - cVar.f9639c))) % 360.0f);
                    cVar.f9643g = degrees;
                    if (degrees < -180.0f) {
                        cVar.f9643g = degrees + 360.0f;
                    } else if (degrees > 180.0f) {
                        cVar.f9643g = degrees - 360.0f;
                    }
                }
                f fVar = cVar.f9645i;
                if (fVar != null) {
                    fVar.f0(cVar);
                }
                cVar.f9637a = x7;
                cVar.f9638b = y7;
                cVar.f9639c = x6;
                cVar.f9640d = y6;
            }
        }
        if ((motionEvent.getAction() & 255) == 1) {
            setImageToWrapCropBounds();
        }
        return true;
    }

    public void setDoubleTapScaleSteps(int i7) {
        this.O = i7;
    }

    public void setGestureEnabled(boolean z6) {
        this.N = z6;
    }

    public void setRotateEnabled(boolean z6) {
        this.L = z6;
    }

    public void setScaleEnabled(boolean z6) {
        this.M = z6;
    }
}
