package a5;

import java.io.File;
import java.io.FileOutputStream;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public final File f318a;

    /* renamed from: b, reason: collision with root package name */
    public final FileOutputStream f319b;

    public d(File file) {
        File createTempFile = File.createTempFile("NanoHTTPD-", h0.FRAGMENT_ENCODE_SET, file);
        this.f318a = createTempFile;
        this.f319b = new FileOutputStream(createTempFile);
    }
}
