package androidx.constraintlayout.helper.widget;

import a0.t;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MotionEffect extends MotionHelper {
    public static final int AUTO = -1;
    public static final int EAST = 2;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final String TAG = "FadeMove";
    public static final int WEST = 3;

    /* renamed from: m, reason: collision with root package name */
    public float f1547m;

    /* renamed from: n, reason: collision with root package name */
    public int f1548n;

    /* renamed from: o, reason: collision with root package name */
    public int f1549o;

    /* renamed from: p, reason: collision with root package name */
    public int f1550p;

    /* renamed from: q, reason: collision with root package name */
    public int f1551q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f1552r;

    /* renamed from: s, reason: collision with root package name */
    public int f1553s;

    /* renamed from: t, reason: collision with root package name */
    public int f1554t;

    public MotionEffect(Context context) {
        super(context);
        this.f1547m = 0.1f;
        this.f1548n = 49;
        this.f1549o = 50;
        this.f1550p = 0;
        this.f1551q = 0;
        this.f1552r = true;
        this.f1553s = -1;
        this.f1554t = -1;
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1547m = 0.1f;
        this.f1548n = 49;
        this.f1549o = 50;
        this.f1550p = 0;
        this.f1551q = 0;
        this.f1552r = true;
        this.f1553s = -1;
        this.f1554t = -1;
        t(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1547m = 0.1f;
        this.f1548n = 49;
        this.f1549o = 50;
        this.f1550p = 0;
        this.f1551q = 0;
        this.f1552r = true;
        this.f1553s = -1;
        this.f1554t = -1;
        t(context, attributeSet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x01a4, code lost:
    
        if (r1 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x016a, code lost:
    
        if (r14 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0180, code lost:
    
        if (r14 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01d7  */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void s(androidx.constraintlayout.motion.widget.MotionLayout r20, java.util.HashMap r21) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.MotionEffect.s(androidx.constraintlayout.motion.widget.MotionLayout, java.util.HashMap):void");
    }

    public final void t(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.MotionEffect);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = obtainStyledAttributes.getIndex(i7);
                if (index == t.MotionEffect_motionEffect_start) {
                    int i8 = obtainStyledAttributes.getInt(index, this.f1548n);
                    this.f1548n = i8;
                    this.f1548n = Math.max(Math.min(i8, 99), 0);
                } else if (index == t.MotionEffect_motionEffect_end) {
                    int i9 = obtainStyledAttributes.getInt(index, this.f1549o);
                    this.f1549o = i9;
                    this.f1549o = Math.max(Math.min(i9, 99), 0);
                } else if (index == t.MotionEffect_motionEffect_translationX) {
                    this.f1550p = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1550p);
                } else if (index == t.MotionEffect_motionEffect_translationY) {
                    this.f1551q = obtainStyledAttributes.getDimensionPixelOffset(index, this.f1551q);
                } else if (index == t.MotionEffect_motionEffect_alpha) {
                    this.f1547m = obtainStyledAttributes.getFloat(index, this.f1547m);
                } else if (index == t.MotionEffect_motionEffect_move) {
                    this.f1554t = obtainStyledAttributes.getInt(index, this.f1554t);
                } else if (index == t.MotionEffect_motionEffect_strict) {
                    this.f1552r = obtainStyledAttributes.getBoolean(index, this.f1552r);
                } else if (index == t.MotionEffect_motionEffect_viewTransition) {
                    this.f1553s = obtainStyledAttributes.getResourceId(index, this.f1553s);
                }
            }
            int i10 = this.f1548n;
            int i11 = this.f1549o;
            if (i10 == i11) {
                if (i10 > 0) {
                    this.f1548n = i10 - 1;
                } else {
                    this.f1549o = i11 + 1;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }
}
