package f6;

import c5.r;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import o5.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements HostnameVerifier {
    public static final f INSTANCE = new f();

    public static ArrayList a(X509Certificate x509Certificate) {
        List b7 = b(x509Certificate, 7);
        List b8 = b(x509Certificate, 2);
        i5.a.g("<this>", b7);
        i5.a.g("elements", b8);
        ArrayList arrayList = new ArrayList(b8.size() + b7.size());
        arrayList.addAll(b7);
        arrayList.addAll(b8);
        return arrayList;
    }

    public static List b(X509Certificate x509Certificate, int i7) {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return r.INSTANCE;
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && i5.a.c(list.get(0), Integer.valueOf(i7)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return r.INSTANCE;
        }
    }

    public static boolean c(String str) {
        int i7;
        int length = str.length();
        int length2 = str.length();
        if (length2 < 0) {
            throw new IllegalArgumentException(a.a.e("endIndex < beginIndex: ", length2, " < 0").toString());
        }
        if (length2 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + length2 + " > " + str.length()).toString());
        }
        long j7 = 0;
        int i8 = 0;
        while (i8 < length2) {
            char charAt = str.charAt(i8);
            if (charAt < 128) {
                j7++;
            } else {
                if (charAt < 2048) {
                    i7 = 2;
                } else if (charAt < 55296 || charAt > 57343) {
                    i7 = 3;
                } else {
                    int i9 = i8 + 1;
                    char charAt2 = i9 < length2 ? str.charAt(i9) : (char) 0;
                    if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                        j7++;
                        i8 = i9;
                    } else {
                        j7 += 4;
                        i8 += 2;
                    }
                }
                j7 += i7;
            }
            i8++;
        }
        return length == ((int) j7);
    }

    public static boolean d(String str, X509Certificate x509Certificate) {
        int length;
        i5.a.g("host", str);
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        o5.e eVar = t5.b.f9093b;
        eVar.getClass();
        if (eVar.f8062a.matcher(str).matches()) {
            String w6 = i5.a.w(str);
            List b7 = b(x509Certificate, 7);
            if (!(b7 instanceof Collection) || !b7.isEmpty()) {
                Iterator it = b7.iterator();
                while (it.hasNext()) {
                    if (i5.a.c(w6, i5.a.w((String) it.next()))) {
                        return true;
                    }
                }
            }
        } else {
            if (c(str)) {
                Locale locale = Locale.US;
                i5.a.f("US", locale);
                str = str.toLowerCase(locale);
                i5.a.f("this as java.lang.String).toLowerCase(locale)", str);
            }
            List<String> b8 = b(x509Certificate, 2);
            if (!(b8 instanceof Collection) || !b8.isEmpty()) {
                for (String str2 : b8) {
                    INSTANCE.getClass();
                    if (str.length() != 0 && !k.R(str, ".", false) && !k.B(str, "..") && str2 != null && str2.length() != 0 && !k.R(str2, ".", false) && !k.B(str2, "..")) {
                        String concat = !k.B(str, ".") ? str.concat(".") : str;
                        if (!k.B(str2, ".")) {
                            str2 = str2.concat(".");
                        }
                        if (c(str2)) {
                            Locale locale2 = Locale.US;
                            i5.a.f("US", locale2);
                            str2 = str2.toLowerCase(locale2);
                            i5.a.f("this as java.lang.String).toLowerCase(locale)", str2);
                        }
                        if (!k.A(str2, "*")) {
                            if (i5.a.c(concat, str2)) {
                                return true;
                            }
                        } else if (k.R(str2, "*.", false) && k.F(str2, '*', 1, false, 4) == -1 && concat.length() >= str2.length() && !i5.a.c("*.", str2)) {
                            String substring = str2.substring(1);
                            i5.a.f("this as java.lang.String).substring(startIndex)", substring);
                            if (k.B(concat, substring) && ((length = concat.length() - substring.length()) <= 0 || k.J(concat, '.', length - 1, 4) == -1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        i5.a.g("host", str);
        i5.a.g("session", sSLSession);
        if (!c(str)) {
            return false;
        }
        try {
            Certificate certificate = sSLSession.getPeerCertificates()[0];
            i5.a.e("null cannot be cast to non-null type java.security.cert.X509Certificate", certificate);
            return d(str, (X509Certificate) certificate);
        } catch (SSLException unused) {
            return false;
        }
    }
}
