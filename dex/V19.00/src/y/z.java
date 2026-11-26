package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.SparseArray;
import android.util.Xml;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z {
    public static final int AUTO_ANIMATE_TO_END = 4;
    public static final int AUTO_ANIMATE_TO_START = 3;
    public static final int AUTO_JUMP_TO_END = 2;
    public static final int AUTO_JUMP_TO_START = 1;
    public static final int AUTO_NONE = 0;
    public static final int INTERPOLATE_ANTICIPATE = 6;
    public static final int INTERPOLATE_BOUNCE = 4;
    public static final int INTERPOLATE_EASE_IN = 1;
    public static final int INTERPOLATE_EASE_IN_OUT = 0;
    public static final int INTERPOLATE_EASE_OUT = 2;
    public static final int INTERPOLATE_LINEAR = 3;
    public static final int INTERPOLATE_OVERSHOOT = 5;
    public static final int INTERPOLATE_REFERENCE_ID = -2;
    public static final int INTERPOLATE_SPLINE_STRING = -1;

    /* renamed from: a, reason: collision with root package name */
    public final int f9947a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f9948b;

    /* renamed from: c, reason: collision with root package name */
    public int f9949c;

    /* renamed from: d, reason: collision with root package name */
    public int f9950d;

    /* renamed from: e, reason: collision with root package name */
    public int f9951e;

    /* renamed from: f, reason: collision with root package name */
    public String f9952f;

    /* renamed from: g, reason: collision with root package name */
    public int f9953g;

    /* renamed from: h, reason: collision with root package name */
    public int f9954h;

    /* renamed from: i, reason: collision with root package name */
    public final float f9955i;

    /* renamed from: j, reason: collision with root package name */
    public final a0 f9956j;

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f9957k;

    /* renamed from: l, reason: collision with root package name */
    public c0 f9958l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f9959m;

    /* renamed from: n, reason: collision with root package name */
    public final int f9960n;

    /* renamed from: o, reason: collision with root package name */
    public final boolean f9961o;

    /* renamed from: p, reason: collision with root package name */
    public int f9962p;

    /* renamed from: q, reason: collision with root package name */
    public final int f9963q;

    /* renamed from: r, reason: collision with root package name */
    public final int f9964r;

    public z(a0 a0Var, int i7, int i8) {
        this.f9947a = -1;
        this.f9948b = false;
        this.f9949c = -1;
        this.f9950d = -1;
        this.f9951e = 0;
        this.f9952f = null;
        this.f9953g = -1;
        this.f9954h = 400;
        this.f9955i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9957k = new ArrayList();
        this.f9958l = null;
        this.f9959m = new ArrayList();
        this.f9960n = 0;
        this.f9961o = false;
        this.f9962p = -1;
        this.f9963q = 0;
        this.f9964r = 0;
        this.f9947a = -1;
        this.f9956j = a0Var;
        this.f9950d = i7;
        this.f9949c = i8;
        this.f9954h = a0Var.f9742j;
        this.f9963q = a0Var.f9743k;
    }

    public z(a0 a0Var, Context context, XmlResourceParser xmlResourceParser) {
        this.f9947a = -1;
        this.f9948b = false;
        this.f9949c = -1;
        this.f9950d = -1;
        this.f9951e = 0;
        this.f9952f = null;
        this.f9953g = -1;
        this.f9954h = 400;
        this.f9955i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9957k = new ArrayList();
        this.f9958l = null;
        this.f9959m = new ArrayList();
        this.f9960n = 0;
        this.f9961o = false;
        this.f9962p = -1;
        this.f9963q = 0;
        this.f9964r = 0;
        this.f9954h = a0Var.f9742j;
        this.f9963q = a0Var.f9743k;
        this.f9956j = a0Var;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.Transition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            int i8 = a0.t.Transition_constraintSetEnd;
            SparseArray sparseArray = a0Var.f9739g;
            if (index == i8) {
                this.f9949c = obtainStyledAttributes.getResourceId(index, -1);
                String resourceTypeName = context.getResources().getResourceTypeName(this.f9949c);
                if ("layout".equals(resourceTypeName)) {
                    a0.p pVar = new a0.p();
                    pVar.j(context, this.f9949c);
                    sparseArray.append(this.f9949c, pVar);
                } else if ("xml".equals(resourceTypeName)) {
                    this.f9949c = a0Var.j(context, this.f9949c);
                }
            } else if (index == a0.t.Transition_constraintSetStart) {
                this.f9950d = obtainStyledAttributes.getResourceId(index, this.f9950d);
                String resourceTypeName2 = context.getResources().getResourceTypeName(this.f9950d);
                if ("layout".equals(resourceTypeName2)) {
                    a0.p pVar2 = new a0.p();
                    pVar2.j(context, this.f9950d);
                    sparseArray.append(this.f9950d, pVar2);
                } else if ("xml".equals(resourceTypeName2)) {
                    this.f9950d = a0Var.j(context, this.f9950d);
                }
            } else if (index == a0.t.Transition_motionInterpolator) {
                int i9 = obtainStyledAttributes.peekValue(index).type;
                if (i9 == 1) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                    this.f9953g = resourceId;
                    if (resourceId != -1) {
                        this.f9951e = -2;
                    }
                } else if (i9 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.f9952f = string;
                    if (string != null) {
                        if (string.indexOf("/") > 0) {
                            this.f9953g = obtainStyledAttributes.getResourceId(index, -1);
                            this.f9951e = -2;
                        } else {
                            this.f9951e = -1;
                        }
                    }
                } else {
                    this.f9951e = obtainStyledAttributes.getInteger(index, this.f9951e);
                }
            } else if (index == a0.t.Transition_duration) {
                int i10 = obtainStyledAttributes.getInt(index, this.f9954h);
                this.f9954h = i10;
                if (i10 < 8) {
                    this.f9954h = 8;
                }
            } else if (index == a0.t.Transition_staggered) {
                this.f9955i = obtainStyledAttributes.getFloat(index, this.f9955i);
            } else if (index == a0.t.Transition_autoTransition) {
                this.f9960n = obtainStyledAttributes.getInteger(index, this.f9960n);
            } else if (index == a0.t.Transition_android_id) {
                this.f9947a = obtainStyledAttributes.getResourceId(index, this.f9947a);
            } else if (index == a0.t.Transition_transitionDisable) {
                this.f9961o = obtainStyledAttributes.getBoolean(index, this.f9961o);
            } else if (index == a0.t.Transition_pathMotionArc) {
                this.f9962p = obtainStyledAttributes.getInteger(index, -1);
            } else if (index == a0.t.Transition_layoutDuringTransition) {
                this.f9963q = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == a0.t.Transition_transitionFlags) {
                this.f9964r = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        if (this.f9950d == -1) {
            this.f9948b = true;
        }
        obtainStyledAttributes.recycle();
    }

    public z(a0 a0Var, z zVar) {
        this.f9947a = -1;
        this.f9948b = false;
        this.f9949c = -1;
        this.f9950d = -1;
        this.f9951e = 0;
        this.f9952f = null;
        this.f9953g = -1;
        this.f9954h = 400;
        this.f9955i = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9957k = new ArrayList();
        this.f9958l = null;
        this.f9959m = new ArrayList();
        this.f9960n = 0;
        this.f9961o = false;
        this.f9962p = -1;
        this.f9963q = 0;
        this.f9964r = 0;
        this.f9956j = a0Var;
        this.f9954h = a0Var.f9742j;
        if (zVar != null) {
            this.f9962p = zVar.f9962p;
            this.f9951e = zVar.f9951e;
            this.f9952f = zVar.f9952f;
            this.f9953g = zVar.f9953g;
            this.f9954h = zVar.f9954h;
            this.f9957k = zVar.f9957k;
            this.f9955i = zVar.f9955i;
            this.f9963q = zVar.f9963q;
        }
    }
}
