package s;

import com.yalantis.ucrop.view.CropImageView;
import java.util.Arrays;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i implements Comparable {
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;

    /* renamed from: a, reason: collision with root package name */
    public boolean f8637a;

    /* renamed from: e, reason: collision with root package name */
    public float f8641e;

    /* renamed from: i, reason: collision with root package name */
    public h f8645i;

    /* renamed from: b, reason: collision with root package name */
    public int f8638b = -1;

    /* renamed from: c, reason: collision with root package name */
    public int f8639c = -1;

    /* renamed from: d, reason: collision with root package name */
    public int f8640d = 0;

    /* renamed from: f, reason: collision with root package name */
    public boolean f8642f = false;

    /* renamed from: g, reason: collision with root package name */
    public final float[] f8643g = new float[9];

    /* renamed from: h, reason: collision with root package name */
    public final float[] f8644h = new float[9];

    /* renamed from: j, reason: collision with root package name */
    public c[] f8646j = new c[16];

    /* renamed from: k, reason: collision with root package name */
    public int f8647k = 0;

    /* renamed from: l, reason: collision with root package name */
    public int f8648l = 0;

    public i(h hVar) {
        this.f8645i = hVar;
    }

    public final void a(c cVar) {
        int i7 = 0;
        while (true) {
            int i8 = this.f8647k;
            if (i7 >= i8) {
                c[] cVarArr = this.f8646j;
                if (i8 >= cVarArr.length) {
                    this.f8646j = (c[]) Arrays.copyOf(cVarArr, cVarArr.length * 2);
                }
                c[] cVarArr2 = this.f8646j;
                int i9 = this.f8647k;
                cVarArr2[i9] = cVar;
                this.f8647k = i9 + 1;
                return;
            }
            if (this.f8646j[i7] == cVar) {
                return;
            } else {
                i7++;
            }
        }
    }

    public final void b(c cVar) {
        int i7 = this.f8647k;
        int i8 = 0;
        while (i8 < i7) {
            if (this.f8646j[i8] == cVar) {
                while (i8 < i7 - 1) {
                    c[] cVarArr = this.f8646j;
                    int i9 = i8 + 1;
                    cVarArr[i8] = cVarArr[i9];
                    i8 = i9;
                }
                this.f8647k--;
                return;
            }
            i8++;
        }
    }

    public final void c() {
        this.f8645i = h.UNKNOWN;
        this.f8640d = 0;
        this.f8638b = -1;
        this.f8639c = -1;
        this.f8641e = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f8642f = false;
        int i7 = this.f8647k;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8646j[i8] = null;
        }
        this.f8647k = 0;
        this.f8648l = 0;
        this.f8637a = false;
        Arrays.fill(this.f8644h, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f8638b - ((i) obj).f8638b;
    }

    public final void d(d dVar, float f7) {
        this.f8641e = f7;
        this.f8642f = true;
        int i7 = this.f8647k;
        this.f8639c = -1;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8646j[i8].h(dVar, this, false);
        }
        this.f8647k = 0;
    }

    public final void e(d dVar, c cVar) {
        int i7 = this.f8647k;
        for (int i8 = 0; i8 < i7; i8++) {
            this.f8646j[i8].i(dVar, cVar, false);
        }
        this.f8647k = 0;
    }

    public final String toString() {
        return h0.FRAGMENT_ENCODE_SET + this.f8638b;
    }
}
