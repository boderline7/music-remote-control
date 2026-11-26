package w1;

import com.android.dex.util.ExceptionWithContext;
import java.io.IOException;
import java.io.StringWriter;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends e2.d {

    /* renamed from: c, reason: collision with root package name */
    public final int f9430c;

    public i(int i7, int i8) {
        super(i7);
        this.f9430c = i8;
    }

    public final int i() {
        int length = this.f6354b.length;
        if (length == 0) {
            return 0;
        }
        h hVar = (h) f(length - 1);
        return hVar.b() + hVar.e();
    }

    public final void j(e2.c cVar) {
        int i7 = cVar.f6349c;
        int length = this.f6354b.length;
        if (cVar.d()) {
            boolean z6 = cVar.f6350d;
            for (int i8 = 0; i8 < length; i8++) {
                h hVar = (h) f(i8);
                int b7 = hVar.b() * 2;
                String str = null;
                if (b7 != 0 || z6) {
                    int i9 = cVar.f6353g;
                    int i10 = cVar.f6352f - ((i9 / 2) + ((i9 * 2) + 8));
                    String g7 = hVar.g();
                    if (g7 != null) {
                        String str2 = "  " + hVar.f() + ": ";
                        int length2 = str2.length();
                        int length3 = i10 == 0 ? g7.length() : i10 - length2;
                        StringWriter stringWriter = new StringWriter((str2.length() + g7.length()) * 3);
                        androidx.appcompat.widget.w wVar = new androidx.appcompat.widget.w(stringWriter, length2, length3, h0.FRAGMENT_ENCODE_SET);
                        try {
                            ((e2.e) wVar.f1439f).write(str2);
                            ((e2.e) wVar.f1440g).write(g7);
                            wVar.d();
                            str = stringWriter.toString();
                        } catch (IOException e7) {
                            throw new RuntimeException("shouldn't happen", e7);
                        }
                    }
                }
                if (str != null) {
                    cVar.b(b7, str);
                } else if (b7 != 0) {
                    cVar.b(b7, h0.FRAGMENT_ENCODE_SET);
                }
            }
        }
        for (int i11 = 0; i11 < length; i11++) {
            h hVar2 = (h) f(i11);
            try {
                hVar2.m(cVar);
            } catch (RuntimeException e8) {
                throw ExceptionWithContext.a("...while writing " + hVar2, e8);
            }
        }
        int i12 = (cVar.f6349c - i7) / 2;
        if (i12 == i()) {
            return;
        }
        throw new RuntimeException("write length mismatch; expected " + i() + " but actually wrote " + i12);
    }
}
