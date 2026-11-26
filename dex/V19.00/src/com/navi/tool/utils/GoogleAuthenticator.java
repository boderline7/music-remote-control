package com.navi.tool.utils;

import java.util.Date;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GoogleAuthenticator {
    public static boolean verifyCode(String str) {
        return NativeBridge.verifyTotp(str, new Date().getTime() / 1000);
    }
}
