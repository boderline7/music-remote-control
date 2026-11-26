package androidx.constraintlayout.motion.widget;

import a0.p;
import a0.t;
import a0.x;
import a0.y;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.appcompat.app.s0;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import l6.f;
import p0.z;
import t.g;
import u.i;
import u.j;
import x.b;
import y.a;
import y.a0;
import y.b0;
import y.c0;
import y.l;
import y.n;
import y.o;
import y.q;
import y.r;
import y.s;
import y.u;
import y.v;
import y.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionLayout extends ConstraintLayout implements z {
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    public static boolean H0 = false;
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_NEVER_TO_END = 7;
    public static final int TOUCH_UP_NEVER_TO_START = 6;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    public int A;
    public w A0;
    public boolean B;
    public final s B0;
    public final HashMap C;
    public boolean C0;
    public long D;
    public final RectF D0;
    public float E;
    public View E0;
    public float F;
    public Matrix F0;
    public float G;
    public final ArrayList G0;
    public long H;
    public float I;
    public boolean J;
    public boolean K;
    public v L;
    public int M;
    public r N;
    public boolean O;
    public final b P;
    public final q Q;
    public a R;
    public int S;
    public int T;
    public boolean U;
    public float V;
    public float W;

    /* renamed from: a0, reason: collision with root package name */
    public long f1559a0;

    /* renamed from: b0, reason: collision with root package name */
    public float f1560b0;

    /* renamed from: c0, reason: collision with root package name */
    public boolean f1561c0;

    /* renamed from: d0, reason: collision with root package name */
    public ArrayList f1562d0;

    /* renamed from: e0, reason: collision with root package name */
    public ArrayList f1563e0;

    /* renamed from: f0, reason: collision with root package name */
    public ArrayList f1564f0;

    /* renamed from: g0, reason: collision with root package name */
    public CopyOnWriteArrayList f1565g0;

    /* renamed from: h0, reason: collision with root package name */
    public int f1566h0;

    /* renamed from: i0, reason: collision with root package name */
    public long f1567i0;

    /* renamed from: j0, reason: collision with root package name */
    public float f1568j0;

    /* renamed from: k0, reason: collision with root package name */
    public int f1569k0;

    /* renamed from: l0, reason: collision with root package name */
    public float f1570l0;

    /* renamed from: m0, reason: collision with root package name */
    public boolean f1571m0;

    /* renamed from: n0, reason: collision with root package name */
    public int f1572n0;

    /* renamed from: o0, reason: collision with root package name */
    public int f1573o0;

    /* renamed from: p0, reason: collision with root package name */
    public int f1574p0;

    /* renamed from: q0, reason: collision with root package name */
    public int f1575q0;

    /* renamed from: r0, reason: collision with root package name */
    public int f1576r0;

    /* renamed from: s, reason: collision with root package name */
    public a0 f1577s;

    /* renamed from: s0, reason: collision with root package name */
    public int f1578s0;

    /* renamed from: t, reason: collision with root package name */
    public o f1579t;

    /* renamed from: t0, reason: collision with root package name */
    public float f1580t0;

    /* renamed from: u, reason: collision with root package name */
    public Interpolator f1581u;

    /* renamed from: u0, reason: collision with root package name */
    public final g f1582u0;

    /* renamed from: v, reason: collision with root package name */
    public float f1583v;

    /* renamed from: v0, reason: collision with root package name */
    public boolean f1584v0;

    /* renamed from: w, reason: collision with root package name */
    public int f1585w;

    /* renamed from: w0, reason: collision with root package name */
    public u f1586w0;

    /* renamed from: x, reason: collision with root package name */
    public int f1587x;

    /* renamed from: x0, reason: collision with root package name */
    public Runnable f1588x0;

    /* renamed from: y, reason: collision with root package name */
    public int f1589y;

    /* renamed from: y0, reason: collision with root package name */
    public final Rect f1590y0;

    /* renamed from: z, reason: collision with root package name */
    public int f1591z;

    /* renamed from: z0, reason: collision with root package name */
    public boolean f1592z0;

    public MotionLayout(Context context) {
        super(context);
        this.f1581u = null;
        this.f1583v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1585w = -1;
        this.f1587x = -1;
        this.f1589y = -1;
        this.f1591z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap();
        this.D = 0L;
        this.E = 1.0f;
        this.F = CropImageView.DEFAULT_ASPECT_RATIO;
        this.G = CropImageView.DEFAULT_ASPECT_RATIO;
        this.I = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = false;
        this.M = 0;
        this.O = false;
        this.P = new b();
        this.Q = new q(this);
        this.U = false;
        this.f1561c0 = false;
        this.f1562d0 = null;
        this.f1563e0 = null;
        this.f1564f0 = null;
        this.f1565g0 = null;
        this.f1566h0 = 0;
        this.f1567i0 = -1L;
        this.f1568j0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1569k0 = 0;
        this.f1570l0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1571m0 = false;
        this.f1582u0 = new g(0);
        this.f1584v0 = false;
        this.f1588x0 = null;
        new HashMap();
        this.f1590y0 = new Rect();
        this.f1592z0 = false;
        this.A0 = w.UNDEFINED;
        this.B0 = new s(this);
        this.C0 = false;
        this.D0 = new RectF();
        this.E0 = null;
        this.F0 = null;
        this.G0 = new ArrayList();
        y(null);
    }

    public MotionLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1581u = null;
        this.f1583v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1585w = -1;
        this.f1587x = -1;
        this.f1589y = -1;
        this.f1591z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap();
        this.D = 0L;
        this.E = 1.0f;
        this.F = CropImageView.DEFAULT_ASPECT_RATIO;
        this.G = CropImageView.DEFAULT_ASPECT_RATIO;
        this.I = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = false;
        this.M = 0;
        this.O = false;
        this.P = new b();
        this.Q = new q(this);
        this.U = false;
        this.f1561c0 = false;
        this.f1562d0 = null;
        this.f1563e0 = null;
        this.f1564f0 = null;
        this.f1565g0 = null;
        this.f1566h0 = 0;
        this.f1567i0 = -1L;
        this.f1568j0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1569k0 = 0;
        this.f1570l0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1571m0 = false;
        this.f1582u0 = new g(0);
        this.f1584v0 = false;
        this.f1588x0 = null;
        new HashMap();
        this.f1590y0 = new Rect();
        this.f1592z0 = false;
        this.A0 = w.UNDEFINED;
        this.B0 = new s(this);
        this.C0 = false;
        this.D0 = new RectF();
        this.E0 = null;
        this.F0 = null;
        this.G0 = new ArrayList();
        y(attributeSet);
    }

    public MotionLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1581u = null;
        this.f1583v = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1585w = -1;
        this.f1587x = -1;
        this.f1589y = -1;
        this.f1591z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap();
        this.D = 0L;
        this.E = 1.0f;
        this.F = CropImageView.DEFAULT_ASPECT_RATIO;
        this.G = CropImageView.DEFAULT_ASPECT_RATIO;
        this.I = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = false;
        this.M = 0;
        this.O = false;
        this.P = new b();
        this.Q = new q(this);
        this.U = false;
        this.f1561c0 = false;
        this.f1562d0 = null;
        this.f1563e0 = null;
        this.f1564f0 = null;
        this.f1565g0 = null;
        this.f1566h0 = 0;
        this.f1567i0 = -1L;
        this.f1568j0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1569k0 = 0;
        this.f1570l0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f1571m0 = false;
        this.f1582u0 = new g(0);
        this.f1584v0 = false;
        this.f1588x0 = null;
        new HashMap();
        this.f1590y0 = new Rect();
        this.f1592z0 = false;
        this.A0 = w.UNDEFINED;
        this.B0 = new s(this);
        this.C0 = false;
        this.D0 = new RectF();
        this.E0 = null;
        this.F0 = null;
        this.G0 = new ArrayList();
        y(attributeSet);
    }

    public static Rect p(MotionLayout motionLayout, i iVar) {
        motionLayout.getClass();
        int u6 = iVar.u();
        Rect rect = motionLayout.f1590y0;
        rect.top = u6;
        rect.left = iVar.t();
        rect.right = iVar.s() + rect.left;
        rect.bottom = iVar.m() + rect.top;
        return rect;
    }

    public final void A() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        if (this.L == null && ((copyOnWriteArrayList = this.f1565g0) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        ArrayList arrayList = this.G0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            v vVar = this.L;
            if (vVar != null) {
                vVar.a(num.intValue());
            }
            CopyOnWriteArrayList copyOnWriteArrayList2 = this.f1565g0;
            if (copyOnWriteArrayList2 != null) {
                Iterator it2 = copyOnWriteArrayList2.iterator();
                while (it2.hasNext()) {
                    ((v) it2.next()).a(num.intValue());
                }
            }
        }
        arrayList.clear();
    }

    public final void B() {
        this.B0.k();
        invalidate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
    
        if (r18 != 7) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
    
        if ((((r17 * r5) - (((r2 * r5) * r5) / 2.0f)) + r1) > 1.0f) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0085, code lost:
    
        r1 = r15.P;
        r2 = r15.G;
        r5 = r15.E;
        r6 = r15.f1577s.g();
        r3 = r15.f1577s.f9735c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0095, code lost:
    
        if (r3 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0097, code lost:
    
        r3 = r3.f9958l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0099, code lost:
    
        if (r3 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009b, code lost:
    
        r7 = r3.f9775s;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a0, code lost:
    
        r1.b(r2, r16, r17, r5, r6, r7);
        r15.f1583v = com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO;
        r1 = r15.f1587x;
        r15.I = r8;
        r15.f1587x = r1;
        r15.f1579t = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009f, code lost:
    
        r7 = 0.0f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0073, code lost:
    
        r1 = r15.G;
        r2 = r15.f1577s.g();
        r13.f9893a = r17;
        r13.f9894b = r1;
        r13.f9895c = r2;
        r15.f1579t = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0071, code lost:
    
        if ((((((r2 * r3) * r3) / 2.0f) + (r17 * r3)) + r1) < com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(float r16, float r17, int r18) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.C(float, float, int):void");
    }

    public final void D(int i7, int i8) {
        y yVar;
        a0 a0Var = this.f1577s;
        if (a0Var != null && (yVar = a0Var.f9734b) != null) {
            int i9 = this.f1587x;
            float f7 = -1;
            a0.w wVar = (a0.w) yVar.f194b.get(i7);
            if (wVar == null) {
                i9 = i7;
            } else {
                ArrayList arrayList = wVar.f186b;
                int i10 = wVar.f187c;
                if (f7 != -1.0f && f7 != -1.0f) {
                    Iterator it = arrayList.iterator();
                    x xVar = null;
                    while (true) {
                        if (it.hasNext()) {
                            x xVar2 = (x) it.next();
                            if (xVar2.a(f7, f7)) {
                                if (i9 == xVar2.f192e) {
                                    break;
                                } else {
                                    xVar = xVar2;
                                }
                            }
                        } else if (xVar != null) {
                            i9 = xVar.f192e;
                        }
                    }
                } else if (i10 != i9) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (i9 == ((x) it2.next()).f192e) {
                            break;
                        }
                    }
                    i9 = i10;
                }
            }
            if (i9 != -1) {
                i7 = i9;
            }
        }
        int i11 = this.f1587x;
        if (i11 == i7) {
            return;
        }
        if (this.f1585w == i7) {
            q(CropImageView.DEFAULT_ASPECT_RATIO);
            if (i8 > 0) {
                this.E = i8 / 1000.0f;
                return;
            }
            return;
        }
        if (this.f1589y == i7) {
            q(1.0f);
            if (i8 > 0) {
                this.E = i8 / 1000.0f;
                return;
            }
            return;
        }
        this.f1589y = i7;
        if (i11 != -1) {
            setTransition(i11, i7);
            q(1.0f);
            this.G = CropImageView.DEFAULT_ASPECT_RATIO;
            q(1.0f);
            this.f1588x0 = null;
            if (i8 > 0) {
                this.E = i8 / 1000.0f;
                return;
            }
            return;
        }
        this.O = false;
        this.I = 1.0f;
        this.F = CropImageView.DEFAULT_ASPECT_RATIO;
        this.G = CropImageView.DEFAULT_ASPECT_RATIO;
        this.H = getNanoTime();
        this.D = getNanoTime();
        this.J = false;
        this.f1579t = null;
        if (i8 == -1) {
            this.E = this.f1577s.c() / 1000.0f;
        }
        this.f1585w = -1;
        this.f1577s.n(-1, this.f1589y);
        SparseArray sparseArray = new SparseArray();
        if (i8 == 0) {
            this.E = this.f1577s.c() / 1000.0f;
        } else if (i8 > 0) {
            this.E = i8 / 1000.0f;
        }
        int childCount = getChildCount();
        HashMap hashMap = this.C;
        hashMap.clear();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            hashMap.put(childAt, new n(childAt));
            sparseArray.put(childAt.getId(), (n) hashMap.get(childAt));
        }
        this.K = true;
        p b7 = this.f1577s.b(i7);
        s sVar = this.B0;
        sVar.i(null, b7);
        B();
        sVar.a();
        int childCount2 = getChildCount();
        for (int i13 = 0; i13 < childCount2; i13++) {
            View childAt2 = getChildAt(i13);
            n nVar = (n) hashMap.get(childAt2);
            if (nVar != null) {
                y.x xVar3 = nVar.f9871f;
                xVar3.f9929c = CropImageView.DEFAULT_ASPECT_RATIO;
                xVar3.f9930d = CropImageView.DEFAULT_ASPECT_RATIO;
                xVar3.f(childAt2.getX(), childAt2.getY(), childAt2.getWidth(), childAt2.getHeight());
                l lVar = nVar.f9873h;
                lVar.getClass();
                childAt2.getX();
                childAt2.getY();
                childAt2.getWidth();
                childAt2.getHeight();
                lVar.f9849c = childAt2.getVisibility();
                lVar.f9847a = childAt2.getVisibility() != 0 ? 0.0f : childAt2.getAlpha();
                lVar.f9850d = childAt2.getElevation();
                lVar.f9851e = childAt2.getRotation();
                lVar.f9852f = childAt2.getRotationX();
                lVar.f9853g = childAt2.getRotationY();
                lVar.f9854h = childAt2.getScaleX();
                lVar.f9855i = childAt2.getScaleY();
                lVar.f9856j = childAt2.getPivotX();
                lVar.f9857k = childAt2.getPivotY();
                lVar.f9858l = childAt2.getTranslationX();
                lVar.f9859m = childAt2.getTranslationY();
                lVar.f9860n = childAt2.getTranslationZ();
            }
        }
        int width = getWidth();
        int height = getHeight();
        if (this.f1564f0 != null) {
            for (int i14 = 0; i14 < childCount; i14++) {
                n nVar2 = (n) hashMap.get(getChildAt(i14));
                if (nVar2 != null) {
                    this.f1577s.f(nVar2);
                }
            }
            Iterator it3 = this.f1564f0.iterator();
            while (it3.hasNext()) {
                ((MotionHelper) it3.next()).s(this, hashMap);
            }
            for (int i15 = 0; i15 < childCount; i15++) {
                n nVar3 = (n) hashMap.get(getChildAt(i15));
                if (nVar3 != null) {
                    nVar3.h(width, height, getNanoTime());
                }
            }
        } else {
            for (int i16 = 0; i16 < childCount; i16++) {
                n nVar4 = (n) hashMap.get(getChildAt(i16));
                if (nVar4 != null) {
                    this.f1577s.f(nVar4);
                    nVar4.h(width, height, getNanoTime());
                }
            }
        }
        y.z zVar = this.f1577s.f9735c;
        float f8 = zVar != null ? zVar.f9955i : 0.0f;
        if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
            float f9 = Float.MAX_VALUE;
            float f10 = -3.4028235E38f;
            for (int i17 = 0; i17 < childCount; i17++) {
                y.x xVar4 = ((n) hashMap.get(getChildAt(i17))).f9872g;
                float f11 = xVar4.f9932f + xVar4.f9931e;
                f9 = Math.min(f9, f11);
                f10 = Math.max(f10, f11);
            }
            for (int i18 = 0; i18 < childCount; i18++) {
                n nVar5 = (n) hashMap.get(getChildAt(i18));
                y.x xVar5 = nVar5.f9872g;
                float f12 = xVar5.f9931e;
                float f13 = xVar5.f9932f;
                nVar5.f9879n = 1.0f / (1.0f - f8);
                nVar5.f9878m = f8 - ((((f12 + f13) - f9) * f8) / (f10 - f9));
            }
        }
        this.F = CropImageView.DEFAULT_ASPECT_RATIO;
        this.G = CropImageView.DEFAULT_ASPECT_RATIO;
        this.K = true;
        invalidate();
    }

    public final void E(int i7, p pVar) {
        a0 a0Var = this.f1577s;
        if (a0Var != null) {
            a0Var.f9739g.put(i7, pVar);
        }
        this.B0.i(this.f1577s.b(this.f1585w), this.f1577s.b(this.f1589y));
        B();
        if (this.f1587x == i7) {
            pVar.b(this);
        }
    }

    @Override // p0.y
    public final void b(View view, View view2, int i7, int i8) {
        this.f1559a0 = getNanoTime();
        this.f1560b0 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.V = CropImageView.DEFAULT_ASPECT_RATIO;
        this.W = CropImageView.DEFAULT_ASPECT_RATIO;
    }

    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // p0.y
    public final void d(View view, int i7, int i8, int[] iArr, int i9) {
        y.z zVar;
        boolean z6;
        ?? r12;
        c0 c0Var;
        float f7;
        c0 c0Var2;
        c0 c0Var3;
        c0 c0Var4;
        int i10;
        a0 a0Var = this.f1577s;
        if (a0Var == null || (zVar = a0Var.f9735c) == null || !(!zVar.f9961o)) {
            return;
        }
        int i11 = -1;
        if (!z6 || (c0Var4 = zVar.f9958l) == null || (i10 = c0Var4.f9761e) == -1 || view.getId() == i10) {
            y.z zVar2 = a0Var.f9735c;
            if (zVar2 != null && (c0Var3 = zVar2.f9958l) != null && c0Var3.f9777u) {
                c0 c0Var5 = zVar.f9958l;
                if (c0Var5 != null && (c0Var5.f9779w & 4) != 0) {
                    i11 = i8;
                }
                float f8 = this.F;
                if ((f8 == 1.0f || f8 == CropImageView.DEFAULT_ASPECT_RATIO) && view.canScrollVertically(i11)) {
                    return;
                }
            }
            c0 c0Var6 = zVar.f9958l;
            if (c0Var6 != null && (c0Var6.f9779w & 1) != 0) {
                float f9 = i7;
                float f10 = i8;
                y.z zVar3 = a0Var.f9735c;
                if (zVar3 == null || (c0Var2 = zVar3.f9958l) == null) {
                    f7 = 0.0f;
                } else {
                    c0Var2.f9774r.v(c0Var2.f9760d, c0Var2.f9774r.getProgress(), c0Var2.f9764h, c0Var2.f9763g, c0Var2.f9770n);
                    float f11 = c0Var2.f9767k;
                    float[] fArr = c0Var2.f9770n;
                    if (f11 != CropImageView.DEFAULT_ASPECT_RATIO) {
                        if (fArr[0] == CropImageView.DEFAULT_ASPECT_RATIO) {
                            fArr[0] = 1.0E-7f;
                        }
                        f7 = (f9 * f11) / fArr[0];
                    } else {
                        if (fArr[1] == CropImageView.DEFAULT_ASPECT_RATIO) {
                            fArr[1] = 1.0E-7f;
                        }
                        f7 = (f10 * c0Var2.f9768l) / fArr[1];
                    }
                }
                float f12 = this.G;
                if ((f12 <= CropImageView.DEFAULT_ASPECT_RATIO && f7 < CropImageView.DEFAULT_ASPECT_RATIO) || (f12 >= 1.0f && f7 > CropImageView.DEFAULT_ASPECT_RATIO)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new s0(view));
                    return;
                }
            }
            float f13 = this.F;
            long nanoTime = getNanoTime();
            float f14 = i7;
            this.V = f14;
            float f15 = i8;
            this.W = f15;
            this.f1560b0 = (float) ((nanoTime - this.f1559a0) * 1.0E-9d);
            this.f1559a0 = nanoTime;
            y.z zVar4 = a0Var.f9735c;
            if (zVar4 != null && (c0Var = zVar4.f9958l) != null) {
                MotionLayout motionLayout = c0Var.f9774r;
                float progress = motionLayout.getProgress();
                if (!c0Var.f9769m) {
                    c0Var.f9769m = true;
                    motionLayout.setProgress(progress);
                }
                c0Var.f9774r.v(c0Var.f9760d, progress, c0Var.f9764h, c0Var.f9763g, c0Var.f9770n);
                float f16 = c0Var.f9767k;
                float[] fArr2 = c0Var.f9770n;
                if (Math.abs((c0Var.f9768l * fArr2[1]) + (f16 * fArr2[0])) < 0.01d) {
                    fArr2[0] = 0.01f;
                    fArr2[1] = 0.01f;
                }
                float f17 = c0Var.f9767k;
                float max = Math.max(Math.min(progress + (f17 != CropImageView.DEFAULT_ASPECT_RATIO ? (f14 * f17) / fArr2[0] : (f15 * c0Var.f9768l) / fArr2[1]), 1.0f), CropImageView.DEFAULT_ASPECT_RATIO);
                if (max != motionLayout.getProgress()) {
                    motionLayout.setProgress(max);
                }
            }
            if (f13 != this.F) {
                iArr[0] = i7;
                r12 = 1;
                iArr[1] = i8;
            } else {
                r12 = 1;
            }
            s(false);
            if (iArr[0] == 0 && iArr[r12] == 0) {
                return;
            }
            this.U = r12;
        }
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0360  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchDraw(android.graphics.Canvas r35) {
        /*
            Method dump skipped, instructions count: 1323
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    @Override // p0.y
    public final void e(int i7, View view) {
        c0 c0Var;
        a0 a0Var = this.f1577s;
        if (a0Var != null) {
            float f7 = this.f1560b0;
            float f8 = CropImageView.DEFAULT_ASPECT_RATIO;
            if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
                return;
            }
            float f9 = this.V / f7;
            float f10 = this.W / f7;
            y.z zVar = a0Var.f9735c;
            if (zVar == null || (c0Var = zVar.f9958l) == null) {
                return;
            }
            c0Var.f9769m = false;
            MotionLayout motionLayout = c0Var.f9774r;
            float progress = motionLayout.getProgress();
            c0Var.f9774r.v(c0Var.f9760d, progress, c0Var.f9764h, c0Var.f9763g, c0Var.f9770n);
            float f11 = c0Var.f9767k;
            float[] fArr = c0Var.f9770n;
            float f12 = f11 != CropImageView.DEFAULT_ASPECT_RATIO ? (f9 * f11) / fArr[0] : (f10 * c0Var.f9768l) / fArr[1];
            if (!Float.isNaN(f12)) {
                progress += f12 / 3.0f;
            }
            if (progress != CropImageView.DEFAULT_ASPECT_RATIO) {
                boolean z6 = progress != 1.0f;
                int i8 = c0Var.f9759c;
                if ((i8 != 3) && z6) {
                    if (progress >= 0.5d) {
                        f8 = 1.0f;
                    }
                    motionLayout.C(f8, f12, i8);
                }
            }
        }
    }

    @Override // p0.z
    public final void f(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        if (this.U || i7 != 0 || i8 != 0) {
            iArr[0] = iArr[0] + i9;
            iArr[1] = iArr[1] + i10;
        }
        this.U = false;
    }

    public int[] getConstraintSetIds() {
        a0 a0Var = this.f1577s;
        if (a0Var == null) {
            return null;
        }
        SparseArray sparseArray = a0Var.f9739g;
        int size = sparseArray.size();
        int[] iArr = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr[i7] = sparseArray.keyAt(i7);
        }
        return iArr;
    }

    public int getCurrentState() {
        return this.f1587x;
    }

    public ArrayList<y.z> getDefinedTransitions() {
        a0 a0Var = this.f1577s;
        if (a0Var == null) {
            return null;
        }
        return a0Var.f9736d;
    }

    public a getDesignTool() {
        if (this.R == null) {
            this.R = new a();
        }
        return this.R;
    }

    public int getEndState() {
        return this.f1589y;
    }

    public long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.G;
    }

    public a0 getScene() {
        return this.f1577s;
    }

    public int getStartState() {
        return this.f1585w;
    }

    public float getTargetPosition() {
        return this.I;
    }

    public Bundle getTransitionState() {
        if (this.f1586w0 == null) {
            this.f1586w0 = new u(this);
        }
        u uVar = this.f1586w0;
        MotionLayout motionLayout = uVar.f9924e;
        uVar.f9923d = motionLayout.f1589y;
        uVar.f9922c = motionLayout.f1585w;
        uVar.f9921b = motionLayout.getVelocity();
        uVar.f9920a = motionLayout.getProgress();
        u uVar2 = this.f1586w0;
        uVar2.getClass();
        Bundle bundle = new Bundle();
        bundle.putFloat("motion.progress", uVar2.f9920a);
        bundle.putFloat("motion.velocity", uVar2.f9921b);
        bundle.putInt("motion.StartState", uVar2.f9922c);
        bundle.putInt("motion.EndState", uVar2.f9923d);
        return bundle;
    }

    public long getTransitionTimeMs() {
        if (this.f1577s != null) {
            this.E = r0.c() / 1000.0f;
        }
        return (long) (this.E * 1000.0f);
    }

    public float getVelocity() {
        return this.f1583v;
    }

    @Override // p0.y
    public final void i(View view, int i7, int i8, int i9, int i10, int i11) {
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    @Override // p0.y
    public final boolean j(View view, View view2, int i7, int i8) {
        y.z zVar;
        c0 c0Var;
        a0 a0Var = this.f1577s;
        return (a0Var == null || (zVar = a0Var.f9735c) == null || (c0Var = zVar.f9958l) == null || (c0Var.f9779w & 2) != 0) ? false : true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public final void l(int i7) {
        this.f1696k = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        y.z zVar;
        int i7;
        super.onAttachedToWindow();
        Display display = getDisplay();
        if (display != null) {
            display.getRotation();
        }
        a0 a0Var = this.f1577s;
        if (a0Var != null && (i7 = this.f1587x) != -1) {
            p b7 = a0Var.b(i7);
            a0 a0Var2 = this.f1577s;
            int i8 = 0;
            loop0: while (true) {
                SparseArray sparseArray = a0Var2.f9739g;
                if (i8 >= sparseArray.size()) {
                    break;
                }
                int keyAt = sparseArray.keyAt(i8);
                SparseIntArray sparseIntArray = a0Var2.f9741i;
                int i9 = sparseIntArray.get(keyAt);
                int size = sparseIntArray.size();
                while (i9 > 0) {
                    if (i9 == keyAt) {
                        break loop0;
                    }
                    int i10 = size - 1;
                    if (size < 0) {
                        break loop0;
                    }
                    i9 = sparseIntArray.get(i9);
                    size = i10;
                }
                a0Var2.m(keyAt, this);
                i8++;
            }
            ArrayList arrayList = this.f1564f0;
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((MotionHelper) it.next()).getClass();
                }
            }
            if (b7 != null) {
                b7.b(this);
            }
            this.f1585w = this.f1587x;
        }
        z();
        u uVar = this.f1586w0;
        if (uVar != null) {
            if (this.f1592z0) {
                post(new s0(6, this));
                return;
            } else {
                uVar.a();
                return;
            }
        }
        a0 a0Var3 = this.f1577s;
        if (a0Var3 == null || (zVar = a0Var3.f9735c) == null || zVar.f9960n != 4) {
            return;
        }
        q(1.0f);
        this.f1588x0 = null;
        setState(w.SETUP);
        setState(w.MOVING);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0103  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [boolean, int] */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r26) {
        /*
            Method dump skipped, instructions count: 1211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        this.f1584v0 = true;
        try {
            if (this.f1577s == null) {
                super.onLayout(z6, i7, i8, i9, i10);
                return;
            }
            int i11 = i9 - i7;
            int i12 = i10 - i8;
            if (this.S != i11 || this.T != i12) {
                B();
                s(true);
            }
            this.S = i11;
            this.T = i12;
        } finally {
            this.f1584v0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        boolean z6;
        if (this.f1577s == null) {
            super.onMeasure(i7, i8);
            return;
        }
        boolean z7 = true;
        boolean z8 = (this.f1591z == i7 && this.A == i8) ? false : true;
        if (this.C0) {
            this.C0 = false;
            z();
            A();
            z8 = true;
        }
        if (this.f1693h) {
            z8 = true;
        }
        this.f1591z = i7;
        this.A = i8;
        int h7 = this.f1577s.h();
        y.z zVar = this.f1577s.f9735c;
        int i9 = zVar == null ? -1 : zVar.f9949c;
        j jVar = this.f1688c;
        s sVar = this.B0;
        if ((!z8 && h7 == sVar.f9911a && i9 == sVar.f9912b) || this.f1585w == -1) {
            if (z8) {
                super.onMeasure(i7, i8);
            }
            z6 = true;
        } else {
            super.onMeasure(i7, i8);
            sVar.i(this.f1577s.b(h7), this.f1577s.b(i9));
            sVar.k();
            sVar.f9911a = h7;
            sVar.f9912b = i9;
            z6 = false;
        }
        if (this.f1571m0 || z6) {
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int s6 = jVar.s() + getPaddingRight() + getPaddingLeft();
            int m6 = jVar.m() + paddingBottom;
            int i10 = this.f1576r0;
            if (i10 == Integer.MIN_VALUE || i10 == 0) {
                s6 = (int) ((this.f1580t0 * (this.f1574p0 - r1)) + this.f1572n0);
                requestLayout();
            }
            int i11 = this.f1578s0;
            if (i11 == Integer.MIN_VALUE || i11 == 0) {
                m6 = (int) ((this.f1580t0 * (this.f1575q0 - r2)) + this.f1573o0);
                requestLayout();
            }
            setMeasuredDimension(s6, m6);
        }
        float signum = Math.signum(this.I - this.G);
        long nanoTime = getNanoTime();
        o oVar = this.f1579t;
        float f7 = this.G + (!(oVar instanceof b) ? (((nanoTime - this.H) * signum) * 1.0E-9f) / this.E : 0.0f);
        if (this.J) {
            f7 = this.I;
        }
        if ((signum <= CropImageView.DEFAULT_ASPECT_RATIO || f7 < this.I) && (signum > CropImageView.DEFAULT_ASPECT_RATIO || f7 > this.I)) {
            z7 = false;
        } else {
            f7 = this.I;
        }
        if (oVar != null && !z7) {
            f7 = this.O ? oVar.getInterpolation((nanoTime - this.D) * 1.0E-9f) : oVar.getInterpolation(f7);
        }
        if ((signum > CropImageView.DEFAULT_ASPECT_RATIO && f7 >= this.I) || (signum <= CropImageView.DEFAULT_ASPECT_RATIO && f7 <= this.I)) {
            f7 = this.I;
        }
        this.f1580t0 = f7;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator = this.f1581u;
        if (interpolator != null) {
            f7 = interpolator.getInterpolation(f7);
        }
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            n nVar = (n) this.C.get(childAt);
            if (nVar != null) {
                nVar.e(f7, nanoTime2, childAt, this.f1582u0);
            }
        }
        if (this.f1571m0) {
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f7, float f8) {
        return false;
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        c0 c0Var;
        a0 a0Var = this.f1577s;
        if (a0Var != null) {
            boolean k7 = k();
            a0Var.f9748p = k7;
            y.z zVar = a0Var.f9735c;
            if (zVar == null || (c0Var = zVar.f9958l) == null) {
                return;
            }
            c0Var.c(k7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x07d7  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x07dc A[RETURN] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r34) {
        /*
            Method dump skipped, instructions count: 2019
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.f1565g0 == null) {
                this.f1565g0 = new CopyOnWriteArrayList();
            }
            this.f1565g0.add(motionHelper);
            if (motionHelper.f1555i) {
                if (this.f1562d0 == null) {
                    this.f1562d0 = new ArrayList();
                }
                this.f1562d0.add(motionHelper);
            }
            if (motionHelper.f1556j) {
                if (this.f1563e0 == null) {
                    this.f1563e0 = new ArrayList();
                }
                this.f1563e0.add(motionHelper);
            }
            if (motionHelper instanceof MotionEffect) {
                if (this.f1564f0 == null) {
                    this.f1564f0 = new ArrayList();
                }
                this.f1564f0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList arrayList = this.f1562d0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList arrayList2 = this.f1563e0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    public final void q(float f7) {
        if (this.f1577s == null) {
            return;
        }
        float f8 = this.G;
        float f9 = this.F;
        if (f8 != f9 && this.J) {
            this.G = f9;
        }
        float f10 = this.G;
        if (f10 == f7) {
            return;
        }
        this.O = false;
        this.I = f7;
        this.E = r0.c() / 1000.0f;
        setProgress(this.I);
        this.f1579t = null;
        this.f1581u = this.f1577s.e();
        this.J = false;
        this.D = getNanoTime();
        this.K = true;
        this.F = f10;
        this.G = f10;
        invalidate();
    }

    public final void r() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            n nVar = (n) this.C.get(getChildAt(i7));
            if (nVar != null) {
                "button".equals(f.G(nVar.f9867b));
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public final void requestLayout() {
        a0 a0Var;
        y.z zVar;
        if (!this.f1571m0 && this.f1587x == -1 && (a0Var = this.f1577s) != null && (zVar = a0Var.f9735c) != null) {
            int i7 = zVar.f9963q;
            if (i7 == 0) {
                return;
            }
            if (i7 == 2) {
                int childCount = getChildCount();
                for (int i8 = 0; i8 < childCount; i8++) {
                    ((n) this.C.get(getChildAt(i8))).f9869d = true;
                }
                return;
            }
        }
        super.requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void s(boolean r23) {
        /*
            Method dump skipped, instructions count: 628
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.s(boolean):void");
    }

    public void setDebugMode(int i7) {
        this.M = i7;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z6) {
        this.f1592z0 = z6;
    }

    public void setInteractionEnabled(boolean z6) {
        this.B = z6;
    }

    public void setInterpolatedProgress(float f7) {
        if (this.f1577s != null) {
            setState(w.MOVING);
            Interpolator e7 = this.f1577s.e();
            if (e7 != null) {
                setProgress(e7.getInterpolation(f7));
                return;
            }
        }
        setProgress(f7);
    }

    public void setOnHide(float f7) {
        ArrayList arrayList = this.f1563e0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((MotionHelper) this.f1563e0.get(i7)).setProgress(f7);
            }
        }
    }

    public void setOnShow(float f7) {
        ArrayList arrayList = this.f1562d0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((MotionHelper) this.f1562d0.get(i7)).setProgress(f7);
            }
        }
    }

    public void setProgress(float f7) {
        if (f7 >= CropImageView.DEFAULT_ASPECT_RATIO) {
            int i7 = (f7 > 1.0f ? 1 : (f7 == 1.0f ? 0 : -1));
        }
        if (!isAttachedToWindow()) {
            if (this.f1586w0 == null) {
                this.f1586w0 = new u(this);
            }
            this.f1586w0.f9920a = f7;
            return;
        }
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.G == 1.0f && this.f1587x == this.f1589y) {
                setState(w.MOVING);
            }
            this.f1587x = this.f1585w;
            if (this.G == CropImageView.DEFAULT_ASPECT_RATIO) {
                setState(w.FINISHED);
            }
        } else if (f7 >= 1.0f) {
            if (this.G == CropImageView.DEFAULT_ASPECT_RATIO && this.f1587x == this.f1585w) {
                setState(w.MOVING);
            }
            this.f1587x = this.f1589y;
            if (this.G == 1.0f) {
                setState(w.FINISHED);
            }
        } else {
            this.f1587x = -1;
            setState(w.MOVING);
        }
        if (this.f1577s == null) {
            return;
        }
        this.J = true;
        this.I = f7;
        this.F = f7;
        this.H = -1L;
        this.D = -1L;
        this.f1579t = null;
        this.K = true;
        invalidate();
    }

    public void setProgress(float f7, float f8) {
        if (!isAttachedToWindow()) {
            if (this.f1586w0 == null) {
                this.f1586w0 = new u(this);
            }
            u uVar = this.f1586w0;
            uVar.f9920a = f7;
            uVar.f9921b = f8;
            return;
        }
        setProgress(f7);
        setState(w.MOVING);
        this.f1583v = f8;
        float f9 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f8 != CropImageView.DEFAULT_ASPECT_RATIO) {
            if (f8 > CropImageView.DEFAULT_ASPECT_RATIO) {
                f9 = 1.0f;
            }
            q(f9);
        } else {
            if (f7 == CropImageView.DEFAULT_ASPECT_RATIO || f7 == 1.0f) {
                return;
            }
            if (f7 > 0.5f) {
                f9 = 1.0f;
            }
            q(f9);
        }
    }

    public void setScene(a0 a0Var) {
        c0 c0Var;
        this.f1577s = a0Var;
        boolean k7 = k();
        a0Var.f9748p = k7;
        y.z zVar = a0Var.f9735c;
        if (zVar != null && (c0Var = zVar.f9958l) != null) {
            c0Var.c(k7);
        }
        B();
    }

    public void setStartState(int i7) {
        if (isAttachedToWindow()) {
            this.f1587x = i7;
            return;
        }
        if (this.f1586w0 == null) {
            this.f1586w0 = new u(this);
        }
        u uVar = this.f1586w0;
        uVar.f9922c = i7;
        uVar.f9923d = i7;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int i7, int i8, int i9) {
        setState(w.SETUP);
        this.f1587x = i7;
        this.f1585w = -1;
        this.f1589y = -1;
        a0.i iVar = this.f1696k;
        if (iVar != null) {
            iVar.b(i8, i9, i7);
            return;
        }
        a0 a0Var = this.f1577s;
        if (a0Var != null) {
            a0Var.b(i7).b(this);
        }
    }

    public void setState(w wVar) {
        w wVar2 = w.FINISHED;
        if (wVar == wVar2 && this.f1587x == -1) {
            return;
        }
        w wVar3 = this.A0;
        this.A0 = wVar;
        w wVar4 = w.MOVING;
        if (wVar3 == wVar4 && wVar == wVar4) {
            t();
        }
        int i7 = y.p.f9892a[wVar3.ordinal()];
        if (i7 != 1 && i7 != 2) {
            if (i7 == 3 && wVar == wVar2) {
                u();
                return;
            }
            return;
        }
        if (wVar == wVar4) {
            t();
        }
        if (wVar == wVar2) {
            u();
        }
    }

    public void setTransition(int i7) {
        if (this.f1577s != null) {
            y.z w6 = w(i7);
            this.f1585w = w6.f9950d;
            this.f1589y = w6.f9949c;
            if (!isAttachedToWindow()) {
                if (this.f1586w0 == null) {
                    this.f1586w0 = new u(this);
                }
                u uVar = this.f1586w0;
                uVar.f9922c = this.f1585w;
                uVar.f9923d = this.f1589y;
                return;
            }
            int i8 = this.f1587x;
            float f7 = i8 == this.f1585w ? 0.0f : i8 == this.f1589y ? 1.0f : Float.NaN;
            a0 a0Var = this.f1577s;
            a0Var.f9735c = w6;
            c0 c0Var = w6.f9958l;
            if (c0Var != null) {
                c0Var.c(a0Var.f9748p);
            }
            this.B0.i(this.f1577s.b(this.f1585w), this.f1577s.b(this.f1589y));
            B();
            if (this.G != f7) {
                if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
                    r();
                    this.f1577s.b(this.f1585w).b(this);
                } else if (f7 == 1.0f) {
                    r();
                    this.f1577s.b(this.f1589y).b(this);
                }
            }
            this.G = Float.isNaN(f7) ? 0.0f : f7;
            if (!Float.isNaN(f7)) {
                setProgress(f7);
            } else {
                f.E();
                q(CropImageView.DEFAULT_ASPECT_RATIO);
            }
        }
    }

    public void setTransition(int i7, int i8) {
        if (!isAttachedToWindow()) {
            if (this.f1586w0 == null) {
                this.f1586w0 = new u(this);
            }
            u uVar = this.f1586w0;
            uVar.f9922c = i7;
            uVar.f9923d = i8;
            return;
        }
        a0 a0Var = this.f1577s;
        if (a0Var != null) {
            this.f1585w = i7;
            this.f1589y = i8;
            a0Var.n(i7, i8);
            this.B0.i(this.f1577s.b(i7), this.f1577s.b(i8));
            B();
            this.G = CropImageView.DEFAULT_ASPECT_RATIO;
            q(CropImageView.DEFAULT_ASPECT_RATIO);
        }
    }

    public void setTransition(y.z zVar) {
        c0 c0Var;
        a0 a0Var = this.f1577s;
        a0Var.f9735c = zVar;
        if (zVar != null && (c0Var = zVar.f9958l) != null) {
            c0Var.c(a0Var.f9748p);
        }
        setState(w.SETUP);
        int i7 = this.f1587x;
        y.z zVar2 = this.f1577s.f9735c;
        if (i7 == (zVar2 == null ? -1 : zVar2.f9949c)) {
            this.G = 1.0f;
            this.F = 1.0f;
            this.I = 1.0f;
        } else {
            this.G = CropImageView.DEFAULT_ASPECT_RATIO;
            this.F = CropImageView.DEFAULT_ASPECT_RATIO;
            this.I = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        this.H = (zVar.f9964r & 1) != 0 ? -1L : getNanoTime();
        int h7 = this.f1577s.h();
        a0 a0Var2 = this.f1577s;
        y.z zVar3 = a0Var2.f9735c;
        int i8 = zVar3 != null ? zVar3.f9949c : -1;
        if (h7 == this.f1585w && i8 == this.f1589y) {
            return;
        }
        this.f1585w = h7;
        this.f1589y = i8;
        a0Var2.n(h7, i8);
        p b7 = this.f1577s.b(this.f1585w);
        p b8 = this.f1577s.b(this.f1589y);
        s sVar = this.B0;
        sVar.i(b7, b8);
        int i9 = this.f1585w;
        int i10 = this.f1589y;
        sVar.f9911a = i9;
        sVar.f9912b = i10;
        sVar.k();
        B();
    }

    public void setTransitionDuration(int i7) {
        a0 a0Var = this.f1577s;
        if (a0Var == null) {
            return;
        }
        y.z zVar = a0Var.f9735c;
        if (zVar != null) {
            zVar.f9954h = Math.max(i7, 8);
        } else {
            a0Var.f9742j = i7;
        }
    }

    public void setTransitionListener(v vVar) {
        this.L = vVar;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.f1586w0 == null) {
            this.f1586w0 = new u(this);
        }
        u uVar = this.f1586w0;
        uVar.getClass();
        uVar.f9920a = bundle.getFloat("motion.progress");
        uVar.f9921b = bundle.getFloat("motion.velocity");
        uVar.f9922c = bundle.getInt("motion.StartState");
        uVar.f9923d = bundle.getInt("motion.EndState");
        if (isAttachedToWindow()) {
            this.f1586w0.a();
        }
    }

    public final void t() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        if ((this.L == null && ((copyOnWriteArrayList2 = this.f1565g0) == null || copyOnWriteArrayList2.isEmpty())) || this.f1570l0 == this.F) {
            return;
        }
        if (this.f1569k0 != -1 && (copyOnWriteArrayList = this.f1565g0) != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                ((v) it.next()).getClass();
            }
        }
        this.f1569k0 = -1;
        this.f1570l0 = this.F;
        v vVar = this.L;
        if (vVar != null) {
            vVar.b();
        }
        CopyOnWriteArrayList copyOnWriteArrayList3 = this.f1565g0;
        if (copyOnWriteArrayList3 != null) {
            Iterator it2 = copyOnWriteArrayList3.iterator();
            while (it2.hasNext()) {
                ((v) it2.next()).b();
            }
        }
    }

    @Override // android.view.View
    public final String toString() {
        Context context = getContext();
        return f.F(context, this.f1585w) + "->" + f.F(context, this.f1589y) + " (pos:" + this.G + " Dpos/Dt:" + this.f1583v;
    }

    public final void u() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        if ((this.L != null || ((copyOnWriteArrayList = this.f1565g0) != null && !copyOnWriteArrayList.isEmpty())) && this.f1569k0 == -1) {
            this.f1569k0 = this.f1587x;
            ArrayList arrayList = this.G0;
            int intValue = !arrayList.isEmpty() ? ((Integer) arrayList.get(arrayList.size() - 1)).intValue() : -1;
            int i7 = this.f1587x;
            if (intValue != i7 && i7 != -1) {
                arrayList.add(Integer.valueOf(i7));
            }
        }
        A();
        Runnable runnable = this.f1588x0;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void v(int i7, float f7, float f8, float f9, float[] fArr) {
        View c7 = c(i7);
        n nVar = (n) this.C.get(c7);
        if (nVar != null) {
            nVar.d(f7, f8, f9, fArr);
            c7.getY();
        } else {
            if (c7 == null) {
                return;
            }
            c7.getContext().getResources().getResourceName(i7);
        }
    }

    public final y.z w(int i7) {
        Iterator it = this.f1577s.f9736d.iterator();
        while (it.hasNext()) {
            y.z zVar = (y.z) it.next();
            if (zVar.f9947a == i7) {
                return zVar;
            }
        }
        return null;
    }

    public final boolean x(float f7, float f8, MotionEvent motionEvent, View view) {
        boolean z6;
        boolean onTouchEvent;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (x((r3.getLeft() + f7) - view.getScrollX(), (r3.getTop() + f8) - view.getScrollY(), motionEvent, viewGroup.getChildAt(childCount))) {
                    z6 = true;
                    break;
                }
            }
        }
        z6 = false;
        if (!z6) {
            RectF rectF = this.D0;
            rectF.set(f7, f8, (view.getRight() + f7) - view.getLeft(), (view.getBottom() + f8) - view.getTop());
            if (motionEvent.getAction() != 0 || rectF.contains(motionEvent.getX(), motionEvent.getY())) {
                float f9 = -f7;
                float f10 = -f8;
                Matrix matrix = view.getMatrix();
                if (matrix.isIdentity()) {
                    motionEvent.offsetLocation(f9, f10);
                    onTouchEvent = view.onTouchEvent(motionEvent);
                    motionEvent.offsetLocation(-f9, -f10);
                } else {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(f9, f10);
                    if (this.F0 == null) {
                        this.F0 = new Matrix();
                    }
                    matrix.invert(this.F0);
                    obtain.transform(this.F0);
                    onTouchEvent = view.onTouchEvent(obtain);
                    obtain.recycle();
                }
                if (onTouchEvent) {
                    return true;
                }
            }
        }
        return z6;
    }

    public final void y(AttributeSet attributeSet) {
        a0 a0Var;
        a0 a0Var2;
        H0 = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z6 = true;
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.MotionLayout_layoutDescription) {
                    this.f1577s = new a0(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == t.MotionLayout_currentState) {
                    this.f1587x = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == t.MotionLayout_motionProgress) {
                    this.I = obtainStyledAttributes.getFloat(index, CropImageView.DEFAULT_ASPECT_RATIO);
                    this.K = true;
                } else if (index == t.MotionLayout_applyMotionScene) {
                    z6 = obtainStyledAttributes.getBoolean(index, z6);
                } else if (index == t.MotionLayout_showPaths) {
                    if (this.M == 0) {
                        this.M = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == t.MotionLayout_motionDebug) {
                    this.M = obtainStyledAttributes.getInt(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            if (!z6) {
                this.f1577s = null;
            }
        }
        if (this.M != 0 && (a0Var2 = this.f1577s) != null) {
            int h7 = a0Var2.h();
            a0 a0Var3 = this.f1577s;
            p b7 = a0Var3.b(a0Var3.h());
            f.F(getContext(), h7);
            int childCount = getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = getChildAt(i8);
                if (b7.i(childAt.getId()) == null) {
                    f.G(childAt);
                }
            }
            Integer[] numArr = (Integer[]) b7.f174f.keySet().toArray(new Integer[0]);
            int length = numArr.length;
            int[] iArr = new int[length];
            for (int i9 = 0; i9 < length; i9++) {
                iArr[i9] = numArr[i9].intValue();
            }
            for (int i10 = 0; i10 < length; i10++) {
                int i11 = iArr[i10];
                f.F(getContext(), i11);
                findViewById(iArr[i10]);
                int i12 = b7.h(i11).f85e.f96d;
                int i13 = b7.h(i11).f85e.f94c;
            }
            SparseIntArray sparseIntArray = new SparseIntArray();
            SparseIntArray sparseIntArray2 = new SparseIntArray();
            Iterator it = this.f1577s.f9736d.iterator();
            while (it.hasNext()) {
                y.z zVar = (y.z) it.next();
                y.z zVar2 = this.f1577s.f9735c;
                int i14 = zVar.f9950d;
                int i15 = zVar.f9949c;
                f.F(getContext(), i14);
                f.F(getContext(), i15);
                sparseIntArray.get(i14);
                sparseIntArray2.get(i15);
                sparseIntArray.put(i14, i15);
                sparseIntArray2.put(i15, i14);
                this.f1577s.b(i14);
                this.f1577s.b(i15);
            }
        }
        if (this.f1587x != -1 || (a0Var = this.f1577s) == null) {
            return;
        }
        this.f1587x = a0Var.h();
        this.f1585w = this.f1577s.h();
        y.z zVar3 = this.f1577s.f9735c;
        this.f1589y = zVar3 != null ? zVar3.f9949c : -1;
    }

    public final void z() {
        y.z zVar;
        c0 c0Var;
        View findViewById;
        View findViewById2;
        a0 a0Var = this.f1577s;
        if (a0Var == null) {
            return;
        }
        if (a0Var.a(this.f1587x, this)) {
            requestLayout();
            return;
        }
        int i7 = this.f1587x;
        KeyEvent.Callback callback = null;
        if (i7 != -1) {
            a0 a0Var2 = this.f1577s;
            ArrayList arrayList = a0Var2.f9736d;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                y.z zVar2 = (y.z) it.next();
                if (zVar2.f9959m.size() > 0) {
                    Iterator it2 = zVar2.f9959m.iterator();
                    while (it2.hasNext()) {
                        int i8 = ((y.y) it2.next()).f9945b;
                        if (i8 != -1 && (findViewById2 = findViewById(i8)) != null) {
                            findViewById2.setOnClickListener(null);
                        }
                    }
                }
            }
            ArrayList arrayList2 = a0Var2.f9738f;
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                y.z zVar3 = (y.z) it3.next();
                if (zVar3.f9959m.size() > 0) {
                    Iterator it4 = zVar3.f9959m.iterator();
                    while (it4.hasNext()) {
                        int i9 = ((y.y) it4.next()).f9945b;
                        if (i9 != -1 && (findViewById = findViewById(i9)) != null) {
                            findViewById.setOnClickListener(null);
                        }
                    }
                }
            }
            Iterator it5 = arrayList.iterator();
            while (it5.hasNext()) {
                y.z zVar4 = (y.z) it5.next();
                if (zVar4.f9959m.size() > 0) {
                    Iterator it6 = zVar4.f9959m.iterator();
                    while (it6.hasNext()) {
                        ((y.y) it6.next()).a(this, i7, zVar4);
                    }
                }
            }
            Iterator it7 = arrayList2.iterator();
            while (it7.hasNext()) {
                y.z zVar5 = (y.z) it7.next();
                if (zVar5.f9959m.size() > 0) {
                    Iterator it8 = zVar5.f9959m.iterator();
                    while (it8.hasNext()) {
                        ((y.y) it8.next()).a(this, i7, zVar5);
                    }
                }
            }
        }
        if (!this.f1577s.o() || (zVar = this.f1577s.f9735c) == null || (c0Var = zVar.f9958l) == null) {
            return;
        }
        int i10 = c0Var.f9760d;
        if (i10 != -1) {
            MotionLayout motionLayout = c0Var.f9774r;
            KeyEvent.Callback findViewById3 = motionLayout.findViewById(i10);
            if (findViewById3 == null) {
                f.F(motionLayout.getContext(), c0Var.f9760d);
            }
            callback = findViewById3;
        }
        if (callback instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) callback;
            nestedScrollView.setOnTouchListener(new b0(0));
            nestedScrollView.setOnScrollChangeListener(new p3.f());
        }
    }
}
