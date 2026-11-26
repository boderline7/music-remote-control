package t1;

import android.os.Environment;
import java.io.File;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {
    public static final int DEFDOWNLOAD = 16;
    public static final int DIR_ROOT = 0;
    public static final int EXTERNAL_SD_PATH = 9;
    public static final int EXT_ROOT = 19;
    public static final int KWDIR_CACHE = 3;
    public static final int KWDIR_CODECS = 6;
    public static final int KWDIR_CRASH = 8;
    public static final int KWDIR_DOWNLOAD = 2;
    public static final int KWDIR_HOME = 1;
    public static final int KWDIR_LYRIC = 4;
    public static final int KWDIR_PLAYCACHE = 7;
    public static final int KWDIR_PLAYLISTS = 5;
    public static final int LIBS = 15;
    public static final int LOG = 11;
    public static final int MVCACHE = 20;
    public static final int MVDOWNED = 21;
    public static final int OFFLINE_LOG = 12;
    public static final int PICTURE = 17;
    public static final int SETTING = 10;
    public static final int SKIN = 13;
    public static final int TEMP = 14;
    public static final int WELCOME = 18;

    /* renamed from: a, reason: collision with root package name */
    public static final String f9024a = Environment.getExternalStorageDirectory().getAbsolutePath();

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        new File(sb.toString() + str + "kuwo.zhp").exists();
        new File(Environment.getRootDirectory().getAbsoluteFile() + str + "etc" + str + "vold.fstab");
        new ArrayList();
    }
}
