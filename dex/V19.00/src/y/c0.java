package y;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c0 {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    public static final float[][] E = {new float[]{0.5f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.5f}, new float[]{1.0f, 0.5f}};
    public static final float[][] F = {new float[]{CropImageView.DEFAULT_ASPECT_RATIO, -1.0f}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f}, new float[]{-1.0f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{-1.0f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO}};
    public final float A;
    public final float B;
    public final int C;
    public final int D;

    /* renamed from: a, reason: collision with root package name */
    public final int f9757a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9758b;

    /* renamed from: c, reason: collision with root package name */
    public int f9759c;

    /* renamed from: d, reason: collision with root package name */
    public final int f9760d;

    /* renamed from: e, reason: collision with root package name */
    public final int f9761e;

    /* renamed from: f, reason: collision with root package name */
    public final int f9762f;

    /* renamed from: g, reason: collision with root package name */
    public float f9763g;

    /* renamed from: h, reason: collision with root package name */
    public float f9764h;

    /* renamed from: i, reason: collision with root package name */
    public final int f9765i;

    /* renamed from: j, reason: collision with root package name */
    public final boolean f9766j;

    /* renamed from: k, reason: collision with root package name */
    public float f9767k;

    /* renamed from: l, reason: collision with root package name */
    public float f9768l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f9769m = false;

    /* renamed from: n, reason: collision with root package name */
    public final float[] f9770n = new float[2];

    /* renamed from: o, reason: collision with root package name */
    public final int[] f9771o = new int[2];

    /* renamed from: p, reason: collision with root package name */
    public float f9772p;

    /* renamed from: q, reason: collision with root package name */
    public float f9773q;

    /* renamed from: r, reason: collision with root package name */
    public final MotionLayout f9774r;

    /* renamed from: s, reason: collision with root package name */
    public final float f9775s;

    /* renamed from: t, reason: collision with root package name */
    public final float f9776t;

    /* renamed from: u, reason: collision with root package name */
    public final boolean f9777u;

    /* renamed from: v, reason: collision with root package name */
    public final float f9778v;

    /* renamed from: w, reason: collision with root package name */
    public final int f9779w;

    /* renamed from: x, reason: collision with root package name */
    public final float f9780x;

    /* renamed from: y, reason: collision with root package name */
    public final float f9781y;

    /* renamed from: z, reason: collision with root package name */
    public final float f9782z;

    public c0(Context context, MotionLayout motionLayout, XmlResourceParser xmlResourceParser) {
        this.f9757a = 0;
        this.f9758b = 0;
        this.f9759c = 0;
        this.f9760d = -1;
        this.f9761e = -1;
        this.f9762f = -1;
        this.f9763g = 0.5f;
        this.f9764h = 0.5f;
        this.f9765i = -1;
        this.f9766j = false;
        this.f9767k = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9768l = 1.0f;
        this.f9775s = 4.0f;
        this.f9776t = 1.2f;
        this.f9777u = true;
        this.f9778v = 1.0f;
        this.f9779w = 0;
        this.f9780x = 10.0f;
        this.f9781y = 10.0f;
        this.f9782z = 1.0f;
        this.A = Float.NaN;
        this.B = Float.NaN;
        this.C = 0;
        this.D = 0;
        this.f9774r = motionLayout;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), a0.t.OnSwipe);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == a0.t.OnSwipe_touchAnchorId) {
                this.f9760d = obtainStyledAttributes.getResourceId(index, this.f9760d);
            } else if (index == a0.t.OnSwipe_touchAnchorSide) {
                int i8 = obtainStyledAttributes.getInt(index, this.f9757a);
                this.f9757a = i8;
                float[] fArr = E[i8];
                this.f9764h = fArr[0];
                this.f9763g = fArr[1];
            } else if (index == a0.t.OnSwipe_dragDirection) {
                int i9 = obtainStyledAttributes.getInt(index, this.f9758b);
                this.f9758b = i9;
                if (i9 < 6) {
                    float[] fArr2 = F[i9];
                    this.f9767k = fArr2[0];
                    this.f9768l = fArr2[1];
                } else {
                    this.f9768l = Float.NaN;
                    this.f9767k = Float.NaN;
                    this.f9766j = true;
                }
            } else if (index == a0.t.OnSwipe_maxVelocity) {
                this.f9775s = obtainStyledAttributes.getFloat(index, this.f9775s);
            } else if (index == a0.t.OnSwipe_maxAcceleration) {
                this.f9776t = obtainStyledAttributes.getFloat(index, this.f9776t);
            } else if (index == a0.t.OnSwipe_moveWhenScrollAtTop) {
                this.f9777u = obtainStyledAttributes.getBoolean(index, this.f9777u);
            } else if (index == a0.t.OnSwipe_dragScale) {
                this.f9778v = obtainStyledAttributes.getFloat(index, this.f9778v);
            } else if (index == a0.t.OnSwipe_dragThreshold) {
                this.f9780x = obtainStyledAttributes.getFloat(index, this.f9780x);
            } else if (index == a0.t.OnSwipe_touchRegionId) {
                this.f9761e = obtainStyledAttributes.getResourceId(index, this.f9761e);
            } else if (index == a0.t.OnSwipe_onTouchUp) {
                this.f9759c = obtainStyledAttributes.getInt(index, this.f9759c);
            } else if (index == a0.t.OnSwipe_nestedScrollFlags) {
                this.f9779w = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == a0.t.OnSwipe_limitBoundsTo) {
                this.f9762f = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == a0.t.OnSwipe_rotationCenterId) {
                this.f9765i = obtainStyledAttributes.getResourceId(index, this.f9765i);
            } else if (index == a0.t.OnSwipe_springDamping) {
                this.f9781y = obtainStyledAttributes.getFloat(index, this.f9781y);
            } else if (index == a0.t.OnSwipe_springMass) {
                this.f9782z = obtainStyledAttributes.getFloat(index, this.f9782z);
            } else if (index == a0.t.OnSwipe_springStiffness) {
                this.A = obtainStyledAttributes.getFloat(index, this.A);
            } else if (index == a0.t.OnSwipe_springStopThreshold) {
                this.B = obtainStyledAttributes.getFloat(index, this.B);
            } else if (index == a0.t.OnSwipe_springBoundary) {
                this.C = obtainStyledAttributes.getInt(index, this.C);
            } else if (index == a0.t.OnSwipe_autoCompleteMode) {
                this.D = obtainStyledAttributes.getInt(index, this.D);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final RectF a(MotionLayout motionLayout, RectF rectF) {
        View findViewById;
        int i7 = this.f9762f;
        if (i7 == -1 || (findViewById = motionLayout.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    public final RectF b(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i7 = this.f9761e;
        if (i7 == -1 || (findViewById = viewGroup.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    public final void c(boolean z6) {
        float[][] fArr = E;
        float[][] fArr2 = F;
        if (z6) {
            fArr2[4] = fArr2[3];
            fArr2[5] = fArr2[2];
            fArr[5] = fArr[2];
            fArr[6] = fArr[1];
        } else {
            fArr2[4] = fArr2[2];
            fArr2[5] = fArr2[3];
            fArr[5] = fArr[1];
            fArr[6] = fArr[2];
        }
        float[] fArr3 = fArr[this.f9757a];
        this.f9764h = fArr3[0];
        this.f9763g = fArr3[1];
        int i7 = this.f9758b;
        if (i7 >= 6) {
            return;
        }
        float[] fArr4 = fArr2[i7];
        this.f9767k = fArr4[0];
        this.f9768l = fArr4[1];
    }

    public final String toString() {
        if (Float.isNaN(this.f9767k)) {
            return c.ROTATION;
        }
        return this.f9767k + " , " + this.f9768l;
    }
}
