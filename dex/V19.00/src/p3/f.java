package p3;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Path;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.method.KeyListener;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.core.widget.NestedScrollView;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class f implements androidx.core.widget.m, h1.e, w3.d, a4.b, a2.f {

    /* renamed from: b, reason: collision with root package name */
    public static f f8235b;

    /* renamed from: c, reason: collision with root package name */
    public static Field f8236c;

    /* renamed from: d, reason: collision with root package name */
    public static boolean f8237d;

    /* renamed from: a, reason: collision with root package name */
    public int f8238a;

    public /* synthetic */ f() {
        this.f8238a = 1;
    }

    public /* synthetic */ f(int i7) {
        this.f8238a = i7;
    }

    public /* synthetic */ f(int i7, int i8) {
        this.f8238a = i7;
    }

    public static byte[][] C(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, bArr[0].length, bArr.length);
        for (int i7 = 0; i7 < bArr.length; i7++) {
            int length = (bArr.length - i7) - 1;
            for (int i8 = 0; i8 < bArr[0].length; i8++) {
                bArr2[i8][length] = bArr[i7][i8];
            }
        }
        return bArr2;
    }

    public static void M(a4.c cVar, StringBuilder sb) {
        int charAt = sb.charAt(2) + (sb.charAt(1) * '(') + (sb.charAt(0) * 1600) + 1;
        ((StringBuilder) cVar.f274h).append(new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)}));
        sb.delete(0, 3);
    }

    public static y3.b d(byte[][] bArr, int i7) {
        int i8 = i7 * 2;
        int length = bArr[0].length + i8;
        int length2 = bArr.length + i8;
        y3.b bVar = new y3.b(length, length2);
        int[] iArr = bVar.f10177d;
        int length3 = iArr.length;
        for (int i9 = 0; i9 < length3; i9++) {
            iArr[i9] = 0;
        }
        int i10 = (length2 - i7) - 1;
        int i11 = 0;
        while (i11 < bArr.length) {
            byte[] bArr2 = bArr[i11];
            for (int i12 = 0; i12 < bArr[0].length; i12++) {
                if (bArr2[i12] == 1) {
                    bVar.b(i12 + i7, i10);
                }
            }
            i11++;
            i10--;
        }
        return bVar;
    }

    private static y3.b h(String str, w3.a aVar, int i7, int i8, HashMap hashMap) {
        w3.d lVar;
        int i9 = 1;
        int i10 = 2;
        int i11 = 0;
        switch (w3.c.f9469a[aVar.ordinal()]) {
            case 1:
                lVar = new b4.l(1);
                break;
            case 2:
                lVar = new b4.l(2);
                break;
            case 3:
                lVar = new b4.l(0);
                break;
            case 4:
                lVar = new v2.a(14);
                break;
            case 5:
                lVar = new f(28, i11);
                break;
            case 6:
                lVar = new b4.i(i9);
                break;
            case 7:
                lVar = new b4.i(i10);
                break;
            case 8:
                lVar = new b4.i(i11);
                break;
            case 9:
                lVar = new b4.m();
                break;
            case 10:
                lVar = new f(27, i11);
                break;
            case 11:
                lVar = new b4.b();
                break;
            case 12:
                lVar = new f(22, i11);
                break;
            case 13:
                lVar = new f(21, i11);
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + aVar);
        }
        return lVar.a(str, aVar, i7, i8, hashMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static y3.b i(java.lang.String r19, w3.a r20, int r21, int r22, java.util.HashMap r23) {
        /*
            Method dump skipped, instructions count: 1281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.f.i(java.lang.String, w3.a, int, int, java.util.HashMap):y3.b");
    }

    /* JADX WARN: Removed duplicated region for block: B:232:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0330  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static y3.b k(java.lang.String r23, w3.a r24, int r25, int r26, java.util.HashMap r27) {
        /*
            Method dump skipped, instructions count: 1754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.f.k(java.lang.String, w3.a, int, int, java.util.HashMap):y3.b");
    }

    public static String m(StringBuilder sb) {
        int length = sb.length();
        if (length == 0) {
            throw new IllegalStateException("StringBuilder must not be empty");
        }
        int charAt = (sb.charAt(0) << 18) + ((length >= 2 ? sb.charAt(1) : (char) 0) << '\f') + ((length >= 3 ? sb.charAt(2) : (char) 0) << 6) + (length >= 4 ? sb.charAt(3) : (char) 0);
        char c7 = (char) ((charAt >> 16) & 255);
        char c8 = (char) ((charAt >> 8) & 255);
        char c9 = (char) (charAt & 255);
        StringBuilder sb2 = new StringBuilder(3);
        sb2.append(c7);
        if (length >= 2) {
            sb2.append(c8);
        }
        if (length >= 3) {
            sb2.append(c9);
        }
        return sb2.toString();
    }

    public static Path t(float f7, float f8, float f9, float f10) {
        Path path = new Path();
        path.moveTo(f7, f8);
        path.lineTo(f9, f10);
        return path;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004b, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0088, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x007b, code lost:
    
        if (r11 != false) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean v(android.view.inputmethod.InputConnection r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.f.v(android.view.inputmethod.InputConnection, android.text.Editable, int, int, boolean):boolean");
    }

    public boolean A(Spannable spannable) {
        return false;
    }

    public InputConnection B(InputConnection inputConnection, EditorInfo editorInfo) {
        return inputConnection;
    }

    public void D(boolean z6) {
    }

    public void E(boolean z6) {
    }

    public void F(boolean z6) {
    }

    public void G(boolean z6) {
    }

    public void H() {
    }

    public void I(int i7, View view) {
        if (!f8237d) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f8236c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f8237d = true;
        }
        Field field = f8236c;
        if (field != null) {
            try {
                f8236c.setInt(view, i7 | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void J() {
    }

    public void K(a2.g gVar) {
        a2.m mVar = gVar.f230c;
        if (mVar != null) {
            int e7 = mVar.e() + mVar.f237a;
            if (e7 > this.f8238a) {
                this.f8238a = e7;
            }
        }
        a2.n nVar = gVar.f231d;
        int length = nVar.f6354b.length;
        for (int i7 = 0; i7 < length; i7++) {
            a2.m mVar2 = (a2.m) nVar.f(i7);
            int e8 = mVar2.e() + mVar2.f237a;
            if (e8 > this.f8238a) {
                this.f8238a = e8;
            }
        }
    }

    public TransformationMethod L(TransformationMethod transformationMethod) {
        return transformationMethod;
    }

    /* JADX WARN: Code restructure failed: missing block: B:199:0x04c4, code lost:
    
        if (r1 < 8) goto L232;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0297 A[LOOP:29: B:393:0x0295->B:394:0x0297, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:397:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x06fd  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x096d  */
    /* JADX WARN: Removed duplicated region for block: B:600:0x09e9  */
    /* JADX WARN: Removed duplicated region for block: B:609:0x09e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:655:0x0967 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:689:0x0b22  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x0b39  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x0c61 A[LOOP:49: B:737:0x0c5f->B:738:0x0c61, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:742:0x0c7c  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x0c87  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02e3 A[LOOP:2: B:75:0x02e1->B:76:0x02e3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:777:0x0d11  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02f6  */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.appcompat.app.n[], java.io.Serializable] */
    @Override // w3.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public y3.b a(java.lang.String r32, w3.a r33, int r34, int r35, java.util.HashMap r36) {
        /*
            Method dump skipped, instructions count: 3440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.f.a(java.lang.String, w3.a, int, int, java.util.HashMap):y3.b");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00af, code lost:
    
        if (r8 <= 2) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0062, code lost:
    
        l6.f.K(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0065, code lost:
    
        throw null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(a4.c r12) {
        /*
            Method dump skipped, instructions count: 796
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p3.f.b(a4.c):void");
    }

    @Override // a2.f
    public void c(a2.j jVar) {
        K(jVar);
    }

    @Override // a2.f
    public void e(a2.i iVar) {
        K(iVar);
    }

    @Override // a2.f
    public void f(a2.t tVar) {
        K(tVar);
    }

    @Override // a2.f
    public void g(a2.u uVar) {
        K(uVar);
    }

    @Override // h1.e
    public void j() {
    }

    public int l(char c7, StringBuilder sb) {
        if (c7 == ' ') {
            sb.append((char) 3);
            return 1;
        }
        if (c7 >= '0' && c7 <= '9') {
            sb.append((char) (c7 - ','));
            return 1;
        }
        if (c7 >= 'A' && c7 <= 'Z') {
            sb.append((char) (c7 - '3'));
            return 1;
        }
        if (c7 < ' ') {
            sb.append((char) 0);
            sb.append(c7);
            return 2;
        }
        if (c7 <= '/') {
            sb.append((char) 1);
            sb.append((char) (c7 - '!'));
            return 2;
        }
        if (c7 <= '@') {
            sb.append((char) 1);
            sb.append((char) (c7 - '+'));
            return 2;
        }
        if (c7 <= '_') {
            sb.append((char) 1);
            sb.append((char) (c7 - 'E'));
            return 2;
        }
        if (c7 > 127) {
            sb.append("\u0001\u001e");
            return l((char) (c7 - 128), sb) + 2;
        }
        sb.append((char) 2);
        sb.append((char) (c7 - '`'));
        return 2;
    }

    @Override // h1.e
    public void n(int i7, Object obj) {
        switch (this.f8238a) {
            case 14:
                break;
            default:
                if (i7 == 6 || i7 == 7 || i7 == 8) {
                    break;
                }
                break;
        }
    }

    public boolean o() {
        return this instanceof g;
    }

    public void p(float f7, float f8, float f9, z zVar) {
        zVar.d(f7, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public int q() {
        switch (this.f8238a) {
            case 23:
                return 0;
            case 24:
                return 5;
            case 25:
                return 1;
            default:
                return 4;
        }
    }

    public InputFilter[] r(InputFilter[] inputFilterArr) {
        return inputFilterArr;
    }

    public KeyListener s(KeyListener keyListener) {
        return keyListener;
    }

    public Signature[] u(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    public void w(a4.c cVar, StringBuilder sb) {
        int length = (sb.length() / 3) * 2;
        int length2 = sb.length() % 3;
        int a7 = cVar.a() + length;
        cVar.d(a7);
        int i7 = ((a4.j) cVar.f275i).f297b - a7;
        if (length2 == 2) {
            sb.append((char) 0);
            while (sb.length() >= 3) {
                M(cVar, sb);
            }
            if (cVar.c()) {
                cVar.e((char) 254);
            }
        } else if (i7 == 1 && length2 == 1) {
            while (sb.length() >= 3) {
                M(cVar, sb);
            }
            if (cVar.c()) {
                cVar.e((char) 254);
            }
            cVar.f267a--;
        } else {
            if (length2 != 0) {
                throw new IllegalStateException("Unexpected case. Please report!");
            }
            while (sb.length() >= 3) {
                M(cVar, sb);
            }
            if (i7 > 0 || cVar.c()) {
                cVar.e((char) 254);
            }
        }
        cVar.f268b = 0;
    }

    @Override // androidx.core.widget.m
    public void x(NestedScrollView nestedScrollView) {
    }

    public void y(int i7) {
    }

    public boolean z() {
        return false;
    }
}
