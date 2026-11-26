package c5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l extends z {
    public static byte[] A1(byte[] bArr, int i7, int i8) {
        i5.a.g("<this>", bArr);
        int length = bArr.length;
        if (i8 <= length) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i7, i8);
            i5.a.f("copyOfRange(this, fromIndex, toIndex)", copyOfRange);
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i8 + ") is greater than size (" + length + ").");
    }

    public static s B1() {
        s sVar = s.INSTANCE;
        i5.a.e("null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>", sVar);
        return sVar;
    }

    public static void C1(Object[] objArr, a1.b bVar, int i7, int i8) {
        i5.a.g("<this>", objArr);
        Arrays.fill(objArr, i7, i8, bVar);
    }

    public static char D1(char[] cArr) {
        i5.a.g("<this>", cArr);
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static Map E1(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return B1();
        }
        if (size != 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(z.I0(arrayList.size()));
            F1(arrayList, linkedHashMap);
            return linkedHashMap;
        }
        b5.a aVar = (b5.a) arrayList.get(0);
        i5.a.g("pair", aVar);
        Map singletonMap = Collections.singletonMap(aVar.f2975a, aVar.f2976b);
        i5.a.f("singletonMap(pair.first, pair.second)", singletonMap);
        return singletonMap;
    }

    public static final void F1(ArrayList arrayList, LinkedHashMap linkedHashMap) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b5.a aVar = (b5.a) it.next();
            linkedHashMap.put(aVar.f2975a, aVar.f2976b);
        }
    }

    public static int x1(Iterable iterable) {
        i5.a.g("<this>", iterable);
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }

    public static void y1(int i7, int i8, int i9, byte[] bArr, byte[] bArr2) {
        i5.a.g("<this>", bArr);
        i5.a.g("destination", bArr2);
        System.arraycopy(bArr, i8, bArr2, i7, i9 - i8);
    }

    public static final void z1(Object[] objArr, Object[] objArr2, int i7, int i8, int i9) {
        i5.a.g("<this>", objArr);
        i5.a.g("destination", objArr2);
        System.arraycopy(objArr, i8, objArr2, i7, i9 - i8);
    }
}
