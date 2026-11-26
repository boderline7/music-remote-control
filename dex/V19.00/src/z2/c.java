package z2;

import android.graphics.Outline;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;
import com.navi.tool.view.RealtimeBlurView;
import com.yalantis.ucrop.view.CropImageView;
import p3.c0;
import p3.d0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends ViewOutlineProvider {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f10255a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f10256b;

    public /* synthetic */ c(int i7, Object obj) {
        this.f10255a = i7;
        this.f10256b = obj;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        int i7 = this.f10255a;
        Object obj = this.f10256b;
        switch (i7) {
            case 0:
                f fVar = ((Chip) obj).f3532e;
                if (fVar == null) {
                    outline.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
                    break;
                } else {
                    fVar.getOutline(outline);
                    break;
                }
            case 1:
                c0 c0Var = (c0) obj;
                if (c0Var.f8229c != null && !c0Var.f8230d.isEmpty()) {
                    RectF rectF = c0Var.f8230d;
                    outline.setRoundRect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom, c0Var.f8234g);
                    break;
                }
                break;
            case 2:
                d0 d0Var = (d0) obj;
                if (!d0Var.f8231e.isEmpty()) {
                    outline.setPath(d0Var.f8231e);
                    break;
                }
                break;
            default:
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), ((RealtimeBlurView) obj).f5994q);
                break;
        }
    }
}
