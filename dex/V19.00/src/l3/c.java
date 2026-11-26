package l3;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.progressindicator.BaseProgressIndicator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends o1.c {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f7341b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f7342c;

    public c(int i7, View view) {
        this.f7341b = i7;
        this.f7342c = view;
    }

    @Override // o1.c
    public final void a(Drawable drawable) {
        int i7 = this.f7341b;
        View view = this.f7342c;
        switch (i7) {
            case 0:
                BaseProgressIndicator baseProgressIndicator = (BaseProgressIndicator) view;
                baseProgressIndicator.setIndeterminate(false);
                baseProgressIndicator.setProgressCompat(baseProgressIndicator.f4024b, baseProgressIndicator.f4025c);
                break;
            case 1:
                BaseProgressIndicator baseProgressIndicator2 = (BaseProgressIndicator) view;
                if (!baseProgressIndicator2.f4029g) {
                    baseProgressIndicator2.setVisibility(baseProgressIndicator2.f4030h);
                    break;
                }
                break;
            default:
                ColorStateList colorStateList = ((MaterialCheckBox) view).f3518o;
                if (colorStateList != null) {
                    j0.b.h(drawable, colorStateList);
                    break;
                }
                break;
        }
    }

    @Override // o1.c
    public final void b(Drawable drawable) {
        switch (this.f7341b) {
            case 2:
                MaterialCheckBox materialCheckBox = (MaterialCheckBox) this.f7342c;
                ColorStateList colorStateList = materialCheckBox.f3518o;
                if (colorStateList != null) {
                    j0.b.g(drawable, colorStateList.getColorForState(materialCheckBox.f3522s, colorStateList.getDefaultColor()));
                    break;
                }
                break;
        }
    }
}
