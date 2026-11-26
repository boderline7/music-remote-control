package com.google.zxing;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class ReaderException extends Exception {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f4481a;

    /* renamed from: b, reason: collision with root package name */
    public static final StackTraceElement[] f4482b;

    static {
        f4481a = System.getProperty("surefire.test.class.path") != null;
        f4482b = new StackTraceElement[0];
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
