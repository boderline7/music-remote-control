package l3;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import androidx.appcompat.widget.i3;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l extends Drawable implements Animatable {

    /* renamed from: k, reason: collision with root package name */
    public static final i3 f7379k = new i3(Float.class, "growFraction", 14);

    /* renamed from: a, reason: collision with root package name */
    public final Context f7380a;

    /* renamed from: b, reason: collision with root package name */
    public final d f7381b;

    /* renamed from: d, reason: collision with root package name */
    public ValueAnimator f7383d;

    /* renamed from: e, reason: collision with root package name */
    public ValueAnimator f7384e;

    /* renamed from: f, reason: collision with root package name */
    public ArrayList f7385f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f7386g;

    /* renamed from: h, reason: collision with root package name */
    public float f7387h;

    /* renamed from: j, reason: collision with root package name */
    public int f7389j;

    /* renamed from: i, reason: collision with root package name */
    public final Paint f7388i = new Paint();

    /* renamed from: c, reason: collision with root package name */
    public a f7382c = new a();

    public l(Context context, d dVar) {
        this.f7380a = context;
        this.f7381b = dVar;
        setAlpha(255);
    }

    public final float b() {
        d dVar = this.f7381b;
        if (dVar.f7347e == 0 && dVar.f7348f == 0) {
            return 1.0f;
        }
        return this.f7387h;
    }

    public final boolean c() {
        ValueAnimator valueAnimator = this.f7384e;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public final boolean d() {
        ValueAnimator valueAnimator = this.f7383d;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    public final boolean e(boolean z6, boolean z7, boolean z8) {
        a aVar = this.f7382c;
        ContentResolver contentResolver = this.f7380a.getContentResolver();
        aVar.getClass();
        return f(z6, z7, z8 && Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) > CropImageView.DEFAULT_ASPECT_RATIO);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean f(boolean z6, boolean z7, boolean z8) {
        ValueAnimator valueAnimator = this.f7383d;
        i3 i3Var = f7379k;
        Object[] objArr = 0;
        if (valueAnimator == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, i3Var, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            this.f7383d = ofFloat;
            ofFloat.setDuration(500L);
            this.f7383d.setInterpolator(r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator2 = this.f7383d;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
            }
            this.f7383d = valueAnimator2;
            valueAnimator2.addListener(new k(this, objArr == true ? 1 : 0));
        }
        int i7 = 1;
        if (this.f7384e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, i3Var, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
            this.f7384e = ofFloat2;
            ofFloat2.setDuration(500L);
            this.f7384e.setInterpolator(r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR);
            ValueAnimator valueAnimator3 = this.f7384e;
            if (valueAnimator3 != null && valueAnimator3.isRunning()) {
                throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
            }
            this.f7384e = valueAnimator3;
            valueAnimator3.addListener(new k(this, i7));
        }
        if (!isVisible() && !z6) {
            return false;
        }
        ValueAnimator valueAnimator4 = z6 ? this.f7383d : this.f7384e;
        ValueAnimator valueAnimator5 = z6 ? this.f7384e : this.f7383d;
        if (!z8) {
            if (valueAnimator5.isRunning()) {
                boolean z9 = this.f7386g;
                this.f7386g = true;
                valueAnimator5.cancel();
                this.f7386g = z9;
            }
            if (valueAnimator4.isRunning()) {
                valueAnimator4.end();
            } else {
                boolean z10 = this.f7386g;
                this.f7386g = true;
                valueAnimator4.end();
                this.f7386g = z10;
            }
            return super.setVisible(z6, false);
        }
        if (z8 && valueAnimator4.isRunning()) {
            return false;
        }
        boolean z11 = !z6 || super.setVisible(z6, false);
        d dVar = this.f7381b;
        if (!z6 ? dVar.f7348f != 0 : dVar.f7347e != 0) {
            boolean z12 = this.f7386g;
            this.f7386g = true;
            valueAnimator4.end();
            this.f7386g = z12;
            return z11;
        }
        if (z7 || !valueAnimator4.isPaused()) {
            valueAnimator4.start();
        } else {
            valueAnimator4.resume();
        }
        return z11;
    }

    public final void g(c cVar) {
        ArrayList arrayList = this.f7385f;
        if (arrayList == null || !arrayList.contains(cVar)) {
            return;
        }
        this.f7385f.remove(cVar);
        if (this.f7385f.isEmpty()) {
            this.f7385f = null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f7389j;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return d() || c();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f7389j = i7;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f7388i.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z7) {
        return e(z6, z7, true);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        f(true, true, false);
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        f(false, true, false);
    }
}
