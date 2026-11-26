package y0;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z extends ReplacementSpan {

    /* renamed from: b, reason: collision with root package name */
    public final q f10031b;

    /* renamed from: a, reason: collision with root package name */
    public final Paint.FontMetricsInt f10030a = new Paint.FontMetricsInt();

    /* renamed from: c, reason: collision with root package name */
    public float f10032c = 1.0f;

    public z(q qVar) {
        if (qVar == null) {
            throw new NullPointerException("metadata cannot be null");
        }
        this.f10031b = qVar;
    }

    @Override // android.text.style.ReplacementSpan
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int getSize(Paint paint, CharSequence charSequence, int i7, int i8, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.f10030a;
        paint.getFontMetricsInt(fontMetricsInt2);
        float abs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        q qVar = this.f10031b;
        this.f10032c = abs / (qVar.c().a(14) != 0 ? r8.f10245b.getShort(r1 + r8.f10244a) : (short) 0);
        z0.a c7 = qVar.c();
        int a7 = c7.a(14);
        if (a7 != 0) {
            c7.f10245b.getShort(a7 + c7.f10244a);
        }
        short s6 = (short) ((qVar.c().a(12) != 0 ? r5.f10245b.getShort(r7 + r5.f10244a) : (short) 0) * this.f10032c);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s6;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i7, int i8, float f7, int i9, int i10, int i11, Paint paint) {
        l.a().getClass();
        q qVar = this.f10031b;
        j.h hVar = qVar.f9997b;
        Typeface typeface = (Typeface) hVar.f6893d;
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        canvas.drawText((char[]) hVar.f6891b, qVar.f9996a * 2, 2, f7, i10, paint);
        paint.setTypeface(typeface2);
    }
}
