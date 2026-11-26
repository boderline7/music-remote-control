package a5;

import android.support.v4.media.session.PlaybackStateCompat;
import fi.iki.elonen.NanoHTTPD$ResponseException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import javax.net.ssl.SSLException;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {
    public static final int BUFSIZE = 8192;
    public static final int MAX_HEADER_SIZE = 1024;

    /* renamed from: a, reason: collision with root package name */
    public final e f322a;

    /* renamed from: b, reason: collision with root package name */
    public final OutputStream f323b;

    /* renamed from: c, reason: collision with root package name */
    public final BufferedInputStream f324c;

    /* renamed from: d, reason: collision with root package name */
    public int f325d;

    /* renamed from: e, reason: collision with root package name */
    public int f326e;

    /* renamed from: f, reason: collision with root package name */
    public String f327f;

    /* renamed from: g, reason: collision with root package name */
    public g f328g;

    /* renamed from: h, reason: collision with root package name */
    public HashMap f329h;

    /* renamed from: i, reason: collision with root package name */
    public HashMap f330i;

    /* renamed from: j, reason: collision with root package name */
    public c f331j;

    /* renamed from: k, reason: collision with root package name */
    public final String f332k;

    /* renamed from: l, reason: collision with root package name */
    public String f333l;

    /* renamed from: m, reason: collision with root package name */
    public final /* synthetic */ n f334m;

    public f(n nVar, e eVar, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
        this.f334m = nVar;
        this.f322a = eVar;
        this.f324c = new BufferedInputStream(inputStream, 8192);
        this.f323b = outputStream;
        this.f332k = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
        if (!inetAddress.isLoopbackAddress() && !inetAddress.isAnyLocalAddress()) {
            inetAddress.getHostName().getClass();
        }
        this.f330i = new HashMap();
    }

    public static void c(String str, Map map) {
        String trim;
        String str2;
        if (str == null) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf >= 0) {
                trim = n.b(nextToken.substring(0, indexOf)).trim();
                str2 = n.b(nextToken.substring(indexOf + 1));
            } else {
                trim = n.b(nextToken).trim();
                str2 = h0.FRAGMENT_ENCODE_SET;
            }
            List list = (List) map.get(trim);
            if (list == null) {
                list = new ArrayList();
                map.put(trim, list);
            }
            list.add(str2);
        }
    }

    public static int e(byte[] bArr, int i7) {
        int i8;
        int i9 = 0;
        while (true) {
            int i10 = i9 + 1;
            if (i10 >= i7) {
                return 0;
            }
            byte b7 = bArr[i9];
            if (b7 == 13 && bArr[i10] == 10 && (i8 = i9 + 3) < i7 && bArr[i9 + 2] == 13 && bArr[i8] == 10) {
                return i9 + 4;
            }
            if (b7 == 10 && bArr[i10] == 10) {
                return i9 + 2;
            }
            i9 = i10;
        }
    }

    public static int[] f(ByteBuffer byteBuffer, byte[] bArr) {
        int[] iArr = new int[0];
        if (byteBuffer.remaining() < bArr.length) {
            return iArr;
        }
        int length = bArr.length + 4096;
        byte[] bArr2 = new byte[length];
        int remaining = byteBuffer.remaining() < length ? byteBuffer.remaining() : length;
        byteBuffer.get(bArr2, 0, remaining);
        int length2 = remaining - bArr.length;
        int i7 = 0;
        do {
            for (int i8 = 0; i8 < length2; i8++) {
                for (int i9 = 0; i9 < bArr.length && bArr2[i8 + i9] == bArr[i9]; i9++) {
                    if (i9 == bArr.length - 1) {
                        int[] iArr2 = new int[iArr.length + 1];
                        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                        iArr2[iArr.length] = i7 + i8;
                        iArr = iArr2;
                    }
                }
            }
            i7 += length2;
            System.arraycopy(bArr2, length - bArr.length, bArr2, 0, bArr.length);
            length2 = length - bArr.length;
            if (byteBuffer.remaining() < length2) {
                length2 = byteBuffer.remaining();
            }
            byteBuffer.get(bArr2, bArr.length, length2);
        } while (length2 > 0);
        return iArr;
    }

    public final void a(BufferedReader bufferedReader, HashMap hashMap, Map map, Map map2) {
        String b7;
        try {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(readLine);
            if (!stringTokenizer.hasMoreTokens()) {
                throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
            }
            hashMap.put("method", stringTokenizer.nextToken());
            if (!stringTokenizer.hasMoreTokens()) {
                throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
            }
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(63);
            if (indexOf >= 0) {
                c(nextToken.substring(indexOf + 1), map);
                b7 = n.b(nextToken.substring(0, indexOf));
            } else {
                b7 = n.b(nextToken);
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.f333l = stringTokenizer.nextToken();
            } else {
                this.f333l = "HTTP/1.1";
                n.f357k.log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");
            }
            String readLine2 = bufferedReader.readLine();
            while (readLine2 != null && !readLine2.trim().isEmpty()) {
                int indexOf2 = readLine2.indexOf(58);
                if (indexOf2 >= 0) {
                    map2.put(readLine2.substring(0, indexOf2).trim().toLowerCase(Locale.US), readLine2.substring(indexOf2 + 1).trim());
                }
                readLine2 = bufferedReader.readLine();
            }
            hashMap.put("uri", b7);
        } catch (IOException e7) {
            throw new NanoHTTPD$ResponseException(k.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + e7.getMessage(), e7);
        }
    }

    public final void b(b bVar, ByteBuffer byteBuffer, Map map, HashMap hashMap) {
        int i7;
        String str;
        String str2 = bVar.f315d;
        try {
            int[] f7 = f(byteBuffer, str2.getBytes());
            int i8 = 2;
            try {
                if (f7.length < 2) {
                    throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
                }
                int i9 = 1024;
                byte[] bArr = new byte[1024];
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                while (i11 < f7.length - 1) {
                    byteBuffer.position(f7[i11]);
                    int remaining = byteBuffer.remaining() < i9 ? byteBuffer.remaining() : i9;
                    byteBuffer.get(bArr, i10, remaining);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, i10, remaining), Charset.forName(bVar.a())), remaining);
                    String readLine = bufferedReader.readLine();
                    if (readLine == null || !readLine.contains(str2)) {
                        throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
                    }
                    String readLine2 = bufferedReader.readLine();
                    String str3 = null;
                    int i13 = i8;
                    String str4 = null;
                    int i14 = i12;
                    String str5 = null;
                    while (readLine2 != null && readLine2.trim().length() > 0) {
                        Matcher matcher = n.f354h.matcher(readLine2);
                        if (matcher.matches()) {
                            Matcher matcher2 = n.f356j.matcher(matcher.group(i8));
                            while (matcher2.find()) {
                                String group = matcher2.group(1);
                                if ("name".equalsIgnoreCase(group)) {
                                    str = matcher2.group(2);
                                } else if ("filename".equalsIgnoreCase(group)) {
                                    str5 = matcher2.group(2);
                                    if (!str5.isEmpty()) {
                                        if (i14 > 0) {
                                            str = str3 + String.valueOf(i14);
                                            i14++;
                                        } else {
                                            i14++;
                                        }
                                    }
                                }
                                str3 = str;
                            }
                        }
                        Matcher matcher3 = n.f355i.matcher(readLine2);
                        if (matcher3.matches()) {
                            i7 = 2;
                            str4 = matcher3.group(2).trim();
                        } else {
                            i7 = 2;
                        }
                        readLine2 = bufferedReader.readLine();
                        i13++;
                        i8 = i7;
                    }
                    int i15 = i8;
                    int i16 = 0;
                    while (true) {
                        int i17 = i13 - 1;
                        if (i13 <= 0) {
                            break;
                        }
                        while (bArr[i16] != 10) {
                            i16++;
                        }
                        i16++;
                        i13 = i17;
                    }
                    if (i16 >= remaining - 4) {
                        throw new NanoHTTPD$ResponseException(k.INTERNAL_ERROR, "Multipart header size exceeds MAX_HEADER_SIZE.");
                    }
                    int i18 = f7[i11] + i16;
                    i11++;
                    int i19 = f7[i11] - 4;
                    byteBuffer.position(i18);
                    List list = (List) map.get(str3);
                    if (list == null) {
                        list = new ArrayList();
                        map.put(str3, list);
                    }
                    if (str4 == null) {
                        byte[] bArr2 = new byte[i19 - i18];
                        byteBuffer.get(bArr2);
                        list.add(new String(bArr2, bVar.a()));
                    } else {
                        String i20 = i(byteBuffer, i18, i19 - i18);
                        if (hashMap.containsKey(str3)) {
                            int i21 = i15;
                            while (true) {
                                if (!hashMap.containsKey(str3 + i21)) {
                                    break;
                                } else {
                                    i21++;
                                }
                            }
                            hashMap.put(str3 + i21, i20);
                        } else {
                            hashMap.put(str3, i20);
                        }
                        list.add(str5);
                    }
                    i8 = i15;
                    i12 = i14;
                    i9 = 1024;
                    i10 = 0;
                }
            } catch (NanoHTTPD$ResponseException e7) {
                throw e7;
            } catch (Exception e8) {
                e = e8;
                throw new NanoHTTPD$ResponseException(k.INTERNAL_ERROR, e.toString());
            }
        } catch (NanoHTTPD$ResponseException e9) {
            throw e9;
        } catch (Exception e10) {
            e = e10;
        }
    }

    public final void d() {
        byte[] bArr;
        boolean z6;
        BufferedInputStream bufferedInputStream;
        int read;
        n nVar = this.f334m;
        e eVar = this.f322a;
        OutputStream outputStream = this.f323b;
        l lVar = null;
        try {
            try {
                try {
                    try {
                        bArr = new byte[8192];
                        z6 = false;
                        this.f325d = 0;
                        this.f326e = 0;
                        bufferedInputStream = this.f324c;
                        bufferedInputStream.mark(8192);
                        try {
                            read = bufferedInputStream.read(bArr, 0, 8192);
                        } catch (SSLException e7) {
                            throw e7;
                        } catch (IOException unused) {
                            n.d(bufferedInputStream);
                            n.d(outputStream);
                            throw new SocketException("NanoHttpd Shutdown");
                        }
                    } catch (NanoHTTPD$ResponseException e8) {
                        n.c(e8.a(), n.MIME_PLAINTEXT, e8.getMessage()).D(outputStream);
                        n.d(outputStream);
                    }
                } catch (SocketException e9) {
                    throw e9;
                } catch (SSLException e10) {
                    n.c(k.INTERNAL_ERROR, n.MIME_PLAINTEXT, "SSL PROTOCOL FAILURE: " + e10.getMessage()).D(outputStream);
                    n.d(outputStream);
                }
            } catch (SocketTimeoutException e11) {
                throw e11;
            } catch (IOException e12) {
                n.c(k.INTERNAL_ERROR, n.MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + e12.getMessage()).D(outputStream);
                n.d(outputStream);
            }
            if (read == -1) {
                n.d(bufferedInputStream);
                n.d(outputStream);
                throw new SocketException("NanoHttpd Shutdown");
            }
            while (read > 0) {
                int i7 = this.f326e + read;
                this.f326e = i7;
                int e13 = e(bArr, i7);
                this.f325d = e13;
                if (e13 > 0) {
                    break;
                }
                int i8 = this.f326e;
                read = bufferedInputStream.read(bArr, i8, 8192 - i8);
            }
            if (this.f325d < this.f326e) {
                bufferedInputStream.reset();
                bufferedInputStream.skip(this.f325d);
            }
            this.f329h = new HashMap();
            HashMap hashMap = this.f330i;
            if (hashMap == null) {
                this.f330i = new HashMap();
            } else {
                hashMap.clear();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.f326e)));
            HashMap hashMap2 = new HashMap();
            a(bufferedReader, hashMap2, this.f329h, this.f330i);
            String str = this.f332k;
            if (str != null) {
                this.f330i.put("remote-addr", str);
                this.f330i.put("http-client-ip", str);
            }
            g a7 = g.a((String) hashMap2.get("method"));
            this.f328g = a7;
            if (a7 == null) {
                throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + ((String) hashMap2.get("method")) + " unhandled.");
            }
            this.f327f = (String) hashMap2.get("uri");
            this.f331j = new c(this.f330i);
            String str2 = (String) this.f330i.get("connection");
            boolean z7 = "HTTP/1.1".equals(this.f333l) && (str2 == null || !str2.matches("(?i).*close.*"));
            lVar = nVar.e(this);
            String str3 = (String) this.f330i.get("accept-encoding");
            this.f331j.a();
            lVar.I(this.f328g);
            if (n.h(lVar) && str3 != null && str3.contains("gzip")) {
                z6 = true;
            }
            lVar.G(z6);
            lVar.H(z7);
            lVar.D(outputStream);
            if (!z7 || lVar.B()) {
                throw new SocketException("NanoHttpd Shutdown");
            }
            n.d(lVar);
            eVar.a();
        } catch (Throwable th) {
            n.d(null);
            eVar.a();
            throw th;
        }
    }

    public final HashMap g() {
        HashMap hashMap = new HashMap();
        for (String str : this.f329h.keySet()) {
            hashMap.put(str, ((List) this.f329h.get(str)).get(0));
        }
        return hashMap;
    }

    public final void h(HashMap hashMap) {
        long j7;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutput dataOutput;
        ByteBuffer map;
        RandomAccessFile randomAccessFile = null;
        try {
            if (this.f330i.containsKey("content-length")) {
                j7 = Long.parseLong((String) this.f330i.get("content-length"));
            } else {
                j7 = this.f325d < this.f326e ? r4 - r3 : 0L;
            }
            if (j7 < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutput = new DataOutputStream(byteArrayOutputStream);
            } else {
                try {
                    e eVar = this.f322a;
                    d dVar = new d(eVar.f320a);
                    eVar.f321b.add(dVar);
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(dVar.f318a.getAbsolutePath(), "rw");
                    byteArrayOutputStream = null;
                    randomAccessFile = randomAccessFile2;
                    dataOutput = randomAccessFile2;
                } catch (Exception e7) {
                    throw new Error(e7);
                }
            }
            byte[] bArr = new byte[512];
            while (this.f326e >= 0 && j7 > 0) {
                int read = this.f324c.read(bArr, 0, (int) Math.min(j7, 512L));
                this.f326e = read;
                j7 -= read;
                if (read > 0) {
                    dataOutput.write(bArr, 0, read);
                }
            }
            if (byteArrayOutputStream != null) {
                map = ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            } else {
                map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                randomAccessFile.seek(0L);
            }
            if (g.POST.equals(this.f328g)) {
                b bVar = new b((String) this.f330i.get("content-type"));
                String str = bVar.f313b;
                if (!"multipart/form-data".equalsIgnoreCase(str)) {
                    byte[] bArr2 = new byte[map.remaining()];
                    map.get(bArr2);
                    String trim = new String(bArr2, bVar.a()).trim();
                    if ("application/x-www-form-urlencoded".equalsIgnoreCase(str)) {
                        c(trim, this.f329h);
                    } else if (trim.length() != 0) {
                        hashMap.put("postData", trim);
                    }
                } else {
                    if (bVar.f315d == null) {
                        throw new NanoHTTPD$ResponseException(k.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                    }
                    b(bVar, map, this.f329h, hashMap);
                }
            } else if (g.PUT.equals(this.f328g)) {
                hashMap.put("content", i(map, 0, map.limit()));
            }
        } finally {
            n.d(null);
        }
    }

    public final String i(ByteBuffer byteBuffer, int i7, int i8) {
        File file;
        ByteBuffer duplicate;
        FileOutputStream fileOutputStream;
        if (i8 <= 0) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                e eVar = this.f322a;
                d dVar = new d(eVar.f320a);
                eVar.f321b.add(dVar);
                file = dVar.f318a;
                duplicate = byteBuffer.duplicate();
                fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e7) {
            e = e7;
        }
        try {
            FileChannel channel = fileOutputStream.getChannel();
            duplicate.position(i7).limit(i7 + i8);
            channel.write(duplicate.slice());
            String absolutePath = file.getAbsolutePath();
            n.d(fileOutputStream);
            return absolutePath;
        } catch (Exception e8) {
            e = e8;
            throw new Error(e);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            n.d(fileOutputStream2);
            throw th;
        }
    }
}
