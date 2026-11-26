package b6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    public final List f2991a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2992b;

    /* renamed from: c, reason: collision with root package name */
    public String f2993c;

    public k(ArrayList arrayList) {
        this.f2991a = arrayList;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        i5.a.g("proxy", obj);
        i5.a.g("method", method);
        if (objArr == null) {
            objArr = new Object[0];
        }
        String name = method.getName();
        Class<?> returnType = method.getReturnType();
        if (i5.a.c(name, "supports") && i5.a.c(Boolean.TYPE, returnType)) {
            return Boolean.TRUE;
        }
        if (i5.a.c(name, "unsupported") && i5.a.c(Void.TYPE, returnType)) {
            this.f2992b = true;
            return null;
        }
        boolean c7 = i5.a.c(name, "protocols");
        List list = this.f2991a;
        if (c7 && objArr.length == 0) {
            return list;
        }
        if ((i5.a.c(name, "selectProtocol") || i5.a.c(name, "select")) && i5.a.c(String.class, returnType) && objArr.length == 1) {
            Object obj2 = objArr[0];
            if (obj2 instanceof List) {
                i5.a.e("null cannot be cast to non-null type kotlin.collections.List<*>", obj2);
                List list2 = (List) obj2;
                int size = list2.size();
                if (size >= 0) {
                    int i7 = 0;
                    while (true) {
                        Object obj3 = list2.get(i7);
                        i5.a.e("null cannot be cast to non-null type kotlin.String", obj3);
                        String str = (String) obj3;
                        if (!list.contains(str)) {
                            if (i7 == size) {
                                break;
                            }
                            i7++;
                        } else {
                            this.f2993c = str;
                            return str;
                        }
                    }
                }
                String str2 = (String) list.get(0);
                this.f2993c = str2;
                return str2;
            }
        }
        if ((!i5.a.c(name, "protocolSelected") && !i5.a.c(name, "selected")) || objArr.length != 1) {
            return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
        }
        Object obj4 = objArr[0];
        i5.a.e("null cannot be cast to non-null type kotlin.String", obj4);
        this.f2993c = (String) obj4;
        return null;
    }
}
