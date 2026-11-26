package cn.kuwo.autosdk;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l {

    /* renamed from: a, reason: collision with root package name */
    public static final m f3128a = new m();

    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        Inflater inflater = new Inflater();
        inflater.setInput(bArr, 0, bArr.length);
        try {
            byte[] bArr2 = new byte[1024];
            while (!inflater.finished()) {
                byteArrayOutputStream.write(bArr2, 0, inflater.inflate(bArr2));
            }
            inflater.end();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }
}
