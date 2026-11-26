package z;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.utils.widget.MotionLabel;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10242a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MotionLabel f10243b;

    public /* synthetic */ e(MotionLabel motionLabel, int i7) {
        this.f10242a = i7;
        this.f10243b = motionLabel;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i7 = this.f10242a;
        MotionLabel motionLabel = this.f10243b;
        switch (i7) {
            case 0:
                outline.setRoundRect(0, 0, motionLabel.getWidth(), motionLabel.getHeight(), (Math.min(r10, r11) * motionLabel.f1646f) / 2.0f);
                break;
            default:
                outline.setRoundRect(0, 0, motionLabel.getWidth(), motionLabel.getHeight(), motionLabel.f1647g);
                break;
        }
    }
}
