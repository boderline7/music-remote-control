package z;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.ImageFilterView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10231a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ImageFilterView f10232b;

    public /* synthetic */ b(ImageFilterView imageFilterView, int i7) {
        this.f10231a = i7;
        this.f10232b = imageFilterView;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i7 = this.f10231a;
        ImageFilterView imageFilterView = this.f10232b;
        switch (i7) {
            case 0:
                outline.setRoundRect(0, 0, imageFilterView.getWidth(), imageFilterView.getHeight(), (Math.min(r10, r11) * imageFilterView.f1614i) / 2.0f);
                break;
            default:
                outline.setRoundRect(0, 0, imageFilterView.getWidth(), imageFilterView.getHeight(), imageFilterView.f1615j);
                break;
        }
    }
}
