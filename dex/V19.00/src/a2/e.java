package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e {
    public static final c2.b LIST_Error;
    public static final c2.b LIST_Error_ArithmeticException;
    public static final c2.b LIST_Error_ClassCastException;
    public static final c2.b LIST_Error_NegativeArraySizeException;
    public static final c2.b LIST_Error_NullPointerException;
    public static final c2.b LIST_Error_Null_ArrayIndexOutOfBounds;
    public static final c2.b LIST_Error_Null_ArrayIndex_ArrayStore;
    public static final c2.b LIST_Error_Null_IllegalMonitorStateException;
    public static final c2.c TYPE_ArithmeticException;
    public static final c2.c TYPE_ArrayIndexOutOfBoundsException;
    public static final c2.c TYPE_ArrayStoreException;
    public static final c2.c TYPE_ClassCastException;
    public static final c2.c TYPE_Error;
    public static final c2.c TYPE_IllegalMonitorStateException;
    public static final c2.c TYPE_NegativeArraySizeException;
    public static final c2.c TYPE_NullPointerException;

    static {
        c2.c g7 = c2.c.g("Ljava/lang/ArithmeticException;");
        TYPE_ArithmeticException = g7;
        c2.c g8 = c2.c.g("Ljava/lang/ArrayIndexOutOfBoundsException;");
        TYPE_ArrayIndexOutOfBoundsException = g8;
        c2.c g9 = c2.c.g("Ljava/lang/ArrayStoreException;");
        TYPE_ArrayStoreException = g9;
        c2.c g10 = c2.c.g("Ljava/lang/ClassCastException;");
        TYPE_ClassCastException = g10;
        c2.c g11 = c2.c.g("Ljava/lang/Error;");
        TYPE_Error = g11;
        c2.c g12 = c2.c.g("Ljava/lang/IllegalMonitorStateException;");
        TYPE_IllegalMonitorStateException = g12;
        c2.c g13 = c2.c.g("Ljava/lang/NegativeArraySizeException;");
        TYPE_NegativeArraySizeException = g13;
        c2.c g14 = c2.c.g("Ljava/lang/NullPointerException;");
        TYPE_NullPointerException = g14;
        LIST_Error = c2.b.i(g11);
        LIST_Error_ArithmeticException = c2.b.j(g11, g7);
        LIST_Error_ClassCastException = c2.b.j(g11, g10);
        LIST_Error_NegativeArraySizeException = c2.b.j(g11, g13);
        LIST_Error_NullPointerException = c2.b.j(g11, g14);
        LIST_Error_Null_ArrayIndexOutOfBounds = c2.b.k(g11, g14, g8);
        c2.b bVar = new c2.b(4);
        bVar.g(0, g11);
        bVar.g(1, g14);
        bVar.g(2, g8);
        bVar.g(3, g9);
        LIST_Error_Null_ArrayIndex_ArrayStore = bVar;
        LIST_Error_Null_IllegalMonitorStateException = c2.b.k(g11, g14, g12);
    }
}
