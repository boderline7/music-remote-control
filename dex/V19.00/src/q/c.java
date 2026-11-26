package q;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements Collection, Set {

    /* renamed from: e, reason: collision with root package name */
    public static final int[] f8372e = new int[0];

    /* renamed from: f, reason: collision with root package name */
    public static final Object[] f8373f = new Object[0];

    /* renamed from: g, reason: collision with root package name */
    public static Object[] f8374g;

    /* renamed from: h, reason: collision with root package name */
    public static int f8375h;

    /* renamed from: i, reason: collision with root package name */
    public static Object[] f8376i;

    /* renamed from: j, reason: collision with root package name */
    public static int f8377j;

    /* renamed from: a, reason: collision with root package name */
    public int[] f8378a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f8379b;

    /* renamed from: c, reason: collision with root package name */
    public int f8380c;

    /* renamed from: d, reason: collision with root package name */
    public a f8381d;

    public c(int i7) {
        if (i7 == 0) {
            this.f8378a = f8372e;
            this.f8379b = f8373f;
        } else {
            a(i7);
        }
        this.f8380c = 0;
    }

    public static void b(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (c.class) {
                try {
                    if (f8377j < 10) {
                        objArr[0] = f8376i;
                        objArr[1] = iArr;
                        for (int i8 = i7 - 1; i8 >= 2; i8--) {
                            objArr[i8] = null;
                        }
                        f8376i = objArr;
                        f8377j++;
                    }
                } finally {
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (c.class) {
                try {
                    if (f8375h < 10) {
                        objArr[0] = f8374g;
                        objArr[1] = iArr;
                        for (int i9 = i7 - 1; i9 >= 2; i9--) {
                            objArr[i9] = null;
                        }
                        f8374g = objArr;
                        f8375h++;
                    }
                } finally {
                }
            }
        }
    }

    public final void a(int i7) {
        if (i7 == 8) {
            synchronized (c.class) {
                try {
                    Object[] objArr = f8376i;
                    if (objArr != null) {
                        this.f8379b = objArr;
                        f8376i = (Object[]) objArr[0];
                        this.f8378a = (int[]) objArr[1];
                        objArr[1] = null;
                        objArr[0] = null;
                        f8377j--;
                        return;
                    }
                } finally {
                }
            }
        } else if (i7 == 4) {
            synchronized (c.class) {
                try {
                    Object[] objArr2 = f8374g;
                    if (objArr2 != null) {
                        this.f8379b = objArr2;
                        f8374g = (Object[]) objArr2[0];
                        this.f8378a = (int[]) objArr2[1];
                        objArr2[1] = null;
                        objArr2[0] = null;
                        f8375h--;
                        return;
                    }
                } finally {
                }
            }
        }
        this.f8378a = new int[i7];
        this.f8379b = new Object[i7];
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i7;
        int c7;
        if (obj == null) {
            c7 = d();
            i7 = 0;
        } else {
            int hashCode = obj.hashCode();
            i7 = hashCode;
            c7 = c(hashCode, obj);
        }
        if (c7 >= 0) {
            return false;
        }
        int i8 = ~c7;
        int i9 = this.f8380c;
        int[] iArr = this.f8378a;
        if (i9 >= iArr.length) {
            int i10 = 8;
            if (i9 >= 8) {
                i10 = (i9 >> 1) + i9;
            } else if (i9 < 4) {
                i10 = 4;
            }
            Object[] objArr = this.f8379b;
            a(i10);
            int[] iArr2 = this.f8378a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f8379b, 0, objArr.length);
            }
            b(iArr, objArr, this.f8380c);
        }
        int i11 = this.f8380c;
        if (i8 < i11) {
            int[] iArr3 = this.f8378a;
            int i12 = i8 + 1;
            System.arraycopy(iArr3, i8, iArr3, i12, i11 - i8);
            Object[] objArr2 = this.f8379b;
            System.arraycopy(objArr2, i8, objArr2, i12, this.f8380c - i8);
        }
        this.f8378a[i8] = i7;
        this.f8379b[i8] = obj;
        this.f8380c++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        int size = collection.size() + this.f8380c;
        int[] iArr = this.f8378a;
        boolean z6 = false;
        if (iArr.length < size) {
            Object[] objArr = this.f8379b;
            a(size);
            int i7 = this.f8380c;
            if (i7 > 0) {
                System.arraycopy(iArr, 0, this.f8378a, 0, i7);
                System.arraycopy(objArr, 0, this.f8379b, 0, this.f8380c);
            }
            b(iArr, objArr, this.f8380c);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            z6 |= add(it.next());
        }
        return z6;
    }

    public final int c(int i7, Object obj) {
        int i8 = this.f8380c;
        if (i8 == 0) {
            return -1;
        }
        int a7 = d.a(i8, i7, this.f8378a);
        if (a7 < 0 || obj.equals(this.f8379b[a7])) {
            return a7;
        }
        int i9 = a7 + 1;
        while (i9 < i8 && this.f8378a[i9] == i7) {
            if (obj.equals(this.f8379b[i9])) {
                return i9;
            }
            i9++;
        }
        for (int i10 = a7 - 1; i10 >= 0 && this.f8378a[i10] == i7; i10--) {
            if (obj.equals(this.f8379b[i10])) {
                return i10;
            }
        }
        return ~i9;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        int i7 = this.f8380c;
        if (i7 != 0) {
            b(this.f8378a, this.f8379b, i7);
            this.f8378a = f8372e;
            this.f8379b = f8373f;
            this.f8380c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int d() {
        int i7 = this.f8380c;
        if (i7 == 0) {
            return -1;
        }
        int a7 = d.a(i7, 0, this.f8378a);
        if (a7 < 0 || this.f8379b[a7] == null) {
            return a7;
        }
        int i8 = a7 + 1;
        while (i8 < i7 && this.f8378a[i8] == 0) {
            if (this.f8379b[i8] == null) {
                return i8;
            }
            i8++;
        }
        for (int i9 = a7 - 1; i9 >= 0 && this.f8378a[i9] == 0; i9--) {
            if (this.f8379b[i9] == null) {
                return i9;
            }
        }
        return ~i8;
    }

    public final void e(int i7) {
        Object[] objArr = this.f8379b;
        Object obj = objArr[i7];
        int i8 = this.f8380c;
        if (i8 <= 1) {
            b(this.f8378a, objArr, i8);
            this.f8378a = f8372e;
            this.f8379b = f8373f;
            this.f8380c = 0;
            return;
        }
        int[] iArr = this.f8378a;
        if (iArr.length <= 8 || i8 >= iArr.length / 3) {
            int i9 = i8 - 1;
            this.f8380c = i9;
            if (i7 < i9) {
                int i10 = i7 + 1;
                System.arraycopy(iArr, i10, iArr, i7, i9 - i7);
                Object[] objArr2 = this.f8379b;
                System.arraycopy(objArr2, i10, objArr2, i7, this.f8380c - i7);
            }
            this.f8379b[this.f8380c] = null;
            return;
        }
        a(i8 > 8 ? i8 + (i8 >> 1) : 8);
        this.f8380c--;
        if (i7 > 0) {
            System.arraycopy(iArr, 0, this.f8378a, 0, i7);
            System.arraycopy(objArr, 0, this.f8379b, 0, i7);
        }
        int i11 = this.f8380c;
        if (i7 < i11) {
            int i12 = i7 + 1;
            System.arraycopy(iArr, i12, this.f8378a, i7, i11 - i7);
            System.arraycopy(objArr, i12, this.f8379b, i7, this.f8380c - i7);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.f8380c != set.size()) {
                return false;
            }
            for (int i7 = 0; i7 < this.f8380c; i7++) {
                try {
                    if (!set.contains(this.f8379b[i7])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.f8378a;
        int i7 = this.f8380c;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            i8 += iArr[i9];
        }
        return i8;
    }

    public final int indexOf(Object obj) {
        return obj == null ? d() : c(obj.hashCode(), obj);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f8380c <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        int i7 = 1;
        if (this.f8381d == null) {
            this.f8381d = new a(1, this);
        }
        a aVar = this.f8381d;
        if (((h) aVar.f7061b) == null) {
            aVar.f7061b = new h(aVar, i7);
        }
        return ((h) aVar.f7061b).iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        e(indexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean z6 = false;
        while (it.hasNext()) {
            z6 |= remove(it.next());
        }
        return z6;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        boolean z6 = false;
        for (int i7 = this.f8380c - 1; i7 >= 0; i7--) {
            if (!collection.contains(this.f8379b[i7])) {
                e(i7);
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.f8380c;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        int i7 = this.f8380c;
        Object[] objArr = new Object[i7];
        System.arraycopy(this.f8379b, 0, objArr, 0, i7);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        if (objArr.length < this.f8380c) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.f8380c);
        }
        System.arraycopy(this.f8379b, 0, objArr, 0, this.f8380c);
        int length = objArr.length;
        int i7 = this.f8380c;
        if (length > i7) {
            objArr[i7] = null;
        }
        return objArr;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8380c * 14);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8380c; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            Object obj = this.f8379b[i7];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
