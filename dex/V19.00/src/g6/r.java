package g6;

import java.util.RandomAccess;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r extends c5.g implements RandomAccess {
    public static final q Companion = new q();

    /* renamed from: a, reason: collision with root package name */
    public final l[] f6588a;

    /* renamed from: b, reason: collision with root package name */
    public final int[] f6589b;

    public r(l[] lVarArr, int[] iArr) {
        this.f6588a = lVarArr;
        this.f6589b = iArr;
    }

    @Override // c5.b
    public final int a() {
        return this.f6588a.length;
    }

    @Override // c5.b, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof l) {
            return super.contains((l) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        return this.f6588a[i7];
    }

    @Override // c5.g, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof l) {
            return super.indexOf((l) obj);
        }
        return -1;
    }

    @Override // c5.g, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof l) {
            return super.lastIndexOf((l) obj);
        }
        return -1;
    }
}
