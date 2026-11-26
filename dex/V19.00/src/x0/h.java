package x0;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import n0.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h implements a {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;

    /* renamed from: a, reason: collision with root package name */
    public float f9559a;

    /* renamed from: b, reason: collision with root package name */
    public float f9560b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f9561c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f9562d;

    /* renamed from: e, reason: collision with root package name */
    public final k f9563e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f9564f;

    /* renamed from: g, reason: collision with root package name */
    public final float f9565g;

    /* renamed from: h, reason: collision with root package name */
    public long f9566h;

    /* renamed from: i, reason: collision with root package name */
    public final float f9567i;

    /* renamed from: j, reason: collision with root package name */
    public final ArrayList f9568j;

    /* renamed from: k, reason: collision with root package name */
    public final ArrayList f9569k;
    public static final g TRANSLATION_X = new e(y.c.TRANSLATION_X, 5);
    public static final g TRANSLATION_Y = new e(y.c.TRANSLATION_Y, 6);
    public static final g TRANSLATION_Z = new e(y.c.TRANSLATION_Z, 7);
    public static final g SCALE_X = new e(y.c.SCALE_X, 8);
    public static final g SCALE_Y = new e(y.c.SCALE_Y, 9);
    public static final g ROTATION = new e(y.c.ROTATION, 10);
    public static final g ROTATION_X = new e(y.c.ROTATION_X, 11);
    public static final g ROTATION_Y = new e(y.c.ROTATION_Y, 12);
    public static final g X = new e("x", 13);
    public static final g Y = new e("y", 0);
    public static final g Z = new e("z", 1);
    public static final g ALPHA = new e(y.c.ALPHA, 2);
    public static final g SCROLL_X = new e("scrollX", 3);
    public static final g SCROLL_Y = new e("scrollY", 4);

    public h(Object obj) {
        l3.i iVar = l3.j.f7371q;
        this.f9559a = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f9560b = Float.MAX_VALUE;
        this.f9561c = false;
        this.f9564f = false;
        this.f9565g = -3.4028235E38f;
        this.f9566h = 0L;
        this.f9568j = new ArrayList();
        this.f9569k = new ArrayList();
        this.f9562d = obj;
        this.f9563e = iVar;
        if (iVar == ROTATION || iVar == ROTATION_X || iVar == ROTATION_Y) {
            this.f9567i = 0.1f;
            return;
        }
        if (iVar == ALPHA) {
            this.f9567i = 0.00390625f;
        } else if (iVar == SCALE_X || iVar == SCALE_Y) {
            this.f9567i = 0.00390625f;
        } else {
            this.f9567i = 1.0f;
        }
    }

    public final void a(float f7) {
        this.f9563e.d(this.f9562d, f7);
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f9569k;
            if (i7 >= arrayList.size()) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (arrayList.get(size) == null) {
                        arrayList.remove(size);
                    }
                }
                return;
            }
            if (arrayList.get(i7) != null) {
                a.a.u(arrayList.get(i7));
                throw null;
            }
            i7++;
        }
    }
}
