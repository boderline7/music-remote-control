package h1;

import android.content.pm.PackageInfo;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f {
    public static final int DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST = 2;
    public static final int DIAGNOSTIC_CURRENT_PROFILE_EXISTS = 1;
    public static final int DIAGNOSTIC_PROFILE_IS_COMPRESSED = 5;
    public static final int DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST = 4;
    public static final int DIAGNOSTIC_REF_PROFILE_EXISTS = 3;
    public static final int RESULT_ALREADY_INSTALLED = 2;
    public static final int RESULT_BASELINE_PROFILE_NOT_FOUND = 6;
    public static final int RESULT_BENCHMARK_OPERATION_FAILURE = 15;
    public static final int RESULT_BENCHMARK_OPERATION_SUCCESS = 14;
    public static final int RESULT_BENCHMARK_OPERATION_UNKNOWN = 16;
    public static final int RESULT_DELETE_SKIP_FILE_SUCCESS = 11;
    public static final int RESULT_DESIRED_FORMAT_UNSUPPORTED = 5;
    public static final int RESULT_INSTALL_SKIP_FILE_SUCCESS = 10;
    public static final int RESULT_INSTALL_SUCCESS = 1;
    public static final int RESULT_IO_EXCEPTION = 7;
    public static final int RESULT_META_FILE_REQUIRED_BUT_NOT_FOUND = 9;
    public static final int RESULT_NOT_WRITABLE = 4;
    public static final int RESULT_PARSE_EXCEPTION = 8;
    public static final int RESULT_SAVE_PROFILE_SIGNALLED = 12;
    public static final int RESULT_SAVE_PROFILE_SKIPPED = 13;
    public static final int RESULT_UNSUPPORTED_ART_VERSION = 3;

    /* renamed from: a, reason: collision with root package name */
    public static final p3.f f6681a = new p3.f(14, 0);

    public static void a(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x01bf, code lost:
    
        if (r7 == null) goto L117;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0296 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x01c2 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0291  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(android.content.Context r17, l.a r18, h1.e r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.f.b(android.content.Context, l.a, h1.e, boolean):void");
    }
}
