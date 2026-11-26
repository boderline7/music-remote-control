package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w {

    /* renamed from: a, reason: collision with root package name */
    public final int f185a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f186b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final int f187c;

    public w(Context context, XmlResourceParser xmlResourceParser) {
        this.f187c = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), t.State);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.State_android_id) {
                this.f185a = obtainStyledAttributes.getResourceId(index, this.f185a);
            } else if (index == t.State_constraints) {
                int resourceId = obtainStyledAttributes.getResourceId(index, this.f187c);
                this.f187c = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                "layout".equals(resourceTypeName);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
