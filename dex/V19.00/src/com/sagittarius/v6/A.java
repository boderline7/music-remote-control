package com.sagittarius.v6;

import android.content.Context;
import com.sagittarius.v6.b.h;
import dalvik.system.DexFile;
import java.nio.ByteBuffer;
import java.util.Enumeration;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class A {
    public static void a1(ByteBuffer byteBuffer) {
        h.a(StubApplication.mContext, byteBuffer);
    }

    public static void a2(ByteBuffer[] byteBufferArr) {
        h.a(StubApplication.mContext, byteBufferArr);
    }

    public static void a3(String str) {
        h.a(StubApplication.mContext, str);
    }

    public static Enumeration a4(DexFile dexFile) {
        return h.a(dexFile);
    }

    public static native void n1(Context context, String str, String str2, String str3, int i7);

    public static native void n2(Context context);

    public static native void n3(Context context);
}
