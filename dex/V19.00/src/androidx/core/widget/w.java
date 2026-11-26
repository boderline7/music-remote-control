package androidx.core.widget;

import android.graphics.Paint;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.widget.TextView;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class w {
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;

    public static n0.e a(TextView textView) {
        TextDirectionHeuristic textDirectionHeuristic;
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 28) {
            return new n0.e(u.b(textView));
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        TextDirectionHeuristic textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        int a7 = s.a(textView);
        int d7 = s.d(textView);
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else {
            if (i7 < 28 || (textView.getInputType() & 15) != 3) {
                boolean z6 = r.b(textView) == 1;
                switch (r.c(textView)) {
                    case 2:
                        textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                        break;
                    case 3:
                        textDirectionHeuristic = TextDirectionHeuristics.LTR;
                        break;
                    case 4:
                        textDirectionHeuristic = TextDirectionHeuristics.RTL;
                        break;
                    case 5:
                        textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                        break;
                    case 6:
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                        break;
                    case 7:
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                        break;
                    default:
                        if (!z6) {
                            textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                            break;
                        } else {
                            textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                            break;
                        }
                }
            } else {
                byte directionality = Character.getDirectionality(u.a(t.a(r.d(textView)))[0].codePointAt(0));
                textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            }
        }
        return new n0.e(textPaint, textDirectionHeuristic, a7, d7);
    }

    public static void b(TextView textView, int i7) {
        z.u(i7);
        if (Build.VERSION.SDK_INT >= 28) {
            u.c(textView, i7);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i8 = q.a(textView) ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i7 > Math.abs(i8)) {
            textView.setPadding(textView.getPaddingLeft(), i7 + i8, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void c(TextView textView, int i7) {
        z.u(i7);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i8 = q.a(textView) ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i7 > Math.abs(i8)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i7 - i8);
        }
    }

    public static ActionMode.Callback d(ActionMode.Callback callback) {
        return callback instanceof v ? ((v) callback).f1823a : callback;
    }

    public static ActionMode.Callback e(ActionMode.Callback callback, TextView textView) {
        return (Build.VERSION.SDK_INT > 27 || (callback instanceof v) || callback == null) ? callback : new v(callback, textView);
    }
}
