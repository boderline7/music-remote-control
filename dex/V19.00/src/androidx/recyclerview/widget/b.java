package androidx.recyclerview.widget;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b {

    /* renamed from: d, reason: collision with root package name */
    public final n1 f2379d;

    /* renamed from: a, reason: collision with root package name */
    public final s.e f2376a = new s.e(30, 1);

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f2377b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2378c = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    public int f2381f = 0;

    /* renamed from: e, reason: collision with root package name */
    public final b1 f2380e = new b1(this);

    public b(n1 n1Var) {
        this.f2379d = n1Var;
    }

    public final boolean a(int i7) {
        ArrayList arrayList = this.f2378c;
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            a aVar = (a) arrayList.get(i8);
            int i9 = aVar.f2371a;
            if (i9 == 8) {
                if (f(aVar.f2374d, i8 + 1) == i7) {
                    return true;
                }
            } else if (i9 == 1) {
                int i10 = aVar.f2372b;
                int i11 = aVar.f2374d + i10;
                while (i10 < i11) {
                    if (f(i10, i8 + 1) == i7) {
                        return true;
                    }
                    i10++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public final void b() {
        ArrayList arrayList = this.f2378c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f2379d.a((a) arrayList.get(i7));
        }
        l(arrayList);
        this.f2381f = 0;
    }

    public final void c() {
        b();
        ArrayList arrayList = this.f2377b;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            a aVar = (a) arrayList.get(i7);
            int i8 = aVar.f2371a;
            n1 n1Var = this.f2379d;
            if (i8 == 1) {
                n1Var.a(aVar);
                n1Var.d(aVar.f2372b, aVar.f2374d);
            } else if (i8 == 2) {
                n1Var.a(aVar);
                int i9 = aVar.f2372b;
                int i10 = aVar.f2374d;
                RecyclerView recyclerView = n1Var.f2551a;
                recyclerView.R(i9, i10, true);
                recyclerView.f2314i0 = true;
                recyclerView.f2308f0.f2467c += i10;
            } else if (i8 == 4) {
                n1Var.a(aVar);
                n1Var.c(aVar.f2372b, aVar.f2374d, aVar.f2373c);
            } else if (i8 == 8) {
                n1Var.a(aVar);
                n1Var.e(aVar.f2372b, aVar.f2374d);
            }
        }
        l(arrayList);
        this.f2381f = 0;
    }

    public final void d(a aVar) {
        int i7;
        int i8 = aVar.f2371a;
        if (i8 == 1 || i8 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int m6 = m(aVar.f2372b, i8);
        int i9 = aVar.f2372b;
        int i10 = aVar.f2371a;
        if (i10 == 2) {
            i7 = 0;
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + aVar);
            }
            i7 = 1;
        }
        int i11 = 1;
        for (int i12 = 1; i12 < aVar.f2374d; i12++) {
            int m7 = m((i7 * i12) + aVar.f2372b, aVar.f2371a);
            int i13 = aVar.f2371a;
            if (i13 == 2 ? m7 != m6 : !(i13 == 4 && m7 == m6 + 1)) {
                a h7 = h(aVar.f2373c, i13, m6, i11);
                e(h7, i9);
                k(h7);
                if (aVar.f2371a == 4) {
                    i9 += i11;
                }
                i11 = 1;
                m6 = m7;
            } else {
                i11++;
            }
        }
        Object obj = aVar.f2373c;
        k(aVar);
        if (i11 > 0) {
            a h8 = h(obj, aVar.f2371a, m6, i11);
            e(h8, i9);
            k(h8);
        }
    }

    public final void e(a aVar, int i7) {
        n1 n1Var = this.f2379d;
        n1Var.a(aVar);
        int i8 = aVar.f2371a;
        if (i8 != 2) {
            if (i8 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            n1Var.c(i7, aVar.f2374d, aVar.f2373c);
        } else {
            int i9 = aVar.f2374d;
            RecyclerView recyclerView = n1Var.f2551a;
            recyclerView.R(i7, i9, true);
            recyclerView.f2314i0 = true;
            recyclerView.f2308f0.f2467c += i9;
        }
    }

    public final int f(int i7, int i8) {
        ArrayList arrayList = this.f2378c;
        int size = arrayList.size();
        while (i8 < size) {
            a aVar = (a) arrayList.get(i8);
            int i9 = aVar.f2371a;
            if (i9 == 8) {
                int i10 = aVar.f2372b;
                if (i10 == i7) {
                    i7 = aVar.f2374d;
                } else {
                    if (i10 < i7) {
                        i7--;
                    }
                    if (aVar.f2374d <= i7) {
                        i7++;
                    }
                }
            } else {
                int i11 = aVar.f2372b;
                if (i11 > i7) {
                    continue;
                } else if (i9 == 2) {
                    int i12 = aVar.f2374d;
                    if (i7 < i11 + i12) {
                        return -1;
                    }
                    i7 -= i12;
                } else if (i9 == 1) {
                    i7 += aVar.f2374d;
                }
            }
            i8++;
        }
        return i7;
    }

    public final boolean g() {
        return this.f2377b.size() > 0;
    }

    public final a h(Object obj, int i7, int i8, int i9) {
        a aVar = (a) this.f2376a.a();
        if (aVar != null) {
            aVar.f2371a = i7;
            aVar.f2372b = i8;
            aVar.f2374d = i9;
            aVar.f2373c = obj;
            return aVar;
        }
        a aVar2 = new a();
        aVar2.f2371a = i7;
        aVar2.f2372b = i8;
        aVar2.f2374d = i9;
        aVar2.f2373c = obj;
        return aVar2;
    }

    public final void i(a aVar) {
        this.f2378c.add(aVar);
        int i7 = aVar.f2371a;
        n1 n1Var = this.f2379d;
        if (i7 == 1) {
            n1Var.d(aVar.f2372b, aVar.f2374d);
            return;
        }
        if (i7 == 2) {
            int i8 = aVar.f2372b;
            int i9 = aVar.f2374d;
            RecyclerView recyclerView = n1Var.f2551a;
            recyclerView.R(i8, i9, false);
            recyclerView.f2314i0 = true;
            return;
        }
        if (i7 == 4) {
            n1Var.c(aVar.f2372b, aVar.f2374d, aVar.f2373c);
        } else if (i7 == 8) {
            n1Var.e(aVar.f2372b, aVar.f2374d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + aVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instructions count: 655
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.b.j():void");
    }

    public final void k(a aVar) {
        aVar.f2373c = null;
        this.f2376a.b(aVar);
    }

    public final void l(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            k((a) arrayList.get(i7));
        }
        arrayList.clear();
    }

    public final int m(int i7, int i8) {
        int i9;
        int i10;
        ArrayList arrayList = this.f2378c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            a aVar = (a) arrayList.get(size);
            int i11 = aVar.f2371a;
            if (i11 == 8) {
                int i12 = aVar.f2372b;
                int i13 = aVar.f2374d;
                if (i12 < i13) {
                    i10 = i12;
                    i9 = i13;
                } else {
                    i9 = i12;
                    i10 = i13;
                }
                if (i7 < i10 || i7 > i9) {
                    if (i7 < i12) {
                        if (i8 == 1) {
                            aVar.f2372b = i12 + 1;
                            aVar.f2374d = i13 + 1;
                        } else if (i8 == 2) {
                            aVar.f2372b = i12 - 1;
                            aVar.f2374d = i13 - 1;
                        }
                    }
                } else if (i10 == i12) {
                    if (i8 == 1) {
                        aVar.f2374d = i13 + 1;
                    } else if (i8 == 2) {
                        aVar.f2374d = i13 - 1;
                    }
                    i7++;
                } else {
                    if (i8 == 1) {
                        aVar.f2372b = i12 + 1;
                    } else if (i8 == 2) {
                        aVar.f2372b = i12 - 1;
                    }
                    i7--;
                }
            } else {
                int i14 = aVar.f2372b;
                if (i14 <= i7) {
                    if (i11 == 1) {
                        i7 -= aVar.f2374d;
                    } else if (i11 == 2) {
                        i7 += aVar.f2374d;
                    }
                } else if (i8 == 1) {
                    aVar.f2372b = i14 + 1;
                } else if (i8 == 2) {
                    aVar.f2372b = i14 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            a aVar2 = (a) arrayList.get(size2);
            if (aVar2.f2371a == 8) {
                int i15 = aVar2.f2374d;
                if (i15 == aVar2.f2372b || i15 < 0) {
                    arrayList.remove(size2);
                    k(aVar2);
                }
            } else if (aVar2.f2374d <= 0) {
                arrayList.remove(size2);
                k(aVar2);
            }
        }
        return i7;
    }
}
