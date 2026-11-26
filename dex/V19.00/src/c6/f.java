package c6;

import android.util.Log;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends Handler {
    public static final f INSTANCE = new f();

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int min;
        i5.a.g("record", logRecord);
        e eVar = e.INSTANCE;
        String loggerName = logRecord.getLoggerName();
        i5.a.f("record.loggerName", loggerName);
        int intValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        int i7 = intValue > level.intValue() ? 5 : logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        String message = logRecord.getMessage();
        i5.a.f("record.message", message);
        Throwable thrown = logRecord.getThrown();
        eVar.getClass();
        String str = (String) e.f3083b.get(loggerName);
        if (str == null) {
            str = o5.l.V(23, loggerName);
        }
        if (Log.isLoggable(str, i7)) {
            if (thrown != null) {
                message = message + '\n' + Log.getStackTraceString(thrown);
            }
            int length = message.length();
            int i8 = 0;
            while (i8 < length) {
                int F = o5.k.F(message, '\n', i8, false, 4);
                if (F == -1) {
                    F = length;
                }
                while (true) {
                    min = Math.min(F, i8 + 4000);
                    String substring = message.substring(i8, min);
                    i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
                    Log.println(i7, str, substring);
                    if (min >= F) {
                        break;
                    } else {
                        i8 = min;
                    }
                }
                i8 = min + 1;
            }
        }
    }
}
