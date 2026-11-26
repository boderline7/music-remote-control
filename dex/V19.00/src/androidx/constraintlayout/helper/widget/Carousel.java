package androidx.constraintlayout.helper.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.util.ArrayList;
import w.a;
import y.c0;
import y.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Carousel extends MotionHelper {
    public static final int TOUCH_UP_CARRY_ON = 2;
    public static final int TOUCH_UP_IMMEDIATE_STOP = 1;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f1503m;

    /* renamed from: n, reason: collision with root package name */
    public int f1504n;

    /* renamed from: o, reason: collision with root package name */
    public MotionLayout f1505o;

    /* renamed from: p, reason: collision with root package name */
    public int f1506p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f1507q;

    /* renamed from: r, reason: collision with root package name */
    public int f1508r;

    /* renamed from: s, reason: collision with root package name */
    public int f1509s;

    /* renamed from: t, reason: collision with root package name */
    public int f1510t;

    /* renamed from: u, reason: collision with root package name */
    public int f1511u;

    /* renamed from: v, reason: collision with root package name */
    public float f1512v;

    /* renamed from: w, reason: collision with root package name */
    public int f1513w;

    /* renamed from: x, reason: collision with root package name */
    public int f1514x;

    /* renamed from: y, reason: collision with root package name */
    public float f1515y;

    public Carousel(Context context) {
        super(context);
        this.f1503m = new ArrayList();
        this.f1504n = 0;
        this.f1506p = -1;
        this.f1507q = false;
        this.f1508r = -1;
        this.f1509s = -1;
        this.f1510t = -1;
        this.f1511u = -1;
        this.f1512v = 0.9f;
        this.f1513w = 4;
        this.f1514x = 1;
        this.f1515y = 2.0f;
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1503m = new ArrayList();
        this.f1504n = 0;
        this.f1506p = -1;
        this.f1507q = false;
        this.f1508r = -1;
        this.f1509s = -1;
        this.f1510t = -1;
        this.f1511u = -1;
        this.f1512v = 0.9f;
        this.f1513w = 4;
        this.f1514x = 1;
        this.f1515y = 2.0f;
        t(context, attributeSet);
    }

    public Carousel(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1503m = new ArrayList();
        this.f1504n = 0;
        this.f1506p = -1;
        this.f1507q = false;
        this.f1508r = -1;
        this.f1509s = -1;
        this.f1510t = -1;
        this.f1511u = -1;
        this.f1512v = 0.9f;
        this.f1513w = 4;
        this.f1514x = 1;
        this.f1515y = 2.0f;
        t(context, attributeSet);
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, y.v
    public final void a(int i7) {
        int i8 = this.f1504n;
        if (i7 == this.f1511u) {
            this.f1504n = i8 + 1;
        } else if (i7 == this.f1510t) {
            this.f1504n = i8 - 1;
        }
        if (!this.f1507q) {
            throw null;
        }
        throw null;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, y.v
    public final void b() {
    }

    public int getCount() {
        return 0;
    }

    public int getCurrentIndex() {
        return this.f1504n;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        c0 c0Var;
        c0 c0Var2;
        super.onAttachedToWindow();
        if (getParent() instanceof MotionLayout) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            for (int i7 = 0; i7 < this.f1678b; i7++) {
                this.f1503m.add(motionLayout.c(this.f1677a[i7]));
            }
            this.f1505o = motionLayout;
            if (this.f1514x == 2) {
                z w6 = motionLayout.w(this.f1509s);
                if (w6 != null && (c0Var2 = w6.f9958l) != null) {
                    c0Var2.f9759c = 5;
                }
                z w7 = this.f1505o.w(this.f1508r);
                if (w7 == null || (c0Var = w7.f9958l) == null) {
                    return;
                }
                c0Var.f9759c = 5;
            }
        }
    }

    public void setAdapter(a aVar) {
    }

    public final void t(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.Carousel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.Carousel_carousel_firstView) {
                    this.f1506p = obtainStyledAttributes.getResourceId(index, this.f1506p);
                } else if (index == t.Carousel_carousel_backwardTransition) {
                    this.f1508r = obtainStyledAttributes.getResourceId(index, this.f1508r);
                } else if (index == t.Carousel_carousel_forwardTransition) {
                    this.f1509s = obtainStyledAttributes.getResourceId(index, this.f1509s);
                } else if (index == t.Carousel_carousel_emptyViewsBehavior) {
                    this.f1513w = obtainStyledAttributes.getInt(index, this.f1513w);
                } else if (index == t.Carousel_carousel_previousState) {
                    this.f1510t = obtainStyledAttributes.getResourceId(index, this.f1510t);
                } else if (index == t.Carousel_carousel_nextState) {
                    this.f1511u = obtainStyledAttributes.getResourceId(index, this.f1511u);
                } else if (index == t.Carousel_carousel_touchUp_dampeningFactor) {
                    this.f1512v = obtainStyledAttributes.getFloat(index, this.f1512v);
                } else if (index == t.Carousel_carousel_touchUpMode) {
                    this.f1514x = obtainStyledAttributes.getInt(index, this.f1514x);
                } else if (index == t.Carousel_carousel_touchUp_velocityThreshold) {
                    this.f1515y = obtainStyledAttributes.getFloat(index, this.f1515y);
                } else if (index == t.Carousel_carousel_infinite) {
                    this.f1507q = obtainStyledAttributes.getBoolean(index, this.f1507q);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }
}
