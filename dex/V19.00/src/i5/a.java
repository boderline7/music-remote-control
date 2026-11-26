package i5;

import a6.w;
import c5.r;
import com.yalantis.ucrop.view.CropImageView;
import g6.p;
import g6.x;
import g6.z;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import kotlin.UninitializedPropertyAccessException;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public static final Object[] f6799a = new Object[0];

    public static final void a(w5.a aVar, w5.c cVar, String str) {
        w5.g.Companion.getClass();
        Logger logger = w5.g.f9512h;
        StringBuilder sb = new StringBuilder();
        sb.append(cVar.f9505b);
        sb.append(' ');
        String format = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        f("format(format, *args)", format);
        sb.append(format);
        sb.append(": ");
        sb.append(aVar.f9498a);
        logger.fine(sb.toString());
    }

    public static void b(StringBuilder sb, Object obj, h5.l lVar) {
        if (lVar != null) {
            sb.append((CharSequence) lVar.b(obj));
            return;
        }
        if (obj == null || (obj instanceof CharSequence)) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void d(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        o(a.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void e(String str, Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        o(a.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void f(String str, Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        o(a.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void g(String str, Object obj) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = a.class.getName();
            int i7 = 0;
            while (!stackTrace[i7].getClassName().equals(name)) {
                i7++;
            }
            while (stackTrace[i7].getClassName().equals(name)) {
                i7++;
            }
            StackTraceElement stackTraceElement = stackTrace[i7];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            o(a.class.getName(), nullPointerException);
            throw nullPointerException;
        }
    }

    public static void h(int i7) {
        l5.e eVar = new l5.e(2, 36, 1);
        if (2 > i7 || i7 > eVar.f7451b) {
            throw new IllegalArgumentException("radix " + i7 + " was not in valid range " + new l5.e(2, 36, 1));
        }
    }

    public static int i(int i7, int i8) {
        if (i7 < i8) {
            return -1;
        }
        return i7 == i8 ? 0 : 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00d5, code lost:
    
        if (r7 == r0) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
    
        if (r8 != (-1)) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d9, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00da, code lost:
    
        r1 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r1, r1);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ec, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a7  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.net.InetAddress j(int r17, int r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i5.a.j(int, int, java.lang.String):java.net.InetAddress");
    }

    public static final boolean k(char c7, char c8, boolean z6) {
        if (c7 == c8) {
            return true;
        }
        if (!z6) {
            return false;
        }
        char upperCase = Character.toUpperCase(c7);
        char upperCase2 = Character.toUpperCase(c8);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final String l(long j7) {
        String str;
        if (j7 <= -999500000) {
            str = ((j7 - 500000000) / w.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        } else if (j7 <= -999500) {
            str = ((j7 - 500000) / 1000000) + " ms";
        } else if (j7 <= 0) {
            str = ((j7 - CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION) / 1000) + " µs";
        } else if (j7 < 999500) {
            str = ((j7 + CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION) / 1000) + " µs";
        } else if (j7 < 999500000) {
            str = ((j7 + 500000) / 1000000) + " ms";
        } else {
            str = ((j7 + 500000000) / w.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        }
        String format = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        f("format(format, *args)", format);
        return format;
    }

    public static final boolean m(AssertionError assertionError) {
        String message;
        Logger logger = p.f6587a;
        return (assertionError.getCause() == null || (message = assertionError.getMessage()) == null || !o5.k.A(message, "getsockname failed")) ? false : true;
    }

    public static boolean n() {
        try {
            return Class.forName("android.os.Looper").getDeclaredMethod("getMainLooper", new Class[0]).invoke(null, new Object[0]) != null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public static void o(String str, RuntimeException runtimeException) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i7 = -1;
        for (int i8 = 0; i8 < length; i8++) {
            if (str.equals(stackTrace[i8].getClassName())) {
                i7 = i8;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i7 + 1, length));
    }

    public static final int p(x xVar, int i7) {
        int i8;
        g("<this>", xVar);
        int i9 = i7 + 1;
        int length = xVar.f6606d.length;
        int[] iArr = xVar.f6607e;
        g("<this>", iArr);
        int i10 = length - 1;
        int i11 = 0;
        while (true) {
            if (i11 <= i10) {
                i8 = (i11 + i10) >>> 1;
                int i12 = iArr[i8];
                if (i12 >= i9) {
                    if (i12 <= i9) {
                        break;
                    }
                    i10 = i8 - 1;
                } else {
                    i11 = i8 + 1;
                }
            } else {
                i8 = (-i11) - 1;
                break;
            }
        }
        return i8 >= 0 ? i8 : ~i8;
    }

    public static final g6.d q(Socket socket) {
        Logger logger = p.f6587a;
        z zVar = new z(socket);
        OutputStream outputStream = socket.getOutputStream();
        f("getOutputStream(...)", outputStream);
        return new g6.d(zVar, new g6.d(outputStream, zVar));
    }

    public static final g6.e r(Socket socket) {
        Logger logger = p.f6587a;
        z zVar = new z(socket);
        InputStream inputStream = socket.getInputStream();
        f("getInputStream(...)", inputStream);
        return new g6.e(zVar, new g6.e(inputStream, zVar));
    }

    public static l5.b s(l5.e eVar, int i7) {
        g("<this>", eVar);
        boolean z6 = i7 > 0;
        Integer valueOf = Integer.valueOf(i7);
        g("step", valueOf);
        if (!z6) {
            throw new IllegalArgumentException("Step must be positive, was: " + valueOf + '.');
        }
        l5.a aVar = l5.b.Companion;
        if (eVar.f7452c <= 0) {
            i7 = -i7;
        }
        aVar.getClass();
        return new l5.b(eVar.f7450a, eVar.f7451b, i7);
    }

    public static void t(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(a.a.h("lateinit property ", str, " has not been initialized"));
        o(a.class.getName(), uninitializedPropertyAccessException);
        throw uninitializedPropertyAccessException;
    }

    public static final Object[] u(Collection collection) {
        g("collection", collection);
        int size = collection.size();
        Object[] objArr = f6799a;
        if (size == 0) {
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return objArr;
        }
        Object[] objArr2 = new Object[size];
        int i7 = 0;
        while (true) {
            int i8 = i7 + 1;
            objArr2[i7] = it.next();
            if (i8 >= objArr2.length) {
                if (!it.hasNext()) {
                    return objArr2;
                }
                int i9 = ((i8 * 3) + 1) >>> 1;
                if (i9 <= i8) {
                    i9 = 2147483645;
                    if (i8 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArr2 = Arrays.copyOf(objArr2, i9);
                f("copyOf(result, newSize)", objArr2);
            } else if (!it.hasNext()) {
                Object[] copyOf = Arrays.copyOf(objArr2, i8);
                f("copyOf(result, size)", copyOf);
                return copyOf;
            }
            i7 = i8;
        }
    }

    public static final Object[] v(Collection collection, Object[] objArr) {
        Object[] objArr2;
        g("collection", collection);
        objArr.getClass();
        int size = collection.size();
        int i7 = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArr2 = objArr;
        } else {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            e("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>", newInstance);
            objArr2 = (Object[]) newInstance;
        }
        while (true) {
            int i8 = i7 + 1;
            objArr2[i7] = it.next();
            if (i8 >= objArr2.length) {
                if (!it.hasNext()) {
                    return objArr2;
                }
                int i9 = ((i8 * 3) + 1) >>> 1;
                if (i9 <= i8) {
                    i9 = 2147483645;
                    if (i8 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArr2 = Arrays.copyOf(objArr2, i9);
                f("copyOf(result, newSize)", objArr2);
            } else if (!it.hasNext()) {
                if (objArr2 == objArr) {
                    objArr[i8] = null;
                    return objArr;
                }
                Object[] copyOf = Arrays.copyOf(objArr2, i8);
                f("copyOf(result, size)", copyOf);
                return copyOf;
            }
            i7 = i8;
        }
    }

    public static final String w(String str) {
        g("<this>", str);
        int i7 = 0;
        int i8 = -1;
        if (!o5.k.A(str, ":")) {
            try {
                String ascii = IDN.toASCII(str);
                f("toASCII(host)", ascii);
                Locale locale = Locale.US;
                f("US", locale);
                String lowerCase = ascii.toLowerCase(locale);
                f("this as java.lang.String).toLowerCase(locale)", lowerCase);
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i9 = 0; i9 < length; i9++) {
                    char charAt = lowerCase.charAt(i9);
                    if (i(charAt, 31) <= 0 || i(charAt, z1.c.NEG_FLOAT) >= 0 || o5.k.F(" #%/:?@[\\]", charAt, 0, false, 6) != -1) {
                        return null;
                    }
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress j7 = (o5.k.R(str, "[", false) && o5.k.B(str, "]")) ? j(1, str.length() - 1, str) : j(0, str.length(), str);
        if (j7 == null) {
            return null;
        }
        byte[] address = j7.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return j7.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < address.length) {
            int i12 = i10;
            while (i12 < 16 && address[i12] == 0 && address[i12 + 1] == 0) {
                i12 += 2;
            }
            int i13 = i12 - i10;
            if (i13 > i11 && i13 >= 4) {
                i8 = i10;
                i11 = i13;
            }
            i10 = i12 + 2;
        }
        g6.h hVar = new g6.h();
        while (i7 < address.length) {
            if (i7 == i8) {
                hVar.N(58);
                i7 += i11;
                if (i7 == 16) {
                    hVar.N(58);
                }
            } else {
                if (i7 > 0) {
                    hVar.N(58);
                }
                byte b7 = address[i7];
                byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
                hVar.O(((b7 & 255) << 8) | (address[i7 + 1] & 255));
                i7 += 2;
            }
        }
        return hVar.H(hVar.f6573b, o5.a.UTF_8);
    }

    public static String x(String str) {
        List list;
        g("<this>", str);
        if (!(!o5.k.I("|"))) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
        }
        o5.k.O(0);
        List asList = Arrays.asList("\r\n", "\n", "\r");
        f("asList(this)", asList);
        o5.c cVar = new o5.c(str, 0, 0, new o5.j(asList, false, 1));
        c5.a aVar = new c5.a(2, str);
        Iterator it = cVar.iterator();
        if (it.hasNext()) {
            Object b7 = aVar.b(it.next());
            if (it.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(b7);
                while (it.hasNext()) {
                    arrayList.add(aVar.b(it.next()));
                }
                list = arrayList;
            } else {
                list = n1.z.G0(b7);
            }
        } else {
            list = r.INSTANCE;
        }
        int length = str.length();
        list.size();
        o5.f fVar = o5.f.INSTANCE;
        int m02 = n1.z.m0(list);
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        for (Object obj : list) {
            int i8 = i7 + 1;
            if (i7 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            String str2 = (String) obj;
            String str3 = null;
            if ((i7 == 0 || i7 == m02) && o5.k.I(str2)) {
                str2 = null;
            } else {
                int length2 = str2.length();
                int i9 = 0;
                while (true) {
                    if (i9 >= length2) {
                        i9 = -1;
                        break;
                    }
                    char charAt = str2.charAt(i9);
                    if (!(Character.isWhitespace(charAt) || Character.isSpaceChar(charAt))) {
                        break;
                    }
                    i9++;
                }
                if (i9 != -1 && o5.k.Q(str2, "|", i9, false)) {
                    str3 = str2.substring(i9 + 1);
                    f("this as java.lang.String).substring(startIndex)", str3);
                }
                if (str3 != null) {
                    str2 = (String) fVar.b(str3);
                }
            }
            if (str2 != null) {
                arrayList2.add(str2);
            }
            i7 = i8;
        }
        StringBuilder sb = new StringBuilder(length);
        c5.p.H1(arrayList2, sb, "\n", h0.FRAGMENT_ENCODE_SET, h0.FRAGMENT_ENCODE_SET, -1, "...", null);
        String sb2 = sb.toString();
        f("mapIndexedNotNull { inde…\"\\n\")\n        .toString()", sb2);
        return sb2;
    }

    public static l5.e y(int i7, int i8) {
        if (i8 > Integer.MIN_VALUE) {
            return new l5.e(i7, i8 - 1, 1);
        }
        l5.e.Companion.getClass();
        return l5.e.f7457d;
    }
}
