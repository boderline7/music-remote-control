package t1;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements FilenameFilter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String[] f9043a;

    public g(String[] strArr) {
        this.f9043a = strArr;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : this.f9043a) {
                if (str.toLowerCase().endsWith(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
