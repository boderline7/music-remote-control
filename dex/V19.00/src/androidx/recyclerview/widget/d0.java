package androidx.recyclerview.widget;

import android.R;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d0 extends o1 implements v1 {
    public static final int[] C = {R.attr.state_pressed};
    public static final int[] D = new int[0];
    public int A;
    public final z B;

    /* renamed from: a, reason: collision with root package name */
    public final int f2399a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2400b;

    /* renamed from: c, reason: collision with root package name */
    public final StateListDrawable f2401c;

    /* renamed from: d, reason: collision with root package name */
    public final Drawable f2402d;

    /* renamed from: e, reason: collision with root package name */
    public final int f2403e;

    /* renamed from: f, reason: collision with root package name */
    public final int f2404f;

    /* renamed from: g, reason: collision with root package name */
    public final StateListDrawable f2405g;

    /* renamed from: h, reason: collision with root package name */
    public final Drawable f2406h;

    /* renamed from: i, reason: collision with root package name */
    public final int f2407i;

    /* renamed from: j, reason: collision with root package name */
    public final int f2408j;

    /* renamed from: k, reason: collision with root package name */
    public int f2409k;

    /* renamed from: l, reason: collision with root package name */
    public int f2410l;

    /* renamed from: m, reason: collision with root package name */
    public float f2411m;

    /* renamed from: n, reason: collision with root package name */
    public int f2412n;

    /* renamed from: o, reason: collision with root package name */
    public int f2413o;

    /* renamed from: p, reason: collision with root package name */
    public float f2414p;

    /* renamed from: s, reason: collision with root package name */
    public final RecyclerView f2417s;

    /* renamed from: z, reason: collision with root package name */
    public final ValueAnimator f2424z;

    /* renamed from: q, reason: collision with root package name */
    public int f2415q = 0;

    /* renamed from: r, reason: collision with root package name */
    public int f2416r = 0;

    /* renamed from: t, reason: collision with root package name */
    public boolean f2418t = false;

    /* renamed from: u, reason: collision with root package name */
    public boolean f2419u = false;

    /* renamed from: v, reason: collision with root package name */
    public int f2420v = 0;

    /* renamed from: w, reason: collision with root package name */
    public int f2421w = 0;

    /* renamed from: x, reason: collision with root package name */
    public final int[] f2422x = new int[2];

    /* renamed from: y, reason: collision with root package name */
    public final int[] f2423y = new int[2];

    public d0(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i7, int i8, int i9) {
        int i10 = 0;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.f2424z = ofFloat;
        this.A = 0;
        z zVar = new z(i10, this);
        this.B = zVar;
        a0 a0Var = new a0(this);
        this.f2401c = stateListDrawable;
        this.f2402d = drawable;
        this.f2405g = stateListDrawable2;
        this.f2406h = drawable2;
        this.f2403e = Math.max(i7, stateListDrawable.getIntrinsicWidth());
        this.f2404f = Math.max(i7, drawable.getIntrinsicWidth());
        this.f2407i = Math.max(i7, stateListDrawable2.getIntrinsicWidth());
        this.f2408j = Math.max(i7, drawable2.getIntrinsicWidth());
        this.f2399a = i8;
        this.f2400b = i9;
        stateListDrawable.setAlpha(255);
        drawable.setAlpha(255);
        ofFloat.addListener(new b0(this));
        ofFloat.addUpdateListener(new c0(i10, this));
        RecyclerView recyclerView2 = this.f2417s;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.Z(this);
            RecyclerView recyclerView3 = this.f2417s;
            recyclerView3.f2325o.remove(this);
            if (recyclerView3.f2327p == this) {
                recyclerView3.f2327p = null;
            }
            ArrayList arrayList = this.f2417s.f2312h0;
            if (arrayList != null) {
                arrayList.remove(a0Var);
            }
            this.f2417s.removeCallbacks(zVar);
        }
        this.f2417s = recyclerView;
        if (recyclerView != null) {
            recyclerView.g(this);
            this.f2417s.f2325o.add(this);
            this.f2417s.h(a0Var);
        }
    }

    public static int h(float f7, float f8, int[] iArr, int i7, int i8, int i9) {
        int i10 = iArr[1] - iArr[0];
        if (i10 == 0) {
            return 0;
        }
        int i11 = i7 - i9;
        int i12 = (int) (((f8 - f7) / i10) * i11);
        int i13 = i8 + i12;
        if (i13 >= i11 || i13 < 0) {
            return 0;
        }
        return i12;
    }

    @Override // androidx.recyclerview.widget.v1
    public final boolean a(MotionEvent motionEvent) {
        int i7 = this.f2420v;
        if (i7 == 1) {
            boolean g7 = g(motionEvent.getX(), motionEvent.getY());
            boolean f7 = f(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!g7 && !f7) {
                return false;
            }
            if (f7) {
                this.f2421w = 1;
                this.f2414p = (int) motionEvent.getX();
            } else if (g7) {
                this.f2421w = 2;
                this.f2411m = (int) motionEvent.getY();
            }
            i(2);
        } else if (i7 != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.v1
    public final void c(MotionEvent motionEvent) {
        if (this.f2420v == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean g7 = g(motionEvent.getX(), motionEvent.getY());
            boolean f7 = f(motionEvent.getX(), motionEvent.getY());
            if (g7 || f7) {
                if (f7) {
                    this.f2421w = 1;
                    this.f2414p = (int) motionEvent.getX();
                } else if (g7) {
                    this.f2421w = 2;
                    this.f2411m = (int) motionEvent.getY();
                }
                i(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.f2420v == 2) {
            this.f2411m = CropImageView.DEFAULT_ASPECT_RATIO;
            this.f2414p = CropImageView.DEFAULT_ASPECT_RATIO;
            i(1);
            this.f2421w = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.f2420v == 2) {
            j();
            int i7 = this.f2421w;
            int i8 = this.f2400b;
            if (i7 == 1) {
                float x6 = motionEvent.getX();
                int[] iArr = this.f2423y;
                iArr[0] = i8;
                int i9 = this.f2415q - i8;
                iArr[1] = i9;
                float max = Math.max(i8, Math.min(i9, x6));
                if (Math.abs(this.f2413o - max) >= 2.0f) {
                    int h7 = h(this.f2414p, max, iArr, this.f2417s.computeHorizontalScrollRange(), this.f2417s.computeHorizontalScrollOffset(), this.f2415q);
                    if (h7 != 0) {
                        this.f2417s.scrollBy(h7, 0);
                    }
                    this.f2414p = max;
                }
            }
            if (this.f2421w == 2) {
                float y6 = motionEvent.getY();
                int[] iArr2 = this.f2422x;
                iArr2[0] = i8;
                int i10 = this.f2416r - i8;
                iArr2[1] = i10;
                float max2 = Math.max(i8, Math.min(i10, y6));
                if (Math.abs(this.f2410l - max2) < 2.0f) {
                    return;
                }
                int h8 = h(this.f2411m, max2, iArr2, this.f2417s.computeVerticalScrollRange(), this.f2417s.computeVerticalScrollOffset(), this.f2416r);
                if (h8 != 0) {
                    this.f2417s.scrollBy(0, h8);
                }
                this.f2411m = max2;
            }
        }
    }

    @Override // androidx.recyclerview.widget.v1
    public final void e(boolean z6) {
    }

    public final boolean f(float f7, float f8) {
        if (f8 >= this.f2416r - this.f2407i) {
            int i7 = this.f2413o;
            int i8 = this.f2412n;
            if (f7 >= i7 - (i8 / 2) && f7 <= (i8 / 2) + i7) {
                return true;
            }
        }
        return false;
    }

    public final boolean g(float f7, float f8) {
        RecyclerView recyclerView = this.f2417s;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        boolean z6 = p0.m0.d(recyclerView) == 1;
        int i8 = this.f2403e;
        if (z6) {
            if (f7 > i8 / 2) {
                return false;
            }
        } else if (f7 < this.f2415q - i8) {
            return false;
        }
        int i9 = this.f2410l;
        int i10 = this.f2409k / 2;
        return f8 >= ((float) (i9 - i10)) && f8 <= ((float) (i10 + i9));
    }

    public final void i(int i7) {
        z zVar = this.B;
        StateListDrawable stateListDrawable = this.f2401c;
        if (i7 == 2 && this.f2420v != 2) {
            stateListDrawable.setState(C);
            this.f2417s.removeCallbacks(zVar);
        }
        if (i7 == 0) {
            this.f2417s.invalidate();
        } else {
            j();
        }
        if (this.f2420v == 2 && i7 != 2) {
            stateListDrawable.setState(D);
            this.f2417s.removeCallbacks(zVar);
            this.f2417s.postDelayed(zVar, 1200);
        } else if (i7 == 1) {
            this.f2417s.removeCallbacks(zVar);
            this.f2417s.postDelayed(zVar, 1500);
        }
        this.f2420v = i7;
    }

    public final void j() {
        int i7 = this.A;
        ValueAnimator valueAnimator = this.f2424z;
        if (i7 != 0) {
            if (i7 != 3) {
                return;
            } else {
                valueAnimator.cancel();
            }
        }
        this.A = 1;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        valueAnimator.setDuration(500L);
        valueAnimator.setStartDelay(0L);
        valueAnimator.start();
    }

    @Override // androidx.recyclerview.widget.o1
    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, g2 g2Var) {
        if (this.f2415q != this.f2417s.getWidth() || this.f2416r != this.f2417s.getHeight()) {
            this.f2415q = this.f2417s.getWidth();
            this.f2416r = this.f2417s.getHeight();
            i(0);
            return;
        }
        if (this.A != 0) {
            if (this.f2418t) {
                int i7 = this.f2415q;
                int i8 = this.f2403e;
                int i9 = i7 - i8;
                int i10 = this.f2410l;
                int i11 = this.f2409k;
                int i12 = i10 - (i11 / 2);
                StateListDrawable stateListDrawable = this.f2401c;
                stateListDrawable.setBounds(0, 0, i8, i11);
                int i13 = this.f2416r;
                int i14 = this.f2404f;
                Drawable drawable = this.f2402d;
                drawable.setBounds(0, 0, i14, i13);
                RecyclerView recyclerView2 = this.f2417s;
                int i15 = p0.d1.OVER_SCROLL_ALWAYS;
                if (p0.m0.d(recyclerView2) == 1) {
                    drawable.draw(canvas);
                    canvas.translate(i8, i12);
                    canvas.scale(-1.0f, 1.0f);
                    stateListDrawable.draw(canvas);
                    canvas.scale(1.0f, 1.0f);
                    canvas.translate(-i8, -i12);
                } else {
                    canvas.translate(i9, CropImageView.DEFAULT_ASPECT_RATIO);
                    drawable.draw(canvas);
                    canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, i12);
                    stateListDrawable.draw(canvas);
                    canvas.translate(-i9, -i12);
                }
            }
            if (this.f2419u) {
                int i16 = this.f2416r;
                int i17 = this.f2407i;
                int i18 = i16 - i17;
                int i19 = this.f2413o;
                int i20 = this.f2412n;
                int i21 = i19 - (i20 / 2);
                StateListDrawable stateListDrawable2 = this.f2405g;
                stateListDrawable2.setBounds(0, 0, i20, i17);
                int i22 = this.f2415q;
                int i23 = this.f2408j;
                Drawable drawable2 = this.f2406h;
                drawable2.setBounds(0, 0, i22, i23);
                canvas.translate(CropImageView.DEFAULT_ASPECT_RATIO, i18);
                drawable2.draw(canvas);
                canvas.translate(i21, CropImageView.DEFAULT_ASPECT_RATIO);
                stateListDrawable2.draw(canvas);
                canvas.translate(-i21, -i18);
            }
        }
    }
}
