package o5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l extends k {
    public static String V(int i7, String str) {
        if (i7 < 0) {
            throw new IllegalArgumentException(a.a.e("Requested character count ", i7, " is less than zero.").toString());
        }
        int length = str.length();
        if (i7 > length) {
            i7 = length;
        }
        String substring = str.substring(0, i7);
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }
}
