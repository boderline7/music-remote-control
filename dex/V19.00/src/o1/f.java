package o1;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import h0.q;
import java.util.ArrayList;
import n1.z;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends g implements Animatable {

    /* renamed from: c, reason: collision with root package name */
    public final Context f7806c;

    /* renamed from: d, reason: collision with root package name */
    public androidx.appcompat.widget.d f7807d = null;

    /* renamed from: e, reason: collision with root package name */
    public ArrayList f7808e = null;

    /* renamed from: f, reason: collision with root package name */
    public final g.a f7809f = new g.a(this);

    /* renamed from: b, reason: collision with root package name */
    public final d f7805b = new d();

    public f(Context context) {
        this.f7806c = context;
    }

    @Override // o1.g, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return j0.b.b(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        d dVar = this.f7805b;
        dVar.f7799a.draw(canvas);
        if (dVar.f7800b.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.a.a(drawable) : this.f7805b.f7799a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        int changingConfigurations = super.getChangingConfigurations();
        this.f7805b.getClass();
        return changingConfigurations | 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.b.c(drawable) : this.f7805b.f7799a.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f7810a != null) {
            return new e(this.f7810a.getConstantState());
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f7805b.f7799a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f7805b.f7799a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.getOpacity() : this.f7805b.f7799a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        d dVar;
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.b.d(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            dVar = this.f7805b;
            if (eventType == 1 || (xmlPullParser.getDepth() < depth && eventType == 3)) {
                break;
            }
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray O0 = z.O0(resources, theme, attributeSet, a.f7795e);
                    int resourceId = O0.getResourceId(0, 0);
                    if (resourceId != 0) {
                        p pVar = new p();
                        ThreadLocal threadLocal = q.f6642a;
                        pVar.f7810a = h0.j.a(resources, resourceId, theme);
                        new o(pVar.f7810a.getConstantState());
                        pVar.f7872f = false;
                        pVar.setCallback(this.f7809f);
                        p pVar2 = dVar.f7799a;
                        if (pVar2 != null) {
                            pVar2.setCallback(null);
                        }
                        dVar.f7799a = pVar;
                    }
                    O0.recycle();
                } else if ("target".equals(name)) {
                    TypedArray obtainAttributes = resources.obtainAttributes(attributeSet, a.f7796f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f7806c;
                        if (context == null) {
                            obtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        Animator loadAnimator = AnimatorInflater.loadAnimator(context, resourceId2);
                        loadAnimator.setTarget(dVar.f7799a.f7868b.f7855b.f7853o.getOrDefault(string, null));
                        if (dVar.f7801c == null) {
                            dVar.f7801c = new ArrayList();
                            dVar.f7802d = new q.b();
                        }
                        dVar.f7801c.add(loadAnimator);
                        dVar.f7802d.put(loadAnimator, string);
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        if (dVar.f7800b == null) {
            dVar.f7800b = new AnimatorSet();
        }
        dVar.f7800b.playTogether(dVar.f7801c);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f7810a;
        return drawable != null ? j0.a.d(drawable) : this.f7805b.f7799a.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        Drawable drawable = this.f7810a;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f7805b.f7800b.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.isStateful() : this.f7805b.f7799a.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f7805b.f7799a.setBounds(rect);
        }
    }

    @Override // o1.g, android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i7) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.setLevel(i7) : this.f7805b.f7799a.setLevel(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f7810a;
        return drawable != null ? drawable.setState(iArr) : this.f7805b.f7799a.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setAlpha(i7);
        } else {
            this.f7805b.f7799a.setAlpha(i7);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z6) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            j0.a.e(drawable, z6);
        } else {
            this.f7805b.f7799a.setAutoMirrored(z6);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f7805b.f7799a.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            z.b1(drawable, i7);
        } else {
            this.f7805b.f7799a.setTint(i7);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            z.c1(drawable, colorStateList);
        } else {
            this.f7805b.f7799a.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            z.d1(drawable, mode);
        } else {
            this.f7805b.f7799a.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z7) {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            return drawable.setVisible(z6, z7);
        }
        this.f7805b.f7799a.setVisible(z6, z7);
        return super.setVisible(z6, z7);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
            return;
        }
        d dVar = this.f7805b;
        if (dVar.f7800b.isStarted()) {
            return;
        }
        dVar.f7800b.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        Drawable drawable = this.f7810a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f7805b.f7800b.end();
        }
    }
}
