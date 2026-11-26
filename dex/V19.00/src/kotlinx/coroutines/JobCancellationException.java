package kotlinx.coroutines;

import i5.a;
import java.util.concurrent.CancellationException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class JobCancellationException extends CancellationException {
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof JobCancellationException) {
                JobCancellationException jobCancellationException = (JobCancellationException) obj;
                if (a.c(jobCancellationException.getMessage(), getMessage())) {
                    jobCancellationException.getClass();
                    if (!a.c(null, null) || !a.c(jobCancellationException.getCause(), getCause())) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        a.d(getMessage());
        throw null;
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return a.a.i(new StringBuilder(), super.toString(), "; job=null");
    }
}
