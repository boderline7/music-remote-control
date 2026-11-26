package androidx.constraintlayout.helper.widget;

import a0.k;
import a0.q;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.widget.VirtualLayout;
import u.i;
import u.l;
import u.p;
import u.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Flow extends VirtualLayout {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL = 1;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;

    /* renamed from: k, reason: collision with root package name */
    public l f1528k;

    public Flow(Context context) {
        super(context);
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        this.f1528k = new l();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_android_orientation) {
                    this.f1528k.f9197a1 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_android_padding) {
                    l lVar = this.f1528k;
                    int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    lVar.f9213x0 = dimensionPixelSize;
                    lVar.f9214y0 = dimensionPixelSize;
                    lVar.f9215z0 = dimensionPixelSize;
                    lVar.A0 = dimensionPixelSize;
                } else if (index == t.ConstraintLayout_Layout_android_paddingStart) {
                    l lVar2 = this.f1528k;
                    int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    lVar2.f9215z0 = dimensionPixelSize2;
                    lVar2.B0 = dimensionPixelSize2;
                    lVar2.C0 = dimensionPixelSize2;
                } else if (index == t.ConstraintLayout_Layout_android_paddingEnd) {
                    this.f1528k.A0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_android_paddingLeft) {
                    this.f1528k.B0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_android_paddingTop) {
                    this.f1528k.f9213x0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_android_paddingRight) {
                    this.f1528k.C0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_android_paddingBottom) {
                    this.f1528k.f9214y0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_wrapMode) {
                    this.f1528k.Y0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.f1528k.I0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.f1528k.J0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.f1528k.K0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.f1528k.M0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.f1528k.L0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.f1528k.N0 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.f1528k.O0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.f1528k.Q0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.f1528k.S0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.f1528k.R0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.f1528k.T0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_verticalBias) {
                    this.f1528k.P0 = obtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == t.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.f1528k.W0 = obtainStyledAttributes.getInt(index, 2);
                } else if (index == t.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.f1528k.X0 = obtainStyledAttributes.getInt(index, 2);
                } else if (index == t.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.f1528k.U0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_verticalGap) {
                    this.f1528k.V0 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == t.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.f1528k.Z0 = obtainStyledAttributes.getInt(index, -1);
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f1680d = this.f1528k;
        r();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void m(k kVar, p pVar, q qVar, SparseArray sparseArray) {
        super.m(kVar, pVar, qVar, sparseArray);
        if (pVar instanceof l) {
            l lVar = (l) pVar;
            int i7 = qVar.V;
            if (i7 != -1) {
                lVar.f9197a1 = i7;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void n(i iVar, boolean z6) {
        l lVar = this.f1528k;
        int i7 = lVar.f9215z0;
        if (i7 > 0 || lVar.A0 > 0) {
            if (z6) {
                lVar.B0 = lVar.A0;
                lVar.C0 = i7;
            } else {
                lVar.B0 = i7;
                lVar.C0 = lVar.A0;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onMeasure(int i7, int i8) {
        s(this.f1528k, i7, i8);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public final void s(s sVar, int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (sVar == null) {
            setMeasuredDimension(0, 0);
        } else {
            sVar.V(mode, size, mode2, size2);
            setMeasuredDimension(sVar.E0, sVar.F0);
        }
    }

    public void setFirstHorizontalBias(float f7) {
        this.f1528k.Q0 = f7;
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i7) {
        this.f1528k.K0 = i7;
        requestLayout();
    }

    public void setFirstVerticalBias(float f7) {
        this.f1528k.R0 = f7;
        requestLayout();
    }

    public void setFirstVerticalStyle(int i7) {
        this.f1528k.L0 = i7;
        requestLayout();
    }

    public void setHorizontalAlign(int i7) {
        this.f1528k.W0 = i7;
        requestLayout();
    }

    public void setHorizontalBias(float f7) {
        this.f1528k.O0 = f7;
        requestLayout();
    }

    public void setHorizontalGap(int i7) {
        this.f1528k.U0 = i7;
        requestLayout();
    }

    public void setHorizontalStyle(int i7) {
        this.f1528k.I0 = i7;
        requestLayout();
    }

    public void setLastHorizontalBias(float f7) {
        this.f1528k.S0 = f7;
        requestLayout();
    }

    public void setLastHorizontalStyle(int i7) {
        this.f1528k.M0 = i7;
        requestLayout();
    }

    public void setLastVerticalBias(float f7) {
        this.f1528k.T0 = f7;
        requestLayout();
    }

    public void setLastVerticalStyle(int i7) {
        this.f1528k.N0 = i7;
        requestLayout();
    }

    public void setMaxElementsWrap(int i7) {
        this.f1528k.Z0 = i7;
        requestLayout();
    }

    public void setOrientation(int i7) {
        this.f1528k.f9197a1 = i7;
        requestLayout();
    }

    public void setPadding(int i7) {
        l lVar = this.f1528k;
        lVar.f9213x0 = i7;
        lVar.f9214y0 = i7;
        lVar.f9215z0 = i7;
        lVar.A0 = i7;
        requestLayout();
    }

    public void setPaddingBottom(int i7) {
        this.f1528k.f9214y0 = i7;
        requestLayout();
    }

    public void setPaddingLeft(int i7) {
        this.f1528k.B0 = i7;
        requestLayout();
    }

    public void setPaddingRight(int i7) {
        this.f1528k.C0 = i7;
        requestLayout();
    }

    public void setPaddingTop(int i7) {
        this.f1528k.f9213x0 = i7;
        requestLayout();
    }

    public void setVerticalAlign(int i7) {
        this.f1528k.X0 = i7;
        requestLayout();
    }

    public void setVerticalBias(float f7) {
        this.f1528k.P0 = f7;
        requestLayout();
    }

    public void setVerticalGap(int i7) {
        this.f1528k.V0 = i7;
        requestLayout();
    }

    public void setVerticalStyle(int i7) {
        this.f1528k.J0 = i7;
        requestLayout();
    }

    public void setWrapMode(int i7) {
        this.f1528k.Y0 = i7;
        requestLayout();
    }
}
