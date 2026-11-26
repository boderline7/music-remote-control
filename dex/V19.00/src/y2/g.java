package y2;

import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public final float f10141a;

    /* renamed from: b, reason: collision with root package name */
    public final float f10142b;

    /* renamed from: d, reason: collision with root package name */
    public h f10144d;

    /* renamed from: e, reason: collision with root package name */
    public h f10145e;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f10143c = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    public int f10146f = -1;

    /* renamed from: g, reason: collision with root package name */
    public int f10147g = -1;

    /* renamed from: h, reason: collision with root package name */
    public float f10148h = CropImageView.DEFAULT_ASPECT_RATIO;

    /* renamed from: i, reason: collision with root package name */
    public int f10149i = -1;

    public g(float f7, float f8) {
        this.f10141a = f7;
        this.f10142b = f8;
    }

    public final void a(float f7, float f8, float f9, boolean z6, boolean z7) {
        float f10;
        float f11 = f9 / 2.0f;
        float f12 = f7 - f11;
        float f13 = f11 + f7;
        float f14 = this.f10142b;
        if (f13 > f14) {
            f10 = Math.abs(f13 - Math.max(f13 - f9, f14));
        } else {
            f10 = CropImageView.DEFAULT_ASPECT_RATIO;
            if (f12 < CropImageView.DEFAULT_ASPECT_RATIO) {
                f10 = Math.abs(f12 - Math.min(f12 + f9, CropImageView.DEFAULT_ASPECT_RATIO));
            }
        }
        b(f7, f8, f9, z6, z7, f10);
    }

    public final void b(float f7, float f8, float f9, boolean z6, boolean z7, float f10) {
        if (f9 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        ArrayList arrayList = this.f10143c;
        if (z7) {
            if (z6) {
                throw new IllegalArgumentException("Anchor keylines cannot be focal.");
            }
            int i7 = this.f10149i;
            if (i7 != -1 && i7 != 0) {
                throw new IllegalArgumentException("Anchor keylines must be either the first or last keyline.");
            }
            this.f10149i = arrayList.size();
        }
        h hVar = new h(Float.MIN_VALUE, f7, f8, f9, z7, f10);
        if (z6) {
            if (this.f10144d == null) {
                this.f10144d = hVar;
                this.f10146f = arrayList.size();
            }
            if (this.f10147g != -1 && arrayList.size() - this.f10147g > 1) {
                throw new IllegalArgumentException("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
            }
            if (f9 != this.f10144d.f10153d) {
                throw new IllegalArgumentException("Keylines that are marked as focal must all have the same masked item size.");
            }
            this.f10145e = hVar;
            this.f10147g = arrayList.size();
        } else {
            if (this.f10144d == null && f9 < this.f10148h) {
                throw new IllegalArgumentException("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            }
            if (this.f10145e != null && f9 > this.f10148h) {
                throw new IllegalArgumentException("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            }
        }
        this.f10148h = f9;
        arrayList.add(hVar);
    }

    public final void c(float f7, float f8, float f9, int i7, boolean z6) {
        if (i7 <= 0 || f9 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return;
        }
        for (int i8 = 0; i8 < i7; i8++) {
            a((i8 * f9) + f7, f8, f9, z6, false);
        }
    }

    public final i d() {
        if (this.f10144d == null) {
            throw new IllegalStateException("There must be a keyline marked as focal.");
        }
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        while (true) {
            ArrayList arrayList2 = this.f10143c;
            int size = arrayList2.size();
            float f7 = this.f10141a;
            if (i7 >= size) {
                return new i(f7, arrayList, this.f10146f, this.f10147g);
            }
            h hVar = (h) arrayList2.get(i7);
            arrayList.add(new h((i7 * f7) + (this.f10144d.f10151b - (this.f10146f * f7)), hVar.f10151b, hVar.f10152c, hVar.f10153d, hVar.f10154e, hVar.f10155f));
            i7++;
        }
    }
}
