package i5;

import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h implements Serializable {
    public final String toString() {
        k.f6813a.getClass();
        String obj = getClass().getGenericInterfaces()[0].toString();
        if (obj.startsWith("kotlin.jvm.functions.")) {
            obj = obj.substring(21);
        }
        a.f("renderLambdaToString(this)", obj);
        return obj;
    }
}
