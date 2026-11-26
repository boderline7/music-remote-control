package a2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_BRIDGE = 64;
    public static final int ACC_CONSTRUCTOR = 65536;
    public static final int ACC_DECLARED_SYNCHRONIZED = 131072;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_FINAL = 16;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_NATIVE = 256;
    public static final int ACC_PRIVATE = 2;
    public static final int ACC_PROTECTED = 4;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_STATIC = 8;
    public static final int ACC_STRICT = 2048;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNCHRONIZED = 32;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int ACC_TRANSIENT = 128;
    public static final int ACC_VARARGS = 128;
    public static final int ACC_VOLATILE = 64;
    public static final int CLASS_FLAGS = 30257;
    public static final int FIELD_FLAGS = 20703;
    public static final int INNER_CLASS_FLAGS = 30239;
    public static final int METHOD_FLAGS = 204287;

    public static String a(int i7, int i8, int i9) {
        StringBuilder sb = new StringBuilder(80);
        int i10 = (~i8) & i7;
        int i11 = i7 & i8;
        if ((i11 & 1) != 0) {
            sb.append("|public");
        }
        if ((i11 & 2) != 0) {
            sb.append("|private");
        }
        if ((i11 & 4) != 0) {
            sb.append("|protected");
        }
        if ((i11 & 8) != 0) {
            sb.append("|static");
        }
        if ((i11 & 16) != 0) {
            sb.append("|final");
        }
        if ((i11 & 32) != 0) {
            if (i9 == 1) {
                sb.append("|super");
            } else {
                sb.append("|synchronized");
            }
        }
        if ((i11 & 64) != 0) {
            if (i9 == 3) {
                sb.append("|bridge");
            } else {
                sb.append("|volatile");
            }
        }
        if ((i11 & 128) != 0) {
            if (i9 == 3) {
                sb.append("|varargs");
            } else {
                sb.append("|transient");
            }
        }
        if ((i11 & 256) != 0) {
            sb.append("|native");
        }
        if ((i11 & 512) != 0) {
            sb.append("|interface");
        }
        if ((i11 & 1024) != 0) {
            sb.append("|abstract");
        }
        if ((i11 & 2048) != 0) {
            sb.append("|strictfp");
        }
        if ((i11 & 4096) != 0) {
            sb.append("|synthetic");
        }
        if ((i11 & 8192) != 0) {
            sb.append("|annotation");
        }
        if ((i11 & 16384) != 0) {
            sb.append("|enum");
        }
        if ((65536 & i11) != 0) {
            sb.append("|constructor");
        }
        if ((i11 & 131072) != 0) {
            sb.append("|declared_synchronized");
        }
        if (i10 != 0 || sb.length() == 0) {
            sb.append('|');
            sb.append(z.l1(i10));
        }
        return sb.substring(1);
    }
}
