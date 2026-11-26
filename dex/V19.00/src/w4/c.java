package w4;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.TransformImageView;
import g6.d;
import g6.d0;
import g6.j;
import g6.p;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.logging.Logger;
import n1.z;
import s5.a1;
import s5.o0;
import s5.r0;
import s5.x0;
import s5.y0;
import t4.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends AsyncTask {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f9492a;

    /* renamed from: b, reason: collision with root package name */
    public Uri f9493b;

    /* renamed from: c, reason: collision with root package name */
    public final Uri f9494c;

    /* renamed from: d, reason: collision with root package name */
    public final int f9495d;

    /* renamed from: e, reason: collision with root package name */
    public final int f9496e;

    /* renamed from: f, reason: collision with root package name */
    public final v2.a f9497f;

    public c(Context context, Uri uri, Uri uri2, int i7, int i8, v2.a aVar) {
        this.f9492a = new WeakReference(context);
        this.f9493b = uri;
        this.f9494c = uri2;
        this.f9495d = i7;
        this.f9496e = i8;
        this.f9497f = aVar;
    }

    public final void a(Uri uri, Uri uri2) {
        InputStream inputStream;
        Uri uri3 = this.f9494c;
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
        Context context = (Context) this.f9492a.get();
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            try {
                if (inputStream == null) {
                    throw new NullPointerException("InputStream for given input Uri is null");
                }
                OutputStream openOutputStream = uri2.getScheme().equals("content") ? context.getContentResolver().openOutputStream(uri2) : new FileOutputStream(new File(uri2.getPath()));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        z.z(openOutputStream);
                        z.z(inputStream);
                        this.f9493b = uri3;
                        return;
                    }
                    openOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable th) {
                th = th;
                z.z(null);
                z.z(inputStream);
                this.f9493b = uri3;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    public final void b(Uri uri, Uri uri2) {
        x0 x0Var;
        d dVar;
        Uri uri3 = this.f9494c;
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        Context context = (Context) this.f9492a.get();
        if (context == null) {
            throw new NullPointerException("Context is null");
        }
        t4.a aVar = t4.a.INSTANCE;
        if (aVar.f9084a == null) {
            aVar.f9084a = new o0();
        }
        o0 o0Var = aVar.f9084a;
        j jVar = null;
        try {
            r0 r0Var = new r0();
            r0Var.f(uri.toString());
            x0Var = o0Var.a(r0Var.a()).f();
            a1 a1Var = x0Var.f8919g;
            try {
                y0 y0Var = (y0) a1Var;
                int i7 = y0Var.f8926a;
                j jVar2 = y0Var.f8928c;
                try {
                    OutputStream openOutputStream = uri3.getScheme().equals("content") ? context.getContentResolver().openOutputStream(uri2) : new FileOutputStream(new File(uri2.getPath()));
                    if (openOutputStream == null) {
                        throw new NullPointerException("OutputStream for given output Uri is null");
                    }
                    Logger logger = p.f6587a;
                    dVar = new d(openOutputStream, new d0());
                    try {
                        jVar2.m(dVar);
                        z.z(jVar2);
                        z.z(dVar);
                        z.z(a1Var);
                        o0Var.f8842a.b();
                        this.f9493b = uri3;
                    } catch (Throwable th) {
                        th = th;
                        jVar = jVar2;
                        z.z(jVar);
                        z.z(dVar);
                        if (x0Var != null) {
                            z.z(x0Var.f8919g);
                        }
                        o0Var.f8842a.b();
                        this.f9493b = uri3;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    dVar = null;
                }
            } catch (Throwable th3) {
                th = th3;
                dVar = null;
            }
        } catch (Throwable th4) {
            th = th4;
            x0Var = null;
            dVar = null;
        }
    }

    public final void c() {
        this.f9493b.getScheme();
        String scheme = this.f9493b.getScheme();
        boolean equals = scheme.equals("http");
        Uri uri = this.f9494c;
        if (equals || scheme.equals("https")) {
            b(this.f9493b, uri);
        } else if (this.f9493b.getScheme().equals("content")) {
            a(this.f9493b, uri);
        } else if (!this.f9493b.getScheme().equals("file")) {
            throw new IllegalArgumentException(a.a.B("Invalid Uri scheme", this.f9493b.getScheme()));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
    
        r8 = new w4.b(new java.lang.IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + r14.f9493b + "]"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b1, code lost:
    
        n1.z.z(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
    
        return r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0178  */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object doInBackground(java.lang.Object[] r15) {
        /*
            Method dump skipped, instructions count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: w4.c.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        b bVar = (b) obj;
        Exception exc = bVar.f9491c;
        v2.a aVar = this.f9497f;
        if (exc != null) {
            y4.d dVar = ((TransformImageView) aVar.f9388b).f6207j;
            if (dVar != null) {
                UCropActivity uCropActivity = ((k) dVar).f9087b;
                uCropActivity.f(exc);
                uCropActivity.finish();
                return;
            }
            return;
        }
        Uri uri = this.f9493b;
        TransformImageView transformImageView = (TransformImageView) aVar.f9388b;
        transformImageView.f6215r = uri;
        Uri uri2 = this.f9494c;
        transformImageView.f6216s = uri2;
        transformImageView.f6213p = uri.getPath();
        ((TransformImageView) aVar.f9388b).f6214q = uri2 != null ? uri2.getPath() : null;
        TransformImageView transformImageView2 = (TransformImageView) aVar.f9388b;
        transformImageView2.f6217t = bVar.f9490b;
        transformImageView2.f6210m = true;
        transformImageView2.setImageBitmap(bVar.f9489a);
    }
}
