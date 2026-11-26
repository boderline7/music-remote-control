package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class e1 {
    private final Map<String, Object> mBagOfTags = new HashMap();
    private final Set<Closeable> mCloseables = new LinkedHashSet();
    private volatile boolean mCleared = false;

    public static void a(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    public void addCloseable(Closeable closeable) {
        if (this.mCleared) {
            a(closeable);
            return;
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                this.mCloseables.add(closeable);
            }
        }
    }

    public final void clear() {
        this.mCleared = true;
        Map<String, Object> map = this.mBagOfTags;
        if (map != null) {
            synchronized (map) {
                try {
                    Iterator<Object> it = this.mBagOfTags.values().iterator();
                    while (it.hasNext()) {
                        a(it.next());
                    }
                } finally {
                }
            }
        }
        Set<Closeable> set = this.mCloseables;
        if (set != null) {
            synchronized (set) {
                try {
                    Iterator<Closeable> it2 = this.mCloseables.iterator();
                    while (it2.hasNext()) {
                        a(it2.next());
                    }
                } finally {
                }
            }
            this.mCloseables.clear();
        }
        onCleared();
    }

    public <T> T getTag(String str) {
        T t6;
        Map<String, Object> map = this.mBagOfTags;
        if (map == null) {
            return null;
        }
        synchronized (map) {
            t6 = (T) this.mBagOfTags.get(str);
        }
        return t6;
    }

    public void onCleared() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T setTagIfAbsent(String str, T t6) {
        Object obj;
        synchronized (this.mBagOfTags) {
            try {
                obj = this.mBagOfTags.get(str);
                if (obj == 0) {
                    this.mBagOfTags.put(str, t6);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj != 0) {
            t6 = obj;
        }
        if (this.mCleared) {
            a(t6);
        }
        return t6;
    }
}
