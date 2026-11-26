package p0;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class g2 extends f2 {
    public g2(l2 l2Var, WindowInsets windowInsets) {
        super(l2Var, windowInsets);
    }

    @Override // p0.j2
    public l2 a() {
        WindowInsets consumeDisplayCutout;
        consumeDisplayCutout = this.f8127c.consumeDisplayCutout();
        return l2.h(null, consumeDisplayCutout);
    }

    @Override // p0.j2
    public k e() {
        DisplayCutout displayCutout;
        displayCutout = this.f8127c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new k(displayCutout);
    }

    @Override // p0.e2, p0.j2
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g2)) {
            return false;
        }
        g2 g2Var = (g2) obj;
        return Objects.equals(this.f8127c, g2Var.f8127c) && Objects.equals(this.f8131g, g2Var.f8131g);
    }

    @Override // p0.j2
    public int hashCode() {
        return this.f8127c.hashCode();
    }
}
