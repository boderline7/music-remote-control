package q1;

import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends SingleLineTransformationMethod {

    /* renamed from: a, reason: collision with root package name */
    public Locale f8434a;

    @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        CharSequence transformation = super.getTransformation(charSequence, view);
        if (transformation != null) {
            return transformation.toString().toUpperCase(this.f8434a);
        }
        return null;
    }
}
