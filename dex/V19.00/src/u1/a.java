package u1;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final int PER_USER_RANGE = 100000;

    public static String a(ClassLoader classLoader, Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("path");
            declaredField.setAccessible(true);
            return (String) declaredField.get(classLoader);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException unused) {
            String obj = classLoader.toString();
            if (!obj.contains("DexPathList")) {
                int lastIndexOf = obj.lastIndexOf(91);
                if (lastIndexOf != -1) {
                    obj = obj.substring(lastIndexOf + 1);
                }
                int indexOf = obj.indexOf(93);
                return indexOf == -1 ? obj : obj.substring(0, indexOf);
            }
            int indexOf2 = obj.indexOf("DexPathList");
            int i7 = indexOf2 + 11;
            if (obj.length() <= indexOf2 + 15) {
                return obj;
            }
            String substring = obj.substring(i7);
            int indexOf3 = substring.indexOf(93);
            if (substring.charAt(0) != '[' || substring.charAt(1) != '[' || indexOf3 < 0) {
                return obj;
            }
            String[] split = substring.substring(2, indexOf3).split(",");
            for (int i8 = 0; i8 < split.length; i8++) {
                int indexOf4 = split[i8].indexOf(34);
                int lastIndexOf2 = split[i8].lastIndexOf(34);
                if (indexOf4 > 0 && indexOf4 < lastIndexOf2) {
                    split[i8] = split[i8].substring(indexOf4 + 1, lastIndexOf2);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String str : split) {
                if (sb.length() > 0) {
                    sb.append(':');
                }
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public static File[] b(String str) {
        int lastIndexOf;
        int lastIndexOf2;
        int lastIndexOf3;
        int indexOf;
        Integer num;
        ArrayList arrayList = new ArrayList();
        if (str.startsWith("dexPath=")) {
            int indexOf2 = str.indexOf(44);
            str = indexOf2 == -1 ? str.substring(8) : str.substring(8, indexOf2);
        }
        for (String str2 : str.split(":")) {
            if (str2.startsWith("/data/app/") && (lastIndexOf = str2.lastIndexOf(".apk")) == str2.length() - 4 && (lastIndexOf2 = str2.lastIndexOf("/", lastIndexOf)) != 9 && (lastIndexOf3 = str2.lastIndexOf("/", lastIndexOf2 - 1)) != -1 && (indexOf = str2.indexOf("-", lastIndexOf3)) != -1) {
                String substring = str2.substring(lastIndexOf3 + 1, indexOf);
                File file = new File(a.a.B("/data/data/", substring));
                File file2 = null;
                if (!file.isDirectory() || !file.canWrite()) {
                    file = null;
                }
                if (file == null) {
                    try {
                        num = (Integer) Class.forName("android.os.Process").getMethod("myUid", new Class[0]).invoke(null, new Object[0]);
                    } catch (Exception unused) {
                        num = null;
                    }
                    if (num != null) {
                        File file3 = new File(String.format("/data/user/%d/%s", Integer.valueOf(num.intValue() / PER_USER_RANGE), substring));
                        if (file3.isDirectory() && file3.canWrite()) {
                            file2 = file3;
                        }
                    }
                    file = file2;
                }
                if (file != null) {
                    File file4 = new File(file, "cache");
                    if ((file4.exists() || file4.mkdir()) && file4.isDirectory() && file4.canWrite()) {
                        arrayList.add(file4);
                    }
                }
            }
        }
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }
}
