package z2;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.a0;
import com.google.android.material.internal.b0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f10253d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f10254e;

    public /* synthetic */ b(int i7, Object obj) {
        this.f10253d = i7;
        this.f10254e = obj;
    }

    @Override // l6.f
    public final void a0(int i7) {
        switch (this.f10253d) {
            case 0:
                break;
            default:
                b0 b0Var = (b0) this.f10254e;
                b0Var.f3800e = true;
                a0 a0Var = (a0) b0Var.f3801f.get();
                if (a0Var != null) {
                    a0Var.a();
                    break;
                }
                break;
        }
    }

    @Override // l6.f
    public final void c0(Typeface typeface, boolean z6) {
        int i7 = this.f10253d;
        Object obj = this.f10254e;
        switch (i7) {
            case 0:
                Chip chip = (Chip) obj;
                f fVar = chip.f3532e;
                chip.setText(fVar.E0 ? fVar.F : chip.getText());
                chip.requestLayout();
                chip.invalidate();
                break;
            default:
                if (!z6) {
                    b0 b0Var = (b0) obj;
                    b0Var.f3800e = true;
                    a0 a0Var = (a0) b0Var.f3801f.get();
                    if (a0Var != null) {
                        a0Var.a();
                        break;
                    }
                }
                break;
        }
    }
}
