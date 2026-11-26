package y0;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b0 implements Spannable {

    /* renamed from: a, reason: collision with root package name */
    public boolean f9966a = false;

    /* renamed from: b, reason: collision with root package name */
    public Spannable f9967b;

    public b0(Spannable spannable) {
        this.f9967b = spannable;
    }

    public b0(CharSequence charSequence) {
        this.f9967b = new SpannableString(charSequence);
    }

    public final void a() {
        Spannable spannable = this.f9967b;
        if (!this.f9966a) {
            if ((Build.VERSION.SDK_INT < 28 ? new p3.f(8, 0) : new a0(8, 0)).A(spannable)) {
                this.f9967b = new SpannableString(spannable);
            }
        }
        this.f9966a = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i7) {
        return this.f9967b.charAt(i7);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.f9967b.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.f9967b.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f9967b.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f9967b.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f9967b.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i7, int i8, Class cls) {
        return this.f9967b.getSpans(i7, i8, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f9967b.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i7, int i8, Class cls) {
        return this.f9967b.nextSpanTransition(i7, i8, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.f9967b.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i7, int i8, int i9) {
        a();
        this.f9967b.setSpan(obj, i7, i8, i9);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i7, int i8) {
        return this.f9967b.subSequence(i7, i8);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f9967b.toString();
    }
}
