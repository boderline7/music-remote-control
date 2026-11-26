package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {

    /* renamed from: n, reason: collision with root package name */
    public static final SparseIntArray f132n;

    /* renamed from: a, reason: collision with root package name */
    public boolean f133a;

    /* renamed from: b, reason: collision with root package name */
    public int f134b;

    /* renamed from: c, reason: collision with root package name */
    public int f135c;

    /* renamed from: d, reason: collision with root package name */
    public String f136d;

    /* renamed from: e, reason: collision with root package name */
    public int f137e;

    /* renamed from: f, reason: collision with root package name */
    public int f138f;

    /* renamed from: g, reason: collision with root package name */
    public float f139g;

    /* renamed from: h, reason: collision with root package name */
    public float f140h;

    /* renamed from: i, reason: collision with root package name */
    public float f141i;

    /* renamed from: j, reason: collision with root package name */
    public int f142j;

    /* renamed from: k, reason: collision with root package name */
    public String f143k;

    /* renamed from: l, reason: collision with root package name */
    public int f144l;

    /* renamed from: m, reason: collision with root package name */
    public int f145m;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f132n = sparseIntArray;
        sparseIntArray.append(t.Motion_motionPathRotate, 1);
        sparseIntArray.append(t.Motion_pathMotionArc, 2);
        sparseIntArray.append(t.Motion_transitionEasing, 3);
        sparseIntArray.append(t.Motion_drawPath, 4);
        sparseIntArray.append(t.Motion_animateRelativeTo, 5);
        sparseIntArray.append(t.Motion_animateCircleAngleTo, 6);
        sparseIntArray.append(t.Motion_motionStagger, 7);
        sparseIntArray.append(t.Motion_quantizeMotionSteps, 8);
        sparseIntArray.append(t.Motion_quantizeMotionPhase, 9);
        sparseIntArray.append(t.Motion_quantizeMotionInterpolator, 10);
    }

    public final void a(m mVar) {
        this.f133a = mVar.f133a;
        this.f134b = mVar.f134b;
        this.f136d = mVar.f136d;
        this.f137e = mVar.f137e;
        this.f138f = mVar.f138f;
        this.f140h = mVar.f140h;
        this.f139g = mVar.f139g;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.Motion);
        this.f133a = true;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            switch (f132n.get(index)) {
                case 1:
                    this.f140h = obtainStyledAttributes.getFloat(index, this.f140h);
                    break;
                case 2:
                    this.f137e = obtainStyledAttributes.getInt(index, this.f137e);
                    break;
                case 3:
                    if (obtainStyledAttributes.peekValue(index).type == 3) {
                        this.f136d = obtainStyledAttributes.getString(index);
                        break;
                    } else {
                        this.f136d = t.f.f8958c[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    }
                case 4:
                    this.f138f = obtainStyledAttributes.getInt(index, 0);
                    break;
                case 5:
                    this.f134b = p.l(obtainStyledAttributes, index, this.f134b);
                    break;
                case 6:
                    this.f135c = obtainStyledAttributes.getInteger(index, this.f135c);
                    break;
                case 7:
                    this.f139g = obtainStyledAttributes.getFloat(index, this.f139g);
                    break;
                case 8:
                    this.f142j = obtainStyledAttributes.getInteger(index, this.f142j);
                    break;
                case 9:
                    this.f141i = obtainStyledAttributes.getFloat(index, this.f141i);
                    break;
                case 10:
                    int i8 = obtainStyledAttributes.peekValue(index).type;
                    if (i8 == 1) {
                        int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                        this.f145m = resourceId;
                        if (resourceId != -1) {
                            this.f144l = -2;
                            break;
                        } else {
                            break;
                        }
                    } else if (i8 == 3) {
                        String string = obtainStyledAttributes.getString(index);
                        this.f143k = string;
                        if (string.indexOf("/") > 0) {
                            this.f145m = obtainStyledAttributes.getResourceId(index, -1);
                            this.f144l = -2;
                            break;
                        } else {
                            this.f144l = -1;
                            break;
                        }
                    } else {
                        this.f144l = obtainStyledAttributes.getInteger(index, this.f145m);
                        break;
                    }
            }
        }
        obtainStyledAttributes.recycle();
    }
}
