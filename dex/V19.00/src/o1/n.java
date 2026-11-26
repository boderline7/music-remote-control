package o1;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    public int f7854a;

    /* renamed from: b, reason: collision with root package name */
    public m f7855b;

    /* renamed from: c, reason: collision with root package name */
    public ColorStateList f7856c;

    /* renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f7857d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f7858e;

    /* renamed from: f, reason: collision with root package name */
    public Bitmap f7859f;

    /* renamed from: g, reason: collision with root package name */
    public ColorStateList f7860g;

    /* renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f7861h;

    /* renamed from: i, reason: collision with root package name */
    public int f7862i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f7863j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f7864k;

    /* renamed from: l, reason: collision with root package name */
    public Paint f7865l;

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.f7854a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new p(this);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new p(this);
    }
}
