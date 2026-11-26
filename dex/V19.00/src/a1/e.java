package a1;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends y0.j {

    /* renamed from: a, reason: collision with root package name */
    public final WeakReference f206a;

    /* renamed from: b, reason: collision with root package name */
    public final WeakReference f207b;

    public e(TextView textView, f fVar) {
        this.f206a = new WeakReference(textView);
        this.f207b = new WeakReference(fVar);
    }

    @Override // y0.j
    public final void b() {
        InputFilter[] filters;
        int length;
        TextView textView = (TextView) this.f206a.get();
        InputFilter inputFilter = (InputFilter) this.f207b.get();
        if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
            return;
        }
        for (InputFilter inputFilter2 : filters) {
            if (inputFilter2 == inputFilter) {
                if (textView.isAttachedToWindow()) {
                    CharSequence text = textView.getText();
                    l a7 = l.a();
                    if (text == null) {
                        length = 0;
                    } else {
                        a7.getClass();
                        length = text.length();
                    }
                    CharSequence f7 = a7.f(0, length, text);
                    if (text == f7) {
                        return;
                    }
                    int selectionStart = Selection.getSelectionStart(f7);
                    int selectionEnd = Selection.getSelectionEnd(f7);
                    textView.setText(f7);
                    if (f7 instanceof Spannable) {
                        Spannable spannable = (Spannable) f7;
                        if (selectionStart >= 0 && selectionEnd >= 0) {
                            Selection.setSelection(spannable, selectionStart, selectionEnd);
                            return;
                        } else if (selectionStart >= 0) {
                            Selection.setSelection(spannable, selectionStart);
                            return;
                        } else {
                            if (selectionEnd >= 0) {
                                Selection.setSelection(spannable, selectionEnd);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            }
        }
    }
}
