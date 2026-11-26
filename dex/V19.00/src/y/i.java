package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends c {
    public static final String DRAWPATH = "drawPath";
    public static final String PERCENT_HEIGHT = "percentHeight";
    public static final String PERCENT_WIDTH = "percentWidth";
    public static final String PERCENT_X = "percentX";
    public static final String PERCENT_Y = "percentY";
    public static final String SIZE_PERCENT = "sizePercent";
    public static final String TRANSITION_EASING = "transitionEasing";
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;

    /* renamed from: e, reason: collision with root package name */
    public int f9836e = -1;

    /* renamed from: f, reason: collision with root package name */
    public String f9837f = null;

    /* renamed from: g, reason: collision with root package name */
    public int f9838g = -1;

    /* renamed from: h, reason: collision with root package name */
    public int f9839h = 0;

    /* renamed from: i, reason: collision with root package name */
    public float f9840i = Float.NaN;

    /* renamed from: j, reason: collision with root package name */
    public float f9841j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    public float f9842k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    public float f9843l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    public float f9844m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    public float f9845n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    public int f9846o = 0;

    @Override // y.c
    public final void a(HashMap hashMap) {
    }

    @Override // y.c
    /* renamed from: b */
    public final c clone() {
        i iVar = new i();
        iVar.f9753a = this.f9753a;
        iVar.f9754b = this.f9754b;
        iVar.f9755c = this.f9755c;
        iVar.f9756d = this.f9756d;
        iVar.f9837f = this.f9837f;
        iVar.f9838g = this.f9838g;
        iVar.f9839h = this.f9839h;
        iVar.f9840i = this.f9840i;
        iVar.f9841j = Float.NaN;
        iVar.f9842k = this.f9842k;
        iVar.f9843l = this.f9843l;
        iVar.f9844m = this.f9844m;
        iVar.f9845n = this.f9845n;
        return iVar;
    }

    @Override // y.c
    public final /* bridge */ /* synthetic */ void c(HashSet hashSet) {
    }

    @Override // y.c
    public final void d(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a0.t.KeyPosition);
        SparseIntArray sparseIntArray = h.f9835a;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            SparseIntArray sparseIntArray2 = h.f9835a;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    if (MotionLayout.H0) {
                        int resourceId = obtainStyledAttributes.getResourceId(index, this.f9754b);
                        this.f9754b = resourceId;
                        if (resourceId == -1) {
                            this.f9755c = obtainStyledAttributes.getString(index);
                            break;
                        } else {
                            break;
                        }
                    } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.f9755c = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        this.f9754b = obtainStyledAttributes.getResourceId(index, this.f9754b);
                        break;
                    }
                case 2:
                    this.f9753a = obtainStyledAttributes.getInt(index, this.f9753a);
                    break;
                case 3:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.f9837f = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        this.f9837f = t.f.f8958c[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    }
                case 4:
                    this.f9836e = obtainStyledAttributes.getInteger(index, this.f9836e);
                    break;
                case 5:
                    this.f9839h = obtainStyledAttributes.getInt(index, this.f9839h);
                    break;
                case 6:
                    this.f9842k = obtainStyledAttributes.getFloat(index, this.f9842k);
                    break;
                case 7:
                    this.f9843l = obtainStyledAttributes.getFloat(index, this.f9843l);
                    break;
                case 8:
                    float f7 = obtainStyledAttributes.getFloat(index, this.f9841j);
                    this.f9840i = f7;
                    this.f9841j = f7;
                    break;
                case 9:
                    this.f9846o = obtainStyledAttributes.getInt(index, this.f9846o);
                    break;
                case 10:
                    this.f9838g = obtainStyledAttributes.getInt(index, this.f9838g);
                    break;
                case 11:
                    this.f9840i = obtainStyledAttributes.getFloat(index, this.f9840i);
                    break;
                case 12:
                    this.f9841j = obtainStyledAttributes.getFloat(index, this.f9841j);
                    break;
                default:
                    Integer.toHexString(index);
                    sparseIntArray2.get(index);
                    break;
            }
        }
    }

    public final void g(String str, Object obj) {
        switch (str) {
            case "transitionEasing":
                this.f9837f = obj.toString();
                break;
            case "percentWidth":
                this.f9840i = c.f((Number) obj);
                break;
            case "percentHeight":
                this.f9841j = c.f((Number) obj);
                break;
            case "drawPath":
                Number number = (Number) obj;
                this.f9839h = number instanceof Integer ? ((Integer) number).intValue() : Integer.parseInt(number.toString());
                break;
            case "sizePercent":
                float f7 = c.f((Number) obj);
                this.f9840i = f7;
                this.f9841j = f7;
                break;
            case "percentX":
                this.f9842k = c.f((Number) obj);
                break;
            case "percentY":
                this.f9843l = c.f((Number) obj);
                break;
        }
    }
}
