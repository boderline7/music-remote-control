package g6;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class l implements Serializable, Comparable {
    public static final k Companion = new k();
    public static final l EMPTY = new l(new byte[0]);

    /* renamed from: a, reason: collision with root package name */
    public final byte[] f6574a;

    /* renamed from: b, reason: collision with root package name */
    public transient int f6575b;

    /* renamed from: c, reason: collision with root package name */
    public transient String f6576c;

    public l(byte[] bArr) {
        i5.a.g("data", bArr);
        this.f6574a = bArr;
    }

    public String a() {
        byte[] bArr = a.f6552a;
        byte[] bArr2 = this.f6574a;
        i5.a.g("<this>", bArr2);
        i5.a.g("map", bArr);
        byte[] bArr3 = new byte[((bArr2.length + 2) / 3) * 4];
        int length = bArr2.length - (bArr2.length % 3);
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            byte b7 = bArr2[i7];
            int i9 = i7 + 2;
            byte b8 = bArr2[i7 + 1];
            i7 += 3;
            byte b9 = bArr2[i9];
            bArr3[i8] = bArr[(b7 & 255) >> 2];
            bArr3[i8 + 1] = bArr[((b7 & 3) << 4) | ((b8 & 255) >> 4)];
            int i10 = i8 + 3;
            bArr3[i8 + 2] = bArr[((b8 & 15) << 2) | ((b9 & 255) >> 6)];
            i8 += 4;
            bArr3[i10] = bArr[b9 & 63];
        }
        int length2 = bArr2.length - length;
        if (length2 == 1) {
            byte b10 = bArr2[i7];
            bArr3[i8] = bArr[(b10 & 255) >> 2];
            bArr3[i8 + 1] = bArr[(b10 & 3) << 4];
            bArr3[i8 + 2] = 61;
            bArr3[i8 + 3] = 61;
        } else if (length2 == 2) {
            int i11 = i7 + 1;
            byte b11 = bArr2[i7];
            byte b12 = bArr2[i11];
            bArr3[i8] = bArr[(b11 & 255) >> 2];
            bArr3[i8 + 1] = bArr[((b11 & 3) << 4) | ((b12 & 255) >> 4)];
            bArr3[i8 + 2] = bArr[(b12 & 15) << 2];
            bArr3[i8 + 3] = 61;
        }
        return new String(bArr3, o5.a.UTF_8);
    }

    public l b(String str) {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.f6574a, 0, c());
        byte[] digest = messageDigest.digest();
        i5.a.d(digest);
        return new l(digest);
    }

    public int c() {
        return this.f6574a.length;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        if (r0 < r1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
    
        if (r7 < r8) goto L9;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int compareTo(java.lang.Object r10) {
        /*
            r9 = this;
            g6.l r10 = (g6.l) r10
            java.lang.String r0 = "other"
            i5.a.g(r0, r10)
            int r0 = r9.c()
            int r1 = r10.c()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = r3
        L15:
            r5 = 1
            r6 = -1
            if (r4 >= r2) goto L30
            byte r7 = r9.f(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.f(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L2a
            int r4 = r4 + 1
            goto L15
        L2a:
            if (r7 >= r8) goto L2e
        L2c:
            r3 = r6
            goto L36
        L2e:
            r3 = r5
            goto L36
        L30:
            if (r0 != r1) goto L33
            goto L36
        L33:
            if (r0 >= r1) goto L2e
            goto L2c
        L36:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.l.compareTo(java.lang.Object):int");
    }

    public String d() {
        byte[] bArr = this.f6574a;
        char[] cArr = new char[bArr.length * 2];
        int i7 = 0;
        for (byte b7 : bArr) {
            int i8 = i7 + 1;
            char[] cArr2 = h6.b.f6770a;
            cArr[i7] = cArr2[(b7 >> 4) & 15];
            i7 += 2;
            cArr[i8] = cArr2[b7 & 15];
        }
        return new String(cArr);
    }

    public byte[] e() {
        return this.f6574a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof l) {
            l lVar = (l) obj;
            int c7 = lVar.c();
            byte[] bArr = this.f6574a;
            if (c7 == bArr.length && lVar.g(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte f(int i7) {
        return this.f6574a[i7];
    }

    public boolean g(int i7, byte[] bArr, int i8, int i9) {
        i5.a.g("other", bArr);
        if (i7 >= 0) {
            byte[] bArr2 = this.f6574a;
            if (i7 <= bArr2.length - i9 && i8 >= 0 && i8 <= bArr.length - i9 && n1.z.i(i7, i8, i9, bArr2, bArr)) {
                return true;
            }
        }
        return false;
    }

    public boolean h(l lVar, int i7) {
        i5.a.g("other", lVar);
        return lVar.g(0, this.f6574a, 0, i7);
    }

    public int hashCode() {
        int i7 = this.f6575b;
        if (i7 != 0) {
            return i7;
        }
        int hashCode = Arrays.hashCode(this.f6574a);
        this.f6575b = hashCode;
        return hashCode;
    }

    public l i() {
        int i7 = 0;
        while (true) {
            byte[] bArr = this.f6574a;
            if (i7 >= bArr.length) {
                return this;
            }
            byte b7 = bArr[i7];
            if (b7 >= 65 && b7 <= 90) {
                byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                i5.a.f("copyOf(this, size)", copyOf);
                copyOf[i7] = (byte) (b7 + o2.d.VMCF_USEFLAGS);
                for (int i8 = i7 + 1; i8 < copyOf.length; i8++) {
                    byte b8 = copyOf[i8];
                    if (b8 >= 65 && b8 <= 90) {
                        copyOf[i8] = (byte) (b8 + o2.d.VMCF_USEFLAGS);
                    }
                }
                return new l(copyOf);
            }
            i7++;
        }
    }

    public final String j() {
        String str = this.f6576c;
        if (str != null) {
            return str;
        }
        byte[] e7 = e();
        i5.a.g("<this>", e7);
        String str2 = new String(e7, o5.a.UTF_8);
        this.f6576c = str2;
        return str2;
    }

    public void k(h hVar, int i7) {
        i5.a.g("buffer", hVar);
        hVar.L(this.f6574a, 0, i7);
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x00e8, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0120, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0124, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x00c8, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0163, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x016a, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x015c, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x019b, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x019e, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x01a1, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0130, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x01a4, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x008b, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x007a, code lost:
    
        if (r6 == 64) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00f0, code lost:
    
        if (r6 == 64) goto L180;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 621
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.l.toString():java.lang.String");
    }
}
