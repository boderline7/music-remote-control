package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public final float f58a;

    /* renamed from: b, reason: collision with root package name */
    public final float f59b;

    /* renamed from: c, reason: collision with root package name */
    public final float f60c;

    /* renamed from: d, reason: collision with root package name */
    public final float f61d;

    /* renamed from: e, reason: collision with root package name */
    public final int f62e;

    /* renamed from: f, reason: collision with root package name */
    public final p f63f;

    public h(Context context, XmlResourceParser xmlResourceParser) {
        this.f58a = Float.NaN;
        this.f59b = Float.NaN;
        this.f60c = Float.NaN;
        this.f61d = Float.NaN;
        this.f62e = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.Variant);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.Variant_constraints) {
                int resourceId = obtainStyledAttributes.getResourceId(index, this.f62e);
                this.f62e = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                if ("layout".equals(resourceTypeName)) {
                    p pVar = new p();
                    this.f63f = pVar;
                    pVar.e((ConstraintLayout) LayoutInflater.from(context).inflate(resourceId, (ViewGroup) null));
                }
            } else if (index == t.Variant_region_heightLessThan) {
                this.f61d = obtainStyledAttributes.getDimension(index, this.f61d);
            } else if (index == t.Variant_region_heightMoreThan) {
                this.f59b = obtainStyledAttributes.getDimension(index, this.f59b);
            } else if (index == t.Variant_region_widthLessThan) {
                this.f60c = obtainStyledAttributes.getDimension(index, this.f60c);
            } else if (index == t.Variant_region_widthMoreThan) {
                this.f58a = obtainStyledAttributes.getDimension(index, this.f58a);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final boolean a(float f7, float f8) {
        float f9 = this.f58a;
        if (!Float.isNaN(f9) && f7 < f9) {
            return false;
        }
        float f10 = this.f59b;
        if (!Float.isNaN(f10) && f8 < f10) {
            return false;
        }
        float f11 = this.f60c;
        if (!Float.isNaN(f11) && f7 > f11) {
            return false;
        }
        float f12 = this.f61d;
        return Float.isNaN(f12) || f8 <= f12;
    }
}
