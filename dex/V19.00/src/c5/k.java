package c5;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends h {
    public static final j Companion = new j();

    /* renamed from: d, reason: collision with root package name */
    public static final Object[] f3075d = new Object[0];

    /* renamed from: a, reason: collision with root package name */
    public int f3076a;

    /* renamed from: b, reason: collision with root package name */
    public Object[] f3077b = f3075d;

    /* renamed from: c, reason: collision with root package name */
    public int f3078c;

    public final void a(Object obj) {
        c(this.f3078c + 1);
        this.f3077b[e(this.f3076a + this.f3078c)] = obj;
        this.f3078c++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        int i8;
        c cVar = g.Companion;
        int i9 = this.f3078c;
        cVar.getClass();
        c.b(i7, i9);
        int i10 = this.f3078c;
        if (i7 == i10) {
            a(obj);
            return;
        }
        if (i7 == 0) {
            c(i10 + 1);
            int i11 = this.f3076a;
            if (i11 == 0) {
                Object[] objArr = this.f3077b;
                i5.a.g("<this>", objArr);
                i11 = objArr.length;
            }
            int i12 = i11 - 1;
            this.f3076a = i12;
            this.f3077b[i12] = obj;
            this.f3078c++;
            return;
        }
        c(i10 + 1);
        int e7 = e(this.f3076a + i7);
        int i13 = this.f3078c;
        if (i7 < ((i13 + 1) >> 1)) {
            if (e7 == 0) {
                Object[] objArr2 = this.f3077b;
                i5.a.g("<this>", objArr2);
                i8 = objArr2.length - 1;
            } else {
                i8 = e7 - 1;
            }
            int i14 = this.f3076a;
            if (i14 == 0) {
                Object[] objArr3 = this.f3077b;
                i5.a.g("<this>", objArr3);
                i14 = objArr3.length;
            }
            int i15 = i14 - 1;
            int i16 = this.f3076a;
            if (i8 >= i16) {
                Object[] objArr4 = this.f3077b;
                objArr4[i15] = objArr4[i16];
                l.z1(objArr4, objArr4, i16, i16 + 1, i8 + 1);
            } else {
                Object[] objArr5 = this.f3077b;
                l.z1(objArr5, objArr5, i16 - 1, i16, objArr5.length);
                Object[] objArr6 = this.f3077b;
                objArr6[objArr6.length - 1] = objArr6[0];
                l.z1(objArr6, objArr6, 0, 1, i8 + 1);
            }
            this.f3077b[i8] = obj;
            this.f3076a = i15;
        } else {
            int e8 = e(this.f3076a + i13);
            if (e7 < e8) {
                Object[] objArr7 = this.f3077b;
                l.z1(objArr7, objArr7, e7 + 1, e7, e8);
            } else {
                Object[] objArr8 = this.f3077b;
                l.z1(objArr8, objArr8, 1, 0, e8);
                Object[] objArr9 = this.f3077b;
                objArr9[0] = objArr9[objArr9.length - 1];
                l.z1(objArr9, objArr9, e7 + 1, e7, objArr9.length - 1);
            }
            this.f3077b[e7] = obj;
        }
        this.f3078c++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a(obj);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i7, Collection collection) {
        i5.a.g("elements", collection);
        c cVar = g.Companion;
        int i8 = this.f3078c;
        cVar.getClass();
        c.b(i7, i8);
        if (collection.isEmpty()) {
            return false;
        }
        int i9 = this.f3078c;
        if (i7 == i9) {
            return addAll(collection);
        }
        c(collection.size() + i9);
        int e7 = e(this.f3076a + this.f3078c);
        int e8 = e(this.f3076a + i7);
        int size = collection.size();
        if (i7 < ((this.f3078c + 1) >> 1)) {
            int i10 = this.f3076a;
            int i11 = i10 - size;
            if (e8 < i10) {
                Object[] objArr = this.f3077b;
                l.z1(objArr, objArr, i11, i10, objArr.length);
                if (size >= e8) {
                    Object[] objArr2 = this.f3077b;
                    l.z1(objArr2, objArr2, objArr2.length - size, 0, e8);
                } else {
                    Object[] objArr3 = this.f3077b;
                    l.z1(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.f3077b;
                    l.z1(objArr4, objArr4, 0, size, e8);
                }
            } else if (i11 >= 0) {
                Object[] objArr5 = this.f3077b;
                l.z1(objArr5, objArr5, i11, i10, e8);
            } else {
                Object[] objArr6 = this.f3077b;
                i11 += objArr6.length;
                int i12 = e8 - i10;
                int length = objArr6.length - i11;
                if (length >= i12) {
                    l.z1(objArr6, objArr6, i11, i10, e8);
                } else {
                    l.z1(objArr6, objArr6, i11, i10, i10 + length);
                    Object[] objArr7 = this.f3077b;
                    l.z1(objArr7, objArr7, 0, this.f3076a + length, e8);
                }
            }
            this.f3076a = i11;
            int i13 = e8 - size;
            if (i13 < 0) {
                i13 += this.f3077b.length;
            }
            b(i13, collection);
        } else {
            int i14 = e8 + size;
            if (e8 < e7) {
                int i15 = size + e7;
                Object[] objArr8 = this.f3077b;
                if (i15 <= objArr8.length) {
                    l.z1(objArr8, objArr8, i14, e8, e7);
                } else if (i14 >= objArr8.length) {
                    l.z1(objArr8, objArr8, i14 - objArr8.length, e8, e7);
                } else {
                    int length2 = e7 - (i15 - objArr8.length);
                    l.z1(objArr8, objArr8, 0, length2, e7);
                    Object[] objArr9 = this.f3077b;
                    l.z1(objArr9, objArr9, i14, e8, length2);
                }
            } else {
                Object[] objArr10 = this.f3077b;
                l.z1(objArr10, objArr10, size, 0, e7);
                Object[] objArr11 = this.f3077b;
                if (i14 >= objArr11.length) {
                    l.z1(objArr11, objArr11, i14 - objArr11.length, e8, objArr11.length);
                } else {
                    l.z1(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.f3077b;
                    l.z1(objArr12, objArr12, i14, e8, objArr12.length - size);
                }
            }
            b(e8, collection);
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        i5.a.g("elements", collection);
        if (collection.isEmpty()) {
            return false;
        }
        c(collection.size() + this.f3078c);
        b(e(this.f3076a + this.f3078c), collection);
        return true;
    }

    public final void b(int i7, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.f3077b.length;
        while (i7 < length && it.hasNext()) {
            this.f3077b[i7] = it.next();
            i7++;
        }
        int i8 = this.f3076a;
        for (int i9 = 0; i9 < i8 && it.hasNext(); i9++) {
            this.f3077b[i9] = it.next();
        }
        this.f3078c = collection.size() + this.f3078c;
    }

    public final void c(int i7) {
        if (i7 < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.f3077b;
        if (i7 <= objArr.length) {
            return;
        }
        if (objArr == f3075d) {
            if (i7 < 10) {
                i7 = 10;
            }
            this.f3077b = new Object[i7];
            return;
        }
        j jVar = Companion;
        int length = objArr.length;
        jVar.getClass();
        int i8 = length + (length >> 1);
        if (i8 - i7 < 0) {
            i8 = i7;
        }
        if (i8 - 2147483639 > 0) {
            i8 = i7 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i8];
        Object[] objArr3 = this.f3077b;
        l.z1(objArr3, objArr2, 0, this.f3076a, objArr3.length);
        Object[] objArr4 = this.f3077b;
        int length2 = objArr4.length;
        int i9 = this.f3076a;
        l.z1(objArr4, objArr2, length2 - i9, 0, i9);
        this.f3076a = 0;
        this.f3077b = objArr2;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int e7 = e(this.f3076a + this.f3078c);
        int i7 = this.f3076a;
        if (i7 < e7) {
            l.C1(this.f3077b, null, i7, e7);
        } else if (!isEmpty()) {
            Object[] objArr = this.f3077b;
            l.C1(objArr, null, this.f3076a, objArr.length);
            l.C1(this.f3077b, null, 0, e7);
        }
        this.f3076a = 0;
        this.f3078c = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i7) {
        i5.a.g("<this>", this.f3077b);
        if (i7 == r0.length - 1) {
            return 0;
        }
        return i7 + 1;
    }

    public final int e(int i7) {
        Object[] objArr = this.f3077b;
        return i7 >= objArr.length ? i7 - objArr.length : i7;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        c cVar = g.Companion;
        int i8 = this.f3078c;
        cVar.getClass();
        c.a(i7, i8);
        return this.f3077b[e(this.f3076a + i7)];
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i7;
        int e7 = e(this.f3076a + this.f3078c);
        int i8 = this.f3076a;
        if (i8 < e7) {
            while (i8 < e7) {
                if (i5.a.c(obj, this.f3077b[i8])) {
                    i7 = this.f3076a;
                } else {
                    i8++;
                }
            }
            return -1;
        }
        if (i8 < e7) {
            return -1;
        }
        int length = this.f3077b.length;
        while (true) {
            if (i8 >= length) {
                for (int i9 = 0; i9 < e7; i9++) {
                    if (i5.a.c(obj, this.f3077b[i9])) {
                        i8 = i9 + this.f3077b.length;
                        i7 = this.f3076a;
                    }
                }
                return -1;
            }
            if (i5.a.c(obj, this.f3077b[i8])) {
                i7 = this.f3076a;
                break;
            }
            i8++;
        }
        return i8 - i7;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.f3078c == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i7;
        int e7 = e(this.f3076a + this.f3078c);
        int i8 = this.f3076a;
        if (i8 < e7) {
            length = e7 - 1;
            if (i8 <= length) {
                while (!i5.a.c(obj, this.f3077b[length])) {
                    if (length != i8) {
                        length--;
                    }
                }
                i7 = this.f3076a;
                return length - i7;
            }
            return -1;
        }
        if (i8 > e7) {
            int i9 = e7 - 1;
            while (true) {
                if (-1 >= i9) {
                    Object[] objArr = this.f3077b;
                    i5.a.g("<this>", objArr);
                    length = objArr.length - 1;
                    int i10 = this.f3076a;
                    if (i10 <= length) {
                        while (!i5.a.c(obj, this.f3077b[length])) {
                            if (length != i10) {
                                length--;
                            }
                        }
                        i7 = this.f3076a;
                    }
                } else {
                    if (i5.a.c(obj, this.f3077b[i9])) {
                        length = i9 + this.f3077b.length;
                        i7 = this.f3076a;
                        break;
                    }
                    i9--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int e7;
        i5.a.g("elements", collection);
        boolean z6 = false;
        z6 = false;
        z6 = false;
        if (!isEmpty() && this.f3077b.length != 0) {
            int e8 = e(this.f3076a + this.f3078c);
            int i7 = this.f3076a;
            if (i7 < e8) {
                e7 = i7;
                while (i7 < e8) {
                    Object obj = this.f3077b[i7];
                    if (!collection.contains(obj)) {
                        this.f3077b[e7] = obj;
                        e7++;
                    } else {
                        z6 = true;
                    }
                    i7++;
                }
                l.C1(this.f3077b, null, e7, e8);
            } else {
                int length = this.f3077b.length;
                boolean z7 = false;
                int i8 = i7;
                while (i7 < length) {
                    Object[] objArr = this.f3077b;
                    Object obj2 = objArr[i7];
                    objArr[i7] = null;
                    if (!collection.contains(obj2)) {
                        this.f3077b[i8] = obj2;
                        i8++;
                    } else {
                        z7 = true;
                    }
                    i7++;
                }
                e7 = e(i8);
                for (int i9 = 0; i9 < e8; i9++) {
                    Object[] objArr2 = this.f3077b;
                    Object obj3 = objArr2[i9];
                    objArr2[i9] = null;
                    if (!collection.contains(obj3)) {
                        this.f3077b[e7] = obj3;
                        e7 = d(e7);
                    } else {
                        z7 = true;
                    }
                }
                z6 = z7;
            }
            if (z6) {
                int i10 = e7 - this.f3076a;
                if (i10 < 0) {
                    i10 += this.f3077b.length;
                }
                this.f3078c = i10;
            }
        }
        return z6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int e7;
        i5.a.g("elements", collection);
        boolean z6 = false;
        z6 = false;
        z6 = false;
        if (!isEmpty() && this.f3077b.length != 0) {
            int e8 = e(this.f3076a + this.f3078c);
            int i7 = this.f3076a;
            if (i7 < e8) {
                e7 = i7;
                while (i7 < e8) {
                    Object obj = this.f3077b[i7];
                    if (collection.contains(obj)) {
                        this.f3077b[e7] = obj;
                        e7++;
                    } else {
                        z6 = true;
                    }
                    i7++;
                }
                l.C1(this.f3077b, null, e7, e8);
            } else {
                int length = this.f3077b.length;
                boolean z7 = false;
                int i8 = i7;
                while (i7 < length) {
                    Object[] objArr = this.f3077b;
                    Object obj2 = objArr[i7];
                    objArr[i7] = null;
                    if (collection.contains(obj2)) {
                        this.f3077b[i8] = obj2;
                        i8++;
                    } else {
                        z7 = true;
                    }
                    i7++;
                }
                e7 = e(i8);
                for (int i9 = 0; i9 < e8; i9++) {
                    Object[] objArr2 = this.f3077b;
                    Object obj3 = objArr2[i9];
                    objArr2[i9] = null;
                    if (collection.contains(obj3)) {
                        this.f3077b[e7] = obj3;
                        e7 = d(e7);
                    } else {
                        z7 = true;
                    }
                }
                z6 = z7;
            }
            if (z6) {
                int i10 = e7 - this.f3076a;
                if (i10 < 0) {
                    i10 += this.f3077b.length;
                }
                this.f3078c = i10;
            }
        }
        return z6;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        c cVar = g.Companion;
        int i8 = this.f3078c;
        cVar.getClass();
        c.a(i7, i8);
        int e7 = e(this.f3076a + i7);
        Object[] objArr = this.f3077b;
        Object obj2 = objArr[e7];
        objArr[e7] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.f3078c]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        i5.a.g("array", objArr);
        int length = objArr.length;
        int i7 = this.f3078c;
        if (length < i7) {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i7);
            i5.a.e("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>", newInstance);
            objArr = (Object[]) newInstance;
        }
        int e7 = e(this.f3076a + this.f3078c);
        int i8 = this.f3076a;
        if (i8 < e7) {
            l.z1(this.f3077b, objArr, 0, i8, e7);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.f3077b;
            l.z1(objArr2, objArr, 0, this.f3076a, objArr2.length);
            Object[] objArr3 = this.f3077b;
            l.z1(objArr3, objArr, objArr3.length - this.f3076a, 0, e7);
        }
        int length2 = objArr.length;
        int i9 = this.f3078c;
        if (length2 > i9) {
            objArr[i9] = null;
        }
        return objArr;
    }
}
