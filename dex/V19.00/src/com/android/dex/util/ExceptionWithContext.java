package com.android.dex.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ExceptionWithContext extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    public final StringBuffer f3188a;

    public ExceptionWithContext(String str, Exception exc) {
        super(str == null ? exc != null ? exc.getMessage() : null : str, exc);
        if (!(exc instanceof ExceptionWithContext)) {
            this.f3188a = new StringBuffer(200);
            return;
        }
        String stringBuffer = ((ExceptionWithContext) exc).f3188a.toString();
        StringBuffer stringBuffer2 = new StringBuffer(stringBuffer.length() + 200);
        this.f3188a = stringBuffer2;
        stringBuffer2.append(stringBuffer);
    }

    public static ExceptionWithContext a(String str, Exception exc) {
        ExceptionWithContext exceptionWithContext = exc instanceof ExceptionWithContext ? (ExceptionWithContext) exc : new ExceptionWithContext(null, exc);
        if (str == null) {
            throw new NullPointerException("str == null");
        }
        StringBuffer stringBuffer = exceptionWithContext.f3188a;
        stringBuffer.append(str);
        if (!str.endsWith("\n")) {
            stringBuffer.append('\n');
        }
        return exceptionWithContext;
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        printStream.println(this.f3188a);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        printWriter.println(this.f3188a);
    }
}
