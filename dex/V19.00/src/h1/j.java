package h1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j {

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f6683a = {112, 114, 111, 0};

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f6684b = {112, 114, 109, 0};

    public static byte[] a(c[] cVarArr, byte[] bArr) {
        int i7 = 0;
        int i8 = 0;
        for (c cVar : cVarArr) {
            i8 += ((((cVar.f6676g * 2) + 7) & (-8)) / 8) + (cVar.f6674e * 2) + b(cVar.f6670a, cVar.f6671b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + cVar.f6675f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i8);
        if (Arrays.equals(bArr, o.f6694c)) {
            int length = cVarArr.length;
            while (i7 < length) {
                c cVar2 = cVarArr[i7];
                l(byteArrayOutputStream, cVar2, b(cVar2.f6670a, cVar2.f6671b, bArr));
                n(byteArrayOutputStream, cVar2);
                k(byteArrayOutputStream, cVar2);
                m(byteArrayOutputStream, cVar2);
                i7++;
            }
        } else {
            for (c cVar3 : cVarArr) {
                l(byteArrayOutputStream, cVar3, b(cVar3.f6670a, cVar3.f6671b, bArr));
            }
            int length2 = cVarArr.length;
            while (i7 < length2) {
                c cVar4 = cVarArr[i7];
                n(byteArrayOutputStream, cVar4);
                k(byteArrayOutputStream, cVar4);
                m(byteArrayOutputStream, cVar4);
                i7++;
            }
        }
        if (byteArrayOutputStream.size() == i8) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + i8);
    }

    public static String b(String str, String str2, byte[] bArr) {
        byte[] bArr2 = o.f6696e;
        boolean equals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = o.f6695d;
        String str3 = (equals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        return a.a.i(a.a.j(str), (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!", str2);
    }

    public static int c(int i7, int i8, int i9) {
        if (i7 == 1) {
            throw new IllegalStateException("HOT methods are not stored in the bitmap");
        }
        if (i7 == 2) {
            return i8;
        }
        if (i7 == 4) {
            return i8 + i9;
        }
        throw new IllegalStateException(a.a.d("Unexpected flag: ", i7));
    }

    public static int[] d(ByteArrayInputStream byteArrayInputStream, int i7) {
        int[] iArr = new int[i7];
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            i8 += (int) z.Z0(byteArrayInputStream, 2);
            iArr[i9] = i8;
        }
        return iArr;
    }

    public static c[] e(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, c[] cVarArr) {
        byte[] bArr3 = o.f6697f;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, o.f6698g)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int Z0 = (int) z.Z0(fileInputStream, 2);
            byte[] V0 = z.V0(fileInputStream, (int) z.Z0(fileInputStream, 4), (int) z.Z0(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(V0);
            try {
                c[] g7 = g(byteArrayInputStream, bArr2, Z0, cVarArr);
                byteArrayInputStream.close();
                return g7;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(o.f6692a, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int Z02 = (int) z.Z0(fileInputStream, 1);
        byte[] V02 = z.V0(fileInputStream, (int) z.Z0(fileInputStream, 4), (int) z.Z0(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(V02);
        try {
            c[] f7 = f(byteArrayInputStream2, Z02, cVarArr);
            byteArrayInputStream2.close();
            return f7;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static c[] f(ByteArrayInputStream byteArrayInputStream, int i7, c[] cVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i7 != cVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i7];
        int[] iArr = new int[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int Z0 = (int) z.Z0(byteArrayInputStream, 2);
            iArr[i8] = (int) z.Z0(byteArrayInputStream, 2);
            strArr[i8] = new String(z.U0(byteArrayInputStream, Z0), StandardCharsets.UTF_8);
        }
        for (int i9 = 0; i9 < i7; i9++) {
            c cVar = cVarArr[i9];
            if (!cVar.f6671b.equals(strArr[i9])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i10 = iArr[i9];
            cVar.f6674e = i10;
            cVar.f6677h = d(byteArrayInputStream, i10);
        }
        return cVarArr;
    }

    public static c[] g(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i7, c[] cVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        if (i7 != cVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i8 = 0; i8 < i7; i8++) {
            z.Z0(byteArrayInputStream, 2);
            String str = new String(z.U0(byteArrayInputStream, (int) z.Z0(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long Z0 = z.Z0(byteArrayInputStream, 4);
            int Z02 = (int) z.Z0(byteArrayInputStream, 2);
            c cVar = null;
            if (cVarArr.length > 0) {
                int indexOf = str.indexOf("!");
                if (indexOf < 0) {
                    indexOf = str.indexOf(":");
                }
                String substring = indexOf > 0 ? str.substring(indexOf + 1) : str;
                int i9 = 0;
                while (true) {
                    if (i9 >= cVarArr.length) {
                        break;
                    }
                    if (cVarArr[i9].f6671b.equals(substring)) {
                        cVar = cVarArr[i9];
                        break;
                    }
                    i9++;
                }
            }
            if (cVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            cVar.f6673d = Z0;
            int[] d7 = d(byteArrayInputStream, Z02);
            if (Arrays.equals(bArr, o.f6696e)) {
                cVar.f6674e = Z02;
                cVar.f6677h = d7;
            }
        }
        return cVarArr;
    }

    public static c[] h(FileInputStream fileInputStream, byte[] bArr, String str) {
        if (!Arrays.equals(bArr, o.f6693b)) {
            throw new IllegalStateException("Unsupported version");
        }
        int Z0 = (int) z.Z0(fileInputStream, 1);
        byte[] V0 = z.V0(fileInputStream, (int) z.Z0(fileInputStream, 4), (int) z.Z0(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(V0);
        try {
            c[] i7 = i(byteArrayInputStream, str, Z0);
            byteArrayInputStream.close();
            return i7;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static c[] i(ByteArrayInputStream byteArrayInputStream, String str, int i7) {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new c[0];
        }
        c[] cVarArr = new c[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int Z0 = (int) z.Z0(byteArrayInputStream, 2);
            int Z02 = (int) z.Z0(byteArrayInputStream, 2);
            cVarArr[i8] = new c(str, new String(z.U0(byteArrayInputStream, Z0), StandardCharsets.UTF_8), z.Z0(byteArrayInputStream, 4), Z02, (int) z.Z0(byteArrayInputStream, 4), (int) z.Z0(byteArrayInputStream, 4), new int[Z02], new TreeMap());
        }
        for (int i9 = 0; i9 < i7; i9++) {
            c cVar = cVarArr[i9];
            int available = byteArrayInputStream.available() - cVar.f6675f;
            int i10 = 0;
            while (true) {
                int available2 = byteArrayInputStream.available();
                treeMap = cVar.f6678i;
                if (available2 <= available) {
                    break;
                }
                i10 += (int) z.Z0(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(i10), 1);
                for (int Z03 = (int) z.Z0(byteArrayInputStream, 2); Z03 > 0; Z03--) {
                    z.Z0(byteArrayInputStream, 2);
                    int Z04 = (int) z.Z0(byteArrayInputStream, 1);
                    if (Z04 != 6 && Z04 != 7) {
                        while (Z04 > 0) {
                            z.Z0(byteArrayInputStream, 1);
                            for (int Z05 = (int) z.Z0(byteArrayInputStream, 1); Z05 > 0; Z05--) {
                                z.Z0(byteArrayInputStream, 2);
                            }
                            Z04--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != available) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            cVar.f6677h = d(byteArrayInputStream, cVar.f6674e);
            int i11 = cVar.f6676g;
            BitSet valueOf = BitSet.valueOf(z.U0(byteArrayInputStream, (((i11 * 2) + 7) & (-8)) / 8));
            for (int i12 = 0; i12 < i11; i12++) {
                int i13 = valueOf.get(c(2, i12, i11)) ? 2 : 0;
                if (valueOf.get(c(4, i12, i11))) {
                    i13 |= 4;
                }
                if (i13 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i12));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i12), Integer.valueOf(i13 | num.intValue()));
                }
            }
        }
        return cVarArr;
    }

    /* JADX WARN: Finally extract failed */
    public static boolean j(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, c[] cVarArr) {
        ArrayList arrayList;
        int length;
        byte[] bArr2 = o.f6692a;
        int i7 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = o.f6693b;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] a7 = a(cVarArr, bArr3);
                z.u1(byteArrayOutputStream, cVarArr.length, 1);
                z.u1(byteArrayOutputStream, a7.length, 4);
                byte[] D = z.D(a7);
                z.u1(byteArrayOutputStream, D.length, 4);
                byteArrayOutputStream.write(D);
                return true;
            }
            byte[] bArr4 = o.f6695d;
            if (Arrays.equals(bArr, bArr4)) {
                z.u1(byteArrayOutputStream, cVarArr.length, 1);
                for (c cVar : cVarArr) {
                    int size = cVar.f6678i.size() * 4;
                    String b7 = b(cVar.f6670a, cVar.f6671b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    z.v1(byteArrayOutputStream, b7.getBytes(charset).length);
                    z.v1(byteArrayOutputStream, cVar.f6677h.length);
                    z.u1(byteArrayOutputStream, size, 4);
                    z.u1(byteArrayOutputStream, cVar.f6672c, 4);
                    byteArrayOutputStream.write(b7.getBytes(charset));
                    Iterator it = cVar.f6678i.keySet().iterator();
                    while (it.hasNext()) {
                        z.v1(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        z.v1(byteArrayOutputStream, 0);
                    }
                    for (int i8 : cVar.f6677h) {
                        z.v1(byteArrayOutputStream, i8);
                    }
                }
                return true;
            }
            byte[] bArr5 = o.f6694c;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] a8 = a(cVarArr, bArr5);
                z.u1(byteArrayOutputStream, cVarArr.length, 1);
                z.u1(byteArrayOutputStream, a8.length, 4);
                byte[] D2 = z.D(a8);
                z.u1(byteArrayOutputStream, D2.length, 4);
                byteArrayOutputStream.write(D2);
                return true;
            }
            byte[] bArr6 = o.f6696e;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            z.v1(byteArrayOutputStream, cVarArr.length);
            for (c cVar2 : cVarArr) {
                String b8 = b(cVar2.f6670a, cVar2.f6671b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                z.v1(byteArrayOutputStream, b8.getBytes(charset2).length);
                TreeMap treeMap = cVar2.f6678i;
                z.v1(byteArrayOutputStream, treeMap.size());
                z.v1(byteArrayOutputStream, cVar2.f6677h.length);
                z.u1(byteArrayOutputStream, cVar2.f6672c, 4);
                byteArrayOutputStream.write(b8.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    z.v1(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i9 : cVar2.f6677h) {
                    z.v1(byteArrayOutputStream, i9);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            z.v1(byteArrayOutputStream2, cVarArr.length);
            int i10 = 2;
            int i11 = 2;
            for (c cVar3 : cVarArr) {
                z.u1(byteArrayOutputStream2, cVar3.f6672c, 4);
                z.u1(byteArrayOutputStream2, cVar3.f6673d, 4);
                z.u1(byteArrayOutputStream2, cVar3.f6676g, 4);
                String b9 = b(cVar3.f6670a, cVar3.f6671b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = b9.getBytes(charset3).length;
                z.v1(byteArrayOutputStream2, length2);
                i11 = i11 + 14 + length2;
                byteArrayOutputStream2.write(b9.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i11 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i11 + ", does not match actual size " + byteArray.length);
            }
            p pVar = new p(d.DEX_FILES, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(pVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i12 = 0;
            for (int i13 = 0; i13 < cVarArr.length; i13++) {
                try {
                    c cVar4 = cVarArr[i13];
                    z.v1(byteArrayOutputStream3, i13);
                    z.v1(byteArrayOutputStream3, cVar4.f6674e);
                    i12 = i12 + 4 + (cVar4.f6674e * 2);
                    k(byteArrayOutputStream3, cVar4);
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i12 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i12 + ", does not match actual size " + byteArray2.length);
            }
            p pVar2 = new p(d.CLASSES, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(pVar2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i14 = 0;
            int i15 = 0;
            while (i14 < cVarArr.length) {
                try {
                    c cVar5 = cVarArr[i14];
                    Iterator it3 = cVar5.f6678i.entrySet().iterator();
                    int i16 = i7;
                    while (it3.hasNext()) {
                        i16 |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        m(byteArrayOutputStream4, cVar5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            n(byteArrayOutputStream4, cVar5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            z.v1(byteArrayOutputStream3, i14);
                            int length3 = byteArray3.length + i10 + byteArray4.length;
                            int i17 = i15 + 6;
                            ArrayList arrayList4 = arrayList3;
                            z.u1(byteArrayOutputStream3, length3, 4);
                            z.v1(byteArrayOutputStream3, i16);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i15 = i17 + length3;
                            i14++;
                            arrayList3 = arrayList4;
                            i7 = 0;
                            i10 = 2;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i15 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i15 + ", does not match actual size " + byteArray5.length);
            }
            p pVar3 = new p(d.METHODS, byteArray5, true);
            byteArrayOutputStream3.close();
            arrayList2.add(pVar3);
            long j7 = 4;
            long size2 = j7 + j7 + 4 + (arrayList2.size() * 16);
            z.u1(byteArrayOutputStream, arrayList2.size(), 4);
            int i18 = 0;
            while (i18 < arrayList2.size()) {
                p pVar4 = (p) arrayList2.get(i18);
                z.u1(byteArrayOutputStream, pVar4.f6699a.f6680a, 4);
                z.u1(byteArrayOutputStream, size2, 4);
                boolean z6 = pVar4.f6701c;
                byte[] bArr7 = pVar4.f6700b;
                if (z6) {
                    long length4 = bArr7.length;
                    byte[] D3 = z.D(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(D3);
                    z.u1(byteArrayOutputStream, D3.length, 4);
                    z.u1(byteArrayOutputStream, length4, 4);
                    length = D3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    z.u1(byteArrayOutputStream, bArr7.length, 4);
                    z.u1(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += length;
                i18++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i19 = 0; i19 < arrayList6.size(); i19++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i19));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static void k(ByteArrayOutputStream byteArrayOutputStream, c cVar) {
        int i7 = 0;
        for (int i8 : cVar.f6677h) {
            Integer valueOf = Integer.valueOf(i8);
            z.v1(byteArrayOutputStream, valueOf.intValue() - i7);
            i7 = valueOf.intValue();
        }
    }

    public static void l(ByteArrayOutputStream byteArrayOutputStream, c cVar, String str) {
        Charset charset = StandardCharsets.UTF_8;
        z.v1(byteArrayOutputStream, str.getBytes(charset).length);
        z.v1(byteArrayOutputStream, cVar.f6674e);
        z.u1(byteArrayOutputStream, cVar.f6675f, 4);
        z.u1(byteArrayOutputStream, cVar.f6672c, 4);
        z.u1(byteArrayOutputStream, cVar.f6676g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void m(ByteArrayOutputStream byteArrayOutputStream, c cVar) {
        byte[] bArr = new byte[(((cVar.f6676g * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : cVar.f6678i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            int i7 = intValue2 & 2;
            int i8 = cVar.f6676g;
            if (i7 != 0) {
                int c7 = c(2, intValue, i8);
                int i9 = c7 / 8;
                bArr[i9] = (byte) ((1 << (c7 % 8)) | bArr[i9]);
            }
            if ((intValue2 & 4) != 0) {
                int c8 = c(4, intValue, i8);
                int i10 = c8 / 8;
                bArr[i10] = (byte) ((1 << (c8 % 8)) | bArr[i10]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void n(ByteArrayOutputStream byteArrayOutputStream, c cVar) {
        int i7 = 0;
        for (Map.Entry entry : cVar.f6678i.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                z.v1(byteArrayOutputStream, intValue - i7);
                z.v1(byteArrayOutputStream, 0);
                i7 = intValue;
            }
        }
    }
}
