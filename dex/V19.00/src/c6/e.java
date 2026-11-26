package c6;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import n1.z;
import s5.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {
    public static final e INSTANCE = new e();

    /* renamed from: a, reason: collision with root package name */
    public static final CopyOnWriteArraySet f3082a = new CopyOnWriteArraySet();

    /* renamed from: b, reason: collision with root package name */
    public static final Map f3083b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r22 = o0.class.getPackage();
        String name = r22 != null ? r22.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(o0.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(a6.h.class.getName(), "okhttp.Http2");
        linkedHashMap.put(w5.g.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        int size = linkedHashMap.size();
        f3083b = size != 0 ? size != 1 ? new LinkedHashMap(linkedHashMap) : z.i1(linkedHashMap) : c5.l.B1();
    }
}
