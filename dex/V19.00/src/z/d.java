package z;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.MotionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10240a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MotionButton f10241b;

    public /* synthetic */ d(MotionButton motionButton, int i7) {
        this.f10240a = i7;
        this.f10241b = motionButton;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i7 = this.f10240a;
        MotionButton motionButton = this.f10241b;
        switch (i7) {
            case 0:
                outline.setRoundRect(0, 0, motionButton.getWidth(), motionButton.getHeight(), (Math.min(r10, r11) * motionButton.f1636d) / 2.0f);
                break;
            default:
                outline.setRoundRect(0, 0, motionButton.getWidth(), motionButton.getHeight(), motionButton.f1637e);
                break;
        }
    }
}
