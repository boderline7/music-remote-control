package h2;

import java.io.File;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: b, reason: collision with root package name */
    public static final j6.a f6719b = j6.b.d(f.class);

    /* renamed from: a, reason: collision with root package name */
    public final File f6720a;

    public f(File file) {
        this.f6720a = file;
    }

    public static File a(File file, String str) {
        String[] split = str.split("\\\\");
        int length = split.length;
        if (length == 1) {
            return new File(file, str);
        }
        if (length <= 1) {
            return null;
        }
        String str2 = h0.FRAGMENT_ENCODE_SET;
        for (int i7 = 0; i7 < split.length - 1; i7++) {
            StringBuilder j7 = a.a.j(str2);
            j7.append(File.separator);
            j7.append(split[i7]);
            str2 = j7.toString();
            new File(file, str2).mkdir();
        }
        StringBuilder j8 = a.a.j(str2);
        j8.append(File.separator);
        j8.append(split[split.length - 1]);
        File file2 = new File(file, j8.toString());
        file2.createNewFile();
        return file2;
    }
}
