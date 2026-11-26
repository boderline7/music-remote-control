package androidx.constraintlayout.helper.widget;

import a0.f;
import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.VirtualLayout;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CircularFlow extends VirtualLayout {

    /* renamed from: u, reason: collision with root package name */
    public static int f1516u;

    /* renamed from: v, reason: collision with root package name */
    public static float f1517v;

    /* renamed from: k, reason: collision with root package name */
    public ConstraintLayout f1518k;

    /* renamed from: l, reason: collision with root package name */
    public int f1519l;

    /* renamed from: m, reason: collision with root package name */
    public float[] f1520m;

    /* renamed from: n, reason: collision with root package name */
    public int[] f1521n;

    /* renamed from: o, reason: collision with root package name */
    public int f1522o;

    /* renamed from: p, reason: collision with root package name */
    public int f1523p;

    /* renamed from: q, reason: collision with root package name */
    public String f1524q;

    /* renamed from: r, reason: collision with root package name */
    public String f1525r;

    /* renamed from: s, reason: collision with root package name */
    public Float f1526s;

    /* renamed from: t, reason: collision with root package name */
    public Integer f1527t;

    public CircularFlow(Context context) {
        super(context);
    }

    public CircularFlow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularFlow(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
    }

    private void setAngles(String str) {
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1523p = 0;
        while (true) {
            int indexOf = str.indexOf(44, i7);
            if (indexOf == -1) {
                t(str.substring(i7).trim());
                return;
            } else {
                t(str.substring(i7, indexOf).trim());
                i7 = indexOf + 1;
            }
        }
    }

    private void setRadius(String str) {
        if (str == null) {
            return;
        }
        int i7 = 0;
        this.f1522o = 0;
        while (true) {
            int indexOf = str.indexOf(44, i7);
            if (indexOf == -1) {
                u(str.substring(i7).trim());
                return;
            } else {
                u(str.substring(i7, indexOf).trim());
                i7 = indexOf + 1;
            }
        }
    }

    public float[] getAngles() {
        return Arrays.copyOf(this.f1520m, this.f1523p);
    }

    public int[] getRadius() {
        return Arrays.copyOf(this.f1521n, this.f1522o);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void l(AttributeSet attributeSet) {
        super.l(attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, t.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.ConstraintLayout_Layout_circularflow_viewCenter) {
                    this.f1519l = obtainStyledAttributes.getResourceId(index, 0);
                } else if (index == t.ConstraintLayout_Layout_circularflow_angles) {
                    String string = obtainStyledAttributes.getString(index);
                    this.f1524q = string;
                    setAngles(string);
                } else if (index == t.ConstraintLayout_Layout_circularflow_radiusInDP) {
                    String string2 = obtainStyledAttributes.getString(index);
                    this.f1525r = string2;
                    setRadius(string2);
                } else if (index == t.ConstraintLayout_Layout_circularflow_defaultAngle) {
                    Float valueOf = Float.valueOf(obtainStyledAttributes.getFloat(index, f1517v));
                    this.f1526s = valueOf;
                    setDefaultAngle(valueOf.floatValue());
                } else if (index == t.ConstraintLayout_Layout_circularflow_defaultRadius) {
                    Integer valueOf2 = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(index, f1516u));
                    this.f1527t = valueOf2;
                    setDefaultRadius(valueOf2.intValue());
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.f1524q;
        if (str != null) {
            this.f1520m = new float[1];
            setAngles(str);
        }
        String str2 = this.f1525r;
        if (str2 != null) {
            this.f1521n = new int[1];
            setRadius(str2);
        }
        Float f7 = this.f1526s;
        if (f7 != null) {
            setDefaultAngle(f7.floatValue());
        }
        Integer num = this.f1527t;
        if (num != null) {
            setDefaultRadius(num.intValue());
        }
        this.f1518k = (ConstraintLayout) getParent();
        for (int i7 = 0; i7 < this.f1678b; i7++) {
            View c7 = this.f1518k.c(this.f1677a[i7]);
            if (c7 != null) {
                int i8 = f1516u;
                float f8 = f1517v;
                int[] iArr = this.f1521n;
                HashMap hashMap = this.f1684h;
                if (iArr == null || i7 >= iArr.length) {
                    Integer num2 = this.f1527t;
                    if (num2 == null || num2.intValue() == -1) {
                    } else {
                        this.f1522o++;
                        if (this.f1521n == null) {
                            this.f1521n = new int[1];
                        }
                        int[] radius = getRadius();
                        this.f1521n = radius;
                        radius[this.f1522o - 1] = i8;
                    }
                } else {
                    i8 = iArr[i7];
                }
                float[] fArr = this.f1520m;
                if (fArr == null || i7 >= fArr.length) {
                    Float f9 = this.f1526s;
                    if (f9 == null || f9.floatValue() == -1.0f) {
                    } else {
                        this.f1523p++;
                        if (this.f1520m == null) {
                            this.f1520m = new float[1];
                        }
                        float[] angles = getAngles();
                        this.f1520m = angles;
                        angles[this.f1523p - 1] = f8;
                    }
                } else {
                    f8 = fArr[i7];
                }
                f fVar = (f) c7.getLayoutParams();
                fVar.f45r = f8;
                fVar.f41p = this.f1519l;
                fVar.f43q = i8;
                c7.setLayoutParams(fVar);
            }
        }
        f();
    }

    public void setDefaultAngle(float f7) {
        f1517v = f7;
    }

    public void setDefaultRadius(int i7) {
        f1516u = i7;
    }

    public final void t(String str) {
        float[] fArr;
        if (str == null || str.length() == 0 || this.f1679c == null || (fArr = this.f1520m) == null) {
            return;
        }
        if (this.f1523p + 1 > fArr.length) {
            this.f1520m = Arrays.copyOf(fArr, fArr.length + 1);
        }
        this.f1520m[this.f1523p] = Integer.parseInt(str);
        this.f1523p++;
    }

    public final void u(String str) {
        Context context;
        int[] iArr;
        if (str == null || str.length() == 0 || (context = this.f1679c) == null || (iArr = this.f1521n) == null) {
            return;
        }
        if (this.f1522o + 1 > iArr.length) {
            this.f1521n = Arrays.copyOf(iArr, iArr.length + 1);
        }
        this.f1521n[this.f1522o] = (int) (Integer.parseInt(str) * context.getResources().getDisplayMetrics().density);
        this.f1522o++;
    }
}
