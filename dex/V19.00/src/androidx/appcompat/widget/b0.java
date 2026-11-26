package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 {

    /* renamed from: a, reason: collision with root package name */
    public final TextView f1191a;

    /* renamed from: b, reason: collision with root package name */
    public final e.a f1192b;

    public b0(TextView textView) {
        this.f1191a = textView;
        this.f1192b = new e.a(textView);
    }

    public final InputFilter[] a(InputFilter[] inputFilterArr) {
        return ((p3.f) this.f1192b.f6290b).r(inputFilterArr);
    }

    public final void b(AttributeSet attributeSet, int i7) {
        TypedArray obtainStyledAttributes = this.f1191a.getContext().obtainStyledAttributes(attributeSet, f.j.AppCompatTextView, i7, 0);
        try {
            boolean z6 = obtainStyledAttributes.hasValue(f.j.AppCompatTextView_emojiCompatEnabled) ? obtainStyledAttributes.getBoolean(f.j.AppCompatTextView_emojiCompatEnabled, true) : true;
            obtainStyledAttributes.recycle();
            d(z6);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void c(boolean z6) {
        ((p3.f) this.f1192b.f6290b).D(z6);
    }

    public final void d(boolean z6) {
        ((p3.f) this.f1192b.f6290b).G(z6);
    }
}
