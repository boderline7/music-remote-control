package o1;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7803a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7804b;

    public e(Drawable.ConstantState constantState) {
        this.f7804b = constantState;
    }

    public e(g3.b bVar) {
        this.f7804b = bVar;
    }

    public /* synthetic */ e(g3.b bVar, int i7) {
        this(bVar);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        switch (this.f7803a) {
            case 0:
                return ((Drawable.ConstantState) this.f7804b).canApplyTheme();
            default:
                return super.canApplyTheme();
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        switch (this.f7803a) {
            case 0:
                return ((Drawable.ConstantState) this.f7804b).getChangingConfigurations();
            default:
                return 0;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        int i7 = this.f7803a;
        Object obj = this.f7804b;
        switch (i7) {
            case 0:
                f fVar = new f(null);
                Drawable newDrawable = ((Drawable.ConstantState) obj).newDrawable();
                fVar.f7810a = newDrawable;
                newDrawable.setCallback(fVar.f7809f);
                return fVar;
            default:
                return (g3.b) obj;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        switch (this.f7803a) {
            case 0:
                f fVar = new f(null);
                Drawable newDrawable = ((Drawable.ConstantState) this.f7804b).newDrawable(resources);
                fVar.f7810a = newDrawable;
                newDrawable.setCallback(fVar.f7809f);
                return fVar;
            default:
                return super.newDrawable(resources);
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        switch (this.f7803a) {
            case 0:
                f fVar = new f(null);
                Drawable newDrawable = ((Drawable.ConstantState) this.f7804b).newDrawable(resources, theme);
                fVar.f7810a = newDrawable;
                newDrawable.setCallback(fVar.f7809f);
                return fVar;
            default:
                return super.newDrawable(resources, theme);
        }
    }
}
