package y5;

import java.net.ProtocolException;
import s5.h0;
import s5.q0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {
    public static m a(String str) {
        q0 q0Var;
        int i7;
        String str2;
        i5.a.g("statusLine", str);
        if (o5.k.R(str, "HTTP/1.", false)) {
            i7 = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                q0Var = q0.HTTP_1_0;
            } else {
                if (charAt != 1) {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                q0Var = q0.HTTP_1_1;
            }
        } else {
            if (!o5.k.R(str, "ICY ", false)) {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            q0Var = q0.HTTP_1_0;
            i7 = 4;
        }
        int i8 = i7 + 3;
        if (str.length() < i8) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        try {
            String substring = str.substring(i7, i8);
            i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
            int parseInt = Integer.parseInt(substring);
            if (str.length() <= i8) {
                str2 = h0.FRAGMENT_ENCODE_SET;
            } else {
                if (str.charAt(i8) != ' ') {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                str2 = str.substring(i7 + 4);
                i5.a.f("this as java.lang.String).substring(startIndex)", str2);
            }
            return new m(q0Var, parseInt, str2);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
    }
}
