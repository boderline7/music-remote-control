package g3;

import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements h {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6455a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ExtendedFloatingActionButton f6456b;

    public /* synthetic */ c(ExtendedFloatingActionButton extendedFloatingActionButton, int i7) {
        this.f6455a = i7;
        this.f6456b = extendedFloatingActionButton;
    }

    @Override // g3.h
    public final int k() {
        int i7 = this.f6455a;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6456b;
        switch (i7) {
            case 0:
                return extendedFloatingActionButton.getCollapsedSize();
            default:
                return (extendedFloatingActionButton.getMeasuredWidth() - (extendedFloatingActionButton.getCollapsedPadding() * 2)) + extendedFloatingActionButton.A + extendedFloatingActionButton.B;
        }
    }

    @Override // g3.h
    public final int l() {
        int i7 = this.f6455a;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6456b;
        switch (i7) {
            case 0:
                return extendedFloatingActionButton.getCollapsedSize();
            default:
                return extendedFloatingActionButton.getMeasuredHeight();
        }
    }

    @Override // g3.h
    public final int n() {
        int i7 = this.f6455a;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6456b;
        switch (i7) {
            case 0:
                return extendedFloatingActionButton.getCollapsedPadding();
            default:
                return extendedFloatingActionButton.B;
        }
    }

    @Override // g3.h
    public final int p() {
        int i7 = this.f6455a;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6456b;
        switch (i7) {
            case 0:
                return extendedFloatingActionButton.getCollapsedPadding();
            default:
                return extendedFloatingActionButton.A;
        }
    }

    @Override // g3.h
    public final ViewGroup.LayoutParams s() {
        switch (this.f6455a) {
            case 0:
                return new ViewGroup.LayoutParams(k(), l());
            default:
                return new ViewGroup.LayoutParams(-2, -2);
        }
    }
}
