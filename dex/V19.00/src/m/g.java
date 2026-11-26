package m;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class g implements Iterable {

    /* renamed from: a, reason: collision with root package name */
    public c f7482a;

    /* renamed from: b, reason: collision with root package name */
    public c f7483b;

    /* renamed from: c, reason: collision with root package name */
    public final WeakHashMap f7484c = new WeakHashMap();

    /* renamed from: d, reason: collision with root package name */
    public int f7485d = 0;

    public c a(Object obj) {
        c cVar = this.f7482a;
        while (cVar != null && !cVar.f7473a.equals(obj)) {
            cVar = cVar.f7475c;
        }
        return cVar;
    }

    public Object b(Object obj) {
        c a7 = a(obj);
        if (a7 == null) {
            return null;
        }
        this.f7485d--;
        WeakHashMap weakHashMap = this.f7484c;
        if (!weakHashMap.isEmpty()) {
            Iterator it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                ((f) it.next()).a(a7);
            }
        }
        c cVar = a7.f7476d;
        if (cVar != null) {
            cVar.f7475c = a7.f7475c;
        } else {
            this.f7482a = a7.f7475c;
        }
        c cVar2 = a7.f7475c;
        if (cVar2 != null) {
            cVar2.f7476d = cVar;
        } else {
            this.f7483b = cVar;
        }
        a7.f7475c = null;
        a7.f7476d = null;
        return a7.f7474b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0048, code lost:
    
        if (r3.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0050, code lost:
    
        if (((m.e) r7).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L4
            return r0
        L4:
            boolean r1 = r7 instanceof m.g
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            m.g r7 = (m.g) r7
            int r1 = r6.f7485d
            int r3 = r7.f7485d
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L1b:
            r3 = r1
            m.e r3 = (m.e) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            r4 = r7
            m.e r4 = (m.e) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L44
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L3b
            if (r4 != 0) goto L43
        L3b:
            if (r3 == 0) goto L1b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L53
            m.e r7 = (m.e) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m.g.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Iterator it = iterator();
        int i7 = 0;
        while (true) {
            e eVar = (e) it;
            if (!eVar.hasNext()) {
                return i7;
            }
            i7 += ((Map.Entry) eVar.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        b bVar = new b(this.f7482a, this.f7483b, 0);
        this.f7484c.put(bVar, Boolean.FALSE);
        return bVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (true) {
            e eVar = (e) it;
            if (!eVar.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) eVar.next()).toString());
            if (eVar.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
