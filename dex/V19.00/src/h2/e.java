package h2;

import com.github.junrar.exception.HeaderNotInArchiveException;
import com.github.junrar.exception.RarException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import k2.g;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e {

    /* renamed from: a, reason: collision with root package name */
    public static final j6.a f6718a = j6.b.d(e.class);

    public static void a(File file, File file2) {
        j6.a aVar = f6718a;
        if (file == null) {
            throw new IllegalArgumentException("archive and destination must me set");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("the archive does not exit: " + file);
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("First argument should be a file but was " + file.getAbsolutePath());
        }
        if (file2 == null) {
            throw new IllegalArgumentException("archive and destination must me set");
        }
        if (!file2.exists() || !file2.isDirectory()) {
            throw new IllegalArgumentException("the destination must exist and point to a directory: " + file2);
        }
        try {
            d dVar = new d(file);
            f fVar = new f(file2);
            ArrayList arrayList = new ArrayList();
            while (true) {
                try {
                    g D = dVar.D();
                    dVar.f6716j = D;
                    if (D == null) {
                        return;
                    }
                    try {
                        File b7 = b(fVar, dVar, D);
                        if (b7 != null) {
                            arrayList.add(b7);
                        }
                    } catch (RarException | IOException e7) {
                        aVar.i("error extracting the file", e7);
                        throw e7;
                    }
                } finally {
                    dVar.close();
                }
            }
        } catch (RarException | IOException e8) {
            aVar.i("Error while creating archive", e8);
            throw e8;
        }
    }

    public static File b(f fVar, d dVar, g gVar) {
        f6718a.d("extracting: {}", gVar.d());
        short s6 = gVar.f7213d;
        boolean z6 = (s6 & 224) == 224;
        File file = fVar.f6720a;
        if (z6) {
            boolean z7 = (s6 & 224) == 224;
            File file2 = null;
            String d7 = z7 ? gVar.d() : null;
            if (d7 != null) {
                file2 = new File(file, d7);
                try {
                    String canonicalPath = file2.getCanonicalPath();
                    if (!canonicalPath.startsWith(file.getCanonicalPath())) {
                        throw new IllegalStateException("Rar contains invalid path: '" + canonicalPath + "'");
                    }
                } catch (IOException e7) {
                    throw new IllegalStateException(e7);
                }
            }
            return file2;
        }
        String d8 = gVar.d();
        File file3 = new File(file, d8);
        String canonicalPath2 = file3.getCanonicalPath();
        if (!canonicalPath2.startsWith(file.getCanonicalPath())) {
            throw new IllegalStateException(a.a.h("Rar contains file with invalid path: '", canonicalPath2, "'"));
        }
        if (!file3.exists()) {
            try {
                file3 = f.a(file, d8);
            } catch (IOException e8) {
                f.f6719b.q(file3.getName(), e8);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file3);
        try {
            if (!dVar.f6709c.contains(gVar)) {
                throw new HeaderNotInArchiveException();
            }
            try {
                dVar.A(gVar, fileOutputStream);
                fileOutputStream.close();
                return file3;
            } catch (Exception e9) {
                if (e9 instanceof RarException) {
                    throw ((RarException) e9);
                }
                throw new RarException(e9);
            }
        } finally {
        }
    }
}
