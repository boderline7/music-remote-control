package g0;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {
    public static File a(Context context) {
        return context.getCodeCacheDir();
    }

    public static Drawable b(Context context, int i7) {
        return context.getDrawable(i7);
    }

    public static File c(Context context) {
        return context.getNoBackupFilesDir();
    }
}
