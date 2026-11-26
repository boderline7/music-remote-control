package x4;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final int UNKNOWN_ORIENTATION = -1;

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f9634b = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* renamed from: c, reason: collision with root package name */
    public static final int[] f9635c = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* renamed from: a, reason: collision with root package name */
    public final v2.a f9636a;

    public b(InputStream inputStream) {
        this.f9636a = new v2.a(15, inputStream);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x0033 -> B:8:0x0056). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r3, int r4, int r5, android.net.Uri r6, android.net.Uri r7) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            java.io.InputStream r6 = r1.openInputStream(r6)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L42
            androidx.exifinterface.media.ExifInterface r1 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            android.content.ContentResolver r3 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            java.lang.String r2 = "rw"
            android.os.ParcelFileDescriptor r0 = r3.openFileDescriptor(r7, r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            androidx.exifinterface.media.ExifInterface r3 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            java.io.FileDescriptor r7 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            e(r1, r3, r4, r5)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3b
            if (r6 == 0) goto L2e
            r6.close()     // Catch: java.io.IOException -> L2a
            goto L2e
        L2a:
            r3 = move-exception
            r3.getMessage()
        L2e:
            r0.close()     // Catch: java.io.IOException -> L32
            goto L56
        L32:
            r3 = move-exception
            r3.getMessage()
            goto L56
        L37:
            r3 = move-exception
            r4 = r0
            r0 = r6
            goto L58
        L3b:
            r3 = move-exception
            r4 = r0
            r0 = r6
            goto L44
        L3f:
            r3 = move-exception
            r4 = r0
            goto L58
        L42:
            r3 = move-exception
            r4 = r0
        L44:
            r3.getMessage()     // Catch: java.lang.Throwable -> L57
            if (r0 == 0) goto L51
            r0.close()     // Catch: java.io.IOException -> L4d
            goto L51
        L4d:
            r3 = move-exception
            r3.getMessage()
        L51:
            if (r4 == 0) goto L56
            r4.close()     // Catch: java.io.IOException -> L32
        L56:
            return
        L57:
            r3 = move-exception
        L58:
            if (r0 == 0) goto L62
            r0.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r5 = move-exception
            r5.getMessage()
        L62:
            if (r4 == 0) goto L6c
            r4.close()     // Catch: java.io.IOException -> L68
            goto L6c
        L68:
            r4 = move-exception
            r4.getMessage()
        L6c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: x4.b.a(android.content.Context, int, int, android.net.Uri, android.net.Uri):void");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x001d -> B:7:0x002c). Please report as a decompilation issue!!! */
    public static void b(Context context, int i7, int i8, Uri uri, String str) {
        InputStream inputStream = null;
        try {
            try {
                try {
                    inputStream = context.getContentResolver().openInputStream(uri);
                    e(new ExifInterface(inputStream), new ExifInterface(str), i7, i8);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e7) {
                            e7.getMessage();
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e8.getMessage();
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (IOException e9) {
            e9.getMessage();
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x001c -> B:6:0x002b). Please report as a decompilation issue!!! */
    public static void c(Context context, ExifInterface exifInterface, int i7, int i8, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            try {
                try {
                    parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
                    e(exifInterface, new ExifInterface(parcelFileDescriptor.getFileDescriptor()), i7, i8);
                    parcelFileDescriptor.close();
                } catch (Throwable th) {
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException e7) {
                            e7.getMessage();
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                e8.getMessage();
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            }
        } catch (IOException e9) {
            e9.getMessage();
        }
    }

    public static void d(ExifInterface exifInterface, int i7, int i8, String str) {
        try {
            e(exifInterface, new ExifInterface(str), i7, i8);
        } catch (IOException e7) {
            e7.getMessage();
        }
    }

    public static void e(ExifInterface exifInterface, ExifInterface exifInterface2, int i7, int i8) {
        String[] strArr = {"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "PhotographicSensitivity", "Make", "Model", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};
        for (int i9 = 0; i9 < 22; i9++) {
            String str = strArr[i9];
            String attribute = exifInterface.getAttribute(str);
            if (!TextUtils.isEmpty(attribute)) {
                exifInterface2.setAttribute(str, attribute);
            }
        }
        exifInterface2.setAttribute("ImageWidth", String.valueOf(i7));
        exifInterface2.setAttribute("ImageLength", String.valueOf(i8));
        exifInterface2.setAttribute("Orientation", "0");
        exifInterface2.saveAttributes();
    }

    public final int f() {
        int i7;
        v2.a aVar = this.f9636a;
        int c7 = aVar.c();
        if ((c7 & 65496) != 65496 && c7 != 19789 && c7 != 18761) {
            Log.isLoggable("ImageHeaderParser", 3);
            return -1;
        }
        while (true) {
            if (((short) (((InputStream) aVar.f9388b).read() & 255)) != 255) {
                Log.isLoggable("ImageHeaderParser", 3);
                break;
            }
            short read = (short) (((InputStream) aVar.f9388b).read() & 255);
            if (read == 218) {
                break;
            }
            if (read == 217) {
                Log.isLoggable("ImageHeaderParser", 3);
                break;
            }
            i7 = aVar.c() - 2;
            if (read == 225) {
                break;
            }
            long j7 = i7;
            long j8 = 0;
            if (j7 >= 0) {
                long j9 = j7;
                while (j9 > 0) {
                    long skip = ((InputStream) aVar.f9388b).skip(j9);
                    if (skip <= 0) {
                        if (((InputStream) aVar.f9388b).read() == -1) {
                            break;
                        }
                        skip = 1;
                    }
                    j9 -= skip;
                }
                j8 = j7 - j9;
            }
            if (j8 != j7) {
                Log.isLoggable("ImageHeaderParser", 3);
                break;
            }
        }
        i7 = -1;
        if (i7 == -1) {
            Log.isLoggable("ImageHeaderParser", 3);
            return -1;
        }
        byte[] bArr = new byte[i7];
        int i8 = i7;
        while (i8 > 0) {
            int read2 = ((InputStream) aVar.f9388b).read(bArr, i7 - i8, i8);
            if (read2 == -1) {
                break;
            }
            i8 -= read2;
        }
        if (i7 - i8 != i7) {
            Log.isLoggable("ImageHeaderParser", 3);
            return -1;
        }
        byte[] bArr2 = f9634b;
        boolean z6 = i7 > bArr2.length;
        if (z6) {
            for (int i9 = 0; i9 < bArr2.length; i9++) {
                if (bArr[i9] != bArr2[i9]) {
                    break;
                }
            }
        }
        if (z6) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
            ByteBuffer byteBuffer = (ByteBuffer) wrap.order(byteOrder).limit(i7);
            short s6 = byteBuffer.getShort(6);
            if (s6 != 19789) {
                if (s6 == 18761) {
                    byteOrder = ByteOrder.LITTLE_ENDIAN;
                } else {
                    Log.isLoggable("ImageHeaderParser", 3);
                }
            }
            byteBuffer.order(byteOrder);
            int i10 = byteBuffer.getInt(10);
            short s7 = byteBuffer.getShort(i10 + 6);
            for (int i11 = 0; i11 < s7; i11++) {
                int i12 = (i11 * 12) + i10 + 8;
                if (byteBuffer.getShort(i12) == 274) {
                    short s8 = byteBuffer.getShort(i12 + 2);
                    if (s8 < 1 || s8 > 12) {
                        Log.isLoggable("ImageHeaderParser", 3);
                    } else {
                        int i13 = byteBuffer.getInt(i12 + 4);
                        if (i13 < 0) {
                            Log.isLoggable("ImageHeaderParser", 3);
                        } else {
                            Log.isLoggable("ImageHeaderParser", 3);
                            int i14 = i13 + f9635c[s8];
                            if (i14 > 4) {
                                Log.isLoggable("ImageHeaderParser", 3);
                            } else {
                                int i15 = i12 + 8;
                                if (i15 < 0 || i15 > byteBuffer.remaining()) {
                                    Log.isLoggable("ImageHeaderParser", 3);
                                } else {
                                    if (i14 >= 0 && i14 + i15 <= byteBuffer.remaining()) {
                                        return byteBuffer.getShort(i15);
                                    }
                                    Log.isLoggable("ImageHeaderParser", 3);
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }
        Log.isLoggable("ImageHeaderParser", 3);
        return -1;
    }
}
