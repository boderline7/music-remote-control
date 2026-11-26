package a1;

import a2.t;
import android.widget.EditText;
import androidx.appcompat.widget.u;
import java.lang.reflect.Array;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements a2.f {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f197a;

    /* renamed from: b, reason: collision with root package name */
    public final int f198b;

    /* renamed from: c, reason: collision with root package name */
    public final int f199c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f200d;

    public b() {
        this.f197a = 5;
        this.f200d = new b[256];
        this.f198b = 0;
        this.f199c = 0;
    }

    public b(int i7, int i8, int i9) {
        this.f197a = i9;
        if (i9 != 5) {
            this.f200d = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i8, i7);
            this.f198b = i7;
            this.f199c = i8;
        } else {
            this.f200d = null;
            this.f198b = i7;
            int i10 = i8 & 7;
            this.f199c = i10 == 0 ? 8 : i10;
        }
    }

    public b(int i7, int i8, o2.j jVar) {
        this.f197a = 3;
        this.f198b = i7;
        this.f199c = i8;
        this.f200d = jVar;
    }

    public b(EditText editText) {
        this.f197a = 0;
        this.f198b = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f199c = 0;
        if (editText == null) {
            throw new NullPointerException("editText cannot be null");
        }
        this.f200d = new a(editText);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public b(byte[] bArr) {
        this(bArr, bArr.length);
        this.f197a = 2;
    }

    public b(byte[] bArr, int i7) {
        this.f197a = 2;
        if (i7 < 0) {
            throw new IllegalArgumentException("end < start");
        }
        if (i7 > bArr.length) {
            throw new IllegalArgumentException("end > bytes.length");
        }
        this.f200d = bArr;
        this.f198b = 0;
        this.f199c = i7;
    }

    public b(boolean[] zArr, int i7, int i8) {
        this.f197a = 1;
        this.f200d = zArr;
        this.f198b = i7;
        this.f199c = i8;
    }

    public final byte a(int i7, int i8) {
        return ((byte[][]) this.f200d)[i8][i7];
    }

    public final void b(int i7, int i8, int i9) {
        ((byte[][]) this.f200d)[i8][i7] = (byte) i9;
    }

    @Override // a2.f
    public final /* bridge */ /* synthetic */ void c(a2.j jVar) {
    }

    @Override // a2.f
    public final void e(a2.i iVar) {
        if (iVar.f228a.f241a == 3) {
            int i7 = ((b2.k) iVar.f227e).f2934a;
            Object obj = this.f200d;
            ((boolean[]) obj)[0] = ((boolean[]) obj)[0] && (this.f198b - this.f199c) + i7 == iVar.f230c.f237a;
        }
    }

    @Override // a2.f
    public final /* bridge */ /* synthetic */ void f(t tVar) {
    }

    @Override // a2.f
    public final /* bridge */ /* synthetic */ void g(a2.u uVar) {
    }

    public final String toString() {
        switch (this.f197a) {
            case 4:
                StringBuilder sb = new StringBuilder((this.f198b * 2 * this.f199c) + 2);
                for (int i7 = 0; i7 < this.f199c; i7++) {
                    byte[] bArr = ((byte[][]) this.f200d)[i7];
                    for (int i8 = 0; i8 < this.f198b; i8++) {
                        byte b7 = bArr[i8];
                        if (b7 == 0) {
                            sb.append(" 0");
                        } else if (b7 != 1) {
                            sb.append("  ");
                        } else {
                            sb.append(" 1");
                        }
                    }
                    sb.append('\n');
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }
}
