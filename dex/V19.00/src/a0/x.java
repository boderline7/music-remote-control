package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    public final float f188a;

    /* renamed from: b, reason: collision with root package name */
    public final float f189b;

    /* renamed from: c, reason: collision with root package name */
    public final float f190c;

    /* renamed from: d, reason: collision with root package name */
    public final float f191d;

    /* renamed from: e, reason: collision with root package name */
    public final int f192e;

    public x(Context context, XmlResourceParser xmlResourceParser) {
        this.f188a = Float.NaN;
        this.f189b = Float.NaN;
        this.f190c = Float.NaN;
        this.f191d = Float.NaN;
        this.f192e = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.Variant);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.Variant_constraints) {
                int resourceId = obtainStyledAttributes.getResourceId(index, this.f192e);
                this.f192e = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                "layout".equals(resourceTypeName);
            } else if (index == t.Variant_region_heightLessThan) {
                this.f191d = obtainStyledAttributes.getDimension(index, this.f191d);
            } else if (index == t.Variant_region_heightMoreThan) {
                this.f189b = obtainStyledAttributes.getDimension(index, this.f189b);
            } else if (index == t.Variant_region_widthLessThan) {
                this.f190c = obtainStyledAttributes.getDimension(index, this.f190c);
            } else if (index == t.Variant_region_widthMoreThan) {
                this.f188a = obtainStyledAttributes.getDimension(index, this.f188a);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public final boolean a(float f7, float f8) {
        float f9 = this.f188a;
        if (!Float.isNaN(f9) && f7 < f9) {
            return false;
        }
        float f10 = this.f189b;
        if (!Float.isNaN(f10) && f8 < f10) {
            return false;
        }
        float f11 = this.f190c;
        if (!Float.isNaN(f11) && f7 > f11) {
            return false;
        }
        float f12 = this.f191d;
        return Float.isNaN(f12) || f8 <= f12;
    }
}
