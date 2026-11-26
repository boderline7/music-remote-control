package g6;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6570a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f6571b;

    public /* synthetic */ g(j jVar, int i7) {
        this.f6570a = i7;
        this.f6571b = jVar;
    }

    @Override // java.io.InputStream
    public final int available() {
        int i7 = this.f6570a;
        j jVar = this.f6571b;
        switch (i7) {
            case 0:
                return (int) Math.min(((h) jVar).f6573b, androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
            default:
                t tVar = (t) jVar;
                if (tVar.f6595c) {
                    throw new IOException("closed");
                }
                return (int) Math.min(tVar.f6594b.f6573b, androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.f6570a) {
            case 0:
                break;
            default:
                ((t) this.f6571b).close();
                break;
        }
    }

    @Override // java.io.InputStream
    public final int read() {
        int i7 = this.f6570a;
        j jVar = this.f6571b;
        switch (i7) {
            case 0:
                h hVar = (h) jVar;
                if (hVar.f6573b > 0) {
                    return hVar.y() & 255;
                }
                return -1;
            default:
                t tVar = (t) jVar;
                if (tVar.f6595c) {
                    throw new IOException("closed");
                }
                h hVar2 = tVar.f6594b;
                if (hVar2.f6573b == 0 && tVar.f6593a.k(hVar2, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return hVar2.y() & 255;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i8) {
        int i9 = this.f6570a;
        j jVar = this.f6571b;
        switch (i9) {
            case 0:
                i5.a.g("sink", bArr);
                return ((h) jVar).F(bArr, i7, i8);
            default:
                i5.a.g("data", bArr);
                t tVar = (t) jVar;
                if (tVar.f6595c) {
                    throw new IOException("closed");
                }
                n1.z.w(bArr.length, i7, i8);
                h hVar = tVar.f6594b;
                if (hVar.f6573b == 0 && tVar.f6593a.k(hVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return hVar.F(bArr, i7, i8);
        }
    }

    public final String toString() {
        int i7 = this.f6570a;
        j jVar = this.f6571b;
        switch (i7) {
            case 0:
                return ((h) jVar) + ".inputStream()";
            default:
                return ((t) jVar) + ".inputStream()";
        }
    }
}
