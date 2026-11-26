package androidx.constraintlayout.widget;

import a0.f;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import z1.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Placeholder extends View {

    /* renamed from: a, reason: collision with root package name */
    public int f1705a;

    /* renamed from: b, reason: collision with root package name */
    public View f1706b;

    /* renamed from: c, reason: collision with root package name */
    public int f1707c;

    public Placeholder(Context context) {
        super(context);
        this.f1705a = -1;
        this.f1706b = null;
        this.f1707c = 4;
        a(null);
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1705a = -1;
        this.f1706b = null;
        this.f1707c = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1705a = -1;
        this.f1706b = null;
        this.f1707c = 4;
        a(attributeSet);
    }

    public final void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1707c);
        this.f1705a = -1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_placeholder);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_placeholder_content) {
                    this.f1705a = obtainStyledAttributes.getResourceId(index, this.f1705a);
                } else if (index == t.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.f1707c = obtainStyledAttributes.getInt(index, this.f1707c);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public View getContent() {
        return this.f1706b;
    }

    public int getEmptyVisibility() {
        return this.f1707c;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawRGB(c.XOR_INT_LIT8, c.XOR_INT_LIT8, c.XOR_INT_LIT8);
            Paint paint = new Paint();
            paint.setARGB(255, c.MUL_INT_LIT16, c.MUL_INT_LIT16, c.MUL_INT_LIT16);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            paint.setTextSize(rect.height());
            int height = rect.height();
            int width = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds("?", 0, 1, rect);
            canvas.drawText("?", ((width / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((rect.height() / 2.0f) + (height / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i7) {
        View findViewById;
        if (this.f1705a == i7) {
            return;
        }
        View view = this.f1706b;
        if (view != null) {
            view.setVisibility(0);
            ((f) this.f1706b.getLayoutParams()).f22f0 = false;
            this.f1706b = null;
        }
        this.f1705a = i7;
        if (i7 == -1 || (findViewById = ((View) getParent()).findViewById(i7)) == null) {
            return;
        }
        findViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i7) {
        this.f1707c = i7;
    }
}
