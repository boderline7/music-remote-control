package n0;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final TextPaint f7576a;

    /* renamed from: b, reason: collision with root package name */
    public final TextDirectionHeuristic f7577b;

    /* renamed from: c, reason: collision with root package name */
    public final int f7578c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7579d;

    public e(PrecomputedText.Params params) {
        TextPaint textPaint;
        TextDirectionHeuristic textDirection;
        int breakStrategy;
        int hyphenationFrequency;
        textPaint = params.getTextPaint();
        this.f7576a = textPaint;
        textDirection = params.getTextDirection();
        this.f7577b = textDirection;
        breakStrategy = params.getBreakStrategy();
        this.f7578c = breakStrategy;
        hyphenationFrequency = params.getHyphenationFrequency();
        this.f7579d = hyphenationFrequency;
    }

    public e(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i7, int i8) {
        PrecomputedText.Params.Builder breakStrategy;
        PrecomputedText.Params.Builder hyphenationFrequency;
        PrecomputedText.Params.Builder textDirection;
        if (Build.VERSION.SDK_INT >= 29) {
            breakStrategy = androidx.media.o.j(textPaint).setBreakStrategy(i7);
            hyphenationFrequency = breakStrategy.setHyphenationFrequency(i8);
            textDirection = hyphenationFrequency.setTextDirection(textDirectionHeuristic);
            textDirection.build();
        }
        this.f7576a = textPaint;
        this.f7577b = textDirectionHeuristic;
        this.f7578c = i7;
        this.f7579d = i8;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (this.f7578c == eVar.f7578c && this.f7579d == eVar.f7579d) {
            TextPaint textPaint = this.f7576a;
            float textSize = textPaint.getTextSize();
            TextPaint textPaint2 = eVar.f7576a;
            return textSize == textPaint2.getTextSize() && textPaint.getTextScaleX() == textPaint2.getTextScaleX() && textPaint.getTextSkewX() == textPaint2.getTextSkewX() && textPaint.getLetterSpacing() == textPaint2.getLetterSpacing() && TextUtils.equals(textPaint.getFontFeatureSettings(), textPaint2.getFontFeatureSettings()) && textPaint.getFlags() == textPaint2.getFlags() && textPaint.getTextLocales().equals(textPaint2.getTextLocales()) && (textPaint.getTypeface() != null ? textPaint.getTypeface().equals(textPaint2.getTypeface()) : textPaint2.getTypeface() == null) && this.f7577b == eVar.f7577b;
        }
        return false;
    }

    public final int hashCode() {
        TextPaint textPaint = this.f7576a;
        return o0.b.b(Float.valueOf(textPaint.getTextSize()), Float.valueOf(textPaint.getTextScaleX()), Float.valueOf(textPaint.getTextSkewX()), Float.valueOf(textPaint.getLetterSpacing()), Integer.valueOf(textPaint.getFlags()), textPaint.getTextLocales(), textPaint.getTypeface(), Boolean.valueOf(textPaint.isElegantTextHeight()), this.f7577b, Integer.valueOf(this.f7578c), Integer.valueOf(this.f7579d));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        StringBuilder sb2 = new StringBuilder("textSize=");
        TextPaint textPaint = this.f7576a;
        sb2.append(textPaint.getTextSize());
        sb.append(sb2.toString());
        sb.append(", textScaleX=" + textPaint.getTextScaleX());
        sb.append(", textSkewX=" + textPaint.getTextSkewX());
        sb.append(", letterSpacing=" + textPaint.getLetterSpacing());
        sb.append(", elegantTextHeight=" + textPaint.isElegantTextHeight());
        sb.append(", textLocale=" + textPaint.getTextLocales());
        sb.append(", typeface=" + textPaint.getTypeface());
        sb.append(", variationSettings=" + textPaint.getFontVariationSettings());
        sb.append(", textDir=" + this.f7577b);
        sb.append(", breakStrategy=" + this.f7578c);
        sb.append(", hyphenationFrequency=" + this.f7579d);
        sb.append("}");
        return sb.toString();
    }
}
