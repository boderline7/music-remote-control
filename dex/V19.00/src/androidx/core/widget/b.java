package androidx.core.widget;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.appcompat.app.s0;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class b implements View.OnTouchListener {
    public static final int EDGE_TYPE_INSIDE = 0;
    public static final int EDGE_TYPE_INSIDE_EXTEND = 1;
    public static final int EDGE_TYPE_OUTSIDE = 2;
    public static final float NO_MAX = Float.MAX_VALUE;
    public static final float NO_MIN = 0.0f;
    public static final float RELATIVE_UNSPECIFIED = 0.0f;

    /* renamed from: q, reason: collision with root package name */
    public static final int f1802q = ViewConfiguration.getTapTimeout();

    /* renamed from: a, reason: collision with root package name */
    public final a f1803a;

    /* renamed from: b, reason: collision with root package name */
    public final AccelerateInterpolator f1804b;

    /* renamed from: c, reason: collision with root package name */
    public final View f1805c;

    /* renamed from: d, reason: collision with root package name */
    public s0 f1806d;

    /* renamed from: e, reason: collision with root package name */
    public final float[] f1807e;

    /* renamed from: f, reason: collision with root package name */
    public final float[] f1808f;

    /* renamed from: g, reason: collision with root package name */
    public final int f1809g;

    /* renamed from: h, reason: collision with root package name */
    public final int f1810h;

    /* renamed from: i, reason: collision with root package name */
    public final float[] f1811i;

    /* renamed from: j, reason: collision with root package name */
    public final float[] f1812j;

    /* renamed from: k, reason: collision with root package name */
    public final float[] f1813k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f1814l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f1815m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f1816n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f1817o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f1818p;

    public b(View view) {
        a aVar = new a();
        aVar.f1797e = Long.MIN_VALUE;
        aVar.f1799g = -1L;
        aVar.f1798f = 0L;
        this.f1803a = aVar;
        this.f1804b = new AccelerateInterpolator();
        float[] fArr = {CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
        this.f1807e = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f1808f = fArr2;
        float[] fArr3 = {CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
        this.f1811i = fArr3;
        float[] fArr4 = {CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
        this.f1812j = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f1813k = fArr5;
        this.f1805c = view;
        float f7 = Resources.getSystem().getDisplayMetrics().density;
        float f8 = ((int) ((1575.0f * f7) + 0.5f)) / 1000.0f;
        fArr5[0] = f8;
        fArr5[1] = f8;
        float f9 = ((int) ((f7 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f9;
        fArr4[1] = f9;
        this.f1809g = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.f1810h = f1802q;
        aVar.f1793a = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        aVar.f1794b = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    }

    public static float b(float f7, float f8, float f9) {
        return f7 > f9 ? f9 : f7 < f8 ? f8 : f7;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float a(float r4, float r5, float r6, int r7) {
        /*
            r3 = this;
            float[] r0 = r3.f1807e
            r0 = r0[r7]
            float[] r1 = r3.f1808f
            r1 = r1[r7]
            float r0 = r0 * r5
            r2 = 0
            float r0 = b(r0, r2, r1)
            float r1 = r3.c(r4, r0)
            float r5 = r5 - r4
            float r4 = r3.c(r5, r0)
            float r4 = r4 - r1
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            android.view.animation.AccelerateInterpolator r0 = r3.f1804b
            if (r5 >= 0) goto L25
            float r4 = -r4
            float r4 = r0.getInterpolation(r4)
            float r4 = -r4
            goto L2d
        L25:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 <= 0) goto L36
            float r4 = r0.getInterpolation(r4)
        L2d:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r4 = b(r4, r5, r0)
            goto L37
        L36:
            r4 = r2
        L37:
            int r5 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r5 != 0) goto L3c
            return r2
        L3c:
            float[] r0 = r3.f1811i
            r0 = r0[r7]
            float[] r1 = r3.f1812j
            r1 = r1[r7]
            float[] r2 = r3.f1813k
            r7 = r2[r7]
            float r0 = r0 * r6
            if (r5 <= 0) goto L51
            float r4 = r4 * r0
            float r4 = b(r4, r1, r7)
            return r4
        L51:
            float r4 = -r4
            float r4 = r4 * r0
            float r4 = b(r4, r1, r7)
            float r4 = -r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.b.a(float, float, float, int):float");
    }

    public final float c(float f7, float f8) {
        if (f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        int i7 = this.f1809g;
        if (i7 == 0 || i7 == 1) {
            if (f7 < f8) {
                if (f7 >= CropImageView.DEFAULT_ASPECT_RATIO) {
                    return 1.0f - (f7 / f8);
                }
                if (this.f1817o && i7 == 1) {
                    return 1.0f;
                }
            }
        } else if (i7 == 2 && f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            return f7 / (-f8);
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void d() {
        int i7 = 0;
        if (this.f1815m) {
            this.f1817o = false;
            return;
        }
        a aVar = this.f1803a;
        aVar.getClass();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i8 = (int) (currentAnimationTimeMillis - aVar.f1797e);
        int i9 = aVar.f1794b;
        if (i8 > i9) {
            i7 = i9;
        } else if (i8 >= 0) {
            i7 = i8;
        }
        aVar.f1801i = i7;
        aVar.f1800h = aVar.a(currentAnimationTimeMillis);
        aVar.f1799g = currentAnimationTimeMillis;
    }

    public final boolean e() {
        ListView listView;
        int count;
        a aVar = this.f1803a;
        float f7 = aVar.f1796d;
        int abs = (int) (f7 / Math.abs(f7));
        Math.abs(aVar.f1795c);
        if (abs == 0 || (count = (listView = ((i) this).f1821r).getCount()) == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i7 = firstVisiblePosition + childCount;
        if (abs > 0) {
            if (i7 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (abs >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r0 != 3) goto L29;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
            r7 = this;
            boolean r0 = r7.f1818p
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r9.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r8 = 3
            if (r0 == r8) goto L16
            goto L7c
        L16:
            r7.d()
            goto L7c
        L1a:
            r7.f1816n = r2
            r7.f1814l = r1
        L1e:
            float r0 = r9.getX()
            int r3 = r8.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r7.f1805c
            int r5 = r4.getWidth()
            float r5 = (float) r5
            float r0 = r7.a(r0, r3, r5, r1)
            float r9 = r9.getY()
            int r8 = r8.getHeight()
            float r8 = (float) r8
            int r3 = r4.getHeight()
            float r3 = (float) r3
            float r8 = r7.a(r9, r8, r3, r2)
            androidx.core.widget.a r9 = r7.f1803a
            r9.f1795c = r0
            r9.f1796d = r8
            boolean r8 = r7.f1817o
            if (r8 != 0) goto L7c
            boolean r8 = r7.e()
            if (r8 == 0) goto L7c
            androidx.appcompat.app.s0 r8 = r7.f1806d
            if (r8 != 0) goto L60
            androidx.appcompat.app.s0 r8 = new androidx.appcompat.app.s0
            r9 = 7
            r8.<init>(r9, r7)
            r7.f1806d = r8
        L60:
            r7.f1817o = r2
            r7.f1815m = r2
            boolean r8 = r7.f1814l
            if (r8 != 0) goto L75
            int r8 = r7.f1810h
            if (r8 <= 0) goto L75
            androidx.appcompat.app.s0 r9 = r7.f1806d
            long r5 = (long) r8
            int r8 = p0.d1.OVER_SCROLL_ALWAYS
            p0.l0.n(r4, r9, r5)
            goto L7a
        L75:
            androidx.appcompat.app.s0 r8 = r7.f1806d
            r8.run()
        L7a:
            r7.f1814l = r2
        L7c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.b.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
