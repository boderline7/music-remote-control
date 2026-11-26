package z;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10229a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ImageFilterButton f10230b;

    public /* synthetic */ a(ImageFilterButton imageFilterButton, int i7) {
        this.f10229a = i7;
        this.f10230b = imageFilterButton;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i7 = this.f10229a;
        ImageFilterButton imageFilterButton = this.f10230b;
        switch (i7) {
            case 0:
                outline.setRoundRect(0, 0, imageFilterButton.getWidth(), imageFilterButton.getHeight(), (Math.min(r10, r11) * imageFilterButton.f1595f) / 2.0f);
                break;
            default:
                outline.setRoundRect(0, 0, imageFilterButton.getWidth(), imageFilterButton.getHeight(), imageFilterButton.f1596g);
                break;
        }
    }
}
