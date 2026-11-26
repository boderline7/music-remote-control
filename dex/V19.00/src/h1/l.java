package h1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public final int f6685a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6686b;

    /* renamed from: c, reason: collision with root package name */
    public final long f6687c;

    /* renamed from: d, reason: collision with root package name */
    public final long f6688d;

    public l(int i7, int i8, long j7, long j8) {
        this.f6685a = i7;
        this.f6686b = i8;
        this.f6687c = j7;
        this.f6688d = j8;
    }

    public static l a(File file) {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            l lVar = new l(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
            dataInputStream.close();
            return lVar;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void b(File file) {
        file.delete();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dataOutputStream.writeInt(this.f6685a);
            dataOutputStream.writeInt(this.f6686b);
            dataOutputStream.writeLong(this.f6687c);
            dataOutputStream.writeLong(this.f6688d);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f6686b == lVar.f6686b && this.f6687c == lVar.f6687c && this.f6685a == lVar.f6685a && this.f6688d == lVar.f6688d;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f6686b), Long.valueOf(this.f6687c), Integer.valueOf(this.f6685a), Long.valueOf(this.f6688d));
    }
}
