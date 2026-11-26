package w3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a AZTEC;
    public static final a CODABAR;
    public static final a CODE_128;
    public static final a CODE_39;
    public static final a CODE_93;
    public static final a DATA_MATRIX;
    public static final a EAN_13;
    public static final a EAN_8;
    public static final a ITF;
    public static final a MAXICODE;
    public static final a PDF_417;
    public static final a QR_CODE;
    public static final a RSS_14;
    public static final a RSS_EXPANDED;
    public static final a UPC_A;
    public static final a UPC_E;
    public static final a UPC_EAN_EXTENSION;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a[] f9467a;

    static {
        a aVar = new a("AZTEC", 0);
        AZTEC = aVar;
        a aVar2 = new a("CODABAR", 1);
        CODABAR = aVar2;
        a aVar3 = new a("CODE_39", 2);
        CODE_39 = aVar3;
        a aVar4 = new a("CODE_93", 3);
        CODE_93 = aVar4;
        a aVar5 = new a("CODE_128", 4);
        CODE_128 = aVar5;
        a aVar6 = new a("DATA_MATRIX", 5);
        DATA_MATRIX = aVar6;
        a aVar7 = new a("EAN_8", 6);
        EAN_8 = aVar7;
        a aVar8 = new a("EAN_13", 7);
        EAN_13 = aVar8;
        a aVar9 = new a("ITF", 8);
        ITF = aVar9;
        a aVar10 = new a("MAXICODE", 9);
        MAXICODE = aVar10;
        a aVar11 = new a("PDF_417", 10);
        PDF_417 = aVar11;
        a aVar12 = new a("QR_CODE", 11);
        QR_CODE = aVar12;
        a aVar13 = new a("RSS_14", 12);
        RSS_14 = aVar13;
        a aVar14 = new a("RSS_EXPANDED", 13);
        RSS_EXPANDED = aVar14;
        a aVar15 = new a("UPC_A", 14);
        UPC_A = aVar15;
        a aVar16 = new a("UPC_E", 15);
        UPC_E = aVar16;
        a aVar17 = new a("UPC_EAN_EXTENSION", 16);
        UPC_EAN_EXTENSION = aVar17;
        f9467a = new a[]{aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f9467a.clone();
    }
}
