package l6;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d implements ILoggerFactory {

    /* renamed from: a, reason: collision with root package name */
    public boolean f7465a = false;

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f7466b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final LinkedBlockingQueue f7467c = new LinkedBlockingQueue();

    @Override // org.slf4j.ILoggerFactory
    public final synchronized j6.a c(String str) {
        c cVar;
        cVar = (c) this.f7466b.get(str);
        if (cVar == null) {
            cVar = new c(str, this.f7467c, this.f7465a);
            this.f7466b.put(str, cVar);
        }
        return cVar;
    }
}
