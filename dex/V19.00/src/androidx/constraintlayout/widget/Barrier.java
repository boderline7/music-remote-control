package androidx.constraintlayout.widget;

import a0.k;
import a0.l;
import a0.q;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import u.a;
import u.i;
import u.j;
import u.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;

    /* renamed from: i, reason: collision with root package name */
    public int f1674i;

    /* renamed from: j, reason: collision with root package name */
    public int f1675j;

    /* renamed from: k, reason: collision with root package name */
    public a f1676k;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        super.setVisibility(8);
    }

    public boolean getAllowsGoneWidget() {
        return this.f1676k.f9095y0;
    }

    public int getMargin() {
        return this.f1676k.f9096z0;
    }

    public int getType() {
        return this.f1674i;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        this.f1676k = new a();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_barrierDirection) {
                    setType(obtainStyledAttributes.getInt(index, 0));
                } else if (index == t.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.f1676k.f9095y0 = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == t.ConstraintLayout_Layout_barrierMargin) {
                    this.f1676k.f9096z0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f1680d = this.f1676k;
        r();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void m(k kVar, p pVar, q qVar, SparseArray sparseArray) {
        super.m(kVar, pVar, qVar, sparseArray);
        if (pVar instanceof a) {
            a aVar = (a) pVar;
            boolean z6 = ((j) pVar.V).A0;
            l lVar = kVar.f85e;
            s(aVar, lVar.f103g0, z6);
            aVar.f9095y0 = lVar.f119o0;
            aVar.f9096z0 = lVar.f105h0;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void n(i iVar, boolean z6) {
        s(iVar, this.f1674i, z6);
    }

    public final void s(i iVar, int i7, boolean z6) {
        this.f1675j = i7;
        if (z6) {
            int i8 = this.f1674i;
            if (i8 == 5) {
                this.f1675j = 1;
            } else if (i8 == 6) {
                this.f1675j = 0;
            }
        } else {
            int i9 = this.f1674i;
            if (i9 == 5) {
                this.f1675j = 0;
            } else if (i9 == 6) {
                this.f1675j = 1;
            }
        }
        if (iVar instanceof a) {
            ((a) iVar).f9094x0 = this.f1675j;
        }
    }

    public void setAllowsGoneWidget(boolean z6) {
        this.f1676k.f9095y0 = z6;
    }

    public void setDpMargin(int i7) {
        this.f1676k.f9096z0 = (int) ((i7 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i7) {
        this.f1676k.f9096z0 = i7;
    }

    public void setType(int i7) {
        this.f1674i = i7;
    }
}
