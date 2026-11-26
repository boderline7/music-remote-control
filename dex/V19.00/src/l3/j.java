package l3;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Looper;
import android.provider.Settings;
import android.util.AndroidRuntimeException;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends l {

    /* renamed from: q, reason: collision with root package name */
    public static final i f7371q = new i("indicatorLevel");

    /* renamed from: l, reason: collision with root package name */
    public final m f7372l;

    /* renamed from: m, reason: collision with root package name */
    public final x0.j f7373m;

    /* renamed from: n, reason: collision with root package name */
    public final x0.i f7374n;

    /* renamed from: o, reason: collision with root package name */
    public float f7375o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f7376p;

    public j(Context context, d dVar, m mVar) {
        super(context, dVar);
        this.f7376p = false;
        this.f7372l = mVar;
        mVar.f7391b = this;
        x0.j jVar = new x0.j();
        this.f7373m = jVar;
        jVar.f9574b = 1.0f;
        jVar.f9575c = false;
        jVar.f9573a = Math.sqrt(50.0f);
        jVar.f9575c = false;
        x0.i iVar = new x0.i(this);
        iVar.f9571m = Float.MAX_VALUE;
        iVar.f9572n = false;
        this.f7374n = iVar;
        iVar.f9570l = jVar;
        if (this.f7387h != 1.0f) {
            this.f7387h = 1.0f;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            canvas.save();
            m mVar = this.f7372l;
            Rect bounds = getBounds();
            float b7 = b();
            mVar.f7390a.a();
            mVar.a(canvas, bounds, b7);
            m mVar2 = this.f7372l;
            Paint paint = this.f7388i;
            mVar2.c(canvas, paint);
            this.f7372l.b(canvas, paint, CropImageView.DEFAULT_ASPECT_RATIO, this.f7375o, b3.a.a(this.f7381b.f7345c[0], this.f7389j));
            canvas.restore();
        }
    }

    @Override // l3.l
    public final boolean f(boolean z6, boolean z7, boolean z8) {
        boolean f7 = super.f(z6, z7, z8);
        a aVar = this.f7382c;
        ContentResolver contentResolver = this.f7380a.getContentResolver();
        aVar.getClass();
        float f8 = Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (f8 == CropImageView.DEFAULT_ASPECT_RATIO) {
            this.f7376p = true;
        } else {
            this.f7376p = false;
            float f9 = 50.0f / f8;
            x0.j jVar = this.f7373m;
            jVar.getClass();
            if (f9 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                throw new IllegalArgumentException("Spring stiffness constant must be positive.");
            }
            jVar.f9573a = Math.sqrt(f9);
            jVar.f9575c = false;
        }
        return f7;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f7372l.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f7372l.e();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        this.f7374n.b();
        this.f7375o = getLevel() / 10000.0f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i7) {
        boolean z6 = this.f7376p;
        x0.i iVar = this.f7374n;
        if (z6) {
            iVar.b();
            this.f7375o = i7 / 10000.0f;
            invalidateSelf();
        } else {
            iVar.f9560b = this.f7375o * 10000.0f;
            iVar.f9561c = true;
            float f7 = i7;
            if (iVar.f9564f) {
                iVar.f9571m = f7;
            } else {
                if (iVar.f9570l == null) {
                    iVar.f9570l = new x0.j(f7);
                }
                x0.j jVar = iVar.f9570l;
                double d7 = f7;
                jVar.f9581i = d7;
                double d8 = (float) d7;
                if (d8 > Float.MAX_VALUE) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
                }
                float f8 = iVar.f9565g;
                if (d8 < f8) {
                    throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
                }
                double abs = Math.abs(iVar.f9567i * 0.75f);
                jVar.f9576d = abs;
                jVar.f9577e = abs * 62.5d;
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    throw new AndroidRuntimeException("Animations may only be started on the main thread");
                }
                boolean z7 = iVar.f9564f;
                if (!z7 && !z7) {
                    iVar.f9564f = true;
                    if (!iVar.f9561c) {
                        iVar.f9560b = iVar.f9563e.b(iVar.f9562d);
                    }
                    float f9 = iVar.f9560b;
                    if (f9 > Float.MAX_VALUE || f9 < f8) {
                        throw new IllegalArgumentException("Starting value need to be in between min value and max value");
                    }
                    ThreadLocal<x0.d> threadLocal = x0.d.sAnimatorHandler;
                    if (threadLocal.get() == null) {
                        threadLocal.set(new x0.d());
                    }
                    x0.d dVar = threadLocal.get();
                    ArrayList arrayList = dVar.f9551b;
                    if (arrayList.size() == 0) {
                        if (dVar.f9553d == null) {
                            dVar.f9553d = new x0.c(dVar.f9552c);
                        }
                        dVar.f9553d.e();
                    }
                    if (!arrayList.contains(iVar)) {
                        arrayList.add(iVar);
                    }
                }
            }
        }
        return true;
    }
}
