package d4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public enum b {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);


    /* renamed from: a, reason: collision with root package name */
    public final int[] f6283a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6284b;

    b(int[] iArr, int i7) {
        this.f6283a = iArr;
        this.f6284b = i7;
    }

    public final int a(c cVar) {
        int i7 = cVar.f6286a;
        return this.f6283a[i7 <= 9 ? (char) 0 : i7 <= 26 ? (char) 1 : (char) 2];
    }
}
