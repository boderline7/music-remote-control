package t1;

import java.io.File;
import java.io.FilenameFilter;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements FilenameFilter {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f9044a;

    public h(String str) {
        this.f9044a = str;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        if (str == null || h0.FRAGMENT_ENCODE_SET.equals(str)) {
            return false;
        }
        try {
            return str.matches(this.f9044a);
        } catch (Exception unused) {
            return false;
        }
    }
}
