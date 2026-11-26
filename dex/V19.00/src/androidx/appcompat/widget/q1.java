package androidx.appcompat.widget;

import android.graphics.Canvas;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q1 extends g.c {

    /* renamed from: b, reason: collision with root package name */
    public boolean f1379b;

    @Override // g.c, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.f1379b) {
            super.draw(canvas);
        }
    }

    @Override // g.c, android.graphics.drawable.Drawable
    public final void setHotspot(float f7, float f8) {
        if (this.f1379b) {
            super.setHotspot(f7, f8);
        }
    }

    @Override // g.c, android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i7, int i8, int i9, int i10) {
        if (this.f1379b) {
            super.setHotspotBounds(i7, i8, i9, i10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setState(int[] iArr) {
        if (this.f1379b) {
            return this.f6409a.setState(iArr);
        }
        return false;
    }

    @Override // g.c, android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z7) {
        if (this.f1379b) {
            return super.setVisible(z6, z7);
        }
        return false;
    }
}
