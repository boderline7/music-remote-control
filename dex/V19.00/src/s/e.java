package s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8627a;

    /* renamed from: b, reason: collision with root package name */
    public final Object[] f8628b;

    /* renamed from: c, reason: collision with root package name */
    public int f8629c;

    public e(int i7, int i8) {
        this.f8627a = i8;
        if (i8 != 1) {
            if (i7 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.f8628b = new Object[i7];
        } else {
            if (i7 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.f8628b = new Object[i7];
        }
    }

    public Object a() {
        int i7 = this.f8627a;
        Object[] objArr = this.f8628b;
        switch (i7) {
            case 0:
                int i8 = this.f8629c;
                if (i8 <= 0) {
                    return null;
                }
                int i9 = i8 - 1;
                Object obj = objArr[i9];
                objArr[i9] = null;
                this.f8629c = i9;
                return obj;
            default:
                int i10 = this.f8629c;
                if (i10 <= 0) {
                    return null;
                }
                int i11 = i10 - 1;
                Object obj2 = objArr[i11];
                objArr[i11] = null;
                this.f8629c = i11;
                return obj2;
        }
    }

    public boolean b(Object obj) {
        int i7 = this.f8627a;
        Object[] objArr = this.f8628b;
        switch (i7) {
            case 0:
                int i8 = this.f8629c;
                if (i8 >= objArr.length) {
                    return false;
                }
                objArr[i8] = obj;
                this.f8629c = i8 + 1;
                return true;
            default:
                int i9 = 0;
                while (true) {
                    int i10 = this.f8629c;
                    if (i9 >= i10) {
                        if (i10 >= objArr.length) {
                            return false;
                        }
                        objArr[i10] = obj;
                        this.f8629c = i10 + 1;
                        return true;
                    }
                    if (objArr[i9] == obj) {
                        throw new IllegalStateException("Already in the pool!");
                    }
                    i9++;
                }
        }
    }
}
