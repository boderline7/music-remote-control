package y1;

import com.android.dex.DexIndexOverflowException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class t extends h0 {
    @Override // y1.h0
    public final void k() {
        int i7 = 0;
        if (c().size() <= 65536) {
            Iterator it = c().iterator();
            while (it.hasNext()) {
                ((s) it.next()).g(i7);
                i7++;
            }
            return;
        }
        TreeMap treeMap = new TreeMap();
        Iterator it2 = c().iterator();
        while (it2.hasNext()) {
            String str = ((s) it2.next()).f10100b.g().f2940a;
            int lastIndexOf = str.lastIndexOf(47);
            String replace = lastIndexOf == -1 ? "default" : str.substring(str.lastIndexOf(91) + 2, lastIndexOf).replace('/', '.');
            AtomicInteger atomicInteger = (AtomicInteger) treeMap.get(replace);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger();
                treeMap.put(replace, atomicInteger);
            }
            atomicInteger.incrementAndGet();
        }
        Formatter formatter = new Formatter();
        try {
            formatter.format("Too many %1$s references to fit in one dex file: %2$d; max is %3$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.%nReferences by package:", this instanceof w ? "method" : "field", Integer.valueOf(c().size()), 65536);
            for (Map.Entry entry : treeMap.entrySet()) {
                formatter.format("%n%6d %s", Integer.valueOf(((AtomicInteger) entry.getValue()).get()), entry.getKey());
            }
            String formatter2 = formatter.toString();
            formatter.close();
            throw new DexIndexOverflowException(formatter2, null);
        } catch (Throwable th) {
            formatter.close();
            throw th;
        }
    }
}
