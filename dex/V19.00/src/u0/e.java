package u0;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.OverScroller;
import androidx.appcompat.app.s0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;
import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;

    /* renamed from: x, reason: collision with root package name */
    public static final d f9236x = new d(0);

    /* renamed from: a, reason: collision with root package name */
    public int f9237a;

    /* renamed from: b, reason: collision with root package name */
    public int f9238b;

    /* renamed from: d, reason: collision with root package name */
    public float[] f9240d;

    /* renamed from: e, reason: collision with root package name */
    public float[] f9241e;

    /* renamed from: f, reason: collision with root package name */
    public float[] f9242f;

    /* renamed from: g, reason: collision with root package name */
    public float[] f9243g;

    /* renamed from: h, reason: collision with root package name */
    public int[] f9244h;

    /* renamed from: i, reason: collision with root package name */
    public int[] f9245i;

    /* renamed from: j, reason: collision with root package name */
    public int[] f9246j;

    /* renamed from: k, reason: collision with root package name */
    public int f9247k;

    /* renamed from: l, reason: collision with root package name */
    public VelocityTracker f9248l;

    /* renamed from: m, reason: collision with root package name */
    public final float f9249m;

    /* renamed from: n, reason: collision with root package name */
    public float f9250n;

    /* renamed from: o, reason: collision with root package name */
    public int f9251o;

    /* renamed from: p, reason: collision with root package name */
    public final int f9252p;

    /* renamed from: q, reason: collision with root package name */
    public int f9253q;

    /* renamed from: r, reason: collision with root package name */
    public final OverScroller f9254r;

    /* renamed from: s, reason: collision with root package name */
    public final f f9255s;

    /* renamed from: t, reason: collision with root package name */
    public View f9256t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f9257u;

    /* renamed from: v, reason: collision with root package name */
    public final ViewGroup f9258v;

    /* renamed from: c, reason: collision with root package name */
    public int f9239c = -1;

    /* renamed from: w, reason: collision with root package name */
    public final s0 f9259w = new s0(8, this);

    public e(Context context, ViewGroup viewGroup, f fVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (fVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.f9258v = viewGroup;
        this.f9255s = fVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        int i7 = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f9252p = i7;
        this.f9251o = i7;
        this.f9238b = viewConfiguration.getScaledTouchSlop();
        this.f9249m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f9250n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f9254r = new OverScroller(context, f9236x);
    }

    public final void a() {
        this.f9239c = -1;
        float[] fArr = this.f9240d;
        if (fArr != null) {
            Arrays.fill(fArr, CropImageView.DEFAULT_ASPECT_RATIO);
            Arrays.fill(this.f9241e, CropImageView.DEFAULT_ASPECT_RATIO);
            Arrays.fill(this.f9242f, CropImageView.DEFAULT_ASPECT_RATIO);
            Arrays.fill(this.f9243g, CropImageView.DEFAULT_ASPECT_RATIO);
            Arrays.fill(this.f9244h, 0);
            Arrays.fill(this.f9245i, 0);
            Arrays.fill(this.f9246j, 0);
            this.f9247k = 0;
        }
        VelocityTracker velocityTracker = this.f9248l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f9248l = null;
        }
    }

    public final void b(int i7, View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = this.f9258v;
        if (parent != viewGroup) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + viewGroup + ")");
        }
        this.f9256t = view;
        this.f9239c = i7;
        this.f9255s.h0(i7, view);
        o(1);
    }

    public final boolean c(float f7, float f8, int i7, int i8) {
        float abs = Math.abs(f7);
        float abs2 = Math.abs(f8);
        if ((this.f9244h[i7] & i8) != i8 || (this.f9253q & i8) == 0 || (this.f9246j[i7] & i8) == i8 || (this.f9245i[i7] & i8) == i8) {
            return false;
        }
        int i9 = this.f9238b;
        if (abs <= i9 && abs2 <= i9) {
            return false;
        }
        if (abs < abs2 * 0.5f) {
            this.f9255s.getClass();
        }
        return (this.f9245i[i7] & i8) == 0 && abs > ((float) this.f9238b);
    }

    public final boolean d(View view, float f7, float f8) {
        if (view == null) {
            return false;
        }
        f fVar = this.f9255s;
        boolean z6 = fVar.I(view) > 0;
        boolean z7 = fVar.J() > 0;
        if (!z6 || !z7) {
            return z6 ? Math.abs(f7) > ((float) this.f9238b) : z7 && Math.abs(f8) > ((float) this.f9238b);
        }
        float f9 = (f8 * f8) + (f7 * f7);
        int i7 = this.f9238b;
        return f9 > ((float) (i7 * i7));
    }

    public final void e(int i7) {
        float[] fArr = this.f9240d;
        if (fArr != null) {
            int i8 = this.f9247k;
            int i9 = 1 << i7;
            if ((i8 & i9) != 0) {
                fArr[i7] = 0.0f;
                this.f9241e[i7] = 0.0f;
                this.f9242f[i7] = 0.0f;
                this.f9243g[i7] = 0.0f;
                this.f9244h[i7] = 0;
                this.f9245i[i7] = 0;
                this.f9246j[i7] = 0;
                this.f9247k = (~i9) & i8;
            }
        }
    }

    public final int f(int i7, int i8, int i9) {
        if (i7 == 0) {
            return 0;
        }
        float width = this.f9258v.getWidth() / 2;
        float sin = (((float) Math.sin((Math.min(1.0f, Math.abs(i7) / r0) - 0.5f) * 0.47123894f)) * width) + width;
        int abs = Math.abs(i8);
        return Math.min(abs > 0 ? Math.round(Math.abs(sin / abs) * 1000.0f) * 4 : (int) (((Math.abs(i7) / i9) + 1.0f) * 256.0f), 600);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        if (r2 == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean g() {
        /*
            r9 = this;
            int r0 = r9.f9237a
            r1 = 2
            if (r0 != r1) goto L5b
            android.widget.OverScroller r0 = r9.f9254r
            boolean r2 = r0.computeScrollOffset()
            int r3 = r0.getCurrX()
            int r4 = r0.getCurrY()
            android.view.View r5 = r9.f9256t
            int r5 = r5.getLeft()
            int r5 = r3 - r5
            android.view.View r6 = r9.f9256t
            int r6 = r6.getTop()
            int r6 = r4 - r6
            if (r5 == 0) goto L2c
            android.view.View r7 = r9.f9256t
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            r7.offsetLeftAndRight(r5)
        L2c:
            if (r6 == 0) goto L35
            android.view.View r7 = r9.f9256t
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            r7.offsetTopAndBottom(r6)
        L35:
            if (r5 != 0) goto L39
            if (r6 == 0) goto L40
        L39:
            l6.f r5 = r9.f9255s
            android.view.View r6 = r9.f9256t
            r5.j0(r6, r3, r4)
        L40:
            if (r2 == 0) goto L52
            int r5 = r0.getFinalX()
            if (r3 != r5) goto L52
            int r3 = r0.getFinalY()
            if (r4 != r3) goto L52
            r0.abortAnimation()
            goto L54
        L52:
            if (r2 != 0) goto L5b
        L54:
            androidx.appcompat.app.s0 r0 = r9.f9259w
            android.view.ViewGroup r2 = r9.f9258v
            r2.post(r0)
        L5b:
            int r0 = r9.f9237a
            if (r0 != r1) goto L61
            r0 = 1
            goto L62
        L61:
            r0 = 0
        L62:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.e.g():boolean");
    }

    public final View h(int i7, int i8) {
        ViewGroup viewGroup = this.f9258v;
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            this.f9255s.getClass();
            View childAt = viewGroup.getChildAt(childCount);
            if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && i8 >= childAt.getTop() && i8 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(int r11, int r12, int r13, int r14) {
        /*
            r10 = this;
            android.view.View r0 = r10.f9256t
            int r2 = r0.getLeft()
            android.view.View r0 = r10.f9256t
            int r3 = r0.getTop()
            int r4 = r11 - r2
            int r5 = r12 - r3
            android.widget.OverScroller r1 = r10.f9254r
            r11 = 0
            if (r4 != 0) goto L1e
            if (r5 != 0) goto L1e
            r1.abortAnimation()
            r10.o(r11)
            return r11
        L1e:
            android.view.View r12 = r10.f9256t
            float r0 = r10.f9250n
            int r0 = (int) r0
            float r6 = r10.f9249m
            int r6 = (int) r6
            int r7 = java.lang.Math.abs(r13)
            if (r7 >= r0) goto L2e
            r13 = r11
            goto L35
        L2e:
            if (r7 <= r6) goto L35
            if (r13 <= 0) goto L34
            r13 = r6
            goto L35
        L34:
            int r13 = -r6
        L35:
            float r0 = r10.f9250n
            int r0 = (int) r0
            int r7 = java.lang.Math.abs(r14)
            if (r7 >= r0) goto L40
        L3e:
            r14 = r11
            goto L48
        L40:
            if (r7 <= r6) goto L48
            if (r14 <= 0) goto L46
            r14 = r6
            goto L48
        L46:
            int r11 = -r6
            goto L3e
        L48:
            int r11 = java.lang.Math.abs(r4)
            int r0 = java.lang.Math.abs(r5)
            int r6 = java.lang.Math.abs(r13)
            int r7 = java.lang.Math.abs(r14)
            int r8 = r6 + r7
            int r9 = r11 + r0
            if (r13 == 0) goto L62
            float r11 = (float) r6
            float r6 = (float) r8
        L60:
            float r11 = r11 / r6
            goto L65
        L62:
            float r11 = (float) r11
            float r6 = (float) r9
            goto L60
        L65:
            if (r14 == 0) goto L6b
            float r0 = (float) r7
            float r6 = (float) r8
        L69:
            float r0 = r0 / r6
            goto L6e
        L6b:
            float r0 = (float) r0
            float r6 = (float) r9
            goto L69
        L6e:
            l6.f r6 = r10.f9255s
            int r12 = r6.I(r12)
            int r12 = r10.f(r4, r13, r12)
            int r13 = r6.J()
            int r13 = r10.f(r5, r14, r13)
            float r12 = (float) r12
            float r12 = r12 * r11
            float r11 = (float) r13
            float r11 = r11 * r0
            float r11 = r11 + r12
            int r6 = (int) r11
            r1.startScroll(r2, r3, r4, r5, r6)
            r11 = 2
            r10.o(r11)
            r11 = 1
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.e.i(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        if (r9.f9239c == (-1)) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:
    
        k();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 457
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.e.j(android.view.MotionEvent):void");
    }

    public final void k() {
        VelocityTracker velocityTracker = this.f9248l;
        float f7 = this.f9249m;
        velocityTracker.computeCurrentVelocity(1000, f7);
        float xVelocity = this.f9248l.getXVelocity(this.f9239c);
        float f8 = this.f9250n;
        float abs = Math.abs(xVelocity);
        float f9 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (abs < f8) {
            xVelocity = 0.0f;
        } else if (abs > f7) {
            xVelocity = xVelocity > CropImageView.DEFAULT_ASPECT_RATIO ? f7 : -f7;
        }
        float yVelocity = this.f9248l.getYVelocity(this.f9239c);
        float f10 = this.f9250n;
        float abs2 = Math.abs(yVelocity);
        if (abs2 >= f10) {
            if (abs2 > f7) {
                if (yVelocity <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    f7 = -f7;
                }
                f9 = f7;
            } else {
                f9 = yVelocity;
            }
        }
        this.f9257u = true;
        this.f9255s.k0(this.f9256t, xVelocity, f9);
        this.f9257u = false;
        if (this.f9237a == 1) {
            o(0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r3v3, types: [l6.f] */
    public final void l(float f7, float f8, int i7) {
        boolean c7 = c(f7, f8, i7, 1);
        boolean z6 = c7;
        if (c(f8, f7, i7, 4)) {
            z6 = (c7 ? 1 : 0) | 4;
        }
        boolean z7 = z6;
        if (c(f7, f8, i7, 2)) {
            z7 = (z6 ? 1 : 0) | 2;
        }
        ?? r02 = z7;
        if (c(f8, f7, i7, 8)) {
            r02 = (z7 ? 1 : 0) | 8;
        }
        if (r02 != 0) {
            int[] iArr = this.f9245i;
            iArr[i7] = iArr[i7] | r02;
            this.f9255s.W(r02, i7);
        }
    }

    public final void m(float f7, float f8, int i7) {
        float[] fArr = this.f9240d;
        if (fArr == null || fArr.length <= i7) {
            int i8 = i7 + 1;
            float[] fArr2 = new float[i8];
            float[] fArr3 = new float[i8];
            float[] fArr4 = new float[i8];
            float[] fArr5 = new float[i8];
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            int[] iArr3 = new int[i8];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f9241e;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f9242f;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f9243g;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f9244h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f9245i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f9246j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f9240d = fArr2;
            this.f9241e = fArr3;
            this.f9242f = fArr4;
            this.f9243g = fArr5;
            this.f9244h = iArr;
            this.f9245i = iArr2;
            this.f9246j = iArr3;
        }
        float[] fArr9 = this.f9240d;
        this.f9242f[i7] = f7;
        fArr9[i7] = f7;
        float[] fArr10 = this.f9241e;
        this.f9243g[i7] = f8;
        fArr10[i7] = f8;
        int[] iArr7 = this.f9244h;
        int i9 = (int) f7;
        int i10 = (int) f8;
        ViewGroup viewGroup = this.f9258v;
        int i11 = i9 < viewGroup.getLeft() + this.f9251o ? 1 : 0;
        if (i10 < viewGroup.getTop() + this.f9251o) {
            i11 |= 4;
        }
        if (i9 > viewGroup.getRight() - this.f9251o) {
            i11 |= 2;
        }
        if (i10 > viewGroup.getBottom() - this.f9251o) {
            i11 |= 8;
        }
        iArr7[i7] = i11;
        this.f9247k |= 1 << i7;
    }

    public final void n(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i7 = 0; i7 < pointerCount; i7++) {
            int pointerId = motionEvent.getPointerId(i7);
            if ((this.f9247k & (1 << pointerId)) != 0) {
                float x6 = motionEvent.getX(i7);
                float y6 = motionEvent.getY(i7);
                this.f9242f[pointerId] = x6;
                this.f9243g[pointerId] = y6;
            }
        }
    }

    public final void o(int i7) {
        this.f9258v.removeCallbacks(this.f9259w);
        if (this.f9237a != i7) {
            this.f9237a = i7;
            this.f9255s.i0(i7);
            if (this.f9237a == 0) {
                this.f9256t = null;
            }
        }
    }

    public final boolean p(int i7, int i8) {
        if (this.f9257u) {
            return i(i7, i8, (int) this.f9248l.getXVelocity(this.f9239c), (int) this.f9248l.getYVelocity(this.f9239c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d4, code lost:
    
        if (r13 != r12) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.e.q(android.view.MotionEvent):boolean");
    }

    public final boolean r(View view, int i7, int i8) {
        this.f9256t = view;
        this.f9239c = -1;
        boolean i9 = i(i7, i8, 0, 0);
        if (!i9 && this.f9237a == 0 && this.f9256t != null) {
            this.f9256t = null;
        }
        return i9;
    }

    public final boolean s(int i7, View view) {
        if (view == this.f9256t && this.f9239c == i7) {
            return true;
        }
        if (view == null || !this.f9255s.E0(i7, view)) {
            return false;
        }
        this.f9239c = i7;
        b(i7, view);
        return true;
    }
}
