package g;

import android.graphics.drawable.Drawable;
import o1.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Drawable.Callback {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6407a = 1;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6408b;

    public a(f fVar) {
        this.f6408b = fVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        switch (this.f6407a) {
            case 0:
                break;
            default:
                ((f) this.f6408b).invalidateSelf();
                break;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j7) {
        switch (this.f6407a) {
            case 0:
                Drawable.Callback callback = (Drawable.Callback) this.f6408b;
                if (callback != null) {
                    callback.scheduleDrawable(drawable, runnable, j7);
                    break;
                }
                break;
            default:
                ((f) this.f6408b).scheduleSelf(runnable, j7);
                break;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        switch (this.f6407a) {
            case 0:
                Drawable.Callback callback = (Drawable.Callback) this.f6408b;
                if (callback != null) {
                    callback.unscheduleDrawable(drawable, runnable);
                    break;
                }
                break;
            default:
                ((f) this.f6408b).unscheduleSelf(runnable);
                break;
        }
    }
}
