package l2;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.recyclerview.widget.e0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import n2.k;
import n2.m;
import o2.g;
import o2.h;
import o2.i;
import o2.j;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends c {
    public static final int[] F0 = {4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 14, 0, 12};
    public n2.a A0;
    public long B0;
    public boolean C0;
    public int D0;
    public int E0;

    /* renamed from: r0, reason: collision with root package name */
    public final n2.c f7324r0;

    /* renamed from: s0, reason: collision with root package name */
    public int f7325s0;

    /* renamed from: t0, reason: collision with root package name */
    public final o2.c f7326t0;

    /* renamed from: u0, reason: collision with root package name */
    public final ArrayList f7327u0;

    /* renamed from: v0, reason: collision with root package name */
    public final ArrayList f7328v0;

    /* renamed from: w0, reason: collision with root package name */
    public final ArrayList f7329w0;

    /* renamed from: x0, reason: collision with root package name */
    public int f7330x0;

    /* renamed from: y0, reason: collision with root package name */
    public boolean f7331y0;

    /* renamed from: z0, reason: collision with root package name */
    public final byte[] f7332z0;

    public d(a aVar) {
        this.f7294i = new int[4];
        this.f7298m = new int[256];
        this.f7299n = new int[256];
        this.f7300o = new int[256];
        this.f7301p = new int[256];
        this.f7302q = new int[256];
        this.f7303r = new int[256];
        this.f7304s = new int[256];
        this.f7305t = new int[256];
        this.f7306u = new int[256];
        this.f7307v = new int[256];
        this.f7308w = new int[256];
        this.f7312f0 = new m2.b[4];
        this.f7313g0 = new byte[1028];
        this.f7318l0 = new m2.a[4];
        this.f7319m0 = new m2.b(2);
        this.f7320n0 = new m2.b(1);
        this.f7321o0 = new m2.b(3);
        this.f7322p0 = new m2.b(5);
        this.f7323q0 = new m2.b(0);
        this.f7324r0 = new n2.c();
        this.f7326t0 = new o2.c();
        this.f7327u0 = new ArrayList();
        this.f7328v0 = new ArrayList();
        this.f7329w0 = new ArrayList();
        this.f7332z0 = new byte[404];
        this.f7290e = aVar;
        this.f7293h = null;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    public final void o(o2.i r40) {
        /*
            Method dump skipped, instructions count: 3692
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.d.o(o2.i):void");
    }

    public final void p() {
        e eVar;
        int i7 = this.f7296k;
        int i8 = 4194303;
        int i9 = (this.f7295j - i7) & 4194303;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f7328v0;
            if (i10 >= arrayList.size()) {
                int i11 = this.f7295j;
                if (i11 < i7) {
                    q(this.f7293h, i7, (-i7) & 4194303);
                    q(this.f7293h, 0, i11);
                } else {
                    q(this.f7293h, i7, i11 - i7);
                }
                this.f7296k = this.f7295j;
                return;
            }
            e eVar2 = (e) arrayList.get(i10);
            if (eVar2 != null) {
                if (eVar2.f7336d) {
                    eVar2.f7336d = false;
                } else {
                    int i12 = eVar2.f7333a;
                    int i13 = eVar2.f7334b;
                    int i14 = i12 - i7;
                    if ((i14 & i8) >= i9) {
                        continue;
                    } else {
                        if (i7 != i12) {
                            if (i12 < i7) {
                                q(this.f7293h, i7, (-i7) & i8);
                                q(this.f7293h, 0, i12);
                            } else {
                                q(this.f7293h, i7, i14);
                            }
                            i9 = (this.f7295j - i12) & i8;
                            i7 = i12;
                        }
                        if (i13 > i9) {
                            while (i10 < arrayList.size()) {
                                e eVar3 = (e) arrayList.get(i10);
                                if (eVar3 != null && eVar3.f7336d) {
                                    eVar3.f7336d = false;
                                }
                                i10++;
                            }
                            this.f7296k = i7;
                            return;
                        }
                        i7 = (i12 + i13) & i8;
                        o2.c cVar = this.f7326t0;
                        if (i12 < i7 || i7 == 0) {
                            cVar.j(0, this.f7293h, i12, i13);
                        } else {
                            int i15 = 4194304 - i12;
                            cVar.j(0, this.f7293h, i12, i15);
                            cVar.j(i15, this.f7293h, 0, i7);
                        }
                        ArrayList arrayList2 = this.f7327u0;
                        i iVar = ((e) arrayList2.get(eVar2.f7337e)).f7338f;
                        int size = iVar.f7900d.size();
                        i iVar2 = eVar2.f7338f;
                        Vector vector = iVar.f7900d;
                        if (size > 64) {
                            iVar2.f7900d.setSize(vector.size());
                            for (int i16 = 0; i16 < vector.size() - 64; i16++) {
                                int i17 = i16 + 64;
                                iVar2.f7900d.set(i17, vector.get(i17));
                            }
                        }
                        o(iVar2);
                        Vector vector2 = iVar2.f7900d;
                        if (vector2.size() > 64) {
                            if (vector.size() < vector2.size()) {
                                vector.setSize(vector2.size());
                            }
                            for (int i18 = 0; i18 < vector2.size() - 64; i18++) {
                                int i19 = i18 + 64;
                                vector.set(i19, vector2.get(i19));
                            }
                        } else {
                            vector.clear();
                        }
                        int i20 = iVar2.f7903g;
                        int i21 = iVar2.f7904h;
                        byte[] bArr = new byte[i21];
                        for (int i22 = 0; i22 < i21; i22++) {
                            bArr[i22] = cVar.f7881d[i20 + i22];
                        }
                        arrayList.set(i10, null);
                        while (true) {
                            int i23 = i10 + 1;
                            if (i23 >= arrayList.size() || (eVar = (e) arrayList.get(i23)) == null || eVar.f7333a != i12 || eVar.f7334b != i21 || eVar.f7336d) {
                                break;
                            }
                            cVar.j(0, bArr, 0, i21);
                            i iVar3 = ((e) arrayList2.get(eVar.f7337e)).f7338f;
                            int size2 = iVar3.f7900d.size();
                            i iVar4 = eVar.f7338f;
                            Vector vector3 = iVar3.f7900d;
                            if (size2 > 64) {
                                iVar4.f7900d.setSize(vector3.size());
                                for (int i24 = 0; i24 < vector3.size() - 64; i24++) {
                                    int i25 = i24 + 64;
                                    iVar4.f7900d.set(i25, vector3.get(i25));
                                }
                            }
                            o(iVar4);
                            Vector vector4 = iVar4.f7900d;
                            if (vector4.size() > 64) {
                                if (vector3.size() < vector4.size()) {
                                    vector3.setSize(vector4.size());
                                }
                                for (int i26 = 0; i26 < vector4.size() - 64; i26++) {
                                    int i27 = i26 + 64;
                                    vector3.set(i27, vector4.get(i27));
                                }
                            } else {
                                vector3.clear();
                            }
                            int i28 = iVar4.f7903g;
                            i21 = iVar4.f7904h;
                            bArr = new byte[i21];
                            for (int i29 = 0; i29 < i21; i29++) {
                                bArr[i29] = ((Byte) vector4.get(i28 + i29)).byteValue();
                            }
                            arrayList.set(i23, null);
                            i10 = i23;
                        }
                        this.f7290e.c(bArr, 0, i21);
                        this.B0 += i21;
                        i9 = (this.f7295j - i7) & 4194303;
                    }
                }
            }
            i10++;
            i8 = 4194303;
        }
    }

    public final void q(byte[] bArr, int i7, int i8) {
        long j7 = this.B0;
        long j8 = this.f7292g;
        if (j7 >= j8) {
            return;
        }
        long j9 = j8 - j7;
        long j10 = i8;
        if (j10 > j9) {
            i8 = (int) j9;
        }
        this.f7290e.c(bArr, i7, i8);
        this.B0 += j10;
    }

    public final boolean r(int i7, ArrayList arrayList) {
        int i8;
        e eVar;
        o2.a aVar;
        e eVar2;
        int c7;
        e eVar3;
        j jVar;
        int i9;
        int i10;
        o2.a aVar2 = new o2.a();
        aVar2.f7876a = 0;
        aVar2.f7877b = 0;
        for (int i11 = 0; i11 < Math.min(32768, arrayList.size()); i11++) {
            aVar2.f7878c[i11] = ((Byte) arrayList.get(i11)).byteValue();
        }
        o2.c cVar = this.f7326t0;
        if (cVar.f7881d == null) {
            cVar.f7881d = new byte[262148];
        }
        int i12 = i7 & 128;
        ArrayList arrayList2 = this.f7328v0;
        ArrayList arrayList3 = this.f7327u0;
        ArrayList arrayList4 = this.f7329w0;
        if (i12 != 0) {
            i8 = o2.c.c(aVar2);
            if (i8 == 0) {
                arrayList4.clear();
                this.f7330x0 = 0;
                arrayList3.clear();
                arrayList2.clear();
            } else {
                i8--;
            }
        } else {
            i8 = this.f7330x0;
        }
        if (i8 > arrayList3.size() || i8 > arrayList4.size()) {
            return false;
        }
        this.f7330x0 = i8;
        boolean z6 = i8 == arrayList3.size();
        e eVar4 = new e();
        if (!z6) {
            eVar = (e) arrayList3.get(i8);
            eVar4.f7337e = i8;
            eVar.f7335c++;
        } else {
            if (i8 > 1024) {
                return false;
            }
            eVar = new e();
            arrayList3.add(eVar);
            eVar4.f7337e = arrayList3.size() - 1;
            arrayList4.add(0);
            eVar.f7335c = 0;
        }
        arrayList2.add(eVar4);
        eVar4.f7335c = eVar.f7335c;
        int c8 = o2.c.c(aVar2);
        if ((i7 & 64) != 0) {
            c8 += 258;
        }
        eVar4.f7333a = (this.f7295j + c8) & 4194303;
        if ((i7 & 32) != 0) {
            eVar4.f7334b = o2.c.c(aVar2);
        } else {
            eVar4.f7334b = i8 < arrayList4.size() ? ((Integer) arrayList4.get(i8)).intValue() : 0;
        }
        int i13 = this.f7296k;
        int i14 = this.f7295j;
        eVar4.f7336d = i13 != i14 && ((i13 - i14) & 4194303) <= c8;
        arrayList4.set(i8, Integer.valueOf(eVar4.f7334b));
        Arrays.fill(eVar4.f7338f.f7902f, 0);
        int[] iArr = eVar4.f7338f.f7902f;
        iArr[3] = 245760;
        iArr[4] = eVar4.f7334b;
        iArr[5] = eVar4.f7335c;
        if ((i7 & 16) != 0) {
            int b7 = aVar2.b() >>> 9;
            aVar2.a(7);
            for (int i15 = 0; i15 < 7; i15++) {
                if (((1 << i15) & b7) != 0) {
                    eVar4.f7338f.f7902f[i15] = o2.c.c(aVar2);
                }
            }
        }
        i iVar = eVar.f7338f;
        if (z6) {
            int c9 = o2.c.c(aVar2);
            if (c9 >= 65536 || c9 == 0) {
                return false;
            }
            byte[] bArr = new byte[c9];
            for (int i16 = 0; i16 < c9; i16++) {
                if (aVar2.f7876a + 3 >= 32768) {
                    return false;
                }
                bArr[i16] = (byte) (aVar2.b() >>> 8);
                aVar2.a(8);
            }
            cVar.f7876a = 0;
            cVar.f7877b = 0;
            int min = Math.min(32768, c9);
            for (int i17 = 0; i17 < min; i17++) {
                byte[] bArr2 = cVar.f7878c;
                bArr2[i17] = (byte) (bArr2[i17] | bArr[i17]);
            }
            byte b8 = 0;
            for (int i18 = 1; i18 < c9; i18++) {
                b8 = (byte) (b8 ^ bArr[i18]);
            }
            cVar.a(8);
            iVar.f7899c = 0;
            byte b9 = bArr[0];
            byte[] bArr3 = o2.d.f7887a;
            ArrayList arrayList5 = iVar.f7897a;
            if (b8 == b9) {
                aVar = aVar2;
                eVar3 = eVar4;
                a1.b[] bVarArr = {new a1.b(53, -1386780537, j.VMSF_E8), new a1.b(57, 1020781950, j.VMSF_E8E9), new a1.b(z1.c.INVOKE_INTERFACE_RANGE, 929663295, j.VMSF_ITANIUM), new a1.b(29, 235276157, j.VMSF_DELTA), new a1.b(z1.c.AND_INT, 472669640, j.VMSF_RGB), new a1.b(z1.c.ADD_INT_LIT8, -1132075263, j.VMSF_AUDIO), new a1.b(40, 1186579808, j.VMSF_UPCASE)};
                int i19 = ~i2.a.a(-1, bArr, 0, c9);
                int i20 = 0;
                while (true) {
                    if (i20 >= 7) {
                        jVar = j.VMSF_NONE;
                        break;
                    }
                    a1.b bVar = bVarArr[i20];
                    if (bVar.f199c == i19 && bVar.f198b == c9) {
                        jVar = (j) bVar.f200d;
                        break;
                    }
                    i20++;
                }
                if (jVar != j.VMSF_NONE) {
                    h hVar = new h();
                    hVar.f7893a = o2.e.VM_STANDARD;
                    e0 e0Var = hVar.f7895c;
                    e0Var.f2434a = jVar.f7906a;
                    g gVar = g.VM_OPNONE;
                    e0Var.f2437d = gVar;
                    hVar.f7896d.f2437d = gVar;
                    arrayList5.add(hVar);
                    i9 = 1;
                    iVar.f7899c++;
                    c9 = 0;
                } else {
                    i9 = 1;
                }
                int b10 = cVar.b();
                cVar.a(i9);
                if ((b10 & 32768) != 0) {
                    o2.c.c(cVar);
                    for (int i21 = 0; cVar.f7876a < c9 && i21 < 0; i21++) {
                        iVar.f7901e.add(Byte.valueOf((byte) (cVar.b() >>> 8)));
                        cVar.a(8);
                    }
                }
                while (cVar.f7876a < c9) {
                    h hVar2 = new h();
                    int b11 = cVar.b();
                    o2.e eVar5 = null;
                    if ((b11 & 32768) == 0) {
                        int i22 = b11 >>> 12;
                        o2.e[] values = o2.e.values();
                        int length = values.length;
                        int i23 = 0;
                        while (true) {
                            if (i23 >= length) {
                                break;
                            }
                            o2.e eVar6 = values[i23];
                            if (eVar6.f7889a == i22) {
                                eVar5 = eVar6;
                                break;
                            }
                            i23++;
                        }
                        hVar2.f7893a = eVar5;
                        cVar.a(4);
                    } else {
                        int i24 = (b11 >>> 10) - 24;
                        o2.e[] values2 = o2.e.values();
                        int length2 = values2.length;
                        int i25 = 0;
                        while (true) {
                            if (i25 >= length2) {
                                break;
                            }
                            o2.e eVar7 = values2[i25];
                            if (eVar7.f7889a == i24) {
                                eVar5 = eVar7;
                                break;
                            }
                            i25++;
                        }
                        hVar2.f7893a = eVar5;
                        cVar.a(6);
                    }
                    if ((bArr3[hVar2.f7893a.f7889a] & 4) != 0) {
                        hVar2.f7894b = (cVar.b() >>> 15) == 1;
                        cVar.a(1);
                    } else {
                        hVar2.f7894b = false;
                    }
                    e0 e0Var2 = hVar2.f7895c;
                    g gVar2 = g.VM_OPNONE;
                    e0Var2.f2437d = gVar2;
                    hVar2.f7896d.f2437d = gVar2;
                    int i26 = bArr3[hVar2.f7893a.f7889a] & 3;
                    if (i26 > 0) {
                        cVar.d(e0Var2, hVar2.f7894b);
                        if (i26 == 2) {
                            cVar.d(hVar2.f7896d, hVar2.f7894b);
                        } else {
                            e0 e0Var3 = hVar2.f7895c;
                            if (((g) e0Var3.f2437d) == g.VM_OPINT && (bArr3[hVar2.f7893a.f7889a] & 24) != 0) {
                                int i27 = e0Var3.f2434a;
                                if (i27 >= 256) {
                                    i10 = i27 - 256;
                                } else {
                                    if (i27 >= 136) {
                                        i27 -= 264;
                                    } else if (i27 >= 16) {
                                        i27 -= 8;
                                    } else if (i27 >= 8) {
                                        i27 -= 16;
                                    }
                                    i10 = i27 + iVar.f7899c;
                                }
                                e0Var3.f2434a = i10;
                            }
                        }
                    }
                    iVar.f7899c++;
                    arrayList5.add(hVar2);
                }
            } else {
                aVar = aVar2;
                eVar3 = eVar4;
            }
            h hVar3 = new h();
            hVar3.f7893a = o2.e.VM_RET;
            e0 e0Var4 = hVar3.f7895c;
            g gVar3 = g.VM_OPNONE;
            e0Var4.f2437d = gVar3;
            hVar3.f7896d.f2437d = gVar3;
            arrayList5.add(hVar3);
            iVar.f7899c++;
            if (c9 != 0) {
                Iterator it = arrayList5.iterator();
                while (it.hasNext()) {
                    h hVar4 = (h) it.next();
                    int i28 = o2.b.f7879a[hVar4.f7893a.ordinal()];
                    if (i28 == 1) {
                        hVar4.f7893a = hVar4.f7894b ? o2.e.VM_MOVB : o2.e.VM_MOVD;
                    } else if (i28 == 4) {
                        hVar4.f7893a = hVar4.f7894b ? o2.e.VM_CMPB : o2.e.VM_CMPD;
                    } else if ((bArr3[hVar4.f7893a.f7889a] & o2.d.VMCF_CHFLAGS) != 0) {
                        for (int indexOf = arrayList5.indexOf(hVar4) + 1; indexOf < arrayList5.size(); indexOf++) {
                            byte b12 = bArr3[((h) arrayList5.get(indexOf)).f7893a.f7889a];
                            if ((b12 & 56) == 0) {
                                if ((b12 & o2.d.VMCF_CHFLAGS) != 0) {
                                    break;
                                }
                            }
                        }
                        int i29 = o2.b.f7879a[hVar4.f7893a.ordinal()];
                        if (i29 == 7) {
                            hVar4.f7893a = hVar4.f7894b ? o2.e.VM_ADDB : o2.e.VM_ADDD;
                        } else if (i29 == 10) {
                            hVar4.f7893a = hVar4.f7894b ? o2.e.VM_SUBB : o2.e.VM_SUBD;
                        } else if (i29 == 15) {
                            hVar4.f7893a = hVar4.f7894b ? o2.e.VM_INCB : o2.e.VM_INCD;
                        } else if (i29 == 18) {
                            hVar4.f7893a = hVar4.f7894b ? o2.e.VM_DECB : o2.e.VM_DECD;
                        } else if (i29 == 39) {
                            hVar4.f7893a = hVar4.f7894b ? o2.e.VM_NEGB : o2.e.VM_NEGD;
                        }
                    }
                }
            }
            eVar2 = eVar3;
        } else {
            aVar = aVar2;
            eVar2 = eVar4;
        }
        i iVar2 = eVar2.f7338f;
        iVar2.f7898b = iVar.f7897a;
        iVar2.f7899c = iVar.f7899c;
        int size = iVar.f7901e.size();
        if (size > 0 && size < 8192) {
            eVar2.f7338f.f7901e = iVar.f7901e;
        }
        if (eVar2.f7338f.f7900d.size() < 64) {
            eVar2.f7338f.f7900d.clear();
            eVar2.f7338f.f7900d.setSize(64);
        }
        Vector vector = eVar2.f7338f.f7900d;
        for (int i30 = 0; i30 < 7; i30++) {
            o2.c.i(vector, i30 * 4, eVar2.f7338f.f7902f[i30]);
        }
        o2.c.i(vector, 28, eVar2.f7334b);
        o2.c.i(vector, 32, 0);
        o2.c.i(vector, 36, 0);
        o2.c.i(vector, 40, 0);
        o2.c.i(vector, 44, eVar2.f7335c);
        for (int i31 = 0; i31 < 16; i31++) {
            vector.set(i31 + 48, (byte) 0);
        }
        if ((i7 & 8) != 0) {
            o2.a aVar3 = aVar;
            if (aVar3.f7876a + 3 >= 32768 || (c7 = o2.c.c(aVar3)) > 8128) {
                return false;
            }
            int size2 = eVar2.f7338f.f7900d.size();
            int i32 = c7 + 64;
            if (size2 < i32) {
                eVar2.f7338f.f7900d.setSize(i32 - size2);
            }
            Vector vector2 = eVar2.f7338f.f7900d;
            for (int i33 = 0; i33 < c7; i33++) {
                if (aVar3.f7876a + 3 >= 32768) {
                    return false;
                }
                vector2.set(64 + i33, Byte.valueOf((byte) (aVar3.b() >>> 8)));
                aVar3.a(8);
            }
        }
        return true;
    }

    public final void s(int i7, int i8) {
        int i9 = this.f7295j;
        int i10 = i9 - i8;
        if (i10 < 0 || i10 >= 4194044 || i9 >= 4194044) {
            while (true) {
                int i11 = i7 - 1;
                if (i7 == 0) {
                    return;
                }
                byte[] bArr = this.f7293h;
                int i12 = this.f7295j;
                bArr[i12] = bArr[i10 & 4194303];
                this.f7295j = (i12 + 1) & 4194303;
                i7 = i11;
                i10++;
            }
        } else if (i8 == 1) {
            byte[] bArr2 = this.f7293h;
            Arrays.fill(bArr2, i9, i9 + i7, bArr2[i10]);
            this.f7295j += i7;
        } else {
            if (i10 + i7 <= i9) {
                byte[] bArr3 = this.f7293h;
                System.arraycopy(bArr3, i10, bArr3, i9, i7);
                this.f7295j += i7;
                return;
            }
            while (true) {
                byte[] bArr4 = this.f7293h;
                int i13 = this.f7295j;
                this.f7295j = i13 + 1;
                int i14 = i10 + 1;
                bArr4[i13] = bArr4[i10];
                i7--;
                if (i7 <= 0) {
                    return;
                } else {
                    i10 = i14;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0160 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t(int r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 2366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.d.t(int, boolean):void");
    }

    public final int u() {
        if (this.f7876a > 32738) {
            j();
        }
        int i7 = this.f7876a;
        this.f7876a = i7 + 1;
        return this.f7878c[i7] & 255;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean v() {
        int b7;
        int i7;
        int b8;
        int i8;
        n2.c cVar;
        int[] iArr;
        int[] iArr2;
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[404];
        if (this.f7876a > this.f7291f - 25 && !j()) {
            return false;
        }
        char c7 = '\b';
        a((8 - this.f7877b) & 7);
        long b9 = b();
        if ((PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID & b9) == 0) {
            this.A0 = n2.a.BLOCK_LZ;
            this.D0 = 0;
            this.E0 = 0;
            long j7 = 16384 & b9;
            byte[] bArr3 = this.f7332z0;
            if (j7 == 0) {
                Arrays.fill(bArr3, (byte) 0);
            }
            a(2);
            int i9 = 0;
            while (i9 < 20) {
                int b10 = (b() >>> 12) & 255;
                a(4);
                if (b10 == 15) {
                    int b11 = (b() >>> 12) & 255;
                    a(4);
                    if (b11 == 0) {
                        bArr[i9] = 15;
                    } else {
                        int i10 = b11 + 2;
                        while (true) {
                            int i11 = i10 - 1;
                            if (i10 <= 0 || i9 >= 20) {
                                break;
                            }
                            bArr[i9] = 0;
                            i9++;
                            i10 = i11;
                        }
                        i9--;
                    }
                } else {
                    bArr[i9] = (byte) b10;
                }
                i9++;
            }
            m2.b bVar = this.f7323q0;
            boolean z6 = false;
            c.n(bArr, 0, bVar, 20);
            int i12 = 0;
            int i13 = 404;
            while (i12 < i13) {
                if (this.f7876a > this.f7291f - 5 && !j()) {
                    return z6;
                }
                int m6 = m(bVar);
                if (m6 < 16) {
                    bArr2[i12] = (byte) ((m6 + bArr3[i12]) & 15);
                    i12++;
                } else if (m6 < 18) {
                    if (m6 == 16) {
                        b8 = (b() >>> 13) + 3;
                        a(3);
                    } else {
                        b8 = (b() >>> 9) + 11;
                        a(7);
                    }
                    while (true) {
                        int i14 = b8 - 1;
                        if (b8 <= 0 || i12 >= 404) {
                            break;
                        }
                        bArr2[i12] = bArr2[i12 - 1];
                        i12++;
                        b8 = i14;
                    }
                } else {
                    if (m6 == 18) {
                        b7 = (b() >>> 13) + 3;
                        a(3);
                    } else {
                        b7 = (b() >>> 9) + 11;
                        a(7);
                    }
                    while (true) {
                        int i15 = b7 - 1;
                        if (b7 <= 0) {
                            i7 = 404;
                            break;
                        }
                        i7 = 404;
                        if (i12 >= 404) {
                            break;
                        }
                        bArr2[i12] = 0;
                        b7 = i15;
                        i12++;
                    }
                    i13 = i7;
                    z6 = false;
                }
                i7 = 404;
                i13 = i7;
                z6 = false;
            }
            boolean z7 = z6;
            this.f7331y0 = true;
            if (this.f7876a > this.f7291f) {
                return z7;
            }
            c.n(bArr2, z7 ? 1 : 0, this.f7319m0, 299);
            c.n(bArr2, 299, this.f7320n0, 60);
            c.n(bArr2, 359, this.f7321o0, 17);
            c.n(bArr2, 376, this.f7322p0, 28);
            System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
            return true;
        }
        this.A0 = n2.a.BLOCK_PPM;
        n2.c cVar2 = this.f7324r0;
        cVar2.getClass();
        int u6 = u();
        Object[] objArr = (u6 & 32) != 0;
        m mVar = cVar2.f7717u;
        if (objArr == true) {
            i8 = u();
            if (i8 > 1) {
                i8 = 1;
            }
        } else {
            if (mVar.f7755a == 0) {
                return false;
            }
            i8 = 0;
        }
        if ((u6 & 64) != 0) {
            this.f7325s0 = u();
        }
        n2.g gVar = cVar2.f7716t;
        gVar.f7743e = this;
        gVar.f7740b = 0L;
        gVar.f7739a = 0L;
        gVar.f7741c = 4294967295L;
        int i16 = 0;
        for (int i17 = 4; i16 < i17; i17 = 4) {
            gVar.f7740b = ((gVar.f7740b << c7) | gVar.f7743e.u()) & 4294967295L;
            i16++;
            cVar2 = cVar2;
            c7 = '\b';
        }
        n2.c cVar3 = cVar2;
        if (objArr == true) {
            int i18 = u6 & 31;
            int i19 = i18 + 1;
            if (i19 > 16) {
                i19 = ((i18 - 15) * 3) + 16;
            }
            if (i19 == 1) {
                mVar.g();
                return false;
            }
            int i20 = (i8 + 1) << 20;
            if (mVar.f7755a != i20) {
                mVar.g();
                int i21 = m.UNIT_SIZE;
                int i22 = ((i20 / 12) * i21) + i21;
                mVar.f7769o = i22 + z1.c.SHR_INT;
                mVar.f7767m = new byte[i22 + z1.c.USHR_LONG];
                mVar.f7759e = 1;
                int i23 = i22 + 1;
                mVar.f7765k = i23 - i21;
                mVar.f7755a = i20;
                mVar.f7768n = i23;
                int i24 = 0;
                while (true) {
                    n2.i[] iVarArr = mVar.f7762h;
                    if (i24 >= iVarArr.length) {
                        break;
                    }
                    n2.i iVar = new n2.i(mVar.f7767m);
                    iVarArr[i24] = iVar;
                    iVar.f7735b = i23;
                    i24++;
                    i23 += 4;
                }
                byte[] bArr4 = mVar.f7767m;
                mVar.f7770p = new n2.i(bArr4);
                mVar.f7771q = new n2.h(bArr4);
                mVar.f7772r = new n2.h(bArr4);
                mVar.f7773s = new n2.h(bArr4);
            }
            cVar = cVar3;
            cVar.f7699c = new n2.d(mVar.f7767m);
            cVar.f7700d = new n2.d(mVar.f7767m);
            cVar.f7701e = new k(mVar.f7767m);
            cVar.f7698b = new n2.j();
            for (int i25 = 0; i25 < 25; i25++) {
                for (int i26 = 0; i26 < 16; i26++) {
                    cVar.f7697a[i25][i26] = new n2.j();
                }
            }
            cVar.f7712p = 1;
            cVar.f7705i = i19;
            cVar.c();
            int[] iArr3 = cVar.f7710n;
            iArr3[0] = 0;
            iArr3[1] = 2;
            for (int i27 = 0; i27 < 9; i27++) {
                iArr3[i27 + 2] = 4;
            }
            for (int i28 = 0; i28 < 245; i28++) {
                iArr3[i28 + 11] = 6;
            }
            int i29 = 0;
            while (true) {
                iArr = cVar.f7709m;
                if (i29 >= 3) {
                    break;
                }
                iArr[i29] = i29;
                i29++;
            }
            int i30 = i29;
            int i31 = 1;
            int i32 = 1;
            while (i29 < 256) {
                iArr[i29] = i30;
                i31--;
                if (i31 == 0) {
                    i32++;
                    i30++;
                    i31 = i32;
                }
                i29++;
            }
            int i33 = 0;
            while (true) {
                iArr2 = cVar.f7711o;
                if (i33 >= 64) {
                    break;
                }
                iArr2[i33] = 0;
                i33++;
            }
            for (int i34 = 0; i34 < 192; i34++) {
                iArr2[i34 + 64] = 8;
            }
            cVar.f7698b.f7750b = 7;
        } else {
            cVar = cVar3;
        }
        if (cVar.f7699c.f7735b != 0) {
            return true;
        }
        return false;
    }

    public final void w(boolean z6) {
        int i7 = 0;
        if (!z6) {
            this.f7331y0 = false;
            Arrays.fill(this.f7294i, 0);
            this.f7297l = 0;
            this.L = 0;
            this.M = 0;
            Arrays.fill(this.f7332z0, (byte) 0);
            this.f7295j = 0;
            this.f7296k = 0;
            this.f7325s0 = 2;
            this.f7329w0.clear();
            this.f7330x0 = 0;
            this.f7327u0.clear();
            this.f7328v0.clear();
        }
        this.f7876a = 0;
        this.f7877b = 0;
        this.C0 = false;
        this.B0 = 0L;
        this.f7291f = 0;
        this.f7289d = 0;
        if (z6) {
            return;
        }
        this.f7316j0 = 0;
        this.f7317k0 = 0;
        this.f7315i0 = 1;
        int i8 = 0;
        while (true) {
            m2.a[] aVarArr = this.f7318l0;
            if (i8 >= aVarArr.length) {
                break;
            }
            m2.a aVar = new m2.a();
            aVar.f7521k = new int[11];
            aVarArr[i8] = aVar;
            i8++;
        }
        Arrays.fill(this.f7313g0, (byte) 0);
        while (true) {
            m2.b[] bVarArr = this.f7312f0;
            if (i7 >= bVarArr.length) {
                return;
            }
            bVarArr[i7] = new m2.b(4);
            i7++;
        }
    }
}
