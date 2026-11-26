package w1;

import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class v {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap f9445a;

    static {
        HashMap hashMap = new HashMap(400);
        f9445a = hashMap;
        hashMap.put(a2.r.NOP, k.NOP);
        a2.p pVar = a2.r.MOVE_INT;
        j jVar = k.MOVE;
        hashMap.put(pVar, jVar);
        a2.p pVar2 = a2.r.MOVE_LONG;
        j jVar2 = k.MOVE_WIDE;
        hashMap.put(pVar2, jVar2);
        hashMap.put(a2.r.MOVE_FLOAT, jVar);
        hashMap.put(a2.r.MOVE_DOUBLE, jVar2);
        a2.p pVar3 = a2.r.MOVE_OBJECT;
        j jVar3 = k.MOVE_OBJECT;
        hashMap.put(pVar3, jVar3);
        hashMap.put(a2.r.MOVE_PARAM_INT, jVar);
        hashMap.put(a2.r.MOVE_PARAM_LONG, jVar2);
        hashMap.put(a2.r.MOVE_PARAM_FLOAT, jVar);
        hashMap.put(a2.r.MOVE_PARAM_DOUBLE, jVar2);
        hashMap.put(a2.r.MOVE_PARAM_OBJECT, jVar3);
        a2.p pVar4 = a2.r.CONST_INT;
        j jVar4 = k.CONST_4;
        hashMap.put(pVar4, jVar4);
        a2.p pVar5 = a2.r.CONST_LONG;
        j jVar5 = k.CONST_WIDE_16;
        hashMap.put(pVar5, jVar5);
        hashMap.put(a2.r.CONST_FLOAT, jVar4);
        hashMap.put(a2.r.CONST_DOUBLE, jVar5);
        hashMap.put(a2.r.CONST_OBJECT_NOTHROW, jVar4);
        hashMap.put(a2.r.GOTO, k.GOTO);
        a2.p pVar6 = a2.r.IF_EQZ_INT;
        j jVar6 = k.IF_EQZ;
        hashMap.put(pVar6, jVar6);
        a2.p pVar7 = a2.r.IF_NEZ_INT;
        j jVar7 = k.IF_NEZ;
        hashMap.put(pVar7, jVar7);
        hashMap.put(a2.r.IF_LTZ_INT, k.IF_LTZ);
        hashMap.put(a2.r.IF_GEZ_INT, k.IF_GEZ);
        hashMap.put(a2.r.IF_LEZ_INT, k.IF_LEZ);
        hashMap.put(a2.r.IF_GTZ_INT, k.IF_GTZ);
        hashMap.put(a2.r.IF_EQZ_OBJECT, jVar6);
        hashMap.put(a2.r.IF_NEZ_OBJECT, jVar7);
        a2.p pVar8 = a2.r.IF_EQ_INT;
        j jVar8 = k.IF_EQ;
        hashMap.put(pVar8, jVar8);
        a2.p pVar9 = a2.r.IF_NE_INT;
        j jVar9 = k.IF_NE;
        hashMap.put(pVar9, jVar9);
        hashMap.put(a2.r.IF_LT_INT, k.IF_LT);
        hashMap.put(a2.r.IF_GE_INT, k.IF_GE);
        hashMap.put(a2.r.IF_LE_INT, k.IF_LE);
        hashMap.put(a2.r.IF_GT_INT, k.IF_GT);
        hashMap.put(a2.r.IF_EQ_OBJECT, jVar8);
        hashMap.put(a2.r.IF_NE_OBJECT, jVar9);
        hashMap.put(a2.r.SWITCH, k.SPARSE_SWITCH);
        hashMap.put(a2.r.ADD_INT, k.ADD_INT_2ADDR);
        hashMap.put(a2.r.ADD_LONG, k.ADD_LONG_2ADDR);
        hashMap.put(a2.r.ADD_FLOAT, k.ADD_FLOAT_2ADDR);
        hashMap.put(a2.r.ADD_DOUBLE, k.ADD_DOUBLE_2ADDR);
        hashMap.put(a2.r.SUB_INT, k.SUB_INT_2ADDR);
        hashMap.put(a2.r.SUB_LONG, k.SUB_LONG_2ADDR);
        hashMap.put(a2.r.SUB_FLOAT, k.SUB_FLOAT_2ADDR);
        hashMap.put(a2.r.SUB_DOUBLE, k.SUB_DOUBLE_2ADDR);
        hashMap.put(a2.r.MUL_INT, k.MUL_INT_2ADDR);
        hashMap.put(a2.r.MUL_LONG, k.MUL_LONG_2ADDR);
        hashMap.put(a2.r.MUL_FLOAT, k.MUL_FLOAT_2ADDR);
        hashMap.put(a2.r.MUL_DOUBLE, k.MUL_DOUBLE_2ADDR);
        hashMap.put(a2.r.DIV_INT, k.DIV_INT_2ADDR);
        hashMap.put(a2.r.DIV_LONG, k.DIV_LONG_2ADDR);
        hashMap.put(a2.r.DIV_FLOAT, k.DIV_FLOAT_2ADDR);
        hashMap.put(a2.r.DIV_DOUBLE, k.DIV_DOUBLE_2ADDR);
        hashMap.put(a2.r.REM_INT, k.REM_INT_2ADDR);
        hashMap.put(a2.r.REM_LONG, k.REM_LONG_2ADDR);
        hashMap.put(a2.r.REM_FLOAT, k.REM_FLOAT_2ADDR);
        hashMap.put(a2.r.REM_DOUBLE, k.REM_DOUBLE_2ADDR);
        hashMap.put(a2.r.NEG_INT, k.NEG_INT);
        hashMap.put(a2.r.NEG_LONG, k.NEG_LONG);
        hashMap.put(a2.r.NEG_FLOAT, k.NEG_FLOAT);
        hashMap.put(a2.r.NEG_DOUBLE, k.NEG_DOUBLE);
        hashMap.put(a2.r.AND_INT, k.AND_INT_2ADDR);
        hashMap.put(a2.r.AND_LONG, k.AND_LONG_2ADDR);
        hashMap.put(a2.r.OR_INT, k.OR_INT_2ADDR);
        hashMap.put(a2.r.OR_LONG, k.OR_LONG_2ADDR);
        hashMap.put(a2.r.XOR_INT, k.XOR_INT_2ADDR);
        hashMap.put(a2.r.XOR_LONG, k.XOR_LONG_2ADDR);
        hashMap.put(a2.r.SHL_INT, k.SHL_INT_2ADDR);
        hashMap.put(a2.r.SHL_LONG, k.SHL_LONG_2ADDR);
        hashMap.put(a2.r.SHR_INT, k.SHR_INT_2ADDR);
        hashMap.put(a2.r.SHR_LONG, k.SHR_LONG_2ADDR);
        hashMap.put(a2.r.USHR_INT, k.USHR_INT_2ADDR);
        hashMap.put(a2.r.USHR_LONG, k.USHR_LONG_2ADDR);
        hashMap.put(a2.r.NOT_INT, k.NOT_INT);
        hashMap.put(a2.r.NOT_LONG, k.NOT_LONG);
        hashMap.put(a2.r.ADD_CONST_INT, k.ADD_INT_LIT8);
        hashMap.put(a2.r.SUB_CONST_INT, k.RSUB_INT_LIT8);
        hashMap.put(a2.r.MUL_CONST_INT, k.MUL_INT_LIT8);
        hashMap.put(a2.r.DIV_CONST_INT, k.DIV_INT_LIT8);
        hashMap.put(a2.r.REM_CONST_INT, k.REM_INT_LIT8);
        hashMap.put(a2.r.AND_CONST_INT, k.AND_INT_LIT8);
        hashMap.put(a2.r.OR_CONST_INT, k.OR_INT_LIT8);
        hashMap.put(a2.r.XOR_CONST_INT, k.XOR_INT_LIT8);
        hashMap.put(a2.r.SHL_CONST_INT, k.SHL_INT_LIT8);
        hashMap.put(a2.r.SHR_CONST_INT, k.SHR_INT_LIT8);
        hashMap.put(a2.r.USHR_CONST_INT, k.USHR_INT_LIT8);
        hashMap.put(a2.r.CMPL_LONG, k.CMP_LONG);
        hashMap.put(a2.r.CMPL_FLOAT, k.CMPL_FLOAT);
        hashMap.put(a2.r.CMPL_DOUBLE, k.CMPL_DOUBLE);
        hashMap.put(a2.r.CMPG_FLOAT, k.CMPG_FLOAT);
        hashMap.put(a2.r.CMPG_DOUBLE, k.CMPG_DOUBLE);
        hashMap.put(a2.r.CONV_L2I, k.LONG_TO_INT);
        hashMap.put(a2.r.CONV_F2I, k.FLOAT_TO_INT);
        hashMap.put(a2.r.CONV_D2I, k.DOUBLE_TO_INT);
        hashMap.put(a2.r.CONV_I2L, k.INT_TO_LONG);
        hashMap.put(a2.r.CONV_F2L, k.FLOAT_TO_LONG);
        hashMap.put(a2.r.CONV_D2L, k.DOUBLE_TO_LONG);
        hashMap.put(a2.r.CONV_I2F, k.INT_TO_FLOAT);
        hashMap.put(a2.r.CONV_L2F, k.LONG_TO_FLOAT);
        hashMap.put(a2.r.CONV_D2F, k.DOUBLE_TO_FLOAT);
        hashMap.put(a2.r.CONV_I2D, k.INT_TO_DOUBLE);
        hashMap.put(a2.r.CONV_L2D, k.LONG_TO_DOUBLE);
        hashMap.put(a2.r.CONV_F2D, k.FLOAT_TO_DOUBLE);
        hashMap.put(a2.r.TO_BYTE, k.INT_TO_BYTE);
        hashMap.put(a2.r.TO_CHAR, k.INT_TO_CHAR);
        hashMap.put(a2.r.TO_SHORT, k.INT_TO_SHORT);
        hashMap.put(a2.r.RETURN_VOID, k.RETURN_VOID);
        a2.p pVar10 = a2.r.RETURN_INT;
        j jVar10 = k.RETURN;
        hashMap.put(pVar10, jVar10);
        a2.p pVar11 = a2.r.RETURN_LONG;
        j jVar11 = k.RETURN_WIDE;
        hashMap.put(pVar11, jVar11);
        hashMap.put(a2.r.RETURN_FLOAT, jVar10);
        hashMap.put(a2.r.RETURN_DOUBLE, jVar11);
        hashMap.put(a2.r.RETURN_OBJECT, k.RETURN_OBJECT);
        hashMap.put(a2.r.ARRAY_LENGTH, k.ARRAY_LENGTH);
        hashMap.put(a2.r.THROW, k.THROW);
        hashMap.put(a2.r.MONITOR_ENTER, k.MONITOR_ENTER);
        hashMap.put(a2.r.MONITOR_EXIT, k.MONITOR_EXIT);
        a2.p pVar12 = a2.r.AGET_INT;
        j jVar12 = k.AGET;
        hashMap.put(pVar12, jVar12);
        a2.p pVar13 = a2.r.AGET_LONG;
        j jVar13 = k.AGET_WIDE;
        hashMap.put(pVar13, jVar13);
        hashMap.put(a2.r.AGET_FLOAT, jVar12);
        hashMap.put(a2.r.AGET_DOUBLE, jVar13);
        hashMap.put(a2.r.AGET_OBJECT, k.AGET_OBJECT);
        hashMap.put(a2.r.AGET_BOOLEAN, k.AGET_BOOLEAN);
        hashMap.put(a2.r.AGET_BYTE, k.AGET_BYTE);
        hashMap.put(a2.r.AGET_CHAR, k.AGET_CHAR);
        hashMap.put(a2.r.AGET_SHORT, k.AGET_SHORT);
        a2.p pVar14 = a2.r.APUT_INT;
        j jVar14 = k.APUT;
        hashMap.put(pVar14, jVar14);
        a2.p pVar15 = a2.r.APUT_LONG;
        j jVar15 = k.APUT_WIDE;
        hashMap.put(pVar15, jVar15);
        hashMap.put(a2.r.APUT_FLOAT, jVar14);
        hashMap.put(a2.r.APUT_DOUBLE, jVar15);
        hashMap.put(a2.r.APUT_OBJECT, k.APUT_OBJECT);
        hashMap.put(a2.r.APUT_BOOLEAN, k.APUT_BOOLEAN);
        hashMap.put(a2.r.APUT_BYTE, k.APUT_BYTE);
        hashMap.put(a2.r.APUT_CHAR, k.APUT_CHAR);
        hashMap.put(a2.r.APUT_SHORT, k.APUT_SHORT);
        hashMap.put(a2.r.NEW_INSTANCE, k.NEW_INSTANCE);
        hashMap.put(a2.r.CHECK_CAST, k.CHECK_CAST);
        hashMap.put(a2.r.INSTANCE_OF, k.INSTANCE_OF);
        a2.p pVar16 = a2.r.GET_FIELD_LONG;
        j jVar16 = k.IGET_WIDE;
        hashMap.put(pVar16, jVar16);
        hashMap.put(a2.r.GET_FIELD_FLOAT, k.IGET);
        hashMap.put(a2.r.GET_FIELD_DOUBLE, jVar16);
        hashMap.put(a2.r.GET_FIELD_OBJECT, k.IGET_OBJECT);
        a2.p pVar17 = a2.r.GET_STATIC_LONG;
        j jVar17 = k.SGET_WIDE;
        hashMap.put(pVar17, jVar17);
        hashMap.put(a2.r.GET_STATIC_FLOAT, k.SGET);
        hashMap.put(a2.r.GET_STATIC_DOUBLE, jVar17);
        hashMap.put(a2.r.GET_STATIC_OBJECT, k.SGET_OBJECT);
        a2.p pVar18 = a2.r.PUT_FIELD_LONG;
        j jVar18 = k.IPUT_WIDE;
        hashMap.put(pVar18, jVar18);
        hashMap.put(a2.r.PUT_FIELD_FLOAT, k.IPUT);
        hashMap.put(a2.r.PUT_FIELD_DOUBLE, jVar18);
        hashMap.put(a2.r.PUT_FIELD_OBJECT, k.IPUT_OBJECT);
        a2.p pVar19 = a2.r.PUT_STATIC_LONG;
        j jVar19 = k.SPUT_WIDE;
        hashMap.put(pVar19, jVar19);
        hashMap.put(a2.r.PUT_STATIC_FLOAT, k.SPUT);
        hashMap.put(a2.r.PUT_STATIC_DOUBLE, jVar19);
        hashMap.put(a2.r.PUT_STATIC_OBJECT, k.SPUT_OBJECT);
    }

    public static j a(a2.g gVar) {
        a2.p pVar = gVar.f228a;
        j jVar = (j) f9445a.get(pVar);
        if (jVar != null) {
            return jVar;
        }
        int i7 = pVar.f241a;
        if (i7 == 4) {
            return k.MOVE_EXCEPTION;
        }
        if (i7 == 5) {
            b2.a aVar = ((a2.t) gVar).f227e;
            if (aVar instanceof b2.v) {
                return k.CONST_CLASS;
            }
            if (aVar instanceof b2.u) {
                return k.CONST_STRING;
            }
            throw new RuntimeException("Unexpected constant type");
        }
        if (i7 == 41) {
            return k.NEW_ARRAY;
        }
        if (i7 == 42) {
            return k.FILLED_NEW_ARRAY;
        }
        if (i7 == 55) {
            a2.m mVar = gVar.f230c;
            if (mVar == null) {
                return k.NOP;
            }
            switch (mVar.f238b.c()) {
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case 8:
                    return k.MOVE_RESULT;
                case 4:
                case 7:
                    return k.MOVE_RESULT_WIDE;
                case 9:
                    return k.MOVE_RESULT_OBJECT;
                default:
                    throw new RuntimeException("Unexpected basic type");
            }
        }
        switch (i7) {
            case 45:
                int i8 = ((b2.i) ((a2.t) gVar).f227e).b().f3041b;
                if (i8 == 1) {
                    return k.IGET_BOOLEAN;
                }
                if (i8 == 2) {
                    return k.IGET_BYTE;
                }
                if (i8 == 3) {
                    return k.IGET_CHAR;
                }
                if (i8 == 6) {
                    return k.IGET;
                }
                if (i8 == 8) {
                    return k.IGET_SHORT;
                }
                break;
            case 46:
                int i9 = ((b2.i) ((a2.t) gVar).f227e).b().f3041b;
                if (i9 == 1) {
                    return k.SGET_BOOLEAN;
                }
                if (i9 == 2) {
                    return k.SGET_BYTE;
                }
                if (i9 == 3) {
                    return k.SGET_CHAR;
                }
                if (i9 == 6) {
                    return k.SGET;
                }
                if (i9 == 8) {
                    return k.SGET_SHORT;
                }
                break;
            case 47:
                int i10 = ((b2.i) ((a2.t) gVar).f227e).b().f3041b;
                if (i10 == 1) {
                    return k.IPUT_BOOLEAN;
                }
                if (i10 == 2) {
                    return k.IPUT_BYTE;
                }
                if (i10 == 3) {
                    return k.IPUT_CHAR;
                }
                if (i10 == 6) {
                    return k.IPUT;
                }
                if (i10 == 8) {
                    return k.IPUT_SHORT;
                }
                break;
            case 48:
                int i11 = ((b2.i) ((a2.t) gVar).f227e).b().f3041b;
                if (i11 == 1) {
                    return k.SPUT_BOOLEAN;
                }
                if (i11 == 2) {
                    return k.SPUT_BYTE;
                }
                if (i11 == 3) {
                    return k.SPUT_CHAR;
                }
                if (i11 == 6) {
                    return k.SPUT;
                }
                if (i11 == 8) {
                    return k.SPUT_SHORT;
                }
                break;
            case 49:
                return k.INVOKE_STATIC;
            case 50:
                return k.INVOKE_VIRTUAL;
            case 51:
                return k.INVOKE_SUPER;
            case 52:
                return k.INVOKE_DIRECT;
            case 53:
                return k.INVOKE_INTERFACE;
            default:
                switch (i7) {
                    case z1.c.IF_NEZ /* 57 */:
                        return k.FILL_ARRAY_DATA;
                    case z1.c.IF_LTZ /* 58 */:
                        return k.INVOKE_POLYMORPHIC;
                    case z1.c.IF_GEZ /* 59 */:
                        return k.INVOKE_CUSTOM;
                }
        }
        throw new RuntimeException("unknown rop: " + pVar);
    }
}
