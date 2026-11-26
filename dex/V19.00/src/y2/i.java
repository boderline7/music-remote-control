package y2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    public final float f10156a;

    /* renamed from: b, reason: collision with root package name */
    public final List f10157b;

    /* renamed from: c, reason: collision with root package name */
    public final int f10158c;

    /* renamed from: d, reason: collision with root package name */
    public final int f10159d;

    public i(float f7, ArrayList arrayList, int i7, int i8) {
        this.f10156a = f7;
        this.f10157b = Collections.unmodifiableList(arrayList);
        this.f10158c = i7;
        this.f10159d = i8;
    }

    public final h a() {
        return (h) this.f10157b.get(this.f10158c);
    }

    public final h b() {
        return (h) this.f10157b.get(0);
    }

    public final h c() {
        return (h) this.f10157b.get(this.f10159d);
    }

    public final h d() {
        return (h) this.f10157b.get(r0.size() - 1);
    }
}
