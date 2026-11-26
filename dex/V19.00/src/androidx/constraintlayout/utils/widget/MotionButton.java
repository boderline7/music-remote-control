package androidx.constraintlayout.utils.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import com.yalantis.ucrop.view.CropImageView;
import z.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionButton extends AppCompatButton {

    /* renamed from: d, reason: collision with root package name */
    public float f1636d;

    /* renamed from: e, reason: collision with root package name */
    public float f1637e;

    /* renamed from: f, reason: collision with root package name */
    public Path f1638f;

    /* renamed from: g, reason: collision with root package name */
    public d f1639g;

    /* renamed from: h, reason: collision with root package name */
    public RectF f1640h;

    public MotionButton(Context context) {
        super(context);
        this.f1636d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1637e = Float.NaN;
        a(null);
    }

    public MotionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1636d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1637e = Float.NaN;
        a(attributeSet);
    }

    public MotionButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1636d = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1637e = Float.NaN;
        a(attributeSet);
    }

    public final void a(AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ImageFilterView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ImageFilterView_round) {
                    setRound(obtainStyledAttributes.getDimension(index, CropImageView.DEFAULT_ASPECT_RATIO));
                } else if (index == t.ImageFilterView_roundPercent) {
                    setRoundPercent(obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO));
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public float getRound() {
        return this.f1637e;
    }

    public float getRoundPercent() {
        return this.f1636d;
    }

    public void setRound(float f7) {
        if (Float.isNaN(f7)) {
            this.f1637e = f7;
            float f8 = this.f1636d;
            this.f1636d = -1.0f;
            setRoundPercent(f8);
            return;
        }
        boolean z6 = this.f1637e != f7;
        this.f1637e = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1638f == null) {
                this.f1638f = new Path();
            }
            if (this.f1640h == null) {
                this.f1640h = new RectF();
            }
            if (this.f1639g == null) {
                d dVar = new d(this, 1);
                this.f1639g = dVar;
                setOutlineProvider(dVar);
            }
            setClipToOutline(true);
            this.f1640h.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, getWidth(), getHeight());
            this.f1638f.reset();
            Path path = this.f1638f;
            RectF rectF = this.f1640h;
            float f9 = this.f1637e;
            path.addRoundRect(rectF, f9, f9, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f7) {
        boolean z6 = this.f1636d != f7;
        this.f1636d = f7;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.f1638f == null) {
                this.f1638f = new Path();
            }
            if (this.f1640h == null) {
                this.f1640h = new RectF();
            }
            if (this.f1639g == null) {
                d dVar = new d(this, 0);
                this.f1639g = dVar;
                setOutlineProvider(dVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.f1636d) / 2.0f;
            this.f1640h.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, height);
            this.f1638f.reset();
            this.f1638f.addRoundRect(this.f1640h, min, min, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z6) {
            invalidateOutline();
        }
    }
}
