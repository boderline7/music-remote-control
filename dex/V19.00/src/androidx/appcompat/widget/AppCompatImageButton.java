package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatImageButton extends ImageButton {

    /* renamed from: a, reason: collision with root package name */
    public final w f1025a;

    /* renamed from: b, reason: collision with root package name */
    public final c0 f1026b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1027c;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.imageButtonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        this.f1027c = false;
        l3.a(this, getContext());
        w wVar = new w(this);
        this.f1025a = wVar;
        wVar.g(attributeSet, i7);
        c0 c0Var = new c0(this);
        this.f1026b = c0Var;
        c0Var.c(attributeSet, i7);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1025a;
        if (wVar != null) {
            wVar.b();
        }
        c0 c0Var = this.f1026b;
        if (c0Var != null) {
            c0Var.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1025a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1025a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        n3 n3Var;
        c0 c0Var = this.f1026b;
        if (c0Var == null || (n3Var = (n3) c0Var.f1201d) == null) {
            return null;
        }
        return (ColorStateList) n3Var.f1363c;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        n3 n3Var;
        c0 c0Var = this.f1026b;
        if (c0Var == null || (n3Var = (n3) c0Var.f1201d) == null) {
            return null;
        }
        return (PorterDuff.Mode) n3Var.f1364d;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return ((((ImageView) this.f1026b.f1199b).getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1025a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1025a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c0 c0Var = this.f1026b;
        if (c0Var != null) {
            c0Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        c0 c0Var = this.f1026b;
        if (c0Var != null && drawable != null && !this.f1027c) {
            c0Var.f1198a = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (c0Var != null) {
            c0Var.a();
            if (this.f1027c) {
                return;
            }
            ImageView imageView = (ImageView) c0Var.f1199b;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(c0Var.f1198a);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i7) {
        super.setImageLevel(i7);
        this.f1027c = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i7) {
        this.f1026b.d(i7);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        c0 c0Var = this.f1026b;
        if (c0Var != null) {
            c0Var.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1025a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1025a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        c0 c0Var = this.f1026b;
        if (c0Var != null) {
            c0Var.e(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        c0 c0Var = this.f1026b;
        if (c0Var != null) {
            c0Var.f(mode);
        }
    }
}
