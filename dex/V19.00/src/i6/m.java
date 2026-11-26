package i6;

import java.lang.reflect.Method;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    public final Method f6857a;

    /* renamed from: b, reason: collision with root package name */
    public final ThreadMode f6858b;

    /* renamed from: c, reason: collision with root package name */
    public final Class f6859c;

    /* renamed from: d, reason: collision with root package name */
    public final int f6860d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f6861e;

    /* renamed from: f, reason: collision with root package name */
    public String f6862f;

    public m(Method method, Class cls, ThreadMode threadMode, int i7, boolean z6) {
        this.f6857a = method;
        this.f6858b = threadMode;
        this.f6859c = cls;
        this.f6860d = i7;
        this.f6861e = z6;
    }

    public final synchronized void a() {
        if (this.f6862f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f6857a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f6857a.getName());
            sb.append('(');
            sb.append(this.f6859c.getName());
            this.f6862f = sb.toString();
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        a();
        m mVar = (m) obj;
        mVar.a();
        return this.f6862f.equals(mVar.f6862f);
    }

    public final int hashCode() {
        return this.f6857a.hashCode();
    }
}
