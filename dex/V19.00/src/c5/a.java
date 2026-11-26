package c5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends i5.h implements h5.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3064a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3065b;

    public a(int i7, Object obj) {
        this.f3064a = i7;
        this.f3065b = obj;
    }

    @Override // h5.l
    public final Object b(Object obj) {
        int i7 = this.f3064a;
        Object obj2 = this.f3065b;
        switch (i7) {
            case 0:
                return obj == ((b) obj2) ? "(this Collection)" : String.valueOf(obj);
            case 1:
                String str = (String) obj;
                i5.a.g("line", str);
                return a.a.i(new StringBuilder(), (String) obj2, str);
            default:
                l5.e eVar = (l5.e) obj;
                i5.a.g("it", eVar);
                return o5.k.S((CharSequence) obj2, eVar);
        }
    }
}
