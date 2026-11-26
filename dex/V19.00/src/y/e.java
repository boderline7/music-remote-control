package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends c {
    public static final int KEY_TYPE = 1;

    /* renamed from: e, reason: collision with root package name */
    public int f9797e = -1;

    /* renamed from: f, reason: collision with root package name */
    public float f9798f = Float.NaN;

    /* renamed from: g, reason: collision with root package name */
    public float f9799g = Float.NaN;

    /* renamed from: h, reason: collision with root package name */
    public float f9800h = Float.NaN;

    /* renamed from: i, reason: collision with root package name */
    public float f9801i = Float.NaN;

    /* renamed from: j, reason: collision with root package name */
    public float f9802j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    public float f9803k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    public float f9804l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    public float f9805m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    public float f9806n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    public float f9807o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    public float f9808p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    public float f9809q = Float.NaN;

    /* renamed from: r, reason: collision with root package name */
    public float f9810r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    public float f9811s = Float.NaN;

    public e() {
        this.f9756d = new HashMap();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x009c, code lost:
    
        if (r1.equals(y.c.SCALE_Y) == false) goto L15;
     */
    @Override // y.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.util.HashMap r7) {
        /*
            Method dump skipped, instructions count: 574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y.e.a(java.util.HashMap):void");
    }

    @Override // y.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final c clone() {
        e eVar = new e();
        eVar.f9753a = this.f9753a;
        eVar.f9754b = this.f9754b;
        eVar.f9755c = this.f9755c;
        eVar.f9756d = this.f9756d;
        eVar.f9797e = this.f9797e;
        eVar.f9798f = this.f9798f;
        eVar.f9799g = this.f9799g;
        eVar.f9800h = this.f9800h;
        eVar.f9801i = this.f9801i;
        eVar.f9802j = this.f9802j;
        eVar.f9803k = this.f9803k;
        eVar.f9804l = this.f9804l;
        eVar.f9805m = this.f9805m;
        eVar.f9806n = this.f9806n;
        eVar.f9807o = this.f9807o;
        eVar.f9808p = this.f9808p;
        eVar.f9809q = this.f9809q;
        eVar.f9810r = this.f9810r;
        eVar.f9811s = this.f9811s;
        return eVar;
    }

    @Override // y.c
    public final void c(HashSet hashSet) {
        if (!Float.isNaN(this.f9798f)) {
            hashSet.add(c.ALPHA);
        }
        if (!Float.isNaN(this.f9799g)) {
            hashSet.add(c.ELEVATION);
        }
        if (!Float.isNaN(this.f9800h)) {
            hashSet.add(c.ROTATION);
        }
        if (!Float.isNaN(this.f9801i)) {
            hashSet.add(c.ROTATION_X);
        }
        if (!Float.isNaN(this.f9802j)) {
            hashSet.add(c.ROTATION_Y);
        }
        if (!Float.isNaN(this.f9803k)) {
            hashSet.add(c.PIVOT_X);
        }
        if (!Float.isNaN(this.f9804l)) {
            hashSet.add(c.PIVOT_Y);
        }
        if (!Float.isNaN(this.f9808p)) {
            hashSet.add(c.TRANSLATION_X);
        }
        if (!Float.isNaN(this.f9809q)) {
            hashSet.add(c.TRANSLATION_Y);
        }
        if (!Float.isNaN(this.f9810r)) {
            hashSet.add(c.TRANSLATION_Z);
        }
        if (!Float.isNaN(this.f9805m)) {
            hashSet.add(c.TRANSITION_PATH_ROTATE);
        }
        if (!Float.isNaN(this.f9806n)) {
            hashSet.add(c.SCALE_X);
        }
        if (!Float.isNaN(this.f9807o)) {
            hashSet.add(c.SCALE_Y);
        }
        if (!Float.isNaN(this.f9811s)) {
            hashSet.add(c.PROGRESS);
        }
        if (this.f9756d.size() > 0) {
            Iterator it = this.f9756d.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + ((String) it.next()));
            }
        }
    }

    @Override // y.c
    public final void d(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a0.t.KeyAttribute);
        SparseIntArray sparseIntArray = d.f9783a;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            SparseIntArray sparseIntArray2 = d.f9783a;
            switch (sparseIntArray2.get(index)) {
                case 1:
                    this.f9798f = obtainStyledAttributes.getFloat(index, this.f9798f);
                    break;
                case 2:
                    this.f9799g = obtainStyledAttributes.getDimension(index, this.f9799g);
                    break;
                case 3:
                case 11:
                default:
                    Integer.toHexString(index);
                    sparseIntArray2.get(index);
                    break;
                case 4:
                    this.f9800h = obtainStyledAttributes.getFloat(index, this.f9800h);
                    break;
                case 5:
                    this.f9801i = obtainStyledAttributes.getFloat(index, this.f9801i);
                    break;
                case 6:
                    this.f9802j = obtainStyledAttributes.getFloat(index, this.f9802j);
                    break;
                case 7:
                    this.f9806n = obtainStyledAttributes.getFloat(index, this.f9806n);
                    break;
                case 8:
                    this.f9805m = obtainStyledAttributes.getFloat(index, this.f9805m);
                    break;
                case 9:
                    obtainStyledAttributes.getString(index);
                    break;
                case 10:
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
                case 12:
                    this.f9753a = obtainStyledAttributes.getInt(index, this.f9753a);
                    break;
                case 13:
                    this.f9797e = obtainStyledAttributes.getInteger(index, this.f9797e);
                    break;
                case 14:
                    this.f9807o = obtainStyledAttributes.getFloat(index, this.f9807o);
                    break;
                case 15:
                    this.f9808p = obtainStyledAttributes.getDimension(index, this.f9808p);
                    break;
                case 16:
                    this.f9809q = obtainStyledAttributes.getDimension(index, this.f9809q);
                    break;
                case 17:
                    this.f9810r = obtainStyledAttributes.getDimension(index, this.f9810r);
                    break;
                case 18:
                    this.f9811s = obtainStyledAttributes.getFloat(index, this.f9811s);
                    break;
                case 19:
                    this.f9803k = obtainStyledAttributes.getDimension(index, this.f9803k);
                    break;
                case 20:
                    this.f9804l = obtainStyledAttributes.getDimension(index, this.f9804l);
                    break;
            }
        }
    }

    @Override // y.c
    public final void e(HashMap hashMap) {
        if (this.f9797e == -1) {
            return;
        }
        if (!Float.isNaN(this.f9798f)) {
            hashMap.put(c.ALPHA, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9799g)) {
            hashMap.put(c.ELEVATION, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9800h)) {
            hashMap.put(c.ROTATION, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9801i)) {
            hashMap.put(c.ROTATION_X, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9802j)) {
            hashMap.put(c.ROTATION_Y, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9803k)) {
            hashMap.put(c.PIVOT_X, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9804l)) {
            hashMap.put(c.PIVOT_Y, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9808p)) {
            hashMap.put(c.TRANSLATION_X, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9809q)) {
            hashMap.put(c.TRANSLATION_Y, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9810r)) {
            hashMap.put(c.TRANSLATION_Z, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9805m)) {
            hashMap.put(c.TRANSITION_PATH_ROTATE, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9806n)) {
            hashMap.put(c.SCALE_X, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9807o)) {
            hashMap.put(c.SCALE_Y, Integer.valueOf(this.f9797e));
        }
        if (!Float.isNaN(this.f9811s)) {
            hashMap.put(c.PROGRESS, Integer.valueOf(this.f9797e));
        }
        if (this.f9756d.size() > 0) {
            Iterator it = this.f9756d.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put(a.a.B("CUSTOM,", (String) it.next()), Integer.valueOf(this.f9797e));
            }
        }
    }

    public final void g(String str, Object obj) {
        switch (str) {
            case "motionProgress":
                this.f9811s = c.f((Number) obj);
                break;
            case "transitionEasing":
                obj.toString();
                break;
            case "rotationX":
                this.f9801i = c.f((Number) obj);
                break;
            case "rotationY":
                this.f9802j = c.f((Number) obj);
                break;
            case "translationX":
                this.f9808p = c.f((Number) obj);
                break;
            case "translationY":
                this.f9809q = c.f((Number) obj);
                break;
            case "translationZ":
                this.f9810r = c.f((Number) obj);
                break;
            case "scaleX":
                this.f9806n = c.f((Number) obj);
                break;
            case "scaleY":
                this.f9807o = c.f((Number) obj);
                break;
            case "transformPivotX":
                this.f9803k = c.f((Number) obj);
                break;
            case "transformPivotY":
                this.f9804l = c.f((Number) obj);
                break;
            case "rotation":
                this.f9800h = c.f((Number) obj);
                break;
            case "elevation":
                this.f9799g = c.f((Number) obj);
                break;
            case "transitionPathRotate":
                this.f9805m = c.f((Number) obj);
                break;
            case "alpha":
                this.f9798f = c.f((Number) obj);
                break;
            case "curveFit":
                Number number = (Number) obj;
                this.f9797e = number instanceof Integer ? ((Integer) number).intValue() : Integer.parseInt(number.toString());
                break;
            case "visibility":
                if (!(obj instanceof Boolean)) {
                    Boolean.parseBoolean(obj.toString());
                    break;
                } else {
                    ((Boolean) obj).booleanValue();
                    break;
                }
        }
    }
}
