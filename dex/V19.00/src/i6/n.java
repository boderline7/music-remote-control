package i6;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f6863a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public final HashMap f6864b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f6865c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public final StringBuilder f6866d = new StringBuilder(128);

    /* renamed from: e, reason: collision with root package name */
    public Class f6867e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f6868f;

    public final boolean a(Method method, Class cls) {
        StringBuilder sb = this.f6866d;
        sb.setLength(0);
        sb.append(method.getName());
        sb.append('>');
        sb.append(cls.getName());
        String sb2 = sb.toString();
        Class<?> declaringClass = method.getDeclaringClass();
        HashMap hashMap = this.f6865c;
        Class cls2 = (Class) hashMap.put(sb2, declaringClass);
        if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
            return true;
        }
        hashMap.put(sb2, cls2);
        return false;
    }
}
