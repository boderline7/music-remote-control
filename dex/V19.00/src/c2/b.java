package c2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends e2.d implements e {
    public static final b BOOLEANARR_INT;
    public static final b BYTEARR_INT;
    public static final b CHARARR_INT;
    public static final b DOUBLE;
    public static final b DOUBLEARR_INT;
    public static final b DOUBLE_DOUBLE;
    public static final b DOUBLE_DOUBLEARR_INT;
    public static final b DOUBLE_OBJECT;
    public static final b EMPTY = new b(0);
    public static final b FLOAT;
    public static final b FLOATARR_INT;
    public static final b FLOAT_FLOAT;
    public static final b FLOAT_FLOATARR_INT;
    public static final b FLOAT_OBJECT;
    public static final b INT;
    public static final b INTARR_INT;
    public static final b INT_BOOLEANARR_INT;
    public static final b INT_BYTEARR_INT;
    public static final b INT_CHARARR_INT;
    public static final b INT_INT;
    public static final b INT_INTARR_INT;
    public static final b INT_OBJECT;
    public static final b INT_SHORTARR_INT;
    public static final b LONG;
    public static final b LONGARR_INT;
    public static final b LONG_INT;
    public static final b LONG_LONG;
    public static final b LONG_LONGARR_INT;
    public static final b LONG_OBJECT;
    public static final b OBJECT;
    public static final b OBJECTARR_INT;
    public static final b OBJECT_OBJECT;
    public static final b OBJECT_OBJECTARR_INT;
    public static final b RETURN_ADDRESS;
    public static final b SHORTARR_INT;
    public static final b THROWABLE;

    static {
        c cVar = c.INT;
        INT = i(cVar);
        c cVar2 = c.LONG;
        LONG = i(cVar2);
        c cVar3 = c.FLOAT;
        FLOAT = i(cVar3);
        c cVar4 = c.DOUBLE;
        DOUBLE = i(cVar4);
        c cVar5 = c.OBJECT;
        OBJECT = i(cVar5);
        RETURN_ADDRESS = i(c.RETURN_ADDRESS);
        THROWABLE = i(c.THROWABLE);
        INT_INT = j(cVar, cVar);
        LONG_LONG = j(cVar2, cVar2);
        FLOAT_FLOAT = j(cVar3, cVar3);
        DOUBLE_DOUBLE = j(cVar4, cVar4);
        OBJECT_OBJECT = j(cVar5, cVar5);
        INT_OBJECT = j(cVar, cVar5);
        LONG_OBJECT = j(cVar2, cVar5);
        FLOAT_OBJECT = j(cVar3, cVar5);
        DOUBLE_OBJECT = j(cVar4, cVar5);
        LONG_INT = j(cVar2, cVar);
        c cVar6 = c.INT_ARRAY;
        INTARR_INT = j(cVar6, cVar);
        c cVar7 = c.LONG_ARRAY;
        LONGARR_INT = j(cVar7, cVar);
        c cVar8 = c.FLOAT_ARRAY;
        FLOATARR_INT = j(cVar8, cVar);
        c cVar9 = c.DOUBLE_ARRAY;
        DOUBLEARR_INT = j(cVar9, cVar);
        c cVar10 = c.OBJECT_ARRAY;
        OBJECTARR_INT = j(cVar10, cVar);
        c cVar11 = c.BOOLEAN_ARRAY;
        BOOLEANARR_INT = j(cVar11, cVar);
        c cVar12 = c.BYTE_ARRAY;
        BYTEARR_INT = j(cVar12, cVar);
        c cVar13 = c.CHAR_ARRAY;
        CHARARR_INT = j(cVar13, cVar);
        c cVar14 = c.SHORT_ARRAY;
        SHORTARR_INT = j(cVar14, cVar);
        INT_INTARR_INT = k(cVar, cVar6, cVar);
        LONG_LONGARR_INT = k(cVar2, cVar7, cVar);
        FLOAT_FLOATARR_INT = k(cVar3, cVar8, cVar);
        DOUBLE_DOUBLEARR_INT = k(cVar4, cVar9, cVar);
        OBJECT_OBJECTARR_INT = k(cVar5, cVar10, cVar);
        INT_BOOLEANARR_INT = k(cVar, cVar11, cVar);
        INT_BYTEARR_INT = k(cVar, cVar12, cVar);
        INT_CHARARR_INT = k(cVar, cVar13, cVar);
        INT_SHORTARR_INT = k(cVar, cVar14, cVar);
    }

    public static b i(c cVar) {
        b bVar = new b(1);
        bVar.g(0, cVar);
        return bVar;
    }

    public static b j(c cVar, c cVar2) {
        b bVar = new b(2);
        bVar.g(0, cVar);
        bVar.g(1, cVar2);
        return bVar;
    }

    public static b k(c cVar, c cVar2, c cVar3) {
        b bVar = new b(3);
        bVar.g(0, cVar);
        bVar.g(1, cVar2);
        bVar.g(2, cVar3);
        return bVar;
    }
}
