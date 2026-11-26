package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o {

    /* renamed from: o, reason: collision with root package name */
    public static final SparseIntArray f151o;

    /* renamed from: a, reason: collision with root package name */
    public boolean f152a;

    /* renamed from: b, reason: collision with root package name */
    public float f153b;

    /* renamed from: c, reason: collision with root package name */
    public float f154c;

    /* renamed from: d, reason: collision with root package name */
    public float f155d;

    /* renamed from: e, reason: collision with root package name */
    public float f156e;

    /* renamed from: f, reason: collision with root package name */
    public float f157f;

    /* renamed from: g, reason: collision with root package name */
    public float f158g;

    /* renamed from: h, reason: collision with root package name */
    public float f159h;

    /* renamed from: i, reason: collision with root package name */
    public int f160i;

    /* renamed from: j, reason: collision with root package name */
    public float f161j;

    /* renamed from: k, reason: collision with root package name */
    public float f162k;

    /* renamed from: l, reason: collision with root package name */
    public float f163l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f164m;

    /* renamed from: n, reason: collision with root package name */
    public float f165n;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f151o = sparseIntArray;
        sparseIntArray.append(t.Transform_android_rotation, 1);
        sparseIntArray.append(t.Transform_android_rotationX, 2);
        sparseIntArray.append(t.Transform_android_rotationY, 3);
        sparseIntArray.append(t.Transform_android_scaleX, 4);
        sparseIntArray.append(t.Transform_android_scaleY, 5);
        sparseIntArray.append(t.Transform_android_transformPivotX, 6);
        sparseIntArray.append(t.Transform_android_transformPivotY, 7);
        sparseIntArray.append(t.Transform_android_translationX, 8);
        sparseIntArray.append(t.Transform_android_translationY, 9);
        sparseIntArray.append(t.Transform_android_translationZ, 10);
        sparseIntArray.append(t.Transform_android_elevation, 11);
        sparseIntArray.append(t.Transform_transformPivotTarget, 12);
    }

    public final void a(o oVar) {
        this.f152a = oVar.f152a;
        this.f153b = oVar.f153b;
        this.f154c = oVar.f154c;
        this.f155d = oVar.f155d;
        this.f156e = oVar.f156e;
        this.f157f = oVar.f157f;
        this.f158g = oVar.f158g;
        this.f159h = oVar.f159h;
        this.f160i = oVar.f160i;
        this.f161j = oVar.f161j;
        this.f162k = oVar.f162k;
        this.f163l = oVar.f163l;
        this.f164m = oVar.f164m;
        this.f165n = oVar.f165n;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.Transform);
        this.f152a = true;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            switch (f151o.get(index)) {
                case 1:
                    this.f153b = obtainStyledAttributes.getFloat(index, this.f153b);
                    break;
                case 2:
                    this.f154c = obtainStyledAttributes.getFloat(index, this.f154c);
                    break;
                case 3:
                    this.f155d = obtainStyledAttributes.getFloat(index, this.f155d);
                    break;
                case 4:
                    this.f156e = obtainStyledAttributes.getFloat(index, this.f156e);
                    break;
                case 5:
                    this.f157f = obtainStyledAttributes.getFloat(index, this.f157f);
                    break;
                case 6:
                    this.f158g = obtainStyledAttributes.getDimension(index, this.f158g);
                    break;
                case 7:
                    this.f159h = obtainStyledAttributes.getDimension(index, this.f159h);
                    break;
                case 8:
                    this.f161j = obtainStyledAttributes.getDimension(index, this.f161j);
                    break;
                case 9:
                    this.f162k = obtainStyledAttributes.getDimension(index, this.f162k);
                    break;
                case 10:
                    this.f163l = obtainStyledAttributes.getDimension(index, this.f163l);
                    break;
                case 11:
                    this.f164m = true;
                    this.f165n = obtainStyledAttributes.getDimension(index, this.f165n);
                    break;
                case 12:
                    this.f160i = p.l(obtainStyledAttributes, index, this.f160i);
                    break;
            }
        }
        obtainStyledAttributes.recycle();
    }
}
