package a0;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    public boolean f146a;

    /* renamed from: b, reason: collision with root package name */
    public int f147b;

    /* renamed from: c, reason: collision with root package name */
    public int f148c;

    /* renamed from: d, reason: collision with root package name */
    public float f149d;

    /* renamed from: e, reason: collision with root package name */
    public float f150e;

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, t.PropertySet);
        this.f146a = true;
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == t.PropertySet_android_alpha) {
                this.f149d = obtainStyledAttributes.getFloat(index, this.f149d);
            } else if (index == t.PropertySet_android_visibility) {
                int i8 = obtainStyledAttributes.getInt(index, this.f147b);
                this.f147b = i8;
                this.f147b = p.f166g[i8];
            } else if (index == t.PropertySet_visibilityMode) {
                this.f148c = obtainStyledAttributes.getInt(index, this.f148c);
            } else if (index == t.PropertySet_motionProgress) {
                this.f150e = obtainStyledAttributes.getFloat(index, this.f150e);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
