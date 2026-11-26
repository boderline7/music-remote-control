package p0;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements f, h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8133a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f8134b;

    /* renamed from: c, reason: collision with root package name */
    public int f8135c;

    /* renamed from: d, reason: collision with root package name */
    public int f8136d;

    /* renamed from: e, reason: collision with root package name */
    public Object f8137e;

    /* renamed from: f, reason: collision with root package name */
    public Cloneable f8138f;

    public g(int i7) {
        this.f8133a = 2;
        this.f8134b = new ArrayList(i7);
        this.f8135c = 0;
        this.f8137e = null;
        this.f8138f = null;
        this.f8136d = 0;
    }

    public g(ClipData clipData, int i7) {
        this.f8133a = 0;
        this.f8134b = clipData;
        this.f8135c = i7;
    }

    public g(g gVar) {
        this.f8133a = 1;
        ClipData clipData = (ClipData) gVar.f8134b;
        clipData.getClass();
        this.f8134b = clipData;
        int i7 = gVar.f8135c;
        if (i7 < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", "source", 0, 5));
        }
        if (i7 > 5) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", "source", 0, 5));
        }
        this.f8135c = i7;
        int i8 = gVar.f8136d;
        if ((i8 & 1) == i8) {
            this.f8136d = i8;
            this.f8137e = (Uri) gVar.f8137e;
            this.f8138f = (Bundle) gVar.f8138f;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i8) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }

    public static a2.m j(a2.m mVar) {
        if (mVar == null || mVar.f238b.b() != c2.c.KNOWN_NULL) {
            return mVar;
        }
        return a2.m.f(mVar.f237a, c2.c.OBJECT);
    }

    @Override // p0.f
    public final i a() {
        return new i(new g(this));
    }

    @Override // p0.h
    public final ClipData b() {
        return (ClipData) this.f8134b;
    }

    @Override // p0.f
    public final void c(Bundle bundle) {
        this.f8138f = bundle;
    }

    @Override // p0.f
    public final void d(Uri uri) {
        this.f8137e = uri;
    }

    @Override // p0.f
    public final void e(int i7) {
        this.f8136d = i7;
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [int[], java.lang.Cloneable, java.lang.Object] */
    public final void f(int i7, int i8) {
        Object obj = this.f8138f;
        boolean z6 = ((int[]) obj) == null;
        int i9 = this.f8136d;
        if (i7 != i9 || z6) {
            if (i7 < i9) {
                throw new RuntimeException("shouldn't happen");
            }
            if (z6 || i8 >= ((int[]) obj).length) {
                int i10 = i8 + 1;
                a2.o oVar = new a2.o(i10);
                ?? r7 = new int[i10];
                Arrays.fill((int[]) r7, -1);
                if (!z6) {
                    a2.o oVar2 = (a2.o) this.f8137e;
                    int length = oVar2.f239b.length;
                    for (int i11 = 0; i11 < length; i11++) {
                        a2.m f7 = oVar2.f(i11);
                        if (f7 != null) {
                            oVar.g(f7);
                        }
                    }
                    Object obj2 = this.f8138f;
                    System.arraycopy((int[]) obj2, 0, r7, 0, ((int[]) obj2).length);
                }
                this.f8137e = oVar;
                this.f8138f = r7;
            }
        }
    }

    public final void g(int i7, w1.o oVar, a2.m mVar) {
        if (oVar == w1.o.START) {
            throw new RuntimeException("shouldn't happen");
        }
        int i8 = ((int[]) this.f8138f)[mVar.f237a];
        if (i8 >= 0) {
            ((w1.p) ((ArrayList) this.f8134b).get(i8)).getClass();
            if (i7 == 0) {
                throw null;
            }
        }
        h(i7, oVar, mVar);
    }

    public final void h(int i7, w1.o oVar, a2.m mVar) {
        a2.m j7 = j(mVar);
        int i8 = mVar.f237a;
        f(i7, i8);
        if (((int[]) this.f8138f)[i8] >= 0) {
            return;
        }
        int size = ((ArrayList) this.f8134b).size();
        do {
            size--;
            if (size < 0) {
                a2.o oVar2 = (a2.o) this.f8137e;
                oVar2.getClass();
                try {
                    oVar2.f239b[j7.f237a] = null;
                    oVar2.f240c = -1;
                    ((ArrayList) this.f8134b).set(size, null);
                    this.f8135c++;
                    for (int i9 = size - 1; i9 >= 0; i9--) {
                        if (((w1.p) ((ArrayList) this.f8134b).get(i9)) != null) {
                            throw null;
                        }
                    }
                    return;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw new IllegalArgumentException("bogus reg");
                }
            }
        } while (((w1.p) ((ArrayList) this.f8134b).get(size)) == null);
        if (i7 == 0) {
            throw null;
        }
        int i10 = j7.f237a;
        new w1.p(i7, oVar, j7);
        throw null;
    }

    @Override // p0.h
    public final int i() {
        return this.f8136d;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0036 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(int r11, a2.m r12) {
        /*
            r10 = this;
            int r0 = r12.f237a
            a2.m r12 = j(r12)
            r10.f(r11, r0)
            java.lang.Object r1 = r10.f8137e
            a2.o r1 = (a2.o) r1
            a2.m r1 = r1.f(r0)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L16
            goto L28
        L16:
            c2.d r4 = r12.f238b
            c2.c r4 = r4.b()
            c2.d r5 = r1.f238b
            c2.c r5 = r5.b()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L2d
        L28:
            r12.getClass()
        L2b:
            r4 = r2
            goto L34
        L2d:
            int r4 = r12.f237a
            int r5 = r1.f237a
            if (r4 != r5) goto L2b
            r4 = r3
        L34:
            if (r4 == 0) goto L37
            return
        L37:
            java.lang.Object r4 = r10.f8137e
            a2.o r4 = (a2.o) r4
            a2.m[] r4 = r4.f239b
            int r5 = r4.length
        L3e:
            r6 = 0
            if (r2 >= r5) goto L5c
            r7 = r4[r2]
            if (r7 != 0) goto L46
            goto L59
        L46:
            c2.d r8 = r12.f238b
            c2.c r8 = r8.b()
            c2.d r9 = r7.f238b
            c2.c r9 = r9.b()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L59
            goto L5d
        L59:
            int r2 = r2 + 1
            goto L3e
        L5c:
            r7 = r6
        L5d:
            if (r7 == 0) goto L64
            w1.o r2 = w1.o.END_MOVED
            r10.g(r11, r2, r7)
        L64:
            java.lang.Cloneable r2 = r10.f8138f
            int[] r2 = (int[]) r2
            r2 = r2[r0]
            if (r1 != 0) goto Lb6
            if (r2 < 0) goto L7f
            java.lang.Object r1 = r10.f8134b
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            java.lang.Object r1 = r1.get(r2)
            w1.p r1 = (w1.p) r1
            r1.getClass()
            if (r11 == 0) goto L7e
            goto L7f
        L7e:
            throw r6
        L7f:
            if (r0 <= 0) goto L98
            java.lang.Object r1 = r10.f8137e
            a2.o r1 = (a2.o) r1
            int r2 = r0 + (-1)
            a2.m r1 = r1.f(r2)
            if (r1 == 0) goto L98
            boolean r2 = r1.g()
            if (r2 == 0) goto L98
            w1.o r2 = w1.o.END_CLOBBERED_BY_NEXT
            r10.g(r11, r2, r1)
        L98:
            boolean r1 = r12.g()
            if (r1 == 0) goto Lae
            java.lang.Object r1 = r10.f8137e
            a2.o r1 = (a2.o) r1
            int r0 = r0 + r3
            a2.m r0 = r1.f(r0)
            if (r0 == 0) goto Lae
            w1.o r1 = w1.o.END_CLOBBERED_BY_PREV
            r10.g(r11, r1, r0)
        Lae:
            w1.o r0 = w1.o.START
            w1.p r1 = new w1.p
            r1.<init>(r11, r0, r12)
            throw r6
        Lb6:
            w1.o r12 = w1.o.END_REPLACED
            w1.p r0 = new w1.p
            r0.<init>(r11, r12, r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p0.g.k(int, a2.m):void");
    }

    @Override // p0.h
    public final ContentInfo m() {
        return null;
    }

    @Override // p0.h
    public final int q() {
        return this.f8135c;
    }

    public final String toString() {
        String str;
        switch (this.f8133a) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(((ClipData) this.f8134b).getDescription());
                sb.append(", source=");
                int i7 = this.f8135c;
                sb.append(i7 != 0 ? i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? i7 != 5 ? String.valueOf(i7) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i8 = this.f8136d;
                sb.append((i8 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i8));
                Uri uri = (Uri) this.f8137e;
                String str2 = s5.h0.FRAGMENT_ENCODE_SET;
                if (uri == null) {
                    str = s5.h0.FRAGMENT_ENCODE_SET;
                } else {
                    str = ", hasLinkUri(" + ((Uri) this.f8137e).toString().length() + ")";
                }
                sb.append(str);
                if (((Bundle) this.f8138f) != null) {
                    str2 = ", hasExtras";
                }
                return a.a.i(sb, str2, "}");
            default:
                return super.toString();
        }
    }
}
