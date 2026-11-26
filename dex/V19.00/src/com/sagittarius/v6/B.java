package com.sagittarius.v6;

import com.sagittarius.v6.a.a;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class B {
    private static final String[] lllll = null;

    static {
        llIll();
    }

    private static String IlIll(String str, String str2) {
        byte[] a7 = a.a(str);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str2.toCharArray();
        int i7 = 0;
        for (char c7 : a7) {
            sb.append((char) (c7 ^ charArray[i7 % charArray.length]));
            i7++;
        }
        return sb.toString();
    }

    public static void a1(String str) {
        String format = String.format(lllll[0], AppInfo.DATAPATH, AppInfo.f6106h, str);
        if (new File(format).exists()) {
            System.load(format);
        } else {
            System.loadLibrary(str);
        }
    }

    private static void llIll() {
        lllll = new String[]{IlIll("QwVGe38VWQU8OEkaADd/FVgaOg==", "fviUZ")};
    }
}
