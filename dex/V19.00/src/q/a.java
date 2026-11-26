package q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends k.d {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f8369d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f8370e;

    public a(int i7, Object obj) {
        this.f8369d = i7;
        this.f8370e = obj;
    }

    @Override // k.d
    public final void d() {
        int i7 = this.f8369d;
        Object obj = this.f8370e;
        switch (i7) {
            case 0:
                ((b) obj).clear();
                break;
            default:
                ((c) obj).clear();
                break;
        }
    }

    @Override // k.d
    public final Object e(int i7, int i8) {
        int i9 = this.f8369d;
        Object obj = this.f8370e;
        switch (i9) {
            case 0:
                return ((b) obj).f8411b[(i7 << 1) + i8];
            default:
                return ((c) obj).f8379b[i7];
        }
    }

    @Override // k.d
    public final b f() {
        switch (this.f8369d) {
            case 0:
                return (b) this.f8370e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    @Override // k.d
    public final int g() {
        int i7 = this.f8369d;
        Object obj = this.f8370e;
        switch (i7) {
            case 0:
                return ((b) obj).f8412c;
            default:
                return ((c) obj).f8380c;
        }
    }

    @Override // k.d
    public final int h(Object obj) {
        int i7 = this.f8369d;
        Object obj2 = this.f8370e;
        switch (i7) {
            case 0:
                return ((b) obj2).e(obj);
            default:
                return ((c) obj2).indexOf(obj);
        }
    }

    @Override // k.d
    public final int i(Object obj) {
        int i7 = this.f8369d;
        Object obj2 = this.f8370e;
        switch (i7) {
            case 0:
                return ((b) obj2).g(obj);
            default:
                return ((c) obj2).indexOf(obj);
        }
    }

    @Override // k.d
    public final void j(Object obj, Object obj2) {
        int i7 = this.f8369d;
        Object obj3 = this.f8370e;
        switch (i7) {
            case 0:
                ((b) obj3).put(obj, obj2);
                break;
            default:
                ((c) obj3).add(obj);
                break;
        }
    }

    @Override // k.d
    public final void k(int i7) {
        int i8 = this.f8369d;
        Object obj = this.f8370e;
        switch (i8) {
            case 0:
                ((b) obj).i(i7);
                break;
            default:
                ((c) obj).e(i7);
                break;
        }
    }

    @Override // k.d
    public final Object l(int i7, Object obj) {
        switch (this.f8369d) {
            case 0:
                int i8 = (i7 << 1) + 1;
                Object[] objArr = ((b) this.f8370e).f8411b;
                Object obj2 = objArr[i8];
                objArr[i8] = obj;
                return obj2;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }
}
