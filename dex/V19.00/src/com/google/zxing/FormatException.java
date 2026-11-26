package com.google.zxing;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class FormatException extends ReaderException {

    /* renamed from: c, reason: collision with root package name */
    public static final FormatException f4480c;

    static {
        FormatException formatException = new FormatException();
        f4480c = formatException;
        formatException.setStackTrace(ReaderException.f4482b);
    }

    private FormatException() {
    }

    public static FormatException a() {
        return ReaderException.f4481a ? new FormatException() : f4480c;
    }
}
