package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import p0.d1;
import q1.c;
import q1.d;
import q1.e;
import q1.j;

@e
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class PagerTitleStrip extends ViewGroup {

    /* renamed from: n, reason: collision with root package name */
    public static final int[] f2809n = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* renamed from: o, reason: collision with root package name */
    public static final int[] f2810o = {R.attr.textAllCaps};

    /* renamed from: a, reason: collision with root package name */
    public ViewPager f2811a;

    /* renamed from: b, reason: collision with root package name */
    public final TextView f2812b;

    /* renamed from: c, reason: collision with root package name */
    public final TextView f2813c;

    /* renamed from: d, reason: collision with root package name */
    public final TextView f2814d;

    /* renamed from: e, reason: collision with root package name */
    public int f2815e;

    /* renamed from: f, reason: collision with root package name */
    public float f2816f;

    /* renamed from: g, reason: collision with root package name */
    public int f2817g;

    /* renamed from: h, reason: collision with root package name */
    public int f2818h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f2819i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2820j;

    /* renamed from: k, reason: collision with root package name */
    public final c f2821k;

    /* renamed from: l, reason: collision with root package name */
    public int f2822l;

    /* renamed from: m, reason: collision with root package name */
    public int f2823m;

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2815e = -1;
        this.f2816f = -1.0f;
        this.f2821k = new c(this);
        TextView textView = new TextView(context);
        this.f2812b = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f2813c = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f2814d = textView3;
        addView(textView3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2809n);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            textView.setTextAppearance(resourceId);
            textView2.setTextAppearance(resourceId);
            textView3.setTextAppearance(resourceId);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            setTextSize(0, dimensionPixelSize);
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, 0);
            textView.setTextColor(color);
            textView2.setTextColor(color);
            textView3.setTextColor(color);
        }
        this.f2818h = obtainStyledAttributes.getInteger(3, 80);
        obtainStyledAttributes.recycle();
        this.f2823m = textView2.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView.setEllipsize(truncateAt);
        textView2.setEllipsize(truncateAt);
        textView3.setEllipsize(truncateAt);
        if (resourceId != 0) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f2810o);
            boolean z6 = obtainStyledAttributes2.getBoolean(0, false);
            obtainStyledAttributes2.recycle();
            if (z6) {
                setSingleLineAllCaps(textView);
                setSingleLineAllCaps(textView2);
                setSingleLineAllCaps(textView3);
                this.f2817g = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
            }
        }
        textView.setSingleLine();
        textView2.setSingleLine();
        textView3.setSingleLine();
        this.f2817g = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        Context context = textView.getContext();
        d dVar = new d();
        dVar.f8434a = context.getResources().getConfiguration().locale;
        textView.setTransformationMethod(dVar);
    }

    public final void a() {
        ViewPager viewPager = this.f2811a;
        if (viewPager != null) {
            this.f2815e = -1;
            this.f2816f = -1.0f;
            b(viewPager.getCurrentItem());
            requestLayout();
        }
    }

    public final void b(int i7) {
        this.f2819i = true;
        TextView textView = this.f2812b;
        textView.setText((CharSequence) null);
        TextView textView2 = this.f2813c;
        textView2.setText((CharSequence) null);
        TextView textView3 = this.f2814d;
        textView3.setText((CharSequence) null);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        textView.measure(makeMeasureSpec, makeMeasureSpec2);
        textView2.measure(makeMeasureSpec, makeMeasureSpec2);
        textView3.measure(makeMeasureSpec, makeMeasureSpec2);
        this.f2815e = i7;
        if (!this.f2820j) {
            c(i7, this.f2816f, false);
        }
        this.f2819i = false;
    }

    public void c(int i7, float f7, boolean z6) {
        int i8;
        int i9;
        int i10;
        int i11;
        if (i7 != this.f2815e) {
            this.f2811a.getAdapter();
            b(i7);
        } else if (!z6 && f7 == this.f2816f) {
            return;
        }
        this.f2820j = true;
        TextView textView = this.f2812b;
        int measuredWidth = textView.getMeasuredWidth();
        TextView textView2 = this.f2813c;
        int measuredWidth2 = textView2.getMeasuredWidth();
        TextView textView3 = this.f2814d;
        int measuredWidth3 = textView3.getMeasuredWidth();
        int i12 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i13 = paddingRight + i12;
        int i14 = (width - (paddingLeft + i12)) - i13;
        float f8 = f7 + 0.5f;
        if (f8 > 1.0f) {
            f8 -= 1.0f;
        }
        int i15 = ((width - i13) - ((int) (i14 * f8))) - i12;
        int i16 = measuredWidth2 + i15;
        int baseline = textView.getBaseline();
        int baseline2 = textView2.getBaseline();
        int baseline3 = textView3.getBaseline();
        int max = Math.max(Math.max(baseline, baseline2), baseline3);
        int i17 = max - baseline;
        int i18 = max - baseline2;
        int i19 = max - baseline3;
        int max2 = Math.max(Math.max(textView.getMeasuredHeight() + i17, textView2.getMeasuredHeight() + i18), textView3.getMeasuredHeight() + i19);
        int i20 = this.f2818h & z1.c.INVOKE_DIRECT;
        if (i20 == 16) {
            i8 = (((height - paddingTop) - paddingBottom) - max2) / 2;
        } else {
            if (i20 != 80) {
                i9 = i17 + paddingTop;
                i10 = i18 + paddingTop;
                i11 = paddingTop + i19;
                textView2.layout(i15, i10, i16, textView2.getMeasuredHeight() + i10);
                int min = Math.min(paddingLeft, (i15 - this.f2817g) - measuredWidth);
                textView.layout(min, i9, measuredWidth + min, textView.getMeasuredHeight() + i9);
                int max3 = Math.max((width - paddingRight) - measuredWidth3, i16 + this.f2817g);
                textView3.layout(max3, i11, max3 + measuredWidth3, textView3.getMeasuredHeight() + i11);
                this.f2816f = f7;
                this.f2820j = false;
            }
            i8 = (height - paddingBottom) - max2;
        }
        i9 = i17 + i8;
        i10 = i18 + i8;
        i11 = i8 + i19;
        textView2.layout(i15, i10, i16, textView2.getMeasuredHeight() + i10);
        int min2 = Math.min(paddingLeft, (i15 - this.f2817g) - measuredWidth);
        textView.layout(min2, i9, measuredWidth + min2, textView.getMeasuredHeight() + i9);
        int max32 = Math.max((width - paddingRight) - measuredWidth3, i16 + this.f2817g);
        textView3.layout(max32, i11, max32 + measuredWidth3, textView3.getMeasuredHeight() + i11);
        this.f2816f = f7;
        this.f2820j = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.f2817g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        viewPager.getAdapter();
        c cVar = this.f2821k;
        viewPager.F = cVar;
        if (viewPager.G == null) {
            viewPager.G = new ArrayList();
        }
        viewPager.G.add(cVar);
        this.f2811a = viewPager;
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f2811a;
        if (viewPager != null) {
            viewPager.getAdapter();
            a();
            ViewPager viewPager2 = this.f2811a;
            j jVar = viewPager2.F;
            viewPager2.F = null;
            ArrayList arrayList = viewPager2.G;
            if (arrayList != null) {
                arrayList.remove(this.f2821k);
            }
            this.f2811a = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        if (this.f2811a != null) {
            float f7 = this.f2816f;
            if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
                f7 = 0.0f;
            }
            c(this.f2815e, f7, true);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int max;
        if (View.MeasureSpec.getMode(i7) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i8, paddingBottom, -2);
        int size = View.MeasureSpec.getSize(i7);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i7, (int) (size * 0.2f), -2);
        this.f2812b.measure(childMeasureSpec2, childMeasureSpec);
        TextView textView = this.f2813c;
        textView.measure(childMeasureSpec2, childMeasureSpec);
        this.f2814d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i8) == 1073741824) {
            max = View.MeasureSpec.getSize(i8);
        } else {
            max = Math.max(getMinHeight(), textView.getMeasuredHeight() + paddingBottom);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(max, i8, textView.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.f2819i) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i7) {
        this.f2818h = i7;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f7) {
        int i7 = ((int) (f7 * 255.0f)) & 255;
        this.f2822l = i7;
        int i8 = (i7 << 24) | (this.f2823m & d1.MEASURED_SIZE_MASK);
        this.f2812b.setTextColor(i8);
        this.f2814d.setTextColor(i8);
    }

    public void setTextColor(int i7) {
        this.f2823m = i7;
        this.f2813c.setTextColor(i7);
        int i8 = (this.f2822l << 24) | (this.f2823m & d1.MEASURED_SIZE_MASK);
        this.f2812b.setTextColor(i8);
        this.f2814d.setTextColor(i8);
    }

    public void setTextSize(int i7, float f7) {
        this.f2812b.setTextSize(i7, f7);
        this.f2813c.setTextSize(i7, f7);
        this.f2814d.setTextSize(i7, f7);
    }

    public void setTextSpacing(int i7) {
        this.f2817g = i7;
        requestLayout();
    }
}
