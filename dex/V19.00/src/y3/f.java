package y3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public final char f10186a;

    /* renamed from: b, reason: collision with root package name */
    public final int f10187b;

    /* renamed from: c, reason: collision with root package name */
    public final f f10188c;

    /* renamed from: d, reason: collision with root package name */
    public final int f10189d;

    public f(char c7, d dVar, int i7, f fVar, int i8) {
        char c8 = c7 == i8 ? (char) 1000 : c7;
        this.f10186a = c8;
        this.f10187b = i7;
        this.f10188c = fVar;
        int length = c8 == 1000 ? 1 : dVar.b(c7, i7).length;
        length = (fVar == null ? 0 : fVar.f10187b) != i7 ? length + 3 : length;
        this.f10189d = fVar != null ? length + fVar.f10189d : length;
    }
}
