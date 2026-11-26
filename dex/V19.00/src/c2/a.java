package c2;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Comparable {

    /* renamed from: e, reason: collision with root package name */
    public static final ConcurrentHashMap f3034e = new ConcurrentHashMap(10000, 0.75f);

    /* renamed from: a, reason: collision with root package name */
    public final String f3035a;

    /* renamed from: b, reason: collision with root package name */
    public final c f3036b;

    /* renamed from: c, reason: collision with root package name */
    public final b f3037c;

    /* renamed from: d, reason: collision with root package name */
    public b f3038d;

    public a(String str, c cVar, b bVar) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        }
        if (cVar == null) {
            throw new NullPointerException("returnType == null");
        }
        this.f3035a = str;
        this.f3036b = cVar;
        this.f3037c = bVar;
        this.f3038d = null;
    }

    public static a b(String str) {
        int i7;
        a aVar = (a) f3034e.get(str);
        if (aVar != null) {
            return aVar;
        }
        int length = str.length();
        if (str.charAt(0) != '(') {
            throw new IllegalArgumentException("bad descriptor");
        }
        int i8 = 0;
        int i9 = 1;
        while (true) {
            if (i9 >= length) {
                i9 = 0;
                break;
            }
            char charAt = str.charAt(i9);
            if (charAt == ')') {
                break;
            }
            if (charAt >= 'A' && charAt <= 'Z') {
                i8++;
            }
            i9++;
        }
        if (i9 == 0 || i9 == length - 1) {
            throw new IllegalArgumentException("bad descriptor");
        }
        if (str.indexOf(41, i9 + 1) != -1) {
            throw new IllegalArgumentException("bad descriptor");
        }
        c[] cVarArr = new c[i8];
        int i10 = 0;
        int i11 = 1;
        while (true) {
            char charAt2 = str.charAt(i11);
            if (charAt2 == ')') {
                String substring = str.substring(i11 + 1);
                ConcurrentHashMap concurrentHashMap = c.f3039f;
                try {
                    c g7 = substring.equals("V") ? c.VOID : c.g(substring);
                    b bVar = new b(i10);
                    for (int i12 = 0; i12 < i10; i12++) {
                        bVar.g(i12, cVarArr[i12]);
                    }
                    return new a(str, g7, bVar);
                } catch (NullPointerException unused) {
                    throw new NullPointerException("descriptor == null");
                }
            }
            int i13 = i11;
            while (charAt2 == '[') {
                i13++;
                charAt2 = str.charAt(i13);
            }
            if (charAt2 == 'L') {
                int indexOf = str.indexOf(59, i13);
                if (indexOf == -1) {
                    throw new IllegalArgumentException("bad descriptor");
                }
                i7 = indexOf + 1;
            } else {
                i7 = i13 + 1;
            }
            cVarArr[i10] = c.g(str.substring(i11, i7));
            i10++;
            i11 = i7;
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(a aVar) {
        if (this == aVar) {
            return 0;
        }
        int compareTo = this.f3036b.f3040a.compareTo(aVar.f3036b.f3040a);
        if (compareTo != 0) {
            return compareTo;
        }
        b bVar = this.f3037c;
        int length = bVar.f6354b.length;
        b bVar2 = aVar.f3037c;
        int length2 = bVar2.f6354b.length;
        int min = Math.min(length, length2);
        for (int i7 = 0; i7 < min; i7++) {
            int compareTo2 = ((c) bVar.f(i7)).f3040a.compareTo(((c) bVar2.f(i7)).f3040a);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        if (length < length2) {
            return -1;
        }
        return length > length2 ? 1 : 0;
    }

    public final b c() {
        if (this.f3038d == null) {
            b bVar = this.f3037c;
            int length = bVar.f6354b.length;
            b bVar2 = new b(length);
            boolean z6 = false;
            for (int i7 = 0; i7 < length; i7++) {
                c cVar = (c) bVar.f(i7);
                int i8 = cVar.f3041b;
                if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 6 || i8 == 8) {
                    cVar = c.INT;
                    z6 = true;
                }
                bVar2.g(i7, cVar);
            }
            if (z6) {
                bVar = bVar2;
            }
            this.f3038d = bVar;
        }
        return this.f3038d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        return this.f3035a.equals(((a) obj).f3035a);
    }

    public final int hashCode() {
        return this.f3035a.hashCode();
    }

    public final String toString() {
        return this.f3035a;
    }
}
