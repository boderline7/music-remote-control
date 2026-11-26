package com.sagittarius.v6.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
final class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6107a = null;

    static {
        a();
    }

    private static String a(String str, String str2) {
        byte[] a7 = com.sagittarius.v6.a.a.a(str);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str2.toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        return sb.toString();
    }

    private static void a() {
        String[] strArr = new String[2];
        f6107a = strArr;
        strArr[0] = a("JyouDxU+OC4=", "WKZgY");
        f6107a[1] = a("AgURLwcJKAwkAw0WHAIYHgEGMh4eDQA1", "ldeFq");
    }
}
