package androidx.viewpager.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;
import g0.d;
import g0.g;
import p0.d1;
import q1.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class PagerTabStrip extends PagerTitleStrip {
    public final int A;
    public boolean B;
    public float C;
    public float D;
    public final int E;

    /* renamed from: p, reason: collision with root package name */
    public int f2798p;

    /* renamed from: q, reason: collision with root package name */
    public final int f2799q;

    /* renamed from: r, reason: collision with root package name */
    public final int f2800r;

    /* renamed from: s, reason: collision with root package name */
    public final int f2801s;

    /* renamed from: t, reason: collision with root package name */
    public final int f2802t;

    /* renamed from: u, reason: collision with root package name */
    public final int f2803u;

    /* renamed from: v, reason: collision with root package name */
    public final Paint f2804v;

    /* renamed from: w, reason: collision with root package name */
    public final Rect f2805w;

    /* renamed from: x, reason: collision with root package name */
    public int f2806x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f2807y;

    /* renamed from: z, reason: collision with root package name */
    public boolean f2808z;

    public PagerTabStrip(Context context) {
        this(context, null);
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.f2804v = paint;
        this.f2805w = new Rect();
        this.f2806x = 255;
        this.f2807y = false;
        this.f2808z = false;
        int i7 = this.f2823m;
        this.f2798p = i7;
        paint.setColor(i7);
        float f7 = context.getResources().getDisplayMetrics().density;
        this.f2799q = (int) ((3.0f * f7) + 0.5f);
        this.f2800r = (int) ((6.0f * f7) + 0.5f);
        this.f2801s = (int) (64.0f * f7);
        this.f2803u = (int) ((16.0f * f7) + 0.5f);
        this.A = (int) ((1.0f * f7) + 0.5f);
        this.f2802t = (int) ((f7 * 32.0f) + 0.5f);
        this.E = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f2812b.setFocusable(true);
        this.f2812b.setOnClickListener(new b(this, 0));
        this.f2814d.setFocusable(true);
        this.f2814d.setOnClickListener(new b(this, 1));
        if (getBackground() == null) {
            this.f2807y = true;
        }
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public final void c(int i7, float f7, boolean z6) {
        int height = getHeight();
        TextView textView = this.f2813c;
        int left = textView.getLeft();
        int i8 = this.f2803u;
        int right = textView.getRight() + i8;
        int i9 = height - this.f2799q;
        Rect rect = this.f2805w;
        rect.set(left - i8, i9, right, height);
        super.c(i7, f7, z6);
        this.f2806x = (int) (Math.abs(f7 - 0.5f) * 2.0f * 255.0f);
        rect.union(textView.getLeft() - i8, i9, textView.getRight() + i8, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.f2807y;
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.f2802t);
    }

    public int getTabIndicatorColor() {
        return this.f2798p;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        TextView textView = this.f2813c;
        int left = textView.getLeft();
        int i7 = this.f2803u;
        int i8 = left - i7;
        int right = textView.getRight() + i7;
        int i9 = height - this.f2799q;
        Paint paint = this.f2804v;
        paint.setColor((this.f2806x << 24) | (this.f2798p & d1.MEASURED_SIZE_MASK));
        float f7 = right;
        float f8 = height;
        canvas.drawRect(i8, i9, f7, f8, paint);
        if (this.f2807y) {
            paint.setColor((this.f2798p & d1.MEASURED_SIZE_MASK) | d1.MEASURED_STATE_MASK);
            canvas.drawRect(getPaddingLeft(), height - this.A, getWidth() - getPaddingRight(), f8, paint);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.B) {
            return false;
        }
        float x6 = motionEvent.getX();
        float y6 = motionEvent.getY();
        if (action == 0) {
            this.C = x6;
            this.D = y6;
            this.B = false;
        } else if (action == 1) {
            int left = this.f2813c.getLeft();
            int i7 = this.f2803u;
            if (x6 < left - i7) {
                ViewPager viewPager = this.f2811a;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            } else if (x6 > r5.getRight() + i7) {
                ViewPager viewPager2 = this.f2811a;
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        } else if (action == 2) {
            float abs = Math.abs(x6 - this.C);
            int i8 = this.E;
            if (abs > i8 || Math.abs(y6 - this.D) > i8) {
                this.B = true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        super.setBackgroundColor(i7);
        if (this.f2808z) {
            return;
        }
        this.f2807y = (i7 & d1.MEASURED_STATE_MASK) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f2808z) {
            return;
        }
        this.f2807y = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        if (this.f2808z) {
            return;
        }
        this.f2807y = i7 == 0;
    }

    public void setDrawFullUnderline(boolean z6) {
        this.f2807y = z6;
        this.f2808z = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        int i11 = this.f2800r;
        if (i10 < i11) {
            i10 = i11;
        }
        super.setPadding(i7, i8, i9, i10);
    }

    public void setTabIndicatorColor(int i7) {
        this.f2798p = i7;
        this.f2804v.setColor(i7);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i7) {
        Context context = getContext();
        Object obj = g.f6423a;
        setTabIndicatorColor(d.a(context, i7));
    }

    @Override // androidx.viewpager.widget.PagerTitleStrip
    public void setTextSpacing(int i7) {
        int i8 = this.f2801s;
        if (i7 < i8) {
            i7 = i8;
        }
        super.setTextSpacing(i7);
    }
}
