package s5;

import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class v0 {
    public static final u0 Companion = new u0();

    public static final v0 create(g6.l lVar, l0 l0Var) {
        Companion.getClass();
        i5.a.g("<this>", lVar);
        return new s0(l0Var, lVar, 1);
    }

    public static final v0 create(File file, l0 l0Var) {
        Companion.getClass();
        i5.a.g("<this>", file);
        return new s0(l0Var, file, 0);
    }

    public static final v0 create(String str, l0 l0Var) {
        Companion.getClass();
        return u0.a(str, l0Var);
    }

    public static final v0 create(l0 l0Var, g6.l lVar) {
        Companion.getClass();
        i5.a.g("content", lVar);
        return new s0(l0Var, lVar, 1);
    }

    public static final v0 create(l0 l0Var, File file) {
        Companion.getClass();
        i5.a.g("file", file);
        return new s0(l0Var, file, 0);
    }

    public static final v0 create(l0 l0Var, String str) {
        Companion.getClass();
        i5.a.g("content", str);
        return u0.a(str, l0Var);
    }

    public static final v0 create(l0 l0Var, byte[] bArr) {
        Companion.getClass();
        i5.a.g("content", bArr);
        return u0.b(bArr, l0Var, 0, bArr.length);
    }

    public static final v0 create(l0 l0Var, byte[] bArr, int i7) {
        Companion.getClass();
        i5.a.g("content", bArr);
        return u0.b(bArr, l0Var, i7, bArr.length);
    }

    public static final v0 create(l0 l0Var, byte[] bArr, int i7, int i8) {
        Companion.getClass();
        i5.a.g("content", bArr);
        return u0.b(bArr, l0Var, i7, i8);
    }

    public static final v0 create(byte[] bArr) {
        u0 u0Var = Companion;
        u0Var.getClass();
        i5.a.g("<this>", bArr);
        return u0.c(u0Var, bArr, null, 0, 7);
    }

    public static final v0 create(byte[] bArr, l0 l0Var) {
        u0 u0Var = Companion;
        u0Var.getClass();
        i5.a.g("<this>", bArr);
        return u0.c(u0Var, bArr, l0Var, 0, 6);
    }

    public static final v0 create(byte[] bArr, l0 l0Var, int i7) {
        u0 u0Var = Companion;
        u0Var.getClass();
        i5.a.g("<this>", bArr);
        return u0.c(u0Var, bArr, l0Var, i7, 4);
    }

    public static final v0 create(byte[] bArr, l0 l0Var, int i7, int i8) {
        Companion.getClass();
        return u0.b(bArr, l0Var, i7, i8);
    }

    public abstract long contentLength();

    public abstract l0 contentType();

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public abstract void writeTo(g6.i iVar);
}
