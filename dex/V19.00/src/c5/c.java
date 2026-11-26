package c5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static void a(int i7, int i8) {
        if (i7 < 0 || i7 >= i8) {
            throw new IndexOutOfBoundsException("index: " + i7 + ", size: " + i8);
        }
    }

    public static void b(int i7, int i8) {
        if (i7 < 0 || i7 > i8) {
            throw new IndexOutOfBoundsException("index: " + i7 + ", size: " + i8);
        }
    }
}
