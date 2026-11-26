package q;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class k {

    /* renamed from: d, reason: collision with root package name */
    public static Object[] f8406d;

    /* renamed from: e, reason: collision with root package name */
    public static int f8407e;

    /* renamed from: f, reason: collision with root package name */
    public static Object[] f8408f;

    /* renamed from: g, reason: collision with root package name */
    public static int f8409g;

    /* renamed from: a, reason: collision with root package name */
    public int[] f8410a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f8411b;

    /* renamed from: c, reason: collision with root package name */
    public int f8412c;

    public k() {
        this.f8410a = d.f8382a;
        this.f8411b = d.f8383b;
        this.f8412c = 0;
    }

    public k(int i7) {
        if (i7 == 0) {
            this.f8410a = d.f8382a;
            this.f8411b = d.f8383b;
        } else {
            a(i7);
        }
        this.f8412c = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (k.class) {
                try {
                    if (f8409g < 10) {
                        objArr[0] = f8408f;
                        objArr[1] = iArr;
                        for (int i8 = (i7 << 1) - 1; i8 >= 2; i8--) {
                            objArr[i8] = null;
                        }
                        f8408f = objArr;
                        f8409g++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (k.class) {
                try {
                    if (f8407e < 10) {
                        objArr[0] = f8406d;
                        objArr[1] = iArr;
                        for (int i9 = (i7 << 1) - 1; i9 >= 2; i9--) {
                            objArr[i9] = null;
                        }
                        f8406d = objArr;
                        f8407e++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i7) {
        if (i7 == 8) {
            synchronized (k.class) {
                try {
                    Object[] objArr = f8408f;
                    if (objArr != null) {
                        this.f8411b = objArr;
                        f8408f = (Object[]) objArr[0];
                        this.f8410a = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f8409g--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i7 == 4) {
            synchronized (k.class) {
                try {
                    Object[] objArr2 = f8406d;
                    if (objArr2 != null) {
                        this.f8411b = objArr2;
                        f8406d = (Object[]) objArr2[0];
                        this.f8410a = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f8407e--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.f8410a = new int[i7];
        this.f8411b = new Object[i7 << 1];
    }

    public final void b(int i7) {
        int i8 = this.f8412c;
        int[] iArr = this.f8410a;
        if (iArr.length < i7) {
            Object[] objArr = this.f8411b;
            a(i7);
            if (this.f8412c > 0) {
                System.arraycopy(iArr, 0, this.f8410a, 0, i8);
                System.arraycopy(objArr, 0, this.f8411b, 0, i8 << 1);
            }
            c(iArr, objArr, i8);
        }
        if (this.f8412c != i8) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i7 = this.f8412c;
        if (i7 > 0) {
            int[] iArr = this.f8410a;
            Object[] objArr = this.f8411b;
            this.f8410a = d.f8382a;
            this.f8411b = d.f8383b;
            this.f8412c = 0;
            c(iArr, objArr, i7);
        }
        if (this.f8412c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public final int d(int i7, Object obj) {
        int i8 = this.f8412c;
        if (i8 == 0) {
            return -1;
        }
        try {
            int a7 = d.a(i8, i7, this.f8410a);
            if (a7 < 0 || obj.equals(this.f8411b[a7 << 1])) {
                return a7;
            }
            int i9 = a7 + 1;
            while (i9 < i8 && this.f8410a[i9] == i7) {
                if (obj.equals(this.f8411b[i9 << 1])) {
                    return i9;
                }
                i9++;
            }
            for (int i10 = a7 - 1; i10 >= 0 && this.f8410a[i10] == i7; i10--) {
                if (obj.equals(this.f8411b[i10 << 1])) {
                    return i10;
                }
            }
            return ~i9;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k) {
            k kVar = (k) obj;
            if (this.f8412c != kVar.f8412c) {
                return false;
            }
            for (int i7 = 0; i7 < this.f8412c; i7++) {
                try {
                    Object h7 = h(i7);
                    Object j7 = j(i7);
                    Object orDefault = kVar.getOrDefault(h7, null);
                    if (j7 == null) {
                        if (orDefault != null || !kVar.containsKey(h7)) {
                            return false;
                        }
                    } else if (!j7.equals(orDefault)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.f8412c != map.size()) {
                return false;
            }
            for (int i8 = 0; i8 < this.f8412c; i8++) {
                try {
                    Object h8 = h(i8);
                    Object j8 = j(i8);
                    Object obj2 = map.get(h8);
                    if (j8 == null) {
                        if (obj2 != null || !map.containsKey(h8)) {
                            return false;
                        }
                    } else if (!j8.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i7 = this.f8412c;
        if (i7 == 0) {
            return -1;
        }
        try {
            int a7 = d.a(i7, 0, this.f8410a);
            if (a7 < 0 || this.f8411b[a7 << 1] == null) {
                return a7;
            }
            int i8 = a7 + 1;
            while (i8 < i7 && this.f8410a[i8] == 0) {
                if (this.f8411b[i8 << 1] == null) {
                    return i8;
                }
                i8++;
            }
            for (int i9 = a7 - 1; i9 >= 0 && this.f8410a[i9] == 0; i9--) {
                if (this.f8411b[i9 << 1] == null) {
                    return i9;
                }
            }
            return ~i8;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int g(Object obj) {
        int i7 = this.f8412c * 2;
        Object[] objArr = this.f8411b;
        if (obj == null) {
            for (int i8 = 1; i8 < i7; i8 += 2) {
                if (objArr[i8] == null) {
                    return i8 >> 1;
                }
            }
            return -1;
        }
        for (int i9 = 1; i9 < i7; i9 += 2) {
            if (obj.equals(objArr[i9])) {
                return i9 >> 1;
            }
        }
        return -1;
    }

    public final Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int e7 = e(obj);
        return e7 >= 0 ? this.f8411b[(e7 << 1) + 1] : obj2;
    }

    public final Object h(int i7) {
        return this.f8411b[i7 << 1];
    }

    public final int hashCode() {
        int[] iArr = this.f8410a;
        Object[] objArr = this.f8411b;
        int i7 = this.f8412c;
        int i8 = 1;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            Object obj = objArr[i8];
            i10 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i9];
            i9++;
            i8 += 2;
        }
        return i10;
    }

    public final Object i(int i7) {
        Object[] objArr = this.f8411b;
        int i8 = i7 << 1;
        Object obj = objArr[i8 + 1];
        int i9 = this.f8412c;
        int i10 = 0;
        if (i9 <= 1) {
            c(this.f8410a, objArr, i9);
            this.f8410a = d.f8382a;
            this.f8411b = d.f8383b;
        } else {
            int i11 = i9 - 1;
            int[] iArr = this.f8410a;
            if (iArr.length <= 8 || i9 >= iArr.length / 3) {
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    int i13 = i11 - i7;
                    System.arraycopy(iArr, i12, iArr, i7, i13);
                    Object[] objArr2 = this.f8411b;
                    System.arraycopy(objArr2, i12 << 1, objArr2, i8, i13 << 1);
                }
                Object[] objArr3 = this.f8411b;
                int i14 = i11 << 1;
                objArr3[i14] = null;
                objArr3[i14 + 1] = null;
            } else {
                a(i9 > 8 ? i9 + (i9 >> 1) : 8);
                if (i9 != this.f8412c) {
                    throw new ConcurrentModificationException();
                }
                if (i7 > 0) {
                    System.arraycopy(iArr, 0, this.f8410a, 0, i7);
                    System.arraycopy(objArr, 0, this.f8411b, 0, i8);
                }
                if (i7 < i11) {
                    int i15 = i7 + 1;
                    int i16 = i11 - i7;
                    System.arraycopy(iArr, i15, this.f8410a, i7, i16);
                    System.arraycopy(objArr, i15 << 1, this.f8411b, i8, i16 << 1);
                }
            }
            i10 = i11;
        }
        if (i9 != this.f8412c) {
            throw new ConcurrentModificationException();
        }
        this.f8412c = i10;
        return obj;
    }

    public final boolean isEmpty() {
        return this.f8412c <= 0;
    }

    public final Object j(int i7) {
        return this.f8411b[(i7 << 1) + 1];
    }

    public final Object put(Object obj, Object obj2) {
        int i7;
        int d7;
        int i8 = this.f8412c;
        if (obj == null) {
            d7 = f();
            i7 = 0;
        } else {
            int hashCode = obj.hashCode();
            i7 = hashCode;
            d7 = d(hashCode, obj);
        }
        if (d7 >= 0) {
            int i9 = (d7 << 1) + 1;
            Object[] objArr = this.f8411b;
            Object obj3 = objArr[i9];
            objArr[i9] = obj2;
            return obj3;
        }
        int i10 = ~d7;
        int[] iArr = this.f8410a;
        if (i8 >= iArr.length) {
            int i11 = 8;
            if (i8 >= 8) {
                i11 = (i8 >> 1) + i8;
            } else if (i8 < 4) {
                i11 = 4;
            }
            Object[] objArr2 = this.f8411b;
            a(i11);
            if (i8 != this.f8412c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f8410a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f8411b, 0, objArr2.length);
            }
            c(iArr, objArr2, i8);
        }
        if (i10 < i8) {
            int[] iArr3 = this.f8410a;
            int i12 = i10 + 1;
            System.arraycopy(iArr3, i10, iArr3, i12, i8 - i10);
            Object[] objArr3 = this.f8411b;
            System.arraycopy(objArr3, i10 << 1, objArr3, i12 << 1, (this.f8412c - i10) << 1);
        }
        int i13 = this.f8412c;
        if (i8 == i13) {
            int[] iArr4 = this.f8410a;
            if (i10 < iArr4.length) {
                iArr4[i10] = i7;
                Object[] objArr4 = this.f8411b;
                int i14 = i10 << 1;
                objArr4[i14] = obj;
                objArr4[i14 + 1] = obj2;
                this.f8412c = i13 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    public final Object remove(Object obj) {
        int e7 = e(obj);
        if (e7 >= 0) {
            return i(e7);
        }
        return null;
    }

    public final boolean remove(Object obj, Object obj2) {
        int e7 = e(obj);
        if (e7 < 0) {
            return false;
        }
        Object j7 = j(e7);
        if (obj2 != j7 && (obj2 == null || !obj2.equals(j7))) {
            return false;
        }
        i(e7);
        return true;
    }

    public final Object replace(Object obj, Object obj2) {
        int e7 = e(obj);
        if (e7 < 0) {
            return null;
        }
        int i7 = (e7 << 1) + 1;
        Object[] objArr = this.f8411b;
        Object obj3 = objArr[i7];
        objArr[i7] = obj2;
        return obj3;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int e7 = e(obj);
        if (e7 < 0) {
            return false;
        }
        Object j7 = j(e7);
        if (j7 != obj2 && (obj2 == null || !obj2.equals(j7))) {
            return false;
        }
        int i7 = (e7 << 1) + 1;
        Object[] objArr = this.f8411b;
        Object obj4 = objArr[i7];
        objArr[i7] = obj3;
        return true;
    }

    public final int size() {
        return this.f8412c;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8412c * 28);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8412c; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            Object h7 = h(i7);
            if (h7 != this) {
                sb.append(h7);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object j7 = j(i7);
            if (j7 != this) {
                sb.append(j7);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
