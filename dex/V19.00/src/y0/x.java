package y0;

import android.os.Build;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.TextWatcher;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x implements TextWatcher, SpanWatcher {

    /* renamed from: a, reason: collision with root package name */
    public final Object f10026a;

    /* renamed from: b, reason: collision with root package name */
    public final AtomicInteger f10027b = new AtomicInteger(0);

    public x(Object obj) {
        this.f10026a = obj;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        ((TextWatcher) this.f10026a).afterTextChanged(editable);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        ((TextWatcher) this.f10026a).beforeTextChanged(charSequence, i7, i8, i9);
    }

    @Override // android.text.SpanWatcher
    public final void onSpanAdded(Spannable spannable, Object obj, int i7, int i8) {
        if (this.f10027b.get() <= 0 || !(obj instanceof z)) {
            ((SpanWatcher) this.f10026a).onSpanAdded(spannable, obj, i7, i8);
        }
    }

    @Override // android.text.SpanWatcher
    public final void onSpanChanged(Spannable spannable, Object obj, int i7, int i8, int i9, int i10) {
        int i11;
        int i12;
        if (this.f10027b.get() <= 0 || !(obj instanceof z)) {
            if (Build.VERSION.SDK_INT < 28) {
                if (i7 > i8) {
                    i7 = 0;
                }
                if (i9 > i10) {
                    i11 = i7;
                    i12 = 0;
                    ((SpanWatcher) this.f10026a).onSpanChanged(spannable, obj, i11, i8, i12, i10);
                }
            }
            i11 = i7;
            i12 = i9;
            ((SpanWatcher) this.f10026a).onSpanChanged(spannable, obj, i11, i8, i12, i10);
        }
    }

    @Override // android.text.SpanWatcher
    public final void onSpanRemoved(Spannable spannable, Object obj, int i7, int i8) {
        if (this.f10027b.get() <= 0 || !(obj instanceof z)) {
            ((SpanWatcher) this.f10026a).onSpanRemoved(spannable, obj, i7, i8);
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        ((TextWatcher) this.f10026a).onTextChanged(charSequence, i7, i8, i9);
    }
}
