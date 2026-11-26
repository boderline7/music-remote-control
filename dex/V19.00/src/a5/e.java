package a5;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final File f320a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f321b;

    public e() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        this.f320a = file;
        if (!file.exists()) {
            file.mkdirs();
        }
        this.f321b = new ArrayList();
    }

    public final void a() {
        File file;
        ArrayList arrayList = this.f321b;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            try {
                n.d(dVar.f319b);
                file = dVar.f318a;
            } catch (Exception e7) {
                n.f357k.log(Level.WARNING, "could not delete file ", (Throwable) e7);
            }
            if (!file.delete()) {
                throw new Exception("could not delete temporary file: " + file.getAbsolutePath());
            }
        }
        arrayList.clear();
    }
}
