package p0;

import android.R;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n2 extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public final Window f8164e;

    /* renamed from: f, reason: collision with root package name */
    public final View f8165f;

    public n2(Window window, View view) {
        super(2, 0);
        this.f8164e = window;
        this.f8165f = view;
    }

    @Override // p3.f
    public final void H() {
        O(2048);
        N(4096);
    }

    @Override // p3.f
    public final void J() {
        for (int i7 = 1; i7 <= 256; i7 <<= 1) {
            if ((8 & i7) != 0) {
                Window window = this.f8164e;
                if (i7 == 1) {
                    O(4);
                    window.clearFlags(1024);
                } else if (i7 == 2) {
                    O(2);
                } else if (i7 == 8) {
                    View view = this.f8165f;
                    if (view.isInEditMode() || view.onCheckIsTextEditor()) {
                        view.requestFocus();
                    } else {
                        view = window.getCurrentFocus();
                    }
                    if (view == null) {
                        view = window.findViewById(R.id.content);
                    }
                    if (view != null && view.hasWindowFocus()) {
                        view.post(new m2(0, view));
                    }
                }
            }
        }
    }

    public final void N(int i7) {
        View decorView = this.f8164e.getDecorView();
        decorView.setSystemUiVisibility(i7 | decorView.getSystemUiVisibility());
    }

    public final void O(int i7) {
        View decorView = this.f8164e.getDecorView();
        decorView.setSystemUiVisibility((~i7) & decorView.getSystemUiVisibility());
    }

    @Override // p3.f
    public final void y(int i7) {
        for (int i8 = 1; i8 <= 256; i8 <<= 1) {
            if ((i7 & i8) != 0) {
                if (i8 == 1) {
                    N(4);
                } else if (i8 == 2) {
                    N(2);
                } else if (i8 == 8) {
                    Window window = this.f8164e;
                    ((InputMethodManager) window.getContext().getSystemService("input_method")).hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
                }
            }
        }
    }
}
