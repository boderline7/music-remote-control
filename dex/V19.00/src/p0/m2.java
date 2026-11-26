package p0;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.bottomappbar.BottomAppBar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8162a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f8163b;

    public /* synthetic */ m2(int i7, View view) {
        this.f8162a = i7;
        this.f8163b = view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f8162a;
        boolean z6 = false;
        Object[] objArr = 0;
        View view = this.f8163b;
        switch (i7) {
            case 0:
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
                break;
            case 1:
                int i8 = BottomAppBar.f3327v0;
                view.requestLayout();
                break;
            default:
                view.requestFocus();
                view.post(new com.google.android.material.internal.g0(view, z6, objArr == true ? 1 : 0));
                break;
        }
    }
}
