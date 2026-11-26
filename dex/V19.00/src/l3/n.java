package l3;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends l {

    /* renamed from: l, reason: collision with root package name */
    public final m f7392l;

    /* renamed from: m, reason: collision with root package name */
    public k.d f7393m;

    public n(Context context, d dVar, m mVar, k.d dVar2) {
        super(context, dVar);
        this.f7392l = mVar;
        mVar.f7391b = this;
        this.f7393m = dVar2;
        dVar2.f7060a = this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (getBounds().isEmpty() || !isVisible() || !canvas.getClipBounds(rect)) {
            return;
        }
        canvas.save();
        m mVar = this.f7392l;
        Rect bounds = getBounds();
        float b7 = b();
        mVar.f7390a.a();
        mVar.a(canvas, bounds, b7);
        m mVar2 = this.f7392l;
        Paint paint = this.f7388i;
        mVar2.c(canvas, paint);
        int i7 = 0;
        while (true) {
            k.d dVar = this.f7393m;
            int[] iArr = (int[]) dVar.f7062c;
            if (i7 >= iArr.length) {
                canvas.restore();
                return;
            }
            m mVar3 = this.f7392l;
            float[] fArr = (float[]) dVar.f7061b;
            int i8 = i7 * 2;
            mVar3.b(canvas, paint, fArr[i8], fArr[i8 + 1], iArr[i7]);
            i7++;
        }
    }

    @Override // l3.l
    public final boolean f(boolean z6, boolean z7, boolean z8) {
        boolean f7 = super.f(z6, z7, z8);
        if (!isRunning()) {
            this.f7393m.c();
        }
        a aVar = this.f7382c;
        ContentResolver contentResolver = this.f7380a.getContentResolver();
        aVar.getClass();
        Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (z6 && z8) {
            this.f7393m.s();
        }
        return f7;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f7392l.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f7392l.e();
    }
}
