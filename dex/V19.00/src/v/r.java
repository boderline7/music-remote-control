package v;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.constraintlayout.widget.VirtualLayout;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    public int f9360a;

    /* renamed from: b, reason: collision with root package name */
    public int f9361b;

    /* renamed from: c, reason: collision with root package name */
    public int f9362c;

    /* renamed from: d, reason: collision with root package name */
    public int f9363d;

    /* renamed from: e, reason: collision with root package name */
    public int f9364e;

    /* renamed from: f, reason: collision with root package name */
    public int f9365f;

    /* renamed from: g, reason: collision with root package name */
    public final Object f9366g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f9367h;

    public r(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.f9367h = constraintLayout;
        this.f9366g = constraintLayout2;
    }

    public r(s sVar, u.i iVar, s.d dVar, int i7) {
        this.f9367h = sVar;
        this.f9366g = new WeakReference(iVar);
        u.f fVar = iVar.J;
        dVar.getClass();
        this.f9360a = s.d.n(fVar);
        this.f9361b = s.d.n(iVar.K);
        this.f9362c = s.d.n(iVar.L);
        this.f9363d = s.d.n(iVar.M);
        this.f9364e = s.d.n(iVar.N);
        this.f9365f = i7;
    }

    public static boolean a(int i7, int i8, int i9) {
        if (i7 == i8) {
            return true;
        }
        int mode = View.MeasureSpec.getMode(i7);
        View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        if (mode2 == 1073741824) {
            return (mode == Integer.MIN_VALUE || mode == 0) && i9 == size;
        }
        return false;
    }

    public final void b(u.i iVar, b bVar) {
        int makeMeasureSpec;
        int makeMeasureSpec2;
        int max;
        int i7;
        int i8;
        int i9;
        boolean z6;
        int baseline;
        int i10;
        int childMeasureSpec;
        if (iVar == null) {
            return;
        }
        if (iVar.f9145i0 == 8 && !iVar.F) {
            bVar.f9319e = 0;
            bVar.f9320f = 0;
            bVar.f9321g = 0;
            return;
        }
        if (iVar.V == null) {
            return;
        }
        u.h hVar = bVar.f9315a;
        u.h hVar2 = bVar.f9316b;
        int i11 = bVar.f9317c;
        int i12 = bVar.f9318d;
        int i13 = this.f9360a + this.f9361b;
        int i14 = this.f9362c;
        View view = (View) iVar.f9143h0;
        int[] iArr = a0.d.f10a;
        int i15 = iArr[hVar.ordinal()];
        u.f fVar = iVar.L;
        u.f fVar2 = iVar.J;
        if (i15 != 1) {
            if (i15 == 2) {
                childMeasureSpec = ViewGroup.getChildMeasureSpec(this.f9364e, i14, -2);
            } else if (i15 == 3) {
                int i16 = this.f9364e;
                int i17 = fVar2 != null ? fVar2.f9122g : 0;
                if (fVar != null) {
                    i17 += fVar.f9122g;
                }
                childMeasureSpec = ViewGroup.getChildMeasureSpec(i16, i14 + i17, -1);
            } else if (i15 != 4) {
                makeMeasureSpec = 0;
            } else {
                makeMeasureSpec = ViewGroup.getChildMeasureSpec(this.f9364e, i14, -2);
                boolean z7 = iVar.f9162r == 1;
                int i18 = bVar.f9324j;
                if (i18 == 1 || i18 == 2) {
                    boolean z8 = view.getMeasuredHeight() == iVar.m();
                    if (bVar.f9324j == 2 || !z7 || ((z7 && z8) || (view instanceof Placeholder) || iVar.C())) {
                        childMeasureSpec = View.MeasureSpec.makeMeasureSpec(iVar.s(), c.EXACTLY);
                    }
                }
            }
            makeMeasureSpec = childMeasureSpec;
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i11, c.EXACTLY);
        }
        int i19 = iArr[hVar2.ordinal()];
        if (i19 == 1) {
            makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i12, c.EXACTLY);
        } else if (i19 == 2) {
            makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f9365f, i13, -2);
        } else if (i19 == 3) {
            int i20 = this.f9365f;
            int i21 = fVar2 != null ? iVar.K.f9122g : 0;
            if (fVar != null) {
                i21 += iVar.M.f9122g;
            }
            makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(i20, i13 + i21, -1);
        } else if (i19 != 4) {
            makeMeasureSpec2 = 0;
        } else {
            makeMeasureSpec2 = ViewGroup.getChildMeasureSpec(this.f9365f, i13, -2);
            boolean z9 = iVar.f9164s == 1;
            int i22 = bVar.f9324j;
            if (i22 == 1 || i22 == 2) {
                boolean z10 = view.getMeasuredWidth() == iVar.s();
                if (bVar.f9324j == 2 || !z9 || ((z9 && z10) || (view instanceof Placeholder) || iVar.D())) {
                    makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iVar.m(), c.EXACTLY);
                }
            }
        }
        u.j jVar = (u.j) iVar.V;
        Object obj = this.f9367h;
        if (jVar != null && u.q.b(((ConstraintLayout) obj).f1694i, 256) && view.getMeasuredWidth() == iVar.s() && view.getMeasuredWidth() < jVar.s() && view.getMeasuredHeight() == iVar.m() && view.getMeasuredHeight() < jVar.m() && view.getBaseline() == iVar.f9133c0 && !iVar.B() && a(iVar.H, makeMeasureSpec, iVar.s()) && a(iVar.I, makeMeasureSpec2, iVar.m())) {
            bVar.f9319e = iVar.s();
            bVar.f9320f = iVar.m();
            bVar.f9321g = iVar.f9133c0;
            return;
        }
        u.h hVar3 = u.h.MATCH_CONSTRAINT;
        boolean z11 = hVar == hVar3;
        boolean z12 = hVar2 == hVar3;
        u.h hVar4 = u.h.MATCH_PARENT;
        boolean z13 = hVar2 == hVar4 || hVar2 == u.h.FIXED;
        boolean z14 = hVar == hVar4 || hVar == u.h.FIXED;
        boolean z15 = z11 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        boolean z16 = z12 && iVar.Y > CropImageView.DEFAULT_ASPECT_RATIO;
        if (view == null) {
            return;
        }
        a0.f fVar3 = (a0.f) view.getLayoutParams();
        int i23 = bVar.f9324j;
        if (i23 != 1 && i23 != 2 && z11 && iVar.f9162r == 0 && z12 && iVar.f9164s == 0) {
            baseline = 0;
            i10 = -1;
            z6 = false;
            max = 0;
            i8 = 0;
        } else {
            if ((view instanceof VirtualLayout) && (iVar instanceof u.s)) {
                ((VirtualLayout) view).s((u.s) iVar, makeMeasureSpec, makeMeasureSpec2);
            } else {
                view.measure(makeMeasureSpec, makeMeasureSpec2);
            }
            iVar.H = makeMeasureSpec;
            iVar.I = makeMeasureSpec2;
            iVar.f9140g = false;
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            int baseline2 = view.getBaseline();
            int i24 = iVar.f9168u;
            max = i24 > 0 ? Math.max(i24, measuredWidth) : measuredWidth;
            int i25 = iVar.f9170v;
            if (i25 > 0) {
                max = Math.min(i25, max);
            }
            int i26 = iVar.f9172x;
            if (i26 > 0) {
                i8 = Math.max(i26, measuredHeight);
                i7 = makeMeasureSpec2;
            } else {
                i7 = makeMeasureSpec2;
                i8 = measuredHeight;
            }
            int i27 = iVar.f9173y;
            if (i27 > 0) {
                i8 = Math.min(i27, i8);
            }
            if (!u.q.b(((ConstraintLayout) obj).f1694i, 1)) {
                if (z15 && z13) {
                    max = (int) ((i8 * iVar.Y) + 0.5f);
                } else if (z16 && z14) {
                    i8 = (int) ((max / iVar.Y) + 0.5f);
                }
            }
            if (measuredWidth == max && measuredHeight == i8) {
                baseline = baseline2;
                i10 = -1;
                z6 = false;
            } else {
                if (measuredWidth != max) {
                    i9 = c.EXACTLY;
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max, c.EXACTLY);
                } else {
                    i9 = c.EXACTLY;
                }
                int makeMeasureSpec3 = measuredHeight != i8 ? View.MeasureSpec.makeMeasureSpec(i8, i9) : i7;
                view.measure(makeMeasureSpec, makeMeasureSpec3);
                iVar.H = makeMeasureSpec;
                iVar.I = makeMeasureSpec3;
                z6 = false;
                iVar.f9140g = false;
                max = view.getMeasuredWidth();
                i8 = view.getMeasuredHeight();
                baseline = view.getBaseline();
                i10 = -1;
            }
        }
        boolean z17 = baseline != i10 ? true : z6;
        bVar.f9323i = (max == bVar.f9317c && i8 == bVar.f9318d) ? z6 : true;
        boolean z18 = fVar3.f16c0 ? true : z17;
        if (z18 && baseline != -1 && iVar.f9133c0 != baseline) {
            bVar.f9323i = true;
        }
        bVar.f9319e = max;
        bVar.f9320f = i8;
        bVar.f9322h = z18;
        bVar.f9321g = baseline;
    }
}
