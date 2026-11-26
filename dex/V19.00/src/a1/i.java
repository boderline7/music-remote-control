package a1;

import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.widget.TextView;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final h f215e;

    public i(TextView textView) {
        super(13, 0);
        this.f215e = new h(textView);
    }

    @Override // p3.f
    public final void D(boolean z6) {
        if (!(l.f9977j != null)) {
            return;
        }
        this.f215e.D(z6);
    }

    @Override // p3.f
    public final void G(boolean z6) {
        boolean z7 = !(l.f9977j != null);
        h hVar = this.f215e;
        if (z7) {
            hVar.f214g = z6;
        } else {
            hVar.G(z6);
        }
    }

    @Override // p3.f
    public final TransformationMethod L(TransformationMethod transformationMethod) {
        return (l.f9977j != null) ^ true ? transformationMethod : this.f215e.L(transformationMethod);
    }

    @Override // p3.f
    public final InputFilter[] r(InputFilter[] inputFilterArr) {
        return (l.f9977j != null) ^ true ? inputFilterArr : this.f215e.r(inputFilterArr);
    }

    @Override // p3.f
    public final boolean z() {
        return this.f215e.f214g;
    }
}
