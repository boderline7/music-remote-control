package o1;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    public Drawable f7810a;

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void clearColorFilter() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.clearColorFilter();
        } else {
            super.clearColorFilter();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable getCurrent() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getCurrent() : super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getMinimumHeight() : super.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getMinimumWidth() : super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final int[] getState() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getState() : super.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getTransparentRegion() : super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i7) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.setLevel(i7) : super.onLevelChange(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setChangingConfigurations(int i7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setChangingConfigurations(i7);
        } else {
            super.setChangingConfigurations(i7);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(int i7, PorterDuff.Mode mode) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setColorFilter(i7, mode);
        } else {
            super.setColorFilter(i7, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setFilterBitmap(boolean z6) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setFilterBitmap(z6);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspot(float f7, float f8) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.e(drawable, f7, f8);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i7, int i8, int i9, int i10) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.f(drawable, i7, i8, i9, i10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setState(int[] iArr) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.setState(iArr) : super.setState(iArr);
    }
}
