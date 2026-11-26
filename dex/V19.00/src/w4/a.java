package w4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.exifinterface.media.ExifInterface;
import com.yalantis.ucrop.UCropActivity;
import java.io.File;
import java.lang.ref.WeakReference;
import n1.z;
import t4.k;
import y2.d;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends AsyncTask {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f9470a;

    /* renamed from: b, reason: collision with root package name */
    public Bitmap f9471b;

    /* renamed from: c, reason: collision with root package name */
    public final RectF f9472c;

    /* renamed from: d, reason: collision with root package name */
    public final RectF f9473d;

    /* renamed from: e, reason: collision with root package name */
    public float f9474e;

    /* renamed from: f, reason: collision with root package name */
    public final float f9475f;

    /* renamed from: g, reason: collision with root package name */
    public final int f9476g;

    /* renamed from: h, reason: collision with root package name */
    public final int f9477h;

    /* renamed from: i, reason: collision with root package name */
    public final Bitmap.CompressFormat f9478i;

    /* renamed from: j, reason: collision with root package name */
    public final int f9479j;

    /* renamed from: k, reason: collision with root package name */
    public final String f9480k;

    /* renamed from: l, reason: collision with root package name */
    public final String f9481l;

    /* renamed from: m, reason: collision with root package name */
    public final Uri f9482m;

    /* renamed from: n, reason: collision with root package name */
    public final Uri f9483n;

    /* renamed from: o, reason: collision with root package name */
    public final k f9484o;

    /* renamed from: p, reason: collision with root package name */
    public int f9485p;

    /* renamed from: q, reason: collision with root package name */
    public int f9486q;

    /* renamed from: r, reason: collision with root package name */
    public int f9487r;

    /* renamed from: s, reason: collision with root package name */
    public int f9488s;

    public a(Context context, Bitmap bitmap, d dVar, a4.c cVar, k kVar) {
        this.f9470a = new WeakReference(context);
        this.f9471b = bitmap;
        this.f9472c = (RectF) dVar.f10135c;
        this.f9473d = (RectF) dVar.f10136d;
        this.f9474e = dVar.f10133a;
        this.f9475f = dVar.f10134b;
        this.f9476g = cVar.f267a;
        this.f9477h = cVar.f268b;
        this.f9478i = (Bitmap.CompressFormat) cVar.f271e;
        this.f9479j = cVar.f269c;
        this.f9480k = cVar.f270d;
        this.f9481l = (String) cVar.f272f;
        this.f9482m = (Uri) cVar.f274h;
        this.f9483n = (Uri) cVar.f275i;
        this.f9484o = kVar;
    }

    public final void a(Context context) {
        Uri uri = this.f9482m;
        boolean w02 = z.w0(uri);
        Uri uri2 = this.f9483n;
        boolean w03 = z.w0(uri2);
        if (w02 && w03) {
            x4.b.a(context, this.f9485p, this.f9486q, uri, uri2);
            return;
        }
        String str = this.f9481l;
        if (w02) {
            x4.b.b(context, this.f9485p, this.f9486q, uri, str);
            return;
        }
        String str2 = this.f9480k;
        if (w03) {
            x4.b.c(context, new ExifInterface(str2), this.f9485p, this.f9486q, uri2);
        } else {
            x4.b.d(new ExifInterface(str2), this.f9485p, this.f9486q, str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x011f, code lost:
    
        if (r7 == com.yalantis.ucrop.view.CropImageView.DEFAULT_ASPECT_RATIO) goto L35;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b() {
        /*
            Method dump skipped, instructions count: 495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: w4.a.b():void");
    }

    @Override // android.os.AsyncTask
    public final Object doInBackground(Object[] objArr) {
        Bitmap bitmap = this.f9471b;
        if (bitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (bitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.f9473d.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        if (this.f9483n == null) {
            return new NullPointerException("ImageOutputUri is null");
        }
        try {
            b();
            this.f9471b = null;
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        Throwable th = (Throwable) obj;
        k kVar = this.f9484o;
        if (kVar != null) {
            UCropActivity uCropActivity = kVar.f9087b;
            if (th != null) {
                uCropActivity.f(th);
                uCropActivity.finish();
                return;
            }
            Uri uri = this.f9483n;
            if (!z.w0(uri)) {
                uri = Uri.fromFile(new File(this.f9481l));
            }
            int i7 = this.f9487r;
            int i8 = this.f9488s;
            int i9 = this.f9485p;
            uCropActivity.setResult(-1, new Intent().putExtra("com.yalantis.ucrop.OutputUri", uri).putExtra("com.yalantis.ucrop.CropAspectRatio", uCropActivity.f6151l.getTargetAspectRatio()).putExtra("com.yalantis.ucrop.ImageWidth", i9).putExtra("com.yalantis.ucrop.ImageHeight", this.f9486q).putExtra("com.yalantis.ucrop.OffsetX", i7).putExtra("com.yalantis.ucrop.OffsetY", i8));
            uCropActivity.finish();
        }
    }
}
