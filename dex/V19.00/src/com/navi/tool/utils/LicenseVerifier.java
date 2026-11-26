package com.navi.tool.utils;

import java.security.SecureRandom;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LicenseVerifier {

    public static class Result {
        public boolean block;
        public String error;
        public String expd = h0.FRAGMENT_ENCODE_SET;
    }

    public static String newNonce() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        char[] charArray = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder(32);
        for (int i7 = 0; i7 < 16; i7++) {
            byte b7 = bArr[i7];
            sb.append(charArray[(b7 >> 4) & 15]);
            sb.append(charArray[b7 & 15]);
        }
        return sb.toString();
    }

    public static Result verify(String str, String str2, String str3) {
        Result result = new Result();
        if (str == null) {
            result.error = "empty";
            return result;
        }
        if (str2 == null) {
            str2 = h0.FRAGMENT_ENCODE_SET;
        }
        if (str3 == null) {
            str3 = h0.FRAGMENT_ENCODE_SET;
        }
        try {
            String verifySignedLicense = NativeBridge.verifySignedLicense(str, str2, str3);
            if (verifySignedLicense == null) {
                result.error = "null";
                return result;
            }
            if (!verifySignedLicense.startsWith("OK|")) {
                result.error = verifySignedLicense;
                return result;
            }
            String[] split = verifySignedLicense.split("\\|", 4);
            if (split.length < 4) {
                result.error = "bad_result";
                return result;
            }
            result.block = "1".equals(split[2]);
            result.expd = split[3];
            return result;
        } catch (Throwable unused) {
            result.error = "native_ex";
            return result;
        }
    }
}
