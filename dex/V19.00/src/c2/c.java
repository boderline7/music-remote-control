package c2;

import java.util.concurrent.ConcurrentHashMap;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements d, Comparable {
    public static final c ANNOTATION;
    public static final c BOOLEAN;
    public static final c BOOLEAN_ARRAY;
    public static final c BOOLEAN_CLASS;
    public static final int BT_ADDR = 10;
    public static final int BT_BOOLEAN = 1;
    public static final int BT_BYTE = 2;
    public static final int BT_CHAR = 3;
    public static final int BT_COUNT = 11;
    public static final int BT_DOUBLE = 4;
    public static final int BT_FLOAT = 5;
    public static final int BT_INT = 6;
    public static final int BT_LONG = 7;
    public static final int BT_OBJECT = 9;
    public static final int BT_SHORT = 8;
    public static final int BT_VOID = 0;
    public static final c BYTE;
    public static final c BYTE_ARRAY;
    public static final c BYTE_CLASS;
    public static final c CHAR;
    public static final c CHARACTER_CLASS;
    public static final c CHAR_ARRAY;
    public static final c CLASS;
    public static final c CLONEABLE;
    public static final c DOUBLE;
    public static final c DOUBLE_ARRAY;
    public static final c DOUBLE_CLASS;
    public static final c FLOAT;
    public static final c FLOAT_ARRAY;
    public static final c FLOAT_CLASS;
    public static final c INT;
    public static final c INTEGER_CLASS;
    public static final c INT_ARRAY;
    public static final c KNOWN_NULL;
    public static final c LONG;
    public static final c LONG_ARRAY;
    public static final c LONG_CLASS;
    public static final c METHOD_HANDLE;
    public static final c METHOD_TYPE;
    public static final c OBJECT;
    public static final c OBJECT_ARRAY;
    public static final c RETURN_ADDRESS;
    public static final c SERIALIZABLE;
    public static final c SHORT;
    public static final c SHORT_ARRAY;
    public static final c SHORT_CLASS;
    public static final c STRING;
    public static final c THROWABLE;
    public static final c VAR_HANDLE;
    public static final c VOID;
    public static final c VOID_CLASS;

    /* renamed from: f, reason: collision with root package name */
    public static final ConcurrentHashMap f3039f = new ConcurrentHashMap(10000, 0.75f);

    /* renamed from: a, reason: collision with root package name */
    public final String f3040a;

    /* renamed from: b, reason: collision with root package name */
    public final int f3041b;

    /* renamed from: c, reason: collision with root package name */
    public String f3042c;

    /* renamed from: d, reason: collision with root package name */
    public c f3043d;

    /* renamed from: e, reason: collision with root package name */
    public c f3044e;

    static {
        c cVar = new c("Z", 1);
        BOOLEAN = cVar;
        c cVar2 = new c("B", 2);
        BYTE = cVar2;
        c cVar3 = new c("C", 3);
        CHAR = cVar3;
        c cVar4 = new c("D", 4);
        DOUBLE = cVar4;
        c cVar5 = new c("F", 5);
        FLOAT = cVar5;
        c cVar6 = new c("I", 6);
        INT = cVar6;
        c cVar7 = new c("J", 7);
        LONG = cVar7;
        c cVar8 = new c("S", 8);
        SHORT = cVar8;
        VOID = new c("V", 0);
        KNOWN_NULL = new c("<null>", 9);
        RETURN_ADDRESS = new c("<addr>", 10);
        c cVar9 = new c("Ljava/lang/annotation/Annotation;", 9);
        ANNOTATION = cVar9;
        c cVar10 = new c("Ljava/lang/Class;", 9);
        CLASS = cVar10;
        c cVar11 = new c("Ljava/lang/Cloneable;", 9);
        CLONEABLE = cVar11;
        c cVar12 = new c("Ljava/lang/invoke/MethodHandle;", 9);
        METHOD_HANDLE = cVar12;
        METHOD_TYPE = new c("Ljava/lang/invoke/MethodType;", 9);
        c cVar13 = new c("Ljava/lang/invoke/VarHandle;", 9);
        VAR_HANDLE = cVar13;
        c cVar14 = new c("Ljava/lang/Object;", 9);
        OBJECT = cVar14;
        c cVar15 = new c("Ljava/io/Serializable;", 9);
        SERIALIZABLE = cVar15;
        c cVar16 = new c("Ljava/lang/String;", 9);
        STRING = cVar16;
        c cVar17 = new c("Ljava/lang/Throwable;", 9);
        THROWABLE = cVar17;
        c cVar18 = new c("Ljava/lang/Boolean;", 9);
        BOOLEAN_CLASS = cVar18;
        c cVar19 = new c("Ljava/lang/Byte;", 9);
        BYTE_CLASS = cVar19;
        c cVar20 = new c("Ljava/lang/Character;", 9);
        CHARACTER_CLASS = cVar20;
        c cVar21 = new c("Ljava/lang/Double;", 9);
        DOUBLE_CLASS = cVar21;
        c cVar22 = new c("Ljava/lang/Float;", 9);
        FLOAT_CLASS = cVar22;
        c cVar23 = new c("Ljava/lang/Integer;", 9);
        INTEGER_CLASS = cVar23;
        c cVar24 = new c("Ljava/lang/Long;", 9);
        LONG_CLASS = cVar24;
        c cVar25 = new c("Ljava/lang/Short;", 9);
        SHORT_CLASS = cVar25;
        c cVar26 = new c("Ljava/lang/Void;", 9);
        VOID_CLASS = cVar26;
        c cVar27 = new c("[Z", 9);
        BOOLEAN_ARRAY = cVar27;
        c cVar28 = new c("[B", 9);
        BYTE_ARRAY = cVar28;
        c cVar29 = new c("[C", 9);
        CHAR_ARRAY = cVar29;
        c cVar30 = new c("[D", 9);
        DOUBLE_ARRAY = cVar30;
        c cVar31 = new c("[F", 9);
        FLOAT_ARRAY = cVar31;
        c cVar32 = new c("[I", 9);
        INT_ARRAY = cVar32;
        c cVar33 = new c("[J", 9);
        LONG_ARRAY = cVar33;
        c cVar34 = new c("[Ljava/lang/Object;", 9);
        OBJECT_ARRAY = cVar34;
        c cVar35 = new c("[S", 9);
        SHORT_ARRAY = cVar35;
        h(cVar);
        h(cVar2);
        h(cVar3);
        h(cVar4);
        h(cVar5);
        h(cVar6);
        h(cVar7);
        h(cVar8);
        h(cVar9);
        h(cVar10);
        h(cVar11);
        h(cVar12);
        h(cVar13);
        h(cVar14);
        h(cVar15);
        h(cVar16);
        h(cVar17);
        h(cVar18);
        h(cVar19);
        h(cVar20);
        h(cVar21);
        h(cVar22);
        h(cVar23);
        h(cVar24);
        h(cVar25);
        h(cVar26);
        h(cVar27);
        h(cVar28);
        h(cVar29);
        h(cVar30);
        h(cVar31);
        h(cVar32);
        h(cVar33);
        h(cVar34);
        h(cVar35);
    }

    public c(String str, int i7) {
        if (str == null) {
            throw new NullPointerException("descriptor == null");
        }
        if (i7 < 0 || i7 >= 11) {
            throw new IllegalArgumentException("bad basicType");
        }
        this.f3040a = str;
        this.f3041b = i7;
        this.f3043d = null;
        this.f3044e = null;
    }

    public static c g(String str) {
        c cVar = (c) f3039f.get(str);
        if (cVar != null) {
            return cVar;
        }
        try {
            char charAt = str.charAt(0);
            if (charAt == '[') {
                c g7 = g(str.substring(1));
                if (g7.f3043d == null) {
                    g7.f3043d = h(new c("[" + g7.f3040a, 9));
                }
                return g7.f3043d;
            }
            int length = str.length();
            if (charAt == 'L') {
                int i7 = length - 1;
                if (str.charAt(i7) == ';') {
                    for (int i8 = 1; i8 < i7; i8++) {
                        char charAt2 = str.charAt(i8);
                        if (charAt2 != '(' && charAt2 != ')' && charAt2 != '.') {
                            if (charAt2 == '/') {
                                if (i8 == 1 || i8 == i7 || str.charAt(i8 - 1) == '/') {
                                    throw new IllegalArgumentException("bad descriptor: ".concat(str));
                                }
                            } else if (charAt2 != ';' && charAt2 != '[') {
                            }
                        }
                        throw new IllegalArgumentException("bad descriptor: ".concat(str));
                    }
                    return h(new c(str, 9));
                }
            }
            throw new IllegalArgumentException("bad descriptor: ".concat(str));
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("descriptor is empty");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("descriptor == null");
        }
    }

    public static c h(c cVar) {
        c cVar2 = (c) f3039f.putIfAbsent(cVar.f3040a, cVar);
        return cVar2 != null ? cVar2 : cVar;
    }

    public final int a() {
        int i7 = this.f3041b;
        if (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 6 || i7 == 8) {
            return 6;
        }
        return i7;
    }

    @Override // c2.d
    public final c b() {
        return this;
    }

    @Override // c2.d
    public final int c() {
        return this.f3041b;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f3040a.compareTo(((c) obj).f3040a);
    }

    @Override // e2.i
    public final String d() {
        int i7 = this.f3041b;
        String str = this.f3040a;
        switch (i7) {
            case 0:
                return "void";
            case 1:
                return "boolean";
            case 2:
                return "byte";
            case 3:
                return "char";
            case 4:
                return "double";
            case 5:
                return "float";
            case 6:
                return "int";
            case 7:
                return "long";
            case 8:
                return "short";
            case 9:
                if (str.charAt(0) == '[') {
                    return f().d() + h0.PATH_SEGMENT_ENCODE_SET_URI;
                }
                if (this.f3042c == null) {
                    if (i7 != 9) {
                        throw new IllegalArgumentException("not an object type: ".concat(str));
                    }
                    if (str.charAt(0) == '[') {
                        this.f3042c = str;
                    } else {
                        this.f3042c = str.substring(1, str.length() - 1);
                    }
                }
                return this.f3042c.replace("/", ".");
            default:
                return str;
        }
    }

    public final int e() {
        int i7 = this.f3041b;
        return (i7 == 4 || i7 == 7) ? 2 : 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        return this.f3040a.equals(((c) obj).f3040a);
    }

    public final c f() {
        if (this.f3044e == null) {
            String str = this.f3040a;
            if (str.charAt(0) != '[') {
                throw new IllegalArgumentException("not an array type: " + str);
            }
            this.f3044e = g(str.substring(1));
        }
        return this.f3044e;
    }

    public final int hashCode() {
        return this.f3040a.hashCode();
    }

    public final String toString() {
        return this.f3040a;
    }
}
