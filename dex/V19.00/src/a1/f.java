package a1;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f implements InputFilter {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f208a;

    /* renamed from: b, reason: collision with root package name */
    public e f209b;

    public f(TextView textView) {
        this.f208a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i7, int i8, Spanned spanned, int i9, int i10) {
        TextView textView = this.f208a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int b7 = l.a().b();
        if (b7 != 0) {
            if (b7 == 1) {
                if ((i10 == 0 && i9 == 0 && spanned.length() == 0 && charSequence == textView.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i7 != 0 || i8 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i7, i8);
                }
                return l.a().f(0, charSequence.length(), charSequence);
            }
            if (b7 != 3) {
                return charSequence;
            }
        }
        l a7 = l.a();
        if (this.f209b == null) {
            this.f209b = new e(textView, this);
        }
        a7.g(this.f209b);
        return charSequence;
    }
}
