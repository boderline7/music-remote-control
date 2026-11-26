package t1;

import android.text.TextUtils;
import java.io.File;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class i {
    public static final String EMULATED_0 = "/storage/emulated/0";
    public static final String EMULATED_LEGACY = "/storage/emulated/legacy";

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return (lastIndexOf <= 0 || lastIndexOf == str.length() + (-1)) ? str : str.substring(lastIndexOf + 1, str.length());
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            for (String str2 : file.list()) {
                StringBuilder j7 = a.a.j(str);
                j7.append(File.separator);
                j7.append(str2);
                if (!b(j7.toString())) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static void c(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return;
        }
        file.mkdirs();
    }
}
