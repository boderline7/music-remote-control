package a5;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    public final j f340a;

    /* renamed from: b, reason: collision with root package name */
    public final String f341b;

    /* renamed from: c, reason: collision with root package name */
    public final InputStream f342c;

    /* renamed from: d, reason: collision with root package name */
    public final long f343d;

    /* renamed from: e, reason: collision with root package name */
    public final h f344e = new h(this);

    /* renamed from: f, reason: collision with root package name */
    public final HashMap f345f = new HashMap();

    /* renamed from: g, reason: collision with root package name */
    public g f346g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f347h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f348i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f349j;

    public l(k kVar, String str, InputStream inputStream, long j7) {
        this.f340a = kVar;
        this.f341b = str;
        this.f342c = inputStream;
        this.f343d = j7;
        this.f347h = j7 < 0;
        this.f349j = true;
    }

    public static void C(PrintWriter printWriter, String str, String str2) {
        printWriter.append((CharSequence) str).append(": ").append((CharSequence) str2).append("\r\n");
    }

    public final String A(String str) {
        return (String) this.f345f.get(str.toLowerCase());
    }

    public final boolean B() {
        return "close".equals(A("connection"));
    }

    public final void D(OutputStream outputStream) {
        String str = this.f341b;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        j jVar = this.f340a;
        try {
            if (jVar == null) {
                throw new Error("sendResponse(): Status can't be null.");
            }
            PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, new b(str).a())), false);
            k kVar = (k) jVar;
            printWriter.append("HTTP/1.1 ").append(h0.FRAGMENT_ENCODE_SET + kVar.f338a + " " + kVar.f339b).append(" \r\n");
            if (str != null) {
                C(printWriter, "Content-Type", str);
            }
            if (A("date") == null) {
                C(printWriter, "Date", simpleDateFormat.format(new Date()));
            }
            for (Map.Entry entry : this.f344e.entrySet()) {
                C(printWriter, (String) entry.getKey(), (String) entry.getValue());
            }
            if (A("connection") == null) {
                C(printWriter, "Connection", this.f349j ? "keep-alive" : "close");
            }
            if (A("content-length") != null) {
                this.f348i = false;
            }
            if (this.f348i) {
                C(printWriter, "Content-Encoding", "gzip");
                this.f347h = true;
            }
            InputStream inputStream = this.f342c;
            long j7 = inputStream != null ? this.f343d : 0L;
            g gVar = this.f346g;
            g gVar2 = g.HEAD;
            if (gVar != gVar2 && this.f347h) {
                C(printWriter, "Transfer-Encoding", "chunked");
            } else if (!this.f348i) {
                j7 = F(printWriter, j7);
            }
            printWriter.append("\r\n");
            printWriter.flush();
            if (this.f346g != gVar2 && this.f347h) {
                i iVar = new i(outputStream);
                if (this.f348i) {
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(iVar);
                    E(gZIPOutputStream, -1L);
                    gZIPOutputStream.finish();
                } else {
                    E(iVar, -1L);
                }
                iVar.A();
            } else if (this.f348i) {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                E(gZIPOutputStream2, -1L);
                gZIPOutputStream2.finish();
            } else {
                E(outputStream, j7);
            }
            outputStream.flush();
            n.d(inputStream);
        } catch (IOException e7) {
            n.f357k.log(Level.SEVERE, "Could not send response to the client", (Throwable) e7);
        }
    }

    public final void E(OutputStream outputStream, long j7) {
        byte[] bArr = new byte[(int) 16384];
        boolean z6 = j7 == -1;
        while (true) {
            if (j7 <= 0 && !z6) {
                return;
            }
            int read = this.f342c.read(bArr, 0, (int) (z6 ? 16384L : Math.min(j7, 16384L)));
            if (read <= 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
            if (!z6) {
                j7 -= read;
            }
        }
    }

    public final long F(PrintWriter printWriter, long j7) {
        String A = A("content-length");
        if (A != null) {
            try {
                j7 = Long.parseLong(A);
            } catch (NumberFormatException unused) {
                n.f357k.severe("content-length was no number ".concat(A));
            }
        }
        printWriter.print("Content-Length: " + j7 + "\r\n");
        return j7;
    }

    public final void G(boolean z6) {
        this.f348i = z6;
    }

    public final void H(boolean z6) {
        this.f349j = z6;
    }

    public final void I(g gVar) {
        this.f346g = gVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        InputStream inputStream = this.f342c;
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
