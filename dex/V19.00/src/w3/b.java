package w3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final b AZTEC_LAYERS;
    public static final b CHARACTER_SET;
    public static final b CODE128_COMPACT;
    public static final b DATA_MATRIX_COMPACT;
    public static final b DATA_MATRIX_SHAPE;
    public static final b ERROR_CORRECTION;
    public static final b FORCE_C40;
    public static final b FORCE_CODE_SET;
    public static final b GS1_FORMAT;
    public static final b MARGIN;

    @Deprecated
    public static final b MAX_SIZE;

    @Deprecated
    public static final b MIN_SIZE;
    public static final b PDF417_AUTO_ECI;
    public static final b PDF417_COMPACT;
    public static final b PDF417_COMPACTION;
    public static final b PDF417_DIMENSIONS;
    public static final b QR_COMPACT;
    public static final b QR_MASK_PATTERN;
    public static final b QR_VERSION;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ b[] f9468a;

    static {
        b bVar = new b("ERROR_CORRECTION", 0);
        ERROR_CORRECTION = bVar;
        b bVar2 = new b("CHARACTER_SET", 1);
        CHARACTER_SET = bVar2;
        b bVar3 = new b("DATA_MATRIX_SHAPE", 2);
        DATA_MATRIX_SHAPE = bVar3;
        b bVar4 = new b("DATA_MATRIX_COMPACT", 3);
        DATA_MATRIX_COMPACT = bVar4;
        b bVar5 = new b("MIN_SIZE", 4);
        MIN_SIZE = bVar5;
        b bVar6 = new b("MAX_SIZE", 5);
        MAX_SIZE = bVar6;
        b bVar7 = new b("MARGIN", 6);
        MARGIN = bVar7;
        b bVar8 = new b("PDF417_COMPACT", 7);
        PDF417_COMPACT = bVar8;
        b bVar9 = new b("PDF417_COMPACTION", 8);
        PDF417_COMPACTION = bVar9;
        b bVar10 = new b("PDF417_DIMENSIONS", 9);
        PDF417_DIMENSIONS = bVar10;
        b bVar11 = new b("PDF417_AUTO_ECI", 10);
        PDF417_AUTO_ECI = bVar11;
        b bVar12 = new b("AZTEC_LAYERS", 11);
        AZTEC_LAYERS = bVar12;
        b bVar13 = new b("QR_VERSION", 12);
        QR_VERSION = bVar13;
        b bVar14 = new b("QR_MASK_PATTERN", 13);
        QR_MASK_PATTERN = bVar14;
        b bVar15 = new b("QR_COMPACT", 14);
        QR_COMPACT = bVar15;
        b bVar16 = new b("GS1_FORMAT", 15);
        GS1_FORMAT = bVar16;
        b bVar17 = new b("FORCE_CODE_SET", 16);
        FORCE_CODE_SET = bVar17;
        b bVar18 = new b("FORCE_C40", 17);
        FORCE_C40 = bVar18;
        b bVar19 = new b("CODE128_COMPACT", 18);
        CODE128_COMPACT = bVar19;
        f9468a = new b[]{bVar, bVar2, bVar3, bVar4, bVar5, bVar6, bVar7, bVar8, bVar9, bVar10, bVar11, bVar12, bVar13, bVar14, bVar15, bVar16, bVar17, bVar18, bVar19};
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f9468a.clone();
    }
}
